/**
 * 从 UA / Client Hints 尽量解析机型（网页端无系统 API，不保证精确）。
 * - Chromium：userAgentData.getHighEntropyValues(['model'])
 * - 经典 UA：Android xx; MODEL) / iPhone / iPad
 */
export function parseDeviceModelFromUa(uaRaw: string | null | undefined): string | null {
  const ua = (uaRaw ?? "").trim();
  if (!ua) {
    return null;
  }

  const android = ua.match(/Android[^;]*;\s*([^;)]+)\)/i);
  if (android?.[1]) {
    const token = android[1].trim();
    // Chrome 缩减 UA 常用 "K" 占位，不是真机型
    if (token && !/^k$/i.test(token) && !/^wv$/i.test(token) && token.toLowerCase() !== "linux") {
      return token.slice(0, 128);
    }
  }

  if (/iPhone/i.test(ua)) {
    return "iPhone";
  }
  if (/iPad/i.test(ua)) {
    return "iPad";
  }
  if (/Android/i.test(ua)) {
    return "Android";
  }
  return null;
}
