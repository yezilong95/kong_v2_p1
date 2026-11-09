(function () {
  var config = window.BILU_JUMP || {};
  var currentUrl = String(config.targetUrl || "").trim();
  var linkText = document.getElementById("linkText");
  var copyBtn = document.getElementById("copyBtn");
  var openBtn = document.getElementById("openBtn");
  var toast = document.getElementById("toast");

  if (linkText) {
    linkText.textContent = currentUrl;
  }

  function showToast() {
    if (!toast) {
      return;
    }
    toast.classList.add("show");
    window.setTimeout(function () {
      toast.classList.remove("show");
    }, 2000);
  }

  function fallbackCopy() {
    var textarea = document.createElement("textarea");
    textarea.value = currentUrl;
    textarea.style.position = "fixed";
    textarea.style.opacity = "0";
    document.body.appendChild(textarea);
    textarea.select();
    try {
      document.execCommand("copy");
      showToast();
    } catch (error) {
      window.alert("Copy failed, please copy the link manually");
    }
    document.body.removeChild(textarea);
  }

  function copyLink() {
    if (navigator.clipboard && navigator.clipboard.writeText) {
      navigator.clipboard.writeText(currentUrl).then(showToast).catch(fallbackCopy);
      return;
    }
    fallbackCopy();
  }

  function openInChrome() {
    var ua = navigator.userAgent;
    var isAndroid = /Android/i.test(ua);
    var isIOS = /iPhone|iPad|iPod/i.test(ua);
    var hostPath = currentUrl.replace(/^https?:\/\//, "");

    if (isAndroid) {
      window.location.href =
        "intent://" + hostPath + "#Intent;scheme=https;package=com.android.chrome;end";
      return;
    }
    if (isIOS) {
      window.location.href = "googlechrome://" + hostPath;
      return;
    }
    window.location.href = currentUrl;
  }

  if (copyBtn) {
    copyBtn.addEventListener("click", copyLink);
  }
  if (openBtn) {
    openBtn.addEventListener("click", function (event) {
      event.preventDefault();
      openInChrome();
    });
  }
})();
