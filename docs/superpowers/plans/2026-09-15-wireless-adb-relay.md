# Wireless ADB Relay Implementation Plan

> **For agentic workers:** Implement task-by-task. Steps use checkbox (`- [ ]`) syntax. **Do not commit unless user asks.** Hard rule: never change `/ws/device` or `/ws/device/screen` message parse semantics.

**Goal:** Cross-LAN wireless ADB pair/connect via new `/ws/device/adb` relay, with Manage-local `adb`, without breaking existing remote control.

**Architecture:** Commands on existing control WS; binary TCP relay on new adb WS; Guard bypass only while `AdbSession.isActive`; blackhole VPN untouched.

**Tech Stack:** Kotlin (`Android`), Fastify WS (Manage/api), React (Manage/web), platform `adb` CLI.

## Global Constraints

- Manage 与手机不同局域网 → 必须 WS 中继，禁止假设直连手机局域网 IP
- 不做真实 VPN；不改 `BlackholeVpnService`
- 只改 `Android` + `Manage`（`Android1` 为备份，不改）
- `/ws/device`、`/ws/device/screen` 协议零语义变更
- 未启动 ADB 流程时不得常连 `/ws/device/adb`
- `AppListProtectGuard` 仅会话旁路，默认行为不变
- 现有 RemoteOps 按钮 cmd / waitMs 不得改坏

## File Map

| File | Role |
|------|------|
| `Android/.../adb/AdbSession.kt` | `isActive` flag + timeout |
| `Android/.../adb/DeveloperOptionsHelper.kt` | Open developer settings |
| `Android/.../adb/AdbWirelessAutomator.kt` | UI scrape prepare (S2) |
| `Android/.../network/DeviceAdbRelayClient.kt` | Adb WS client (S3) — 实际在 `adb/` 包 |
| `Android/.../adb/AdbTcpBridge.kt` | Local TCP ↔ frames (S3) — 在 FrameCodec 文件 |
| `Android/.../command/CommandExecutor.kt` | New cmds only |
| `Android/.../accessibility/AppListProtectGuard.kt` | Session bypass only |
| `Manage/api/src/ws/device-adb.ws.ts` | New WS route (S3) |
| `Manage/api/src/services/adb.service.ts` | Orchestration + local adb (S4) |
| `Manage/api/src/routes/adb.routes.ts` | `POST /api/adb/pair` (S4) |
| `Manage/web/.../RemoteOpsPanel.tsx` | Two new buttons |

---

### Task 1: AdbSession + open_developer_options (S1)

**Files:**
- Create: `Android/app/src/main/java/com/vibelink/client/adb/AdbSession.kt`
- Create: `Android/app/src/main/java/com/vibelink/client/adb/DeveloperOptionsHelper.kt`
- Modify: `Android/.../command/CommandExecutor.kt` — add `open_developer_options`, `adb_wireless_done`
- Modify: `Android/.../accessibility/AppListProtectGuard.kt` — skip developer kick when `AdbSession.isActive`

**Interfaces:**
- Produces: `AdbSession.begin(timeoutMs)`, `AdbSession.end()`, `AdbSession.isActive`, `DeveloperOptionsHelper.open(context): JSONObject result`

- [ ] Add `AdbSession` object (default inactive)
- [ ] Add `DeveloperOptionsHelper.open`
- [ ] Wire cmds in `CommandExecutor`
- [ ] Guard: if active, do not kick developer/wireless debug pages
- [ ] Web: 「开发者」button → `open_developer_options`
- [ ] Verify: compile Android; Guard still kicks when inactive

### Task 2: adb_wireless_prepare scrape (S2)

**Files:**
- Create: `AdbWirelessAutomator.kt`
- Modify: `CommandExecutor.kt` — `adb_wireless_prepare` begins session + scrape

- [ ] Automator opens wireless debugging / pairing UI via accessibility
- [ ] Parse ip, pairPort, pairCode, connectPort from tree
- [ ] On failure call `AdbSession.end()` and return `scrape_failed`
- [ ] Manual verify on device if available

### Task 3: Adb relay WS (S3)

**Files:**
- Create: `Manage/api/src/ws/device-adb.ws.ts`
- Modify: `connection-manager.ts` — add adb socket map only
- Modify: app route registration (additive)
- Create: `DeviceAdbRelayClient.kt`, `AdbTcpBridge.kt`, frame codec

- [ ] Register `/ws/device/adb` without touching device/screen parsers
- [ ] B connects only when relay requested
- [ ] Bidirectional DATA frames; CLOSE cleans sockets
- [ ] Verify: control + screen still work with adb WS idle

### Task 4: POST /api/adb/pair + Web button (S4)

**Files:**
- Create: `adb.service.ts`, `adb.routes.ts`
- Modify: `RemoteOpsPanel.tsx` — 「自动配送 ADB」

- [ ] Orchestrate prepare → local listen → relay → `adb pair` → `adb connect` → done
- [ ] Structured errors; always finally cleanup
- [ ] Regression smoke: existing RemoteOps actions unchanged

---

**Regression gate (every task):** R1–R10 from design spec; especially R5 (no adb WS until needed) and R6/R7 (Guard).
