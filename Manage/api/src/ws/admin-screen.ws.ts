import type { FastifyInstance } from "fastify";
import type { WebSocket } from "@fastify/websocket";
import { connectionManager } from "./connection-manager.js";
import {
  resolveDeviceScreenSocket,
  sendDeviceScreenCommand,
  stopStreamingForDevice,
} from "./screen-control.js";
import {
  attachAdbUiTreeWatcher,
  detachAdbScreenshotWatcher,
  detachAdbUiTreeWatcher,
  startAdbUiTree,
  stopAdbScreenshotIfUnwatched,
  stopAdbUiTreeIfUnwatched,
} from "../services/adb/adb-screen-runtime.js";
import { getIceServers } from "../services/webrtc-ice.service.js";

interface AdminSubscribeScreenMessage {
  type: "subscribe_screen";
  deviceId: string;
  fps?: number;
  intervalMs?: number;
  mode?: "screenshot" | "projection" | "adb_screenshot" | "screenhelper";
}

interface AdminUnsubscribeScreenMessage {
  type: "unsubscribe_screen";
}

interface AdminSubscribeCameraMessage {
  type: "subscribe_camera";
  deviceId: string;
  facing: "front" | "back";
  fps?: number;
}

interface AdminUnsubscribeCameraMessage {
  type: "unsubscribe_camera";
}

interface AdminSubscribeAdbUiTreeMessage {
  type: "subscribe_adb_ui_tree";
  deviceId: string;
}

interface AdminUnsubscribeAdbUiTreeMessage {
  type: "unsubscribe_adb_ui_tree";
}

interface AdminSubscribeUiTreeMessage {
  type: "subscribe_ui_tree";
  deviceId: string;
}

interface AdminUnsubscribeUiTreeMessage {
  type: "unsubscribe_ui_tree";
}

interface AdminWebrtcSignalMessage {
  type: "webrtc_offer" | "webrtc_answer" | "webrtc_ice" | "webrtc_hangup";
  deviceId: string;
  payload?: Record<string, unknown>;
}

type AdminScreenInboundMessage =
  | AdminSubscribeScreenMessage
  | AdminUnsubscribeScreenMessage
  | AdminSubscribeCameraMessage
  | AdminUnsubscribeCameraMessage
  | AdminSubscribeAdbUiTreeMessage
  | AdminUnsubscribeAdbUiTreeMessage
  | AdminSubscribeUiTreeMessage
  | AdminUnsubscribeUiTreeMessage
  | AdminWebrtcSignalMessage;

function parseAdminScreenMessage(
  raw: Buffer | ArrayBuffer | Buffer[],
): AdminScreenInboundMessage | null {
  try {
    const text = Buffer.isBuffer(raw)
      ? raw.toString("utf8")
      : raw instanceof ArrayBuffer
        ? Buffer.from(raw).toString("utf8")
        : Buffer.concat(raw).toString("utf8");
    return JSON.parse(text) as AdminScreenInboundMessage;
  } catch {
    return null;
  }
}

function stopDeviceStreamIfUnwatched(deviceId: string) {
  if (connectionManager.shouldStopDeviceScreen(deviceId)) {
    stopStreamingForDevice(deviceId);
  }
}

function detachProjectionWatch(socket: WebSocket) {
  const previous = connectionManager.setAdminScreenWatchTarget(socket, null);
  if (previous) {
    stopDeviceStreamIfUnwatched(previous);
  }
  return previous;
}

