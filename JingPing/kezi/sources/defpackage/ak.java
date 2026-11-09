package defpackage;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public final class ak implements oj0 {
    public final zj a;
    public oj0 b;

    public ak(zj zjVar) {
        this.a = zjVar;
    }

    @Override // defpackage.oj0
    public final String a(SSLSocket sSLSocket) {
        oj0 oj0VarE = e(sSLSocket);
        if (oj0VarE != null) {
            return oj0VarE.a(sSLSocket);
        }
        return null;
    }

    @Override // defpackage.oj0
    public final boolean b(SSLSocket sSLSocket) {
        return this.a.b(sSLSocket);
    }

    @Override // defpackage.oj0
    public final boolean c() {
        return true;
    }

    @Override // defpackage.oj0
    public final void d(SSLSocket sSLSocket, String str, List list) {
        oy.j(list, "protocols");
        oj0 oj0VarE = e(sSLSocket);
        if (oj0VarE != null) {
            oj0VarE.d(sSLSocket, str, list);
        }
    }

    public final synchronized oj0 e(SSLSocket sSLSocket) {
        try {
            if (this.b == null && this.a.b(sSLSocket)) {
                this.b = this.a.c(sSLSocket);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.b;
    }
}
