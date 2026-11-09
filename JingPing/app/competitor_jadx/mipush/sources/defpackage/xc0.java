package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.l;

/* JADX INFO: loaded from: classes.dex */
public final class xc0 extends LinearLayoutManager {
    public final /* synthetic */ int E;
    public final /* synthetic */ l F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public xc0(l lVar, int i, int i2) {
        super(i);
        this.F = lVar;
        this.E = i2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void C0(un0 un0Var, int[] iArr) {
        int i = this.E;
        l lVar = this.F;
        if (i == 0) {
            iArr[0] = lVar.E0.getWidth();
            iArr[1] = lVar.E0.getWidth();
        } else {
            iArr[0] = lVar.E0.getHeight();
            iArr[1] = lVar.E0.getHeight();
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final void z0(RecyclerView recyclerView, int i) {
        aw0 aw0Var = new aw0(recyclerView.getContext());
        aw0Var.a = i;
        A0(aw0Var);
    }
}
