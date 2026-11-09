package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class s0 implements Iterator {
    public final /* synthetic */ int E;
    public int F;
    public final Object G;

    public s0(Object[] objArr) {
        this.E = 1;
        this.F = 0;
        this.G = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.E) {
            case 0:
                if (this.F < ((v0) this.G).a()) {
                }
                break;
            default:
                if (this.F < ((Object[]) this.G).length) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.E) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i = this.F;
                this.F = i + 1;
                return ((v0) this.G).get(i);
            default:
                int i2 = this.F;
                Object[] objArr = (Object[]) this.G;
                if (i2 != objArr.length) {
                    this.F = i2 + 1;
                    return objArr[i2];
                }
                throw new NoSuchElementException("Out of elements: " + this.F);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Cannot remove element from an Array.");
        }
    }

    public s0(v0 v0Var) {
        this.E = 0;
        this.G = v0Var;
    }
}
