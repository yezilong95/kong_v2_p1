export const ADB_PNG_MAGIC = "VLPN";
export const ADB_PNG_VERSION = 1;
export const ADB_PNG_HEADER_SIZE = 28;

export interface DecodedAdbPngFrame {
  version: number;
  seq: number;
  width: number;
  height: number;
  timestamp: number;
  payload: Uint8Array;
}

function asBytes(data: ArrayBuffer | Uint8Array): Uint8Array {
  return data instanceof Uint8Array ? data : new Uint8Array(data);
}

export function decodeAdbPngFrame(data: ArrayBuffer | Uint8Array): DecodedAdbPngFrame {
  const bytes = asBytes(data);
  if (bytes.byteLength < ADB_PNG_HEADER_SIZE) {
    throw new Error("adb_png_frame_truncated");
  }
  const magic = String.fromCharCode(bytes[0], bytes[1], bytes[2], bytes[3]);
  if (magic !== ADB_PNG_MAGIC) {
    throw new Error(`adb_png_bad_magic:${magic}`);
  }
  const view = new DataView(bytes.buffer, bytes.byteOffset, bytes.byteLength);
  const version = view.getUint8(4);
  if (version !== ADB_PNG_VERSION) {
    throw new Error(`adb_png_unsupported_version:${version}`);
  }
  const seq = view.getUint32(8);
  const width = view.getUint16(12);
  const height = view.getUint16(14);
  const timestamp = Number(view.getBigUint64(16));
  const payloadLen = view.getUint32(24);
  if (bytes.byteLength !== ADB_PNG_HEADER_SIZE + payloadLen) {
    throw new Error(
      `adb_png_length_mismatch:declared=${payloadLen}:actual=${bytes.byteLength - ADB_PNG_HEADER_SIZE}`,
    );
  }
  return {
    version,
    seq,
    width,
    height,
    timestamp,
    payload: bytes.slice(ADB_PNG_HEADER_SIZE),
  };
}

export function shouldDiscardAdbPngSeq(lastSeq: number | null, seq: number): boolean {
  if (lastSeq == null) {
    return false;
  }
  if (seq === lastSeq) {
    return true;
  }
  return ((seq - lastSeq) >>> 0) > 0x7fffffff;
}
