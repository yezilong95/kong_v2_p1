import net from "node:net";
import type { WebSocket } from "@fastify/websocket";
import { sendCommand } from "./command.service.js";
import { connectionManager } from "../ws/connection-manager.js";
import { executeAdbText } from "./adb/adb-executor.js";
import {
  connectAdbUntilAccepted,
  resolveAdbRelayConnectPort,
  waitUntilAdbTransportReady,
} from "./adb/adb-connect-ready.js";
import {
  adbSessionRegistry,
  type AdbSession,
  type AdbSessionStatus,
} from "./adb/adb-session-registry.js";
import {
  resolveRemoteAccessibilityComponent,
  readConfiguredClientPackageName,
} from "./adb/adb-enable-accessibility.js";
import { grantClientPermissions, type GrantResult } from "./adb/adb-grant-permissions.js";
import {
  deleteAdbEndpoint,
  getAdbEndpoint,
  recordAdbEndpointFailure,
  saveAdbEndpoint,
  setAdbEndpointState,
} from "./adb/adb-session-store.js";

const MAGIC = 0xa1;
const VERSION = 1;
const TYPE_OPEN = 1;
const TYPE_DATA = 2;
const TYPE_CLOSE = 3;
const TYPE_ERR = 4;

function encodeFrame(type: number, streamId: number, payload: Buffer = Buffer.alloc(0)): Buffer {
  const header = Buffer.alloc(12);
  header.writeUInt8(MAGIC, 0);
  header.writeUInt8(VERSION, 1);
  header.writeUInt8(type & 0xff, 2);
  header.writeUInt8(0, 3);
  header.writeUInt32BE(streamId >>> 0, 4);
  header.writeUInt32BE(payload.length >>> 0, 8);
  return Buffer.concat([header, payload]);
}

function decodeFrame(buf: Buffer): { type: number; streamId: number; payload: Buffer } | null {
  if (buf.length < 12) {
    return null;
  }
  if (buf.readUInt8(0) !== MAGIC || buf.readUInt8(1) !== VERSION) {
    return null;
  }
  const type = buf.readUInt8(2);
  const streamId = buf.readUInt32BE(4);
  const len = buf.readUInt32BE(8);
  if (len < 0 || len > 4 * 1024 * 1024 || buf.length < 12 + len) {
    return null;
  }
  return {
    type,
    streamId,
    payload: buf.subarray(12, 12 + len),
  };
}

type ReadyResolver = (ok: boolean) => void;

interface StreamPipe {
  streamId: number;
  role: "pair" | "connect";
  remoteHost: string;
  remotePort: number;
  localPort: number;
  server: net.Server;
  sockets: Set<net.Socket>;
  remoteReady: boolean;
  pending: Buffer[];
  readyResolvers: ReadyResolver[];
}

interface DeviceRelayHub {
  deviceId: string;
  streams: Map<number, StreamPipe>;
  ws: WebSocket | null;
}

const hubs = new Map<string, DeviceRelayHub>();
/** 正在走完整配对流程的设备。恢复逻辑要避让，否则会和配对抢同一个 hub。 */
const pairingInFlight = new Set<string>();

/**
 * 手机自己把中继 WS 挂回来时的回调。
 *
 * 由 adb-restore-bootstrap 注入，而不是直接 import：那个模块反过来要
 * 用这里的 restoreDeviceAdb，写成静态 import 就成了循环依赖。
 */
let onOrphanAdbSocket: ((deviceId: string) => void) | null = null;

export function setOrphanAdbSocketHandler(handler: ((deviceId: string) => void) | null): void {
  onOrphanAdbSocket = handler;
}

/**
 * 重新配对成功后的回调。
 *
 * 恢复调度里记着「这台设备失败次数用完，不再自动重试」。人工重配之后
 * 端点已经换了新的，那个结论过期了，必须让它把标记清掉。
 */
let onAdbEndpointRepaired: ((deviceId: string) => void) | null = null;

