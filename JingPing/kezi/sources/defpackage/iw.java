package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class iw implements to {
    public int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;
    public Object f;
    public Iterable g;

    public iw(p2 p2Var, d90 d90Var, wc0 wc0Var) {
        List listL;
        oy.j(d90Var, "routeDatabase");
        oy.j(wc0Var, "call");
        this.b = p2Var;
        this.c = d90Var;
        this.d = wc0Var;
        EmptyList emptyList = EmptyList.INSTANCE;
        this.e = emptyList;
        this.f = emptyList;
        this.g = new ArrayList();
        fx fxVar = p2Var.h;
        oy.j(fxVar, "url");
        URI uriG = fxVar.g();
        if (uriG.getHost() == null) {
            listL = kq0.l(Proxy.NO_PROXY);
        } else {
            List<Proxy> listSelect = p2Var.g.select(uriG);
            listL = (listSelect == null || listSelect.isEmpty()) ? kq0.l(Proxy.NO_PROXY) : kq0.x(listSelect);
        }
        this.e = listL;
        this.a = 0;
    }

    @Override // defpackage.to
    public hj0 a(z4 z4Var, long j) {
        if ("chunked".equalsIgnoreCase(((tv) z4Var.d).a("Transfer-Encoding"))) {
            if (this.a == 1) {
                this.a = 2;
                return new dw(this);
            }
            throw new IllegalStateException(("state: " + this.a).toString());
        }
        if (j == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.a == 1) {
            this.a = 2;
            return new gw(this);
        }
        throw new IllegalStateException(("state: " + this.a).toString());
    }

    @Override // defpackage.to
    public void b(z4 z4Var) {
        Proxy.Type type = ((yc0) this.c).b.b.type();
        oy.i(type, "connection.route().proxy.type()");
        StringBuilder sb = new StringBuilder();
        sb.append((String) z4Var.c);
        sb.append(' ');
        fx fxVar = (fx) z4Var.b;
        if (fxVar.i || type != Proxy.Type.HTTP) {
            String strB = fxVar.b();
            String strD = fxVar.d();
            if (strD != null) {
                strB = strB + '?' + strD;
            }
            sb.append(strB);
        } else {
            sb.append(fxVar);
        }
        sb.append(" HTTP/1.1");
        String string = sb.toString();
        oy.i(string, "StringBuilder().apply(builderAction).toString()");
        k((tv) z4Var.d, string);
    }

    @Override // defpackage.to
    public uj0 c(gf0 gf0Var) {
        if (!dx.a(gf0Var)) {
            return j(0L);
        }
        if ("chunked".equalsIgnoreCase(gf0.a(gf0Var, "Transfer-Encoding"))) {
            fx fxVar = (fx) gf0Var.b.b;
            if (this.a == 4) {
                this.a = 5;
                return new ew(this, fxVar);
            }
            throw new IllegalStateException(("state: " + this.a).toString());
        }
        long jK = kq0.k(gf0Var);
        if (jK != -1) {
            return j(jK);
        }
        if (this.a == 4) {
            this.a = 5;
            ((yc0) this.c).k();
            return new hw(this);
        }
        throw new IllegalStateException(("state: " + this.a).toString());
    }

    @Override // defpackage.to
    public void cancel() {
        Socket socket = ((yc0) this.c).c;
        if (socket != null) {
            kq0.e(socket);
        }
    }

    @Override // defpackage.to
    public void d() {
        ((rc0) this.e).flush();
    }

    @Override // defpackage.to
    public void e() {
        ((rc0) this.e).flush();
    }

    @Override // defpackage.to
    public ff0 f(boolean z) {
        fc fcVar = (fc) this.f;
        int i = this.a;
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalStateException(("state: " + this.a).toString());
        }
        try {
            String strI = ((sc0) fcVar.c).I(fcVar.b);
            fcVar.b -= (long) strI.length();
            d5 d5VarI = oy.I(strI);
            int i2 = d5VarI.b;
            ff0 ff0Var = new ff0();
            Protocol protocol = (Protocol) d5VarI.c;
            oy.j(protocol, "protocol");
            ff0Var.b = protocol;
            ff0Var.c = i2;
            ff0Var.d = (String) d5VarI.d;
            ff0Var.f = fcVar.f().c();
            if (z && i2 == 100) {
                return null;
            }
            if (i2 == 100) {
                this.a = 3;
                return ff0Var;
            }
            if (102 > i2 || i2 >= 200) {
                this.a = 4;
                return ff0Var;
            }
            this.a = 3;
            return ff0Var;
        } catch (EOFException e) {
            throw new IOException("unexpected end of stream on ".concat(((yc0) this.c).b.a.h.f()), e);
        }
    }

    @Override // defpackage.to
    public long g(gf0 gf0Var) {
        if (!dx.a(gf0Var)) {
            return 0L;
        }
        if ("chunked".equalsIgnoreCase(gf0.a(gf0Var, "Transfer-Encoding"))) {
            return -1L;
        }
        return kq0.k(gf0Var);
    }

    @Override // defpackage.to
    public yc0 h() {
        return (yc0) this.c;
    }

    public boolean i() {
        return this.a < ((List) this.e).size() || !((ArrayList) this.g).isEmpty();
    }

    public fw j(long j) {
        if (this.a == 4) {
            this.a = 5;
            return new fw(this, j);
        }
        throw new IllegalStateException(("state: " + this.a).toString());
    }

    public void k(tv tvVar, String str) {
        oy.j(str, "requestLine");
        if (this.a != 0) {
            throw new IllegalStateException(("state: " + this.a).toString());
        }
        rc0 rc0Var = (rc0) this.e;
        rc0Var.u(str);
        rc0Var.u("\r\n");
        int size = tvVar.size();
        for (int i = 0; i < size; i++) {
            rc0Var.u(tvVar.b(i));
            rc0Var.u(": ");
            rc0Var.u(tvVar.d(i));
            rc0Var.u("\r\n");
        }
        rc0Var.u("\r\n");
        this.a = 1;
    }

    public iw(fa0 fa0Var, yc0 yc0Var, sc0 sc0Var, rc0 rc0Var) {
        oy.j(yc0Var, "connection");
        oy.j(sc0Var, "source");
        oy.j(rc0Var, "sink");
        this.b = fa0Var;
        this.c = yc0Var;
        this.d = sc0Var;
        this.e = rc0Var;
        this.f = new fc(sc0Var);
    }
}
