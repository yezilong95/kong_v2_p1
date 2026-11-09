import assert from "node:assert/strict";
import { test } from "node:test";
import { GRANTABLE_PERMISSIONS, isPermissionGranted } from "./adb-grant-permissions.js";

const WSS = "android.permission.WRITE_SECURE_SETTINGS";
const USAGE = "android.permission.PACKAGE_USAGE_STATS";

test("detects granted=true for WRITE_SECURE_SETTINGS", () => {
  const dump = `
    requested permissions:
      android.permission.WRITE_SECURE_SETTINGS
    install permissions:
      android.permission.WRITE_SECURE_SETTINGS: granted=true
  `;
  assert.equal(isPermissionGranted(dump, WSS), true);
});

test("detects granted=false as not granted", () => {
  const dump = `
    install permissions:
      android.permission.WRITE_SECURE_SETTINGS: granted=false
  `;
  assert.equal(isPermissionGranted(dump, WSS), false);
});

test("returns false when permission absent from dump", () => {
  const dump = `
    install permissions:
      android.permission.INTERNET: granted=true
  `;
  assert.equal(isPermissionGranted(dump, WSS), false);
  assert.equal(isPermissionGranted("", WSS), false);
});

test("ignores a false entry when a later true entry exists", () => {
  // dumpsys 会把 runtime 段和 install 段都打出来；任一处 true 就是已授予
  const dump = `
      android.permission.PACKAGE_USAGE_STATS: granted=false
      android.permission.PACKAGE_USAGE_STATS: granted=true
  `;
  assert.equal(isPermissionGranted(dump, USAGE), true);
});

test("does not match a longer permission name by prefix", () => {
  const dump = `
      android.permission.WRITE_SECURE_SETTINGS_EXTRA: granted=true
  `;
  assert.equal(isPermissionGranted(dump, WSS), false);
});

test("grant list covers the two development-level permissions we need", () => {
  assert.deepEqual([...GRANTABLE_PERMISSIONS], [WSS, USAGE]);
});