export function setAdbEndpointRepairedHandler(
  handler: ((deviceId: string) => void) | null,
): void {
  onAdbEndpointRepaired = handler;
}

function markStreamReady(pipe: StreamPipe, ok: boolean) {
  if (!ok) {
    pipe.remoteReady = false;
    pipe.pending.length = 0;
  }
  const resolvers = pipe.readyResolvers.splice(0, pipe.readyResolvers.length);
  for (const resolve of resolvers) {
    resolve(ok);
  }
}

export function onDeviceAdbSocket(
  deviceId: string,
  socket: WebSocket | null,
  binary?: Buffer,
): void {
  const hub = hubs.get(deviceId);
  if (socket === null) {
    if (hub) {
      hub.ws = null;
    }
    // 只标记离线，不删会话：端点还存在，手机回来时可以免配对重连。
    // 删掉就分不清「从没连过」和「连过但现在断了」。
    adbSessionRegistry.markOffline(deviceId, "adb_ws_disconnected");
    return;
  }
  if (!hub) {
    // 手机自己把中继 WS 挂回来了，但服务端还没有 hub（典型场景：服务刚重启、
    // 客户端自动重连成功）。这时需要有人建流并 adb connect，否则这条 WS 空转。
    onOrphanAdbSocket?.(deviceId);
    return;
  }
  hub.ws = socket;
  // hub 还在但会话已经翻成离线（比如服务端重启前留下的半死状态）：
  // WS 回来了不代表 adb 链路能用，仍然要重建一次。延后执行，
  // 让 addDeviceAdb 先把这台设备的槽位换成本次连接。
  if (adbSessionRegistry.get(deviceId)?.status === "offline") {
    queueMicrotask(() => onOrphanAdbSocket?.(deviceId));
  }
  if (!binary) {
    return;
  }
  const frame = decodeFrame(binary);
  if (!frame) {
    return;
  }
  const pipe = hub.streams.get(frame.streamId);
  if (!pipe) {
    return;
  }
  if (frame.type === TYPE_OPEN) {
    const text = frame.payload.toString("utf8");
    const ok = /"ok"\s*:\s*true/.test(text) || text.length === 0;
    markStreamReady(pipe, ok);
    return;
  }
  if (frame.type === TYPE_DATA) {
    for (const sock of pipe.sockets) {
      if (!sock.destroyed) {
        sock.write(frame.payload);
      }
    }
    return;
  }
  if (frame.type === TYPE_CLOSE || frame.type === TYPE_ERR) {
    markStreamReady(pipe, false);
    for (const sock of pipe.sockets) {
      sock.destroy();
    }
    pipe.sockets.clear();
  }
}

function listenEphemeral(): Promise<{ server: net.Server; port: number }> {
  return new Promise((resolve, reject) => {
    const server = net.createServer();
    server.listen(0, "127.0.0.1", () => {
      const address = server.address();
      if (!address || typeof address === "string") {
        reject(new Error("listen_failed"));
        return;
      }
      resolve({ server, port: address.port });
    });
    server.on("error", reject);
  });
}

function sendToDevice(hub: DeviceRelayHub, frame: Buffer) {
  const ws = hub.ws ?? connectionManager.getDeviceAdbSocket(hub.deviceId);
  if (!ws || ws.readyState !== ws.OPEN) {
    return false;
  }
  ws.send(frame);
  return true;
}

function waitStreamReady(pipe: StreamPipe, timeoutMs: number): Promise<boolean> {
  if (pipe.remoteReady) {
    return Promise.resolve(true);
  }
  return new Promise((resolve) => {
    const timer = setTimeout(() => {
      const idx = pipe.readyResolvers.indexOf(onReady);
      if (idx >= 0) {
        pipe.readyResolvers.splice(idx, 1);
      }
      resolve(false);
    }, timeoutMs);
    const onReady = (ok: boolean) => {
      clearTimeout(timer);
      resolve(ok);
    };
    pipe.readyResolvers.push(onReady);
  });
}

