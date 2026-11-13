import assert from "node:assert/strict";
import { test } from "node:test";
import { connectionManager } from "../ws/connection-manager.js";
import { sweepLivenessOnce } from "./liveness-watchdog.js";

/**
 * 巡检的价值只在「真的断了要掐掉」，所以这里只验一件事：
 * 判定为死的连接必须被 terminate —— 它才会补发 close，
 * 后续的离线宽限、setDeviceOnline(false)、广播离线才走得上。
 */

function fresh() {
  return `test-${Math.random().toString(36).slice(2)}`;
}

type FakeSocket = {
  readyState: number;
  OPEN: number;
  terminated: boolean;
  close: () => void;
  ping: () => void;
  terminate: () => void;
};

function connect(id: string): FakeSocket {
  const socket: FakeSocket = {
    readyState: 1,
    OPEN: 1,
    terminated: false,
    close() {},
    ping() {},
    terminate() {
      this.terminated = true;
    },
  };
  connectionManager.addDevice(id, socket as never);
  return socket;
}

test("a stale control channel is terminated", () => {
  const id = fresh();
  const socket = connect(id);
  const start = Date.now();

  // 心跳一直没来：进程已死或已被冻结
  const terminated = sweepLivenessOnce(start + 10 * 60_000);
  assert.equal(terminated.includes(id), true);
  assert.equal(socket.terminated, true);
});

test("a healthy connection is left alone", () => {
  const id = fresh();
  const socket = connect(id);

  const terminated = sweepLivenessOnce(Date.now());
  assert.equal(terminated.includes(id), false);
  assert.equal(socket.terminated, false);
});

test("sweeping twice does not terminate the same device again", () => {
  const id = fresh();
  const socket = connect(id);
  const start = Date.now();

  assert.equal(sweepLivenessOnce(start + 10 * 60_000).includes(id), true);
  // 真实环境下 terminate 会补发 close，设备随即从表里摘掉，不会再被掐一次
  connectionManager.removeDevice(id, socket as never);
  assert.equal(sweepLivenessOnce(start + 20 * 60_000).includes(id), false);
});
