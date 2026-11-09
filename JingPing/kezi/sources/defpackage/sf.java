package defpackage;

import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public final class sf {
    public final List a;
    public int b;
    public boolean c;
    public boolean d;

    public sf(List list) {
        oy.j(list, "connectionSpecs");
        this.a = list;
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [java.io.Serializable, java.lang.String[]] */
    public final rf a(SSLSocket sSLSocket) throws UnknownServiceException {
        rf rfVar;
        int i;
        boolean z;
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        int i2 = this.b;
        List list = this.a;
        int size = list.size();
        while (true) {
            if (i2 >= size) {
                rfVar = null;
                break;
            }
            rfVar = (rf) list.get(i2);
            if (rfVar.b(sSLSocket)) {
                this.b = i2 + 1;
                break;
            }
            i2++;
        }
        if (rfVar == null) {
            StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
            sb.append(this.d);
            sb.append(", modes=");
            sb.append(list);
            sb.append(", supported protocols=");
            String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
            oy.g(enabledProtocols2);
            String string = Arrays.toString(enabledProtocols2);
            oy.i(string, "toString(this)");
            sb.append(string);
            throw new UnknownServiceException(sb.toString());
        }
        int i3 = this.b;
        int size2 = list.size();
        while (true) {
            i = 0;
            if (i3 >= size2) {
                z = false;
                break;
            }
            if (((rf) list.get(i3)).b(sSLSocket)) {
                z = true;
                break;
            }
            i3++;
        }
        this.c = z;
        boolean z2 = this.d;
        String[] strArr = rfVar.c;
        if (strArr != null) {
            String[] enabledCipherSuites2 = sSLSocket.getEnabledCipherSuites();
            oy.i(enabledCipherSuites2, "sslSocket.enabledCipherSuites");
            enabledCipherSuites = kq0.p(enabledCipherSuites2, strArr, rc.c);
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        ?? r6 = rfVar.d;
        if (r6 != 0) {
            String[] enabledProtocols3 = sSLSocket.getEnabledProtocols();
            oy.i(enabledProtocols3, "sslSocket.enabledProtocols");
            enabledProtocols = kq0.p(enabledProtocols3, r6, j80.b);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        oy.i(supportedCipherSuites, "supportedCipherSuites");
        l3 l3Var = rc.c;
        byte[] bArr = kq0.a;
        int length = supportedCipherSuites.length;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (l3Var.compare(supportedCipherSuites[i], "TLS_FALLBACK_SCSV") == 0) {
                break;
            }
            i++;
        }
        if (z2 && i != -1) {
            oy.i(enabledCipherSuites, "cipherSuitesIntersection");
            String str = supportedCipherSuites[i];
            oy.i(str, "supportedCipherSuites[indexOfFallbackScsv]");
            Object[] objArrCopyOf = Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length + 1);
            oy.i(objArrCopyOf, "copyOf(this, newSize)");
            enabledCipherSuites = (String[]) objArrCopyOf;
            enabledCipherSuites[enabledCipherSuites.length - 1] = str;
        }
        qf qfVar = new qf();
        qfVar.a = rfVar.a;
        qfVar.c = strArr;
        qfVar.d = r6;
        qfVar.b = rfVar.b;
        oy.i(enabledCipherSuites, "cipherSuitesIntersection");
        qfVar.c((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
        oy.i(enabledProtocols, "tlsVersionsIntersection");
        qfVar.d((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length));
        rf rfVarA = qfVar.a();
        if (rfVarA.c() != null) {
            sSLSocket.setEnabledProtocols(rfVarA.d);
        }
        if (rfVarA.a() != null) {
            sSLSocket.setEnabledCipherSuites(rfVarA.c);
        }
        return rfVar;
    }
}
