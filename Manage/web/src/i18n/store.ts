import { DEFAULT_LOCALE, persistLocale, readStoredLocale, type Locale } from "./locale.ts";
import { ID_EXTRA } from "./messages-id-extra.ts";
import { ID_MESSAGES } from "./messages-id.ts";

const MESSAGES: Record<string, string> = { ...ID_MESSAGES, ...ID_EXTRA };

type Listener = () => void;

let currentLocale: Locale = DEFAULT_LOCALE;
const listeners = new Set<Listener>();

export function getLocale(): Locale {
  return currentLocale;
}

export function hydrateLocale() {
  currentLocale = readStoredLocale();
  applyDocumentLocale(currentLocale);
}

export function setLocale(locale: Locale) {
  if (locale === currentLocale) {
    persistLocale(locale);
    applyDocumentLocale(locale);
    return;
  }
  currentLocale = locale;
  persistLocale(locale);
  applyDocumentLocale(locale);
  listeners.forEach((listener) => listener());
}

export function subscribeLocale(listener: Listener) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

export function t(source: string, vars?: Record<string, string | number | unknown>): string {
  const translated = currentLocale === "zh-CN" ? source : (MESSAGES[source] ?? source);
  if (!vars) {
    return translated;
  }
  let out = translated;
  for (const [key, value] of Object.entries(vars)) {
    out = out.split(`{${key}}`).join(String(value ?? ""));
  }
  return out;
}

function applyDocumentLocale(locale: Locale) {
  if (typeof document === "undefined") {
    return;
  }
  document.documentElement.lang = locale === "zh-CN" ? "zh-CN" : "id";
}
