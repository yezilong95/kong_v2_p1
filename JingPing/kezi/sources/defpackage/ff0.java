package defpackage;

import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class ff0 {
    public z4 a;
    public Protocol b;
    public String d;
    public pv e;
    public if0 g;
    public gf0 h;
    public gf0 i;
    public gf0 j;
    public long k;
    public long l;
    public so m;
    public int c = -1;
    public o70 f = new o70(23);

    public static void b(gf0 gf0Var, String str) {
        if (gf0Var != null) {
            if (gf0Var.h != null) {
                throw new IllegalArgumentException(str.concat(".body != null").toString());
            }
            if (gf0Var.i != null) {
                throw new IllegalArgumentException(str.concat(".networkResponse != null").toString());
            }
            if (gf0Var.j != null) {
                throw new IllegalArgumentException(str.concat(".cacheResponse != null").toString());
            }
            if (gf0Var.k != null) {
                throw new IllegalArgumentException(str.concat(".priorResponse != null").toString());
            }
        }
    }

    public final gf0 a() {
        int i = this.c;
        if (i < 0) {
            throw new IllegalStateException(("code < 0: " + this.c).toString());
        }
        z4 z4Var = this.a;
        if (z4Var == null) {
            throw new IllegalStateException("request == null");
        }
        Protocol protocol = this.b;
        if (protocol == null) {
            throw new IllegalStateException("protocol == null");
        }
        String str = this.d;
        if (str != null) {
            return new gf0(z4Var, protocol, str, i, this.e, this.f.t(), this.g, this.h, this.i, this.j, this.k, this.l, this.m);
        }
        throw new IllegalStateException("message == null");
    }
}
