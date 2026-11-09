package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class yq implements qq {
    public final xq f;
    public final byte[] g;
    public final or h;
    public final BigInteger i;
    public final BigInteger j;

    public yq(xq xqVar, or orVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        if (xqVar == null) {
            throw new NullPointerException("curve");
        }
        if (bigInteger == null) {
            throw new NullPointerException("n");
        }
        this.f = xqVar;
        this.h = a(xqVar, orVar);
        this.i = bigInteger;
        this.j = bigInteger2;
        this.g = z91.l(bArr);
    }

    public static or a(xq xqVar, or orVar) {
        if (orVar == null) {
            throw new NullPointerException("Point cannot be null");
        }
        if (!xqVar.g(orVar.a)) {
            throw new IllegalArgumentException("Point must be on the same curve");
        }
        or orVarO = xqVar.l(orVar).o();
        if (orVarO.l()) {
            throw new IllegalArgumentException("Point at infinity");
        }
        if (orVarO.k(false, true)) {
            return orVarO;
        }
        throw new IllegalArgumentException("Point not on curve");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof yq)) {
            return false;
        }
        yq yqVar = (yq) obj;
        return this.f.g(yqVar.f) && this.h.d(yqVar.h) && this.i.equals(yqVar.i);
    }

    public final int hashCode() {
        return ((((this.f.hashCode() ^ 1028) * 257) ^ this.h.hashCode()) * 257) ^ this.i.hashCode();
    }
}
