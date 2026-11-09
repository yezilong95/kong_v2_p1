export type AdbSessionStatus = "connected" | "offline";

export interface AdbSession {
  deviceId: string;
  serial: string;
  status: AdbSessionStatus;
}

export interface AdbDisconnectEvent {
  deviceId: string;
  serial: string;
  reason: string;
}

type AdbDisconnectListener = (event: AdbDisconnectEvent) => void;

export interface AdbSessionRegistry {
  register(deviceId: string, serial: string): AdbSession;
  get(deviceId: string): AdbSession | null;
  /**
   * 连接断了但端点还在。
   *
   * 和 `clear` 的区别：会话保留、状态翻成 `offline`，重连成功再翻回来。
   * 这样管理端能区分「从没连过」和「连过但现在断了，正在等重连」，
   * 而不是一律显示未连接。
   */
  markOffline(deviceId: string, reason?: string): boolean;
  clear(deviceId: string, reason?: string): boolean;
  subscribeDisconnect(listener: AdbDisconnectListener): () => void;
  reset(): void;
}

const LOOPBACK_SERIAL_PATTERN = /^127\.0\.0\.1:([1-9]\d{0,4})$/;

function validateRegistration(deviceId: string, serial: string): void {
  if (!deviceId.trim()) {
    throw new Error("adb_session_register_failed: deviceId is required");
  }

  const match = LOOPBACK_SERIAL_PATTERN.exec(serial);
  const port = Number(match?.[1] ?? 0);
  if (!match || port > 65_535) {
    throw new Error(
      `adb_session_register_failed: deviceId=${deviceId}, invalid serial=${serial}`,
    );
  }
}

export function createAdbSessionRegistry(): AdbSessionRegistry {
  const sessions = new Map<string, AdbSession>();
  const disconnectListeners = new Set<AdbDisconnectListener>();

  function emitDisconnect(session: AdbSession, reason: string) {
    const event: AdbDisconnectEvent = {
      deviceId: session.deviceId,
      serial: session.serial,
      reason,
    };
    for (const listener of disconnectListeners) {
      listener(event);
    }
  }

  return {
    register(deviceId, serial) {
      validateRegistration(deviceId, serial);
      const session: AdbSession = {
        deviceId,
        serial,
        status: "connected",
      };
      sessions.set(deviceId, session);
      return session;
    },

    get(deviceId) {
      return sessions.get(deviceId) ?? null;
    },

    markOffline(deviceId, reason = "disconnected") {
      const session = sessions.get(deviceId);
      if (!session) {
        return false;
      }
      // 已经是离线态就不再重复通知：断线回调可能连着来好几次
      if (session.status === "offline") {
        return false;
      }
      session.status = "offline";
      emitDisconnect(session, reason);
      return true;
    },

    clear(deviceId, reason = "disconnected") {
      const session = sessions.get(deviceId);
      if (!session) {
        return false;
      }

      sessions.delete(deviceId);
      // 离线态已经通知过，不重复；否则带看画面之类的工作进程会收到两次
      if (session.status === "connected") {
        emitDisconnect(session, reason);
      }
      return true;
    },

    subscribeDisconnect(listener) {
      disconnectListeners.add(listener);
      return () => {
        disconnectListeners.delete(listener);
      };
    },

    reset() {
      sessions.clear();
      disconnectListeners.clear();
    },
  };
}

export const adbSessionRegistry = createAdbSessionRegistry();
