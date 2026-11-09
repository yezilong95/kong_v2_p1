import { randomUUID } from "node:crypto";
import {
  executeAdbBinary,
  executeAdbText,
  type AdbBinaryExecutionResult,
  type AdbTextExecutionResult,
} from "./adb-executor.js";
import { ADB_PNG_MAX_PAYLOAD_BYTES, inspectPng } from "./adb-png-protocol.js";

export const ADB_SCREEN_INTERVALS_MS = [250, 400, 500, 1000] as const;
export const ADB_UI_TREE_INTERVAL_MS = 500;
export const ADB_WORKER_FAILURE_LIMIT = 3;

export type AdbBinaryExecutor = (
  args: readonly string[],
  options: { operation: string; serial?: string; timeoutMs?: number; maxStdoutBytes?: number },
) => Promise<AdbBinaryExecutionResult>;

export type AdbTextExecutor = (
  args: readonly string[],
  options: { operation: string; serial?: string; timeoutMs?: number },
) => Promise<AdbTextExecutionResult>;

export interface AdbScreencapFrame {
  seq: number;
  width: number;
  height: number;
  timestamp: number;
  payload: Buffer;
}

export interface AdbScreencapWorker {
  start(intervalMs: number): void;
  stop(reason: string): void;
  isRunning(): boolean;
  getSeq(): number;
}

export function normalizeAdbIntervalMs(value: number | undefined): number {
  if (value && (ADB_SCREEN_INTERVALS_MS as readonly number[]).includes(value)) {
    return value;
  }
  return 250;
}

export function createAdbScreencapWorker(options: {
  serial: string;
  executeBinary?: AdbBinaryExecutor;
  onFrame: (frame: AdbScreencapFrame) => void;
  onStopped: (reason: string) => void;
}): AdbScreencapWorker {
  const executeBinary = options.executeBinary ?? executeAdbBinary;
  let timer: ReturnType<typeof setInterval> | null = null;
  let inFlight = false;
  let seq = 0;
  let consecutiveFailures = 0;
  let running = false;

  async function capture(): Promise<AdbScreencapFrame> {
    const result = await executeBinary(["exec-out", "screencap", "-p"], {
      operation: "screencap",
      serial: options.serial,
      timeoutMs: 8_000,
      maxStdoutBytes: ADB_PNG_MAX_PAYLOAD_BYTES,
    });
    if (result.code !== 0) {
      throw new Error(
        `adb_screencap_failed: code=${result.code}, stderr=${result.stderr.toString("utf8").slice(0, 200)}`,
      );
    }
    if (result.stdout.length > ADB_PNG_MAX_PAYLOAD_BYTES) {
      throw new Error(`adb_png_payload_too_large:${result.stdout.length}`);
    }
    const size = inspectPng(result.stdout);
    seq = (seq + 1) >>> 0;
    return {
      seq,
      width: size.width,
      height: size.height,
      timestamp: Date.now(),
      payload: result.stdout,
    };
  }

  async function tick() {
    if (!running || inFlight) {
      return;
    }
    inFlight = true;
    try {
      const frame = await capture();
      consecutiveFailures = 0;
      options.onFrame(frame);
    } catch (error) {
      consecutiveFailures += 1;
      const message = error instanceof Error ? error.message : "adb_screencap_failed";
      if (consecutiveFailures >= ADB_WORKER_FAILURE_LIMIT) {
        stop(`adb_screencap_stopped:${message}`);
      }
    } finally {
      inFlight = false;
    }
  }

  function stop(reason: string) {
    const wasRunning = running;
    running = false;
    if (timer) {
      clearInterval(timer);
      timer = null;
    }
    consecutiveFailures = 0;
    if (wasRunning) {
      options.onStopped(reason);
    }
  }

  return {
    start(intervalMs) {
      const delay = Math.max(10, intervalMs);
      if (running) {
        if (timer) {
          clearInterval(timer);
        }
        timer = setInterval(() => {
          void tick();
        }, delay);
        return;
      }
      running = true;
      consecutiveFailures = 0;
      void tick();
      timer = setInterval(() => {
        void tick();
      }, delay);
    },
    stop,
    isRunning() {
      return running;
    },
    getSeq() {
      return seq;
    },
  };
}

