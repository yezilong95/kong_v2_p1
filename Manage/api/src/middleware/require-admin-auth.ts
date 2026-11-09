import type { FastifyReply, FastifyRequest } from "fastify";
import { extractBearerToken, verifyAdminToken } from "../auth/token.js";
import type { AdminRole } from "../services/admin-user.service.js";
import { validateAdminSession } from "../services/admin-user.service.js";

export function resolveAdminToken(request: FastifyRequest): string | null {
  const bearer = extractBearerToken(request.headers.authorization);
  if (bearer) {
    return bearer;
  }

  const queryToken = (request.query as { token?: string }).token;
  if (typeof queryToken === "string" && queryToken.trim()) {
    return queryToken.trim();
  }

  return null;
}

export async function requireAdminAuth(
  request: FastifyRequest,
  reply: FastifyReply,
): Promise<boolean> {
  const token = resolveAdminToken(request);
  const payload = token ? verifyAdminToken(token) : null;
  if (!payload) {
    reply.code(401).send({
      ok: false,
      message: "unauthorized",
    });
    return false;
  }

  try {
    const result = await validateAdminSession(payload.sub, payload.sv);
    if (!result.ok) {
      reply.code(401).send({
        ok: false,
        message: result.reason,
      });
      return false;
    }

    request.adminUser = result.admin.username;
    request.adminId = result.admin.id;
    request.adminRole = result.admin.role;
    return true;
  } catch (error) {
    request.log.error({ error }, "admin_auth_validate_failed");
    reply.code(401).send({
      ok: false,
      message: "unauthorized",
    });
    return false;
  }
}

export function requireSuperAdmin(request: FastifyRequest, reply: FastifyReply): boolean {
  if (request.adminRole !== "super") {
    reply.code(403).send({
      ok: false,
      message: "forbidden_super_only",
    });
    return false;
  }
  return true;
}

export type { AdminRole };
