package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class yt0 extends sq {
    public static final l21[] h = {new xt0(qq.b)};
    public final nr g;

    public yt0() {
        super(239, 158, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 12);
        this.b = new xt0(BigInteger.valueOf(0L));
        this.c = new xt0(BigInteger.valueOf(1L));
        this.d = new BigInteger(1, c30.b("2000000000000000000000000000005A79FEC67CB6E91F1C1DA800E478A5"));
        this.e = BigInteger.valueOf(4L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new yt0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 8];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            fa0.v(i2, ((xt0) orVar.b).U, jArr);
            fa0.v(i2 + 4, ((xt0) orVar.c).U, jArr);
            i2 += 8;
        }
        return new zj(this, i, jArr, 25);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 12);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new xt0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 239;
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
