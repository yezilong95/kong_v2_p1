(() => {
  const MESSAGES = {
    en: {
      pageTitle: "Enter passcode",
      title: "Enter passcode",
      emergency: "Emergency call",
      keypadAria: "Number pad",
      backspaceAria: "Delete",
      confirm: "OK",
    },
    "zh-CN": {
      pageTitle: "输入密码",
      title: "输入密码",
      emergency: "紧急呼叫",
      keypadAria: "数字键盘",
      backspaceAria: "删除",
      confirm: "确认",
    },
    "zh-TW": {
      pageTitle: "輸入密碼",
      title: "輸入密碼",
      emergency: "緊急呼叫",
      keypadAria: "數字鍵盤",
      backspaceAria: "刪除",
      confirm: "確認",
    },
    ja: {
      pageTitle: "パスコードを入力",
      title: "パスコードを入力",
      emergency: "緊急通報",
      keypadAria: "テンキー",
      backspaceAria: "削除",
      confirm: "確認",
    },
    es: {
      pageTitle: "Introduzca el código",
      title: "Introduzca el código",
      emergency: "Llamada de emergencia",
      keypadAria: "Teclado numérico",
      backspaceAria: "Borrar",
      confirm: "OK",
    },
    ms: {
      pageTitle: "Masukkan kata laluan",
      title: "Masukkan kata laluan",
      emergency: "Panggilan kecemasan",
      keypadAria: "Pad nombor",
      backspaceAria: "Padam",
      confirm: "OK",
    },
  };

  function normalizeLocale(raw) {
    const tag = String(raw || "en").trim().replace(/_/g, "-");
    if (MESSAGES[tag]) {
      return tag;
    }
    const lower = tag.toLowerCase();
    if (MESSAGES[lower]) {
      return lower;
    }
    const base = lower.split("-")[0];
    if (base === "zh") {
      if (/(tw|hk|mo|hant)/i.test(tag)) {
        return "zh-TW";
      }
      return "zh-CN";
    }
    if (MESSAGES[base]) {
      return base;
    }
    return "en";
  }

  function resolveLocale() {
    const params = new URLSearchParams(window.location.search);
    const forced = params.get("lang");
    if (forced) {
      return normalizeLocale(forced);
    }
    const nav = navigator.language || navigator.userLanguage || "en";
    return normalizeLocale(nav);
  }

  const locale = resolveLocale();
  const strings = MESSAGES[locale] || MESSAGES.en;

  function t(key) {
    return strings[key] || MESSAGES.en[key] || key;
  }

  function applyToDocument() {
    document.documentElement.lang = locale;
    const titleEl = document.getElementById("titleText");
    const emergencyBtn = document.getElementById("emergencyBtn");
    const keypadEl = document.getElementById("keypad");
    document.title = t("pageTitle");
    if (titleEl) {
      titleEl.textContent = t("title");
    }
    if (emergencyBtn) {
      emergencyBtn.textContent = t("emergency");
    }
    if (keypadEl) {
      keypadEl.setAttribute("aria-label", t("keypadAria"));
    }
    return { locale, t };
  }

  window.OppoPinI18n = {
    locale,
    t,
    applyToDocument,
    backspaceAria: () => t("backspaceAria"),
    confirmLabel: () => t("confirm"),
  };
})();
