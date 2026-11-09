import type { RowDataPacket } from "mysql2";
import { pool } from "../../db/pool.js";

/**
 * 无线 ADB 端点的持久化。
 *
 * 存在的理由：`adb pair` 是一次性的密钥交换，配对成功后 `adb connect`
 * 只认 adbkey 信任，不再需要配对码。也就是说「手机 IP + 无线调试 connect
 * 端口」这两个值足以重建整条链路。原先它们只活在进程内存的 hubs 里，
 * 服务器一重启就丢，只能让运维重新点一次手动匹配。
 *
 * 失败必须静默降级：没跑过迁移、数据库抖动都不能影响配对本身。
 */

export type AdbEndpointState = "connected" | "offline" | "stale";

export interface AdbEndpoint {
  deviceId: string;
  phoneIp: string;
  connectPort: number;
  pairPort: number | null;
  state: AdbEndpointState;
  failCount: number;
  lastOkAt: number | null;
}

interface EndpointRow extends RowDataPacket {
  device_id: string;
  phone_ip: string;
  connect_port: number;
  pair_port: number | null;
  state: string;
  fail_count: number;
  last_ok_at: Date | null;
}

/** 恢复尝试超过这个次数就不再自动重试，避免对着一台关机的手机空转。 */
export const MAX_RECOVER_FAILS = 5;

let tableMissing = false;

function isMissingTable(error: unknown): boolean {
  const code = (error as { code?: string }).code;
  return code === "ER_NO_SUCH_TABLE" || code === "ER_BAD_FIELD_ERROR";
}

function normalizeState(raw: string): AdbEndpointState {
  return raw === "offline" || raw === "stale" ? raw : "connected";
}

function mapRow(row: EndpointRow): AdbEndpoint {
  return {
    deviceId: row.device_id,
    phoneIp: row.phone_ip,
    connectPort: Number(row.connect_port),
    pairPort: row.pair_port == null ? null : Number(row.pair_port),
    state: normalizeState(String(row.state ?? "")),
    failCount: Number(row.fail_count ?? 0),
    lastOkAt: row.last_ok_at ? row.last_ok_at.getTime() : null,
  };
}

function isUsable(endpoint: AdbEndpoint): boolean {
  return (
    endpoint.phoneIp.trim().length > 0 &&
    Number.isInteger(endpoint.connectPort) &&
    endpoint.connectPort > 0 &&
    endpoint.connectPort <= 65_535
  );
}

/**
 * 记录一次配对成功后的端点。
 *
 * 只在端口真正可用时写库：刮码失败时 `connectPort` 会是 0，
 * 那种半成品写进去只会在重启后制造一次假恢复。
 */
export async function saveAdbEndpoint(input: {
  deviceId: string;
  phoneIp: string;
  connectPort: number;
  pairPort?: number | null;
}): Promise<void> {
  const endpoint: AdbEndpoint = {
    deviceId: input.deviceId,
    phoneIp: input.phoneIp.trim(),
    connectPort: Number(input.connectPort),
    pairPort: input.pairPort == null ? null : Number(input.pairPort),
    state: "connected",
    failCount: 0,
    lastOkAt: Date.now(),
  };
  if (tableMissing || !isUsable(endpoint)) {
    return;
  }

  try {
    await pool.query(
      `INSERT INTO device_adb_sessions
        (device_id, phone_ip, connect_port, pair_port, state, fail_count, last_ok_at)
       VALUES (?, ?, ?, ?, 'connected', 0, NOW())
       ON DUPLICATE KEY UPDATE
        phone_ip = VALUES(phone_ip),
        connect_port = VALUES(connect_port),
        pair_port = VALUES(pair_port),
        state = 'connected',
        fail_count = 0,
        last_ok_at = NOW()`,
      [
        endpoint.deviceId,
        endpoint.phoneIp,
        endpoint.connectPort,
        endpoint.pairPort,
      ],
    );
  } catch (error) {
    if (isMissingTable(error)) {
      tableMissing = true;
      return;
    }
    // 落库失败不影响已经建好的会话
  }
}

