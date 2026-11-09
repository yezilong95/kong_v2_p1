package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class ft0 extends tq {
    public static final BigInteger h = gt0.V;
    public static final l21[] i = {new gt0(qq.b)};
    public final ck g;

    public ft0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 14);
        this.b = new gt0(new BigInteger(1, c30.b("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.c = new gt0(new BigInteger(1, c30.b("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00")));
        this.d = new BigInteger(1, c30.b("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new ft0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 34];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            System.arraycopy(((gt0) orVar.b).U, 0, iArr, i3, 17);
            System.arraycopy(((gt0) orVar.c).U, 0, iArr, i3 + 17, 17);
            i3 += 34;
        }
        return new zj(this, i2, iArr, 13);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 14);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new gt0(bigInteger);
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
        int[] iArr = new int[17];
        do {
            byte[] bArr = new byte[68];
            do {
                secureRandom.nextBytes(bArr);
                ma0.Q(17, bArr, iArr);
                iArr[16] = iArr[16] & 511;
            } while (fa0.l0(17, iArr, z91.W) == 0);
        } while (z91.M(iArr) != 0);
        return new gt0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
