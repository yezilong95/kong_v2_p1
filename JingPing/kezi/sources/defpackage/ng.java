package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ng extends mg {
    public ArrayList g0 = new ArrayList();
    public final po h0 = new po(this);
    public final gk i0;
    public m8 j0;
    public boolean k0;
    public final f20 l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public db[] q0;
    public db[] r0;
    public int s0;
    public boolean t0;
    public boolean u0;

    public ng() {
        gk gkVar = new gk();
        gkVar.b = true;
        gkVar.c = true;
        gkVar.e = new ArrayList();
        new ArrayList();
        gkVar.f = null;
        gkVar.g = new l8();
        gkVar.h = new ArrayList();
        gkVar.a = this;
        gkVar.d = this;
        this.i0 = gkVar;
        this.j0 = null;
        this.k0 = false;
        this.l0 = new f20();
        this.o0 = 0;
        this.p0 = 0;
        this.q0 = new db[4];
        this.r0 = new db[4];
        this.s0 = 263;
        this.t0 = false;
        this.u0 = false;
    }

    @Override // defpackage.mg
    public final void A(boolean z, boolean z2) {
        super.A(z, z2);
        int size = this.g0.size();
        for (int i = 0; i < size; i++) {
            ((mg) this.g0.get(i)).A(z, z2);
        }
    }

    public final void C(mg mgVar, int i) {
        if (i == 0) {
            int i2 = this.o0 + 1;
            db[] dbVarArr = this.r0;
            if (i2 >= dbVarArr.length) {
                this.r0 = (db[]) Arrays.copyOf(dbVarArr, dbVarArr.length * 2);
            }
            db[] dbVarArr2 = this.r0;
            int i3 = this.o0;
            dbVarArr2[i3] = new db(mgVar, 0, this.k0);
            this.o0 = i3 + 1;
            return;
        }
        if (i == 1) {
            int i4 = this.p0 + 1;
            db[] dbVarArr3 = this.q0;
            if (i4 >= dbVarArr3.length) {
                this.q0 = (db[]) Arrays.copyOf(dbVarArr3, dbVarArr3.length * 2);
            }
            db[] dbVarArr4 = this.q0;
            int i5 = this.p0;
            dbVarArr4[i5] = new db(mgVar, 1, this.k0);
            this.p0 = i5 + 1;
        }
    }

    public final void D(f20 f20Var) {
        a(f20Var);
        int size = this.g0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            mg mgVar = (mg) this.g0.get(i);
            boolean[] zArr = mgVar.I;
            zArr[0] = false;
            zArr[1] = false;
            if (mgVar instanceof v7) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                mg mgVar2 = (mg) this.g0.get(i2);
                if (mgVar2 instanceof v7) {
                    v7 v7Var = (v7) mgVar2;
                    for (int i3 = 0; i3 < v7Var.h0; i3++) {
                        mg mgVar3 = v7Var.g0[i3];
                        int i4 = v7Var.i0;
                        if (i4 == 0 || i4 == 1) {
                            mgVar3.I[0] = true;
                        } else if (i4 == 2 || i4 == 3) {
                            mgVar3.I[1] = true;
                        }
                    }
                }
            }
        }
        for (int i5 = 0; i5 < size; i5++) {
            mg mgVar4 = (mg) this.g0.get(i5);
            mgVar4.getClass();
            if ((mgVar4 instanceof pq) || (mgVar4 instanceof kv)) {
                mgVar4.a(f20Var);
            }
        }
        for (int i6 = 0; i6 < size; i6++) {
            mg mgVar5 = (mg) this.g0.get(i6);
            if (mgVar5 instanceof ng) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = mgVar5.J;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3) {
                    mgVar5.x(ConstraintWidget$DimensionBehaviour.FIXED);
                }
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3) {
                    mgVar5.y(ConstraintWidget$DimensionBehaviour.FIXED);
                }
                mgVar5.a(f20Var);
                if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3) {
                    mgVar5.x(constraintWidget$DimensionBehaviour);
                }
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3) {
                    mgVar5.y(constraintWidget$DimensionBehaviour2);
                }
            } else {
                mgVar5.h = -1;
                mgVar5.i = -1;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = this.J[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                if (constraintWidget$DimensionBehaviour4 != constraintWidget$DimensionBehaviour5 && mgVar5.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                    ag agVar = mgVar5.y;
                    int i7 = agVar.e;
                    int iM = m();
                    ag agVar2 = mgVar5.A;
                    int i8 = iM - agVar2.e;
                    agVar.g = f20Var.j(agVar);
                    agVar2.g = f20Var.j(agVar2);
                    f20Var.d(agVar.g, i7);
                    f20Var.d(agVar2.g, i8);
                    mgVar5.h = 2;
                    mgVar5.P = i7;
                    int i9 = i8 - i7;
                    mgVar5.L = i9;
                    int i10 = mgVar5.S;
                    if (i9 < i10) {
                        mgVar5.L = i10;
                    }
                }
                if (this.J[1] != constraintWidget$DimensionBehaviour5 && mgVar5.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                    ag agVar3 = mgVar5.z;
                    int i11 = agVar3.e;
                    int iJ = j();
                    ag agVar4 = mgVar5.B;
                    int i12 = iJ - agVar4.e;
                    agVar3.g = f20Var.j(agVar3);
                    agVar4.g = f20Var.j(agVar4);
                    f20Var.d(agVar3.g, i11);
                    f20Var.d(agVar4.g, i12);
                    if (mgVar5.R > 0 || mgVar5.X == 8) {
                        ag agVar5 = mgVar5.C;
                        sj0 sj0VarJ = f20Var.j(agVar5);
                        agVar5.g = sj0VarJ;
                        f20Var.d(sj0VarJ, mgVar5.R + i11);
                    }
                    mgVar5.i = 2;
                    mgVar5.Q = i11;
                    int i13 = i12 - i11;
                    mgVar5.M = i13;
                    int i14 = mgVar5.T;
                    if (i13 < i14) {
                        mgVar5.M = i14;
                    }
                }
                if (!(mgVar5 instanceof pq) && !(mgVar5 instanceof kv)) {
                    mgVar5.a(f20Var);
                }
            }
        }
        if (this.o0 > 0) {
            rp0.d(this, f20Var, 0);
        }
        if (this.p0 > 0) {
            rp0.d(this, f20Var, 1);
        }
    }

    public final boolean E(int i, boolean z) {
        boolean z2;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        boolean z3;
        gk gkVar = this.i0;
        ng ngVar = gkVar.a;
        boolean z4 = false;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviourI = ngVar.i(0);
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviourI2 = ngVar.i(1);
        int iN = ngVar.n();
        int iO = ngVar.o();
        ArrayList<ut0> arrayList = gkVar.e;
        dr0 dr0Var = ngVar.e;
        yv yvVar = ngVar.d;
        if (z && (constraintWidget$DimensionBehaviourI == (constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) || constraintWidget$DimensionBehaviourI2 == constraintWidget$DimensionBehaviour)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = z;
                    break;
                }
                ut0 ut0Var = (ut0) it.next();
                if (ut0Var.f == i && !ut0Var.k()) {
                    z3 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z3 && constraintWidget$DimensionBehaviourI == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) {
                    ngVar.x(ConstraintWidget$DimensionBehaviour.FIXED);
                    ngVar.z(gkVar.d(ngVar, 0));
                    yvVar.e.d(ngVar.m());
                }
            } else if (z3 && constraintWidget$DimensionBehaviourI2 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) {
                ngVar.y(ConstraintWidget$DimensionBehaviour.FIXED);
                ngVar.w(gkVar.d(ngVar, 1));
                dr0Var.e.d(ngVar.j());
            }
        }
        if (i == 0) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ngVar.J[0];
            if (constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                int iM = ngVar.m() + iN;
                yvVar.i.d(iM);
                yvVar.e.d(iM - iN);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ngVar.J[1];
            if (constraintWidget$DimensionBehaviour3 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour3 == ConstraintWidget$DimensionBehaviour.MATCH_PARENT) {
                int iJ = ngVar.j() + iO;
                dr0Var.i.d(iJ);
                dr0Var.e.d(iJ - iO);
                z2 = true;
            }
            z2 = false;
        }
        gkVar.g();
        for (ut0 ut0Var2 : arrayList) {
            if (ut0Var2.f == i && (ut0Var2.b != ngVar || ut0Var2.g)) {
                ut0Var2.e();
            }
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z4 = true;
                break;
            }
            ut0 ut0Var3 = (ut0) it2.next();
            if (ut0Var3.f == i && (z2 || ut0Var3.b != ngVar)) {
                if (!ut0Var3.h.j || !ut0Var3.i.j || (!(ut0Var3 instanceof eb) && !ut0Var3.e.j)) {
                    break;
                }
            }
        }
        ngVar.x(constraintWidget$DimensionBehaviourI);
        ngVar.y(constraintWidget$DimensionBehaviourI2);
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F() {
        /*
            Method dump skipped, instruction units count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ng.F():void");
    }

    public final void G() {
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2;
        ArrayList arrayList = (ArrayList) this.h0.c;
        arrayList.clear();
        int size = this.g0.size();
        for (int i = 0; i < size; i++) {
            mg mgVar = (mg) this.g0.get(i);
            ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = mgVar.J;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = constraintWidget$DimensionBehaviourArr[0];
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
            if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour4 || constraintWidget$DimensionBehaviour3 == (constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.MATCH_PARENT) || (constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1]) == constraintWidget$DimensionBehaviour4 || constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour) {
                arrayList.add(mgVar);
            }
        }
        this.i0.b = true;
    }

    @Override // defpackage.mg
    public final void t() {
        this.l0.r();
        this.m0 = 0;
        this.n0 = 0;
        this.g0.clear();
        super.t();
    }

    @Override // defpackage.mg
    public final void v(ba baVar) {
        super.v(baVar);
        int size = this.g0.size();
        for (int i = 0; i < size; i++) {
            ((mg) this.g0.get(i)).v(baVar);
        }
    }
}
