package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class qt0 extends sq {
    public static final l21[] h = {new nt0(qq.b)};
    public final nr g;

    public qt0() {
        super(163, 3, 6, 7);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 7);
        this.b = new nt0(BigInteger.valueOf(1L));
        this.c = new nt0(new BigInteger(1, c30.b("020A601907B8C953CA1481EB10512F78744A3205FD")));
        this.d = new BigInteger(1, c30.b("040000000000000000000292FE77E70C12A4234C33"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new qt0();
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
        return new zj(this, i, jArr, 20);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 7);
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
