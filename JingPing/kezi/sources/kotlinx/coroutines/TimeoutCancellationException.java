package kotlinx.coroutines;

import defpackage.bz;
import java.util.concurrent.CancellationException;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class TimeoutCancellationException extends CancellationException {
    public final transient bz coroutine;

    public TimeoutCancellationException(String str, bz bzVar) {
        super(str);
        this.coroutine = bzVar;
    }

    public TimeoutCancellationException createCopy() {
        String message = getMessage();
        if (message == null) {
            message = ExtensionRequestData.EMPTY_VALUE;
        }
        TimeoutCancellationException timeoutCancellationException = new TimeoutCancellationException(message, this.coroutine);
        timeoutCancellationException.initCause(this);
        return timeoutCancellationException;
    }

    public TimeoutCancellationException(String str) {
        this(str, null);
    }
}
