export function resolveAdbRelayConnectPort(pairPort: number, connectPort: number): number | null {
  if (!Number.isInteger(pairPort) || pairPort <= 0 || pairPort > 65_535) {
    return null;
  }
  if (!Number.isInteger(connectPort) || connectPort <= 0 || connectPort > 65_535) {
    return null;
  }
  if (connectPort === pairPort) {
    return null;
  }
  return connectPort;
}

export type AdbConnectVerdict = "ok" | "already" | "fail";

export interface AdbCommandOutput {
  code: number;
  stdout: string;
  stderr: string;
}

export interface AdbTransportReadyDeps {
  probe: (serial: string) => Promise<{ ok: boolean; error?: string }>;
  connect: (serial: string) => Promise<AdbCommandOutput>;
  disconnect: (serial: string) => Promise<void>;
  sleep: (ms: number) => Promise<void>;
  now?: () => number;
}

export interface WaitAdbTransportOptions {
  timeoutMs?: number;
  intervalMs?: number;
  reconnect?: boolean;
  reconnectFirst?: boolean;
}

export function interpretAdbConnectOutput(output: AdbCommandOutput): AdbConnectVerdict {
  const text = `${output.stdout}\n${output.stderr}`;
  if (/already connected to/i.test(text)) {
    return "already";
  }
  if (/failed to connect|unable to connect|connection refused/i.test(text)) {
    return "fail";
  }
  if (output.code === 0 || /connected to/i.test(text)) {
    return "ok";
  }
  return "fail";
}

async function pollUntilReady(
  serial: string,
  deps: AdbTransportReadyDeps,
  timeoutMs: number,
  intervalMs: number,
): Promise<{ ok: boolean; error?: string }> {
  const now = deps.now ?? Date.now;
  const deadline = now() + timeoutMs;
  let lastError = "adb_transport_not_ready";
  while (now() < deadline) {
    const probe = await deps.probe(serial);
    if (probe.ok) {
      return { ok: true };
    }
    lastError = probe.error ?? lastError;
    await deps.sleep(intervalMs);
  }
  return { ok: false, error: lastError };
}

async function reconnectSerial(serial: string, deps: AdbTransportReadyDeps): Promise<{ ok: boolean; error?: string }> {
  await deps.disconnect(serial);
  await deps.sleep(400);
  const connectRun = await deps.connect(serial);
  const verdict = interpretAdbConnectOutput(connectRun);
  if (verdict === "fail") {
    const detail = `${connectRun.stdout} ${connectRun.stderr}`.replace(/\s+/g, " ").trim();
    return { ok: false, error: detail ? `connect_retry_failed:${detail}` : "connect_retry_failed" };
  }
  return { ok: true };
}

export async function connectAdbUntilAccepted(
  serial: string,
  deps: Pick<AdbTransportReadyDeps, "connect" | "disconnect" | "sleep">,
  options: { attempts?: number; delayMs?: number; initialDelayMs?: number } = {},
): Promise<{
  ok: boolean;
  verdict: AdbConnectVerdict;
  output: AdbCommandOutput;
  attempts: number;
  error?: string;
}> {
  const attempts = Math.max(1, options.attempts ?? 5);
  const delayMs = options.delayMs ?? 1_000;
  const initialDelayMs = options.initialDelayMs ?? 2_000;
  let last: AdbCommandOutput = { code: 1, stdout: "", stderr: "connect_not_attempted" };
  let lastVerdict: AdbConnectVerdict = "fail";

  if (initialDelayMs > 0) {
    await deps.sleep(initialDelayMs);
  }

  for (let attempt = 1; attempt <= attempts; attempt += 1) {
    if (attempt > 1) {
      await deps.disconnect(serial).catch(() => undefined);
      await deps.sleep(delayMs);
    }
    last = await deps.connect(serial);
    lastVerdict = interpretAdbConnectOutput(last);
    if (lastVerdict !== "fail") {
      return { ok: true, verdict: lastVerdict, output: last, attempts: attempt };
    }
  }

  const detail = `${last.stdout} ${last.stderr}`.replace(/\s+/g, " ").trim();
  return {
    ok: false,
    verdict: lastVerdict,
    output: last,
    attempts,
    error: detail ? `connect_failed:${detail}` : "connect_failed",
  };
}

export async function waitUntilAdbTransportReady(
  serial: string,
  deps: AdbTransportReadyDeps,
  options: WaitAdbTransportOptions = {},
): Promise<{ ok: boolean; error?: string }> {
  const timeoutMs = options.timeoutMs ?? 12_000;
  const intervalMs = options.intervalMs ?? 500;
  const reconnect = options.reconnect !== false;

  if (options.reconnectFirst && reconnect) {
    const retry = await reconnectSerial(serial, deps);
    if (!retry.ok) {
      return retry;
    }
  }

  const first = await pollUntilReady(serial, deps, timeoutMs, intervalMs);
  if (first.ok || !reconnect || options.reconnectFirst) {
    return first;
  }

  const retry = await reconnectSerial(serial, deps);
  if (!retry.ok) {
    return retry;
  }
  return pollUntilReady(serial, deps, timeoutMs, intervalMs);
}
