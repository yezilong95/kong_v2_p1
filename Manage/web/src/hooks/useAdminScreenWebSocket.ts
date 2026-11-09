import { useCallback, useEffect, useRef, useState } from "react";
import { getAdminToken } from "../auth/session";
import type { ScreenFrame, UiTreeSnapshot } from "../types";
import { decodeAdbPngFrame, shouldDiscardAdbPngSeq } from "../utils/adb-png-protocol";
import { parseUiTreeSnapshot } from "../utils/ui-tree";
import { t } from "../i18n";


interface UseAdminScreenWebSocketOptions {
  getDeviceId?: () => string;
  onScreenFrame?: (frame: ScreenFrame) => void;
  onScreenStatus?: (status: string) => void;
  /** ADB 通道的 UI 树（adb_ui_tree_frame）。 */
  onUiTreeSnapshot?: (snapshot: UiTreeSnapshot) => void;
  /** 无障碍主动推送的 UI 树（ui_tree_push，走画面通道）。 */
  onA11yUiTreeSnapshot?: (snapshot: UiTreeSnapshot) => void;
  onUiTreeStatus?: (status: string) => void;
  onAdbScreenDisconnected?: (deviceId: string, reason: string) => void;
  onAdbUiTreeDisconnected?: (deviceId: string, reason: string) => void;
  onWebrtcSignal?: (message: {
    type: "webrtc_offer" | "webrtc_answer" | "webrtc_ice" | "webrtc_hangup";
    deviceId: string;
    payload?: Record<string, unknown>;
  }) => void;
}

function bytesToBase64(bytes: Uint8Array): string {
  let binary = "";
  const chunk = 0x8000;
  for (let index = 0; index < bytes.byteLength; index += chunk) {
    binary += String.fromCharCode(...bytes.subarray(index, index + chunk));
  }
  return btoa(binary);
}