async function createHub(
  deviceId: string,
  pair: { host: string; port: number } | null,
  connect: { host: string; port: number },
): Promise<DeviceRelayHub> {
  await destroyHub(deviceId);

  const pairListen = pair ? await listenEphemeral() : null;
  const connectListen = await listenEphemeral();

  const hub: DeviceRelayHub = {
    deviceId,
    streams: new Map(),
    ws: connectionManager.getDeviceAdbSocket(deviceId),
  };

  const attach = (
    streamId: number,
    role: "pair" | "connect",
    remote: { host: string; port: number },
    local: { server: net.Server; port: number },
  ) => {
    const pipe: StreamPipe = {
      streamId,
      role,
      remoteHost: remote.host,
      remotePort: remote.port,
      localPort: local.port,
      server: local.server,
      sockets: new Set(),
      remoteReady: false,
      pending: [],
      readyResolvers: [],
    };
    local.server.on("connection", (sock) => {
      // 每个新连接重置就绪态（adb pair / connect 各一次）
      pipe.remoteReady = false;
      pipe.pending = [];
      pipe.readyResolvers = [];
      pipe.sockets.add(sock);

      const openPayload = Buffer.from(
        JSON.stringify({ host: remote.host, port: remote.port, role }),
        "utf8",
      );
      sendToDevice(hub, encodeFrame(TYPE_OPEN, streamId, openPayload));

      void (async () => {
        const ready = await waitStreamReady(pipe, 12_000);
        if (!ready) {
          sock.destroy();
          return;
        }
        for (const chunk of pipe.pending) {
          sendToDevice(hub, encodeFrame(TYPE_DATA, streamId, chunk));
        }
        pipe.pending.length = 0;
        pipe.remoteReady = true;
      })();

      sock.on("data", (chunk: Buffer) => {
        if (!pipe.remoteReady) {
          pipe.pending.push(chunk);
          return;
        }
        sendToDevice(hub, encodeFrame(TYPE_DATA, streamId, chunk));
      });
      sock.on("close", () => {
        pipe.sockets.delete(sock);
        sendToDevice(hub, encodeFrame(TYPE_CLOSE, streamId));
      });
      sock.on("error", () => {
        pipe.sockets.delete(sock);
      });
    });
    hub.streams.set(streamId, pipe);
  };

  if (pair) {
    attach(1, "pair", pair, pairListen!);
  }
  attach(2, "connect", connect, connectListen);
  hubs.set(deviceId, hub);
  return hub;
}

export async function destroyHub(deviceId: string): Promise<void> {
  const hub = hubs.get(deviceId);
  if (!hub) {
    return;
  }
  hubs.delete(deviceId);
  adbSessionRegistry.clear(deviceId, "relay_hub_destroyed");
  for (const pipe of hub.streams.values()) {
    markStreamReady(pipe, false);
    for (const sock of pipe.sockets) {
      sock.destroy();
    }
    await new Promise<void>((resolve) => pipe.server.close(() => resolve()));
  }
}

function runAdb(args: string[], timeoutMs: number, operation: string) {
  return executeAdbText(args, { operation, timeoutMs });
}

function sleep(ms: number) {
  return new Promise<void>((resolve) => {
    setTimeout(resolve, ms);
  });
}

function clipAdbOutput(text: string, max = 240): string {
  const oneLine = text.replace(/\s+/g, " ").trim();
  if (oneLine.length <= max) {
    return oneLine;
  }
  return `${oneLine.slice(0, max)}…`;
}

export type AdbPairResult = {
  ok: boolean;
  status: string;
  error?: string;
  prepare?: Record<string, unknown>;
  pairLocalPort?: number;
  connectLocalPort?: number;
  devices?: string;
  adbStdout?: string;
  adbStderr?: string;
  /** 配对成功后顺手授予的能力权限（失败不影响配对结论） */
  grants?: GrantResult;
};

export function getDeviceAdbSession(deviceId: string): AdbSession | null {
  return adbSessionRegistry.get(deviceId);
}

