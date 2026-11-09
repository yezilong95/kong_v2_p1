package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class kn extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup a;
    public final /* synthetic */ View b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ nw0 d;
    public final /* synthetic */ mn e;

    public kn(ViewGroup viewGroup, View view, boolean z, nw0 nw0Var, mn mnVar) {
        this.a = viewGroup;
        this.b = view;
        this.c = z;
        this.d = nw0Var;
        this.e = mnVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.a;
        View view = this.b;
        viewGroup.endViewTransition(view);
        if (this.c) {
            zn.a(view, this.d.a);
        }
        this.e.e();
    }
}
