package defpackage;

import android.net.ssl.SSLSockets;
import android.os.Build;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class a3 implements oj0 {
    @Override // defpackage.oj0
    public final String a(SSLSocket sSLSocket) {
        String applicationProtocol = sSLSocket.getApplicationProtocol();
        if (applicationProtocol == null ? true : applicationProtocol.equals(ExtensionRequestData.EMPTY_VALUE)) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // defpackage.oj0
    public final boolean b(SSLSocket sSLSocket) {
        return SSLSockets.isSupportedSocket(sSLSocket);
    }

    @Override // defpackage.oj0
    public final boolean c() {
        rb0 rb0Var = rb0.a;
        return yo.l() && Build.VERSION.SDK_INT >= 29;
    }

    @Override // defpackage.oj0
    public final void d(SSLSocket sSLSocket, String str, List list) throws IOException {
        oy.j(list, "protocols");
        try {
            SSLSockets.setUseSessionTickets(sSLSocket, true);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            rb0 rb0Var = rb0.a;
            sSLParameters.setApplicationProtocols((String[]) yo.h(list).toArray(new String[0]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalArgumentException e) {
            throw new IOException("Android internal error", e);
        }
    }
}
