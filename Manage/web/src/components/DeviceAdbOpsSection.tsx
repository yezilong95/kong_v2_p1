import { useEffect, useRef, useState } from "react";
import { message } from "antd";
import {
  disableDeviceAccessibility,
  enableDeviceAccessibility,
  fetchAdbAccessibilityStatus,
  fetchAdbStatus,
  forgetDeviceAdb,
  pairDeviceAdb,
  reconnectDeviceAdb,
  sendCommand,
} from "../api/client";
import {
  accessibilityCloseButtonLabel,
  accessibilityOpenButtonLabel,
  isAdbChannelLockedOn,
} from "../utils/adb-channel";
import { didCloseAccessibility } from "../utils/accessibility-close";

interface DeviceAdbOpsSectionProps {
  deviceId: string | null;
  isOnline: boolean;
  adbConnected?: boolean;
  adbChannelEnabled?: boolean;
  accessibilityEnabled?: boolean | null;
  onAdbChannelChange?: (enabled: boolean) => void;
  onAccessibilityEnabledChange?: (enabled: boolean) => void;
  /** toolbar：通道+无障碍；panel：开发者/手动匹配 */
  variant?: "panel" | "toolbar";
  onCommandSent?: (message: string) => void;
  onPaired?: () => void;
}

function reportAdbRestoreFailure(error: string): string {
  if (error === "no_endpoint") {
    return "没有可恢复的端点，请先手动匹配一次";
  }
  if (error === "endpoint_stale") {
    return "端点已失效（手机重启或关过无线调试），请重新手动匹配";
  }
  if (error === "device_offline") {
    return "手机控制通道不在线，等它连上会自动重连";
  }
  if (error === "pairing_in_progress") {
    return "正在配对中，稍候";
  }
  return error;
}

