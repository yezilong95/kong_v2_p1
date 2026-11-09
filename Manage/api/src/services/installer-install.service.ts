import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";
import { sqlTodayInBusinessTz } from "../utils/business-date.js";

interface InstallerInstallLogRow extends RowDataPacket {
  id: string;
  android_id: string | null;
  model: string | null;
  brand: string | null;
  installer_package: string | null;
  version_name: string | null;
  version_code: number | null;
  client_ip: string | null;
  user_agent: string | null;
  installed_at: Date;
  created_at: Date;
}

export interface InstallerInstallLogInput {
  androidId?: string | null;
  model?: string | null;
  brand?: string | null;
  installerPackage?: string | null;
  versionName?: string | null;
  versionCode?: number | null;
  clientIp?: string | null;
  userAgent?: string | null;
  installedAt?: number;
}

export interface InstallerInstallLogRecord {
  id: string;
  androidId: string | null;
  model: string | null;
  brand: string | null;
  installerPackage: string | null;
  versionName: string | null;
  versionCode: number | null;
  clientIp: string | null;
  userAgent: string | null;
  installedAt: Date;
  createdAt: Date;
}

function mapRow(row: InstallerInstallLogRow): InstallerInstallLogRecord {
  return {
    id: row.id,
    androidId: row.android_id,
    model: row.model,
    brand: row.brand,
    installerPackage: row.installer_package,
    versionName: row.version_name,
    versionCode: row.version_code,
    clientIp: row.client_ip,
    userAgent: row.user_agent,
    installedAt: row.installed_at,
    createdAt: row.created_at,
  };
}

export async function createInstallerInstallLog(
  input: InstallerInstallLogInput,
): Promise<InstallerInstallLogRecord> {
  const id = uuidv4();
  const installedAt = input.installedAt ? new Date(input.installedAt) : new Date();

  await pool.query(
    `INSERT INTO installer_install_logs
      (id, android_id, model, brand, installer_package, version_name, version_code,
       client_ip, user_agent, installed_at)
     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      input.androidId?.trim() || null,
      input.model?.trim() || null,
      input.brand?.trim() || null,
      input.installerPackage?.trim() || null,
      input.versionName?.trim() || null,
      input.versionCode ?? null,
      input.clientIp?.trim()?.slice(0, 64) || null,
      input.userAgent?.trim()?.slice(0, 512) || null,
      installedAt,
    ],
  );

  const [rows] = await pool.query<InstallerInstallLogRow[]>(
    "SELECT * FROM installer_install_logs WHERE id = ? LIMIT 1",
    [id],
  );
  const record = rows[0] ? mapRow(rows[0]) : null;
  if (!record) {
    throw new Error("installer_install_log_save_failed");
  }
  return record;
}

export async function countInstallerInstallLogs(): Promise<number> {
  const [rows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS total FROM installer_install_logs`,
  );
  const total = Number(rows[0]?.total ?? 0);
  return Number.isFinite(total) ? total : 0;
}

export async function countInstallerInstallLogsToday(): Promise<number> {
  const [rows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS today
     FROM installer_install_logs
     WHERE ${sqlTodayInBusinessTz("installed_at")}`,
  );
  const today = Number(rows[0]?.today ?? 0);
  return Number.isFinite(today) ? today : 0;
}

export async function listInstallerInstallLogs(options?: {
  limit?: number;
}): Promise<InstallerInstallLogRecord[]> {
  const limit = Math.min(Math.max(options?.limit ?? 200, 1), 1000);
  const [rows] = await pool.query<InstallerInstallLogRow[]>(
    `SELECT * FROM installer_install_logs
     ORDER BY installed_at DESC
     LIMIT ?`,
    [limit],
  );
  return rows.map(mapRow);
}
