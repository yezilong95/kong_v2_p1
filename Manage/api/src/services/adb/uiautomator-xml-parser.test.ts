import assert from "node:assert/strict";
import { test } from "node:test";
import { parseUiAutomatorXml } from "./uiautomator-xml-parser.js";

const SAMPLE = `<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>
<hierarchy rotation="0">
  <node index="0" text="" resource-id="" class="android.widget.FrameLayout" package="com.android.systemui" content-desc="" checkable="false" clickable="false" enabled="true" focusable="false" bounds="[0,0][1080,2400]">
    <node index="0" text="Allow" resource-id="android:id/button1" class="android.widget.Button" package="com.android.systemui" content-desc="Allow projection" checkable="false" clickable="true" enabled="true" focusable="true" bounds="[100,200][400,280]" />
    <node index="1" text="Deny" resource-id="android:id/button2" class="android.widget.Button" package="com.example.app" content-desc="" checkable="false" clickable="true" enabled="true" focusable="true" bounds="[500,200][800,280]" />
  </node>
</hierarchy>`;

test("parses dump xml into assistant nodes with depth and screen size", () => {
  const snapshot = parseUiAutomatorXml(SAMPLE);
  assert.equal(snapshot.nodeCount, 3);
  assert.equal(snapshot.screenWidth, 1080);
  assert.equal(snapshot.screenHeight, 2400);
  assert.equal(snapshot.packageName, "com.example.app");
  assert.equal(snapshot.nodes[0].depth, 0);
  assert.equal(snapshot.nodes[1].depth, 1);
  assert.equal(snapshot.nodes[1].clickable, true);
  assert.equal(snapshot.nodes[1].text, "Allow");
  assert.deepEqual(snapshot.nodes[1].bounds, { left: 100, top: 200, right: 400, bottom: 280 });
  assert.equal(snapshot.nodes[2].packageName, "com.example.app");
});

test("skips invalid bounds and decodes xml entities", () => {
  const snapshot = parseUiAutomatorXml(`
    <node class="android.widget.TextView" text="A &amp; B" bounds="[0,0][0,10]" />
    <node class="android.widget.TextView" text="ok" bounds="[0,0][10,10]" />
  `);
  assert.equal(snapshot.nodeCount, 1);
  assert.equal(snapshot.nodes[0].text, "ok");
});
