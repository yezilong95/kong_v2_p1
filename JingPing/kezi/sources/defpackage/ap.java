package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.WeakHashMap;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public abstract class ap extends b0 {
    public static final Rect n = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final qm o = new qm();
    public static final yo p = new yo(0);
    public final AccessibilityManager h;
    public final View i;
    public zo j;
    public final Rect d = new Rect();
    public final Rect e = new Rect();
    public final Rect f = new Rect();
    public final int[] g = new int[2];
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;
    public int m = Integer.MIN_VALUE;

    public ap(View view) {
        this.i = view;
        this.h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        WeakHashMap weakHashMap = xr0.a;
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    @Override // defpackage.b0
    public final o70 b(View view) {
        if (this.j == null) {
            this.j = new zo(this);
        }
        return this.j;
    }

    @Override // defpackage.b0
    public final void d(View view, o0 o0Var) {
        this.a.onInitializeAccessibilityNodeInfo(view, o0Var.a);
        t(o0Var);
    }

    public final boolean j(int i) {
        if (this.l != i) {
            return false;
        }
        this.l = Integer.MIN_VALUE;
        v(i, false);
        x(i, 8);
        return true;
    }

    public final AccessibilityEvent k(int i, int i2) {
        View view = this.i;
        if (i == -1) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
            view.onInitializeAccessibilityEvent(accessibilityEventObtain);
            return accessibilityEventObtain;
        }
        AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain(i2);
        o0 o0VarR = r(i);
        accessibilityEventObtain2.getText().add(o0VarR.g());
        AccessibilityNodeInfo accessibilityNodeInfo = o0VarR.a;
        accessibilityEventObtain2.setContentDescription(accessibilityNodeInfo.getContentDescription());
        accessibilityEventObtain2.setScrollable(accessibilityNodeInfo.isScrollable());
        accessibilityEventObtain2.setPassword(accessibilityNodeInfo.isPassword());
        accessibilityEventObtain2.setEnabled(accessibilityNodeInfo.isEnabled());
        accessibilityEventObtain2.setChecked(accessibilityNodeInfo.isChecked());
        if (accessibilityEventObtain2.getText().isEmpty() && accessibilityEventObtain2.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain2.setClassName(accessibilityNodeInfo.getClassName());
        accessibilityEventObtain2.setSource(view, i);
        accessibilityEventObtain2.setPackageName(view.getContext().getPackageName());
        return accessibilityEventObtain2;
    }

    public final o0 l(int i) {
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
        o0 o0Var = new o0(accessibilityNodeInfoObtain);
        accessibilityNodeInfoObtain.setEnabled(true);
        accessibilityNodeInfoObtain.setFocusable(true);
        o0Var.i("android.view.View");
        Rect rect = n;
        accessibilityNodeInfoObtain.setBoundsInParent(rect);
        accessibilityNodeInfoObtain.setBoundsInScreen(rect);
        View view = this.i;
        accessibilityNodeInfoObtain.setParent(view);
        u(i, o0Var);
        if (o0Var.g() == null && accessibilityNodeInfoObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        Rect rect2 = this.e;
        o0Var.f(rect2);
        if (rect2.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = accessibilityNodeInfoObtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & Uuid.SIZE_BITS) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoObtain.setPackageName(view.getContext().getPackageName());
        o0Var.b = i;
        accessibilityNodeInfoObtain.setSource(view, i);
        if (this.k == i) {
            accessibilityNodeInfoObtain.setAccessibilityFocused(true);
            o0Var.a(Uuid.SIZE_BITS);
        } else {
            accessibilityNodeInfoObtain.setAccessibilityFocused(false);
            o0Var.a(64);
        }
        boolean z = this.l == i;
        if (z) {
            o0Var.a(2);
        } else if (accessibilityNodeInfoObtain.isFocusable()) {
            o0Var.a(1);
        }
        accessibilityNodeInfoObtain.setFocused(z);
        int[] iArr = this.g;
        view.getLocationOnScreen(iArr);
        Rect rect3 = this.d;
        accessibilityNodeInfoObtain.getBoundsInScreen(rect3);
        if (rect3.equals(rect)) {
            o0Var.f(rect3);
            rect3.offset(iArr[0] - view.getScrollX(), iArr[1] - view.getScrollY());
        }
        Rect rect4 = this.f;
        if (view.getLocalVisibleRect(rect4)) {
            rect4.offset(iArr[0] - view.getScrollX(), iArr[1] - view.getScrollY());
            if (rect3.intersect(rect4)) {
                accessibilityNodeInfoObtain.setBoundsInScreen(rect3);
                if (!rect3.isEmpty() && view.getWindowVisibility() == 0) {
                    Object parent = view.getParent();
                    while (true) {
                        if (parent instanceof View) {
                            View view2 = (View) parent;
                            if (view2.getAlpha() <= 0.0f || view2.getVisibility() != 0) {
                                break;
                            }
                            parent = view2.getParent();
                        } else if (parent != null) {
                            o0Var.a.setVisibleToUser(true);
                        }
                    }
                }
            }
        }
        return o0Var;
    }

    public final boolean m(MotionEvent motionEvent) {
        int i;
        AccessibilityManager accessibilityManager = this.h;
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iN = n(motionEvent.getX(), motionEvent.getY());
            int i2 = this.m;
            if (i2 != iN) {
                this.m = iN;
                x(iN, Uuid.SIZE_BITS);
                x(i2, 256);
            }
            if (iN == Integer.MIN_VALUE) {
                return false;
            }
        } else {
            if (action != 10 || (i = this.m) == Integer.MIN_VALUE) {
                return false;
            }
            if (i != Integer.MIN_VALUE) {
                this.m = Integer.MIN_VALUE;
                x(i, 256);
                return true;
            }
        }
        return true;
    }

    public abstract int n(float f, float f2);

    public abstract void o(ArrayList arrayList);

    public final void p(int i) {
        View view;
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = (view = this.i).getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventK = k(i, 2048);
        accessibilityEventK.setContentChangeTypes(0);
        parent.requestSendAccessibilityEvent(view, accessibilityEventK);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(int r21, android.graphics.Rect r22) {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ap.q(int, android.graphics.Rect):boolean");
    }

    public final o0 r(int i) {
        if (i != -1) {
            return l(i);
        }
        View view = this.i;
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(view);
        o0 o0Var = new o0(accessibilityNodeInfoObtain);
        WeakHashMap weakHashMap = xr0.a;
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain);
        ArrayList arrayList = new ArrayList();
        o(arrayList);
        if (accessibilityNodeInfoObtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            o0Var.a.addChild(view, ((Integer) arrayList.get(i2)).intValue());
        }
        return o0Var;
    }

    public abstract boolean s(int i, int i2, Bundle bundle);

    public abstract void u(int i, o0 o0Var);

    public final boolean w(int i) {
        int i2;
        View view = this.i;
        if ((!view.isFocused() && !view.requestFocus()) || (i2 = this.l) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            j(i2);
        }
        if (i == Integer.MIN_VALUE) {
            return false;
        }
        this.l = i;
        v(i, true);
        x(i, 8);
        return true;
    }

    public final void x(int i, int i2) {
        View view;
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = (view = this.i).getParent()) == null) {
            return;
        }
        parent.requestSendAccessibilityEvent(view, k(i, i2));
    }

    public void t(o0 o0Var) {
    }

    public void v(int i, boolean z) {
    }
}
