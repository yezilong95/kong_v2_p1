package defpackage;

import io.nats.client.support.Status;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class eu0 extends sq {
    public static final l21[] h = {new cu0(qq.b)};
    public final nr g;

    public eu0() {
        super(Status.CONFLICT_CODE, 87, 0, 0);
        l21 l21Var = null;
        this.g = new nr(this, l21Var, l21Var, 16);
        this.b = new cu0(BigInteger.valueOf(1L));
        this.c = new cu0(new BigInteger(1, c30.b("0021A5C2C8EE9FEB5C4B9A753B7B476B7FD6422EF1F3DD674761FA99D6AC27C8A9A197B272822F6CD57A55AA4F50AE317B13545F")));
        this.d = new BigInteger(1, c30.b("010000000000000000000000000000000000000000000000000001E2AAD6A612F33307BE5FA47C3C9E052F838164CD37D9A21173"));
        this.e = BigInteger.valueOf(2L);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new eu0();
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
        return new zj(this, i, jArr, 29);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 16);
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
