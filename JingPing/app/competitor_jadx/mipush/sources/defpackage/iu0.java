package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class iu0 extends sq {
    public static final l21[] h = {new fu0(qq.b)};
    public static final fu0 i;
    public static final fu0 j;
    public final nr g;

    static {
        fu0 fu0Var = new fu0(new BigInteger(1, c30.b("02F40E7E2221F295DE297117B7F3D62F5C6A97FFCB8CEFF1CD6BA8CE4A9A18AD84FFABBD8EFA59332BE7AD6756A66E294AFD185A78FF12AA520E4DE739BACA0C7FFEFF7F2955727A")));
        i = fu0Var;
        j = (fu0) fu0Var.T0();
    }

    public iu0() {
        super(571, 2, 5, 10);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 18);
        this.b = new fu0(BigInteger.valueOf(1L));
        this.c = i;
        this.d = new BigInteger(1, c30.b("03FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE661CE18FF55987308059B186823851EC7DD9CA1161DE93D5174D66E8382E9BB2FE84E47"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new iu0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        long[] jArr = new long[i2 * 18];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            s90.q(i3, ((fu0) orVar.b).U, jArr);
            s90.q(i3 + 9, ((fu0) orVar.c).U, jArr);
            i3 += 18;
        }
        return new gu0(this, i2, jArr, 1);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 18);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new fu0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 571;
    }

    @Override // defpackage.xq
    public final or k() {
        return this.g;
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 6;
    }
}
