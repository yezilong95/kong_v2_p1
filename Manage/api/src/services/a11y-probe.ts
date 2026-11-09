/**
 * 无障碍「实际连接」探测缓存。
 *
 * 为什么要有这层：心跳 payload 只带 `accessibilityStatus`（settings 里有没有），
 * 不带 `serviceConnected` / `controlReady`。运维真正需要的是后者——
 * 「settings 说开了，服务到底活没活」。
 *
 * 不改客户端：探测复用已有的 `check_accessibility` 命令（它会返回
 * status / enabled / serviceConnected / controlReady）。列表接口只读缓存，
 * 由后台按 TTL 补探测，避免把设备回包时延叠加到列表响应上。
 */

export interface A11yProbeResult {
  /** 客户端原始状态：enabled / disabled / not_bound；解析不出时为 null */
  statusRaw: string | null;
  /** 归一化后的中文状态，与 accessibilityStatus 同一套取值 */
  statusLabel: string | null;
  /** RemoteAccessibilityService 的 onServiceConnected 是否触发过 */
  serviceConnected: boolean | null;
  /** AccessibilityControlBridge 是否拿到服务实例（真正能点/能滑） */
  controlReady: boolean | null;
  probedAt: number;
  /** 探测失败原因；成功为 null */
  error: string | null;
}

const STATUS_LABELS: Record<string, string> = {
  enabled: "允许",
  not_bound: "未绑定",
  disabled: "拒绝",
};

function readBoolean(value: unknown): boolean | null {
  if (typeof value === "boolean") {
    return value;
  }
  if (value === 1 || value === "1" || value === "true") {
    return true;
  }
  if (value === 0 || value === "0" || value === "false") {
    return false;
  }
  return null;
}

function readString(value: unknown): string | null {
  if (typeof value !== "string") {
    return null;
  }
  const trimmed = value.trim();
  return trimmed.length > 0 ? trimmed : null;
}

/** 把 `check_accessibility` 的 data 归一化成固定结构（纯函数，便于测试）。 */
export function normalizeA11yProbeResult(
  data: Record<string, unknown> | null | undefined,
  probedAt: number,
  error: string | null = null,
): A11yProbeResult {
  if (!data) {
    return {
      statusRaw: null,
      statusLabel: null,
      serviceConnected: null,
      controlReady: null,
      probedAt,
      error: error ?? "empty_probe_result",
    };
  }

  // 旧包不认识 check_accessibility：服务端顶层 when 会回 unsupported_cmd。
  // 这与「设备没回包」是两件事（一个要升级，一个可能是冻结），
  // 但两者都归到 error 里交给上层按前缀区分 TTL，不需要额外字段。
  const statusRaw = readString(data.status);
  const enabled = readBoolean(data.enabled);
  // status 缺失时退回 enabled 推断；两者都没有才算未知。
  const statusLabel =
    (statusRaw ? STATUS_LABELS[statusRaw] : null) ??
    (enabled === true ? "允许" : enabled === false ? "拒绝" : null);

  return {
    statusRaw,
    statusLabel,
    serviceConnected: readBoolean(data.serviceConnected),
    controlReady: readBoolean(data.controlReady),
    probedAt,
    error,
  };
}

export type A11yProbeSender = (
  deviceId: string,
) => Promise<Record<string, unknown> | null>;

/**
 * 探测结果落地的地方（由 bootstrap 注入，指向 connectionManager）。
 * 放在这里是为了让 a11y-probe 保持纯模块，测试不需要拉起整个 WS 层。
 */
export type A11yProbeSink = (deviceId: string, result: A11yProbeResult) => void;

export interface A11yProbeStoreOptions {
  /** 同一设备两次成功探测的最小间隔 */
  ttlMs: number;
  /** 探测失败后的重试间隔（比成功 TTL 短，避免一坏就 5 分钟不更新） */
  errorTtlMs: number;
  /**
   * 客户端明确回 `unsupported_cmd` 时的间隔。
   * 客户端能力不会自愈，只能等它升级，所以别按 30 秒去骚扰它。
   */
  unsupportedTtlMs: number;
  /** 同时在途的探测数上限，避免首屏 100 台设备同时打满命令通道 */
  maxInflight: number;
  now?: () => number;
  /** 每次探测完成后回调（用于推送前端） */
  onResult?: (deviceId: string, result: A11yProbeResult) => void;
}

/**
 * 进程内探测缓存 + 并发闸门。
 *
 * 闸门的意义：一页 100 台在线设备若并发发 `check_accessibility`，
 * 命令通道和 WS 都会抖。限流后首屏可能只有前几台立刻有值，
 * 其余先显示「检测中」，下一轮轮询补上。
 */
export class A11yProbeStore {
  private readonly cache = new Map<string, A11yProbeResult>();
  private readonly inflight = new Set<string>();
  private readonly queue: string[] = [];
  private readonly sender: A11yProbeSender;
  private readonly ttlMs: number;
  private readonly errorTtlMs: number;
  private readonly unsupportedTtlMs: number;
  private readonly maxInflight: number;
  private readonly now: () => number;
  private readonly onResult?: (deviceId: string, result: A11yProbeResult) => void;
  /** 队列上限：极端情况下（一次翻满 200 台）防止无界增长 */
  private static readonly MAX_QUEUE = 64;

