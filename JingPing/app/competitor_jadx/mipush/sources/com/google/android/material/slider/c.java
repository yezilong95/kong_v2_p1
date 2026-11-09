package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import defpackage.fa0;
import defpackage.j11;
import defpackage.wl0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AnimatorListenerAdapter {
    public final /* synthetic */ BaseSlider a;

    public c(BaseSlider baseSlider) {
        this.a = baseSlider;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        BaseSlider baseSlider = this.a;
        ViewGroup viewGroupQ = fa0.Q(baseSlider);
        wl0 wl0Var = viewGroupQ == null ? null : new wl0(viewGroupQ);
        Iterator it = baseSlider.P.iterator();
        while (it.hasNext()) {
            ((ViewOverlay) wl0Var.E).remove((j11) it.next());
        }
    }
}
