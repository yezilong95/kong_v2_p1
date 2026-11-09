package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class mq0 extends tq {
    public static final BigInteger h = nq0.V;
    public static final l21[] i = {new nq0(qq.b)};
    public final ck g;

    public mq0() {
        super(h);
        l21 l21Var = null;
        this.g = new ck(this, l21Var, l21Var, 2);
        this.b = new nq0(new BigInteger(1, c30.b("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC")));
        this.c = new nq0(new BigInteger(1, c30.b("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93")));
        this.d = new BigInteger(1, c30.b("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123"));
        this.e = BigInteger.valueOf(1L);
        this.f = 2;
    }

    @Override // defpackage.xq
    public final xq a() {
        return new mq0();
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i2) {
        int[] iArr = new int[i2 * 16];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            or orVar = orVarArr[i4];
            fa0.u(i3, ((nq0) orVar.b).U, iArr);
            fa0.u(i3 + 8, ((nq0) orVar.c).U, iArr);
            i3 += 16;
        }
        return new zj(this, i2, iArr, 1);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new ck(this, l21Var, l21Var2, 2);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        return new nq0(bigInteger);
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
            } while (fa0.l0(8, iArr, qm.i) == 0);
        } while (qm.Q(iArr) != 0);
        return new nq0(iArr);
    }

    @Override // defpackage.xq
    public final boolean p(int i2) {
        return i2 == 2;
    }
}
