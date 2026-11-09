import assert from "node:assert/strict";
import { beforeEach, test } from "node:test";
import { adbSessionRegistry } from "./adb/adb-session-registry.js";
import {
  getDeviceAdbSerial,
  getDeviceAdbStatus,
  onDeviceAdbSocket,
} from "./adb-relay.service.js";

beforeEach(() => {
  adbSessionRegistry.reset();
});

test("ADB WebSocket disconnect clears the registered serial and propagates once", () => {
  const disconnects: unknown[] = [];
  const unsubscribe = adbSessionRegistry.subscribeDisconnect((event) => disconnects.push(event));
  adbSessionRegistry.register("device-1", "127.0.0.1:41001");

  onDeviceAdbSocket("device-1", null);
  onDeviceAdbSocket("device-1", null);

  assert.equal(getDeviceAdbSerial("device-1"), null);
  assert.equal(disconnects.length, 1);
  unsubscribe();
});

test("ADB WebSocket disconnect marks the session offline instead of dropping it", () => {
  adbSessionRegistry.register("device-1", "127.0.0.1:41001");

  onDeviceAdbSocket("device-1", null);

  // 会话仍在，只是不可用：恢复逻辑要靠这条记录知道「这台以前配对过」
  const session = adbSessionRegistry.get("device-1");
  assert.equal(session?.status, "offline");
  assert.equal(getDeviceAdbStatus("device-1").status, "offline");
  // serial 指向的本地端口已随 hub 销毁，不能再拿去执行 adb 命令
  assert.equal(getDeviceAdbSerial("device-1"), null);
});
