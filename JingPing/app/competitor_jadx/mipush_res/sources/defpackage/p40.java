package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class p40 implements Iterator {
    public final int E;
    public final int F;
    public boolean G;
    public int H;

    public p40(int i, int i2, int i3) {
        this.E = i3;
        this.F = i2;
        boolean z = false;
        if (i3 <= 0 ? i >= i2 : i <= i2) {
            z = true;
        }
        this.G = z;
        this.H = z ? i : i2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.G;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.H;
        if (i != this.F) {
            this.H = this.E + i;
        } else {
            if (!this.G) {
                throw new NoSuchElementException();
            }
            this.G = false;
        }
        return Integer.valueOf(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
