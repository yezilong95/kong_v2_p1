import { spawn } from "node:child_process";

export interface AdbExecutionOptions {
  operation: string;
  serial?: string;
  timeoutMs?: number;
  adbBin?: string;
  maxStdoutBytes?: number;
}

export interface AdbBinaryExecutionResult {
  code: number;
  stdout: Buffer;
  stderr: Buffer;
}

export interface AdbTextExecutionResult {
  code: number;
  stdout: string;
  stderr: string;
}

export class AdbExecutionError extends Error {
  readonly operation: string;
  readonly serial: string | null;
  readonly isTimeout: boolean;

  constructor(
    message: string,
    options: {
      operation: string;
      serial?: string;
      isTimeout?: boolean;
      cause?: unknown;
    },
  ) {
    const serialContext = options.serial ? `, serial=${options.serial}` : "";
    super(`ADB operation failed: operation=${options.operation}${serialContext}, ${message}`, {
      cause: options.cause,
    });
    this.name = "AdbExecutionError";
    this.operation = options.operation;
    this.serial = options.serial ?? null;
    this.isTimeout = options.isTimeout ?? false;
  }
}

function buildAdbArgs(args: readonly string[], serial?: string): string[] {
  return serial ? ["-s", serial, ...args] : [...args];
}

export function executeAdbBinary(
  args: readonly string[],
  options: AdbExecutionOptions,
): Promise<AdbBinaryExecutionResult> {
  const adbBin = options.adbBin?.trim() || process.env.ADB_BIN?.trim() || "adb";
  const timeoutMs = options.timeoutMs ?? 10_000;
  if (!options.operation.trim()) {
    return Promise.reject(
      new AdbExecutionError("operation is required", {
        operation: options.operation,
        serial: options.serial,
      }),
    );
  }
  if (!Number.isFinite(timeoutMs) || timeoutMs <= 0) {
    return Promise.reject(
      new AdbExecutionError(`invalid timeoutMs=${timeoutMs}`, {
        operation: options.operation,
        serial: options.serial,
      }),
    );
  }

  return new Promise((resolve, reject) => {
    let isSettled = false;
    const stdoutChunks: Buffer[] = [];
    const stderrChunks: Buffer[] = [];
    const child = spawn(adbBin, buildAdbArgs(args, options.serial), {
      shell: false,
      stdio: ["ignore", "pipe", "pipe"],
      windowsHide: true,
    });

    const settle = (callback: () => void) => {
      if (isSettled) {
        return;
      }
      isSettled = true;
      clearTimeout(timer);
      callback();
    };

    child.stdout.on("data", (chunk: Buffer) => {
      stdoutChunks.push(chunk);
      if (options.maxStdoutBytes) {
        const total = stdoutChunks.reduce((sum, item) => sum + item.length, 0);
        if (total > options.maxStdoutBytes) {
          child.kill("SIGKILL");
          settle(() => {
            reject(
              new AdbExecutionError(`stdout exceeded ${options.maxStdoutBytes} bytes`, {
                operation: options.operation,
                serial: options.serial,
              }),
            );
          });
        }
      }
    });
    child.stderr.on("data", (chunk: Buffer) => {
      stderrChunks.push(chunk);
    });
    child.on("error", (cause) => {
      settle(() => {
        reject(
          new AdbExecutionError(cause.message, {
            operation: options.operation,
            serial: options.serial,
            cause,
          }),
        );
      });
    });
    child.on("close", (code) => {
      settle(() => {
        resolve({
          code: code ?? 1,
          stdout: Buffer.concat(stdoutChunks),
          stderr: Buffer.concat(stderrChunks),
        });
      });
    });

    const timer = setTimeout(() => {
      child.kill("SIGKILL");
      settle(() => {
        reject(
          new AdbExecutionError(`timeout after ${timeoutMs}ms`, {
            operation: options.operation,
            serial: options.serial,
            isTimeout: true,
          }),
        );
      });
    }, timeoutMs);
  });
}

export async function executeAdbText(
  args: readonly string[],
  options: AdbExecutionOptions,
): Promise<AdbTextExecutionResult> {
  const result = await executeAdbBinary(args, options);
  return {
    code: result.code,
    stdout: result.stdout.toString("utf8"),
    stderr: result.stderr.toString("utf8"),
  };
}
