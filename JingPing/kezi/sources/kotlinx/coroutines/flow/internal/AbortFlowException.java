package kotlinx.coroutines.flow.internal;

import defpackage.qq;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class AbortFlowException extends CancellationException {
    public final transient qq owner;

    public AbortFlowException(qq qqVar) {
        super("Flow was aborted, no more elements needed");
        this.owner = qqVar;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
