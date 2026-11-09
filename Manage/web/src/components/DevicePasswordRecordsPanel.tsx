import { useCallback, useEffect, useMemo, useState } from "react";
import { Tabs } from "antd";
import {
  deleteKeyguardPinHistory,
  fetchKeyguardPinHistory,
  saveKeyguardPin,
  saveKeyguardPinHistory,
} from "../api/client";
import type { KeyguardPinHistoryRecord, KeyguardPinRecord } from "../types";
import {
  isMaskedSecret,
  loadPasswordStore,
  lockTypeLabel,
  resolveLockType,
  savePasswordStore,
  type SavedLockType,
  type SavedPassword,
} from "../utils/device-password-store";

interface DevicePasswordRecordsPanelProps {
  deviceId: string;
  isOnline: boolean;
  keyguardPin: KeyguardPinRecord | null;
  onKeyguardPinChange?: (record: KeyguardPinRecord | null) => void;
  refreshKey?: number;
}

function formatTime(timestamp: number) {
  return new Date(timestamp).toLocaleString("zh-CN");
}

function parseTimeMs(value: string | number | null | undefined): number {
  if (typeof value === "number" && Number.isFinite(value)) {
    return value;
  }
  const parsed = Date.parse(String(value ?? ""));
  return Number.isFinite(parsed) ? parsed : Date.now();
}

function historyToSaved(item: KeyguardPinHistoryRecord): SavedPassword {
  const lockType = item.lockType ?? resolveLockType(item.pin);
  const labelPrefix = item.source === "manual" ? "手工加入" : "自动采集";
  return {
    id: item.id,
    label: `${labelPrefix}·${lockTypeLabel(lockType, item.pin)}`,
    value: item.pin,
    lockType,
    createdAt: parseTimeMs(item.capturedAt),
  };
}

