# 无线 ADB（跨局域网 WS 中继）设计

**日期:** 2026-09-15  
**状态:** 已批准并实施中（S1–S4 代码已落地，待真机联调）  
**决策:** 方案 ① ADB over WS 中继（已批准）  
**硬约束:** 现有远控 / 投屏 / 命令 / 黑洞 VPN / Guard 等功能不得回归

---

## 1. 背景与目标

### 目标

运维在 Manage Web 上：

1. 点「开发者」→ 手机打开开发者选项页  
2. 点「自动配送 ADB」→ 自动开启无线调试、刮配对信息、经中继完成 `adb pair` / `adb connect`  
3. **ADB 客户端跑在 Manage 服务器本机**（与 Web 后台同机）

### 网络前提（已确认）

- Manage 机器与手机 **不在同一局域网**  
- 手机已能出站连接 Manage（现有 `/ws/device` 远控）  
- **不做** B 包真实 VPN；保留现有黑洞 VPN 不动  
- Android 11+（系统无线调试配对）

### 非目标（一期）

- 真 VPN / WireGuard / OpenVPN  
- 公网 FRP/ngrok  
- Android 10 及以下 `tcpip 5555` 方案  
- 同步改 `Android1/` 备份（主工程为 `Android/`）  
- 改造现有 `/ws/device` 或 `/ws/device/screen` 的消息格式

---

## 2. 为什么必须独立通道（回归防护核心）

现有 `/ws/device` 在服务端把 **每条 message 当 UTF-8 JSON** 解析；解析失败回 `invalid_json`。  
投屏通道 `/ws/device/screen` 同样是独立 JSON 命令通道。

若把 ADB 二进制塞进控制通道：

- 会触发 `invalid_json` / 打爆日志  
- 可能干扰心跳、命令排队、在线状态  
- 与投屏抢同一连接的背压行为不可控

**因此：ADB 中继必须走全新独立 WebSocket，例如 `/ws/device/adb`。**  
控制通道、投屏通道的代码路径默认 **零改动**（仅允许「注册新路由 / connectionManager 增字段」这类加法）。

---

## 3. 架构

```
[Manage Web]
    │  POST /api/command/send          （开发者按钮）
    │  POST /api/adb/pair              （自动配送，编排）
    ▼
[Manage API]
    │  WS /ws/device          —— 现有命令（不变）
    │  WS /ws/device/screen   —— 现有投屏（不变）
    │  WS /ws/device/adb      —— 新增：二进制中继
    │  本机 TCP listen 127.0.0.1:Lpair / Lconn
    │  spawn: adb pair 127.0.0.1:Lpair
    │         adb connect 127.0.0.1:Lconn
    ▼
[B: Android]
    │  DeviceWebSocketClient       —— 不变
    │  DeviceScreenWebSocketClient —— 不变
    │  DeviceAdbRelayClient        —— 新增（按需连接，默认不连）
    │  本地 TCP → 无线调试 IP:pairPort / IP:connectPort
    │  AppListProtectGuard         —— 仅「ADB 会话 flag」旁路，默认行为不变
    │  BlackholeVpnService         —— 不改
```

### 端到端流水线（自动配送）

1. Web → `POST /api/adb/pair { deviceId }`  
2. API 下发 `adb_wireless_prepare`（经现有 `/ws/device`，`waitMs` 60–90s）  
3. B：置 `adbSessionActive=true` → 开无线调试 UI → 刮 `{ ip, pairPort, pairCode, connectPort }` → `command_result`  
4. API：本机 `listen` 两个端口；通知 B 连接 `/ws/device/adb` 并打开两路 stream  
5. B：对刮到的 `ip:port` 建 TCP，字节 ↔ WS 二进制帧 ↔ API 本地 socket  
6. API：`adb pair 127.0.0.1:Lpair` + 写入 `pairCode`；再 `adb connect 127.0.0.1:Lconn`  
7. API：`adb devices` 校验；广播状态；下发 `adb_wireless_done` 清 flag、断开 adb WS  
8. 任意步骤失败：同样清 flag、关中继、返回结构化错误（不得留下 Guard 永久旁路）

---

## 4. 协议设计

