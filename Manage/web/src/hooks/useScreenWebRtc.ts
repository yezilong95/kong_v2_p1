import { useCallback, useEffect, useRef, useState } from "react";
import { fetchIceServers, type IceServerConfig } from "../api/client";

export type WebrtcSignalMessage = {
  type: "webrtc_offer" | "webrtc_answer" | "webrtc_ice" | "webrtc_hangup";
  deviceId: string;
  payload?: Record<string, unknown>;
};

interface UseScreenWebRtcOptions {
  deviceId: string;
  enabled: boolean;
  sendScreen: (message: Record<string, unknown>) => void;
  onStatus?: (status: string) => void;
}

const RELAY_FAIL_FALLBACK_THRESHOLD = 2;
const RELAY_FALLBACK_STORAGE_KEY = "screen_webrtc_relay_fallback";

function readRelayFallback(): boolean {
  try {
    return window.sessionStorage.getItem(RELAY_FALLBACK_STORAGE_KEY) === "1";
  } catch {
    return false;
  }
}

function writeRelayFallback(active: boolean) {
  try {
    if (active) {
      window.sessionStorage.setItem(RELAY_FALLBACK_STORAGE_KEY, "1");
    } else {
      window.sessionStorage.removeItem(RELAY_FALLBACK_STORAGE_KEY);
    }
  } catch {
    // ignore storage errors (private mode etc.)
  }
}

