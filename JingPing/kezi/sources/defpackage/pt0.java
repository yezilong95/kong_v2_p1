package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class pt0 extends AnimatorListenerAdapter implements gp0 {
    public final View a;
    public final int b;
    public final ViewGroup c;
    public boolean e;
    public boolean f = false;
    public final boolean d = true;

    public pt0(View view, int i) {
        this.a = view;
        this.b = i;
        this.c = (ViewGroup) view.getParent();
        f(true);
    }

    @Override // defpackage.gp0
    public final void c() {
        f(false);
    }

    @Override // defpackage.gp0
    public final void d(Transition transition) {
        if (!this.f) {
            jt0.c(this.a, this.b);
            ViewGroup viewGroup = this.c;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        f(false);
        transition.u(this);
    }

    @Override // defpackage.gp0
    public final void e() {
        f(true);
    }

    public final void f(boolean z) {
        ViewGroup viewGroup;
        if (!this.d || this.e == z || (viewGroup = this.c) == null) {
            return;
        }
        this.e = z;
        oy.Q(viewGroup, z);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (!this.f) {
            jt0.c(this.a, this.b);
            ViewGroup viewGroup = this.c;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        f(false);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        if (this.f) {
            return;
        }
        jt0.c(this.a, this.b);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        if (this.f) {
            return;
        }
        jt0.c(this.a, 0);
    }

    @Override // defpackage.gp0
    public final void a() {
    }

    @Override // defpackage.gp0
    public final void b() {
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}
