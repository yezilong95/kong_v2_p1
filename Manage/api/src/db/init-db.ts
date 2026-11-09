import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import mysql from "mysql2/promise";
import { config } from "../config.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export async function initDatabase(): Promise<void> {
  const sqlPath = path.resolve(__dirname, "../../sql/init.sql");
  const sql = fs.readFileSync(sqlPath, "utf8");
  const statements = sql
    .split(";")
    .map((item) => item.trim())
    .filter((item) => item.length > 0 && !item.startsWith("--"));

  const connection = await mysql.createConnection({
    host: config.db.host,
    port: config.db.port,
    user: config.db.user,
    password: config.db.password,
    multipleStatements: true,
  });

  try {
    for (const statement of statements) {
      await connection.query(statement);
    }
  } finally {
    await connection.end();
  }
}

initDatabase().catch((error) => {
  console.error("[db:init] failed:", error);
  process.exit(1);
});
