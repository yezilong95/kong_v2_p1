package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class vn implements Iterator {
    public int E = -1;
    public int F;
    public int G;
    public q40 H;
    public final /* synthetic */ wn I;

    public vn(wn wnVar) {
        this.I = wnVar;
        wnVar.getClass();
        int length = ((String) wnVar.b).length();
        if (length < 0) {
            throw new IllegalArgumentException(zn.k(length, "Cannot coerce value to an empty range: maximum ", " is less than minimum 0."));
        }
        length = length >= 0 ? 0 : length;
        this.F = length;
        this.G = length;
    }

    public final void a() {
        Pair pair;
        int i = this.G;
        if (i < 0) {
            this.E = 0;
            this.H = null;
            return;
        }
        wn wnVar = this.I;
        wnVar.getClass();
        String str = (String) wnVar.b;
        if (i <= str.length() && (pair = (Pair) ((sq0) wnVar.c).invoke(str, Integer.valueOf(this.G))) != null) {
            int iIntValue = ((Number) pair.component1()).intValue();
            int iIntValue2 = ((Number) pair.component2()).intValue();
            this.H = iIntValue <= Integer.MIN_VALUE ? q40.H : new q40(this.F, iIntValue - 1, 1);
            int i2 = iIntValue + iIntValue2;
            this.F = i2;
            this.G = i2 + (iIntValue2 == 0 ? 1 : 0);
        } else {
            this.H = new q40(this.F, qx0.l0(str), 1);
            this.G = -1;
        }
        this.E = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.E == -1) {
            a();
        }
        return this.E == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.E == -1) {
            a();
        }
        if (this.E == 0) {
            throw new NoSuchElementException();
        }
        q40 q40Var = this.H;
        t40.l(q40Var, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.H = null;
        this.E = -1;
        return q40Var;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
