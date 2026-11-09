package defpackage;

import java.net.SocketTimeoutException;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class ax extends k7 {
    public final /* synthetic */ bx m;

    public ax(bx bxVar) {
        this.m = bxVar;
    }

    @Override // defpackage.k7
    public final void j() {
        this.m.e(ErrorCode.CANCEL);
        uw uwVar = this.m.b;
        synchronized (uwVar) {
            long j = uwVar.o;
            long j2 = uwVar.n;
            if (j < j2) {
                return;
            }
            uwVar.n = j2 + 1;
            uwVar.p = System.nanoTime() + ((long) 1000000000);
            uwVar.i.c(new rw(hk.k(new StringBuilder(), uwVar.d, " ping"), uwVar, 0), 0L);
        }
    }

    public final void k() throws SocketTimeoutException {
        if (i()) {
            throw new SocketTimeoutException("timeout");
        }
    }
}
