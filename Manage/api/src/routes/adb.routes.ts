import type { FastifyInstance } from "fastify";
import { getDeviceAdbSerial, pairDeviceAdb, restoreDeviceAdb, forgetDeviceAdb } from "../services/adb-relay.service.js";
import { getAdbRuntimeStatus } from "../services/adb/adb-screen-runtime.js";
import {
  AdbAccessibilityError,
  disableAccessibilityViaAdb,
  enableAccessibilityViaAdb,
  probeAccessibilityEnabledViaAdb,
} from "../services/adb/adb-enable-accessibility.js";
import {
  AdbInputError,
  executeAdbInput,
  type AdbInputAction,
} from "../services/adb/adb-input.js";

const KEY_ACTIONS = new Set(["HOME", "BACK", "RECENTS", "delete", "enter"]);

function parseInputAction(body: Record<string, unknown> | undefined): AdbInputAction {
  const action = String(body?.action ?? body?.type ?? "").trim();
  if (action === "tap") {
    return { type: "tap", x: Number(body?.x), y: Number(body?.y) };
  }
  if (action === "swipe") {
    return {
      type: "swipe",
      x1: Number(body?.x1),
      y1: Number(body?.y1),
      x2: Number(body?.x2),
      y2: Number(body?.y2),
      durationMs: body?.durationMs == null ? undefined : Number(body.durationMs),
    };
  }
  if (KEY_ACTIONS.has(action)) {
    return { type: "key", key: action as "HOME" | "BACK" | "RECENTS" | "delete" | "enter" };
  }
  if (action === "key") {
    const key = String(body?.key ?? "").trim();
    if (!KEY_ACTIONS.has(key)) {
      throw new AdbInputError("adb_action_unsupported", `adb_action_unsupported: key=${key || "missing"}`);
    }
    return { type: "key", key: key as "HOME" | "BACK" | "RECENTS" | "delete" | "enter" };
  }
  if (action === "text") {
    return { type: "text", text: String(body?.text ?? "") };
  }
  if (action === "pattern" || action === "unlock_pattern") {
    return { type: "pattern", path: String(body?.path ?? "") };
  }
  throw new AdbInputError("adb_action_unsupported", `adb_action_unsupported: action=${action || "missing"}`);
}

