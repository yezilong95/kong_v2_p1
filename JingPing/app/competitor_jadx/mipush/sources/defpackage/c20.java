package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class c20 implements Iterator {
    public Object E;
    public int F = -2;
    public final /* synthetic */ wn G;

    public c20(wn wnVar) {
        this.G = wnVar;
    }

    public final void a() {
        Object objInvoke;
        int i = this.F;
        wn wnVar = this.G;
        if (i == -2) {
            objInvoke = ((go0) wnVar.b).invoke();
        } else {
            ho0 ho0Var = (ho0) wnVar.c;
            Object obj = this.E;
            t40.k(obj);
            objInvoke = ho0Var.invoke(obj);
        }
        this.E = objInvoke;
        this.F = objInvoke == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.F < 0) {
            a();
        }
        return this.F == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.F < 0) {
            a();
        }
        if (this.F == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.E;
        t40.l(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
        this.F = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
