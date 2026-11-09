package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class qr extends er {
    public final BigInteger G;

    public qr(BigInteger bigInteger, yq yqVar) {
        super(yqVar, true);
        if (bigInteger == null) {
            throw new NullPointerException("Scalar cannot be null");
        }
        if (bigInteger.compareTo(qq.b) < 0 || bigInteger.compareTo(yqVar.i) >= 0) {
            throw new IllegalArgumentException("Scalar is not in the interval [1, n - 1]");
        }
        this.G = bigInteger;
    }
}
