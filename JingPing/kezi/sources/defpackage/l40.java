package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class l40 implements Set {
    public final /* synthetic */ int b;
    public final /* synthetic */ ux c;

    public /* synthetic */ l40(ux uxVar, int i) {
        this.b = i;
        this.c = uxVar;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.b) {
            case 0:
                ux uxVar = this.c;
                int iE = uxVar.e();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    uxVar.h(entry.getKey(), entry.getValue());
                }
                return iE != uxVar.e();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        switch (this.b) {
            case 0:
                this.c.b();
                break;
            default:
                this.c.b();
                break;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.b) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                ux uxVar = this.c;
                int iF = uxVar.f(key);
                if (iF < 0) {
                    return false;
                }
                Object objC = uxVar.c(iF, 1);
                Object value = entry.getValue();
                return objC == value || (objC != null && objC.equals(value));
            default:
                return this.c.f(obj) >= 0;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.b) {
            case 0:
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        break;
                    }
                }
                break;
            default:
                Map mapD = this.c.d();
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!mapD.containsKey(it2.next())) {
                        break;
                    }
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        switch (this.b) {
        }
        return ux.k(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        switch (this.b) {
            case 0:
                ux uxVar = this.c;
                int iHashCode = 0;
                for (int iE = uxVar.e() - 1; iE >= 0; iE--) {
                    Object objC = uxVar.c(iE, 0);
                    Object objC2 = uxVar.c(iE, 1);
                    iHashCode += (objC == null ? 0 : objC.hashCode()) ^ (objC2 == null ? 0 : objC2.hashCode());
                }
                return iHashCode;
            default:
                ux uxVar2 = this.c;
                int iHashCode2 = 0;
                for (int iE2 = uxVar2.e() - 1; iE2 >= 0; iE2--) {
                    Object objC3 = uxVar2.c(iE2, 0);
                    iHashCode2 += objC3 == null ? 0 : objC3.hashCode();
                }
                return iHashCode2;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        switch (this.b) {
            case 0:
                if (this.c.e() == 0) {
                }
                break;
            default:
                if (this.c.e() == 0) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.b) {
            case 0:
                return new m40(this.c);
            default:
                return new k40(this.c, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                ux uxVar = this.c;
                int iF = uxVar.f(obj);
                if (iF < 0) {
                    return false;
                }
                uxVar.i(iF);
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                Map mapD = this.c.d();
                int size = mapD.size();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    mapD.remove(it.next());
                }
                return size != mapD.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                Map mapD = this.c.d();
                int size = mapD.size();
                Iterator it = mapD.keySet().iterator();
                while (it.hasNext()) {
                    if (!collection.contains(it.next())) {
                        it.remove();
                    }
                }
                return size != mapD.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        switch (this.b) {
        }
        return this.c.e();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return this.c.p(objArr, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                ux uxVar = this.c;
                int iE = uxVar.e();
                Object[] objArr = new Object[iE];
                for (int i = 0; i < iE; i++) {
                    objArr[i] = uxVar.c(i, 0);
                }
                return objArr;
        }
    }
}
