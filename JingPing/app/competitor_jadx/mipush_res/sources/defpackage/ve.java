package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class ve extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ af b;

    public /* synthetic */ ve(af afVar, int i) {
        this.a = i;
        this.b = afVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                this.b.d();
                break;
            default:
                af afVar = this.b;
                ze revealInfo = afVar.getRevealInfo();
                revealInfo.c = Float.MAX_VALUE;
                afVar.setRevealInfo(revealInfo);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                this.b.f();
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
