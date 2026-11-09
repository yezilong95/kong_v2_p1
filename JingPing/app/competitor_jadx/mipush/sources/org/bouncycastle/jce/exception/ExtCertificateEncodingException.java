package org.bouncycastle.jce.exception;

import java.security.cert.CertificateEncodingException;

/* JADX INFO: loaded from: classes.dex */
public class ExtCertificateEncodingException extends CertificateEncodingException {
    public final Throwable E;

    public ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
