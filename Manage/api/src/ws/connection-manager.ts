import type { WebSocket } from "@fastify/websocket";
import type { AdminPushMessage } from "../types/messages.js";

interface DeviceConnection {
  deviceId: string;
  socket: WebSocket;
}

/** WS 层 ping 周期。客户端 OkHttp 会自动回 pong，不需要改客户端代码。 */
export const WS_PING_INTERVAL_MS = 30_000;
/** pong 超时。客户端 DeviceWebSocketClient 用 90 秒，这里取同值，两边判定断线应同时发生。 */
export const WS_PONG_TIMEOUT_MS = 90_000;
/** 应用层心跳超时：客户端 30s 一跳，3 个周期没到就当停了。与前端 HEARTBEAT_STALE_MS 同值。 */
export const APP_HEARTBEAT_TIMEOUT_MS = 90_000;

export type LivenessFailureReason = "ws_pong_timeout" | "heartbeat_stale";

class ConnectionManager {
  private readonly deviceConnections = new Map<string, DeviceConnection>();
  private readonly deviceScreenConnections = new Map<string, DeviceConnection>();
  private readonly deviceAdbConnections = new Map<string, DeviceConnection>();
  private readonly adminConnections = new Set<WebSocket>();
  private readonly adminScreenConnections = new Set<WebSocket>();
  private readonly adminScreenWatchTargets = new Map<WebSocket, string | null>();
  private readonly adminAdbScreenWatchTargets = new Map<WebSocket, string | null>();
  private readonly adminAdbUiTreeWatchTargets = new Map<WebSocket, string | null>();
  private readonly adminUiTreeWatchTargets = new Map<WebSocket, string | null>();
  private readonly deviceWatcherCounts = new Map<string, number>();
  private readonly adbScreenshotWatcherCounts = new Map<string, number>();
  private readonly adbUiTreeWatcherCounts = new Map<string, number>();
  private readonly uiTreeWatcherCounts = new Map<string, number>();
  /**
   * 最近一次 ui_tree_push 的快照缓存（TTL 10s）。
   * 新订阅者接入时可立即回放，避免等到下一次设备事件才看到首帧。
   */
  private readonly uiTreeLastSnapshots = new Map<
    string,
    { payload: Record<string, unknown>; receivedAt: number }
  >();
  private static readonly UI_TREE_SNAPSHOT_TTL_MS = 10_000;
  /** 控制 RTT、a11y RTT、adb 可达 三档健康度（进程内，无持久化） */
  private readonly deviceHealth = new Map<
    string,
    {
      controlOk: boolean;
      a11yRttMs: number | null;
      adbOk: boolean;
      /** 无障碍「实际连接」：心跳上报（新版客户端）或服务端探针结果 */
      a11yServiceConnected: boolean | null;
      a11yControlReady: boolean | null;
      /** 这组值的产生时刻（心跳到达时间 / 探针开始时间） */
      a11yReportedAt: number;
      /** 值从哪来的：心跳更新鲜、探针兜底 */
      a11ySource: "heartbeat" | "probe" | null;
      /** 探针失败原因；心跳值与成功探测都为 null */
      a11yError: string | null;
      updatedAt: number;
    }
  >();
  /** 控制通道断线宽限：重连窗口内不广播离线，也不从在线列表剔除 */
  private readonly offlineGraceTimers = new Map<string, ReturnType<typeof setTimeout>>();
  private readonly offlineGraceDeviceIds = new Set<string>();
  /** Control-channel blips under high RTT; keep device "online" briefly. */
  private static readonly OFFLINE_GRACE_MS = 12_000;

  /**
   * 通道活性（进程内）。关机/掉网后 TCP 停在半开态，内核不吐 FIN，
   * `close` 回调一次都不来；只有主动 ping 才能把这种条目踢掉。
   */
  private readonly liveness = new Map<
    string,
    {
      /** 最后一次收到 pong 帧的时刻 */
      lastPongAt: number;
      /** 最后一次收到应用层 JSON 心跳的时刻 */
      lastAppHeartbeatAt: number;
      /**
       * 这条连接上是否见过至少一次 JSON 心跳。
       * 没见过就不按心跳停更判死：极老的客户端可能只回 pong 不发心跳，
       * 那会变成每 90 秒被踢一次、反复重连。pong 那道闸仍然兜底。
       */
      sawAppHeartbeat: boolean;
    }
  >();

