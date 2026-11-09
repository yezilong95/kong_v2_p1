import type { FastifyInstance, FastifyReply, FastifyRequest } from "fastify";
import { resolveClientIp } from "../utils/client-ip.js";
import { parseDeviceModelFromUa } from "../utils/device-model.js";
import { createApkDownloadLog } from "../services/apk-download.service.js";
import {
  DEFAULT_LANDING_PAGE_KEY,
  recordLandingPageView,
} from "../services/landing-view.service.js";

interface SubmitLandingViewBody {
  pageKey?: string;
}

interface LandingViewQuery {
  pageKey?: string;
}

interface LandingApkDownloadQuery {
  apkUrl?: string;
  pageKey?: string;
  model?: string;
}

async function recordViewFromRequest(
  request: FastifyRequest<{
    Body?: SubmitLandingViewBody;
    Querystring?: LandingViewQuery;
  }>,
): Promise<void> {
  const pageKey =
    request.body?.pageKey ??
    request.query?.pageKey ??
    DEFAULT_LANDING_PAGE_KEY;
  const ua = request.headers["user-agent"];
  await recordLandingPageView({
    pageKey,
    clientIp: resolveClientIp(request),
    userAgent: typeof ua === "string" ? ua : null,
  });
}

export async function registerLandingRoutes(app: FastifyInstance) {
  // GET：落地页 Image 像素上报，无 CORS 预检
  app.get<{ Querystring: LandingViewQuery }>(
    "/api/public/landing-view",
    async (request, reply: FastifyReply) => {
      try {
        await recordViewFromRequest(request);
        return reply.code(204).send();
      } catch (error) {
        request.log.error({ err: error }, "landing_view_record_failed");
        return reply.code(500).send({
          ok: false,
          message: error instanceof Error ? error.message : "landing_view_failed",
        });
      }
    },
  );

  app.post<{ Body: SubmitLandingViewBody }>(
    "/api/public/landing-view",
    async (request, reply: FastifyReply) => {
      try {
        await recordViewFromRequest(request);
        return { ok: true, data: { recorded: true } };
      } catch (error) {
        request.log.error({ err: error }, "landing_view_record_failed");
        return reply.code(500).send({
          ok: false,
          message: error instanceof Error ? error.message : "landing_view_failed",
        });
      }
    },
  );

  // GET：落地页点击下载 → 写入 apk_download_logs（像素上报，避 CORS）
  app.get<{ Querystring: LandingApkDownloadQuery }>(
    "/api/public/apk-download",
    async (request, reply: FastifyReply) => {
      try {
        const pageKey = (request.query.pageKey || DEFAULT_LANDING_PAGE_KEY).trim().slice(0, 64);
        const apkUrl = (request.query.apkUrl || "").trim().slice(0, 1024) || null;
        const ua = typeof request.headers["user-agent"] === "string"
          ? request.headers["user-agent"]
          : null;
        const fromQuery = (request.query.model || "").trim().slice(0, 128);
        const model =
          fromQuery ||
          parseDeviceModelFromUa(ua) ||
          "unknown";
        await createApkDownloadLog({
          apkUrl,
          installerPackage: `landing:${pageKey}`,
          model,
          status: "success",
          clientIp: resolveClientIp(request),
          userAgent: ua,
        });
        return reply.code(204).send();
      } catch (error) {
        request.log.error({ err: error }, "landing_apk_download_log_failed");
        return reply.code(500).send({
          ok: false,
          message: error instanceof Error ? error.message : "apk_download_log_failed",
        });
      }
    },
  );
}
