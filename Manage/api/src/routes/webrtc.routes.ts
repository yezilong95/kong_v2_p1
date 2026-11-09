import type { FastifyInstance } from "fastify";
import { getIceServers } from "../services/webrtc-ice.service.js";

export async function registerWebrtcRoutes(app: FastifyInstance) {
  app.get("/api/webrtc/ice-servers", async () => {
    return {
      ok: true,
      data: {
        iceServers: getIceServers(),
      },
    };
  });
}
