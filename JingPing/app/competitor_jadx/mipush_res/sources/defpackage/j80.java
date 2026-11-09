package defpackage;

import java.io.IOException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public final class j80 implements Enumeration {
    public x a;
    public g0 b;

    public final g0 a() {
        try {
            return this.a.z();
        } catch (IOException e) {
            throw new ASN1ParsingException("malformed ASN.1: " + e, e);
        }
    }

    @Override // java.util.Enumeration
    public final boolean hasMoreElements() {
        return this.b != null;
    }

    @Override // java.util.Enumeration
    public final Object nextElement() {
        g0 g0Var = this.b;
        if (g0Var == null) {
            throw new NoSuchElementException();
        }
        this.b = a();
        return g0Var;
    }
}
