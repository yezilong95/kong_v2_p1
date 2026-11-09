package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class xq {
    public final ew a;
    public l21 b;
    public l21 c;
    public BigInteger d;
    public BigInteger e;
    public int f = 0;

    public xq(ew ewVar) {
        this.a = ewVar;
    }

    public abstract xq a();

    public l21 b(or[] orVarArr, int i) {
        int i2 = i();
        byte[] bArr = new byte[i * i2 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            or orVar = orVarArr[i4];
            orVar.b.E(i3, bArr);
            int i5 = i3 + i2;
            orVar.c.E(i5, bArr);
            i3 = i5 + i2;
        }
        return new rq(this, i, i2, bArr);
    }

    public or c(BigInteger bigInteger, BigInteger bigInteger2) {
        return d(h(bigInteger), h(bigInteger2));
    }

    public abstract or d(l21 l21Var, l21 l21Var2);

    public final or e(byte[] bArr) {
        or orVarK;
        int i = i();
        byte b = bArr[0];
        if (b != 0) {
            if (b == 2 || b == 3) {
                if (bArr.length != i + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                orVarK = f(b & 1, oa.d(bArr, 1, i));
                if (!orVarK.k(true, true)) {
                    throw new IllegalArgumentException("Invalid point");
                }
            } else if (b != 4) {
                if (b != 6 && b != 7) {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b, 16));
                }
                if (bArr.length != (i * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                }
                BigInteger bigIntegerD = oa.d(bArr, 1, i);
                BigInteger bigIntegerD2 = oa.d(bArr, i + 1, i);
                if (bigIntegerD2.testBit(0) != (b == 7)) {
                    throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                }
                orVarK = q(bigIntegerD, bigIntegerD2);
            } else {
                if (bArr.length != (i * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
                }
                orVarK = q(oa.d(bArr, 1, i), oa.d(bArr, i + 1, i));
            }
        } else {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            }
            orVarK = k();
        }
        if (b == 0 || !orVarK.l()) {
            return orVarK;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof xq) && g((xq) obj);
        }
        return true;
    }

    public abstract or f(int i, BigInteger bigInteger);

    public final boolean g(xq xqVar) {
        if (this == xqVar) {
            return true;
        }
        if (xqVar != null) {
            return this.a.equals(xqVar.a) && this.b.h1().equals(xqVar.b.h1()) && this.c.h1().equals(xqVar.c.h1());
        }
        return false;
    }

    public abstract l21 h(BigInteger bigInteger);

    public final int hashCode() {
        return (this.a.hashCode() ^ Integer.rotateLeft(this.b.h1().hashCode(), 8)) ^ Integer.rotateLeft(this.c.h1().hashCode(), 16);
    }

    public final int i() {
        return (j() + 7) / 8;
    }

    public abstract int j();

    public abstract or k();

    public or l(or orVar) {
        if (this == orVar.a) {
            return orVar;
        }
        if (orVar.l()) {
            return k();
        }
        or orVarO = orVar.o();
        return c(orVarO.b.h1(), orVarO.i().h1());
    }

    public final void m(or[] orVarArr) {
        int length = orVarArr.length;
        if (length < 0 || orVarArr.length - length < 0) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (or orVar : orVarArr) {
            if (orVar != null && this != orVar.a) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
        int i = this.f;
        if (i == 0 || i == 5) {
            return;
        }
        l21[] l21VarArr = new l21[length];
        int[] iArr = new int[length];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            or orVar2 = orVarArr[i3];
            if (orVar2 != null && !orVar2.m()) {
                l21VarArr[i2] = orVar2.j();
                iArr[i2] = i3;
                i2++;
            }
        }
        if (i2 == 0) {
            return;
        }
        l21[] l21VarArr2 = new l21[i2];
        l21VarArr2[0] = l21VarArr[0];
        int i4 = 0;
        while (true) {
            int i5 = i4 + 1;
            if (i5 >= i2) {
                break;
            }
            l21VarArr2[i5] = l21VarArr2[i4].k0(l21VarArr[i5]);
            i4 = i5;
        }
        l21 l21VarY = l21VarArr2[i4].Y();
        while (i4 > 0) {
            int i6 = i4 - 1;
            l21 l21Var = l21VarArr[i4];
            l21VarArr[i4] = l21VarArr2[i6].k0(l21VarY);
            l21VarY = l21VarY.k0(l21Var);
            i4 = i6;
        }
        l21VarArr[0] = l21VarY;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = iArr[i7];
            orVarArr[i8] = orVarArr[i8].p(l21VarArr[i7]);
        }
    }

    public final el0 n(or orVar, String str, dl0 dl0Var) {
        Hashtable hashtable;
        el0 el0VarA;
        if (orVar == null || this != orVar.a) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
        synchronized (orVar) {
            try {
                hashtable = orVar.e;
                if (hashtable == null) {
                    hashtable = new Hashtable(4);
                    orVar.e = hashtable;
                }
            } finally {
            }
        }
        synchronized (hashtable) {
            try {
                el0 el0Var = (el0) hashtable.get(str);
                el0VarA = dl0Var.a(el0Var);
                if (el0VarA != el0Var) {
                    hashtable.put(str, el0VarA);
                }
            } finally {
            }
        }
        return el0VarA;
    }

    public abstract l21 o(SecureRandom secureRandom);

    public abstract boolean p(int i);

    public final or q(BigInteger bigInteger, BigInteger bigInteger2) {
        or orVarC = c(bigInteger, bigInteger2);
        if (orVarC.k(false, true)) {
            return orVarC;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }
}
