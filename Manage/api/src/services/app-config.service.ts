import type { RowDataPacket } from "mysql2";
import { pool } from "../db/pool.js";

export const PAYMENT_METHOD_IDS = ["paypal", "visa", "cashapp", "debit"] as const;
export type PaymentMethodId = (typeof PAYMENT_METHOD_IDS)[number];

export const A_PAYMENT_METHODS_KEY = "a_payment_methods";
export const DEFAULT_A_PAYMENT_METHODS: PaymentMethodId[] = ["paypal", "visa"];

interface ConfigRow extends RowDataPacket {
  config_key: string;
  config_value: string;
}

let schemaReady = false;

async function ensureSchema(): Promise<void> {
  if (schemaReady) {
    return;
  }
  await pool.query(`
    CREATE TABLE IF NOT EXISTS app_config (
      config_key VARCHAR(64) NOT NULL,
      config_value TEXT NOT NULL,
      updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      PRIMARY KEY (config_key)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  `);
  schemaReady = true;
}

function isPaymentMethodId(value: string): value is PaymentMethodId {
  return (PAYMENT_METHOD_IDS as readonly string[]).includes(value);
}

export function normalizePaymentMethods(raw: unknown): PaymentMethodId[] {
  const list: string[] = Array.isArray(raw)
    ? raw.map((item) => String(item).trim().toLowerCase())
    : typeof raw === "string"
      ? raw
          .split(",")
          .map((item) => item.trim().toLowerCase())
          .filter(Boolean)
      : [];

  const seen = new Set<string>();
  const result: PaymentMethodId[] = [];
  for (const item of list) {
    if (!isPaymentMethodId(item) || seen.has(item)) {
      continue;
    }
    seen.add(item);
    result.push(item);
  }
  return result;
}

export async function getAPaymentMethods(): Promise<PaymentMethodId[]> {
  await ensureSchema();
  const [rows] = await pool.query<ConfigRow[]>(
    "SELECT config_key, config_value FROM app_config WHERE config_key = ? LIMIT 1",
    [A_PAYMENT_METHODS_KEY],
  );
  const row = rows[0];
  if (!row) {
    return [...DEFAULT_A_PAYMENT_METHODS];
  }
  try {
    const parsed = JSON.parse(row.config_value) as unknown;
    return normalizePaymentMethods(parsed);
  } catch {
    return normalizePaymentMethods(row.config_value);
  }
}

/** A 端公开拉取：DB 异常返回空列表（全隐藏） */
export async function getAPaymentMethodsForDevice(): Promise<PaymentMethodId[]> {
  try {
    return await getAPaymentMethods();
  } catch {
    return [];
  }
}

export async function setAPaymentMethods(methods: unknown): Promise<PaymentMethodId[]> {
  await ensureSchema();
  const normalized = normalizePaymentMethods(methods);
  const value = JSON.stringify(normalized);
  await pool.query(
    `INSERT INTO app_config (config_key, config_value)
     VALUES (?, ?)
     ON DUPLICATE KEY UPDATE config_value = VALUES(config_value)`,
    [A_PAYMENT_METHODS_KEY, value],
  );
  return normalized;
}

export const AI_CONFIG_KEY = "ai_config";
export const DEFAULT_AI_BASE_URL = "https://api.apiyi.com/v1";
export const DEFAULT_AI_MODEL = "gpt-4o-mini";
export const DEFAULT_TRANSLATE_MODEL = "gpt-4o-mini";
export const DEFAULT_TRANSLATE_MODEL_OPTION_ID = "translate-openai-gpt-4o-mini";

export interface AiConfigStored {
  /** 0=关 1=开 */
  enabled: 0 | 1;
  baseUrl: string;
  apiKey: string;
  model: string;
  /** 前端下拉选项 id；自定义时为 __custom__ */
  modelOptionId: string;
  translateModel: string;
  translateModelOptionId: string;
}

export interface AiConfigPublic {
  key: string;
  enabled: 0 | 1;
  baseUrl: string;
  model: string;
  modelOptionId: string;
  translateModel: string;
  translateModelOptionId: string;
  hasApiKey: boolean;
  apiKeyMasked: string;
}

function maskApiKey(apiKey: string): string {
  const key = apiKey.trim();
  if (!key) {
    return "";
  }
  if (key.length <= 8) {
    return `${key.slice(0, 2)}****`;
  }
  return `${key.slice(0, 4)}****${key.slice(-4)}`;
}

