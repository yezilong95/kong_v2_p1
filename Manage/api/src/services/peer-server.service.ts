import type { RowDataPacket, ResultSetHeader } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

export interface PeerServerRecord {
  id: string;
  name: string;
  apiBaseUrl: string;
  wsBaseUrl: string;
  wsScreenBaseUrl: string;
  transferSecret: string;
  enabled: 0 | 1;
  createdAt: string;
  updatedAt: string;
}

interface PeerServerRow extends RowDataPacket {
  id: string;
  name: string;
  apiBaseUrl: string;
  wsBaseUrl: string;
  wsScreenBaseUrl: string;
  transferSecret: string;
  enabled: number;
  createdAt: Date;
  updatedAt: Date;
}

function mapPeer(row: PeerServerRow): PeerServerRecord {
  return {
    id: row.id,
    name: row.name,
    apiBaseUrl: row.apiBaseUrl,
    wsBaseUrl: row.wsBaseUrl,
    wsScreenBaseUrl: row.wsScreenBaseUrl,
    transferSecret: row.transferSecret,
    enabled: row.enabled === 1 ? 1 : 0,
    createdAt: row.createdAt.toISOString(),
    updatedAt: row.updatedAt.toISOString(),
  };
}

function requireUrl(value: unknown, field: string): string {
  const text = String(value ?? "").trim().replace(/\/+$/, "");
  if (!text) {
    throw new Error(`${field}_required`);
  }
  try {
    const parsed = new URL(text);
    if (!parsed.hostname) {
      throw new Error(`${field}_invalid`);
    }
  } catch (error) {
    if (error instanceof Error && error.message.endsWith("_required")) {
      throw error;
    }
    throw new Error(`${field}_invalid`);
  }
  return text;
}

export async function listPeerServers(): Promise<PeerServerRecord[]> {
  const [rows] = await pool.query<PeerServerRow[]>(
    "SELECT * FROM peer_servers ORDER BY createdAt ASC",
  );
  return rows.map(mapPeer);
}

export async function getPeerServerById(id: string): Promise<PeerServerRecord | null> {
  const [rows] = await pool.query<PeerServerRow[]>(
    "SELECT * FROM peer_servers WHERE id = ? LIMIT 1",
    [id],
  );
  return rows[0] ? mapPeer(rows[0]) : null;
}

export async function createPeerServer(input: {
  name?: unknown;
  apiBaseUrl?: unknown;
  wsBaseUrl?: unknown;
  wsScreenBaseUrl?: unknown;
  transferSecret?: unknown;
  enabled?: unknown;
}): Promise<PeerServerRecord> {
  const name = String(input.name ?? "").trim();
  if (!name) {
    throw new Error("peer_name_required");
  }
  const transferSecret = String(input.transferSecret ?? "").trim();
  if (!transferSecret) {
    throw new Error("transfer_secret_required");
  }
  const id = uuidv4();
  const enabled = String(input.enabled ?? "1").trim() === "0" ? 0 : 1;
  await pool.query(
    `INSERT INTO peer_servers
      (id, name, apiBaseUrl, wsBaseUrl, wsScreenBaseUrl, transferSecret, enabled)
     VALUES (?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      name.slice(0, 64),
      requireUrl(input.apiBaseUrl, "apiBaseUrl"),
      requireUrl(input.wsBaseUrl, "wsBaseUrl"),
      requireUrl(input.wsScreenBaseUrl, "wsScreenBaseUrl"),
      transferSecret.slice(0, 128),
      enabled,
    ],
  );
  const created = await getPeerServerById(id);
  if (!created) {
    throw new Error("peer_create_failed");
  }
  return created;
}

export async function updatePeerServer(
  id: string,
  input: {
    name?: unknown;
    apiBaseUrl?: unknown;
    wsBaseUrl?: unknown;
    wsScreenBaseUrl?: unknown;
    transferSecret?: unknown;
    enabled?: unknown;
  },
): Promise<PeerServerRecord> {
  const existing = await getPeerServerById(id);
  if (!existing) {
    throw new Error("peer_not_found");
  }
  const name = input.name == null ? existing.name : String(input.name).trim();
  if (!name) {
    throw new Error("peer_name_required");
  }
  const transferSecret =
    input.transferSecret == null
      ? existing.transferSecret
      : String(input.transferSecret).trim();
  if (!transferSecret) {
    throw new Error("transfer_secret_required");
  }
  const enabled =
    input.enabled == null
      ? existing.enabled
      : String(input.enabled).trim() === "0"
        ? 0
        : 1;
  await pool.query(
    `UPDATE peer_servers SET
      name = ?,
      apiBaseUrl = ?,
      wsBaseUrl = ?,
      wsScreenBaseUrl = ?,
      transferSecret = ?,
      enabled = ?
     WHERE id = ?`,
    [
      name.slice(0, 64),
      requireUrl(input.apiBaseUrl ?? existing.apiBaseUrl, "apiBaseUrl"),
      requireUrl(input.wsBaseUrl ?? existing.wsBaseUrl, "wsBaseUrl"),
      requireUrl(input.wsScreenBaseUrl ?? existing.wsScreenBaseUrl, "wsScreenBaseUrl"),
      transferSecret.slice(0, 128),
      enabled,
      id,
    ],
  );
  const updated = await getPeerServerById(id);
  if (!updated) {
    throw new Error("peer_update_failed");
  }
  return updated;
}

export async function deletePeerServer(id: string): Promise<void> {
  const [result] = await pool.query<ResultSetHeader>("DELETE FROM peer_servers WHERE id = ?", [id]);
  if (result.affectedRows < 1) {
    throw new Error("peer_not_found");
  }
}