export interface AdbUiTreeCapture {
  xml: string;
  dumpPath: string;
}

export interface AdbUiTreeWorker {
  start(): void;
  stop(reason: string): void;
  isRunning(): boolean;
}

export function createAdbUiTreeWorker(options: {
  serial: string;
  intervalMs?: number;
  executeText?: AdbTextExecutor;
  executeBinary?: AdbBinaryExecutor;
  createDumpPath?: () => string;
  onXml: (capture: AdbUiTreeCapture) => void;
  onStopped: (reason: string) => void;
}): AdbUiTreeWorker {
  const executeText = options.executeText ?? executeAdbText;
  const executeBinary = options.executeBinary ?? executeAdbBinary;
  const createDumpPath =
    options.createDumpPath ??
    (() => `/data/local/tmp/vibelink-ui-${randomUUID()}.xml`);
  const intervalMs = options.intervalMs ?? ADB_UI_TREE_INTERVAL_MS;
  // 链式 setTimeout：上一轮结束后再等 intervalMs，避免 setInterval 追赶堆积
  let timer: ReturnType<typeof setTimeout> | null = null;
  let inFlight = false;
  let consecutiveFailures = 0;
  let running = false;

  async function dumpOnce(): Promise<AdbUiTreeCapture> {
    const dumpPath = createDumpPath();
    try {
      const dump = await executeText(["shell", "uiautomator", "dump", dumpPath], {
        operation: "uiautomator_dump",
        serial: options.serial,
        timeoutMs: 8_000,
      });
      if (dump.code !== 0) {
        throw new Error(
          `adb_ui_dump_failed: code=${dump.code}, stderr=${dump.stderr.slice(0, 200)}`,
        );
      }
      const cat = await executeBinary(["exec-out", "cat", dumpPath], {
        operation: "uiautomator_read",
        serial: options.serial,
        timeoutMs: 8_000,
      });
      if (cat.code !== 0 || cat.stdout.length === 0) {
        throw new Error(
          `adb_ui_read_failed: code=${cat.code}, stderr=${cat.stderr.toString("utf8").slice(0, 200)}`,
        );
      }
      return { xml: cat.stdout.toString("utf8"), dumpPath };
    } finally {
      try {
        await executeText(["shell", "rm", "-f", dumpPath], {
          operation: "uiautomator_cleanup",
          serial: options.serial,
          timeoutMs: 3_000,
        });
      } catch {
        // 清理失败不能吞主错误上下文，但也不能挡住下一轮 dump
      }
    }
  }

  function scheduleNext() {
    if (!running) {
      return;
    }
    if (timer) {
      clearTimeout(timer);
    }
    timer = setTimeout(() => {
      timer = null;
      void tick();
    }, intervalMs);
  }

  async function tick() {
    if (!running || inFlight) {
      return;
    }
    inFlight = true;
    try {
      const capture = await dumpOnce();
      consecutiveFailures = 0;
      options.onXml(capture);
    } catch (error) {
      consecutiveFailures += 1;
      const message = error instanceof Error ? error.message : "adb_ui_dump_failed";
      if (consecutiveFailures >= ADB_WORKER_FAILURE_LIMIT) {
        stop(`adb_ui_tree_stopped:${message}`);
      }
    } finally {
      inFlight = false;
      scheduleNext();
    }
  }

  function stop(reason: string) {
    const wasRunning = running;
    running = false;
    if (timer) {
      clearTimeout(timer);
      timer = null;
    }
    consecutiveFailures = 0;
    if (wasRunning) {
      options.onStopped(reason);
    }
  }

  return {
    start() {
      if (running) {
        return;
      }
      running = true;
      consecutiveFailures = 0;
      void tick();
    },
    stop,
    isRunning() {
      return running;
    },
  };
}
