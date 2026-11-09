package defpackage;

import androidx.work.impl.WorkDatabaseVersions;

/* JADX INFO: loaded from: classes.dex */
public final class nr extends mr {
    public final /* synthetic */ int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ nr(xq xqVar, l21 l21Var, l21 l21Var2, int i) {
        super(xqVar, l21Var, l21Var2, 0);
        this.h = i;
    }

    private final or A(or orVar) {
        long[] jArr;
        long[] jArr2;
        fu0 fu0Var;
        fu0 fu0Var2;
        fu0 fu0Var3;
        if (l()) {
            return orVar;
        }
        if (orVar.l()) {
            return this;
        }
        fu0 fu0Var4 = (fu0) this.b;
        fu0 fu0Var5 = (fu0) orVar.b;
        boolean zH = s90.H(fu0Var4.U);
        xq xqVar = this.a;
        if (zH) {
            return s90.H(fu0Var5.U) ? xqVar.k() : orVar.a(this);
        }
        fu0 fu0Var6 = (fu0) this.c;
        fu0 fu0Var7 = (fu0) this.d[0];
        fu0 fu0Var8 = (fu0) orVar.c;
        fu0 fu0Var9 = (fu0) orVar.j();
        long[] jArr3 = new long[9];
        long[] jArr4 = new long[9];
        long[] jArr5 = new long[9];
        long[] jArr6 = new long[9];
        boolean zB0 = fu0Var7.b0();
        long[] jArr7 = fu0Var7.U;
        long[] jArrO = zB0 ? null : j31.O(jArr7);
        long[] jArr8 = fu0Var5.U;
        if (jArrO == null) {
            jArr = fu0Var8.U;
        } else {
            j31.H(jArr8, jArrO, jArr4);
            j31.H(fu0Var8.U, jArrO, jArr6);
            jArr = jArr6;
            jArr8 = jArr4;
        }
        long[] jArrO2 = fu0Var9.b0() ? null : j31.O(fu0Var9.U);
        long[] jArr9 = fu0Var4.U;
        if (jArrO2 == null) {
            jArr2 = fu0Var6.U;
        } else {
            j31.H(jArr9, jArrO2, jArr3);
            j31.H(fu0Var6.U, jArrO2, jArr5);
            jArr9 = jArr3;
            jArr2 = jArr5;
        }
        j31.a(jArr2, jArr, jArr5);
        j31.a(jArr9, jArr8, jArr6);
        if (s90.H(jArr6)) {
            return s90.H(jArr5) ? u() : xqVar.k();
        }
        if (s90.H(fu0Var5.U)) {
            or orVarO = o();
            fu0 fu0Var10 = (fu0) orVarO.b;
            l21 l21VarI = orVarO.i();
            l21 l21VarA = l21VarI.e(fu0Var8).A(fu0Var10);
            fu0Var = (fu0) l21VarA.U0().e(l21VarA).e(fu0Var10);
            if (s90.H(fu0Var.U)) {
                return new nr(xqVar, fu0Var, xqVar.c, 17);
            }
            fu0Var2 = (fu0) l21VarA.k0(fu0Var10.e(fu0Var)).e(fu0Var).e(l21VarI).A(fu0Var).e(fu0Var);
            fu0Var3 = (fu0) xqVar.h(qq.b);
        } else {
            j31.b0(jArr6, jArr6);
            long[] jArrO3 = j31.O(jArr5);
            j31.H(jArr9, jArrO3, jArr3);
            j31.H(jArr8, jArrO3, jArr4);
            fu0Var = new fu0(jArr3);
            j31.F(jArr3, jArr4, jArr3);
            if (s90.H(jArr3)) {
                return new nr(xqVar, fu0Var, xqVar.c, 17);
            }
            fu0 fu0Var11 = new fu0(jArr5);
            j31.H(jArr6, jArrO3, jArr5);
            if (jArrO2 != null) {
                j31.H(jArr5, jArrO2, jArr5);
            }
            long[] jArr10 = new long[18];
            j31.a(jArr4, jArr6, jArr6);
            long[] jArr11 = new long[18];
            j31.w(jArr6, jArr11);
            j31.d(jArr10, jArr11, jArr10);
            j31.a(fu0Var6.U, jArr7, jArr6);
            j31.G(jArr6, jArr5, jArr10);
            fu0Var2 = new fu0(jArr6);
            j31.R(jArr10, jArr6);
            if (jArrO != null) {
                j31.H(jArr5, jArrO, jArr5);
            }
            fu0Var3 = fu0Var11;
        }
        return new nr(xqVar, fu0Var, fu0Var2, new l21[]{fu0Var3}, 17);
    }

    private final or w(or orVar) {
        l21 l21VarK0;
        l21 l21VarK02;
        l21 l21VarK03;
        l21 l21Var;
        l21 l21VarH;
        l21 l21Var2;
        if (l()) {
            return orVar;
        }
        if (orVar.l()) {
            return this;
        }
        l21 l21VarK04 = this.b;
        boolean zD0 = l21VarK04.d0();
        xq xqVar = this.a;
        l21 l21Var3 = orVar.b;
        if (zD0) {
            return l21Var3.d0() ? xqVar.k() : orVar.a(this);
        }
        l21 l21Var4 = this.d[0];
        l21 l21VarJ = orVar.j();
        boolean zB0 = l21Var4.b0();
        l21 l21Var5 = orVar.c;
        if (zB0) {
            l21VarK0 = l21Var5;
            l21VarK02 = l21Var3;
        } else {
            l21VarK02 = l21Var3.k0(l21Var4);
            l21VarK0 = l21Var5.k0(l21Var4);
        }
        boolean zB02 = l21VarJ.b0();
        l21 l21Var6 = this.c;
        if (zB02) {
            l21VarK03 = l21Var6;
        } else {
            l21VarK04 = l21VarK04.k0(l21VarJ);
            l21VarK03 = l21Var6.k0(l21VarJ);
        }
        l21 l21VarE = l21VarK03.e(l21VarK0);
        l21 l21VarE2 = l21VarK04.e(l21VarK02);
        if (l21VarE2.d0()) {
            return l21VarE.d0() ? u() : xqVar.k();
        }
        if (l21Var3.d0()) {
            or orVarO = o();
            l21 l21Var7 = orVarO.b;
            l21 l21VarI = orVarO.i();
            l21 l21VarA = l21VarI.e(l21Var5).A(l21Var7);
            l21 l21VarH2 = zn.h(l21VarA, l21VarA, l21Var7);
            if (l21VarH2.d0()) {
                return new nr(xqVar, l21VarH2, xqVar.c, 13);
            }
            l21 l21VarE3 = l21VarA.k0(l21Var7.e(l21VarH2)).e(l21VarH2).e(l21VarI).A(l21VarH2).e(l21VarH2);
            l21VarH = xqVar.h(qq.b);
            l21Var2 = l21VarE3;
            l21Var = l21VarH2;
        } else {
            l21 l21VarU0 = l21VarE2.U0();
            l21 l21VarK05 = l21VarE.k0(l21VarK04);
            l21 l21VarK06 = l21VarE.k0(l21VarK02);
            l21 l21VarK07 = l21VarK05.k0(l21VarK06);
            if (l21VarK07.d0()) {
                return new nr(xqVar, l21VarK07, xqVar.c, 13);
            }
            l21 l21VarK08 = l21VarE.k0(l21VarU0);
            l21 l21VarK09 = !zB02 ? l21VarK08.k0(l21VarJ) : l21VarK08;
            l21 l21VarA1 = l21VarK06.e(l21VarU0).a1(l21VarK09, l21Var6.e(l21Var4));
            if (zB0) {
                l21 l21Var8 = l21VarK09;
                l21Var = l21VarK07;
                l21VarH = l21Var8;
                l21Var2 = l21VarA1;
            } else {
                l21 l21VarK010 = l21VarK09.k0(l21Var4);
                l21Var2 = l21VarA1;
                l21Var = l21VarK07;
                l21VarH = l21VarK010;
            }
        }
        return new nr(xqVar, l21Var, l21Var2, new l21[]{l21VarH}, 13);
    }

    private final or x(or orVar) {
        l21 l21VarK0;
        l21 l21VarK02;
        l21 l21VarK03;
        l21 l21Var;
        l21 l21VarH;
        l21 l21Var2;
        if (l()) {
            return orVar;
        }
        if (orVar.l()) {
            return this;
        }
        l21 l21VarK04 = this.b;
        boolean zD0 = l21VarK04.d0();
        xq xqVar = this.a;
        l21 l21Var3 = orVar.b;
        if (zD0) {
            return l21Var3.d0() ? xqVar.k() : orVar.a(this);
        }
        l21 l21Var4 = this.d[0];
        l21 l21VarJ = orVar.j();
        boolean zB0 = l21Var4.b0();
        l21 l21Var5 = orVar.c;
        if (zB0) {
            l21VarK0 = l21Var5;
            l21VarK02 = l21Var3;
        } else {
            l21VarK02 = l21Var3.k0(l21Var4);
            l21VarK0 = l21Var5.k0(l21Var4);
        }
        boolean zB02 = l21VarJ.b0();
        l21 l21Var6 = this.c;
        if (zB02) {
            l21VarK03 = l21Var6;
        } else {
            l21VarK04 = l21VarK04.k0(l21VarJ);
            l21VarK03 = l21Var6.k0(l21VarJ);
        }
        l21 l21VarE = l21VarK03.e(l21VarK0);
        l21 l21VarE2 = l21VarK04.e(l21VarK02);
        if (l21VarE2.d0()) {
            return l21VarE.d0() ? u() : xqVar.k();
        }
        if (l21Var3.d0()) {
            or orVarO = o();
            l21 l21Var7 = orVarO.b;
            l21 l21VarI = orVarO.i();
            l21 l21VarA = l21VarI.e(l21Var5).A(l21Var7);
            l21 l21VarH2 = l21VarA.U0().e(l21VarA).e(l21Var7).h();
            if (l21VarH2.d0()) {
                return new nr(xqVar, l21VarH2, xqVar.c.T0(), 14);
            }
            l21 l21VarE3 = l21VarA.k0(l21Var7.e(l21VarH2)).e(l21VarH2).e(l21VarI).A(l21VarH2).e(l21VarH2);
            l21VarH = xqVar.h(qq.b);
            l21Var2 = l21VarE3;
            l21Var = l21VarH2;
        } else {
            l21 l21VarU0 = l21VarE2.U0();
            l21 l21VarK05 = l21VarE.k0(l21VarK04);
            l21 l21VarK06 = l21VarE.k0(l21VarK02);
            l21 l21VarK07 = l21VarK05.k0(l21VarK06);
            if (l21VarK07.d0()) {
                return new nr(xqVar, l21VarK07, xqVar.c.T0(), 14);
            }
            l21 l21VarK08 = l21VarE.k0(l21VarU0);
            l21 l21VarK09 = !zB02 ? l21VarK08.k0(l21VarJ) : l21VarK08;
            l21 l21VarA1 = l21VarK06.e(l21VarU0).a1(l21VarK09, l21Var6.e(l21Var4));
            if (zB0) {
                l21 l21Var8 = l21VarK09;
                l21Var = l21VarK07;
                l21VarH = l21Var8;
                l21Var2 = l21VarA1;
            } else {
                l21 l21VarK010 = l21VarK09.k0(l21Var4);
                l21Var2 = l21VarA1;
                l21Var = l21VarK07;
                l21VarH = l21VarK010;
            }
        }
        return new nr(xqVar, l21Var, l21Var2, new l21[]{l21VarH}, 14);
    }

