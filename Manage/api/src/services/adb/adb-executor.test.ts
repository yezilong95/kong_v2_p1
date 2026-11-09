import assert from "node:assert/strict";
import { chmodSync, mkdtempSync, writeFileSync } from "node:fs";
import { tmpdir } from "node:os";
import { join } from "node:path";
import { describe, test } from "node:test";
import {
  AdbExecutionError,
  executeAdbBinary,
  executeAdbText,
} from "./adb-executor.js";

describe("adb executor", () => {
  test("binary execution preserves stdout bytes", async () => {
    const expected = Buffer.from([0x00, 0xff, 0x80, 0x41, 0x00]);

    const result = await executeAdbBinary(
      ["-e", `process.stdout.write(Buffer.from("${expected.toString("base64")}", "base64"))`],
      {
        adbBin: process.execPath,
        operation: "capture",
        timeoutMs: 1_000,
      },
    );

    assert.equal(result.code, 0);
    assert.deepEqual(result.stdout, expected);
  });

  test("text execution passes serial and metacharacters as literal arguments", async () => {
    const result = await executeAdbText(
      ["shell metacharacters ; $HOME"],
      {
        adbBin: "/bin/echo",
        operation: "probe",
        serial: "127.0.0.1:41001",
        timeoutMs: 1_000,
      },
    );

    assert.equal(
      result.stdout.trim(),
      "-s 127.0.0.1:41001 shell metacharacters ; $HOME",
    );
  });

  test("timeout kills the process and reports operation and serial", async () => {
    const dir = mkdtempSync(join(tmpdir(), "adb-exec-"));
    const sleeper = join(dir, "sleeper.mjs");
    writeFileSync(sleeper, "#!/usr/bin/env node\nsetInterval(() => undefined, 1000);\n");
    chmodSync(sleeper, 0o755);

    await assert.rejects(
      executeAdbText([], {
        adbBin: sleeper,
        operation: "transport_probe",
        serial: "127.0.0.1:41001",
        timeoutMs: 40,
      }),
      (error: unknown) => {
        assert.ok(error instanceof AdbExecutionError);
        assert.equal(error.isTimeout, true);
        assert.match(error.message, /transport_probe/);
        assert.match(error.message, /127\.0\.0\.1:41001/);
        return true;
      },
    );
  });
});
