package org.bouncycastle.crypto.io;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class CipherIOException extends IOException {
    private static final long serialVersionUID = 1;
    public final Throwable E;

    public CipherIOException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
