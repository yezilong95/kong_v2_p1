package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class i8 implements l8 {
    public final m8 b;
    public final tb c;
    public int a = 0;
    public int d = 8;
    public int[] e = new int[8];
    public int[] f = new int[8];
    public float[] g = new float[8];
    public int h = -1;
    public int i = -1;
    public boolean j = false;

    public i8(m8 m8Var, tb tbVar) {
        this.b = m8Var;
        this.c = tbVar;
    }

    @Override // defpackage.l8
    public final float a(int i) {
        int i2 = this.h;
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            if (i3 == i) {
                return this.g[i2];
            }
            i2 = this.f[i2];
        }
        return 0.0f;
    }

    @Override // defpackage.l8
    public final float b(hw0 hw0Var) {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.a; i2++) {
            if (this.e[i] == hw0Var.b) {
                return this.g[i];
            }
            i = this.f[i];
        }
        return 0.0f;
    }

    @Override // defpackage.l8
    public final boolean c(hw0 hw0Var) {
        int i = this.h;
        if (i != -1) {
            for (int i2 = 0; i != -1 && i2 < this.a; i2++) {
                if (this.e[i] == hw0Var.b) {
                    return true;
                }
                i = this.f[i];
            }
        }
        return false;
    }

    @Override // defpackage.l8
    public final void clear() {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.a; i2++) {
            hw0 hw0Var = ((hw0[]) this.c.d)[this.e[i]];
            if (hw0Var != null) {
                hw0Var.b(this.b);
            }
            i = this.f[i];
        }
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.a = 0;
    }

    @Override // defpackage.l8
    public final int d() {
        return this.a;
    }

    @Override // defpackage.l8
    public final float e(hw0 hw0Var, boolean z) {
        int i = this.h;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.a) {
            if (this.e[i] == hw0Var.b) {
                if (i == this.h) {
                    this.h = this.f[i];
                } else {
                    int[] iArr = this.f;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    hw0Var.b(this.b);
                }
                hw0Var.l--;
                this.a--;
                this.e[i] = -1;
                if (this.j) {
                    this.i = i;
                }
                return this.g[i];
            }
            i2++;
            i3 = i;
            i = this.f[i];
        }
        return 0.0f;
    }

    @Override // defpackage.l8
    public final hw0 f(int i) {
        int i2 = this.h;
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            if (i3 == i) {
                return ((hw0[]) this.c.d)[this.e[i2]];
            }
            i2 = this.f[i2];
        }
        return null;
    }

    @Override // defpackage.l8
    public final void g(hw0 hw0Var, float f, boolean z) {
        if (f <= -0.001f || f >= 0.001f) {
            int i = this.h;
            m8 m8Var = this.b;
            if (i == -1) {
                this.h = 0;
                this.g[0] = f;
                this.e[0] = hw0Var.b;
                this.f[0] = -1;
                hw0Var.l++;
                hw0Var.a(m8Var);
                this.a++;
                if (this.j) {
                    return;
                }
                int i2 = this.i + 1;
                this.i = i2;
                int[] iArr = this.e;
                if (i2 >= iArr.length) {
                    this.j = true;
                    this.i = iArr.length - 1;
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.a; i4++) {
                int i5 = this.e[i];
                int i6 = hw0Var.b;
                if (i5 == i6) {
                    float[] fArr = this.g;
                    float f2 = fArr[i] + f;
                    if (f2 > -0.001f && f2 < 0.001f) {
                        f2 = 0.0f;
                    }
                    fArr[i] = f2;
                    if (f2 == 0.0f) {
                        if (i == this.h) {
                            this.h = this.f[i];
                        } else {
                            int[] iArr2 = this.f;
                            iArr2[i3] = iArr2[i];
                        }
                        if (z) {
                            hw0Var.b(m8Var);
                        }
                        if (this.j) {
                            this.i = i;
                        }
                        hw0Var.l--;
                        this.a--;
                        return;
                    }
                    return;
                }
                if (i5 < i6) {
                    i3 = i;
                }
                i = this.f[i];
            }
            int length = this.i;
            int i7 = length + 1;
            if (this.j) {
                int[] iArr3 = this.e;
                if (iArr3[length] != -1) {
                    length = iArr3.length;
                }
            } else {
                length = i7;
            }
            int[] iArr4 = this.e;
            if (length >= iArr4.length && this.a < iArr4.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr5 = this.e;
                    if (i8 >= iArr5.length) {
                        break;
                    }
                    if (iArr5[i8] == -1) {
                        length = i8;
                        break;
                    }
                    i8++;
                }
            }
            int[] iArr6 = this.e;
            if (length >= iArr6.length) {
                length = iArr6.length;
                int i9 = this.d * 2;
                this.d = i9;
                this.j = false;
                this.i = length - 1;
                this.g = Arrays.copyOf(this.g, i9);
                this.e = Arrays.copyOf(this.e, this.d);
                this.f = Arrays.copyOf(this.f, this.d);
            }
            this.e[length] = hw0Var.b;
            this.g[length] = f;
            if (i3 != -1) {
                int[] iArr7 = this.f;
                iArr7[length] = iArr7[i3];
                iArr7[i3] = length;
            } else {
                this.f[length] = this.h;
                this.h = length;
            }
            hw0Var.l++;
            hw0Var.a(m8Var);
            this.a++;
            if (!this.j) {
                this.i++;
            }
            int i10 = this.i;
            int[] iArr8 = this.e;
            if (i10 >= iArr8.length) {
                this.j = true;
                this.i = iArr8.length - 1;
            }
        }
    }

    @Override // defpackage.l8
    public final float h(m8 m8Var, boolean z) {
        float fB = b(m8Var.a);
        e(m8Var.a, z);
        l8 l8Var = m8Var.d;
        int iD = l8Var.d();
        for (int i = 0; i < iD; i++) {
            hw0 hw0VarF = l8Var.f(i);
            g(hw0VarF, l8Var.b(hw0VarF) * fB, z);
        }
        return fB;
    }

    @Override // defpackage.l8
    public final void i(float f) {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.a; i2++) {
            float[] fArr = this.g;
            fArr[i] = fArr[i] / f;
            i = this.f[i];
        }
    }

    @Override // defpackage.l8
    public final void j(hw0 hw0Var, float f) {
        if (f == 0.0f) {
            e(hw0Var, true);
            return;
        }
        int i = this.h;
        m8 m8Var = this.b;
        if (i == -1) {
            this.h = 0;
            this.g[0] = f;
            this.e[0] = hw0Var.b;
            this.f[0] = -1;
            hw0Var.l++;
            hw0Var.a(m8Var);
            this.a++;
            if (this.j) {
                return;
            }
            int i2 = this.i + 1;
            this.i = i2;
            int[] iArr = this.e;
            if (i2 >= iArr.length) {
                this.j = true;
                this.i = iArr.length - 1;
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.a; i4++) {
            int i5 = this.e[i];
            int i6 = hw0Var.b;
            if (i5 == i6) {
                this.g[i] = f;
                return;
            }
            if (i5 < i6) {
                i3 = i;
            }
            i = this.f[i];
        }
        int length = this.i;
        int i7 = length + 1;
        if (this.j) {
            int[] iArr2 = this.e;
            if (iArr2[length] != -1) {
                length = iArr2.length;
            }
        } else {
            length = i7;
        }
        int[] iArr3 = this.e;
        if (length >= iArr3.length && this.a < iArr3.length) {
            int i8 = 0;
            while (true) {
                int[] iArr4 = this.e;
                if (i8 >= iArr4.length) {
                    break;
                }
                if (iArr4[i8] == -1) {
                    length = i8;
                    break;
                }
                i8++;
            }
        }
        int[] iArr5 = this.e;
        if (length >= iArr5.length) {
            length = iArr5.length;
            int i9 = this.d * 2;
            this.d = i9;
            this.j = false;
            this.i = length - 1;
            this.g = Arrays.copyOf(this.g, i9);
            this.e = Arrays.copyOf(this.e, this.d);
            this.f = Arrays.copyOf(this.f, this.d);
        }
        this.e[length] = hw0Var.b;
        this.g[length] = f;
        if (i3 != -1) {
            int[] iArr6 = this.f;
            iArr6[length] = iArr6[i3];
            iArr6[i3] = length;
        } else {
            this.f[length] = this.h;
            this.h = length;
        }
        hw0Var.l++;
        hw0Var.a(m8Var);
        int i10 = this.a + 1;
        this.a = i10;
        if (!this.j) {
            this.i++;
        }
        int[] iArr7 = this.e;
        if (i10 >= iArr7.length) {
            this.j = true;
        }
        if (this.i >= iArr7.length) {
            this.j = true;
            this.i = iArr7.length - 1;
        }
    }

    @Override // defpackage.l8
    public final void k() {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.a; i2++) {
            float[] fArr = this.g;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.f[i];
        }
    }

    public final String toString() {
        int i = this.h;
        String string = "";
        for (int i2 = 0; i != -1 && i2 < this.a; i2++) {
            StringBuilder sbP = zn.p(zn.m(string, " -> "));
            sbP.append(this.g[i]);
            sbP.append(" : ");
            StringBuilder sbP2 = zn.p(sbP.toString());
            sbP2.append(((hw0[]) this.c.d)[this.e[i]]);
            string = sbP2.toString();
            i = this.f[i];
        }
        return string;
    }
}
