import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { pool } from "./pool.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export async function runP10Migration(): Promise<void> {
  const sqlPath = path.resolve(__dirname, "../../sql/migrate-p10.sql");
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
      // 幂等：列/索引/外键已存在时跳过
      if (code === "ER_DUP_FIELDNAME" || code === "ER_DUP_KEYNAME" || code === "ER_FK_DUP_NAME") {
        continue;
      }
      throw error;
    }
  }
}

runP10Migration()
  .then(async () => {
    await pool.end();
    console.log("[db:migrate-p10] completed");
  })
  .catch(async (error) => {
    console.error("[db:migrate-p10] failed:", error);
    await pool.end();
    process.exit(1);
  });
