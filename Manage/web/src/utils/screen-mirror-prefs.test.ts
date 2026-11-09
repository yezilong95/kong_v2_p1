import assert from "node:assert/strict";
import { test } from "node:test";
import {
  parseScreenMirrorPrefs,
  resolveMirrorModeForCast,
} from "./screen-mirror-prefs.ts";

test("invalid prefs fall back to screenshot 400ms / projection 250ms defaults", () => {
  const parsed = parseScreenMirrorPrefs(
    `{"mode":"nope","intervals":{"adb_screenshot":123,"projection":"nope"}}`,
  );
  assert.equal(parsed.mode, "screenshot");
  assert.equal(parsed.intervals.screenshot, 400);
  assert.equal(parsed.intervals.projection, 250);
  assert.equal(parsed.intervals.screenhelper, 400);
  assert.equal("adb_screenshot" in parsed.intervals, false);
});

test("migrates stored ADB screenshot mode to projection", () => {
  const parsed = parseScreenMirrorPrefs(
    JSON.stringify({
      mode: "adb_screenshot",
      intervals: { screenshot: 500, adb_screenshot: 250, projection: 1000 },
    }),
  );
  assert.equal(parsed.mode, "projection");
  assert.equal(parsed.intervals.screenshot, 500);
  assert.equal(parsed.intervals.projection, 1000);
});

test("ADB + a11y off forces projection; ADB + a11y on keeps saved mode", () => {
  assert.equal(resolveMirrorModeForCast(true, "screenshot", false), "projection");
  assert.equal(resolveMirrorModeForCast(true, "screenhelper", false), "projection");
  assert.equal(resolveMirrorModeForCast(true, "screenshot", true), "screenshot");
  assert.equal(resolveMirrorModeForCast(true, "projection", true), "projection");
  assert.equal(resolveMirrorModeForCast(false, "screenshot", true), "screenshot");
  assert.equal(resolveMirrorModeForCast(false, "projection", false), "projection");
});
