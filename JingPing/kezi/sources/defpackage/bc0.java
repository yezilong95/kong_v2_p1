package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class bc0 extends e7 {
    public sj0[] f;
    public sj0[] g;
    public int h;
    public ac0 i;

    @Override // defpackage.e7
    public final sj0 d(boolean[] zArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.h; i2++) {
            sj0[] sj0VarArr = this.f;
            sj0 sj0Var = sj0VarArr[i2];
            if (!zArr[sj0Var.b]) {
                ac0 ac0Var = this.i;
                ac0Var.b = sj0Var;
                int i3 = 8;
                if (i == -1) {
                    while (i3 >= 0) {
                        float f = ac0Var.b.h[i3];
                        if (f <= 0.0f) {
                            if (f < 0.0f) {
                                i = i2;
                                break;
                            }
                            i3--;
                        }
                    }
                } else {
                    sj0 sj0Var2 = sj0VarArr[i];
                    while (true) {
                        if (i3 >= 0) {
                            float f2 = sj0Var2.h[i3];
                            float f3 = ac0Var.b.h[i3];
                            if (f3 == f2) {
                                i3--;
                            } else if (f3 < f2) {
                            }
                        }
                    }
                }
            }
        }
        if (i == -1) {
            return null;
        }
        return this.f[i];
    }

    @Override // defpackage.e7
    public final void h(e7 e7Var, boolean z) {
        sj0 sj0Var = e7Var.a;
        if (sj0Var == null) {
            return;
        }
        d7 d7Var = e7Var.d;
        int iK = d7Var.k();
        for (int i = 0; i < iK; i++) {
            sj0 sj0VarE = d7Var.e(i);
            float fA = d7Var.a(i);
            ac0 ac0Var = this.i;
            ac0Var.b = sj0VarE;
            boolean z2 = sj0VarE.a;
            float[] fArr = sj0Var.h;
            if (z2) {
                boolean z3 = true;
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr2 = ac0Var.b.h;
                    float f = (fArr[i2] * fA) + fArr2[i2];
                    fArr2[i2] = f;
                    if (Math.abs(f) < 1.0E-4f) {
                        ac0Var.b.h[i2] = 0.0f;
                    } else {
                        z3 = false;
                    }
                }
                if (z3) {
                    ac0Var.c.j(ac0Var.b);
                }
            } else {
                for (int i3 = 0; i3 < 9; i3++) {
                    float f2 = fArr[i3];
                    if (f2 != 0.0f) {
                        float f3 = f2 * fA;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        ac0Var.b.h[i3] = f3;
                    } else {
                        ac0Var.b.h[i3] = 0.0f;
                    }
                }
                i(sj0VarE);
            }
            this.b = (e7Var.b * fA) + this.b;
        }
        j(sj0Var);
    }

    public final void i(sj0 sj0Var) {
        int i;
        int i2 = this.h + 1;
        sj0[] sj0VarArr = this.f;
        if (i2 > sj0VarArr.length) {
            sj0[] sj0VarArr2 = (sj0[]) Arrays.copyOf(sj0VarArr, sj0VarArr.length * 2);
            this.f = sj0VarArr2;
            this.g = (sj0[]) Arrays.copyOf(sj0VarArr2, sj0VarArr2.length * 2);
        }
        sj0[] sj0VarArr3 = this.f;
        int i3 = this.h;
        sj0VarArr3[i3] = sj0Var;
        int i4 = i3 + 1;
        this.h = i4;
        if (i4 > 1 && sj0VarArr3[i3].b > sj0Var.b) {
            int i5 = 0;
            while (true) {
                i = this.h;
                if (i5 >= i) {
                    break;
                }
                this.g[i5] = this.f[i5];
                i5++;
            }
            Arrays.sort(this.g, 0, i, new l3(7));
            for (int i6 = 0; i6 < this.h; i6++) {
                this.f[i6] = this.g[i6];
            }
        }
        sj0Var.a = true;
        sj0Var.a(this);
    }

    public final void j(sj0 sj0Var) {
        int i = 0;
        while (i < this.h) {
            if (this.f[i] == sj0Var) {
                while (true) {
                    int i2 = this.h;
                    if (i >= i2 - 1) {
                        this.h = i2 - 1;
                        sj0Var.a = false;
                        return;
                    } else {
                        sj0[] sj0VarArr = this.f;
                        int i3 = i + 1;
                        sj0VarArr[i] = sj0VarArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // defpackage.e7
    public final String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.h; i++) {
            sj0 sj0Var = this.f[i];
            ac0 ac0Var = this.i;
            ac0Var.b = sj0Var;
            str = str + ac0Var + " ";
        }
        return str;
    }
}
