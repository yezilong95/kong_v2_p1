package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class bc0 implements Iterator, Map.Entry {
    public int E;
    public final /* synthetic */ j8 H;
    public boolean G = false;
    public int F = -1;

    public bc0(j8 j8Var) {
        this.H = j8Var;
        this.E = j8Var.d() - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.G) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i = this.F;
        j8 j8Var = this.H;
        Object objB = j8Var.b(i, 0);
        if (key != objB && (key == null || !key.equals(objB))) {
            return false;
        }
        Object value = entry.getValue();
        Object objB2 = j8Var.b(this.F, 1);
        return value == objB2 || (value != null && value.equals(objB2));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (!this.G) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.H.b(this.F, 0);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (!this.G) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.H.b(this.F, 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.F < this.E;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.G) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i = this.F;
        j8 j8Var = this.H;
        Object objB = j8Var.b(i, 0);
        Object objB2 = j8Var.b(this.F, 1);
        return (objB == null ? 0 : objB.hashCode()) ^ (objB2 != null ? objB2.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.F++;
        this.G = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.G) {
            throw new IllegalStateException();
        }
        this.H.g(this.F);
        this.F--;
        this.E--;
        this.G = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (!this.G) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        j8 j8Var = this.H;
        int i = this.F;
        switch (j8Var.d) {
            case 0:
                int i2 = (i << 1) + 1;
                Object[] objArr = ((k8) j8Var.e).F;
                Object obj2 = objArr[i2];
                objArr[i2] = obj;
                return obj2;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
