import assert from "node:assert/strict";
import { test } from "node:test";
import { setLocale } from "../i18n/store.ts";
import {
  describeA11yProbe,
  describeA11ySetting,
  formatRelativeTime,
} from "./accessibility-view.ts";

setLocale("zh-CN");

const NOW = Date.parse("2026-11-06T16:00:00.000Z");

test("relative time buckets", () => {
  assert.equal(formatRelativeTime(null, NOW), "-");
  assert.equal(formatRelativeTime(0, NOW), "-");
  assert.equal(formatRelativeTime(NOW - 3_000, NOW), "刚刚");
  assert.equal(formatRelativeTime(NOW - 42_000, NOW), "42 秒前");
  assert.equal(formatRelativeTime(NOW - 5 * 60_000, NOW), "5 分钟前");
  assert.equal(formatRelativeTime(NOW - 3 * 3_600_000, NOW), "3 小时前");
  assert.equal(formatRelativeTime(NOW - 2 * 86_400_000, NOW), "2 天前");
  // 时钟漂移：未来时间不能显示成负数
  assert.equal(formatRelativeTime(NOW + 5_000, NOW), "刚刚");
});

test("offline device never reuses the frozen a11y label", () => {
  const view = describeA11ySetting({
    isOnline: false,
    status: "未绑定",
    lastHeartbeatAt: "2026-11-06T15:57:00.000Z",
    now: NOW,
  });
  assert.equal(view.label, "已离线");
  assert.equal(view.tone, "idle");
  assert.equal(view.stale, true);
  // 旧值仍要在 title 里交代清楚，否则运维不知道它掉线前是什么
  assert.match(view.title, /未绑定/);
  assert.match(view.title, /3 分钟前/);
});

test("offline device without heartbeat is still offline, not unknown", () => {
  const view = describeA11ySetting({
    isOnline: false,
    status: null,
    lastHeartbeatAt: null,
    now: NOW,
  });
  assert.equal(view.label, "已离线");
  assert.equal(view.stale, false);
  assert.equal(view.tone, "idle");
});

test("online 未绑定 is a hard failure, not a neutral state", () => {
  const view = describeA11ySetting({
    isOnline: true,
    status: "未绑定",
    lastHeartbeatAt: "2026-11-06T15:59:00.000Z",
    now: NOW,
  });
  assert.equal(view.label, "未绑定");
  assert.equal(view.tone, "danger");
  assert.equal(view.stale, false);
  assert.match(view.title, /18 条控制命令/);
});

test("online 允许 and 拒绝 map to distinct tones", () => {
  const allowed = describeA11ySetting({
    isOnline: true,
    status: "允许",
    lastHeartbeatAt: null,
    now: NOW,
  });
  assert.equal(allowed.tone, "ok");
  const denied = describeA11ySetting({
    isOnline: true,
    status: "拒绝",
    lastHeartbeatAt: null,
    now: NOW,
  });
  assert.equal(denied.tone, "danger");
});

test("probe column shows 检测中 before the first sample lands", () => {
  const view = describeA11yProbe({ isOnline: true, probe: null, now: NOW });
  assert.equal(view.label, "检测中");
  assert.equal(view.tone, "idle");
});

test("probe column never claims health for an offline device", () => {
  const view = describeA11yProbe({
    isOnline: false,
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 60_000,
      error: null,
    },
    now: NOW,
  });
  assert.equal(view.label, "无法探测");
});

test("settings says allowed but service is dead — must call it out", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: false,
      controlReady: false,
      probedAt: NOW - 30_000,
      error: null,
    },
    now: NOW,
  });
  assert.equal(view.label, "未连接");
  assert.equal(view.tone, "danger");
  assert.match(view.title, /假阳性/);
});

test("service connected but control bridge not ready is a warning, not ok", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: false,
      probedAt: NOW - 10_000,
      error: null,
    },
    now: NOW,
  });
  assert.equal(view.tone, "warn");
  assert.equal(view.label, "服务在·控制未就绪");
});

test("fully healthy probe reports ok", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 10_000,
      error: null,
    },
    now: NOW,
  });
  assert.equal(view.label, "已连接");
  assert.equal(view.tone, "ok");
});

test("probe error is distinguished from a confirmed disconnect", () => {
  const view = describeA11yProbe({
    isOnline: true,
    probe: {
      serviceConnected: null,
      controlReady: null,
      probedAt: NOW - 20_000,
      error: "wait_timeout",
    },
    now: NOW,
  });
  assert.equal(view.label, "探测失败");
  assert.equal(view.tone, "danger");
  assert.match(view.title, /wait_timeout/);
});

test("old client without serviceConnected reports 未知 not 未连接", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: null,
      controlReady: null,
      probedAt: NOW - 20_000,
      error: null,
    },
    now: NOW,
  });
  assert.equal(view.label, "未知");
  assert.equal(view.tone, "idle");
});

test("a heartbeat-sourced value names its origin", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 20_000,
      error: null,
      source: "heartbeat",
    },
    now: NOW,
  });
  assert.equal(view.label, "已连接");
  assert.match(view.title, /心跳上报/);
});

test("a probe-sourced value names its origin", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 20_000,
      error: null,
      source: "probe",
    },
    now: NOW,
  });
  assert.equal(view.label, "已连接");
  assert.match(view.title, /服务端探测/);
});

test("a stale heartbeat must not keep showing 已连接", () => {
  // 设备被冻结：心跳停在 5 分钟前，值还是 true。
  // 这正是「未绑定 / 已离线」要修的那类误导，不能给绿色。
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 5 * 60_000,
      error: null,
      source: "heartbeat",
    },
    now: NOW,
  });
  assert.equal(view.label, "心跳停更");
  assert.equal(view.tone, "warn");
  assert.match(view.title, /冻结/);
});

test("a fresh heartbeat is not flagged as stale", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 30_000,
      error: null,
      source: "heartbeat",
    },
    now: NOW,
  });
  assert.equal(view.label, "已连接");
});

test("an old probe value never goes stale — probes are already TTL-bounded", () => {
  const view = describeA11yProbe({
    isOnline: true,
    settingStatus: "允许",
    probe: {
      serviceConnected: true,
      controlReady: true,
      probedAt: NOW - 5 * 60_000,
      error: null,
      source: "probe",
    },
    now: NOW,
  });
  assert.equal(view.label, "已连接");
});