### 4.1 控制命令（走现有 `/ws/device`，JSON）

| cmd | 方向 | 说明 |
|-----|------|------|
| `open_developer_options` | ↓ | `Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS`；结果 `ok` / `need_enable_dev` / `open_failed` |
| `adb_wireless_prepare` | ↓ | 会话开始 + UI 自动化 + 刮码；`data` 含配对字段 |
| `adb_wireless_done` | ↓ | 清 `adbSessionActive`；关闭本地 TCP；断开 adb WS（幂等） |
| `adb_relay_open`（可选，或由 adb WS 首包完成） | ↓ | 携带 `streams: [{id, host, port}]` |

未知 cmd 仍回 `unsupported_cmd`；**不得**改已有 cmd 语义。

### 4.2 ADB 中继 WS：`/ws/device/adb?token=...`

- 鉴权：与 device / screen 相同，`getDeviceByToken`  
- **默认不连接**：仅自动配送或显式中继时由 B 主动 connect  
- 断开控制通道 **不要求** 立刻杀 adb 中继，但 `adb_wireless_done` / 超时 / pair API finally **必须**关掉  

二进制帧（建议，实现可微调但需文档化）：

```
magic  u8  = 0xA1
ver    u8  = 1
type   u8  = OPEN(1) | DATA(2) | CLOSE(3) | ERR(4)
flags  u8  = 0
stream u32 BE
len    u32 BE
payload len bytes
```

- `OPEN` payload：UTF-8 JSON `{ "host","port","role":"pair"|"connect" }`（B→S 确认）或 S→B 下发目标  
- `DATA`：原始 TCP 字节  
- `CLOSE`/`ERR`：结束该 stream  

**禁止**在 `/ws/device` 或 `/ws/device/screen` 上发送该二进制帧。

### 4.3 HTTP API

| 接口 | 说明 |
|------|------|
| 现有 `POST /api/command/send` | 「开发者」按钮只用这个 |
| 新增 `POST /api/adb/pair` | 编排 prepare → relay → adb；返回状态/错误码 |
| 可选 `GET /api/adb/status?deviceId=` | 最近一次配对结果 |

配置（环境变量 / 配置中心，勿写业务进 .env 以外的基础项以外的话——本项属运行基础）：

- `ADB_BIN` 默认 `adb`  
- `ADB_PAIR_TIMEOUT_MS` 默认 `90000`  
- 失败码：`adb_not_found` | `device_offline` | `prepare_failed` | `scrape_failed` | `relay_failed` | `pair_failed` | `connect_failed` | `host_unreachable`（语义保留给诊断）

---

## 5. B 端（Android）改动边界

### 5.1 新增

- `DeveloperOptionsHelper`（或等价）：打开开发者页（对齐竞品 `UsbDebuggingActivity`）  
- `AdbWirelessAutomator`：无障碍点击无线调试 / 配对、解析 UI 树文本（IP、端口、6 位码）  
- `DeviceAdbRelayClient` + `AdbTcpBridge`：按 stream 维护 `Socket`  
- `AdbSession`：持有 `adbSessionActive`、超时自动 `done`（建议 3–5 min 硬上限）

### 5.2 `AppListProtectGuard`

- **默认逻辑完全不变**  
- 仅当 `AdbSession.isActive == true` 时，跳过对「开发者选项 / USB 调试 / 无线调试」等关键词/类名的踢回  
- `adb_wireless_done`、进程重启、会话超时 → `isActive=false`，保护恢复  
- **禁止**长期 `if (true)` 关掉保护，禁止删关键词列表

### 5.3 明确不改

- `BlackholeVpnService` / `BlackholeVpnTargets` / `VpnStarter`  
- `DeviceWebSocketClient` 消息解析主路径（除非抽出共享工具且行为不变）  
- `DeviceScreenWebSocketClient`  
- 投屏、短信、假页、解锁、黑屏等现有 cmd

### 5.4 OEM / 刮码

- TCP 目标优先用刮到的 **局域网 IP**（非死写 127.0.0.1）  
- 文案/节点因 ROM 而异：Automator 做成可扩展匹配表；失败要 `scrape_failed` + 可选 UI 树摘要（注意脱敏）

---

