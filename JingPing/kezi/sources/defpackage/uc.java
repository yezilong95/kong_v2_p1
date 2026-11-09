package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class uc extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ vc b;

    public /* synthetic */ uc(vc vcVar, int i) {
        this.a = i;
        this.b = vcVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                super.onAnimationEnd(animator);
                vc vcVar = this.b;
                vcVar.a();
                d8 d8Var = vcVar.k;
                if (d8Var != null) {
                    d8Var.a((vx) vcVar.a);
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
                vc vcVar = this.b;
                vcVar.h = (vcVar.h + 4) % vcVar.g.c.length;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }
}
