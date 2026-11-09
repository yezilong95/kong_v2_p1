import assert from "node:assert/strict";
import { test } from "node:test";
import {
  connectAdbUntilAccepted,
  interpretAdbConnectOutput,
  resolveAdbRelayConnectPort,
  waitUntilAdbTransportReady,
} from "./adb-connect-ready.js";

test("treats already-connected as stale, not a fresh handshake", () => {
  assert.equal(
    interpretAdbConnectOutput({
      code: 0,
      stdout: "already connected to 127.0.0.1:36887",
      stderr: "",
    }),
    "already",
  );
  assert.equal(
    interpretAdbConnectOutput({
      code: 0,
      stdout: "connected to 127.0.0.1:36887",
      stderr: "",
    }),
    "ok",
  );
  assert.equal(
    interpretAdbConnectOutput({
      code: 1,
      stdout: "",
      stderr: "failed to connect to '127.0.0.1:36887': Connection refused",
    }),
    "fail",
  );
});

test("waitUntilAdbTransportReady succeeds after transient offline probes", async () => {
  let now = 0;
  let probes = 0;
  const result = await waitUntilAdbTransportReady(
    "127.0.0.1:36887",
    {
      now: () => now,
      sleep: async (ms) => {
        now += ms;
      },
      probe: async () => {
        probes += 1;
        return probes >= 3
          ? { ok: true }
          : { ok: false, error: "stderr=adb: device offline" };
      },
      connect: async () => {
        throw new Error("should not reconnect");
      },
      disconnect: async () => {
        throw new Error("should not reconnect");
      },
    },
    { timeoutMs: 2000, intervalMs: 400 },
  );
  assert.equal(result.ok, true);
  assert.equal(probes, 3);
});

test("waitUntilAdbTransportReady disconnects and reconnects once after offline window", async () => {
  let now = 0;
  let disconnects = 0;
  let connects = 0;
  const result = await waitUntilAdbTransportReady(
    "127.0.0.1:36887",
    {
      now: () => now,
      sleep: async (ms) => {
        now += ms;
      },
      probe: async () => {
        if (disconnects === 0) {
          return { ok: false, error: "stderr=adb: device offline" };
        }
        return { ok: true };
      },
      connect: async () => {
        connects += 1;
        return { code: 0, stdout: "connected to 127.0.0.1:36887", stderr: "" };
      },
      disconnect: async () => {
        disconnects += 1;
      },
    },
    { timeoutMs: 800, intervalMs: 400 },
  );
  assert.equal(result.ok, true);
  assert.equal(disconnects, 1);
  assert.equal(connects, 1);
});

test("reconnectFirst skips the stale session before probing", async () => {
  let disconnects = 0;
  let connects = 0;
  let probedBeforeReconnect = false;
  const result = await waitUntilAdbTransportReady(
    "127.0.0.1:36887",
    {
      now: () => 0,
      sleep: async () => undefined,
      probe: async () => {
        if (disconnects === 0) {
          probedBeforeReconnect = true;
        }
        return { ok: disconnects > 0 };
      },
      connect: async () => {
        connects += 1;
        return { code: 0, stdout: "connected to 127.0.0.1:36887", stderr: "" };
      },
      disconnect: async () => {
        disconnects += 1;
      },
    },
    { timeoutMs: 800, intervalMs: 400, reconnectFirst: true },
  );
  assert.equal(result.ok, true);
  assert.equal(disconnects, 1);
  assert.equal(connects, 1);
  assert.equal(probedBeforeReconnect, false);
});

test("connectAdbUntilAccepted retries failed handshake after pair bounce", async () => {
  const sleeps: number[] = [];
  let connects = 0;
  let disconnects = 0;
  const result = await connectAdbUntilAccepted(
    "127.0.0.1:44161",
    {
      sleep: async (ms) => {
        sleeps.push(ms);
      },
      connect: async () => {
        connects += 1;
        if (connects < 3) {
          return { code: 1, stdout: "", stderr: "failed to connect to 127.0.0.1:44161" };
        }
        return { code: 0, stdout: "connected to 127.0.0.1:44161", stderr: "" };
      },
      disconnect: async () => {
        disconnects += 1;
      },
    },
    { initialDelayMs: 1500, delayMs: 800, attempts: 5 },
  );
  assert.equal(result.ok, true);
  assert.equal(result.verdict, "ok");
  assert.equal(result.attempts, 3);
  assert.equal(disconnects, 2);
  assert.deepEqual(sleeps, [1500, 800, 800]);
});

test("connectAdbUntilAccepted keeps connect_failed after exhausting retries", async () => {
  const result = await connectAdbUntilAccepted(
    "127.0.0.1:44161",
    {
      sleep: async () => undefined,
      connect: async () => ({
        code: 1,
        stdout: "",
        stderr: "failed to connect to 127.0.0.1:44161",
      }),
      disconnect: async () => undefined,
    },
    { initialDelayMs: 0, delayMs: 0, attempts: 3 },
  );
  assert.equal(result.ok, false);
  assert.equal(result.attempts, 3);
  assert.match(result.error ?? "", /connect_failed:failed to connect to 127.0.0.1:44161/);
});

test("resolveAdbRelayConnectPort rejects missing or pair-port fallback", () => {
  assert.equal(resolveAdbRelayConnectPort(35755, 0), null);
  assert.equal(resolveAdbRelayConnectPort(35755, 35755), null);
  assert.equal(resolveAdbRelayConnectPort(37111, 35755), 35755);
});
