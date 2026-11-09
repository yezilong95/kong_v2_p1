package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class eb extends ut0 {
    public final ArrayList k;
    public int l;

    public eb(mg mgVar, int i) {
        mg mgVar2;
        super(mgVar);
        this.k = new ArrayList();
        this.f = i;
        mg mgVar3 = this.b;
        mg mgVarL = mgVar3.l(i);
        while (true) {
            mg mgVar4 = mgVarL;
            mgVar2 = mgVar3;
            mgVar3 = mgVar4;
            if (mgVar3 == null) {
                break;
            } else {
                mgVarL = mgVar3.l(this.f);
            }
        }
        this.b = mgVar2;
        int i2 = this.f;
        ut0 ut0Var = i2 == 0 ? mgVar2.d : i2 == 1 ? mgVar2.e : null;
        ArrayList<ut0> arrayList = this.k;
        arrayList.add(ut0Var);
        mg mgVarK = mgVar2.k(this.f);
        while (mgVarK != null) {
            int i3 = this.f;
            arrayList.add(i3 == 0 ? mgVarK.d : i3 == 1 ? mgVarK.e : null);
            mgVarK = mgVarK.k(this.f);
        }
        for (ut0 ut0Var2 : arrayList) {
            int i4 = this.f;
            if (i4 == 0) {
                ut0Var2.b.b = this;
            } else if (i4 == 1) {
                ut0Var2.b.c = this;
            }
        }
        if (this.f == 0 && ((ng) this.b.K).k0 && arrayList.size() > 1) {
            this.b = ((ut0) arrayList.get(arrayList.size() - 1)).b;
        }
        this.l = this.f == 0 ? this.b.Z : this.b.a0;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x01c1 A[PHI: r1 r25
      0x01c1: PHI (r1v57 int) = (r1v55 int), (r1v60 int) binds: [B:122:0x01bf, B:113:0x019f] A[DONT_GENERATE, DONT_INLINE]
      0x01c1: PHI (r25v1 int) = (r25v0 int), (r25v3 int) binds: [B:122:0x01bf, B:113:0x019f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0155  */
    @Override // defpackage.fk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(defpackage.fk r27) {
        /*
            Method dump skipped, instruction units count: 991
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eb.a(fk):void");
    }

    @Override // defpackage.ut0
    public final void d() {
        ArrayList arrayList = this.k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ut0) it.next()).d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        mg mgVar = ((ut0) arrayList.get(0)).b;
        mg mgVar2 = ((ut0) arrayList.get(size - 1)).b;
        int i = this.f;
        ik ikVar = this.i;
        ik ikVar2 = this.h;
        if (i == 0) {
            ag agVar = mgVar.y;
            ag agVar2 = mgVar2.A;
            ik ikVarI = ut0.i(agVar, 0);
            int iC = agVar.c();
            mg mgVarM = m();
            if (mgVarM != null) {
                iC = mgVarM.y.c();
            }
            if (ikVarI != null) {
                ut0.b(ikVar2, ikVarI, iC);
            }
            ik ikVarI2 = ut0.i(agVar2, 0);
            int iC2 = agVar2.c();
            mg mgVarN = n();
            if (mgVarN != null) {
                iC2 = mgVarN.A.c();
            }
            if (ikVarI2 != null) {
                ut0.b(ikVar, ikVarI2, -iC2);
            }
        } else {
            ag agVar3 = mgVar.z;
            ag agVar4 = mgVar2.B;
            ik ikVarI3 = ut0.i(agVar3, 1);
            int iC3 = agVar3.c();
            mg mgVarM2 = m();
            if (mgVarM2 != null) {
                iC3 = mgVarM2.z.c();
            }
            if (ikVarI3 != null) {
                ut0.b(ikVar2, ikVarI3, iC3);
            }
            ik ikVarI4 = ut0.i(agVar4, 1);
            int iC4 = agVar4.c();
            mg mgVarN2 = n();
            if (mgVarN2 != null) {
                iC4 = mgVarN2.B.c();
            }
            if (ikVarI4 != null) {
                ut0.b(ikVar, ikVarI4, -iC4);
            }
        }
        ikVar2.a = this;
        ikVar.a = this;
    }

    @Override // defpackage.ut0
    public final void e() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return;
            }
            ((ut0) arrayList.get(i)).e();
            i++;
        }
    }

    @Override // defpackage.ut0
    public final void f() {
        this.c = null;
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            ((ut0) it.next()).f();
        }
    }

    @Override // defpackage.ut0
    public final long j() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            ut0 ut0Var = (ut0) arrayList.get(i);
            j = ((long) ut0Var.i.f) + ut0Var.j() + j + ((long) ut0Var.h.f);
        }
        return j;
    }

    @Override // defpackage.ut0
    public final boolean k() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (!((ut0) arrayList.get(i)).k()) {
                return false;
            }
        }
        return true;
    }

    public final mg m() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return null;
            }
            mg mgVar = ((ut0) arrayList.get(i)).b;
            if (mgVar.X != 8) {
                return mgVar;
            }
            i++;
        }
    }

    public final mg n() {
        ArrayList arrayList = this.k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            mg mgVar = ((ut0) arrayList.get(size)).b;
            if (mgVar.X != 8) {
                return mgVar;
            }
        }
        return null;
    }

    public final String toString() {
        String strConcat = "ChainRun ".concat(this.f == 0 ? "horizontal : " : "vertical : ");
        for (ut0 ut0Var : this.k) {
            strConcat = hk.i(hk.i(strConcat, "<") + ut0Var, "> ");
        }
        return strConcat;
    }
}
