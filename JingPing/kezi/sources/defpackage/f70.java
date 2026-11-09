package defpackage;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class f70 {
    public final SparseArray a;
    public qn b;

    public f70(int i) {
        this.a = new SparseArray(i);
    }

    public final void a(qn qnVar, int i, int i2) {
        int iA = qnVar.a(i);
        SparseArray sparseArray = this.a;
        f70 f70Var = sparseArray == null ? null : (f70) sparseArray.get(iA);
        if (f70Var == null) {
            f70Var = new f70(1);
            sparseArray.put(qnVar.a(i), f70Var);
        }
        if (i2 > i) {
            f70Var.a(qnVar, i + 1, i2);
        } else {
            f70Var.b = qnVar;
        }
    }
}