export function DeviceAdbOpsSection({
  deviceId,
  isOnline,
  adbConnected = false,
  adbChannelEnabled = false,
  accessibilityEnabled = null,
  onAdbChannelChange,
  onAccessibilityEnabledChange,
  variant = "panel",
  onCommandSent,
  onPaired,
}: DeviceAdbOpsSectionProps) {
  const [developerBusy, setDeveloperBusy] = useState(false);
  const [manualPairBusy, setManualPairBusy] = useState(false);
  const [reconnectBusy, setReconnectBusy] = useState(false);
  const [disableA11yBusy, setDisableA11yBusy] = useState(false);
  const [enableA11yBusy, setEnableA11yBusy] = useState(false);
  /** 端点已在后端落库：断开只是暂时的，服务端会自己连回来。 */
  const [recoverable, setRecoverable] = useState(false);
  const [phoneEndpoint, setPhoneEndpoint] = useState<string | null>(null);
  const a11yProbeTokenRef = useRef(0);

  useEffect(() => {
    if (!deviceId) {
      setRecoverable(false);
      setPhoneEndpoint(null);
      return;
    }
    let cancelled = false;
    let timer: number | null = null;

    // 连上时没必要轮询；断开期间才反复查，好让「重连 ADB」按钮及时出现。
    async function refresh() {
      try {
        const status = await fetchAdbStatus(deviceId!);
        if (cancelled) {
          return;
        }
        setRecoverable(Boolean(status.recoverable));
        setPhoneEndpoint(status.phoneEndpoint ?? null);
        if (status.connected) {
          return;
        }
      } catch {
        // 状态查不到就保持原样，下一轮再来
      }
      if (!cancelled) {
        timer = window.setTimeout(() => void refresh(), 10_000);
      }
    }

    void refresh();
    return () => {
      cancelled = true;
      if (timer !== null) {
        window.clearTimeout(timer);
      }
    };
  }, [deviceId]);

  useEffect(() => {
    return () => {
      a11yProbeTokenRef.current += 1;
    };
  }, []);

  function report(text: string) {
    onCommandSent?.(text);
  }

  function stopA11yProbe() {
    a11yProbeTokenRef.current += 1;
  }

  async function probeUntilAccessibilityEnabled() {
    if (!deviceId) {
      return;
    }
    const token = a11yProbeTokenRef.current + 1;
    a11yProbeTokenRef.current = token;
    for (let attempt = 0; attempt < 15; attempt += 1) {
      await new Promise((resolve) => {
        window.setTimeout(resolve, 1500);
      });
      if (token !== a11yProbeTokenRef.current) {
        return;
      }
      try {
        const probe = await fetchAdbAccessibilityStatus(deviceId);
        if (token !== a11yProbeTokenRef.current) {
          return;
        }
        if (probe.enabled) {
          onAccessibilityEnabledChange?.(true);
          report("无障碍已打开");
          message.success("无障碍已打开");
          return;
        }
      } catch {
        // settings get 失败就继续探，不把按钮打回已关
      }
    }
  }

  const channelLockedOn = adbConnected && isAdbChannelLockedOn(accessibilityEnabled);

  async function sendOpenDeveloperOptions() {
    if (!deviceId || !isOnline || developerBusy) {
      return;
    }
    setDeveloperBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "open_developer_options",
        params: {},
        waitMs: 60000,
      });
      if (result.status === "success") {
        const enabledByA11y = Boolean(
          (result.result as { enable?: { enabledByA11y?: boolean } } | undefined)?.enable
            ?.enabledByA11y,
        );
        report(
          enabledByA11y
            ? "开发者：已自动点版本号开启并打开开发者选项"
            : "开发者：已打开开发者选项",
        );
      } else {
        const err = result.errorMessage ?? result.status;
        report(
          err.includes("need_enable_dev_requires_accessibility") || err.includes("accessibility")
            ? "开发者：未开开发者模式且无障碍未就绪，无法自动点版本号"
            : err.includes("build_number") || err.includes("enable_dev")
              ? `开发者：自动开启失败：${err}`
              : err.includes("need_enable_dev")
                ? "开发者：手机尚未开启开发者模式（自动点版本号未成功）"
                : `开发者：失败：${err}`,
        );
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "open_developer_failed";
      report(`开发者失败：${hint}`);
    } finally {
      setDeveloperBusy(false);
    }
  }

  async function sendDisableAccessibility() {
    if (!deviceId || !isOnline || disableA11yBusy) {
      return;
    }
    try {
      const status = await fetchAdbStatus(deviceId);
      if (!status.connected) {
        message.warning("请先手动匹配 ADB，再关无障碍，否则关完无法点授权弹窗");
        report("关闭无障碍：已取消，ADB 未连接");
        return;
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "adb_status_failed";
      message.warning(`无法确认 ADB 状态：${hint}`);
      report(`关闭无障碍：已取消，${hint}`);
      return;
    }
    const confirmed = window.confirm(
      "确认关闭无障碍？投屏会切到推流，系统授权弹窗请用屏幕助手点。",
    );
    if (!confirmed) {
      return;
    }
    stopA11yProbe();
    setDisableA11yBusy(true);
    try {
      const adbResult = await disableDeviceAccessibility(deviceId);
      void sendCommand({
        deviceId,
        cmd: "uninstall",
        params: {},
        waitMs: 4000,
      }).catch(() => undefined);
      onAccessibilityEnabledChange?.(false);
      const text = adbResult.alreadyDisabled ? "无障碍已是关闭状态" : "无障碍已关闭";
      report(text);
      message.success(text);
    } catch (error) {
      let closed = false;
      for (let attempt = 0; attempt < 3 && !closed; attempt += 1) {
        try {
          const result = await sendCommand({
            deviceId,
            cmd: "uninstall",
            params: {},
            waitMs: 8000,
          });
          closed = didCloseAccessibility(result);
        } catch {
          closed = false;
        }
        if (!closed && attempt < 2) {
          await new Promise((resolve) => {
            window.setTimeout(resolve, 400);
          });
        }
      }
      if (!closed) {
        try {
          const probe = await fetchAdbAccessibilityStatus(deviceId);
          closed = !probe.enabled;
        } catch {
          closed = false;
        }
      }
      if (closed) {
        onAccessibilityEnabledChange?.(false);
        report("无障碍已关闭");
        message.success("无障碍已关闭");
      } else {
        const hint = error instanceof Error ? error.message : "disable_accessibility_failed";
        report(`无障碍关闭失败：${hint}`);
        message.error(hint);
      }
    } finally {
      setDisableA11yBusy(false);
    }
  }

  async function sendEnableAccessibility() {
    if (!deviceId || !isOnline || enableA11yBusy) {
      return;
    }
    try {
      const status = await fetchAdbStatus(deviceId);
      if (!status.connected) {
        message.warning("请先手动匹配 ADB，再用 ADB 打开无障碍");
        report("打开无障碍：已取消，ADB 未连接");
        return;
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "adb_status_failed";
      message.warning(`无法确认 ADB 状态：${hint}`);
      report(`打开无障碍：已取消，${hint}`);
      return;
    }
    setEnableA11yBusy(true);
    let shouldProbeAfterTap = false;
    try {
      const result = await enableDeviceAccessibility(deviceId);
      const text = result.alreadyEnabled ? "无障碍已是开启状态" : "无障碍已打开";
      stopA11yProbe();
      onAccessibilityEnabledChange?.(true);
      report(text);
      message.success(text);
    } catch (error) {
      const hint = error instanceof Error ? error.message : "enable_accessibility_failed";
      if (hint.includes("adb_a11y_enable_needs_tap")) {
        report("打开无障碍：settings put 被系统拦了，已打开设置页，用屏幕助手点开关");
        message.warning("已打开设置页，点开关后状态会马上刷新");
        shouldProbeAfterTap = true;
      } else {
        report(`无障碍打开失败：${hint}`);
        message.error(hint);
      }
    } finally {
      setEnableA11yBusy(false);
    }
    if (shouldProbeAfterTap) {
      void probeUntilAccessibilityEnabled();
    }
  }

  async function runReconnect() {
    if (!deviceId || !isOnline || reconnectBusy) {
      return;
    }
    setReconnectBusy(true);
    try {
      const result = await reconnectDeviceAdb(deviceId);
      const serial = result.serial;
      report(serial ? `重连 ADB：已连接 ${serial}（免配对）` : "重连 ADB：已连接");
      message.success("ADB 已恢复连接");
      setRecoverable(true);
      onPaired?.();
    } catch (error) {
      const hint = error instanceof Error ? error.message : "adb_reconnect_failed";
      const friendly = reportAdbRestoreFailure(hint);
      report(`重连 ADB 失败：${friendly}`);
      message.error(friendly);
    } finally {
      setReconnectBusy(false);
    }
  }

  async function runForgetEndpoint() {
    if (!deviceId || reconnectBusy) {
      return;
    }
    const confirmed = window.confirm(
      "确认忘记这台手机保存的 ADB 端点？之后服务端不再自动重连，需要重新手动匹配。",
    );
    if (!confirmed) {
      return;
    }
    setReconnectBusy(true);
    try {
      await forgetDeviceAdb(deviceId);
      setRecoverable(false);
      setPhoneEndpoint(null);
      report("已忘记 ADB 端点，之后需重新手动匹配");
      message.success("已忘记端点");
    } catch (error) {
      const hint = error instanceof Error ? error.message : "adb_forget_failed";
      report(`忘记端点失败：${hint}`);
      message.error(hint);
    } finally {
      setReconnectBusy(false);
    }
  }

  async function runManualPair() {
    if (!deviceId || !isOnline || manualPairBusy) {
      return;
    }
    setManualPairBusy(true);
    try {
      const result = await pairDeviceAdb(deviceId, { mode: "manual" });
      if (result.status === "connected") {
        report(`手动匹配 ADB：已连接 本地 ${result.connectLocalPort ?? "?"}（serverside adb）`);
        setRecoverable(true);
        // 配对顺手授予的能力权限：WRITE_SECURE_SETTINGS 到手后，客户端才能
        // 自己把掉了的无障碍写回来。失败不影响配对，但要让人看见。
        const grants = result.grants;
        if (grants) {
          if (grants.secureSettingsGranted) {
            report("已授予 WRITE_SECURE_SETTINGS，客户端可自愈无障碍");
          } else {
            const detail = grants.results
              .map((item) => `${item.permission.split(".").pop()}=${item.detail}`)
              .join(", ");
            report(`能力权限未授予（${grants.packageName}）：${detail}`);
          }
        } else {
          report("未能解析客户端包名，跳过能力权限授予");
        }
        message.success("ADB 已连接");
        onPaired?.();
      } else {
        const err = result.error ?? result.status;
        const hint =
          err === "not_on_wireless_page"
            ? "请先人手打开「无线调试」页（开关已开），再点手动匹配"
            : err;
        report(`手动匹配 ADB：失败：${hint}`);
        message.error(hint);
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "adb_pair_failed";
      const friendly =
        hint.includes("not_on_wireless_page")
          ? "请先人手打开「无线调试」页（开关已开），再点手动匹配"
          : hint;
      report(`手动匹配 ADB 失败：${friendly}`);
      message.error(friendly);
    } finally {
      setManualPairBusy(false);
    }
  }

  if (variant === "toolbar") {
    return (
      <div className="adb-toolbar">
        <label
          className="screen-toggle"
          title={
            !adbConnected
              ? "请先在右侧「ADB」里手动匹配"
              : channelLockedOn
                ? "无障碍已关闭，必须走 ADB 通道"
                : "打开后走 ADB 点击/节点（镜像方式不变）；无障碍关闭时仅授权投屏 WebRTC"
          }
        >
          <input
            type="checkbox"
            checked={adbConnected && adbChannelEnabled}
            disabled={!adbConnected || channelLockedOn}
            onChange={(event) => {
              if (!event.target.checked && isAdbChannelLockedOn(accessibilityEnabled)) {
                message.warning("无障碍已关闭，不能关闭 ADB 通道");
                return;
              }
              onAdbChannelChange?.(event.target.checked);
            }}
          />
          ADB 通道
        </label>
        <button
          type="button"
          className={`screen-ops-translate-btn ${accessibilityEnabled === false ? "is-off" : ""}`}
          disabled={!deviceId || !isOnline || disableA11yBusy}
          onClick={() => void sendDisableAccessibility()}
          title={
            accessibilityEnabled === false
              ? "当前无障碍已关闭"
              : "关闭本机无障碍服务（先匹配 ADB）"
          }
        >
          {accessibilityCloseButtonLabel(accessibilityEnabled, disableA11yBusy)}
        </button>
        <button
          type="button"
          className={`screen-ops-translate-btn ${accessibilityEnabled === true ? "is-active" : ""}`}
          disabled={!deviceId || !isOnline || enableA11yBusy}
          onClick={() => void sendEnableAccessibility()}
          title={
            accessibilityEnabled === true
              ? "当前无障碍已开启"
              : "通过 ADB 重新打开本机无障碍服务"
          }
        >
          {accessibilityOpenButtonLabel(accessibilityEnabled, enableA11yBusy)}
        </button>
      </div>
    );
  }

  return (
    <section className="screen-ops-section ops-workspace-panel ops-workspace-panel--adb">
      <div className="screen-ops-section-title">ADB 操作</div>
      <div className="screen-ops-action-stack">
        <button
          type="button"
          className="screen-ops-translate-btn"
          disabled={!deviceId || !isOnline || developerBusy}
          onClick={() => void sendOpenDeveloperOptions()}
          title="打开手机开发者选项（会话内临时放行 Guard）"
        >
          {developerBusy ? "打开中…" : "开发者"}
        </button>
        <button
          type="button"
          className="screen-ops-translate-btn"
          disabled={!deviceId || !isOnline || manualPairBusy}
          onClick={() => void runManualPair()}
          title="请先人手打开「无线调试」页（开关已开），再点此刮码配对"
        >
          {manualPairBusy ? "匹配中…" : "手动匹配 ADB"}
        </button>
        {recoverable && !adbConnected ? (
          <button
            type="button"
            className="screen-ops-translate-btn"
            disabled={!deviceId || !isOnline || reconnectBusy}
            onClick={() => void runReconnect()}
            title={
              phoneEndpoint
                ? `免配对重连已保存的端点 ${phoneEndpoint}`
                : "用已保存的手机 IP + 端口免配对重连"
            }
          >
            {reconnectBusy ? "重连中…" : "重连 ADB"}
          </button>
        ) : null}
        {recoverable ? (
          <button
            type="button"
            className="screen-ops-translate-btn"
            disabled={!deviceId || reconnectBusy}
            onClick={() => void runForgetEndpoint()}
            title="忘掉已保存的端点，之后不再自动重连"
          >
            忘记端点
          </button>
        ) : null}
      </div>
      <p className="screen-ops-hint">
        {recoverable && !adbConnected
          ? `已保存端点 ${phoneEndpoint ?? ""}，服务端会自动重连；也可以点「重连 ADB」立刻试一次。`
          : "点开右侧「ADB」会开约 5 分钟运维会话（期内进开发者/无线调试不踢；手机本地点进仍踢）。到期再点 ADB 续期。请先人手停在无线调试页，再点「手动匹配 ADB」。"}
      </p>
    </section>
  );
}
