package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class vs0 extends tq {
    public static final BigInteger h = ws0.V;
    public static final l21[] i = {new ws0(qq.b)};
    public final ck g;

    public vs0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 9);
        this.b = new ws0(qq.a);
        this.c = new ws0(BigInteger.valueOf(5L));
        this.d = new BigInteger(1, c30.b("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new vs0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 14];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            s90.o(i3, ((ws0) orVar.b).U, iArr);
            s90.o(i3 + 7, ((ws0) orVar.c).U, iArr);
            i3 += 14;
        }
        return new zj(this, i2, iArr, 8);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 9);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new ws0(bigInteger);
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
            } while (fa0.l0(7, iArr, z91.T) == 0);
        } while (z91.L(iArr) != 0);
        return new ws0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
