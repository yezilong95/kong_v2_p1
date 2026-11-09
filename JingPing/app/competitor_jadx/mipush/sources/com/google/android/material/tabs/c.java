package com.google.android.material.tabs;

import android.view.View;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public final class c implements View.OnLayoutChangeListener {
    public final /* synthetic */ View a;
    public final /* synthetic */ TabLayout.TabView b;

    public c(TabLayout.TabView tabView, View view) {
        this.b = tabView;
        this.a = view;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        View view2 = this.a;
        if (view2.getVisibility() == 0) {
            int i9 = TabLayout.TabView.P;
            this.b.c(view2);
        }
    }
}
