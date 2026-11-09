package defpackage;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class n40 implements Collection {
    public final /* synthetic */ ux b;

    public n40(ux uxVar) {
        this.b = uxVar;
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
        this.b.b();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.b.g(obj) >= 0;
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
        return this.b.e() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new k40(this.b, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        ux uxVar = this.b;
        int iG = uxVar.g(obj);
        if (iG < 0) {
            return false;
        }
        uxVar.i(iG);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        ux uxVar = this.b;
        int iE = uxVar.e();
        int i = 0;
        boolean z = false;
        while (i < iE) {
            if (collection.contains(uxVar.c(i, 1))) {
                uxVar.i(i);
                i--;
                iE--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        ux uxVar = this.b;
        int iE = uxVar.e();
        int i = 0;
        boolean z = false;
        while (i < iE) {
            if (!collection.contains(uxVar.c(i, 1))) {
                uxVar.i(i);
                i--;
                iE--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.b.e();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        ux uxVar = this.b;
        int iE = uxVar.e();
        Object[] objArr = new Object[iE];
        for (int i = 0; i < iE; i++) {
            objArr[i] = uxVar.c(i, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.p(objArr, 1);
    }
}
