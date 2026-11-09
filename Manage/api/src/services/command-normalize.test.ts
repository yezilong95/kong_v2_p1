import assert from "node:assert/strict";
import { test } from "node:test";
import { normalizeDeviceCommand } from "./command-normalize.js";

test("maps disable_accessibility to uninstall for current B packages", () => {
  assert.equal(normalizeDeviceCommand("disable_accessibility"), "uninstall");
  assert.equal(normalizeDeviceCommand("uninstall"), "uninstall");
  assert.equal(normalizeDeviceCommand("click"), "click");
});
