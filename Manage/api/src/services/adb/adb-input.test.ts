import assert from "node:assert/strict";
import { test } from "node:test";
import {
  ADB_ACTION_UNSUPPORTED,
  AdbInputError,
  buildAdbInputArgs,
} from "./adb-input.js";

test("whitelist tap swipe keys and ascii text", () => {
  assert.deepEqual(buildAdbInputArgs({ type: "tap", x: 10, y: 20 }), [
    "shell",
    "input",
    "tap",
    "10",
    "20",
  ]);
  assert.deepEqual(
    buildAdbInputArgs({ type: "swipe", x1: 1, y1: 2, x2: 3, y2: 4, durationMs: 300 }),
    ["shell", "input", "swipe", "1", "2", "3", "4", "300"],
  );
  assert.deepEqual(buildAdbInputArgs({ type: "key", key: "HOME" }), [
    "shell",
    "input",
    "keyevent",
    "3",
  ]);
  assert.deepEqual(buildAdbInputArgs({ type: "key", key: "BACK" }), [
    "shell",
    "input",
    "keyevent",
    "4",
  ]);
  assert.deepEqual(buildAdbInputArgs({ type: "key", key: "RECENTS" }), [
    "shell",
    "input",
    "keyevent",
    "187",
  ]);
  assert.deepEqual(buildAdbInputArgs({ type: "key", key: "delete" }), [
    "shell",
    "input",
    "keyevent",
    "67",
  ]);
  assert.deepEqual(buildAdbInputArgs({ type: "key", key: "enter" }), [
    "shell",
    "input",
    "keyevent",
    "66",
  ]);
  assert.deepEqual(buildAdbInputArgs({ type: "text", text: "Hello world" }), [
    "shell",
    "input",
    "text",
    "Hello%sworld",
  ]);
});

test("pattern unlock is explicitly unsupported", () => {
  try {
    buildAdbInputArgs({ type: "pattern", path: "1-2-3-6" });
    assert.fail("expected error");
  } catch (error) {
    assert.ok(error instanceof AdbInputError);
    assert.equal(error.code, ADB_ACTION_UNSUPPORTED);
  }
});

test("rejects non-ascii text and invalid coordinates", () => {
  assert.throws(() => buildAdbInputArgs({ type: "text", text: "你好" }), AdbInputError);
  assert.throws(() => buildAdbInputArgs({ type: "tap", x: 1.5, y: 2 }), AdbInputError);
});
