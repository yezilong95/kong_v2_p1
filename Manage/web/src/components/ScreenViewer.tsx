import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { sendAdbInput, sendCommand } from "../api/client";
import { ScreenAssistant } from "./ScreenAssistant";
import { DeviceAdbOpsSection } from "./DeviceAdbOpsSection";
import { DeviceRemarkPanel } from "./DeviceRemarkPanel";
import { DeviceScreenshotPanel } from "./DeviceScreenshotPanel";
import { AssistantVirtualKeyboard } from "./AssistantVirtualKeyboard";
import { ScreenAssistantOpsPanel } from "./ScreenAssistantOpsPanel";
import { PhoneAgentPanel } from "./PhoneAgentPanel";
import {
  AssistantSkeleton,
  DEFAULT_DEVICE_SIZE,
  deviceStageStyle,
  MirrorSkeleton,
  OpsPanelSkeleton,
} from "./ScreenSkeleton";
import { useUiLabelTranslation } from "../hooks/useUiLabelTranslation";
import type { OpsWorkspaceKind } from "../types/ops-workspace";
import { opsWorkspaceLabel } from "../types/ops-workspace";
import type { ScreenFrame, UiTreeSnapshot } from "../types";
import type { MirrorMode } from "../utils/screen-mirror-prefs";
import { baseMirrorMode, mirrorTransport, mirrorQuality, resolveMirrorModeForCast } from "../utils/screen-mirror-prefs";
import {
  filterHumanUsefulNodes,
  filterNodesByPrimaryPackage,
  collectNodeLabelTexts,
  getUiTreeDisplayMax,
  selectDisplayNodes,
  type UiTreeDisplayMode,
} from "../utils/ui-tree";
import { useI18n, t } from "../i18n";


interface ScreenViewerProps {
  deviceId: string | null;
  isOnline: boolean;
  frame: ScreenFrame | null;
  webrtcStream?: MediaStream | null;
  webrtcConnected?: boolean;
  /** Real phone pixels from webrtc_offer (capture/encode size is different). */
  webrtcScreenSize?: { width: number; height: number } | null;
  pollMs: number;
  errorText?: string | null;
  castEnabled: boolean;
  mirrorMode: MirrorMode;
  cameraFacing: "front" | "back" | null;
  onCastEnabledChange: (enabled: boolean) => void;
  onMirrorModeChange: (mode: MirrorMode) => void;
  onCameraFacingChange: (facing: "front" | "back" | null) => void;
  onPollMsChange: (pollMs: number) => void;
  sendScreen: (message: Record<string, unknown>) => void;
  screenConnected: boolean;
  adbConnected: boolean;
  adbChannelEnabled: boolean;
  accessibilityEnabled: boolean | null;
  onAdbChannelChange: (enabled: boolean) => void;
  onAccessibilityEnabledChange: (enabled: boolean) => void;
  onAdbConnectedChange: (connected: boolean) => void;
  uiSnapshot: UiTreeSnapshot | null;
  uiError?: string | null;
  /** 公共操作区当前视图 */
  opsWorkspace?: OpsWorkspaceKind;
  /** 供右侧工具栏 portal 锁屏/应用等内容 */
  onWorkspaceHostChange?: (host: HTMLDivElement | null) => void;
  remark?: string | null;
  onRemarkSaved?: (remark: string | null) => void;
  onCommandSent?: (message: string) => void;
  /** 把延迟指标上报到顶栏展示 */
  onLatencyReport?: (latency: ScreenLatencyReport) => void;
}

export interface ScreenLatencyReport {
  controlLatencyMs: number | null;
  latencyMs: number | null;
  frameGapMs: number | null;
  tone: "ok" | "warn" | "bad" | "idle";
}

interface RenderedImageRect {
  left: number;
  top: number;
  width: number;
  height: number;
}

interface SurfacePoint {
  left: number;
  top: number;
  deviceX: number;
  deviceY: number;
}

interface SwipePreview {
  x1: number;
  y1: number;
  x2: number;
  y2: number;
  length: number;
  angle: number;
}

const SWIPE_MIN_DEVICE_PX = 24;
const SWIPE_MIN_HORIZONTAL_DEVICE_PX = 12;
const CURSOR_DOT_SIZE = 22;
const CONTROL_PING_MS = 3_000;
const LATENCY_EMA_ALPHA = 0.3;

function smoothLatencyMs(previous: number | null, sample: number): number {
  if (previous == null || !Number.isFinite(previous)) {
    return sample;
  }
  return Math.round(previous * (1 - LATENCY_EMA_ALPHA) + sample * LATENCY_EMA_ALPHA);
}

function latencyTone(latencyMs: number | null, pollMs: number): "ok" | "warn" | "bad" | "idle" {
  if (latencyMs == null) {
    return "idle";
  }
  if (latencyMs <= pollMs * 1.5) {
    return "ok";
  }
  if (latencyMs <= pollMs * 3) {
    return "warn";
  }
  return "bad";
}

