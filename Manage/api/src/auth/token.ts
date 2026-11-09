import crypto from "node:crypto";
import { config } from "../config.js";
import type { AdminRole } from "../services/admin-user.service.js";

const TOKEN_TTL_MS = 24 * 60 * 60 * 1000; // 1 day

export interface AdminTokenPayload {
  /** admin user id */
  sub: string;
  username: string;
  role: AdminRole;
  /** sessionVersion snapshot */
  sv: number;
  exp: number;
}

function encodeSegment(value: unknown): string {
  return Buffer.from(JSON.stringify(value)).toString("base64url");
}

function decodeSegment<T>(segment: string): T {
  return JSON.parse(Buffer.from(segment, "base64url").toString("utf8")) as T;
}

function signSegment(segment: string): string {
  return crypto.createHmac("sha256", config.admin.tokenSecret).update(segment).digest("base64url");
}

export function createAdminToken(input: {
  adminId: string;
  username: string;
  role: AdminRole;
  sessionVersion: number;
}): { token: string; expiresAt: number } {
  const expiresAt = Date.now() + TOKEN_TTL_MS;
  const payload: AdminTokenPayload = {
    sub: input.adminId,
    username: input.username,
    role: input.role,
    sv: input.sessionVersion,
    exp: expiresAt,
  };
  const encoded = encodeSegment(payload);
  const signature = signSegment(encoded);
  return {
    token: `${encoded}.${signature}`,
    expiresAt,
  };
}

export function verifyAdminToken(token: string): AdminTokenPayload | null {
  const trimmed = token.trim();
  if (!trimmed) {
    return null;
  }

  const dotIndex = trimmed.lastIndexOf(".");
  if (dotIndex <= 0) {
    return null;
  }

  const encoded = trimmed.slice(0, dotIndex);
  const signature = trimmed.slice(dotIndex + 1);
  if (signSegment(encoded) !== signature) {
    return null;
  }

  try {
    const payload = decodeSegment<AdminTokenPayload>(encoded);
    if (
      !payload.sub ||
      !payload.username ||
      (payload.role !== "super" && payload.role !== "admin") ||
      typeof payload.sv !== "number" ||
      typeof payload.exp !== "number" ||
      payload.exp < Date.now()
    ) {
      return null;
    }
    return payload;
  } catch {
    return null;
  }
}

export function extractBearerToken(authorizationHeader?: string): string | null {
  if (!authorizationHeader) {
    return null;
  }
  const match = authorizationHeader.match(/^Bearer\s+(.+)$/i);
  return match?.[1]?.trim() ?? null;
}
