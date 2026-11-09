package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AnimatorListenerAdapter {
    public final /* synthetic */ g a;

    public f(g gVar) {
        this.a = gVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        g gVar = this.a;
        gVar.c.setVisibility(8);
        if (!gVar.a.g()) {
            gVar.a.f();
        }
        gVar.a.setTransitionState(SearchView.TransitionState.HIDDEN);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.a.a.setTransitionState(SearchView.TransitionState.HIDING);
    }
}
