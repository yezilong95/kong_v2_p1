package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class bd extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ gd b;

    public /* synthetic */ bd(gd gdVar, int i) {
        this.a = i;
        this.b = gdVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                this.b.g();
                break;
            default:
                gd gdVar = this.b;
                fd revealInfo = gdVar.getRevealInfo();
                revealInfo.c = Float.MAX_VALUE;
                gdVar.setRevealInfo(revealInfo);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                this.b.d();
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
