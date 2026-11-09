import type { FastifyInstance } from "fastify";
import { requireSuperAdmin } from "../middleware/require-admin-auth.js";
import {
  createAdminUser,
  deleteAdminUser,
  listAdmins,
  normalizeRenewMonths,
  renewAdminUser,
  setAdminStatus,
  updateAdminPassword,
} from "../services/admin-user.service.js";

function mapAdminError(message: string): { code: number; message: string } {
  switch (message) {
    case "username_invalid":
    case "password_too_short":
    case "username_taken":
      return { code: 400, message };
    case "admin_not_found":
      return { code: 404, message };
    case "super_no_renew":
    case "cannot_lock_super":
    case "cannot_delete_super":
    case "cannot_delete_self":
      return { code: 400, message };
    default:
      return { code: 500, message };
  }
}

export async function registerAdminUserRoutes(app: FastifyInstance) {
  app.get("/api/adm/admins", async (request, reply) => {
    if (!requireSuperAdmin(request, reply)) {
      return;
    }
    const items = await listAdmins();
    return { ok: true, data: { items } };
  });

  app.post<{
    Body: { username?: string; password?: string; months?: number };
  }>("/api/adm/admins", async (request, reply) => {
    if (!requireSuperAdmin(request, reply)) {
      return;
    }
    const months = normalizeRenewMonths(request.body?.months);
    if (!months) {
      return reply.code(400).send({ ok: false, message: "months_invalid" });
    }
    try {
      const admin = await createAdminUser({
        username: request.body?.username ?? "",
        password: request.body?.password ?? "",
        months,
        createdBy: request.adminId ?? "",
      });
      return { ok: true, data: admin };
    } catch (error) {
      const message = error instanceof Error ? error.message : "admin_create_failed";
      const mapped = mapAdminError(message);
      return reply.code(mapped.code).send({ ok: false, message: mapped.message });
    }
  });

  app.post<{
    Params: { adminId: string };
    Body: { months?: number };
  }>("/api/adm/admins/:adminId/renew", async (request, reply) => {
    if (!requireSuperAdmin(request, reply)) {
      return;
    }
    const months = normalizeRenewMonths(request.body?.months);
    if (!months) {
      return reply.code(400).send({ ok: false, message: "months_invalid" });
    }
    try {
      const admin = await renewAdminUser({
        adminId: request.params.adminId,
        months,
        operatorId: request.adminId ?? "",
      });
      return { ok: true, data: admin };
    } catch (error) {
      const message = error instanceof Error ? error.message : "admin_renew_failed";
      const mapped = mapAdminError(message);
      return reply.code(mapped.code).send({ ok: false, message: mapped.message });
    }
  });

  app.patch<{
    Params: { adminId: string };
    Body: { status?: number; password?: string };
  }>("/api/adm/admins/:adminId", async (request, reply) => {
    if (!requireSuperAdmin(request, reply)) {
      return;
    }
    const adminId = request.params.adminId;
    try {
      if (request.body?.password != null) {
        const admin = await updateAdminPassword(adminId, request.body.password);
        return { ok: true, data: admin };
      }
      if (request.body?.status === 0 || request.body?.status === 1) {
        const admin = await setAdminStatus(adminId, request.body.status);
        return { ok: true, data: admin };
      }
      return reply.code(400).send({ ok: false, message: "nothing_to_update" });
    } catch (error) {
      const message = error instanceof Error ? error.message : "admin_update_failed";
      const mapped = mapAdminError(message);
      return reply.code(mapped.code).send({ ok: false, message: mapped.message });
    }
  });

  app.delete<{ Params: { adminId: string } }>(
    "/api/adm/admins/:adminId",
    async (request, reply) => {
      if (!requireSuperAdmin(request, reply)) {
        return;
      }
      try {
        await deleteAdminUser(request.params.adminId, request.adminId ?? "");
        return { ok: true, data: null };
      } catch (error) {
        const message = error instanceof Error ? error.message : "admin_delete_failed";
        const mapped = mapAdminError(message);
        return reply.code(mapped.code).send({ ok: false, message: mapped.message });
      }
    },
  );
}
