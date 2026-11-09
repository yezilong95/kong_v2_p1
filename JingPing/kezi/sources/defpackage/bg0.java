package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class bg0 {
    public ut0 a;
    public ArrayList b;

    public static long a(ik ikVar, long j) {
        ut0 ut0Var = ikVar.d;
        if (ut0Var instanceof uv) {
            return j;
        }
        ArrayList arrayList = ikVar.k;
        int size = arrayList.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            fk fkVar = (fk) arrayList.get(i);
            if (fkVar instanceof ik) {
                ik ikVar2 = (ik) fkVar;
                if (ikVar2.d != ut0Var) {
                    jMin = Math.min(jMin, a(ikVar2, ((long) ikVar2.f) + j));
                }
            }
        }
        if (ikVar != ut0Var.i) {
            return jMin;
        }
        long j2 = ut0Var.j();
        ik ikVar3 = ut0Var.h;
        long j3 = j - j2;
        return Math.min(Math.min(jMin, a(ikVar3, j3)), j3 - ((long) ikVar3.f));
    }

    public static long b(ik ikVar, long j) {
        ut0 ut0Var = ikVar.d;
        if (ut0Var instanceof uv) {
            return j;
        }
        ArrayList arrayList = ikVar.k;
        int size = arrayList.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            fk fkVar = (fk) arrayList.get(i);
            if (fkVar instanceof ik) {
                ik ikVar2 = (ik) fkVar;
                if (ikVar2.d != ut0Var) {
                    jMax = Math.max(jMax, b(ikVar2, ((long) ikVar2.f) + j));
                }
            }
        }
        if (ikVar != ut0Var.h) {
            return jMax;
        }
        long j2 = ut0Var.j();
        ik ikVar3 = ut0Var.i;
        long j3 = j + j2;
        return Math.max(Math.max(jMax, b(ikVar3, j3)), j3 - ((long) ikVar3.f));
    }
}
