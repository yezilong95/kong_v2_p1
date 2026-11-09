package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import defpackage.fa0;
import defpackage.y4;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static RectF a(TabLayout tabLayout, View view) {
        if (view == null) {
            return new RectF();
        }
        if (tabLayout.l0 || !(view instanceof TabLayout.TabView)) {
            return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        TabLayout.TabView tabView = (TabLayout.TabView) view;
        int contentWidth = tabView.getContentWidth();
        int contentHeight = tabView.getContentHeight();
        int iC = (int) fa0.C(tabView.getContext(), 24);
        if (contentWidth < iC) {
            contentWidth = iC;
        }
        int right = (tabView.getRight() + tabView.getLeft()) / 2;
        int bottom = (tabView.getBottom() + tabView.getTop()) / 2;
        int i = contentWidth / 2;
        return new RectF(right - i, bottom - (contentHeight / 2), i + right, (right / 2) + bottom);
    }

    public void b(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        RectF rectFA = a(tabLayout, view);
        RectF rectFA2 = a(tabLayout, view2);
        drawable.setBounds(y4.c((int) rectFA.left, f, (int) rectFA2.left), drawable.getBounds().top, y4.c((int) rectFA.right, f, (int) rectFA2.right), drawable.getBounds().bottom);
    }
}
