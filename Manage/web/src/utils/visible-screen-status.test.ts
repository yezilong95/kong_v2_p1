import assert from "node:assert/strict";
import { test } from "node:test";
import { visibleScreenStatus } from "./visible-screen-status.ts";

test("shows english screencap failures and waiting, ignores receiving chatter", () => {
  assert.equal(visibleScreenStatus("ADB 画面接收中…"), null);
  assert.equal(visibleScreenStatus("画面接收中…"), null);
  assert.equal(visibleScreenStatus(""), null);
  assert.equal(
    visibleScreenStatus("ADB 画面：adb_screencap_stopped:adb_screencap_failed: code=1"),
    "ADB 画面：adb_screencap_stopped:adb_screencap_failed: code=1",
  );
  assert.equal(
    visibleScreenStatus("已请求投屏，等待手机授权/首帧…"),
    "已请求投屏，等待手机授权/首帧…",
  );
  assert.equal(
    visibleScreenStatus("投屏启动失败：device_offline"),
    "投屏启动失败：device_offline",
  );
});
