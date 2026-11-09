package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class zb0 implements Iterator {
    public final int E;
    public int F;
    public int G;
    public boolean H = false;
    public final /* synthetic */ j8 I;

    public zb0(j8 j8Var, int i) {
        this.I = j8Var;
        this.E = i;
        this.F = j8Var.d();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.G < this.F;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objB = this.I.b(this.G, this.E);
        this.G++;
        this.H = true;
        return objB;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.H) {
            throw new IllegalStateException();
        }
        int i = this.G - 1;
        this.G = i;
        this.F--;
        this.H = false;
        this.I.g(i);
    }
}
