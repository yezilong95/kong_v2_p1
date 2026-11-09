package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class vt0 extends sq {
    public static final l21[] h = {new ut0(qq.b)};
    public final nr g;

    public vt0() {
        super(233, 74, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 10);
        this.b = new ut0(BigInteger.valueOf(0L));
        this.c = new ut0(BigInteger.valueOf(1L));
        this.d = new BigInteger(1, c30.b("8000000000000000000000000000069D5BB915BCD46EFB1AD5F173ABDF"));
        this.e = BigInteger.valueOf(4L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new vt0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 8];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            fa0.v(i2, ((ut0) orVar.b).U, jArr);
            fa0.v(i2 + 4, ((ut0) orVar.c).U, jArr);
            i2 += 8;
        }
        return new zj(this, i, jArr, 23);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 10);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new ut0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 233;
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
