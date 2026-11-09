package defpackage;

import java.io.IOException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes.dex */
public final class uo {
    public final zc0 a;
    public final p2 b;
    public final wc0 c;
    public w2 d;
    public iw e;
    public int f;
    public int g;
    public int h;
    public zf0 i;

    public uo(zc0 zc0Var, p2 p2Var, wc0 wc0Var) {
        oy.j(zc0Var, "connectionPool");
        oy.j(wc0Var, "call");
        this.a = zc0Var;
        this.b = p2Var;
        this.c = wc0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:180:0x02d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARN: Type inference failed for: r6v14, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.yc0 a(int r13, int r14, int r15, boolean r16, boolean r17) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 895
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uo.a(int, int, int, boolean, boolean):yc0");
    }

    public final boolean b(fx fxVar) {
        oy.j(fxVar, "url");
        fx fxVar2 = this.b.h;
        return fxVar.e == fxVar2.e && oy.c(fxVar.d, fxVar2.d);
    }

    public final void c(IOException iOException) {
        oy.j(iOException, "e");
        this.i = null;
        if ((iOException instanceof StreamResetException) && ((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
            this.f++;
        } else if (iOException instanceof ConnectionShutdownException) {
            this.g++;
        } else {
            this.h++;
        }
    }
}