  /** 登记一条新连接：时间戳从现在起算，否则第一轮扫描会立刻判死。 */
  private initLiveness(deviceId: string, now = Date.now()) {
    this.liveness.set(deviceId, {
      lastPongAt: now,
      lastAppHeartbeatAt: now,
      sawAppHeartbeat: false,
    });
  }

  /**
   * 收到应用层心跳时调用。
   *
   * 只刷新心跳时间，不动 pong —— 两个信号要各自独立才有意义：
   * 半开 TCP 上两者同时停（判 ws_pong_timeout），
   * Hiber 冻结时内核还在回 pong、只有心跳停（判 heartbeat_stale）。
   */
  markAppHeartbeat(deviceId: string, now = Date.now()) {
    const entry = this.liveness.get(deviceId);
    // 没有条目说明这条连接已经下线并清理过了：迟到的���跳不要凭空造条目，
    // 否则它会一直留在 Map 里，既扫不到也永远删不掉。
    if (!entry) {
      return;
    }
    entry.lastAppHeartbeatAt = now;
    entry.sawAppHeartbeat = true;
  }

  /** WS 层 pong 回调调用。 */
  markPong(deviceId: string, now = Date.now()) {
    const entry = this.liveness.get(deviceId);
    if (!entry) {
      return;
    }
    entry.lastPongAt = now;
  }

  /**
   * 扫一轮活性，返回需要被踢掉的设备，同时发出下一次 ping。
   *
   * 只做判定，不碰 socket：断开由调用方 `terminate()`，后续的
   * close → 离线宽限 → 广播离线 全走现成那条路，不多开分支。
   */
  sweepLiveness(now = Date.now()): Array<{ deviceId: string; reason: LivenessFailureReason }> {
    const dead: Array<{ deviceId: string; reason: LivenessFailureReason }> = [];

    for (const [deviceId, conn] of this.deviceConnections) {
      const entry = this.liveness.get(deviceId) ?? {
        lastPongAt: now,
        lastAppHeartbeatAt: now,
        sawAppHeartbeat: false,
      };
      this.liveness.set(deviceId, entry);

      // 先看 pong：WS 层连不通，说明通道已经彻底没了，这是最强信号。
      // 再看应用层心跳：内核还活着会回 pong，但进程被 Hiber 冻住的话
      // 回不出 JSON 心跳，那台设备同样收不到任何指令，也得按离线算。
      if (now - entry.lastPongAt >= WS_PONG_TIMEOUT_MS) {
        dead.push({ deviceId, reason: "ws_pong_timeout" });
        continue;
      }
      if (entry.sawAppHeartbeat && now - entry.lastAppHeartbeatAt >= APP_HEARTBEAT_TIMEOUT_MS) {
        dead.push({ deviceId, reason: "heartbeat_stale" });
        continue;
      }

      try {
        if (conn.socket.readyState === conn.socket.OPEN) {
          conn.socket.ping();
        }
      } catch {
        // ping 抛错说明 socket 已经坏了；时间戳会停在旧值，下一轮自然判死
      }
    }

    return dead;
  }

  /** 设备彻底下线时清掉活性状态，避免 Map 无限增长。 */
  clearLiveness(deviceId: string) {
    this.liveness.delete(deviceId);
  }

  addDevice(deviceId: string, socket: WebSocket): { resumedWithinGrace: boolean } {
    const resumedWithinGrace = this.cancelDeviceOfflineGrace(deviceId);
    const existing = this.deviceConnections.get(deviceId);
    if (existing) {
      try {
        existing.socket.close(4000, "replaced_by_new_connection");
      } catch {
        // ignore stale socket close errors
      }
    }

    this.deviceConnections.set(deviceId, { deviceId, socket });
    this.initLiveness(deviceId);
    return { resumedWithinGrace };
  }

  removeDevice(deviceId: string, socket: WebSocket) {
    const current = this.deviceConnections.get(deviceId);
    if (current?.socket === socket) {
      this.deviceConnections.delete(deviceId);
      this.clearLiveness(deviceId);
      return true;
    }
    return false;
  }

