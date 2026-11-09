package com.google.android.material.appbar;

import android.view.View;
import android.widget.ScrollView;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.b0;
import defpackage.o0;

/* JADX INFO: loaded from: classes.dex */
public final class b extends b0 {
    public final /* synthetic */ AppBarLayout.BaseBehavior d;

    public b(AppBarLayout.BaseBehavior baseBehavior) {
        this.d = baseBehavior;
    }

    @Override // defpackage.b0
    public final void d(View view, o0 o0Var) {
        this.a.onInitializeAccessibilityNodeInfo(view, o0Var.a);
        o0Var.l(this.d.p);
        o0Var.i(ScrollView.class.getName());
    }
}
