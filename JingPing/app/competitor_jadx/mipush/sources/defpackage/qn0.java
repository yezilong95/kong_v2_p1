package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class qn0 extends en0 {
    public final /* synthetic */ RecyclerView a;

    public qn0(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    @Override // defpackage.en0
    public final void a() {
        RecyclerView recyclerView = this.a;
        recyclerView.h(null);
        recyclerView.H0.f = true;
        recyclerView.T(true);
        if (recyclerView.H.h()) {
            return;
        }
        recyclerView.requestLayout();
    }
}
