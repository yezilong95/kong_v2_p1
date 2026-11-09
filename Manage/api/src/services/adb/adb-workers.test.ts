import assert from "node:assert/strict";
import { test } from "node:test";
import {
  ADB_PNG_MAX_PAYLOAD_BYTES,
  inspectPng,
} from "./adb-png-protocol.js";
import {
  createAdbScreencapWorker,
  createAdbUiTreeWorker,
} from "./adb-workers.js";

const ONE_PIXEL_PNG = Buffer.from(
  "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mP8z8BQDwAEhQGAhKmMIQAAAABJRU5ErkJggg==",
  "base64",
);

function sleep(ms: number) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

test("screencap worker skips overlapping captures", async () => {
  let inflight = 0;
  let started = 0;
  let frames = 0;
  const gate: Array<(value: unknown) => void> = [];
  const worker = createAdbScreencapWorker({
    serial: "127.0.0.1:41001",
    executeBinary: async () => {
      started += 1;
      inflight += 1;
      await new Promise((resolve) => gate.push(resolve));
      inflight -= 1;
      return { code: 0, stdout: ONE_PIXEL_PNG, stderr: Buffer.alloc(0) };
    },
    onFrame() {
      frames += 1;
    },
    onStopped() {},
  });

  worker.start(20);
  await sleep(70);
  assert.equal(started, 1);
  assert.equal(inflight, 1);
  gate.forEach((resolve) => resolve(undefined));
  await sleep(30);
  worker.stop("test");
  assert.equal(frames, 1);
  assert.equal(inspectPng(ONE_PIXEL_PNG).width, 1);
  assert.ok(ONE_PIXEL_PNG.length < ADB_PNG_MAX_PAYLOAD_BYTES);
});

test("screencap worker stops after three consecutive failures", async () => {
  let stopped: string | null = null;
  const worker = createAdbScreencapWorker({
    serial: "127.0.0.1:41001",
    executeBinary: async () => ({
      code: 1,
      stdout: Buffer.alloc(0),
      stderr: Buffer.from("device offline"),
    }),
    onFrame() {
      assert.fail("should not emit frames");
    },
    onStopped(reason) {
      stopped = reason;
    },
  });
  worker.start(10);
  await sleep(80);
  worker.stop("cleanup");
  assert.match(String(stopped), /adb_screencap_stopped/);
});

test("ui tree worker dumps unique path, reads xml, and always cleans up", async () => {
  const calls: string[][] = [];
  let dumpIndex = 0;
  const worker = createAdbUiTreeWorker({
    serial: "127.0.0.1:41001",
    intervalMs: 5_000,
    createDumpPath: () => `/data/local/tmp/vibelink-ui-${++dumpIndex}.xml`,
    executeText: async (args) => {
      calls.push([...args]);
      return { code: 0, stdout: "UI hierchary dumped to: path", stderr: "" };
    },
    executeBinary: async (args) => {
      calls.push([...args]);
      return {
        code: 0,
        stdout: Buffer.from('<node class="android.view.View" bounds="[0,0][10,10]" />'),
        stderr: Buffer.alloc(0),
      };
    },
    onXml(capture) {
      assert.match(capture.dumpPath, /vibelink-ui-1\.xml/);
      assert.match(capture.xml, /android.view.View/);
    },
    onStopped() {},
  });
  worker.start();
  await sleep(30);
  worker.stop("test");
  assert.deepEqual(calls[0], ["shell", "uiautomator", "dump", "/data/local/tmp/vibelink-ui-1.xml"]);
  assert.deepEqual(calls[1], ["exec-out", "cat", "/data/local/tmp/vibelink-ui-1.xml"]);
  assert.deepEqual(calls[2], ["shell", "rm", "-f", "/data/local/tmp/vibelink-ui-1.xml"]);
});

test("ui tree worker schedules next dump after completion, never overlaps", async () => {
  let inflight = 0;
  let maxInflight = 0;
  let dumps = 0;
  const startedAt: number[] = [];
  const gate: Array<(value: unknown) => void> = [];
  const worker = createAdbUiTreeWorker({
    serial: "127.0.0.1:41001",
    intervalMs: 40,
    createDumpPath: () => `/data/local/tmp/vibelink-ui-${++dumps}.xml`,
    executeText: async (args) => {
      if (args[1] === "uiautomator") {
        startedAt.push(Date.now());
        inflight += 1;
        maxInflight = Math.max(maxInflight, inflight);
        await new Promise((resolve) => gate.push(resolve));
        inflight -= 1;
      }
      return { code: 0, stdout: "", stderr: "" };
    },
    executeBinary: async () => ({
      code: 0,
      stdout: Buffer.from('<node class="android.view.View" bounds="[0,0][10,10]" />'),
      stderr: Buffer.alloc(0),
    }),
    onXml() {},
    onStopped() {},
  });

  worker.start();
  await sleep(20);
  assert.equal(startedAt.length, 1);
  assert.equal(inflight, 1);
  // 慢 dump 期间不应再起一轮
  await sleep(80);
  assert.equal(startedAt.length, 1);
  gate[0]?.(undefined);
  await sleep(20);
  // 结束后再等 intervalMs 才开第二轮
  assert.equal(startedAt.length, 1);
  await sleep(50);
  assert.equal(startedAt.length, 2);
  assert.ok(startedAt[1]! - startedAt[0]! >= 40);
  assert.equal(maxInflight, 1);
  gate[1]?.(undefined);
  worker.stop("test");
});

test("ui tree worker still cleans dump path after dump failure", async () => {
  const calls: string[][] = [];
  const worker = createAdbUiTreeWorker({
    serial: "127.0.0.1:41001",
    intervalMs: 5_000,
    createDumpPath: () => "/data/local/tmp/vibelink-ui-fail.xml",
    executeText: async (args) => {
      calls.push([...args]);
      if (args[1] === "uiautomator") {
        return { code: 1, stdout: "", stderr: "Killed" };
      }
      return { code: 0, stdout: "", stderr: "" };
    },
    executeBinary: async () => {
      throw new Error("should_not_cat");
    },
    onXml() {
      assert.fail("no xml");
    },
    onStopped() {},
  });
  worker.start();
  await sleep(30);
  worker.stop("test");
  assert.ok(calls.some((args) => args[1] === "rm"));
});
