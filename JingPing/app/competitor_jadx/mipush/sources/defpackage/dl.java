package defpackage;

import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class dl extends DHParameterSpec {
    public final BigInteger a;
    public final BigInteger b;
    public final int c;
    public il d;

    public dl(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        super(bigInteger, bigInteger3, i2);
        this.a = bigInteger2;
        this.b = bigInteger4;
        this.c = i;
    }
}
