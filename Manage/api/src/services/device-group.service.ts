import type { RowDataPacket, ResultSetHeader } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface DeviceGroupRow extends RowDataPacket {
  id: string;
  name: string;
  sort_order: number;
  created_at: Date;
  updated_at: Date;
  device_count?: number;
}

export interface DeviceGroupRecord {
  id: string;
  name: string;
  sortOrder: number;
  deviceCount: number;
  createdAt: string;
  updatedAt: string;
}

function mapGroup(row: DeviceGroupRow): DeviceGroupRecord {
  return {
    id: row.id,
    name: row.name,
    sortOrder: row.sort_order,
    deviceCount: Number(row.device_count ?? 0),
    createdAt: row.created_at.toISOString(),
    updatedAt: row.updated_at.toISOString(),
  };
}

export async function listDeviceGroups(): Promise<DeviceGroupRecord[]> {
  const [rows] = await pool.query<DeviceGroupRow[]>(
    `SELECT g.*,
            (SELECT COUNT(*) FROM devices d WHERE d.group_id = g.id AND d.transferred_at IS NULL) AS device_count
     FROM device_groups g
     ORDER BY g.sort_order ASC, g.created_at ASC`,
  );
  return rows.map(mapGroup);
}

export async function countUngroupedDevices(): Promise<number> {
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT COUNT(*) AS total FROM devices WHERE group_id IS NULL AND transferred_at IS NULL",
  );
  return Number(rows[0]?.total ?? 0);
}

export async function countAllDevices(): Promise<number> {
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT COUNT(*) AS total FROM devices WHERE transferred_at IS NULL",
  );
  return Number(rows[0]?.total ?? 0);
}

export async function getDeviceGroupById(id: string): Promise<DeviceGroupRecord | null> {
  const [rows] = await pool.query<DeviceGroupRow[]>(
    `SELECT g.*,
            (SELECT COUNT(*) FROM devices d WHERE d.group_id = g.id AND d.transferred_at IS NULL) AS device_count
     FROM device_groups g
     WHERE g.id = ?
     LIMIT 1`,
    [id],
  );
  const row = rows[0];
  return row ? mapGroup(row) : null;
}

export async function createDeviceGroup(name: string, sortOrder = 0): Promise<DeviceGroupRecord> {
  const trimmed = name.trim();
  if (!trimmed) {
    throw new Error("group_name_required");
  }
  const id = uuidv4();
  await pool.query(
    `INSERT INTO device_groups (id, name, sort_order) VALUES (?, ?, ?)`,
    [id, trimmed, sortOrder],
  );
  const created = await getDeviceGroupById(id);
  if (!created) {
    throw new Error("group_create_failed");
  }
  return created;
}

export async function updateDeviceGroup(
  id: string,
  input: { name?: string; sortOrder?: number },
): Promise<DeviceGroupRecord | null> {
  const existing = await getDeviceGroupById(id);
  if (!existing) {
    return null;
  }
  const nextName = input.name != null ? input.name.trim() : existing.name;
  if (!nextName) {
    throw new Error("group_name_required");
  }
  const nextSort =
    input.sortOrder != null && Number.isFinite(input.sortOrder)
      ? Math.trunc(input.sortOrder)
      : existing.sortOrder;

  await pool.query(
    `UPDATE device_groups SET name = ?, sort_order = ? WHERE id = ?`,
    [nextName, nextSort, id],
  );
  return getDeviceGroupById(id);
}

export async function deleteDeviceGroup(id: string): Promise<boolean> {
  // ON DELETE SET NULL：设备自动归未分组
  const [result] = await pool.query<ResultSetHeader>(
    "DELETE FROM device_groups WHERE id = ?",
    [id],
  );
  return result.affectedRows > 0;
}

export async function assertGroupExists(groupId: string | null): Promise<void> {
  if (groupId == null) {
    return;
  }
  const group = await getDeviceGroupById(groupId);
  if (!group) {
    throw new Error("group_not_found");
  }
}
