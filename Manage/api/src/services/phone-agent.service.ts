import { getAiConfigStored } from "./app-config.service.js";
import { chatCompletion } from "./ai-chat-client.js";

const MAX_HISTORY = 30;
const MAX_GOAL_LEN = 500;

export type PhoneAgentActionType =
  | "click"
  | "swipe"
  | "input_text"
  | "open_app"
  | "global_action"
  | "wake_screen"
  | "wait"
  | "done"
  | "failed";

export interface PhoneAgentUiNodeInput {
  i: number;
  text?: string;
  desc?: string;
  clickable?: boolean;
  focusable?: boolean;
  bounds: { left: number; top: number; right: number; bottom: number };
  packageName?: string;
}

export interface PhoneAgentHistoryStep {
  stepIndex: number;
  action: PhoneAgentActionType;
  reason: string;
  params: Record<string, unknown>;
  result?: string;
}

export interface PhoneAgentStepPlan {
  action: PhoneAgentActionType;
  reason: string;
  confidence: number;
  params: Record<string, unknown>;
}

const SYSTEM_PROMPT = `You are an Android phone automation planner. Plan EXACTLY ONE next step toward the user's goal.
Respond with ONLY one JSON object (no markdown):
{
  "action": "click"|"swipe"|"input_text"|"open_app"|"global_action"|"wake_screen"|"wait"|"done"|"failed",
  "reason": "short Chinese explanation",
  "confidence": 0.0-1.0,
  "params": {}
}

Action params:
- click: {"nodeIndex": number} prefer node from UI list; or {"x": number, "y": number}
- swipe: {"x1": number, "y1": number, "x2": number, "y2": number, "durationMs": number optional}
- input_text: {"text": string}
- open_app: {"packageName": string}
- global_action: {"action": "back"|"home"|"recents"}
- wake_screen: {}
- wait: {"ms": number} only when UI is loading, max 3000
- done: {"summary": string} when goal achieved
- failed: {"reason": string} when impossible

Rules:
- One step only. Do not chain actions.
- Prefer clickable nodes (nodeIndex) over raw coordinates.
- Use global_action back/home when stuck on wrong screen.
- Use wake_screen if keyguardLocked is true.
- Never invent package names not in context.
- If goal is done, use done. If blocked, use failed with reason.`;

function extractJsonObject(text: string): Record<string, unknown> {
  const trimmed = text.trim();
  if (!trimmed) {
    throw new Error("ai_response_empty");
  }
  try {
    const parsed = JSON.parse(trimmed) as unknown;
    if (parsed && typeof parsed === "object" && !Array.isArray(parsed)) {
      return parsed as Record<string, unknown>;
    }
  } catch {
    // fall through
  }
  const start = trimmed.indexOf("{");
  const end = trimmed.lastIndexOf("}");
  if (start >= 0 && end > start) {
    const parsed = JSON.parse(trimmed.slice(start, end + 1)) as unknown;
    if (parsed && typeof parsed === "object" && !Array.isArray(parsed)) {
      return parsed as Record<string, unknown>;
    }
  }
  throw new Error("ai_response_not_json");
}

function clampConfidence(value: unknown): number {
  const num = Number(value);
  if (!Number.isFinite(num)) {
    return 0;
  }
  return Math.min(1, Math.max(0, num));
}

function readInt(value: unknown): number | null {
  const num = Number(value);
  if (!Number.isFinite(num)) {
    return null;
  }
  return Math.round(num);
}

const ALLOWED_ACTIONS = new Set<PhoneAgentActionType>([
  "click",
  "swipe",
  "input_text",
  "open_app",
  "global_action",
  "wake_screen",
  "wait",
  "done",
  "failed",
]);

const GLOBAL_ACTIONS = new Set(["back", "home", "recents"]);

