import { t } from "../i18n/store.ts";
/**
 * 设备列表「无障碍」两列的可视化规则。
 *
 * 关键点：`accessibilityStatus` 和「实际连接」是两件事，
 * 而且**离线设备的 accessibilityStatus 是最后一跳的冻结值**。
 * 运维看到同一句「未绑定」时，含义可能完全不同：
 *   - 在线 + 未绑定 → 服务真的没绑上，发指令会被 ensureControlReady 拒绝
 *   - 离线 + 未绑定 → 这是它掉线之前的状态，现在连指令都发不到
 */

export type A11yTone = "ok" | "warn" | "danger" | "idle";

export interface A11ySettingView {
  label: string;
  tone: A11yTone;
  /** 悬浮提示：把「为什么是这个值」说清楚 */
  title: string;
  /** 状态取自离线前的最后一跳，不代表当前 */
  stale: boolean;
}

export interface A11yProbeView {
  serviceConnected: boolean | null;
  controlReady: boolean | null;
  probedAt: number;
  error: string | null;
  /** heartbeat = 新版客户端心跳上报；probe = 服务端主动探测 */
  source?: "heartbeat" | "probe" | null;
}

export function formatRelativeTime(
  timestampMs: number | null | undefined,
  now: number = Date.now(),
): string {
  if (timestampMs == null || !Number.isFinite(timestampMs) || timestampMs <= 0) {
    return "-";
  }
  const diffMs = now - timestampMs;
  if (diffMs < 0) {
    return t("刚刚");
  }
  const seconds = Math.floor(diffMs / 1000);
  if (seconds < 10) {
    return t("刚刚");
  }
  if (seconds < 60) {
    return t("{0} 秒前", { 0: seconds });
  }
  const minutes = Math.floor(seconds / 60);
  if (minutes < 60) {
    return t("{0} 分钟前", { 0: minutes });
  }
  const hours = Math.floor(minutes / 60);
  if (hours < 24) {
    return t("{0} 小时前", { 0: hours });
  }
  return t("{0} 天前", { 0: Math.floor(hours / 24) });
}

/**
 * 「设置状态」列：settings 里有没有本组件。
 * 离线时不再复用旧文案，直接显示「已离线」——避免把残留值当成现状。
 */
export function describeA11ySetting(input: {
  isOnline: boolean;
  status: string | null;
  /** 最后一次心跳（ISO 字符串或空） */
  lastHeartbeatAt: string | null;
  now?: number;
}): A11ySettingView {
  const status = input.status?.trim() || null;
  const now = input.now ?? Date.now();

  if (!input.isOnline) {
    const heartbeatMs = input.lastHeartbeatAt ? Date.parse(input.lastHeartbeatAt) : NaN;
    const when = Number.isNaN(heartbeatMs) ? null : heartbeatMs;
    const stale = status != null;
    return {
      label: t("已离线"),
      tone: "idle",
      stale,
      title: stale
        ? t("设备已离线。下面显示的是离线前的残留值：{0}（最后上报 {1}），不代表当前状态。", { 0: status, 1: formatRelativeTime(when, now) })
        : t("设备已离线（最后上报 {0}），无法读取无障碍设置。", { 0: formatRelativeTime(when, now) }),
    };
  }

  if (status === "允许") {
    return {
      label: t("允许"),
      tone: "ok",
      stale: false,
      title: t("系统设置的无障碍服务列表里包含本应用（settings 视角）。实际能否点击看右侧「实际连接」。"),
    };
  }
  if (status === "未绑定") {
    return {
      label: t("未绑定"),
      tone: "danger",
      stale: false,
      title:
        t("无障碍总开关是开的，但系统服务列表里没有本应用组件。点击/滑动/输入等 18 条控制命令会被直接拒绝。"),
    };
  }
  if (status === "拒绝") {
    return {
      label: t("拒绝"),
      tone: "danger",
      stale: false,
      title: t("无障碍总开关处于关闭状态，所有控制命令不可用。"),
    };
  }
  return {
    label: status ?? "-",
    tone: status ? "danger" : "idle",
    stale: false,
    title: status ? t("上报值：{0}", { 0: status }) : t("设备尚未上报无障碍状态。"),
  };
}

