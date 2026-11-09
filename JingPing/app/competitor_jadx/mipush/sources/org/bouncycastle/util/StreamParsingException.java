package org.bouncycastle.util;

/* JADX INFO: loaded from: classes.dex */
public class StreamParsingException extends Exception {
    public final Throwable E;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
