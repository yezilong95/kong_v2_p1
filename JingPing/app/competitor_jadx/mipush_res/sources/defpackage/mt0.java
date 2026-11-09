package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class mt0 extends sq {
    public static final l21[] h = {new kt0(qq.b)};
    public final nr g;

    public mt0() {
        super(131, 2, 3, 8);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 4);
        this.b = new kt0(new BigInteger(1, c30.b("03E5A88919D7CAFCBF415F07C2176573B2")));
        this.c = new kt0(new BigInteger(1, c30.b("04B8266A46C55657AC734CE38F018F2192")));
        this.d = new BigInteger(1, c30.b("0400000000000000016954A233049BA98F"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new mt0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 6];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            o80.l(i2, ((kt0) orVar.b).U, jArr);
            o80.l(i2 + 3, ((kt0) orVar.c).U, jArr);
            i2 += 6;
        }
        return new zj(this, i, jArr, 17);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 4);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new kt0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return 131;
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
