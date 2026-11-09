/** API 进程示例：在 Manage/api 目录执行 pm2 start ../deploy/pm2.ecosystem.config.cjs */
module.exports = {
  apps: [
    {
      name: "remote-control-api",
      cwd: __dirname + "/../api",
      script: "npm",
      args: "run start",
      env: {
        NODE_ENV: "production",
        HOST: "0.0.0.0",
        PORT: "3000",
      },
      autorestart: true,
      max_restarts: 20,
    },
  ],
};
