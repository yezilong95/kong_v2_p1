package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AnimatorListenerAdapter {
    public final /* synthetic */ g a;

    public c(g gVar) {
        this.a = gVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        g gVar = this.a;
        if (!gVar.a.g()) {
            gVar.a.i();
        }
        gVar.a.setTransitionState(SearchView.TransitionState.SHOWN);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        g gVar = this.a;
        gVar.c.setVisibility(0);
        SearchBar searchBar = gVar.o;
        searchBar.B0.getClass();
        View centerView = searchBar.getCenterView();
        if (centerView != null) {
            centerView.setAlpha(0.0f);
        }
    }
}
