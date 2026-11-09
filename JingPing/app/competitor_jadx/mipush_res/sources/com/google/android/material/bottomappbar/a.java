package com.google.android.material.bottomappbar;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$dimen;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.fa0;

/* JADX INFO: loaded from: classes.dex */
public final class a implements View.OnLayoutChangeListener {
    public final /* synthetic */ BottomAppBar.Behavior a;

    public a(BottomAppBar.Behavior behavior) {
        this.a = behavior;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        BottomAppBar.Behavior behavior = this.a;
        BottomAppBar bottomAppBar = (BottomAppBar) behavior.R.get();
        if (bottomAppBar == null || !((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton))) {
            view.removeOnLayoutChangeListener(this);
            return;
        }
        int height = view.getHeight();
        if (view instanceof FloatingActionButton) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            int measuredWidth = floatingActionButton.getMeasuredWidth();
            int measuredHeight = floatingActionButton.getMeasuredHeight();
            Rect rect = behavior.Q;
            rect.set(0, 0, measuredWidth, measuredHeight);
            floatingActionButton.j(rect);
            int iHeight = rect.height();
            bottomAppBar.H(iHeight);
            bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().e.a(new RectF(rect)));
            height = iHeight;
        }
        c cVar = (c) view.getLayoutParams();
        if (behavior.S == 0) {
            if (bottomAppBar.E0 == 1) {
                ((ViewGroup.MarginLayoutParams) cVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fab_bottom_margin) - ((view.getMeasuredHeight() - height) / 2));
            }
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = bottomAppBar.getLeftInset();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = bottomAppBar.getRightInset();
            boolean zD0 = fa0.d0(view);
            int i9 = bottomAppBar.F0;
            if (zD0) {
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin += i9;
            } else {
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin += i9;
            }
        }
        int i10 = BottomAppBar.V0;
        bottomAppBar.G();
    }
}
