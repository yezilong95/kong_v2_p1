package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class eq extends AnimatorListenerAdapter {
    public boolean a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ e5 c;
    public final /* synthetic */ kq d;

    public eq(kq kqVar, boolean z, e5 e5Var) {
        this.d = kqVar;
        this.b = z;
        this.c = e5Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        kq kqVar = this.d;
        kqVar.r = 0;
        kqVar.l = null;
        if (this.a) {
            return;
        }
        boolean z = this.b;
        kqVar.v.a(z ? 8 : 4, z);
        e5 e5Var = this.c;
        if (e5Var != null) {
            ((cq) e5Var.c).a((FloatingActionButton) e5Var.d);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        kq kqVar = this.d;
        kqVar.v.a(0, this.b);
        kqVar.r = 1;
        kqVar.l = animator;
        this.a = false;
    }
}
