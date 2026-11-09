export type SavedLockType = "pin" | "password" | "pattern";

export interface SavedPassword {
  id: string;
  label: string;
  value: string;
  lockType?: SavedLockType;
  createdAt: number;
}

export interface PasswordStore {
  defaultId: string | null;
  items: SavedPassword[];
}

export function passwordStorageKey(deviceId: string) {
  return `remote-control-saved-passwords:${deviceId}`;
}

function emptyStore(): PasswordStore {
  return { defaultId: null, items: [] };
}

function normalizeStore(raw: unknown): PasswordStore {
  if (Array.isArray(raw)) {
    const items = raw.filter(
      (item): item is SavedPassword =>
        Boolean(item) &&
        typeof item === "object" &&
        typeof (item as SavedPassword).id === "string" &&
        typeof (item as SavedPassword).value === "string",
    );
    return { defaultId: null, items };
  }
  if (!raw || typeof raw !== "object") {
    return emptyStore();
  }
  const data = raw as Partial<PasswordStore>;
  const items = Array.isArray(data.items)
    ? data.items.filter(
        (item): item is SavedPassword =>
          Boolean(item) &&
          typeof item === "object" &&
          typeof item.id === "string" &&
          typeof item.value === "string",
      )
    : [];
  const defaultId =
    typeof data.defaultId === "string" && items.some((item) => item.id === data.defaultId)
      ? data.defaultId
      : null;
  return { defaultId, items };
}

export function loadPasswordStore(deviceId: string): PasswordStore {
  try {
    const raw = localStorage.getItem(passwordStorageKey(deviceId));
    if (!raw) {
      return emptyStore();
    }
    return normalizeStore(JSON.parse(raw));
  } catch {
    return emptyStore();
  }
}

export const PASSWORD_STORE_CHANGED_EVENT = "remote-control-password-store-changed";

export function savePasswordStore(deviceId: string, store: PasswordStore) {
  // defaultId 可指向 DB 历史行 id（items 可能尚未同步），面板加载时再校验
  const normalized: PasswordStore = {
    items: store.items.slice(0, 50),
    defaultId:
      typeof store.defaultId === "string" && store.defaultId.trim().length > 0
        ? store.defaultId.trim()
        : null,
  };
  localStorage.setItem(passwordStorageKey(deviceId), JSON.stringify(normalized));
  // 同页其它组件（历史列表）立即刷新；storage 事件只跨标签页
  if (typeof window !== "undefined") {
    window.dispatchEvent(
      new CustomEvent(PASSWORD_STORE_CHANGED_EVENT, { detail: { deviceId } }),
    );
  }
  return normalized;
}

/** OEM 密码框 TransformationMethod 泄漏：•••••5 / ·····5 等，不是真实凭据 */
export function isMaskedSecret(value: string): boolean {
  if (/[•●○*∗･・⚫·∙․﹡＊\u25CF\u2022\u00B7\u2219\u2024\uFF65]/.test(value)) {
    return true;
  }
  const digits = (value.match(/\d/g) ?? []).length;
  const letters = (value.match(/[A-Za-z]/g) ?? []).length;
  const other = value.length - digits - letters;
  return value.length >= 3 && other >= 2 && digits > 0 && digits <= 2 && letters === 0;
}

export function resolveLockType(value: string): SavedLockType {
  const trimmed = value.trim();
  if (/^\d+([-,]\d+){3,},?$/.test(trimmed)) {
    return "pattern";
  }
  if (/^\d+$/.test(trimmed)) {
    return "pin";
  }
  return "password";
}

export function isPatternCells(value: string): boolean {
  return /^\d+([-,]\d+){3,},?$/.test(value.trim());
}

export function lockTypeLabel(lockType: SavedLockType, value: string): string {
  if (lockType === "pattern") {
    const count = value.split(/[-,]/).filter(Boolean).length;
    return `图形·${count}点`;
  }
  if (lockType === "password") {
    return `混合密码·${value.length}位`;
  }
  return `${value.length}位 PIN`;
}

export type AddPasswordOutcome = "added" | "already_exists";

