package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import java.util.Arrays;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class j90 {
    public static int o = 1000;
    public static boolean p = true;
    public final nl0 b;
    public m8[] e;
    public final tb k;
    public m8 n;
    public int a = 0;
    public int c = 32;
    public int d = 32;
    public boolean f = false;
    public boolean[] g = new boolean[32];
    public int h = 1;
    public int i = 0;
    public int j = 32;
    public hw0[] l = new hw0[o];
    public int m = 0;

    public j90() {
        this.e = null;
        this.e = new m8[32];
        q();
        tb tbVar = new tb();
        tbVar.a = new al0();
        tbVar.b = new al0();
        tbVar.c = new al0();
        tbVar.d = new hw0[32];
        this.k = tbVar;
        nl0 nl0Var = new nl0(tbVar);
        nl0Var.f = new hw0[Uuid.SIZE_BITS];
        nl0Var.g = new hw0[Uuid.SIZE_BITS];
        nl0Var.h = 0;
        nl0Var.i = new ml0(nl0Var);
        this.b = nl0Var;
        if (p) {
            this.n = new i90(tbVar);
        } else {
            this.n = new m8(tbVar);
        }
    }

    public static int m(Object obj) {
        hw0 hw0Var = ((mh) obj).g;
        if (hw0Var != null) {
            return (int) (hw0Var.e + 0.5f);
        }
        return 0;
    }

    public final hw0 a(SolverVariable$Type solverVariable$Type) {
        hw0 hw0Var = (hw0) ((al0) this.k.c).a();
        if (hw0Var == null) {
            hw0Var = new hw0(solverVariable$Type);
            hw0Var.i = solverVariable$Type;
        } else {
            hw0Var.c();
            hw0Var.i = solverVariable$Type;
        }
        int i = this.m;
        int i2 = o;
        if (i >= i2) {
            int i3 = i2 * 2;
            o = i3;
            this.l = (hw0[]) Arrays.copyOf(this.l, i3);
        }
        hw0[] hw0VarArr = this.l;
        int i4 = this.m;
        this.m = i4 + 1;
        hw0VarArr[i4] = hw0Var;
        return hw0Var;
    }

    public final void b(hw0 hw0Var, hw0 hw0Var2, int i, float f, hw0 hw0Var3, hw0 hw0Var4, int i2, int i3) {
        m8 m8VarK = k();
        if (hw0Var2 == hw0Var3) {
            m8VarK.d.j(hw0Var, 1.0f);
            m8VarK.d.j(hw0Var4, 1.0f);
            m8VarK.d.j(hw0Var2, -2.0f);
        } else if (f == 0.5f) {
            m8VarK.d.j(hw0Var, 1.0f);
            m8VarK.d.j(hw0Var2, -1.0f);
            m8VarK.d.j(hw0Var3, -1.0f);
            m8VarK.d.j(hw0Var4, 1.0f);
            if (i > 0 || i2 > 0) {
                m8VarK.b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            m8VarK.d.j(hw0Var, -1.0f);
            m8VarK.d.j(hw0Var2, 1.0f);
            m8VarK.b = i;
        } else if (f >= 1.0f) {
            m8VarK.d.j(hw0Var4, -1.0f);
            m8VarK.d.j(hw0Var3, 1.0f);
            m8VarK.b = -i2;
        } else {
            float f2 = 1.0f - f;
            m8VarK.d.j(hw0Var, f2 * 1.0f);
            m8VarK.d.j(hw0Var2, f2 * (-1.0f));
            m8VarK.d.j(hw0Var3, (-1.0f) * f);
            m8VarK.d.j(hw0Var4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                m8VarK.b = (i2 * f) + ((-i) * f2);
            }
        }
        if (i3 != 8) {
            m8VarK.a(this, i3);
        }
        c(m8VarK);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(defpackage.m8 r18) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j90.c(m8):void");
    }

    public final void d(hw0 hw0Var, int i) {
        int i2 = hw0Var.c;
        if (i2 == -1) {
            hw0Var.e = i;
            hw0Var.f = true;
            int i3 = hw0Var.k;
            for (int i4 = 0; i4 < i3; i4++) {
                hw0Var.j[i4].g(hw0Var, false);
            }
            hw0Var.k = 0;
            return;
        }
        if (i2 == -1) {
            m8 m8VarK = k();
            m8VarK.a = hw0Var;
            float f = i;
            hw0Var.e = f;
            m8VarK.b = f;
            m8VarK.e = true;
            c(m8VarK);
            return;
        }
        m8 m8Var = this.e[i2];
        if (m8Var.e) {
            m8Var.b = i;
            return;
        }
        if (m8Var.d.d() == 0) {
            m8Var.e = true;
            m8Var.b = i;
            return;
        }
        m8 m8VarK2 = k();
        if (i < 0) {
            m8VarK2.b = i * (-1);
            m8VarK2.d.j(hw0Var, 1.0f);
        } else {
            m8VarK2.b = i;
            m8VarK2.d.j(hw0Var, -1.0f);
        }
        c(m8VarK2);
    }

    public final void e(hw0 hw0Var, hw0 hw0Var2, int i, int i2) {
        boolean z = false;
        if (i2 == 8 && hw0Var2.f && hw0Var.c == -1) {
            hw0Var.e = hw0Var2.e + i;
            hw0Var.f = true;
            int i3 = hw0Var.k;
            for (int i4 = 0; i4 < i3; i4++) {
                hw0Var.j[i4].g(hw0Var, false);
            }
            hw0Var.k = 0;
            return;
        }
        m8 m8VarK = k();
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            m8VarK.b = i;
        }
        if (z) {
            m8VarK.d.j(hw0Var, 1.0f);
            m8VarK.d.j(hw0Var2, -1.0f);
        } else {
            m8VarK.d.j(hw0Var, -1.0f);
            m8VarK.d.j(hw0Var2, 1.0f);
        }
        if (i2 != 8) {
            m8VarK.a(this, i2);
        }
        c(m8VarK);
    }

    public final void f(hw0 hw0Var, hw0 hw0Var2, int i, int i2) {
        m8 m8VarK = k();
        hw0 hw0VarL = l();
        hw0VarL.d = 0;
        m8VarK.b(hw0Var, hw0Var2, hw0VarL, i);
        if (i2 != 8) {
            m8VarK.d.j(i(i2), (int) (m8VarK.d.b(hw0VarL) * (-1.0f)));
        }
        c(m8VarK);
    }

    public final void g(hw0 hw0Var, hw0 hw0Var2, int i, int i2) {
        m8 m8VarK = k();
        hw0 hw0VarL = l();
        hw0VarL.d = 0;
        m8VarK.c(hw0Var, hw0Var2, hw0VarL, i);
        if (i2 != 8) {
            m8VarK.d.j(i(i2), (int) (m8VarK.d.b(hw0VarL) * (-1.0f)));
        }
        c(m8VarK);
    }

    public final void h(m8 m8Var) {
        boolean z = p;
        tb tbVar = this.k;
        if (z) {
            m8 m8Var2 = this.e[this.i];
            if (m8Var2 != null) {
                ((al0) tbVar.a).b(m8Var2);
            }
        } else {
            m8 m8Var3 = this.e[this.i];
            if (m8Var3 != null) {
                ((al0) tbVar.b).b(m8Var3);
            }
        }
        m8[] m8VarArr = this.e;
        int i = this.i;
        m8VarArr[i] = m8Var;
        hw0 hw0Var = m8Var.a;
        hw0Var.c = i;
        this.i = i + 1;
        hw0Var.d(m8Var);
    }

    public final hw0 i(int i) {
        if (this.h + 1 >= this.d) {
            n();
        }
        hw0 hw0VarA = a(SolverVariable$Type.ERROR);
        int i2 = this.a + 1;
        this.a = i2;
        this.h++;
        hw0VarA.b = i2;
        hw0VarA.d = i;
        ((hw0[]) this.k.d)[i2] = hw0VarA;
        nl0 nl0Var = this.b;
        nl0Var.i.E = hw0VarA;
        float[] fArr = hw0VarA.h;
        Arrays.fill(fArr, 0.0f);
        fArr[hw0VarA.d] = 1.0f;
        nl0Var.i(hw0VarA);
        return hw0VarA;
    }

    public final hw0 j(Object obj) {
        if (obj == null) {
            return null;
        }
        if (this.h + 1 >= this.d) {
            n();
        }
        if (!(obj instanceof mh)) {
            return null;
        }
        mh mhVar = (mh) obj;
        hw0 hw0Var = mhVar.g;
        if (hw0Var == null) {
            mhVar.i();
            hw0Var = mhVar.g;
        }
        int i = hw0Var.b;
        tb tbVar = this.k;
        if (i != -1 && i <= this.a && ((hw0[]) tbVar.d)[i] != null) {
            return hw0Var;
        }
        if (i != -1) {
            hw0Var.c();
        }
        int i2 = this.a + 1;
        this.a = i2;
        this.h++;
        hw0Var.b = i2;
        hw0Var.i = SolverVariable$Type.UNRESTRICTED;
        ((hw0[]) tbVar.d)[i2] = hw0Var;
        return hw0Var;
    }

    public final m8 k() {
        boolean z = p;
        tb tbVar = this.k;
        if (z) {
            m8 m8Var = (m8) ((al0) tbVar.a).a();
            if (m8Var == null) {
                return new i90(tbVar);
            }
            m8Var.a = null;
            m8Var.d.clear();
            m8Var.b = 0.0f;
            m8Var.e = false;
            return m8Var;
        }
        m8 m8Var2 = (m8) ((al0) tbVar.b).a();
        if (m8Var2 == null) {
            return new m8(tbVar);
        }
        m8Var2.a = null;
        m8Var2.d.clear();
        m8Var2.b = 0.0f;
        m8Var2.e = false;
        return m8Var2;
    }

    public final hw0 l() {
        if (this.h + 1 >= this.d) {
            n();
        }
        hw0 hw0VarA = a(SolverVariable$Type.SLACK);
        int i = this.a + 1;
        this.a = i;
        this.h++;
        hw0VarA.b = i;
        ((hw0[]) this.k.d)[i] = hw0VarA;
        return hw0VarA;
    }

    public final void n() {
        int i = this.c * 2;
        this.c = i;
        this.e = (m8[]) Arrays.copyOf(this.e, i);
        tb tbVar = this.k;
        tbVar.d = (hw0[]) Arrays.copyOf((hw0[]) tbVar.d, this.c);
        int i2 = this.c;
        this.g = new boolean[i2];
        this.d = i2;
        this.j = i2;
    }

    public final void o(nl0 nl0Var) {
        tb tbVar;
        int i = 0;
        while (true) {
            if (i >= this.i) {
                break;
            }
            m8 m8Var = this.e[i];
            if (m8Var.a.i != SolverVariable$Type.UNRESTRICTED) {
                float f = 0.0f;
                if (m8Var.b < 0.0f) {
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
                            tbVar = this.k;
                            if (i6 >= i8) {
                                break;
                            }
                            m8 m8Var2 = this.e[i6];
                            if (m8Var2.a.i != SolverVariable$Type.UNRESTRICTED && !m8Var2.e && m8Var2.b < f) {
                                int i9 = i3;
                                while (i9 < this.h) {
                                    hw0 hw0Var = ((hw0[]) tbVar.d)[i9];
                                    float fB = m8Var2.d.b(hw0Var);
                                    if (fB > f) {
                                        for (int i10 = 0; i10 < 9; i10++) {
                                            float f3 = hw0Var.g[i10] / fB;
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
                            m8 m8Var3 = this.e[i4];
                            m8Var3.a.c = -1;
                            m8Var3.f(((hw0[]) tbVar.d)[i5]);
                            hw0 hw0Var2 = m8Var3.a;
                            hw0Var2.c = i4;
                            hw0Var2.d(m8Var3);
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
        p(nl0Var);
        for (int i11 = 0; i11 < this.i; i11++) {
            m8 m8Var4 = this.e[i11];
            m8Var4.a.e = m8Var4.b;
        }
    }

    public final void p(m8 m8Var) {
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
            hw0 hw0Var = m8Var.a;
            if (hw0Var != null) {
                this.g[hw0Var.b] = true;
            }
            hw0 hw0VarD = m8Var.d(this.g);
            if (hw0VarD != null) {
                boolean[] zArr = this.g;
                int i3 = hw0VarD.b;
                if (zArr[i3]) {
                    return;
                } else {
                    zArr[i3] = true;
                }
            }
            if (hw0VarD != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.i; i5++) {
                    m8 m8Var2 = this.e[i5];
                    if (m8Var2.a.i != SolverVariable$Type.UNRESTRICTED && !m8Var2.e && m8Var2.d.c(hw0VarD)) {
                        float fB = m8Var2.d.b(hw0VarD);
                        if (fB < 0.0f) {
                            float f2 = (-m8Var2.b) / fB;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    m8 m8Var3 = this.e[i4];
                    m8Var3.a.c = -1;
                    m8Var3.f(hw0VarD);
                    hw0 hw0Var2 = m8Var3.a;
                    hw0Var2.c = i4;
                    hw0Var2.d(m8Var3);
                }
            } else {
                z = true;
            }
        }
    }

    public final void q() {
        boolean z = p;
        tb tbVar = this.k;
        int i = 0;
        if (z) {
            while (true) {
                m8[] m8VarArr = this.e;
                if (i >= m8VarArr.length) {
                    return;
                }
                m8 m8Var = m8VarArr[i];
                if (m8Var != null) {
                    ((al0) tbVar.a).b(m8Var);
                }
                this.e[i] = null;
                i++;
            }
        } else {
            while (true) {
                m8[] m8VarArr2 = this.e;
                if (i >= m8VarArr2.length) {
                    return;
                }
                m8 m8Var2 = m8VarArr2[i];
                if (m8Var2 != null) {
                    ((al0) tbVar.b).b(m8Var2);
                }
                this.e[i] = null;
                i++;
            }
        }
    }

    public final void r() {
        tb tbVar;
        int i = 0;
        while (true) {
            tbVar = this.k;
            hw0[] hw0VarArr = (hw0[]) tbVar.d;
            if (i >= hw0VarArr.length) {
                break;
            }
            hw0 hw0Var = hw0VarArr[i];
            if (hw0Var != null) {
                hw0Var.c();
            }
            i++;
        }
        al0 al0Var = (al0) tbVar.c;
        hw0[] hw0VarArr2 = this.l;
        int length = this.m;
        al0Var.getClass();
        if (length > hw0VarArr2.length) {
            length = hw0VarArr2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            hw0 hw0Var2 = hw0VarArr2[i2];
            int i3 = al0Var.c;
            Object[] objArr = al0Var.b;
            if (i3 < objArr.length) {
                objArr[i3] = hw0Var2;
                al0Var.c = i3 + 1;
            }
        }
        this.m = 0;
        Arrays.fill((hw0[]) tbVar.d, (Object) null);
        this.a = 0;
        nl0 nl0Var = this.b;
        nl0Var.h = 0;
        nl0Var.b = 0.0f;
        this.h = 1;
        for (int i4 = 0; i4 < this.i; i4++) {
            this.e[i4].getClass();
        }
        q();
        this.i = 0;
        if (p) {
            this.n = new i90(tbVar);
        } else {
            this.n = new m8(tbVar);
        }
    }
}
