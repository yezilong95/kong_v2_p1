package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class ks0 extends tq {
    public static final BigInteger h = ls0.V;
    public static final l21[] i = {new ls0(qq.b)};
    public final ck g;

    public ks0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 3);
        this.b = new ls0(new BigInteger(1, c30.b("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.c = new ls0(new BigInteger(1, c30.b("E87579C11079F43DD824993C2CEE5ED3")));
        this.d = new BigInteger(1, c30.b("FFFFFFFE0000000075A30D1B9038A115"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new ks0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 8];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            la0.k(i3, ((ls0) orVar.b).U, iArr);
            la0.k(i3 + 4, ((ls0) orVar.c).U, iArr);
            i3 += 8;
        }
        return new zj(this, i2, iArr, 2);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 3);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new ls0(bigInteger);
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
        int[] iArr = new int[4];
        do {
            byte[] bArr = new byte[16];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(4, bArr, iArr);
            } while (fa0.l0(4, iArr, z91.Q) == 0);
        } while (z91.K(iArr) != 0);
        return new ls0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
