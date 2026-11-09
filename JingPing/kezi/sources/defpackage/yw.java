package defpackage;

import java.net.SocketTimeoutException;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class yw implements hj0 {
    public final boolean b;
    public final p9 c = new p9();
    public boolean d;
    public final /* synthetic */ bx e;

    public yw(bx bxVar, boolean z) {
        this.e = bxVar;
        this.b = z;
    }

    /* JADX WARN: Finally extract failed */
    public final void a(boolean z) throws SocketTimeoutException {
        long jMin;
        boolean z2;
        bx bxVar = this.e;
        synchronized (bxVar) {
            bxVar.l.h();
            while (bxVar.e >= bxVar.f && !this.b && !this.d) {
                try {
                    synchronized (bxVar) {
                        ErrorCode errorCode = bxVar.m;
                        if (errorCode != null) {
                            break;
                        } else {
                            bxVar.k();
                        }
                    }
                } catch (Throwable th) {
                    bxVar.l.k();
                    throw th;
                }
            }
            bxVar.l.k();
            bxVar.b();
            jMin = Math.min(bxVar.f - bxVar.e, this.c.c);
            bxVar.e += jMin;
            z2 = z && jMin == this.c.c;
        }
        this.e.l.h();
        try {
            bx bxVar2 = this.e;
            bxVar2.b.G(bxVar2.a, z2, this.c, jMin);
        } finally {
            this.e.l.k();
        }
    }

    @Override // defpackage.hj0
    public final jo0 b() {
        return this.e.l;
    }

    @Override // defpackage.hj0
    public final void c(long j, p9 p9Var) throws SocketTimeoutException {
        byte[] bArr = kq0.a;
        p9 p9Var2 = this.c;
        p9Var2.c(j, p9Var);
        while (p9Var2.c >= 16384) {
            a(false);
        }
    }

    @Override // defpackage.hj0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws SocketTimeoutException {
        boolean z;
        bx bxVar = this.e;
        byte[] bArr = kq0.a;
        synchronized (bxVar) {
            if (this.d) {
                return;
            }
            synchronized (bxVar) {
                z = bxVar.m == null;
            }
            bx bxVar2 = this.e;
            if (!bxVar2.j.b) {
                if (this.c.c > 0) {
                    while (this.c.c > 0) {
                        a(true);
                    }
                } else if (z) {
                    bxVar2.b.G(bxVar2.a, true, null, 0L);
                }
            }
            synchronized (this.e) {
                this.d = true;
            }
            this.e.b.flush();
            this.e.a();
        }
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final void flush() throws SocketTimeoutException {
        bx bxVar = this.e;
        byte[] bArr = kq0.a;
        synchronized (bxVar) {
            bxVar.b();
        }
        while (this.c.c > 0) {
            a(false);
            this.e.b.flush();
        }
    }
}
