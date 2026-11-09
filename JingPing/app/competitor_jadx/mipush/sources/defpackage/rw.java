package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class rw extends AnimatorListenerAdapter {
    public boolean a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ i6 c;
    public final /* synthetic */ xw d;

    public rw(xw xwVar, boolean z, i6 i6Var) {
        this.d = xwVar;
        this.b = z;
        this.c = i6Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        xw xwVar = this.d;
        xwVar.r = 0;
        xwVar.l = null;
        if (this.a) {
            return;
        }
        boolean z = this.b;
        xwVar.v.a(z ? 8 : 4, z);
        i6 i6Var = this.c;
        if (i6Var != null) {
            ((pw) i6Var.F).a((FloatingActionButton) i6Var.G);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        xw xwVar = this.d;
        xwVar.v.a(0, this.b);
        xwVar.r = 1;
        xwVar.l = animator;
        this.a = false;
    }
}
