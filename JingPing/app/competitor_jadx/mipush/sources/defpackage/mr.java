package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class mr extends or {
    public final /* synthetic */ int g;

    /* JADX WARN: Illegal instructions before constructor call */
    public mr(xq xqVar, l21 l21Var, l21 l21Var2, int i) {
        l21[] l21VarArr;
        this.g = i;
        int i2 = xqVar == null ? 0 : xqVar.f;
        if (i2 == 0 || i2 == 5) {
            l21VarArr = or.f;
        } else {
            l21 l21VarH = xqVar.h(qq.b);
            if (i2 == 1 || i2 == 2) {
                l21VarArr = new l21[]{l21VarH};
            } else if (i2 == 3) {
                l21VarArr = new l21[]{l21VarH, l21VarH, l21VarH};
            } else if (i2 != 4) {
                if (i2 != 6) {
                    throw new IllegalArgumentException("unknown coordinate system");
                }
                l21VarArr = new l21[]{l21VarH};
            } else {
                l21VarArr = new l21[]{l21VarH, xqVar.b};
            }
        }
        super(xqVar, l21Var, l21Var2, l21VarArr);
    }

    @Override // defpackage.or
    public boolean f() {
        return e().g1();
    }

    @Override // defpackage.or
    public final boolean q() {
        l21 l21VarP0;
        l21 l21VarA1;
        switch (this.g) {
            case 0:
                xq xqVar = this.a;
                l21 l21VarK0 = xqVar.b;
                l21 l21VarK02 = xqVar.c;
                int i = xqVar.f;
                l21[] l21VarArr = this.d;
                l21 l21Var = this.c;
                l21 l21Var2 = this.b;
                if (i != 6) {
                    l21 l21VarK03 = l21Var.e(l21Var2).k0(l21Var);
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                        l21 l21Var3 = l21VarArr[0];
                        if (!l21Var3.b0()) {
                            l21 l21VarK04 = l21Var3.k0(l21Var3.U0());
                            l21VarK03 = l21VarK03.k0(l21Var3);
                            l21VarK0 = l21VarK0.k0(l21Var3);
                            l21VarK02 = l21VarK02.k0(l21VarK04);
                        }
                    }
                    return l21VarK03.equals(l21Var2.e(l21VarK0).k0(l21Var2.U0()).e(l21VarK02));
                }
                l21 l21Var4 = l21VarArr[0];
                boolean zB0 = l21Var4.b0();
                if (l21Var2.d0()) {
                    l21 l21VarU0 = l21Var.U0();
                    if (!zB0) {
                        l21VarK02 = l21VarK02.k0(l21Var4.U0());
                    }
                    return l21VarU0.equals(l21VarK02);
                }
                l21 l21VarU02 = l21Var2.U0();
                if (zB0) {
                    l21VarP0 = zn.h(l21Var, l21Var, l21VarK0);
                    l21VarA1 = l21VarU02.U0().e(l21VarK02);
                } else {
                    l21 l21VarU03 = l21Var4.U0();
                    l21 l21VarU04 = l21VarU03.U0();
                    l21VarP0 = l21Var.e(l21Var4).p0(l21Var, l21VarK0, l21VarU03);
                    l21VarA1 = l21VarU02.a1(l21VarK02, l21VarU04);
                }
                return l21VarP0.k0(l21VarU02).equals(l21VarA1);
            default:
                xq xqVar2 = this.a;
                l21 l21VarK05 = xqVar2.b;
                l21 l21VarK06 = xqVar2.c;
                l21 l21VarU05 = this.c.U0();
                int iG = g();
                if (iG != 0) {
                    l21[] l21VarArr2 = this.d;
                    if (iG == 1) {
                        l21 l21Var5 = l21VarArr2[0];
                        if (!l21Var5.b0()) {
                            l21 l21VarU06 = l21Var5.U0();
                            l21 l21VarK07 = l21Var5.k0(l21VarU06);
                            l21VarU05 = l21VarU05.k0(l21Var5);
                            l21VarK05 = l21VarK05.k0(l21VarU06);
                            l21VarK06 = l21VarK06.k0(l21VarK07);
                        }
                    } else {
                        if (iG != 2 && iG != 3 && iG != 4) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                        l21 l21Var6 = l21VarArr2[0];
                        if (!l21Var6.b0()) {
                            l21 l21VarU07 = l21Var6.U0();
                            l21 l21VarU08 = l21VarU07.U0();
                            l21 l21VarK08 = l21VarU07.k0(l21VarU08);
                            l21VarK05 = l21VarK05.k0(l21VarU08);
                            l21VarK06 = l21VarK06.k0(l21VarK08);
                        }
                    }
                }
                l21 l21Var7 = this.b;
                return l21VarU05.equals(l21Var7.U0().e(l21VarK05).k0(l21Var7).e(l21VarK06));
        }
    }

    @Override // defpackage.or
    public boolean r() {
        switch (this.g) {
            case 0:
                xq xqVar = this.a;
                BigInteger bigInteger = xqVar.e;
                if (!qq.c.equals(bigInteger)) {
                    if (!qq.e.equals(bigInteger)) {
                        break;
                    } else {
                        or orVarO = o();
                        orVarO.b();
                        sq sqVar = (sq) xqVar;
                        l21 l21Var = xqVar.b;
                        l21 l21Var2 = orVarO.b;
                        l21 l21VarR = sqVar.r(l21Var2.e(l21Var));
                        if (l21VarR != null) {
                            if (((zq) l21Var2.k0(l21VarR).e(orVarO.e())).k1() == 0) {
                            }
                        }
                    }
                } else {
                    or orVarO2 = o();
                    orVarO2.b();
                    if (((zq) orVarO2.b).k1() != 0) {
                    }
                }
                break;
        }
        return super.r();
    }

    @Override // defpackage.or
    public final or s(or orVar) {
        switch (this.g) {
            case 0:
                if (!orVar.l()) {
                }
                break;
            default:
                if (!orVar.l()) {
                }
                break;
        }
        return a(orVar.n());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ mr(xq xqVar, l21 l21Var, l21 l21Var2, l21[] l21VarArr, int i) {
        super(xqVar, l21Var, l21Var2, l21VarArr);
        this.g = i;
    }
}
