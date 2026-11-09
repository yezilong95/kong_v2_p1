package defpackage;

import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class cb0 implements Cloneable {
    public static final Object I = new Object();
    public boolean E = false;
    public long[] F;
    public Object[] G;
    public int H;

    public cb0() {
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
        this.F = new long[i4];
        this.G = new Object[i4];
    }

    public final void a() {
        int i = this.H;
        Object[] objArr = this.G;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.H = 0;
        this.E = false;
    }

    public final void b() {
        int i = this.H;
        long[] jArr = this.F;
        Object[] objArr = this.G;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != I) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.E = false;
        this.H = i2;
    }

    public final Object c(long j, Long l) {
        Object obj;
        int iH = z91.h(this.F, this.H, j);
        return (iH < 0 || (obj = this.G[iH]) == I) ? l : obj;
    }

    public final Object clone() {
        try {
            cb0 cb0Var = (cb0) super.clone();
            cb0Var.F = (long[]) this.F.clone();
            cb0Var.G = (Object[]) this.G.clone();
            return cb0Var;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void d(long j, Object obj) {
        int iH = z91.h(this.F, this.H, j);
        if (iH >= 0) {
            this.G[iH] = obj;
            return;
        }
        int i = ~iH;
        int i2 = this.H;
        if (i < i2) {
            Object[] objArr = this.G;
            if (objArr[i] == I) {
                this.F[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.E && i2 >= this.F.length) {
            b();
            i = ~z91.h(this.F, this.H, j);
        }
        int i3 = this.H;
        if (i3 >= this.F.length) {
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
            long[] jArr2 = this.F;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.G;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.F = jArr;
            this.G = objArr2;
        }
        int i8 = this.H - i;
        if (i8 != 0) {
            long[] jArr3 = this.F;
            int i9 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i9, i8);
            Object[] objArr4 = this.G;
            System.arraycopy(objArr4, i, objArr4, i9, this.H - i);
        }
        this.F[i] = j;
        this.G[i] = obj;
        this.H++;
    }

    public final int e() {
        if (this.E) {
            b();
        }
        return this.H;
    }

    public final Object f(int i) {
        if (this.E) {
            b();
        }
        return this.G[i];
    }

    public final String toString() {
        if (e() <= 0) {
            return JsonUtils.EMPTY_JSON;
        }
        StringBuilder sb = new StringBuilder(this.H * 28);
        sb.append('{');
        for (int i = 0; i < this.H; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (this.E) {
                b();
            }
            sb.append(this.F[i]);
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
