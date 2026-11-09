package defpackage;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class t0 extends s0 implements ListIterator {
    public final /* synthetic */ v0 H;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t0(v0 v0Var, int i) {
        super(v0Var);
        this.H = v0Var;
        int iA = v0Var.a();
        if (i < 0 || i > iA) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, iA, ", size: "));
        }
        this.F = i;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.F > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.F;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i = this.F - 1;
        this.F = i;
        return this.H.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.F - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