export function getDeviceAdbSerial(deviceId: string): string | null {
  const session = getDeviceAdbSession(deviceId);
  // 离线会话不能用：serial 指的本地临时端口早已随 hub 一起销毁
  if (!session || session.status !== "connected") {
    return null;
  }
  return session.serial;
}

export function getDeviceAdbStatus(deviceId: string): {
  status: AdbSessionStatus | "none";
  serial: string | null;
} {
  const session = adbSessionRegistry.get(deviceId);
  return {
    status: session?.status ?? "none",
    serial: session?.serial ?? null,
  };
}

export interface AdbTransportProbeResult {
  ok: boolean;
  serial: string | null;
  state: string;
  error?: string;
}

export async function probeAdbSerial(
  serial: string,
  timeoutMs = 5_000,
): Promise<AdbTransportProbeResult> {
  try {
    const probe = await executeAdbText(["shell", "echo", "vibelink_adb_probe"], {
      operation: "transport_probe",
      serial,
      timeoutMs,
    });
    const output = `${probe.stdout}\n${probe.stderr}`;
    const ok = probe.code === 0 && output.includes("vibelink_adb_probe");
    return {
      ok,
      serial,
      state: ok ? "device" : "unavailable",
      ...(ok
        ? {}
        : {
            error: `adb_transport_probe_failed: serial=${serial}, code=${probe.code}, stderr=${clipAdbOutput(probe.stderr)}`,
          }),
    };
  } catch (error) {
    return {
      ok: false,
      serial,
      state: "unknown",
      error:
        error instanceof Error
          ? error.message
          : `adb_transport_probe_failed: serial=${serial}`,
    };
  }
}

export async function probeDeviceAdbTransport(
  deviceId: string,
  timeoutMs = 5_000,
): Promise<AdbTransportProbeResult> {
  const serial = getDeviceAdbSerial(deviceId);
  if (!serial) {
    return {
      ok: false,
      serial: null,
      state: "disconnected",
      error: `adb_transport_unavailable: deviceId=${deviceId}, serial is not registered`,
    };
  }
  return probeAdbSerial(serial, timeoutMs);
}

/**
 * 解析客户端包名：优先从设备上真实安装的服务反查（最准），
 * 退回仓库里的 `android-package.properties`。
 *
 * 不能只用配置：马甲包每期换名，配置有时和真机装的不是同一版。
 */
async function resolveClientPackageName(serial: string): Promise<string | null> {
  try {
    const component = await resolveRemoteAccessibilityComponent(serial);
    const pkg = component.slice(0, component.indexOf("/"));
    if (pkg) {
      return pkg;
    }
  } catch {
    // 无障碍组件查不到（服务没注册/未安装）→ 退回配置
  }
  return readConfiguredClientPackageName();
}

/**
 * 配对成功后的顺手授权。
 *
 * 失败绝不能影响配对结论：配对已经成功，此时把整次调用判成失败会让前端
 * 以为没连上，反而把刚建好的会话拆掉。所以这里全包 try/catch。
 */
async function grantPermissionsBestEffort(serial: string): Promise<GrantResult | undefined> {
  try {
    const packageName = await resolveClientPackageName(serial);
    if (!packageName) {
      return undefined;
    }
    return await grantClientPermissions(serial, packageName);
  } catch {
    return undefined;
  }
}

export async function pairDeviceAdb(
  deviceId: string,
  options: { mode?: "auto" | "manual" } = {},
): Promise<AdbPairResult> {
  // 配对过程会 destroyHub + 重建 hub，此时让恢复逻辑避让，
  // 否则并发恢复会把刚建好的流拆掉，表现为「配对成功又立刻掉线」。
  pairingInFlight.add(deviceId);
  try {
    return await pairDeviceAdbInner(deviceId, options);
  } finally {
    pairingInFlight.delete(deviceId);
  }
}

