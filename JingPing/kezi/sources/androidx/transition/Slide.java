package androidx.transition;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import defpackage.cj0;
import defpackage.ij0;
import defpackage.jj0;
import defpackage.kj0;
import defpackage.mp0;
import defpackage.xi;
import defpackage.za0;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class Slide extends Visibility {
    public static final DecelerateInterpolator B = new DecelerateInterpolator();
    public static final AccelerateInterpolator C = new AccelerateInterpolator();
    public static final ij0 D = new ij0(0);
    public static final ij0 E = new ij0(1);
    public static final jj0 F = new jj0(0);
    public static final ij0 G = new ij0(2);
    public static final ij0 H = new ij0(3);
    public static final jj0 I = new jj0(1);
    public final kj0 A;

    @SuppressLint({"RestrictedApi"})
    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        jj0 jj0Var = I;
        this.A = jj0Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.l);
        int iQ = za0.q(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        typedArrayObtainStyledAttributes.recycle();
        if (iQ == 3) {
            this.A = D;
        } else if (iQ == 5) {
            this.A = G;
        } else if (iQ == 48) {
            this.A = F;
        } else if (iQ == 80) {
            this.A = jj0Var;
        } else if (iQ == 8388611) {
            this.A = E;
        } else {
            if (iQ != 8388613) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            this.A = H;
        }
        cj0 cj0Var = new cj0();
        cj0Var.t = iQ;
        this.t = cj0Var;
    }

    @Override // androidx.transition.Visibility
    public final Animator H(ViewGroup viewGroup, View view, mp0 mp0Var, mp0 mp0Var2) {
        if (mp0Var2 == null) {
            return null;
        }
        int[] iArr = (int[]) mp0Var2.a.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return xi.m(view, mp0Var2, iArr[0], iArr[1], this.A.a(viewGroup, view), this.A.b(viewGroup, view), translationX, translationY, B, this);
    }

    @Override // androidx.transition.Visibility
    public final Animator I(ViewGroup viewGroup, View view, mp0 mp0Var) {
        if (mp0Var == null) {
            return null;
        }
        int[] iArr = (int[]) mp0Var.a.get("android:slide:screenPosition");
        return xi.m(view, mp0Var, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.A.a(viewGroup, view), this.A.b(viewGroup, view), C, this);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        Visibility.F(mp0Var);
        int[] iArr = new int[2];
        mp0Var.b.getLocationOnScreen(iArr);
        mp0Var.a.put("android:slide:screenPosition", iArr);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        Visibility.F(mp0Var);
        int[] iArr = new int[2];
        mp0Var.b.getLocationOnScreen(iArr);
        mp0Var.a.put("android:slide:screenPosition", iArr);
    }
}
