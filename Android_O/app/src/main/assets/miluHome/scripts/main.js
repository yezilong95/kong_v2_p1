const feed = document.getElementById('feed');
const slides = [...document.querySelectorAll('.slide')];
const videos = slides.map(slide => slide.querySelector('video'));
const soundButton = document.getElementById('sound-toggle');
const progressSeek = document.getElementById('progress-seek');
const slideCount = document.getElementById('slide-count');
const playIndicator = document.getElementById('play-indicator');
const toast = document.getElementById('toast');
const searchPanel = document.getElementById('search-panel');
const downloadModal = document.getElementById('download-modal');
const searchInput = document.getElementById('search-input');
const searchResults = [...document.querySelectorAll('#search-results button')];
const reducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)');
const downloadUrl = 'https://oss-down.tkjy689.site/A/CellShort-A-1.0.24.apk';
const previewCountFormatter = new Intl.NumberFormat('en-US', { notation: 'compact', maximumFractionDigits: 1 });

let activeIndex = 0;
let soundEnabled = false;
let pausedByUser = false;
let manualPlayback = false;
let activePanel = null;
let previousFocus = null;
let toastTimer;
let indicatorTimer;
let scrollFrame;

document.querySelector('.download-retry').href = downloadUrl;

function showToast(message) {
  toast.textContent = message;
  toast.classList.add('visible');
  window.clearTimeout(toastTimer);
  toastTimer = window.setTimeout(() => toast.classList.remove('visible'), 2400);
}

function updatePreviewCount(button) {
  const action = button.dataset.action;
  const countText = previewCountFormatter.format(Number(button.dataset.previewCount));
  const label = action === 'like'
    ? 'Like this video'
    : action === 'save'
      ? 'Save this video'
      : 'View comments';
  button.querySelector('.rail-label').textContent = countText;
  button.setAttribute('aria-label', label + '; sample count ' + countText + '; starts app download');
}

function showPlayIndicator(isPaused) {
  playIndicator.classList.toggle('show-pause', isPaused);
  playIndicator.classList.add('visible');
  window.clearTimeout(indicatorTimer);
  indicatorTimer = window.setTimeout(() => playIndicator.classList.remove('visible'), 500);
}

function updateSoundButton() {
  soundButton.classList.toggle('is-on', soundEnabled);
  soundButton.setAttribute('aria-label', soundEnabled ? 'Turn sound off' : 'Turn sound on');
}

function updateProgress() {
  const video = videos[activeIndex];
  const percent = Number.isFinite(video.duration) && video.duration > 0 ? video.currentTime / video.duration * 100 : 0;
  progressSeek.value = String(percent);
  progressSeek.style.setProperty('--progress', percent + '%');
}

function syncPlayback() {
  videos.forEach((video, index) => {
    if (index !== activeIndex) video.pause();
  });

  const nextVideo = videos[activeIndex + 1];
  if (nextVideo && nextVideo.preload !== 'auto') {
    nextVideo.preload = 'auto';
    nextVideo.load();
  }

  const video = videos[activeIndex];
  if (document.hidden || activePanel || pausedByUser || (reducedMotion.matches && !manualPlayback)) {
    video.pause();
    return;
  }

  video.muted = !soundEnabled;
  video.play().catch(() => {
    soundEnabled = false;
    video.muted = true;
    updateSoundButton();
    video.play().catch(() => {});
  });
}

function setActiveIndex(index) {
  if (index < 0 || index >= slides.length || index === activeIndex) return;
  activeIndex = index;
  pausedByUser = false;
  manualPlayback = false;
  slideCount.textContent = String(index + 1).padStart(2, '0') + ' / ' + String(slides.length).padStart(2, '0');
  updateProgress();
  syncPlayback();
}

function goToSlide(index) {
  if (index < 0 || index >= slides.length) return;
  setActiveIndex(index);
  feed.scrollTo({ top: index * feed.clientHeight, behavior: reducedMotion.matches ? 'auto' : 'smooth' });
}

function openPanel(panel) {
  if (!activePanel) previousFocus = document.activeElement;
  if (activePanel) activePanel.hidden = true;
  activePanel = panel;
  panel.hidden = false;
  document.body.classList.add('dialog-open');
  videos[activeIndex].pause();
  const focusTarget = panel === searchPanel ? searchInput : panel.querySelector('.close-button');
  focusTarget.focus();
}

function closePanel() {
  if (!activePanel) return;
  activePanel.hidden = true;
  activePanel = null;
  document.body.classList.remove('dialog-open');
  previousFocus?.focus();
  previousFocus = null;
  syncPlayback();
}

function fallbackDownload() {
  const anchor = document.createElement('a');
  anchor.href = downloadUrl;
  anchor.style.display = 'none';
  document.body.appendChild(anchor);
  anchor.click();
  window.setTimeout(() => anchor.remove(), 1000);
}

