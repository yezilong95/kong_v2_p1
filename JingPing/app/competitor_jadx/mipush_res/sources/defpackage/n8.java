package defpackage;

import io.nats.client.support.JsonUtils;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class n8 implements Collection, Set {
    public static final int[] I = new int[0];
    public static final Object[] J = new Object[0];
    public static Object[] K;
    public static int L;
    public static Object[] M;
    public static int N;
    public int[] E;
    public Object[] F;
    public int G;
    public j8 H;

    public n8(int i) {
        if (i == 0) {
            this.E = I;
            this.F = J;
        } else {
            a(i);
        }
        this.G = 0;
    }

    public static void b(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (n8.class) {
                try {
                    if (N < 10) {
                        objArr[0] = M;
                        objArr[1] = iArr;
                        for (int i2 = i - 1; i2 >= 2; i2--) {
                            objArr[i2] = null;
                        }
                        M = objArr;
                        N++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (n8.class) {
                try {
                    if (L < 10) {
                        objArr[0] = K;
                        objArr[1] = iArr;
                        for (int i3 = i - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        K = objArr;
                        L++;
                    }
                } finally {
                }
            }
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (n8.class) {
                try {
                    Object[] objArr = M;
                    if (objArr != null) {
                        this.F = objArr;
                        M = (Object[]) objArr[0];
                        this.E = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        N--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i == 4) {
            synchronized (n8.class) {
                try {
                    Object[] objArr2 = K;
                    if (objArr2 != null) {
                        this.F = objArr2;
                        K = (Object[]) objArr2[0];
                        this.E = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        L--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.E = new int[i];
        this.F = new Object[i];
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i;
        int iC;
        if (obj == null) {
            iC = d();
            i = 0;
        } else {
            int iHashCode = obj.hashCode();
            i = iHashCode;
            iC = c(iHashCode, obj);
        }
        if (iC >= 0) {
            return false;
        }
        int i2 = ~iC;
        int i3 = this.G;
        int[] iArr = this.E;
        if (i3 >= iArr.length) {
            int i4 = 8;
            if (i3 >= 8) {
                i4 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i4 = 4;
            }
            Object[] objArr = this.F;
            a(i4);
            int[] iArr2 = this.E;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.F, 0, objArr.length);
            }
            b(iArr, objArr, this.G);
        }
        int i5 = this.G;
        if (i2 < i5) {
            int[] iArr3 = this.E;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr2 = this.F;
            System.arraycopy(objArr2, i2, objArr2, i6, this.G - i2);
        }
        this.E[i2] = i;
        this.F[i2] = obj;
        this.G++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        int size = collection.size() + this.G;
        int[] iArr = this.E;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.F;
            a(size);
            int i = this.G;
            if (i > 0) {
                System.arraycopy(iArr, 0, this.E, 0, i);
                System.arraycopy(objArr, 0, this.F, 0, this.G);
            }
            b(iArr, objArr, this.G);
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public final int c(int i, Object obj) {
        int i2 = this.G;
        if (i2 == 0) {
            return -1;
        }
        int iG = z91.g(i2, i, this.E);
        if (iG < 0 || obj.equals(this.F[iG])) {
            return iG;
        }
        int i3 = iG + 1;
        while (i3 < i2 && this.E[i3] == i) {
            if (obj.equals(this.F[i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iG - 1; i4 >= 0 && this.E[i4] == i; i4--) {
            if (obj.equals(this.F[i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        int i = this.G;
        if (i != 0) {
            b(this.E, this.F, i);
            this.E = I;
            this.F = J;
            this.G = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? d() : c(obj.hashCode(), obj)) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int d() {
        int i = this.G;
        if (i == 0) {
            return -1;
        }
        int iG = z91.g(i, 0, this.E);
        if (iG < 0 || this.F[iG] == null) {
            return iG;
        }
        int i2 = iG + 1;
        while (i2 < i && this.E[i2] == 0) {
            if (this.F[i2] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iG - 1; i3 >= 0 && this.E[i3] == 0; i3--) {
            if (this.F[i3] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final void e(int i) {
        Object[] objArr = this.F;
        Object obj = objArr[i];
        int i2 = this.G;
        if (i2 <= 1) {
            b(this.E, objArr, i2);
            this.E = I;
            this.F = J;
            this.G = 0;
            return;
        }
        int[] iArr = this.E;
        if (iArr.length <= 8 || i2 >= iArr.length / 3) {
            int i3 = i2 - 1;
            this.G = i3;
            if (i < i3) {
                int i4 = i + 1;
                System.arraycopy(iArr, i4, iArr, i, i3 - i);
                Object[] objArr2 = this.F;
                System.arraycopy(objArr2, i4, objArr2, i, this.G - i);
            }
            this.F[this.G] = null;
            return;
        }
        a(i2 > 8 ? i2 + (i2 >> 1) : 8);
        this.G--;
        if (i > 0) {
            System.arraycopy(iArr, 0, this.E, 0, i);
            System.arraycopy(objArr, 0, this.F, 0, i);
        }
        int i5 = this.G;
        if (i < i5) {
            int i6 = i + 1;
            System.arraycopy(iArr, i6, this.E, i, i5 - i);
            System.arraycopy(objArr, i6, this.F, i, this.G - i);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.G != set.size()) {
                return false;
            }
            for (int i = 0; i < this.G; i++) {
                try {
                    if (!set.contains(this.F[i])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.E;
        int i = this.G;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.G <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        if (this.H == null) {
            this.H = new j8(1, this);
        }
        j8 j8Var = this.H;
        if (j8Var.b == null) {
            j8Var.b = new ac0(j8Var, 1);
        }
        return j8Var.b.iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iD = obj == null ? d() : c(obj.hashCode(), obj);
        if (iD < 0) {
            return false;
        }
        e(iD);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        boolean z = false;
        for (int i = this.G - 1; i >= 0; i--) {
            if (!collection.contains(this.F[i])) {
                e(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.G;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        int i = this.G;
        Object[] objArr = new Object[i];
        System.arraycopy(this.F, 0, objArr, 0, i);
        return objArr;
    }

    public final String toString() {
        if (isEmpty()) {
            return JsonUtils.EMPTY_JSON;
        }
        StringBuilder sb = new StringBuilder(this.G * 14);
        sb.append('{');
        for (int i = 0; i < this.G; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.F[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        if (objArr.length < this.G) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.G);
        }
        System.arraycopy(this.F, 0, objArr, 0, this.G);
        int length = objArr.length;
        int i = this.G;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }
}
