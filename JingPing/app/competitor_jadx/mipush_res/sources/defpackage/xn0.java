package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;

/* JADX INFO: loaded from: classes.dex */
public class xn0 extends y1 {
    public final RecyclerView d;
    public final tp e;

    public xn0(RecyclerView recyclerView) {
        this.d = recyclerView;
        tp tpVar = this.e;
        if (tpVar != null) {
            this.e = tpVar;
        } else {
            this.e = new tp(this);
        }
    }

    @Override // defpackage.y1
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || this.d.K()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().T(accessibilityEvent);
        }
    }

    @Override // defpackage.y1
    public void d(View view, j2 j2Var) {
        this.a.onInitializeAccessibilityNodeInfo(view, j2Var.a);
        RecyclerView recyclerView = this.d;
        if (recyclerView.K() || recyclerView.getLayoutManager() == null) {
            return;
        }
        k layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.b;
        layoutManager.U(recyclerView2.F, recyclerView2.H0, j2Var);
    }

    @Override // defpackage.y1
    public final boolean g(View view, int i, Bundle bundle) {
        if (super.g(view, i, bundle)) {
            return true;
        }
        RecyclerView recyclerView = this.d;
        if (recyclerView.K() || recyclerView.getLayoutManager() == null) {
            return false;
        }
        k layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.b;
        return layoutManager.h0(recyclerView2.F, recyclerView2.H0, i, bundle);
    }
}
