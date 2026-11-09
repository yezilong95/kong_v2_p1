package defpackage;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class i0 implements Enumeration {
    public int a = 0;
    public final /* synthetic */ j0 b;

    public i0(j0 j0Var) {
        this.b = j0Var;
    }

    @Override // java.util.Enumeration
    public final boolean hasMoreElements() {
        return this.a < this.b.E.length;
    }

    @Override // java.util.Enumeration
    public final Object nextElement() {
        int i = this.a;
        t[] tVarArr = this.b.E;
        if (i >= tVarArr.length) {
            throw new NoSuchElementException();
        }
        this.a = i + 1;
        return tVarArr[i];
    }
}
