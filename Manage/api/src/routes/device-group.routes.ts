import type { FastifyInstance } from "fastify";
import {
  countAllDevices,
  countUngroupedDevices,
  createDeviceGroup,
  deleteDeviceGroup,
  listDeviceGroups,
  updateDeviceGroup,
  assertGroupExists,
} from "../services/device-group.service.js";

export async function registerDeviceGroupRoutes(app: FastifyInstance) {
  app.get("/api/device-groups", async () => {
    const [groups, ungroupedCount, totalCount] = await Promise.all([
      listDeviceGroups(),
      countUngroupedDevices(),
      countAllDevices(),
    ]);
    return {
      ok: true,
      data: {
        groups,
        ungroupedCount,
        totalCount,
      },
    };
  });

  app.post<{ Body: { name?: string; sortOrder?: number } }>(
    "/api/device-groups",
    async (request, reply) => {
      try {
        const name = request.body?.name?.trim() ?? "";
        if (!name) {
          return reply.code(400).send({ ok: false, message: "group_name_required" });
        }
        const group = await createDeviceGroup(name, request.body?.sortOrder ?? 0);
        return { ok: true, data: group };
      } catch (error) {
        const message = error instanceof Error ? error.message : "group_create_failed";
        const code = (error as { code?: string }).code;
        if (code === "ER_DUP_ENTRY") {
          return reply.code(409).send({ ok: false, message: "group_name_exists" });
        }
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.patch<{
    Params: { groupId: string };
    Body: { name?: string; sortOrder?: number };
  }>("/api/device-groups/:groupId", async (request, reply) => {
    try {
      const groupId = request.params.groupId?.trim();
      if (!groupId) {
        return reply.code(400).send({ ok: false, message: "group_id_required" });
      }
      const group = await updateDeviceGroup(groupId, {
        name: request.body?.name,
        sortOrder: request.body?.sortOrder,
      });
      if (!group) {
        return reply.code(404).send({ ok: false, message: "group_not_found" });
      }
      return { ok: true, data: group };
    } catch (error) {
      const message = error instanceof Error ? error.message : "group_update_failed";
      const code = (error as { code?: string }).code;
      if (code === "ER_DUP_ENTRY") {
        return reply.code(409).send({ ok: false, message: "group_name_exists" });
      }
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Params: { groupId: string } }>(
    "/api/device-groups/:groupId",
    async (request, reply) => {
      const groupId = request.params.groupId?.trim();
      if (!groupId) {
        return reply.code(400).send({ ok: false, message: "group_id_required" });
      }
      const deleted = await deleteDeviceGroup(groupId);
      if (!deleted) {
        return reply.code(404).send({ ok: false, message: "group_not_found" });
      }
      return { ok: true, data: null };
    },
  );
}
