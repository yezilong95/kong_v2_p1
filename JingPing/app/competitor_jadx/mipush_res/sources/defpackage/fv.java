package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.transition.R$id;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class fv extends AnimatorListenerAdapter implements s11 {
    public final View a;
    public boolean b = false;

    public fv(View view) {
        this.a = view;
    }

    @Override // defpackage.s11
    public final void b() {
        View view = this.a;
        view.setTag(R$id.transition_pause_alpha, Float.valueOf(view.getVisibility() == 0 ? p61.a.H(view) : 0.0f));
    }

    @Override // defpackage.s11
    public final void e() {
        this.a.setTag(R$id.transition_pause_alpha, null);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        p61.b(this.a, 1.0f);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        onAnimationEnd(animator, false);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        View view = this.a;
        if (view.hasOverlappingRendering() && view.getLayerType() == 0) {
            this.b = true;
            view.setLayerType(2, null);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        boolean z2 = this.b;
        View view = this.a;
        if (z2) {
            view.setLayerType(0, null);
        }
        if (z) {
            return;
        }
        p61.b(view, 1.0f);
        p61.a.getClass();
    }

    @Override // defpackage.s11
    public final void c(Transition transition) {
    }

    @Override // defpackage.s11
    public final void d(Transition transition) {
    }

    @Override // defpackage.s11
    public final void f(Transition transition) {
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
    }
}
