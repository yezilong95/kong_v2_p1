package defpackage;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class wq extends tq {
    public static final Set j = Collections.synchronizedSet(new HashSet());
    public static final l0 k = new l0();
    public BigInteger g;
    public BigInteger h;
    public ck i;

    /* JADX WARN: Code restructure failed: missing block: B:191:0x023e, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x024f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public wq(java.math.BigInteger r18, java.math.BigInteger r19, java.math.BigInteger r20, java.math.BigInteger r21, java.math.BigInteger r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 693
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wq.<init>(java.math.BigInteger, java.math.BigInteger, java.math.BigInteger, java.math.BigInteger, java.math.BigInteger, boolean):void");
    }

    @Override // defpackage.xq
    public final xq a() {
        l21 l21Var = this.b;
        l21 l21Var2 = this.c;
        BigInteger bigInteger = this.d;
        BigInteger bigInteger2 = this.e;
        BigInteger bigInteger3 = this.g;
        wq wqVar = new wq(bigInteger3);
        wqVar.g = bigInteger3;
        wqVar.h = this.h;
        l21 l21Var3 = null;
        wqVar.i = new ck(wqVar, l21Var3, l21Var3, 1);
        wqVar.b = l21Var;
        wqVar.c = l21Var2;
        wqVar.d = bigInteger;
        wqVar.e = bigInteger2;
        wqVar.f = 4;
        return wqVar;
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 1);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        if (bigInteger != null && bigInteger.signum() >= 0) {
            BigInteger bigInteger2 = this.g;
            if (bigInteger.compareTo(bigInteger2) < 0) {
                return new cr(bigInteger2, this.h, bigInteger);
            }
        }
        throw new IllegalArgumentException("x value invalid for Fp field element");
    }

    @Override // defpackage.xq
    public final int j() {
        return this.g.bitLength();
    }

    @Override // defpackage.xq
    public final or k() {
        return this.i;
    }

    @Override // defpackage.xq
    public final or l(or orVar) {
        int i;
        return (this == orVar.a || this.f != 2 || orVar.l() || !((i = orVar.a.f) == 2 || i == 3 || i == 4)) ? super.l(orVar) : new ck(this, h(orVar.b.h1()), h(orVar.c.h1()), new l21[]{h(orVar.d[0].h1())}, 1);
    }

    @Override // defpackage.xq
    public final boolean p(int i) {
        return i == 0 || i == 1 || i == 2 || i == 4;
    }
}
