import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";
import type { CommandResultPayload, CommandStatus } from "../types/messages.js";
import { getDeviceById } from "./device.service.js";
import { connectionManager } from "../ws/connection-manager.js";
import { normalizeDeviceCommand } from "./command-normalize.js";

interface CommandRow extends RowDataPacket {
  id: string;
  device_id: string;
  cmd: string;
  params: string | Record<string, unknown> | null;
  status: CommandStatus;
  result: string | Record<string, unknown> | null;
  error_message: string | null;
  created_at: Date;
  updated_at: Date;
  completed_at: Date | null;
}

interface PendingCommand {
  resolve: (command: ReturnType<typeof mapCommand>) => void;
  reject: (error: Error) => void;
  timer: NodeJS.Timeout;
}

const pendingWaits = new Map<string, PendingCommand>();
/** Ephemeral commandId → cmd (no DB row / admin broadcast). */
const ephemeralCommandCmds = new Map<string, string>();
/** ephemeral ping 发送时间：用于计算 a11y/控制通道 RTT（毫秒）。 */
const pingSentAt = new Map<string, { deviceId: string; sentAt: number }>();

const EPHEMERAL_COMMANDS = new Set([
  "get_screen",
  "capture_screenshot",
  "ping",
  "zombie_wake",
  "zombie_wake_clear",
]);

/**
 * Single-flight：相同 (deviceId, cmd) 在途时合并请求。
 * 仅对可合并的只读快照命令开启；这些命令任意一次结果都能满足并发等待方。
 */
const SINGLE_FLIGHT_COMMANDS = new Set(["get_screen", "capture_screenshot"]);
const singleFlightInflight = new Map<string, Promise<ReturnType<typeof mapCommand>>>();

function isEphemeralCommand(cmd: string) {
  return EPHEMERAL_COMMANDS.has(cmd);
}

function buildEphemeralCommand(input: {
  commandId: string;
  deviceId: string;
  cmd: string;
  status: CommandStatus;
  result?: Record<string, unknown> | null;
  errorMessage?: string | null;
}) {
  const now = new Date();
  return {
    id: input.commandId,
    deviceId: input.deviceId,
    cmd: input.cmd,
    params: null,
    status: input.status,
    result: input.result ?? null,
    errorMessage: input.errorMessage ?? null,
    createdAt: now,
    updatedAt: now,
    completedAt: input.status === "sent" ? null : now,
  };
}

function parseJsonField<T>(value: string | T | null): T | null {
  if (value == null) {
    return null;
  }
  if (typeof value === "string") {
    try {
      return JSON.parse(value) as T;
    } catch {
      return null;
    }
  }
  return value;
}

function mapCommand(row: CommandRow) {
  return {
    id: row.id,
    deviceId: row.device_id,
    cmd: row.cmd,
    params: parseJsonField<Record<string, unknown>>(row.params),
    status: row.status,
    result: parseJsonField<Record<string, unknown>>(row.result),
    errorMessage: row.error_message,
    createdAt: row.created_at,
    updatedAt: row.updated_at,
    completedAt: row.completed_at,
  };
}

async function getCommandRow(commandId: string) {
  const [rows] = await pool.query<CommandRow[]>(
    "SELECT * FROM commands WHERE id = ? LIMIT 1",
    [commandId],
  );
  return rows[0] ?? null;
}

export async function sendCommand(input: {
  deviceId: string;
  cmd: string;
  params?: Record<string, unknown>;
  waitMs?: number;
  /**
   * 强制按 ephemeral 处理（不落库、不进活动日志）。
   * 用于服务端自己发起的轮询类探测，避免把 `commands` 表刷满；
   * 不传时仍按 EPHEMERAL_COMMANDS 判定，手工下发行为不变。
   */
  ephemeral?: boolean;
}) {
  const normalizedCmd = normalizeDeviceCommand(input.cmd.trim());
  const deviceId = input.deviceId.trim();
  if (SINGLE_FLIGHT_COMMANDS.has(normalizedCmd) && deviceId) {
    const key = `${deviceId}:${normalizedCmd}`;
    const existing = singleFlightInflight.get(key);
    if (existing) {
      return existing;
    }
    const promise = sendCommandImpl(input).finally(() => {
      singleFlightInflight.delete(key);
    });
    singleFlightInflight.set(key, promise);
    return promise;
  }
  return sendCommandImpl(input);
}

