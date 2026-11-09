import assert from "node:assert/strict";
import { test } from "node:test";
import {
  decodeAdbPngFrame,
  encodeAdbPngFrame,
  inspectPng,
} from "./adb-png-protocol.js";

const ONE_PIXEL_PNG = Buffer.from(
  "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mP8z8BQDwAEhQGAhKmMIQAAAABJRU5ErkJggg==",
  "base64",
);

test("inspectPng reads IHDR dimensions and requires IEND", () => {
  assert.deepEqual(inspectPng(ONE_PIXEL_PNG), { width: 1, height: 1 });
  assert.throws(() => inspectPng(ONE_PIXEL_PNG.subarray(0, 20)), /truncated|signature|ihdr/);
});

test("VLPN roundtrip preserves seq width height timestamp and payload", () => {
  const encoded = encodeAdbPngFrame({
    version: 1,
    seq: 42,
    width: 1,
    height: 1,
    timestamp: 1_700_000_000_123,
    payload: ONE_PIXEL_PNG,
  });
  const decoded = decodeAdbPngFrame(encoded);
  assert.equal(decoded.seq, 42);
  assert.equal(decoded.width, 1);
  assert.equal(decoded.height, 1);
  assert.equal(decoded.timestamp, 1_700_000_000_123);
  assert.deepEqual(decoded.payload, ONE_PIXEL_PNG);
});

test("decode rejects stale truncated frames", () => {
  assert.throws(() => decodeAdbPngFrame(Buffer.from("VLPN")), /truncated/);
});
