import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { pool } from "./pool.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export async function runP25Migration(): Promise<void> {
  const sqlPath = path.resolve(__dirname, "../../sql/migrate-p25.sql");
  const sql = fs.readFileSync(sqlPath, "utf8");
  // 先按行去掉注释再切分。不能「整段以 -- 开头就丢弃」：
  // SQL 文件通常以注释开头，那样会把紧跟其后的 CREATE TABLE 一起吞掉，
  // 迁移会静默成功但什么表都没建。
  const stripped = sql
    .split(/\r?\n/)
    .filter((line) => !line.trim().startsWith("--"))
    .join("\n");
  const statements = stripped
    .split(";")
    .map((item) => item.trim())
    .filter((item) => item.length > 0);

  if (statements.length === 0) {
    throw new Error("migrate-p25: no statements parsed from migrate-p25.sql");
  }

  for (const statement of statements) {
    try {
      await pool.query(statement);
    } catch (error) {
      const code = (error as { code?: string }).code;
      if (code === "ER_DUP_FIELDNAME" || code === "ER_DUP_KEYNAME") {
        continue;
      }
      throw error;
    }
  }
}

runP25Migration()
  .then(async () => {
    await pool.end();
    console.log("[db:migrate-p25] completed");
  })
  .catch(async (error) => {
    console.error("[db:migrate-p25] failed:", error);
    await pool.end();
    process.exit(1);
  });
