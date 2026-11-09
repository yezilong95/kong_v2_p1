package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public abstract class tq extends xq {
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public tq(java.math.BigInteger r5) {
        /*
            r4 = this;
            il0 r0 = defpackage.fw.a
            int r0 = r5.bitLength()
            int r1 = r5.signum()
            if (r1 <= 0) goto L3d
            r1 = 2
            if (r0 < r1) goto L3d
            r2 = 3
            if (r0 >= r2) goto L33
            java.math.BigInteger r0 = defpackage.oa.a
            int r0 = r5.bitLength()
            r3 = 31
            if (r0 > r3) goto L2b
            int r0 = r5.intValue()
            if (r0 == r1) goto L28
            if (r0 == r2) goto L25
            goto L33
        L25:
            il0 r5 = defpackage.fw.b
            goto L39
        L28:
            il0 r5 = defpackage.fw.a
            goto L39
        L2b:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r0 = "BigInteger out of int range"
            r5.<init>(r0)
            throw r5
        L33:
            il0 r0 = new il0
            r0.<init>(r5)
            r5 = r0
        L39:
            r4.<init>(r5)
            return
        L3d:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "'characteristic' must be >= 2"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tq.<init>(java.math.BigInteger):void");
    }

    @Override // defpackage.xq
    public final or f(int i, BigInteger bigInteger) {
        l21 l21VarH = h(bigInteger);
        l21 l21VarT0 = l21VarH.U0().e(this.b).k0(l21VarH).e(this.c).T0();
        if (l21VarT0 == null) {
            throw new IllegalArgumentException("Invalid point compression");
        }
        if (l21VarT0.g1() != (i == 1)) {
            l21VarT0 = l21VarT0.q0();
        }
        return d(l21VarH, l21VarT0);
    }

    @Override // defpackage.xq
    public l21 o(SecureRandom secureRandom) {
        BigInteger bigIntegerB;
        BigInteger bigIntegerB2 = this.a.b();
        while (true) {
            bigIntegerB = oa.b(bigIntegerB2.bitLength(), secureRandom);
            if (bigIntegerB.signum() > 0 && bigIntegerB.compareTo(bigIntegerB2) < 0) {
                break;
            }
        }
        l21 l21VarH = h(bigIntegerB);
        while (true) {
            BigInteger bigIntegerB3 = oa.b(bigIntegerB2.bitLength(), secureRandom);
            if (bigIntegerB3.signum() > 0 && bigIntegerB3.compareTo(bigIntegerB2) < 0) {
                return l21VarH.k0(h(bigIntegerB3));
            }
        }
    }
}
