import assert from "node:assert/strict";
import { test } from "node:test";
import {
  accessibilityCloseButtonLabel,
  accessibilityOpenButtonLabel,
  applyOptimisticAccessibilityStatus,
  consumeA11yOverrideIfConfirmed,
  isAdbChannelLockedOn,
} from "./adb-channel.ts";

test("locks ADB channel on when accessibility is off", () => {
  assert.equal(isAdbChannelLockedOn(false), true);
  assert.equal(isAdbChannelLockedOn(true), false);
  assert.equal(isAdbChannelLockedOn(null), false);
});

test("close/open a11y buttons show current status", () => {
  assert.equal(accessibilityCloseButtonLabel(true, false), "关闭无障碍");
  assert.equal(accessibilityCloseButtonLabel(false, false), "无障碍已关");
  assert.equal(accessibilityCloseButtonLabel(null, false), "关闭无障碍");
  assert.equal(accessibilityCloseButtonLabel(false, true), "关闭中…");
  assert.equal(accessibilityOpenButtonLabel(true, false), "无障碍已开");
  assert.equal(accessibilityOpenButtonLabel(false, false), "打开无障碍");
  assert.equal(accessibilityOpenButtonLabel(null, false), "打开无障碍");
  assert.equal(accessibilityOpenButtonLabel(true, true), "打开中…");
});

test("optimistic a11y status is not overwritten by stale heartbeat", () => {
  const devices = [
    { deviceId: "d1", accessibilityStatus: "拒绝" as string | null },
    { deviceId: "d2", accessibilityStatus: "允许" as string | null },
  ];
  const override = { deviceId: "d1", enabled: true };
  const merged = applyOptimisticAccessibilityStatus(devices, override);
  assert.equal(merged[0]?.accessibilityStatus, "允许");
  assert.equal(merged[1]?.accessibilityStatus, "允许");
  assert.equal(consumeA11yOverrideIfConfirmed(devices, override)?.enabled, true);
  assert.equal(
    consumeA11yOverrideIfConfirmed(
      [{ deviceId: "d1", accessibilityStatus: "允许" }],
      override,
    ),
    null,
  );
});
