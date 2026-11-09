import { AdbExecutionError, executeAdbText } from "./adb-executor.js";

export const ADB_ACTION_UNSUPPORTED = "adb_action_unsupported";

export type AdbInputAction =
  | { type: "tap"; x: number; y: number }
  | { type: "swipe"; x1: number; y1: number; x2: number; y2: number; durationMs?: number }
  | { type: "key"; key: "HOME" | "BACK" | "RECENTS" | "delete" | "enter" }
  | { type: "text"; text: string }
  | { type: "pattern"; path?: string };

const ASCII_TEXT = /^[\x20-\x7e]+$/;
const KEYEVENT_MAP: Record<Extract<AdbInputAction, { type: "key" }>["key"], string> = {
  HOME: "3",
  BACK: "4",
  RECENTS: "187",
  delete: "67",
  enter: "66",
};

export class AdbInputError extends Error {
  readonly code: string;

  constructor(code: string, message: string) {
    super(message);
    this.name = "AdbInputError";
    this.code = code;
  }
}

function assertInt(name: string, value: number): number {
  if (!Number.isInteger(value) || value < 0 || value > 100_000) {
    throw new AdbInputError("adb_input_invalid", `invalid ${name}=${value}`);
  }
  return value;
}

export function buildAdbInputArgs(action: AdbInputAction): string[] {
  if (action.type === "pattern") {
    throw new AdbInputError(
      ADB_ACTION_UNSUPPORTED,
      "adb_action_unsupported: pattern unlock is not available in ADB mode",
    );
  }
  if (action.type === "tap") {
    return ["shell", "input", "tap", String(assertInt("x", action.x)), String(assertInt("y", action.y))];
  }
  if (action.type === "swipe") {
    const duration = action.durationMs ?? 300;
    return [
      "shell",
      "input",
      "swipe",
      String(assertInt("x1", action.x1)),
      String(assertInt("y1", action.y1)),
      String(assertInt("x2", action.x2)),
      String(assertInt("y2", action.y2)),
      String(assertInt("durationMs", duration)),
    ];
  }
  if (action.type === "key") {
    const code = KEYEVENT_MAP[action.key];
    if (!code) {
      throw new AdbInputError(ADB_ACTION_UNSUPPORTED, `adb_action_unsupported: key=${String(action.key)}`);
    }
    return ["shell", "input", "keyevent", code];
  }
  if (action.type === "text") {
    if (!action.text || !ASCII_TEXT.test(action.text)) {
      throw new AdbInputError(
        "adb_input_invalid",
        "ADB text only accepts printable ASCII",
      );
    }
    return ["shell", "input", "text", escapeAdbText(action.text)];
  }
  throw new AdbInputError(ADB_ACTION_UNSUPPORTED, `adb_action_unsupported: ${JSON.stringify(action)}`);
}

export function escapeAdbText(text: string): string {
  return text.replace(/ /g, "%s").replace(/(['"\\$&;<>|()])/g, "\\$1");
}

export async function executeAdbInput(
  serial: string,
  action: AdbInputAction,
): Promise<{ ok: true; args: string[] }> {
  const args = buildAdbInputArgs(action);
  try {
    const result = await executeAdbText(args, {
      operation: `input_${action.type}`,
      serial,
      timeoutMs: 5_000,
    });
    if (result.code !== 0) {
      throw new AdbInputError(
        "adb_input_failed",
        `adb_input_failed: type=${action.type}, code=${result.code}, stderr=${result.stderr.slice(0, 200)}`,
      );
    }
    return { ok: true, args };
  } catch (error) {
    if (error instanceof AdbInputError) {
      throw error;
    }
    const message = error instanceof AdbExecutionError ? error.message : String(error);
    throw new AdbInputError("adb_input_failed", message);
  }
}
