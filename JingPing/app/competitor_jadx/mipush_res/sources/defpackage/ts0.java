package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class ts0 extends tq {
    public static final BigInteger h = us0.V;
    public static final l21[] i = {new us0(qq.b)};
    public final ck g;

    public ts0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 8);
        this.b = new us0(new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFC")));
        this.c = new us0(new BigInteger(1, c30.b("64210519E59C80E70FA7E9AB72243049FEB8DEECC146B9B1")));
        this.d = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFF99DEF836146BC9B1B4D22831"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new ts0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 12];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            o80.k(i3, ((us0) orVar.b).U, iArr);
            o80.k(i3 + 6, ((us0) orVar.c).U, iArr);
            i3 += 12;
        }
        return new zj(this, i2, iArr, 7);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 8);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new us0(bigInteger);
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
        int[] iArr = new int[6];
        do {
            byte[] bArr = new byte[24];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(6, bArr, iArr);
            } while (fa0.l0(6, iArr, j31.D) == 0);
        } while (j31.A(iArr) != 0);
        return new us0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
