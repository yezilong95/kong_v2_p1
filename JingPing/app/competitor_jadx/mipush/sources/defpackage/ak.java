package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class ak extends tq {
    public static final BigInteger h = bk.V;
    public static final BigInteger i;
    public static final BigInteger j;
    public static final l21[] k;
    public final ck g;

    static {
        BigInteger bigInteger = new BigInteger(1, c30.b("2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA984914A144"));
        i = bigInteger;
        j = new BigInteger(1, c30.b("7B425ED097B425ED097B425ED097B425ED097B425ED097B4260B5E9C7710C864"));
        k = new l21[]{new bk(qq.b), new bk(bigInteger)};
    }

    public ak() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 0);
        this.b = new bk(i);
        this.c = new bk(j);
        this.d = new BigInteger(1, c30.b("1000000000000000000000000000000014DEF9DEA2F79CD65812631A5CF5D3ED"));
        this.e = BigInteger.valueOf(8L);
        this.f = 4;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new ak();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 16];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            fa0.u(i3, ((bk) orVar.b).U, iArr);
            fa0.u(i3 + 8, ((bk) orVar.c).U, iArr);
            i3 += 16;
        }
        return new zj(this, i2, iArr, 0);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 0);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new bk(bigInteger);
    }

    @Override // defpackage.xq
    public final int j() {
        return h.bitLength();
    }

    @Override // defpackage.xq
    public final or k() {
        return this.g;
    }

    @Override // defpackage.tq, defpackage.xq
    public final l21 o(SecureRandom secureRandom) {
        int[] iArr = new int[8];
        do {
            byte[] bArr = new byte[32];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(8, bArr, iArr);
                iArr[7] = iArr[7] & Integer.MAX_VALUE;
            } while (fa0.l0(8, iArr, qm.c) == 0);
        } while (qm.P(iArr) != 0);
        return new bk(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 4;
    }
}
