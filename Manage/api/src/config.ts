import "dotenv/config";
import crypto from "node:crypto";

function requireEnv(key: string, fallback?: string): string {
  const value = process.env[key] ?? fallback;
  if (!value) {
    throw new Error(`Missing required environment variable: ${key}`);
  }
  return value;
}

const adminPassword = requireEnv("ADMIN_PASSWORD");
const adminUsername = process.env.ADMIN_USERNAME?.trim() || "admin";
const adminTokenSecret =
  process.env.ADMIN_TOKEN_SECRET?.trim() ||
  crypto.createHash("sha256").update(`${adminUsername}:${adminPassword}:remote-control`).digest("hex");

function parsePositiveInt(value: string | undefined, fallback: number): number {
  const parsed = Number(value);
  if (!Number.isFinite(parsed) || parsed <= 0) {
    return fallback;
  }
  return Math.round(parsed);
}

function parseIceServersFromEnv(): Array<{
  urls: string | string[];
  username?: string;
  credential?: string;
}> {
  const raw = process.env.WEBRTC_ICE_SERVERS_JSON?.trim();
  if (raw) {
    try {
      const parsed = JSON.parse(raw) as unknown;
      if (Array.isArray(parsed)) {
        return parsed as Array<{
          urls: string | string[];
          username?: string;
          credential?: string;
        }>;
      }
    } catch {
      // fall through to defaults
    }
  }

  const servers: Array<{
    urls: string | string[];
    username?: string;
    credential?: string;
  }> = [
    { urls: "stun:stun.l.google.com:19302" },
    { urls: "stun:stun1.l.google.com:19302" },
  ];

  const turnUrls = process.env.WEBRTC_TURN_URLS?.trim();
  const turnUsername = process.env.WEBRTC_TURN_USERNAME?.trim();
  const turnCredential = process.env.WEBRTC_TURN_CREDENTIAL?.trim();
  if (turnUrls) {
    const urls = turnUrls.split(",").map((item) => item.trim()).filter(Boolean);
    if (urls.length > 0) {
      servers.push({
        urls: urls.length === 1 ? urls[0]! : urls,
        ...(turnUsername ? { username: turnUsername } : {}),
        ...(turnCredential ? { credential: turnCredential } : {}),
      });
    }
  }

  return servers;
}

export const config = {
  host: process.env.HOST ?? "0.0.0.0",
  port: Number(process.env.PORT ?? 3000),
  admin: {
    username: adminUsername,
    password: adminPassword,
    tokenSecret: adminTokenSecret,
  },
  db: {
    host: requireEnv("DB_HOST", "127.0.0.1"),
    port: Number(process.env.DB_PORT ?? 3306),
    user: requireEnv("DB_USER", "root"),
    password: process.env.DB_PASSWORD ?? "",
    database: requireEnv("DB_NAME", "remote_control"),
  },
  ai: {
    /** APIYI chat/completions 单次请求超时（毫秒），默认 5 分钟 */
    requestTimeoutMs: parsePositiveInt(process.env.AI_REQUEST_TIMEOUT_MS, 300_000),
  },
  webrtc: {
    iceServers: parseIceServersFromEnv(),
  },
};
