package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class zs0 extends tq {
    public static final BigInteger h = at0.V;
    public static final l21[] i = {new at0(qq.b)};
    public final ck g;

    public zs0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 11);
        this.b = new at0(qq.a);
        this.c = new at0(BigInteger.valueOf(7L));
        this.d = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new zs0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 16];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            fa0.u(i3, ((at0) orVar.b).U, iArr);
            fa0.u(i3 + 8, ((at0) orVar.c).U, iArr);
            i3 += 16;
        }
        return new zj(this, i2, iArr, 10);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 11);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new at0(bigInteger);
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
            } while (fa0.l0(8, iArr, t40.o) == 0);
        } while (t40.y0(iArr) != 0);
        return new at0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