async function pairDeviceAdbInner(
  deviceId: string,
  _options: { mode?: "auto" | "manual" } = {},
): Promise<AdbPairResult> {
  const timeoutMs = Number(process.env.ADB_PAIR_TIMEOUT_MS ?? 90_000);
  // Force manual: scrape only when already on wireless debugging page.
  const mode = "manual" as const;

  try {
    const which = await runAdb(["version"], 8_000, "version_probe");
    if (/ENOENT|not found/i.test(which.stderr)) {
      return { ok: false, status: "failed", error: "adb_not_found" };
    }
    // Android 11+ 无线配对需要 platform-tools >= 30（adb pair）
    const helpProbe = await runAdb(["help"], 5_000, "pair_capability_probe");
    const helpText = `${helpProbe.stdout}\n${helpProbe.stderr}`;
    if (!/\bpair\b/i.test(helpText)) {
      return {
        ok: false,
        status: "failed",
        error:
          "adb_pair_unsupported:服务器 adb 过旧，不支持 pair。请升级 platform-tools≥30，或设置 ADB_BIN 指向新版 adb",
        adbStdout: which.stdout,
        adbStderr: which.stderr,
      };
    }

    const prepareCmd = await sendCommand({
      deviceId,
      // 统一走 prepare + mode；旧包若无 prepare_manual 也不会再被单独打中
      cmd: "adb_wireless_prepare",
      params: { mode },
      waitMs: Math.min(timeoutMs, 90_000),
    });
    if (prepareCmd.status !== "success" || !prepareCmd.result) {
      const err = String(prepareCmd.errorMessage || "prepare_failed");
      // 手机 APK 过旧：不认 mode、也不认 prepare 时给出明确提示
      if (/unsupported_cmd:adb_wireless_prepare/.test(err)) {
        return {
          ok: false,
          status: "failed",
          error:
            "unsupported_cmd:adb_wireless_prepare — 请重新安装含无线 ADB 的 client APK 后再手动匹配",
          prepare: prepareCmd.result as Record<string, unknown> | undefined,
        };
      }
      return {
        ok: false,
        status: "failed",
        error: err,
        prepare: prepareCmd.result as Record<string, unknown> | undefined,
      };
    }

    const result = prepareCmd.result as Record<string, unknown>;
    const ip = String(result.ip ?? "").trim();
    const pairPort = Number(result.pairPort ?? 0);
    const pairCode = String(result.pairCode ?? "").trim();
    const connectPort = Number(result.connectPort ?? 0);
    if (!ip || !pairPort || pairCode.length !== 6) {
      await sendCommand({ deviceId, cmd: "adb_wireless_done", params: {}, waitMs: 5_000 }).catch(() => undefined);
      return { ok: false, status: "failed", error: "scrape_failed", prepare: result };
    }

    const connectTargetPort = resolveAdbRelayConnectPort(pairPort, connectPort);
    if (connectTargetPort == null) {
      await sendCommand({ deviceId, cmd: "adb_wireless_done", params: {}, waitMs: 5_000 }).catch(() => undefined);
      return {
        ok: false,
        status: "failed",
        error: "scrape_failed:connect_port",
        prepare: result,
      };
    }
    const hub = await createHub(
      deviceId,
      { host: ip, port: pairPort },
      { host: ip, port: connectTargetPort },
    );
    const pairLocal = hub.streams.get(1)!.localPort;
    const connectLocal = hub.streams.get(2)!.localPort;

    const relayCmd = await sendCommand({
      deviceId,
      cmd: "adb_relay_start",
      params: {
        openNow: false,
        streams: [
          { streamId: 1, host: ip, port: pairPort, role: "pair" },
          { streamId: 2, host: ip, port: connectTargetPort, role: "connect" },
        ],
      },
      waitMs: 20_000,
    });
    if (relayCmd.status !== "success") {
      await cleanupDevice(deviceId);
      return {
        ok: false,
        status: "failed",
        error: relayCmd.errorMessage || "relay_failed",
        prepare: result,
      };
    }

    const wsReadyDeadline = Date.now() + 10_000;
    while (Date.now() < wsReadyDeadline) {
      if (connectionManager.getDeviceAdbSocket(deviceId)) {
        break;
      }
      await new Promise((r) => setTimeout(r, 200));
    }
    hub.ws = connectionManager.getDeviceAdbSocket(deviceId);
    if (!hub.ws) {
      await cleanupDevice(deviceId);
      return { ok: false, status: "failed", error: "relay_failed", prepare: result };
    }

    // 配对窗口很短：尽快 pair
    const pairRun = await runAdb(
      ["pair", `127.0.0.1:${pairLocal}`, pairCode],
      Math.min(timeoutMs, 45_000),
      "pair",
    );
    if (pairRun.code !== 0) {
      const detail = clipAdbOutput(`${pairRun.stdout} ${pairRun.stderr}`);
      const error =
        /unknown command\s+pair/i.test(detail)
          ? "adb_pair_unsupported:服务器 adb 过旧，不支持 pair。请升级 platform-tools≥30，或设置 ADB_BIN 指向新版 adb"
          : detail
            ? `pair_failed:${detail}`
            : "pair_failed";
      await cleanupDevice(deviceId);
      return {
        ok: false,
        status: "failed",
        error,
        prepare: result,
        pairLocalPort: pairLocal,
        connectLocalPort: connectLocal,
        adbStdout: pairRun.stdout,
        adbStderr: pairRun.stderr,
      };
    }

    const serial = `127.0.0.1:${connectLocal}`;
    const connected = await connectAdbUntilAccepted(
      serial,
      {
        connect: () => runAdb(["connect", serial], 20_000, "connect"),
        disconnect: async () => {
          await runAdb(["disconnect", serial], 8_000, "disconnect_stale");
        },
        sleep,
      },
      { attempts: 5, initialDelayMs: 2_000, delayMs: 1_000 },
    );
    if (!connected.ok) {
      await cleanupDevice(deviceId);
      return {
        ok: false,
        status: "failed",
        error: `${connected.error ?? "connect_failed"}; remote=${ip}:${connectTargetPort}`,
        prepare: result,
        pairLocalPort: pairLocal,
        connectLocalPort: connectLocal,
        adbStdout: connected.output.stdout,
        adbStderr: connected.output.stderr,
      };
    }

    const ready = await waitUntilAdbTransportReady(
      serial,
      {
        probe: () => probeAdbSerial(serial, 4_000),
        connect: () => runAdb(["connect", serial], 20_000, "connect_retry"),
        disconnect: async () => {
          await runAdb(["disconnect", serial], 8_000, "disconnect_stale");
        },
        sleep,
      },
      {
        timeoutMs: 12_000,
        intervalMs: 500,
        reconnectFirst: connected.verdict === "already",
      },
    );
    if (!ready.ok) {
      await cleanupDevice(deviceId);
      return {
        ok: false,
        status: "failed",
        error: ready.error ?? "adb_transport_offline",
        prepare: result,
        pairLocalPort: pairLocal,
        connectLocalPort: connectLocal,
        adbStdout: connected.output.stdout,
        adbStderr: connected.output.stderr,
      };
    }

    adbSessionRegistry.register(deviceId, serial);
    // 端点落库：服务器重启后靠这两个值免配对重连
    await saveAdbEndpoint({
      deviceId,
      phoneIp: ip,
      connectPort: connectTargetPort,
      pairPort,
    });
    // 重新配对会重置端点，恢复调度里的「失败次数用完」标记也要一并清掉
    onAdbEndpointRepaired?.(deviceId);
    const grants = await grantPermissionsBestEffort(serial);
    const devices = await runAdb(["devices"], 8_000, "list_devices");
    return {
      ok: true,
      status: "connected",
      prepare: result,
      pairLocalPort: pairLocal,
      connectLocalPort: connectLocal,
      devices: devices.stdout,
      grants,
    };
  } catch (error) {
    await cleanupDevice(deviceId).catch(() => undefined);
    const message = error instanceof Error ? error.message : "adb_pair_failed";
    if (message === "device_offline") {
      return { ok: false, status: "failed", error: "device_offline" };
    }
    return { ok: false, status: "failed", error: message };
  }
}