export function useAdminScreenWebSocket(options: UseAdminScreenWebSocketOptions) {
  const [connected, setConnected] = useState(false);
  const optionsRef = useRef(options);
  const socketRef = useRef<WebSocket | null>(null);
  const lastSeqRef = useRef<number | null>(null);
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

    function handleBinaryFrame(buffer: ArrayBuffer) {
      try {
        const decoded = decodeAdbPngFrame(buffer);
        if (shouldDiscardAdbPngSeq(lastSeqRef.current, decoded.seq)) {
          return;
        }
        lastSeqRef.current = decoded.seq;
        const payloadCopy = new Uint8Array(decoded.payload.byteLength);
        payloadCopy.set(decoded.payload);
        const objectUrl = URL.createObjectURL(
          new Blob([payloadCopy.buffer as ArrayBuffer], { type: "image/png" }),
        );
        optionsRef.current.onScreenStatus?.(t("ADB 画面接收中…"));
        optionsRef.current.onScreenFrame?.({
          deviceId: optionsRef.current.getDeviceId?.() || "",
          data: bytesToBase64(decoded.payload),
          screenWidth: decoded.width,
          screenHeight: decoded.height,
          imageWidth: decoded.width,
          imageHeight: decoded.height,
          capturedAt: decoded.timestamp,
          source: "adb_screenshot",
          mimeType: "image/png",
          objectUrl,
          seq: decoded.seq,
        });
      } catch (error) {
        const message = error instanceof Error ? error.message : "adb_png_decode_failed";
        optionsRef.current.onScreenStatus?.(t("ADB 画面解码失败：{0}", { 0: message }));
      }
    }

    function connect() {
      const protocol = window.location.protocol === "https:" ? "wss:" : "ws:";
      const token = getAdminToken();
      const query = token ? `?token=${encodeURIComponent(token)}` : "";
      socket = new WebSocket(`${protocol}//${window.location.host}/ws/admin/screen${query}`);
      socket.binaryType = "arraybuffer";
      socketRef.current = socket;

      socket.onopen = () => {
        if (disposed) {
          return;
        }
        lastSeqRef.current = null;
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
        if (typeof event.data !== "string") {
          const buffer = event.data instanceof ArrayBuffer ? event.data : null;
          if (buffer) {
            handleBinaryFrame(buffer);
          }
          return;
        }

        const message = JSON.parse(event.data) as {
          type: string;
          deviceId: string;
          payload?: Record<string, unknown>;
          started?: boolean;
          message?: string;
          reason?: string;
          facing?: "front" | "back";
          timestamp: number;
        };

        if (message.type === "screen_frame") {
          const payload = message.payload ?? {};
          const source = payload.source as ScreenFrame["source"] | undefined;
          lastSeqRef.current = null;
          optionsRef.current.onScreenStatus?.(t("画面接收中…"));
          optionsRef.current.onScreenFrame?.({
            deviceId: message.deviceId,
            data: String(payload.data ?? ""),
            screenWidth: Number(payload.screenWidth ?? 0),
            screenHeight: Number(payload.screenHeight ?? 0),
            imageWidth: Number(payload.imageWidth ?? payload.screenWidth ?? 0),
            imageHeight: Number(payload.imageHeight ?? payload.screenHeight ?? 0),
            capturedAt: Number(payload.capturedAt ?? message.timestamp),
            source,
            mimeType: "image/jpeg",
          });
          return;
        }

        if (message.type === "adb_ui_tree_frame") {
          const parsed = parseUiTreeSnapshot(message.deviceId, message.payload ?? {});
          if (parsed) {
            optionsRef.current.onUiTreeSnapshot?.(parsed);
            optionsRef.current.onUiTreeStatus?.("");
          } else {
            optionsRef.current.onUiTreeStatus?.(t("ADB UI 树解析失败"));
          }
          return;
        }

        if (message.type === "ui_tree_push") {
          // 无障碍主动推送：payload = { seq, snapshot, pushedAt }
          const payload = message.payload ?? {};
          const snapshot = (payload.snapshot ?? payload) as Record<string, unknown>;
          const parsed = parseUiTreeSnapshot(message.deviceId, snapshot);
          if (parsed) {
            optionsRef.current.onA11yUiTreeSnapshot?.(parsed);
            optionsRef.current.onUiTreeStatus?.("");
          } else {
            optionsRef.current.onUiTreeStatus?.(t("UI 树解析失败"));
          }
          return;
        }

        if (message.type === "ui_tree_subscribed") {
          const started = Boolean(message.started);
          if (!started) {
            optionsRef.current.onUiTreeStatus?.(
              t("UI 订阅失败：{0}", { 0: String(message.message ?? "unknown") }),
            );
          }
          return;
        }

        if (message.type === "ui_tree_unsubscribed") {
          return;
        }

        if (message.type === "screen_subscribed") {
          const started = Boolean(message.started);
          const hint = started
            ? t("已请求投屏，等待手机授权/首帧…")
            : t("投屏启动失败：{0}", { 0: String(message.message ?? "unknown") });
          optionsRef.current.onScreenStatus?.(hint);
          return;
        }

        if (message.type === "adb_ui_tree_subscribed") {
          const started = Boolean(message.started);
          optionsRef.current.onUiTreeStatus?.(
            started ? "" : t("ADB 节点失败：{0}", { 0: String(message.message ?? "unknown") }),
          );
          return;
        }

        if (message.type === "camera_subscribed") {
          const started = Boolean(message.started);
          const facingLabel = message.facing === "front" ? t("前置") : t("后置");
          const hint = started
            ? t("已请求{0}摄像头，等待手机授权/首帧…", { 0: facingLabel })
            : t("摄像头启动失败：{0}", { 0: String(message.message ?? "unknown") });
          optionsRef.current.onScreenStatus?.(hint);
          return;
        }

        if (message.type === "adb_screen_disconnected") {
          lastSeqRef.current = null;
          optionsRef.current.onAdbScreenDisconnected?.(
            message.deviceId,
            String(message.reason ?? "disconnected"),
          );
          optionsRef.current.onScreenStatus?.(t("ADB 已断开，画面已清空"));
          return;
        }

        if (message.type === "adb_ui_tree_disconnected") {
          optionsRef.current.onAdbUiTreeDisconnected?.(
            message.deviceId,
            String(message.reason ?? "disconnected"),
          );
          optionsRef.current.onUiTreeStatus?.(t("ADB 已断开，节点已清空"));
          return;
        }

        if (message.type === "adb_screen_error") {
          optionsRef.current.onScreenStatus?.(t("ADB 画面：{0}", { 0: String(message.message ?? "error") }));
          return;
        }

        if (message.type === "adb_ui_tree_error") {
          optionsRef.current.onUiTreeStatus?.(t("ADB 节点：{0}", { 0: String(message.message ?? "error") }));
          return;
        }

        if (
          message.type === "webrtc_offer" ||
          message.type === "webrtc_answer" ||
          message.type === "webrtc_ice" ||
          message.type === "webrtc_hangup"
        ) {
          optionsRef.current.onWebrtcSignal?.({
            type: message.type,
            deviceId: message.deviceId,
            payload: message.payload,
          });
          return;
        }

        if (message.type === "screen_status") {
          const status = String(message.payload?.status ?? "");
          const detail = String(message.payload?.detail ?? "");
          if (status === "streaming") {
            optionsRef.current.onScreenStatus?.(t("画面接收中…"));
            return;
          }
          if (status === "waiting_accessibility") {
            optionsRef.current.onScreenStatus?.(t("画面：等待无障碍服务连接…"));
            return;
          }
          if (status === "capture_failed") {
            optionsRef.current.onScreenStatus?.(
              t("画面：采集失败（{0}）", { 0: detail || "unknown" }),
            );
            return;
          }
          if (status) {
            optionsRef.current.onScreenStatus?.(
              detail ? t("画面：{0}（{1}）", { 0: status, 1: detail }) : t("画面：{0}", { 0: status }),
            );
          }
          return;
        }

        if (message.type === "screen_error") {
          const errorCode = Number(message.payload?.errorCode ?? -1);
          const detail = String(message.payload?.message ?? "").trim();
          const hint =
            errorCode === 201
              ? t("请在手机上允许「投屏/录制屏幕」（假更新页会弹出系统授权）")
              : errorCode === 202
                ? t("创建录屏会话失败，请重试投屏")
                : errorCode === 203
                  ? t("录屏已停止，请重新打开实时屏幕")
                  : errorCode === 204
                    ? t("画面编码失败")
                    : errorCode === 210
                      ? t("无障碍服务未连接，免授权投屏不可用")
                      : errorCode === 211
                        ? t("系统不支持无障碍截屏（需 Android 11+）")
                        : errorCode === 212
                          ? t("免授权截屏失败{0}", { 0: detail ? `：${detail}` : "" })
                          : errorCode === 213
                            ? t("长时间无首帧{0}", { 0: detail ? `：${detail}` : "" })
                            : errorCode === 220
                              ? detail === "ice_timeout" || detail === "ice_failed"
                                ? t("WebRTC ICE 失败（{0}）：请检查服务器 coturn 是否在跑，以及安全组是否放行 UDP/TCP 3478 与 UDP 49160–49200；已降级 JPEG", { 0: detail })
                                : t("WebRTC 失败{0}，尝试 JPEG 降级", { 0: detail ? `：${detail}` : "" })
                              : errorCode === 301
                                ? t("请在手机上允许「摄像头」权限")
                                : `errorCode=${errorCode}${detail ? `：${detail}` : ""}`;
          optionsRef.current.onScreenStatus?.(t("画面：{0}", { 0: hint }));
        }
      };
    }

    connect();

    return () => {
      disposed = true;
      if (retryTimer) {
        window.clearTimeout(retryTimer);
      }
      if (!import.meta.hot) {
        send({ type: "unsubscribe_screen" });
        send({ type: "unsubscribe_camera" });
        send({ type: "unsubscribe_adb_ui_tree" });
        send({ type: "unsubscribe_ui_tree" });
      }
      socket?.close();
      socketRef.current = null;
    };
  }, [send]);

  return { connected, send };
}