  /**
   * 清掉某台设备的无障碍「实际连接」缓存。
   *
   * 设备重连时必须清：断线期间无障碍服务可能已经崩了或被杀，
   * 留着旧值会让「已连接」一直显示在界面上，而这正是要修的误导。
   */
  clearDeviceA11yHealth(deviceId: string) {
    const prev = this.deviceHealth.get(deviceId);
    if (!prev) {
      return;
    }
    this.deviceHealth.set(deviceId, {
      ...prev,
      a11yServiceConnected: null,
      a11yControlReady: null,
      a11yReportedAt: 0,
      a11ySource: null,
      a11yError: null,
      updatedAt: Date.now(),
    });
  }

  /**
   * 控制通道短暂断开（客户端先 disconnect 再 connect）时延迟广播离线。
   * 宽限内重连会 cancel，后台在线状态不再闪。
   */
  scheduleDeviceOfflineGrace(deviceId: string, onConfirmOffline: () => void) {
    this.cancelDeviceOfflineGrace(deviceId);
    this.offlineGraceDeviceIds.add(deviceId);
    const timer = setTimeout(() => {
      this.offlineGraceTimers.delete(deviceId);
      this.offlineGraceDeviceIds.delete(deviceId);
      if (!this.deviceConnections.has(deviceId)) {
        onConfirmOffline();
      }
    }, ConnectionManager.OFFLINE_GRACE_MS);
    this.offlineGraceTimers.set(deviceId, timer);
  }

  cancelDeviceOfflineGrace(deviceId: string): boolean {
    const timer = this.offlineGraceTimers.get(deviceId);
    this.offlineGraceDeviceIds.delete(deviceId);
    if (!timer) {
      return false;
    }
    clearTimeout(timer);
    this.offlineGraceTimers.delete(deviceId);
    return true;
  }

  getDeviceSocket(deviceId: string) {
    return this.deviceConnections.get(deviceId)?.socket ?? null;
  }

  addDeviceScreen(deviceId: string, socket: WebSocket) {
    const existing = this.deviceScreenConnections.get(deviceId);
    if (existing) {
      try {
        existing.socket.close(4000, "replaced_by_new_screen_connection");
      } catch {
        // ignore stale socket close errors
      }
    }

    this.deviceScreenConnections.set(deviceId, { deviceId, socket });
  }

  removeDeviceScreen(deviceId: string, socket: WebSocket) {
    const current = this.deviceScreenConnections.get(deviceId);
    if (current?.socket === socket) {
      this.deviceScreenConnections.delete(deviceId);
      return true;
    }
    return false;
  }

  getDeviceScreenSocket(deviceId: string) {
    return this.deviceScreenConnections.get(deviceId)?.socket ?? null;
  }

  addDeviceAdb(deviceId: string, socket: WebSocket) {
    const existing = this.deviceAdbConnections.get(deviceId);
    if (existing) {
      try {
        existing.socket.close(4000, "replaced_by_new_adb_connection");
      } catch {
        // ignore
      }
    }
    this.deviceAdbConnections.set(deviceId, { deviceId, socket });
  }

  removeDeviceAdb(deviceId: string, socket: WebSocket) {
    const current = this.deviceAdbConnections.get(deviceId);
    if (current?.socket === socket) {
      this.deviceAdbConnections.delete(deviceId);
      return true;
    }
    return false;
  }

  getDeviceAdbSocket(deviceId: string) {
    return this.deviceAdbConnections.get(deviceId)?.socket ?? null;
  }

  isDeviceConnected(deviceId: string) {
    return this.deviceConnections.has(deviceId);
  }

  /** 控制或画面任一在线（投屏订阅用，不代表可发指令） */
  hasDeviceChannel(deviceId: string) {
    return this.deviceConnections.has(deviceId) || this.deviceScreenConnections.has(deviceId);
  }

  addAdmin(socket: WebSocket) {
    this.adminConnections.add(socket);
  }

  removeAdmin(socket: WebSocket) {
    this.adminConnections.delete(socket);
  }

  addAdminScreen(socket: WebSocket) {
    this.adminScreenConnections.add(socket);
    this.adminScreenWatchTargets.set(socket, null);
    this.adminAdbScreenWatchTargets.set(socket, null);
    this.adminAdbUiTreeWatchTargets.set(socket, null);
    this.adminUiTreeWatchTargets.set(socket, null);
  }