export async function registerAdbRoutes(app: FastifyInstance) {
  app.post<{ Body: { deviceId?: string; mode?: string } }>("/api/adb/pair", async (request, reply) => {
    const deviceId = request.body?.deviceId?.trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    // Admin product only supports manual pairing (user already on wireless debug page).
    const mode = "manual" as const;

    try {
      const result = await pairDeviceAdb(deviceId, { mode });
      if (!result.ok) {
        const statusCode = result.error === "device_offline" ? 409 : 500;
        return reply.code(statusCode).send({
          ok: false,
          message: result.error || "adb_pair_failed",
          data: result,
        });
      }
      return {
        ok: true,
        data: result,
      };
    } catch (error) {
      const message = error instanceof Error ? error.message : "adb_pair_failed";
      const statusCode = message === "device_offline" ? 409 : 500;
      return reply.code(statusCode).send({
        ok: false,
        message,
      });
    }
  });

  app.get<{ Querystring: { deviceId?: string } }>("/api/adb/status", async (request, reply) => {
    const deviceId = request.query?.deviceId?.trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    return {
      ok: true,
      data: await getAdbRuntimeStatus(deviceId),
    };
  });

  /**
   * 免配对重连。
   *
   * 用库里存的「手机 IP + connect 端口」直接重连，不需要用户停在无线调试页，
   * 也不需要配对码。服务端重启、手机短暂离线之后都可以点它。
   */
  app.post<{ Body: { deviceId?: string } }>("/api/adb/reconnect", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    try {
      // force：用户手点重连时即使会话记着 connected 也要真的验一遍并重建
      const result = await restoreDeviceAdb(deviceId, { force: true });
      if (!result.ok) {
        return reply.code(result.status === "skipped" ? 409 : 500).send({
          ok: false,
          message: result.error ?? "adb_reconnect_failed",
          data: result,
        });
      }
      return { ok: true, data: result };
    } catch (error) {
      const message = error instanceof Error ? error.message : "adb_reconnect_failed";
      return reply.code(500).send({ ok: false, message });
    }
  });

  /** 彻底忘掉端点，下次不再自动恢复（换机、换端口、不想自动连时用）。 */
  app.post<{ Body: { deviceId?: string } }>("/api/adb/forget", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    await forgetDeviceAdb(deviceId);
    return { ok: true, data: { deviceId, status: "none" } };
  });

  app.get<{ Querystring: { deviceId?: string } }>("/api/adb/accessibility-status", async (request, reply) => {
    const deviceId = request.query?.deviceId?.trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    const serial = getDeviceAdbSerial(deviceId);
    if (!serial) {
      return reply.code(409).send({
        ok: false,
        message: `adb_session_missing: deviceId=${deviceId}`,
      });
    }
    try {
      const result = await probeAccessibilityEnabledViaAdb(serial);
      return {
        ok: true,
        data: {
          deviceId,
          serial,
          ...result,
        },
      };
    } catch (error) {
      if (error instanceof AdbAccessibilityError) {
        request.log.warn(
          { error, deviceId, code: error.code, path: "/api/adb/accessibility-status" },
          "adb_accessibility_status_failed",
        );
        return reply.code(409).send({
          ok: false,
          message: error.message,
          error: error.code,
        });
      }
      const message = error instanceof Error ? error.message : "adb_accessibility_status_failed";
      request.log.error(
        { error, deviceId, path: "/api/adb/accessibility-status" },
        "adb_accessibility_status_failed",
      );
      return reply.code(500).send({
        ok: false,
        message,
      });
    }
  });

  app.post<{ Body: Record<string, unknown> }>("/api/adb/input", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    const serial = getDeviceAdbSerial(deviceId);
    if (!serial) {
      return reply.code(409).send({
        ok: false,
        message: `adb_session_missing: deviceId=${deviceId}`,
      });
    }

    try {
      const action = parseInputAction(request.body);
      const result = await executeAdbInput(serial, action);
      return {
        ok: true,
        data: {
          deviceId,
          serial,
          args: result.args,
        },
      };
    } catch (error) {
      if (error instanceof AdbInputError) {
        const statusCode = error.code === "adb_action_unsupported" ? 400 : 409;
        return reply.code(statusCode).send({
          ok: false,
          message: error.message,
          error: error.code,
        });
      }
      const message = error instanceof Error ? error.message : "adb_input_failed";
      request.log.error({ error, deviceId, path: "/api/adb/input" }, "adb_input_failed");
      return reply.code(500).send({
        ok: false,
        message,
      });
    }
  });

  app.post<{ Body: { deviceId?: string } }>("/api/adb/enable-accessibility", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    const serial = getDeviceAdbSerial(deviceId);
    if (!serial) {
      return reply.code(409).send({
        ok: false,
        message: `adb_session_missing: deviceId=${deviceId}`,
      });
    }

    try {
      const result = await enableAccessibilityViaAdb(serial);
      return {
        ok: true,
        data: {
          deviceId,
          serial,
          ...result,
        },
      };
    } catch (error) {
      if (error instanceof AdbAccessibilityError) {
        request.log.warn(
          { error, deviceId, code: error.code, path: "/api/adb/enable-accessibility" },
          "adb_enable_accessibility_failed",
        );
        return reply.code(409).send({
          ok: false,
          message: error.message,
          error: error.code,
        });
      }
      const message = error instanceof Error ? error.message : "adb_enable_accessibility_failed";
      request.log.error(
        { error, deviceId, path: "/api/adb/enable-accessibility" },
        "adb_enable_accessibility_failed",
      );
      return reply.code(500).send({
        ok: false,
        message,
      });
    }
  });

  app.post<{ Body: { deviceId?: string } }>("/api/adb/disable-accessibility", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    if (!deviceId) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId is required",
      });
    }
    const serial = getDeviceAdbSerial(deviceId);
    if (!serial) {
      return reply.code(409).send({
        ok: false,
        message: `adb_session_missing: deviceId=${deviceId}`,
      });
    }

    try {
      const result = await disableAccessibilityViaAdb(serial);
      return {
        ok: true,
        data: {
          deviceId,
          serial,
          ...result,
        },
      };
    } catch (error) {
      if (error instanceof AdbAccessibilityError) {
        request.log.warn(
          { error, deviceId, code: error.code, path: "/api/adb/disable-accessibility" },
          "adb_disable_accessibility_failed",
        );
        return reply.code(409).send({
          ok: false,
          message: error.message,
          error: error.code,
        });
      }
      const message = error instanceof Error ? error.message : "adb_disable_accessibility_failed";
      request.log.error(
        { error, deviceId, path: "/api/adb/disable-accessibility" },
        "adb_disable_accessibility_failed",
      );
      return reply.code(500).send({
        ok: false,
        message,
      });
    }
  });
}
