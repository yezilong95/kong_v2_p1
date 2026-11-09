package defpackage;

import java.io.Closeable;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class gf0 implements Closeable {
    public final z4 b;
    public final Protocol c;
    public final String d;
    public final int e;
    public final pv f;
    public final tv g;
    public final if0 h;
    public final gf0 i;
    public final gf0 j;
    public final gf0 k;
    public final long l;
    public final long m;
    public final so n;

    public gf0(z4 z4Var, Protocol protocol, String str, int i, pv pvVar, tv tvVar, if0 if0Var, gf0 gf0Var, gf0 gf0Var2, gf0 gf0Var3, long j, long j2, so soVar) {
        oy.j(z4Var, "request");
        oy.j(protocol, "protocol");
        oy.j(str, "message");
        this.b = z4Var;
        this.c = protocol;
        this.d = str;
        this.e = i;
        this.f = pvVar;
        this.g = tvVar;
        this.h = if0Var;
        this.i = gf0Var;
        this.j = gf0Var2;
        this.k = gf0Var3;
        this.l = j;
        this.m = j2;
        this.n = soVar;
    }

    public static String a(gf0 gf0Var, String str) {
        gf0Var.getClass();
        String strA = gf0Var.g.a(str);
        if (strA == null) {
            return null;
        }
        return strA;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if0 if0Var = this.h;
        if (if0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        if0Var.close();
    }

    public final ff0 t() {
        ff0 ff0Var = new ff0();
        ff0Var.a = this.b;
        ff0Var.b = this.c;
        ff0Var.c = this.e;
        ff0Var.d = this.d;
        ff0Var.e = this.f;
        ff0Var.f = this.g.c();
        ff0Var.g = this.h;
        ff0Var.h = this.i;
        ff0Var.i = this.j;
        ff0Var.j = this.k;
        ff0Var.k = this.l;
        ff0Var.l = this.m;
        ff0Var.m = this.n;
        return ff0Var;
    }

    public final String toString() {
        return "Response{protocol=" + this.c + ", code=" + this.e + ", message=" + this.d + ", url=" + ((fx) this.b.b) + '}';
    }
}
