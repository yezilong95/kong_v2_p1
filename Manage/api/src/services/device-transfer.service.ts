import type { ResultSetHeader, RowDataPacket } from "mysql2";
import { pool } from "../db/pool.js";
import { getDeviceTransferEnabled, getDeviceTransferSecret } from "./app-config.service.js";
import { sendCommand } from "./command.service.js";
import {
  getDeviceById,
  type DeviceRecord,
} from "./device.service.js";
import { getPeerServerById } from "./peer-server.service.js";
import { getDeviceGroupById } from "./device-group.service.js";
import { connectionManager } from "../ws/connection-manager.js";

export interface TransferableDeviceSnapshot {
  id: string;
  androidId: string;
  token: string;
  model: string | null;
  remark: string | null;
  bindCode: string | null;
  groupId: string | null;
  appVersion: string | null;
  brand: string | null;
  localeLanguage: string | null;
  localeRegion: string | null;
  androidVersion: string | null;
  sdkInt: number | null;
}

interface DeviceIdentityRow extends RowDataPacket {
  id: string;
  token: string;
}

export async function getTransferSnapshot(deviceId: string): Promise<TransferableDeviceSnapshot | null> {
  const device = await getDeviceById(deviceId);
  if (!device?.deviceToken) {
    return null;
  }
  return {
    id: device.deviceId,
    androidId: device.androidId,
    token: device.deviceToken,
    model: device.model,
    remark: device.remark,
    bindCode: device.bindCode,
    groupId: device.groupId,
    appVersion: device.appVersion,
    brand: device.brand,
    localeLanguage: device.localeLanguage,
    localeRegion: device.localeRegion,
    androidVersion: device.androidVersion,
    sdkInt: device.sdkInt,
  };
}

export async function markDeviceTransferred(deviceId: string, apiBaseUrl: string): Promise<void> {
  await pool.query(
    "UPDATE devices SET transferred_to = ?, transferred_at = UTC_TIMESTAMP() WHERE id = ?",
    [apiBaseUrl.slice(0, 256), deviceId],
  );
}

export async function clearDeviceTransferred(deviceId: string): Promise<void> {
  await pool.query(
    "UPDATE devices SET transferred_to = NULL, transferred_at = NULL WHERE id = ?",
    [deviceId],
  );
}

export async function acceptTransferredDevice(
  snapshot: TransferableDeviceSnapshot,
): Promise<DeviceRecord> {
  const androidId = snapshot.androidId.trim();
  const token = snapshot.token.trim();
  const id = snapshot.id.trim();
  if (!androidId || !token || !id) {
    throw new Error("transfer_identity_required");
  }

  const [androidRows] = await pool.query<DeviceIdentityRow[]>(
    "SELECT id, token FROM devices WHERE android_id = ? LIMIT 1",
    [androidId],
  );
  if (androidRows[0] && androidRows[0].id !== id) {
    throw new Error("android_id_conflict");
  }

  const [tokenRows] = await pool.query<DeviceIdentityRow[]>(
    "SELECT id, token FROM devices WHERE token = ? LIMIT 1",
    [token],
  );
  if (tokenRows[0] && tokenRows[0].id !== id) {
    throw new Error("token_conflict");
  }

  let groupId = snapshot.groupId?.trim() || null;
  if (groupId) {
    const group = await getDeviceGroupById(groupId);
    if (!group) {
      groupId = null;
    }
  }

  const [result] = await pool.query<ResultSetHeader>(
    `INSERT INTO devices (
      id, android_id, model, remark, bind_code, group_id, token,
      app_version, brand, locale_language, locale_region, android_version, sdk_int,
      is_online, transferred_to, transferred_at
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, NULL, NULL)
    ON DUPLICATE KEY UPDATE
      android_id = VALUES(android_id),
      model = COALESCE(VALUES(model), model),
      remark = COALESCE(VALUES(remark), remark),
      bind_code = COALESCE(VALUES(bind_code), bind_code),
      group_id = VALUES(group_id),
      token = VALUES(token),
      app_version = COALESCE(VALUES(app_version), app_version),
      brand = COALESCE(VALUES(brand), brand),
      locale_language = COALESCE(VALUES(locale_language), locale_language),
      locale_region = COALESCE(VALUES(locale_region), locale_region),
      android_version = COALESCE(VALUES(android_version), android_version),
      sdk_int = COALESCE(VALUES(sdk_int), sdk_int),
      transferred_to = NULL,
      transferred_at = NULL,
      is_online = 0`,
    [
      id,
      androidId,
      snapshot.model,
      snapshot.remark,
      snapshot.bindCode,
      groupId,
      token,
      snapshot.appVersion,
      snapshot.brand,
      snapshot.localeLanguage,
      snapshot.localeRegion,
      snapshot.androidVersion,
      snapshot.sdkInt,
    ],
  );

  if (result.affectedRows < 1) {
    throw new Error("transfer_upsert_failed");
  }

  const device = await getDeviceById(id);
  if (!device) {
    throw new Error("transfer_upsert_failed");
  }
  return device;
}

