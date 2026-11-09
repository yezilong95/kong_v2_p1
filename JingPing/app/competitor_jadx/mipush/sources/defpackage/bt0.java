package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class bt0 extends tq {
    public static final BigInteger h = ct0.V;
    public static final l21[] i = {new ct0(qq.b)};
    public final ck g;

    public bt0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 12);
        this.b = new ct0(new BigInteger(1, c30.b("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC")));
        this.c = new ct0(new BigInteger(1, c30.b("5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B")));
        this.d = new BigInteger(1, c30.b("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new bt0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 16];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            fa0.u(i3, ((ct0) orVar.b).U, iArr);
            fa0.u(i3 + 8, ((ct0) orVar.c).U, iArr);
            i3 += 16;
        }
        return new zj(this, i2, iArr, 11);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 12);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new ct0(bigInteger);
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
            } while (fa0.l0(8, iArr, l21.P) == 0);
        } while (l21.e0(iArr) != 0);
        return new ct0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