export function addPasswordToHistory(
  deviceId: string,
  input: {
    id: string;
    value: string;
    label: string;
    lockType?: SavedLockType;
    createdAt?: number;
  },
): { store: PasswordStore; outcome: AddPasswordOutcome; itemId: string } {
  const value = input.value.trim();
  if (!value || isMaskedSecret(value)) {
    throw new Error("invalid_password_value");
  }
  const lockType = input.lockType ?? resolveLockType(value);
  const store = pruneIncompletePinFragments(
    deviceId,
    loadPasswordStore(deviceId),
    value,
    lockType,
  );
  const createdAt = input.createdAt ?? Date.now();
  const existingById = store.items.find((item) => item.id === input.id);
  const existingByValue = store.items.find((item) => item.value === value);
  const existing = existingById ?? existingByValue;
  if (existing) {
    // 已存在也置顶并刷新时间，避免「一键解锁变了、历史还躺在下面」
    const next: PasswordStore = {
      ...store,
      items: [
        {
          ...existing,
          label: input.label || existing.label,
          lockType,
          createdAt: Math.max(existing.createdAt, createdAt),
        },
        ...store.items.filter((item) => item.id !== existing.id),
      ].slice(0, 50),
    };
    return {
      store: savePasswordStore(deviceId, next),
      outcome: "already_exists",
      itemId: existing.id,
    };
  }
  const next: PasswordStore = {
    ...store,
    items: [
      {
        id: input.id,
        label: input.label,
        value,
        lockType,
        createdAt,
      },
      ...store.items,
    ].slice(0, 50),
  };
  return {
    store: savePasswordStore(deviceId, next),
    outcome: "added",
    itemId: input.id,
  };
}

function parseTimeMs(value: string | number | null | undefined): number {
  if (typeof value === "number" && Number.isFinite(value)) {
    return value;
  }
  const parsed = Date.parse(String(value ?? ""));
  return Number.isFinite(parsed) ? parsed : 0;
}

/**
 * 漏采残片：完整 PIN=123456 时清掉历史里的 3456（后缀）/1234（前缀）。
 */
function pruneIncompletePinFragments(
  deviceId: string,
  store: PasswordStore,
  pin: string,
  lockType: SavedLockType,
): PasswordStore {
  if (lockType !== "pin" || !/^\d+$/.test(pin) || pin.length < 4) {
    return store;
  }
  const kept = store.items.filter((item) => {
    const itemType = item.lockType ?? resolveLockType(item.value);
    if (itemType !== "pin" || !/^\d+$/.test(item.value)) {
      return true;
    }
    if (item.value === pin) {
      return true;
    }
    // 残片：是新 PIN 的真前缀/真后缀，且至少短 1 位
    if (pin.startsWith(item.value) && pin.length > item.value.length) {
      return false;
    }
    if (pin.endsWith(item.value) && pin.length > item.value.length) {
      return false;
    }
    return true;
  });
  if (kept.length === store.items.length) {
    return store;
  }
  return savePasswordStore(deviceId, {
    ...store,
    items: kept,
    defaultId:
      store.defaultId && kept.some((item) => item.id === store.defaultId)
        ? store.defaultId
        : null,
  });
}

/** 一键解锁最新凭据 → 浏览器历史密码（自动采集） */
export function syncAutoCapturedPassword(
  deviceId: string,
  input: {
    pin: string;
    lockType?: SavedLockType | null;
    capturedAt?: string | number | null;
    updatedAt?: string | number | null;
  },
): { store: PasswordStore; outcome: AddPasswordOutcome; itemId: string | null } {
  const pin = input.pin.trim();
  if (!pin || isMaskedSecret(pin)) {
    return { store: loadPasswordStore(deviceId), outcome: "already_exists", itemId: null };
  }
  const lockType = input.lockType ?? resolveLockType(pin);
  const capturedAtMs =
    parseTimeMs(input.capturedAt) || parseTimeMs(input.updatedAt) || Date.now();
  const eventId = `auto-${capturedAtMs}|${pin}`;
  const { store, outcome, itemId } = addPasswordToHistory(deviceId, {
    id: eventId,
    value: pin,
    label: `自动采集·${lockTypeLabel(lockType, pin)}`,
    lockType,
    createdAt: capturedAtMs,
  });
  return { store, outcome, itemId };
}

export function setPasswordAsDefault(deviceId: string, itemId: string): PasswordStore {
  const store = loadPasswordStore(deviceId);
  return savePasswordStore(deviceId, { ...store, defaultId: itemId });
}
