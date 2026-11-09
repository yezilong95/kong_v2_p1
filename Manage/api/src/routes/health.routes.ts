import type { FastifyInstance } from "fastify";
import { pingDatabase } from "../db/pool.js";

export async function registerHealthRoutes(app: FastifyInstance) {
  app.get("/health", async () => {
    await pingDatabase();
    return {
      ok: true,
      data: {
        service: "remote-control-api",
        timestamp: Date.now(),
      },
    };
  });
}
