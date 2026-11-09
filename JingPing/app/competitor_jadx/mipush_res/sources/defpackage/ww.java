package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public abstract class ww extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
    public boolean a;
    public float b;
    public float c;
    public final /* synthetic */ zw d;

    public ww(zw zwVar) {
        this.d = zwVar;
    }

    public abstract float a();

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        float f = (int) this.c;
        ld0 ld0Var = this.d.b;
        if (ld0Var != null) {
            ld0Var.l(f);
        }
        this.a = false;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        boolean z = this.a;
        zw zwVar = this.d;
        if (!z) {
            ld0 ld0Var = zwVar.b;
            this.b = ld0Var == null ? 0.0f : ld0Var.E.m;
            this.c = a();
            this.a = true;
        }
        float f = this.b;
        float animatedFraction = (int) ((valueAnimator.getAnimatedFraction() * (this.c - f)) + f);
        ld0 ld0Var2 = zwVar.b;
        if (ld0Var2 != null) {
            ld0Var2.l(animatedFraction);
        }
    }
}
