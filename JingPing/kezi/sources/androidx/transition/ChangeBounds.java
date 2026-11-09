package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ed0;
import defpackage.fb;
import defpackage.gb;
import defpackage.hb;
import defpackage.ib;
import defpackage.jb;
import defpackage.jt0;
import defpackage.lp0;
import defpackage.mp0;
import defpackage.oy;
import defpackage.xr0;
import defpackage.y7;
import defpackage.za0;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeBounds extends Transition {
    public static final fb A;
    public static final fb B;
    public static final fb C;
    public static final fb D;
    public static final fb E;
    public static final ed0 F;
    public static final String[] z = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public boolean y;

    static {
        new y7(PointF.class, "boundsOrigin").b = new Rect();
        A = new fb(PointF.class, "topLeft", 0);
        B = new fb(PointF.class, "bottomRight", 1);
        C = new fb(PointF.class, "bottomRight", 2);
        D = new fb(PointF.class, "topLeft", 3);
        E = new fb(PointF.class, "position", 4);
        F = new ed0();
    }

    @SuppressLint({"RestrictedApi"})
    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.h);
        boolean z2 = za0.u((XmlResourceParser) attributeSet, "resizeClip") ? typedArrayObtainStyledAttributes.getBoolean(0, false) : false;
        typedArrayObtainStyledAttributes.recycle();
        this.y = z2;
    }

    public final void F(mp0 mp0Var) {
        View view = mp0Var.b;
        WeakHashMap weakHashMap = xr0.a;
        if (!view.isLaidOut() && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        HashMap map = mp0Var.a;
        map.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        map.put("android:changeBounds:parent", mp0Var.b.getParent());
        if (this.y) {
            map.put("android:changeBounds:clip", view.getClipBounds());
        }
    }

    @Override // androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public final Animator j(ViewGroup viewGroup, mp0 mp0Var, mp0 mp0Var2) {
        int i;
        Rect rect;
        ObjectAnimator objectAnimatorOfObject;
        int i2;
        Rect rect2;
        View view;
        ObjectAnimator objectAnimatorOfObject2;
        Animator animator;
        ObjectAnimator objectAnimatorOfObject3;
        if (mp0Var == null || mp0Var2 == null) {
            return null;
        }
        HashMap map = mp0Var.a;
        HashMap map2 = mp0Var2.a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        Rect rect3 = (Rect) map.get("android:changeBounds:bounds");
        Rect rect4 = (Rect) map2.get("android:changeBounds:bounds");
        int i3 = rect3.left;
        int i4 = rect4.left;
        int i5 = rect3.top;
        int i6 = rect4.top;
        int i7 = rect3.right;
        int i8 = rect4.right;
        int i9 = rect3.bottom;
        int i10 = rect4.bottom;
        int i11 = i7 - i3;
        int i12 = i9 - i5;
        int i13 = i8 - i4;
        int i14 = i10 - i6;
        Rect rect5 = (Rect) map.get("android:changeBounds:clip");
        Rect rect6 = (Rect) map2.get("android:changeBounds:clip");
        if ((i11 == 0 || i12 == 0) && (i13 == 0 || i14 == 0)) {
            i = 0;
        } else {
            i = (i3 == i4 && i5 == i6) ? 0 : 1;
            if (i7 != i8 || i9 != i10) {
                i++;
            }
        }
        if ((rect5 != null && !rect5.equals(rect6)) || (rect5 == null && rect6 != null)) {
            i++;
        }
        if (i <= 0) {
            return null;
        }
        boolean z2 = this.y;
        View view2 = mp0Var2.b;
        fb fbVar = E;
        if (z2) {
            jt0.a(view2, i3, i5, Math.max(i11, i13) + i3, Math.max(i12, i14) + i5);
            if (i3 == i4 && i5 == i6) {
                rect = rect6;
                objectAnimatorOfObject = null;
            } else {
                rect = rect6;
                objectAnimatorOfObject = ObjectAnimator.ofObject(view2, fbVar, (TypeConverter) null, this.u.a(i3, i5, i4, i6));
            }
            if (rect5 == null) {
                i2 = 0;
                rect2 = new Rect(0, 0, i11, i12);
            } else {
                i2 = 0;
                rect2 = rect5;
            }
            Rect rect7 = rect == null ? new Rect(i2, i2, i13, i14) : rect;
            if (rect2.equals(rect7)) {
                view = view2;
                objectAnimatorOfObject2 = null;
            } else {
                WeakHashMap weakHashMap = xr0.a;
                view2.setClipBounds(rect2);
                objectAnimatorOfObject2 = ObjectAnimator.ofObject(view2, "clipBounds", F, rect2, rect7);
                view = view2;
                objectAnimatorOfObject2.addListener(new hb(view, rect, i4, i6, i8, i10));
            }
            boolean z3 = lp0.a;
            animator = objectAnimatorOfObject;
            if (objectAnimatorOfObject == null) {
                animator = objectAnimatorOfObject2;
            } else if (objectAnimatorOfObject2 != null) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorOfObject, objectAnimatorOfObject2);
                animator = animatorSet;
            }
        } else {
            jt0.a(view2, i3, i5, i7, i9);
            if (i != 2) {
                objectAnimatorOfObject3 = (i3 == i4 && i5 == i6) ? ObjectAnimator.ofObject(view2, C, (TypeConverter) null, this.u.a(i7, i9, i8, i10)) : ObjectAnimator.ofObject(view2, D, (TypeConverter) null, this.u.a(i3, i5, i4, i6));
            } else if (i11 == i13 && i12 == i14) {
                objectAnimatorOfObject3 = ObjectAnimator.ofObject(view2, fbVar, (TypeConverter) null, this.u.a(i3, i5, i4, i6));
            } else {
                jb jbVar = new jb();
                jbVar.e = view2;
                ObjectAnimator objectAnimatorOfObject4 = ObjectAnimator.ofObject(jbVar, A, (TypeConverter) null, this.u.a(i3, i5, i4, i6));
                ObjectAnimator objectAnimatorOfObject5 = ObjectAnimator.ofObject(jbVar, B, (TypeConverter) null, this.u.a(i7, i9, i8, i10));
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(objectAnimatorOfObject4, objectAnimatorOfObject5);
                animatorSet2.addListener(new gb(jbVar));
                view = view2;
                animator = animatorSet2;
            }
            view = view2;
            animator = objectAnimatorOfObject3;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            oy.Q(viewGroup4, true);
            a(new ib(viewGroup4));
        }
        return animator;
    }

    @Override // androidx.transition.Transition
    public final String[] o() {
        return z;
    }
}
