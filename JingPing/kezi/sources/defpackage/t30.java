package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class t30 implements Cloneable {
    public static final Object f = new Object();
    public boolean b = false;
    public long[] c;
    public Object[] d;
    public int e;

    public t30() {
        int i;
        int i2 = 4;
        while (true) {
            i = 80;
            if (i2 >= 32) {
                break;
            }
            int i3 = (1 << i2) - 12;
            if (80 <= i3) {
                i = i3;
                break;
            }
            i2++;
        }
        int i4 = i / 8;
        this.c = new long[i4];
        this.d = new Object[i4];
    }

    public final void a() {
        int i = this.e;
        Object[] objArr = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.b = false;
    }

    public final void b() {
        int i = this.e;
        long[] jArr = this.c;
        Object[] objArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.b = false;
        this.e = i2;
    }

    public final Object c(long j, Long l) {
        Object obj;
        int iE = xi.e(this.c, this.e, j);
        return (iE < 0 || (obj = this.d[iE]) == f) ? l : obj;
    }

    public final Object clone() {
        try {
            t30 t30Var = (t30) super.clone();
            t30Var.c = (long[]) this.c.clone();
            t30Var.d = (Object[]) this.d.clone();
            return t30Var;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void d(long j, Object obj) {
        int iE = xi.e(this.c, this.e, j);
        if (iE >= 0) {
            this.d[iE] = obj;
            return;
        }
        int i = ~iE;
        int i2 = this.e;
        if (i < i2) {
            Object[] objArr = this.d;
            if (objArr[i] == f) {
                this.c[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.b && i2 >= this.c.length) {
            b();
            i = ~xi.e(this.c, this.e, j);
        }
        int i3 = this.e;
        if (i3 >= this.c.length) {
            int i4 = (i3 + 1) * 8;
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
            int i7 = i4 / 8;
            long[] jArr = new long[i7];
            Object[] objArr2 = new Object[i7];
            long[] jArr2 = this.c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.c = jArr;
            this.d = objArr2;
        }
        int i8 = this.e - i;
        if (i8 != 0) {
            long[] jArr3 = this.c;
            int i9 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i9, i8);
            Object[] objArr4 = this.d;
            System.arraycopy(objArr4, i, objArr4, i9, this.e - i);
        }
        this.c[i] = j;
        this.d[i] = obj;
        this.e++;
    }

    public final int e() {
        if (this.b) {
            b();
        }
        return this.e;
    }

    public final Object f(int i) {
        if (this.b) {
            b();
        }
        return this.d[i];
    }

    public final String toString() {
        if (e() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.e * 28);
        sb.append('{');
        for (int i = 0; i < this.e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (this.b) {
                b();
            }
            sb.append(this.c[i]);
            sb.append('=');
            Object objF = f(i);
            if (objF != this) {
                sb.append(objF);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
