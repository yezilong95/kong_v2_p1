import type { FastifyInstance } from "fastify";
import { resolveUpdateApkParams } from "../services/app-config.service.js";
import {
  getCommand,
  listCommands,
  sendCommand,
} from "../services/command.service.js";
import type { SendCommandBody } from "../types/messages.js";

export async function registerCommandRoutes(app: FastifyInstance) {
  app.post<{ Body: SendCommandBody }>("/api/command/send", async (request, reply) => {
    const deviceId = request.body?.deviceId?.trim();
    const cmd = request.body?.cmd?.trim();

    if (!deviceId || !cmd) {
      return reply.code(400).send({
        ok: false,
        message: "deviceId and cmd are required",
      });
    }

    try {
      const params =
        cmd === "update_apk"
          ? await resolveUpdateApkParams(request.body?.params)
          : request.body?.params;
      const command = await sendCommand({
        deviceId,
        cmd,
        params,
        waitMs: request.body?.waitMs,
      });

      return {
        ok: true,
        data: command,
      };
    } catch (error) {
      const message = error instanceof Error ? error.message : "send_command_failed";
      const statusCode =
        message === "device_not_found"
          ? 404
          : message === "device_offline"
            ? 409
            : message === "client_apk_url_not_configured" ||
                message === "client_apk_url_invalid" ||
                message === "client_apk_update_disabled"
              ? 400
              : 500;
      return reply.code(statusCode).send({
        ok: false,
        message,
      });
    }
  });

  app.get<{ Params: { commandId: string } }>(
    "/api/commands/:commandId",
    async (request, reply) => {
      const command = await getCommand(request.params.commandId);
      if (!command) {
        return reply.code(404).send({
          ok: false,
          message: "command_not_found",
        });
      }

      return {
        ok: true,
        data: command,
      };
    },
  );

  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/commands",
    async (request) => {
      const limit = request.query.limit ? Number(request.query.limit) : 50;
      const commands = await listCommands({
        deviceId: request.query.deviceId,
        limit: Number.isFinite(limit) ? limit : 50,
      });

      return {
        ok: true,
        data: commands,
      };
    },
  );
}
