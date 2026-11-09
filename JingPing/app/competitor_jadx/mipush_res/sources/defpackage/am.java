package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class am extends xl {
    public static final BigInteger F = BigInteger.valueOf(1);
    public static final BigInteger G = BigInteger.valueOf(2);

    public am(BigInteger bigInteger, zl zlVar) {
        super(false);
        if (zlVar != null) {
            BigInteger bigInteger2 = G;
            if (bigInteger2.compareTo(bigInteger) <= 0) {
                BigInteger bigInteger3 = zlVar.G;
                if (bigInteger3.subtract(bigInteger2).compareTo(bigInteger) >= 0) {
                    if (F.equals(bigInteger.modPow(zlVar.F, bigInteger3))) {
                        return;
                    }
                }
            }
            throw new IllegalArgumentException("y value does not appear to be in correct group");
        }
    }
}
