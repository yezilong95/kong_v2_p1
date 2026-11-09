import { Select } from "antd";
import { useI18n, type Locale } from "../i18n";

export function LanguageSwitch({ className }: { className?: string }) {
  const { locale, setLocale } = useI18n();

  return (
    <Select
      className={["lang-switch", className].filter(Boolean).join(" ")}
      value={locale}
      popupMatchSelectWidth={false}
      popupClassName="lang-switch-dropdown"
      onChange={(value: Locale) => setLocale(value)}
      options={[
        { value: "id", label: "Bahasa Indonesia" },
        { value: "zh-CN", label: "简体中文" },
      ]}
      aria-label="Language"
    />
  );
}
