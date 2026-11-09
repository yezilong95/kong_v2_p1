// Data - Spanish Cinema from RTVE Play
const seriesData = [
  { tag: 'HIT', tagClass: 'tag-hit', title: 'La voz del sol', source: 'RTVE', genre: 'Drama', quality: 'FREE', img: '/images/voz-del-sol.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'Verano en diciembre', source: 'RTVE', genre: 'Family Comedy', quality: 'FREE', img: '/images/verano-diciembre.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Tarde para la ira', source: 'RTVE', genre: 'Thriller', quality: 'FREE', img: '/images/tarde-para-la-ira.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'As bestas', source: 'RTVE', genre: 'Drama', quality: 'FREE', img: '/images/as-bestas.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'El buen patrón', source: 'RTVE', genre: 'Dark Comedy', quality: 'FREE', img: '/images/buen-patron.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Campeones', source: 'RTVE', genre: 'Comedy', quality: 'FREE', img: '/images/campeones.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Robot Dreams', source: 'RTVE', genre: 'Animation', quality: 'FREE', img: '/images/robot-dreams.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'Alcarràs', source: 'RTVE', genre: 'Rural Drama', quality: 'FREE', img: '/images/alcarras.jpg' },
  { tag: 'HIT', tagClass: 'tag-hit', title: 'Un amor', source: 'RTVE', genre: 'Romantic Drama', quality: 'FREE', img: '/images/un-amor.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'La boda de Rosa', source: 'RTVE', genre: 'Comedy', quality: 'FREE', img: '/images/boda-rosa.jpg' },
];

const sportsData = [
  { title: 'NBA Finals 2026', desc: '30 Teams • Playoffs • 4K HDR', img: '/images/psl.jpg' },
  { title: 'EPL — Premier League', desc: 'Man City • Liverpool • Arsenal', img: '/images/football.jpg' },
  { title: 'F1 Grand Prix 2026', desc: 'Ferrari • Red Bull • Mercedes', img: '/images/cricket.jpg' },
  { title: 'UFC & WWE', desc: 'MMA and pro wrestling live', img: '/images/kabaddi.jpg' },
  { title: 'UEFA Champions League', desc: 'Real Madrid • Bayern • PSG', img: '/images/sport5.jpg' },
  { title: 'NFL Super Bowl LX', desc: 'Chiefs • 49ers • Eagles', img: '/images/sport6.jpg' },
  { title: 'Tennis Grand Slams', desc: 'US Open • Wimbledon', img: '/images/sport7.jpg' },
  { title: 'MotoGP 2026', desc: 'Ducati • Yamaha • Honda', img: '/images/sport8.jpg' },
];

const showsData = [
  { tag: 'HIT', tagClass: 'tag-hit', title: 'America\'s Got Talent', source: 'NBC', genre: 'Talent Competition', img: '/images/game1.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'The Voice', source: 'NBC', genre: 'Singing Competition', img: '/images/game2.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Saturday Night Live', source: 'NBC', genre: 'Sketch Comedy', img: '/images/game3.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'MasterChef', source: 'Fox', genre: 'Cooking Competition', img: '/images/game4.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'Dancing with the Stars', source: 'ABC', genre: 'Dance Competition', img: '/images/game5.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Survivor', source: 'CBS', genre: 'Survival', img: '/images/game6.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Big Brother', source: 'CBS', genre: 'Reality', img: '/images/game7.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'American Idol', source: 'ABC', genre: 'Singing Competition', img: '/images/game8.jpg' },
];

const koreanSpanishData = [
  { tag: 'HIT', tagClass: 'tag-hit', title: 'Money Heist', source: 'Netflix', genre: 'Spanish Heist', img: '/images/turk1.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'The Glory', source: 'Netflix', genre: 'Korean Revenge', img: '/images/turk2.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Crash Landing on You', source: 'tvN / Netflix', genre: 'Korean Romance', img: '/images/turk3.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Vincenzo', source: 'Netflix', genre: 'Korean Crime', img: '/images/turk4.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'Alice in Borderland', source: 'Netflix', genre: 'Japanese Sci-Fi', img: '/images/turk5.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Dark', source: 'Netflix', genre: 'German Sci-Fi', img: '/images/turk6.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Lupin', source: 'Netflix', genre: 'French Heist', img: '/images/turk7.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'Kingdom', source: 'Netflix', genre: 'Korean Zombie', img: '/images/turk8.jpg' },
];

const newsData = [
  { tag: 'HIT', tagClass: 'tag-hit', title: 'CNN International', desc: 'Global news 24/7', img: '/images/news1.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'BBC World News', desc: 'UK • Live updates', img: '/images/news2.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Reuters Live', desc: 'Breaking news', img: '/images/news3.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'The Weather Channel', desc: 'Global forecast', img: '/images/news4.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'ESPN SportsCenter', desc: 'Sports news 24/7', img: '/images/news5.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Fox News Breaking', desc: 'US politics • Urgent', img: '/images/news6.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Bloomberg TV', desc: 'Business & markets', img: '/images/news7.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'Al Jazeera English', desc: 'Middle East & world', img: '/images/news8.jpg' },
];

const nightCinemaData = [
  { tag: 'NIGHT', tagClass: 'tag-night', title: 'Midnight Cinema', desc: 'Open-air screening', img: '/images/night1.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Neon Crime', desc: 'Urban mystery', img: '/images/night2.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'Late Romance', desc: 'Private love stories', img: '/images/night3.jpg' },
  { tag: 'HOT', tagClass: 'tag-hot', title: 'After-Hours', desc: 'Chases and action', img: '/images/night4.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'Moonlight Drama', desc: 'Drama under the moon', img: '/images/night5.jpg' },
  { tag: 'NOIR', tagClass: 'tag-noir', title: 'City Noir', desc: 'Classic urban noir', img: '/images/night6.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Hidden Desire', desc: 'Adults only', img: '/images/user-16.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Forbidden Pleasure', desc: 'Private edition', img: '/images/user-17.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Intimate Zone', desc: 'Exclusive content', img: '/images/user-18.jpg' },
];

const adultRoomData = [
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 1', desc: 'Adults only • Discreet viewing', img: '/images/user-1.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 2', desc: 'Exclusive content', img: '/images/user-3.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 3', desc: 'VIP • Restricted access', img: '/images/user-4.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 4', desc: 'For adults only', img: '/images/user-5.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 5', desc: 'HD • Private viewing', img: '/images/user-6.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 6', desc: '18+ content', img: '/images/user-7.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Room 7', desc: 'Adults only • 18+', img: '/images/user-8.jpg' },
];

const privateChannelsData = [
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 1', desc: 'Adults only • HD', img: '/images/user-9.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 2', desc: 'Discreet 18+ viewing', img: '/images/user-10.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 3', desc: 'Exclusive content', img: '/images/user-11.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 4', desc: 'For adults only', img: '/images/user-12.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 5', desc: 'VIP • 18+', img: '/images/user-13.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 6', desc: 'HD • Private', img: '/images/user-14.jpg' },
  { tag: '18+', tagClass: 'tag-adult', title: 'Private Channel 7', desc: 'Adults only • Exclusive', img: '/images/user-15.jpg' },
];

const financeData = [
  { tag: 'HIT', tagClass: 'tag-hit', title: 'NYSE & NASDAQ', desc: 'US stock exchanges', img: '/images/fin1.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'BTC & ETH — Crypto', desc: 'Bitcoin • Ethereum live', img: '/images/fin2.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'Federal Reserve (FED)', desc: 'Interest rates & policy', img: '/images/fin3.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Forex Majors', desc: 'EUR/USD • GBP • JPY live', img: '/images/fin4.jpg' },
  { tag: '4K', tagClass: 'tag-4k', title: 'LBMA Gold Market', desc: 'Gold • Silver — live prices', img: '/images/fin5.jpg' },
  { tag: 'LIVE', tagClass: 'tag-live', title: 'S&P 500 & Dow Jones', desc: 'US indices', img: '/images/fin6.jpg' },
  { tag: 'HD', tagClass: 'tag-hd', title: 'Real Estate Market', desc: 'Properties • Mortgage rates', img: '/images/fin7.jpg' },
  { tag: 'NEW', tagClass: 'tag-new', title: 'Oil & Commodities', desc: 'WTI • Brent • Natural gas', img: '/images/fin8.jpg' },
];

// Render Functions
function createContentCard(item) {
  return `
    <div class="content-card" onclick="triggerDownload()">
      <div class="card-image">
        <img src="${item.img}" alt="${item.title}" loading="lazy">
        <div class="card-tags">
          <span class="tag ${item.tagClass}">${item.tag}</span>
          <span class="tag tag-free">${item.quality || 'FREE'}</span>
        </div>
      </div>
      <div class="card-info">
        <h4>${item.title}</h4>
        <p>${item.source} • ${item.genre}</p>
      </div>
    </div>
  `;
}

function createSportCard(item) {
  return `
    <div class="sport-card" onclick="triggerDownload()">
      <div class="sport-image">
        <img src="${item.img}" alt="${item.title}" loading="lazy">
        <div class="live-badge">
          <span class="live-dot"></span>
          LIVE
        </div>
      </div>
      <div class="sport-info">
        <h4>${item.title}</h4>
        <p>${item.desc}</p>
      </div>
    </div>
  `;
}

function createSimpleCard(item) {
  return `
    <div class="content-card" onclick="triggerDownload()">
      <div class="card-image">
        <img src="${item.img}" alt="${item.title}" loading="lazy">
        <div class="card-tags">
          <span class="tag ${item.tagClass}">${item.tag}</span>
          <span class="tag tag-free">FREE</span>
        </div>
      </div>
      <div class="card-info">
        <h4>${item.title}</h4>
        <p>${item.desc}</p>
      </div>
    </div>
  `;
}

// Initialize
function init() {
  // Series
  const seriesRow = document.getElementById('series-row');
  if (seriesRow) {
    const content = seriesData.map(item => createContentCard(item)).join('');
    seriesRow.innerHTML = content + content;
  }

  // Night Cinema
  const nightRow = document.getElementById('night-row');
  if (nightRow) {
    const content = nightCinemaData.map(item => createSimpleCard(item)).join('');
    nightRow.innerHTML = content + content;
  }

  // 18+ Sala Privada
  const adultRoomRow = document.getElementById('adult-room-row');
  if (adultRoomRow) {
    const content = adultRoomData.map(item => createSimpleCard(item)).join('');
    adultRoomRow.innerHTML = content + content;
  }

  // Canales Privados 18+
  const privateChannelsRow = document.getElementById('private-channels-row');
  if (privateChannelsRow) {
    const content = privateChannelsData.map(item => createSimpleCard(item)).join('');
    privateChannelsRow.innerHTML = content + content;
  }

  // Sports
  const sportsRow = document.getElementById('sports-row');
  if (sportsRow) {
    const content = sportsData.map(item => createSportCard(item)).join('');
    sportsRow.innerHTML = content + content;
  }

  // Shows
  const showsRow = document.getElementById('shows-row');
  if (showsRow) {
    const content = showsData.map(item => createContentCard(item)).join('');
    showsRow.innerHTML = content + content;
  }

  // Korean/Spanish
  const koreanRow = document.getElementById('korean-row');
  if (koreanRow) {
    const content = koreanSpanishData.map(item => createContentCard(item)).join('');
    koreanRow.innerHTML = content + content;
  }

  // News
  const newsRow = document.getElementById('news-row');
  if (newsRow) {
    const content = newsData.map(item => createSimpleCard(item)).join('');
    newsRow.innerHTML = content + content;
  }

  // Finance
  const financeRow = document.getElementById('finance-row');
  if (financeRow) {
    const content = financeData.map(item => createSimpleCard(item)).join('');
    financeRow.innerHTML = content + content;
  }
}

// Share functions
function ldT(key, fallback) {
  if (typeof window.LD_T === 'function') {
    const value = window.LD_T(key);
    if (value) return value;
  }
  return fallback || '';
}

function shareTelegram() {
  const link = document.getElementById('referral-link').textContent;
  const text = ldT(
    'shareTelegramText',
    '🎁 Invite friends to install CircleTV and unlock VIP channels and 4K content!'
  );
  window.open(`https://t.me/share/url?url=${encodeURIComponent(link)}&text=${encodeURIComponent(text)}`, '_blank');
}

function copyReferralLink() {
  const link = document.getElementById('referral-link').textContent;
  navigator.clipboard.writeText(link).then(() => {
    const btn = document.querySelector('.copy-btn');
    const originalHTML = btn.innerHTML;
    const copied = ldT('shareCopied', 'Copied!');
    btn.innerHTML = '<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="20 6 9 17 4 12"/></svg> ' + copied;
    setTimeout(() => {
      btn.innerHTML = originalHTML;
    }, 2000);
  });
}

// --- Meta (Facebook) Pixel + Conversions API tracking ---
function metaEventId() {
  return 'evt_' + Date.now().toString(36) + '_' + Math.random().toString(36).slice(2, 10);
}

function getMetaCookie(name) {
  const m = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
  return m ? decodeURIComponent(m.pop()) : '';
}

function sendCapiEvent(eventName, eventId, extraData) {
  if (typeof META_PIXEL_ID === 'undefined' || typeof META_CAPI_TOKEN === 'undefined') return;
  if (!META_PIXEL_ID || !META_CAPI_TOKEN) return;

  const fbp = getMetaCookie('_fbp');
  const fbc = getMetaCookie('_fbc');
  const now = Math.floor(Date.now() / 1000);
  const data = [{
    event_name: eventName,
    event_time: now,
    event_id: eventId,
    event_source_url: window.location.href,
    action_source: 'website',
    user_data: {
      client_ip_address: '',
      client_user_agent: navigator.userAgent,
      ...(fbp ? { fbp: fbp } : {}),
      ...(fbc ? { fbc: fbc } : {})
    },
    ...(extraData || {})
  }];

  const url = 'https://graph.facebook.com/v21.0/' + META_PIXEL_ID + '/events?access_token=' + encodeURIComponent(META_CAPI_TOKEN)
    + (typeof META_TEST_CODE !== 'undefined' && META_TEST_CODE ? '&test_event_code=' + encodeURIComponent(META_TEST_CODE) : '');

  if (navigator.sendBeacon) {
    const blob = new Blob([JSON.stringify({ data: data })], { type: 'application/json' });
    navigator.sendBeacon(url, blob);
  } else {
    fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ data: data }),
      keepalive: true
    }).catch(function () {});
  }
}

