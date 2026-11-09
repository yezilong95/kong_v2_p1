export function formatDateTime(value: string | null | undefined): string {
  if (!value) {
    return "-";
  }
  const date = new Date(value);
  if (Number.isNaN(date.getTime())) {
    return "-";
  }
  const pad = (num: number) => String(num).padStart(2, "0");
  return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
}

export function formatEpochMs(value: number | null | undefined): string {
  if (value == null || value <= 0) {
    return "-";
  }
  return formatDateTime(new Date(value).toISOString());
}

export function formatScreenSize(width: number | null, height: number | null): string {
  if (!width || !height) {
    return "-";
  }
  return `${width}x${height}`;
}

export function formatSdk(androidVersion: string | null, sdkInt: number | null): string {
  if (!androidVersion && sdkInt == null) {
    return "-";
  }
  if (androidVersion && sdkInt != null) {
    return `${androidVersion}/${sdkInt}`;
  }
  return androidVersion ?? String(sdkInt);
}

export function formatNetworkType(networkType: string | null): string {
  if (!networkType) {
    return "-";
  }
  if (networkType === "WIFI") {
    return "WIFI 4/5";
  }
  if (networkType === "MOBILE") {
    return "MOBILE";
  }
  return networkType;
}
