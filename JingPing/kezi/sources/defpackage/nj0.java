package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class nj0 extends qd0 {
    public boolean a = false;
    public final /* synthetic */ fb0 b;

    public nj0(fb0 fb0Var) {
        this.b = fb0Var;
    }

    @Override // defpackage.qd0
    public final void a(RecyclerView recyclerView, int i) {
        if (i == 0 && this.a) {
            this.a = false;
            this.b.h();
        }
    }

    @Override // defpackage.qd0
    public final void b(RecyclerView recyclerView, int i, int i2) {
        if (i == 0 && i2 == 0) {
            return;
        }
        this.a = true;
    }
}
