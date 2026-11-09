package defpackage;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class on0 {
    public SparseArray a;
    public int b;

    public final nn0 a(int i) {
        SparseArray sparseArray = this.a;
        nn0 nn0Var = (nn0) sparseArray.get(i);
        if (nn0Var != null) {
            return nn0Var;
        }
        nn0 nn0Var2 = new nn0();
        sparseArray.put(i, nn0Var2);
        return nn0Var2;
    }
}
