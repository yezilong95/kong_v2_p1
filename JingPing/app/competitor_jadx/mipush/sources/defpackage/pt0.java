package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class pt0 extends sq {
    public static final l21[] h = {new nt0(qq.b)};
    public final nr g;

    public pt0() {
        super(163, 3, 6, 7);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 6);
        this.b = new nt0(new BigInteger(1, c30.b("07B6882CAAEFA84F9554FF8428BD88E246D2782AE2")));
        this.c = new nt0(new BigInteger(1, c30.b("0713612DCDDCB40AAB946BDA29CA91F73AF958AFD9")));
        this.d = new BigInteger(1, c30.b("03FFFFFFFFFFFFFFFFFFFF48AAB689C29CA710279B"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new pt0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 6];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            o80.l(i2, ((nt0) orVar.b).U, jArr);
            o80.l(i2 + 3, ((nt0) orVar.c).U, jArr);
            i2 += 6;
        }
        return new zj(this, i, jArr, 19);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 6);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new nt0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 163;
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
