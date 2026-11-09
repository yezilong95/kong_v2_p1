import type { FastifyRequest } from "fastify";

function normalizeIp(raw: string | undefined | null): string | null {
  if (!raw) {
    return null;
  }
  const ip = raw.replace(/^::ffff:/i, "").trim();
  if (!ip) {
    return null;
  }
  // 本机回环 / 未指定：绝不当成终端 IP 入库
  if (
    ip === "127.0.0.1" ||
    ip === "::1" ||
    ip === "0.0.0.0" ||
    ip === "::" ||
    ip.startsWith("127.")
  ) {
    return null;
  }
  return ip.slice(0, 64);
}

/**
 * 解析真实客户端 IP：优先反代头。
 * 反代未带头时返回 null（不要写 127.0.0.1）。
 */
export function resolveClientIp(request: FastifyRequest): string | null {
  const header = (name: string): string | undefined => {
    const raw = request.headers[name];
    if (Array.isArray(raw)) {
      return raw[0];
    }
    return typeof raw === "string" ? raw : undefined;
  };

  const candidates = [
    header("x-forwarded-for")?.split(",")[0]?.trim(),
    header("x-real-ip")?.trim(),
    header("cf-connecting-ip")?.trim(),
    request.ip,
    request.socket.remoteAddress,
  ];

  for (const raw of candidates) {
    const ip = normalizeIp(raw);
    if (ip) {
      return ip;
    }
  }
  return null;
}

export function normalizeClientIp(raw: string | undefined | null): string | null {
  return normalizeIp(raw);
}
