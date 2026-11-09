package okhttp3.internal.connection;

import defpackage.nv0;
import defpackage.oy;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class RouteException extends RuntimeException {
    public final IOException b;
    public IOException c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RouteException(IOException iOException) {
        super(iOException);
        oy.j(iOException, "firstConnectException");
        this.b = iOException;
        this.c = iOException;
    }

    public final void addConnectException(IOException iOException) {
        oy.j(iOException, "e");
        nv0.c(this.b, iOException);
        this.c = iOException;
    }

    public final IOException getFirstConnectException() {
        return this.b;
    }

    public final IOException getLastConnectException() {
        return this.c;
    }
}
