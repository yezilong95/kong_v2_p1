package defpackage;

import java.math.BigInteger;
import java.util.Hashtable;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class or {
    public static final l21[] f = new l21[0];
    public final xq a;
    public final l21 b;
    public final l21 c;
    public final l21[] d;
    public Hashtable e = null;

    public or(xq xqVar, l21 l21Var, l21 l21Var2, l21[] l21VarArr) {
        this.a = xqVar;
        this.b = l21Var;
        this.c = l21Var2;
        this.d = l21VarArr;
    }

    public abstract or a(or orVar);

    public final void b() {
        if (!m()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public abstract or c();

    /* JADX WARN: Removed duplicated region for block: B:38:0x0072 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean d(defpackage.or r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L5
            goto L73
        L5:
            xq r1 = r9.a
            xq r2 = r8.a
            r3 = 1
            if (r2 != 0) goto Le
            r4 = r3
            goto Lf
        Le:
            r4 = r0
        Lf:
            if (r1 != 0) goto L13
            r5 = r3
            goto L14
        L13:
            r5 = r0
        L14:
            boolean r6 = r8.l()
            boolean r7 = r9.l()
            if (r6 != 0) goto L64
            if (r7 == 0) goto L21
            goto L64
        L21:
            if (r4 == 0) goto L27
            if (r5 == 0) goto L27
        L25:
            r1 = r8
            goto L4b
        L27:
            if (r4 == 0) goto L2e
            or r9 = r9.o()
            goto L25
        L2e:
            if (r5 == 0) goto L35
            or r1 = r8.o()
            goto L4b
        L35:
            boolean r1 = r2.g(r1)
            if (r1 != 0) goto L3c
            goto L73
        L3c:
            or r9 = r2.l(r9)
            or[] r9 = new defpackage.or[]{r8, r9}
            r2.m(r9)
            r1 = r9[r0]
            r9 = r9[r3]
        L4b:
            l21 r2 = r1.b
            l21 r4 = r9.b
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L73
            l21 r1 = r1.i()
            l21 r9 = r9.i()
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L73
            goto L72
        L64:
            if (r6 == 0) goto L73
            if (r7 == 0) goto L73
            if (r4 != 0) goto L72
            if (r5 != 0) goto L72
            boolean r9 = r2.g(r1)
            if (r9 == 0) goto L73
        L72:
            return r3
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.or.d(or):boolean");
    }

    public final l21 e() {
        b();
        return i();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof or) {
            return d((or) obj);
        }
        return false;
    }

    public abstract boolean f();

    public final int g() {
        xq xqVar = this.a;
        if (xqVar == null) {
            return 0;
        }
        return xqVar.f;
    }

    public final byte[] h(boolean z) {
        if (l()) {
            return new byte[1];
        }
        or orVarO = o();
        l21 l21Var = orVarO.b;
        byte[] bArrA = oa.a(l21Var.J(), l21Var.h1());
        if (z) {
            byte[] bArr = new byte[bArrA.length + 1];
            bArr[0] = (byte) (orVarO.f() ? 3 : 2);
            System.arraycopy(bArrA, 0, bArr, 1, bArrA.length);
            return bArr;
        }
        l21 l21VarI = orVarO.i();
        byte[] bArrA2 = oa.a(l21VarI.J(), l21VarI.h1());
        byte[] bArr2 = new byte[bArrA.length + bArrA2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(bArrA, 0, bArr2, 1, bArrA.length);
        System.arraycopy(bArrA2, 0, bArr2, bArrA.length + 1, bArrA2.length);
        return bArr2;
    }

    public final int hashCode() {
        xq xqVar = this.a;
        int i = xqVar == null ? 0 : ~xqVar.hashCode();
        if (l()) {
            return i;
        }
        or orVarO = o();
        return (i ^ (orVarO.b.hashCode() * 17)) ^ (orVarO.i().hashCode() * 257);
    }

    public l21 i() {
        return this.c;
    }

    public l21 j() {
        l21[] l21VarArr = this.d;
        if (l21VarArr.length <= 0) {
            return null;
        }
        return l21VarArr[0];
    }

    public final boolean k(boolean z, boolean z2) {
        if (l()) {
            return true;
        }
        return !((q31) this.a.n(this, "bc_validity", new lr(this, z, z2))).a;
    }

    public final boolean l() {
        if (this.b == null || this.c == null) {
            return true;
        }
        l21[] l21VarArr = this.d;
        return l21VarArr.length > 0 && l21VarArr[0].d0();
    }

    public final boolean m() {
        int iG = g();
        return iG == 0 || iG == 5 || l() || this.d[0].b0();
    }

    public abstract or n();

    public final or o() {
        int iG;
        if (!l() && (iG = g()) != 0 && iG != 5) {
            l21 l21VarJ = j();
            if (!l21VarJ.b0()) {
                xq xqVar = this.a;
                if (xqVar == null) {
                    throw new IllegalStateException("Detached points must be in affine coordinates");
                }
                l21 l21VarO = xqVar.o(a.b());
                return p(l21VarJ.k0(l21VarO).Y().k0(l21VarO));
            }
        }
        return this;
    }

    public final or p(l21 l21Var) {
        l21 l21VarK0;
        int iG = g();
        if (iG == 1) {
            l21VarK0 = this.b.k0(l21Var);
        } else if (iG == 2 || iG == 3 || iG == 4) {
            l21 l21VarU0 = l21Var.U0();
            l21Var = l21VarU0.k0(l21Var);
            l21VarK0 = this.b.k0(l21VarU0);
        } else {
            if (iG != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
            l21VarK0 = this.b.k0(l21Var);
        }
        return this.a.d(l21VarK0, this.c.k0(l21Var));
    }

    public abstract boolean q();

    public boolean r() {
        BigInteger bigInteger;
        BigInteger bigInteger2 = qq.b;
        xq xqVar = this.a;
        if (!bigInteger2.equals(xqVar.e) && (bigInteger = xqVar.d) != null) {
            BigInteger bigIntegerAbs = bigInteger.abs();
            or orVarK = xqVar.k();
            int iBitLength = bigIntegerAbs.bitLength();
            if (iBitLength > 0) {
                if (bigIntegerAbs.testBit(0)) {
                    orVarK = this;
                }
                or orVarU = this;
                for (int i = 1; i < iBitLength; i++) {
                    orVarU = orVarU.u();
                    if (bigIntegerAbs.testBit(i)) {
                        orVarK = orVarK.a(orVarU);
                    }
                }
            }
            if (bigInteger.signum() < 0) {
                orVarK = orVarK.n();
            }
            if (!orVarK.l()) {
                return false;
            }
        }
        return true;
    }

    public abstract or s(or orVar);

    public or t(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        or orVarU = this;
        while (true) {
            i--;
            if (i < 0) {
                return orVarU;
            }
            orVarU = orVarU.u();
        }
    }

    public final String toString() {
        if (l()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer("(");
        stringBuffer.append(this.b);
        stringBuffer.append(',');
        stringBuffer.append(this.c);
        int i = 0;
        while (true) {
            l21[] l21VarArr = this.d;
            if (i >= l21VarArr.length) {
                stringBuffer.append(')');
                return stringBuffer.toString();
            }
            stringBuffer.append(',');
            stringBuffer.append(l21VarArr[i]);
            i++;
        }
    }

    public abstract or u();

    public abstract or v(or orVar);
}
