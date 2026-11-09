package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;
import androidx.transition.Transition;
import androidx.transition.Visibility;

/* JADX INFO: loaded from: classes.dex */
public final class a71 extends AnimatorListenerAdapter implements s11 {
    public final ViewGroup a;
    public final View b;
    public final View c;
    public boolean d = true;
    public final /* synthetic */ Visibility e;

    public a71(Visibility visibility, ViewGroup viewGroup, View view, View view2) {
        this.e = visibility;
        this.a = viewGroup;
        this.b = view;
        this.c = view2;
    }

    @Override // defpackage.s11
    public final void d(Transition transition) {
        if (this.d) {
            h();
        }
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
        transition.x(this);
    }

    public final void h() {
        this.c.setTag(R$id.save_overlay_view, null);
        this.a.getOverlay().remove(this.b);
        this.d = false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        h();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        this.a.getOverlay().remove(this.b);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        View view = this.b;
        if (view.getParent() == null) {
            this.a.getOverlay().add(view);
        } else {
            this.e.c();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator, boolean z) {
        if (z) {
            View view = this.c;
            int i = R$id.save_overlay_view;
            View view2 = this.b;
            view.setTag(i, view2);
            this.a.getOverlay().add(view2);
            this.d = true;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        if (z) {
            return;
        }
        h();
    }

    @Override // defpackage.s11
    public final void b() {
    }

    @Override // defpackage.s11
    public final void e() {
    }

    @Override // defpackage.s11
    public final void c(Transition transition) {
    }
}
