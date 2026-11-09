package defpackage;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class zf0 {
    public final p2 a;
    public final Proxy b;
    public final InetSocketAddress c;

    public zf0(p2 p2Var, Proxy proxy, InetSocketAddress inetSocketAddress) {
        oy.j(inetSocketAddress, "socketAddress");
        this.a = p2Var;
        this.b = proxy;
        this.c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zf0)) {
            return false;
        }
        zf0 zf0Var = (zf0) obj;
        return oy.c(zf0Var.a, this.a) && oy.c(zf0Var.b, this.b) && oy.c(zf0Var.c, this.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Route{" + this.c + '}';
    }
}