  removeAdminScreen(socket: WebSocket) {
    const watchedDeviceId = this.adminScreenWatchTargets.get(socket) ?? null;
    const adbScreenDeviceId = this.adminAdbScreenWatchTargets.get(socket) ?? null;
    const adbUiDeviceId = this.adminAdbUiTreeWatchTargets.get(socket) ?? null;
    const uiTreeDeviceId = this.adminUiTreeWatchTargets.get(socket) ?? null;
    if (watchedDeviceId) {
      this.decrementDeviceWatchers(watchedDeviceId);
    }
    if (adbScreenDeviceId) {
      this.decrementCounter(this.adbScreenshotWatcherCounts, adbScreenDeviceId);
    }
    if (adbUiDeviceId) {
      this.decrementCounter(this.adbUiTreeWatcherCounts, adbUiDeviceId);
    }
    if (uiTreeDeviceId) {
      this.decrementCounter(this.uiTreeWatcherCounts, uiTreeDeviceId);
    }
    this.adminScreenWatchTargets.delete(socket);
    this.adminAdbScreenWatchTargets.delete(socket);
    this.adminAdbUiTreeWatchTargets.delete(socket);
    this.adminUiTreeWatchTargets.delete(socket);
    this.adminScreenConnections.delete(socket);
    return {
      projectionDeviceId: watchedDeviceId,
      adbScreenDeviceId,
      adbUiDeviceId,
      uiTreeDeviceId,
    };
  }

  setAdminScreenWatchTarget(socket: WebSocket, deviceId: string | null) {
    const previous = this.adminScreenWatchTargets.get(socket) ?? null;
    if (previous === deviceId) {
      return previous;
    }
    if (previous) {
      this.decrementDeviceWatchers(previous);
    }
    if (deviceId) {
      this.incrementDeviceWatchers(deviceId);
    }
    this.adminScreenWatchTargets.set(socket, deviceId);
    return previous;
  }

  shouldStopDeviceScreen(deviceId: string) {
    return (this.deviceWatcherCounts.get(deviceId) ?? 0) <= 0;
  }

  setAdminAdbScreenWatchTarget(socket: WebSocket, deviceId: string | null) {
    const previous = this.adminAdbScreenWatchTargets.get(socket) ?? null;
    if (previous === deviceId) {
      return previous;
    }
    if (previous) {
      this.decrementCounter(this.adbScreenshotWatcherCounts, previous);
    }
    if (deviceId) {
      this.incrementCounter(this.adbScreenshotWatcherCounts, deviceId);
    }
    this.adminAdbScreenWatchTargets.set(socket, deviceId);
    return previous;
  }

  setAdminAdbUiTreeWatchTarget(socket: WebSocket, deviceId: string | null) {
    const previous = this.adminAdbUiTreeWatchTargets.get(socket) ?? null;
    if (previous === deviceId) {
      return previous;
    }
    if (previous) {
      this.decrementCounter(this.adbUiTreeWatcherCounts, previous);
    }
    if (deviceId) {
      this.incrementCounter(this.adbUiTreeWatcherCounts, deviceId);
    }
    this.adminAdbUiTreeWatchTargets.set(socket, deviceId);
    return previous;
  }

  shouldStopAdbScreenshot(deviceId: string) {
    return (this.adbScreenshotWatcherCounts.get(deviceId) ?? 0) <= 0;
  }

  shouldStopAdbUiTree(deviceId: string) {
    return (this.adbUiTreeWatcherCounts.get(deviceId) ?? 0) <= 0;
  }

  setAdminUiTreeWatchTarget(socket: WebSocket, deviceId: string | null) {
    const previous = this.adminUiTreeWatchTargets.get(socket) ?? null;
    if (previous === deviceId) {
      return previous;
    }
    if (previous) {
      this.decrementCounter(this.uiTreeWatcherCounts, previous);
    }
    if (deviceId) {
      this.incrementCounter(this.uiTreeWatcherCounts, deviceId);
    }
    this.adminUiTreeWatchTargets.set(socket, deviceId);
    return previous;
  }

  shouldStopUiTreePush(deviceId: string) {
    return (this.uiTreeWatcherCounts.get(deviceId) ?? 0) <= 0;
  }

  getUiTreeWatcherCount(deviceId: string) {
    return this.uiTreeWatcherCounts.get(deviceId) ?? 0;
  }

