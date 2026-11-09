package defpackage;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class n9 implements oj0 {
    public static final m9 a = new m9();

    @Override // defpackage.oj0
    public final String a(SSLSocket sSLSocket) {
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null ? true : applicationProtocol.equals(ExtensionRequestData.EMPTY_VALUE)) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // defpackage.oj0
    public final boolean b(SSLSocket sSLSocket) {
        return false;
    }

    @Override // defpackage.oj0
    public final boolean c() {
        boolean z = l9.d;
        return l9.d;
    }

    @Override // defpackage.oj0
    public final void d(SSLSocket sSLSocket, String str, List list) {
        oy.j(list, "protocols");
        if (b(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            rb0 rb0Var = rb0.a;
            parameters.setApplicationProtocols((String[]) yo.h(list).toArray(new String[0]));
            bCSSLSocket.setParameters(parameters);
        }
    }
}