  constructor(sender: A11yProbeSender, options: A11yProbeStoreOptions) {
    this.sender = sender;
    this.ttlMs = Math.max(options.ttlMs, 1);
    this.errorTtlMs = Math.max(options.errorTtlMs, 1);
    this.unsupportedTtlMs = Math.max(options.unsupportedTtlMs, 1);
    this.maxInflight = Math.max(options.maxInflight, 1);
    this.now = options.now ?? Date.now;
    this.onResult = options.onResult;
  }

  /** 取缓存；不做新鲜度判断，调用方按 probedAt / isOnline 自行展示。 */
  get(deviceId: string): A11yProbeResult | null {
    return this.cache.get(deviceId) ?? null;
  }

  /**
   * 触发一次后台探测：命中 TTL、已在途、排队已满时直接返回。
   * 刻意不返回 Promise——列表接口不能被设备回包拖住。
   *
   * `force` 用于手工刷新或心跳断档时的补测，跳过后台 TTL 判断。
   */
  schedule(deviceId: string, options?: { force?: boolean }): void {
    if (!deviceId || this.inflight.has(deviceId) || this.queue.includes(deviceId)) {
      return;
    }
    const cached = this.cache.get(deviceId);
    if (!options?.force && cached && !this.isStale(cached)) {
      return;
    }
    if (this.queue.length >= A11yProbeStore.MAX_QUEUE) {
      return;
    }
    this.queue.push(deviceId);
    void this.drain();
  }

  /** 立即探测一次并等结果（测试与需要同步值的场景）。 */
  async run(deviceId: string): Promise<A11yProbeResult> {
    this.inflight.add(deviceId);
    const startedAt = this.now();
    try {
      const data = await this.sender(deviceId);
      const result = normalizeA11yProbeResult(data, startedAt);
      this.cache.set(deviceId, result);
      this.onResult?.(deviceId, result);
      return result;
    } catch (error) {
      const message = error instanceof Error ? error.message : "probe_failed";
      const result = normalizeA11yProbeResult(null, startedAt, message);
      this.cache.set(deviceId, result);
      this.onResult?.(deviceId, result);
      return result;
    } finally {
      this.inflight.delete(deviceId);
      void this.drain();
    }
  }

  reset(): void {
    this.cache.clear();
    this.inflight.clear();
    this.queue.length = 0;
  }

  private isStale(result: A11yProbeResult): boolean {
    const ttl = result.error
      ? result.error.startsWith("unsupported_cmd")
        ? this.unsupportedTtlMs
        : this.errorTtlMs
      : this.ttlMs;
    return this.now() - result.probedAt >= ttl;
  }

  private async drain(): Promise<void> {
    while (this.inflight.size < this.maxInflight && this.queue.length > 0) {
      const deviceId = this.queue.shift();
      if (!deviceId) {
        return;
      }
      void this.run(deviceId);
    }
  }
}

/**
 * 模块级单例。device.service 只依赖这里（纯模块，无 import 环），
 * 真正的 sender / sink 由 a11y-probe-bootstrap 在启动时注入。
 */
let currentStore: A11yProbeStore | null = null;

export function setA11yProbeStore(store: A11yProbeStore | null): void {
  currentStore = store;
}

export function scheduleA11yProbe(deviceId: string, options?: { force?: boolean }): void {
  currentStore?.schedule(deviceId, options);
}

/** 仅供测试/诊断：直接取缓存里的原始探测记录。 */
export function peekA11yProbe(deviceId: string): A11yProbeResult | null {
  return currentStore?.get(deviceId) ?? null;
}

/**
 * 心跳值超过这个时长就当作「停更了」，照样补探针。
 *
 * 心跳间隔 30s，取 3 个周期。这堵的是方案 a 的原始缺陷：
 * 设备冻结后心跳停摆，值会永久停在冻结前的 `true`，
 * 界面上和「真的连着」完全一样。有了这条，停更的心跳会被探针覆盖成
 * 「探测失败」，运维看到的是「不知道」而不是假的「已连接」。
 */
export const HEARTBEAT_A11Y_MAX_AGE_MS = 90_000;

/**
 * 这条在线设备行要不要补一次探针。
 *
 * 放在这里而不是 device.service，是为了让这条规则能被单测直接钉住：
 * 它一旦退化成「来源是 heartbeat 就永远不探」，冻结设备上的假「已连接」
 * 就会复活——而这正是这次要修的那类误导。
 */
export function shouldScheduleA11yProbe(input: {
  isOnline: boolean;
  source: "heartbeat" | "probe" | null;
  reportedAt: number;
  now: number;
}): boolean {
  if (!input.isOnline) {
    return false;
  }
  const heartbeatFresh =
    input.source === "heartbeat" &&
    input.reportedAt > 0 &&
    input.now - input.reportedAt < HEARTBEAT_A11Y_MAX_AGE_MS;
  return !heartbeatFresh;
}
