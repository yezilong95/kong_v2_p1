package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class qd extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ rd b;

    public /* synthetic */ qd(rd rdVar, int i) {
        this.a = i;
        this.b = rdVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                this.b.b.h(false);
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                this.b.b.h(true);
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
