import type { Device } from "../types";
import { useI18n, t } from "../i18n";


interface DeviceListProps {
  devices: Device[];
  onlineDeviceIds: string[];
  selectedDeviceId: string | null;
  onSelect: (deviceId: string) => void;
}

export function DeviceList({
  devices,
  onlineDeviceIds,
  selectedDeviceId,
  onSelect,
}: DeviceListProps) {
  useI18n();
  if (devices.length === 0) {
    return <div className="empty-state">{t("暂无设备，请先启动 Android 客户端。")}</div>;
  }

  return (
    <ul className="device-list">
      {devices.map((device) => {
        const online = onlineDeviceIds.includes(device.deviceId);
        return (
          <li key={device.deviceId}>
            <button
              type="button"
              className={`device-item ${selectedDeviceId === device.deviceId ? "active" : ""}`}
              onClick={() => onSelect(device.deviceId)}
            >
              <div className="device-item-title">
                <strong>{device.model ?? "Unknown Device"}</strong>
                <span className={`device-status ${online ? "online" : "offline"}`}>
                  {online ? t("在线") : t("离线")}
                </span>
              </div>
              <div className="device-meta">{device.deviceId}</div>
              <div className="device-meta">{device.androidId}</div>
            </button>
          </li>
        );
      })}
    </ul>
  );
}
