package defpackage;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class fw extends cw {
    public long e;
    public final /* synthetic */ iw f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fw(iw iwVar, long j) {
        super(iwVar);
        this.f = iwVar;
        this.e = j;
        if (j == 0) {
            a();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        if (this.e != 0) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (!kq0.h(this)) {
                ((yc0) this.f.c).k();
                a();
            }
        }
        this.c = true;
    }

    @Override // defpackage.cw, defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        oy.j(p9Var, "sink");
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long j2 = this.e;
        if (j2 == 0) {
            return -1L;
        }
        long jD = super.d(Math.min(j2, 8192L), p9Var);
        if (jD == -1) {
            ((yc0) this.f.c).k();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            a();
            throw protocolException;
        }
        long j3 = this.e - jD;
        this.e = j3;
        if (j3 == 0) {
            a();
        }
        return jD;
    }
}
