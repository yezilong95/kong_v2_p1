package defpackage;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class z2 extends rb0 {
    public static final boolean d;
    public final ArrayList c;

    static {
        d = yo.l() && Build.VERSION.SDK_INT >= 29;
    }

    public z2() {
        ArrayList arrayListD0 = g7.d0(new oj0[]{(!yo.l() || Build.VERSION.SDK_INT < 29) ? null : new a3(), new ak(i3.f), new ak(xf.a), new ak(n9.a)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListD0) {
            if (((oj0) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.c = arrayList;
    }

    @Override // defpackage.rb0
    public final oy b(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        b3 b3Var = x509TrustManagerExtensions != null ? new b3(x509TrustManager, x509TrustManagerExtensions) : null;
        return b3Var != null ? b3Var : new j8(c(x509TrustManager));
    }

    @Override // defpackage.rb0
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object next;
        oy.j(list, "protocols");
        Iterator it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((oj0) next).b(sSLSocket)) {
                    break;
                }
            }
        }
        oj0 oj0Var = (oj0) next;
        if (oj0Var != null) {
            oj0Var.d(sSLSocket, str, list);
        }
    }

    @Override // defpackage.rb0
    public final String f(SSLSocket sSLSocket) {
        Object next;
        Iterator it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((oj0) next).b(sSLSocket)) {
                break;
            }
        }
        oj0 oj0Var = (oj0) next;
        if (oj0Var != null) {
            return oj0Var.a(sSLSocket);
        }
        return null;
    }

    @Override // defpackage.rb0
    public final boolean h(String str) {
        oy.j(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}
