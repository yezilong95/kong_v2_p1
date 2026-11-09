package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class f90 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ g90 b;

    public /* synthetic */ f90(g90 g90Var, int i) {
        this.a = i;
        this.b = g90Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                super.onAnimationEnd(animator);
                g90 g90Var = this.b;
                g90Var.c();
                z9 z9Var = g90Var.j;
                if (z9Var != null) {
                    z9Var.a((a40) g90Var.a);
                }
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        switch (this.a) {
            case 0:
                super.onAnimationRepeat(animator);
                g90 g90Var = this.b;
                g90Var.g = (g90Var.g + 1) % g90Var.f.c.length;
                g90Var.h = true;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }
}