  /** 推送 ui_tree_push 给订阅该 deviceId 的所有 admin，并缓存最近一帧用于补帧。 */
  sendUiTreePushToWatchers(deviceId: string, payload: Record<string, unknown>) {
    this.uiTreeLastSnapshots.set(deviceId, { payload, receivedAt: Date.now() });
    const message = {
      type: "ui_tree_push",
      deviceId,
      payload,
      timestamp: Date.now(),
    };
    this.sendJsonToTargetedWatchers(this.adminUiTreeWatchTargets, deviceId, message);
  }

  /** 新订阅者接入时调用：若近 10s 内有缓存，立即单发一帧避免等待。 */
  replayUiTreeSnapshotTo(socket: WebSocket, deviceId: string) {
    const cached = this.uiTreeLastSnapshots.get(deviceId);
    if (!cached) return;
    if (Date.now() - cached.receivedAt > ConnectionManager.UI_TREE_SNAPSHOT_TTL_MS) {
      this.uiTreeLastSnapshots.delete(deviceId);
      return;
    }
    if (socket.readyState !== socket.OPEN) return;
    socket.send(
      JSON.stringify({
        type: "ui_tree_push",
        deviceId,
        payload: cached.payload,
        timestamp: Date.now(),
        cached: true,
      }),
    );
  }

  // ---- 三档健康度（进程内） ----
  updateDeviceHealth(
    deviceId: string,
    patch: Partial<{
      controlOk: boolean;
      a11yRttMs: number | null;
      adbOk: boolean;
      /**
       * 无障碍「实际连接」三元组。`a11yReportedAt` 是**值的产生时刻**，
       * 不是写入时刻：探针在途时心跳先到，探针结果回得晚但产生得早，
       * 用产生时刻比较才不会把新鲜的心跳值覆盖成旧的探测值。
       */
      a11yServiceConnected: boolean | null;
      a11yControlReady: boolean | null;
      a11yReportedAt: number;
      a11ySource: "heartbeat" | "probe";
      a11yError?: string | null;
    }>,
  ) {
    const prev = this.deviceHealth.get(deviceId) ?? {
      controlOk: false,
      a11yRttMs: null as number | null,
      adbOk: false,
      a11yServiceConnected: null as boolean | null,
      a11yControlReady: null as boolean | null,
      a11yReportedAt: 0,
      a11ySource: null as "heartbeat" | "probe" | null,
      a11yError: null as string | null,
      updatedAt: 0,
    };

    // 只有更新的值（按产生时刻）才能覆盖无障碍三元组。
    const acceptA11y =
      patch.a11yReportedAt != null && patch.a11yReportedAt > prev.a11yReportedAt;

    this.deviceHealth.set(deviceId, {
      controlOk: patch.controlOk ?? prev.controlOk,
      a11yRttMs: patch.a11yRttMs !== undefined ? patch.a11yRttMs : prev.a11yRttMs,
      adbOk: patch.adbOk ?? prev.adbOk,
      a11yServiceConnected: acceptA11y
        ? (patch.a11yServiceConnected ?? null)
        : prev.a11yServiceConnected,
      a11yControlReady: acceptA11y
        ? (patch.a11yControlReady ?? null)
        : prev.a11yControlReady,
      a11yReportedAt: acceptA11y ? patch.a11yReportedAt! : prev.a11yReportedAt,
      a11ySource: acceptA11y ? (patch.a11ySource ?? prev.a11ySource) : prev.a11ySource,
      a11yError: acceptA11y ? (patch.a11yError ?? null) : prev.a11yError,
      updatedAt: Date.now(),
    });
  }

  getDeviceHealth(deviceId: string) {
    const h = this.deviceHealth.get(deviceId);
    // controlOk / adbOk 直接从连接状态推断，不依赖手动 update。
    const controlOk = this.deviceConnections.has(deviceId);
    const adbOk = this.deviceAdbConnections.has(deviceId);
    const a11yRttMs = h?.a11yRttMs ?? null;
    return {
      controlOk,
      a11yRttMs,
      adbOk,
      a11yServiceConnected: h?.a11yServiceConnected ?? null,
      a11yControlReady: h?.a11yControlReady ?? null,
      a11yReportedAt: h?.a11yReportedAt ?? 0,
      a11ySource: h?.a11ySource ?? null,
      a11yError: h?.a11yError ?? null,
    };
  }

