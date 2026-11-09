package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class v50 extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ int b;
    public final /* synthetic */ w50 c;

    public v50(w50 w50Var, boolean z, int i) {
        this.c = w50Var;
        this.a = z;
        this.b = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        w50 w50Var = this.c;
        w50Var.b.setTranslationX(0.0f);
        w50Var.d(0.0f, this.b, this.a);
    }
}
