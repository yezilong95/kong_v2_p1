package com.google.android.material.appbar;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.y0;

/* JADX INFO: loaded from: classes.dex */
public final class c implements y0 {
    public final /* synthetic */ CoordinatorLayout b;
    public final /* synthetic */ AppBarLayout c;
    public final /* synthetic */ View d;
    public final /* synthetic */ int e;
    public final /* synthetic */ AppBarLayout.BaseBehavior f;

    public c(AppBarLayout.BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
        this.f = baseBehavior;
        this.b = coordinatorLayout;
        this.c = appBarLayout;
        this.d = view;
        this.e = i;
    }

    @Override // defpackage.y0
    public final boolean c(View view) {
        View view2 = this.d;
        int i = this.e;
        this.f.I(this.b, this.c, view2, i, new int[]{0, 0});
        return true;
    }
}
