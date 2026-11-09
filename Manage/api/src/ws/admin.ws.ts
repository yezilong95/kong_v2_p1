import type { FastifyInstance } from "fastify";
import type { WebSocket } from "@fastify/websocket";
import { listDevices } from "../services/device.service.js";
import { connectionManager } from "./connection-manager.js";

export async function registerAdminWsRoutes(app: FastifyInstance) {
  app.get("/ws/admin", { websocket: true }, (socket: WebSocket) => {
    connectionManager.addAdmin(socket);

    void (async () => {
      const devices = await listDevices();
      socket.send(
        JSON.stringify({
          type: "snapshot",
          payload: {
            devices,
            onlineDeviceIds: connectionManager.getOnlineDeviceIds(),
          },
          timestamp: Date.now(),
        }),
      );
    })();

    socket.on("close", () => {
      connectionManager.removeAdmin(socket);
    });
  });
}
