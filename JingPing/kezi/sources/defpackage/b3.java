package defpackage;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class b3 extends oy {
    public final X509TrustManager l;
    public final X509TrustManagerExtensions m;

    public b3(X509TrustManager x509TrustManager, X509TrustManagerExtensions x509TrustManagerExtensions) {
        this.l = x509TrustManager;
        this.m = x509TrustManagerExtensions;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof b3) && ((b3) obj).l == this.l;
    }

    public final int hashCode() {
        return System.identityHashCode(this.l);
    }

    @Override // defpackage.oy
    public final List l(List list, String str) throws SSLPeerUnverifiedException {
        oy.j(list, "chain");
        oy.j(str, "hostname");
        try {
            List<X509Certificate> listCheckServerTrusted = this.m.checkServerTrusted((X509Certificate[]) list.toArray(new X509Certificate[0]), "RSA", str);
            oy.i(listCheckServerTrusted, "x509TrustManagerExtensio…ficates, \"RSA\", hostname)");
            return listCheckServerTrusted;
        } catch (CertificateException e) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
            sSLPeerUnverifiedException.initCause(e);
            throw sSLPeerUnverifiedException;
        }
    }
}
