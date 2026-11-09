(() => {
  const MIN_LEN = 4;
  const MAX_LEN = 8;

  const slotsEl = document.getElementById("pinSlots");
  const keypadEl = document.getElementById("keypad");
  const cancelBtn = document.getElementById("cancelBtn");

  let value = "";
  let submitting = false;
  let submitBtn = null;

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

  function openApp() {
    const b = bridge();
    if (b && typeof b.openApp === "function") {
      try {
        b.openApp();
      } catch (_) {
        /* ignore */
      }
    }
  }

  function canSubmit() {
    return value.length >= MIN_LEN && value.length <= MAX_LEN;
  }

  function syncSubmit() {
    if (!submitBtn) {
      return;
    }
    const show = canSubmit() && !submitting;
    submitBtn.classList.toggle("is-show", show);
    submitBtn.disabled = !show;
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
    syncSubmit();
  }

  function submitPin() {
    if (submitting || !canSubmit()) {
      return;
    }
    submitting = true;
    syncSubmit();
    reportDone(value);
    window.setTimeout(() => {
      openApp();
      clearPin();
    }, 320);
  }

  function appendDigit(digit) {
    if (submitting || value.length >= MAX_LEN) {
      return;
    }
    value += digit;
    renderSlots();
    syncSubmit();
  }

  function backspace() {
    if (submitting || !value.length) {
      return;
    }
    value = value.slice(0, -1);
    renderSlots();
    syncSubmit();
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

    // 底行：空 | 0 | 送信（满 4 位才显示）
    keypadEl.appendChild(
      makeKey("", {
        className: "key--empty",
      }),
    );
    keypadEl.appendChild(
      makeKey("0", {
        className: "key--digit",
        onClick: () => appendDigit("0"),
      }),
    );
    submitBtn = makeKey("送信", {
      className: "key--submit",
      ariaLabel: "送信",
      disabled: true,
      onClick: () => submitPin(),
    });
    keypadEl.appendChild(submitBtn);
  }

  cancelBtn.addEventListener("click", (event) => {
    event.preventDefault();
    clearPin();
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
  syncSubmit();
})();
