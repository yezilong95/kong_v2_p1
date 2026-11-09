package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class md0 extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ int b;
    public final /* synthetic */ nd0 c;

    public md0(nd0 nd0Var, boolean z, int i) {
        this.c = nd0Var;
        this.a = z;
        this.b = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        nd0 nd0Var = this.c;
        nd0Var.b.setTranslationX(0.0f);
        nd0Var.d(0.0f, this.b, this.a);
    }
}
