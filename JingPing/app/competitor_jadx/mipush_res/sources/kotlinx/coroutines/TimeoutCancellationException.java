package kotlinx.coroutines;

import defpackage.f50;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class TimeoutCancellationException extends CancellationException {
    public final transient f50 coroutine;

    public TimeoutCancellationException(String str, f50 f50Var) {
        super(str);
        this.coroutine = f50Var;
    }

    public TimeoutCancellationException createCopy() {
        String message = getMessage();
        if (message == null) {
            message = "";
        }
        TimeoutCancellationException timeoutCancellationException = new TimeoutCancellationException(message, this.coroutine);
        timeoutCancellationException.initCause(this);
        return timeoutCancellationException;
    }

    public TimeoutCancellationException(String str) {
        this(str, null);
    }
}
