import type { ResultSetHeader, RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

export interface FakePageWebLink {
  id: string;
  name: string;
  url: string;
  createdAt: Date;
}

interface WebLinkRow extends RowDataPacket {
  id: string;
  name: string;
  url: string;
  createdAt: Date;
}

let schemaReady = false;

async function ensureSchema(): Promise<void> {
  if (schemaReady) {
    return;
  }
  await pool.query(`
    CREATE TABLE IF NOT EXISTS fake_page_web_links (
      id VARCHAR(36) NOT NULL PRIMARY KEY,
      name VARCHAR(128) NOT NULL,
      url VARCHAR(512) NOT NULL,
      createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
      UNIQUE KEY uk_fake_page_web_url (url(191)),
      KEY idx_fake_page_web_created (createdAt)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  `);
  schemaReady = true;
}

function mapRow(row: WebLinkRow): FakePageWebLink {
  return {
    id: row.id,
    name: row.name,
    url: row.url,
    createdAt: row.createdAt,
  };
}

function isHttpsUrl(value: string): boolean {
  return /^https:\/\/.+/i.test(value.trim());
}

export async function listFakePageWebLinks(): Promise<FakePageWebLink[]> {
  await ensureSchema();
  const [rows] = await pool.query<WebLinkRow[]>(
    "SELECT * FROM fake_page_web_links ORDER BY createdAt DESC, name ASC",
  );
  return rows.map(mapRow);
}

export async function createFakePageWebLink(input: {
  name: string;
  url: string;
}): Promise<FakePageWebLink> {
  await ensureSchema();
  const name = input.name.trim().slice(0, 128);
  const url = input.url.trim().slice(0, 512);
  if (!name) {
    throw new Error("name_required");
  }
  if (!url) {
    throw new Error("url_required");
  }
  if (!isHttpsUrl(url)) {
    throw new Error("url_must_be_https");
  }
  const id = uuidv4();
  try {
    await pool.query<ResultSetHeader>(
      `INSERT INTO fake_page_web_links (id, name, url, createdAt)
       VALUES (?, ?, ?, CURRENT_TIMESTAMP)`,
      [id, name, url],
    );
  } catch (error) {
    const code = (error as { code?: string }).code;
    if (code === "ER_DUP_ENTRY") {
      throw new Error("url_already_exists");
    }
    throw error;
  }
  const [rows] = await pool.query<WebLinkRow[]>(
    "SELECT * FROM fake_page_web_links WHERE id = ? LIMIT 1",
    [id],
  );
  if (!rows[0]) {
    throw new Error("web_link_create_failed");
  }
  return mapRow(rows[0]);
}

export async function deleteFakePageWebLink(id: string): Promise<void> {
  await ensureSchema();
  const linkId = id.trim();
  if (!linkId) {
    throw new Error("id_required");
  }
  await pool.query("DELETE FROM fake_page_web_links WHERE id = ?", [linkId]);
}