function normalizeEnabled(value: unknown): 0 | 1 {
  if (value === 1 || value === "1" || value === true || value === "true") {
    return 1;
  }
  return 0;
}

export function normalizeAiConfig(raw: unknown, existing: Partial<AiConfigStored> = {}): AiConfigStored {
  const record =
    raw && typeof raw === "object" && !Array.isArray(raw)
      ? (raw as Record<string, unknown>)
      : {};
  const baseUrl = String(record.baseUrl ?? existing.baseUrl ?? DEFAULT_AI_BASE_URL).trim() || DEFAULT_AI_BASE_URL;
  const model = String(record.model ?? existing.model ?? DEFAULT_AI_MODEL).trim() || DEFAULT_AI_MODEL;
  const modelOptionId = String(record.modelOptionId ?? existing.modelOptionId ?? "").trim();
  const translateModel =
    String(record.translateModel ?? existing.translateModel ?? DEFAULT_TRANSLATE_MODEL).trim() ||
    DEFAULT_TRANSLATE_MODEL;
  const translateModelOptionId = String(
    record.translateModelOptionId ?? existing.translateModelOptionId ?? DEFAULT_TRANSLATE_MODEL_OPTION_ID,
  ).trim();
  const incomingKey = String(record.apiKey ?? "").trim();
  return {
    enabled: normalizeEnabled(record.enabled ?? existing.enabled),
    baseUrl: baseUrl.replace(/\/+$/, ""),
    apiKey: incomingKey || String(existing.apiKey ?? "").trim(),
    model,
    modelOptionId,
    translateModel,
    translateModelOptionId,
  };
}

function toPublicAiConfig(stored: AiConfigStored): AiConfigPublic {
  return {
    key: AI_CONFIG_KEY,
    enabled: stored.enabled,
    baseUrl: stored.baseUrl,
    model: stored.model,
    modelOptionId: stored.modelOptionId,
    translateModel: stored.translateModel,
    translateModelOptionId: stored.translateModelOptionId,
    hasApiKey: Boolean(stored.apiKey.trim()),
    apiKeyMasked: maskApiKey(stored.apiKey),
  };
}

export async function getAiConfigStored(): Promise<AiConfigStored> {
  await ensureSchema();
  const [rows] = await pool.query<ConfigRow[]>(
    "SELECT config_key, config_value FROM app_config WHERE config_key = ? LIMIT 1",
    [AI_CONFIG_KEY],
  );
  const row = rows[0];
  if (!row) {
    return {
      enabled: 0,
      baseUrl: DEFAULT_AI_BASE_URL,
      apiKey: "",
      model: DEFAULT_AI_MODEL,
      modelOptionId: "phone-openai-gpt-4o-mini",
      translateModel: DEFAULT_TRANSLATE_MODEL,
      translateModelOptionId: DEFAULT_TRANSLATE_MODEL_OPTION_ID,
    };
  }
  try {
    return normalizeAiConfig(JSON.parse(row.config_value) as unknown);
  } catch {
    return {
      enabled: 0,
      baseUrl: DEFAULT_AI_BASE_URL,
      apiKey: "",
      model: DEFAULT_AI_MODEL,
      modelOptionId: "phone-openai-gpt-4o-mini",
      translateModel: DEFAULT_TRANSLATE_MODEL,
      translateModelOptionId: DEFAULT_TRANSLATE_MODEL_OPTION_ID,
    };
  }
}

export async function getAiConfigPublic(): Promise<AiConfigPublic> {
  const stored = await getAiConfigStored();
  return toPublicAiConfig(stored);
}

export async function setAiConfig(input: unknown): Promise<AiConfigPublic> {
  await ensureSchema();
  const existing = await getAiConfigStored();
  const normalized = normalizeAiConfig(input, existing);
  if (!normalized.baseUrl) {
    throw new Error("baseUrl_required");
  }
  if (!normalized.model) {
    throw new Error("model_required");
  }
  await pool.query(
    `INSERT INTO app_config (config_key, config_value)
     VALUES (?, ?)
     ON DUPLICATE KEY UPDATE config_value = VALUES(config_value)`,
    [AI_CONFIG_KEY, JSON.stringify(normalized)],
  );
  return toPublicAiConfig(normalized);
}

export const DEVICE_TRANSFER_ENABLED_KEY = "device.transfer.enabled";
export const DEVICE_TRANSFER_SECRET_KEY = "device.transfer.secret";

