package com.google.android.material.appbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.e2;
import defpackage.j2;
import defpackage.y1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends y1 {
    public final /* synthetic */ AppBarLayout d;
    public final /* synthetic */ CoordinatorLayout e;
    public final /* synthetic */ AppBarLayout.BaseBehavior f;

    public b(CoordinatorLayout coordinatorLayout, AppBarLayout.BaseBehavior baseBehavior, AppBarLayout appBarLayout) {
        this.f = baseBehavior;
        this.d = appBarLayout;
        this.e = coordinatorLayout;
    }

    @Override // defpackage.y1
    public final void d(View view, j2 j2Var) {
        AppBarLayout.BaseBehavior baseBehavior;
        View viewG;
        this.a.onInitializeAccessibilityNodeInfo(view, j2Var.a);
        j2Var.i(ScrollView.class.getName());
        AppBarLayout appBarLayout = this.d;
        if (appBarLayout.getTotalScrollRange() == 0 || (viewG = AppBarLayout.BaseBehavior.G((baseBehavior = this.f), this.e)) == null) {
            return;
        }
        int childCount = appBarLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((AppBarLayout.LayoutParams) appBarLayout.getChildAt(i).getLayoutParams()).a != 0) {
                if (baseBehavior.x() != (-appBarLayout.getTotalScrollRange())) {
                    j2Var.b(e2.h);
                    j2Var.l(true);
                }
                if (baseBehavior.x() != 0) {
                    if (!viewG.canScrollVertically(-1)) {
                        j2Var.b(e2.i);
                        j2Var.l(true);
                        return;
                    } else {
                        if ((-appBarLayout.getDownNestedPreScrollRange()) != 0) {
                            j2Var.b(e2.i);
                            j2Var.l(true);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
        }
    }

    @Override // defpackage.y1
    public final boolean g(View view, int i, Bundle bundle) {
        AppBarLayout appBarLayout = this.d;
        if (i == 4096) {
            appBarLayout.setExpanded(false);
            return true;
        }
        if (i != 8192) {
            return super.g(view, i, bundle);
        }
        AppBarLayout.BaseBehavior baseBehavior = this.f;
        if (baseBehavior.x() != 0) {
            View viewG = AppBarLayout.BaseBehavior.G(baseBehavior, this.e);
            if (!viewG.canScrollVertically(-1)) {
                appBarLayout.setExpanded(true);
                return true;
            }
            int i2 = -appBarLayout.getDownNestedPreScrollRange();
            if (i2 != 0) {
                CoordinatorLayout coordinatorLayout = this.e;
                AppBarLayout appBarLayout2 = this.d;
                this.f.J(coordinatorLayout, appBarLayout2, viewG, i2, new int[]{0, 0});
                return true;
            }
        }
        return false;
    }
}
