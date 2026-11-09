package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import java.util.Arrays;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class f20 {
    public static int o = 1000;
    public static boolean p = true;
    public final bc0 b;
    public e7[] e;
    public final ba k;
    public e7 n;
    public int a = 0;
    public int c = 32;
    public int d = 32;
    public boolean f = false;
    public boolean[] g = new boolean[32];
    public int h = 1;
    public int i = 0;
    public int j = 32;
    public sj0[] l = new sj0[o];
    public int m = 0;

    public f20() {
        this.e = null;
        this.e = new e7[32];
        q();
        ba baVar = new ba();
        baVar.a = new vb0();
        baVar.b = new vb0();
        baVar.c = new vb0();
        baVar.d = new sj0[32];
        this.k = baVar;
        bc0 bc0Var = new bc0(baVar);
        bc0Var.f = new sj0[Uuid.SIZE_BITS];
        bc0Var.g = new sj0[Uuid.SIZE_BITS];
        bc0Var.h = 0;
        bc0Var.i = new ac0(bc0Var);
        this.b = bc0Var;
        if (p) {
            this.n = new e20(baVar);
        } else {
            this.n = new e7(baVar);
        }
    }

    public static int m(Object obj) {
        sj0 sj0Var = ((ag) obj).g;
        if (sj0Var != null) {
            return (int) (sj0Var.e + 0.5f);
        }
        return 0;
    }

    public final sj0 a(SolverVariable$Type solverVariable$Type) {
        sj0 sj0Var = (sj0) ((vb0) this.k.c).a();
        if (sj0Var == null) {
            sj0Var = new sj0(solverVariable$Type);
            sj0Var.i = solverVariable$Type;
        } else {
            sj0Var.c();
            sj0Var.i = solverVariable$Type;
        }
        int i = this.m;
        int i2 = o;
        if (i >= i2) {
            int i3 = i2 * 2;
            o = i3;
            this.l = (sj0[]) Arrays.copyOf(this.l, i3);
        }
        sj0[] sj0VarArr = this.l;
        int i4 = this.m;
        this.m = i4 + 1;
        sj0VarArr[i4] = sj0Var;
        return sj0Var;
    }

    public final void b(sj0 sj0Var, sj0 sj0Var2, int i, float f, sj0 sj0Var3, sj0 sj0Var4, int i2, int i3) {
        e7 e7VarK = k();
        if (sj0Var2 == sj0Var3) {
            e7VarK.d.i(sj0Var, 1.0f);
            e7VarK.d.i(sj0Var4, 1.0f);
            e7VarK.d.i(sj0Var2, -2.0f);
        } else if (f == 0.5f) {
            e7VarK.d.i(sj0Var, 1.0f);
            e7VarK.d.i(sj0Var2, -1.0f);
            e7VarK.d.i(sj0Var3, -1.0f);
            e7VarK.d.i(sj0Var4, 1.0f);
            if (i > 0 || i2 > 0) {
                e7VarK.b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            e7VarK.d.i(sj0Var, -1.0f);
            e7VarK.d.i(sj0Var2, 1.0f);
            e7VarK.b = i;
        } else if (f >= 1.0f) {
            e7VarK.d.i(sj0Var4, -1.0f);
            e7VarK.d.i(sj0Var3, 1.0f);
            e7VarK.b = -i2;
        } else {
            float f2 = 1.0f - f;
            e7VarK.d.i(sj0Var, f2 * 1.0f);
            e7VarK.d.i(sj0Var2, f2 * (-1.0f));
            e7VarK.d.i(sj0Var3, (-1.0f) * f);
            e7VarK.d.i(sj0Var4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                e7VarK.b = (i2 * f) + ((-i) * f2);
            }
        }
        if (i3 != 8) {
            e7VarK.a(this, i3);
        }
        c(e7VarK);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(defpackage.e7 r18) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f20.c(e7):void");
    }

    public final void d(sj0 sj0Var, int i) {
        int i2 = sj0Var.c;
        if (i2 == -1) {
            sj0Var.e = i;
            sj0Var.f = true;
            int i3 = sj0Var.k;
            for (int i4 = 0; i4 < i3; i4++) {
                sj0Var.j[i4].g(sj0Var, false);
            }
            sj0Var.k = 0;
            return;
        }
        if (i2 == -1) {
            e7 e7VarK = k();
            e7VarK.a = sj0Var;
            float f = i;
            sj0Var.e = f;
            e7VarK.b = f;
            e7VarK.e = true;
            c(e7VarK);
            return;
        }
        e7 e7Var = this.e[i2];
        if (e7Var.e) {
            e7Var.b = i;
            return;
        }
        if (e7Var.d.k() == 0) {
            e7Var.e = true;
            e7Var.b = i;
            return;
        }
        e7 e7VarK2 = k();
        if (i < 0) {
            e7VarK2.b = i * (-1);
            e7VarK2.d.i(sj0Var, 1.0f);
        } else {
            e7VarK2.b = i;
            e7VarK2.d.i(sj0Var, -1.0f);
        }
        c(e7VarK2);
    }

    public final void e(sj0 sj0Var, sj0 sj0Var2, int i, int i2) {
        boolean z = false;
        if (i2 == 8 && sj0Var2.f && sj0Var.c == -1) {
            sj0Var.e = sj0Var2.e + i;
            sj0Var.f = true;
            int i3 = sj0Var.k;
            for (int i4 = 0; i4 < i3; i4++) {
                sj0Var.j[i4].g(sj0Var, false);
            }
            sj0Var.k = 0;
            return;
        }
        e7 e7VarK = k();
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            e7VarK.b = i;
        }
        if (z) {
            e7VarK.d.i(sj0Var, 1.0f);
            e7VarK.d.i(sj0Var2, -1.0f);
        } else {
            e7VarK.d.i(sj0Var, -1.0f);
            e7VarK.d.i(sj0Var2, 1.0f);
        }
        if (i2 != 8) {
            e7VarK.a(this, i2);
        }
        c(e7VarK);
    }

    public final void f(sj0 sj0Var, sj0 sj0Var2, int i, int i2) {
        e7 e7VarK = k();
        sj0 sj0VarL = l();
        sj0VarL.d = 0;
        e7VarK.b(sj0Var, sj0Var2, sj0VarL, i);
        if (i2 != 8) {
            e7VarK.d.i(i(i2), (int) (e7VarK.d.b(sj0VarL) * (-1.0f)));
        }
        c(e7VarK);
    }

    public final void g(sj0 sj0Var, sj0 sj0Var2, int i, int i2) {
        e7 e7VarK = k();
        sj0 sj0VarL = l();
        sj0VarL.d = 0;
        e7VarK.c(sj0Var, sj0Var2, sj0VarL, i);
        if (i2 != 8) {
            e7VarK.d.i(i(i2), (int) (e7VarK.d.b(sj0VarL) * (-1.0f)));
        }
        c(e7VarK);
    }

    public final void h(e7 e7Var) {
        boolean z = p;
        ba baVar = this.k;
        if (z) {
            e7 e7Var2 = this.e[this.i];
            if (e7Var2 != null) {
                ((vb0) baVar.a).b(e7Var2);
            }
        } else {
            e7 e7Var3 = this.e[this.i];
            if (e7Var3 != null) {
                ((vb0) baVar.b).b(e7Var3);
            }
        }
        e7[] e7VarArr = this.e;
        int i = this.i;
        e7VarArr[i] = e7Var;
        sj0 sj0Var = e7Var.a;
        sj0Var.c = i;
        this.i = i + 1;
        sj0Var.d(e7Var);
    }

    public final sj0 i(int i) {
        if (this.h + 1 >= this.d) {
            n();
        }
        sj0 sj0VarA = a(SolverVariable$Type.ERROR);
        int i2 = this.a + 1;
        this.a = i2;
        this.h++;
        sj0VarA.b = i2;
        sj0VarA.d = i;
        ((sj0[]) this.k.d)[i2] = sj0VarA;
        bc0 bc0Var = this.b;
        bc0Var.i.b = sj0VarA;
        float[] fArr = sj0VarA.h;
        Arrays.fill(fArr, 0.0f);
        fArr[sj0VarA.d] = 1.0f;
        bc0Var.i(sj0VarA);
        return sj0VarA;
    }

    public final sj0 j(Object obj) {
        if (obj == null) {
            return null;
        }
        if (this.h + 1 >= this.d) {
            n();
        }
        if (!(obj instanceof ag)) {
            return null;
        }
        ag agVar = (ag) obj;
        sj0 sj0Var = agVar.g;
        if (sj0Var == null) {
            agVar.i();
            sj0Var = agVar.g;
        }
        int i = sj0Var.b;
        ba baVar = this.k;
        if (i != -1 && i <= this.a && ((sj0[]) baVar.d)[i] != null) {
            return sj0Var;
        }
        if (i != -1) {
            sj0Var.c();
        }
        int i2 = this.a + 1;
        this.a = i2;
        this.h++;
        sj0Var.b = i2;
        sj0Var.i = SolverVariable$Type.UNRESTRICTED;
        ((sj0[]) baVar.d)[i2] = sj0Var;
        return sj0Var;
    }

    public final e7 k() {
        boolean z = p;
        ba baVar = this.k;
        if (z) {
            e7 e7Var = (e7) ((vb0) baVar.a).a();
            if (e7Var == null) {
                return new e20(baVar);
            }
            e7Var.a = null;
            e7Var.d.clear();
            e7Var.b = 0.0f;
            e7Var.e = false;
            return e7Var;
        }
        e7 e7Var2 = (e7) ((vb0) baVar.b).a();
        if (e7Var2 == null) {
            return new e7(baVar);
        }
        e7Var2.a = null;
        e7Var2.d.clear();
        e7Var2.b = 0.0f;
        e7Var2.e = false;
        return e7Var2;
    }

    public final sj0 l() {
        if (this.h + 1 >= this.d) {
            n();
        }
        sj0 sj0VarA = a(SolverVariable$Type.SLACK);
        int i = this.a + 1;
        this.a = i;
        this.h++;
        sj0VarA.b = i;
        ((sj0[]) this.k.d)[i] = sj0VarA;
        return sj0VarA;
    }

    public final void n() {
        int i = this.c * 2;
        this.c = i;
        this.e = (e7[]) Arrays.copyOf(this.e, i);
        ba baVar = this.k;
        baVar.d = (sj0[]) Arrays.copyOf((sj0[]) baVar.d, this.c);
        int i2 = this.c;
        this.g = new boolean[i2];
        this.d = i2;
        this.j = i2;
    }

    public final void o(bc0 bc0Var) {
        ba baVar;
        int i = 0;
        while (true) {
            if (i >= this.i) {
                break;
            }
            e7 e7Var = this.e[i];
            if (e7Var.a.i != SolverVariable$Type.UNRESTRICTED) {
                float f = 0.0f;
                if (e7Var.b < 0.0f) {
                    boolean z = false;
                    int i2 = 0;
                    while (!z) {
                        int i3 = 1;
                        i2++;
                        float f2 = Float.MAX_VALUE;
                        int i4 = -1;
                        int i5 = -1;
                        int i6 = 0;
                        int i7 = 0;
                        while (true) {
                            int i8 = this.i;
                            baVar = this.k;
                            if (i6 >= i8) {
                                break;
                            }
                            e7 e7Var2 = this.e[i6];
                            if (e7Var2.a.i != SolverVariable$Type.UNRESTRICTED && !e7Var2.e && e7Var2.b < f) {
                                int i9 = i3;
                                while (i9 < this.h) {
                                    sj0 sj0Var = ((sj0[]) baVar.d)[i9];
                                    float fB = e7Var2.d.b(sj0Var);
                                    if (fB > f) {
                                        for (int i10 = 0; i10 < 9; i10++) {
                                            float f3 = sj0Var.g[i10] / fB;
                                            if ((f3 < f2 && i10 == i7) || i10 > i7) {
                                                i7 = i10;
                                                f2 = f3;
                                                i4 = i6;
                                                i5 = i9;
                                            }
                                        }
                                    }
                                    i9++;
                                    f = 0.0f;
                                }
                            }
                            i6++;
                            f = 0.0f;
                            i3 = 1;
                        }
                        if (i4 != -1) {
                            e7 e7Var3 = this.e[i4];
                            e7Var3.a.c = -1;
                            e7Var3.f(((sj0[]) baVar.d)[i5]);
                            sj0 sj0Var2 = e7Var3.a;
                            sj0Var2.c = i4;
                            sj0Var2.d(e7Var3);
                        } else {
                            z = true;
                        }
                        if (i2 > this.h / 2) {
                            z = true;
                        }
                        f = 0.0f;
                    }
                }
            }
            i++;
        }
        p(bc0Var);
        for (int i11 = 0; i11 < this.i; i11++) {
            e7 e7Var4 = this.e[i11];
            e7Var4.a.e = e7Var4.b;
        }
    }

    public final void p(e7 e7Var) {
        for (int i = 0; i < this.h; i++) {
            this.g[i] = false;
        }
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2++;
            if (i2 >= this.h * 2) {
                return;
            }
            sj0 sj0Var = e7Var.a;
            if (sj0Var != null) {
                this.g[sj0Var.b] = true;
            }
            sj0 sj0VarD = e7Var.d(this.g);
            if (sj0VarD != null) {
                boolean[] zArr = this.g;
                int i3 = sj0VarD.b;
                if (zArr[i3]) {
                    return;
                } else {
                    zArr[i3] = true;
                }
            }
            if (sj0VarD != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.i; i5++) {
                    e7 e7Var2 = this.e[i5];
                    if (e7Var2.a.i != SolverVariable$Type.UNRESTRICTED && !e7Var2.e && e7Var2.d.c(sj0VarD)) {
                        float fB = e7Var2.d.b(sj0VarD);
                        if (fB < 0.0f) {
                            float f2 = (-e7Var2.b) / fB;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    e7 e7Var3 = this.e[i4];
                    e7Var3.a.c = -1;
                    e7Var3.f(sj0VarD);
                    sj0 sj0Var2 = e7Var3.a;
                    sj0Var2.c = i4;
                    sj0Var2.d(e7Var3);
                }
            } else {
                z = true;
            }
        }
    }

    public final void q() {
        boolean z = p;
        ba baVar = this.k;
        int i = 0;
        if (z) {
            while (true) {
                e7[] e7VarArr = this.e;
                if (i >= e7VarArr.length) {
                    return;
                }
                e7 e7Var = e7VarArr[i];
                if (e7Var != null) {
                    ((vb0) baVar.a).b(e7Var);
                }
                this.e[i] = null;
                i++;
            }
        } else {
            while (true) {
                e7[] e7VarArr2 = this.e;
                if (i >= e7VarArr2.length) {
                    return;
                }
                e7 e7Var2 = e7VarArr2[i];
                if (e7Var2 != null) {
                    ((vb0) baVar.b).b(e7Var2);
                }
                this.e[i] = null;
                i++;
            }
        }
    }

    public final void r() {
        ba baVar;
        int i = 0;
        while (true) {
            baVar = this.k;
            sj0[] sj0VarArr = (sj0[]) baVar.d;
            if (i >= sj0VarArr.length) {
                break;
            }
            sj0 sj0Var = sj0VarArr[i];
            if (sj0Var != null) {
                sj0Var.c();
            }
            i++;
        }
        vb0 vb0Var = (vb0) baVar.c;
        sj0[] sj0VarArr2 = this.l;
        int length = this.m;
        vb0Var.getClass();
        if (length > sj0VarArr2.length) {
            length = sj0VarArr2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            sj0 sj0Var2 = sj0VarArr2[i2];
            int i3 = vb0Var.c;
            Object[] objArr = vb0Var.b;
            if (i3 < objArr.length) {
                objArr[i3] = sj0Var2;
                vb0Var.c = i3 + 1;
            }
        }
        this.m = 0;
        Arrays.fill((sj0[]) baVar.d, (Object) null);
        this.a = 0;
        bc0 bc0Var = this.b;
        bc0Var.h = 0;
        bc0Var.b = 0.0f;
        this.h = 1;
        for (int i4 = 0; i4 < this.i; i4++) {
            this.e[i4].getClass();
        }
        q();
        this.i = 0;
        if (p) {
            this.n = new e20(baVar);
        } else {
            this.n = new e7(baVar);
        }
    }
}
