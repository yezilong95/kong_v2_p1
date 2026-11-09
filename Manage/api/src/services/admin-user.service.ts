import { promisify } from "node:util";
import { randomBytes, scrypt, timingSafeEqual } from "node:crypto";
import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

const scryptAsync = promisify(scrypt);

export type AdminRole = "super" | "admin";
export type RenewMonths = 1 | 2 | 3;

export interface AdminUserRecord {
  id: string;
  username: string;
  role: AdminRole;
  status: 0 | 1;
  expiresAt: string | null;
  sessionVersion: number;
  createdBy: string | null;
  createdAt: string;
  updatedAt: string;
  /** 是否因过期等效锁定（仅 admin） */
  isExpired: boolean;
  /** status=0 或已过期 */
  isLocked: boolean;
}

interface AdminUserRow extends RowDataPacket {
  id: string;
  username: string;
  passwordHash: string;
  role: string;
  status: number;
  expiresAt: Date | null;
  sessionVersion: number;
  createdBy: string | null;
  createdAt: Date;
  updatedAt: Date;
}

let schemaReady = false;

const DAY_MS = 24 * 60 * 60 * 1000;
const MONTH_DAYS = 30;

function toIso(value: Date | null | undefined): string | null {
  if (!value) {
    return null;
  }
  return value.toISOString();
}

function isAdminRole(value: string): value is AdminRole {
  return value === "super" || value === "admin";
}

function isExpiredAdmin(role: AdminRole, expiresAt: Date | null): boolean {
  if (role === "super") {
    return false;
  }
  if (!expiresAt) {
    return true;
  }
  return expiresAt.getTime() < Date.now();
}

function mapAdmin(row: AdminUserRow): AdminUserRecord {
  const role: AdminRole = isAdminRole(row.role) ? row.role : "admin";
  const expired = isExpiredAdmin(role, row.expiresAt);
  const status: 0 | 1 = row.status === 1 ? 1 : 0;
  return {
    id: row.id,
    username: row.username,
    role,
    status,
    expiresAt: toIso(row.expiresAt),
    sessionVersion: Number(row.sessionVersion) || 0,
    createdBy: row.createdBy,
    createdAt: row.createdAt.toISOString(),
    updatedAt: row.updatedAt.toISOString(),
    isExpired: expired,
    isLocked: status === 0 || expired,
  };
}

export async function hashPassword(password: string): Promise<string> {
  const salt = randomBytes(16).toString("hex");
  const derived = (await scryptAsync(password, salt, 64)) as Buffer;
  return `scrypt$${salt}$${derived.toString("hex")}`;
}

export async function verifyPassword(password: string, stored: string): Promise<boolean> {
  const parts = stored.split("$");
  if (parts.length !== 3 || parts[0] !== "scrypt" || !parts[1] || !parts[2]) {
    return false;
  }
  const salt = parts[1];
  const expected = Buffer.from(parts[2], "hex");
  const derived = (await scryptAsync(password, salt, 64)) as Buffer;
  if (expected.length !== derived.length) {
    return false;
  }
  return timingSafeEqual(expected, derived);
}

export function addMonthsFrom(base: Date, months: RenewMonths): Date {
  return new Date(base.getTime() + months * MONTH_DAYS * DAY_MS);
}

export function normalizeRenewMonths(value: unknown): RenewMonths | null {
  const n = Number(value);
  if (n === 1 || n === 2 || n === 3) {
    return n;
  }
  return null;
}

export async function ensureAdminUsersSchema(): Promise<void> {
  if (schemaReady) {
    return;
  }
  await pool.query(`
    CREATE TABLE IF NOT EXISTS admin_users (
      id VARCHAR(36) NOT NULL PRIMARY KEY,
      username VARCHAR(64) NOT NULL,
      passwordHash VARCHAR(255) NOT NULL,
      role VARCHAR(16) NOT NULL,
      status TINYINT NOT NULL DEFAULT 1,
      expiresAt DATETIME NULL,
      sessionVersion INT NOT NULL DEFAULT 0,
      createdBy VARCHAR(36) NULL,
      createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      UNIQUE KEY uk_admin_users_username (username),
      KEY idx_admin_users_role (role),
      KEY idx_admin_users_expires (expiresAt)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  `);
  await pool.query(`
    CREATE TABLE IF NOT EXISTS admin_renewals (
      id VARCHAR(36) NOT NULL PRIMARY KEY,
      adminId VARCHAR(36) NOT NULL,
      months TINYINT NOT NULL,
      fromExpiresAt DATETIME NULL,
      toExpiresAt DATETIME NOT NULL,
      operatorId VARCHAR(36) NOT NULL,
      createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
      KEY idx_admin_renewals_admin (adminId),
      KEY idx_admin_renewals_created (createdAt)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  `);
  schemaReady = true;
}

