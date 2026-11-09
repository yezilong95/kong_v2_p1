package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;

/* JADX INFO: loaded from: classes.dex */
public class be0 extends b0 {
    public final RecyclerView d;
    public final ql e;

    public be0(RecyclerView recyclerView) {
        this.d = recyclerView;
        ql qlVar = this.e;
        if (qlVar != null) {
            this.e = qlVar;
        } else {
            this.e = new ql(this);
        }
    }

    @Override // defpackage.b0
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || this.d.K()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().Q(accessibilityEvent);
        }
    }

    @Override // defpackage.b0
    public void d(View view, o0 o0Var) {
        this.a.onInitializeAccessibilityNodeInfo(view, o0Var.a);
        RecyclerView recyclerView = this.d;
        if (recyclerView.K() || recyclerView.getLayoutManager() == null) {
            return;
        }
        k layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.b;
        layoutManager.R(recyclerView2.c, recyclerView2.e0, o0Var);
    }

    @Override // defpackage.b0
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
        return layoutManager.e0(recyclerView2.c, recyclerView2.e0, i, bundle);
    }
}
