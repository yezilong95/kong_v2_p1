package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.transition.R$id;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class np0 extends AnimatorListenerAdapter implements gp0 {
    public final View a;
    public final View b;
    public final int c;
    public final int d;
    public int[] e;
    public float f;
    public float g;
    public final float h;
    public final float i;

    public np0(View view, View view2, int i, int i2, float f, float f2) {
        this.b = view;
        this.a = view2;
        this.c = i - Math.round(view.getTranslationX());
        this.d = i2 - Math.round(view.getTranslationY());
        this.h = f;
        this.i = f2;
        int[] iArr = (int[]) view2.getTag(R$id.transition_position);
        this.e = iArr;
        if (iArr != null) {
            view2.setTag(R$id.transition_position, null);
        }
    }

    @Override // defpackage.gp0
    public final void d(Transition transition) {
        View view = this.b;
        view.setTranslationX(this.h);
        view.setTranslationY(this.i);
        transition.u(this);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        if (this.e == null) {
            this.e = new int[2];
        }
        int[] iArr = this.e;
        float f = this.c;
        View view = this.b;
        iArr[0] = Math.round(view.getTranslationX() + f);
        this.e[1] = Math.round(view.getTranslationY() + this.d);
        this.a.setTag(R$id.transition_position, this.e);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        View view = this.b;
        this.f = view.getTranslationX();
        this.g = view.getTranslationY();
        view.setTranslationX(this.h);
        view.setTranslationY(this.i);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        float f = this.f;
        View view = this.b;
        view.setTranslationX(f);
        view.setTranslationY(this.g);
    }

    @Override // defpackage.gp0
    public final void a() {
    }

    @Override // defpackage.gp0
    public final void b() {
    }

    @Override // defpackage.gp0
    public final void c() {
    }

    @Override // defpackage.gp0
    public final void e() {
    }
}
