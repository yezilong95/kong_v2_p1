package defpackage;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Ref$IntRef;
import okhttp3.internal.http2.ErrorCode;
import org.java_websocket.WebSocketImpl;

/* JADX INFO: loaded from: classes.dex */
public final class uw implements Closeable {
    public static final gi0 A;
    public final lw b;
    public final LinkedHashMap c = new LinkedHashMap();
    public final String d;
    public int e;
    public int f;
    public boolean g;
    public final an0 h;
    public final zm0 i;
    public final zm0 j;
    public final zm0 k;
    public final qm l;
    public long m;
    public long n;
    public long o;
    public long p;
    public final gi0 q;
    public gi0 r;
    public long s;
    public long t;
    public long u;
    public long v;
    public final Socket w;
    public final cx x;
    public final ow y;
    public final LinkedHashSet z;

    static {
        gi0 gi0Var = new gi0();
        gi0Var.c(7, 65535);
        gi0Var.c(5, WebSocketImpl.RCVBUF);
        A = gi0Var;
    }

    public uw(z4 z4Var) {
        this.b = (lw) z4Var.g;
        String str = (String) z4Var.d;
        if (str == null) {
            oy.S("connectionName");
            throw null;
        }
        this.d = str;
        this.f = 3;
        an0 an0Var = (an0) z4Var.b;
        this.h = an0Var;
        this.i = an0Var.e();
        this.j = an0Var.e();
        this.k = an0Var.e();
        this.l = qm.i;
        gi0 gi0Var = new gi0();
        gi0Var.c(7, 16777216);
        this.q = gi0Var;
        this.r = A;
        this.v = r0.a();
        Socket socket = (Socket) z4Var.c;
        if (socket == null) {
            oy.S("socket");
            throw null;
        }
        this.w = socket;
        rc0 rc0Var = (rc0) z4Var.f;
        if (rc0Var == null) {
            oy.S("sink");
            throw null;
        }
        this.x = new cx(rc0Var);
        sc0 sc0Var = (sc0) z4Var.e;
        if (sc0Var == null) {
            oy.S("source");
            throw null;
        }
        this.y = new ow(this, new xw(sc0Var));
        this.z = new LinkedHashSet();
    }

    public final synchronized bx C(int i) {
        return (bx) this.c.get(Integer.valueOf(i));
    }

    public final synchronized bx D(int i) {
        bx bxVar;
        bxVar = (bx) this.c.remove(Integer.valueOf(i));
        notifyAll();
        return bxVar;
    }

    public final void E(ErrorCode errorCode) {
        oy.j(errorCode, "statusCode");
        synchronized (this.x) {
            Ref$IntRef ref$IntRef = new Ref$IntRef();
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                int i = this.e;
                ref$IntRef.element = i;
                this.x.D(i, errorCode, kq0.a);
            }
        }
    }

    public final synchronized void F(long j) {
        long j2 = this.s + j;
        this.s = j2;
        long j3 = j2 - this.t;
        if (j3 >= this.q.a() / 2) {
            I(j3, 0);
            this.t += j3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0035, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.x.d);
        r6 = r2;
        r8.u += r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void G(int r9, boolean r10, defpackage.p9 r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            cx r12 = r8.x
            r12.t(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L68
            monitor-enter(r8)
        L12:
            long r4 = r8.u     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            long r6 = r8.v     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L34
            java.util.LinkedHashMap r2 = r8.c     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            if (r2 == 0) goto L2c
            r8.wait()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            goto L12
        L2a:
            r9 = move-exception
            goto L66
        L2c:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            throw r9     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
        L34:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L2a
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L2a
            cx r4 = r8.x     // Catch: java.lang.Throwable -> L2a
            int r4 = r4.d     // Catch: java.lang.Throwable -> L2a
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L2a
            long r4 = r8.u     // Catch: java.lang.Throwable -> L2a
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L2a
            long r4 = r4 + r6
            r8.u = r4     // Catch: java.lang.Throwable -> L2a
            monitor-exit(r8)
            long r12 = r12 - r6
            cx r4 = r8.x
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.t(r5, r9, r11, r2)
            goto Ld
        L59:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r9.interrupt()     // Catch: java.lang.Throwable -> L2a
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L2a
            r9.<init>()     // Catch: java.lang.Throwable -> L2a
            throw r9     // Catch: java.lang.Throwable -> L2a
        L66:
            monitor-exit(r8)
            throw r9
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uw.G(int, boolean, p9, long):void");
    }

    public final void H(int i, ErrorCode errorCode) {
        oy.j(errorCode, "errorCode");
        this.i.c(new sw(this.d + '[' + i + "] writeSynReset", this, i, errorCode), 0L);
    }

    public final void I(long j, int i) {
        this.i.c(new tw(this.d + '[' + i + "] windowUpdate", this, i, j), 0L);
    }

    public final void a(ErrorCode errorCode, ErrorCode errorCode2, IOException iOException) {
        int i;
        Object[] array;
        oy.j(errorCode, "connectionCode");
        oy.j(errorCode2, "streamCode");
        byte[] bArr = kq0.a;
        try {
            E(errorCode);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (this.c.isEmpty()) {
                array = null;
            } else {
                array = this.c.values().toArray(new bx[0]);
                this.c.clear();
            }
        }
        bx[] bxVarArr = (bx[]) array;
        if (bxVarArr != null) {
            for (bx bxVar : bxVarArr) {
                try {
                    bxVar.c(errorCode2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.x.close();
        } catch (IOException unused3) {
        }
        try {
            this.w.close();
        } catch (IOException unused4) {
        }
        this.i.e();
        this.j.e();
        this.k.e();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void flush() {
        this.x.flush();
    }

    public final void t(IOException iOException) {
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        a(errorCode, errorCode, iOException);
    }
}
