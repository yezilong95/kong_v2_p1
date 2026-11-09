package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class tt0 extends sq {
    public static final l21[] h = {new rt0(qq.b)};
    public final nr g;

    public tt0() {
        super(193, 15, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 9);
        this.b = new rt0(new BigInteger(1, c30.b("0163F35A5137C2CE3EA6ED8667190B0BC43ECD69977702709B")));
        this.c = new rt0(new BigInteger(1, c30.b("00C9BB9E8927D4D64C377E2AB2856A5B16E3EFB7F61D4316AE")));
        this.d = new BigInteger(1, c30.b("010000000000000000000000015AAB561B005413CCD4EE99D5"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new tt0();
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
        return new zj(this, i, jArr, 22);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 9);
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
