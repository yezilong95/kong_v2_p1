package okhttp3.internal.http2;

import defpackage.oy;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamResetException(ErrorCode errorCode) {
        super("stream was reset: " + errorCode);
        oy.j(errorCode, "errorCode");
        this.errorCode = errorCode;
    }
}
