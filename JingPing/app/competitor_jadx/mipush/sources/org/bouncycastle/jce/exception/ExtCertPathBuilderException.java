package org.bouncycastle.jce.exception;

import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;

/* JADX INFO: loaded from: classes.dex */
public class ExtCertPathBuilderException extends CertPathBuilderException {
    public final Throwable E;

    public ExtCertPathBuilderException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public ExtCertPathBuilderException(String str, Throwable th, CertPath certPath, int i) {
        super(str, th);
        this.E = th;
    }
}
