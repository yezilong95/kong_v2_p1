package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class zl implements le {
    public BigInteger E;
    public BigInteger F;
    public BigInteger G;
    public final cm H;

    public zl(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, cm cmVar) {
        this.E = bigInteger3;
        this.G = bigInteger;
        this.F = bigInteger2;
        this.H = cmVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zl)) {
            return false;
        }
        zl zlVar = (zl) obj;
        if (zlVar.G.equals(this.G)) {
            if (zlVar.F.equals(this.F)) {
                if (zlVar.E.equals(this.E)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.G.hashCode() ^ this.F.hashCode()) ^ this.E.hashCode();
    }
}
