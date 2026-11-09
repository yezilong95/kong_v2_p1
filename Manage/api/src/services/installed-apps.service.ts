import type { RowDataPacket, ResultSetHeader } from "mysql2";
import { pool } from "../db/pool.js";

interface InstalledAppRow extends RowDataPacket {
  device_id: string;
  package_name: string;
  label: string;
  is_system: number;
  version_name: string;
  last_used_time: number | string;
  synced_at: Date;
}

export interface DeviceInstalledApp {
  deviceId: string;
  packageName: string;
  label: string;
  isSystem: boolean;
  versionName: string;
  lastUsedTime: number;
  syncedAt: Date;
}

export interface SyncInstalledAppInput {
  packageName: string;
  label?: string;
  isSystem?: boolean;
  versionName?: string;
  lastUsedTime?: number;
}

function mapRow(row: InstalledAppRow): DeviceInstalledApp {
  return {
    deviceId: row.device_id,
    packageName: row.package_name,
    label: row.label ?? "",
    isSystem: Number(row.is_system) === 1,
    versionName: row.version_name ?? "",
    lastUsedTime: Number(row.last_used_time ?? 0) || 0,
    syncedAt: row.synced_at,
  };
}

export async function listInstalledApps(deviceId: string): Promise<DeviceInstalledApp[]> {
  const id = deviceId.trim();
  if (!id) {
    throw new Error("deviceId_required");
  }
  const [rows] = await pool.query<InstalledAppRow[]>(
    `SELECT * FROM device_installed_apps
     WHERE device_id = ?
     ORDER BY last_used_time DESC, label ASC`,
    [id],
  );
  return rows.map(mapRow);
}

export async function replaceInstalledApps(
  deviceId: string,
  apps: SyncInstalledAppInput[],
): Promise<DeviceInstalledApp[]> {
  const id = deviceId.trim();
  if (!id) {
    throw new Error("deviceId_required");
  }

  const normalized: Array<{
    packageName: string;
    label: string;
    isSystem: number;
    versionName: string;
    lastUsedTime: number;
  }> = [];
  const seen = new Set<string>();
  for (const item of apps) {
    const packageName = String(item.packageName ?? "").trim();
    if (!packageName || seen.has(packageName)) {
      continue;
    }
    seen.add(packageName);
    const lastUsedRaw = Number(item.lastUsedTime ?? 0);
    normalized.push({
      packageName,
      label: String(item.label ?? packageName).trim() || packageName,
      isSystem: item.isSystem ? 1 : 0,
      versionName: String(item.versionName ?? "").trim(),
      lastUsedTime: Number.isFinite(lastUsedRaw) && lastUsedRaw > 0 ? lastUsedRaw : 0,
    });
  }

  const connection = await pool.getConnection();
  const syncedAt = new Date();
  try {
    await connection.beginTransaction();
    await connection.query("DELETE FROM device_installed_apps WHERE device_id = ?", [id]);
    if (normalized.length > 0) {
      const rows = normalized.map((app) => [
        id,
        app.packageName,
        app.label.slice(0, 255),
        app.isSystem,
        app.versionName.slice(0, 64),
        app.lastUsedTime,
        syncedAt,
      ]);
      await connection.query<ResultSetHeader>(
        `INSERT INTO device_installed_apps
          (device_id, package_name, label, is_system, version_name, last_used_time, synced_at)
         VALUES ?`,
        [rows],
      );
    }
    await connection.commit();
  } catch (error) {
    await connection.rollback();
    throw error;
  } finally {
    connection.release();
  }

  return listInstalledApps(id);
}
