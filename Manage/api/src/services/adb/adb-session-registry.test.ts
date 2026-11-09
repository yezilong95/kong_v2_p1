import assert from "node:assert/strict";
import { describe, test } from "node:test";
import { createAdbSessionRegistry } from "./adb-session-registry.js";

describe("adb session registry", () => {
  test("register stores a session and replaces the serial for the same device", () => {
    const registry = createAdbSessionRegistry();

    registry.register("device-1", "127.0.0.1:41001");
    registry.register("device-1", "127.0.0.1:41002");

    assert.deepEqual(registry.get("device-1"), {
      deviceId: "device-1",
      serial: "127.0.0.1:41002",
      status: "connected",
    });
  });

  test("clear notifies disconnect subscribers exactly once", () => {
    const registry = createAdbSessionRegistry();
    const events: unknown[] = [];
    registry.subscribeDisconnect((event) => events.push(event));
    registry.register("device-1", "127.0.0.1:41001");

    assert.equal(registry.clear("device-1", "adb_ws_disconnected"), true);
    assert.equal(registry.clear("device-1", "adb_ws_disconnected"), false);

    assert.deepEqual(events, [
      {
        deviceId: "device-1",
        serial: "127.0.0.1:41001",
        reason: "adb_ws_disconnected",
      },
    ]);
  });

  test("each registry instance is isolated for tests", () => {
    const first = createAdbSessionRegistry();
    const second = createAdbSessionRegistry();
    first.register("device-1", "127.0.0.1:41001");

    assert.equal(second.get("device-1"), null);
  });

  test("markOffline keeps the session and flips the status instead of deleting it", () => {
    const registry = createAdbSessionRegistry();
    const events: unknown[] = [];
    registry.subscribeDisconnect((event) => events.push(event));
    registry.register("device-1", "127.0.0.1:41001");

    assert.equal(registry.markOffline("device-1", "adb_ws_disconnected"), true);

    // 会话还在，只是状态变了：这样才能区分「没配对过」和「配对过但现在断了」
    assert.deepEqual(registry.get("device-1"), {
      deviceId: "device-1",
      serial: "127.0.0.1:41001",
      status: "offline",
    });
    assert.equal(events.length, 1);
  });

  test("markOffline is idempotent so repeated close callbacks notify once", () => {
    const registry = createAdbSessionRegistry();
    const events: unknown[] = [];
    registry.subscribeDisconnect((event) => events.push(event));
    registry.register("device-1", "127.0.0.1:41001");

    assert.equal(registry.markOffline("device-1", "adb_ws_disconnected"), true);
    assert.equal(registry.markOffline("device-1", "adb_ws_disconnected"), false);

    assert.equal(events.length, 1);
  });

  test("re-registering after an offline session restores connected status", () => {
    const registry = createAdbSessionRegistry();
    registry.register("device-1", "127.0.0.1:41001");
    registry.markOffline("device-1");

    registry.register("device-1", "127.0.0.1:41009");

    assert.equal(registry.get("device-1")?.status, "connected");
    assert.equal(registry.get("device-1")?.serial, "127.0.0.1:41009");
  });

  test("clear on an already offline session deletes it without a second notify", () => {
    const registry = createAdbSessionRegistry();
    const events: unknown[] = [];
    registry.subscribeDisconnect((event) => events.push(event));
    registry.register("device-1", "127.0.0.1:41001");
    registry.markOffline("device-1", "adb_ws_disconnected");

    assert.equal(registry.clear("device-1", "adb_forgotten"), true);
    assert.equal(registry.get("device-1"), null);
    assert.equal(events.length, 1);
  });
});
