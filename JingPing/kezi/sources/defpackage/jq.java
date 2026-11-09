package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public abstract class jq extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
    public boolean a;
    public float b;
    public float c;
    public final /* synthetic */ mq d;

    public jq(mq mqVar) {
        this.d = mqVar;
    }

    public abstract float a();

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        float f = (int) this.c;
        u50 u50Var = this.d.b;
        if (u50Var != null) {
            u50Var.l(f);
        }
        this.a = false;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        boolean z = this.a;
        mq mqVar = this.d;
        if (!z) {
            u50 u50Var = mqVar.b;
            this.b = u50Var == null ? 0.0f : u50Var.b.m;
            this.c = a();
            this.a = true;
        }
        float f = this.b;
        float animatedFraction = (int) ((valueAnimator.getAnimatedFraction() * (this.c - f)) + f);
        u50 u50Var2 = mqVar.b;
        if (u50Var2 != null) {
            u50Var2.l(animatedFraction);
        }
    }
}
