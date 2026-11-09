import assert from "node:assert/strict";
import { test } from "node:test";
import { connectionManager } from "./connection-manager.js";

/**
 * 无障碍「实际连接」有两个来源：新版客户端心跳、服务端探针。
 * 合并规则是「产生时刻更新者胜」——探针回包可能比心跳晚到，
 * 但它问的是更早的时刻，不能把新鲜的心跳值覆盖掉。
 */

function fresh() {
  return `test-${Math.random().toString(36).slice(2)}`;
}

test("heartbeat value is visible through getDeviceHealth", () => {
  const id = fresh();
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: true,
    a11yControlReady: true,
    a11yReportedAt: 1_000,
    a11ySource: "heartbeat",
  });

  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yServiceConnected, true);
  assert.equal(health.a11yControlReady, true);
  assert.equal(health.a11yReportedAt, 1_000);
  assert.equal(health.a11ySource, "heartbeat");
  assert.equal(health.a11yError, null);
});

test("a late-arriving probe cannot overwrite a fresher heartbeat", () => {
  const id = fresh();
  // 心跳先产生（时刻 5000）
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: true,
    a11yControlReady: true,
    a11yReportedAt: 5_000,
    a11ySource: "heartbeat",
  });
  // 探针在更早的时刻开始（1000），但回包更晚——不能覆盖
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: false,
    a11yControlReady: false,
    a11yReportedAt: 1_000,
    a11ySource: "probe",
    a11yError: "wait_timeout",
  });

  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yServiceConnected, true, "stale probe must not win");
  assert.equal(health.a11ySource, "heartbeat");
  assert.equal(health.a11yError, null);
});

test("a fresher probe does overwrite an older heartbeat", () => {
  const id = fresh();
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: true,
    a11yControlReady: true,
    a11yReportedAt: 1_000,
    a11ySource: "heartbeat",
  });
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: false,
    a11yControlReady: false,
    a11yReportedAt: 9_000,
    a11ySource: "probe",
    a11yError: null,
  });

  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yServiceConnected, false);
  assert.equal(health.a11ySource, "probe");
});

test("probe failure is recorded so the UI can show 探测失败", () => {
  const id = fresh();
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: null,
    a11yControlReady: null,
    a11yReportedAt: 2_000,
    a11ySource: "probe",
    a11yError: "device_offline",
  });

  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yError, "device_offline");
  assert.equal(health.a11yServiceConnected, null);
});

test("reconnect clears the cached a11y health so it cannot look alive", () => {
  const id = fresh();
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: true,
    a11yControlReady: true,
    a11yReportedAt: 7_000,
    a11ySource: "probe",
  });
  connectionManager.clearDeviceA11yHealth(id);

  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yServiceConnected, null);
  assert.equal(health.a11yReportedAt, 0);
  assert.equal(health.a11ySource, null);
});

test("clearDeviceA11yHealth is a no-op for an unknown device", () => {
  const id = fresh();
  connectionManager.clearDeviceA11yHealth(id);
  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yServiceConnected, null);
  assert.equal(health.a11yReportedAt, 0);
});

test("a11y RTT updates do not disturb the a11y connection triple", () => {
  const id = fresh();
  connectionManager.updateDeviceHealth(id, {
    a11yServiceConnected: true,
    a11yControlReady: true,
    a11yReportedAt: 3_000,
    a11ySource: "heartbeat",
  });
  // ping 的 RTT 上报不带 a11yReportedAt，不能顺手把三元组清空
  connectionManager.updateDeviceHealth(id, { a11yRttMs: 120 });

  const health = connectionManager.getDeviceHealth(id);
  assert.equal(health.a11yRttMs, 120);
  assert.equal(health.a11yServiceConnected, true);
  assert.equal(health.a11yReportedAt, 3_000);
});
