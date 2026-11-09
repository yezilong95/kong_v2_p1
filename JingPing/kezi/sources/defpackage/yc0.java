package defpackage;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Protocol;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ErrorCode;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class yc0 extends lw {
    public final zf0 b;
    public Socket c;
    public Socket d;
    public pv e;
    public Protocol f;
    public uw g;
    public sc0 h;
    public rc0 i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;
    public final ArrayList p;
    public long q;

    public yc0(zc0 zc0Var, zf0 zf0Var) {
        oy.j(zc0Var, "connectionPool");
        oy.j(zf0Var, "route");
        this.b = zf0Var;
        this.o = 1;
        this.p = new ArrayList();
        this.q = Long.MAX_VALUE;
    }

    public static void d(fa0 fa0Var, zf0 zf0Var, IOException iOException) {
        oy.j(fa0Var, "client");
        oy.j(zf0Var, "failedRoute");
        oy.j(iOException, "failure");
        if (zf0Var.b.type() != Proxy.Type.DIRECT) {
            p2 p2Var = zf0Var.a;
            p2Var.g.connectFailed(p2Var.h.g(), zf0Var.b.address(), iOException);
        }
        d90 d90Var = fa0Var.z;
        synchronized (d90Var) {
            ((LinkedHashSet) d90Var.b).add(zf0Var);
        }
    }

    @Override // defpackage.lw
    public final synchronized void a(uw uwVar, gi0 gi0Var) {
        oy.j(uwVar, "connection");
        oy.j(gi0Var, "settings");
        this.o = (gi0Var.a & 16) != 0 ? gi0Var.b[4] : Integer.MAX_VALUE;
    }

    @Override // defpackage.lw
    public final void b(bx bxVar) {
        bxVar.c(ErrorCode.REFUSED_STREAM, null);
    }

    public final void c(int i, int i2, int i3, boolean z, wc0 wc0Var) throws Throwable {
        zf0 zf0Var;
        oy.j(wc0Var, "call");
        if (this.f != null) {
            throw new IllegalStateException("already connected");
        }
        List list = this.b.a.j;
        sf sfVar = new sf(list);
        p2 p2Var = this.b.a;
        if (p2Var.c == null) {
            if (!list.contains(rf.f)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String str = this.b.a.h.d;
            rb0 rb0Var = rb0.a;
            if (!rb0.a.h(str)) {
                throw new RouteException(new UnknownServiceException(hk.j("CLEARTEXT communication to ", str, " not permitted by network security policy")));
            }
        } else if (p2Var.i.contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        RouteException routeException = null;
        do {
            try {
                zf0 zf0Var2 = this.b;
                if (zf0Var2.a.c != null && zf0Var2.b.type() == Proxy.Type.HTTP) {
                    f(i, i2, i3, wc0Var);
                    if (this.c == null) {
                    }
                    zf0Var = this.b;
                    if (zf0Var.a.c == null && zf0Var.b.type() == Proxy.Type.HTTP && this.c == null) {
                        throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                    }
                    this.q = System.nanoTime();
                    return;
                }
                e(i, i2, wc0Var);
                g(sfVar, wc0Var);
                oy.j(this.b.c, "inetSocketAddress");
                zf0Var = this.b;
                if (zf0Var.a.c == null) {
                }
                this.q = System.nanoTime();
                return;
            } catch (IOException e) {
                Socket socket = this.d;
                if (socket != null) {
                    kq0.e(socket);
                }
                Socket socket2 = this.c;
                if (socket2 != null) {
                    kq0.e(socket2);
                }
                this.d = null;
                this.c = null;
                this.h = null;
                this.i = null;
                this.e = null;
                this.f = null;
                this.g = null;
                this.o = 1;
                oy.j(this.b.c, "inetSocketAddress");
                if (routeException == null) {
                    routeException = new RouteException(e);
                } else {
                    routeException.addConnectException(e);
                }
                if (!z) {
                    throw routeException;
                }
                sfVar.d = true;
                if (!sfVar.c) {
                    throw routeException;
                }
                if (e instanceof ProtocolException) {
                    throw routeException;
                }
                if (e instanceof InterruptedIOException) {
                    throw routeException;
                }
                if ((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) {
                    throw routeException;
                }
                if (e instanceof SSLPeerUnverifiedException) {
                    throw routeException;
                }
            }
        } while (e instanceof SSLException);
        throw routeException;
    }

    public final void e(int i, int i2, wc0 wc0Var) throws IOException {
        Socket socketCreateSocket;
        zf0 zf0Var = this.b;
        Proxy proxy = zf0Var.b;
        p2 p2Var = zf0Var.a;
        Proxy.Type type = proxy.type();
        int i3 = type == null ? -1 : xc0.a[type.ordinal()];
        if (i3 == 1 || i3 == 2) {
            socketCreateSocket = p2Var.b.createSocket();
            oy.g(socketCreateSocket);
        } else {
            socketCreateSocket = new Socket(proxy);
        }
        this.c = socketCreateSocket;
        InetSocketAddress inetSocketAddress = this.b.c;
        oy.j(wc0Var, "call");
        oy.j(inetSocketAddress, "inetSocketAddress");
        socketCreateSocket.setSoTimeout(i2);
        try {
            rb0 rb0Var = rb0.a;
            rb0.a.e(socketCreateSocket, this.b.c, i);
            try {
                this.h = new sc0(za0.N(socketCreateSocket));
                this.i = new rc0(za0.M(socketCreateSocket));
            } catch (NullPointerException e) {
                if (oy.c(e.getMessage(), "throw with null exception")) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.b.c);
            connectException.initCause(e2);
            throw connectException;
        }
    }

    public final void f(int i, int i2, int i3, wc0 wc0Var) throws IOException {
        o2 o2Var = new o2();
        zf0 zf0Var = this.b;
        fx fxVar = zf0Var.a.h;
        oy.j(fxVar, "url");
        o2Var.b = fxVar;
        o2Var.l("CONNECT", null);
        p2 p2Var = zf0Var.a;
        o2Var.j("Host", kq0.w(p2Var.h, true));
        o2Var.j("Proxy-Connection", "Keep-Alive");
        o2Var.j("User-Agent", "okhttp/4.12.0");
        z4 z4VarA = o2Var.a();
        o70 o70Var = new o70(23);
        oy.j(Protocol.HTTP_1_1, "protocol");
        oy.f("Proxy-Authenticate");
        oy.k("OkHttp-Preemptive", "Proxy-Authenticate");
        o70Var.B("Proxy-Authenticate");
        o70Var.s("Proxy-Authenticate", "OkHttp-Preemptive");
        o70Var.t();
        p2Var.f.getClass();
        e(i, i2, wc0Var);
        String str = "CONNECT " + kq0.w((fx) z4VarA.b, true) + " HTTP/1.1";
        sc0 sc0Var = this.h;
        oy.g(sc0Var);
        rc0 rc0Var = this.i;
        oy.g(rc0Var);
        iw iwVar = new iw(null, this, sc0Var, rc0Var);
        jo0 jo0VarB = sc0Var.b.b();
        long j = i2;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        jo0VarB.g(j);
        rc0Var.b.b().g(i3);
        iwVar.k((tv) z4VarA.d, str);
        iwVar.d();
        ff0 ff0VarF = iwVar.f(false);
        oy.g(ff0VarF);
        ff0VarF.a = z4VarA;
        gf0 gf0VarA = ff0VarF.a();
        long jK = kq0.k(gf0VarA);
        if (jK != -1) {
            fw fwVarJ = iwVar.j(jK);
            kq0.u(fwVarJ, Integer.MAX_VALUE);
            fwVarJ.close();
        }
        int i4 = gf0VarA.e;
        if (i4 != 200) {
            if (i4 != 407) {
                throw new IOException(hk.g(i4, "Unexpected response code for CONNECT: "));
            }
            p2Var.f.getClass();
            throw new IOException("Failed to authenticate with proxy");
        }
        if (!sc0Var.c.a() || !rc0Var.c.a()) {
            throw new IOException("TLS tunnel buffered too many bytes!");
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void g(sf sfVar, wc0 wc0Var) throws Throwable {
        SSLSocket sSLSocket;
        Protocol protocolA;
        int i = 1;
        p2 p2Var = this.b.a;
        if (p2Var.c == null) {
            List list = p2Var.i;
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (!list.contains(protocol)) {
                this.d = this.c;
                this.f = Protocol.HTTP_1_1;
                return;
            } else {
                this.d = this.c;
                this.f = protocol;
                l();
                return;
            }
        }
        oy.j(wc0Var, "call");
        p2 p2Var2 = this.b.a;
        SSLSocketFactory sSLSocketFactory = p2Var2.c;
        SSLSocket sSLSocket2 = null;
        String strF = null;
        try {
            oy.g(sSLSocketFactory);
            Socket socket = this.c;
            fx fxVar = p2Var2.h;
            Socket socketCreateSocket = sSLSocketFactory.createSocket(socket, fxVar.d, fxVar.e, true);
            oy.h(socketCreateSocket, "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            sSLSocket = (SSLSocket) socketCreateSocket;
        } catch (Throwable th) {
            th = th;
        }
        try {
            rf rfVarA = sfVar.a(sSLSocket);
            if (rfVarA.b) {
                rb0 rb0Var = rb0.a;
                rb0.a.d(sSLSocket, p2Var2.h.d, p2Var2.i);
            }
            sSLSocket.startHandshake();
            SSLSession session = sSLSocket.getSession();
            oy.i(session, "sslSocketSession");
            pv pvVarQ = xi.q(session);
            HostnameVerifier hostnameVerifier = p2Var2.d;
            oy.g(hostnameVerifier);
            if (hostnameVerifier.verify(p2Var2.h.d, session)) {
                cb cbVar = p2Var2.e;
                oy.g(cbVar);
                this.e = new pv(pvVarQ.a, pvVarQ.b, pvVarQ.c, new bb(cbVar, pvVarQ, p2Var2, i));
                cbVar.a(p2Var2.h.d, new ju(3, this));
                if (rfVarA.b) {
                    rb0 rb0Var2 = rb0.a;
                    strF = rb0.a.f(sSLSocket);
                }
                this.d = sSLSocket;
                this.h = new sc0(za0.N(sSLSocket));
                this.i = new rc0(za0.M(sSLSocket));
                if (strF != null) {
                    Protocol.Companion.getClass();
                    protocolA = nc0.a(strF);
                } else {
                    protocolA = Protocol.HTTP_1_1;
                }
                this.f = protocolA;
                rb0 rb0Var3 = rb0.a;
                rb0.a.a(sSLSocket);
                if (this.f == Protocol.HTTP_2) {
                    l();
                    return;
                }
                return;
            }
            List listA = pvVarQ.a();
            if (listA.isEmpty()) {
                throw new SSLPeerUnverifiedException("Hostname " + p2Var2.h.d + " not verified (no certificates)");
            }
            Object obj = listA.get(0);
            oy.h(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            X509Certificate x509Certificate = (X509Certificate) obj;
            StringBuilder sb = new StringBuilder("\n              |Hostname ");
            sb.append(p2Var2.h.d);
            sb.append(" not verified:\n              |    certificate: ");
            cb cbVar2 = cb.c;
            sb.append(za0.J(x509Certificate));
            sb.append("\n              |    DN: ");
            sb.append(x509Certificate.getSubjectDN().getName());
            sb.append("\n              |    subjectAltNames: ");
            List listA2 = da0.a(x509Certificate, 7);
            List listA3 = da0.a(x509Certificate, 2);
            oy.j(listA2, "<this>");
            oy.j(listA3, "elements");
            ArrayList arrayList = new ArrayList(listA3.size() + listA2.size());
            arrayList.addAll(listA2);
            arrayList.addAll(listA3);
            sb.append(arrayList);
            sb.append("\n              ");
            throw new SSLPeerUnverifiedException(wk0.d0(sb.toString()));
        } catch (Throwable th2) {
            th = th2;
            sSLSocket2 = sSLSocket;
            if (sSLSocket2 != null) {
                rb0 rb0Var4 = rb0.a;
                rb0.a.a(sSLSocket2);
            }
            if (sSLSocket2 != null) {
                kq0.e(sSLSocket2);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(defpackage.p2 r9, java.util.ArrayList r10) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yc0.h(p2, java.util.ArrayList):boolean");
    }

    public final boolean i(boolean z) {
        long j;
        byte[] bArr = kq0.a;
        long jNanoTime = System.nanoTime();
        Socket socket = this.c;
        oy.g(socket);
        Socket socket2 = this.d;
        oy.g(socket2);
        oy.g(this.h);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        uw uwVar = this.g;
        if (uwVar != null) {
            synchronized (uwVar) {
                if (uwVar.g) {
                    return false;
                }
                if (uwVar.o < uwVar.n) {
                    if (jNanoTime >= uwVar.p) {
                        return false;
                    }
                }
                return true;
            }
        }
        synchronized (this) {
            j = jNanoTime - this.q;
        }
        if (j < 10000000000L || !z) {
            return true;
        }
        try {
            int soTimeout = socket2.getSoTimeout();
            try {
                socket2.setSoTimeout(1);
                return !r4.a();
            } finally {
                socket2.setSoTimeout(soTimeout);
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final to j(fa0 fa0Var, ad0 ad0Var) throws SocketException {
        oy.j(fa0Var, "client");
        Socket socket = this.d;
        oy.g(socket);
        sc0 sc0Var = this.h;
        oy.g(sc0Var);
        rc0 rc0Var = this.i;
        oy.g(rc0Var);
        uw uwVar = this.g;
        if (uwVar != null) {
            return new vw(fa0Var, this, ad0Var, uwVar);
        }
        int i = ad0Var.g;
        socket.setSoTimeout(i);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        sc0Var.b.b().g(i);
        rc0Var.b.b().g(ad0Var.h);
        return new iw(fa0Var, this, sc0Var, rc0Var);
    }

    public final synchronized void k() {
        this.j = true;
    }

    public final void l() throws SocketException {
        Socket socket = this.d;
        oy.g(socket);
        sc0 sc0Var = this.h;
        oy.g(sc0Var);
        rc0 rc0Var = this.i;
        oy.g(rc0Var);
        socket.setSoTimeout(0);
        an0 an0Var = an0.h;
        z4 z4Var = new z4(an0Var);
        String str = this.b.a.h.d;
        oy.j(str, "peerName");
        z4Var.c = socket;
        String str2 = kq0.g + ' ' + str;
        oy.j(str2, "<set-?>");
        z4Var.d = str2;
        z4Var.e = sc0Var;
        z4Var.f = rc0Var;
        z4Var.g = this;
        uw uwVar = new uw(z4Var);
        this.g = uwVar;
        gi0 gi0Var = uw.A;
        int i = 4;
        this.o = (gi0Var.a & 16) != 0 ? gi0Var.b[4] : Integer.MAX_VALUE;
        cx cxVar = uwVar.x;
        synchronized (cxVar) {
            try {
                if (cxVar.e) {
                    throw new IOException("closed");
                }
                Logger logger = cx.g;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(kq0.i(">> CONNECTION " + jw.a.hex(), new Object[0]));
                }
                rc0 rc0Var2 = cxVar.b;
                ByteString byteString = jw.a;
                rc0Var2.getClass();
                oy.j(byteString, "byteString");
                if (rc0Var2.d) {
                    throw new IllegalStateException("closed");
                }
                rc0Var2.c.L(byteString);
                rc0Var2.a();
                cxVar.b.flush();
            } finally {
            }
        }
        cx cxVar2 = uwVar.x;
        gi0 gi0Var2 = uwVar.q;
        synchronized (cxVar2) {
            try {
                oy.j(gi0Var2, "settings");
                if (cxVar2.e) {
                    throw new IOException("closed");
                }
                cxVar2.C(0, Integer.bitCount(gi0Var2.a) * 6, 4, 0);
                int i2 = 0;
                while (i2 < 10) {
                    boolean z = true;
                    if (((1 << i2) & gi0Var2.a) == 0) {
                        z = false;
                    }
                    if (z) {
                        int i3 = i2 != i ? i2 != 7 ? i2 : i : 3;
                        rc0 rc0Var3 = cxVar2.b;
                        if (rc0Var3.d) {
                            throw new IllegalStateException("closed");
                        }
                        p9 p9Var = rc0Var3.c;
                        th0 th0VarK = p9Var.K(2);
                        int i4 = th0VarK.c;
                        byte[] bArr = th0VarK.a;
                        bArr[i4] = (byte) ((i3 >>> 8) & 255);
                        bArr[i4 + 1] = (byte) (i3 & 255);
                        th0VarK.c = i4 + 2;
                        p9Var.c += 2;
                        rc0Var3.a();
                        cxVar2.b.C(gi0Var2.b[i2]);
                    }
                    i2++;
                    i = 4;
                }
                cxVar2.b.flush();
            } finally {
            }
        }
        if (uwVar.q.a() != 65535) {
            uwVar.x.H(r2 - 65535, 0);
        }
        an0Var.e().c(new rw(uwVar.d, uwVar.y, 2), 0L);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        zf0 zf0Var = this.b;
        sb.append(zf0Var.a.h.d);
        sb.append(':');
        sb.append(zf0Var.a.h.e);
        sb.append(", proxy=");
        sb.append(zf0Var.b);
        sb.append(" hostAddress=");
        sb.append(zf0Var.c);
        sb.append(" cipherSuite=");
        pv pvVar = this.e;
        if (pvVar == null || (obj = pvVar.b) == null) {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f);
        sb.append('}');
        return sb.toString();
    }
}
