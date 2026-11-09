package defpackage;

import io.nats.client.support.JsonUtils;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class vv0 {
    public static Object[] H;
    public static int I;
    public static Object[] J;
    public static int K;
    public int[] E;
    public Object[] F;
    public int G;

    public vv0() {
        this.E = z91.I;
        this.F = z91.J;
        this.G = 0;
    }

    public static void c(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (vv0.class) {
                try {
                    if (K < 10) {
                        objArr[0] = J;
                        objArr[1] = iArr;
                        for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                            objArr[i2] = null;
                        }
                        J = objArr;
                        K++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (vv0.class) {
                try {
                    if (I < 10) {
                        objArr[0] = H;
                        objArr[1] = iArr;
                        for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        H = objArr;
                        I++;
                    }
                } finally {
                }
            }
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (vv0.class) {
                try {
                    Object[] objArr = J;
                    if (objArr != null) {
                        this.F = objArr;
                        J = (Object[]) objArr[0];
                        this.E = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        K--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i == 4) {
            synchronized (vv0.class) {
                try {
                    Object[] objArr2 = H;
                    if (objArr2 != null) {
                        this.F = objArr2;
                        H = (Object[]) objArr2[0];
                        this.E = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        I--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.E = new int[i];
        this.F = new Object[i << 1];
    }

    public final void b(int i) {
        int i2 = this.G;
        int[] iArr = this.E;
        if (iArr.length < i) {
            Object[] objArr = this.F;
            a(i);
            if (this.G > 0) {
                System.arraycopy(iArr, 0, this.E, 0, i2);
                System.arraycopy(objArr, 0, this.F, 0, i2 << 1);
            }
            c(iArr, objArr, i2);
        }
        if (this.G != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        int i = this.G;
        if (i > 0) {
            int[] iArr = this.E;
            Object[] objArr = this.F;
            this.E = z91.I;
            this.F = z91.J;
            this.G = 0;
            c(iArr, objArr, i);
        }
        if (this.G > 0) {
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
        int i2 = this.G;
        if (i2 == 0) {
            return -1;
        }
        try {
            int iG = z91.g(i2, i, this.E);
            if (iG < 0 || obj.equals(this.F[iG << 1])) {
                return iG;
            }
            int i3 = iG + 1;
            while (i3 < i2 && this.E[i3] == i) {
                if (obj.equals(this.F[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = iG - 1; i4 >= 0 && this.E[i4] == i; i4--) {
                if (obj.equals(this.F[i4 << 1])) {
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
        if (obj instanceof vv0) {
            vv0 vv0Var = (vv0) obj;
            if (this.G != vv0Var.G) {
                return false;
            }
            for (int i = 0; i < this.G; i++) {
                try {
                    Object objH = h(i);
                    Object objJ = j(i);
                    Object orDefault = vv0Var.getOrDefault(objH, null);
                    if (objJ == null) {
                        if (orDefault != null || !vv0Var.containsKey(objH)) {
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
            if (this.G != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.G; i2++) {
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
        int i = this.G;
        if (i == 0) {
            return -1;
        }
        try {
            int iG = z91.g(i, 0, this.E);
            if (iG < 0 || this.F[iG << 1] == null) {
                return iG;
            }
            int i2 = iG + 1;
            while (i2 < i && this.E[i2] == 0) {
                if (this.F[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = iG - 1; i3 >= 0 && this.E[i3] == 0; i3--) {
                if (this.F[i3 << 1] == null) {
                    return i3;
                }
            }
            return ~i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int g(Object obj) {
        int i = this.G * 2;
        Object[] objArr = this.F;
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
        return iE >= 0 ? this.F[(iE << 1) + 1] : obj2;
    }

    public final Object h(int i) {
        return this.F[i << 1];
    }

    public final int hashCode() {
        int[] iArr = this.E;
        Object[] objArr = this.F;
        int i = this.G;
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
        Object[] objArr = this.F;
        int i2 = i << 1;
        Object obj = objArr[i2 + 1];
        int i3 = this.G;
        int i4 = 0;
        if (i3 <= 1) {
            c(this.E, objArr, i3);
            this.E = z91.I;
            this.F = z91.J;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.E;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i5) {
                    int i6 = i + 1;
                    int i7 = i5 - i;
                    System.arraycopy(iArr, i6, iArr, i, i7);
                    Object[] objArr2 = this.F;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i2, i7 << 1);
                }
                Object[] objArr3 = this.F;
                int i8 = i5 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                a(i3 > 8 ? i3 + (i3 >> 1) : 8);
                if (i3 != this.G) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.E, 0, i);
                    System.arraycopy(objArr, 0, this.F, 0, i2);
                }
                if (i < i5) {
                    int i9 = i + 1;
                    int i10 = i5 - i;
                    System.arraycopy(iArr, i9, this.E, i, i10);
                    System.arraycopy(objArr, i9 << 1, this.F, i2, i10 << 1);
                }
            }
            i4 = i5;
        }
        if (i3 != this.G) {
            throw new ConcurrentModificationException();
        }
        this.G = i4;
        return obj;
    }

    public final boolean isEmpty() {
        return this.G <= 0;
    }

    public final Object j(int i) {
        return this.F[(i << 1) + 1];
    }

    public final Object put(Object obj, Object obj2) {
        int i;
        int iD;
        int i2 = this.G;
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
            Object[] objArr = this.F;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = ~iD;
        int[] iArr = this.E;
        if (i2 >= iArr.length) {
            int i5 = 8;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i5 = 4;
            }
            Object[] objArr2 = this.F;
            a(i5);
            if (i2 != this.G) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.E;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.F, 0, objArr2.length);
            }
            c(iArr, objArr2, i2);
        }
        if (i4 < i2) {
            int[] iArr3 = this.E;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.F;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.G - i4) << 1);
        }
        int i7 = this.G;
        if (i2 == i7) {
            int[] iArr4 = this.E;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.F;
                int i8 = i4 << 1;
                objArr4[i8] = obj;
                objArr4[i8 + 1] = obj2;
                this.G = i7 + 1;
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
        Object[] objArr = this.F;
        Object obj3 = objArr[i];
        objArr[i] = obj2;
        return obj3;
    }

    public final int size() {
        return this.G;
    }

    public final String toString() {
        if (isEmpty()) {
            return JsonUtils.EMPTY_JSON;
        }
        StringBuilder sb = new StringBuilder(this.G * 28);
        sb.append('{');
        for (int i = 0; i < this.G; i++) {
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
        Object[] objArr = this.F;
        Object obj4 = objArr[i];
        objArr[i] = obj3;
        return true;
    }

    public vv0(int i) {
        if (i == 0) {
            this.E = z91.I;
            this.F = z91.J;
        } else {
            a(i);
        }
        this.G = 0;
    }
}
