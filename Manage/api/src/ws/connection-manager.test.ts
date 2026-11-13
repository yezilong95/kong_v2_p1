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

/**
 * 通道活性：设备关机时 TCP 停在半开态，内核不吐 FIN，`close` 一次都不来。
 * 只能靠主动 ping + 应用层心跳判定，否则界面会一直显示「在线」，
 * 最长要等 nginx `proxy_read_timeout`（1 小时）才自愈。
 */

type FakeSocket = {
  readyState: number;
  OPEN: number;
  pingCount: number;
  terminated: boolean;
  close: () => void;
  ping: () => void;
  terminate: () => void;
};

function fakeSocket(): FakeSocket {
  return {
    readyState: 1,
    OPEN: 1,
    pingCount: 0,
    terminated: false,
    close() {},
    ping() {
      this.pingCount += 1;
    },
    terminate() {
      this.terminated = true;
    },
  };
}

function connect(id: string) {
  const socket = fakeSocket();
  connectionManager.addDevice(id, socket as never);
  return socket;
}

test("a fresh connection is not judged dead on the first sweep", () => {
  const id = fresh();
  connect(id);

  const dead = connectionManager.sweepLiveness(Date.now());
  assert.equal(
    dead.some((entry) => entry.deviceId === id),
    false,
    "刚连上的设备不能被第一轮扫描误杀",
  );
});

test("no pong for the timeout window is judged dead", () => {
  const id = fresh();
  const socket = connect(id);
  const start = Date.now();

  // 两轮扫描：心跳保持新鲜（进程还在），但 pong 一直不回 —— 典型半开 TCP
  connectionManager.markAppHeartbeat(id, start + 30_000);
  assert.deepEqual(
    connectionManager.sweepLiveness(start + 30_000).filter((entry) => entry.deviceId === id),
    [],
  );
  connectionManager.markAppHeartbeat(id, start + 90_000);

  const dead = connectionManager.sweepLiveness(start + 90_000);
  assert.deepEqual(
    dead.filter((entry) => entry.deviceId === id),
    [{ deviceId: id, reason: "ws_pong_timeout" }],
  );
  assert.equal(socket.pingCount > 0, true, "扫描应该主动发过 ping");
});

test("a fresh pong keeps the connection alive", () => {
  const id = fresh();
  connect(id);
  const start = Date.now();

  connectionManager.markPong(id, start + 30_000);
  connectionManager.markAppHeartbeat(id, start + 30_000);
  connectionManager.sweepLiveness(start + 30_000);

  connectionManager.markPong(id, start + 60_000);
  connectionManager.markAppHeartbeat(id, start + 60_000);
  const dead = connectionManager.sweepLiveness(start + 60_000);
  assert.equal(dead.some((entry) => entry.deviceId === id), false);
});

test("a frozen app is judged dead even while pong keeps coming", () => {
  const id = fresh();
  connect(id);
  const start = Date.now();

  // Hiber 冻住进程：内核照样回 pong，但 JSON 心跳停了。
  // 这类设备收不到任何指令，按离线算。
  connectionManager.markAppHeartbeat(id, start);
  connectionManager.markPong(id, start + 90_000);
  const dead = connectionManager.sweepLiveness(start + 90_000);
  assert.deepEqual(
    dead.filter((entry) => entry.deviceId === id),
    [{ deviceId: id, reason: "heartbeat_stale" }],
  );
});

test("a client that never sends JSON heartbeats is not killed for it", () => {
  const id = fresh();
  connect(id);
  const start = Date.now();

  // 老客户端只回 pong、不发心跳：不能按心跳停更判死，否则会无限重连
  connectionManager.markPong(id, start + 90_000);
  const dead = connectionManager.sweepLiveness(start + 90_000);
  assert.equal(dead.some((entry) => entry.deviceId === id), false);
});

test("removeDevice clears the liveness entry so the map cannot grow forever", () => {
  const id = fresh();
  const socket = connect(id);
  connectionManager.removeDevice(id, socket as never);

  // 条目清掉后，即便时间戳非常旧也不该再被判死
  const dead = connectionManager.sweepLiveness(Date.now() + 10 * 60_000);
  assert.equal(dead.some((entry) => entry.deviceId === id), false);
});

test("a late pong cannot resurrect a device that is already gone", () => {
  const id = fresh();
  const socket = connect(id);
  connectionManager.removeDevice(id, socket as never);

  // close 之后才到的 pong 不应该重新建出活性条目
  connectionManager.markPong(id, Date.now());
  assert.equal(connectionManager.isDeviceConnected(id), false);
});
