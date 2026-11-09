package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class ud0 extends id0 {
    public final /* synthetic */ RecyclerView a;

    public ud0(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    @Override // defpackage.id0
    public final void a() {
        RecyclerView recyclerView = this.a;
        recyclerView.h(null);
        recyclerView.e0.f = true;
        recyclerView.T(true);
        if (recyclerView.e.i()) {
            return;
        }
        recyclerView.requestLayout();
    }
}
