/**
 * embed.js - 流量统计 + 下载中转 + FB Pixel 自动加载（一行接入版）
 *
 * 在落地页 </body> 前加这一行即可：
 *   <script src="https://你的统计域名/embed.js" data-pid="1" data-file="1" data-download="all"></script>
 *
 * 参数：
 *   data-pid       必填，项目编号（后台「项目管理」创建后得到的 ID）
 *   data-file      选填，文件编号（后台「文件管理」创建后得到的 ID）
 *   data-download  选填：
 *                   - "all"                     → 页面上所有 a/button 点击都自动下载并记录
 *                   - CSS 选择器（如 ".js-dl" 或 "a[data-dl]"）→ 只接管匹配这些元素
 *   data-base      选填，统计服务域名，不填则自动取当前脚本所在域名
 *
 * FB Pixel：后台「流量设置」里配置 Pixel ID 并启用后，本脚本会自动加载
 * Meta Pixel，发送 PageView；用户点下载时发送 DownloadClick。
 */
(function () {
  var script = document.currentScript;
  var base = script ? (script.getAttribute('data-base') || script.src.replace(/\/embed\.js.*$/, '')) : '';
  var pid = script ? (parseInt(script.getAttribute('data-pid'), 10) || 1) : 1;
  var fileId = script ? (parseInt(script.getAttribute('data-file'), 10) || 0) : 0;
  var downloadMode = script ? (script.getAttribute('data-download') || '') : '';

  function getParam(name) {
    try { return new URLSearchParams(window.location.search).get(name) || ''; } catch (e) { return ''; }
  }

  var fbclid = getParam('fbclid');
  var utm    = getParam('utm_campaign');
  var adId   = getParam('ad_id');

  // 1) 访问统计埋点（1x1 像素上报，跨域无碍）
  var trackParams = 'pid=' + pid;
  if (fbclid) trackParams += '&fbclid=' + encodeURIComponent(fbclid);
  if (utm)    trackParams += '&utm_campaign=' + encodeURIComponent(utm);
  if (adId)   trackParams += '&ad_id=' + encodeURIComponent(adId);

  var px = new Image();
  px.src = base + '/track.php?' + trackParams;

  // 2) 下载中转：动态创建 <a> 触发下载，隐藏真实下载链接
  window.trackDownload = function (id) {
    var href = base + '/getfile.php?fileid=' + encodeURIComponent(id) + '&pid=' + pid;
    if (fbclid) href += '&fbclid=' + encodeURIComponent(fbclid);

    var a = document.createElement('a');
    a.href = href;
    a.style.display = 'none';
    a.target = '_self';
    a.setAttribute('data-track-download', '1');
    // 关键：先 append 再 click，且延迟移除节点，避免安卓/WebView 因节点被立即销毁而中断下载（表现为“无法加载对象”）
    document.body.appendChild(a);
    a.click();
    setTimeout(function () {
      if (a.parentNode) {
        a.parentNode.removeChild(a);
      }
    }, 1000);

    // FB Pixel 下载事件
    if (typeof window.fbq === 'function') {
      try { window.fbq('track', 'DownloadClick'); } catch (e) {}
    }
  };

  // 3) 自动劫持：
  //    - downloadMode = "all"                 → 接管所有 a/button
  //    - downloadMode = CSS 选择器 ".js-dl"    → 只接管命中该选择器的元素
  if (downloadMode && fileId) {
    var selector = downloadMode === 'all' ? 'a, button' : downloadMode;
    document.addEventListener('click', function (e) {
      var el = e.target && e.target.closest ? e.target.closest(selector) : null;
      if (el && !el.getAttribute('data-track-download')) {
        e.preventDefault();
        window.trackDownload(fileId);
      }
    }, false);
  }

  // 4) FB Pixel 自动加载（后台配置并启用后生效）
  function loadFbPixel(pixelId) {
    if (window.fbq) {
      try {
        window.fbq('init', pixelId);
        window.fbq('track', 'PageView');
      } catch (e) {}
      return;
    }
    (function (f, b, e, v, n, t, s) {
      if (f.fbq) return;
      n = f.fbq = function () { n.callMethod ? n.callMethod.apply(n, arguments) : n.queue.push(arguments); };
      if (!f._fbq) f._fbq = n;
      n.push = n; n.loaded = true; n.version = '2.0'; n.queue = [];
      t = b.createElement(e); t.async = true; t.src = v;
      s = b.getElementsByTagName(e)[0];
      s.parentNode.insertBefore(t, s);
    })(window, document, 'script', 'https://connect.facebook.net/en_US/fbevents.js');
    window.fbq('init', pixelId);
    window.fbq('track', 'PageView');
  }

  function fetchPixel() {
    try {
      var xhr = new XMLHttpRequest();
      xhr.open('GET', base + '/pixel.php', true);
      xhr.onload = function () {
        try {
          var cfg = JSON.parse(xhr.responseText);
          if (cfg && cfg.enabled && cfg.pixel_id) {
            loadFbPixel(cfg.pixel_id);
          }
        } catch (e) {}
      };
      xhr.onerror = function () {};
      xhr.send();
    } catch (e) {}
  }

  fetchPixel();
})();