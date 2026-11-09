package defpackage;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes.dex */
public final class bx {
    public final int a;
    public final uw b;
    public long c;
    public long d;
    public long e;
    public long f;
    public final ArrayDeque g;
    public boolean h;
    public final zw i;
    public final yw j;
    public final ax k;
    public final ax l;
    public ErrorCode m;
    public IOException n;

    public bx(int i, uw uwVar, boolean z, boolean z2, tv tvVar) {
        oy.j(uwVar, "connection");
        this.a = i;
        this.b = uwVar;
        this.f = uwVar.r.a();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.g = arrayDeque;
        this.i = new zw(this, uwVar.q.a(), z2);
        this.j = new yw(this, z);
        this.k = new ax(this);
        this.l = new ax(this);
        if (tvVar == null) {
            if (!g()) {
                throw new IllegalStateException("remotely-initiated streams should have headers");
            }
        } else {
            if (g()) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
            }
            arrayDeque.add(tvVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r2 = this;
            byte[] r0 = defpackage.kq0.a
            monitor-enter(r2)
            zw r0 = r2.i     // Catch: java.lang.Throwable -> L19
            boolean r1 = r0.c     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L1b
            boolean r0 = r0.f     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1b
            yw r0 = r2.j     // Catch: java.lang.Throwable -> L19
            boolean r1 = r0.b     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L17
            boolean r0 = r0.d     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1b
        L17:
            r0 = 1
            goto L1c
        L19:
            r0 = move-exception
            goto L34
        L1b:
            r0 = 0
        L1c:
            boolean r1 = r2.h()     // Catch: java.lang.Throwable -> L19
            monitor-exit(r2)
            if (r0 == 0) goto L2a
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.CANCEL
            r1 = 0
            r2.c(r0, r1)
            return
        L2a:
            if (r1 != 0) goto L33
            uw r0 = r2.b
            int r1 = r2.a
            r0.D(r1)
        L33:
            return
        L34:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bx.a():void");
    }

    public final void b() throws IOException {
        yw ywVar = this.j;
        if (ywVar.d) {
            throw new IOException("stream closed");
        }
        if (ywVar.b) {
            throw new IOException("stream finished");
        }
        if (this.m != null) {
            IOException iOException = this.n;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.m;
            oy.g(errorCode);
            throw new StreamResetException(errorCode);
        }
    }

    public final void c(ErrorCode errorCode, IOException iOException) {
        oy.j(errorCode, "rstStatusCode");
        if (d(errorCode, iOException)) {
            uw uwVar = this.b;
            uwVar.getClass();
            uwVar.x.G(this.a, errorCode);
        }
    }

    public final boolean d(ErrorCode errorCode, IOException iOException) {
        byte[] bArr = kq0.a;
        synchronized (this) {
            if (this.m != null) {
                return false;
            }
            this.m = errorCode;
            this.n = iOException;
            notifyAll();
            if (this.i.c) {
                if (this.j.b) {
                    return false;
                }
            }
            this.b.D(this.a);
            return true;
        }
    }

    public final void e(ErrorCode errorCode) {
        oy.j(errorCode, "errorCode");
        if (d(errorCode, null)) {
            this.b.H(this.a, errorCode);
        }
    }

    public final yw f() {
        synchronized (this) {
            if (!this.h && !g()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.j;
    }

    public final boolean g() {
        boolean z = (this.a & 1) == 1;
        this.b.getClass();
        return true == z;
    }

    public final synchronized boolean h() {
        if (this.m != null) {
            return false;
        }
        zw zwVar = this.i;
        if (zwVar.c || zwVar.f) {
            yw ywVar = this.j;
            if (ywVar.b || ywVar.d) {
                if (this.h) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void i(tv tvVar, boolean z) {
        boolean zH;
        oy.j(tvVar, "headers");
        byte[] bArr = kq0.a;
        synchronized (this) {
            try {
                if (this.h && z) {
                    this.i.getClass();
                } else {
                    this.h = true;
                    this.g.add(tvVar);
                }
                if (z) {
                    this.i.c = true;
                }
                zH = h();
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zH) {
            return;
        }
        this.b.D(this.a);
    }

    public final synchronized void j(ErrorCode errorCode) {
        oy.j(errorCode, "errorCode");
        if (this.m == null) {
            this.m = errorCode;
            notifyAll();
        }
    }

    public final void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
