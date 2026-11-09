package org.bouncycastle.jce.exception;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ExtIOException extends IOException {
    public final Throwable E;

    public ExtIOException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