async function sendCommandImpl(input: {
  deviceId: string;
  cmd: string;
  params?: Record<string, unknown>;
  waitMs?: number;
  ephemeral?: boolean;
}) {
  const deviceId = input.deviceId.trim();
  const cmd = normalizeDeviceCommand(input.cmd.trim());
  if (!deviceId || !cmd) {
    throw new Error("deviceId and cmd are required");
  }

  const device = await getDeviceById(deviceId);
  if (!device) {
    throw new Error("device_not_found");
  }

  const commandId = uuidv4();
  const paramsJson = input.params ? JSON.stringify(input.params) : null;
  const ephemeral = input.ephemeral === true || isEphemeralCommand(cmd);

  if (ephemeral) {
    ephemeralCommandCmds.set(commandId, cmd);
  } else {
    await pool.query(
      "INSERT INTO commands (id, device_id, cmd, params, status) VALUES (?, ?, ?, ?, 'pending')",
      [commandId, deviceId, cmd, paramsJson],
    );
  }

  const socket = connectionManager.getDeviceSocket(deviceId);
  if (!socket || socket.readyState !== socket.OPEN) {
    if (ephemeral) {
      ephemeralCommandCmds.delete(commandId);
    } else {
      await pool.query(
        "UPDATE commands SET status = 'failed', error_message = ?, completed_at = UTC_TIMESTAMP() WHERE id = ?",
        ["device_offline", commandId],
      );
    }
    throw new Error("device_offline");
  }

  if (!ephemeral) {
    await pool.query("UPDATE commands SET status = 'sent' WHERE id = ?", [commandId]);
  }

  socket.send(
    JSON.stringify({
      type: "command",
      id: commandId,
      cmd,
      params: input.params ?? {},
    }),
  );
  if (cmd === "ping") {
    pingSentAt.set(commandId, { deviceId, sentAt: Date.now() });
  }

  if (!ephemeral) {
    connectionManager.broadcastToAdmins({
      type: "command_sent",
      deviceId,
      payload: {
        commandId,
        cmd,
        params: input.params ?? {},
      },
      timestamp: Date.now(),
    });
  }

  const waitMs = input.waitMs ?? 0;
  if (waitMs <= 0) {
    if (ephemeral) {
      return buildEphemeralCommand({
        commandId,
        deviceId,
        cmd,
        status: "sent",
      });
    }
    return mapCommand((await getCommandRow(commandId))!);
  }

  return waitForCommandResult(commandId, waitMs, { deviceId, cmd, ephemeral });
}

function waitForCommandResult(
  commandId: string,
  waitMs: number,
  context?: { deviceId: string; cmd: string; ephemeral: boolean },
) {
  return new Promise<ReturnType<typeof mapCommand>>((resolve, reject) => {
    const timer = setTimeout(async () => {
      pendingWaits.delete(commandId);
      const ephemeral = context?.ephemeral ?? ephemeralCommandCmds.has(commandId);
      if (ephemeral) {
        const ephemeralCmd =
          context?.cmd ?? ephemeralCommandCmds.get(commandId) ?? "unknown";
        ephemeralCommandCmds.delete(commandId);
        if (context) {
          resolve(
            buildEphemeralCommand({
              commandId,
              deviceId: context.deviceId,
              cmd: ephemeralCmd,
              status: "timeout",
              errorMessage: "wait_timeout",
            }),
          );
          return;
        }
        reject(new Error("command_not_found"));
        return;
      }

      await pool.query(
        "UPDATE commands SET status = 'timeout', error_message = ?, completed_at = UTC_TIMESTAMP() WHERE id = ? AND status = 'sent'",
        ["wait_timeout", commandId],
      );
      const row = await getCommandRow(commandId);
      if (row) {
        resolve(mapCommand(row));
        return;
      }
      reject(new Error("command_not_found"));
    }, waitMs);

    pendingWaits.set(commandId, { resolve, reject, timer });
  });
}

