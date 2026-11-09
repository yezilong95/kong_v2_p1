import type { FastifyInstance, FastifyReply, FastifyRequest } from "fastify";
import { createReadStream, existsSync, statSync } from "node:fs";
import { join, normalize, sep } from "node:path";

const WEI_PAGE_ROOT = join(process.cwd(), "..", "weiPage");

const MIME_BY_EXT: Record<string, string> = {
  ".html": "text/html; charset=utf-8",
  ".css": "text/css; charset=utf-8",
  ".js": "application/javascript; charset=utf-8",
  ".svg": "image/svg+xml",
  ".png": "image/png",
  ".jpg": "image/jpeg",
  ".jpeg": "image/jpeg",
  ".webp": "image/webp",
  ".woff2": "font/woff2",
};

function resolveWeiPagePath(requestPath: string): string | null {
  const relative = requestPath.replace(/^\/public\/wei-page\/?/, "");
  const safeRelative = relative.split("/").filter((part) => part && part !== "." && part !== "..").join(sep);
  const absolute = normalize(join(WEI_PAGE_ROOT, safeRelative));
  if (!absolute.startsWith(normalize(WEI_PAGE_ROOT))) {
    return null;
  }
  return absolute;
}

function pickFilePath(basePath: string): string | null {
  if (existsSync(basePath) && statSync(basePath).isFile()) {
    return basePath;
  }
  const indexPath = join(basePath, "index.html");
  if (existsSync(indexPath) && statSync(indexPath).isFile()) {
    return indexPath;
  }
  return null;
}

export async function registerWeiPageRoutes(app: FastifyInstance) {
  app.get("/public/wei-page/*", async (request: FastifyRequest, reply: FastifyReply) => {
    const pathname = request.url.split("?")[0] ?? request.url;
    const resolved = resolveWeiPagePath(pathname);
    if (!resolved) {
      return reply.code(404).send({ ok: false, message: "wei_page_not_found" });
    }
    const filePath = pickFilePath(resolved);
    if (!filePath) {
      return reply.code(404).send({ ok: false, message: "wei_page_not_found" });
    }
    const ext = filePath.slice(filePath.lastIndexOf(".")).toLowerCase();
    const mime = MIME_BY_EXT[ext] ?? "application/octet-stream";
    reply.header("Content-Type", mime);
    reply.header("Cache-Control", "public, max-age=300");
    return reply.send(createReadStream(filePath));
  });
}
