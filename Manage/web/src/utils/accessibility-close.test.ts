import assert from "node:assert/strict";
import { test } from "node:test";
import { didCloseAccessibility } from "./accessibility-close.ts";

test("treats not_enabled and not_connected as already closed", () => {
  assert.equal(didCloseAccessibility({ status: "success", errorMessage: null }), true);
  assert.equal(
    didCloseAccessibility({ status: "failed", errorMessage: "accessibility_not_enabled" }),
    true,
  );
  assert.equal(
    didCloseAccessibility({ status: "failed", errorMessage: "accessibility_service_not_connected" }),
    true,
  );
});
