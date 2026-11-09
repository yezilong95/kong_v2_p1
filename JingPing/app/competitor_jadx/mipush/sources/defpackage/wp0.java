package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class wp0 {
    public q71 a;
    public ArrayList b;

    public static long a(ao aoVar, long j) {
        q71 q71Var = aoVar.d;
        if (q71Var instanceof a30) {
            return j;
        }
        ArrayList arrayList = aoVar.k;
        int size = arrayList.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            xn xnVar = (xn) arrayList.get(i);
            if (xnVar instanceof ao) {
                ao aoVar2 = (ao) xnVar;
                if (aoVar2.d != q71Var) {
                    jMin = Math.min(jMin, a(aoVar2, ((long) aoVar2.f) + j));
                }
            }
        }
        if (aoVar != q71Var.i) {
            return jMin;
        }
        long j2 = q71Var.j();
        ao aoVar3 = q71Var.h;
        long j3 = j - j2;
        return Math.min(Math.min(jMin, a(aoVar3, j3)), j3 - ((long) aoVar3.f));
    }

    public static long b(ao aoVar, long j) {
        q71 q71Var = aoVar.d;
        if (q71Var instanceof a30) {
            return j;
        }
        ArrayList arrayList = aoVar.k;
        int size = arrayList.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            xn xnVar = (xn) arrayList.get(i);
            if (xnVar instanceof ao) {
                ao aoVar2 = (ao) xnVar;
                if (aoVar2.d != q71Var) {
                    jMax = Math.max(jMax, b(aoVar2, ((long) aoVar2.f) + j));
                }
            }
        }
        if (aoVar != q71Var.h) {
            return jMax;
        }
        long j2 = q71Var.j();
        ao aoVar3 = q71Var.i;
        long j3 = j + j2;
        return Math.max(Math.max(jMax, b(aoVar3, j3)), j3 - ((long) aoVar3.f));
    }
}
