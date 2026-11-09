package defpackage;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes.dex */
public final class xf implements oj0 {
    public static final wf a = new wf();

    @Override // defpackage.oj0
    public final String a(SSLSocket sSLSocket) {
        if (b(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // defpackage.oj0
    public final boolean b(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // defpackage.oj0
    public final boolean c() {
        boolean z = vf.d;
        return vf.d;
    }

    @Override // defpackage.oj0
    public final void d(SSLSocket sSLSocket, String str, List list) {
        oy.j(list, "protocols");
        if (b(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            rb0 rb0Var = rb0.a;
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) yo.h(list).toArray(new String[0]));
        }
    }
}
