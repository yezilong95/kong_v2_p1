package androidx.transition;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.b71;
import defpackage.la0;
import defpackage.qm;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final String[] g0 = {"android:visibility:visibility", "android:visibility:parent"};
    public int f0;

    public Visibility() {
        this.f0 = 3;
    }

    public static void I(a21 a21Var) {
        int visibility = a21Var.b.getVisibility();
        HashMap map = a21Var.a;
        map.put("android:visibility:visibility", Integer.valueOf(visibility));
        View view = a21Var.b;
        map.put("android:visibility:parent", view.getParent());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        map.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.b71 J(defpackage.a21 r8, defpackage.a21 r9) {
        /*
            b71 r0 = new b71
            r0.<init>()
            r1 = 0
            r0.a = r1
            r0.b = r1
            r2 = 0
            r3 = -1
            java.lang.String r4 = "android:visibility:parent"
            java.lang.String r5 = "android:visibility:visibility"
            if (r8 == 0) goto L2f
            java.util.HashMap r6 = r8.a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L2f
            java.lang.Object r7 = r6.get(r5)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r0.c = r7
            java.lang.Object r6 = r6.get(r4)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.e = r6
            goto L33
        L2f:
            r0.c = r3
            r0.e = r2
        L33:
            if (r9 == 0) goto L52
            java.util.HashMap r6 = r9.a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L52
            java.lang.Object r2 = r6.get(r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.d = r2
            java.lang.Object r2 = r6.get(r4)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.f = r2
            goto L56
        L52:
            r0.d = r3
            r0.f = r2
        L56:
            r2 = 1
            if (r8 == 0) goto L8a
            if (r9 == 0) goto L8a
            int r8 = r0.c
            int r9 = r0.d
            if (r8 != r9) goto L68
            android.view.ViewGroup r3 = r0.e
            android.view.ViewGroup r4 = r0.f
            if (r3 != r4) goto L68
            goto L9f
        L68:
            if (r8 == r9) goto L78
            if (r8 != 0) goto L71
            r0.b = r1
            r0.a = r2
            return r0
        L71:
            if (r9 != 0) goto L9f
            r0.b = r2
            r0.a = r2
            return r0
        L78:
            android.view.ViewGroup r8 = r0.f
            if (r8 != 0) goto L81
            r0.b = r1
            r0.a = r2
            return r0
        L81:
            android.view.ViewGroup r8 = r0.e
            if (r8 != 0) goto L9f
            r0.b = r2
            r0.a = r2
            return r0
        L8a:
            if (r8 != 0) goto L95
            int r8 = r0.d
            if (r8 != 0) goto L95
            r0.b = r2
            r0.a = r2
            return r0
        L95:
            if (r9 != 0) goto L9f
            int r8 = r0.c
            if (r8 != 0) goto L9f
            r0.b = r1
            r0.a = r2
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.J(a21, a21):b71");
    }

    public Animator K(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        return null;
    }

    public Animator L(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        return null;
    }

    public final void M(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.f0 = i;
    }

    @Override // androidx.transition.Transition
    public void d(a21 a21Var) {
        I(a21Var);
    }

    @Override // androidx.transition.Transition
    public void g(a21 a21Var) {
        I(a21Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        if (J(n(r5, false), r(r5, false)).a != false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0276  */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.animation.Animator k(android.view.ViewGroup r24, defpackage.a21 r25, defpackage.a21 r26) {
        /*
            Method dump skipped, instruction units count: 674
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.k(android.view.ViewGroup, a21, a21):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] q() {
        return g0;
    }

    @Override // androidx.transition.Transition
    public final boolean s(a21 a21Var, a21 a21Var2) {
        if (a21Var == null && a21Var2 == null) {
            return false;
        }
        if (a21Var != null && a21Var2 != null && a21Var2.a.containsKey("android:visibility:visibility") != a21Var.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        b71 b71VarJ = J(a21Var, a21Var2);
        if (b71VarJ.a) {
            return b71VarJ.c == 0 || b71VarJ.d == 0;
        }
        return false;
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f0 = 3;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.t);
        int iY = la0.y(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (iY != 0) {
            M(iY);
        }
    }
}
