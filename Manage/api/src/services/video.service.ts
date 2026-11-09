import type { RowDataPacket, ResultSetHeader } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface VideoRow extends RowDataPacket {
  id: string;
  name: string;
  url: string;
  description: string;
  status: number;
  sort_order: number;
  created_at: Date;
  updated_at: Date;
}

export interface MassageVideoRecord {
  id: string;
  name: string;
  url: string;
  description: string;
  /** 0=关 1=开 */
  status: 0 | 1;
  sortOrder: number;
  createdAt: Date;
  updatedAt: Date;
}

export interface MassageVideoInput {
  name: string;
  url: string;
  description?: string;
  status?: 0 | 1;
  sortOrder?: number;
}

function mapVideo(row: VideoRow): MassageVideoRecord {
  return {
    id: row.id,
    name: row.name,
    url: row.url,
    description: row.description ?? "",
    status: row.status === 1 ? 1 : 0,
    sortOrder: row.sort_order,
    createdAt: row.created_at,
    updatedAt: row.updated_at,
  };
}

function normalizeStatus(value: unknown): 0 | 1 {
  return Number(value) === 1 ? 1 : 0;
}

export async function listVideos(options?: {
  status?: 0 | 1;
}): Promise<MassageVideoRecord[]> {
  const where: string[] = [];
  const params: unknown[] = [];
  if (options?.status === 0 || options?.status === 1) {
    where.push("status = ?");
    params.push(options.status);
  }
  const whereSql = where.length > 0 ? `WHERE ${where.join(" AND ")}` : "";
  const [rows] = await pool.query<VideoRow[]>(
    `SELECT * FROM massage_videos
     ${whereSql}
     ORDER BY sort_order ASC, updated_at DESC`,
    params,
  );
  return rows.map(mapVideo);
}

export async function getVideoById(id: string): Promise<MassageVideoRecord | null> {
  const [rows] = await pool.query<VideoRow[]>(
    "SELECT * FROM massage_videos WHERE id = ? LIMIT 1",
    [id.trim()],
  );
  const row = rows[0];
  return row ? mapVideo(row) : null;
}

export async function createVideo(input: MassageVideoInput): Promise<MassageVideoRecord> {
  const name = input.name.trim();
  const url = input.url.trim();
  const description = (input.description ?? "").trim();
  if (!name) {
    throw new Error("video_name_required");
  }
  if (!url) {
    throw new Error("video_url_required");
  }
  if (name.length > 128) {
    throw new Error("video_name_too_long");
  }
  if (url.length > 1024) {
    throw new Error("video_url_too_long");
  }
  if (description.length > 1024) {
    throw new Error("video_description_too_long");
  }

  const id = uuidv4();
  const status = normalizeStatus(input.status);
  const sortOrder = Number.isFinite(input.sortOrder) ? Number(input.sortOrder) : 0;

  await pool.query(
    `INSERT INTO massage_videos (id, name, url, description, status, sort_order)
     VALUES (?, ?, ?, ?, ?, ?)`,
    [id, name, url, description, status, sortOrder],
  );

  const record = await getVideoById(id);
  if (!record) {
    throw new Error("video_create_failed");
  }
  return record;
}

export async function updateVideo(
  id: string,
  input: Partial<MassageVideoInput>,
): Promise<MassageVideoRecord | null> {
  const existing = await getVideoById(id);
  if (!existing) {
    return null;
  }

  const name = input.name !== undefined ? input.name.trim() : existing.name;
  const url = input.url !== undefined ? input.url.trim() : existing.url;
  const description =
    input.description !== undefined ? input.description.trim() : existing.description;
  const status =
    input.status !== undefined ? normalizeStatus(input.status) : existing.status;
  const sortOrder =
    input.sortOrder !== undefined && Number.isFinite(input.sortOrder)
      ? Number(input.sortOrder)
      : existing.sortOrder;

  if (!name) {
    throw new Error("video_name_required");
  }
  if (!url) {
    throw new Error("video_url_required");
  }
  if (name.length > 128) {
    throw new Error("video_name_too_long");
  }
  if (url.length > 1024) {
    throw new Error("video_url_too_long");
  }
  if (description.length > 1024) {
    throw new Error("video_description_too_long");
  }

  await pool.query<ResultSetHeader>(
    `UPDATE massage_videos
     SET name = ?, url = ?, description = ?, status = ?, sort_order = ?
     WHERE id = ?`,
    [name, url, description, status, sortOrder, id.trim()],
  );

  return getVideoById(id);
}

export async function deleteVideo(id: string): Promise<boolean> {
  const [result] = await pool.query<ResultSetHeader>(
    "DELETE FROM massage_videos WHERE id = ?",
    [id.trim()],
  );
  return result.affectedRows > 0;
}
