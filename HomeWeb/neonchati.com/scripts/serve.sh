#!/bin/sh
# 静态文件服务启动脚本（native-static 模板）
# 端口必须从环境变量 DEPLOY_RUN_PORT 读取，禁止硬编码
SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
cd "$SCRIPT_DIR/.." || exit 1
PORT="${DEPLOY_RUN_PORT:-5000}"
echo "[serve.sh] starting http.server on port ${PORT}, dir: $(pwd)"
exec python3 -m http.server "$PORT" --bind 0.0.0.0
