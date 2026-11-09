package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.ad;
import defpackage.bd;
import defpackage.cd;
import defpackage.la0;
import defpackage.o80;
import defpackage.p61;
import defpackage.pi0;
import defpackage.qm;
import defpackage.yc;
import defpackage.z11;
import defpackage.zc;
import defpackage.zm0;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeBounds extends Transition {
    public static final String[] g0 = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final yc h0 = new yc(PointF.class, "topLeft", 0);
    public static final yc i0 = new yc(PointF.class, "bottomRight", 1);
    public static final yc j0 = new yc(PointF.class, "bottomRight", 2);
    public static final yc k0 = new yc(PointF.class, "topLeft", 3);
    public static final yc l0 = new yc(PointF.class, "position", 4);
    public static final zm0 m0 = new zm0();
    public boolean f0;

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f0 = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.s);
        boolean z = la0.J((XmlResourceParser) attributeSet, "resizeClip") ? typedArrayObtainStyledAttributes.getBoolean(0, false) : false;
        typedArrayObtainStyledAttributes.recycle();
        this.f0 = z;
    }

    public final void I(a21 a21Var) {
        View view = a21Var.b;
        if (!view.isLaidOut() && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        HashMap map = a21Var.a;
        map.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        map.put("android:changeBounds:parent", a21Var.b.getParent());
        if (this.f0) {
            map.put("android:changeBounds:clip", view.getClipBounds());
        }
    }

    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        I(a21Var);
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        Rect rect;
        I(a21Var);
        if (!this.f0 || (rect = (Rect) a21Var.b.getTag(R$id.transition_clip)) == null) {
            return;
        }
        a21Var.a.put("android:changeBounds:clip", rect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.transition.Transition
    public final Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        int i;
        int i2;
        int i3;
        int i4;
        ObjectAnimator objectAnimatorA;
        int i5;
        Rect rect;
        View view;
        ObjectAnimator objectAnimatorOfObject;
        Animator animator;
        ObjectAnimator objectAnimatorA2;
        if (a21Var != null && a21Var2 != null) {
            HashMap map = a21Var.a;
            HashMap map2 = a21Var2.a;
            ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
            ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
            if (viewGroup2 != null && viewGroup3 != null) {
                Rect rect2 = (Rect) map.get("android:changeBounds:bounds");
                Rect rect3 = (Rect) map2.get("android:changeBounds:bounds");
                int i6 = rect2.left;
                int i7 = rect3.left;
                int i8 = rect2.top;
                int i9 = rect3.top;
                int i10 = rect2.right;
                int i11 = rect3.right;
                int i12 = rect2.bottom;
                int i13 = rect3.bottom;
                int i14 = i10 - i6;
                int i15 = i12 - i8;
                int i16 = i11 - i7;
                int i17 = i13 - i9;
                Rect rect4 = (Rect) map.get("android:changeBounds:clip");
                Rect rect5 = (Rect) map2.get("android:changeBounds:clip");
                if ((i14 == 0 || i15 == 0) && (i16 == 0 || i17 == 0)) {
                    i = 0;
                } else {
                    i = (i6 == i7 && i8 == i9) ? 0 : 1;
                    if (i10 != i11 || i12 != i13) {
                        i++;
                    }
                }
                if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
                    i++;
                }
                if (i <= 0) {
                    return null;
                }
                boolean z = this.f0;
                View view2 = a21Var2.b;
                yc ycVar = l0;
                if (z) {
                    p61.a(view2, i6, i8, i6 + Math.max(i14, i16), i8 + Math.max(i15, i17));
                    if (i6 == i7 && i8 == i9) {
                        objectAnimatorA = null;
                        i2 = i12;
                        i3 = i6;
                        i4 = i7;
                    } else {
                        i2 = i12;
                        i3 = i6;
                        i4 = i7;
                        objectAnimatorA = pi0.a(view2, ycVar, this.a0.a(i6, i8, i7, i9));
                    }
                    boolean z2 = rect4 == null;
                    if (z2) {
                        i5 = 0;
                        rect = new Rect(0, 0, i14, i15);
                    } else {
                        i5 = 0;
                        rect = rect4;
                    }
                    boolean z3 = rect5 == null ? 1 : i5;
                    Rect rect6 = z3 != 0 ? new Rect(i5, i5, i16, i17) : rect5;
                    if (rect.equals(rect6)) {
                        view = view2;
                        objectAnimatorOfObject = null;
                    } else {
                        view2.setClipBounds(rect);
                        objectAnimatorOfObject = ObjectAnimator.ofObject(view2, "clipBounds", m0, rect, rect6);
                        view = view2;
                        ad adVar = new ad(view, rect, z2, rect6, z3, i3, i8, i10, i2, i4, i9, i11, i13);
                        objectAnimatorOfObject.addListener(adVar);
                        a(adVar);
                    }
                    boolean z4 = z11.a;
                    animator = objectAnimatorA;
                    if (objectAnimatorA == null) {
                        animator = objectAnimatorOfObject;
                    } else if (objectAnimatorOfObject != null) {
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(objectAnimatorA, objectAnimatorOfObject);
                        animator = animatorSet;
                    }
                } else {
                    p61.a(view2, i6, i8, i10, i12);
                    if (i != 2) {
                        objectAnimatorA2 = (i6 == i7 && i8 == i9) ? pi0.a(view2, j0, this.a0.a(i10, i12, i11, i13)) : pi0.a(view2, k0, this.a0.a(i6, i8, i7, i9));
                    } else if (i14 == i16 && i15 == i17) {
                        objectAnimatorA2 = pi0.a(view2, ycVar, this.a0.a(i6, i8, i7, i9));
                    } else {
                        cd cdVar = new cd(view2);
                        ObjectAnimator objectAnimatorA3 = pi0.a(cdVar, h0, this.a0.a(i6, i8, i7, i9));
                        ObjectAnimator objectAnimatorA4 = pi0.a(cdVar, i0, this.a0.a(i10, i12, i11, i13));
                        AnimatorSet animatorSet2 = new AnimatorSet();
                        animatorSet2.playTogether(objectAnimatorA3, objectAnimatorA4);
                        animatorSet2.addListener(new zc(cdVar));
                        view = view2;
                        animator = animatorSet2;
                    }
                    view = view2;
                    animator = objectAnimatorA2;
                }
                if (view.getParent() instanceof ViewGroup) {
                    ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                    o80.g0(viewGroup4, true);
                    o().a(new bd(viewGroup4));
                }
                return animator;
            }
        }
        return null;
    }

    @Override // androidx.transition.Transition
    public final String[] q() {
        return g0;
    }
}
