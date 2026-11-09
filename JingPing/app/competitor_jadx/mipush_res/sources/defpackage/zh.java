package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zh extends yh {
    public ArrayList g0 = new ArrayList();
    public final ou h0 = new ou(this);
    public final yn i0;
    public ha j0;
    public boolean k0;
    public final j90 l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public wc[] q0;
    public wc[] r0;
    public int s0;
    public boolean t0;
    public boolean u0;

    public zh() {
        yn ynVar = new yn();
        ynVar.b = true;
        ynVar.c = true;
        ynVar.e = new ArrayList();
        new ArrayList();
        ynVar.f = null;
        ynVar.g = new ga();
        ynVar.h = new ArrayList();
        ynVar.a = this;
        ynVar.d = this;
        this.i0 = ynVar;
        this.j0 = null;
        this.k0 = false;
        this.l0 = new j90();
        this.o0 = 0;
        this.p0 = 0;
        this.q0 = new wc[4];
        this.r0 = new wc[4];
        this.s0 = 263;
        this.t0 = false;
        this.u0 = false;
    }

    @Override // defpackage.yh
    public final void A(boolean z, boolean z2) {
        super.A(z, z2);
        int size = this.g0.size();
        for (int i = 0; i < size; i++) {
            ((yh) this.g0.get(i)).A(z, z2);
        }
    }

    public final void C(yh yhVar, int i) {
        if (i == 0) {
            int i2 = this.o0 + 1;
            wc[] wcVarArr = this.r0;
            if (i2 >= wcVarArr.length) {
                this.r0 = (wc[]) Arrays.copyOf(wcVarArr, wcVarArr.length * 2);
            }
            wc[] wcVarArr2 = this.r0;
            int i3 = this.o0;
            wcVarArr2[i3] = new wc(yhVar, 0, this.k0);
            this.o0 = i3 + 1;
            return;
        }
        if (i == 1) {
            int i4 = this.p0 + 1;
            wc[] wcVarArr3 = this.q0;
            if (i4 >= wcVarArr3.length) {
                this.q0 = (wc[]) Arrays.copyOf(wcVarArr3, wcVarArr3.length * 2);
            }
            wc[] wcVarArr4 = this.q0;
            int i5 = this.p0;
            wcVarArr4[i5] = new wc(yhVar, 1, this.k0);
            this.p0 = i5 + 1;
        }
    }

    public final void D(j90 j90Var) {
        a(j90Var);
        int size = this.g0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            yh yhVar = (yh) this.g0.get(i);
            boolean[] zArr = yhVar.I;
            zArr[0] = false;
            zArr[1] = false;
            if (yhVar instanceof q9) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                yh yhVar2 = (yh) this.g0.get(i2);
                if (yhVar2 instanceof q9) {
                    q9 q9Var = (q9) yhVar2;
                    for (int i3 = 0; i3 < q9Var.h0; i3++) {
                        yh yhVar3 = q9Var.g0[i3];
                        int i4 = q9Var.i0;
                        if (i4 == 0 || i4 == 1) {
                            yhVar3.I[0] = true;
                        } else if (i4 == 2 || i4 == 3) {
                            yhVar3.I[1] = true;
                        }
                    }
                }
            }
        }
        for (int i5 = 0; i5 < size; i5++) {
            yh yhVar4 = (yh) this.g0.get(i5);
            yhVar4.getClass();
            if ((yhVar4 instanceof cx) || (yhVar4 instanceof o20)) {
                yhVar4.a(j90Var);
            }
        }
        for (int i6 = 0; i6 < size; i6++) {
            yh yhVar5 = (yh) this.g0.get(i6);
            if (yhVar5 instanceof zh) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = yhVar5.J;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3) {
                    yhVar5.x(ConstraintWidget$DimensionBehaviour.FIXED);
                }
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3) {
                    yhVar5.y(ConstraintWidget$DimensionBehaviour.FIXED);
                }
                yhVar5.a(j90Var);
                if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3) {
                    yhVar5.x(constraintWidget$DimensionBehaviour);
                }
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3) {
                    yhVar5.y(constraintWidget$DimensionBehaviour2);
                }
            } else {
                yhVar5.h = -1;
                yhVar5.i = -1;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = this.J[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                if (constraintWidget$DimensionBehaviour4 != constraintWidget$DimensionBehaviour5 && yhVar5.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                    mh mhVar = yhVar5.y;
                    int i7 = mhVar.e;
                    int iM = m();
                    mh mhVar2 = yhVar5.A;
                    int i8 = iM - mhVar2.e;
                    mhVar.g = j90Var.j(mhVar);
                    mhVar2.g = j90Var.j(mhVar2);
                    j90Var.d(mhVar.g, i7);
                    j90Var.d(mhVar2.g, i8);
                    yhVar5.h = 2;
                    yhVar5.P = i7;
                    int i9 = i8 - i7;
                    yhVar5.L = i9;
                    int i10 = yhVar5.S;
                    if (i9 < i10) {
                        yhVar5.L = i10;
                    }
                }
                if (this.J[1] != constraintWidget$DimensionBehaviour5 && yhVar5.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                    mh mhVar3 = yhVar5.z;
                    int i11 = mhVar3.e;
                    int iJ = j();
                    mh mhVar4 = yhVar5.B;
                    int i12 = iJ - mhVar4.e;
                    mhVar3.g = j90Var.j(mhVar3);
                    mhVar4.g = j90Var.j(mhVar4);
                    j90Var.d(mhVar3.g, i11);
                    j90Var.d(mhVar4.g, i12);
                    if (yhVar5.R > 0 || yhVar5.X == 8) {
                        mh mhVar5 = yhVar5.C;
                        hw0 hw0VarJ = j90Var.j(mhVar5);
                        mhVar5.g = hw0VarJ;
                        j90Var.d(hw0VarJ, yhVar5.R + i11);
                    }
                    yhVar5.i = 2;
                    yhVar5.Q = i11;
                    int i13 = i12 - i11;
                    yhVar5.M = i13;
                    int i14 = yhVar5.T;
                    if (i13 < i14) {
                        yhVar5.M = i14;
                    }
                }
                if (!(yhVar5 instanceof cx) && !(yhVar5 instanceof o20)) {
                    yhVar5.a(j90Var);
                }
            }
        }
        if (this.o0 > 0) {
            l21.k(this, j90Var, 0);
        }
        if (this.p0 > 0) {
            l21.k(this, j90Var, 1);
        }
    }

    public final boolean E(int i, boolean z) {
        boolean z2;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        boolean z3;
        yn ynVar = this.i0;
        zh zhVar = ynVar.a;
        boolean z4 = false;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviourI = zhVar.i(0);
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviourI2 = zhVar.i(1);
        int iN = zhVar.n();
        int iO = zhVar.o();
        ArrayList<q71> arrayList = ynVar.e;
        i41 i41Var = zhVar.e;
        g30 g30Var = zhVar.d;
        if (z && (constraintWidget$DimensionBehaviourI == (constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) || constraintWidget$DimensionBehaviourI2 == constraintWidget$DimensionBehaviour)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = z;
                    break;
                }
                q71 q71Var = (q71) it.next();
                if (q71Var.f == i && !q71Var.k()) {
                    z3 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z3 && constraintWidget$DimensionBehaviourI == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) {
                    zhVar.x(ConstraintWidget$DimensionBehaviour.FIXED);
                    zhVar.z(ynVar.d(zhVar, 0));
                    g30Var.e.d(zhVar.m());
                }
            } else if (z3 && constraintWidget$DimensionBehaviourI2 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) {
                zhVar.y(ConstraintWidget$DimensionBehaviour.FIXED);
                zhVar.w(ynVar.d(zhVar, 1));
                i41Var.e.d(zhVar.j());
            }
        }
        if (i == 0) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = zhVar.J[0];
            if (constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                int iM = zhVar.m() + iN;
                g30Var.i.d(iM);
                g30Var.e.d(iM - iN);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = zhVar.J[1];
            if (constraintWidget$DimensionBehaviour3 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour3 == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                int iJ = zhVar.j() + iO;
                i41Var.i.d(iJ);
                i41Var.e.d(iJ - iO);
                z2 = true;
            }
            z2 = false;
        }
        ynVar.g();
        for (q71 q71Var2 : arrayList) {
            if (q71Var2.f == i && (q71Var2.b != zhVar || q71Var2.g)) {
                q71Var2.e();
            }
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z4 = true;
                break;
            }
            q71 q71Var3 = (q71) it2.next();
            if (q71Var3.f == i && (z2 || q71Var3.b != zhVar)) {
                if (!q71Var3.h.j || !q71Var3.i.j || (!(q71Var3 instanceof xc) && !q71Var3.e.j)) {
                    break;
                }
            }
        }
        zhVar.x(constraintWidget$DimensionBehaviourI);
        zhVar.y(constraintWidget$DimensionBehaviourI2);
        return z4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0203 A[PHI: r0 r13
      0x0203: PHI (r0v21 ??) = (r0v20 ??), (r0v23 ??), (r0v23 ??), (r0v23 ??) binds: [B:88:0x01c3, B:97:0x01eb, B:98:0x01ed, B:100:0x01f3] A[DONT_GENERATE, DONT_INLINE]
      0x0203: PHI (r13v5 boolean) = (r13v4 boolean), (r13v7 boolean), (r13v7 boolean), (r13v7 boolean) binds: [B:88:0x01c3, B:97:0x01eb, B:98:0x01ed, B:100:0x01f3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r0v61 */
    /* JADX WARN: Type inference failed for: r0v62 */
    /* JADX WARN: Type inference failed for: r0v63 */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v38 */
    /* JADX WARN: Type inference failed for: r12v39 */
    /* JADX WARN: Type inference failed for: r12v40 */
    /* JADX WARN: Type inference failed for: r12v41 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F() {
        /*
            Method dump skipped, instruction units count: 542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zh.F():void");
    }

    public final void G() {
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2;
        ArrayList arrayList = (ArrayList) this.h0.F;
        arrayList.clear();
        int size = this.g0.size();
        for (int i = 0; i < size; i++) {
            yh yhVar = (yh) this.g0.get(i);
            ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = yhVar.J;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = constraintWidget$DimensionBehaviourArr[0];
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
            if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour4 || constraintWidget$DimensionBehaviour3 == (constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.MATCH_PARENT) || (constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1]) == constraintWidget$DimensionBehaviour4 || constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour) {
                arrayList.add(yhVar);
            }
        }
        this.i0.b = true;
    }

    @Override // defpackage.yh
    public final void t() {
        this.l0.r();
        this.m0 = 0;
        this.n0 = 0;
        this.g0.clear();
        super.t();
    }

    @Override // defpackage.yh
    public final void v(tb tbVar) {
        super.v(tbVar);
        int size = this.g0.size();
        for (int i = 0; i < size; i++) {
            ((yh) this.g0.get(i)).v(tbVar);
        }
    }
}
