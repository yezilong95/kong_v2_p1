package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class v10 {
    public final BigInteger a;
    public final BigInteger b;
    public final BigInteger c;

    public v10(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.a = bigInteger;
        this.b = bigInteger2;
        this.c = bigInteger3;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof v10) {
            v10 v10Var = (v10) obj;
            if (this.c.equals(v10Var.c) && this.a.equals(v10Var.a) && this.b.equals(v10Var.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.c.hashCode() ^ this.a.hashCode()) ^ this.b.hashCode();
    }
}
