import {
  connectionManager,
  WS_PING_INTERVAL_MS,
} from "../ws/connection-manager.js";

/**
 * 控制通道的活性巡检。
 *
 * 为什么需要它：设备关机时通常先掉 WiFi/蜂窝，进程被杀，FIN 可能压根没发出去；
 * 即使发了，服务端这边也常常只看到一个半开的 TCP —— `close` 回调一次都不来。
 * 于是 `deviceConnections` 里的条目永远留着，界面就一直显示「在线」，
 * 直到 nginx 的 `proxy_read_timeout`（1 小时）把连接掐掉才自愈。
 *
 * 判定只有一件事要保证：**真的断了就别再装作连着**。
 * 两个信号，任一超时就判死 ——
 * 1. WS 层 pong（90s 无 pong）：关机、拔网、飞行模式，TCP 半开；
 * 2. 应用层心跳（90s 无 JSON 心跳）：内核还活着会回 pong，但进程已被
 *    Hiber 冻住，那台设备同样收不到任何指令。
 *
 * 判死之后统一走 `terminate()`：它补发一个 `close` 事件，
 * 后续的 `removeDevice` → 离线宽限 → `setDeviceOnline(false)` → 广播
 * 全部复用 `/ws/device` 里现成那条路，这里不另开分支、不重复广播。
 */

let sweepTimer: ReturnType<typeof setInterval> | null = null;

/** 扫一轮，把判定为死的连接掐掉。返回被掐掉的设备 id。导出仅供测试。 */
export function sweepLivenessOnce(now = Date.now()): string[] {
  const dead = connectionManager.sweepLiveness(now);
  const terminated: string[] = [];

  for (const { deviceId } of dead) {
    const socket = connectionManager.getDeviceSocket(deviceId);
    // 不在表里说明 close 已经走过一轮，离线流程自己会收尾
    if (!socket) {
      continue;
    }
    try {
      // 强制断开，不等对方 FIN：半开连接上永远等不到
      socket.terminate();
      terminated.push(deviceId);
    } catch {
      // terminate 失败（socket 已销毁）不影响下一轮，时间戳会继续走，仍会重新命中
    }
  }

  return terminated;
}

export function bootstrapLivenessWatchdog(): void {
  if (sweepTimer) {
    return;
  }
  sweepTimer = setInterval(() => {
    try {
      sweepLivenessOnce();
    } catch (error) {
      console.warn("[liveness] sweep failed", error);
    }
  }, WS_PING_INTERVAL_MS);
  // 定时器不应该拖住进程退出
  sweepTimer.unref?.();
}

export function stopLivenessWatchdog(): void {
  if (sweepTimer) {
    clearInterval(sweepTimer);
    sweepTimer = null;
  }
}
