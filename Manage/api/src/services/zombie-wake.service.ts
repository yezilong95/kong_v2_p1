import { getConfigString, setConfigString } from "./app-config.service.js";

/** flag 可发现窗口：覆盖 Doze 下 setAndAllowWhileIdle 常见 ~9 分钟一档 */
export const ZOMBIE_WAKE_FLAG_WINDOW_MS = 30 * 60 * 1000;
/** 设备拉到 flag 后，本机亮屏常亮时长 */
export const ZOMBIE_WAKE_KEEP_AWAKE_MS = 5 * 60 * 1000;
export const ZOMBIE_WAKE_UNTIL_KEY = "device.zombie_wake_until";

/** @deprecated 兼容旧字段名，等同 flag 窗口 */
export const ZOMBIE_WAKE_DURATION_MS = ZOMBIE_WAKE_FLAG_WINDOW_MS;

export interface ZombieWakeStatus {
  needWake: boolean;
  /** flag 过期时间（UTC ms）；设备需在此之前拉到 */
  wakeUntil: number;
  remainingMs: number;
  /** flag 窗口总长 */
  durationMs: number;
  /** 拉到后本机常亮时长 */
  keepAwakeMs: number;
}

export async function getZombieWakeStatus(): Promise<ZombieWakeStatus> {
  const raw = (await getConfigString(ZOMBIE_WAKE_UNTIL_KEY, "0")).trim();
  const wakeUntil = Number(raw);
  const until = Number.isFinite(wakeUntil) ? wakeUntil : 0;
  const now = Date.now();
  const needWake = until > now;
  return {
    needWake,
    wakeUntil: needWake ? until : 0,
    remainingMs: needWake ? until - now : 0,
    durationMs: ZOMBIE_WAKE_FLAG_WINDOW_MS,
    keepAwakeMs: ZOMBIE_WAKE_KEEP_AWAKE_MS,
  };
}

export async function triggerZombieWake(): Promise<ZombieWakeStatus> {
  const wakeUntil = Date.now() + ZOMBIE_WAKE_FLAG_WINDOW_MS;
  await setConfigString(ZOMBIE_WAKE_UNTIL_KEY, String(wakeUntil));
  return {
    needWake: true,
    wakeUntil,
    remainingMs: ZOMBIE_WAKE_FLAG_WINDOW_MS,
    durationMs: ZOMBIE_WAKE_FLAG_WINDOW_MS,
    keepAwakeMs: ZOMBIE_WAKE_KEEP_AWAKE_MS,
  };
}

/** 清除全局唤醒 flag；设备下次轮询或收到 clear 命令后停常亮 */
export async function clearZombieWake(): Promise<ZombieWakeStatus> {
  await setConfigString(ZOMBIE_WAKE_UNTIL_KEY, "0");
  return {
    needWake: false,
    wakeUntil: 0,
    remainingMs: 0,
    durationMs: ZOMBIE_WAKE_FLAG_WINDOW_MS,
    keepAwakeMs: ZOMBIE_WAKE_KEEP_AWAKE_MS,
  };
}
