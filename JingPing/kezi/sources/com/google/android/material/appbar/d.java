package com.google.android.material.appbar;

import android.view.View;
import defpackage.y0;

/* JADX INFO: loaded from: classes.dex */
public final class d implements y0 {
    public final /* synthetic */ AppBarLayout b;
    public final /* synthetic */ boolean c;

    public d(AppBarLayout appBarLayout, boolean z) {
        this.b = appBarLayout;
        this.c = z;
    }

    @Override // defpackage.y0
    public final boolean c(View view) {
        this.b.setExpanded(this.c);
        return true;
    }
}