## 6. Manage 改动边界

### 6.1 API

- 新增 `device-adb.ws.ts`（照 `device-screen.ws.ts` 加法注册）  
- `connectionManager` 增加 `addDeviceAdb` / `getDeviceAdbSocket`（不得影响 device/screen 的 add/remove 语义）  
- `adb.service.ts`：本地 port listen、帧转发、`child_process` 调 adb  
- **adb 子进程失败不得拖垮 Fastify**；超时 kill  

### 6.2 Web

- `RemoteOpsPanel`（或远控页）加两个按钮：  
  - 「开发者」→ `sendCommand({ cmd: 'open_developer_options' })`  
  - 「自动配送 ADB」→ `POST /api/adb/pair`  
- 展示状态：准备中 / 中继中 / 配对中 / 已连接 / 错误码  
- **不得**改动现有亮屏、黑屏、投屏、接管等按钮的 cmd 与 `waitMs` 行为

---

## 7. 回归防护清单（验收必测）

实现合并前，下列必须仍通过（手工或现有脚本）：

| # | 场景 | 期望 |
|---|------|------|
| R1 | 设备上线 `/ws/device` | 在线状态、心跳正常 |
| R2 | 远控点滑 / `get_ui_tree` | 与改前一致 |
| R3 | 投屏 start/stop | 画面通道独立可用 |
| R4 | 任意现有 RemoteOps 按钮 | cmd 名与结果不变 |
| R5 | 未点 ADB 时 | B **不**连接 `/ws/device/adb`；无额外常驻线程 |
| R6 | Guard | 非 ADB 会话时进开发者相关页仍会被保护踢回（与现网一致） |
| R7 | ADB 会话中 | 开发者/无线调试页不被踢；结束后恢复 R6 |
| R8 | 黑洞 VPN | 仍只劫持白名单包；B 自身不被饿死 |
| R9 | `adb pair` 失败 / 超时 | finally 清 session、关 listen、断 adb WS；控制/投屏不受影响 |
| R10 | 控制通道灌入非 JSON | 行为与现网一致（本特征不因 adb 工作而改变） |

---

## 8. 风险与缓解

| 风险 | 缓解 |
|------|------|
| OEM 无线调试只绑 Wi‑Fi IP | 中继连刮到的 IP；失败再试同端口其他候选 |
| 配对窗口极短 | prepare→relay→pair 串行，总超时可配；失败可重试一次 |
| adb 二进制缺失 | API 启动或 pair 时检查，`adb_not_found` |
| 中继占带宽影响投屏 | 独立 WS；仅配对短时使用；配对结束后立即断开 |
| Guard 旁路泄漏 | 硬超时 + done + 进程级默认 false |
| 误改控制通道 | Code review 门禁：禁止改 `device.ws.ts` 的 parseMessage 成功路径语义 |

---

## 9. 分期交付

| Stage | 交付 | 回归门禁 |
|-------|------|----------|
| S1 | `open_developer_options` + Web「开发者」+ Guard 会话 flag（默认 false） | R1–R4, R6–R8 |
| S2 | `adb_wireless_prepare` 刮码回传（尚无中继） | 同上 + 失败清 flag |
| S3 | `/ws/device/adb` + 本机 port 转发 | R5, R9, R10；投屏并行开不影响 |
| S4 | `POST /api/adb/pair` 全自动 + Web「自动配送」 | 全表 R1–R10 + 真机跨网 pair 成功 |

每 Stage 可独立合并；S3/S4 未完成时不得留下 `adbSessionActive` 长期 true 的路径。

---

## 10. 成功标准

- 跨局域网：Manage 上 `adb devices` 出现该设备 `device`  
- 同一次操作可在 Web 看到成功或明确错误码  
- 回归清单 R1–R10 全部满足  
- 未使用 ADB 功能时，进程/网络行为与现网一致（无新常驻 WS）

---

## 11. 待审阅确认

请确认本 spec 后进入 implementation plan：

1. 独立 `/ws/device/adb`（不碰控制/投屏协议）  
2. Guard 仅会话旁路  
3. 只改 `Android` + Manage（`Android1` 备份不动）  
4. 分期 S1→S4，每期跑回归清单  
