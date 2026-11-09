import type { FastifyRequest } from "fastify";
import type { AdminRole } from "../services/admin-user.service.js";

declare module "fastify" {
  interface FastifyRequest {
    adminUser?: string;
    adminId?: string;
    adminRole?: AdminRole;
  }
}

export type AuthenticatedRequest = FastifyRequest;
