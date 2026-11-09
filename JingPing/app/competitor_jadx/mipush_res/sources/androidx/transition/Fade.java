package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.fv;
import defpackage.la0;
import defpackage.p61;
import defpackage.qm;

/* JADX INFO: loaded from: classes.dex */
public class Fade extends Visibility {
    public Fade(int i) {
        M(i);
    }

    public static float O(a21 a21Var, float f) {
        Float f2;
        return (a21Var == null || (f2 = (Float) a21Var.a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }

    @Override // androidx.transition.Visibility
    public final Animator K(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        p61.a.getClass();
        return N(view, O(a21Var, 0.0f), 1.0f);
    }

    @Override // androidx.transition.Visibility
    public final Animator L(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        p61.a.getClass();
        ObjectAnimator objectAnimatorN = N(view, O(a21Var, 1.0f), 0.0f);
        if (objectAnimatorN == null) {
            p61.b(view, O(a21Var2, 1.0f));
        }
        return objectAnimatorN;
    }

    public final ObjectAnimator N(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        p61.b(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, p61.b, f2);
        fv fvVar = new fv(view);
        objectAnimatorOfFloat.addListener(fvVar);
        o().a(fvVar);
        return objectAnimatorOfFloat;
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void g(a21 a21Var) {
        Visibility.I(a21Var);
        int i = R$id.transition_pause_alpha;
        View view = a21Var.b;
        Float fValueOf = (Float) view.getTag(i);
        if (fValueOf == null) {
            fValueOf = view.getVisibility() == 0 ? Float.valueOf(p61.a.H(view)) : Float.valueOf(0.0f);
        }
        a21Var.a.put("android:fade:transitionAlpha", fValueOf);
    }

    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.u);
        M(la0.y(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, this.f0));
        typedArrayObtainStyledAttributes.recycle();
    }
}