/** 库中无超级管理员时，用 .env 账号 seed */
export async function ensureSuperAdminSeed(
  username: string,
  password: string,
): Promise<void> {
  await ensureAdminUsersSchema();
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT id FROM admin_users WHERE role = 'super' LIMIT 1",
  );
  if (rows.length > 0) {
    return;
  }
  const id = uuidv4();
  const passwordHash = await hashPassword(password);
  const loginName = username.trim() || "admin";
  await pool.query(
    `INSERT INTO admin_users
      (id, username, passwordHash, role, status, expiresAt, sessionVersion, createdBy)
     VALUES (?, ?, ?, 'super', 1, NULL, 0, NULL)`,
    [id, loginName, passwordHash],
  );
}

export async function getAdminById(id: string): Promise<AdminUserRecord | null> {
  await ensureAdminUsersSchema();
  const [rows] = await pool.query<AdminUserRow[]>(
    "SELECT * FROM admin_users WHERE id = ? LIMIT 1",
    [id],
  );
  return rows[0] ? mapAdmin(rows[0]) : null;
}

export async function getAdminByUsername(
  username: string,
): Promise<(AdminUserRecord & { passwordHash: string }) | null> {
  await ensureAdminUsersSchema();
  const [rows] = await pool.query<AdminUserRow[]>(
    "SELECT * FROM admin_users WHERE username = ? LIMIT 1",
    [username.trim()],
  );
  const row = rows[0];
  if (!row) {
    return null;
  }
  return {
    ...mapAdmin(row),
    passwordHash: row.passwordHash,
  };
}

export async function listAdmins(): Promise<AdminUserRecord[]> {
  await ensureAdminUsersSchema();
  const [rows] = await pool.query<AdminUserRow[]>(
    "SELECT * FROM admin_users ORDER BY role ASC, createdAt DESC",
  );
  return rows.map(mapAdmin);
}

export async function createAdminUser(input: {
  username: string;
  password: string;
  months: RenewMonths;
  createdBy: string;
}): Promise<AdminUserRecord> {
  await ensureAdminUsersSchema();
  const username = input.username.trim();
  if (!username || username.length > 64) {
    throw new Error("username_invalid");
  }
  if (!input.password || input.password.length < 6) {
    throw new Error("password_too_short");
  }
  const existing = await getAdminByUsername(username);
  if (existing) {
    throw new Error("username_taken");
  }
  const id = uuidv4();
  const passwordHash = await hashPassword(input.password);
  const expiresAt = addMonthsFrom(new Date(), input.months);
  await pool.query(
    `INSERT INTO admin_users
      (id, username, passwordHash, role, status, expiresAt, sessionVersion, createdBy)
     VALUES (?, ?, ?, 'admin', 1, ?, 0, ?)`,
    [id, username, passwordHash, expiresAt, input.createdBy],
  );
  const created = await getAdminById(id);
  if (!created) {
    throw new Error("admin_create_failed");
  }
  return created;
}