export async function registerAdminScreenWsRoutes(app: FastifyInstance) {
  app.get("/ws/admin/screen", { websocket: true }, (socket: WebSocket) => {
    connectionManager.addAdminScreen(socket);

    socket.on("message", (raw: Buffer | ArrayBuffer | Buffer[]) => {
      void (async () => {
        const message = parseAdminScreenMessage(raw);
        if (!message) {
          socket.send(JSON.stringify({ type: "error", message: "invalid_json" }));
          return;
        }

        if (message.type === "subscribe_screen") {
          const deviceId = message.deviceId?.trim();
          if (!deviceId) {
            socket.send(JSON.stringify({ type: "error", message: "device_id_required" }));
            return;
          }

          detachAdbScreenshotWatcher(socket);

          if (!connectionManager.hasDeviceChannel(deviceId)) {
            socket.send(
              JSON.stringify({
                type: "screen_subscribed",
                deviceId,
                started: false,
                message: "device_offline",
                timestamp: Date.now(),
              }),
            );
            return;
          }

          const screenReady = await resolveDeviceScreenSocket(deviceId);
          if (!screenReady) {
            socket.send(
              JSON.stringify({
                type: "screen_subscribed",
                deviceId,
                started: false,
                message: "screen_channel_offline",
                timestamp: Date.now(),
              }),
            );
            return;
          }

          const previous = connectionManager.setAdminScreenWatchTarget(socket, deviceId);
          if (previous && previous !== deviceId) {
            stopDeviceStreamIfUnwatched(previous);
          }

          const fps = Math.min(Math.max(message.fps ?? 4, 1), 5);
          const mode =
            message.mode === "projection" || message.mode === "adb_screenshot"
              ? "projection"
              : message.mode === "screenhelper"
                ? "screenhelper"
                : "screenshot";
          const iceServers = getIceServers();
          // projection / screenshot / screenhelper all prefer WebRTC; device falls back to JPEG.
          const transport = "webrtc";
          const started = sendDeviceScreenCommand(deviceId, "start_screen", {
            fps,
            mode,
            transport,
            iceServers,
          });
          if (!started) {
            socket.send(
              JSON.stringify({
                type: "screen_subscribed",
                deviceId,
                fps,
                mode,
                transport,
                started: false,
                message: "start_screen_send_failed",
                timestamp: Date.now(),
              }),
            );
            return;
          }

          socket.send(
            JSON.stringify({
              type: "screen_subscribed",
              deviceId,
              fps,
              mode,
              transport,
              iceServers,
              started,
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (message.type === "subscribe_adb_ui_tree") {
          const deviceId = message.deviceId?.trim();
          if (!deviceId) {
            socket.send(JSON.stringify({ type: "error", message: "device_id_required" }));
            return;
          }
          attachAdbUiTreeWatcher(socket, deviceId);
          try {
            await startAdbUiTree(deviceId);
            socket.send(
              JSON.stringify({
                type: "adb_ui_tree_subscribed",
                deviceId,
                started: true,
                timestamp: Date.now(),
              }),
            );
          } catch (error) {
            const reason = error instanceof Error ? error.message : "adb_ui_tree_start_failed";
            socket.send(
              JSON.stringify({
                type: "adb_ui_tree_subscribed",
                deviceId,
                started: false,
                message: reason,
                timestamp: Date.now(),
              }),
            );
          }
          return;
        }

        if (message.type === "unsubscribe_adb_ui_tree") {
          const previous = detachAdbUiTreeWatcher(socket);
          socket.send(
            JSON.stringify({
              type: "adb_ui_tree_unsubscribed",
              deviceId: previous,
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (message.type === "subscribe_ui_tree") {
          const deviceId = message.deviceId?.trim();
          if (!deviceId) {
            socket.send(JSON.stringify({ type: "error", message: "device_id_required" }));
            return;
          }
          connectionManager.setAdminUiTreeWatchTarget(socket, deviceId);
          // 新订阅者：若最近有缓存快照，立即补帧，无需等设备下一次事件。
          connectionManager.replayUiTreeSnapshotTo(socket, deviceId);

          const screenReady = await resolveDeviceScreenSocket(deviceId);
          if (!screenReady) {
            socket.send(
              JSON.stringify({
                type: "ui_tree_subscribed",
                deviceId,
                started: false,
                message: "screen_channel_offline",
                timestamp: Date.now(),
              }),
            );
            return;
          }
          const sent = sendDeviceScreenCommand(deviceId, "subscribe_ui_tree", {});
          socket.send(
            JSON.stringify({
              type: "ui_tree_subscribed",
              deviceId,
              started: sent,
              message: sent ? undefined : "subscribe_ui_tree_send_failed",
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (message.type === "unsubscribe_ui_tree") {
          const previous = connectionManager.setAdminUiTreeWatchTarget(socket, null);
          if (previous && connectionManager.shouldStopUiTreePush(previous)) {
            // 没人再看了：告诉设备停推。失败不致命（设备侧会按自身超时清掉）。
            sendDeviceScreenCommand(previous, "unsubscribe_ui_tree", {});
          }
          socket.send(
            JSON.stringify({
              type: "ui_tree_unsubscribed",
              deviceId: previous,
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (message.type === "subscribe_camera") {
          const deviceId = message.deviceId?.trim();
          const facing = message.facing === "front" ? "front" : "back";
          if (!deviceId) {
            socket.send(JSON.stringify({ type: "error", message: "device_id_required" }));
            return;
          }

          detachAdbScreenshotWatcher(socket);

          if (!connectionManager.hasDeviceChannel(deviceId)) {
            socket.send(
              JSON.stringify({
                type: "camera_subscribed",
                deviceId,
                facing,
                started: false,
                message: "device_offline",
                timestamp: Date.now(),
              }),
            );
            return;
          }

          const screenReady = await resolveDeviceScreenSocket(deviceId);
          if (!screenReady) {
            socket.send(
              JSON.stringify({
                type: "camera_subscribed",
                deviceId,
                facing,
                started: false,
                message: "screen_channel_offline",
                timestamp: Date.now(),
              }),
            );
            return;
          }

          const previous = connectionManager.setAdminScreenWatchTarget(socket, deviceId);
          if (previous && previous !== deviceId) {
            stopDeviceStreamIfUnwatched(previous);
          }

          const fps = Math.min(Math.max(message.fps ?? 4, 1), 5);
          const started = sendDeviceScreenCommand(deviceId, "start_camera", { facing, fps });
          if (!started) {
            socket.send(
              JSON.stringify({
                type: "camera_subscribed",
                deviceId,
                facing,
                fps,
                started: false,
                message: "start_camera_send_failed",
                timestamp: Date.now(),
              }),
            );
            return;
          }

          socket.send(
            JSON.stringify({
              type: "camera_subscribed",
              deviceId,
              facing,
              fps,
              started,
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (message.type === "unsubscribe_screen") {
          detachAdbScreenshotWatcher(socket);
          const previous = detachProjectionWatch(socket);
          socket.send(
            JSON.stringify({
              type: "screen_unsubscribed",
              deviceId: previous,
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (message.type === "unsubscribe_camera") {
          const previous = detachProjectionWatch(socket);
          socket.send(
            JSON.stringify({
              type: "camera_unsubscribed",
              deviceId: previous,
              timestamp: Date.now(),
            }),
          );
          return;
        }

        if (
          message.type === "webrtc_offer" ||
          message.type === "webrtc_answer" ||
          message.type === "webrtc_ice" ||
          message.type === "webrtc_hangup"
        ) {
          const deviceId = message.deviceId?.trim();
          if (!deviceId) {
            socket.send(JSON.stringify({ type: "error", message: "device_id_required" }));
            return;
          }
          const screenSocket = connectionManager.getDeviceScreenSocket(deviceId);
          if (!screenSocket || screenSocket.readyState !== screenSocket.OPEN) {
            socket.send(
              JSON.stringify({
                type: "error",
                message: "screen_channel_offline",
                timestamp: Date.now(),
              }),
            );
            return;
          }
          screenSocket.send(
            JSON.stringify({
              type: message.type,
              payload: message.payload ?? {},
              timestamp: Date.now(),
            }),
          );
        }
      })();
    });

    socket.on("close", () => {
      const watched = connectionManager.removeAdminScreen(socket);
      if (watched.projectionDeviceId) {
        stopDeviceStreamIfUnwatched(watched.projectionDeviceId);
      }
      if (watched.adbScreenDeviceId) {
        stopAdbScreenshotIfUnwatched(watched.adbScreenDeviceId);
      }
      if (watched.adbUiDeviceId) {
        stopAdbUiTreeIfUnwatched(watched.adbUiDeviceId);
      }
      if (watched.uiTreeDeviceId && connectionManager.shouldStopUiTreePush(watched.uiTreeDeviceId)) {
        sendDeviceScreenCommand(watched.uiTreeDeviceId, "unsubscribe_ui_tree", {});
      }
    });
  });
}
