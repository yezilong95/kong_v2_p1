import type { FastifyInstance } from "fastify";
import type { WebSocket } from "@fastify/websocket";
import { getDeviceByToken, setDeviceOnline } from "../services/device.service.js";
import type { DeviceInboundMessage } from "../types/messages.js";
import { connectionManager } from "./connection-manager.js";

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

export async function registerDeviceScreenWsRoutes(app: FastifyInstance) {
  app.get("/ws/device/screen", { websocket: true }, (socket: WebSocket, request) => {
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
      connectionManager.addDeviceScreen(deviceId, socket);

      if (!connectionManager.getDeviceSocket(deviceId)) {
        await setDeviceOnline(deviceId, true);
        connectionManager.broadcastToAdmins({
          type: "device_online",
          deviceId,
          payload: {
            model: device.model,
            androidId: device.androidId,
            channel: "screen",
          },
          timestamp: Date.now(),
        });
      }

      app.log.info({ deviceId }, "device screen channel connected");
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

      if (message.type === "screen_frame") {
        const payload = message.payload;
        const data = typeof payload?.data === "string" ? payload.data : "";
        if (!data) {
          return;
        }

        connectionManager.sendScreenFrameToWatchers(
          deviceId,
          {
            data,
            screenWidth: payload?.screenWidth,
            screenHeight: payload?.screenHeight,
            imageWidth: payload?.imageWidth,
            imageHeight: payload?.imageHeight,
            source: payload?.source,
            mimeType: payload?.mimeType,
            capturedAt: payload?.capturedAt ?? Date.now(),
          },
          Date.now(),
        );
        return;
      }

      if (message.type === "screen_error") {
        connectionManager.sendScreenErrorToWatchers(
          deviceId,
          {
            errorCode: message.payload?.errorCode,
            message: message.payload?.message,
          },
          Date.now(),
        );
        return;
      }

      if (message.type === "screen_status") {
        connectionManager.sendScreenStatusToWatchers(
          deviceId,
          {
            status: message.payload?.status,
            detail: message.payload?.detail,
          },
          Date.now(),
        );
        return;
      }

      if (message.type === "ui_tree_push") {
        const payload = (message.payload ?? {}) as Record<string, unknown>;
        connectionManager.sendUiTreePushToWatchers(deviceId, payload);
        return;
      }

      if (
        message.type === "webrtc_offer" ||
        message.type === "webrtc_answer" ||
        message.type === "webrtc_ice" ||
        message.type === "webrtc_hangup"
      ) {
        connectionManager.sendScreenToWatchersRaw(deviceId, {
          type: message.type,
          deviceId,
          payload: message.payload ?? {},
          timestamp: Date.now(),
        });
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

      const removed = connectionManager.removeDeviceScreen(deviceId, socket);
      if (!removed) {
        app.log.info({ deviceId }, "stale device screen socket closed");
        return;
      }

      if (!connectionManager.isDeviceConnected(deviceId)) {
        void setDeviceOnline(deviceId, false);
        connectionManager.broadcastToAdmins({
          type: "device_offline",
          deviceId,
          timestamp: Date.now(),
        });
      }

      app.log.info({ deviceId }, "device screen channel disconnected");
    });
  });
}
