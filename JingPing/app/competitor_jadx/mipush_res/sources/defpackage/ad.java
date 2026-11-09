package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.view.View;
import androidx.transition.R$id;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class ad extends AnimatorListenerAdapter implements s11 {
    public final View a;
    public final Rect b;
    public final boolean c;
    public final Rect d;
    public final boolean e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public boolean n;

    public ad(View view, Rect rect, boolean z, Rect rect2, boolean z2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a = view;
        this.b = rect;
        this.c = z;
        this.d = rect2;
        this.e = z2;
        this.f = i;
        this.g = i2;
        this.h = i3;
        this.i = i4;
        this.j = i5;
        this.k = i6;
        this.l = i7;
        this.m = i8;
    }

    @Override // defpackage.s11
    public final void b() {
        View view = this.a;
        view.setTag(R$id.transition_clip, view.getClipBounds());
        view.setClipBounds(this.e ? null : this.d);
    }

    @Override // defpackage.s11
    public final void d(Transition transition) {
        this.n = true;
    }

    @Override // defpackage.s11
    public final void e() {
        int i = R$id.transition_clip;
        View view = this.a;
        Rect rect = (Rect) view.getTag(i);
        view.setTag(R$id.transition_clip, null);
        view.setClipBounds(rect);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        onAnimationEnd(animator, false);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        onAnimationStart(animator, false);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        if (this.n) {
            return;
        }
        Rect rect = null;
        if (z) {
            if (!this.c) {
                rect = this.b;
            }
        } else if (!this.e) {
            rect = this.d;
        }
        View view = this.a;
        view.setClipBounds(rect);
        if (z) {
            p61.a(view, this.f, this.g, this.h, this.i);
        } else {
            p61.a(view, this.j, this.k, this.l, this.m);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator, boolean z) {
        int i = this.h;
        int i2 = this.f;
        int i3 = this.l;
        int i4 = this.j;
        int iMax = Math.max(i - i2, i3 - i4);
        int i5 = this.i;
        int i6 = this.g;
        int i7 = this.m;
        int i8 = this.k;
        int iMax2 = Math.max(i5 - i6, i7 - i8);
        if (z) {
            i2 = i4;
        }
        if (z) {
            i6 = i8;
        }
        View view = this.a;
        p61.a(view, i2, i6, iMax + i2, iMax2 + i6);
        view.setClipBounds(z ? this.d : this.b);
    }

    @Override // defpackage.s11
    public final void c(Transition transition) {
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
    }
}
