package defpackage;

import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class lw0 implements Cloneable {
    public static final Object H = new Object();
    public int[] E;
    public Object[] F;
    public int G;

    public lw0() {
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
        this.E = new int[i4];
        this.F = new Object[i4];
    }

    public final void a(int i, Object obj) {
        int i2 = this.G;
        if (i2 != 0 && i <= this.E[i2 - 1]) {
            c(i, obj);
            return;
        }
        if (i2 >= this.E.length) {
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
            int[] iArr2 = this.E;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.F;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.E = iArr;
            this.F = objArr;
        }
        this.E[i2] = i;
        this.F[i2] = obj;
        this.G = i2 + 1;
    }

    public final Object b(int i, Integer num) {
        Object obj;
        int iG = z91.g(this.G, i, this.E);
        return (iG < 0 || (obj = this.F[iG]) == H) ? num : obj;
    }

    public final void c(int i, Object obj) {
        int iG = z91.g(this.G, i, this.E);
        if (iG >= 0) {
            this.F[iG] = obj;
            return;
        }
        int i2 = ~iG;
        int i3 = this.G;
        if (i2 < i3) {
            Object[] objArr = this.F;
            if (objArr[i2] == H) {
                this.E[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (i3 >= this.E.length) {
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
            int[] iArr2 = this.E;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.F;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.E = iArr;
            this.F = objArr2;
        }
        int i8 = this.G - i2;
        if (i8 != 0) {
            int[] iArr3 = this.E;
            int i9 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i9, i8);
            Object[] objArr4 = this.F;
            System.arraycopy(objArr4, i2, objArr4, i9, this.G - i2);
        }
        this.E[i2] = i;
        this.F[i2] = obj;
        this.G++;
    }

    public final Object clone() {
        try {
            lw0 lw0Var = (lw0) super.clone();
            lw0Var.E = (int[]) this.E.clone();
            lw0Var.F = (Object[]) this.F.clone();
            return lw0Var;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final String toString() {
        int i = this.G;
        if (i <= 0) {
            return JsonUtils.EMPTY_JSON;
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.G; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(this.E[i2]);
            sb.append('=');
            Object obj = this.F[i2];
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
