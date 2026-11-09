package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class gl implements le {
    public final BigInteger E;
    public final BigInteger F;
    public final BigInteger G;
    public final BigInteger H;
    public final int I;
    public final int J;
    public final il K;

    public gl(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this(bigInteger, bigInteger2, null, (i != 0 && i < 160) ? i : 160, i, null, null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof gl)) {
            return false;
        }
        gl glVar = (gl) obj;
        BigInteger bigInteger = this.G;
        if (bigInteger != null) {
            if (!bigInteger.equals(glVar.G)) {
                return false;
            }
        } else if (glVar.G != null) {
            return false;
        }
        if (glVar.F.equals(this.F)) {
            if (glVar.E.equals(this.E)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.F.hashCode() ^ this.E.hashCode();
        BigInteger bigInteger = this.G;
        return iHashCode ^ (bigInteger != null ? bigInteger.hashCode() : 0);
    }

    public gl(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, il ilVar) {
        if (i2 != 0) {
            if (i2 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (i > bigInteger.bitLength() && !cm0.c("org.bouncycastle.dh.allow_unsafe_p_value")) {
            throw new IllegalArgumentException("unsafe p value so small specific l required");
        }
        this.E = bigInteger2;
        this.F = bigInteger;
        this.G = bigInteger3;
        this.I = i;
        this.J = i2;
        this.H = bigInteger4;
        this.K = ilVar;
    }
}