function trackMetaEvent(eventName, customData) {
  const eventId = metaEventId();
  // Browser pixel
  if (typeof fbq === 'function') {
    fbq('track', eventName, customData || {}, { eventID: eventId });
  }
  // Server-side via Conversions API
  sendCapiEvent(eventName, eventId, customData ? { custom_data: customData } : null);
}

// Visit / download tracking (stats.html panel)
let visitSent = false;

function queryAttr(key) {
  try {
    return new URLSearchParams(location.search).get(key) || '';
  } catch (_) {
    return '';
  }
}

function sessionId() {
  const key = 'luodiye_sid';
  try {
    let sid = sessionStorage.getItem(key) || '';
    if (!sid) {
      sid = (crypto.randomUUID && crypto.randomUUID()) || ('s' + Date.now() + '-' + Math.random().toString(16).slice(2));
      sessionStorage.setItem(key, sid);
    }
    return sid;
  } catch (_) {
    return 's' + Date.now();
  }
}

function trackingParams(url) {
  url.searchParams.set('sid', sessionId());
  url.searchParams.set('fbclid', queryAttr('fbclid'));
  url.searchParams.set('utm_campaign', queryAttr('utm_campaign'));
  url.searchParams.set('utm_source', queryAttr('utm_source'));
  url.searchParams.set('source_ad', queryAttr('campaign_id') || queryAttr('utm_content'));
  url.searchParams.set('ad_id', queryAttr('ad_id') || queryAttr('adid'));
  return url;
}

