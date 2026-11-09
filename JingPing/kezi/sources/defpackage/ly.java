package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class ly implements Iterator, wz {
    public final int b;
    public final int c;
    public boolean d;
    public int e;

    public ly(int i, int i2, int i3) {
        this.b = i3;
        this.c = i2;
        boolean z = false;
        if (i3 <= 0 ? i >= i2 : i <= i2) {
            z = true;
        }
        this.d = z;
        this.e = z ? i : i2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.e;
        if (i != this.c) {
            this.e = this.b + i;
        } else {
            if (!this.d) {
                throw new NoSuchElementException();
            }
            this.d = false;
        }
        return Integer.valueOf(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
