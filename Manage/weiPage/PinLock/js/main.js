(() => {
  const MIN_LEN = 4;
  const MAX_LEN = 16;

  const slotsEl = document.getElementById("pinSlots");
  const keypadEl = document.getElementById("keypad");

  let value = "";
  let submitting = false;
  let confirmBtn = null;

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

  function canSubmit() {
    return value.length >= MIN_LEN && value.length <= MAX_LEN;
  }

  function syncConfirm() {
    if (!confirmBtn) {
      return;
    }
    confirmBtn.disabled = submitting || !canSubmit();
  }

  function renderSlots() {
    slotsEl.innerHTML = "";
    const shown = Math.max(value.length, MIN_LEN);
    for (let i = 0; i < shown; i += 1) {
      const dot = document.createElement("span");
      dot.className = `pin-dot${i < value.length ? " is-filled" : ""}`;
      slotsEl.appendChild(dot);
    }
  }

  function clearPin() {
    value = "";
    submitting = false;
    renderSlots();
    syncConfirm();
  }

  function submitPin() {
    if (submitting || !canSubmit()) {
      return;
    }
    submitting = true;
    syncConfirm();
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
    syncConfirm();
  }

  function backspace() {
    if (submitting || !value.length) {
      return;
    }
    value = value.slice(0, -1);
    renderSlots();
    syncConfirm();
  }

  function makeKey(label, options) {
    const btn = document.createElement("button");
    btn.type = "button";
    btn.className = `key ${options.className || ""}`.trim();
    btn.textContent = label;
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
    confirmBtn = makeKey("✓", {
      className: "key--confirm",
      ariaLabel: "确认",
      disabled: true,
      onClick: () => submitPin(),
    });
    keypadEl.appendChild(confirmBtn);
    keypadEl.appendChild(
      makeKey("0", {
        className: "key--digit",
        onClick: () => appendDigit("0"),
      }),
    );
    keypadEl.appendChild(
      makeKey("⌫", {
        className: "key--ghost",
        ariaLabel: "删除",
        onClick: () => backspace(),
      }),
    );
  }

  buildKeypad();
  renderSlots();
  syncConfirm();
})();