async function cleanupDevice(deviceId: string) {
  await destroyHub(deviceId);
  await sendCommand({ deviceId, cmd: "adb_relay_stop", params: {}, waitMs: 5_000 }).catch(() => undefined);
  await sendCommand({ deviceId, cmd: "adb_wireless_done", params: {}, waitMs: 5_000 }).catch(() => undefined);
}

export type AdbRestoreResult = {
  ok: boolean;
  status: "connected" | "skipped" | "failed" | "stale";
  error?: string;
  serial?: string;
  connectLocalPort?: number;
};

/** 同一个 deviceId 的恢复请求串行化：控制通道上线和 60s 巡检可能同时打进来。 */
const restoreInflight = new Map<string, Promise<AdbRestoreResult>>();

function waitForDeviceAdbSocket(deviceId: string, timeoutMs: number): Promise<boolean> {
  const deadline = Date.now() + timeoutMs;
  return new Promise((resolve) => {
    const tick = () => {
      if (connectionManager.getDeviceAdbSocket(deviceId)) {
        resolve(true);
        return;
      }
      if (Date.now() >= deadline) {
        resolve(false);
        return;
      }
      setTimeout(tick, 200);
    };
    tick();
  });
}

/**
 * 免配对重连。
 *
 * 前提：这台设备之前配对过，adbkey 已建立信任。所以只需要
 * 「建 connect 流 → 让手机挂上中继 WS → adb connect 本地端口」，
 * 完全不用再碰配对码和 `adb_wireless_prepare`。
 *
 * 恢复失败不删端点：失败次数到上限由 store 翻成 `stale`，
 * 那时才说明手机端口变了（重启过/关过无线调试），必须重新刮码。
 */
