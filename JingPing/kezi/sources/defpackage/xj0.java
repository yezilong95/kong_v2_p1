package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class xj0 implements Cloneable {
    public static final Object e = new Object();
    public int[] b;
    public Object[] c;
    public int d;

    public xj0() {
        int i;
        int i2 = 4;
        while (true) {
            i = 40;
            if (i2 >= 32) {
                break;
            }
            int i3 = (1 << i2) - 12;
            if (40 <= i3) {
                i = i3;
                break;
            }
            i2++;
        }
        int i4 = i / 4;
        this.b = new int[i4];
        this.c = new Object[i4];
    }

    public final void a(int i, Object obj) {
        int i2 = this.d;
        if (i2 != 0 && i <= this.b[i2 - 1]) {
            c(i, obj);
            return;
        }
        if (i2 >= this.b.length) {
            int i3 = (i2 + 1) * 4;
            int i4 = 4;
            while (true) {
                if (i4 >= 32) {
                    break;
                }
                int i5 = (1 << i4) - 12;
                if (i3 <= i5) {
                    i3 = i5;
                    break;
                }
                i4++;
            }
            int i6 = i3 / 4;
            int[] iArr = new int[i6];
            Object[] objArr = new Object[i6];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = iArr;
            this.c = objArr;
        }
        this.b[i2] = i;
        this.c[i2] = obj;
        this.d = i2 + 1;
    }

    public final Object b(int i, Integer num) {
        Object obj;
        int iD = xi.d(this.d, i, this.b);
        return (iD < 0 || (obj = this.c[iD]) == e) ? num : obj;
    }

    public final void c(int i, Object obj) {
        int iD = xi.d(this.d, i, this.b);
        if (iD >= 0) {
            this.c[iD] = obj;
            return;
        }
        int i2 = ~iD;
        int i3 = this.d;
        if (i2 < i3) {
            Object[] objArr = this.c;
            if (objArr[i2] == e) {
                this.b[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (i3 >= this.b.length) {
            int i4 = (i3 + 1) * 4;
            int i5 = 4;
            while (true) {
                if (i5 >= 32) {
                    break;
                }
                int i6 = (1 << i5) - 12;
                if (i4 <= i6) {
                    i4 = i6;
                    break;
                }
                i5++;
            }
            int i7 = i4 / 4;
            int[] iArr = new int[i7];
            Object[] objArr2 = new Object[i7];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = iArr;
            this.c = objArr2;
        }
        int i8 = this.d - i2;
        if (i8 != 0) {
            int[] iArr3 = this.b;
            int i9 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i9, i8);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, i2, objArr4, i9, this.d - i2);
        }
        this.b[i2] = i;
        this.c[i2] = obj;
        this.d++;
    }

    public final Object clone() {
        try {
            xj0 xj0Var = (xj0) super.clone();
            xj0Var.b = (int[]) this.b.clone();
            xj0Var.c = (Object[]) this.c.clone();
            return xj0Var;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final String toString() {
        int i = this.d;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.d; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(this.b[i2]);
            sb.append('=');
            Object obj = this.c[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
