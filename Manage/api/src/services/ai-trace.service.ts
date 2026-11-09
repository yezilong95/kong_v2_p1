import fs from "node:fs/promises";
import path from "node:path";
import crypto from "node:crypto";

const TRACE_DIR = process.env.AI_TRACE_DIR?.trim() || path.join(process.cwd(), "logs", "ai");
const MAX_RAW_CHARS = 80_000;
const MAX_KEEP_FILES = 300;

export type AiChatScene =
  | "test_connection"
  | "analyze_finance_apps"
  | "extract_accounts"
  | "extract_passwords"
  | "phone_agent_plan"
  | "ui_translate"
  | "unknown";

export interface AiTraceRecord {
  id: string;
  scene: AiChatScene;
  createdAt: string;
  durationMs: number;
  request: {
    endpoint: string;
    model: string;
    apiKeyMasked: string;
    temperature: number;
    messages: Array<{ role: string; content: string }>;
  };
  response: {
    httpStatus: number;
    ok: boolean;
    rawText: string;
    parsed: unknown | null;
    assistantContent: string;
    error: string | null;
  };
}

export interface AiTraceListItem {
  id: string;
  scene: AiChatScene;
  createdAt: string;
  durationMs: number;
  model: string;
  httpStatus: number;
  ok: boolean;
  error: string | null;
  preview: string;
}

function traceFilePath(id: string): string {
  const safe = id.replace(/[^a-zA-Z0-9._-]/g, "");
  return path.join(TRACE_DIR, `${safe}.json`);
}

export function maskApiKey(apiKey: string): string {
  const trimmed = apiKey.trim();
  if (!trimmed) {
    return "";
  }
  if (trimmed.length <= 8) {
    return "****";
  }
  return `${trimmed.slice(0, 4)}****${trimmed.slice(-4)}`;
}

async function ensureTraceDir(): Promise<void> {
  await fs.mkdir(TRACE_DIR, { recursive: true });
}

function trimRaw(text: string): string {
  if (text.length <= MAX_RAW_CHARS) {
    return text;
  }
  return `${text.slice(0, MAX_RAW_CHARS)}\n...[truncated ${text.length - MAX_RAW_CHARS} chars]`;
}

function buildTraceId(scene: AiChatScene): string {
  const now = new Date();
  const stamp = now.toISOString().replace(/[-:TZ.]/g, "").slice(0, 14);
  const rand = crypto.randomBytes(3).toString("hex");
  return `${stamp}-${scene}-${rand}`;
}

async function pruneOldTraces(): Promise<void> {
  try {
    const names = await fs.readdir(TRACE_DIR);
    const files = names.filter((name) => name.endsWith(".json"));
    if (files.length <= MAX_KEEP_FILES) {
      return;
    }
    const stats = await Promise.all(
      files.map(async (name) => {
        const full = path.join(TRACE_DIR, name);
        const stat = await fs.stat(full);
        return { full, mtimeMs: stat.mtimeMs };
      }),
    );
    stats.sort((a, b) => b.mtimeMs - a.mtimeMs);
    const toDelete = stats.slice(MAX_KEEP_FILES);
    await Promise.all(toDelete.map((item) => fs.unlink(item.full).catch(() => undefined)));
  } catch {
    // ignore prune errors
  }
}

export async function saveAiTrace(record: AiTraceRecord): Promise<void> {
  await ensureTraceDir();
  const file = traceFilePath(record.id);
  await fs.writeFile(file, JSON.stringify(record, null, 2), "utf8");
  void pruneOldTraces();
}

export function createAiTraceId(scene: AiChatScene): string {
  return buildTraceId(scene);
}

export async function listAiTraces(limit = 50): Promise<AiTraceListItem[]> {
  await ensureTraceDir();
  const names = await fs.readdir(TRACE_DIR);
  const files = names.filter((name) => name.endsWith(".json"));
  const stats = await Promise.all(
    files.map(async (name) => {
      const full = path.join(TRACE_DIR, name);
      const stat = await fs.stat(full);
      return { name, full, mtimeMs: stat.mtimeMs };
    }),
  );
  stats.sort((a, b) => b.mtimeMs - a.mtimeMs);
  const picked = stats.slice(0, Math.max(1, Math.min(limit, 200)));

  const items: AiTraceListItem[] = [];
  for (const file of picked) {
    try {
      const raw = await fs.readFile(file.full, "utf8");
      const record = JSON.parse(raw) as AiTraceRecord;
      items.push({
        id: record.id,
        scene: record.scene,
        createdAt: record.createdAt,
        durationMs: record.durationMs,
        model: record.request?.model ?? "",
        httpStatus: record.response?.httpStatus ?? 0,
        ok: Boolean(record.response?.ok),
        error: record.response?.error ?? null,
        preview:
          record.response?.assistantContent?.slice(0, 160) ||
          record.response?.rawText?.slice(0, 160) ||
          "",
      });
    } catch {
      items.push({
        id: file.name.replace(/\.json$/, ""),
        scene: "unknown",
        createdAt: new Date(file.mtimeMs).toISOString(),
        durationMs: 0,
        model: "",
        httpStatus: 0,
        ok: false,
        error: "trace_parse_failed",
        preview: "",
      });
    }
  }
  return items;
}

export async function getAiTrace(id: string): Promise<AiTraceRecord | null> {
  try {
    const raw = await fs.readFile(traceFilePath(id), "utf8");
    return JSON.parse(raw) as AiTraceRecord;
  } catch {
    return null;
  }
}

export function getAiTraceDir(): string {
  return TRACE_DIR;
}

export { trimRaw };
