import { v4 as uuidv4 } from "uuid";
import { connectionManager } from "./connection-manager.js";

type ScreenChannelCommand =
  | "start_screen"
  | "stop_screen"
  | "start_camera"
  | "stop_camera"
  | "subscribe_ui_tree"
  | "unsubscribe_ui_tree";

export function sendDeviceScreenCommand(
  deviceId: string,
  cmd: ScreenChannelCommand,
  params: Record<string, unknown> = {},
): boolean {
  const socket = connectionManager.getDeviceScreenSocket(deviceId);
  if (!socket || socket.readyState !== socket.OPEN) {
    return false;
  }

  socket.send(
    JSON.stringify({
      type: "command",
      id: uuidv4(),
      cmd,
      params,
    }),
  );
  return true;
}

export function requestEnsureScreenChannel(deviceId: string): boolean {
  const socket = connectionManager.getDeviceSocket(deviceId);
  if (!socket || socket.readyState !== socket.OPEN) {
    return false;
  }

  socket.send(
    JSON.stringify({
      type: "command",
      id: uuidv4(),
      cmd: "ensure_screen_channel",
      params: {},
    }),
  );
  return true;
}

function sleep(ms: number) {
  return new Promise<void>((resolve) => {
    setTimeout(resolve, ms);
  });
}

export async function waitForDeviceScreenSocket(
  deviceId: string,
  timeoutMs = 5000,
): Promise<boolean> {
  const deadline = Date.now() + timeoutMs;
  while (Date.now() < deadline) {
    const socket = connectionManager.getDeviceScreenSocket(deviceId);
    if (socket && socket.readyState === socket.OPEN) {
      return true;
    }
    await sleep(200);
  }
  return false;
}

export async function resolveDeviceScreenSocket(deviceId: string): Promise<boolean> {
  if (connectionManager.getDeviceScreenSocket(deviceId)) {
    return true;
  }
  requestEnsureScreenChannel(deviceId);
  return waitForDeviceScreenSocket(deviceId);
}

export function stopScreenForDevice(deviceId: string) {
  sendDeviceScreenCommand(deviceId, "stop_screen");
}

export function stopCameraForDevice(deviceId: string) {
  sendDeviceScreenCommand(deviceId, "stop_camera");
}

export function stopStreamingForDevice(deviceId: string) {
  stopScreenForDevice(deviceId);
  stopCameraForDevice(deviceId);
}
