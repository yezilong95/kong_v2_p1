package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class bu0 extends sq {
    public static final l21[] h = {new zt0(qq.b)};
    public final nr g;

    public bu0() {
        super(283, 5, 7, 12);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 14);
        this.b = new zt0(BigInteger.valueOf(1L));
        this.c = new zt0(new BigInteger(1, c30.b("027B680AC8B8596DA5A4AF8A19A0303FCA97FD7645309FA2A581485AF6263E313B79A2F5")));
        this.d = new BigInteger(1, c30.b("03FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEF90399660FC938A90165B042A7CEFADB307"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new bu0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 10];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            la0.m(i2, ((zt0) orVar.b).U, jArr);
            la0.m(i2 + 5, ((zt0) orVar.c).U, jArr);
            i2 += 10;
        }
        return new zj(this, i, jArr, 27);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 14);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new zt0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 283;
    }

    @Override // defpackage.xq
    public final or k() {
        return this.g;
    }

    @Override // defpackage.xq
    public final boolean p(int i) {
        return i == 6;
    }
}
