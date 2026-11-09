# WebRTC TURN 服务器配置

投屏跨网（手机与管理端不在同一 NAT）时，仅 STUN 往往无法打通 ICE，需要 TURN 中继。

## 当前故障结论（外网探测）

手机端 **会启动 WebRTC**（logcat 可见 `jingle_peerconnection_so` / `PeerConnectionFactory`），约 12s 后：

```text
ScreenWebRtcSession: ICE connect timeout after 12000ms
ConnectionService: webrtc screenshot failed: ice_timeout
```

随后降级 JPEG（`WindowManager: captureDisplay`）。  
从外网探测 `47.253.132.67`：**UDP/TCP 3478 均不通**（业务口 `18083` 正常）。  
因此不是「ADB 通道挡了 WebRTC」，而是 **TURN 未对外可达**（coturn 未监听或安全组/防火墙未放行）。

在 ECS 上立刻执行：

```bash
sudo systemctl status coturn --no-pager
sudo ss -ulnp | grep 3478
sudo ss -tlnp | grep 3478
sudo journalctl -u coturn -n 40 --no-pager
# 安全组 + 本机防火墙必须放行：UDP/TCP 3478、UDP 49160-49200
sudo ufw allow 3478/udp; sudo ufw allow 3478/tcp; sudo ufw allow 49160:49200/udp; sudo ufw reload
sudo systemctl restart coturn
```

外网自检（任意电脑）：

```bash
nc -zvu 47.253.132.67 3478
nc -zv 47.253.132.67 3478
```

两路都通后，管理页重新勾选「镜像」，不应再出现 `ice_timeout`。

| 项 | 值 |
|---|---|
| 公网 IP | `47.253.132.67` |
| 内网 IP（阿里云 ECS） | `172.22.31.50` |
| HTTP 入口（业务站，非 TURN） | http://47.253.132.67/ |
| TURN 监听 | `3478`（UDP + TCP） |
| 中继端口段 | UDP `49160`–`49200` |
| 用户名 | `vibelink` |
| 密码 | `RX01sPKcOwxisn71edQn` |
| `external-ip` | `47.253.132.67/172.22.31.50`（公网/内网，缺一不可） |

> TURN URL 不要带 `http://`。正确形态是 `turn:47.253.132.67:3478`。

---

## 1. 在服务器上安装 coturn（不用 Docker）

适用于 Debian / Ubuntu：

```bash
sudo apt update
sudo apt install -y coturn

# 允许开机启动
sudo sed -i 's/^#\?TURNSERVER_ENABLED=.*/TURNSERVER_ENABLED=1/' /etc/default/coturn
```

CentOS / RHEL：

```bash
sudo yum install -y coturn
# 或: sudo dnf install -y coturn
```

---

## 2. 写入 `/etc/turnserver.conf`

先备份，再用下面整段命令**写入文件**（不要把配置行直接粘到 shell 里执行，否则会报 `command not found`）：

```bash
sudo cp -a /etc/turnserver.conf "/etc/turnserver.conf.bak.$(date +%Y%m%d%H%M%S)"

# 先看内网 IP（阿里云常见 172.16.x.x）
ip -4 route get 1.1.1.1 | awk '{print $7; exit}'

sudo tee /etc/turnserver.conf >/dev/null <<'EOF'
listening-port=3478
fingerprint
lt-cred-mech
user=vibelink:RX01sPKcOwxisn71edQn
realm=47.253.132.67
server-name=47.253.132.67
external-ip=47.253.132.67/172.22.31.50
relay-ip=172.22.31.50
listening-ip=172.22.31.50
min-port=49160
max-port=49200
no-multicast-peers
no-cli
EOF

# 确认已写入
grep -E '^(user|external-ip|relay-ip|listening-ip)=' /etc/turnserver.conf
sudo systemctl restart coturn
sudo journalctl -u coturn -n 25 --no-pager
```

说明：

