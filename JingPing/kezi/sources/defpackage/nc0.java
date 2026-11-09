package defpackage;

import java.io.IOException;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class nc0 {
    public static Protocol a(String str) throws IOException {
        oy.j(str, "protocol");
        Protocol protocol = Protocol.HTTP_1_0;
        if (str.equals(protocol.b)) {
            return protocol;
        }
        Protocol protocol2 = Protocol.HTTP_1_1;
        if (str.equals(protocol2.b)) {
            return protocol2;
        }
        Protocol protocol3 = Protocol.H2_PRIOR_KNOWLEDGE;
        if (str.equals(protocol3.b)) {
            return protocol3;
        }
        Protocol protocol4 = Protocol.HTTP_2;
        if (str.equals(protocol4.b)) {
            return protocol4;
        }
        Protocol protocol5 = Protocol.SPDY_3;
        if (str.equals(protocol5.b)) {
            return protocol5;
        }
        Protocol protocol6 = Protocol.QUIC;
        if (str.equals(protocol6.b)) {
            return protocol6;
        }
        throw new IOException("Unexpected protocol: ".concat(str));
    }
}
