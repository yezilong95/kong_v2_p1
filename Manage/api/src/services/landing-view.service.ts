import type { ResultSetHeader, RowDataPacket } from "mysql2";
import { pool } from "../db/pool.js";
import { sqlTodayInBusinessTz } from "../utils/business-date.js";

export const DEFAULT_LANDING_PAGE_KEY = "miluHome";

export interface LandingViewInput {
  pageKey?: string | null;
  clientIp?: string | null;
  userAgent?: string | null;
}

export interface LandingPageStats {
  pageKey: string;
  totalViews: number;
}

function normalizePageKey(raw: string | null | undefined): string {
  const key = (raw ?? "").trim().slice(0, 64);
  return key || DEFAULT_LANDING_PAGE_KEY;
}

export async function recordLandingPageView(input: LandingViewInput): Promise<void> {
  const pageKey = normalizePageKey(input.pageKey);
  const clientIp = input.clientIp?.trim().slice(0, 64) || null;
  const userAgent = input.userAgent?.trim().slice(0, 512) || null;

  await pool.query<ResultSetHeader>(
    `INSERT INTO landing_page_views (page_key, client_ip, user_agent)
     VALUES (?, ?, ?)`,
    [pageKey, clientIp, userAgent],
  );
}

export async function getLandingPageStats(pageKeyRaw?: string | null): Promise<LandingPageStats> {
  const pageKey = normalizePageKey(pageKeyRaw);
  const [rows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS totalViews
     FROM landing_page_views
     WHERE page_key = ?`,
    [pageKey],
  );
  const totalViews = Number(rows[0]?.totalViews ?? 0);
  return {
    pageKey,
    totalViews: Number.isFinite(totalViews) ? totalViews : 0,
  };
}

export async function countLandingPageViewsToday(pageKeyRaw?: string | null): Promise<number> {
  const pageKey = normalizePageKey(pageKeyRaw);
  const [rows] = await pool.query<RowDataPacket[]>(
    `SELECT COUNT(*) AS todayViews
     FROM landing_page_views
     WHERE page_key = ?
       AND ${sqlTodayInBusinessTz("created_at")}`,
    [pageKey],
  );
  const today = Number(rows[0]?.todayViews ?? 0);
  return Number.isFinite(today) ? today : 0;
}
