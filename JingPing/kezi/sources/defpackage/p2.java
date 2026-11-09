package defpackage;

import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public final class p2 {
    public final el a;
    public final SocketFactory b;
    public final SSLSocketFactory c;
    public final HostnameVerifier d;
    public final cb e;
    public final qm f;
    public final ProxySelector g;
    public final fx h;
    public final List i;
    public final List j;

    public p2(String str, int i, el elVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, cb cbVar, qm qmVar, List list, List list2, ProxySelector proxySelector) {
        oy.j(str, "uriHost");
        oy.j(elVar, "dns");
        oy.j(socketFactory, "socketFactory");
        oy.j(qmVar, "proxyAuthenticator");
        oy.j(list, "protocols");
        oy.j(list2, "connectionSpecs");
        oy.j(proxySelector, "proxySelector");
        this.a = elVar;
        this.b = socketFactory;
        this.c = sSLSocketFactory;
        this.d = hostnameVerifier;
        this.e = cbVar;
        this.f = qmVar;
        this.g = proxySelector;
        ex exVar = new ex();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            exVar.a = "http";
        } else {
            if (!str2.equalsIgnoreCase("https")) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            exVar.a = "https";
        }
        String strP = za0.P(yo.q(str, 0, 0, 7));
        if (strP == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        exVar.d = strP;
        if (1 > i || i >= 65536) {
            throw new IllegalArgumentException(hk.g(i, "unexpected port: ").toString());
        }
        exVar.e = i;
        this.h = exVar.a();
        this.i = kq0.x(list);
        this.j = kq0.x(list2);
    }

    public final boolean a(p2 p2Var) {
        oy.j(p2Var, "that");
        return oy.c(this.a, p2Var.a) && oy.c(this.f, p2Var.f) && oy.c(this.i, p2Var.i) && oy.c(this.j, p2Var.j) && oy.c(this.g, p2Var.g) && oy.c(this.c, p2Var.c) && oy.c(this.d, p2Var.d) && oy.c(this.e, p2Var.e) && this.h.e == p2Var.h.e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p2)) {
            return false;
        }
        p2 p2Var = (p2) obj;
        return oy.c(this.h, p2Var.h) && a(p2Var);
    }

    public final int hashCode() {
        return Objects.hashCode(this.e) + ((Objects.hashCode(this.d) + ((Objects.hashCode(this.c) + ((this.g.hashCode() + ((this.j.hashCode() + ((this.i.hashCode() + ((this.f.hashCode() + ((this.a.hashCode() + ((this.h.h.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 961)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        fx fxVar = this.h;
        sb.append(fxVar.d);
        sb.append(':');
        sb.append(fxVar.e);
        sb.append(", ");
        sb.append("proxySelector=" + this.g);
        sb.append('}');
        return sb.toString();
    }
}
