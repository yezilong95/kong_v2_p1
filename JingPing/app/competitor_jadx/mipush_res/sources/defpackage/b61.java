package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class b61 extends LinearLayoutManager {
    public final /* synthetic */ ViewPager2 E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b61(ViewPager2 viewPager2) {
        super(1);
        this.E = viewPager2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void C0(un0 un0Var, int[] iArr) {
        ViewPager2 viewPager2 = this.E;
        int offscreenPageLimit = viewPager2.getOffscreenPageLimit();
        if (offscreenPageLimit == -1) {
            super.C0(un0Var, iArr);
            return;
        }
        int pageSize = viewPager2.getPageSize() * offscreenPageLimit;
        iArr[0] = pageSize;
        iArr[1] = pageSize;
    }

    @Override // androidx.recyclerview.widget.k
    public final void U(l lVar, un0 un0Var, j2 j2Var) {
        super.U(lVar, un0Var, j2Var);
        this.E.a0.getClass();
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean h0(l lVar, un0 un0Var, int i, Bundle bundle) {
        this.E.a0.getClass();
        return super.h0(lVar, un0Var, i, bundle);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean m0(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        return false;
    }
}
