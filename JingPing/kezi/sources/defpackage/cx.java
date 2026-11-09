package defpackage;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;
import org.java_websocket.WebSocketImpl;

/* JADX INFO: loaded from: classes.dex */
public final class cx implements Closeable {
    public static final Logger g = Logger.getLogger(jw.class.getName());
    public final rc0 b;
    public final p9 c;
    public int d;
    public boolean e;
    public final aw f;

    public cx(rc0 rc0Var) {
        oy.j(rc0Var, "sink");
        this.b = rc0Var;
        p9 p9Var = new p9();
        this.c = p9Var;
        this.d = WebSocketImpl.RCVBUF;
        this.f = new aw(p9Var);
    }

    public final void C(int i, int i2, int i3, int i4) {
        Level level = Level.FINE;
        Logger logger = g;
        if (logger.isLoggable(level)) {
            logger.fine(jw.a(false, i, i2, i3, i4));
        }
        if (i2 > this.d) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.d + ": " + i2).toString());
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            throw new IllegalArgumentException(hk.g(i, "reserved bit set: ").toString());
        }
        byte[] bArr = kq0.a;
        rc0 rc0Var = this.b;
        oy.j(rc0Var, "<this>");
        rc0Var.t((i2 >>> 16) & 255);
        rc0Var.t((i2 >>> 8) & 255);
        rc0Var.t(i2 & 255);
        rc0Var.t(i3 & 255);
        rc0Var.t(i4 & 255);
        rc0Var.C(i & Integer.MAX_VALUE);
    }

    public final synchronized void D(int i, ErrorCode errorCode, byte[] bArr) {
        oy.j(errorCode, "errorCode");
        if (this.e) {
            throw new IOException("closed");
        }
        if (errorCode.getHttpCode() == -1) {
            throw new IllegalArgumentException("errorCode.httpCode == -1");
        }
        C(0, bArr.length + 8, 7, 0);
        this.b.C(i);
        this.b.C(errorCode.getHttpCode());
        if (bArr.length != 0) {
            rc0 rc0Var = this.b;
            if (rc0Var.d) {
                throw new IllegalStateException("closed");
            }
            rc0Var.c.M(bArr, 0, bArr.length);
            rc0Var.a();
        }
        this.b.flush();
    }

    public final synchronized void E(boolean z, int i, ArrayList arrayList) {
        if (this.e) {
            throw new IOException("closed");
        }
        this.f.d(arrayList);
        long j = this.c.c;
        long jMin = Math.min(this.d, j);
        int i2 = j == jMin ? 4 : 0;
        if (z) {
            i2 |= 1;
        }
        C(i, (int) jMin, 1, i2);
        this.b.c(jMin, this.c);
        if (j > jMin) {
            long j2 = j - jMin;
            while (j2 > 0) {
                long jMin2 = Math.min(this.d, j2);
                j2 -= jMin2;
                C(i, (int) jMin2, 9, j2 == 0 ? 4 : 0);
                this.b.c(jMin2, this.c);
            }
        }
    }

    public final synchronized void F(int i, int i2, boolean z) {
        if (this.e) {
            throw new IOException("closed");
        }
        C(0, 8, 6, z ? 1 : 0);
        this.b.C(i);
        this.b.C(i2);
        this.b.flush();
    }

    public final synchronized void G(int i, ErrorCode errorCode) {
        oy.j(errorCode, "errorCode");
        if (this.e) {
            throw new IOException("closed");
        }
        if (errorCode.getHttpCode() == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        C(i, 4, 3, 0);
        this.b.C(errorCode.getHttpCode());
        this.b.flush();
    }

    public final synchronized void H(long j, int i) {
        if (this.e) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j).toString());
        }
        C(i, 4, 8, 0);
        this.b.C((int) j);
        this.b.flush();
    }

    public final synchronized void a(gi0 gi0Var) {
        try {
            oy.j(gi0Var, "peerSettings");
            if (this.e) {
                throw new IOException("closed");
            }
            int i = this.d;
            int i2 = gi0Var.a;
            if ((i2 & 32) != 0) {
                i = gi0Var.b[5];
            }
            this.d = i;
            if (((i2 & 2) != 0 ? gi0Var.b[1] : -1) != -1) {
                aw awVar = this.f;
                int i3 = (i2 & 2) != 0 ? gi0Var.b[1] : -1;
                awVar.getClass();
                int iMin = Math.min(i3, WebSocketImpl.RCVBUF);
                int i4 = awVar.d;
                if (i4 != iMin) {
                    if (iMin < i4) {
                        awVar.b = Math.min(awVar.b, iMin);
                    }
                    awVar.c = true;
                    awVar.d = iMin;
                    int i5 = awVar.h;
                    if (iMin < i5) {
                        if (iMin == 0) {
                            sv[] svVarArr = awVar.e;
                            g7.b0(svVarArr, null, 0, svVarArr.length);
                            awVar.f = awVar.e.length - 1;
                            awVar.g = 0;
                            awVar.h = 0;
                        } else {
                            awVar.a(i5 - iMin);
                        }
                    }
                }
            }
            C(0, 0, 4, 1);
            this.b.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.e = true;
        this.b.close();
    }

    public final synchronized void flush() {
        if (this.e) {
            throw new IOException("closed");
        }
        this.b.flush();
    }

    public final synchronized void t(boolean z, int i, p9 p9Var, int i2) {
        if (this.e) {
            throw new IOException("closed");
        }
        C(i, i2, 0, z ? 1 : 0);
        if (i2 > 0) {
            oy.g(p9Var);
            this.b.c(i2, p9Var);
        }
    }
}
