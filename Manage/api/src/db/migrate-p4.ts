import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { pool } from "./pool.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export async function runP4Migration(): Promise<void> {
  const sqlPath = path.resolve(__dirname, "../../sql/migrate-p4.sql");
  const sql = fs.readFileSync(sqlPath, "utf8");
  const statements = sql
    .split(";")
    .map((item) => item.trim())
    .filter((item) => item.length > 0 && !item.startsWith("--"));

  for (const statement of statements) {
    await pool.query(statement);
  }
}

runP4Migration()
  .then(async () => {
    await pool.end();
    console.log("[db:migrate-p4] completed");
  })
  .catch(async (error) => {
    console.error("[db:migrate-p4] failed:", error);
    await pool.end();
    process.exit(1);
  });
