package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class xs0 extends tq {
    public static final BigInteger h = ys0.V;
    public static final l21[] i = {new ys0(qq.b)};
    public final ck g;

    public xs0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 10);
        this.b = new ys0(new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE")));
        this.c = new ys0(new BigInteger(1, c30.b("B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4")));
        this.d = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new xs0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 14];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            s90.o(i3, ((ys0) orVar.b).U, iArr);
            s90.o(i3 + 7, ((ys0) orVar.c).U, iArr);
            i3 += 14;
        }
        return new zj(this, i2, iArr, 9);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 10);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new ys0(bigInteger);
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
        int[] iArr = new int[7];
        do {
            byte[] bArr = new byte[28];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(7, bArr, iArr);
            } while (fa0.l0(7, iArr, qm.n) == 0);
        } while (qm.S(iArr) != 0);
        return new ys0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
