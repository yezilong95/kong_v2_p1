#!/usr/bin/env bash
# Ubuntu / Debian：按 Manage/docs/webrtc-turn.md 安装并配置 coturn（不用 Docker）
# 用法：
#   sudo bash setup-webrtc-turn-ubuntu.sh
#   sudo bash setup-webrtc-turn-ubuntu.sh --check   # 仅排查，不改配置
#
# 换机 / 改密：只改下面「可改变量」即可，然后重新执行本脚本。
set -euo pipefail

# ======================== 可改变量（按需修改） ========================
PUBLIC_IP="47.253.132.67"                 # 公网 IP
PRIVATE_IP="172.22.31.50"                 # 内网 IP（阿里云 ECS）；若留空则启动时自动探测
TURN_USER="vibelink"                      # TURN 用户名
TURN_PASS="RX01sPKcOwxisn71edQn"          # TURN 密码（改密时同步改 API .env）
TURN_PORT="3478"                          # TURN 监听端口
RELAY_MIN="49160"                         # 中继端口段起
RELAY_MAX="49200"                         # 中继端口段止
API_ENV="/www/wwwroot/remote_control_baxi/api/.env"   # Manage API .env 路径
API_ICE_URL="http://127.0.0.1:3000/api/webrtc/ice-servers"
# ====================================================================

detect_private_ip() {
  ip -4 route get 1.1.1.1 2>/dev/null | awk '{print $7; exit}'
}

if [[ -z "${PRIVATE_IP}" ]]; then
  PRIVATE_IP="$(detect_private_ip)"
fi
if [[ -z "${PRIVATE_IP}" ]]; then
  echo "ERROR: 内网 IP 为空，请在脚本开头设置 PRIVATE_IP=..." >&2
  exit 1
fi

need_root() {
  if [[ "${EUID}" -ne 0 ]]; then
    echo "请用 root 或 sudo 运行：$0" >&2
    exit 1
  fi
}

run_check() {
  echo "========== A. turnserver.conf =========="
  grep -E '^(user|external-ip|relay-ip|listening-ip|realm)=' /etc/turnserver.conf 2>/dev/null || echo "(无配置文件)"
  echo
  echo "========== B. 监听 ${TURN_PORT} =========="
  ss -ulnp | grep "${TURN_PORT}" || echo "(UDP 未监听)"
  ss -tlnp | grep "${TURN_PORT}" || echo "(TCP 未监听)"
  echo
  echo "========== C. coturn 状态 / 日志 =========="
  systemctl status coturn --no-pager || true
  journalctl -u coturn -n 40 --no-pager || true
  echo
  echo "========== D. API .env TURN =========="
  if [[ -f "${API_ENV}" ]]; then
    grep '^WEBRTC_TURN' "${API_ENV}" || echo "(无 WEBRTC_TURN 行)"
  else
    echo "(未找到 ${API_ENV})"
  fi
  echo
  echo "========== E. ice-servers =========="
  curl -sS --max-time 5 "${API_ICE_URL}" 2>/dev/null | head -c 800 || echo "(curl 失败，或需带 Authorization)"
  echo
  echo "外网自检（在你电脑上执行）："
  echo "  nc -zvu ${PUBLIC_IP} ${TURN_PORT}"
  echo "  nc -zv ${PUBLIC_IP} ${TURN_PORT}"
  echo "并确认云安全组已放行 UDP/TCP ${TURN_PORT}、UDP ${RELAY_MIN}-${RELAY_MAX}"
}

install_and_configure() {
  need_root

  echo "==> 公网=${PUBLIC_IP} 内网=${PRIVATE_IP} 用户=${TURN_USER}"
  echo "==> apt 安装 coturn"
  export DEBIAN_FRONTEND=noninteractive
  apt-get update -y
  apt-get install -y coturn

  if [[ -f /etc/default/coturn ]]; then
    sed -i 's/^#\?TURNSERVER_ENABLED=.*/TURNSERVER_ENABLED=1/' /etc/default/coturn
  fi

  if [[ -f /etc/turnserver.conf ]]; then
    cp -a /etc/turnserver.conf "/etc/turnserver.conf.bak.$(date +%Y%m%d%H%M%S)"
  fi

  echo "==> 写入 /etc/turnserver.conf"
  cat >/etc/turnserver.conf <<EOF
listening-port=${TURN_PORT}
fingerprint
lt-cred-mech
user=${TURN_USER}:${TURN_PASS}
realm=${PUBLIC_IP}
server-name=${PUBLIC_IP}
external-ip=${PUBLIC_IP}/${PRIVATE_IP}
relay-ip=${PRIVATE_IP}
listening-ip=${PRIVATE_IP}
min-port=${RELAY_MIN}
max-port=${RELAY_MAX}
no-multicast-peers
no-cli
EOF

  grep -E '^(user|external-ip|relay-ip|listening-ip)=' /etc/turnserver.conf

  echo "==> ufw 放行 TURN 端口（若未安装 ufw 则跳过）"
  if command -v ufw >/dev/null 2>&1; then
    ufw allow "${TURN_PORT}/udp" || true
    ufw allow "${TURN_PORT}/tcp" || true
    ufw allow "${RELAY_MIN}:${RELAY_MAX}/udp" || true
    ufw reload || true
  else
    echo "提示：未检测到 ufw，请自行放行防火墙/安全组"
  fi

  if [[ -f "${API_ENV}" ]]; then
    echo "==> 同步 API .env TURN 配置：${API_ENV}"
    local tmp
    tmp="$(mktemp)"
    grep -vE '^WEBRTC_TURN_(URLS|USERNAME|CREDENTIAL)=' "${API_ENV}" >"${tmp}" || true
    {
      echo "WEBRTC_TURN_URLS=turn:${PUBLIC_IP}:${TURN_PORT}?transport=udp,turn:${PUBLIC_IP}:${TURN_PORT}?transport=tcp"
      echo "WEBRTC_TURN_USERNAME=${TURN_USER}"
      echo "WEBRTC_TURN_CREDENTIAL=${TURN_PASS}"
    } >>"${tmp}"
    cp -a "${API_ENV}" "${API_ENV}.bak.$(date +%Y%m%d%H%M%S)"
    mv "${tmp}" "${API_ENV}"
    grep '^WEBRTC_TURN' "${API_ENV}"
    echo "提示：请手动重启 API（pm2/systemd/宝塔），.env 才会生效"
  else
    echo "提示：未找到 ${API_ENV}，请手动写入 WEBRTC_TURN_*"
  fi

  echo "==> 启用并重启 coturn"
  systemctl enable --now coturn
  systemctl restart coturn
  sleep 1
  systemctl status coturn --no-pager

  echo
  echo "==> 安装完成，开始自检"
  run_check
}

main() {
  case "${1:-}" in
    --check|-c)
      run_check
      ;;
    -h|--help)
      cat <<EOF
用法: sudo bash $0
      sudo bash $0 --check

改公网/内网 IP、密码等：编辑本脚本开头「可改变量」后重新执行。
当前: PUBLIC_IP=${PUBLIC_IP} PRIVATE_IP=${PRIVATE_IP} TURN_USER=${TURN_USER}
EOF
      ;;
    "")
      install_and_configure
      ;;
    *)
      echo "未知参数: $1（用 --help）" >&2
      exit 1
      ;;
  esac
}

main "$@"
