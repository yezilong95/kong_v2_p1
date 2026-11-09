import type { RowDataPacket, ResultSetHeader } from "mysql2";
import { pool } from "../db/pool.js";

interface FinanceAppRow extends RowDataPacket {
  package_name: string;
  label: string | null;
  created_at: Date;
}

export interface FinanceAppPackage {
  packageName: string;
  label: string;
  createdAt: Date;
}

function mapRow(row: FinanceAppRow): FinanceAppPackage {
  return {
    packageName: row.package_name,
    label: (row.label ?? "").trim(),
    createdAt: row.created_at,
  };
}

export async function listFinanceApps(): Promise<FinanceAppPackage[]> {
  const [rows] = await pool.query<FinanceAppRow[]>(
    "SELECT * FROM finance_app_packages ORDER BY created_at DESC, package_name ASC",
  );
  return rows.map(mapRow);
}

export async function upsertFinanceApp(input: {
  packageName: string;
  label?: string;
}): Promise<FinanceAppPackage> {
  const packageName = input.packageName.trim();
  if (!packageName) {
    throw new Error("packageName_required");
  }
  const label = (input.label ?? "").trim().slice(0, 255);
  await pool.query<ResultSetHeader>(
    `INSERT INTO finance_app_packages (package_name, label, created_at)
     VALUES (?, ?, CURRENT_TIMESTAMP)
     ON DUPLICATE KEY UPDATE
       label = IF(VALUES(label) = '', label, VALUES(label))`,
    [packageName, label],
  );
  const [rows] = await pool.query<FinanceAppRow[]>(
    "SELECT * FROM finance_app_packages WHERE package_name = ? LIMIT 1",
    [packageName],
  );
  if (!rows[0]) {
    throw new Error("finance_app_upsert_failed");
  }
  return mapRow(rows[0]);
}

export async function deleteFinanceApp(packageName: string): Promise<void> {
  const pkg = packageName.trim();
  if (!pkg) {
    throw new Error("packageName_required");
  }
  await pool.query("DELETE FROM finance_app_packages WHERE package_name = ?", [pkg]);
}

export async function batchUpsertFinanceApps(
  apps: Array<{ packageName?: string; label?: string }>,
): Promise<FinanceAppPackage[]> {
  const results: FinanceAppPackage[] = [];
  const seen = new Set<string>();
  for (const item of apps) {
    const packageName = String(item.packageName ?? "").trim();
    if (!packageName || seen.has(packageName)) {
      continue;
    }
    seen.add(packageName);
    results.push(
      await upsertFinanceApp({
        packageName,
        label: item.label,
      }),
    );
  }
  return results;
}
