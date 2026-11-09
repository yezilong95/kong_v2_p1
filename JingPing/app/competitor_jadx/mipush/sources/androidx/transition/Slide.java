package androidx.transition;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import defpackage.a21;
import defpackage.fa0;
import defpackage.la0;
import defpackage.qm;
import defpackage.rv0;
import defpackage.xv0;
import defpackage.yv0;
import defpackage.zv0;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class Slide extends Visibility {
    public static final DecelerateInterpolator i0 = new DecelerateInterpolator();
    public static final AccelerateInterpolator j0 = new AccelerateInterpolator();
    public static final xv0 k0 = new xv0(0);
    public static final xv0 l0 = new xv0(1);
    public static final yv0 m0 = new yv0(0);
    public static final xv0 n0 = new xv0(2);
    public static final xv0 o0 = new xv0(3);
    public static final yv0 p0 = new yv0(1);
    public final zv0 h0;

    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        yv0 yv0Var = p0;
        this.h0 = yv0Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.w);
        int iY = la0.y(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        typedArrayObtainStyledAttributes.recycle();
        if (iY == 3) {
            this.h0 = k0;
        } else if (iY == 5) {
            this.h0 = n0;
        } else if (iY == 48) {
            this.h0 = m0;
        } else if (iY == 80) {
            this.h0 = yv0Var;
        } else if (iY == 8388611) {
            this.h0 = l0;
        } else {
            if (iY != 8388613) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            this.h0 = o0;
        }
        rv0 rv0Var = new rv0();
        rv0Var.b0 = iY;
        this.Z = rv0Var;
    }

    @Override // androidx.transition.Visibility
    public final Animator K(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        if (a21Var2 == null) {
            return null;
        }
        int[] iArr = (int[]) a21Var2.a.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return fa0.x(view, a21Var2, iArr[0], iArr[1], this.h0.d(viewGroup, view), this.h0.c(viewGroup, view), translationX, translationY, i0, this);
    }

    @Override // androidx.transition.Visibility
    public final Animator L(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        if (a21Var == null) {
            return null;
        }
        int[] iArr = (int[]) a21Var.a.get("android:slide:screenPosition");
        return fa0.x(view, a21Var, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.h0.d(viewGroup, view), this.h0.c(viewGroup, view), j0, this);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void d(a21 a21Var) {
        Visibility.I(a21Var);
        int[] iArr = new int[2];
        a21Var.b.getLocationOnScreen(iArr);
        a21Var.a.put("android:slide:screenPosition", iArr);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void g(a21 a21Var) {
        Visibility.I(a21Var);
        int[] iArr = new int[2];
        a21Var.b.getLocationOnScreen(iArr);
        a21Var.a.put("android:slide:screenPosition", iArr);
    }
}
