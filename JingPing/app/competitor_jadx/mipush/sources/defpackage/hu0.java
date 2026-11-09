package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class hu0 extends sq {
    public static final l21[] h = {new fu0(qq.b)};
    public final nr g;

    public hu0() {
        super(571, 2, 5, 10);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 17);
        this.b = new fu0(BigInteger.valueOf(0L));
        this.c = new fu0(BigInteger.valueOf(1L));
        this.d = new BigInteger(1, c30.b("020000000000000000000000000000000000000000000000000000000000000000000000131850E1F19A63E4B391A8DB917F4138B630D84BE5D639381E91DEB45CFE778F637C1001"));
        this.e = BigInteger.valueOf(4L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new hu0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 18];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            s90.q(i2, ((fu0) orVar.b).U, jArr);
            s90.q(i2 + 9, ((fu0) orVar.c).U, jArr);
            i2 += 18;
        }
        return new gu0(this, i, jArr, 0);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 17);
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
    public final boolean p(int i) {
        return i == 6;
    }
}
