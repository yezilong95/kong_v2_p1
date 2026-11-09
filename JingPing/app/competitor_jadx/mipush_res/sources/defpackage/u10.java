package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class u10 extends a0 {
    public d0 E;
    public d0 F;
    public d0 G = null;

    public u10(d0 d0Var, d0 d0Var2) {
        this.E = d0Var;
        this.F = d0Var2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.u10 g(java.lang.Object r4) {
        /*
            boolean r0 = r4 instanceof defpackage.u10
            if (r0 == 0) goto L7
            u10 r4 = (defpackage.u10) r4
            return r4
        L7:
            if (r4 == 0) goto L75
            u10 r0 = new u10
            j0 r4 = defpackage.j0.q(r4)
            r0.<init>()
            r1 = 0
            t r1 = r4.r(r1)
            d0 r1 = defpackage.d0.s(r1)
            r0.E = r1
            d0 r2 = defpackage.rp0.e
            boolean r2 = r1.k(r2)
            r3 = 1
            if (r2 == 0) goto L37
            int r1 = r4.size()
            if (r1 <= r3) goto L5d
        L2c:
            t r1 = r4.r(r3)
            d0 r1 = defpackage.d0.s(r1)
            r0.F = r1
            goto L5d
        L37:
            d0 r2 = defpackage.rp0.f
            boolean r2 = r1.k(r2)
            if (r2 != 0) goto L57
            d0 r2 = defpackage.rp0.g
            boolean r2 = r1.k(r2)
            if (r2 != 0) goto L57
            d0 r2 = defpackage.rp0.h
            boolean r1 = r1.k(r2)
            if (r1 == 0) goto L50
            goto L57
        L50:
            int r1 = r4.size()
            if (r1 <= r3) goto L5d
            goto L2c
        L57:
            int r1 = r4.size()
            if (r1 > r3) goto L6d
        L5d:
            int r1 = r4.size()
            r2 = 2
            if (r1 <= r2) goto L6c
            t r4 = r4.r(r2)
            d0 r4 = (defpackage.d0) r4
            r0.G = r4
        L6c:
            return r0
        L6d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "digestParamSet expected to be absent"
            r4.<init>(r0)
            throw r4
        L75:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u10.g(java.lang.Object):u10");
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(3);
        uVar.c(this.E);
        d0 d0Var = this.F;
        if (d0Var != null) {
            uVar.c(d0Var);
        }
        d0 d0Var2 = this.G;
        if (d0Var2 != null) {
            uVar.c(d0Var2);
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
