package defpackage;

import io.nats.client.support.Status;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class du0 extends sq {
    public static final l21[] h = {new cu0(qq.b)};
    public final nr g;

    public du0() {
        super(Status.CONFLICT_CODE, 87, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 15);
        this.b = new cu0(BigInteger.valueOf(0L));
        this.c = new cu0(BigInteger.valueOf(1L));
        this.d = new BigInteger(1, c30.b("7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE5F83B2D4EA20400EC4557D5ED3E3E7CA5B4B5C83B8E01E5FCF"));
        this.e = BigInteger.valueOf(4L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new du0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        long[] jArr = new long[i * 14];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            or orVar = orVarArr[i3];
            o80.m(i2, ((cu0) orVar.b).U, jArr);
            o80.m(i2 + 7, ((cu0) orVar.c).U, jArr);
            i2 += 14;
        }
        return new zj(this, i, jArr, 28);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 15);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new cu0(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return Status.CONFLICT_CODE;
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
