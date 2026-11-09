import assert from "node:assert/strict";
import { test } from "node:test";
import { MAX_RECOVER_FAILS, nextEndpointState } from "./adb-session-store.js";

test("connected 可以跳出任何状态", () => {
  assert.equal(nextEndpointState("offline", "connected"), "connected");
  // 重连成功必须能救回 stale，否则换了新端口也永远回不来
  assert.equal(nextEndpointState("stale", "connected"), "connected");
  assert.equal(nextEndpointState(null, "connected"), "connected");
});

test("stale 是终态，offline 不能把它撤销", () => {
  // 设备下线时会无条件写 offline。允许覆盖的话，端点失效的结论每掉线一次就丢。
  assert.equal(nextEndpointState("stale", "offline"), null);
  assert.equal(nextEndpointState("stale", "stale"), null);
});

test("其余状态之间可以互相迁移", () => {
  assert.equal(nextEndpointState("connected", "offline"), "offline");
  assert.equal(nextEndpointState(null, "offline"), "offline");
  assert.equal(nextEndpointState("offline", "offline"), "offline");
});

test("恢复失败上限必须是正数，否则永远到不了 stale", () => {
  assert.ok(Number.isInteger(MAX_RECOVER_FAILS) && MAX_RECOVER_FAILS > 0);
});
