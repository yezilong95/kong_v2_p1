package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class k40 implements Iterator {
    public final int b;
    public int c;
    public int d;
    public boolean e = false;
    public final /* synthetic */ ux f;

    public k40(ux uxVar, int i) {
        this.f = uxVar;
        this.b = i;
        this.c = uxVar.e();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d < this.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objC = this.f.c(this.d, this.b);
        this.d++;
        this.e = true;
        return objC;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.e) {
            throw new IllegalStateException();
        }
        int i = this.d - 1;
        this.d = i;
        this.c--;
        this.e = false;
        this.f.i(i);
    }
}
