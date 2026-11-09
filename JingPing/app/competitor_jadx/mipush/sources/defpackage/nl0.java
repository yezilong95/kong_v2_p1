package defpackage;

import io.nats.client.support.NatsConstants;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class nl0 extends m8 {
    public hw0[] f;
    public hw0[] g;
    public int h;
    public ml0 i;

    @Override // defpackage.m8
    public final hw0 d(boolean[] zArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.h; i2++) {
            hw0[] hw0VarArr = this.f;
            hw0 hw0Var = hw0VarArr[i2];
            if (!zArr[hw0Var.b]) {
                ml0 ml0Var = this.i;
                ml0Var.E = hw0Var;
                int i3 = 8;
                if (i == -1) {
                    while (i3 >= 0) {
                        float f = ml0Var.E.h[i3];
                        if (f <= 0.0f) {
                            if (f < 0.0f) {
                                i = i2;
                                break;
                            }
                            i3--;
                        }
                    }
                } else {
                    hw0 hw0Var2 = hw0VarArr[i];
                    while (true) {
                        if (i3 >= 0) {
                            float f2 = hw0Var2.h[i3];
                            float f3 = ml0Var.E.h[i3];
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

    @Override // defpackage.m8
    public final void h(m8 m8Var, boolean z) {
        hw0 hw0Var = m8Var.a;
        if (hw0Var == null) {
            return;
        }
        l8 l8Var = m8Var.d;
        int iD = l8Var.d();
        for (int i = 0; i < iD; i++) {
            hw0 hw0VarF = l8Var.f(i);
            float fA = l8Var.a(i);
            ml0 ml0Var = this.i;
            ml0Var.E = hw0VarF;
            boolean z2 = hw0VarF.a;
            float[] fArr = hw0Var.h;
            if (z2) {
                boolean z3 = true;
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr2 = ml0Var.E.h;
                    float f = (fArr[i2] * fA) + fArr2[i2];
                    fArr2[i2] = f;
                    if (Math.abs(f) < 1.0E-4f) {
                        ml0Var.E.h[i2] = 0.0f;
                    } else {
                        z3 = false;
                    }
                }
                if (z3) {
                    ml0Var.F.j(ml0Var.E);
                }
            } else {
                for (int i3 = 0; i3 < 9; i3++) {
                    float f2 = fArr[i3];
                    if (f2 != 0.0f) {
                        float f3 = f2 * fA;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        ml0Var.E.h[i3] = f3;
                    } else {
                        ml0Var.E.h[i3] = 0.0f;
                    }
                }
                i(hw0VarF);
            }
            this.b = (m8Var.b * fA) + this.b;
        }
        j(hw0Var);
    }

    public final void i(hw0 hw0Var) {
        int i;
        int i2 = this.h + 1;
        hw0[] hw0VarArr = this.f;
        if (i2 > hw0VarArr.length) {
            hw0[] hw0VarArr2 = (hw0[]) Arrays.copyOf(hw0VarArr, hw0VarArr.length * 2);
            this.f = hw0VarArr2;
            this.g = (hw0[]) Arrays.copyOf(hw0VarArr2, hw0VarArr2.length * 2);
        }
        hw0[] hw0VarArr3 = this.f;
        int i3 = this.h;
        hw0VarArr3[i3] = hw0Var;
        int i4 = i3 + 1;
        this.h = i4;
        if (i4 > 1 && hw0VarArr3[i3].b > hw0Var.b) {
            int i5 = 0;
            while (true) {
                i = this.h;
                if (i5 >= i) {
                    break;
                }
                this.g[i5] = this.f[i5];
                i5++;
            }
            Arrays.sort(this.g, 0, i, new q4(6));
            for (int i6 = 0; i6 < this.h; i6++) {
                this.f[i6] = this.g[i6];
            }
        }
        hw0Var.a = true;
        hw0Var.a(this);
    }

    public final void j(hw0 hw0Var) {
        int i = 0;
        while (i < this.h) {
            if (this.f[i] == hw0Var) {
                while (true) {
                    int i2 = this.h;
                    if (i >= i2 - 1) {
                        this.h = i2 - 1;
                        hw0Var.a = false;
                        return;
                    } else {
                        hw0[] hw0VarArr = this.f;
                        int i3 = i + 1;
                        hw0VarArr[i] = hw0VarArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // defpackage.m8
    public final String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.h; i++) {
            hw0 hw0Var = this.f[i];
            ml0 ml0Var = this.i;
            ml0Var.E = hw0Var;
            str = str + ml0Var + NatsConstants.SPACE;
        }
        return str;
    }
}
