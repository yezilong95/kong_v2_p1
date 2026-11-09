package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class dv implements Iterator, wz {
    public Object b;
    public int c = -2;
    public final /* synthetic */ ek d;

    public dv(ek ekVar) {
        this.d = ekVar;
    }

    public final void a() {
        Object objInvoke;
        int i = this.c;
        ek ekVar = this.d;
        if (i == -2) {
            objInvoke = ((ge0) ekVar.b).invoke();
        } else {
            he0 he0Var = (he0) ekVar.c;
            Object obj = this.b;
            oy.g(obj);
            objInvoke = he0Var.invoke(obj);
        }
        this.b = objInvoke;
        this.c = objInvoke == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.c < 0) {
            a();
        }
        return this.c == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.c < 0) {
            a();
        }
        if (this.c == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.b;
        oy.h(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
        this.c = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
