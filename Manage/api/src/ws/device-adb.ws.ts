import type { FastifyInstance } from "fastify";
import type { WebSocket } from "@fastify/websocket";
import { getDeviceByToken } from "../services/device.service.js";
import { connectionManager } from "./connection-manager.js";
import { onDeviceAdbSocket } from "../services/adb-relay.service.js";

/**
 * 独立 ADB 中继通道。不得与 /ws/device、/ws/device/screen 混用二进制帧。
 * 鉴权与控制通道相同；连接本身不改变设备 online 语义。
 */
export async function registerDeviceAdbWsRoutes(app: FastifyInstance) {
  app.get("/ws/device/adb", { websocket: true }, (socket: WebSocket, request) => {
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
      connectionManager.addDeviceAdb(deviceId, socket);
      onDeviceAdbSocket(deviceId, socket);
      app.log.info({ deviceId }, "device adb relay connected");
    })();

    socket.on("message", (raw: Buffer | ArrayBuffer | Buffer[]) => {
      if (!isReady || !deviceId) {
        return;
      }
      const buffer = Buffer.isBuffer(raw)
        ? raw
        : raw instanceof ArrayBuffer
          ? Buffer.from(raw)
          : Buffer.concat(raw);
      // 二进制帧交给 relay hub；非帧文本忽略（预留）
      onDeviceAdbSocket(deviceId, socket, buffer);
    });

    socket.on("close", () => {
      if (!deviceId) {
        return;
      }
      // 被新连接顶掉的旧 socket 会走到这里。它已经不是当前会话了，
      // 再调一次断开回调会把刚建好的连接判成离线，所以要看移除结果。
      const removed = connectionManager.removeDeviceAdb(deviceId, socket);
      if (!removed) {
        return;
      }
      onDeviceAdbSocket(deviceId, null);
      app.log.info({ deviceId }, "device adb relay disconnected");
    });
  });
}
