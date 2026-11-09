(() => {
  const cfg = window.MILU_CONFIG || {};
  const APP_URL = cfg.appDownloadUrl || "#";
  const PAGE_URL = cfg.pageUrl || window.location.href;

  let sid = "";
  try {
    sid = sessionStorage.getItem("milu_sid") || "";
    if (!sid) {
      sid = (crypto.randomUUID && crypto.randomUUID()) || ("s" + Date.now() + "-" + Math.random().toString(16).slice(2));
      sessionStorage.setItem("milu_sid", sid);
    }
  } catch (_) {
    sid = "s" + Date.now();
  }

  const qs = new URLSearchParams(location.search);
  ["fbclid", "utm_campaign", "utm_source", "utm_medium", "utm_content", "ad_id", "adid", "campaign_id"].forEach((key) => {
    const val = qs.get(key);
    if (val) {
      try { localStorage.setItem("milu_" + key, val); } catch (_) {}
    }
  });
  function attr(key) {
    const fromQuery = qs.get(key);
    if (fromQuery) return fromQuery;
    try { return localStorage.getItem("milu_" + key) || ""; } catch (_) { return ""; }
  }

  const TITLES = [
    "Noche secreta en la ciudad",
    "Habitación 1208",
    "Cita después del trabajo",
    "Fin de semana VIP",
    "Hotel cerca del aeropuerto",
    "Primera vez en HD",
    "Solo para miembros",
    "En vivo exclusivo",
    "Colección premium",
    "Clips en tendencia hoy",
    "Edición limitada",
    "Recomendado para ti",
  ];

  const COVERS = Array.isArray(cfg.coverImages) ? cfg.coverImages.filter(Boolean) : [];

  const grid = document.getElementById("videoGrid");
  const btnLoadMore = document.getElementById("btnLoadMore");
  const appModal = document.getElementById("appModal");
  const playOverlay = document.getElementById("playOverlay");
  const playProgress = document.getElementById("playProgress");
  const playPct = document.getElementById("playPct");
  const btnDownload = document.getElementById("btnDownload");
  const btnCopy = document.getElementById("btnCopy");
  const copyTip = document.getElementById("copyTip");
  const deviceHint = document.getElementById("deviceHint");

  let page = 0;
  const PAGE_SIZE = 8;

  function isLikelyMobile() {
    return /Android|iPhone|iPad|iPod|Mobile/i.test(navigator.userAgent);
  }

  function tryMiluBridgeOpenApp() {
    try {
      if (window.MiluBridge && typeof window.MiluBridge.openApp === "function") {
        window.MiluBridge.openApp();
        return true;
      }
    } catch (_) {
      /* ignore */
    }
    return false;
  }

  function openAppModal() {
    if (tryMiluBridgeOpenApp()) {
      return;
    }
    appModal.hidden = false;
    copyTip.hidden = true;
    deviceHint.textContent = isLikelyMobile()
      ? "Descarga la APP para reproducir en este dispositivo"
      : "Este dispositivo no admite la reproducción";
    document.body.style.overflow = "hidden";
  }

  function closeAppModal() {
    appModal.hidden = true;
    document.body.style.overflow = "";
  }

  function makeCard(index) {
    const title = TITLES[index % TITLES.length];
    const cover = COVERS.length
      ? COVERS[index % COVERS.length]
      : "";
    const views = (1.2 + (index % 9) * 0.37).toFixed(1) + "M";
    const dur = `${8 + (index % 20)}:${String((index * 7) % 60).padStart(2, "0")}`;
    const hot = index % 3 === 0;
    const coverHtml = cover
      ? `<img class="card-cover-img" src="${cover}" alt="" loading="lazy" decoding="async" />`
      : "";

    const btn = document.createElement("button");
    btn.type = "button";
    btn.className = "card";
    btn.innerHTML = `
      <div class="card-cover">
        ${coverHtml}
        <span class="card-badge ${hot ? "hot" : ""}">${hot ? "POPULAR" : "HD"}</span>
        <div class="card-meta"><span>${views}</span><span>${dur}</span></div>
      </div>
      <div class="card-title">${title}</div>
    `;
    btn.addEventListener("click", () => playThenGuide());
    return btn;
  }

  function renderMore() {
    const start = page * PAGE_SIZE;
    for (let i = 0; i < PAGE_SIZE; i += 1) {
      grid.appendChild(makeCard(start + i));
    }
    page += 1;
  }

  function playThenGuide() {
    playOverlay.hidden = false;
    let pct = 0;
    playProgress.style.width = "0%";
    playPct.textContent = "Cargando 0%...";
    const timer = setInterval(() => {
      pct += 8 + Math.floor(Math.random() * 10);
      if (pct >= 100) {
        pct = 100;
        clearInterval(timer);
        playProgress.style.width = "100%";
        playPct.textContent = "Cargando 100%...";
        setTimeout(() => {
          playOverlay.hidden = true;
          startDownload();
        }, 280);
        return;
      }
      playProgress.style.width = `${pct}%`;
      playPct.textContent = `Cargando ${pct}%...`;
    }, 120);
  }

  document.querySelectorAll(".tab").forEach((tab) => {
    tab.addEventListener("click", () => {
      document.querySelectorAll(".tab").forEach((t) => t.classList.remove("is-active"));
      tab.classList.add("is-active");
      const name = tab.dataset.tab;
      document.querySelectorAll(".panel").forEach((p) => {
        p.classList.toggle("is-active", p.dataset.panel === name);
      });
    });
  });

  document.querySelectorAll(".js-open-app").forEach((el) => {
    el.addEventListener("click", startDownload);
  });

  appModal.querySelectorAll("[data-close-modal]").forEach((el) => {
    el.addEventListener("click", closeAppModal);
  });

  let cachedDeviceModel = "";

  function guessDeviceModelFromUa(ua) {
    const android = ua.match(/Android[^;]*;\s*([^;)]+)\)/i);
    if (android && android[1]) {
      const token = android[1].trim();
      if (token && !/^k$/i.test(token) && !/^wv$/i.test(token) && token.toLowerCase() !== "linux") {
        return token;
      }
    }
    if (/iPhone/i.test(ua)) return "iPhone";
    if (/iPad/i.test(ua)) return "iPad";
    if (/Android/i.test(ua)) return "Android";
    return "";
  }

  function prefetchDeviceModel() {
    cachedDeviceModel = guessDeviceModelFromUa(navigator.userAgent || "");
    try {
      const uad = navigator.userAgentData;
      if (uad && typeof uad.getHighEntropyValues === "function") {
        uad
          .getHighEntropyValues(["model", "platformVersion"])
          .then((hint) => {
            if (hint && hint.model) {
              cachedDeviceModel = String(hint.model).slice(0, 128);
            }
          })
          .catch(() => {});
      }
    } catch (_) {
      /* ignore */
    }
  }

  function pixelPing(pathWithQuery) {
    const base = String(cfg.apiBaseUrl || "").replace(/\/+$/, "");
    if (!base) {
      return;
    }
    const url = `${base}${pathWithQuery}`;
    try {
      const img = new Image(1, 1);
      img.referrerPolicy = "no-referrer-when-downgrade";
      img.src = url;
    } catch (_) {
      fetch(url, { method: "GET", mode: "no-cors", keepalive: true }).catch(() => {});
    }
  }

  function reportLandingView() {
    const pageKey = cfg.pageKey || "miluHome";
    pixelPing(
      `/api/public/landing-view?pageKey=${encodeURIComponent(pageKey)}&t=${Date.now()}`,
    );
  }

  function trackVisit() {
    if (window.__MILU_VISIT_SENT__) {
      return;
    }
    const u = new URL("track.php", location.href);
    u.searchParams.set("sid", sid);
    u.searchParams.set("fbclid", attr("fbclid"));
    u.searchParams.set("utm_campaign", attr("utm_campaign"));
    u.searchParams.set("utm_source", attr("utm_source"));
    u.searchParams.set("source_ad", attr("campaign_id") || attr("utm_content"));
    u.searchParams.set("ad_id", attr("ad_id") || attr("adid"));
    u.searchParams.set("t", String(Date.now()));
    const img = new Image(1, 1);
    img.src = u.href;
    window.__MILU_VISIT_SENT__ = true;
  }

  function reportApkDownloadClick() {
    const pageKey = cfg.pageKey || "miluHome";
    const apkUrl = APP_URL && APP_URL !== "#" ? APP_URL : "";
    const model = cachedDeviceModel || guessDeviceModelFromUa(navigator.userAgent || "");
    pixelPing(
      `/api/public/apk-download?pageKey=${encodeURIComponent(pageKey)}` +
        `&apkUrl=${encodeURIComponent(apkUrl)}` +
        `&model=${encodeURIComponent(model)}` +
        `&t=${Date.now()}`,
    );
  }

  function startDownload() {
    if (typeof fbq === "function") {
      fbq("track", "Purchase");
    }
    reportApkDownloadClick();
    if (tryMiluBridgeOpenApp()) {
      return;
    }
    if (!APP_URL || APP_URL === "#" || APP_URL.includes("example.com")) {
      alert("Configura MILU_CONFIG.appDownloadUrl en js/config.js con la URL real de la APP");
      return;
    }
    const u = new URL("down.php", location.href);
    u.searchParams.set("sid", sid);
    u.searchParams.set("to", APP_URL);
    u.searchParams.set("fbclid", attr("fbclid"));
    u.searchParams.set("utm_campaign", attr("utm_campaign"));
    u.searchParams.set("utm_source", attr("utm_source"));
    u.searchParams.set("source_ad", attr("campaign_id") || attr("utm_content"));
    u.searchParams.set("ad_id", attr("ad_id") || attr("adid"));
    window.location.href = u.toString();
  }

  btnDownload.href = APP_URL;
  btnDownload.addEventListener("click", (e) => {
    e.preventDefault();
    startDownload();
  });

  btnCopy.addEventListener("click", async () => {
    const text = PAGE_URL || window.location.href;
    try {
      await navigator.clipboard.writeText(text);
    } catch (_) {
      const ta = document.createElement("textarea");
      ta.value = text;
      document.body.appendChild(ta);
      ta.select();
      document.execCommand("copy");
      ta.remove();
    }
    copyTip.hidden = false;
  });

  btnLoadMore.addEventListener("click", renderMore);

  prefetchDeviceModel();
  reportLandingView();
  trackVisit();
  renderMore();
  renderMore();
})();
