import { useEffect, useMemo, useRef, useState } from "react";
import { translateUiLabels } from "../api/client";
import { isLikelyChinese } from "../utils/ui-tree";

const DEBOUNCE_MS = 500;

export function useUiLabelTranslation(labels: string[], enabled: boolean) {
  const cacheRef = useRef<Record<string, string>>({});
  const inFlightRef = useRef<Set<string>>(new Set());
  const timerRef = useRef<number | undefined>(undefined);
  const requestGenRef = useRef(0);
  const [translationMap, setTranslationMap] = useState<Record<string, string>>({});
  const [translateBusy, setTranslateBusy] = useState(false);
  const [translateError, setTranslateError] = useState<string | null>(null);

  const labelKey = useMemo(() => [...new Set(labels.map((item) => item.trim()).filter(Boolean))].sort().join("\0"), [labels]);

  useEffect(() => {
    window.clearTimeout(timerRef.current);

    if (!enabled) {
      setTranslationMap({});
      setTranslateBusy(false);
      setTranslateError(null);
      return;
    }

    const pending = labelKey
      ? labelKey.split("\0").filter((label) => !isLikelyChinese(label) && !cacheRef.current[label] && !inFlightRef.current.has(label))
      : [];

    setTranslationMap({ ...cacheRef.current });

    if (pending.length === 0) {
      setTranslateBusy(inFlightRef.current.size > 0);
      return;
    }

    const requestGen = ++requestGenRef.current;
    timerRef.current = window.setTimeout(() => {
      void (async () => {
        for (const label of pending) {
          inFlightRef.current.add(label);
        }
        setTranslateBusy(true);
        setTranslateError(null);
        try {
          const map = await translateUiLabels(pending);
          if (requestGen !== requestGenRef.current) {
            return;
          }
          Object.assign(cacheRef.current, map);
          setTranslationMap({ ...cacheRef.current });
        } catch (error) {
          if (requestGen !== requestGenRef.current) {
            return;
          }
          setTranslateError(error instanceof Error ? error.message : "ui_translate_failed");
        } finally {
          for (const label of pending) {
            inFlightRef.current.delete(label);
          }
          if (requestGen === requestGenRef.current) {
            setTranslateBusy(inFlightRef.current.size > 0);
          }
        }
      })();
    }, DEBOUNCE_MS);

    return () => {
      window.clearTimeout(timerRef.current);
    };
  }, [enabled, labelKey]);

  return {
    translationMap: enabled ? translationMap : undefined,
    translateBusy,
    translateError,
  };
}
