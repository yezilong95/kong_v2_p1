package defpackage;

import java.net.ProxySelector;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class fa0 implements Cloneable {
    public static final List A = kq0.l(Protocol.HTTP_2, Protocol.HTTP_1_1);
    public static final List B = kq0.l(rf.e, rf.f);
    public final ba b;
    public final o70 c;
    public final List d;
    public final List e;
    public final vk f;
    public final boolean g;
    public final qm h;
    public final boolean i;
    public final boolean j;
    public final qm k;
    public final el l;
    public final ProxySelector m;
    public final qm n;
    public final SocketFactory o;
    public final SSLSocketFactory p;
    public final X509TrustManager q;
    public final List r;
    public final List s;
    public final da0 t;
    public final cb u;
    public final oy v;
    public final int w;
    public final int x;
    public final int y;
    public final d90 z;

    public fa0(ea0 ea0Var) throws NoSuchAlgorithmException, KeyStoreException {
        this.b = ea0Var.a;
        this.c = ea0Var.b;
        this.d = kq0.x(ea0Var.c);
        this.e = kq0.x(ea0Var.d);
        this.f = ea0Var.e;
        this.g = ea0Var.f;
        this.h = ea0Var.g;
        this.i = ea0Var.h;
        this.j = ea0Var.i;
        this.k = ea0Var.j;
        this.l = ea0Var.k;
        ProxySelector proxySelector = ProxySelector.getDefault();
        this.m = proxySelector == null ? aa0.a : proxySelector;
        this.n = ea0Var.l;
        this.o = ea0Var.m;
        List list = ea0Var.n;
        this.r = list;
        this.s = ea0Var.o;
        this.t = ea0Var.p;
        this.w = ea0Var.r;
        this.x = ea0Var.s;
        this.y = ea0Var.t;
        this.z = new d90(7);
        if (list == null || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((rf) it.next()).a) {
                    rb0 rb0Var = rb0.a;
                    X509TrustManager x509TrustManagerM = rb0.a.m();
                    this.q = x509TrustManagerM;
                    this.p = rb0.a.l(x509TrustManagerM);
                    oy oyVarB = rb0.a.b(x509TrustManagerM);
                    this.v = oyVarB;
                    cb cbVar = ea0Var.q;
                    this.u = oy.c(cbVar.b, oyVarB) ? cbVar : new cb(cbVar.a, oyVarB);
                }
            }
            this.p = null;
            this.v = null;
            this.q = null;
            this.u = cb.c;
        } else {
            this.p = null;
            this.v = null;
            this.q = null;
            this.u = cb.c;
        }
        List list2 = this.d;
        oy.h(list2, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (list2.contains(null)) {
            throw new IllegalStateException(("Null interceptor: " + list2).toString());
        }
        List list3 = this.e;
        oy.h(list3, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (list3.contains(null)) {
            throw new IllegalStateException(("Null network interceptor: " + list3).toString());
        }
        X509TrustManager x509TrustManager = this.q;
        oy oyVar = this.v;
        SSLSocketFactory sSLSocketFactory = this.p;
        List list4 = this.r;
        if (list4 == null || !list4.isEmpty()) {
            Iterator it2 = list4.iterator();
            while (it2.hasNext()) {
                if (((rf) it2.next()).a) {
                    if (sSLSocketFactory == null) {
                        throw new IllegalStateException("sslSocketFactory == null");
                    }
                    if (oyVar == null) {
                        throw new IllegalStateException("certificateChainCleaner == null");
                    }
                    if (x509TrustManager == null) {
                        throw new IllegalStateException("x509TrustManager == null");
                    }
                    return;
                }
            }
        }
        if (sSLSocketFactory != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (oyVar != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (x509TrustManager != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (!oy.c(this.u, cb.c)) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final Object clone() {
        return super.clone();
    }
}
