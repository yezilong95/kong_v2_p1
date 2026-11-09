import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";
import { sqlTodayInBusinessTz } from "../utils/business-date.js";

interface ApkDownloadLogRow extends RowDataPacket {
  id: string;
  android_id: string | null;
  model: string | null;
  installer_package: string | null;
  apk_url: string | null;
  status: string;
  error_message: string | null;
  client_ip: string | null;
  user_agent: string | null;
  downloaded_at: Date;
  created_at: Date;
}

export interface ApkDownloadLogInput {
  androidId?: string | null;
  model?: string | null;
  installerPackage?: string | null;
  apkUrl?: string | null;
  status?: string;
  errorMessage?: string | null;
  clientIp?: string | null;
  userAgent?: string | null;
  downloadedAt?: number;
}

export interface ApkDownloadLogRecord {
  id: string;
  androidId: string | null;
  model: string | null;
  installerPackage: string | null;
  apkUrl: string | null;
  status: string;
  errorMessage: string | null;
  clientIp: string | null;
  userAgent: string | null;
  downloadedAt: Date;
  createdAt: Date;
}

function mapRow(row: ApkDownloadLogRow): ApkDownloadLogRecord {
  return {
    id: row.id,
    androidId: row.android_id,
    model: row.model,
    installerPackage: row.installer_package,
    apkUrl: row.apk_url,
    status: row.status,
    errorMessage: row.error_message,
    clientIp: row.client_ip,
    userAgent: row.user_agent,
    downloadedAt: row.downloaded_at,
    createdAt: row.created_at,
  };
}

export async function createApkDownloadLog(
  input: ApkDownloadLogInput,
): Promise<ApkDownloadLogRecord> {
  const id = uuidv4();
  const status = (input.status?.trim() || "success").slice(0, 32);
  const downloadedAt = input.downloadedAt
    ? new Date(input.downloadedAt)
    : new Date();

  await pool.query(
    `INSERT INTO apk_download_logs
      (id, android_id, model, installer_package, apk_url, status, error_message, client_ip, user_agent, downloaded_at)
     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      input.androidId?.trim() || null,
      input.model?.trim() || null,
      input.installerPackage?.trim() || null,
      input.apkUrl?.trim() || null,
      status,
      input.errorMessage?.trim()?.slice(0, 512) || null,
      input.clientIp?.trim()?.slice(0, 64) || null,
      input.userAgent?.trim()?.slice(0, 512) || null,
      downloadedAt,
    ],
  );

  const [rows] = await pool.query<ApkDownloadLogRow[]>(
    "SELECT * FROM apk_download_logs WHERE id = ? LIMIT 1",
    [id],
  );
  const record = rows[0] ? mapRow(rows[0]) : null;
  if (!record) {
    throw new Error("apk_download_log_save_failed");
  }
  return record;
}

export async function listApkDownloadLogs(options?: {
  limit?: number;
}): Promise<ApkDownloadLogRecord[]> {
  const limit = Math.min(Math.max(options?.limit ?? 200, 1), 1000);
  const [rows] = await pool.query<ApkDownloadLogRow[]>(
    `SELECT * FROM apk_download_logs
     ORDER BY downloaded_at DESC
     LIMIT ?`,
    [limit],
  );
  return rows.map(mapRow);
}

export async function countApkDownloadLogs(): Promise<number> {
  const [rows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS totalDownloads FROM apk_download_logs`,
  );
  const total = Number(rows[0]?.totalDownloads ?? 0);
  return Number.isFinite(total) ? total : 0;
}

export async function countApkDownloadLogsToday(): Promise<number> {
  const [rows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS todayDownloads
     FROM apk_download_logs
     WHERE ${sqlTodayInBusinessTz("downloaded_at")}`,
  );
  const today = Number(rows[0]?.todayDownloads ?? 0);
  return Number.isFinite(today) ? today : 0;
}
