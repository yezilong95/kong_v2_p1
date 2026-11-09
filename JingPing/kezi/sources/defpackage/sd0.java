package defpackage;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class sd0 {
    public SparseArray a;
    public int b;

    public final rd0 a(int i) {
        SparseArray sparseArray = this.a;
        rd0 rd0Var = (rd0) sparseArray.get(i);
        if (rd0Var != null) {
            return rd0Var;
        }
        rd0 rd0Var2 = new rd0();
        sparseArray.put(i, rd0Var2);
        return rd0Var2;
    }
}
