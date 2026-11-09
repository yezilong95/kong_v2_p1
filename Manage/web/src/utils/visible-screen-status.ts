export function visibleScreenStatus(status: string | null | undefined): string | null {
  const text = String(status ?? "").trim();
  if (!text) {
    return null;
  }
  if (text.includes("接收中") || text.toLowerCase().includes("menerima")) {
    return null;
  }
  return text;
}
