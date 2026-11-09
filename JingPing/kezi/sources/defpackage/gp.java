package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class gp extends AnimatorListenerAdapter {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public gp(boolean z, View view, View view2) {
        this.b = z;
        this.c = view;
        this.d = view2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                if (!this.b) {
                    ((View) this.c).setVisibility(4);
                    View view = (View) this.d;
                    view.setAlpha(1.0f);
                    view.setVisibility(0);
                }
                break;
            default:
                kq kqVar = (kq) this.d;
                kqVar.r = 0;
                kqVar.l = null;
                e5 e5Var = (e5) this.c;
                if (e5Var != null) {
                    ((cq) e5Var.c).b();
                }
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                if (this.b) {
                    ((View) this.c).setVisibility(0);
                    View view = (View) this.d;
                    view.setAlpha(0.0f);
                    view.setVisibility(4);
                }
                break;
            default:
                kq kqVar = (kq) this.d;
                kqVar.v.a(0, this.b);
                kqVar.r = 2;
                kqVar.l = animator;
                break;
        }
    }

    public gp(kq kqVar, boolean z, e5 e5Var) {
        this.d = kqVar;
        this.b = z;
        this.c = e5Var;
    }
}