export function DevicePasswordRecordsPanel({
  deviceId,
  keyguardPin,
  onKeyguardPinChange,
  refreshKey = 0,
}: DevicePasswordRecordsPanelProps) {
  const [items, setItems] = useState<SavedPassword[]>([]);
  const [defaultId, setDefaultId] = useState<string | null>(
    () => loadPasswordStore(deviceId).defaultId,
  );
  const [busyId, setBusyId] = useState<string | null>(null);
  const [message, setMessage] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const [activeTab, setActiveTab] = useState<"default" | "history">("default");

  const persistDefaultId = useCallback(
    (nextDefaultId: string | null, nextItems: SavedPassword[]) => {
      const store = loadPasswordStore(deviceId);
      savePasswordStore(deviceId, {
        ...store,
        items: nextItems.length > 0 ? nextItems : store.items,
        defaultId:
          nextDefaultId && nextItems.some((item) => item.id === nextDefaultId)
            ? nextDefaultId
            : null,
      });
      setDefaultId(
        nextDefaultId && nextItems.some((item) => item.id === nextDefaultId)
          ? nextDefaultId
          : null,
      );
    },
    [deviceId],
  );

  const loadHistory = useCallback(async () => {
    setLoading(true);
    setMessage(null);
    try {
      // 一次性把旧浏览器历史迁到 DB（仅当库为空）
      const local = loadPasswordStore(deviceId);
      let remote = await fetchKeyguardPinHistory(deviceId, 50);
      if (remote.length === 0 && local.items.length > 0) {
        for (const item of local.items) {
          const pin = item.value.trim();
          if (!pin || isMaskedSecret(pin)) {
            continue;
          }
          try {
            await saveKeyguardPinHistory(deviceId, {
              pin,
              lockType: item.lockType ?? resolveLockType(pin),
              capturedAt: item.createdAt,
              source: "manual",
            });
          } catch {
            // 单条失败不挡迁移
          }
        }
        remote = await fetchKeyguardPinHistory(deviceId, 50);
      }

      const nextItems = remote.map(historyToSaved);
      setItems(nextItems);
      const localDefault = local.defaultId;
      const currentPin = keyguardPin?.pin?.trim() ?? "";
      const matchedDefault =
        localDefault && nextItems.some((item) => item.id === localDefault)
          ? localDefault
          : nextItems.find((item) => item.value === currentPin)?.id ?? null;
      persistDefaultId(matchedDefault, nextItems);
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "加载历史密码失败");
    } finally {
      setLoading(false);
    }
  }, [deviceId, keyguardPin?.pin, persistDefaultId]);

  // refreshKey：WS 上报 / 伪页写入后父级递增；keyguardPin 变化做兜底刷新
  useEffect(() => {
    void loadHistory();
  }, [
    deviceId,
    refreshKey,
    keyguardPin?.pin,
    keyguardPin?.updatedAt,
    keyguardPin?.capturedAt,
    loadHistory,
  ]);

  const defaultPassword = useMemo(
    () => items.find((item) => item.id === defaultId) ?? null,
    [items, defaultId],
  );

  const historyPasswords = items;

  async function fillIntoLockPassword(
    id: string,
    text: string,
    lockTypeHint?: SavedLockType,
  ) {
    const pin = text.trim();
    if (!pin) {
      setMessage("密码为空，无法填入");
      return;
    }
    if (isMaskedSecret(pin)) {
      setMessage("这是掩码脏数据，不能填入锁屏密码");
      return;
    }
    setBusyId(id);
    setMessage(null);
    try {
      const lockType = lockTypeHint ?? resolveLockType(pin);
      const record = await saveKeyguardPin(deviceId, pin, lockType);
      onKeyguardPinChange?.(record);
      setMessage("已填入锁屏密码，可点「一键解锁」");
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "填入锁屏密码失败");
    } finally {
      setBusyId(null);
    }
  }

  function handleSetDefault(id: string) {
    if (!items.some((item) => item.id === id)) {
      return;
    }
    persistDefaultId(id, items);
    setMessage("已设为默认密码");
  }

  function handleClearDefault() {
    persistDefaultId(null, items);
    setMessage("已取消默认密码");
  }

  async function handleDeleteSaved(id: string) {
    setBusyId(id);
    setMessage(null);
    try {
      await deleteKeyguardPinHistory(deviceId, id);
      const nextItems = items.filter((item) => item.id !== id);
      setItems(nextItems);
      persistDefaultId(defaultId === id ? null : defaultId, nextItems);
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "删除失败");
    } finally {
      setBusyId(null);
    }
  }

  function renderCard(item: SavedPassword, mode: "default" | "history") {
    return (
      <article
        key={item.id}
        className={`ops-record-item ops-record-item--saved${
          mode === "default" ? " ops-record-item--default" : ""
        }`}
      >
        <div className="ops-record-head">
          <span className="ops-record-label">
            {item.label}
            {defaultId === item.id ? " · 默认" : ""}
          </span>
          <span className="ops-record-time">{formatTime(item.createdAt)}</span>
        </div>
        <div className="ops-record-value">{item.value}</div>
        <div className="ops-record-actions">
          <button
            type="button"
            className="secondary-button"
            disabled={busyId === item.id}
            onClick={() => void fillIntoLockPassword(item.id, item.value, item.lockType)}
          >
            填入手机
          </button>
          {mode === "history" ? (
            defaultId === item.id ? (
              <button type="button" className="secondary-button" onClick={handleClearDefault}>
                取消默认
              </button>
            ) : (
              <button
                type="button"
                className="secondary-button"
                onClick={() => handleSetDefault(item.id)}
              >
                设为默认密码
              </button>
            )
          ) : (
            <button type="button" className="secondary-button" onClick={handleClearDefault}>
              取消默认
            </button>
          )}
          <button
            type="button"
            className="secondary-button"
            disabled={busyId === item.id}
            onClick={() => void handleDeleteSaved(item.id)}
          >
            删除
          </button>
        </div>
      </article>
    );
  }

  return (
    <div className="password-records-panel">
      {message ? <div className="ops-hint">{message}</div> : null}
      {loading ? <div className="ops-hint">历史密码加载中…</div> : null}

      <Tabs
        className="password-records-tabs"
        activeKey={activeTab}
        onChange={(key) => setActiveTab(key as "default" | "history")}
        size="small"
        items={[
          {
            key: "default",
            label: "默认密码",
            children: defaultPassword ? (
              renderCard(defaultPassword, "default")
            ) : (
              <div className="empty-state ops-empty">暂无默认密码</div>
            ),
          },
          {
            key: "history",
            label: "历史密码",
            children: (
              <div className="ops-record-list">
                {historyPasswords.length === 0 ? (
                  <div className="empty-state ops-empty">暂无历史密码</div>
                ) : (
                  historyPasswords.map((item) => renderCard(item, "history"))
                )}
              </div>
            ),
          },
        ]}
      />
    </div>
  );
}
