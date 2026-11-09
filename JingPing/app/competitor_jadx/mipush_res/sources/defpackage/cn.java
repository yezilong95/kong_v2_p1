package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.n;

/* JADX INFO: loaded from: classes.dex */
public final class cn extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ dn b;
    public final /* synthetic */ ViewPropertyAnimator c;
    public final /* synthetic */ View d;
    public final /* synthetic */ fn e;

    public /* synthetic */ cn(fn fnVar, dn dnVar, ViewPropertyAnimator viewPropertyAnimator, View view, int i) {
        this.a = i;
        this.e = fnVar;
        this.b = dnVar;
        this.c = viewPropertyAnimator;
        this.d = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                this.c.setListener(null);
                View view = this.d;
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                dn dnVar = this.b;
                n nVar = dnVar.a;
                fn fnVar = this.e;
                fnVar.c(nVar);
                fnVar.r.remove(dnVar.a);
                fnVar.i();
                break;
            default:
                this.c.setListener(null);
                View view2 = this.d;
                view2.setAlpha(1.0f);
                view2.setTranslationX(0.0f);
                view2.setTranslationY(0.0f);
                dn dnVar2 = this.b;
                n nVar2 = dnVar2.b;
                fn fnVar2 = this.e;
                fnVar2.c(nVar2);
                fnVar2.r.remove(dnVar2.b);
                fnVar2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                n nVar = this.b.a;
                this.e.getClass();
                break;
            default:
                n nVar2 = this.b.b;
                this.e.getClass();
                break;
        }
    }
}
