package org.bouncycastle.asn1;

/* JADX INFO: loaded from: classes.dex */
public class ASN1ParsingException extends IllegalStateException {
    public final Throwable E;

    public ASN1ParsingException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public ASN1ParsingException(String str, Throwable th) {
        super(str);
        this.E = th;
    }
}