    private final or y(or orVar) {
        l21 l21VarK0;
        l21 l21VarK02;
        l21 l21VarK03;
        l21 l21Var;
        l21 l21VarH;
        l21 l21Var2;
        if (l()) {
            return orVar;
        }
        if (orVar.l()) {
            return this;
        }
        l21 l21VarK04 = this.b;
        boolean zD0 = l21VarK04.d0();
        xq xqVar = this.a;
        l21 l21Var3 = orVar.b;
        if (zD0) {
            return l21Var3.d0() ? xqVar.k() : orVar.a(this);
        }
        l21 l21Var4 = this.d[0];
        l21 l21VarJ = orVar.j();
        boolean zB0 = l21Var4.b0();
        l21 l21Var5 = orVar.c;
        if (zB0) {
            l21VarK0 = l21Var5;
            l21VarK02 = l21Var3;
        } else {
            l21VarK02 = l21Var3.k0(l21Var4);
            l21VarK0 = l21Var5.k0(l21Var4);
        }
        boolean zB02 = l21VarJ.b0();
        l21 l21Var6 = this.c;
        if (zB02) {
            l21VarK03 = l21Var6;
        } else {
            l21VarK04 = l21VarK04.k0(l21VarJ);
            l21VarK03 = l21Var6.k0(l21VarJ);
        }
        l21 l21VarE = l21VarK03.e(l21VarK0);
        l21 l21VarE2 = l21VarK04.e(l21VarK02);
        if (l21VarE2.d0()) {
            return l21VarE.d0() ? u() : xqVar.k();
        }
        if (l21Var3.d0()) {
            or orVarO = o();
            l21 l21Var7 = orVarO.b;
            l21 l21VarI = orVarO.i();
            l21 l21VarA = l21VarI.e(l21Var5).A(l21Var7);
            l21 l21VarH2 = zn.h(l21VarA, l21VarA, l21Var7);
            if (l21VarH2.d0()) {
                return new nr(xqVar, l21VarH2, xqVar.c, 15);
            }
            l21 l21VarE3 = l21VarA.k0(l21Var7.e(l21VarH2)).e(l21VarH2).e(l21VarI).A(l21VarH2).e(l21VarH2);
            l21VarH = xqVar.h(qq.b);
            l21Var2 = l21VarE3;
            l21Var = l21VarH2;
        } else {
            l21 l21VarU0 = l21VarE2.U0();
            l21 l21VarK05 = l21VarE.k0(l21VarK04);
            l21 l21VarK06 = l21VarE.k0(l21VarK02);
            l21 l21VarK07 = l21VarK05.k0(l21VarK06);
            if (l21VarK07.d0()) {
                return new nr(xqVar, l21VarK07, xqVar.c, 15);
            }
            l21 l21VarK08 = l21VarE.k0(l21VarU0);
            l21 l21VarK09 = !zB02 ? l21VarK08.k0(l21VarJ) : l21VarK08;
            l21 l21VarA1 = l21VarK06.e(l21VarU0).a1(l21VarK09, l21Var6.e(l21Var4));
            if (zB0) {
                l21 l21Var8 = l21VarK09;
                l21Var = l21VarK07;
                l21VarH = l21Var8;
                l21Var2 = l21VarA1;
            } else {
                l21 l21VarK010 = l21VarK09.k0(l21Var4);
                l21Var2 = l21VarA1;
                l21Var = l21VarK07;
                l21VarH = l21VarK010;
            }
        }
        return new nr(xqVar, l21Var, l21Var2, new l21[]{l21VarH}, 15);
    }

    private final or z(or orVar) {
        l21 l21VarK0;
        l21 l21VarK02;
        l21 l21VarK03;
        l21 l21Var;
        l21 l21VarH;
        l21 l21Var2;
        if (l()) {
            return orVar;
        }
        if (orVar.l()) {
            return this;
        }
        l21 l21VarK04 = this.b;
        boolean zD0 = l21VarK04.d0();
        xq xqVar = this.a;
        l21 l21Var3 = orVar.b;
        if (zD0) {
            return l21Var3.d0() ? xqVar.k() : orVar.a(this);
        }
        l21 l21Var4 = this.d[0];
        l21 l21VarJ = orVar.j();
        boolean zB0 = l21Var4.b0();
        l21 l21Var5 = orVar.c;
        if (zB0) {
            l21VarK0 = l21Var5;
            l21VarK02 = l21Var3;
        } else {
            l21VarK02 = l21Var3.k0(l21Var4);
            l21VarK0 = l21Var5.k0(l21Var4);
        }
        boolean zB02 = l21VarJ.b0();
        l21 l21Var6 = this.c;
        if (zB02) {
            l21VarK03 = l21Var6;
        } else {
            l21VarK04 = l21VarK04.k0(l21VarJ);
            l21VarK03 = l21Var6.k0(l21VarJ);
        }
        l21 l21VarE = l21VarK03.e(l21VarK0);
        l21 l21VarE2 = l21VarK04.e(l21VarK02);
        if (l21VarE2.d0()) {
            return l21VarE.d0() ? u() : xqVar.k();
        }
        if (l21Var3.d0()) {
            or orVarO = o();
            l21 l21Var7 = orVarO.b;
            l21 l21VarI = orVarO.i();
            l21 l21VarA = l21VarI.e(l21Var5).A(l21Var7);
            l21 l21VarH2 = l21VarA.U0().e(l21VarA).e(l21Var7).h();
            if (l21VarH2.d0()) {
                return new nr(xqVar, l21VarH2, xqVar.c.T0(), 16);
            }
            l21 l21VarE3 = l21VarA.k0(l21Var7.e(l21VarH2)).e(l21VarH2).e(l21VarI).A(l21VarH2).e(l21VarH2);
            l21VarH = xqVar.h(qq.b);
            l21Var2 = l21VarE3;
            l21Var = l21VarH2;
        } else {
            l21 l21VarU0 = l21VarE2.U0();
            l21 l21VarK05 = l21VarE.k0(l21VarK04);
            l21 l21VarK06 = l21VarE.k0(l21VarK02);
            l21 l21VarK07 = l21VarK05.k0(l21VarK06);
            if (l21VarK07.d0()) {
                return new nr(xqVar, l21VarK07, xqVar.c.T0(), 16);
            }
            l21 l21VarK08 = l21VarE.k0(l21VarU0);
            l21 l21VarK09 = !zB02 ? l21VarK08.k0(l21VarJ) : l21VarK08;
            l21 l21VarA1 = l21VarK06.e(l21VarU0).a1(l21VarK09, l21Var6.e(l21Var4));
            if (zB0) {
                l21 l21Var8 = l21VarK09;
                l21Var = l21VarK07;
                l21VarH = l21Var8;
                l21Var2 = l21VarA1;
            } else {
                l21 l21VarK010 = l21VarK09.k0(l21Var4);
                l21Var2 = l21VarA1;
                l21Var = l21VarK07;
                l21VarH = l21VarK010;
            }
        }
        return new nr(xqVar, l21Var, l21Var2, new l21[]{l21VarH}, 16);
    }

