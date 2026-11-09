package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class p11 implements Iterator {
    public final Iterator E;
    public final /* synthetic */ wn F;

    public p11(wn wnVar) {
        this.F = wnVar;
        this.E = new vn((wn) wnVar.b);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.E.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return ((p0) this.F.c).invoke(this.E.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