export interface DeviceTransferItemResult {
  deviceId: string;
  ok: boolean;
  error?: string;
}

export async function transferDevicesToPeer(input: {
  deviceIds: string[];
  peerServerId: string;
}): Promise<{ peerServerId: string; results: DeviceTransferItemResult[] }> {
  const enabled = await getDeviceTransferEnabled();
  if (enabled !== 1) {
    throw new Error("device_transfer_disabled");
  }

  const peer = await getPeerServerById(input.peerServerId);
  if (!peer || peer.enabled !== 1) {
    throw new Error("peer_not_found_or_disabled");
  }

  const ids = [...new Set(input.deviceIds.map((id) => id.trim()).filter(Boolean))];
  const results: DeviceTransferItemResult[] = [];

  for (const deviceId of ids) {
    results.push(await transferOneDevice(deviceId, peer));
  }

  return { peerServerId: peer.id, results };
}

async function transferOneDevice(
  deviceId: string,
  peer: {
    apiBaseUrl: string;
    wsBaseUrl: string;
    wsScreenBaseUrl: string;
    transferSecret: string;
  },
): Promise<DeviceTransferItemResult> {
  const socket = connectionManager.getDeviceSocket(deviceId);
  if (!socket || socket.readyState !== socket.OPEN) {
    return { deviceId, ok: false, error: "device_offline" };
  }

  const snapshot = await getTransferSnapshot(deviceId);
  if (!snapshot) {
    return { deviceId, ok: false, error: "device_not_found" };
  }

  try {
    await postAcceptTransfer(peer.apiBaseUrl, peer.transferSecret, snapshot);
  } catch (error) {
    const message = error instanceof Error ? error.message : "accept_transfer_failed";
    return { deviceId, ok: false, error: message };
  }

  try {
    const command = await sendCommand({
      deviceId,
      cmd: "switch_server",
      params: {
        apiBaseUrl: peer.apiBaseUrl,
        wsBaseUrl: peer.wsBaseUrl,
        wsScreenBaseUrl: peer.wsScreenBaseUrl,
      },
      waitMs: 8000,
    });
    if (command.status !== "success") {
      return {
        deviceId,
        ok: false,
        error: command.errorMessage || `command_${command.status}`,
      };
    }
  } catch (error) {
    const message = error instanceof Error ? error.message : "switch_server_failed";
    return { deviceId, ok: false, error: message };
  }

  await markDeviceTransferred(deviceId, peer.apiBaseUrl);
  return { deviceId, ok: true };
}

async function postAcceptTransfer(
  apiBaseUrl: string,
  secret: string,
  snapshot: TransferableDeviceSnapshot,
): Promise<void> {
  const url = `${apiBaseUrl.replace(/\/+$/, "")}/api/adm/device/accept-transfer`;
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "X-Transfer-Secret": secret,
    },
    body: JSON.stringify({ device: snapshot }),
  });
  const raw = await response.text();
  let body: { ok?: boolean; message?: string } = {};
  try {
    body = JSON.parse(raw) as { ok?: boolean; message?: string };
  } catch {
    throw new Error(`accept_transfer_non_json:${response.status}`);
  }
  if (!response.ok || body.ok !== true) {
    throw new Error(body.message || `accept_transfer_http_${response.status}`);
  }
}

export async function assertIncomingTransferSecret(headerValue: string | undefined): Promise<boolean> {
  const expected = await getDeviceTransferSecret();
  if (!expected) {
    throw new Error("local_transfer_secret_missing");
  }
  const provided = String(headerValue ?? "").trim();
  return provided.length > 0 && provided === expected;
}
