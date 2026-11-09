package org.bouncycastle.jce.provider;

/* JADX INFO: loaded from: classes.dex */
public class PKIXNameConstraintValidatorException extends Exception {
    public final Throwable E;

    public PKIXNameConstraintValidatorException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public PKIXNameConstraintValidatorException(String str, Throwable th) {
        super(str);
        this.E = th;
    }
}
