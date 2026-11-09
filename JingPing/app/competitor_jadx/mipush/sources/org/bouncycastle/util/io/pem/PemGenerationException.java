package org.bouncycastle.util.io.pem;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class PemGenerationException extends IOException {
    public final Throwable E;

    public PemGenerationException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public PemGenerationException(String str, Throwable th) {
        super(str);
        this.E = th;
    }
}
