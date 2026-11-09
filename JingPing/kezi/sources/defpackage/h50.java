package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.l;

/* JADX INFO: loaded from: classes.dex */
public final class h50 extends LinearLayoutManager {
    public final /* synthetic */ int E;
    public final /* synthetic */ l F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h50(l lVar, int i, int i2) {
        super(i);
        this.F = lVar;
        this.E = i2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final void w0(RecyclerView recyclerView, int i) {
        lj0 lj0Var = new lj0(recyclerView.getContext());
        lj0Var.a = i;
        x0(lj0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void z0(yd0 yd0Var, int[] iArr) {
        int i = this.E;
        l lVar = this.F;
        if (i == 0) {
            iArr[0] = lVar.b0.getWidth();
            iArr[1] = lVar.b0.getWidth();
        } else {
            iArr[0] = lVar.b0.getHeight();
            iArr[1] = lVar.b0.getHeight();
        }
    }
}