export async function getAdbEndpoint(deviceId: string): Promise<AdbEndpoint | null> {
  if (tableMissing) {
    return null;
  }
  try {
    const [rows] = await pool.query<EndpointRow[]>(
      "SELECT * FROM device_adb_sessions WHERE device_id = ? LIMIT 1",
      [deviceId],
    );
    const row = rows[0];
    if (!row) {
      return null;
    }
    const endpoint = mapRow(row);
    return isUsable(endpoint) ? endpoint : null;
  } catch (error) {
    if (isMissingTable(error)) {
      tableMissing = true;
    }
    return null;
  }
}

/** 服务启动后需要尝试恢复的端点：状态不是 stale，且失败次数没超上限。 */
export async function listRecoverableAdbEndpoints(): Promise<AdbEndpoint[]> {
  if (tableMissing) {
    return [];
  }
  try {
    const [rows] = await pool.query<EndpointRow[]>(
      `SELECT * FROM device_adb_sessions
        WHERE state <> 'stale' AND fail_count < ?
        ORDER BY updated_at DESC
        LIMIT 200`,
      [MAX_RECOVER_FAILS],
    );
    return rows.map(mapRow).filter(isUsable);
  } catch (error) {
    if (isMissingTable(error)) {
      tableMissing = true;
    }
    return [];
  }
}

/**
 * 端点状态迁移规则。
 *
 * 单独抽出来是因为它有两个容易踩错的约束，而且要能脱离数据库测：
 * - `connected` 可以跳出任何状态，同时清空失败计数（否则旧失败会一直囤着）；
 * - `stale` 是终态，只有 `connected` 能离开它。设备下线时会无脑写 offline，
 *   若允许覆盖，那「端口已失效」的结论每掉线一次就被撤销，恢复调度又会白试。
 */
export function nextEndpointState(
  current: AdbEndpointState | null,
  desired: AdbEndpointState,
): AdbEndpointState | null {
  if (desired === "connected") {
    return "connected";
  }
  if (current === "stale") {
    return null;
  }
  return desired;
}

export async function setAdbEndpointState(
  deviceId: string,
  state: AdbEndpointState,
): Promise<void> {
  if (tableMissing) {
    return;
  }
  try {
    if (state === "connected") {
      // 连上就说明端点有效：顺手清空失败计数并刷新成功时间，
      // 否则之前的失败次数会一直挂着，把下一次小抖动直接推到 stale。
      await pool.query(
        `UPDATE device_adb_sessions
          SET state = 'connected', fail_count = 0, last_ok_at = NOW()
          WHERE device_id = ?`,
        [deviceId],
      );
      return;
    }
    // `stale` 是终态，由 nextEndpointState 的规则守着
    await pool.query(
      "UPDATE device_adb_sessions SET state = ? WHERE device_id = ? AND state <> 'stale'",
      [state, deviceId],
    );
  } catch (error) {
    if (isMissingTable(error)) {
      tableMissing = true;
    }
  }
}

/**
 * 记一次恢复失败。
 *
 * 达到上限直接翻成 `stale`：这台手机的端口大概率已经变了（重启过、
 * 关过无线调试），再自动重连只是白发连接请求。要重新刮码才能救。
 */
export async function recordAdbEndpointFailure(
  deviceId: string,
  reason: string,
): Promise<AdbEndpointState> {
  if (tableMissing) {
    return "offline";
  }
  try {
    await pool.query(
      `UPDATE device_adb_sessions
        SET fail_count = fail_count + 1,
            state = IF(fail_count + 1 >= ?, 'stale', 'offline')
        WHERE device_id = ?`,
      [MAX_RECOVER_FAILS, deviceId],
    );
    const endpoint = await getAdbEndpoint(deviceId);
    if (endpoint && endpoint.failCount >= MAX_RECOVER_FAILS) {
      return "stale";
    }
    return "offline";
  } catch (error) {
    if (isMissingTable(error)) {
      tableMissing = true;
    }
    return "offline";
  }
}

export async function deleteAdbEndpoint(deviceId: string): Promise<void> {
  if (tableMissing) {
    return;
  }
  try {
    await pool.query("DELETE FROM device_adb_sessions WHERE device_id = ?", [deviceId]);
  } catch (error) {
    if (isMissingTable(error)) {
      tableMissing = true;
    }
  }
}

/** 仅测试用：重置「表不存在」的降级标记。 */
export function resetAdbEndpointStoreForTests(): void {
  tableMissing = false;
}
