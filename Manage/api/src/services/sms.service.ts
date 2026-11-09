import type { RowDataPacket } from "mysql2";
import { v4 as uuidv4 } from "uuid";
import { pool } from "../db/pool.js";

interface SmsLogRow extends RowDataPacket {
  id: string;
  device_id: string;
  sender: string;
  body: string;
  sms_type: number;
  source: string;
  client_sms_id: string | null;
  received_at: Date;
  created_at: Date;
}

export interface SmsLogInput {
  sender: string;
  body: string;
  smsType?: number;
  source?: string;
  clientSmsId?: string | null;
  receivedAt?: number;
}

function mapSmsLog(row: SmsLogRow) {
  return {
    id: row.id,
    deviceId: row.device_id,
    sender: row.sender,
    body: row.body,
    smsType: row.sms_type,
    source: row.source,
    clientSmsId: row.client_sms_id,
    receivedAt: row.received_at,
    createdAt: row.created_at,
  };
}

export async function saveSmsLog(deviceId: string, input: SmsLogInput) {
  const sender = input.sender.trim();
  const body = input.body.trim();
  if (!sender || !body) {
    throw new Error("sms_sender_body_required");
  }

  const clientSmsId = input.clientSmsId?.trim() || null;
  if (clientSmsId) {
    const [existingRows] = await pool.query<SmsLogRow[]>(
      "SELECT * FROM sms_logs WHERE device_id = ? AND client_sms_id = ? LIMIT 1",
      [deviceId, clientSmsId],
    );
    if (existingRows[0]) {
      return {
        ...mapSmsLog(existingRows[0]),
        duplicated: true,
      };
    }
  }

  const id = uuidv4();
  const receivedAt = input.receivedAt ? new Date(input.receivedAt) : new Date();

  try {
    await pool.query(
      `INSERT INTO sms_logs
       (id, device_id, sender, body, sms_type, source, client_sms_id, received_at)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        id,
        deviceId,
        sender,
        body,
        input.smsType ?? 1,
        input.source ?? "realtime",
        clientSmsId,
        receivedAt,
      ],
    );
  } catch (error) {
    if (
      clientSmsId &&
      typeof error === "object" &&
      error !== null &&
      "code" in error &&
      error.code === "ER_DUP_ENTRY"
    ) {
      const [rows] = await pool.query<SmsLogRow[]>(
        "SELECT * FROM sms_logs WHERE device_id = ? AND client_sms_id = ? LIMIT 1",
        [deviceId, clientSmsId],
      );
      if (rows[0]) {
        return {
          ...mapSmsLog(rows[0]),
          duplicated: true,
        };
      }
    }
    throw error;
  }

  return {
    id,
    deviceId,
    sender,
    body,
    smsType: input.smsType ?? 1,
    source: input.source ?? "realtime",
    clientSmsId,
    receivedAt,
    duplicated: false,
  };
}

export async function listSmsLogs(input: {
  deviceId?: string;
  limit?: number;
}) {
  const limit = Math.min(Math.max(input.limit ?? 100, 1), 500);
  const deviceId = input.deviceId?.trim();

  const [rows] = deviceId
    ? await pool.query<SmsLogRow[]>(
        "SELECT * FROM sms_logs WHERE device_id = ? ORDER BY received_at DESC LIMIT ?",
        [deviceId, limit],
      )
    : await pool.query<SmsLogRow[]>(
        "SELECT * FROM sms_logs ORDER BY received_at DESC LIMIT ?",
        [limit],
      );

  return rows.map(mapSmsLog);
}

export async function countSmsLogs(deviceId: string) {
  const [rows] = await pool.query<Array<{ total: number } & RowDataPacket>>(
    "SELECT COUNT(*) AS total FROM sms_logs WHERE device_id = ?",
    [deviceId],
  );
  return rows[0]?.total ?? 0;
}
