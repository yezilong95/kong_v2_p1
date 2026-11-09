package defpackage;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class h3 extends rb0 {
    public static final boolean e;
    public final ArrayList c;
    public final vd d;

    static {
        boolean z = false;
        if (yo.l() && Build.VERSION.SDK_INT < 30) {
            z = true;
        }
        e = z;
    }

    public h3() throws NoSuchMethodException {
        kk0 kk0Var;
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketImpl"));
            Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketFactoryImpl"));
            Class.forName("com.android.org.conscrypt".concat(".SSLParametersImpl"));
            kk0Var = new kk0(cls);
        } catch (Exception e2) {
            rb0.a.getClass();
            rb0.i(5, "unable to load android socket classes", e2);
            kk0Var = null;
        }
        ArrayList arrayListD0 = g7.d0(new oj0[]{kk0Var, new ak(i3.f), new ak(xf.a), new ak(n9.a)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListD0) {
            if (((oj0) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.c = arrayList;
        try {
            Class<?> cls2 = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls2.getMethod("get", new Class[0]);
            method2 = cls2.getMethod("open", String.class);
            method = cls2.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        this.d = new vd(method3, method2, method);
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
    public final op0 c(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new g3(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.c(x509TrustManager);
        }
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
    public final void e(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        oy.j(inetSocketAddress, "address");
        try {
            socket.connect(inetSocketAddress, i);
        } catch (ClassCastException e2) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e2;
            }
            throw new IOException("Exception in connect", e2);
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
    public final Object g() {
        vd vdVar = this.d;
        vdVar.getClass();
        Method method = vdVar.a;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(null, new Object[0]);
                Method method2 = vdVar.b;
                oy.g(method2);
                method2.invoke(objInvoke, "response.body().close()");
                return objInvoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // defpackage.rb0
    public final boolean h(String str) {
        oy.j(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    @Override // defpackage.rb0
    public final void j(Object obj, String str) {
        oy.j(str, "message");
        vd vdVar = this.d;
        vdVar.getClass();
        if (obj != null) {
            try {
                Method method = vdVar.c;
                oy.g(method);
                method.invoke(obj, new Object[0]);
                return;
            } catch (Exception unused) {
            }
        }
        rb0.i(5, str, null);
    }
}
