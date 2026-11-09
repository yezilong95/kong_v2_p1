package defpackage;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocketFactory;
import kotlin.collections.EmptyList;
import kotlin.text.Regex;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;

/* JADX INFO: loaded from: classes.dex */
public final class o9 implements ny {
    public final /* synthetic */ int a = 1;
    public final Object b;

    public o9(qm qmVar) {
        oy.j(qmVar, "cookieJar");
        this.b = qmVar;
    }

    public static int d(gf0 gf0Var, int i) {
        String strA = gf0.a(gf0Var, "Retry-After");
        if (strA == null) {
            return i;
        }
        if (!new Regex("\\d+").matches(strA)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strA);
        oy.i(numValueOf, "valueOf(header)");
        return numValueOf.intValue();
    }

    @Override // defpackage.ny
    public final gf0 a(ad0 ad0Var) {
        boolean z;
        if0 if0Var;
        SSLSocketFactory sSLSocketFactory;
        da0 da0Var;
        cb cbVar;
        switch (this.a) {
            case 0:
                z4 z4Var = ad0Var.e;
                o2 o2VarE = z4Var.e();
                d5 d5Var = (d5) z4Var.e;
                if (d5Var != null) {
                    c60 c60Var = (c60) d5Var.c;
                    if (c60Var != null) {
                        o2VarE.j("Content-Type", c60Var.a);
                    }
                    long j = d5Var.b;
                    if (j != -1) {
                        o2VarE.j("Content-Length", String.valueOf(j));
                        ((o70) o2VarE.d).B("Transfer-Encoding");
                    } else {
                        o2VarE.j("Transfer-Encoding", "chunked");
                        ((o70) o2VarE.d).B("Content-Length");
                    }
                }
                tv tvVar = (tv) z4Var.d;
                String strA = tvVar.a("Host");
                int i = 0;
                fx fxVar = (fx) z4Var.b;
                if (strA == null) {
                    o2VarE.j("Host", kq0.w(fxVar, false));
                }
                if (tvVar.a("Connection") == null) {
                    o2VarE.j("Connection", "Keep-Alive");
                }
                if (tvVar.a("Accept-Encoding") == null && tvVar.a("Range") == null) {
                    o2VarE.j("Accept-Encoding", "gzip");
                    z = true;
                } else {
                    z = false;
                }
                qm qmVar = (qm) this.b;
                qmVar.getClass();
                oy.j(fxVar, "url");
                EmptyList emptyList = EmptyList.INSTANCE;
                if (!emptyList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Object obj : emptyList) {
                        int i2 = i + 1;
                        if (i < 0) {
                            be.Y();
                            throw null;
                        }
                        hh hhVar = (hh) obj;
                        if (i > 0) {
                            sb.append("; ");
                        }
                        sb.append(hhVar.a);
                        sb.append('=');
                        sb.append(hhVar.b);
                        i = i2;
                    }
                    String string = sb.toString();
                    oy.i(string, "StringBuilder().apply(builderAction).toString()");
                    o2VarE.j("Cookie", string);
                }
                if (tvVar.a("User-Agent") == null) {
                    o2VarE.j("User-Agent", "okhttp/4.12.0");
                }
                gf0 gf0VarB = ad0Var.b(o2VarE.a());
                tv tvVar2 = gf0VarB.g;
                dx.b(qmVar, fxVar, tvVar2);
                ff0 ff0VarT = gf0VarB.t();
                ff0VarT.a = z4Var;
                if (z && "gzip".equalsIgnoreCase(gf0.a(gf0VarB, "Content-Encoding")) && dx.a(gf0VarB) && (if0Var = gf0VarB.h) != null) {
                    mv mvVar = new mv(if0Var.C());
                    o70 o70VarC = tvVar2.c();
                    o70VarC.B("Content-Encoding");
                    o70VarC.B("Content-Length");
                    ff0VarT.f = o70VarC.t().c();
                    ff0VarT.g = new bd0(gf0.a(gf0VarB, "Content-Type"), -1L, new sc0(mvVar));
                }
                return ff0VarT.a();
            default:
                z4 z4Var2 = ad0Var.e;
                wc0 wc0Var = ad0Var.a;
                List list = EmptyList.INSTANCE;
                gf0 gf0Var = null;
                int i3 = 0;
                z4 z4VarB = z4Var2;
                while (true) {
                    boolean z2 = true;
                    while (true) {
                        wc0Var.getClass();
                        if (wc0Var.j != null) {
                            throw new IllegalStateException("Check failed.");
                        }
                        synchronized (wc0Var) {
                            try {
                                if (wc0Var.l) {
                                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
                                }
                                if (wc0Var.k) {
                                    throw new IllegalStateException("Check failed.");
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (z2) {
                            zc0 zc0Var = wc0Var.d;
                            fx fxVar2 = (fx) z4VarB.b;
                            boolean z3 = fxVar2.i;
                            fa0 fa0Var = wc0Var.b;
                            if (z3) {
                                SSLSocketFactory sSLSocketFactory2 = fa0Var.p;
                                if (sSLSocketFactory2 == null) {
                                    throw new IllegalStateException("CLEARTEXT-only client");
                                }
                                da0 da0Var2 = fa0Var.t;
                                cbVar = fa0Var.u;
                                sSLSocketFactory = sSLSocketFactory2;
                                da0Var = da0Var2;
                            } else {
                                sSLSocketFactory = null;
                                da0Var = null;
                                cbVar = null;
                            }
                            wc0Var.h = new uo(zc0Var, new p2(fxVar2.d, fxVar2.e, fa0Var.l, fa0Var.o, sSLSocketFactory, da0Var, cbVar, fa0Var.n, fa0Var.s, fa0Var.r, fa0Var.m), wc0Var);
                        }
                        try {
                            if (wc0Var.n) {
                                throw new IOException("Canceled");
                            }
                            try {
                                gf0 gf0VarB2 = ad0Var.b(z4VarB);
                                if (gf0Var != null) {
                                    ff0 ff0VarT2 = gf0VarB2.t();
                                    ff0 ff0VarT3 = gf0Var.t();
                                    ff0VarT3.g = null;
                                    gf0 gf0VarA = ff0VarT3.a();
                                    if (gf0VarA.h != null) {
                                        throw new IllegalArgumentException("priorResponse.body != null");
                                    }
                                    ff0VarT2.j = gf0VarA;
                                    gf0VarB2 = ff0VarT2.a();
                                }
                                gf0Var = gf0VarB2;
                                z4VarB = b(gf0Var, wc0Var.j);
                                if (z4VarB == null) {
                                    wc0Var.f(false);
                                    return gf0Var;
                                }
                                if0 if0Var2 = gf0Var.h;
                                if (if0Var2 != null) {
                                    kq0.d(if0Var2);
                                }
                                i3++;
                                if (i3 > 20) {
                                    throw new ProtocolException("Too many follow-up requests: " + i3);
                                }
                                wc0Var.f(true);
                            } catch (IOException e) {
                                if (!c(e, wc0Var, z4VarB, !(e instanceof ConnectionShutdownException))) {
                                    oy.j(list, "suppressed");
                                    Iterator it = list.iterator();
                                    while (it.hasNext()) {
                                        nv0.c(e, (Exception) it.next());
                                    }
                                    throw e;
                                }
                                oy.j(list, "<this>");
                                ArrayList arrayList = new ArrayList(list.size() + 1);
                                arrayList.addAll(list);
                                arrayList.add(e);
                                wc0Var.f(true);
                                list = arrayList;
                                z2 = false;
                            } catch (RouteException e2) {
                                if (!c(e2.getLastConnectException(), wc0Var, z4VarB, false)) {
                                    IOException firstConnectException = e2.getFirstConnectException();
                                    oy.j(firstConnectException, "<this>");
                                    oy.j(list, "suppressed");
                                    Iterator it2 = list.iterator();
                                    while (it2.hasNext()) {
                                        nv0.c(firstConnectException, (Exception) it2.next());
                                    }
                                    throw firstConnectException;
                                }
                                IOException firstConnectException2 = e2.getFirstConnectException();
                                oy.j(list, "<this>");
                                ArrayList arrayList2 = new ArrayList(list.size() + 1);
                                arrayList2.addAll(list);
                                arrayList2.add(firstConnectException2);
                                wc0Var.f(true);
                                list = arrayList2;
                                z2 = false;
                            }
                        } catch (Throwable th2) {
                            wc0Var.f(true);
                            throw th2;
                        }
                    }
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public defpackage.z4 b(defpackage.gf0 r11, defpackage.so r12) throws java.net.ProtocolException {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o9.b(gf0, so):z4");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(java.io.IOException r4, defpackage.wc0 r5, defpackage.z4 r6, boolean r7) {
        /*
            r3 = this;
            java.lang.Object r6 = r3.b
            fa0 r6 = (defpackage.fa0) r6
            boolean r6 = r6.g
            r0 = 0
            if (r6 != 0) goto Lb
            goto L9d
        Lb:
            if (r7 == 0) goto L12
            boolean r6 = r4 instanceof java.io.FileNotFoundException
            if (r6 == 0) goto L12
            return r0
        L12:
            boolean r6 = r4 instanceof java.net.ProtocolException
            if (r6 == 0) goto L17
            return r0
        L17:
            boolean r6 = r4 instanceof java.io.InterruptedIOException
            if (r6 == 0) goto L22
            boolean r4 = r4 instanceof java.net.SocketTimeoutException
            if (r4 == 0) goto L9d
            if (r7 != 0) goto L9d
            goto L35
        L22:
            boolean r6 = r4 instanceof javax.net.ssl.SSLHandshakeException
            if (r6 == 0) goto L30
            java.lang.Throwable r6 = r4.getCause()
            boolean r6 = r6 instanceof java.security.cert.CertificateException
            if (r6 == 0) goto L30
            goto L9d
        L30:
            boolean r4 = r4 instanceof javax.net.ssl.SSLPeerUnverifiedException
            if (r4 == 0) goto L35
            return r0
        L35:
            uo r4 = r5.h
            defpackage.oy.g(r4)
            int r5 = r4.f
            r6 = 1
            if (r5 != 0) goto L49
            int r7 = r4.g
            if (r7 != 0) goto L49
            int r7 = r4.h
            if (r7 != 0) goto L49
            r4 = r0
            goto L9b
        L49:
            zf0 r7 = r4.i
            if (r7 == 0) goto L4e
            goto L96
        L4e:
            r7 = 0
            if (r5 > r6) goto L81
            int r5 = r4.g
            if (r5 > r6) goto L81
            int r5 = r4.h
            if (r5 <= 0) goto L5a
            goto L81
        L5a:
            wc0 r5 = r4.c
            yc0 r5 = r5.i
            if (r5 != 0) goto L61
            goto L81
        L61:
            monitor-enter(r5)
            int r1 = r5.l     // Catch: java.lang.Throwable -> L7e
            if (r1 == 0) goto L68
            monitor-exit(r5)
            goto L81
        L68:
            zf0 r1 = r5.b     // Catch: java.lang.Throwable -> L7e
            p2 r1 = r1.a     // Catch: java.lang.Throwable -> L7e
            fx r1 = r1.h     // Catch: java.lang.Throwable -> L7e
            p2 r2 = r4.b     // Catch: java.lang.Throwable -> L7e
            fx r2 = r2.h     // Catch: java.lang.Throwable -> L7e
            boolean r1 = defpackage.kq0.a(r1, r2)     // Catch: java.lang.Throwable -> L7e
            if (r1 != 0) goto L7a
            monitor-exit(r5)
            goto L81
        L7a:
            zf0 r7 = r5.b     // Catch: java.lang.Throwable -> L7e
            monitor-exit(r5)
            goto L81
        L7e:
            r4 = move-exception
            monitor-exit(r5)
            throw r4
        L81:
            if (r7 == 0) goto L87
            r4.i = r7
        L85:
            r4 = r6
            goto L9b
        L87:
            w2 r5 = r4.d
            if (r5 == 0) goto L92
            boolean r5 = r5.b()
            if (r5 != r6) goto L92
            goto L96
        L92:
            iw r4 = r4.e
            if (r4 != 0) goto L97
        L96:
            goto L85
        L97:
            boolean r4 = r4.i()
        L9b:
            if (r4 != 0) goto L9e
        L9d:
            return r0
        L9e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o9.c(java.io.IOException, wc0, z4, boolean):boolean");
    }

    public o9(fa0 fa0Var) {
        oy.j(fa0Var, "client");
        this.b = fa0Var;
    }
}
