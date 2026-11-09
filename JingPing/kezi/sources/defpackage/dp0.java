package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class dp0 implements Iterator, wz {
    public final Iterator b;
    public final /* synthetic */ ek c;

    public dp0(ek ekVar) {
        this.c = ekVar;
        this.b = new dk((ek) ekVar.b);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return ((f) this.c.c).invoke(this.b.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
