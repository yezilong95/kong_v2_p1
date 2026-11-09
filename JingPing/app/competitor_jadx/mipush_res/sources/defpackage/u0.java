package defpackage;

import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class u0 extends v0 implements RandomAccess {
    public final v0 E;
    public final int F;
    public final int G;

    public u0(v0 v0Var, int i, int i2) {
        this.E = v0Var;
        this.F = i;
        j31.k(i, i2, v0Var.a());
        this.G = i2 - i;
    }

    @Override // defpackage.v0
    public final int a() {
        return this.G;
    }

    @Override // java.util.List
    public final Object get(int i) {
        int i2 = this.G;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }
        return this.E.get(this.F + i);
    }
}
