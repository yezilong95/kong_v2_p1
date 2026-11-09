package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class xc extends q71 {
    public final ArrayList k;
    public int l;

    public xc(yh yhVar, int i) {
        yh yhVar2;
        super(yhVar);
        this.k = new ArrayList();
        this.f = i;
        yh yhVar3 = this.b;
        yh yhVarL = yhVar3.l(i);
        while (true) {
            yh yhVar4 = yhVarL;
            yhVar2 = yhVar3;
            yhVar3 = yhVar4;
            if (yhVar3 == null) {
                break;
            } else {
                yhVarL = yhVar3.l(this.f);
            }
        }
        this.b = yhVar2;
        int i2 = this.f;
        xn xnVar = i2 == 0 ? yhVar2.d : i2 == 1 ? yhVar2.e : null;
        ArrayList<q71> arrayList = this.k;
        arrayList.add(xnVar);
        yh yhVarK = yhVar2.k(this.f);
        while (yhVarK != null) {
            int i3 = this.f;
            arrayList.add(i3 == 0 ? yhVarK.d : i3 == 1 ? yhVarK.e : null);
            yhVarK = yhVarK.k(this.f);
        }
        for (q71 q71Var : arrayList) {
            int i4 = this.f;
            if (i4 == 0) {
                q71Var.b.b = this;
            } else if (i4 == 1) {
                q71Var.b.c = this;
            }
        }
        if (this.f == 0 && ((zh) this.b.K).k0 && arrayList.size() > 1) {
            this.b = ((q71) arrayList.get(arrayList.size() - 1)).b;
        }
        this.l = this.f == 0 ? this.b.Z : this.b.a0;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x01c1 A[PHI: r1 r25
      0x01c1: PHI (r1v57 int) = (r1v55 int), (r1v60 int) binds: [B:122:0x01bf, B:113:0x019f] A[DONT_GENERATE, DONT_INLINE]
      0x01c1: PHI (r25v1 int) = (r25v0 int), (r25v3 int) binds: [B:122:0x01bf, B:113:0x019f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0155  */
    @Override // defpackage.xn
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(defpackage.xn r27) {
        /*
            Method dump skipped, instruction units count: 991
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xc.a(xn):void");
    }

    @Override // defpackage.q71
    public final void d() {
        ArrayList arrayList = this.k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((q71) it.next()).d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        yh yhVar = ((q71) arrayList.get(0)).b;
        yh yhVar2 = ((q71) arrayList.get(size - 1)).b;
        int i = this.f;
        ao aoVar = this.i;
        ao aoVar2 = this.h;
        if (i == 0) {
            mh mhVar = yhVar.y;
            mh mhVar2 = yhVar2.A;
            ao aoVarI = q71.i(mhVar, 0);
            int iC = mhVar.c();
            yh yhVarM = m();
            if (yhVarM != null) {
                iC = yhVarM.y.c();
            }
            if (aoVarI != null) {
                q71.b(aoVar2, aoVarI, iC);
            }
            ao aoVarI2 = q71.i(mhVar2, 0);
            int iC2 = mhVar2.c();
            yh yhVarN = n();
            if (yhVarN != null) {
                iC2 = yhVarN.A.c();
            }
            if (aoVarI2 != null) {
                q71.b(aoVar, aoVarI2, -iC2);
            }
        } else {
            mh mhVar3 = yhVar.z;
            mh mhVar4 = yhVar2.B;
            ao aoVarI3 = q71.i(mhVar3, 1);
            int iC3 = mhVar3.c();
            yh yhVarM2 = m();
            if (yhVarM2 != null) {
                iC3 = yhVarM2.z.c();
            }
            if (aoVarI3 != null) {
                q71.b(aoVar2, aoVarI3, iC3);
            }
            ao aoVarI4 = q71.i(mhVar4, 1);
            int iC4 = mhVar4.c();
            yh yhVarN2 = n();
            if (yhVarN2 != null) {
                iC4 = yhVarN2.B.c();
            }
            if (aoVarI4 != null) {
                q71.b(aoVar, aoVarI4, -iC4);
            }
        }
        aoVar2.a = this;
        aoVar.a = this;
    }

    @Override // defpackage.q71
    public final void e() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return;
            }
            ((q71) arrayList.get(i)).e();
            i++;
        }
    }

    @Override // defpackage.q71
    public final void f() {
        this.c = null;
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            ((q71) it.next()).f();
        }
    }

    @Override // defpackage.q71
    public final long j() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            q71 q71Var = (q71) arrayList.get(i);
            j = ((long) q71Var.i.f) + q71Var.j() + j + ((long) q71Var.h.f);
        }
        return j;
    }

    @Override // defpackage.q71
    public final boolean k() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (!((q71) arrayList.get(i)).k()) {
                return false;
            }
        }
        return true;
    }

    public final yh m() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return null;
            }
            yh yhVar = ((q71) arrayList.get(i)).b;
            if (yhVar.X != 8) {
                return yhVar;
            }
            i++;
        }
    }

    public final yh n() {
        ArrayList arrayList = this.k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            yh yhVar = ((q71) arrayList.get(size)).b;
            if (yhVar.X != 8) {
                return yhVar;
            }
        }
        return null;
    }

    public final String toString() {
        String strConcat = "ChainRun ".concat(this.f == 0 ? "horizontal : " : "vertical : ");
        for (q71 q71Var : this.k) {
            strConcat = zn.m(zn.m(strConcat, "<") + q71Var, "> ");
        }
        return strConcat;
    }
}
