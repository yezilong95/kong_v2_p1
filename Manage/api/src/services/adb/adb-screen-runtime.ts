import type { WebSocket } from "@fastify/websocket";
import { getDeviceAdbSerial, getDeviceAdbStatus, probeDeviceAdbTransport } from "../adb-relay.service.js";
import { adbSessionRegistry, type AdbSessionStatus } from "./adb-session-registry.js";
import { getAdbEndpoint } from "./adb-session-store.js";
import {
  createAdbScreencapWorker,
  createAdbUiTreeWorker,
  normalizeAdbIntervalMs,
  type AdbScreencapWorker,
  type AdbUiTreeWorker,
} from "./adb-workers.js";
import { encodeAdbPngFrame } from "./adb-png-protocol.js";
import { parseUiAutomatorXml } from "./uiautomator-xml-parser.js";
import { connectionManager } from "../../ws/connection-manager.js";

export interface AdbRuntimeStatus {
  deviceId: string;
  connected: boolean;
  serial: string | null;
  /**
   * `none` = 从没配对过；`offline` = 配对过但当前断了（端点在库里，正在等重连）；
   * `connected` = 会话在线。
   *
   * 区分 `none` 和 `offline` 是为了让管理端能提示「正在恢复」，
   * 而不是把「重启中」和「没配对」混成同一个灰按钮。
   */
  status: AdbSessionStatus | "none";
  /** 端点在库里且未失效：服务端会自己把它连回来，不用人工重新配对。 */
  recoverable: boolean;
  phoneEndpoint: string | null;
  lastOkAt: number | null;
  screenshotRunning: boolean;
  uiTreeRunning: boolean;
  screenshotWatchers: number;
  uiTreeWatchers: number;
  lastScreenshotError: string | null;
  lastUiTreeError: string | null;
}

interface DeviceAdbRuntime {
  deviceId: string;
  serial: string;
  screenshot: AdbScreencapWorker | null;
  uiTree: AdbUiTreeWorker | null;
  lastScreenshotError: string | null;
  lastUiTreeError: string | null;
}

const runtimes = new Map<string, DeviceAdbRuntime>();
let disconnectHookInstalled = false;

function ensureDisconnectHook() {
  if (disconnectHookInstalled) {
    return;
  }
  disconnectHookInstalled = true;
  adbSessionRegistry.subscribeDisconnect((event) => {
    handleAdbDisconnected(event.deviceId, event.reason);
  });
}

function getOrCreateRuntime(deviceId: string, serial: string): DeviceAdbRuntime {
  const existing = runtimes.get(deviceId);
  if (existing) {
    existing.serial = serial;
    return existing;
  }
  const runtime: DeviceAdbRuntime = {
    deviceId,
    serial,
    screenshot: null,
    uiTree: null,
    lastScreenshotError: null,
    lastUiTreeError: null,
  };
  runtimes.set(deviceId, runtime);
  return runtime;
}

function sendScreenJson(deviceId: string, message: Record<string, unknown>) {
  connectionManager.sendAdbScreenJsonToWatchers(deviceId, {
    ...message,
    deviceId,
    timestamp: Date.now(),
  });
}

function sendUiJson(deviceId: string, message: Record<string, unknown>) {
  connectionManager.sendAdbUiTreeJsonToWatchers(deviceId, {
    ...message,
    deviceId,
    timestamp: Date.now(),
  });
}

function stopScreenshot(runtime: DeviceAdbRuntime, reason: string) {
  runtime.screenshot?.stop(reason);
  runtime.screenshot = null;
}

function stopUiTree(runtime: DeviceAdbRuntime, reason: string) {
  runtime.uiTree?.stop(reason);
  runtime.uiTree = null;
}

export function handleAdbDisconnected(deviceId: string, reason: string) {
  const runtime = runtimes.get(deviceId);
  if (runtime) {
    stopScreenshot(runtime, reason);
    stopUiTree(runtime, reason);
    runtimes.delete(deviceId);
  }
  sendScreenJson(deviceId, {
    type: "adb_screen_disconnected",
    reason,
  });
  sendUiJson(deviceId, {
    type: "adb_ui_tree_disconnected",
    reason,
  });
}

export async function ensureAdbTransport(deviceId: string): Promise<string> {
  const serial = getDeviceAdbSerial(deviceId);
  if (!serial) {
    throw new Error(`adb_session_missing: deviceId=${deviceId}`);
  }
  const probe = await probeDeviceAdbTransport(deviceId);
  if (!probe.ok) {
    throw new Error(
      `adb_transport_unavailable: deviceId=${deviceId}, serial=${serial}, ${probe.error ?? probe.state}`,
    );
  }
  return serial;
}

export async function startAdbScreenshot(deviceId: string, intervalMs: number): Promise<void> {
  ensureDisconnectHook();
  const serial = await ensureAdbTransport(deviceId);
  const runtime = getOrCreateRuntime(deviceId, serial);
  runtime.lastScreenshotError = null;
  if (!runtime.screenshot) {
    runtime.screenshot = createAdbScreencapWorker({
      serial,
      onFrame(frame) {
        const encoded = encodeAdbPngFrame({
          version: 1,
          seq: frame.seq,
          width: Math.min(frame.width, 65_535),
          height: Math.min(frame.height, 65_535),
          timestamp: frame.timestamp,
          payload: frame.payload,
        });
        connectionManager.sendAdbPngToWatchers(deviceId, encoded);
      },
      onStopped(reason) {
        runtime.lastScreenshotError = reason;
        runtime.screenshot = null;
        sendScreenJson(deviceId, {
          type: "adb_screen_error",
          message: reason,
        });
      },
    });
  }
  runtime.screenshot.start(normalizeAdbIntervalMs(intervalMs));
}

