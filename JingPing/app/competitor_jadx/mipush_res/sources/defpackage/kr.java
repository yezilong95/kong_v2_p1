package defpackage;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class kr implements AlgorithmParameterSpec {
    public final xq a;
    public final byte[] b;
    public final or c;
    public final BigInteger d;
    public final BigInteger e;

    public kr(vq vqVar, or orVar, BigInteger bigInteger) {
        this.a = vqVar;
        this.c = orVar.o();
        this.d = bigInteger;
        this.e = BigInteger.valueOf(1L);
        this.b = null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof kr)) {
            return false;
        }
        kr krVar = (kr) obj;
        return this.a.g(krVar.a) && this.c.d(krVar.c);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.c.hashCode();
    }

    public kr(xq xqVar, or orVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.a = xqVar;
        this.c = orVar.o();
        this.d = bigInteger;
        this.e = bigInteger2;
        this.b = bArr;
    }
}