export function useScreenWebRtc(options: UseScreenWebRtcOptions) {
  const { deviceId, enabled } = options;
  const [remoteStream, setRemoteStream] = useState<MediaStream | null>(null);
  const [connected, setConnected] = useState(false);
  const pcRef = useRef<RTCPeerConnection | null>(null);
  const iceServersRef = useRef<IceServerConfig[] | null>(null);
  const pendingIceRef = useRef<RTCIceCandidateInit[]>([]);
  const remoteDescSetRef = useRef(false);
  const optionsRef = useRef(options);
  optionsRef.current = options;

  // Relay 失败累计降级：连续 2 次 ICE failed → 当前会话改走 ALL。
  const relayFallbackActiveRef = useRef<boolean>(readRelayFallback());
  const relayFailStreakRef = useRef(0);
  const usingRelayOnlyRef = useRef(true);

  const cleanup = useCallback(() => {
    pendingIceRef.current = [];
    remoteDescSetRef.current = false;
    const pc = pcRef.current;
    pcRef.current = null;
    if (pc) {
      pc.onicecandidate = null;
      pc.ontrack = null;
      pc.onconnectionstatechange = null;
      pc.close();
    }
    setRemoteStream((current) => {
      current?.getTracks().forEach((track) => track.stop());
      return null;
    });
    setConnected(false);
  }, []);

  useEffect(() => {
    if (!enabled) {
      cleanup();
    }
  }, [cleanup, enabled]);

  useEffect(() => {
    return () => {
      cleanup();
    };
  }, [cleanup]);

  const ensurePeer = useCallback(async () => {
    if (pcRef.current) {
      return pcRef.current;
    }
    if (!iceServersRef.current) {
      iceServersRef.current = await fetchIceServers();
    }
    const useRelayOnly = !relayFallbackActiveRef.current;
    usingRelayOnlyRef.current = useRelayOnly;
    const pc = new RTCPeerConnection({
      iceServers: iceServersRef.current.map((server) => ({
        urls: server.urls,
        username: server.username,
        credential: server.credential,
      })),
      // 默认强制走 TURN relay，避免 UDP NAT 映射被回收导致 20s 后 DISCONNECTED。
      // 连续 ICE 失败达到阈值后降级为 "all"，sessionStorage 内保留直至关闭标签。
      iceTransportPolicy: useRelayOnly ? "relay" : "all",
    });
    // Do not pre-add transceiver: remote offer already defines m-lines.
    pc.ontrack = (event) => {
      const stream = event.streams[0] ?? new MediaStream([event.track]);
      setRemoteStream(stream);
      optionsRef.current.onStatus?.("WebRTC 画面已连接");
    };
    pc.onicecandidate = (event) => {
      if (!event.candidate) {
        return;
      }
      optionsRef.current.sendScreen({
        type: "webrtc_ice",
        deviceId: optionsRef.current.deviceId,
        payload: {
          candidate: event.candidate.candidate,
          sdpMid: event.candidate.sdpMid,
          sdpMLineIndex: event.candidate.sdpMLineIndex,
        },
      });
    };
    pc.onconnectionstatechange = () => {
      const state = pc.connectionState;
      setConnected(state === "connected");
      if (state === "connected") {
        // Relay 跑通一次视为 TURN 健康，清掉失败累计。
        if (usingRelayOnlyRef.current) {
          relayFailStreakRef.current = 0;
        }
      }
      if (state === "failed" || state === "closed" || state === "disconnected") {
        optionsRef.current.onStatus?.(
          state === "failed" ? "WebRTC 连接失败，等待 JPEG 降级…" : `WebRTC：${state}`,
        );
      }
      if (state === "failed" && usingRelayOnlyRef.current) {
        relayFailStreakRef.current += 1;
        if (
          relayFailStreakRef.current >= RELAY_FAIL_FALLBACK_THRESHOLD &&
          !relayFallbackActiveRef.current
        ) {
          relayFallbackActiveRef.current = true;
          writeRelayFallback(true);
          optionsRef.current.onStatus?.(
            `TURN relay 连续失败 ${relayFailStreakRef.current} 次，下一次改走 ALL`,
          );
        }
      }
    };
    pcRef.current = pc;
    return pc;
  }, []);

  const handleSignal = useCallback(
    async (message: WebrtcSignalMessage) => {
      if (!optionsRef.current.enabled) {
        return;
      }
      if (message.deviceId && message.deviceId !== optionsRef.current.deviceId) {
        return;
      }

      if (message.type === "webrtc_hangup") {
        cleanup();
        return;
      }

      if (message.type === "webrtc_offer") {
        const sdp = String(message.payload?.sdp ?? "");
        if (!sdp) {
          return;
        }
        cleanup();
        try {
          const pc = await ensurePeer();
          await pc.setRemoteDescription({ type: "offer", sdp });
          remoteDescSetRef.current = true;
          for (const candidate of pendingIceRef.current) {
            await pc.addIceCandidate(candidate);
          }
          pendingIceRef.current = [];
          const answer = await pc.createAnswer();
          await pc.setLocalDescription(answer);
          optionsRef.current.sendScreen({
            type: "webrtc_answer",
            deviceId: optionsRef.current.deviceId,
            payload: {
              sdp: answer.sdp,
              type: "answer",
            },
          });
          optionsRef.current.onStatus?.("WebRTC 应答已发送，等待媒体…");
        } catch (error) {
          const text = error instanceof Error ? error.message : "webrtc_answer_failed";
          optionsRef.current.onStatus?.(`WebRTC 失败：${text}`);
          cleanup();
        }
        return;
      }

      if (message.type === "webrtc_ice") {
        const candidate: RTCIceCandidateInit = {
          candidate: String(message.payload?.candidate ?? ""),
          sdpMid:
            message.payload?.sdpMid == null ? undefined : String(message.payload.sdpMid),
          sdpMLineIndex:
            message.payload?.sdpMLineIndex == null
              ? undefined
              : Number(message.payload.sdpMLineIndex),
        };
        if (!candidate.candidate) {
          return;
        }
        const pc = pcRef.current;
        if (!pc || !remoteDescSetRef.current) {
          pendingIceRef.current.push(candidate);
          return;
        }
        try {
          await pc.addIceCandidate(candidate);
        } catch {
          // ignore late/invalid candidates
        }
      }
    },
    [cleanup, ensurePeer],
  );

  // Reset when device changes.
  useEffect(() => {
    cleanup();
  }, [cleanup, deviceId]);

  return {
    remoteStream,
    connected,
    handleSignal,
    hangup: cleanup,
  };
}