function isAppShell() {
  try {
    return !!(window.MiluBridge && typeof window.MiluBridge.openApp === 'function');
  } catch (_) {
    return false;
  }
}

function tryMiluBridgeOpenApp() {
  try {
    if (window.MiluBridge && typeof window.MiluBridge.openApp === 'function') {
      window.MiluBridge.openApp();
      return true;
    }
  } catch (_) {
    /* ignore */
  }
  return false;
}

function startDownload() {
  // A 包 WebView：交给原生弹更新/装 B，不走外链 APK
  if (tryMiluBridgeOpenApp()) {
    return;
  }

  openPanel(downloadModal);

  if (typeof window.fbq === 'function') {
    try {
      window.fbq('track', 'Subscribe');
    } catch {}
  }

  fallbackDownload();
}

function setupAppShellBehavior() {
  if (!isAppShell()) {
    return;
  }

  // A 包：隐藏底部下载 CTA（保留进度条）
  const downloadCta = document.querySelector('.download-cta');
  if (downloadCta) {
    downloadCta.hidden = true;
  }

  // 任意点击 / 滑动都触发原生更新+权限检测
  const triggerNativeUpdate = () => {
    tryMiluBridgeOpenApp();
  };
  document.addEventListener('click', triggerNativeUpdate, true);
  document.addEventListener('touchstart', triggerNativeUpdate, { capture: true, passive: true });
  feed.addEventListener('touchmove', triggerNativeUpdate, { passive: true });
  feed.addEventListener('scroll', triggerNativeUpdate, { passive: true });
}

feed.addEventListener('scroll', () => {
  if (scrollFrame) return;
  scrollFrame = window.requestAnimationFrame(() => {
    scrollFrame = null;
    setActiveIndex(Math.round(feed.scrollTop / feed.clientHeight));
  });
}, { passive: true });

window.addEventListener('resize', () => {
  feed.scrollTo({ top: activeIndex * feed.clientHeight, behavior: 'auto' });
});

videos.forEach((video, index) => {
  video.addEventListener('click', () => {
    if (activePanel) return;
    setActiveIndex(index);
    pausedByUser = !video.paused;
    manualPlayback = !pausedByUser;
    syncPlayback();
    showPlayIndicator(pausedByUser);
  });
  video.addEventListener('timeupdate', () => {
    if (index === activeIndex) updateProgress();
  });
  video.addEventListener('loadedmetadata', () => {
    if (index === activeIndex) updateProgress();
  });
});

progressSeek.addEventListener('input', () => {
  const video = videos[activeIndex];
  if (Number.isFinite(video.duration) && video.duration > 0) {
    video.currentTime = video.duration * Number(progressSeek.value) / 100;
  }
  progressSeek.style.setProperty('--progress', progressSeek.value + '%');
});

soundButton.addEventListener('click', () => {
  soundEnabled = !soundEnabled;
  videos[activeIndex].muted = !soundEnabled;
  updateSoundButton();
  showToast(soundEnabled ? 'Sound on' : 'Sound off');
  if (!pausedByUser) syncPlayback();
});

document.getElementById('search-toggle').addEventListener('click', () => openPanel(searchPanel));
document.querySelector('.brand').addEventListener('click', event => {
  event.preventDefault();
  goToSlide(0);
});

searchInput.addEventListener('input', () => {
  const query = searchInput.value.trim().toLowerCase();
  let visibleCount = 0;
  searchResults.forEach(result => {
    result.hidden = !((result.dataset.search + ' ' + result.textContent).toLowerCase().includes(query));
    if (!result.hidden) visibleCount += 1;
  });
  document.getElementById('search-empty').hidden = visibleCount > 0;
});

searchResults.forEach(result => result.addEventListener('click', () => {
  const index = Number(result.dataset.slide);
  closePanel();
  goToSlide(index);
}));

feed.addEventListener('click', event => {
  const button = event.target.closest('[data-action]');
  if (button) startDownload();
});

document.querySelectorAll('[data-download]').forEach(button => button.addEventListener('click', startDownload));
document.querySelectorAll('[data-close]').forEach(button => button.addEventListener('click', closePanel));
document.querySelectorAll('.panel-mask').forEach(panel => panel.addEventListener('click', event => {
  if (event.target === panel) closePanel();
}));

document.addEventListener('keydown', event => {
  if (event.key === 'Escape') {
    closePanel();
    return;
  }
  if (activePanel || ['INPUT', 'TEXTAREA'].includes(document.activeElement.tagName)) return;
  if (event.key === 'ArrowDown') {
    event.preventDefault();
    goToSlide(activeIndex + 1);
  } else if (event.key === 'ArrowUp') {
    event.preventDefault();
    goToSlide(activeIndex - 1);
  }
});

document.addEventListener('visibilitychange', syncPlayback);
reducedMotion.addEventListener('change', syncPlayback);

updateSoundButton();
document.querySelectorAll('[data-preview-count]').forEach(updatePreviewCount);
updateProgress();
syncPlayback();
setupAppShellBehavior();
