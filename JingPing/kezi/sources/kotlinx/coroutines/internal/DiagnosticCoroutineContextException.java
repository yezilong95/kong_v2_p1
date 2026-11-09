package kotlinx.coroutines.internal;

import defpackage.nh;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {
    public final transient nh b;

    public DiagnosticCoroutineContextException(nh nhVar) {
        this.b = nhVar;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.b.toString();
    }
}