export async function renewAdminUser(input: {
  adminId: string;
  months: RenewMonths;
  operatorId: string;
}): Promise<AdminUserRecord> {
  await ensureAdminUsersSchema();
  const [rows] = await pool.query<AdminUserRow[]>(
    "SELECT * FROM admin_users WHERE id = ? LIMIT 1",
    [input.adminId],
  );
  const row = rows[0];
  if (!row) {
    throw new Error("admin_not_found");
  }
  if (row.role === "super") {
    throw new Error("super_no_renew");
  }
  const fromExpiresAt = row.expiresAt;
  const baseMs = Math.max(fromExpiresAt?.getTime() ?? 0, Date.now());
  const toExpiresAt = addMonthsFrom(new Date(baseMs), input.months);
  await pool.query(
    `UPDATE admin_users
     SET expiresAt = ?, status = 1, updatedAt = CURRENT_TIMESTAMP
     WHERE id = ?`,
    [toExpiresAt, input.adminId],
  );
  await pool.query(
    `INSERT INTO admin_renewals
      (id, adminId, months, fromExpiresAt, toExpiresAt, operatorId)
     VALUES (?, ?, ?, ?, ?, ?)`,
    [
      uuidv4(),
      input.adminId,
      input.months,
      fromExpiresAt,
      toExpiresAt,
      input.operatorId,
    ],
  );
  const updated = await getAdminById(input.adminId);
  if (!updated) {
    throw new Error("admin_not_found");
  }
  return updated;
}

export async function setAdminStatus(
  adminId: string,
  status: 0 | 1,
): Promise<AdminUserRecord> {
  await ensureAdminUsersSchema();
  const current = await getAdminById(adminId);
  if (!current) {
    throw new Error("admin_not_found");
  }
  if (current.role === "super" && status === 0) {
    throw new Error("cannot_lock_super");
  }
  await pool.query(
    "UPDATE admin_users SET status = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?",
    [status, adminId],
  );
  const updated = await getAdminById(adminId);
  if (!updated) {
    throw new Error("admin_not_found");
  }
  return updated;
}

export async function updateAdminPassword(
  adminId: string,
  password: string,
): Promise<AdminUserRecord> {
  await ensureAdminUsersSchema();
  if (!password || password.length < 6) {
    throw new Error("password_too_short");
  }
  const current = await getAdminById(adminId);
  if (!current) {
    throw new Error("admin_not_found");
  }
  const passwordHash = await hashPassword(password);
  await pool.query(
    "UPDATE admin_users SET passwordHash = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?",
    [passwordHash, adminId],
  );
  const updated = await getAdminById(adminId);
  if (!updated) {
    throw new Error("admin_not_found");
  }
  return updated;
}

export async function deleteAdminUser(
  adminId: string,
  operatorId: string,
): Promise<void> {
  await ensureAdminUsersSchema();
  const current = await getAdminById(adminId);
  if (!current) {
    throw new Error("admin_not_found");
  }
  if (current.role === "super") {
    throw new Error("cannot_delete_super");
  }
  if (current.id === operatorId) {
    throw new Error("cannot_delete_self");
  }
  await pool.query("DELETE FROM admin_users WHERE id = ?", [adminId]);
}

/** 登录成功：提升 sessionVersion，返回新版本 */
export async function bumpSessionVersion(adminId: string): Promise<number> {
  await ensureAdminUsersSchema();
  await pool.query(
    `UPDATE admin_users
     SET sessionVersion = sessionVersion + 1, updatedAt = CURRENT_TIMESTAMP
     WHERE id = ?`,
    [adminId],
  );
  const [rows] = await pool.query<RowDataPacket[]>(
    "SELECT sessionVersion FROM admin_users WHERE id = ? LIMIT 1",
    [adminId],
  );
  return Number(rows[0]?.sessionVersion ?? 0);
}

export async function assertAdminCanLogin(
  admin: AdminUserRecord,
): Promise<"ok" | "account_locked" | "account_expired"> {
  if (admin.role === "super") {
    if (admin.status !== 1) {
      return "account_locked";
    }
    return "ok";
  }
  if (admin.status !== 1) {
    return "account_locked";
  }
  if (admin.isExpired) {
    return "account_expired";
  }
  return "ok";
}

/** 供鉴权：校验 id + sessionVersion，并检查锁定/过期 */
export async function validateAdminSession(
  adminId: string,
  sessionVersion: number,
): Promise<
  | { ok: true; admin: AdminUserRecord }
  | { ok: false; reason: "unauthorized" | "kicked" | "account_locked" | "account_expired" }
> {
  const admin = await getAdminById(adminId);
  if (!admin) {
    return { ok: false, reason: "unauthorized" };
  }
  if (admin.sessionVersion !== sessionVersion) {
    return { ok: false, reason: "kicked" };
  }
  const loginState = await assertAdminCanLogin(admin);
  if (loginState !== "ok") {
    return { ok: false, reason: loginState };
  }
  return { ok: true, admin };
}