function validatePlan(
  raw: Record<string, unknown>,
  input: {
    screenWidth: number;
    screenHeight: number;
    nodes: PhoneAgentUiNodeInput[];
    keyguardLocked?: boolean;
  },
): PhoneAgentStepPlan {
  const action = String(raw.action ?? "").trim() as PhoneAgentActionType;
  if (!ALLOWED_ACTIONS.has(action)) {
    throw new Error("phone_agent_invalid_action");
  }

  const reason = String(raw.reason ?? "").trim().slice(0, 300) || "下一步";
  const confidence = clampConfidence(raw.confidence);
  const paramsRaw = raw.params;
  const params =
    paramsRaw && typeof paramsRaw === "object" && !Array.isArray(paramsRaw)
      ? (paramsRaw as Record<string, unknown>)
      : {};

  const { screenWidth, screenHeight } = input;

  function inScreen(x: number, y: number): boolean {
    return x >= 0 && y >= 0 && x <= screenWidth && y <= screenHeight;
  }

  if (action === "click") {
    const nodeIndex = readInt(params.nodeIndex);
    if (nodeIndex != null) {
      const node = input.nodes.find((item) => item.i === nodeIndex);
      if (!node) {
        throw new Error("phone_agent_invalid_node_index");
      }
      return { action, reason, confidence, params: { nodeIndex } };
    }
    const x = readInt(params.x);
    const y = readInt(params.y);
    if (x == null || y == null || !inScreen(x, y)) {
      throw new Error("phone_agent_invalid_click");
    }
    return { action, reason, confidence, params: { x, y } };
  }

  if (action === "swipe") {
    const x1 = readInt(params.x1);
    const y1 = readInt(params.y1);
    const x2 = readInt(params.x2);
    const y2 = readInt(params.y2);
    if (x1 == null || y1 == null || x2 == null || y2 == null) {
      throw new Error("phone_agent_invalid_swipe");
    }
    if (!inScreen(x1, y1) || !inScreen(x2, y2)) {
      throw new Error("phone_agent_swipe_out_of_bounds");
    }
    const durationMs = readInt(params.durationMs) ?? 320;
    return {
      action,
      reason,
      confidence,
      params: { x1, y1, x2, y2, durationMs: Math.min(2000, Math.max(80, durationMs)) },
    };
  }

  if (action === "input_text") {
    const text = String(params.text ?? "");
    if (!text.trim()) {
      throw new Error("phone_agent_input_text_empty");
    }
    return { action, reason, confidence, params: { text: text.slice(0, 500) } };
  }

  if (action === "open_app") {
    const packageName = String(params.packageName ?? "").trim();
    if (!packageName || !/^[a-zA-Z][\w.]*$/.test(packageName)) {
      throw new Error("phone_agent_invalid_package");
    }
    return { action, reason, confidence, params: { packageName } };
  }

  if (action === "global_action") {
    const nav = String(params.action ?? "").trim();
    if (!GLOBAL_ACTIONS.has(nav)) {
      throw new Error("phone_agent_invalid_global_action");
    }
    return { action, reason, confidence, params: { action: nav } };
  }

  if (action === "wake_screen") {
    return { action, reason, confidence, params: {} };
  }

  if (action === "wait") {
    const ms = readInt(params.ms) ?? 1000;
    return { action, reason, confidence, params: { ms: Math.min(3000, Math.max(300, ms)) } };
  }

  if (action === "done") {
    const summary = String(params.summary ?? reason).trim().slice(0, 500);
    return { action, reason, confidence, params: { summary } };
  }

  if (action === "failed") {
    const failReason = String(params.reason ?? reason).trim().slice(0, 500);
    return { action, reason, confidence, params: { reason: failReason } };
  }

  if (input.keyguardLocked && action !== "wake_screen" && action !== "failed" && action !== "done") {
    // allow but frontend may warn
  }

  throw new Error("phone_agent_validation_failed");
}

export async function planPhoneAgentStep(input: {
  goal: string;
  screenWidth: number;
  screenHeight: number;
  packageName?: string;
  keyguardLocked?: boolean;
  nodes: PhoneAgentUiNodeInput[];
  history?: PhoneAgentHistoryStep[];
}): Promise<PhoneAgentStepPlan> {
  const config = await getAiConfigStored();
  if (config.enabled !== 1) {
    throw new Error("ai_disabled");
  }
  if (!config.apiKey.trim()) {
    throw new Error("ai_api_key_missing");
  }
  if (!config.model.trim()) {
    throw new Error("model_required");
  }

  const goal = input.goal.trim().slice(0, MAX_GOAL_LEN);
  if (!goal) {
    throw new Error("goal_required");
  }
  if (input.screenWidth <= 0 || input.screenHeight <= 0) {
    throw new Error("screen_size_required");
  }
  if (!Array.isArray(input.nodes) || input.nodes.length === 0) {
    throw new Error("ui_nodes_empty");
  }

  const history = (input.history ?? []).slice(-MAX_HISTORY);
  const stepIndex = history.length + 1;

  const uiPayload = input.nodes.slice(0, 120).map((node) => ({
    i: node.i,
    text: (node.text ?? "").slice(0, 80),
    desc: (node.desc ?? "").slice(0, 80),
    clickable: Boolean(node.clickable),
    focusable: Boolean(node.focusable),
    bounds: [
      node.bounds.left,
      node.bounds.top,
      node.bounds.right,
      node.bounds.bottom,
    ],
    pkg: (node.packageName ?? "").slice(0, 80) || undefined,
  }));

  const userContent = JSON.stringify({
    goal,
    stepIndex,
    screen: { width: input.screenWidth, height: input.screenHeight },
    packageName: input.packageName ?? "",
    keyguardLocked: Boolean(input.keyguardLocked),
    history: history.map((item) => ({
      step: item.stepIndex,
      action: item.action,
      reason: item.reason,
      params: item.params,
      result: item.result ?? "",
    })),
    uiNodes: uiPayload,
  });

  const content = await chatCompletion({
    baseUrl: config.baseUrl,
    apiKey: config.apiKey,
    model: config.model,
    scene: "phone_agent_plan",
    messages: [
      { role: "system", content: SYSTEM_PROMPT },
      { role: "user", content: userContent },
    ],
  });

  const parsed = extractJsonObject(content);
  return validatePlan(parsed, {
    screenWidth: input.screenWidth,
    screenHeight: input.screenHeight,
    nodes: input.nodes,
    keyguardLocked: input.keyguardLocked,
  });
}
