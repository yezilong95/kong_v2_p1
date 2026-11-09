import type { FastifyInstance } from "fastify";
import {
  createVideo,
  deleteVideo,
  listVideos,
  updateVideo,
} from "../services/video.service.js";

export async function registerVideoRoutes(app: FastifyInstance) {
  /** 管理端：全部视频 */
  app.get("/api/adm/videos", async () => {
    const videos = await listVideos();
    return { ok: true, data: videos };
  });

  /** A 端公开：仅 status=1（开） */
  app.get("/api/device/videos", async () => {
    const videos = await listVideos({ status: 1 });
    return { ok: true, data: videos };
  });

  app.post<{
    Body: {
      name?: string;
      url?: string;
      description?: string;
      status?: number;
      sortOrder?: number;
    };
  }>("/api/adm/videos", async (request, reply) => {
    try {
      const video = await createVideo({
        name: request.body?.name ?? "",
        url: request.body?.url ?? "",
        description: request.body?.description ?? "",
        status: Number(request.body?.status) === 1 ? 1 : 0,
        sortOrder: request.body?.sortOrder,
      });
      return { ok: true, data: video };
    } catch (error) {
      const message = error instanceof Error ? error.message : "video_create_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.patch<{
    Params: { videoId: string };
    Body: {
      name?: string;
      url?: string;
      description?: string;
      status?: number;
      sortOrder?: number;
    };
  }>("/api/adm/videos/:videoId", async (request, reply) => {
    try {
      const videoId = request.params.videoId?.trim();
      if (!videoId) {
        return reply.code(400).send({ ok: false, message: "video_id_required" });
      }
      const patch: {
        name?: string;
        url?: string;
        description?: string;
        status?: 0 | 1;
        sortOrder?: number;
      } = {};
      if (request.body?.name !== undefined) {
        patch.name = request.body.name;
      }
      if (request.body?.url !== undefined) {
        patch.url = request.body.url;
      }
      if (request.body?.description !== undefined) {
        patch.description = request.body.description;
      }
      if (request.body?.status !== undefined) {
        patch.status = Number(request.body.status) === 1 ? 1 : 0;
      }
      if (request.body?.sortOrder !== undefined) {
        patch.sortOrder = request.body.sortOrder;
      }
      const video = await updateVideo(videoId, patch);
      if (!video) {
        return reply.code(404).send({ ok: false, message: "video_not_found" });
      }
      return { ok: true, data: video };
    } catch (error) {
      const message = error instanceof Error ? error.message : "video_update_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Params: { videoId: string } }>(
    "/api/adm/videos/:videoId",
    async (request, reply) => {
      const videoId = request.params.videoId?.trim();
      if (!videoId) {
        return reply.code(400).send({ ok: false, message: "video_id_required" });
      }
      const deleted = await deleteVideo(videoId);
      if (!deleted) {
        return reply.code(404).send({ ok: false, message: "video_not_found" });
      }
      return { ok: true, data: null };
    },
  );
}
