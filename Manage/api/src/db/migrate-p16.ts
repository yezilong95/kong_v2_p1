import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { pool } from "./pool.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export async function runP16Migration(): Promise<void> {
  const sqlPath = path.resolve(__dirname, "../../sql/migrate-p16.sql");
  const sql = fs.readFileSync(sqlPath, "utf8");
  const statements = sql
    .split(";")
    .map((item) => item.trim())
    .filter((item) => item.length > 0 && !item.startsWith("--"));

  for (const statement of statements) {
    try {
      await pool.query(statement);
    } catch (error) {
      const code = (error as { code?: string }).code;
      if (code === "ER_DUP_FIELDNAME" || code === "ER_DUP_KEYNAME" || code === "ER_TABLE_EXISTS_ERROR") {
        continue;
      }
      throw error;
    }
  }
}

runP16Migration()
  .then(async () => {
    await pool.end();
    console.log("[db:migrate-p16] completed");
  })
  .catch(async (error) => {
    console.error("[db:migrate-p16] failed:", error);
    await pool.end();
    process.exit(1);
  });