function getMediaIntrinsicSize(
  media: HTMLImageElement | HTMLVideoElement,
): { width: number; height: number } | null {
  if (media instanceof HTMLVideoElement) {
    if (media.videoWidth > 0 && media.videoHeight > 0) {
      return { width: media.videoWidth, height: media.videoHeight };
    }
    return null;
  }
  if (media.naturalWidth > 0 && media.naturalHeight > 0) {
    return { width: media.naturalWidth, height: media.naturalHeight };
  }
  return null;
}

/**
 * Map visible media box. Mirror CSS uses object-fit:fill → full element rect.
 * Other modes use contain-style letterboxing from intrinsic size when available.
 */
function getRenderedMediaRect(
  media: HTMLImageElement | HTMLVideoElement,
  fillElement: boolean,
): RenderedImageRect | null {
  const rect = media.getBoundingClientRect();
  if (rect.width <= 0 || rect.height <= 0) {
    return null;
  }
  if (fillElement) {
    return { left: rect.left, top: rect.top, width: rect.width, height: rect.height };
  }

  const intrinsic = getMediaIntrinsicSize(media);
  if (!intrinsic) {
    return { left: rect.left, top: rect.top, width: rect.width, height: rect.height };
  }

  const scale = Math.min(rect.width / intrinsic.width, rect.height / intrinsic.height);
  const width = intrinsic.width * scale;
  const height = intrinsic.height * scale;
  return {
    left: rect.left + (rect.width - width) / 2,
    top: rect.top + (rect.height - height) / 2,
    width,
    height,
  };
}

function mapClientToSurfacePoint(
  clientX: number,
  clientY: number,
  media: HTMLImageElement | HTMLVideoElement,
  surfaceEl: HTMLElement,
  deviceWidth: number,
  deviceHeight: number,
  fillElement: boolean,
  clampToBounds = false,
): SurfacePoint | null {
  if (deviceWidth <= 0 || deviceHeight <= 0) {
    return null;
  }
  const rendered = getRenderedMediaRect(media, fillElement);
  if (!rendered || rendered.width <= 0 || rendered.height <= 0) {
    return null;
  }

  let relX = (clientX - rendered.left) / rendered.width;
  let relY = (clientY - rendered.top) / rendered.height;
  if (clampToBounds) {
    relX = Math.min(1, Math.max(0, relX));
    relY = Math.min(1, Math.max(0, relY));
  } else if (relX < 0 || relX > 1 || relY < 0 || relY > 1) {
    return null;
  }

  const surfaceRect = surfaceEl.getBoundingClientRect();
  return {
    left: rendered.left - surfaceRect.left + relX * rendered.width,
    top: rendered.top - surfaceRect.top + relY * rendered.height,
    deviceX: Math.round(relX * deviceWidth),
    deviceY: Math.round(relY * deviceHeight),
  };
}

function buildSwipePreview(start: SurfacePoint, end: SurfacePoint): SwipePreview {
  const dx = end.left - start.left;
  const dy = end.top - start.top;
  const length = Math.hypot(dx, dy);
  const angle = (Math.atan2(dy, dx) * 180) / Math.PI;
  return {
    x1: start.left,
    y1: start.top,
    x2: end.left,
    y2: end.top,
    length,
    angle,
  };
}

