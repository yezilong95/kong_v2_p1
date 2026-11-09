package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class it0 extends sq {
    public static final l21[] h = {new ht0(qq.b)};
    public final nr g;

    public it0() {
        super(113, 9, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 1);
        this.b = new ht0(new BigInteger(1, c30.b("003088250CA6E7C7FE649CE85820F7")));
        this.c = new ht0(new BigInteger(1, c30.b("00E8BEE4D3E2260744188BE0E9C723")));
        this.d = new BigInteger(1, c30.b("0100000000000000D9CCEC8A39E56F"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new it0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 4];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            la0.l(i2, ((ht0) orVar.b).U, jArr);
            la0.l(i2 + 2, ((ht0) orVar.c).U, jArr);
            i2 += 4;
        }
        return new zj(this, i, jArr, 14);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 1);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new ht0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 113;
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
