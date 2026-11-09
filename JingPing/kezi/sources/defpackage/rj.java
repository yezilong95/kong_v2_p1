package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class rj extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup a;
    public final /* synthetic */ View b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ zj0 d;
    public final /* synthetic */ tj e;

    public rj(ViewGroup viewGroup, View view, boolean z, zj0 zj0Var, tj tjVar) {
        this.a = viewGroup;
        this.b = view;
        this.c = z;
        this.d = zj0Var;
        this.e = tjVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.a;
        View view = this.b;
        viewGroup.endViewTransition(view);
        if (this.c) {
            hk.a(view, this.d.a);
        }
        this.e.d();
    }
}
