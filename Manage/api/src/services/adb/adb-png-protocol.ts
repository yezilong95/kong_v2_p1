export const ADB_PNG_MAGIC = "VLPN";
export const ADB_PNG_VERSION = 1;
export const ADB_PNG_HEADER_SIZE = 28;
export const ADB_PNG_MAX_PAYLOAD_BYTES = 4 * 1024 * 1024;

export interface AdbPngFrame {
  version: number;
  seq: number;
  width: number;
  height: number;
  timestamp: number;
  payload: Buffer;
}

export function encodeAdbPngFrame(frame: AdbPngFrame): Buffer {
  if (frame.version !== ADB_PNG_VERSION) {
    throw new Error(`adb_png_unsupported_version:${frame.version}`);
  }
  if (frame.payload.length === 0) {
    throw new Error("adb_png_empty_payload");
  }
  if (frame.payload.length > ADB_PNG_MAX_PAYLOAD_BYTES) {
    throw new Error(
      `adb_png_payload_too_large:${frame.payload.length}`,
    );
  }
  const header = Buffer.alloc(ADB_PNG_HEADER_SIZE);
  header.write(ADB_PNG_MAGIC, 0, 4, "ascii");
  header.writeUInt8(ADB_PNG_VERSION, 4);
  header.writeUInt32BE(frame.seq >>> 0, 8);
  header.writeUInt16BE(frame.width, 12);
  header.writeUInt16BE(frame.height, 14);
  header.writeBigUInt64BE(BigInt(frame.timestamp), 16);
  header.writeUInt32BE(frame.payload.length, 24);
  return Buffer.concat([header, frame.payload]);
}

export function decodeAdbPngFrame(data: Buffer): AdbPngFrame {
  if (data.length < ADB_PNG_HEADER_SIZE) {
    throw new Error("adb_png_frame_truncated");
  }
  const magic = data.subarray(0, 4).toString("ascii");
  if (magic !== ADB_PNG_MAGIC) {
    throw new Error(`adb_png_bad_magic:${magic}`);
  }
  const version = data.readUInt8(4);
  if (version !== ADB_PNG_VERSION) {
    throw new Error(`adb_png_unsupported_version:${version}`);
  }
  const seq = data.readUInt32BE(8);
  const width = data.readUInt16BE(12);
  const height = data.readUInt16BE(14);
  const timestamp = Number(data.readBigUInt64BE(16));
  const payloadLen = data.readUInt32BE(24);
  if (data.length !== ADB_PNG_HEADER_SIZE + payloadLen) {
    throw new Error(
      `adb_png_length_mismatch:declared=${payloadLen}:actual=${data.length - ADB_PNG_HEADER_SIZE}`,
    );
  }
  return {
    version,
    seq,
    width,
    height,
    timestamp,
    payload: Buffer.from(data.subarray(ADB_PNG_HEADER_SIZE)),
  };
}

const PNG_SIGNATURE = Buffer.from([
  0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a,
]);

export function inspectPng(payload: Buffer): { width: number; height: number } {
  if (payload.length < 33) {
    throw new Error("adb_png_invalid:truncated");
  }
  if (!payload.subarray(0, 8).equals(PNG_SIGNATURE)) {
    throw new Error("adb_png_invalid:bad_signature");
  }
  const ihdrType = payload.subarray(12, 16).toString("ascii");
  if (ihdrType !== "IHDR") {
    throw new Error("adb_png_invalid:missing_ihdr");
  }
  const iendIndex = payload.lastIndexOf(Buffer.from("IEND"));
  if (iendIndex < 0 || iendIndex + 8 > payload.length) {
    throw new Error("adb_png_invalid:missing_iend");
  }
  return {
    width: payload.readUInt32BE(16),
    height: payload.readUInt32BE(20),
  };
}
