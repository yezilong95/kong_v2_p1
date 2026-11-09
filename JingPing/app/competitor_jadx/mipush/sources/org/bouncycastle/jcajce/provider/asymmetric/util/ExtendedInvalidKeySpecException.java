package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.security.spec.InvalidKeySpecException;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedInvalidKeySpecException extends InvalidKeySpecException {
    public final Throwable E;

    public ExtendedInvalidKeySpecException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
