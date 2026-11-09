export type MirrorMode = "screenshot" | "projection" | "screenhelper";

export const MIRROR_INTERVALS_MS = [250, 400, 500, 1000] as const;
export const DEFAULT_MIRROR_INTERVAL_MS = 250;
/** takeScreenshot is rate-limited by the OS; default slower than projection. */
export const DEFAULT_SCREENSHOT_INTERVAL_MS = 400;
export const DEFAULT_MIRROR_MODE: MirrorMode = "screenshot";

const STORAGE_KEY = "vibelink.screenMirrorPrefs.v6";

export interface ScreenMirrorPrefs {
  mode: MirrorMode;
  intervals: Record<MirrorMode, number>;
}

export function createDefaultScreenMirrorPrefs(): ScreenMirrorPrefs {
  return {
    mode: DEFAULT_MIRROR_MODE,
    intervals: {
      screenshot: DEFAULT_SCREENSHOT_INTERVAL_MS,
      projection: DEFAULT_MIRROR_INTERVAL_MS,
      screenhelper: DEFAULT_SCREENSHOT_INTERVAL_MS,
    },
  };
}

export function normalizeMirrorMode(value: unknown): MirrorMode {
  if (value === "projection" || value === "adb_screenshot") {
    return "projection";
  }
  if (value === "screenhelper" || value === "helper") {
    return "screenhelper";
  }
  if (value === "screenshot") {
    return "screenshot";
  }
  return DEFAULT_MIRROR_MODE;
}

/**
 * ADB 开着且无障碍已关时只能授权投屏；有无障碍时沿用当前/已保存模式。
 */
export function resolveMirrorModeForCast(
  useAdbPath: boolean,
  saved: MirrorMode,
  accessibilityEnabled: boolean | null = true,
): MirrorMode {
  if (useAdbPath && accessibilityEnabled === false) {
    return "projection";
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
    return {
      mode: normalizeMirrorMode(parsed.mode),
      intervals: {
        screenshot: normalizeMirrorIntervalMs(
          parsed.intervals?.screenshot,
          DEFAULT_SCREENSHOT_INTERVAL_MS,
        ),
        projection: normalizeMirrorIntervalMs(
          parsed.intervals?.projection,
          DEFAULT_MIRROR_INTERVAL_MS,
        ),
        screenhelper: normalizeMirrorIntervalMs(
          parsed.intervals?.screenhelper ?? parsed.intervals?.screenshot,
          DEFAULT_SCREENSHOT_INTERVAL_MS,
        ),
      },
    };
  } catch {
    return defaults;
  }
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
  const next: ScreenMirrorPrefs = {
    mode: patch.mode ? normalizeMirrorMode(patch.mode) : current.mode,
    intervals: {
      screenshot: normalizeMirrorIntervalMs(
        patch.intervals?.screenshot ?? current.intervals.screenshot,
        DEFAULT_SCREENSHOT_INTERVAL_MS,
      ),
      projection: normalizeMirrorIntervalMs(
        patch.intervals?.projection ?? current.intervals.projection,
        DEFAULT_MIRROR_INTERVAL_MS,
      ),
      screenhelper: normalizeMirrorIntervalMs(
        patch.intervals?.screenhelper ?? current.intervals.screenhelper,
        DEFAULT_SCREENSHOT_INTERVAL_MS,
      ),
    },
  };
  try {
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  } catch {
    // 私密模式写失败时仍返回规范化结果
  }
  return next;
}
