package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class sq extends xq {
    /* JADX WARN: Illegal instructions before constructor call */
    public sq(int i, int i2, int i3, int i4) {
        if (i > cm0.a(1142, "org.bouncycastle.ec.max_f2m_field_size")) {
            throw new IllegalArgumentException(zn.j(i, "field size out of range: "));
        }
        int[] iArr = (i3 | i4) == 0 ? new int[]{0, i2, i} : new int[]{0, i2, i3, i4, i};
        il0 il0Var = fw.a;
        if (iArr[0] != 0) {
            throw new IllegalArgumentException("Irreducible polynomials in GF(2) must have constant term");
        }
        for (int i5 = 1; i5 < iArr.length; i5++) {
            if (iArr[i5] <= iArr[i5 - 1]) {
                throw new IllegalArgumentException("Polynomial exponents must be monotonically increasing");
            }
        }
        super(new d20(fw.a, new l10(iArr)));
        if (cm0.c("org.bouncycastle.ec.disable")) {
            throw new UnsupportedOperationException("F2M disabled by \"org.bouncycastle.ec.disable\"");
        }
        if (cm0.c("org.bouncycastle.ec.disable_f2m")) {
            throw new UnsupportedOperationException("F2M disabled by \"org.bouncycastle.ec.disable_f2m\"");
        }
    }

    @Override // defpackage.xq
    public final or c(BigInteger bigInteger, BigInteger bigInteger2) {
        l21 l21VarH = h(bigInteger);
        l21 l21VarH2 = h(bigInteger2);
        int i = this.f;
        if (i == 5 || i == 6) {
            if (!l21VarH.d0()) {
                l21VarH2 = l21VarH2.A(l21VarH).e(l21VarH);
            } else if (!l21VarH2.U0().equals(this.c)) {
                throw new IllegalArgumentException();
            }
        }
        return d(l21VarH, l21VarH2);
    }

    @Override // defpackage.xq
    public final or f(int i, BigInteger bigInteger) {
        l21 l21VarE;
        l21 l21VarH = h(bigInteger);
        if (l21VarH.d0()) {
            l21VarE = this.c.T0();
        } else {
            l21 l21VarR = r(l21VarH.U0().Y().k0(this.c).e(this.b).e(l21VarH));
            if (l21VarR != null) {
                if (l21VarR.g1() != (i == 1)) {
                    l21VarR = l21VarR.h();
                }
                int i2 = this.f;
                l21VarE = (i2 == 5 || i2 == 6) ? l21VarR.e(l21VarH) : l21VarR.k0(l21VarH);
            } else {
                l21VarE = null;
            }
        }
        if (l21VarE != null) {
            return d(l21VarH, l21VarE);
        }
        throw new IllegalArgumentException("Invalid point compression");
    }

    @Override // defpackage.xq
    public final l21 o(SecureRandom secureRandom) {
        BigInteger bigIntegerB;
        BigInteger bigIntegerB2;
        int iJ = j();
        do {
            bigIntegerB = oa.b(iJ, secureRandom);
        } while (bigIntegerB.signum() <= 0);
        l21 l21VarH = h(bigIntegerB);
        do {
            bigIntegerB2 = oa.b(iJ, secureRandom);
        } while (bigIntegerB2.signum() <= 0);
        return l21VarH.k0(h(bigIntegerB2));
    }

    public final l21 r(l21 l21Var) {
        l21 l21VarE;
        zq zqVar = (zq) l21Var;
        boolean zJ1 = zqVar.j1();
        if (zJ1 && zqVar.k1() != 0) {
            return null;
        }
        int iJ = j();
        if ((iJ & 1) != 0) {
            l21 l21VarI1 = zqVar.i1();
            if (zJ1 || l21VarI1.U0().e(l21VarI1).e(l21Var).d0()) {
                return l21VarI1;
            }
            return null;
        }
        if (l21Var.d0()) {
            return l21Var;
        }
        l21 l21VarH = h(qq.a);
        Random random = new Random();
        do {
            l21 l21VarH2 = h(new BigInteger(iJ, random));
            l21 l21VarE2 = l21Var;
            l21VarE = l21VarH;
            for (int i = 1; i < iJ; i++) {
                l21 l21VarU0 = l21VarE2.U0();
                l21VarE = l21VarE.U0().e(l21VarU0.k0(l21VarH2));
                l21VarE2 = l21VarU0.e(l21Var);
            }
            if (!l21VarE2.d0()) {
                return null;
            }
        } while (l21VarE.U0().e(l21VarE).d0());
        return l21VarE;
    }
}
