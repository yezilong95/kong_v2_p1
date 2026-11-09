package org.bouncycastle.util;

/* JADX INFO: loaded from: classes.dex */
public class StoreException extends RuntimeException {
    public final Throwable E;

    public StoreException(String str, Throwable th) {
        super(str);
        this.E = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
