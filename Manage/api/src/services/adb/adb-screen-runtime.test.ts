import assert from "node:assert/strict";
import { test } from "node:test";
import { createAdbSessionRegistry } from "./adb-session-registry.js";
import { connectionManager } from "../../ws/connection-manager.js";
import {
  handleAdbDisconnected,
  resetAdbScreenRuntimes,
} from "./adb-screen-runtime.js";

test("adb disconnect broadcasts clear events to current watchers", () => {
  resetAdbScreenRuntimes();
  const sent: Array<string | Buffer> = [];
  const socket = {
    readyState: 1,
    OPEN: 1,
    send(payload: string | Buffer) {
      sent.push(payload);
    },
  };
  connectionManager.addAdminScreen(socket as never);
  connectionManager.setAdminAdbScreenWatchTarget(socket as never, "device-1");
  connectionManager.setAdminAdbUiTreeWatchTarget(socket as never, "device-1");

  handleAdbDisconnected("device-1", "adb_ws_disconnected");

  const messages = sent.map((item) => JSON.parse(String(item)) as { type: string; reason: string });
  assert.equal(messages.some((item) => item.type === "adb_screen_disconnected"), true);
  assert.equal(messages.some((item) => item.type === "adb_ui_tree_disconnected"), true);
  assert.ok(messages.every((item) => item.reason === "adb_ws_disconnected"));

  connectionManager.removeAdminScreen(socket as never);
  resetAdbScreenRuntimes();
});

test("session registry disconnect is isolated from leftover sessions", () => {
  const registry = createAdbSessionRegistry();
  registry.register("device-1", "127.0.0.1:41001");
  assert.equal(registry.clear("device-1", "adb_ws_disconnected"), true);
  assert.equal(registry.get("device-1"), null);
});
