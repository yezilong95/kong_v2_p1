package defpackage;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class xe0 {
    public final SparseArray a;
    public ht b;

    public xe0(int i) {
        this.a = new SparseArray(i);
    }

    public final void a(ht htVar, int i, int i2) {
        int iA = htVar.a(i);
        SparseArray sparseArray = this.a;
        xe0 xe0Var = sparseArray == null ? null : (xe0) sparseArray.get(iA);
        if (xe0Var == null) {
            xe0Var = new xe0(1);
            sparseArray.put(htVar.a(i), xe0Var);
        }
        if (i2 > i) {
            xe0Var.a(htVar, i + 1, i2);
        } else {
            xe0Var.b = htVar;
        }
    }
}
