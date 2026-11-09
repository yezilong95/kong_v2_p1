export function isAccessibilityAlreadyOffError(error: string | null | undefined): boolean {
  const normalized = String(error ?? "").trim();
  return (
    normalized === "accessibility_not_enabled" ||
    normalized === "accessibility_service_not_connected"
  );
}

export function didCloseAccessibility(result: {
  status: string;
  errorMessage?: string | null;
}): boolean {
  if (result.status === "success") {
    return true;
  }
  return isAccessibilityAlreadyOffError(result.errorMessage);
}
