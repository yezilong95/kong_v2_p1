import assert from "node:assert/strict";
import { test } from "node:test";
import {
  parseScreenMirrorPrefs,
  resolveMirrorModeForCast,
  baseMirrorMode,
  mirrorTransport,
  mirrorQuality,
  normalizeMirrorMode,
} from "./screen-mirror-prefs.ts";

test("invalid prefs fall back to defaults", () => {
  const parsed = parseScreenMirrorPrefs(
    `{"mode":"nope","intervals":{"adb_screenshot":123,"projection":"nope"}}`,
  );
  assert.equal(parsed.mode, "screenshot_ws");
  assert.equal(parsed.intervals.screenshot, 400);
  assert.equal(parsed.intervals.projection, 250);
  assert.equal(parsed.intervals.screenhelper, 400);
  assert.equal(parsed.intervals.projection_ws, 250);
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

test("ADB + a11y off forces projection; a11y on keeps saved mode", () => {
  assert.equal(resolveMirrorModeForCast(true, "screenshot", false), "projection");
  assert.equal(resolveMirrorModeForCast(true, "screenhelper", false), "projection");
  assert.equal(resolveMirrorModeForCast(true, "screenshot", true), "screenshot");
  assert.equal(resolveMirrorModeForCast(true, "projection", true), "projection");
  assert.equal(resolveMirrorModeForCast(false, "screenshot", true), "screenshot");
  assert.equal(resolveMirrorModeForCast(false, "projection", false), "projection");
});

test("ADB + a11y off preserves transport and quality", () => {
  assert.equal(resolveMirrorModeForCast(true, "screenshot_ws", false), "projection_ws");
  assert.equal(resolveMirrorModeForCast(true, "screenhelper_ws", false), "projection_ws");
  assert.equal(resolveMirrorModeForCast(true, "projection_ws", false), "projection_ws");
  assert.equal(
    resolveMirrorModeForCast(true, "screenshot_ws_hd", false),
    "projection_ws_hd",
  );
  assert.equal(
    resolveMirrorModeForCast(true, "screenhelper_ws_hd", false),
    "projection_ws_hd",
  );
  assert.equal(
    resolveMirrorModeForCast(true, "projection_ws_hd", false),
    "projection_ws_hd",
  );
});

test("baseMirrorMode extracts source for all 9 modes", () => {
  assert.equal(baseMirrorMode("screenshot"), "screenshot");
  assert.equal(baseMirrorMode("screenshot_ws"), "screenshot");
  assert.equal(baseMirrorMode("screenshot_ws_hd"), "screenshot");
  assert.equal(baseMirrorMode("projection"), "projection");
  assert.equal(baseMirrorMode("projection_ws"), "projection");
  assert.equal(baseMirrorMode("projection_ws_hd"), "projection");
  assert.equal(baseMirrorMode("screenhelper"), "screenhelper");
  assert.equal(baseMirrorMode("screenhelper_ws"), "screenhelper");
  assert.equal(baseMirrorMode("screenhelper_ws_hd"), "screenhelper");
});

test("mirrorTransport extracts transport", () => {
  assert.equal(mirrorTransport("screenshot"), "webrtc");
  assert.equal(mirrorTransport("screenshot_ws"), "websocket");
  assert.equal(mirrorTransport("projection"), "webrtc");
  assert.equal(mirrorTransport("projection_ws"), "websocket");
  assert.equal(mirrorTransport("projection_ws_hd"), "websocket");
});

test("mirrorQuality extracts quality", () => {
  assert.equal(mirrorQuality("screenshot"), "standard");
  assert.equal(mirrorQuality("screenshot_ws"), "standard");
  assert.equal(mirrorQuality("screenshot_ws_hd"), "hd");
  assert.equal(mirrorQuality("projection_ws_hd"), "hd");
  assert.equal(mirrorQuality("screenhelper_ws_hd"), "hd");
});

test("normalizeMirrorMode handles all 9 modes plus legacy aliases", () => {
  assert.equal(normalizeMirrorMode("projection_ws_hd"), "projection_ws_hd");
  assert.equal(normalizeMirrorMode("screenshot_ws_hd"), "screenshot_ws_hd");
  assert.equal(normalizeMirrorMode("screenhelper_ws_hd"), "screenhelper_ws_hd");
  assert.equal(normalizeMirrorMode("projection_ws"), "projection_ws");
  assert.equal(normalizeMirrorMode("screenshot_ws"), "screenshot_ws");
  assert.equal(normalizeMirrorMode("adb_screenshot"), "projection");
  assert.equal(normalizeMirrorMode("jpeg"), "projection_ws");
  assert.equal(normalizeMirrorMode("websocket"), "projection_ws");
  assert.equal(normalizeMirrorMode("nope"), "screenshot_ws");
});

test("hd modes fall back to _ws interval when storage lacks their own key", () => {
  const parsed = parseScreenMirrorPrefs(
    JSON.stringify({
      mode: "projection_ws_hd",
      intervals: { projection_ws: 500 },
    }),
  );
  assert.equal(parsed.mode, "projection_ws_hd");
  assert.equal(parsed.intervals.projection_ws_hd, 500);
});

test("hd quality defaults to 250ms, standard ws to 250ms, screenshot/screenhelper to 400ms", () => {
  const parsed = parseScreenMirrorPrefs(null);
  assert.equal(parsed.intervals.screenshot, 400);
  assert.equal(parsed.intervals.screenhelper, 400);
  assert.equal(parsed.intervals.screenhelper_ws, 400);
  assert.equal(parsed.intervals.screenshot_ws, 250);
  assert.equal(parsed.intervals.screenshot_ws_hd, 250);
  assert.equal(parsed.intervals.projection, 250);
  assert.equal(parsed.intervals.projection_ws, 250);
  assert.equal(parsed.intervals.projection_ws_hd, 250);
});
