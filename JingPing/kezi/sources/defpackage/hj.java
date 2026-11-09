package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.n;

/* JADX INFO: loaded from: classes.dex */
public final class hj extends AnimatorListenerAdapter {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ n b;
    public final /* synthetic */ View c;
    public final /* synthetic */ ViewPropertyAnimator d;
    public final /* synthetic */ mj e;

    public hj(mj mjVar, n nVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.e = mjVar;
        this.b = nVar;
        this.d = viewPropertyAnimator;
        this.c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 1:
                this.c.setAlpha(1.0f);
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                this.d.setListener(null);
                this.c.setAlpha(1.0f);
                mj mjVar = this.e;
                n nVar = this.b;
                mjVar.c(nVar);
                mjVar.q.remove(nVar);
                mjVar.i();
                break;
            default:
                this.d.setListener(null);
                mj mjVar2 = this.e;
                n nVar2 = this.b;
                mjVar2.c(nVar2);
                mjVar2.o.remove(nVar2);
                mjVar2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                this.e.getClass();
                break;
            default:
                this.e.getClass();
                break;
        }
    }

    public hj(mj mjVar, n nVar, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.e = mjVar;
        this.b = nVar;
        this.c = view;
        this.d = viewPropertyAnimator;
    }
}
