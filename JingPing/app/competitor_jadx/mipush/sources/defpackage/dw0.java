package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class dw0 extends mn0 {
    public boolean a = false;
    public final /* synthetic */ zj0 b;

    public dw0(zj0 zj0Var) {
        this.b = zj0Var;
    }

    @Override // defpackage.mn0
    public final void a(RecyclerView recyclerView, int i) {
        if (i == 0 && this.a) {
            this.a = false;
            this.b.h();
        }
    }

    @Override // defpackage.mn0
    public final void b(RecyclerView recyclerView, int i, int i2) {
        if (i == 0 && i2 == 0) {
            return;
        }
        this.a = true;
    }
}
