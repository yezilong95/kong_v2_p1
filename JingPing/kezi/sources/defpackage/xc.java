package defpackage;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
public final class xc implements Animator.AnimatorListener {
    public final /* synthetic */ yc a;
    public final /* synthetic */ zc b;

    public xc(zc zcVar, yc ycVar) {
        this.b = zcVar;
        this.a = ycVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        zc zcVar = this.b;
        yc ycVar = this.a;
        zcVar.a(1.0f, ycVar, true);
        ycVar.k = ycVar.e;
        ycVar.l = ycVar.f;
        ycVar.m = ycVar.g;
        ycVar.a((ycVar.j + 1) % ycVar.i.length);
        if (!zcVar.g) {
            zcVar.f += 1.0f;
            return;
        }
        zcVar.g = false;
        animator.cancel();
        animator.setDuration(1332L);
        animator.start();
        if (ycVar.n) {
            ycVar.n = false;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.b.f = 0.0f;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }
}