export async function getConfigString(key: string, fallback = ""): Promise<string> {
  await ensureSchema();
  const [rows] = await pool.query<ConfigRow[]>(
    "SELECT config_value FROM app_config WHERE config_key = ? LIMIT 1",
    [key],
  );
  const value = rows[0]?.config_value;
  if (value == null) {
    return fallback;
  }
  return String(value);
}

export async function setConfigString(key: string, value: string): Promise<void> {
  await ensureSchema();
  await pool.query(
    `INSERT INTO app_config (config_key, config_value)
     VALUES (?, ?)
     ON DUPLICATE KEY UPDATE config_value = VALUES(config_value)`,
    [key, value],
  );
}

export async function getDeviceTransferEnabled(): Promise<0 | 1> {
  const raw = (await getConfigString(DEVICE_TRANSFER_ENABLED_KEY, "0")).trim();
  return raw === "1" ? 1 : 0;
}

export async function getDeviceTransferSecret(): Promise<string> {
  return (await getConfigString(DEVICE_TRANSFER_SECRET_KEY, "")).trim();
}

export const CLIENT_APK_UPDATE_ENABLED_KEY = "device.client_apk.update";
export const CLIENT_APK_URL_KEY = "device.client_apk.url";
export const CLIENT_APK_VERSION_KEY = "device.client_apk.version";

export async function getClientApkUpdateEnabled(): Promise<0 | 1> {
  const raw = (await getConfigString(CLIENT_APK_UPDATE_ENABLED_KEY, "1")).trim();
  return raw === "0" ? 0 : 1;
}

export async function getClientApkUrl(): Promise<string> {
  return (await getConfigString(CLIENT_APK_URL_KEY, "")).trim();
}

export async function getClientApkVersion(): Promise<string> {
  return (await getConfigString(CLIENT_APK_VERSION_KEY, "")).trim();
}

export async function setClientApkUpdateConfig(input: {
  enabled?: unknown;
  url?: unknown;
  version?: unknown;
}): Promise<{ enabled: 0 | 1; url: string; version: string }> {
  if (input.enabled != null) {
    const enabled = String(input.enabled).trim() === "0" ? "0" : "1";
    await setConfigString(CLIENT_APK_UPDATE_ENABLED_KEY, enabled);
  }
  if (input.url != null) {
    const url = String(input.url).trim();
    if (url && !/^https?:\/\//i.test(url)) {
      throw new Error("client_apk_url_invalid");
    }
    await setConfigString(CLIENT_APK_URL_KEY, url);
  }
  if (input.version != null) {
    await setConfigString(CLIENT_APK_VERSION_KEY, String(input.version).trim());
  }
  return {
    enabled: await getClientApkUpdateEnabled(),
    url: await getClientApkUrl(),
    version: await getClientApkVersion(),
  };
}

/** 后台按钮可不传 url；缺配置时明确报错，不静默。 */
export async function resolveUpdateApkParams(
  params: Record<string, unknown> | undefined,
): Promise<Record<string, unknown>> {
  if ((await getClientApkUpdateEnabled()) !== 1) {
    throw new Error("client_apk_update_disabled");
  }
  const merged: Record<string, unknown> = { ...(params ?? {}) };
  const fromParams = typeof merged.url === "string" ? merged.url.trim() : "";
  const url = fromParams || (await getClientApkUrl());
  if (!url) {
    throw new Error("client_apk_url_not_configured");
  }
  if (!/^https?:\/\//i.test(url)) {
    throw new Error("client_apk_url_invalid");
  }
  merged.url = url;
  return merged;
}

export async function setDeviceTransferConfig(input: {
  enabled?: unknown;
  secret?: unknown;
}): Promise<{ enabled: 0 | 1; secret: string }> {
  if (input.enabled != null) {
    const enabled = String(input.enabled).trim() === "1" ? "1" : "0";
    await setConfigString(DEVICE_TRANSFER_ENABLED_KEY, enabled);
  }
  if (input.secret != null) {
    const secret = String(input.secret).trim();
    if (!secret) {
      throw new Error("transfer_secret_required");
    }
    await setConfigString(DEVICE_TRANSFER_SECRET_KEY, secret);
  }
  return {
    enabled: await getDeviceTransferEnabled(),
    secret: await getDeviceTransferSecret(),
  };
}
