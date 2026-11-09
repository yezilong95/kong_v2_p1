/** 后台「今日」统计按东八区自然日 */
export const BUSINESS_TZ = "+08:00";

/** 用于 WHERE：列值为 UTC 入库的 DATETIME */
export function sqlTodayInBusinessTz(column: string): string {
  return `DATE(CONVERT_TZ(${column}, '+00:00', '${BUSINESS_TZ}')) = DATE(CONVERT_TZ(UTC_TIMESTAMP(), '+00:00', '${BUSINESS_TZ}'))`;
}

export async function safeQueryCount(
  run: () => Promise<number>,
  options?: { table?: string },
): Promise<number> {
  try {
    return await run();
  } catch (error) {
    const code = (error as { code?: string }).code;
    if (code === "ER_NO_SUCH_TABLE") {
      console.warn(
        `[dashboard] table missing${options?.table ? `: ${options.table}` : ""}, count as 0`,
      );
      return 0;
    }
    throw error;
  }
}
