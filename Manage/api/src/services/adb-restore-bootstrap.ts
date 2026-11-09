import { listRecoverableAdbEndpoints } from "./adb/adb-session-store.js";
import {
  probeDeviceAdbTransport,
  restoreDeviceAdb,
  setAdbEndpointRepairedHandler,
  setOrphanAdbSocketHandler,
} from "./adb-relay.service.js";
import { adbSessionRegistry } from "./adb/adb-session-registry.js";
import { connectionManager } from "../ws/connection-manager.js";

/**
 * 无线 ADB 的自动恢复调度。
 *
 * 两个触发点：
 * 1. 控制通道上线 —— 手机刚回来，端点还是热的，这是最省事的时机；
 * 2. 定时巡检 —— 兜住「上线那一刻设备还没准备好」和「恢复中途失败」。
 *
 * 为什么需要它：端点（手机 IP + connect 端口）现在落库了，但恢复动作
 * 得有人发起。没有这层调度，重启后端点只是躺在库里，界面照样显示未连接。
 */

const SWEEP_INTERVAL_MS = 60_000;
/** 同一台设备两次自动恢复之间的最小间隔。手点重连不受此限。 */
const PER_DEVICE_COOLDOWN_MS = 45_000;
/** 每轮巡检最多处理多少台，避免一次把 adb 进程池打满。 */
const SWEEP_BATCH = 8;
/** 对「已连接」会话做健康探测的最小间隔：探测要起 adb 进程，别打太勤。 */
const HEALTH_PROBE_INTERVAL_MS = 5 * 60_000;

const lastAttemptAt = new Map<string, number>();
const lastProbeAt = new Map<string, number>();
const pendingAttempts = new Map<string, ReturnType<typeof setTimeout>>();
/** 恢复失败次数到上限的设备：本轮不再自动重试，等人工重新刮码。 */
const exhausted = new Set<string>();

let sweepTimer: ReturnType<typeof setInterval> | null = null;
let sweeping = false;

function shouldAttempt(deviceId: string): boolean {
  if (exhausted.has(deviceId)) {
    return false;
  }
  const last = lastAttemptAt.get(deviceId) ?? 0;
  return Date.now() - last >= PER_DEVICE_COOLDOWN_MS;
}

/**
 * 会话记着 connected，但 adb 传输其实已经死了。
 *
 * 这种半死状态不会自己好：中继 WS 还在，ack 也照常，但手机那边
 * 的 adb 服务已经没了。不管的话界面会一直显示「已连接」，点什么都失败。
 * 所以巡检时顺手探一下，把结论如实翻成离线，让它进恢复队列。
 */
async function demoteStaleConnection(deviceId: string): Promise<boolean> {
  const probe = await probeDeviceAdbTransport(deviceId, 3_000);
  if (probe.ok) {
    return false;
  }
  adbSessionRegistry.markOffline(deviceId, "adb_transport_dead");
  return true;
}

async function attemptRestore(deviceId: string, source: string): Promise<void> {
  if (!shouldAttempt(deviceId)) {
    return;
  }
  const session = adbSessionRegistry.get(deviceId);
  if (session?.status === "connected") {
    return;
  }  lastAttemptAt.set(deviceId, Date.now());
  try {
    const result = await restoreDeviceAdb(deviceId);
    if (result.ok) {
      exhausted.delete(deviceId);
      return;
    }
    if (result.status === "stale") {
      // 端口失效：手机重启过或关过无线调试。自动重试没意义了。
      exhausted.add(deviceId);
    }
    void source;
  } catch {
    // restoreDeviceAdb 内部已兜底，这里只防未捕获拒绝
  }
}

/**
 * 设备控制通道上线后调用。
 *
 * 延迟几秒再试：手机刚重连时 adb 中继 WS 往往还在路上，
 * 立刻发起只会白等 10 秒再失败一次。
 */
export function scheduleAdbRestore(deviceId: string): void {
  if (pendingAttempts.has(deviceId)) {
    return;
  }
  const timer = setTimeout(() => {
    pendingAttempts.delete(deviceId);
    void attemptRestore(deviceId, "device_online");
  }, 5_000);
  pendingAttempts.set(deviceId, timer);
}

export function cancelScheduledAdbRestore(deviceId: string): void {
  const timer = pendingAttempts.get(deviceId);
  if (timer) {
    clearTimeout(timer);
    pendingAttempts.delete(deviceId);
  }
}

async function sweep(): Promise<void> {
  if (sweeping) {
    return;
  }
  sweeping = true;
  try {
    const endpoints = await listRecoverableAdbEndpoints();
    let handled = 0;
    for (const endpoint of endpoints) {
      if (handled >= SWEEP_BATCH) {
        break;
      }
      // 手机不在线时恢复一定失败，只会白烧失败计数
      if (!connectionManager.isDeviceConnected(endpoint.deviceId)) {
        continue;
      }
      const session = adbSessionRegistry.get(endpoint.deviceId);
      if (session?.status === "connected") {
        // 半死会话：中继 WS 还在但 adb 已经不通。探一次再决定跳过与否，
        // 否则这台设备永远卡在「显示已连接、实际不能用」。
        // 探测要起 adb 进程，限频到 5 分钟一次，别每轮都打一遍。
        const lastProbe = lastProbeAt.get(endpoint.deviceId) ?? 0;
        if (Date.now() - lastProbe < HEALTH_PROBE_INTERVAL_MS) {
          continue;
        }
        lastProbeAt.set(endpoint.deviceId, Date.now());
        if (!(await demoteStaleConnection(endpoint.deviceId))) {
          continue;
        }
      }
      if (!shouldAttempt(endpoint.deviceId)) {
        continue;
      }
      handled += 1;
      await attemptRestore(endpoint.deviceId, "sweep");
    }
  } catch {
    // 巡检失败不致命，下一轮再来
  } finally {
    sweeping = false;
  }
}

export function bootstrapAdbRestore(): void {
  if (sweepTimer) {
    return;
  }
  // 手机抢先把中继 WS 连回来时，由这里接手建流 + adb connect。
  setOrphanAdbSocketHandler((deviceId) => {
    scheduleAdbRestore(deviceId);
  });
  // 人工重新配对成功：清掉「失败次数用完」的结论，让它重新进入自动恢复。
  setAdbEndpointRepairedHandler((deviceId) => {
    exhausted.delete(deviceId);
    lastAttemptAt.delete(deviceId);
  });
  sweepTimer = setInterval(() => {
    void sweep();
  }, SWEEP_INTERVAL_MS);
  // 启动后先等一轮：此刻所有设备都还没连上来
  setTimeout(() => {
    void sweep();
  }, 15_000);
}

export function stopAdbRestore(): void {
  if (sweepTimer) {
    clearInterval(sweepTimer);
    sweepTimer = null;
  }
  setOrphanAdbSocketHandler(null);
  setAdbEndpointRepairedHandler(null);
  for (const timer of pendingAttempts.values()) {
    clearTimeout(timer);
  }
  pendingAttempts.clear();
}

/** 仅测试用。 */
export function resetAdbRestoreForTests(): void {
  stopAdbRestore();
  lastAttemptAt.clear();
  lastProbeAt.clear();
  exhausted.clear();
}
