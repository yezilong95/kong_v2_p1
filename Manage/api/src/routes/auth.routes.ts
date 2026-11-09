import type { FastifyInstance } from "fastify";
import { createAdminToken, extractBearerToken, verifyAdminToken } from "../auth/token.js";
import {
  assertAdminCanLogin,
  bumpSessionVersion,
  getAdminByUsername,
  validateAdminSession,
  verifyPassword,
} from "../services/admin-user.service.js";
import {
  clearLoginFailCounter,
  isLoginBlacklisted,
  recordLoginFailure,
} from "../services/login-blacklist.service.js";
import { resolveClientIp } from "../utils/client-ip.js";

interface LoginBody {
  username?: string;
  password?: string;
}

export async function registerAuthRoutes(app: FastifyInstance) {
  app.post<{ Body: LoginBody }>("/api/auth/login", async (request, reply) => {
    const username = request.body?.username?.trim() ?? "";
    const password = request.body?.password ?? "";
    const clientIp = resolveClientIp(request);

    if (!username || !password) {
      return reply.code(400).send({
        ok: false,
        message: "username_and_password_required",
      });
    }

    if (await isLoginBlacklisted(username, clientIp)) {
      request.log.warn({ username, clientIp }, "[auth] login blocked by blacklist");
      return reply.code(401).send({
        ok: false,
        message: "login_blacklisted",
      });
    }

    const admin = await getAdminByUsername(username);
    if (!admin || !(await verifyPassword(password, admin.passwordHash))) {
      if (admin) {
        const blacklisted = await recordLoginFailure(admin.username, clientIp);
        if (blacklisted) {
          request.log.warn(
            { username: admin.username, clientIp },
            "[auth] login blacklisted after consecutive failures",
          );
          return reply.code(401).send({
            ok: false,
            message: "login_blacklisted",
          });
        }
      }
      return reply.code(401).send({
        ok: false,
        message: "invalid_credentials",
      });
    }

    const loginState = await assertAdminCanLogin(admin);
    if (loginState !== "ok") {
      return reply.code(401).send({
        ok: false,
        message: loginState,
      });
    }

    await clearLoginFailCounter(admin.username);

    const sessionVersion = await bumpSessionVersion(admin.id);
    const session = createAdminToken({
      adminId: admin.id,
      username: admin.username,
      role: admin.role,
      sessionVersion,
    });

    return {
      ok: true,
      data: {
        token: session.token,
        expiresAt: session.expiresAt,
        username: admin.username,
        role: admin.role,
        accountExpiresAt: admin.expiresAt,
      },
    };
  });

  app.get("/api/auth/me", async (request, reply) => {
    const token = extractBearerToken(request.headers.authorization);
    const payload = token ? verifyAdminToken(token) : null;
    if (!payload) {
      return reply.code(401).send({
        ok: false,
        message: "unauthorized",
      });
    }

    const result = await validateAdminSession(payload.sub, payload.sv);
    if (!result.ok) {
      return reply.code(401).send({
        ok: false,
        message: result.reason,
      });
    }

    return {
      ok: true,
      data: {
        id: result.admin.id,
        username: result.admin.username,
        role: result.admin.role,
        expiresAt: payload.exp,
        accountExpiresAt: result.admin.expiresAt,
        isLocked: result.admin.isLocked,
      },
    };
  });
}
