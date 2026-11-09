package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class us0 extends LinearLayoutManager {
    public final /* synthetic */ ViewPager2 E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public us0(ViewPager2 viewPager2) {
        super(1);
        this.E = viewPager2;
    }

    @Override // androidx.recyclerview.widget.k
    public final void R(l lVar, yd0 yd0Var, o0 o0Var) {
        super.R(lVar, yd0Var, o0Var);
        this.E.u.getClass();
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean e0(l lVar, yd0 yd0Var, int i, Bundle bundle) {
        this.E.u.getClass();
        return super.e0(lVar, yd0Var, i, bundle);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean j0(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        return false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void z0(yd0 yd0Var, int[] iArr) {
        ViewPager2 viewPager2 = this.E;
        int offscreenPageLimit = viewPager2.getOffscreenPageLimit();
        if (offscreenPageLimit == -1) {
            super.z0(yd0Var, iArr);
            return;
        }
        int pageSize = viewPager2.getPageSize() * offscreenPageLimit;
        iArr[0] = pageSize;
        iArr[1] = pageSize;
    }
}
