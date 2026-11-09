package org.bouncycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public class CryptoException extends Exception {
    public final Throwable E;

    public CryptoException() {
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public CryptoException(String str) {
        super(str);
    }

    public CryptoException(String str, Throwable th) {
        super(str);
        this.E = th;
    }
}
