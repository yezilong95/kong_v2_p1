package defpackage;

import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class k extends l implements RandomAccess {
    public final l b;
    public final int c;
    public final int d;

    public k(l lVar, int i, int i2) {
        this.b = lVar;
        this.c = i;
        za0.h(i, i2, lVar.a());
        this.d = i2 - i;
    }

    @Override // defpackage.l
    public final int a() {
        return this.d;
    }

    @Override // java.util.List
    public final Object get(int i) {
        int i2 = this.d;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }
        return this.b.get(this.c + i);
    }
}
