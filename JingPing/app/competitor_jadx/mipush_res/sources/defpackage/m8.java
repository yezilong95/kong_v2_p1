package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class m8 {
    public l8 d;
    public hw0 a = null;
    public float b = 0.0f;
    public ArrayList c = new ArrayList();
    public boolean e = false;

    public m8(tb tbVar) {
        this.d = new i8(this, tbVar);
    }

    public final void a(j90 j90Var, int i) {
        this.d.j(j90Var.i(i), 1.0f);
        this.d.j(j90Var.i(i), -1.0f);
    }

    public final void b(hw0 hw0Var, hw0 hw0Var2, hw0 hw0Var3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.d.j(hw0Var, 1.0f);
            this.d.j(hw0Var2, -1.0f);
            this.d.j(hw0Var3, -1.0f);
        } else {
            this.d.j(hw0Var, -1.0f);
            this.d.j(hw0Var2, 1.0f);
            this.d.j(hw0Var3, 1.0f);
        }
    }

    public final void c(hw0 hw0Var, hw0 hw0Var2, hw0 hw0Var3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.d.j(hw0Var, 1.0f);
            this.d.j(hw0Var2, -1.0f);
            this.d.j(hw0Var3, 1.0f);
        } else {
            this.d.j(hw0Var, -1.0f);
            this.d.j(hw0Var2, 1.0f);
            this.d.j(hw0Var3, -1.0f);
        }
    }

    public hw0 d(boolean[] zArr) {
        return e(zArr, null);
    }

    public final hw0 e(boolean[] zArr, hw0 hw0Var) {
        SolverVariable$Type solverVariable$Type;
        int iD = this.d.d();
        hw0 hw0Var2 = null;
        float f = 0.0f;
        for (int i = 0; i < iD; i++) {
            float fA = this.d.a(i);
            if (fA < 0.0f) {
                hw0 hw0VarF = this.d.f(i);
                if ((zArr == null || !zArr[hw0VarF.b]) && hw0VarF != hw0Var && (((solverVariable$Type = hw0VarF.i) == SolverVariable$Type.SLACK || solverVariable$Type == SolverVariable$Type.ERROR) && fA < f)) {
                    f = fA;
                    hw0Var2 = hw0VarF;
                }
            }
        }
        return hw0Var2;
    }

    public final void f(hw0 hw0Var) {
        hw0 hw0Var2 = this.a;
        if (hw0Var2 != null) {
            this.d.j(hw0Var2, -1.0f);
            this.a = null;
        }
        float fE = this.d.e(hw0Var, true) * (-1.0f);
        this.a = hw0Var;
        if (fE == 1.0f) {
            return;
        }
        this.b /= fE;
        this.d.i(fE);
    }

    public final void g(hw0 hw0Var, boolean z) {
        if (hw0Var.f) {
            float fB = this.d.b(hw0Var);
            this.b = (hw0Var.e * fB) + this.b;
            this.d.e(hw0Var, z);
            if (z) {
                hw0Var.b(this);
            }
        }
    }

    public void h(m8 m8Var, boolean z) {
        float fH = this.d.h(m8Var, z);
        this.b = (m8Var.b * fH) + this.b;
        if (z) {
            m8Var.a.b(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            hw0 r0 = r10.a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            hw0 r1 = r10.a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = defpackage.zn.m(r0, r1)
            float r1 = r10.b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L35
            java.lang.StringBuilder r0 = defpackage.zn.p(r0)
            float r1 = r10.b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r3
            goto L36
        L35:
            r1 = r4
        L36:
            l8 r5 = r10.d
            int r5 = r5.d()
        L3c:
            if (r4 >= r5) goto L9c
            l8 r6 = r10.d
            hw0 r6 = r6.f(r4)
            if (r6 != 0) goto L47
            goto L99
        L47:
            l8 r7 = r10.d
            float r7 = r7.a(r4)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L52
            goto L99
        L52:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L66
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L76
            java.lang.String r1 = "- "
            java.lang.String r0 = defpackage.zn.m(r0, r1)
        L64:
            float r7 = r7 * r9
            goto L76
        L66:
            if (r8 <= 0) goto L6f
            java.lang.String r1 = " + "
            java.lang.String r0 = defpackage.zn.m(r0, r1)
            goto L76
        L6f:
            java.lang.String r1 = " - "
            java.lang.String r0 = defpackage.zn.m(r0, r1)
            goto L64
        L76:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L81
            java.lang.String r0 = defpackage.zn.m(r0, r6)
            goto L98
        L81:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L98:
            r1 = r3
        L99:
            int r4 = r4 + 1
            goto L3c
        L9c:
            if (r1 != 0) goto La4
            java.lang.String r1 = "0.0"
            java.lang.String r0 = defpackage.zn.m(r0, r1)
        La4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.m8.toString():java.lang.String");
    }
}
