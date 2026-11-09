package defpackage;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class j8 {
    public ac0 a;
    public ac0 b;
    public cc0 c;
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ j8(int i, Object obj) {
        this.d = i;
        this.e = obj;
    }

    public static boolean h(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() == set2.size()) {
                return set.containsAll(set2);
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final void a() {
        switch (this.d) {
            case 0:
                ((k8) this.e).clear();
                break;
            default:
                ((n8) this.e).clear();
                break;
        }
    }

    public final Object b(int i, int i2) {
        switch (this.d) {
            case 0:
                return ((k8) this.e).F[(i << 1) + i2];
            default:
                return ((n8) this.e).F[i];
        }
    }

    public final Map c() {
        switch (this.d) {
            case 0:
                return (k8) this.e;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    public final int d() {
        switch (this.d) {
            case 0:
                return ((k8) this.e).G;
            default:
                return ((n8) this.e).G;
        }
    }

    public final int e(Object obj) {
        switch (this.d) {
            case 0:
                return ((k8) this.e).e(obj);
            default:
                n8 n8Var = (n8) this.e;
                return obj == null ? n8Var.d() : n8Var.c(obj.hashCode(), obj);
        }
    }

    public final int f(Object obj) {
        switch (this.d) {
            case 0:
                return ((k8) this.e).g(obj);
            default:
                n8 n8Var = (n8) this.e;
                return obj == null ? n8Var.d() : n8Var.c(obj.hashCode(), obj);
        }
    }

    public final void g(int i) {
        switch (this.d) {
            case 0:
                ((k8) this.e).i(i);
                break;
            default:
                ((n8) this.e).e(i);
                break;
        }
    }

    public final Object[] i(Object[] objArr, int i) {
        int iD = d();
        if (objArr.length < iD) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), iD);
        }
        for (int i2 = 0; i2 < iD; i2++) {
            objArr[i2] = b(i2, i);
        }
        if (objArr.length > iD) {
            objArr[iD] = null;
        }
        return objArr;
    }
}