export function stopAdbScreenshotIfUnwatched(deviceId: string) {
  if (!connectionManager.shouldStopAdbScreenshot(deviceId)) {
    return;
  }
  const runtime = runtimes.get(deviceId);
  if (!runtime) {
    return;
  }
  stopScreenshot(runtime, "no_screenshot_watchers");
}

export async function startAdbUiTree(deviceId: string): Promise<void> {
  ensureDisconnectHook();
  const serial = await ensureAdbTransport(deviceId);
  const runtime = getOrCreateRuntime(deviceId, serial);
  runtime.lastUiTreeError = null;
  if (runtime.uiTree?.isRunning()) {
    return;
  }
  runtime.uiTree = createAdbUiTreeWorker({
    serial,
    onXml(capture) {
      const parsed = parseUiAutomatorXml(capture.xml);
      sendUiJson(deviceId, {
        type: "adb_ui_tree_frame",
        payload: {
          ...parsed,
          deviceId,
          capturedAt: Date.now(),
        },
      });
    },
    onStopped(reason) {
      runtime.lastUiTreeError = reason;
      runtime.uiTree = null;
      sendUiJson(deviceId, {
        type: "adb_ui_tree_error",
        message: reason,
      });
    },
  });
  runtime.uiTree.start();
}

export function stopAdbUiTreeIfUnwatched(deviceId: string) {
  if (!connectionManager.shouldStopAdbUiTree(deviceId)) {
    return;
  }
  const runtime = runtimes.get(deviceId);
  if (!runtime) {
    return;
  }
  stopUiTree(runtime, "no_ui_tree_watchers");
}

export async function getAdbRuntimeStatus(deviceId: string): Promise<AdbRuntimeStatus> {
  const session = adbSessionRegistry.get(deviceId);
  const runtime = runtimes.get(deviceId);
  let connected = false;
  if (session && session.status === "connected") {
    const probe = await probeDeviceAdbTransport(deviceId, 2_500);
    connected = probe.ok;
  }
  // 端点还在库里就说明服务端能自己连回来，界面不该催人重新配对
  const endpoint = connected ? null : await getAdbEndpoint(deviceId);
  const { status: registryStatus } = getDeviceAdbStatus(deviceId);
  // 服务重启后会话表是空的，但端点还在库里：那属于「配对过、暂时断了」，
  // 不能报成 none（从没配对），否则界面会催人重新刮码。
  let status = registryStatus;
  if (status === "none" && endpoint) {
    status = "offline";
  } else if (status === "connected" && !connected) {
    // 会话记录还在，但传输已经不通：如实报离线，别让界面显示一个假的「已连接」。
    // 这里只做投影，不动注册表：真把状态改掉的是巡检里的健康探测。
    status = "offline";
  }
  return {
    deviceId,
    connected,
    serial: session?.serial ?? null,
    status,
    recoverable: Boolean(endpoint) && endpoint!.state !== "stale",
    phoneEndpoint: endpoint ? `${endpoint.phoneIp}:${endpoint.connectPort}` : null,
    lastOkAt: endpoint?.lastOkAt ?? null,
    screenshotRunning: runtime?.screenshot?.isRunning() ?? false,
    uiTreeRunning: runtime?.uiTree?.isRunning() ?? false,
    screenshotWatchers: connectionManager.getAdbScreenshotWatcherCount(deviceId),
    uiTreeWatchers: connectionManager.getAdbUiTreeWatcherCount(deviceId),
    lastScreenshotError: runtime?.lastScreenshotError ?? null,
    lastUiTreeError: runtime?.lastUiTreeError ?? null,
  };
}

export function resetAdbScreenRuntimes() {
  for (const runtime of runtimes.values()) {
    stopScreenshot(runtime, "reset");
    stopUiTree(runtime, "reset");
  }
  runtimes.clear();
}

export function attachAdbScreenshotWatcher(socket: WebSocket, deviceId: string): string | null {
  const previous = connectionManager.setAdminAdbScreenWatchTarget(socket, deviceId);
  if (previous && previous !== deviceId) {
    stopAdbScreenshotIfUnwatched(previous);
  }
  return previous;
}

export function detachAdbScreenshotWatcher(socket: WebSocket): string | null {
  const previous = connectionManager.setAdminAdbScreenWatchTarget(socket, null);
  if (previous) {
    stopAdbScreenshotIfUnwatched(previous);
  }
  return previous;
}

export function attachAdbUiTreeWatcher(socket: WebSocket, deviceId: string): string | null {
  const previous = connectionManager.setAdminAdbUiTreeWatchTarget(socket, deviceId);
  if (previous && previous !== deviceId) {
    stopAdbUiTreeIfUnwatched(previous);
  }
  return previous;
}

export function detachAdbUiTreeWatcher(socket: WebSocket): string | null {
  const previous = connectionManager.setAdminAdbUiTreeWatchTarget(socket, null);
  if (previous) {
    stopAdbUiTreeIfUnwatched(previous);
  }
  return previous;
}
