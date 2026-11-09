package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e7 {
    public d7 d;
    public sj0 a = null;
    public float b = 0.0f;
    public ArrayList c = new ArrayList();
    public boolean e = false;

    public e7(ba baVar) {
        this.d = new a7(this, baVar);
    }

    public final void a(f20 f20Var, int i) {
        this.d.i(f20Var.i(i), 1.0f);
        this.d.i(f20Var.i(i), -1.0f);
    }

    public final void b(sj0 sj0Var, sj0 sj0Var2, sj0 sj0Var3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.d.i(sj0Var, 1.0f);
            this.d.i(sj0Var2, -1.0f);
            this.d.i(sj0Var3, -1.0f);
        } else {
            this.d.i(sj0Var, -1.0f);
            this.d.i(sj0Var2, 1.0f);
            this.d.i(sj0Var3, 1.0f);
        }
    }

    public final void c(sj0 sj0Var, sj0 sj0Var2, sj0 sj0Var3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.d.i(sj0Var, 1.0f);
            this.d.i(sj0Var2, -1.0f);
            this.d.i(sj0Var3, 1.0f);
        } else {
            this.d.i(sj0Var, -1.0f);
            this.d.i(sj0Var2, 1.0f);
            this.d.i(sj0Var3, -1.0f);
        }
    }

    public sj0 d(boolean[] zArr) {
        return e(zArr, null);
    }

    public final sj0 e(boolean[] zArr, sj0 sj0Var) {
        SolverVariable$Type solverVariable$Type;
        int iK = this.d.k();
        sj0 sj0Var2 = null;
        float f = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.d.a(i);
            if (fA < 0.0f) {
                sj0 sj0VarE = this.d.e(i);
                if ((zArr == null || !zArr[sj0VarE.b]) && sj0VarE != sj0Var && (((solverVariable$Type = sj0VarE.i) == SolverVariable$Type.SLACK || solverVariable$Type == SolverVariable$Type.ERROR) && fA < f)) {
                    f = fA;
                    sj0Var2 = sj0VarE;
                }
            }
        }
        return sj0Var2;
    }

    public final void f(sj0 sj0Var) {
        sj0 sj0Var2 = this.a;
        if (sj0Var2 != null) {
            this.d.i(sj0Var2, -1.0f);
            this.a = null;
        }
        float fD = this.d.d(sj0Var, true) * (-1.0f);
        this.a = sj0Var;
        if (fD == 1.0f) {
            return;
        }
        this.b /= fD;
        this.d.h(fD);
    }

    public final void g(sj0 sj0Var, boolean z) {
        if (sj0Var.f) {
            float fB = this.d.b(sj0Var);
            this.b = (sj0Var.e * fB) + this.b;
            this.d.d(sj0Var, z);
            if (z) {
                sj0Var.b(this);
            }
        }
    }

    public void h(e7 e7Var, boolean z) {
        float fG = this.d.g(e7Var, z);
        this.b = (e7Var.b * fG) + this.b;
        if (z) {
            e7Var.a.b(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            sj0 r0 = r10.a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            sj0 r1 = r10.a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = defpackage.hk.i(r0, r1)
            float r1 = r10.b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L35
            java.lang.StringBuilder r0 = defpackage.hk.l(r0)
            float r1 = r10.b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r3
            goto L36
        L35:
            r1 = r4
        L36:
            d7 r5 = r10.d
            int r5 = r5.k()
        L3c:
            if (r4 >= r5) goto L9c
            d7 r6 = r10.d
            sj0 r6 = r6.e(r4)
            if (r6 != 0) goto L47
            goto L99
        L47:
            d7 r7 = r10.d
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
            java.lang.String r0 = defpackage.hk.i(r0, r1)
        L64:
            float r7 = r7 * r9
            goto L76
        L66:
            if (r8 <= 0) goto L6f
            java.lang.String r1 = " + "
            java.lang.String r0 = defpackage.hk.i(r0, r1)
            goto L76
        L6f:
            java.lang.String r1 = " - "
            java.lang.String r0 = defpackage.hk.i(r0, r1)
            goto L64
        L76:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L81
            java.lang.String r0 = defpackage.hk.i(r0, r6)
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
            java.lang.String r0 = defpackage.hk.i(r0, r1)
        La4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e7.toString():java.lang.String");
    }
}
