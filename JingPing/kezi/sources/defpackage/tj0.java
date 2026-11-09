package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class tj0 implements d7 {
    public int a = 16;
    public final int[] b = new int[16];
    public int[] c = new int[16];
    public int[] d = new int[16];
    public float[] e = new float[16];
    public int[] f = new int[16];
    public int[] g = new int[16];
    public int h = 0;
    public int i = -1;
    public final e20 j;
    public final ba k;

    public tj0(e20 e20Var, ba baVar) {
        this.j = e20Var;
        this.k = baVar;
        clear();
    }

    @Override // defpackage.d7
    public final float a(int i) {
        int i2 = this.h;
        int i3 = this.i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i) {
                return this.e[i3];
            }
            i3 = this.g[i3];
            if (i3 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // defpackage.d7
    public final float b(sj0 sj0Var) {
        int iN = n(sj0Var);
        if (iN != -1) {
            return this.e[iN];
        }
        return 0.0f;
    }

    @Override // defpackage.d7
    public final boolean c(sj0 sj0Var) {
        return n(sj0Var) != -1;
    }

    @Override // defpackage.d7
    public final void clear() {
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            sj0 sj0VarE = e(i2);
            if (sj0VarE != null) {
                sj0VarE.b(this.j);
            }
        }
        for (int i3 = 0; i3 < this.a; i3++) {
            this.d[i3] = -1;
            this.c[i3] = -1;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            this.b[i4] = -1;
        }
        this.h = 0;
        this.i = -1;
    }

    @Override // defpackage.d7
    public final float d(sj0 sj0Var, boolean z) {
        int[] iArr;
        int i;
        int iN = n(sj0Var);
        if (iN == -1) {
            return 0.0f;
        }
        int i2 = sj0Var.b;
        int i3 = i2 % 16;
        int[] iArr2 = this.b;
        int i4 = iArr2[i3];
        if (i4 != -1) {
            if (this.d[i4] == i2) {
                int[] iArr3 = this.c;
                iArr2[i3] = iArr3[i4];
                iArr3[i4] = -1;
            } else {
                while (true) {
                    iArr = this.c;
                    i = iArr[i4];
                    if (i == -1 || this.d[i] == i2) {
                        break;
                    }
                    i4 = i;
                }
                if (i != -1 && this.d[i] == i2) {
                    iArr[i4] = iArr[i];
                    iArr[i] = -1;
                }
            }
        }
        float f = this.e[iN];
        if (this.i == iN) {
            this.i = this.g[iN];
        }
        this.d[iN] = -1;
        int[] iArr4 = this.f;
        int i5 = iArr4[iN];
        if (i5 != -1) {
            int[] iArr5 = this.g;
            iArr5[i5] = iArr5[iN];
        }
        int i6 = this.g[iN];
        if (i6 != -1) {
            iArr4[i6] = iArr4[iN];
        }
        this.h--;
        sj0Var.l--;
        if (z) {
            sj0Var.b(this.j);
        }
        return f;
    }

    @Override // defpackage.d7
    public final sj0 e(int i) {
        int i2 = this.h;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return ((sj0[]) this.k.d)[this.d[i3]];
            }
            i3 = this.g[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // defpackage.d7
    public final void f(sj0 sj0Var, float f, boolean z) {
        if (f <= -0.001f || f >= 0.001f) {
            int iN = n(sj0Var);
            if (iN == -1) {
                i(sj0Var, f);
                return;
            }
            float[] fArr = this.e;
            float f2 = fArr[iN] + f;
            fArr[iN] = f2;
            if (f2 <= -0.001f || f2 >= 0.001f) {
                return;
            }
            fArr[iN] = 0.0f;
            d(sj0Var, z);
        }
    }

    @Override // defpackage.d7
    public final float g(e7 e7Var, boolean z) {
        float fB = b(e7Var.a);
        d(e7Var.a, z);
        tj0 tj0Var = (tj0) e7Var.d;
        int i = tj0Var.h;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = tj0Var.d[i3];
            if (i4 != -1) {
                f(((sj0[]) this.k.d)[i4], tj0Var.e[i3] * fB, z);
                i2++;
            }
            i3++;
        }
        return fB;
    }

    @Override // defpackage.d7
    public final void h(float f) {
        int i = this.h;
        int i2 = this.i;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.e;
            fArr[i2] = fArr[i2] / f;
            i2 = this.g[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // defpackage.d7
    public final void i(sj0 sj0Var, float f) {
        if (f > -0.001f && f < 0.001f) {
            d(sj0Var, true);
            return;
        }
        int i = 0;
        if (this.h == 0) {
            m(0, sj0Var, f);
            l(sj0Var, 0);
            this.i = 0;
            return;
        }
        int iN = n(sj0Var);
        if (iN != -1) {
            this.e[iN] = f;
            return;
        }
        int i2 = this.h + 1;
        int i3 = this.a;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            this.d = Arrays.copyOf(this.d, i4);
            this.e = Arrays.copyOf(this.e, i4);
            this.f = Arrays.copyOf(this.f, i4);
            this.g = Arrays.copyOf(this.g, i4);
            this.c = Arrays.copyOf(this.c, i4);
            for (int i5 = this.a; i5 < i4; i5++) {
                this.d[i5] = -1;
                this.c[i5] = -1;
            }
            this.a = i4;
        }
        int i6 = this.h;
        int i7 = this.i;
        int i8 = -1;
        for (int i9 = 0; i9 < i6; i9++) {
            int i10 = this.d[i7];
            int i11 = sj0Var.b;
            if (i10 == i11) {
                this.e[i7] = f;
                return;
            }
            if (i10 < i11) {
                i8 = i7;
            }
            i7 = this.g[i7];
            if (i7 == -1) {
                break;
            }
        }
        while (true) {
            if (i >= this.a) {
                i = -1;
                break;
            } else if (this.d[i] == -1) {
                break;
            } else {
                i++;
            }
        }
        m(i, sj0Var, f);
        if (i8 != -1) {
            this.f[i] = i8;
            int[] iArr = this.g;
            iArr[i] = iArr[i8];
            iArr[i8] = i;
        } else {
            this.f[i] = -1;
            if (this.h > 0) {
                this.g[i] = this.i;
                this.i = i;
            } else {
                this.g[i] = -1;
            }
        }
        int i12 = this.g[i];
        if (i12 != -1) {
            this.f[i12] = i;
        }
        l(sj0Var, i);
    }

    @Override // defpackage.d7
    public final void j() {
        int i = this.h;
        int i2 = this.i;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.e;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.g[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // defpackage.d7
    public final int k() {
        return this.h;
    }

    public final void l(sj0 sj0Var, int i) {
        int[] iArr;
        int i2 = sj0Var.b % 16;
        int[] iArr2 = this.b;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            iArr2[i2] = i;
        } else {
            while (true) {
                iArr = this.c;
                int i4 = iArr[i3];
                if (i4 == -1) {
                    break;
                } else {
                    i3 = i4;
                }
            }
            iArr[i3] = i;
        }
        this.c[i] = -1;
    }

    public final void m(int i, sj0 sj0Var, float f) {
        this.d[i] = sj0Var.b;
        this.e[i] = f;
        this.f[i] = -1;
        this.g[i] = -1;
        sj0Var.a(this.j);
        sj0Var.l++;
        this.h++;
    }

    public final int n(sj0 sj0Var) {
        if (this.h == 0) {
            return -1;
        }
        int i = sj0Var.b;
        int i2 = this.b[i % 16];
        if (i2 == -1) {
            return -1;
        }
        if (this.d[i2] == i) {
            return i2;
        }
        do {
            i2 = this.c[i2];
            if (i2 == -1) {
                break;
            }
        } while (this.d[i2] != i);
        if (i2 != -1 && this.d[i2] == i) {
            return i2;
        }
        return -1;
    }

    public final String toString() {
        String strI;
        String strI2;
        String strI3 = hashCode() + " { ";
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            sj0 sj0VarE = e(i2);
            if (sj0VarE != null) {
                String str = strI3 + sj0VarE + " = " + a(i2) + " ";
                int iN = n(sj0VarE);
                String strI4 = hk.i(str, "[p: ");
                int i3 = this.f[iN];
                ba baVar = this.k;
                if (i3 != -1) {
                    StringBuilder sbL = hk.l(strI4);
                    sbL.append(((sj0[]) baVar.d)[this.d[this.f[iN]]]);
                    strI = sbL.toString();
                } else {
                    strI = hk.i(strI4, "none");
                }
                String strI5 = hk.i(strI, ", n: ");
                if (this.g[iN] != -1) {
                    StringBuilder sbL2 = hk.l(strI5);
                    sbL2.append(((sj0[]) baVar.d)[this.d[this.g[iN]]]);
                    strI2 = sbL2.toString();
                } else {
                    strI2 = hk.i(strI5, "none");
                }
                strI3 = hk.i(strI2, "]");
            }
        }
        return hk.i(strI3, " }");
    }
}
