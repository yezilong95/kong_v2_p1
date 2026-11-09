package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class st0 extends sq {
    public static final l21[] h = {new rt0(qq.b)};
    public final nr g;

    public st0() {
        super(193, 15, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 8);
        this.b = new rt0(new BigInteger(1, c30.b("0017858FEB7A98975169E171F77B4087DE098AC8A911DF7B01")));
        this.c = new rt0(new BigInteger(1, c30.b("00FDFB49BFE6C3A89FACADAA7A1E5BBC7CC1C2E5D831478814")));
        this.d = new BigInteger(1, c30.b("01000000000000000000000000C7F34A778F443ACC920EBA49"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new st0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 8];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            fa0.v(i2, ((rt0) orVar.b).U, jArr);
            fa0.v(i2 + 4, ((rt0) orVar.c).U, jArr);
            i2 += 8;
        }
        return new zj(this, i, jArr, 21);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 8);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new rt0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 193;
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