export async function restoreDeviceAdb(
  deviceId: string,
  options: { force?: boolean } = {},
): Promise<AdbRestoreResult> {
  const existing = restoreInflight.get(deviceId);
  if (existing) {
    return existing;
  }
  const task = restoreDeviceAdbInner(deviceId, options).finally(() => {
    restoreInflight.delete(deviceId);
  });
  restoreInflight.set(deviceId, task);
  return task;
}

async function restoreDeviceAdbInner(
  deviceId: string,
  options: { force?: boolean } = {},
): Promise<AdbRestoreResult> {
  // 配对进行中：让配对自己收尾，恢复插进去只会互相拆台
  if (pairingInFlight.has(deviceId)) {
    return { ok: false, status: "skipped", error: "pairing_in_progress" };
  }
  const session = adbSessionRegistry.get(deviceId);
  if (session?.status === "connected") {
    // 会话记着在线，但传输可能早就死了（中继 WS 还在、adb 服务没了）。
    // 自动巡检有探测兜底；用户手点「重连」时 force=true，必须真的重建一次，
    // 否则点了没反应比报错更让人困惑。
    if (!options.force) {
      return { ok: true, status: "connected", serial: session.serial };
    }
    const probe = await probeDeviceAdbTransport(deviceId, 3_000);
    if (probe.ok) {
      return { ok: true, status: "connected", serial: session.serial };
    }
    adbSessionRegistry.markOffline(deviceId, "adb_transport_dead");
  }
  // 控制通道不在，命令发不下去，等下一轮
  if (!connectionManager.getDeviceSocket(deviceId)) {
    return { ok: false, status: "skipped", error: "device_offline" };
  }

  const endpoint = await getAdbEndpoint(deviceId);
  if (!endpoint) {
    return { ok: false, status: "skipped", error: "no_endpoint" };
  }
  if (endpoint.state === "stale") {
    return { ok: false, status: "stale", error: "endpoint_stale" };
  }

  try {
    const hub = await createHub(
      deviceId,
      null,
      { host: endpoint.phoneIp, port: endpoint.connectPort },
    );
    const connectLocal = hub.streams.get(2)!.localPort;

    // 手机可能已经自己把 ADB 中继 WS 挂回来了（服务重启后客户端自动重连）。
    // 那就直接复用，别再发一次 relay_start 把刚建好的连接顶掉。
    if (!hub.ws) {
      const relayCmd = await sendCommand({
        deviceId,
        cmd: "adb_relay_start",
        params: {
          openNow: false,
          streams: [
            { streamId: 2, host: endpoint.phoneIp, port: endpoint.connectPort, role: "connect" },
          ],
        },
        waitMs: 20_000,
      });
      if (relayCmd.status !== "success") {
        await destroyHub(deviceId);
        const state = await recordAdbEndpointFailure(deviceId, "relay_failed");
        return {
          ok: false,
          status: state === "stale" ? "stale" : "failed",
          error: relayCmd.errorMessage || "relay_failed",
        };
      }

      const wsReady = await waitForDeviceAdbSocket(deviceId, 10_000);
      hub.ws = connectionManager.getDeviceAdbSocket(deviceId);
      if (!wsReady || !hub.ws) {
        await destroyHub(deviceId);
        const state = await recordAdbEndpointFailure(deviceId, "adb_ws_not_ready");
        return {
          ok: false,
          status: state === "stale" ? "stale" : "failed",
          error: "adb_ws_not_ready",
        };
      }
    }

    const serial = `127.0.0.1:${connectLocal}`;
    const connected = await connectAdbUntilAccepted(
      serial,
      {
        connect: () => runAdb(["connect", serial], 20_000, "connect_restore"),
        disconnect: async () => {
          await runAdb(["disconnect", serial], 8_000, "disconnect_stale");
        },
        sleep,
      },
      { attempts: 3, initialDelayMs: 500, delayMs: 1_000 },
    );
    if (!connected.ok) {
      await destroyHub(deviceId);
      const state = await recordAdbEndpointFailure(deviceId, "connect_failed");
      return {
        ok: false,
        status: state === "stale" ? "stale" : "failed",
        error: `${connected.error ?? "connect_failed"}; remote=${endpoint.phoneIp}:${endpoint.connectPort}`,
      };
    }

    const ready = await waitUntilAdbTransportReady(
      serial,
      {
        probe: () => probeAdbSerial(serial, 4_000),
        connect: () => runAdb(["connect", serial], 20_000, "connect_restore_retry"),
        disconnect: async () => {
          await runAdb(["disconnect", serial], 8_000, "disconnect_stale");
        },
        sleep,
      },
      {
        timeoutMs: 8_000,
        intervalMs: 500,
        reconnectFirst: connected.verdict === "already",
      },
    );
    if (!ready.ok) {
      await destroyHub(deviceId);
      const state = await recordAdbEndpointFailure(deviceId, ready.error ?? "transport_offline");
      return {
        ok: false,
        status: state === "stale" ? "stale" : "failed",
        error: ready.error ?? "adb_transport_offline",
      };
    }

    adbSessionRegistry.register(deviceId, serial);
    await setAdbEndpointState(deviceId, "connected");
    return { ok: true, status: "connected", serial, connectLocalPort: connectLocal };
  } catch (error) {
    await destroyHub(deviceId).catch(() => undefined);
    const state = await recordAdbEndpointFailure(deviceId, "restore_exception");
    return {
      ok: false,
      status: state === "stale" ? "stale" : "failed",
      error: error instanceof Error ? error.message : "restore_failed",
    };
  }
}

/** 设备下线时保留端点，只把状态翻成离线。 */
export async function markAdbEndpointOffline(deviceId: string): Promise<void> {
  await setAdbEndpointState(deviceId, "offline");
}

/** 用户主动断开时彻底清掉端点，避免下次启动又自己连回来。 */
export async function forgetDeviceAdb(deviceId: string): Promise<void> {
  await destroyHub(deviceId);
  // 也要让手机那侧停掉重连，否则它会一直往服务端挂 WS
  await sendCommand({ deviceId, cmd: "adb_relay_stop", params: {}, waitMs: 5_000 }).catch(
    () => undefined,
  );
  await deleteAdbEndpoint(deviceId);
  adbSessionRegistry.clear(deviceId, "adb_forgotten");
}
