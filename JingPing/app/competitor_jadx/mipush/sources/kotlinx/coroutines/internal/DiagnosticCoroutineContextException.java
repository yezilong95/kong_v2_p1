package kotlinx.coroutines.internal;

import defpackage.bj;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {
    public final transient bj E;

    public DiagnosticCoroutineContextException(bj bjVar) {
        this.E = bjVar;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.E.toString();
    }
}
