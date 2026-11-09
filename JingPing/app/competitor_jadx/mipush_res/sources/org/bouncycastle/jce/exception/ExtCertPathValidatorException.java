package org.bouncycastle.jce.exception;

import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;

/* JADX INFO: loaded from: classes.dex */
public class ExtCertPathValidatorException extends CertPathValidatorException {
    public final Throwable E;

    public ExtCertPathValidatorException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public ExtCertPathValidatorException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    public ExtCertPathValidatorException(String str, Throwable th, CertPath certPath, int i) {
        super(str, th, certPath, i);
        this.E = th;
    }
}
