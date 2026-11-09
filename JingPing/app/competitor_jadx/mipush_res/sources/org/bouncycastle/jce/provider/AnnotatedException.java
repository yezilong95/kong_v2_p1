package org.bouncycastle.jce.provider;

/* JADX INFO: loaded from: classes.dex */
public class AnnotatedException extends Exception {
    public final Throwable E;

    public AnnotatedException(String str) {
        this(str, null);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }

    public AnnotatedException(String str, Throwable th) {
        super(str);
        this.E = th;
    }
}
