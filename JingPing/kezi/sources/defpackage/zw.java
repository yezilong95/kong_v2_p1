package defpackage;

import java.io.IOException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes.dex */
public final class zw implements uj0 {
    public final long b;
    public boolean c;
    public final p9 d = new p9();
    public final p9 e = new p9();
    public boolean f;
    public final /* synthetic */ bx g;

    public zw(bx bxVar, long j, boolean z) {
        this.g = bxVar;
        this.b = j;
        this.c = z;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.g.k;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j;
        bx bxVar = this.g;
        synchronized (bxVar) {
            this.f = true;
            p9 p9Var = this.e;
            j = p9Var.c;
            p9Var.I(j);
            bxVar.notifyAll();
        }
        if (j > 0) {
            byte[] bArr = kq0.a;
            this.g.b.F(j);
        }
        this.g.a();
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) throws Throwable {
        ErrorCode errorCode;
        Throwable streamResetException;
        boolean z;
        long jD;
        oy.j(p9Var, "sink");
        do {
            bx bxVar = this.g;
            synchronized (bxVar) {
                bxVar.k.h();
                try {
                    synchronized (bxVar) {
                        errorCode = bxVar.m;
                    }
                } finally {
                    bxVar.k.k();
                }
            }
            if (errorCode == null || this.c) {
                streamResetException = null;
            } else {
                streamResetException = bxVar.n;
                if (streamResetException == null) {
                    synchronized (bxVar) {
                        ErrorCode errorCode2 = bxVar.m;
                        oy.g(errorCode2);
                        streamResetException = new StreamResetException(errorCode2);
                    }
                }
            }
            if (this.f) {
                throw new IOException("stream closed");
            }
            p9 p9Var2 = this.e;
            long j2 = p9Var2.c;
            z = false;
            if (j2 > 0) {
                jD = p9Var2.d(Math.min(8192L, j2), p9Var);
                long j3 = bxVar.c + jD;
                bxVar.c = j3;
                long j4 = j3 - bxVar.d;
                if (streamResetException == null && j4 >= bxVar.b.q.a() / 2) {
                    bxVar.b.I(j4, bxVar.a);
                    bxVar.d = bxVar.c;
                }
            } else {
                if (!this.c && streamResetException == null) {
                    bxVar.k();
                    z = true;
                }
                jD = -1;
            }
        } while (z);
        if (jD != -1) {
            return jD;
        }
        if (streamResetException == null) {
            return -1L;
        }
        throw streamResetException;
    }
}
