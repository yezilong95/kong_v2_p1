import type { FastifyInstance } from "fastify";
import {
  getDeviceTransferEnabled,
  getDeviceTransferSecret,
  setDeviceTransferConfig,
} from "../services/app-config.service.js";
import {
  acceptTransferredDevice,
  assertIncomingTransferSecret,
  transferDevicesToPeer,
  type TransferableDeviceSnapshot,
} from "../services/device-transfer.service.js";
import {
  createPeerServer,
  deletePeerServer,
  listPeerServers,
  updatePeerServer,
} from "../services/peer-server.service.js";

function asSnapshot(raw: unknown): TransferableDeviceSnapshot {
  const body = (raw ?? {}) as Record<string, unknown>;
  return {
    id: String(body.id ?? ""),
    androidId: String(body.androidId ?? ""),
    token: String(body.token ?? ""),
    model: body.model == null ? null : String(body.model),
    remark: body.remark == null ? null : String(body.remark),
    bindCode: body.bindCode == null ? null : String(body.bindCode),
    groupId: body.groupId == null ? null : String(body.groupId),
    appVersion: body.appVersion == null ? null : String(body.appVersion),
    brand: body.brand == null ? null : String(body.brand),
    localeLanguage: body.localeLanguage == null ? null : String(body.localeLanguage),
    localeRegion: body.localeRegion == null ? null : String(body.localeRegion),
    androidVersion: body.androidVersion == null ? null : String(body.androidVersion),
    sdkInt: typeof body.sdkInt === "number" && Number.isFinite(body.sdkInt) ? body.sdkInt : null,
  };
}

export async function registerDeviceTransferRoutes(app: FastifyInstance) {
  app.get("/api/adm/config/device-transfer", async () => {
    return {
      ok: true,
      data: {
        key: "device.transfer.enabled",
        enabled: await getDeviceTransferEnabled(),
        hasSecret: Boolean(await getDeviceTransferSecret()),
        secret: await getDeviceTransferSecret(),
      },
    };
  });

  app.put<{ Body: { enabled?: unknown; secret?: unknown } }>(
    "/api/adm/config/device-transfer",
    async (request, reply) => {
      try {
        const data = await setDeviceTransferConfig(request.body ?? {});
        return {
          ok: true,
          data: {
            key: "device.transfer.enabled",
            enabled: data.enabled,
            hasSecret: Boolean(data.secret),
            secret: data.secret,
          },
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "transfer_config_save_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.get("/api/adm/peer-servers", async () => {
    const peers = await listPeerServers();
    return { ok: true, data: { peers } };
  });

  app.post<{
    Body: {
      name?: unknown;
      apiBaseUrl?: unknown;
      wsBaseUrl?: unknown;
      wsScreenBaseUrl?: unknown;
      transferSecret?: unknown;
      enabled?: unknown;
    };
  }>("/api/adm/peer-servers", async (request, reply) => {
    try {
      const peer = await createPeerServer(request.body ?? {});
      return { ok: true, data: peer };
    } catch (error) {
      const message = error instanceof Error ? error.message : "peer_create_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.put<{
    Params: { id: string };
    Body: {
      name?: unknown;
      apiBaseUrl?: unknown;
      wsBaseUrl?: unknown;
      wsScreenBaseUrl?: unknown;
      transferSecret?: unknown;
      enabled?: unknown;
    };
  }>("/api/adm/peer-servers/:id", async (request, reply) => {
    try {
      const peer = await updatePeerServer(request.params.id, request.body ?? {});
      return { ok: true, data: peer };
    } catch (error) {
      const message = error instanceof Error ? error.message : "peer_update_failed";
      const code = message === "peer_not_found" ? 404 : 400;
      return reply.code(code).send({ ok: false, message });
    }
  });

  app.delete<{ Params: { id: string } }>("/api/adm/peer-servers/:id", async (request, reply) => {
    try {
      await deletePeerServer(request.params.id);
      return { ok: true, data: { id: request.params.id } };
    } catch (error) {
      const message = error instanceof Error ? error.message : "peer_delete_failed";
      const code = message === "peer_not_found" ? 404 : 400;
      return reply.code(code).send({ ok: false, message });
    }
  });

  app.post<{ Body: { deviceIds?: unknown; peerServerId?: unknown } }>(
    "/api/adm/device/transfer",
    async (request, reply) => {
      const deviceIds = Array.isArray(request.body?.deviceIds)
        ? request.body.deviceIds.map((item) => String(item))
        : [];
      const peerServerId = String(request.body?.peerServerId ?? "").trim();
      if (deviceIds.length === 0 || !peerServerId) {
        return reply.code(400).send({ ok: false, message: "deviceIds_and_peerServerId_required" });
      }
      try {
        const data = await transferDevicesToPeer({ deviceIds, peerServerId });
        return { ok: true, data };
      } catch (error) {
        const message = error instanceof Error ? error.message : "device_transfer_failed";
        request.log.error({ error, peerServerId }, "device_transfer_failed");
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.post<{ Body: { device?: unknown } }>("/api/adm/device/accept-transfer", async (request, reply) => {
    try {
      const allowed = await assertIncomingTransferSecret(
        String(request.headers["x-transfer-secret"] ?? ""),
      );
      if (!allowed) {
        return reply.code(401).send({ ok: false, message: "transfer_secret_invalid" });
      }
      const device = await acceptTransferredDevice(asSnapshot(request.body?.device));
      return { ok: true, data: { deviceId: device.deviceId, androidId: device.androidId } };
    } catch (error) {
      const message = error instanceof Error ? error.message : "accept_transfer_failed";
      request.log.error({ error }, "accept_transfer_failed");
      const code =
        message === "transfer_secret_invalid" || message === "local_transfer_secret_missing"
          ? 401
          : 400;
      return reply.code(code).send({ ok: false, message });
    }
  });
}
