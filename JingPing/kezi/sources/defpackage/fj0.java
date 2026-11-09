package defpackage;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class fj0 {
    public static Object[] e;
    public static int f;
    public static Object[] g;
    public static int h;
    public int[] b;
    public Object[] c;
    public int d;

    public fj0() {
        this.b = xi.g;
        this.c = xi.h;
        this.d = 0;
    }

    public static void c(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (fj0.class) {
                try {
                    if (h < 10) {
                        objArr[0] = g;
                        objArr[1] = iArr;
                        for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                            objArr[i2] = null;
                        }
                        g = objArr;
                        h++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (fj0.class) {
                try {
                    if (f < 10) {
                        objArr[0] = e;
                        objArr[1] = iArr;
                        for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        e = objArr;
                        f++;
                    }
                } finally {
                }
            }
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (fj0.class) {
                try {
                    Object[] objArr = g;
                    if (objArr != null) {
                        this.c = objArr;
                        g = (Object[]) objArr[0];
                        this.b = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        h--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i == 4) {
            synchronized (fj0.class) {
                try {
                    Object[] objArr2 = e;
                    if (objArr2 != null) {
                        this.c = objArr2;
                        e = (Object[]) objArr2[0];
                        this.b = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.b = new int[i];
        this.c = new Object[i << 1];
    }

    public final void b(int i) {
        int i2 = this.d;
        int[] iArr = this.b;
        if (iArr.length < i) {
            Object[] objArr = this.c;
            a(i);
            if (this.d > 0) {
                System.arraycopy(iArr, 0, this.b, 0, i2);
                System.arraycopy(objArr, 0, this.c, 0, i2 << 1);
            }
            c(iArr, objArr, i2);
        }
        if (this.d != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        int i = this.d;
        if (i > 0) {
            int[] iArr = this.b;
            Object[] objArr = this.c;
            this.b = xi.g;
            this.c = xi.h;
            this.d = 0;
            c(iArr, objArr, i);
        }
        if (this.d > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public final int d(int i, Object obj) {
        int i2 = this.d;
        if (i2 == 0) {
            return -1;
        }
        try {
            int iD = xi.d(i2, i, this.b);
            if (iD < 0 || obj.equals(this.c[iD << 1])) {
                return iD;
            }
            int i3 = iD + 1;
            while (i3 < i2 && this.b[i3] == i) {
                if (obj.equals(this.c[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = iD - 1; i4 >= 0 && this.b[i4] == i; i4--) {
                if (obj.equals(this.c[i4 << 1])) {
                    return i4;
                }
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof fj0) {
            fj0 fj0Var = (fj0) obj;
            if (this.d != fj0Var.d) {
                return false;
            }
            for (int i = 0; i < this.d; i++) {
                try {
                    Object objH = h(i);
                    Object objJ = j(i);
                    Object orDefault = fj0Var.getOrDefault(objH, null);
                    if (objJ == null) {
                        if (orDefault != null || !fj0Var.containsKey(objH)) {
                            return false;
                        }
                    } else if (!objJ.equals(orDefault)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.d != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.d; i2++) {
                try {
                    Object objH2 = h(i2);
                    Object objJ2 = j(i2);
                    Object obj2 = map.get(objH2);
                    if (objJ2 == null) {
                        if (obj2 != null || !map.containsKey(objH2)) {
                            return false;
                        }
                    } else if (!objJ2.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        int i = this.d;
        if (i == 0) {
            return -1;
        }
        try {
            int iD = xi.d(i, 0, this.b);
            if (iD < 0 || this.c[iD << 1] == null) {
                return iD;
            }
            int i2 = iD + 1;
            while (i2 < i && this.b[i2] == 0) {
                if (this.c[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = iD - 1; i3 >= 0 && this.b[i3] == 0; i3--) {
                if (this.c[i3 << 1] == null) {
                    return i3;
                }
            }
            return ~i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int g(Object obj) {
        int i = this.d * 2;
        Object[] objArr = this.c;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public final Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int iE = e(obj);
        return iE >= 0 ? this.c[(iE << 1) + 1] : obj2;
    }

    public final Object h(int i) {
        return this.c[i << 1];
    }

    public final int hashCode() {
        int[] iArr = this.b;
        Object[] objArr = this.c;
        int i = this.d;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public final Object i(int i) {
        Object[] objArr = this.c;
        int i2 = i << 1;
        Object obj = objArr[i2 + 1];
        int i3 = this.d;
        int i4 = 0;
        if (i3 <= 1) {
            c(this.b, objArr, i3);
            this.b = xi.g;
            this.c = xi.h;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.b;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i5) {
                    int i6 = i + 1;
                    int i7 = i5 - i;
                    System.arraycopy(iArr, i6, iArr, i, i7);
                    Object[] objArr2 = this.c;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i2, i7 << 1);
                }
                Object[] objArr3 = this.c;
                int i8 = i5 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                a(i3 > 8 ? i3 + (i3 >> 1) : 8);
                if (i3 != this.d) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.b, 0, i);
                    System.arraycopy(objArr, 0, this.c, 0, i2);
                }
                if (i < i5) {
                    int i9 = i + 1;
                    int i10 = i5 - i;
                    System.arraycopy(iArr, i9, this.b, i, i10);
                    System.arraycopy(objArr, i9 << 1, this.c, i2, i10 << 1);
                }
            }
            i4 = i5;
        }
        if (i3 != this.d) {
            throw new ConcurrentModificationException();
        }
        this.d = i4;
        return obj;
    }

    public final boolean isEmpty() {
        return this.d <= 0;
    }

    public final Object j(int i) {
        return this.c[(i << 1) + 1];
    }

    public final Object put(Object obj, Object obj2) {
        int i;
        int iD;
        int i2 = this.d;
        if (obj == null) {
            iD = f();
            i = 0;
        } else {
            int iHashCode = obj.hashCode();
            i = iHashCode;
            iD = d(iHashCode, obj);
        }
        if (iD >= 0) {
            int i3 = (iD << 1) + 1;
            Object[] objArr = this.c;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = ~iD;
        int[] iArr = this.b;
        if (i2 >= iArr.length) {
            int i5 = 8;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i5 = 4;
            }
            Object[] objArr2 = this.c;
            a(i5);
            if (i2 != this.d) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.b;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.c, 0, objArr2.length);
            }
            c(iArr, objArr2, i2);
        }
        if (i4 < i2) {
            int[] iArr3 = this.b;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.d - i4) << 1);
        }
        int i7 = this.d;
        if (i2 == i7) {
            int[] iArr4 = this.b;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.c;
                int i8 = i4 << 1;
                objArr4[i8] = obj;
                objArr4[i8 + 1] = obj2;
                this.d = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object orDefault = getOrDefault(obj, null);
        return orDefault == null ? put(obj, obj2) : orDefault;
    }

    public final Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public final Object replace(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0) {
            return null;
        }
        int i = (iE << 1) + 1;
        Object[] objArr = this.c;
        Object obj3 = objArr[i];
        objArr[i] = obj2;
        return obj3;
    }

    public final int size() {
        return this.d;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.d * 28);
        sb.append('{');
        for (int i = 0; i < this.d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object objH = h(i);
            if (objH != this) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objJ = j(i);
            if (objJ != this) {
                sb.append(objJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public final boolean remove(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        Object objJ = j(iE);
        if (obj2 != objJ && (obj2 == null || !obj2.equals(objJ))) {
            return false;
        }
        i(iE);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        Object objJ = j(iE);
        if (objJ != obj2 && (obj2 == null || !obj2.equals(objJ))) {
            return false;
        }
        int i = (iE << 1) + 1;
        Object[] objArr = this.c;
        Object obj4 = objArr[i];
        objArr[i] = obj3;
        return true;
    }

    public fj0(int i) {
        if (i == 0) {
            this.b = xi.g;
            this.c = xi.h;
        } else {
            a(i);
        }
        this.d = 0;
    }
}
