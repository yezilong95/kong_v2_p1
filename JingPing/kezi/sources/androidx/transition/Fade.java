package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.hp;
import defpackage.ip;
import defpackage.jt0;
import defpackage.mp0;
import defpackage.za0;

/* JADX INFO: loaded from: classes.dex */
public class Fade extends Visibility {
    public Fade(int i) {
        J(i);
    }

    @Override // androidx.transition.Visibility
    public final Animator H(ViewGroup viewGroup, View view, mp0 mp0Var, mp0 mp0Var2) {
        Float f;
        float fFloatValue = (mp0Var == null || (f = (Float) mp0Var.a.get("android:fade:transitionAlpha")) == null) ? 0.0f : f.floatValue();
        return K(view, fFloatValue != 1.0f ? fFloatValue : 0.0f, 1.0f);
    }

    @Override // androidx.transition.Visibility
    public final Animator I(ViewGroup viewGroup, View view, mp0 mp0Var) {
        Float f;
        jt0.a.getClass();
        return K(view, (mp0Var == null || (f = (Float) mp0Var.a.get("android:fade:transitionAlpha")) == null) ? 1.0f : f.floatValue(), 0.0f);
    }

    public final ObjectAnimator K(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        jt0.b(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, jt0.b, f2);
        objectAnimatorOfFloat.addListener(new hp(view));
        a(new ip(0, view));
        return objectAnimatorOfFloat;
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        Visibility.F(mp0Var);
        mp0Var.a.put("android:fade:transitionAlpha", Float.valueOf(jt0.a.F(mp0Var.b)));
    }

    @SuppressLint({"RestrictedApi"})
    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.j);
        J(za0.q(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, this.y));
        typedArrayObtainStyledAttributes.recycle();
    }
}