export async function applyCommandResult(
  deviceId: string,
  payload: CommandResultPayload,
) {
  const commandId = payload.id?.trim();
  if (!commandId) {
    throw new Error("command_id_required");
  }

  const status: CommandStatus = payload.ok ? "success" : "failed";
  const resultJson = payload.data ? JSON.stringify(payload.data) : null;
  const errorMessage = payload.ok ? null : payload.error ?? "command_failed";
  const ephemeralCmd = ephemeralCommandCmds.get(commandId);
  const ephemeral = ephemeralCmd != null;

  // L5: 从 ephemeral ping 的 RTT 推断 a11y/控制通道健康度（粗粒度滑动值）。
  const pingTrack = pingSentAt.get(commandId);
  if (pingTrack) {
    pingSentAt.delete(commandId);
    const rtt = Date.now() - pingTrack.sentAt;
    connectionManager.updateDeviceHealth(pingTrack.deviceId, { a11yRttMs: rtt });
  }

  if (!ephemeral) {
    await pool.query(
      `UPDATE commands
       SET status = ?, result = ?, error_message = ?, completed_at = UTC_TIMESTAMP()
       WHERE id = ? AND device_id = ?`,
      [status, resultJson, errorMessage, commandId, deviceId],
    );
  } else {
    ephemeralCommandCmds.delete(commandId);
  }

  const row = ephemeral ? null : await getCommandRow(commandId);
  const command =
    row != null
      ? mapCommand(row)
      : ephemeral
        ? buildEphemeralCommand({
            commandId,
            deviceId,
            cmd: ephemeralCmd ?? "unknown",
            status,
            result: (payload.data as Record<string, unknown> | undefined) ?? null,
            errorMessage,
          })
        : null;

  if (!ephemeral) {
    connectionManager.broadcastToAdmins({
      type: "command_result",
      deviceId,
      payload: {
        commandId,
        ok: payload.ok,
        data: payload.data ?? null,
        error: payload.error ?? null,
        status,
      },
      timestamp: Date.now(),
    });
  } else {
    // L1-5: ephemeral 命令（get_screen / capture_screenshot）也广播，
    // 让详情页能收到异步到达的快照结果（非 single-flight 发起方也能看见）。
    connectionManager.broadcastToAdmins({
      type: "command_result",
      deviceId,
      payload: {
        commandId,
        cmd: ephemeralCmd,
        ok: payload.ok,
        data: payload.data ?? null,
        error: payload.error ?? null,
        status,
        ephemeral: true,
      },
      timestamp: Date.now(),
    });
  }

  const pending = pendingWaits.get(commandId);
  if (pending && command) {
    clearTimeout(pending.timer);
    pendingWaits.delete(commandId);
    pending.resolve(command);
  }

  return command;
}

export async function getCommand(commandId: string) {
  const row = await getCommandRow(commandId);
  return row ? mapCommand(row) : null;
}

export async function listCommands(input: {
  deviceId?: string;
  limit?: number;
}) {
  const limit = Math.min(Math.max(input.limit ?? 50, 1), 200);
  const deviceId = input.deviceId?.trim();

  const [rows] = deviceId
    ? await pool.query<CommandRow[]>(
        "SELECT * FROM commands WHERE device_id = ? ORDER BY created_at DESC LIMIT ?",
        [deviceId, limit],
      )
    : await pool.query<CommandRow[]>(
        "SELECT * FROM commands ORDER BY created_at DESC LIMIT ?",
        [limit],
      );

  return rows.map(mapCommand);
}
