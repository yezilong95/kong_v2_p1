package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.n;

/* JADX INFO: loaded from: classes.dex */
public final class jj extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ kj b;
    public final /* synthetic */ ViewPropertyAnimator c;
    public final /* synthetic */ View d;
    public final /* synthetic */ mj e;

    public /* synthetic */ jj(mj mjVar, kj kjVar, ViewPropertyAnimator viewPropertyAnimator, View view, int i) {
        this.a = i;
        this.e = mjVar;
        this.b = kjVar;
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
                kj kjVar = this.b;
                n nVar = kjVar.a;
                mj mjVar = this.e;
                mjVar.c(nVar);
                mjVar.r.remove(kjVar.a);
                mjVar.i();
                break;
            default:
                this.c.setListener(null);
                View view2 = this.d;
                view2.setAlpha(1.0f);
                view2.setTranslationX(0.0f);
                view2.setTranslationY(0.0f);
                kj kjVar2 = this.b;
                n nVar2 = kjVar2.b;
                mj mjVar2 = this.e;
                mjVar2.c(nVar2);
                mjVar2.r.remove(kjVar2.b);
                mjVar2.i();
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
