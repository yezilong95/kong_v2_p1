import assert from "node:assert/strict";
import { test } from "node:test";
import {
  ADB_PNG_HEADER_SIZE,
  decodeAdbPngFrame,
  shouldDiscardAdbPngSeq,
} from "./adb-png-protocol.ts";

function encode(seq: number, payload: Uint8Array): Uint8Array {
  const bytes = new Uint8Array(ADB_PNG_HEADER_SIZE + payload.byteLength);
  const view = new DataView(bytes.buffer);
  bytes.set(Buffer.from("VLPN"), 0);
  view.setUint8(4, 1);
  view.setUint32(8, seq);
  view.setUint16(12, 1);
  view.setUint16(14, 1);
  view.setBigUint64(16, 1234n);
  view.setUint32(24, payload.byteLength);
  bytes.set(payload, ADB_PNG_HEADER_SIZE);
  return bytes;
}

test("decodes VLPN header and payload", () => {
  const payload = Uint8Array.from([1, 2, 3, 4]);
  const decoded = decodeAdbPngFrame(encode(9, payload));
  assert.equal(decoded.seq, 9);
  assert.deepEqual([...decoded.payload], [1, 2, 3, 4]);
});

test("discards stale or duplicate seq, keeps wrap-around", () => {
  assert.equal(shouldDiscardAdbPngSeq(5, 5), true);
  assert.equal(shouldDiscardAdbPngSeq(5, 4), true);
  assert.equal(shouldDiscardAdbPngSeq(5, 6), false);
  assert.equal(shouldDiscardAdbPngSeq(0xffffffff, 1), false);
});
