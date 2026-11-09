package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class oe extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ pe b;

    public /* synthetic */ oe(pe peVar, int i) {
        this.a = i;
        this.b = peVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                super.onAnimationEnd(animator);
                pe peVar = this.b;
                peVar.c();
                z9 z9Var = peVar.j;
                if (z9Var != null) {
                    z9Var.a((a40) peVar.a);
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
                pe peVar = this.b;
                peVar.g = (peVar.g + 4) % peVar.f.c.length;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }
}
