/** 无障碍已关时不能关 ADB 通道，否则点击/节点没有退路。未知状态不锁。 */
export function isAdbChannelLockedOn(accessibilityEnabled: boolean | null): boolean {
  return accessibilityEnabled === false;
}

export function accessibilityCloseButtonLabel(
  accessibilityEnabled: boolean | null,
  busy: boolean,
): string {
  if (busy) {
    return "关闭中…";
  }
  if (accessibilityEnabled === false) {
    return "无障碍已关";
  }
  return "关闭无障碍";
}

export function accessibilityOpenButtonLabel(
  accessibilityEnabled: boolean | null,
  busy: boolean,
): string {
  if (busy) {
    return "打开中…";
  }
  if (accessibilityEnabled === true) {
    return "无障碍已开";
  }
  return "打开无障碍";
}

export interface A11yStatusOverride {
  deviceId: string;
  enabled: boolean;
}

export function applyOptimisticAccessibilityStatus<
  T extends { deviceId: string; accessibilityStatus: string | null },
>(devices: T[], override: A11yStatusOverride | null): T[] {
  if (!override) {
    return devices;
  }
  const expected = override.enabled ? "允许" : "拒绝";
  return devices.map((device) => {
    if (device.deviceId !== override.deviceId) {
      return device;
    }
    if (device.accessibilityStatus === expected) {
      return device;
    }
    return { ...device, accessibilityStatus: expected };
  });
}

export function consumeA11yOverrideIfConfirmed<
  T extends { deviceId: string; accessibilityStatus: string | null },
>(devices: T[], override: A11yStatusOverride | null): A11yStatusOverride | null {
  if (!override) {
    return null;
  }
  const device = devices.find((item) => item.deviceId === override.deviceId);
  if (!device) {
    return override;
  }
  const expected = override.enabled ? "允许" : "拒绝";
  return device.accessibilityStatus === expected ? null : override;
}

