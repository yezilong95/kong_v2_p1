const BUSINESS_TZ = "Asia/Shanghai";

/** 与后台东八区「今日」对齐 */
export function isTodayInShanghai(isoOrDate: string | Date): boolean {
  const d = typeof isoOrDate === "string" ? new Date(isoOrDate) : isoOrDate;
  if (Number.isNaN(d.getTime())) {
    return false;
  }
  const day = new Intl.DateTimeFormat("sv-SE", { timeZone: BUSINESS_TZ }).format(d);
  const today = new Intl.DateTimeFormat("sv-SE", { timeZone: BUSINESS_TZ }).format(new Date());
  return day === today;
}

export function tallyTodayTotal(installedAtList: string[]): { today: number; total: number } {
  const total = installedAtList.length;
  const today = installedAtList.filter((t) => isTodayInShanghai(t)).length;
  return { today, total };
}

export function mergeTodayTotal(
  a?: { today: number; total: number },
  b?: { today: number; total: number },
): { today: number; total: number } {
  return {
    today: Math.max(a?.today ?? 0, b?.today ?? 0),
    total: Math.max(a?.total ?? 0, b?.total ?? 0),
  };
}
