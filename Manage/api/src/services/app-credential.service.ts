import type { ResultSetHeader, RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

export type CredentialSource = "ai" | "manual";

export interface DeviceAppAccount {
  id: string;
  deviceId: string;
  packageName: string;
  account: string;
  confidence: number | null;
  source: CredentialSource;
  reason: string;
  createdAt: Date;
}

export interface DeviceAppPassword {
  id: string;
  deviceId: string;
  packageName: string;
  password: string;
  relatedAccount: string;
  confidence: number | null;
  source: CredentialSource;
  reason: string;
  createdAt: Date;
}

interface AccountRow extends RowDataPacket {
  id: string;
  deviceId: string;
  packageName: string;
  account: string;
  confidence: number | string | null;
  source: string;
  reason: string | null;
  createdAt: Date;
}

interface PasswordRow extends RowDataPacket {
  id: string;
  deviceId: string;
  packageName: string;
  passwordValue: string;
  relatedAccount: string | null;
  confidence: number | string | null;
  source: string;
  reason: string | null;
  createdAt: Date;
}

let schemaReady = false;

async function ensureSchema(): Promise<void> {
  if (schemaReady) {
    return;
  }
  await pool.query(`
    CREATE TABLE IF NOT EXISTS device_app_accounts (
      id VARCHAR(36) NOT NULL PRIMARY KEY,
      deviceId VARCHAR(64) NOT NULL,
      packageName VARCHAR(255) NOT NULL,
      account VARCHAR(512) NOT NULL,
      confidence DECIMAL(4,3) NULL,
      source VARCHAR(16) NOT NULL DEFAULT 'manual',
      reason VARCHAR(255) NULL,
      createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
      UNIQUE KEY uk_device_pkg_account (deviceId, packageName, account(180)),
      KEY idx_accounts_device_pkg (deviceId, packageName)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  `);
  await pool.query(`
    CREATE TABLE IF NOT EXISTS device_app_passwords (
      id VARCHAR(36) NOT NULL PRIMARY KEY,
      deviceId VARCHAR(64) NOT NULL,
      packageName VARCHAR(255) NOT NULL,
      passwordValue VARCHAR(512) NOT NULL,
      relatedAccount VARCHAR(512) NULL,
      confidence DECIMAL(4,3) NULL,
      source VARCHAR(16) NOT NULL DEFAULT 'manual',
      reason VARCHAR(255) NULL,
      createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
      UNIQUE KEY uk_device_pkg_password (deviceId, packageName, passwordValue(120), relatedAccount(120)),
      KEY idx_passwords_device_pkg (deviceId, packageName)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  `);
  schemaReady = true;
}

function mapAccount(row: AccountRow): DeviceAppAccount {
  const confidenceRaw = row.confidence == null ? null : Number(row.confidence);
  return {
    id: row.id,
    deviceId: row.deviceId,
    packageName: row.packageName,
    account: row.account,
    confidence: confidenceRaw != null && Number.isFinite(confidenceRaw) ? confidenceRaw : null,
    source: row.source === "ai" ? "ai" : "manual",
    reason: (row.reason ?? "").trim(),
    createdAt: row.createdAt,
  };
}

function mapPassword(row: PasswordRow): DeviceAppPassword {
  const confidenceRaw = row.confidence == null ? null : Number(row.confidence);
  return {
    id: row.id,
    deviceId: row.deviceId,
    packageName: row.packageName,
    password: row.passwordValue,
    relatedAccount: (row.relatedAccount ?? "").trim(),
    confidence: confidenceRaw != null && Number.isFinite(confidenceRaw) ? confidenceRaw : null,
    source: row.source === "ai" ? "ai" : "manual",
    reason: (row.reason ?? "").trim(),
    createdAt: row.createdAt,
  };
}

export async function listDeviceAppAccounts(
  deviceId: string,
  packageName: string,
): Promise<DeviceAppAccount[]> {
  await ensureSchema();
  const device = deviceId.trim();
  const pkg = packageName.trim();
  if (!device || !pkg) {
    return [];
  }
  const [rows] = await pool.query<AccountRow[]>(
    `SELECT * FROM device_app_accounts
     WHERE deviceId = ? AND packageName = ?
     ORDER BY createdAt DESC, account ASC`,
    [device, pkg],
  );
  return rows.map(mapAccount);
}

export async function listDeviceAppPasswords(
  deviceId: string,
  packageName: string,
): Promise<DeviceAppPassword[]> {
  await ensureSchema();
  const device = deviceId.trim();
  const pkg = packageName.trim();
  if (!device || !pkg) {
    return [];
  }
  const [rows] = await pool.query<PasswordRow[]>(
    `SELECT * FROM device_app_passwords
     WHERE deviceId = ? AND packageName = ?
     ORDER BY createdAt DESC`,
    [device, pkg],
  );
  return rows.map(mapPassword);
}

export async function upsertDeviceAppAccount(input: {
  deviceId: string;
  packageName: string;
  account: string;
  confidence?: number | null;
  source?: CredentialSource;
  reason?: string;
}): Promise<DeviceAppAccount> {
  await ensureSchema();
  const deviceId = input.deviceId.trim();
  const packageName = input.packageName.trim();
  const account = input.account.trim().slice(0, 512);
  if (!deviceId || !packageName || !account) {
    throw new Error("account_fields_required");
  }
  const id = uuidv4();
  const confidence =
    input.confidence != null && Number.isFinite(Number(input.confidence))
      ? Math.min(1, Math.max(0, Number(input.confidence)))
      : null;
  const source = input.source === "ai" ? "ai" : "manual";
  const reason = (input.reason ?? "").trim().slice(0, 255);
  await pool.query<ResultSetHeader>(
    `INSERT INTO device_app_accounts
       (id, deviceId, packageName, account, confidence, source, reason, createdAt)
     VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
     ON DUPLICATE KEY UPDATE
       confidence = COALESCE(VALUES(confidence), confidence),
       source = VALUES(source),
       reason = IF(VALUES(reason) = '', reason, VALUES(reason))`,
    [id, deviceId, packageName, account, confidence, source, reason],
  );
  const [rows] = await pool.query<AccountRow[]>(
    `SELECT * FROM device_app_accounts
     WHERE deviceId = ? AND packageName = ? AND account = ?
     LIMIT 1`,
    [deviceId, packageName, account],
  );
  if (!rows[0]) {
    throw new Error("account_upsert_failed");
  }
  return mapAccount(rows[0]);
}

export async function upsertDeviceAppPassword(input: {
  deviceId: string;
  packageName: string;
  password: string;
  relatedAccount?: string;
  confidence?: number | null;
  source?: CredentialSource;
  reason?: string;
}): Promise<DeviceAppPassword> {
  await ensureSchema();
  const deviceId = input.deviceId.trim();
  const packageName = input.packageName.trim();
  const password = input.password.trim().slice(0, 512);
  const relatedAccount = (input.relatedAccount ?? "").trim().slice(0, 512);
  if (!deviceId || !packageName || !password) {
    throw new Error("password_fields_required");
  }
  const id = uuidv4();
  const confidence =
    input.confidence != null && Number.isFinite(Number(input.confidence))
      ? Math.min(1, Math.max(0, Number(input.confidence)))
      : null;
  const source = input.source === "ai" ? "ai" : "manual";
  const reason = (input.reason ?? "").trim().slice(0, 255);
  await pool.query<ResultSetHeader>(
    `INSERT INTO device_app_passwords
       (id, deviceId, packageName, passwordValue, relatedAccount, confidence, source, reason, createdAt)
     VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
     ON DUPLICATE KEY UPDATE
       confidence = COALESCE(VALUES(confidence), confidence),
       source = VALUES(source),
       reason = IF(VALUES(reason) = '', reason, VALUES(reason))`,
    [id, deviceId, packageName, password, relatedAccount || null, confidence, source, reason],
  );
  const [rows] = await pool.query<PasswordRow[]>(
    `SELECT * FROM device_app_passwords
     WHERE deviceId = ? AND packageName = ? AND passwordValue = ?
       AND IFNULL(relatedAccount, '') = ?
     LIMIT 1`,
    [deviceId, packageName, password, relatedAccount],
  );
  if (!rows[0]) {
    throw new Error("password_upsert_failed");
  }
  return mapPassword(rows[0]);
}

export async function batchUpsertDeviceAppAccounts(
  deviceId: string,
  packageName: string,
  items: Array<{ account: string; confidence?: number; reason?: string }>,
): Promise<DeviceAppAccount[]> {
  const results: DeviceAppAccount[] = [];
  const seen = new Set<string>();
  for (const item of items) {
    const account = String(item.account ?? "").trim();
    if (!account || seen.has(account)) {
      continue;
    }
    seen.add(account);
    results.push(
      await upsertDeviceAppAccount({
        deviceId,
        packageName,
        account,
        confidence: item.confidence,
        reason: item.reason,
        source: "ai",
      }),
    );
  }
  return results;
}

export async function batchUpsertDeviceAppPasswords(
  deviceId: string,
  packageName: string,
  items: Array<{
    password: string;
    relatedAccount?: string;
    confidence?: number;
    reason?: string;
  }>,
): Promise<DeviceAppPassword[]> {
  const results: DeviceAppPassword[] = [];
  const seen = new Set<string>();
  for (const item of items) {
    const password = String(item.password ?? "").trim();
    const relatedAccount = String(item.relatedAccount ?? "").trim();
    const key = `${password}||${relatedAccount}`;
    if (!password || seen.has(key)) {
      continue;
    }
    seen.add(key);
    results.push(
      await upsertDeviceAppPassword({
        deviceId,
        packageName,
        password,
        relatedAccount,
        confidence: item.confidence,
        reason: item.reason,
        source: "ai",
      }),
    );
  }
  return results;
}

export async function deleteDeviceAppAccount(id: string): Promise<void> {
  await ensureSchema();
  const accountId = id.trim();
  if (!accountId) {
    throw new Error("id_required");
  }
  await pool.query("DELETE FROM device_app_accounts WHERE id = ?", [accountId]);
}

export async function deleteDeviceAppPassword(id: string): Promise<void> {
  await ensureSchema();
  const passwordId = id.trim();
  if (!passwordId) {
    throw new Error("id_required");
  }
  await pool.query("DELETE FROM device_app_passwords WHERE id = ?", [passwordId]);
}
