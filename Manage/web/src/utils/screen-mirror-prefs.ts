/**
 * 采集来源 × 传输方式 × 画质。
 * - 来源：screenshot（无障碍真截图）/ screenhelper（UI 树伪屏）/ projection（MediaProjection）
 * - 传输：webrtc（TURN 中继，视频轨）/ websocket（帧推流，不依赖 TURN）
 * - 画质：standard（1688 同款，短边 240 / WEBP q10，约 3-8KB/帧）/ hd（短边 400 / q40，约 20-30KB/帧）
 */
export type MirrorMode =
  | "screenshot"
  | "screenshot_ws"
  | "screenshot_ws_hd"
  | "screenhelper"
  | "screenhelper_ws"
  | "screenhelper_ws_hd"
  | "projection"
  | "projection_ws"
  | "projection_ws_hd";

export type MirrorBase = "screenshot" | "screenhelper" | "projection";
export type MirrorTransport = "webrtc" | "websocket";
export type MirrorQuality = "standard" | "hd";

export const MIRROR_INTERVALS_MS = [250, 400, 500, 1000] as const;
export const DEFAULT_MIRROR_INTERVAL_MS = 250;
/** takeScreenshot is rate-limited by the OS; default slower than projection. */
export const DEFAULT_SCREENSHOT_INTERVAL_MS = 400;
export const DEFAULT_MIRROR_MODE: MirrorMode = "screenshot_ws";

const STORAGE_KEY = "vibelink.screenMirrorPrefs.v8";

export interface ScreenMirrorPrefs {
  mode: MirrorMode;
  intervals: Record<MirrorMode, number>;
}

const ALL_MODES: MirrorMode[] = [
  "screenshot",
  "screenshot_ws",
  "screenshot_ws_hd",
  "screenhelper",
  "screenhelper_ws",
  "screenhelper_ws_hd",
  "projection",
  "projection_ws",
  "projection_ws_hd",
];

export function baseMirrorMode(mode: MirrorMode): MirrorBase {
  if (mode.startsWith("projection")) {
    return "projection";
  }
  if (mode.startsWith("screenhelper")) {
    return "screenhelper";
  }
  return "screenshot";
}

export function mirrorTransport(mode: MirrorMode): MirrorTransport {
  return mode.includes("_ws") ? "websocket" : "webrtc";
}

export function mirrorQuality(mode: MirrorMode): MirrorQuality {
  return mode.endsWith("_hd") ? "hd" : "standard";
}

export function isWebrtcMirrorMode(mode: MirrorMode): boolean {
  return mirrorTransport(mode) === "webrtc";
}

/**
 * 默认帧间隔，沿用 v7 的历史值（不改动已生效的节奏）：
 * screenshot 400（系统限流）/ screenshot_ws 250 / projection 250 / projection_ws 250
 * / screenhelper 400 / screenhelper_ws 400。高清档帧大，统一 250ms。
 */
function defaultIntervalFor(mode: MirrorMode): number {
  switch (mode) {
    case "screenshot":
    case "screenhelper":
    case "screenhelper_ws":
      return DEFAULT_SCREENSHOT_INTERVAL_MS;
    default:
      return DEFAULT_MIRROR_INTERVAL_MS;
  }
}

export function createDefaultScreenMirrorPrefs(): ScreenMirrorPrefs {
  const intervals = {} as Record<MirrorMode, number>;
  for (const mode of ALL_MODES) {
    intervals[mode] = defaultIntervalFor(mode);
  }
  return { mode: DEFAULT_MIRROR_MODE, intervals };
}

