package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class ps0 extends tq {
    public static final BigInteger h = qs0.V;
    public static final l21[] i = {new qs0(qq.b)};
    public final ck g;

    public ps0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 6);
        this.b = new qs0(new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC70")));
        this.c = new qs0(new BigInteger(1, c30.b("B4E134D3FB59EB8BAB57274904664D5AF50388BA")));
        this.d = new BigInteger(1, c30.b("0100000000000000000000351EE786A818F3A1A16B"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new ps0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 10];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            ma0.m(i3, ((qs0) orVar.b).U, iArr);
            ma0.m(i3 + 5, ((qs0) orVar.c).U, iArr);
            i3 += 10;
        }
        return new zj(this, i2, iArr, 5);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 6);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new qs0(bigInteger);
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
        int[] iArr = new int[5];
        do {
            byte[] bArr = new byte[20];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(5, bArr, iArr);
            } while (fa0.l0(5, iArr, t40.l) == 0);
        } while (t40.x0(iArr) != 0);
        return new qs0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
