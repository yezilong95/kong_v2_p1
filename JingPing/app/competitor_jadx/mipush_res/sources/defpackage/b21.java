package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.transition.R$id;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class b21 extends AnimatorListenerAdapter implements s11 {
    public final View a;
    public final View b;
    public int[] c;
    public float d;
    public float e;
    public final float f;
    public final float g;
    public boolean h;

    public b21(View view, View view2, float f, float f2) {
        this.b = view;
        this.a = view2;
        this.f = f;
        this.g = f2;
        int[] iArr = (int[]) view2.getTag(R$id.transition_position);
        this.c = iArr;
        if (iArr != null) {
            view2.setTag(R$id.transition_position, null);
        }
    }

    @Override // defpackage.s11
    public final void a(Transition transition) {
        if (this.h) {
            return;
        }
        this.a.setTag(R$id.transition_position, null);
    }

    @Override // defpackage.s11
    public final void b() {
        if (this.c == null) {
            this.c = new int[2];
        }
        int[] iArr = this.c;
        View view = this.b;
        view.getLocationOnScreen(iArr);
        this.a.setTag(R$id.transition_position, this.c);
        this.d = view.getTranslationX();
        this.e = view.getTranslationY();
        view.setTranslationX(this.f);
        view.setTranslationY(this.g);
    }

    @Override // defpackage.s11
    public final void d(Transition transition) {
        this.h = true;
        float f = this.f;
        View view = this.b;
        view.setTranslationX(f);
        view.setTranslationY(this.g);
    }

    @Override // defpackage.s11
    public final void e() {
        float f = this.d;
        View view = this.b;
        view.setTranslationX(f);
        view.setTranslationY(this.e);
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
        a(transition);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.h = true;
        float f = this.f;
        View view = this.b;
        view.setTranslationX(f);
        view.setTranslationY(this.g);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        if (z) {
            return;
        }
        float f = this.f;
        View view = this.b;
        view.setTranslationX(f);
        view.setTranslationY(this.g);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        onAnimationEnd(animator, false);
    }

    @Override // defpackage.s11
    public final void c(Transition transition) {
    }
}