/**
 * 「实际连接」列：服务是否真的活着。
 *
 * 数据来自服务端按 TTL 发的 `check_accessibility`（`serviceConnected` /
 * `controlReady`），不是 settings。这一列才回答「能不能真的点」。
 */
/**
 * 心跳值超过这个时长就算停更。
 *
 * 心跳间隔 30s，服务端按 3 个周期判停更，前端跟它保持一致。
 * 停更的「已连接」是设备冻结的典型产物：帧还在，值不动了。
 * 不降级显示的话，运维看到的绿色和「真的活着」一模一样。
 */
export const HEARTBEAT_STALE_MS = 90_000;

export function describeA11yProbe(input: {
  isOnline: boolean;
  probe: A11yProbeView | null | undefined;
  /** 设置状态，用于提示「设置说开了但实际没连上」这类矛盾 */
  settingStatus?: string | null;
  now?: number;
}): { label: string; tone: A11yTone; title: string } {
  const now = input.now ?? Date.now();

  if (!input.isOnline) {
    return {
      label: t("无法探测"),
      tone: "idle",
      title: t("设备离线，无法探测无障碍服务是否真的连着。"),
    };
  }

  const probe = input.probe;
  if (!probe) {
    return {
      label: t("检测中"),
      tone: "idle",
      title:
        t("正在等待第一次探测结果。新版客户端会随 30 秒心跳直接上报；旧版由服务端按需补探（每 5 分钟一次，失败 30 秒后重试）。"),
    };
  }

  const probedAgo = formatRelativeTime(probe.probedAt, now);
  const via =
    probe.source === "heartbeat"
      ? t("心跳上报（{0}）", { 0: probedAgo })
      : probe.source === "probe"
        ? t("服务端探测（{0}）", { 0: probedAgo })
        : probedAgo;

  // 心跳停更：值不再更新，`true` 只能代表最后一跳时的状态。
  // 这种情况必须降级成「停更」而不是继续显示绿色「已连接」。
  if (
    probe.source === "heartbeat" &&
    probe.probedAt > 0 &&
    now - probe.probedAt > HEARTBEAT_STALE_MS
  ) {
    return {
      label: t("心跳停更"),
      tone: "warn",
      title: t("最后一次心跳上报在 {0}，超过 {1} 秒未更新。设备可能已被系统冻结，这时的「已连接」不代表当前状态；服务端会补探针重新确认。", { 0: probedAgo, 1: Math.round(
        HEARTBEAT_STALE_MS / 1000,
      ) }),
    };
  }

  if (probe.error) {
    return {
      label: t("探测失败"),
      tone: "danger",
      title: t("没拿到回包（{0}），最后尝试 {1}。设备可能已冻结或命令通道堵塞。", { 0: probe.error, 1: probedAgo }),
    };
  }

  const service = probe.serviceConnected;
  const control = probe.controlReady;
  const settingAllows = input.settingStatus === "允许";

  if (service === true && control === true) {
    return {
      label: t("已连接"),
      tone: "ok",
      title: t("服务已连接且可执行控制 · {0}", { 0: via }),
    };
  }

  if (service === true && control === false) {
    return {
      label: t("服务在·控制未就绪"),
      tone: "warn",
      title: t("无障碍服务已连接，但控制桥没有拿到服务实例，点击可能失败 · {0}", { 0: via }),
    };
  }

  if (service === false) {
    return {
      label: t("未连接"),
      tone: "danger",
      title: settingAllows
        ? t("设置显示「允许」，但服务实际没连上 · {0}。这是假阳性，常见原因是服务被系统回收或崩溃。", { 0: via })
        : t("无障碍服务未连接 · {0}", { 0: via }),
    };
  }

  return {
    label: t("未知"),
    tone: "idle",
    title: t("回包里没有 serviceConnected 字段，可能是旧版本客户端 · {0}", { 0: via }),
  };
}
