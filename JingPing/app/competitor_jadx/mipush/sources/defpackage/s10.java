package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class s10 implements le {
    public final BigInteger E;
    public final BigInteger F;
    public final BigInteger G;

    public s10(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.E = bigInteger;
        this.F = bigInteger2;
        this.G = bigInteger3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof s10)) {
            return false;
        }
        s10 s10Var = (s10) obj;
        if (s10Var.E.equals(this.E)) {
            if (s10Var.F.equals(this.F)) {
                if (s10Var.G.equals(this.G)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.E.hashCode() ^ this.F.hashCode()) ^ this.G.hashCode();
    }
}
