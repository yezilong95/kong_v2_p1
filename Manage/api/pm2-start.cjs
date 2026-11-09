/**
 * 宝塔 PM2「启动文件」指向本文件。
 * 运行目录必须是 Manage/api（本文件所在目录）。
 *
 * 等价于: npm run start → tsx src/index.ts
 */
const { spawn } = require("child_process");
const path = require("path");
const fs = require("fs");

const cwd = __dirname;
const tsxCli = path.join(cwd, "node_modules", "tsx", "dist", "cli.mjs");
const entry = path.join(cwd, "src", "index.ts");

if (!fs.existsSync(tsxCli)) {
  console.error(
    "[pm2-start] 找不到 tsx，请先在 Manage/api 执行 npm ci（勿用 --omit=dev）"
  );
  process.exit(1);
}
if (!fs.existsSync(entry)) {
  console.error("[pm2-start] 找不到入口:", entry);
  process.exit(1);
}

const child = spawn(process.execPath, [tsxCli, entry], {
  cwd,
  stdio: "inherit",
  env: process.env,
});

child.on("exit", (code, signal) => {
  if (signal) {
    process.kill(process.pid, signal);
    return;
  }
  process.exit(code == null ? 1 : code);
});

process.on("SIGINT", () => child.kill("SIGINT"));
process.on("SIGTERM", () => child.kill("SIGTERM"));