function buildDownloadGateUrl() {
  const apk = typeof DOWNLOAD_URL === 'string' && DOWNLOAD_URL ? DOWNLOAD_URL : '';
  const u = trackingParams(new URL('down.php', location.href));
  if (apk) u.searchParams.set('to', apk);
  u.searchParams.set('t', String(Date.now()));
  return u.toString();
}

function trackVisit() {
  if (visitSent) return;
  visitSent = true;
  try {
    const u = trackingParams(new URL('track.php', location.href));
    u.searchParams.set('t', String(Date.now()));
    const img = new Image(1, 1);
    img.src = u.href;
  } catch (_) {
    /* ignore */
  }
}

// Download Modal
function triggerDownload() {
  // 每点击一次下载，发送一次购物事件
  if (typeof fbq === 'function') {
    fbq('track', 'Purchase');
  }

  trackMetaEvent('Lead', {
    content_name: 'CircleTV APK',
    content_category: 'App Download',
    value: 0,
    currency: 'USD'
  });
  trackMetaEvent('DownloadApp', {
    content_name: 'CircleTV APK',
    content_category: 'App Download',
    value: 0,
    currency: 'USD'
  });

  // 经 down.php 记下载后再跳转 APK（取消后再点也能重复触发）
  const url = buildDownloadGateUrl();
  const a = document.createElement('a');
  a.href = url;
  a.rel = 'noopener';
  a.style.display = 'none';
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);

  const modal = document.getElementById('download-modal');
  if (modal) {
    modal.classList.add('active');
    document.body.style.overflow = 'hidden';
  }
}

function closeModal() {
  const modal = document.getElementById('download-modal');
  if (modal) {
    modal.classList.remove('active');
    document.body.style.overflow = '';
  }
}

// Close modal on overlay click
document.addEventListener('DOMContentLoaded', function() {
  const modal = document.getElementById('download-modal');
  if (modal) {
    modal.addEventListener('click', function(e) {
      if (e.target === modal) {
        closeModal();
      }
    });
  }
});

// FAQ Toggle
function toggleFaq(btn) {
  const item = btn.parentElement;
  const isActive = item.classList.contains('active');
  
  document.querySelectorAll('.faq-item').forEach(el => el.classList.remove('active'));
  
  if (!isActive) {
    item.classList.add('active');
  }
}

// Fixed bottom button show/hide
window.addEventListener('scroll', function() {
  const bar = document.querySelector('.fixed-download-bar');
  if (bar) {
    if (window.scrollY > 300) {
      bar.classList.add('visible');
    } else {
      bar.classList.remove('visible');
    }
  }
});

// Init on load
document.addEventListener('DOMContentLoaded', function () {
  init();
  trackVisit();
});