package defpackage;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class xw implements Closeable {
    public static final Logger e;
    public final sc0 b;
    public final ww c;
    public final zv d;

    static {
        Logger logger = Logger.getLogger(jw.class.getName());
        oy.i(logger, "getLogger(Http2::class.java.name)");
        e = logger;
    }

    public xw(sc0 sc0Var) {
        oy.j(sc0Var, "source");
        this.b = sc0Var;
        ww wwVar = new ww(sc0Var);
        this.c = wwVar;
        this.d = new zv(wwVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
    
        throw new java.io.IOException(defpackage.hk.g(r7, "Header index too large "));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List C(int r6, int r7, int r8, int r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xw.C(int, int, int, int):java.util.List");
    }

    public final void D(ow owVar, int i, int i2, int i3) throws IOException {
        int i4;
        int i5 = 1;
        if (i3 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean z = false;
        boolean z2 = (i2 & 1) != 0;
        if ((i2 & 8) != 0) {
            byte bC = this.b.C();
            byte[] bArr = kq0.a;
            i4 = bC & 255;
        } else {
            i4 = 0;
        }
        if ((i2 & 32) != 0) {
            sc0 sc0Var = this.b;
            sc0Var.F();
            sc0Var.C();
            byte[] bArr2 = kq0.a;
            owVar.getClass();
            i -= 5;
        }
        List listC = C(rp0.J(i, i2, i4), i4, i2, i3);
        owVar.getClass();
        oy.j(listC, "headerBlock");
        owVar.c.getClass();
        if (i3 != 0 && (i3 & 1) == 0) {
            z = true;
        }
        if (z) {
            uw uwVar = owVar.c;
            uwVar.getClass();
            uwVar.j.c(new qw(uwVar.d + '[' + i3 + "] onHeaders", uwVar, i3, listC, z2), 0L);
            return;
        }
        uw uwVar2 = owVar.c;
        synchronized (uwVar2) {
            bx bxVarC = uwVar2.C(i3);
            if (bxVarC != null) {
                bxVarC.i(kq0.v(listC), z2);
                return;
            }
            if (uwVar2.g) {
                return;
            }
            if (i3 <= uwVar2.e) {
                return;
            }
            if (i3 % 2 == uwVar2.f % 2) {
                return;
            }
            bx bxVar = new bx(i3, uwVar2, false, z2, kq0.v(listC));
            uwVar2.e = i3;
            uwVar2.c.put(Integer.valueOf(i3), bxVar);
            uwVar2.h.e().c(new mw(uwVar2.d + '[' + i3 + "] onStream", uwVar2, bxVar, i5), 0L);
        }
    }

    public final void E(ow owVar, int i, int i2, int i3) throws IOException {
        int i4;
        if (i3 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        if ((i2 & 8) != 0) {
            byte bC = this.b.C();
            byte[] bArr = kq0.a;
            i4 = bC & 255;
        } else {
            i4 = 0;
        }
        int iF = this.b.F() & Integer.MAX_VALUE;
        List listC = C(rp0.J(i - 4, i2, i4), i4, i2, i3);
        owVar.getClass();
        oy.j(listC, "requestHeaders");
        uw uwVar = owVar.c;
        uwVar.getClass();
        synchronized (uwVar) {
            if (uwVar.z.contains(Integer.valueOf(iF))) {
                uwVar.H(iF, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            uwVar.z.add(Integer.valueOf(iF));
            uwVar.j.c(new qw(uwVar.d + '[' + iF + "] onRequest", uwVar, iF, listC, 1), 0L);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:141:0x0239, code lost:
    
        throw new java.io.IOException(defpackage.hk.g(r8, "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: "));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(boolean r20, defpackage.ow r21) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 880
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xw.a(boolean, ow):boolean");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.b.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0124, code lost:
    
        if (r8 == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0126, code lost:
    
        r16.i(defpackage.kq0.b, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t(defpackage.ow r18, int r19, int r20, int r21) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xw.t(ow, int, int, int):void");
    }
}