export function normalizeMirrorMode(value: unknown): MirrorMode {
  if (typeof value !== "string") {
    return DEFAULT_MIRROR_MODE;
  }
  const raw = value.trim();
  if ((ALL_MODES as string[]).includes(raw)) {
    return raw as MirrorMode;
  }
  if (raw === "adb_screenshot" || raw === "media_projection" || raw === "cast") {
    return "projection";
  }
  if (raw === "helper" || raw === "ui_tree") {
    return "screenhelper";
  }
  if (raw === "jpeg" || raw === "websocket") {
    return "projection_ws";
  }
  if (raw === "screenshot_jpeg" || raw === "screenshot_websocket") {
    return "screenshot_ws";
  }
  if (raw === "screenhelper_jpeg" || raw === "screenhelper_websocket") {
    return "screenhelper_ws";
  }
  if (raw === "projection_jpeg" || raw === "projection_websocket") {
    return "projection_ws";
  }
  return DEFAULT_MIRROR_MODE;
}

/**
 * ADB 开着且无障碍已关时只能授权投屏；有无障碍时沿用当前/已保存模式。
 * 保留原来的传输方式（中继 / 普通）与画质。
 */
export function resolveMirrorModeForCast(
  useAdbPath: boolean,
  saved: MirrorMode,
  accessibilityEnabled: boolean | null = true,
): MirrorMode {
  if (useAdbPath && accessibilityEnabled === false) {
    if (baseMirrorMode(saved) !== "projection") {
      if (mirrorTransport(saved) === "webrtc") {
        return "projection";
      }
      return mirrorQuality(saved) === "hd" ? "projection_ws_hd" : "projection_ws";
    }
  }
  return saved;
}

export function normalizeMirrorIntervalMs(
  value: unknown,
  fallback: number = DEFAULT_MIRROR_INTERVAL_MS,
): number {
  const numeric = Number(value);
  return (MIRROR_INTERVALS_MS as readonly number[]).includes(numeric)
    ? numeric
    : fallback;
}

export function parseScreenMirrorPrefs(raw: string | null): ScreenMirrorPrefs {
  const defaults = createDefaultScreenMirrorPrefs();
  if (!raw) {
    return defaults;
  }
  try {
    const parsed = JSON.parse(raw) as Partial<ScreenMirrorPrefs> & {
      intervals?: Partial<Record<string, unknown>>;
    };
    const intervals: Partial<Record<string, unknown>> = parsed.intervals ?? {};
    // 旧版本没有 _ws_hd 的独立间隔，回退到同来源的普通档。
    const pick = (mode: MirrorMode) =>
      normalizeMirrorIntervalMs(
        intervals[mode] ?? intervals[legacyFallbackKey(mode)],
        defaults.intervals[mode],
      );
    const nextIntervals = {} as Record<MirrorMode, number>;
    for (const mode of ALL_MODES) {
      nextIntervals[mode] = pick(mode);
    }
    return {
      mode: normalizeMirrorMode(parsed.mode),
      intervals: nextIntervals,
    };
  } catch {
    return defaults;
  }
}

/** 旧存储里 _ws_hd 没有自己的键，读同来源的 _ws 或基础键。 */
function legacyFallbackKey(mode: MirrorMode): string {
  if (mode.endsWith("_ws_hd")) {
    return mode.slice(0, -3);
  }
  return baseMirrorMode(mode);
}

export function loadScreenMirrorPrefs(): ScreenMirrorPrefs {
  try {
    return parseScreenMirrorPrefs(window.localStorage.getItem(STORAGE_KEY));
  } catch {
    return createDefaultScreenMirrorPrefs();
  }
}

export function saveScreenMirrorPrefs(patch: Partial<ScreenMirrorPrefs>): ScreenMirrorPrefs {
  const current = loadScreenMirrorPrefs();
  const mergedIntervals: Partial<Record<string, unknown>> = {
    ...current.intervals,
    ...(patch.intervals ?? {}),
  };
  const nextIntervals = {} as Record<MirrorMode, number>;
  for (const mode of ALL_MODES) {
    nextIntervals[mode] = normalizeMirrorIntervalMs(
      mergedIntervals[mode],
      defaultIntervalFor(mode),
    );
  }
  const next: ScreenMirrorPrefs = {
    mode: patch.mode ? normalizeMirrorMode(patch.mode) : current.mode,
    intervals: nextIntervals,
  };
  try {
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  } catch {
    // 私密模式写失败时仍返回规范化结果
  }
  return next;
}