- 阿里云 ECS：**必须**写成 `external-ip=公网IP/内网IP`（本机即 `47.253.132.67/172.22.31.50`）。只写公网 IP 时，中继地址常变成内网，外网手机连不上。
- `relay-ip` / `listening-ip` 固定为 ECS 内网 IP，避免绑到 Docker 网桥（`172.17` / `172.18`）。
- 内网 IP 若变更，用 `ip -4 route get 1.1.1.1 | awk '{print $7; exit}'` 查完后改配置三处。
- 不要同时开 `use-auth-secret` 与上面的静态 `user=`（二选一；本文用静态账号）。

### 排查（仍是 JPEG / 不是 `<video>` 时）

在服务器上**逐条**执行（不要粘贴整段历史日志）：

```bash
# A. 配置是否真的写进文件
grep -E '^(user|external-ip|relay-ip|listening-ip|realm)=' /etc/turnserver.conf

# B. 是否在听 3478（应看到 0.0.0.0 或公网/内网，不要只有异常）
sudo ss -ulnp | grep 3478
sudo ss -tlnp | grep 3478

# C. 启动日志：Default realm / external-ip 不能为空
sudo journalctl -u coturn -n 40 --no-pager

# D. API .env 是否带 TURN（路径按你的部署）
grep '^WEBRTC_TURN' /www/wwwroot/remote_control_baxi/api/.env

# E. API 是否吐出 turn:（按你们鉴权带上 cookie/token）
curl -sS http://127.0.0.1:3000/api/webrtc/ice-servers | head -c 800; echo
```

期望：

| 检查 | 正常 |
|---|---|
| A | 有 `user=vibelink:...`、`external-ip=47.253.132.67/172.22.31.50`、`relay-ip` / `listening-ip` |
| B | `3478` 在听 |
| C | `Default realm: 47.253.132.67`，无刷屏 `Address already in use` |
| D/E | JSON 里有 `turn:47.253.132.67:3478` 和 username |

若 A 不对，重新执行第 2 节整段 `tee`，然后：

```bash
sudo systemctl enable --now coturn
sudo systemctl restart coturn
sudo systemctl status coturn --no-pager
# 再重启 API（pm2/systemd/宝塔里实际跑 remote_control_baxi/api 的那个）
```

---

## 3. 防火墙 / 安全组

在云厂商安全组与本机防火墙放行：

| 协议 | 端口 | 用途 |
|---|---|---|
| UDP | 3478 | TURN 分配 |
| TCP | 3478 | TURN（TCP 回退） |
| UDP | 49160–49200 | 媒体中继 |

服务器终端`ufw` 示例：

```bash
sudo ufw allow 3478/udp
sudo ufw allow 3478/tcp
sudo ufw allow 49160:49200/udp
sudo ufw reload
```

---

## 4. Manage API（`.env`）

已在 `Manage/api/.env` 配置为：

```env
WEBRTC_TURN_URLS=turn:47.253.132.67:3478?transport=udp,turn:47.253.132.67:3478?transport=tcp
WEBRTC_TURN_USERNAME=vibelink
WEBRTC_TURN_CREDENTIAL=RX01sPKcOwxisn71edQn
```

修改后**重启 API** 进程，配置才会生效。

逻辑位置：`Manage/api/src/config.ts` → `getIceServers()`；订阅投屏时下发给设备，管理端通过 `GET /api/webrtc/ice-servers` 拉取同一份 ICE 列表。

---

## 5. 验证

在 API 所在机器：

```bash
# 应能看到 turn:47.253.132.67 ... 以及 username
curl -s http://127.0.0.1:3000/api/webrtc/ice-servers
```

（若该接口需要管理员登录，带上你们现有的 Authorization。）

本机检查 coturn 是否在听：

```bash
sudo ss -ulnp | grep 3478
sudo ss -tlnp | grep 3478
```

跨网开「镜像」投屏：WebRTC 应能连上；若仍失败，看设备/API 是否收到 `ice_timeout` 后 JPEG 降级，并核对安全组中继端口是否放行。

---

## 6. 改密码

1. 改 `/etc/turnserver.conf` 里的 `user=vibelink:新密码`
2. 同步改 `Manage/api/.env` 的 `WEBRTC_TURN_CREDENTIAL`
3. `sudo systemctl restart coturn`，并重启 API
4. 更新本文档中的密码记录（如有）
