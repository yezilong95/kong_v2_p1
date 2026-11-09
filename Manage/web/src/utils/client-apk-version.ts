/** 点分版本比较：1.0.10 > 1.0.9。任一侧解析不出数字则不视为可更新。 */
export function parseVersionParts(raw: string): number[] {
  return raw
    .trim()
    .split(/[^\d]+/)
    .filter((part) => part.length > 0)
    .map((part) => Number(part))
    .filter((value) => Number.isFinite(value));
}

export function isVersionNewer(target: string, current: string): boolean {
  const next = parseVersionParts(target);
  const installed = parseVersionParts(current);
  if (next.length === 0 || installed.length === 0) {
    return false;
  }
  const length = Math.max(next.length, installed.length);
  for (let index = 0; index < length; index += 1) {
    const a = next[index] ?? 0;
    const b = installed[index] ?? 0;
    if (a !== b) {
      return a > b;
    }
  }
  return false;
}

export function canUpdateClientApk(targetVersion: string, deviceVersion: string | null | undefined): boolean {
  if (!targetVersion.trim() || !deviceVersion?.trim()) {
    return false;
  }
  return isVersionNewer(targetVersion, deviceVersion);
}