  getAdbScreenshotWatcherCount(deviceId: string) {
    return this.adbScreenshotWatcherCounts.get(deviceId) ?? 0;
  }

  getAdbUiTreeWatcherCount(deviceId: string) {
    return this.adbUiTreeWatcherCounts.get(deviceId) ?? 0;
  }

  sendAdbPngToWatchers(deviceId: string, payload: Buffer) {
    for (const socket of this.adminScreenConnections) {
      if (socket.readyState !== socket.OPEN) {
        continue;
      }
      if (this.adminAdbScreenWatchTargets.get(socket) === deviceId) {
        socket.send(payload);
      }
    }
  }

  sendAdbScreenJsonToWatchers(deviceId: string, message: Record<string, unknown>) {
    this.sendJsonToTargetedWatchers(this.adminAdbScreenWatchTargets, deviceId, message);
  }

  sendAdbUiTreeJsonToWatchers(deviceId: string, message: Record<string, unknown>) {
    this.sendJsonToTargetedWatchers(this.adminAdbUiTreeWatchTargets, deviceId, message);
  }

  private sendJsonToTargetedWatchers(
    targets: Map<WebSocket, string | null>,
    deviceId: string,
    message: Record<string, unknown>,
  ) {
    const payload = JSON.stringify(message);
    for (const socket of this.adminScreenConnections) {
      if (socket.readyState !== socket.OPEN) {
        continue;
      }
      if (targets.get(socket) === deviceId) {
        socket.send(payload);
      }
    }
  }

  private incrementDeviceWatchers(deviceId: string) {
    this.incrementCounter(this.deviceWatcherCounts, deviceId);
  }

  private decrementDeviceWatchers(deviceId: string) {
    this.decrementCounter(this.deviceWatcherCounts, deviceId);
  }

  private incrementCounter(store: Map<string, number>, deviceId: string) {
    store.set(deviceId, (store.get(deviceId) ?? 0) + 1);
  }

  private decrementCounter(store: Map<string, number>, deviceId: string) {
    const next = Math.max((store.get(deviceId) ?? 0) - 1, 0);
    if (next === 0) {
      store.delete(deviceId);
      return;
    }
    store.set(deviceId, next);
  }

  broadcastToAdmins(message: AdminPushMessage) {
    const payload = JSON.stringify(message);
    for (const socket of this.adminConnections) {
      if (socket.readyState === socket.OPEN) {
        socket.send(payload);
      }
    }
  }

  sendScreenFrameToWatchers(
    deviceId: string,
    payload: Record<string, unknown>,
    timestamp: number,
  ) {
    this.sendScreenToWatchers(deviceId, {
      type: "screen_frame",
      deviceId,
      payload,
      timestamp,
    });
  }

  sendScreenErrorToWatchers(
    deviceId: string,
    payload: Record<string, unknown>,
    timestamp: number,
  ) {
    this.sendScreenToWatchers(deviceId, {
      type: "screen_error",
      deviceId,
      payload,
      timestamp,
    });
  }

  sendScreenStatusToWatchers(
    deviceId: string,
    payload: Record<string, unknown>,
    timestamp: number,
  ) {
    this.sendScreenToWatchers(deviceId, {
      type: "screen_status",
      deviceId,
      payload,
      timestamp,
    });
  }

  /** Forward WebRTC signaling (or any already-shaped screen message) to watchers. */
  sendScreenToWatchersRaw(deviceId: string, message: Record<string, unknown>) {
    this.sendScreenToWatchers(deviceId, message);
  }

  private sendScreenToWatchers(deviceId: string, message: Record<string, unknown>) {
    const payload = JSON.stringify(message);
    for (const socket of this.adminScreenConnections) {
      if (socket.readyState !== socket.OPEN) {
        continue;
      }
      if (this.adminScreenWatchTargets.get(socket) === deviceId) {
        socket.send(payload);
      }
    }
  }

  getOnlineDeviceIds() {
    if (this.offlineGraceDeviceIds.size === 0) {
      return [...this.deviceConnections.keys()];
    }
    const ids = new Set<string>(this.deviceConnections.keys());
    for (const deviceId of this.offlineGraceDeviceIds) {
      ids.add(deviceId);
    }
    return [...ids];
  }
}

export const connectionManager = new ConnectionManager();
