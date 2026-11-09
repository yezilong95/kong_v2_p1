package androidx.transition;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.mp0;
import defpackage.qt0;
import defpackage.za0;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final String[] z = {"android:visibility:visibility", "android:visibility:parent"};
    public int y;

    public Visibility() {
        this.y = 3;
    }

    public static void F(mp0 mp0Var) {
        int visibility = mp0Var.b.getVisibility();
        HashMap map = mp0Var.a;
        map.put("android:visibility:visibility", Integer.valueOf(visibility));
        View view = mp0Var.b;
        map.put("android:visibility:parent", view.getParent());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        map.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.qt0 G(defpackage.mp0 r8, defpackage.mp0 r9) {
        /*
            qt0 r0 = new qt0
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.G(mp0, mp0):qt0");
    }

    public Animator H(ViewGroup viewGroup, View view, mp0 mp0Var, mp0 mp0Var2) {
        return null;
    }

    public Animator I(ViewGroup viewGroup, View view, mp0 mp0Var) {
        return null;
    }

    public final void J(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.y = i;
    }

    @Override // androidx.transition.Transition
    public void c(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public void f(mp0 mp0Var) {
        F(mp0Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        if (G(m(r5, false), p(r5, false)).a != false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x022a  */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator j(android.view.ViewGroup r24, defpackage.mp0 r25, defpackage.mp0 r26) {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.j(android.view.ViewGroup, mp0, mp0):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] o() {
        return z;
    }

    @Override // androidx.transition.Transition
    public final boolean q(mp0 mp0Var, mp0 mp0Var2) {
        if (mp0Var == null && mp0Var2 == null) {
            return false;
        }
        if (mp0Var != null && mp0Var2 != null && mp0Var2.a.containsKey("android:visibility:visibility") != mp0Var.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        qt0 qt0VarG = G(mp0Var, mp0Var2);
        if (qt0VarG.a) {
            return qt0VarG.c == 0 || qt0VarG.d == 0;
        }
        return false;
    }

    @SuppressLint({"RestrictedApi"})
    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = 3;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.i);
        int iQ = za0.q(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (iQ != 0) {
            J(iQ);
        }
    }
}
