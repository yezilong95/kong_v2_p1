package defpackage;

import io.nats.client.support.NatsConstants;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class iw0 implements l8 {
    public int a = 16;
    public final int[] b = new int[16];
    public int[] c = new int[16];
    public int[] d = new int[16];
    public float[] e = new float[16];
    public int[] f = new int[16];
    public int[] g = new int[16];
    public int h = 0;
    public int i = -1;
    public final i90 j;
    public final tb k;

    public iw0(i90 i90Var, tb tbVar) {
        this.j = i90Var;
        this.k = tbVar;
        clear();
    }

    @Override // defpackage.l8
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

    @Override // defpackage.l8
    public final float b(hw0 hw0Var) {
        int iN = n(hw0Var);
        if (iN != -1) {
            return this.e[iN];
        }
        return 0.0f;
    }

    @Override // defpackage.l8
    public final boolean c(hw0 hw0Var) {
        return n(hw0Var) != -1;
    }

    @Override // defpackage.l8
    public final void clear() {
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            hw0 hw0VarF = f(i2);
            if (hw0VarF != null) {
                hw0VarF.b(this.j);
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

    @Override // defpackage.l8
    public final int d() {
        return this.h;
    }

    @Override // defpackage.l8
    public final float e(hw0 hw0Var, boolean z) {
        int[] iArr;
        int i;
        int iN = n(hw0Var);
        if (iN == -1) {
            return 0.0f;
        }
        int i2 = hw0Var.b;
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
        hw0Var.l--;
        if (z) {
            hw0Var.b(this.j);
        }
        return f;
    }

    @Override // defpackage.l8
    public final hw0 f(int i) {
        int i2 = this.h;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return ((hw0[]) this.k.d)[this.d[i3]];
            }
            i3 = this.g[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // defpackage.l8
    public final void g(hw0 hw0Var, float f, boolean z) {
        if (f <= -0.001f || f >= 0.001f) {
            int iN = n(hw0Var);
            if (iN == -1) {
                j(hw0Var, f);
                return;
            }
            float[] fArr = this.e;
            float f2 = fArr[iN] + f;
            fArr[iN] = f2;
            if (f2 <= -0.001f || f2 >= 0.001f) {
                return;
            }
            fArr[iN] = 0.0f;
            e(hw0Var, z);
        }
    }

    @Override // defpackage.l8
    public final float h(m8 m8Var, boolean z) {
        float fB = b(m8Var.a);
        e(m8Var.a, z);
        iw0 iw0Var = (iw0) m8Var.d;
        int i = iw0Var.h;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = iw0Var.d[i3];
            if (i4 != -1) {
                g(((hw0[]) this.k.d)[i4], iw0Var.e[i3] * fB, z);
                i2++;
            }
            i3++;
        }
        return fB;
    }

    @Override // defpackage.l8
    public final void i(float f) {
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

    @Override // defpackage.l8
    public final void j(hw0 hw0Var, float f) {
        if (f > -0.001f && f < 0.001f) {
            e(hw0Var, true);
            return;
        }
        int i = 0;
        if (this.h == 0) {
            m(0, hw0Var, f);
            l(hw0Var, 0);
            this.i = 0;
            return;
        }
        int iN = n(hw0Var);
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
            int i11 = hw0Var.b;
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
        m(i, hw0Var, f);
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
        l(hw0Var, i);
    }

    @Override // defpackage.l8
    public final void k() {
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

    public final void l(hw0 hw0Var, int i) {
        int[] iArr;
        int i2 = hw0Var.b % 16;
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

    public final void m(int i, hw0 hw0Var, float f) {
        this.d[i] = hw0Var.b;
        this.e[i] = f;
        this.f[i] = -1;
        this.g[i] = -1;
        hw0Var.a(this.j);
        hw0Var.l++;
        this.h++;
    }

    public final int n(hw0 hw0Var) {
        if (this.h == 0) {
            return -1;
        }
        int i = hw0Var.b;
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
        String strM;
        String strM2;
        String strM3 = hashCode() + " { ";
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            hw0 hw0VarF = f(i2);
            if (hw0VarF != null) {
                String str = strM3 + hw0VarF + " = " + a(i2) + NatsConstants.SPACE;
                int iN = n(hw0VarF);
                String strM4 = zn.m(str, "[p: ");
                int i3 = this.f[iN];
                tb tbVar = this.k;
                if (i3 != -1) {
                    StringBuilder sbP = zn.p(strM4);
                    sbP.append(((hw0[]) tbVar.d)[this.d[this.f[iN]]]);
                    strM = sbP.toString();
                } else {
                    strM = zn.m(strM4, "none");
                }
                String strM5 = zn.m(strM, ", n: ");
                if (this.g[iN] != -1) {
                    StringBuilder sbP2 = zn.p(strM5);
                    sbP2.append(((hw0[]) tbVar.d)[this.d[this.g[iN]]]);
                    strM2 = sbP2.toString();
                } else {
                    strM2 = zn.m(strM5, "none");
                }
                strM3 = zn.m(strM2, "]");
            }
        }
        return zn.m(strM3, " }");
    }
}
