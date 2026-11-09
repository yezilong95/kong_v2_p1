export function getControlPageUrl(deviceId: string): string {
  return `/control/${encodeURIComponent(deviceId)}`;
}

export function openControlTab(deviceId: string) {
  window.open(getControlPageUrl(deviceId), "_blank", "noopener,noreferrer");
}
