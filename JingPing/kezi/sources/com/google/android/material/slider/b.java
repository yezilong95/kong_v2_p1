package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import defpackage.d90;
import defpackage.rp0;
import defpackage.xo0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AnimatorListenerAdapter {
    public final /* synthetic */ BaseSlider a;

    public b(BaseSlider baseSlider) {
        this.a = baseSlider;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        BaseSlider baseSlider = this.a;
        ViewGroup viewGroupX = rp0.x(baseSlider);
        d90 d90Var = viewGroupX == null ? null : new d90(viewGroupX);
        Iterator it = baseSlider.l.iterator();
        while (it.hasNext()) {
            ((ViewOverlay) d90Var.b).remove((xo0) it.next());
        }
    }
}
