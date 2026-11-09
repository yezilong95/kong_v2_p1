package defpackage;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class cc0 implements Collection {
    public final /* synthetic */ j8 E;

    public cc0(j8 j8Var) {
        this.E = j8Var;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.E.a();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.E.f(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.E.d() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new zb0(this.E, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        j8 j8Var = this.E;
        int iF = j8Var.f(obj);
        if (iF < 0) {
            return false;
        }
        j8Var.g(iF);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        j8 j8Var = this.E;
        int iD = j8Var.d();
        int i = 0;
        boolean z = false;
        while (i < iD) {
            if (collection.contains(j8Var.b(i, 1))) {
                j8Var.g(i);
                i--;
                iD--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        j8 j8Var = this.E;
        int iD = j8Var.d();
        int i = 0;
        boolean z = false;
        while (i < iD) {
            if (!collection.contains(j8Var.b(i, 1))) {
                j8Var.g(i);
                i--;
                iD--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.E.d();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        j8 j8Var = this.E;
        int iD = j8Var.d();
        Object[] objArr = new Object[iD];
        for (int i = 0; i < iD; i++) {
            objArr[i] = j8Var.b(i, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.E.i(objArr, 1);
    }
}
