import {
  createAiTraceId,
  maskApiKey,
  saveAiTrace,
  trimRaw,
  type AiChatScene,
  type AiTraceRecord,
} from "./ai-trace.service.js";
import { config } from "../config.js";

export interface ChatCompletionInput {
  baseUrl: string;
  apiKey: string;
  model: string;
  messages: Array<{ role: string; content: string }>;
  scene?: AiChatScene;
  timeoutMs?: number;
  temperature?: number;
}

function buildErrorMessage(input: {
  httpStatus: number;
  rawText: string;
  payload: Record<string, unknown>;
  traceId: string;
}): string {
  const { httpStatus, rawText, payload, traceId } = input;
  const looksLikeHtml = /^\s*</.test(rawText);
  if (looksLikeHtml || Object.keys(payload).length === 0) {
    const preview = rawText.replace(/\s+/g, " ").trim().slice(0, 160);
    return `apiyi_non_json_response: HTTP ${httpStatus}, trace=${traceId}, preview=${preview || "(empty)"}`;
  }
  const errMsg =
    typeof payload.error === "object" &&
    payload.error &&
    typeof (payload.error as { message?: unknown }).message === "string"
      ? String((payload.error as { message: string }).message)
      : rawText.slice(0, 240) || `http_${httpStatus}`;
  return `apiyi_request_failed: ${errMsg} (trace=${traceId})`;
}

export async function chatCompletion(input: ChatCompletionInput): Promise<string> {
  const scene = input.scene ?? "unknown";
  const traceId = createAiTraceId(scene);
  const startedAt = Date.now();
  const endpoint = `${input.baseUrl.replace(/\/+$/, "")}/chat/completions`;
  const requestBody = {
    model: input.model,
    temperature: input.temperature ?? 0.1,
    messages: input.messages,
  };

  const traceBase: Omit<AiTraceRecord, "durationMs" | "response"> = {
    id: traceId,
    scene,
    createdAt: new Date().toISOString(),
    request: {
      endpoint,
      model: input.model,
      apiKeyMasked: maskApiKey(input.apiKey),
      temperature: requestBody.temperature,
      messages: input.messages,
    },
  };

  const controller = new AbortController();
  const timer = setTimeout(
    () => controller.abort(),
    input.timeoutMs ?? config.ai.requestTimeoutMs,
  );

  try {
    const response = await fetch(endpoint, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${input.apiKey}`,
      },
      body: JSON.stringify(requestBody),
      signal: controller.signal,
    });

    const rawText = await response.text();
    const trimmedRaw = trimRaw(rawText);
    let payload: Record<string, unknown> = {};
    let parsed: unknown | null = null;
    try {
      parsed = JSON.parse(rawText) as unknown;
      if (parsed && typeof parsed === "object" && !Array.isArray(parsed)) {
        payload = parsed as Record<string, unknown>;
      }
    } catch {
      parsed = null;
    }

    const durationMs = Date.now() - startedAt;

    if (!response.ok) {
      const error = buildErrorMessage({
        httpStatus: response.status,
        rawText: trimmedRaw,
        payload,
        traceId,
      });
      await saveAiTrace({
        ...traceBase,
        durationMs,
        response: {
          httpStatus: response.status,
          ok: false,
          rawText: trimmedRaw,
          parsed,
          assistantContent: "",
          error,
        },
      });
      throw new Error(error);
    }

    if (!parsed || !payload.choices) {
      const error = buildErrorMessage({
        httpStatus: response.status,
        rawText: trimmedRaw,
        payload,
        traceId,
      });
      await saveAiTrace({
        ...traceBase,
        durationMs,
        response: {
          httpStatus: response.status,
          ok: false,
          rawText: trimmedRaw,
          parsed,
          assistantContent: "",
          error,
        },
      });
      throw new Error(error);
    }

    const choices = payload.choices;
    if (!Array.isArray(choices) || choices.length === 0) {
      const error = `apiyi_empty_choices (trace=${traceId})`;
      await saveAiTrace({
        ...traceBase,
        durationMs,
        response: {
          httpStatus: response.status,
          ok: false,
          rawText: trimmedRaw,
          parsed,
          assistantContent: "",
          error,
        },
      });
      throw new Error(error);
    }

    const message = (choices[0] as { message?: { content?: unknown } })?.message;
    const content = String(message?.content ?? "").trim();
    if (!content) {
      const error = `apiyi_empty_content (trace=${traceId})`;
      await saveAiTrace({
        ...traceBase,
        durationMs,
        response: {
          httpStatus: response.status,
          ok: false,
          rawText: trimmedRaw,
          parsed,
          assistantContent: "",
          error,
        },
      });
      throw new Error(error);
    }

    await saveAiTrace({
      ...traceBase,
      durationMs,
      response: {
        httpStatus: response.status,
        ok: true,
        rawText: trimmedRaw,
        parsed,
        assistantContent: content,
        error: null,
      },
    });

    return content;
  } catch (error) {
    const durationMs = Date.now() - startedAt;
    if (error instanceof Error && error.message.includes("trace=")) {
      throw error;
    }
    const message =
      error instanceof Error
        ? error.name === "AbortError"
          ? `apiyi_request_timeout (trace=${traceId})`
          : `${error.message} (trace=${traceId})`
        : `apiyi_request_failed (trace=${traceId})`;
    await saveAiTrace({
      ...traceBase,
      durationMs,
      response: {
        httpStatus: 0,
        ok: false,
        rawText: "",
        parsed: null,
        assistantContent: "",
        error: message,
      },
    });
    throw new Error(message);
  } finally {
    clearTimeout(timer);
  }
}
