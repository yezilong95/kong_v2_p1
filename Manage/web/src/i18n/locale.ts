export type Locale = "id" | "zh-CN";

export const DEFAULT_LOCALE: Locale = "id";
export const LOCALE_STORAGE_KEY = "web_locale";

export function isLocale(value: string | null | undefined): value is Locale {
  return value === "id" || value === "zh-CN";
}

export function readStoredLocale(): Locale {
  try {
    const raw = window.localStorage.getItem(LOCALE_STORAGE_KEY);
    if (isLocale(raw)) {
      return raw;
    }
  } catch {
    /* ignore */
  }
  return DEFAULT_LOCALE;
}

export function persistLocale(locale: Locale) {
  try {
    window.localStorage.setItem(LOCALE_STORAGE_KEY, locale);
  } catch {
    /* ignore */
  }
}
