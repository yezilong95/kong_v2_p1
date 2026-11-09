package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class dv extends AnimatorListenerAdapter {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public dv(boolean z, View view, View view2) {
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
                xw xwVar = (xw) this.d;
                xwVar.r = 0;
                xwVar.l = null;
                i6 i6Var = (i6) this.c;
                if (i6Var != null) {
                    ((pw) i6Var.F).b();
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
                xw xwVar = (xw) this.d;
                xwVar.v.a(0, this.b);
                xwVar.r = 2;
                xwVar.l = animator;
                break;
        }
    }

    public dv(xw xwVar, boolean z, i6 i6Var) {
        this.d = xwVar;
        this.b = z;
        this.c = i6Var;
    }
}
