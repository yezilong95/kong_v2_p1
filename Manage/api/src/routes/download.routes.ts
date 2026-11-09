import type { FastifyInstance } from "fastify";
import { createReadStream, existsSync } from "node:fs";
import { join } from "node:path";

const APK_PATH = join(process.cwd(), "downloads", "client.apk");

export async function registerDownloadRoutes(app: FastifyInstance) {
  app.get("/downloads/client.apk", async (_request, reply) => {
    if (!existsSync(APK_PATH)) {
      return reply.code(404).send({
        ok: false,
        message: "apk_not_found",
        hint: "Put release APK at Manage/api/downloads/client.apk",
      });
    }

    reply.header("Content-Type", "application/vnd.android.package-archive");
    reply.header("Content-Disposition", 'attachment; filename="client.apk"');
    return reply.send(createReadStream(APK_PATH));
  });
}