    /* JADX WARN: Code restructure failed: missing block: B:624:0x0f43, code lost:
    
        if (r10.d0() != false) goto L625;
     */
    /* JADX WARN: Code restructure failed: missing block: B:658:0x1033, code lost:
    
        if (r5.d0() != false) goto L625;
     */
    /* JADX WARN: Code restructure failed: missing block: B:672:0x108e, code lost:
    
        if (r4.d0() != false) goto L625;
     */
    /* JADX WARN: Code restructure failed: missing block: B:770:?, code lost:
    
        return u();
     */
    @Override // defpackage.or
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.or a(defpackage.or r25) {
        /*
            Method dump skipped, instruction units count: 4320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nr.a(or):or");
    }

    @Override // defpackage.or
    public final or c() {
        switch (this.h) {
            case 0:
                b();
                return new nr(null, this.b, e(), 0);
            case 1:
                b();
                return new nr(null, this.b, e(), 1);
            case 2:
                b();
                return new nr(null, this.b, e(), 2);
            case 3:
                b();
                return new nr(null, this.b, e(), 3);
            case 4:
                b();
                return new nr(null, this.b, e(), 4);
            case 5:
                b();
                return new nr(null, this.b, e(), 5);
            case 6:
                b();
                return new nr(null, this.b, e(), 6);
            case 7:
                b();
                return new nr(null, this.b, e(), 7);
            case 8:
                b();
                return new nr(null, this.b, e(), 8);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                b();
                return new nr(null, this.b, e(), 9);
            case 10:
                b();
                return new nr(null, this.b, e(), 10);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                b();
                return new nr(null, this.b, e(), 11);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                b();
                return new nr(null, this.b, e(), 12);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                b();
                return new nr(null, this.b, e(), 13);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                b();
                return new nr(null, this.b, e(), 14);
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                b();
                return new nr(null, this.b, e(), 15);
            case 16:
                b();
                return new nr(null, this.b, e(), 16);
            case 17:
                b();
                return new nr(null, this.b, e(), 17);
            default:
                b();
                return new nr(null, this.b, e(), 18);
        }
    }

    @Override // defpackage.mr, defpackage.or
    public final boolean f() {
        switch (this.h) {
            case 0:
                l21 l21Var = this.b;
                if (!l21Var.d0()) {
                    int iG = g();
                    l21 l21Var2 = this.c;
                    if (iG != 5 && iG != 6) {
                        break;
                    } else if (l21Var2.g1() != l21Var.g1()) {
                    }
                }
                break;
            case 1:
                l21 l21Var3 = this.b;
                if (!l21Var3.d0() && this.c.g1() != l21Var3.g1()) {
                }
                break;
            case 2:
                l21 l21Var4 = this.b;
                if (!l21Var4.d0() && this.c.g1() != l21Var4.g1()) {
                }
                break;
            case 3:
                l21 l21Var5 = this.b;
                if (!l21Var5.d0() && this.c.g1() != l21Var5.g1()) {
                }
                break;
            case 4:
                l21 l21Var6 = this.b;
                if (!l21Var6.d0() && this.c.g1() != l21Var6.g1()) {
                }
                break;
            case 5:
                l21 l21Var7 = this.b;
                if (!l21Var7.d0() && this.c.g1() != l21Var7.g1()) {
                }
                break;
            case 6:
                l21 l21Var8 = this.b;
                if (!l21Var8.d0() && this.c.g1() != l21Var8.g1()) {
                }
                break;
            case 7:
                l21 l21Var9 = this.b;
                if (!l21Var9.d0() && this.c.g1() != l21Var9.g1()) {
                }
                break;
            case 8:
                l21 l21Var10 = this.b;
                if (!l21Var10.d0() && this.c.g1() != l21Var10.g1()) {
                }
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                l21 l21Var11 = this.b;
                if (!l21Var11.d0() && this.c.g1() != l21Var11.g1()) {
                }
                break;
            case 10:
                l21 l21Var12 = this.b;
                if (!l21Var12.d0() && this.c.g1() != l21Var12.g1()) {
                }
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                l21 l21Var13 = this.b;
                if (!l21Var13.d0() && this.c.g1() != l21Var13.g1()) {
                }
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                l21 l21Var14 = this.b;
                if (!l21Var14.d0() && this.c.g1() != l21Var14.g1()) {
                }
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                l21 l21Var15 = this.b;
                if (!l21Var15.d0() && this.c.g1() != l21Var15.g1()) {
                }
                break;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                l21 l21Var16 = this.b;
                if (!l21Var16.d0() && this.c.g1() != l21Var16.g1()) {
                }
                break;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                l21 l21Var17 = this.b;
                if (!l21Var17.d0() && this.c.g1() != l21Var17.g1()) {
                }
                break;
            case 16:
                l21 l21Var18 = this.b;
                if (!l21Var18.d0() && this.c.g1() != l21Var18.g1()) {
                }
                break;
            case 17:
                l21 l21Var19 = this.b;
                if (!l21Var19.d0() && this.c.g1() != l21Var19.g1()) {
                }
                break;
            default:
                l21 l21Var20 = this.b;
                if (!l21Var20.d0() && this.c.g1() != l21Var20.g1()) {
                }
                break;
        }
        return false;
    }

    @Override // defpackage.or
    public final l21 i() {
        switch (this.h) {
            case 0:
                int iG = g();
                l21 l21Var = this.c;
                if ((iG != 5 && iG != 6) || l()) {
                    return l21Var;
                }
                l21 l21Var2 = this.b;
                if (l21Var2.d0()) {
                    return l21Var;
                }
                l21 l21VarK0 = l21Var.e(l21Var2).k0(l21Var2);
                if (6 != iG) {
                    return l21VarK0;
                }
                l21 l21Var3 = this.d[0];
                return !l21Var3.b0() ? l21VarK0.A(l21Var3) : l21VarK0;
            case 1:
                boolean zL = l();
                l21 l21Var4 = this.c;
                if (zL) {
                    return l21Var4;
                }
                l21 l21Var5 = this.b;
                if (l21Var5.d0()) {
                    return l21Var4;
                }
                l21 l21VarK02 = l21Var4.e(l21Var5).k0(l21Var5);
                l21 l21Var6 = this.d[0];
                return !l21Var6.b0() ? l21VarK02.A(l21Var6) : l21VarK02;
            case 2:
                boolean zL2 = l();
                l21 l21Var7 = this.c;
                if (zL2) {
                    return l21Var7;
                }
                l21 l21Var8 = this.b;
                if (l21Var8.d0()) {
                    return l21Var7;
                }
                l21 l21VarK03 = l21Var7.e(l21Var8).k0(l21Var8);
                l21 l21Var9 = this.d[0];
                return !l21Var9.b0() ? l21VarK03.A(l21Var9) : l21VarK03;
            case 3:
                boolean zL3 = l();
                l21 l21Var10 = this.c;
                if (zL3) {
                    return l21Var10;
                }
                l21 l21Var11 = this.b;
                if (l21Var11.d0()) {
                    return l21Var10;
                }
                l21 l21VarK04 = l21Var10.e(l21Var11).k0(l21Var11);
                l21 l21Var12 = this.d[0];
                return !l21Var12.b0() ? l21VarK04.A(l21Var12) : l21VarK04;
            case 4:
                boolean zL4 = l();
                l21 l21Var13 = this.c;
                if (zL4) {
                    return l21Var13;
                }
                l21 l21Var14 = this.b;
                if (l21Var14.d0()) {
                    return l21Var13;
                }
                l21 l21VarK05 = l21Var13.e(l21Var14).k0(l21Var14);
                l21 l21Var15 = this.d[0];
                return !l21Var15.b0() ? l21VarK05.A(l21Var15) : l21VarK05;
            case 5:
                boolean zL5 = l();
                l21 l21Var16 = this.c;
                if (zL5) {
                    return l21Var16;
                }
                l21 l21Var17 = this.b;
                if (l21Var17.d0()) {
                    return l21Var16;
                }
                l21 l21VarK06 = l21Var16.e(l21Var17).k0(l21Var17);
                l21 l21Var18 = this.d[0];
                return !l21Var18.b0() ? l21VarK06.A(l21Var18) : l21VarK06;
            case 6:
                boolean zL6 = l();
                l21 l21Var19 = this.c;
                if (zL6) {
                    return l21Var19;
                }
                l21 l21Var20 = this.b;
                if (l21Var20.d0()) {
                    return l21Var19;
                }
                l21 l21VarK07 = l21Var19.e(l21Var20).k0(l21Var20);
                l21 l21Var21 = this.d[0];
                return !l21Var21.b0() ? l21VarK07.A(l21Var21) : l21VarK07;
            case 7:
                boolean zL7 = l();
                l21 l21Var22 = this.c;
                if (zL7) {
                    return l21Var22;
                }
                l21 l21Var23 = this.b;
                if (l21Var23.d0()) {
                    return l21Var22;
                }
                l21 l21VarK08 = l21Var22.e(l21Var23).k0(l21Var23);
                l21 l21Var24 = this.d[0];
                return !l21Var24.b0() ? l21VarK08.A(l21Var24) : l21VarK08;
            case 8:
                boolean zL8 = l();
                l21 l21Var25 = this.c;
                if (zL8) {
                    return l21Var25;
                }
                l21 l21Var26 = this.b;
                if (l21Var26.d0()) {
                    return l21Var25;
                }
                l21 l21VarK09 = l21Var25.e(l21Var26).k0(l21Var26);
                l21 l21Var27 = this.d[0];
                return !l21Var27.b0() ? l21VarK09.A(l21Var27) : l21VarK09;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                boolean zL9 = l();
                l21 l21Var28 = this.c;
                if (zL9) {
                    return l21Var28;
                }
                l21 l21Var29 = this.b;
                if (l21Var29.d0()) {
                    return l21Var28;
                }
                l21 l21VarK010 = l21Var28.e(l21Var29).k0(l21Var29);
                l21 l21Var30 = this.d[0];
                return !l21Var30.b0() ? l21VarK010.A(l21Var30) : l21VarK010;
            case 10:
                boolean zL10 = l();
                l21 l21Var31 = this.c;
                if (zL10) {
                    return l21Var31;
                }
                l21 l21Var32 = this.b;
                if (l21Var32.d0()) {
                    return l21Var31;
                }
                l21 l21VarK011 = l21Var31.e(l21Var32).k0(l21Var32);
                l21 l21Var33 = this.d[0];
                return !l21Var33.b0() ? l21VarK011.A(l21Var33) : l21VarK011;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                boolean zL11 = l();
                l21 l21Var34 = this.c;
                if (zL11) {
                    return l21Var34;
                }
                l21 l21Var35 = this.b;
                if (l21Var35.d0()) {
                    return l21Var34;
                }
                l21 l21VarK012 = l21Var34.e(l21Var35).k0(l21Var35);
                l21 l21Var36 = this.d[0];
                return !l21Var36.b0() ? l21VarK012.A(l21Var36) : l21VarK012;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                boolean zL12 = l();
                l21 l21Var37 = this.c;
                if (zL12) {
                    return l21Var37;
                }
                l21 l21Var38 = this.b;
                if (l21Var38.d0()) {
                    return l21Var37;
                }
                l21 l21VarK013 = l21Var37.e(l21Var38).k0(l21Var38);
                l21 l21Var39 = this.d[0];
                return !l21Var39.b0() ? l21VarK013.A(l21Var39) : l21VarK013;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                boolean zL13 = l();
                l21 l21Var40 = this.c;
                if (zL13) {
                    return l21Var40;
                }
                l21 l21Var41 = this.b;
                if (l21Var41.d0()) {
                    return l21Var40;
                }
                l21 l21VarK014 = l21Var40.e(l21Var41).k0(l21Var41);
                l21 l21Var42 = this.d[0];
                return !l21Var42.b0() ? l21VarK014.A(l21Var42) : l21VarK014;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                boolean zL14 = l();
                l21 l21Var43 = this.c;
                if (zL14) {
                    return l21Var43;
                }
                l21 l21Var44 = this.b;
                if (l21Var44.d0()) {
                    return l21Var43;
                }
                l21 l21VarK015 = l21Var43.e(l21Var44).k0(l21Var44);
                l21 l21Var45 = this.d[0];
                return !l21Var45.b0() ? l21VarK015.A(l21Var45) : l21VarK015;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                boolean zL15 = l();
                l21 l21Var46 = this.c;
                if (zL15) {
                    return l21Var46;
                }
                l21 l21Var47 = this.b;
                if (l21Var47.d0()) {
                    return l21Var46;
                }
                l21 l21VarK016 = l21Var46.e(l21Var47).k0(l21Var47);
                l21 l21Var48 = this.d[0];
                return !l21Var48.b0() ? l21VarK016.A(l21Var48) : l21VarK016;
            case 16:
                boolean zL16 = l();
                l21 l21Var49 = this.c;
                if (zL16) {
                    return l21Var49;
                }
                l21 l21Var50 = this.b;
                if (l21Var50.d0()) {
                    return l21Var49;
                }
                l21 l21VarK017 = l21Var49.e(l21Var50).k0(l21Var50);
                l21 l21Var51 = this.d[0];
                return !l21Var51.b0() ? l21VarK017.A(l21Var51) : l21VarK017;
            case 17:
                boolean zL17 = l();
                l21 l21Var52 = this.c;
                if (zL17) {
                    return l21Var52;
                }
                l21 l21Var53 = this.b;
                if (l21Var53.d0()) {
                    return l21Var52;
                }
                l21 l21VarK018 = l21Var52.e(l21Var53).k0(l21Var53);
                l21 l21Var54 = this.d[0];
                return !l21Var54.b0() ? l21VarK018.A(l21Var54) : l21VarK018;
            default:
                boolean zL18 = l();
                l21 l21Var55 = this.c;
                if (zL18) {
                    return l21Var55;
                }
                l21 l21Var56 = this.b;
                if (l21Var56.d0()) {
                    return l21Var55;
                }
                l21 l21VarK019 = l21Var55.e(l21Var56).k0(l21Var56);
                l21 l21Var57 = this.d[0];
                return !l21Var57.b0() ? l21VarK019.A(l21Var57) : l21VarK019;
        }
    }

    @Override // defpackage.or
    public final or n() {
        switch (this.h) {
            case 0:
                if (!l()) {
                    l21 l21Var = this.b;
                    if (!l21Var.d0()) {
                        int iG = g();
                        xq xqVar = this.a;
                        l21 l21Var2 = this.c;
                        if (iG == 0) {
                            return new nr(xqVar, l21Var, l21Var2.e(l21Var), 0);
                        }
                        l21[] l21VarArr = this.d;
                        if (iG == 1) {
                            return new nr(xqVar, l21Var, l21Var2.e(l21Var), new l21[]{l21VarArr[0]}, 0);
                        }
                        if (iG == 5) {
                            return new nr(xqVar, l21Var, l21Var2.h(), 0);
                        }
                        if (iG != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                        l21 l21Var3 = l21VarArr[0];
                        return new nr(xqVar, l21Var, l21Var2.e(l21Var3), new l21[]{l21Var3}, 0);
                    }
                }
                return this;
            case 1:
                if (!l()) {
                    l21 l21Var4 = this.b;
                    if (!l21Var4.d0()) {
                        l21 l21Var5 = this.d[0];
                        return new nr(this.a, l21Var4, this.c.e(l21Var5), new l21[]{l21Var5}, 1);
                    }
                }
                return this;
            case 2:
                if (!l()) {
                    l21 l21Var6 = this.b;
                    if (!l21Var6.d0()) {
                        l21 l21Var7 = this.d[0];
                        return new nr(this.a, l21Var6, this.c.e(l21Var7), new l21[]{l21Var7}, 2);
                    }
                }
                return this;
            case 3:
                if (!l()) {
                    l21 l21Var8 = this.b;
                    if (!l21Var8.d0()) {
                        l21 l21Var9 = this.d[0];
                        return new nr(this.a, l21Var8, this.c.e(l21Var9), new l21[]{l21Var9}, 3);
                    }
                }
                return this;
            case 4:
                if (!l()) {
                    l21 l21Var10 = this.b;
                    if (!l21Var10.d0()) {
                        l21 l21Var11 = this.d[0];
                        return new nr(this.a, l21Var10, this.c.e(l21Var11), new l21[]{l21Var11}, 4);
                    }
                }
                return this;
            case 5:
                if (!l()) {
                    l21 l21Var12 = this.b;
                    if (!l21Var12.d0()) {
                        l21 l21Var13 = this.d[0];
                        return new nr(this.a, l21Var12, this.c.e(l21Var13), new l21[]{l21Var13}, 5);
                    }
                }
                return this;
            case 6:
                if (!l()) {
                    l21 l21Var14 = this.b;
                    if (!l21Var14.d0()) {
                        l21 l21Var15 = this.d[0];
                        return new nr(this.a, l21Var14, this.c.e(l21Var15), new l21[]{l21Var15}, 6);
                    }
                }
                return this;
            case 7:
                if (!l()) {
                    l21 l21Var16 = this.b;
                    if (!l21Var16.d0()) {
                        l21 l21Var17 = this.d[0];
                        return new nr(this.a, l21Var16, this.c.e(l21Var17), new l21[]{l21Var17}, 7);
                    }
                }
                return this;
            case 8:
                if (!l()) {
                    l21 l21Var18 = this.b;
                    if (!l21Var18.d0()) {
                        l21 l21Var19 = this.d[0];
                        return new nr(this.a, l21Var18, this.c.e(l21Var19), new l21[]{l21Var19}, 8);
                    }
                }
                return this;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                if (!l()) {
                    l21 l21Var20 = this.b;
                    if (!l21Var20.d0()) {
                        l21 l21Var21 = this.d[0];
                        return new nr(this.a, l21Var20, this.c.e(l21Var21), new l21[]{l21Var21}, 9);
                    }
                }
                return this;
            case 10:
                if (!l()) {
                    l21 l21Var22 = this.b;
                    if (!l21Var22.d0()) {
                        l21 l21Var23 = this.d[0];
                        return new nr(this.a, l21Var22, this.c.e(l21Var23), new l21[]{l21Var23}, 10);
                    }
                }
                return this;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                if (!l()) {
                    l21 l21Var24 = this.b;
                    if (!l21Var24.d0()) {
                        l21 l21Var25 = this.d[0];
                        return new nr(this.a, l21Var24, this.c.e(l21Var25), new l21[]{l21Var25}, 11);
                    }
                }
                return this;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                if (!l()) {
                    l21 l21Var26 = this.b;
                    if (!l21Var26.d0()) {
                        l21 l21Var27 = this.d[0];
                        return new nr(this.a, l21Var26, this.c.e(l21Var27), new l21[]{l21Var27}, 12);
                    }
                }
                return this;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                if (!l()) {
                    l21 l21Var28 = this.b;
                    if (!l21Var28.d0()) {
                        l21 l21Var29 = this.d[0];
                        return new nr(this.a, l21Var28, this.c.e(l21Var29), new l21[]{l21Var29}, 13);
                    }
                }
                return this;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                if (!l()) {
                    l21 l21Var30 = this.b;
                    if (!l21Var30.d0()) {
                        l21 l21Var31 = this.d[0];
                        return new nr(this.a, l21Var30, this.c.e(l21Var31), new l21[]{l21Var31}, 14);
                    }
                }
                return this;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                if (!l()) {
                    l21 l21Var32 = this.b;
                    if (!l21Var32.d0()) {
                        l21 l21Var33 = this.d[0];
                        return new nr(this.a, l21Var32, this.c.e(l21Var33), new l21[]{l21Var33}, 15);
                    }
                }
                return this;
            case 16:
                if (!l()) {
                    l21 l21Var34 = this.b;
                    if (!l21Var34.d0()) {
                        l21 l21Var35 = this.d[0];
                        return new nr(this.a, l21Var34, this.c.e(l21Var35), new l21[]{l21Var35}, 16);
                    }
                }
                return this;
            case 17:
                if (!l()) {
                    l21 l21Var36 = this.b;
                    if (!l21Var36.d0()) {
                        l21 l21Var37 = this.d[0];
                        return new nr(this.a, l21Var36, this.c.e(l21Var37), new l21[]{l21Var37}, 17);
                    }
                }
                return this;
            default:
                if (!l()) {
                    l21 l21Var38 = this.b;
                    if (!l21Var38.d0()) {
                        l21 l21Var39 = this.d[0];
                        return new nr(this.a, l21Var38, this.c.e(l21Var39), new l21[]{l21Var39}, 18);
                    }
                }
                return this;
        }
    }

    @Override // defpackage.or
    public final or u() {
        nr nrVar;
        l21 l21VarE;
        int i = 6;
        int i2 = 18;
        int i3 = 9;
        int i4 = 1;
        l21[] l21VarArr = this.d;
        l21 l21VarK0 = this.c;
        l21 l21VarK02 = this.b;
        int i5 = 0;
        switch (this.h) {
            case 0:
                if (l()) {
                    return this;
                }
                boolean zD0 = l21VarK02.d0();
                xq xqVar = this.a;
                if (zD0) {
                    return xqVar.k();
                }
                int i6 = xqVar.f;
                if (i6 == 0) {
                    l21 l21VarE2 = l21VarK0.A(l21VarK02).e(l21VarK02);
                    l21 l21VarE3 = l21VarE2.U0().e(l21VarE2).e(xqVar.b);
                    return new nr(xqVar, l21VarE3, l21VarK02.a1(l21VarE3, l21VarE2.h()), i5);
                }
                if (i6 == 1) {
                    l21 l21Var = l21VarArr[0];
                    boolean zB0 = l21Var.b0();
                    l21 l21VarK03 = zB0 ? l21VarK02 : l21VarK02.k0(l21Var);
                    if (!zB0) {
                        l21VarK0 = l21VarK0.k0(l21Var);
                    }
                    l21 l21VarU0 = l21VarK02.U0();
                    l21 l21VarE4 = l21VarU0.e(l21VarK0);
                    l21 l21VarU02 = l21VarK03.U0();
                    l21 l21VarE5 = l21VarE4.e(l21VarK03);
                    l21 l21VarP0 = l21VarE5.p0(l21VarE4, l21VarU02, xqVar.b);
                    nrVar = new nr(xqVar, l21VarK03.k0(l21VarP0), l21VarU0.U0().p0(l21VarK03, l21VarP0, l21VarE5), new l21[]{l21VarK03.k0(l21VarU02)}, 0);
                } else {
                    if (i6 != 6) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    l21 l21Var2 = l21VarArr[0];
                    boolean zB02 = l21Var2.b0();
                    l21 l21VarK04 = zB02 ? l21VarK0 : l21VarK0.k0(l21Var2);
                    l21 l21VarU03 = zB02 ? l21Var2 : l21Var2.U0();
                    l21 l21Var3 = xqVar.b;
                    l21 l21VarK05 = zB02 ? l21Var3 : l21Var3.k0(l21VarU03);
                    l21 l21VarH = zn.h(l21VarK0, l21VarK04, l21VarK05);
                    if (l21VarH.d0()) {
                        return new nr(xqVar, l21VarH, xqVar.c.T0(), i5);
                    }
                    l21 l21VarU04 = l21VarH.U0();
                    l21 l21VarK06 = zB02 ? l21VarH : l21VarH.k0(l21VarU03);
                    l21 l21Var4 = xqVar.c;
                    if (l21Var4.n() < (xqVar.j() >> 1)) {
                        l21 l21VarU05 = l21VarK0.e(l21VarK02).U0();
                        l21VarE = l21VarU05.e(l21VarH).e(l21VarU03).k0(l21VarU05).e(l21Var4.b0() ? l21VarK05.e(l21VarU03).U0() : l21VarK05.a1(l21Var4, l21VarU03.U0())).e(l21VarU04);
                        if (!l21Var3.d0()) {
                            if (!l21Var3.b0()) {
                                l21VarE = l21VarE.e(l21Var3.h().k0(l21VarK06));
                            }
                        }
                        nrVar = new nr(xqVar, l21VarU04, l21VarE, new l21[]{l21VarK06}, 0);
                    } else {
                        if (!zB02) {
                            l21VarK02 = l21VarK02.k0(l21Var2);
                        }
                        l21VarE = l21VarK02.a1(l21VarH, l21VarK04).e(l21VarU04);
                    }
                    l21VarE = l21VarE.e(l21VarK06);
                    nrVar = new nr(xqVar, l21VarU04, l21VarE, new l21[]{l21VarK06}, 0);
                }
                return nrVar;
            case 1:
                if (l()) {
                    return this;
                }
                boolean zD02 = l21VarK02.d0();
                xq xqVar2 = this.a;
                if (zD02) {
                    return xqVar2.k();
                }
                l21 l21Var5 = l21VarArr[0];
                boolean zB03 = l21Var5.b0();
                l21 l21VarK07 = zB03 ? l21VarK0 : l21VarK0.k0(l21Var5);
                l21 l21VarU06 = zB03 ? l21Var5 : l21Var5.U0();
                l21 l21VarK08 = xqVar2.b;
                if (!zB03) {
                    l21VarK08 = l21VarK08.k0(l21VarU06);
                }
                l21 l21VarH2 = zn.h(l21VarK0, l21VarK07, l21VarK08);
                if (l21VarH2.d0()) {
                    return new nr(xqVar2, l21VarH2, xqVar2.c.T0(), i4);
                }
                l21 l21VarU07 = l21VarH2.U0();
                l21 l21VarK09 = zB03 ? l21VarH2 : l21VarH2.k0(l21VarU06);
                if (!zB03) {
                    l21VarK02 = l21VarK02.k0(l21Var5);
                }
                return new nr(xqVar2, l21VarU07, l21VarK02.a1(l21VarH2, l21VarK07).e(l21VarU07).e(l21VarK09), new l21[]{l21VarK09}, 1);
            case 2:
                if (l()) {
                    return this;
                }
                boolean zD03 = l21VarK02.d0();
                xq xqVar3 = this.a;
                if (zD03) {
                    return xqVar3.k();
                }
                l21 l21Var6 = l21VarArr[0];
                boolean zB04 = l21Var6.b0();
                l21 l21VarK010 = zB04 ? l21VarK0 : l21VarK0.k0(l21Var6);
                l21 l21VarU08 = zB04 ? l21Var6 : l21Var6.U0();
                l21 l21VarK011 = xqVar3.b;
                if (!zB04) {
                    l21VarK011 = l21VarK011.k0(l21VarU08);
                }
                l21 l21VarH3 = zn.h(l21VarK0, l21VarK010, l21VarK011);
                if (l21VarH3.d0()) {
                    return new nr(xqVar3, l21VarH3, xqVar3.c.T0(), 2);
                }
                l21 l21VarU09 = l21VarH3.U0();
                l21 l21VarK012 = zB04 ? l21VarH3 : l21VarH3.k0(l21VarU08);
                if (!zB04) {
                    l21VarK02 = l21VarK02.k0(l21Var6);
                }
                return new nr(xqVar3, l21VarU09, l21VarK02.a1(l21VarH3, l21VarK010).e(l21VarU09).e(l21VarK012), new l21[]{l21VarK012}, 2);
            case 3:
                if (l()) {
                    return this;
                }
                boolean zD04 = l21VarK02.d0();
                xq xqVar4 = this.a;
                if (zD04) {
                    return xqVar4.k();
                }
                l21 l21Var7 = l21VarArr[0];
                boolean zB05 = l21Var7.b0();
                l21 l21VarK013 = zB05 ? l21VarK0 : l21VarK0.k0(l21Var7);
                l21 l21VarU010 = zB05 ? l21Var7 : l21Var7.U0();
                l21 l21VarK014 = xqVar4.b;
                if (!zB05) {
                    l21VarK014 = l21VarK014.k0(l21VarU010);
                }
                l21 l21VarH4 = zn.h(l21VarK0, l21VarK013, l21VarK014);
                if (l21VarH4.d0()) {
                    return new nr(xqVar4, l21VarH4, xqVar4.c.T0(), 3);
                }
                l21 l21VarU011 = l21VarH4.U0();
                l21 l21VarK015 = zB05 ? l21VarH4 : l21VarH4.k0(l21VarU010);
                if (!zB05) {
                    l21VarK02 = l21VarK02.k0(l21Var7);
                }
                return new nr(xqVar4, l21VarU011, l21VarK02.a1(l21VarH4, l21VarK013).e(l21VarU011).e(l21VarK015), new l21[]{l21VarK015}, 3);
            case 4:
                if (l()) {
                    return this;
                }
                boolean zD05 = l21VarK02.d0();
                xq xqVar5 = this.a;
                if (zD05) {
                    return xqVar5.k();
                }
                l21 l21Var8 = l21VarArr[0];
                boolean zB06 = l21Var8.b0();
                l21 l21VarK016 = zB06 ? l21VarK0 : l21VarK0.k0(l21Var8);
                l21 l21VarU012 = zB06 ? l21Var8 : l21Var8.U0();
                l21 l21VarK017 = xqVar5.b;
                if (!zB06) {
                    l21VarK017 = l21VarK017.k0(l21VarU012);
                }
                l21 l21VarH5 = zn.h(l21VarK0, l21VarK016, l21VarK017);
                if (l21VarH5.d0()) {
                    return new nr(xqVar5, l21VarH5, xqVar5.c.T0(), 4);
                }
                l21 l21VarU013 = l21VarH5.U0();
                l21 l21VarK018 = zB06 ? l21VarH5 : l21VarH5.k0(l21VarU012);
                if (!zB06) {
                    l21VarK02 = l21VarK02.k0(l21Var8);
                }
                return new nr(xqVar5, l21VarU013, l21VarK02.a1(l21VarH5, l21VarK016).e(l21VarU013).e(l21VarK018), new l21[]{l21VarK018}, 4);
            case 5:
                if (l()) {
                    return this;
                }
                boolean zD06 = l21VarK02.d0();
                xq xqVar6 = this.a;
                if (zD06) {
                    return xqVar6.k();
                }
                l21 l21VarU014 = l21VarArr[0];
                boolean zB07 = l21VarU014.b0();
                l21 l21VarK019 = zB07 ? l21VarK0 : l21VarK0.k0(l21VarU014);
                if (!zB07) {
                    l21VarU014 = l21VarU014.U0();
                }
                l21 l21VarH6 = zn.h(l21VarK0, l21VarK019, l21VarU014);
                if (l21VarH6.d0()) {
                    return new nr(xqVar6, l21VarH6, xqVar6.c, 5);
                }
                l21 l21VarU015 = l21VarH6.U0();
                l21 l21VarK020 = zB07 ? l21VarH6 : l21VarH6.k0(l21VarU014);
                l21 l21VarU016 = l21VarK0.e(l21VarK02).U0();
                return new nr(xqVar6, l21VarU015, l21VarU016.e(l21VarH6).e(l21VarU014).k0(l21VarU016).e(l21VarU015), new l21[]{l21VarK020}, 5);
            case 6:
                if (l()) {
                    return this;
                }
                boolean zD07 = l21VarK02.d0();
                xq xqVar7 = this.a;
                if (zD07) {
                    return xqVar7.k();
                }
                l21 l21Var9 = l21VarArr[0];
                boolean zB08 = l21Var9.b0();
                l21 l21VarK021 = zB08 ? l21VarK0 : l21VarK0.k0(l21Var9);
                l21 l21VarU017 = zB08 ? l21Var9 : l21Var9.U0();
                l21 l21VarK022 = xqVar7.b;
                if (!zB08) {
                    l21VarK022 = l21VarK022.k0(l21VarU017);
                }
                l21 l21VarH7 = zn.h(l21VarK0, l21VarK021, l21VarK022);
                if (l21VarH7.d0()) {
                    return new nr(xqVar7, l21VarH7, xqVar7.c.T0(), i);
                }
                l21 l21VarU018 = l21VarH7.U0();
                l21 l21VarK023 = zB08 ? l21VarH7 : l21VarH7.k0(l21VarU017);
                if (!zB08) {
                    l21VarK02 = l21VarK02.k0(l21Var9);
                }
                return new nr(xqVar7, l21VarU018, l21VarK02.a1(l21VarH7, l21VarK021).e(l21VarU018).e(l21VarK023), new l21[]{l21VarK023}, 6);
            case 7:
                if (l()) {
                    return this;
                }
                boolean zD08 = l21VarK02.d0();
                xq xqVar8 = this.a;
                if (zD08) {
                    return xqVar8.k();
                }
                l21 l21Var10 = l21VarArr[0];
                boolean zB09 = l21Var10.b0();
                l21 l21VarK024 = zB09 ? l21VarK0 : l21VarK0.k0(l21Var10);
                l21 l21VarU019 = zB09 ? l21Var10 : l21Var10.U0();
                l21 l21VarH8 = zn.h(l21VarK0, l21VarK024, l21VarU019);
                if (l21VarH8.d0()) {
                    return new nr(xqVar8, l21VarH8, xqVar8.c.T0(), 7);
                }
                l21 l21VarU020 = l21VarH8.U0();
                l21 l21VarK025 = zB09 ? l21VarH8 : l21VarH8.k0(l21VarU019);
                if (!zB09) {
                    l21VarK02 = l21VarK02.k0(l21Var10);
                }
                return new nr(xqVar8, l21VarU020, l21VarK02.a1(l21VarH8, l21VarK024).e(l21VarU020).e(l21VarK025), new l21[]{l21VarK025}, 7);
            case 8:
                if (l()) {
                    return this;
                }
                boolean zD09 = l21VarK02.d0();
                xq xqVar9 = this.a;
                if (zD09) {
                    return xqVar9.k();
                }
                l21 l21Var11 = l21VarArr[0];
                boolean zB010 = l21Var11.b0();
                l21 l21VarK026 = zB010 ? l21VarK0 : l21VarK0.k0(l21Var11);
                l21 l21VarU021 = zB010 ? l21Var11 : l21Var11.U0();
                l21 l21VarK027 = xqVar9.b;
                if (!zB010) {
                    l21VarK027 = l21VarK027.k0(l21VarU021);
                }
                l21 l21VarH9 = zn.h(l21VarK0, l21VarK026, l21VarK027);
                if (l21VarH9.d0()) {
                    return new nr(xqVar9, l21VarH9, xqVar9.c.T0(), 8);
                }
                l21 l21VarU022 = l21VarH9.U0();
                l21 l21VarK028 = zB010 ? l21VarH9 : l21VarH9.k0(l21VarU021);
                if (!zB010) {
                    l21VarK02 = l21VarK02.k0(l21Var11);
                }
                return new nr(xqVar9, l21VarU022, l21VarK02.a1(l21VarH9, l21VarK026).e(l21VarU022).e(l21VarK028), new l21[]{l21VarK028}, 8);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                if (l()) {
                    return this;
                }
                boolean zD010 = l21VarK02.d0();
                xq xqVar10 = this.a;
                if (zD010) {
                    return xqVar10.k();
                }
                l21 l21Var12 = l21VarArr[0];
                boolean zB011 = l21Var12.b0();
                l21 l21VarK029 = zB011 ? l21VarK0 : l21VarK0.k0(l21Var12);
                l21 l21VarU023 = zB011 ? l21Var12 : l21Var12.U0();
                l21 l21VarK030 = xqVar10.b;
                if (!zB011) {
                    l21VarK030 = l21VarK030.k0(l21VarU023);
                }
                l21 l21VarH10 = zn.h(l21VarK0, l21VarK029, l21VarK030);
                if (l21VarH10.d0()) {
                    return new nr(xqVar10, l21VarH10, xqVar10.c.T0(), i3);
                }
                l21 l21VarU024 = l21VarH10.U0();
                l21 l21VarK031 = zB011 ? l21VarH10 : l21VarH10.k0(l21VarU023);
                if (!zB011) {
                    l21VarK02 = l21VarK02.k0(l21Var12);
                }
                return new nr(xqVar10, l21VarU024, l21VarK02.a1(l21VarH10, l21VarK029).e(l21VarU024).e(l21VarK031), new l21[]{l21VarK031}, 9);
            case 10:
                if (l()) {
                    return this;
                }
                boolean zD011 = l21VarK02.d0();
                xq xqVar11 = this.a;
                if (zD011) {
                    return xqVar11.k();
                }
                l21 l21VarU025 = l21VarArr[0];
                boolean zB012 = l21VarU025.b0();
                l21 l21VarU026 = zB012 ? l21VarU025 : l21VarU025.U0();
                l21 l21VarE6 = zB012 ? l21VarK0.U0().e(l21VarK0) : l21VarK0.e(l21VarU025).k0(l21VarK0);
                if (l21VarE6.d0()) {
                    return new nr(xqVar11, l21VarE6, xqVar11.c, 10);
                }
                l21 l21VarU027 = l21VarE6.U0();
                l21 l21VarK032 = zB012 ? l21VarE6 : l21VarE6.k0(l21VarU026);
                l21 l21VarU028 = l21VarK0.e(l21VarK02).U0();
                if (!zB012) {
                    l21VarU025 = l21VarU026.U0();
                }
                return new nr(xqVar11, l21VarU027, l21VarU028.e(l21VarE6).e(l21VarU026).k0(l21VarU028).e(l21VarU025).e(l21VarU027).e(l21VarK032), new l21[]{l21VarK032}, 10);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                if (l()) {
                    return this;
                }
                boolean zD012 = l21VarK02.d0();
                xq xqVar12 = this.a;
                if (zD012) {
                    return xqVar12.k();
                }
                l21 l21Var13 = l21VarArr[0];
                boolean zB013 = l21Var13.b0();
                l21 l21VarK033 = zB013 ? l21VarK0 : l21VarK0.k0(l21Var13);
                l21 l21VarU029 = zB013 ? l21Var13 : l21Var13.U0();
                l21 l21VarH11 = zn.h(l21VarK0, l21VarK033, l21VarU029);
                if (l21VarH11.d0()) {
                    return new nr(xqVar12, l21VarH11, xqVar12.c.T0(), 11);
                }
                l21 l21VarU030 = l21VarH11.U0();
                l21 l21VarK034 = zB013 ? l21VarH11 : l21VarH11.k0(l21VarU029);
                if (!zB013) {
                    l21VarK02 = l21VarK02.k0(l21Var13);
                }
                return new nr(xqVar12, l21VarU030, l21VarK02.a1(l21VarH11, l21VarK033).e(l21VarU030).e(l21VarK034), new l21[]{l21VarK034}, 11);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                if (l()) {
                    return this;
                }
                boolean zD013 = l21VarK02.d0();
                xq xqVar13 = this.a;
                if (zD013) {
                    return xqVar13.k();
                }
                l21 l21VarU031 = l21VarArr[0];
                boolean zB014 = l21VarU031.b0();
                l21 l21VarU032 = zB014 ? l21VarU031 : l21VarU031.U0();
                l21 l21VarE7 = zB014 ? l21VarK0.U0().e(l21VarK0) : l21VarK0.e(l21VarU031).k0(l21VarK0);
                if (l21VarE7.d0()) {
                    return new nr(xqVar13, l21VarE7, xqVar13.c, 12);
                }
                l21 l21VarU033 = l21VarE7.U0();
                l21 l21VarK035 = zB014 ? l21VarE7 : l21VarE7.k0(l21VarU032);
                l21 l21VarU034 = l21VarK0.e(l21VarK02).U0();
                if (!zB014) {
                    l21VarU031 = l21VarU032.U0();
                }
                return new nr(xqVar13, l21VarU033, l21VarU034.e(l21VarE7).e(l21VarU032).k0(l21VarU034).e(l21VarU031).e(l21VarU033).e(l21VarK035), new l21[]{l21VarK035}, 12);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                if (l()) {
                    return this;
                }
                boolean zD014 = l21VarK02.d0();
                xq xqVar14 = this.a;
                if (zD014) {
                    return xqVar14.k();
                }
                l21 l21VarU035 = l21VarArr[0];
                boolean zB015 = l21VarU035.b0();
                l21 l21VarU036 = zB015 ? l21VarU035 : l21VarU035.U0();
                l21 l21VarE8 = zB015 ? l21VarK0.U0().e(l21VarK0) : l21VarK0.e(l21VarU035).k0(l21VarK0);
                if (l21VarE8.d0()) {
                    return new nr(xqVar14, l21VarE8, xqVar14.c, 13);
                }
                l21 l21VarU037 = l21VarE8.U0();
                l21 l21VarK036 = zB015 ? l21VarE8 : l21VarE8.k0(l21VarU036);
                l21 l21VarU038 = l21VarK0.e(l21VarK02).U0();
                if (!zB015) {
                    l21VarU035 = l21VarU036.U0();
                }
                return new nr(xqVar14, l21VarU037, l21VarU038.e(l21VarE8).e(l21VarU036).k0(l21VarU038).e(l21VarU035).e(l21VarU037).e(l21VarK036), new l21[]{l21VarK036}, 13);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                if (l()) {
                    return this;
                }
                boolean zD015 = l21VarK02.d0();
                xq xqVar15 = this.a;
                if (zD015) {
                    return xqVar15.k();
                }
                l21 l21Var14 = l21VarArr[0];
                boolean zB016 = l21Var14.b0();
                l21 l21VarK037 = zB016 ? l21VarK0 : l21VarK0.k0(l21Var14);
                l21 l21VarU039 = zB016 ? l21Var14 : l21Var14.U0();
                l21 l21VarH12 = zn.h(l21VarK0, l21VarK037, l21VarU039);
                if (l21VarH12.d0()) {
                    return new nr(xqVar15, l21VarH12, xqVar15.c.T0(), 14);
                }
                l21 l21VarU040 = l21VarH12.U0();
                l21 l21VarK038 = zB016 ? l21VarH12 : l21VarH12.k0(l21VarU039);
                if (!zB016) {
                    l21VarK02 = l21VarK02.k0(l21Var14);
                }
                return new nr(xqVar15, l21VarU040, l21VarK02.a1(l21VarH12, l21VarK037).e(l21VarU040).e(l21VarK038), new l21[]{l21VarK038}, 14);
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                if (l()) {
                    return this;
                }
                boolean zD016 = l21VarK02.d0();
                xq xqVar16 = this.a;
                if (zD016) {
                    return xqVar16.k();
                }
                l21 l21VarU041 = l21VarArr[0];
                boolean zB017 = l21VarU041.b0();
                l21 l21VarU042 = zB017 ? l21VarU041 : l21VarU041.U0();
                l21 l21VarE9 = zB017 ? l21VarK0.U0().e(l21VarK0) : l21VarK0.e(l21VarU041).k0(l21VarK0);
                if (l21VarE9.d0()) {
                    return new nr(xqVar16, l21VarE9, xqVar16.c, 15);
                }
                l21 l21VarU043 = l21VarE9.U0();
                l21 l21VarK039 = zB017 ? l21VarE9 : l21VarE9.k0(l21VarU042);
                l21 l21VarU044 = l21VarK0.e(l21VarK02).U0();
                if (!zB017) {
                    l21VarU041 = l21VarU042.U0();
                }
                return new nr(xqVar16, l21VarU043, l21VarU044.e(l21VarE9).e(l21VarU042).k0(l21VarU044).e(l21VarU041).e(l21VarU043).e(l21VarK039), new l21[]{l21VarK039}, 15);
            case 16:
                if (l()) {
                    return this;
                }
                boolean zD017 = l21VarK02.d0();
                xq xqVar17 = this.a;
                if (zD017) {
                    return xqVar17.k();
                }
                l21 l21Var15 = l21VarArr[0];
                boolean zB018 = l21Var15.b0();
                l21 l21VarK040 = zB018 ? l21VarK0 : l21VarK0.k0(l21Var15);
                l21 l21VarU045 = zB018 ? l21Var15 : l21Var15.U0();
                l21 l21VarH13 = zn.h(l21VarK0, l21VarK040, l21VarU045);
                if (l21VarH13.d0()) {
                    return new nr(xqVar17, l21VarH13, xqVar17.c.T0(), 16);
                }
                l21 l21VarU046 = l21VarH13.U0();
                l21 l21VarK041 = zB018 ? l21VarH13 : l21VarH13.k0(l21VarU045);
                if (!zB018) {
                    l21VarK02 = l21VarK02.k0(l21Var15);
                }
                return new nr(xqVar17, l21VarU046, l21VarK02.a1(l21VarH13, l21VarK040).e(l21VarU046).e(l21VarK041), new l21[]{l21VarK041}, 16);
            case 17:
                if (l()) {
                    return this;
                }
                boolean zD018 = l21VarK02.d0();
                xq xqVar18 = this.a;
                if (zD018) {
                    return xqVar18.k();
                }
                l21 l21VarU047 = l21VarArr[0];
                boolean zB019 = l21VarU047.b0();
                l21 l21VarU048 = zB019 ? l21VarU047 : l21VarU047.U0();
                l21 l21VarE10 = zB019 ? l21VarK0.U0().e(l21VarK0) : l21VarK0.e(l21VarU047).k0(l21VarK0);
                if (l21VarE10.d0()) {
                    return new nr(xqVar18, l21VarE10, xqVar18.c, 17);
                }
                l21 l21VarU049 = l21VarE10.U0();
                l21 l21VarK042 = zB019 ? l21VarE10 : l21VarE10.k0(l21VarU048);
                l21 l21VarU050 = l21VarK0.e(l21VarK02).U0();
                if (!zB019) {
                    l21VarU047 = l21VarU048.U0();
                }
                return new nr(xqVar18, l21VarU049, l21VarU050.e(l21VarE10).e(l21VarU048).k0(l21VarU050).e(l21VarU047).e(l21VarU049).e(l21VarK042), new l21[]{l21VarK042}, 17);
            default:
                if (l()) {
                    return this;
                }
                fu0 fu0Var = (fu0) l21VarK02;
                boolean zH = s90.H(fu0Var.U);
                xq xqVar19 = this.a;
                if (zH) {
                    return xqVar19.k();
                }
                fu0 fu0Var2 = (fu0) l21VarK0;
                fu0 fu0Var3 = (fu0) l21VarArr[0];
                long[] jArr = new long[9];
                long[] jArr2 = new long[9];
                boolean zB020 = fu0Var3.b0();
                long[] jArr3 = fu0Var3.U;
                long[] jArrO = zB020 ? null : j31.O(jArr3);
                long[] jArr4 = fu0Var2.U;
                if (jArrO != null) {
                    j31.H(jArr4, jArrO, jArr);
                    j31.b0(jArr3, jArr2);
                    jArr4 = jArr;
                    jArr3 = jArr2;
                }
                long[] jArr5 = new long[9];
                j31.b0(fu0Var2.U, jArr5);
                j31.c(jArr4, jArr3, jArr5);
                if (s90.H(jArr5)) {
                    return new nr(xqVar19, new fu0(jArr5), iu0.j, i2);
                }
                long[] jArr6 = new long[18];
                j31.G(jArr5, jArr4, jArr6);
                fu0 fu0Var4 = new fu0(jArr);
                j31.b0(jArr5, jArr);
                fu0 fu0Var5 = new fu0(jArr5);
                if (jArrO != null) {
                    j31.F(jArr5, jArr3, jArr5);
                }
                long[] jArr7 = fu0Var.U;
                if (jArrO != null) {
                    j31.H(jArr7, jArrO, jArr2);
                    jArr7 = jArr2;
                }
                long[] jArr8 = new long[18];
                j31.w(jArr7, jArr8);
                j31.d(jArr6, jArr8, jArr6);
                j31.R(jArr6, jArr2);
                j31.c(jArr, jArr5, jArr2);
                return new nr(xqVar19, fu0Var4, new fu0(jArr2), new l21[]{fu0Var5}, 18);
        }
    }

    @Override // defpackage.or
    public final or v(or orVar) {
        int i = 6;
        int i2 = 18;
        int i3 = 9;
        int i4 = 1;
        l21[] l21VarArr = this.d;
        l21 l21Var = this.c;
        l21 l21Var2 = this.b;
        int i5 = 0;
        switch (this.h) {
            case 0:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        xq xqVar = this.a;
                        if (xqVar.f == 6) {
                            l21 l21Var3 = orVar.d[0];
                            l21 l21Var4 = orVar.b;
                            if (!l21Var4.d0() && l21Var3.b0()) {
                                l21 l21Var5 = l21VarArr[0];
                                l21 l21VarU0 = l21Var2.U0();
                                l21 l21VarU02 = l21Var.U0();
                                l21 l21VarU03 = l21Var5.U0();
                                l21 l21VarE = xqVar.b.k0(l21VarU03).e(l21VarU02).e(l21Var.k0(l21Var5));
                                l21 l21VarH = orVar.c.h();
                                l21 l21VarP0 = xqVar.b.e(l21VarH).k0(l21VarU03).e(l21VarU02).p0(l21VarE, l21VarU0, l21VarU03);
                                l21 l21VarK0 = l21Var4.k0(l21VarU03);
                                l21 l21VarU04 = l21VarK0.e(l21VarE).U0();
                                if (l21VarU04.d0()) {
                                    if (l21VarP0.d0()) {
                                    }
                                } else if (!l21VarP0.d0()) {
                                    l21 l21VarK02 = l21VarP0.U0().k0(l21VarK0);
                                    l21 l21VarK03 = l21VarP0.k0(l21VarU04).k0(l21VarU03);
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            case 1:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ = orVar.j();
                        l21 l21Var6 = orVar.b;
                        if (!l21Var6.d0() && l21VarJ.b0()) {
                            l21 l21Var7 = l21VarArr[0];
                            l21 l21VarU05 = l21Var2.U0();
                            l21 l21VarU06 = l21Var.U0();
                            l21 l21VarU07 = l21Var7.U0();
                            l21 l21VarK04 = l21Var.k0(l21Var7);
                            xq xqVar2 = this.a;
                            l21 l21VarE2 = xqVar2.b.k0(l21VarU07).e(l21VarU06).e(l21VarK04);
                            l21 l21VarH2 = orVar.c.h();
                            l21 l21VarP02 = xqVar2.b.e(l21VarH2).k0(l21VarU07).e(l21VarU06).p0(l21VarE2, l21VarU05, l21VarU07);
                            l21 l21VarK05 = l21Var6.k0(l21VarU07);
                            l21 l21VarU08 = l21VarK05.e(l21VarE2).U0();
                            if (l21VarU08.d0()) {
                                if (l21VarP02.d0()) {
                                }
                            } else if (!l21VarP02.d0()) {
                                l21 l21VarK06 = l21VarP02.U0().k0(l21VarK05);
                                l21 l21VarK07 = l21VarP02.k0(l21VarU08).k0(l21VarU07);
                            }
                        }
                        break;
                    }
                }
                break;
            case 2:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ2 = orVar.j();
                        l21 l21Var8 = orVar.b;
                        if (!l21Var8.d0() && l21VarJ2.b0()) {
                            l21 l21Var9 = l21VarArr[0];
                            l21 l21VarU09 = l21Var2.U0();
                            l21 l21VarU010 = l21Var.U0();
                            l21 l21VarU011 = l21Var9.U0();
                            l21 l21VarK08 = l21Var.k0(l21Var9);
                            xq xqVar3 = this.a;
                            l21 l21VarE3 = xqVar3.b.k0(l21VarU011).e(l21VarU010).e(l21VarK08);
                            l21 l21VarH3 = orVar.c.h();
                            l21 l21VarP03 = xqVar3.b.e(l21VarH3).k0(l21VarU011).e(l21VarU010).p0(l21VarE3, l21VarU09, l21VarU011);
                            l21 l21VarK09 = l21Var8.k0(l21VarU011);
                            l21 l21VarU012 = l21VarK09.e(l21VarE3).U0();
                            if (l21VarU012.d0()) {
                                if (l21VarP03.d0()) {
                                }
                            } else if (!l21VarP03.d0()) {
                                l21 l21VarK010 = l21VarP03.U0().k0(l21VarK09);
                                l21 l21VarK011 = l21VarP03.k0(l21VarU012).k0(l21VarU011);
                            }
                        }
                        break;
                    }
                }
                break;
            case 3:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ3 = orVar.j();
                        l21 l21Var10 = orVar.b;
                        if (!l21Var10.d0() && l21VarJ3.b0()) {
                            l21 l21Var11 = l21VarArr[0];
                            l21 l21VarU013 = l21Var2.U0();
                            l21 l21VarU014 = l21Var.U0();
                            l21 l21VarU015 = l21Var11.U0();
                            l21 l21VarK012 = l21Var.k0(l21Var11);
                            xq xqVar4 = this.a;
                            l21 l21VarE4 = xqVar4.b.k0(l21VarU015).e(l21VarU014).e(l21VarK012);
                            l21 l21VarH4 = orVar.c.h();
                            l21 l21VarP04 = xqVar4.b.e(l21VarH4).k0(l21VarU015).e(l21VarU014).p0(l21VarE4, l21VarU013, l21VarU015);
                            l21 l21VarK013 = l21Var10.k0(l21VarU015);
                            l21 l21VarU016 = l21VarK013.e(l21VarE4).U0();
                            if (l21VarU016.d0()) {
                                if (l21VarP04.d0()) {
                                }
                            } else if (!l21VarP04.d0()) {
                                l21 l21VarK014 = l21VarP04.U0().k0(l21VarK013);
                                l21 l21VarK015 = l21VarP04.k0(l21VarU016).k0(l21VarU015);
                            }
                        }
                        break;
                    }
                }
                break;
            case 4:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ4 = orVar.j();
                        l21 l21Var12 = orVar.b;
                        if (!l21Var12.d0() && l21VarJ4.b0()) {
                            l21 l21Var13 = l21VarArr[0];
                            l21 l21VarU017 = l21Var2.U0();
                            l21 l21VarU018 = l21Var.U0();
                            l21 l21VarU019 = l21Var13.U0();
                            l21 l21VarK016 = l21Var.k0(l21Var13);
                            xq xqVar5 = this.a;
                            l21 l21VarE5 = xqVar5.b.k0(l21VarU019).e(l21VarU018).e(l21VarK016);
                            l21 l21VarH5 = orVar.c.h();
                            l21 l21VarP05 = xqVar5.b.e(l21VarH5).k0(l21VarU019).e(l21VarU018).p0(l21VarE5, l21VarU017, l21VarU019);
                            l21 l21VarK017 = l21Var12.k0(l21VarU019);
                            l21 l21VarU020 = l21VarK017.e(l21VarE5).U0();
                            if (l21VarU020.d0()) {
                                if (l21VarP05.d0()) {
                                }
                            } else if (!l21VarP05.d0()) {
                                l21 l21VarK018 = l21VarP05.U0().k0(l21VarK017);
                                l21 l21VarK019 = l21VarP05.k0(l21VarU020).k0(l21VarU019);
                            }
                        }
                        break;
                    }
                }
                break;
            case 5:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ5 = orVar.j();
                        l21 l21Var14 = orVar.b;
                        if (!l21Var14.d0() && l21VarJ5.b0()) {
                            l21 l21Var15 = l21VarArr[0];
                            l21 l21VarU021 = l21Var2.U0();
                            l21 l21VarU022 = l21Var.U0();
                            l21 l21VarU023 = l21Var15.U0();
                            l21 l21VarE6 = l21VarU023.e(l21VarU022).e(l21Var.k0(l21Var15));
                            l21 l21Var16 = orVar.c;
                            l21 l21VarP06 = l21Var16.k0(l21VarU023).e(l21VarU022).p0(l21VarE6, l21VarU021, l21VarU023);
                            l21 l21VarK020 = l21Var14.k0(l21VarU023);
                            l21 l21VarU024 = l21VarK020.e(l21VarE6).U0();
                            boolean zD0 = l21VarU024.d0();
                            xq xqVar6 = this.a;
                            if (zD0) {
                                if (l21VarP06.d0()) {
                                }
                            } else if (!l21VarP06.d0()) {
                                l21 l21VarK021 = l21VarP06.U0().k0(l21VarK020);
                                l21 l21VarK022 = l21VarP06.k0(l21VarU024).k0(l21VarU023);
                            }
                        }
                        break;
                    }
                }
                break;
            case 6:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ6 = orVar.j();
                        l21 l21Var17 = orVar.b;
                        if (!l21Var17.d0() && l21VarJ6.b0()) {
                            l21 l21Var18 = l21VarArr[0];
                            l21 l21VarU025 = l21Var2.U0();
                            l21 l21VarU026 = l21Var.U0();
                            l21 l21VarU027 = l21Var18.U0();
                            l21 l21VarK023 = l21Var.k0(l21Var18);
                            xq xqVar7 = this.a;
                            l21 l21VarE7 = xqVar7.b.k0(l21VarU027).e(l21VarU026).e(l21VarK023);
                            l21 l21VarH6 = orVar.c.h();
                            l21 l21VarP07 = xqVar7.b.e(l21VarH6).k0(l21VarU027).e(l21VarU026).p0(l21VarE7, l21VarU025, l21VarU027);
                            l21 l21VarK024 = l21Var17.k0(l21VarU027);
                            l21 l21VarU028 = l21VarK024.e(l21VarE7).U0();
                            if (l21VarU028.d0()) {
                                if (l21VarP07.d0()) {
                                }
                            } else if (!l21VarP07.d0()) {
                                l21 l21VarK025 = l21VarP07.U0().k0(l21VarK024);
                                l21 l21VarK026 = l21VarP07.k0(l21VarU028).k0(l21VarU027);
                            }
                        }
                        break;
                    }
                }
                break;
            case 7:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ7 = orVar.j();
                        l21 l21Var19 = orVar.b;
                        if (!l21Var19.d0() && l21VarJ7.b0()) {
                            l21 l21Var20 = l21VarArr[0];
                            l21 l21VarU029 = l21Var2.U0();
                            l21 l21VarU030 = l21Var.U0();
                            l21 l21VarU031 = l21Var20.U0();
                            l21 l21VarE8 = l21VarU031.e(l21VarU030).e(l21Var.k0(l21Var20));
                            l21 l21Var21 = orVar.c;
                            l21 l21VarP08 = l21Var21.k0(l21VarU031).e(l21VarU030).p0(l21VarE8, l21VarU029, l21VarU031);
                            l21 l21VarK027 = l21Var19.k0(l21VarU031);
                            l21 l21VarU032 = l21VarK027.e(l21VarE8).U0();
                            boolean zD02 = l21VarU032.d0();
                            xq xqVar8 = this.a;
                            if (zD02) {
                                if (l21VarP08.d0()) {
                                }
                            } else if (!l21VarP08.d0()) {
                                l21 l21VarK028 = l21VarP08.U0().k0(l21VarK027);
                                l21 l21VarK029 = l21VarP08.k0(l21VarU032).k0(l21VarU031);
                            }
                        }
                        break;
                    }
                }
                break;
            case 8:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ8 = orVar.j();
                        l21 l21Var22 = orVar.b;
                        if (!l21Var22.d0() && l21VarJ8.b0()) {
                            l21 l21Var23 = l21VarArr[0];
                            l21 l21VarU033 = l21Var2.U0();
                            l21 l21VarU034 = l21Var.U0();
                            l21 l21VarU035 = l21Var23.U0();
                            l21 l21VarK030 = l21Var.k0(l21Var23);
                            xq xqVar9 = this.a;
                            l21 l21VarE9 = xqVar9.b.k0(l21VarU035).e(l21VarU034).e(l21VarK030);
                            l21 l21VarH7 = orVar.c.h();
                            l21 l21VarP09 = xqVar9.b.e(l21VarH7).k0(l21VarU035).e(l21VarU034).p0(l21VarE9, l21VarU033, l21VarU035);
                            l21 l21VarK031 = l21Var22.k0(l21VarU035);
                            l21 l21VarU036 = l21VarK031.e(l21VarE9).U0();
                            if (l21VarU036.d0()) {
                                if (l21VarP09.d0()) {
                                }
                            } else if (!l21VarP09.d0()) {
                                l21 l21VarK032 = l21VarP09.U0().k0(l21VarK031);
                                l21 l21VarK033 = l21VarP09.k0(l21VarU036).k0(l21VarU035);
                            }
                        }
                        break;
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ9 = orVar.j();
                        l21 l21Var24 = orVar.b;
                        if (!l21Var24.d0() && l21VarJ9.b0()) {
                            l21 l21Var25 = l21VarArr[0];
                            l21 l21VarU037 = l21Var2.U0();
                            l21 l21VarU038 = l21Var.U0();
                            l21 l21VarU039 = l21Var25.U0();
                            l21 l21VarK034 = l21Var.k0(l21Var25);
                            xq xqVar10 = this.a;
                            l21 l21VarE10 = xqVar10.b.k0(l21VarU039).e(l21VarU038).e(l21VarK034);
                            l21 l21VarH8 = orVar.c.h();
                            l21 l21VarP010 = xqVar10.b.e(l21VarH8).k0(l21VarU039).e(l21VarU038).p0(l21VarE10, l21VarU037, l21VarU039);
                            l21 l21VarK035 = l21Var24.k0(l21VarU039);
                            l21 l21VarU040 = l21VarK035.e(l21VarE10).U0();
                            if (l21VarU040.d0()) {
                                if (l21VarP010.d0()) {
                                }
                            } else if (!l21VarP010.d0()) {
                                l21 l21VarK036 = l21VarP010.U0().k0(l21VarK035);
                                l21 l21VarK037 = l21VarP010.k0(l21VarU040).k0(l21VarU039);
                            }
                        }
                        break;
                    }
                }
                break;
            case 10:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ10 = orVar.j();
                        l21 l21Var26 = orVar.b;
                        if (!l21Var26.d0() && l21VarJ10.b0()) {
                            l21 l21Var27 = l21VarArr[0];
                            l21 l21VarU041 = l21Var2.U0();
                            l21 l21VarU042 = l21Var.U0();
                            l21 l21VarU043 = l21Var27.U0();
                            l21 l21VarE11 = l21VarU042.e(l21Var.k0(l21Var27));
                            l21 l21VarH9 = orVar.c.h();
                            l21 l21VarP011 = l21VarH9.k0(l21VarU043).e(l21VarU042).p0(l21VarE11, l21VarU041, l21VarU043);
                            l21 l21VarK038 = l21Var26.k0(l21VarU043);
                            l21 l21VarU044 = l21VarK038.e(l21VarE11).U0();
                            boolean zD03 = l21VarU044.d0();
                            xq xqVar11 = this.a;
                            if (zD03) {
                                if (l21VarP011.d0()) {
                                }
                            } else if (!l21VarP011.d0()) {
                                l21 l21VarK039 = l21VarP011.U0().k0(l21VarK038);
                                l21 l21VarK040 = l21VarP011.k0(l21VarU044).k0(l21VarU043);
                            }
                        }
                        break;
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ11 = orVar.j();
                        l21 l21Var28 = orVar.b;
                        if (!l21Var28.d0() && l21VarJ11.b0()) {
                            l21 l21Var29 = l21VarArr[0];
                            l21 l21VarU045 = l21Var2.U0();
                            l21 l21VarU046 = l21Var.U0();
                            l21 l21VarU047 = l21Var29.U0();
                            l21 l21VarE12 = l21VarU047.e(l21VarU046).e(l21Var.k0(l21Var29));
                            l21 l21Var30 = orVar.c;
                            l21 l21VarP012 = l21Var30.k0(l21VarU047).e(l21VarU046).p0(l21VarE12, l21VarU045, l21VarU047);
                            l21 l21VarK041 = l21Var28.k0(l21VarU047);
                            l21 l21VarU048 = l21VarK041.e(l21VarE12).U0();
                            boolean zD04 = l21VarU048.d0();
                            xq xqVar12 = this.a;
                            if (zD04) {
                                if (l21VarP012.d0()) {
                                }
                            } else if (!l21VarP012.d0()) {
                                l21 l21VarK042 = l21VarP012.U0().k0(l21VarK041);
                                l21 l21VarK043 = l21VarP012.k0(l21VarU048).k0(l21VarU047);
                            }
                        }
                        break;
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ12 = orVar.j();
                        l21 l21Var31 = orVar.b;
                        if (!l21Var31.d0() && l21VarJ12.b0()) {
                            l21 l21Var32 = l21VarArr[0];
                            l21 l21VarU049 = l21Var2.U0();
                            l21 l21VarU050 = l21Var.U0();
                            l21 l21VarU051 = l21Var32.U0();
                            l21 l21VarE13 = l21VarU050.e(l21Var.k0(l21Var32));
                            l21 l21VarH10 = orVar.c.h();
                            l21 l21VarP013 = l21VarH10.k0(l21VarU051).e(l21VarU050).p0(l21VarE13, l21VarU049, l21VarU051);
                            l21 l21VarK044 = l21Var31.k0(l21VarU051);
                            l21 l21VarU052 = l21VarK044.e(l21VarE13).U0();
                            boolean zD05 = l21VarU052.d0();
                            xq xqVar13 = this.a;
                            if (zD05) {
                                if (l21VarP013.d0()) {
                                }
                            } else if (!l21VarP013.d0()) {
                                l21 l21VarK045 = l21VarP013.U0().k0(l21VarK044);
                                l21 l21VarK046 = l21VarP013.k0(l21VarU052).k0(l21VarU051);
                            }
                        }
                        break;
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ13 = orVar.j();
                        l21 l21Var33 = orVar.b;
                        if (!l21Var33.d0() && l21VarJ13.b0()) {
                            l21 l21Var34 = l21VarArr[0];
                            l21 l21VarU053 = l21Var2.U0();
                            l21 l21VarU054 = l21Var.U0();
                            l21 l21VarU055 = l21Var34.U0();
                            l21 l21VarE14 = l21VarU054.e(l21Var.k0(l21Var34));
                            l21 l21VarH11 = orVar.c.h();
                            l21 l21VarP014 = l21VarH11.k0(l21VarU055).e(l21VarU054).p0(l21VarE14, l21VarU053, l21VarU055);
                            l21 l21VarK047 = l21Var33.k0(l21VarU055);
                            l21 l21VarU056 = l21VarK047.e(l21VarE14).U0();
                            boolean zD06 = l21VarU056.d0();
                            xq xqVar14 = this.a;
                            if (zD06) {
                                if (l21VarP014.d0()) {
                                }
                            } else if (!l21VarP014.d0()) {
                                l21 l21VarK048 = l21VarP014.U0().k0(l21VarK047);
                                l21 l21VarK049 = l21VarP014.k0(l21VarU056).k0(l21VarU055);
                            }
                        }
                        break;
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ14 = orVar.j();
                        l21 l21Var35 = orVar.b;
                        if (!l21Var35.d0() && l21VarJ14.b0()) {
                            l21 l21Var36 = l21VarArr[0];
                            l21 l21VarU057 = l21Var2.U0();
                            l21 l21VarU058 = l21Var.U0();
                            l21 l21VarU059 = l21Var36.U0();
                            l21 l21VarE15 = l21VarU059.e(l21VarU058).e(l21Var.k0(l21Var36));
                            l21 l21Var37 = orVar.c;
                            l21 l21VarP015 = l21Var37.k0(l21VarU059).e(l21VarU058).p0(l21VarE15, l21VarU057, l21VarU059);
                            l21 l21VarK050 = l21Var35.k0(l21VarU059);
                            l21 l21VarU060 = l21VarK050.e(l21VarE15).U0();
                            boolean zD07 = l21VarU060.d0();
                            xq xqVar15 = this.a;
                            if (zD07) {
                                if (l21VarP015.d0()) {
                                }
                            } else if (!l21VarP015.d0()) {
                                l21 l21VarK051 = l21VarP015.U0().k0(l21VarK050);
                                l21 l21VarK052 = l21VarP015.k0(l21VarU060).k0(l21VarU059);
                            }
                        }
                        break;
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ15 = orVar.j();
                        l21 l21Var38 = orVar.b;
                        if (!l21Var38.d0() && l21VarJ15.b0()) {
                            l21 l21Var39 = l21VarArr[0];
                            l21 l21VarU061 = l21Var2.U0();
                            l21 l21VarU062 = l21Var.U0();
                            l21 l21VarU063 = l21Var39.U0();
                            l21 l21VarE16 = l21VarU062.e(l21Var.k0(l21Var39));
                            l21 l21VarH12 = orVar.c.h();
                            l21 l21VarP016 = l21VarH12.k0(l21VarU063).e(l21VarU062).p0(l21VarE16, l21VarU061, l21VarU063);
                            l21 l21VarK053 = l21Var38.k0(l21VarU063);
                            l21 l21VarU064 = l21VarK053.e(l21VarE16).U0();
                            boolean zD08 = l21VarU064.d0();
                            xq xqVar16 = this.a;
                            if (zD08) {
                                if (l21VarP016.d0()) {
                                }
                            } else if (!l21VarP016.d0()) {
                                l21 l21VarK054 = l21VarP016.U0().k0(l21VarK053);
                                l21 l21VarK055 = l21VarP016.k0(l21VarU064).k0(l21VarU063);
                            }
                        }
                        break;
                    }
                }
                break;
            case 16:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ16 = orVar.j();
                        l21 l21Var40 = orVar.b;
                        if (!l21Var40.d0() && l21VarJ16.b0()) {
                            l21 l21Var41 = l21VarArr[0];
                            l21 l21VarU065 = l21Var2.U0();
                            l21 l21VarU066 = l21Var.U0();
                            l21 l21VarU067 = l21Var41.U0();
                            l21 l21VarE17 = l21VarU067.e(l21VarU066).e(l21Var.k0(l21Var41));
                            l21 l21Var42 = orVar.c;
                            l21 l21VarP017 = l21Var42.k0(l21VarU067).e(l21VarU066).p0(l21VarE17, l21VarU065, l21VarU067);
                            l21 l21VarK056 = l21Var40.k0(l21VarU067);
                            l21 l21VarU068 = l21VarK056.e(l21VarE17).U0();
                            boolean zD09 = l21VarU068.d0();
                            xq xqVar17 = this.a;
                            if (zD09) {
                                if (l21VarP017.d0()) {
                                }
                            } else if (!l21VarP017.d0()) {
                                l21 l21VarK057 = l21VarP017.U0().k0(l21VarK056);
                                l21 l21VarK058 = l21VarP017.k0(l21VarU068).k0(l21VarU067);
                            }
                        }
                        break;
                    }
                }
                break;
            case 17:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else if (!l21Var2.d0()) {
                        l21 l21VarJ17 = orVar.j();
                        l21 l21Var43 = orVar.b;
                        if (!l21Var43.d0() && l21VarJ17.b0()) {
                            l21 l21Var44 = l21VarArr[0];
                            l21 l21VarU069 = l21Var2.U0();
                            l21 l21VarU070 = l21Var.U0();
                            l21 l21VarU071 = l21Var44.U0();
                            l21 l21VarE18 = l21VarU070.e(l21Var.k0(l21Var44));
                            l21 l21VarH13 = orVar.c.h();
                            l21 l21VarP018 = l21VarH13.k0(l21VarU071).e(l21VarU070).p0(l21VarE18, l21VarU069, l21VarU071);
                            l21 l21VarK059 = l21Var43.k0(l21VarU071);
                            l21 l21VarU072 = l21VarK059.e(l21VarE18).U0();
                            boolean zD010 = l21VarU072.d0();
                            xq xqVar18 = this.a;
                            if (zD010) {
                                if (l21VarP018.d0()) {
                                }
                            } else if (!l21VarP018.d0()) {
                                l21 l21VarK060 = l21VarP018.U0().k0(l21VarK059);
                                l21 l21VarK061 = l21VarP018.k0(l21VarU072).k0(l21VarU071);
                            }
                        }
                        break;
                    }
                }
                break;
            default:
                if (!l()) {
                    if (orVar.l()) {
                        break;
                    } else {
                        fu0 fu0Var = (fu0) l21Var2;
                        if (!s90.H(fu0Var.U)) {
                            fu0 fu0Var2 = (fu0) orVar.b;
                            fu0 fu0Var3 = (fu0) orVar.j();
                            if (!s90.H(fu0Var2.U) && fu0Var3.b0()) {
                                fu0 fu0Var4 = (fu0) l21Var;
                                fu0 fu0Var5 = (fu0) l21VarArr[0];
                                fu0 fu0Var6 = (fu0) orVar.c;
                                long[] jArr = new long[9];
                                long[] jArr2 = new long[9];
                                long[] jArr3 = new long[9];
                                long[] jArr4 = new long[9];
                                j31.b0(fu0Var.U, jArr);
                                j31.b0(fu0Var4.U, jArr2);
                                j31.b0(fu0Var5.U, jArr3);
                                j31.F(fu0Var4.U, fu0Var5.U, jArr4);
                                j31.c(jArr3, jArr2, jArr4);
                                long[] jArrO = j31.O(jArr3);
                                j31.H(fu0Var6.U, jArrO, jArr3);
                                j31.a(jArr3, jArr2, jArr3);
                                long[] jArr5 = new long[18];
                                j31.G(jArr3, jArr4, jArr5);
                                long[] jArr6 = new long[18];
                                j31.u(jArr, jArrO, jArr6);
                                j31.d(jArr5, jArr6, jArr5);
                                j31.R(jArr5, jArr3);
                                j31.H(fu0Var2.U, jArrO, jArr);
                                j31.a(jArr, jArr4, jArr2);
                                j31.b0(jArr2, jArr2);
                                boolean zH = s90.H(jArr2);
                                xq xqVar19 = this.a;
                                if (zH) {
                                    if (s90.H(jArr3)) {
                                    }
                                } else if (!s90.H(jArr3)) {
                                    fu0 fu0Var7 = new fu0(23);
                                    long[] jArr7 = new long[9];
                                    fu0Var7.U = jArr7;
                                    j31.b0(jArr3, jArr7);
                                    j31.F(jArr7, jArr, jArr7);
                                    fu0 fu0Var8 = new fu0(jArr);
                                    j31.F(jArr3, jArr2, jArr);
                                    j31.H(jArr, jArrO, jArr);
                                    fu0 fu0Var9 = new fu0(jArr2);
                                    j31.a(jArr3, jArr2, jArr2);
                                    j31.b0(jArr2, jArr2);
                                    for (int i6 = 0; i6 < 18; i6++) {
                                        jArr5[i6] = 0;
                                    }
                                    j31.G(fu0Var9.U, jArr4, jArr5);
                                    long[] jArr8 = fu0Var6.U;
                                    jArr4[0] = jArr8[0] ^ 1;
                                    for (int i7 = 1; i7 < 9; i7++) {
                                        jArr4[i7] = jArr8[i7];
                                    }
                                    j31.G(jArr4, fu0Var8.U, jArr5);
                                    j31.R(jArr5, fu0Var9.U);
                                }
                            }
                            break;
                        }
                    }
                }
                break;
        }
        return u().a(orVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ nr(xq xqVar, l21 l21Var, l21 l21Var2, l21[] l21VarArr, int i) {
        super(xqVar, l21Var, l21Var2, l21VarArr, 0);
        this.h = i;
    }
}
