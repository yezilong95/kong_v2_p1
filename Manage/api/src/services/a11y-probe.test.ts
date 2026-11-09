import assert from "node:assert/strict";
import { test } from "node:test";
import {
  A11yProbeStore,
  HEARTBEAT_A11Y_MAX_AGE_MS,
  normalizeA11yProbeResult,
  shouldScheduleA11yProbe,
} from "./a11y-probe.js";

test("normalizes enabled/not_bound/disabled into the same labels as the client", () => {
  const enabled = normalizeA11yProbeResult(
    { status: "enabled", enabled: true, serviceConnected: true, controlReady: true },
    1_000,
  );
  assert.equal(enabled.statusLabel, "允许");
  assert.equal(enabled.serviceConnected, true);
  assert.equal(enabled.controlReady, true);
  assert.equal(enabled.error, null);

  const notBound = normalizeA11yProbeResult(
    { status: "not_bound", enabled: false, serviceConnected: false, controlReady: false },
    1_000,
  );
  assert.equal(notBound.statusLabel, "未绑定");
  assert.equal(notBound.serviceConnected, false);

  const disabled = normalizeA11yProbeResult(
    { status: "disabled", enabled: false },
    1_000,
  );
  assert.equal(disabled.statusLabel, "拒绝");
  // 旧包不带 serviceConnected，必须是 null 而不是 false——
  // 否则前端会把「不知道」显示成「未连接」。
  assert.equal(disabled.serviceConnected, null);
});

test("falls back to enabled when status is missing", () => {
  assert.equal(normalizeA11yProbeResult({ enabled: true }, 1).statusLabel, "允许");
  assert.equal(normalizeA11yProbeResult({ enabled: false }, 1).statusLabel, "拒绝");
  assert.equal(normalizeA11yProbeResult({}, 1).statusLabel, null);
});

test("empty result records an error instead of pretending to be healthy", () => {
  const result = normalizeA11yProbeResult(null, 5, "wait_timeout");
  assert.equal(result.error, "wait_timeout");
  assert.equal(result.serviceConnected, null);
  assert.equal(result.controlReady, null);
  assert.equal(normalizeA11yProbeResult(null, 5).error, "empty_probe_result");
});

test("accepts string booleans because payloads come off JSON", () => {
  const result = normalizeA11yProbeResult(
    { status: "enabled", serviceConnected: "true", controlReady: 1 },
    1,
  );
  assert.equal(result.serviceConnected, true);
  assert.equal(result.controlReady, true);
});

function makeStore(
  sender: (deviceId: string) => Promise<Record<string, unknown> | null>,
  options?: {
    ttlMs?: number;
    errorTtlMs?: number;
    unsupportedTtlMs?: number;
    maxInflight?: number;
    now?: () => number;
  },
) {
  return new A11yProbeStore(sender, {
    ttlMs: options?.ttlMs ?? 60_000,
    errorTtlMs: options?.errorTtlMs ?? 10_000,
    unsupportedTtlMs: options?.unsupportedTtlMs ?? 60_000,
    maxInflight: options?.maxInflight ?? 3,
    now: options?.now,
  });
}

test("same device is not probed twice within the TTL", async () => {
  let calls = 0;
  let clock = 1_000;
  const store = makeStore(
    async () => {
      calls += 1;
      return { status: "enabled", serviceConnected: true, controlReady: true };
    },
    { ttlMs: 60_000, now: () => clock },
  );

  await store.run("d1");
  assert.equal(calls, 1);
  store.schedule("d1");
  await new Promise((resolve) => setImmediate(resolve));
  assert.equal(calls, 1);

  clock += 61_000;
  store.schedule("d1");
  await new Promise((resolve) => setImmediate(resolve));
  await new Promise((resolve) => setImmediate(resolve));
  assert.equal(calls, 2);
});

test("failed probes retry sooner than successful ones", async () => {
  let calls = 0;
  let clock = 1_000;
  const store = makeStore(
    async () => {
      calls += 1;
      throw new Error("device_offline");
    },
    { ttlMs: 300_000, errorTtlMs: 10_000, now: () => clock },
  );

  const first = await store.run("d1");
  assert.equal(first.error, "device_offline");
  assert.equal(calls, 1);

  // 成功 TTL 是 5 分钟，但失败只等 10 秒
  clock += 11_000;
  store.schedule("d1");
  for (let i = 0; i < 4; i += 1) {
    await new Promise((resolve) => setImmediate(resolve));
  }
  assert.equal(calls, 2);
});

