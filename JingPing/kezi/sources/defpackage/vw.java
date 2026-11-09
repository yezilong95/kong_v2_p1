package defpackage;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class vw implements to {
    public static final List g = kq0.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");
    public static final List h = kq0.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");
    public final yc0 a;
    public final ad0 b;
    public final uw c;
    public volatile bx d;
    public final Protocol e;
    public volatile boolean f;

    public vw(fa0 fa0Var, yc0 yc0Var, ad0 ad0Var, uw uwVar) {
        oy.j(fa0Var, "client");
        oy.j(yc0Var, "connection");
        oy.j(uwVar, "http2Connection");
        this.a = yc0Var;
        this.b = ad0Var;
        this.c = uwVar;
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.e = fa0Var.s.contains(protocol) ? protocol : Protocol.HTTP_2;
    }

    @Override // defpackage.to
    public final hj0 a(z4 z4Var, long j) {
        bx bxVar = this.d;
        oy.g(bxVar);
        return bxVar.f();
    }

    @Override // defpackage.to
    public final void b(z4 z4Var) throws IOException {
        int i;
        bx bxVar;
        if (this.d != null) {
            return;
        }
        boolean z = true;
        boolean z2 = ((d5) z4Var.e) != null;
        tv tvVar = (tv) z4Var.d;
        ArrayList arrayList = new ArrayList(tvVar.size() + 4);
        arrayList.add(new sv((String) z4Var.c, sv.f));
        ByteString byteString = sv.g;
        fx fxVar = (fx) z4Var.b;
        oy.j(fxVar, "url");
        String strB = fxVar.b();
        String strD = fxVar.d();
        if (strD != null) {
            strB = strB + '?' + strD;
        }
        arrayList.add(new sv(strB, byteString));
        String strA = ((tv) z4Var.d).a("Host");
        if (strA != null) {
            arrayList.add(new sv(strA, sv.i));
        }
        arrayList.add(new sv(fxVar.a, sv.h));
        int size = tvVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            String strB2 = tvVar.b(i2);
            Locale locale = Locale.US;
            oy.i(locale, "US");
            String lowerCase = strB2.toLowerCase(locale);
            oy.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (!g.contains(lowerCase) || (lowerCase.equals("te") && oy.c(tvVar.d(i2), "trailers"))) {
                arrayList.add(new sv(lowerCase, tvVar.d(i2)));
            }
        }
        uw uwVar = this.c;
        uwVar.getClass();
        boolean z3 = !z2;
        synchronized (uwVar.x) {
            synchronized (uwVar) {
                try {
                    if (uwVar.f > 1073741823) {
                        uwVar.E(ErrorCode.REFUSED_STREAM);
                    }
                    if (uwVar.g) {
                        throw new ConnectionShutdownException();
                    }
                    i = uwVar.f;
                    uwVar.f = i + 2;
                    bxVar = new bx(i, uwVar, z3, false, null);
                    if (z2 && uwVar.u < uwVar.v && bxVar.e < bxVar.f) {
                        z = false;
                    }
                    if (bxVar.h()) {
                        uwVar.c.put(Integer.valueOf(i), bxVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            uwVar.x.E(z3, i, arrayList);
        }
        if (z) {
            uwVar.x.flush();
        }
        this.d = bxVar;
        if (this.f) {
            bx bxVar2 = this.d;
            oy.g(bxVar2);
            bxVar2.e(ErrorCode.CANCEL);
            throw new IOException("Canceled");
        }
        bx bxVar3 = this.d;
        oy.g(bxVar3);
        ax axVar = bxVar3.k;
        long j = this.b.g;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        axVar.g(j);
        bx bxVar4 = this.d;
        oy.g(bxVar4);
        bxVar4.l.g(this.b.h);
    }

    @Override // defpackage.to
    public final uj0 c(gf0 gf0Var) {
        bx bxVar = this.d;
        oy.g(bxVar);
        return bxVar.i;
    }

    @Override // defpackage.to
    public final void cancel() {
        this.f = true;
        bx bxVar = this.d;
        if (bxVar != null) {
            bxVar.e(ErrorCode.CANCEL);
        }
    }

    @Override // defpackage.to
    public final void d() throws SocketTimeoutException {
        bx bxVar = this.d;
        oy.g(bxVar);
        bxVar.f().close();
    }

    @Override // defpackage.to
    public final void e() {
        this.c.flush();
    }

    @Override // defpackage.to
    public final ff0 f(boolean z) throws IOException {
        tv tvVar;
        bx bxVar = this.d;
        if (bxVar == null) {
            throw new IOException("stream wasn't created");
        }
        synchronized (bxVar) {
            bxVar.k.h();
            while (bxVar.g.isEmpty() && bxVar.m == null) {
                try {
                    bxVar.k();
                } catch (Throwable th) {
                    bxVar.k.k();
                    throw th;
                }
            }
            bxVar.k.k();
            if (bxVar.g.isEmpty()) {
                IOException iOException = bxVar.n;
                if (iOException != null) {
                    throw iOException;
                }
                ErrorCode errorCode = bxVar.m;
                oy.g(errorCode);
                throw new StreamResetException(errorCode);
            }
            Object objRemoveFirst = bxVar.g.removeFirst();
            oy.i(objRemoveFirst, "headersQueue.removeFirst()");
            tvVar = (tv) objRemoveFirst;
        }
        Protocol protocol = this.e;
        oy.j(protocol, "protocol");
        ArrayList arrayList = new ArrayList(20);
        int size = tvVar.size();
        d5 d5VarI = null;
        for (int i = 0; i < size; i++) {
            String strB = tvVar.b(i);
            String strD = tvVar.d(i);
            if (oy.c(strB, ":status")) {
                d5VarI = oy.I("HTTP/1.1 " + strD);
            } else if (!h.contains(strB)) {
                oy.j(strB, "name");
                oy.j(strD, "value");
                arrayList.add(strB);
                arrayList.add(vk0.B0(strD).toString());
            }
        }
        if (d5VarI == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        ff0 ff0Var = new ff0();
        ff0Var.b = protocol;
        ff0Var.c = d5VarI.b;
        ff0Var.d = (String) d5VarI.d;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        o70 o70Var = new o70(23);
        ArrayList arrayList2 = (ArrayList) o70Var.c;
        oy.j(arrayList2, "<this>");
        oy.j(strArr, "elements");
        arrayList2.addAll(g7.W(strArr));
        ff0Var.f = o70Var;
        if (z && ff0Var.c == 100) {
            return null;
        }
        return ff0Var;
    }

    @Override // defpackage.to
    public final long g(gf0 gf0Var) {
        if (dx.a(gf0Var)) {
            return kq0.k(gf0Var);
        }
        return 0L;
    }

    @Override // defpackage.to
    public final yc0 h() {
        return this.a;
    }
}
