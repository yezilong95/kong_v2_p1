import assert from "node:assert/strict";
import { test } from "node:test";
import {
  canonicalizeAccessibilityComponent,
  findRemoteAccessibilityComponentInList,
  isAccessibilityComponentEnabled,
  mergeEnabledAccessibilityServices,
  parseAccessibilityComponent,
  parseAccessibilityComponentFromQueryServices,
  stripRemoteAccessibilityServices,
} from "./adb-enable-accessibility.js";

const FULL = "com.skin.app/com.vibelink.client.service.RemoteAccessibilityService";
const VIVO_FULL =
  "com.Aeqweqw.client/com.vibelink.client.service.RemoteAccessibilityService";

test("parses RemoteAccessibilityService from dumpsys accessibility", () => {
  const dumpsys = `
User state[attributes:{id=0, installedServicesCount=2]
  Service[label=TalkBack]
    ResolveInfo{aaa com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService m=0x108000}
  Service[label=Client]
    ComponentInfo{com.skin.app/com.vibelink.client.service.RemoteAccessibilityService}
`;
  assert.equal(parseAccessibilityComponent(dumpsys), FULL);
});

test("parses RemoteAccessibilityService from query-services (ColorOS/OriginOS)", () => {
  const queryOut = `
5 services found:
  Service #4:
    ServiceInfo:
      name=com.vibelink.client.service.RemoteAccessibilityService
      packageName=com.Aeqweqw.client
      enabled=true exported=false directBootAware=true
      ApplicationInfo:
        name=com.vibelink.client.RemoteApp
        packageName=com.Aeqweqw.client
`;
  assert.equal(parseAccessibilityComponentFromQueryServices(queryOut), VIVO_FULL);
  assert.equal(parseAccessibilityComponentFromQueryServices("no services"), null);
});

test("parses RemoteAccessibilityService from dumpsys package intent filter line", () => {
  const packageDump = `
      990526a com.Aeqweqw.client/com.vibelink.client.service.RemoteAccessibilityService filter 38b935b permission android.permission.BIND_ACCESSIBILITY_SERVICE
`;
  assert.equal(parseAccessibilityComponent(packageDump), VIVO_FULL);
});

test("canonicalizes short component form", () => {
  assert.equal(
    canonicalizeAccessibilityComponent("com.skin.app/.service.RemoteAccessibilityService"),
    FULL,
  );
  assert.equal(canonicalizeAccessibilityComponent(FULL), FULL);
  assert.equal(canonicalizeAccessibilityComponent("com.other/.FooService"), null);
});

test("finds our service in enabled list without dumpsys", () => {
  assert.equal(findRemoteAccessibilityComponentInList("null"), null);
  assert.equal(findRemoteAccessibilityComponentInList(""), null);
  assert.equal(
    findRemoteAccessibilityComponentInList("com.skin.app/.service.RemoteAccessibilityService"),
    FULL,
  );
  assert.equal(
    findRemoteAccessibilityComponentInList(
      "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService:com.skin.app/com.vibelink.client.service.RemoteAccessibilityService",
    ),
    FULL,
  );
});

test("detects enabled list with either component form", () => {
  assert.equal(isAccessibilityComponentEnabled(FULL, FULL), true);
  assert.equal(
    isAccessibilityComponentEnabled(
      "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService:com.skin.app/.service.RemoteAccessibilityService",
      FULL,
    ),
    true,
  );
  assert.equal(isAccessibilityComponentEnabled("null", FULL), false);
  assert.equal(isAccessibilityComponentEnabled("", FULL), false);
});

test("merges without wiping existing services", () => {
  assert.equal(mergeEnabledAccessibilityServices("null", FULL), FULL);
  assert.equal(mergeEnabledAccessibilityServices("", FULL), FULL);
  assert.equal(
    mergeEnabledAccessibilityServices(
      "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService",
      FULL,
    ),
    "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService:com.skin.app/com.vibelink.client.service.RemoteAccessibilityService",
  );
  assert.equal(
    mergeEnabledAccessibilityServices("com.skin.app/.service.RemoteAccessibilityService", FULL),
    "com.skin.app/.service.RemoteAccessibilityService",
  );
});

test("strips our service and keeps other accessibility services", () => {
  assert.equal(stripRemoteAccessibilityServices("null"), "");
  assert.equal(stripRemoteAccessibilityServices(FULL), "");
  assert.equal(
    stripRemoteAccessibilityServices(
      "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService:com.skin.app/.service.RemoteAccessibilityService",
    ),
    "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService",
  );
});