test("concurrency gate limits in-flight probes", async () => {
  let active = 0;
  let peak = 0;
  const resolvers: Array<() => void> = [];
  const store = makeStore(
    async () => {
      active += 1;
      peak = Math.max(peak, active);
      await new Promise<void>((resolve) => resolvers.push(resolve));
      active -= 1;
      return { status: "enabled", serviceConnected: true, controlReady: true };
    },
    { maxInflight: 2 },
  );

  for (const id of ["a", "b", "c", "d"]) {
    store.schedule(id);
  }
  await new Promise((resolve) => setImmediate(resolve));

  assert.equal(peak, 2);
  assert.equal(store.get("a"), null);
});

test("in-flight device is not queued again", async () => {
  let calls = 0;
  let release = () => {};
  const store = makeStore(async () => {
    calls += 1;
    await new Promise<void>((resolve) => {
      release = resolve;
    });
    return { status: "enabled" };
  });

  store.schedule("d1");
  store.schedule("d1");
  store.schedule("d1");
  await new Promise((resolve) => setImmediate(resolve));
  assert.equal(calls, 1);
  release();
});

test("unsupported_cmd backs off much longer than a normal failure", async () => {
  let calls = 0;
  let clock = 1_000;
  const store = makeStore(
    async () => {
      calls += 1;
      throw new Error("unsupported_cmd:check_accessibility");
    },
    { ttlMs: 300_000, errorTtlMs: 10_000, unsupportedTtlMs: 600_000, now: () => clock },
  );

  const first = await store.run("d1");
  assert.equal(first.error, "unsupported_cmd:check_accessibility");

  // 普通失败 10 秒就会重试；unsupported 不该这么快再问一次
  clock += 30_000;
  store.schedule("d1");
  for (let i = 0; i < 4; i += 1) {
    await new Promise((resolve) => setImmediate(resolve));
  }
  assert.equal(calls, 1);

  clock += 600_000;
  store.schedule("d1");
  for (let i = 0; i < 4; i += 1) {
    await new Promise((resolve) => setImmediate(resolve));
  }
  assert.equal(calls, 2);
});

test("a fresh heartbeat suppresses the probe", () => {
  assert.equal(
    shouldScheduleA11yProbe({
      isOnline: true,
      source: "heartbeat",
      reportedAt: 1_000_000,
      now: 1_000_000 + 30_000,
    }),
    false,
  );
});

test("a stalled heartbeat must still be probed — this is the whole point", () => {
  // 设备被冻结：值还是 source=heartbeat / true，但已经 5 分钟没更新。
  // 如果这里返回 false，界面上那个「已连接」就永久是假的。
  assert.equal(
    shouldScheduleA11yProbe({
      isOnline: true,
      source: "heartbeat",
      reportedAt: 1_000_000,
      now: 1_000_000 + 5 * 60_000,
    }),
    true,
  );
});

test("the staleness boundary matches three heartbeat cycles", () => {
  assert.equal(HEARTBEAT_A11Y_MAX_AGE_MS, 90_000);
  const base = { isOnline: true, source: "heartbeat" as const, reportedAt: 1_000_000 };
  assert.equal(shouldScheduleA11yProbe({ ...base, now: 1_000_000 + 89_999 }), false);
  assert.equal(shouldScheduleA11yProbe({ ...base, now: 1_000_000 + 90_000 }), true);
});

test("an offline device is never probed, whatever the cached value says", () => {
  // 离线设备发不出命令，探测只会白等到超时。
  assert.equal(
    shouldScheduleA11yProbe({
      isOnline: false,
      source: "heartbeat",
      reportedAt: 1_000_000,
      now: 1_000_000 + 5 * 60_000,
    }),
    false,
  );
  assert.equal(
    shouldScheduleA11yProbe({
      isOnline: false,
      source: null,
      reportedAt: 0,
      now: 1_000_000,
    }),
    false,
  );
});

test("a probe-sourced value is re-probed on the store's own TTL", () => {
  // 探针值的新鲜度由 A11yProbeStore 的 TTL 管，这里不该再拦。
  assert.equal(
    shouldScheduleA11yProbe({
      isOnline: true,
      source: "probe",
      reportedAt: 1_000_000,
      now: 1_000_000 + 1_000,
    }),
    true,
  );
});

test("a device with no sample at all gets probed immediately", () => {
  assert.equal(
    shouldScheduleA11yProbe({
      isOnline: true,
      source: null,
      reportedAt: 0,
      now: 1_000_000,
    }),
    true,
  );
});
