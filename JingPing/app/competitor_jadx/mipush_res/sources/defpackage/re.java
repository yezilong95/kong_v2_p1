package defpackage;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
public final class re implements Animator.AnimatorListener {
    public final /* synthetic */ se a;
    public final /* synthetic */ te b;

    public re(te teVar, se seVar) {
        this.b = teVar;
        this.a = seVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        te teVar = this.b;
        se seVar = this.a;
        teVar.a(1.0f, seVar, true);
        seVar.k = seVar.e;
        seVar.l = seVar.f;
        seVar.m = seVar.g;
        seVar.a((seVar.j + 1) % seVar.i.length);
        if (!teVar.J) {
            teVar.I += 1.0f;
            return;
        }
        teVar.J = false;
        animator.cancel();
        animator.setDuration(1332L);
        animator.start();
        if (seVar.n) {
            seVar.n = false;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.b.I = 0.0f;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }
}
