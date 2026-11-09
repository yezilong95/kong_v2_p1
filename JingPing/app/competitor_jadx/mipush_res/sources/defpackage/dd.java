package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.view.View;
import androidx.transition.ChangeClipBounds;
import androidx.transition.R$id;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class dd extends AnimatorListenerAdapter implements s11 {
    public final Rect a;
    public final Rect b;
    public final View c;

    public dd(Rect rect, Rect rect2, View view) {
        this.c = view;
        this.a = rect;
        this.b = rect2;
    }

    @Override // defpackage.s11
    public final void b() {
        View view = this.c;
        Rect clipBounds = view.getClipBounds();
        if (clipBounds == null) {
            clipBounds = ChangeClipBounds.g0;
        }
        view.setTag(R$id.transition_clip, clipBounds);
        view.setClipBounds(this.b);
    }

    @Override // defpackage.s11
    public final void e() {
        int i = R$id.transition_clip;
        View view = this.c;
        view.setClipBounds((Rect) view.getTag(i));
        view.setTag(R$id.transition_clip, null);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        onAnimationEnd(animator, false);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        View view = this.c;
        if (z) {
            view.setClipBounds(this.a);
        } else {
            view.setClipBounds(this.b);
        }
    }

    @Override // defpackage.s11
    public final void c(Transition transition) {
    }

    @Override // defpackage.s11
    public final void d(Transition transition) {
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
    }
}
