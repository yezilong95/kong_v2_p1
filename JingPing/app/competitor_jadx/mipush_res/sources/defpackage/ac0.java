package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class ac0 implements Set {
    public final /* synthetic */ int E;
    public final /* synthetic */ j8 F;

    public /* synthetic */ ac0(j8 j8Var, int i) {
        this.E = i;
        this.F = j8Var;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.E) {
            case 0:
                j8 j8Var = this.F;
                int iD = j8Var.d();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    switch (j8Var.d) {
                        case 0:
                            ((k8) j8Var.e).put(key, value);
                            break;
                        default:
                            ((n8) j8Var.e).add(key);
                            break;
                    }
                }
                return iD != j8Var.d();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        switch (this.E) {
            case 0:
                this.F.a();
                break;
            default:
                this.F.a();
                break;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.E) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                j8 j8Var = this.F;
                int iE = j8Var.e(key);
                if (iE < 0) {
                    return false;
                }
                Object objB = j8Var.b(iE, 1);
                Object value = entry.getValue();
                return objB == value || (objB != null && objB.equals(value));
            default:
                return this.F.e(obj) >= 0;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.E) {
            case 0:
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        break;
                    }
                }
                break;
            default:
                Map mapC = this.F.c();
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!mapC.containsKey(it2.next())) {
                        break;
                    }
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        switch (this.E) {
        }
        return j8.h(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        switch (this.E) {
            case 0:
                j8 j8Var = this.F;
                int iHashCode = 0;
                for (int iD = j8Var.d() - 1; iD >= 0; iD--) {
                    Object objB = j8Var.b(iD, 0);
                    Object objB2 = j8Var.b(iD, 1);
                    iHashCode += (objB == null ? 0 : objB.hashCode()) ^ (objB2 == null ? 0 : objB2.hashCode());
                }
                return iHashCode;
            default:
                j8 j8Var2 = this.F;
                int iHashCode2 = 0;
                for (int iD2 = j8Var2.d() - 1; iD2 >= 0; iD2--) {
                    Object objB3 = j8Var2.b(iD2, 0);
                    iHashCode2 += objB3 == null ? 0 : objB3.hashCode();
                }
                return iHashCode2;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        switch (this.E) {
            case 0:
                if (this.F.d() == 0) {
                }
                break;
            default:
                if (this.F.d() == 0) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.E) {
            case 0:
                return new bc0(this.F);
            default:
                return new zb0(this.F, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                j8 j8Var = this.F;
                int iE = j8Var.e(obj);
                if (iE < 0) {
                    return false;
                }
                j8Var.g(iE);
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                Map mapC = this.F.c();
                int size = mapC.size();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    mapC.remove(it.next());
                }
                return size != mapC.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                Map mapC = this.F.c();
                int size = mapC.size();
                Iterator it = mapC.keySet().iterator();
                while (it.hasNext()) {
                    if (!collection.contains(it.next())) {
                        it.remove();
                    }
                }
                return size != mapC.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        switch (this.E) {
        }
        return this.F.d();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return this.F.i(objArr, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.E) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                j8 j8Var = this.F;
                int iD = j8Var.d();
                Object[] objArr = new Object[iD];
                for (int i = 0; i < iD; i++) {
                    objArr[i] = j8Var.b(i, 0);
                }
                return objArr;
        }
    }
}