export function ScreenViewer({
  deviceId,
  isOnline,
  frame,
  webrtcStream = null,
  webrtcConnected = false,
  webrtcScreenSize = null,
  pollMs,
  errorText,
  castEnabled,
  mirrorMode,
  cameraFacing,
  onCastEnabledChange,
  onMirrorModeChange,
  onCameraFacingChange,
  onPollMsChange,
  sendScreen,
  screenConnected,
  adbConnected,
  adbChannelEnabled,
  accessibilityEnabled,
  onAdbChannelChange,
  onAccessibilityEnabledChange,
  onAdbConnectedChange,
  uiSnapshot,
  uiError,
  opsWorkspace = "none",
  onWorkspaceHostChange,
  remark = null,
  onRemarkSaved,
  onCommandSent,
  onLatencyReport,
}: ScreenViewerProps) {
  useI18n();
  const surfaceRef = useRef<HTMLDivElement>(null);
  const imgRef = useRef<HTMLImageElement>(null);
  const videoRef = useRef<HTMLVideoElement>(null);
  const dragStartRef = useRef<SurfacePoint | null>(null);
  const dragEndRef = useRef<SurfacePoint | null>(null);
  const [cursorPoint, setCursorPoint] = useState<SurfacePoint | null>(null);
  const [isDragging, setIsDragging] = useState(false);
  const [swipePreview, setSwipePreview] = useState<SwipePreview | null>(null);
  const [refreshBusy, setRefreshBusy] = useState(false);
  const [assistantEnabled, setAssistantEnabled] = useState(false);
  const [uiTreeDisplayMode, setUiTreeDisplayMode] = useState<UiTreeDisplayMode>("compact");
  const [translateEnabled, setTranslateEnabled] = useState(false);
  const [controlLatencyMs, setControlLatencyMs] = useState<number | null>(null);
  const [latencyMs, setLatencyMs] = useState<number | null>(null);
  const [frameGapMs, setFrameGapMs] = useState<number | null>(null);
  const controlPingInFlightRef = useRef(false);
  const lastFrameReceiveAtRef = useRef<number | null>(null);
  const controlLatencySmoothRef = useRef<number | null>(null);
  const latencySmoothRef = useRef<number | null>(null);
  const frameGapSmoothRef = useRef<number | null>(null);
  const lastFrameKeyRef = useRef<string | null>(null);

  const streamActive = castEnabled || cameraFacing !== null;
  const isCameraStream = cameraFacing !== null || Boolean(frame?.source?.startsWith("camera_"));
  const cameraLabel = cameraFacing === "front" || frame?.source === "camera_front" ? t("前置摄像头") : t("后置摄像头");
  const useAdbPath = adbConnected && adbChannelEnabled;
  const useAdbInput = useAdbPath;
  // L3: ADB / a11y 推送共享 uiSnapshot；两者通过 useAdbPath 决定谁订阅，不会同时写入。
  const activeUiSnapshot = uiSnapshot;
  const activeUiError = uiError;
  const latencyToneClass = latencyTone(
    controlLatencyMs ?? latencyMs ?? frameGapMs,
    streamActive ? pollMs : CONTROL_PING_MS,
  );

  // 把延迟指标上报到顶栏（RemoteControlPage）展示
  useEffect(() => {
    onLatencyReport?.({
      controlLatencyMs,
      latencyMs,
      frameGapMs,
      tone: latencyToneClass,
    });
  }, [controlLatencyMs, latencyMs, frameGapMs, latencyToneClass, onLatencyReport]);
  // Prefer JPEG once frames arrive if WebRTC never reaches connected (cross-NAT fallback).
  const hasWebrtcVideo = Boolean(webrtcStream) && (webrtcConnected || !frame);

  // Control-channel RTT as soon as the detail page is open (no cast required).
  useEffect(() => {
    if (!deviceId || !isOnline) {
      controlLatencySmoothRef.current = null;
      setControlLatencyMs(null);
      return;
    }

    let disposed = false;

    async function probeControlLatency() {
      if (disposed || controlPingInFlightRef.current) {
        return;
      }
      controlPingInFlightRef.current = true;
      const startedAt = Date.now();
      try {
        await sendCommand({
          deviceId: deviceId!,
          cmd: "ping",
          waitMs: 8_000,
        });
        if (disposed) {
          return;
        }
        const sample = Math.max(0, Date.now() - startedAt);
        controlLatencySmoothRef.current = smoothLatencyMs(
          controlLatencySmoothRef.current,
          sample,
        );
        setControlLatencyMs(controlLatencySmoothRef.current);
      } catch {
        // Keep last sample; offline / timeout handled by isOnline badge.
      } finally {
        controlPingInFlightRef.current = false;
      }
    }

    void probeControlLatency();
    const timer = window.setInterval(() => {
      void probeControlLatency();
    }, CONTROL_PING_MS);

    return () => {
      disposed = true;
      window.clearInterval(timer);
    };
  }, [deviceId, isOnline]);

  useEffect(() => {
    const video = videoRef.current;
    if (!video) {
      return;
    }
    if (webrtcStream) {
      video.srcObject = webrtcStream;
      void video.play().catch(() => {
        // autoplay can fail until user gesture; muted helps
      });
      return;
    }
    video.srcObject = null;
  }, [webrtcStream]);

  useEffect(() => {
    if (!streamActive || !frame) {
      lastFrameReceiveAtRef.current = null;
      latencySmoothRef.current = null;
      frameGapSmoothRef.current = null;
      lastFrameKeyRef.current = null;
      setLatencyMs(null);
      setFrameGapMs(null);
      return;
    }

    const frameKey = `${frame.seq ?? ""}:${frame.capturedAt}:${frame.objectUrl ?? frame.data.slice(0, 24)}`;
    if (frameKey === lastFrameKeyRef.current) {
      return;
    }
    lastFrameKeyRef.current = frameKey;

    const now = Date.now();
    const sampleLatency = Math.max(0, now - frame.capturedAt);
    latencySmoothRef.current = smoothLatencyMs(latencySmoothRef.current, sampleLatency);
    setLatencyMs(latencySmoothRef.current);

    const previousReceiveAt = lastFrameReceiveAtRef.current;
    lastFrameReceiveAtRef.current = now;
    if (previousReceiveAt != null) {
      const sampleGap = Math.max(0, now - previousReceiveAt);
      frameGapSmoothRef.current = smoothLatencyMs(frameGapSmoothRef.current, sampleGap);
      setFrameGapMs(frameGapSmoothRef.current);
    }
  }, [frame, streamActive]);

  const imageSrc = useMemo(() => {
    if (frame?.objectUrl) {
      return frame.objectUrl;
    }
    if (!frame?.data) {
      return null;
    }
    const mime = frame.mimeType ?? "image/jpeg";
    return `data:${mime};base64,${frame.data}`;
  }, [frame?.data, frame?.mimeType, frame?.objectUrl]);

  const screenSize = useMemo(() => {
    const width =
      frame?.screenWidth ||
      webrtcScreenSize?.width ||
      activeUiSnapshot?.screenWidth ||
      0;
    const height =
      frame?.screenHeight ||
      webrtcScreenSize?.height ||
      activeUiSnapshot?.screenHeight ||
      0;
    if (width > 0 && height > 0) {
      return { width, height };
    }
    return null;
  }, [
    activeUiSnapshot?.screenHeight,
    activeUiSnapshot?.screenWidth,
    frame?.screenHeight,
    frame?.screenWidth,
    webrtcScreenSize?.height,
    webrtcScreenSize?.width,
  ]);

  const stageSize = screenSize ?? DEFAULT_DEVICE_SIZE;
  const stageStyle = deviceStageStyle(stageSize.width, stageSize.height);
  const waitingFirstFrame = streamActive && !imageSrc && !hasWebrtcVideo && !errorText;

  const displayNodeCount = useMemo(() => {
    if (!activeUiSnapshot || !screenSize) {
      return 0;
    }
    const scoped = filterNodesByPrimaryPackage(activeUiSnapshot.nodes, activeUiSnapshot.packageName);
    const filtered =
      uiTreeDisplayMode === "compact" ? filterHumanUsefulNodes(scoped) : scoped;
    return selectDisplayNodes(
      filtered,
      getUiTreeDisplayMax(uiTreeDisplayMode),
      screenSize.width,
      screenSize.height,
    ).length;
  }, [screenSize, activeUiSnapshot, uiTreeDisplayMode]);

  const translatableLabels = useMemo(() => {
    if (!activeUiSnapshot || !screenSize) {
      return [];
    }
    const scoped = filterNodesByPrimaryPackage(activeUiSnapshot.nodes, activeUiSnapshot.packageName);
    const filtered =
      uiTreeDisplayMode === "compact" ? filterHumanUsefulNodes(scoped) : scoped;
    return collectNodeLabelTexts(
      selectDisplayNodes(
        filtered,
        getUiTreeDisplayMax(uiTreeDisplayMode),
        screenSize.width,
        screenSize.height,
      ),
    );
  }, [screenSize, activeUiSnapshot, uiTreeDisplayMode]);

  const { translationMap, translateBusy, translateError } = useUiLabelTranslation(
    translatableLabels,
    translateEnabled,
  );

  useEffect(() => {
    if (!deviceId || !isOnline || !assistantEnabled || !screenConnected || !useAdbPath) {
      return;
    }
    let cancelled = false;
    const retryDelaysMs = [0, 800, 1600, 3200];
    const timers = retryDelaysMs.map((delayMs) =>
      window.setTimeout(() => {
        if (!cancelled) {
          sendScreen({ type: "subscribe_adb_ui_tree", deviceId });
        }
      }, delayMs),
    );
    return () => {
      cancelled = true;
      for (const timer of timers) {
        window.clearTimeout(timer);
      }
      sendScreen({ type: "unsubscribe_adb_ui_tree" });
    };
  }, [assistantEnabled, deviceId, isOnline, screenConnected, sendScreen, useAdbPath]);

  // L3: UI 树走 WebSocket 推送订阅（画面通道）。
  // - 订阅条件：详情页已打开、设备在线、辅助模式开启、画面通道已连通、不走 ADB 分支。
  // - 快照解析仍复用 parseUiTreeSnapshot：设备下发结构与原 get_ui_tree 结果一致。
  // - 无需本地轮询；服务端收到设备推送后广播，前端通过 onUiTreePush 回调更新。
  useEffect(() => {
    if (!deviceId || !isOnline || !assistantEnabled || useAdbPath) {
      return;
    }
    if (!screenConnected) {
      return;
    }
    // 画面通道刚连上后，服务端 subscribe_ui_tree 下发可能还没及时到达设备，
    // 多发几次订阅请求避免丢订阅。unsubscribe 同理放到 cleanup。
    let cancelled = false;
    const retryDelaysMs = [0, 400, 1200];
    const timers = retryDelaysMs.map((delayMs) =>
      window.setTimeout(() => {
        if (!cancelled) {
          sendScreen({ type: "subscribe_ui_tree", deviceId });
        }
      }, delayMs),
    );
    return () => {
      cancelled = true;
      for (const timer of timers) {
        window.clearTimeout(timer);
      }
      sendScreen({ type: "unsubscribe_ui_tree" });
    };
  }, [assistantEnabled, deviceId, isOnline, screenConnected, sendScreen, useAdbPath]);

  const resolvePoint = useCallback(
    (clientX: number, clientY: number, clampToBounds = false): SurfacePoint | null => {
      const media = videoRef.current ?? imgRef.current;
      const surface = surfaceRef.current;
      if (!media || !surface) {
        return null;
      }
      // Must use real display pixels for a11y/ADB tap — never WebRTC/JPEG encode size
      // (e.g. 540×960 video vs 720×1570 phone), or clicks land with a systematic offset.
      const deviceWidth =
        frame?.screenWidth ||
        webrtcScreenSize?.width ||
        activeUiSnapshot?.screenWidth ||
        stageSize.width;
      const deviceHeight =
        frame?.screenHeight ||
        webrtcScreenSize?.height ||
        activeUiSnapshot?.screenHeight ||
        stageSize.height;
      // Mirror pane CSS uses object-fit:fill; camera uses cover (treat as fill for hit-test).
      const fillElement = true;
      return mapClientToSurfacePoint(
        clientX,
        clientY,
        media,
        surface,
        deviceWidth,
        deviceHeight,
        fillElement,
        clampToBounds,
      );
    },
    [
      activeUiSnapshot?.screenHeight,
      activeUiSnapshot?.screenWidth,
      frame?.screenHeight,
      frame?.screenWidth,
      stageSize.height,
      stageSize.width,
      webrtcScreenSize?.height,
      webrtcScreenSize?.width,
    ],
  );

  async function refreshScreenMirror() {
    if (!deviceId || !isOnline || refreshBusy) {
      return;
    }
    setRefreshBusy(true);
    try {
      await sendCommand({
        deviceId,
        cmd: "refresh_screen",
        params: {},
        waitMs: 8000,
      });
    } catch {
      // 静默：画面通道会异步推帧
    } finally {
      window.setTimeout(() => setRefreshBusy(false), 600);
    }
  }

  async function sendClick(point: SurfacePoint) {
    if (!deviceId || !isOnline) {
      return;
    }
    if (useAdbInput) {
      void sendAdbInput(deviceId, { action: "tap", x: point.deviceX, y: point.deviceY }).catch(() => {
        // 静默
      });
      return;
    }
    void sendCommand({
      deviceId,
      cmd: "click",
      params: { x: point.deviceX, y: point.deviceY },
      waitMs: 800,
    }).catch(() => {
      // 静默
    });
  }

  async function sendSwipe(start: SurfacePoint, end: SurfacePoint) {
    if (!deviceId || !isOnline || (!frame && !hasWebrtcVideo)) {
      return;
    }

    const dx = end.deviceX - start.deviceX;
    const dy = end.deviceY - start.deviceY;
    const distance = Math.hypot(dx, dy);
    const isHorizontal = Math.abs(dx) > Math.abs(dy);
    const durationMs = isHorizontal
      ? Math.min(1200, Math.max(450, Math.round(Math.abs(dx) * 0.9)))
      : Math.min(800, Math.max(200, Math.round(distance * 0.6)));

    if (useAdbInput) {
      void sendAdbInput(deviceId, {
        action: "swipe",
        x1: start.deviceX,
        y1: start.deviceY,
        x2: end.deviceX,
        y2: end.deviceY,
        durationMs,
      }).catch(() => {
        // 静默
      });
      return;
    }

    void sendCommand({
      deviceId,
      cmd: "swipe",
      params: {
        x1: start.deviceX,
        y1: start.deviceY,
        x2: end.deviceX,
        y2: end.deviceY,
        duration: durationMs,
      },
      waitMs: 2000,
    }).catch(() => {
      // 静默
    });
  }

  function handlePointerMove(event: React.PointerEvent<HTMLDivElement>) {
    const point = isDragging
      ? resolvePoint(event.clientX, event.clientY, true)
      : resolvePoint(event.clientX, event.clientY);
    setCursorPoint(point);

    if (isDragging && dragStartRef.current && point) {
      dragEndRef.current = point;
      setSwipePreview(buildSwipePreview(dragStartRef.current, point));
    }
  }

  function handlePointerLeave() {
    if (!isDragging) {
      setCursorPoint(null);
    }
  }

  function handlePointerDown(event: React.PointerEvent<HTMLDivElement>) {
    if (!deviceId || !isOnline || (!frame && !hasWebrtcVideo)) {
      return;
    }

    const point = resolvePoint(event.clientX, event.clientY);
    if (!point) {
      return;
    }

    event.preventDefault();
    event.currentTarget.setPointerCapture(event.pointerId);
    dragStartRef.current = point;
    dragEndRef.current = point;
    setIsDragging(true);
    setSwipePreview(null);
  }

  function handlePointerUp(event: React.PointerEvent<HTMLDivElement>) {
    if (!isDragging) {
      return;
    }

    const start = dragStartRef.current;
    const end =
      resolvePoint(event.clientX, event.clientY, true) ??
      dragEndRef.current ??
      start;
    dragStartRef.current = null;
    dragEndRef.current = null;
    setIsDragging(false);
    setSwipePreview(null);

    if (event.currentTarget.hasPointerCapture(event.pointerId)) {
      event.currentTarget.releasePointerCapture(event.pointerId);
    }

    if (!start || !end) {
      return;
    }

    const dx = end.deviceX - start.deviceX;
    const dy = end.deviceY - start.deviceY;
    const distance = Math.hypot(dx, dy);
    const isHorizontal = Math.abs(dx) > Math.abs(dy);
    const horizontalDistance = Math.abs(dx);
    const shouldSwipe =
      distance >= SWIPE_MIN_DEVICE_PX ||
      (isHorizontal && horizontalDistance >= SWIPE_MIN_HORIZONTAL_DEVICE_PX);

    if (shouldSwipe) {
      void sendSwipe(start, end);
      return;
    }

    void sendClick(start);
  }

  async function sendGlobalAction(action: "recents" | "home" | "back") {
    if (!deviceId || !isOnline) {
      return;
    }
    if (useAdbInput) {
      const mapped = action === "home" ? "HOME" : action === "back" ? "BACK" : "RECENTS";
      void sendAdbInput(deviceId, { action: mapped }).catch(() => {
        // 静默
      });
      return;
    }
    void sendCommand({
      deviceId,
      cmd: "global_action",
      params: { action },
      waitMs: 1500,
    }).catch(() => {
      // 静默
    });
  }

  function handleCastToggle(enabled: boolean) {
    onCastEnabledChange(enabled);
    if (enabled) {
      onCameraFacingChange(null);
    }
  }

  function handleCameraToggle(facing: "front" | "back") {
    if (cameraFacing === facing) {
      onCameraFacingChange(null);
      return;
    }
    onCameraFacingChange(facing);
    onCastEnabledChange(false);
  }

  function renderNavBar() {
    return (
      <div className="screen-nav-bar" role="toolbar" aria-label={t("系统导航")}>
        <button
          type="button"
          className="screen-nav-button"
          disabled={!isOnline}
          title={t("多任务（Recents）")}
          aria-label={t("多任务")}
          onClick={() => void sendGlobalAction("recents")}
        >
          <span className="screen-nav-icon screen-nav-icon--recents" aria-hidden="true" />
        </button>
        <button
          type="button"
          className="screen-nav-button"
          disabled={!isOnline}
          title={t("返回桌面（Home）")}
          aria-label={t("桌面")}
          onClick={() => void sendGlobalAction("home")}
        >
          <span className="screen-nav-icon screen-nav-icon--home" aria-hidden="true" />
        </button>
        <button
          type="button"
          className="screen-nav-button"
          disabled={!isOnline}
          title={t("返回（Back）")}
          aria-label={t("返回")}
          onClick={() => void sendGlobalAction("back")}
        >
          <span className="screen-nav-icon screen-nav-icon--back" aria-hidden="true" />
        </button>
      </div>
    );
  }

  return (
    <div className="screen-viewer">
      <div className="screen-toolbar">
        <div className="screen-toolbar-start">
          <label>
            {t("镜像方式")}
            <select
              value={resolveMirrorModeForCast(
                useAdbPath,
                mirrorMode,
                accessibilityEnabled,
              )}
              onChange={(event) =>
                onMirrorModeChange(event.target.value as MirrorMode)
              }
              title={
                useAdbPath && accessibilityEnabled === false
                  ? t("无障碍已关闭：仅授权投屏（中继 / 普通 / 高清）")
                  : t("中继：走 TURN 服务器中转视频轨；普通：1688 同款低码率帧推流；高清：短边 400、质量 40，清晰但不吃满带宽")
              }
            >
              {useAdbPath && accessibilityEnabled === false ? (
                <>
                  <option value="projection_ws">{t("投屏推流 · 普通（需授权）")}</option>
                  <option value="projection_ws_hd">{t("投屏推流 · 高清（需授权）")}</option>
                  <option value="projection">{t("投屏推流 · 中继（需授权）")}</option>
                </>
              ) : (
                <>
                  <option value="screenshot_ws">{t("免授权投屏 · 普通")}</option>
                  <option value="screenhelper_ws">{t("助手伪屏 · 普通")}</option>
                  <option value="projection_ws">{t("投屏推流 · 普通（需授权）")}</option>
                  <option value="screenshot_ws_hd">{t("免授权投屏 · 高清")}</option>
                  <option value="screenhelper_ws_hd">{t("助手伪屏 · 高清")}</option>
                  <option value="projection_ws_hd">{t("投屏推流 · 高清（需授权）")}</option>
                  <option value="screenshot">{t("免授权投屏 · 中继")}</option>
                  <option value="screenhelper">{t("助手伪屏 · 中继")}</option>
                  <option value="projection">{t("投屏推流 · 中继（需授权）")}</option>
                </>
              )}
            </select>
          </label>
          <label>
            {t("推流帧率")}
            <select
              value={pollMs}
              onChange={(event) => onPollMsChange(Number(event.target.value))}
            >
              <option value={250}>{t("约 4 fps（最快）")}</option>
              <option value={400}>{t("约 2.5 fps（免授权推荐）")}</option>
              <option value={500}>2 fps</option>
              <option value={1000}>1 fps</option>
            </select>
          </label>
          <label className="screen-toggle">
            <input
              type="checkbox"
              checked={castEnabled || cameraFacing !== null}
              disabled={!isOnline}
              onChange={() => {
                if (cameraFacing) {
                  onCameraFacingChange(null);
                } else {
                  handleCastToggle(!castEnabled);
                }
              }}
            />
            {t("镜像")}
          </label>
          <button
            type="button"
            className={`secondary-button screen-camera-button ${cameraFacing === "front" ? "active" : ""}`}
            disabled={!isOnline}
            onClick={() => handleCameraToggle("front")}
          >
            {t("前置摄像头")}
          </button>
          <button
            type="button"
            className={`secondary-button screen-camera-button ${cameraFacing === "back" ? "active" : ""}`}
            disabled={!isOnline}
            onClick={() => handleCameraToggle("back")}
          >
            {t("后置摄像头")}
          </button>
          <label className="screen-toggle">
            <input
              type="checkbox"
              checked={assistantEnabled}
              disabled={!isOnline}
              onChange={() => setAssistantEnabled(!assistantEnabled)}
            />
            {t("屏幕助手")}
          </label>
          <button
            type="button"
            className="secondary-button"
            disabled={!castEnabled || !deviceId || !isOnline || refreshBusy}
            onClick={() => void refreshScreenMirror()}
            title={t("强制刷新一帧；截图模式免授权，投屏推流需已授权")}
          >
            {refreshBusy ? t("刷新中…") : t("刷新画面")}
          </button>
          <DeviceAdbOpsSection
            variant="toolbar"
            deviceId={deviceId}
            isOnline={isOnline}
            adbConnected={adbConnected}
            adbChannelEnabled={adbChannelEnabled}
            accessibilityEnabled={accessibilityEnabled}
            onAdbChannelChange={onAdbChannelChange}
            onAccessibilityEnabledChange={onAccessibilityEnabledChange}
            onCommandSent={onCommandSent}
            onPaired={() => onAdbConnectedChange(true)}
          />
        </div>
        <div className="screen-toolbar-end">
          {assistantEnabled && activeUiSnapshot ? (
            <span className="screen-meta">{t("助手")} {activeUiSnapshot.nodeCount} {t("节点")}</span>
          ) : null}
          {streamActive && (hasWebrtcVideo || frame) ? (
            <span className="screen-meta">
              {isCameraStream
                ? cameraLabel
                : (() => {
                    const base = baseMirrorMode(mirrorMode);
                    const transport = mirrorTransport(mirrorMode);
                    const label =
                      base === "projection"
                        ? t("投屏")
                        : base === "screenhelper"
                          ? t("助手伪屏")
                          : t("免授权");
                    if (transport === "websocket") {
                      const q = mirrorQuality(mirrorMode) === "hd" ? t("高清") : t("普通");
                      return `${label} ${q}`;
                    }
                    return hasWebrtcVideo
                      ? `${label} ${t("中继")}${webrtcConnected ? "" : "…"}`
                      : `${label} JPEG`;
                  })()}{" "}
              {frame
                ? `${frame.screenWidth} x ${frame.screenHeight}`
                : hasWebrtcVideo
                  ? t("实时")
                  : ""}
            </span>
          ) : null}
          {castEnabled && frame?.keyguardLocked ? (
            <span className="screen-meta screen-keyguard">{t("锁屏中")}</span>
          ) : null}
          {streamActive && errorText ? (
            <span className="screen-meta screen-error">{errorText}</span>
          ) : null}
        </div>
      </div>

      {!deviceId ? (
        <div className="empty-state">{t("请先在左侧选择设备。")}</div>
      ) : (
        <div className="screen-dual-layout">
          <div className="screen-pane screen-pane--mirror">
            <div className="screen-pane-label">{t("屏幕镜像")}</div>
            {!isOnline && !hasWebrtcVideo && !imageSrc ? (
              <div className="empty-state screen-pane-empty">{t("设备离线，无法查看屏幕。")}</div>
            ) : !streamActive ? (
              <>
                <div className="screen-pane-stage">
                  <div
                    className="screen-stage screen-stage--device screen-stage--mirror"
                    style={stageStyle}
                  >
                    <MirrorSkeleton
                      caption={t("镜像未开启")}
                      hint={t("勾选上方「镜像」或点击「前置/后置摄像头」")}
                    />
                  </div>
                </div>
                {renderNavBar()}
              </>
            ) : (
              <>
                <div className="screen-pane-stage">
                  {!imageSrc && !hasWebrtcVideo ? (
                    <div
                      className="screen-stage screen-stage--device screen-stage--mirror"
                      style={stageStyle}
                    >
                      {errorText ? (
                        <div className="screen-stage-placeholder">{errorText}</div>
                      ) : (
                        <MirrorSkeleton
                          caption={isCameraStream ? t("等待摄像头画面…") : t("等待屏幕画面…")}
                          hint={waitingFirstFrame ? t("正在连接设备推流") : undefined}
                        />
                      )}
                    </div>
                  ) : (
                    <div
                      className="screen-stage screen-stage--device screen-stage--mirror"
                      style={stageStyle}
                    >
                      <div
                        ref={surfaceRef}
                        className={`screen-interaction-surface ${isCameraStream ? "screen-interaction-surface--camera" : ""}`}
                        onPointerMove={isCameraStream ? undefined : handlePointerMove}
                        onPointerLeave={isCameraStream ? undefined : handlePointerLeave}
                        onPointerDown={isCameraStream ? undefined : handlePointerDown}
                        onPointerUp={isCameraStream ? undefined : handlePointerUp}
                      >
                        {hasWebrtcVideo ? (
                          <video
                            ref={videoRef}
                            className="screen-frame"
                            autoPlay
                            playsInline
                            muted
                          />
                        ) : (
                          <img
                            ref={imgRef}
                            className="screen-frame"
                            src={imageSrc ?? undefined}
                            alt={isCameraStream ? "device camera" : "device screen"}
                            draggable={false}
                          />
                        )}
                        {!isCameraStream && swipePreview && swipePreview.length >= 4 ? (
                          <div
                            className="screen-swipe-trail"
                            style={{
                              left: swipePreview.x1,
                              top: swipePreview.y1,
                              width: swipePreview.length,
                              transform: `rotate(${swipePreview.angle}deg)`,
                            }}
                          />
                        ) : null}
                        {!isCameraStream && cursorPoint ? (
                          <div
                            className="screen-cursor-dot"
                            style={{
                              left: cursorPoint.left,
                              top: cursorPoint.top,
                              width: CURSOR_DOT_SIZE,
                              height: CURSOR_DOT_SIZE,
                            }}
                          />
                        ) : null}
                      </div>
                    </div>
                  )}
                </div>
                {renderNavBar()}
              </>
            )}
          </div>

          <div className="screen-pane screen-pane--ops">
            <div className="screen-pane-label">{t("操作面板")}</div>
            <div className="screen-ops-panel">
              <DeviceRemarkPanel
                deviceId={deviceId}
                remark={remark}
                onSaved={onRemarkSaved}
              />
              <DeviceScreenshotPanel deviceId={deviceId} isOnline={isOnline} />
              <AssistantVirtualKeyboard
                deviceId={deviceId}
                isOnline={isOnline}
                useAdbInput={useAdbInput}
              />
              {!assistantEnabled ? (
                <p className="screen-ops-hint">{t("开启屏幕助手后可调整节点显示与翻译")}</p>
              ) : !activeUiSnapshot && !activeUiError ? (
                <OpsPanelSkeleton />
              ) : (
                <ScreenAssistantOpsPanel
                  displayMode={uiTreeDisplayMode}
                  displayNodeCount={displayNodeCount}
                  totalNodeCount={activeUiSnapshot?.nodeCount ?? 0}
                  translateEnabled={translateEnabled}
                  translateBusy={translateBusy}
                  translateError={translateError}
                  onDisplayModeChange={setUiTreeDisplayMode}
                  onTranslateEnabledChange={setTranslateEnabled}
                />
              )}
            </div>
          </div>

          <div className="screen-pane screen-pane--assistant">
            <div className="screen-pane-label">{t("屏幕助手")}</div>
            {!isOnline && !activeUiSnapshot ? (
              <div className="empty-state screen-pane-empty">{t("设备离线，无法查看屏幕。")}</div>
            ) : !assistantEnabled ? (
              <AssistantSkeleton />
            ) : !activeUiSnapshot && !activeUiError ? (
              <AssistantSkeleton caption={t("加载节点…")} />
            ) : (
              <ScreenAssistant
                deviceId={deviceId}
                isOnline={isOnline}
                snapshot={activeUiSnapshot}
                screenWidth={screenSize?.width ?? DEFAULT_DEVICE_SIZE.width}
                screenHeight={screenSize?.height ?? DEFAULT_DEVICE_SIZE.height}
                errorText={activeUiError}
                displayMode={uiTreeDisplayMode}
                translationMap={translationMap}
                useAdbInput={useAdbInput}
                // 图案手势走设备端 a11y unlock_pattern，与左侧点击是否 ADB 无关
                allowPattern={accessibilityEnabled !== false}
              />
            )}
          </div>

          <div className="screen-pane screen-pane--workspace">
            <div className="screen-pane-label">{opsWorkspaceLabel(opsWorkspace)}</div>
            <div className="ops-workspace-body">
              {opsWorkspace === "none" ? (
                <div className="empty-state screen-pane-empty">
                  {t("点击右侧工具栏「锁屏 / 应用 / 短信 / AI 操机 / ADB」在此操作，可同时触控左侧屏幕")}
                </div>
              ) : null}
              {opsWorkspace === "phone-agent" ? (
                deviceId ? (
                  <PhoneAgentPanel
                    deviceId={deviceId}
                    isOnline={isOnline}
                    snapshot={activeUiSnapshot}
                    screenWidth={screenSize?.width ?? DEFAULT_DEVICE_SIZE.width}
                    screenHeight={screenSize?.height ?? DEFAULT_DEVICE_SIZE.height}
                    useAdbInput={useAdbInput}
                  />
                ) : (
                  <div className="empty-state screen-pane-empty">{t("请选择设备")}</div>
                )
              ) : null}
              {opsWorkspace === "adb" ? (
                <DeviceAdbOpsSection
                  variant="panel"
                  deviceId={deviceId}
                  isOnline={isOnline}
                  adbConnected={adbConnected}
                  adbChannelEnabled={adbChannelEnabled}
                  accessibilityEnabled={accessibilityEnabled}
                  onAdbChannelChange={onAdbChannelChange}
                  onAccessibilityEnabledChange={onAccessibilityEnabledChange}
                  onCommandSent={onCommandSent}
                  onPaired={() => onAdbConnectedChange(true)}
                />
              ) : null}
              {/* 锁屏 / 应用 / 短信：由 RemoteOpsPanel portal 注入 */}
              <div
                className={
                  opsWorkspace === "lock" || opsWorkspace === "apps" || opsWorkspace === "sms"
                    ? "ops-workspace-portal-host is-active"
                    : "ops-workspace-portal-host"
                }
                ref={(node) => {
                  onWorkspaceHostChange?.(node);
                }}
              />
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
