import { createContext, useContext, useEffect, useMemo, useState, type ReactNode } from "react";
import { getDisplaySiteName } from "../config";
import { hydrateLocale, getLocale, setLocale as setStoreLocale, subscribeLocale, t as translate } from "./store";
import type { Locale } from "./locale";

interface I18nContextValue {
  locale: Locale;
  setLocale: (locale: Locale) => void;
  t: typeof translate;
}

const I18nContext = createContext<I18nContextValue | null>(null);

hydrateLocale();

export function I18nProvider({ children }: { children: ReactNode }) {
  const [locale, setLocaleState] = useState<Locale>(() => getLocale());

  useEffect(() => {
    document.title = getDisplaySiteName();
    return subscribeLocale(() => {
      setLocaleState(getLocale());
      document.title = getDisplaySiteName();
    });
  }, []);

  const value = useMemo<I18nContextValue>(
    () => ({
      locale,
      setLocale: setStoreLocale,
      t: translate,
    }),
    [locale],
  );

  return <I18nContext.Provider value={value}>{children}</I18nContext.Provider>;
}

export function useI18n(): I18nContextValue {
  const ctx = useContext(I18nContext);
  if (!ctx) {
    throw new Error("useI18n must be used within I18nProvider");
  }
  return ctx;
}
