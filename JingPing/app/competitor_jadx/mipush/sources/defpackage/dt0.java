package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class dt0 extends tq {
    public static final BigInteger h = et0.V;
    public static final l21[] i = {new et0(qq.b)};
    public final ck g;

    public dt0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 13);
        this.b = new et0(new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFC")));
        this.c = new et0(new BigInteger(1, c30.b("B3312FA7E23EE7E4988E056BE3F82D19181D9C6EFE8141120314088F5013875AC656398D8A2ED19D2A85C8EDD3EC2AEF")));
        this.d = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC7634D81F4372DDF581A0DB248B0A77AECEC196ACCC52973"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new dt0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 24];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            System.arraycopy(((et0) orVar.b).U, 0, iArr, i3, 12);
            System.arraycopy(((et0) orVar.c).U, 0, iArr, i3 + 12, 12);
            i3 += 24;
        }
        return new zj(this, i2, iArr, 12);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 13);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new et0(bigInteger);
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
        int[] iArr = new int[12];
        do {
            byte[] bArr = new byte[48];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(12, bArr, iArr);
            } while (fa0.l0(12, iArr, j31.G) == 0);
        } while (j31.B(iArr) != 0);
        return new et0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
