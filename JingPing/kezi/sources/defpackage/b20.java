package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class b20 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ c20 b;

    public /* synthetic */ b20(c20 c20Var, int i) {
        this.a = i;
        this.b = c20Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                super.onAnimationEnd(animator);
                c20 c20Var = this.b;
                c20Var.a();
                d8 d8Var = c20Var.k;
                if (d8Var != null) {
                    d8Var.a((vx) c20Var.a);
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
                c20 c20Var = this.b;
                c20Var.h = (c20Var.h + 1) % c20Var.g.c.length;
                c20Var.i = true;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }
}
