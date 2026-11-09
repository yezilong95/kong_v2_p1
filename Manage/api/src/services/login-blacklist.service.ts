import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

const FAIL_LIMIT = 2;

interface FailRow extends RowDataPacket {
  failCount: number;
}

interface BlacklistHitRow extends RowDataPacket {
  id: string;
}

let schemaReady = false;

async function ensureSchema(): Promise<void> {
  if (schemaReady) {
    return;
  }
  await pool.query(`
    CREATE TABLE IF NOT EXISTS login_blacklist (
      id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
      username VARCHAR(64) NOT NULL COMMENT '登录名',
      ip VARCHAR(64) NULL COMMENT '失败/拉黑 IP，可空',
      failCount INT NOT NULL DEFAULT 0 COMMENT '连续失败次数，>=2 视为拉黑',
      createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次失败时间',
      UNIQUE KEY uk_login_blacklist_username (username),
      KEY idx_login_blacklist_ip (ip)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  `);
  await pool.query("DROP TABLE IF EXISTS login_fail_counters");
  schemaReady = true;
}

export async function isLoginBlacklisted(username: string, ip: string | null): Promise<boolean> {
  await ensureSchema();
  const name = username.trim();
  if (!name) {
    return false;
  }
  if (ip) {
    const [rows] = await pool.query<BlacklistHitRow[]>(
      `SELECT id FROM login_blacklist
       WHERE failCount >= ?
         AND (username = ? OR ip = ?)
       LIMIT 1`,
      [FAIL_LIMIT, name, ip],
    );
    return rows.length > 0;
  }
  const [rows] = await pool.query<BlacklistHitRow[]>(
    "SELECT id FROM login_blacklist WHERE failCount >= ? AND username = ? LIMIT 1",
    [FAIL_LIMIT, name],
  );
  return rows.length > 0;
}

export async function clearLoginFailCounter(username: string): Promise<void> {
  await ensureSchema();
  const name = username.trim();
  if (!name) {
    return;
  }
  await pool.query("DELETE FROM login_blacklist WHERE username = ? AND failCount < ?", [
    name,
    FAIL_LIMIT,
  ]);
}

/** 仅对真实存在的账号计次。failCount>=2 即拉黑。返回是否刚达到阈值。 */
export async function recordLoginFailure(username: string, ip: string | null): Promise<boolean> {
  await ensureSchema();
  const name = username.trim();
  if (!name) {
    return false;
  }

  await pool.query(
    `INSERT INTO login_blacklist (id, username, ip, failCount)
     VALUES (?, ?, ?, 1)
     ON DUPLICATE KEY UPDATE
       failCount = failCount + 1,
       ip = VALUES(ip)`,
    [uuidv4(), name, ip],
  );

  const [rows] = await pool.query<FailRow[]>(
    "SELECT failCount FROM login_blacklist WHERE username = ? LIMIT 1",
    [name],
  );
  return Number(rows[0]?.failCount ?? 0) >= FAIL_LIMIT;
}
