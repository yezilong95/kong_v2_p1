package defpackage;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class wc0 implements Cloneable {
    public final fa0 b;
    public final z4 c;
    public final zc0 d;
    public final vc0 e;
    public final AtomicBoolean f;
    public Object g;
    public uo h;
    public yc0 i;
    public so j;
    public boolean k;
    public boolean l;
    public boolean m;
    public volatile boolean n;
    public volatile so o;
    public volatile yc0 p;

    public wc0(fa0 fa0Var, z4 z4Var) {
        oy.j(fa0Var, "client");
        this.b = fa0Var;
        this.c = z4Var;
        this.d = (zc0) fa0Var.c.c;
        fa0Var.f.getClass();
        vc0 vc0Var = new vc0(this);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        vc0Var.g(0);
        this.e = vc0Var;
        this.f = new AtomicBoolean();
        this.m = true;
    }

    public static final String a(wc0 wc0Var) {
        StringBuilder sb = new StringBuilder();
        sb.append(wc0Var.n ? "canceled " : ExtensionRequestData.EMPTY_VALUE);
        sb.append("call");
        sb.append(" to ");
        sb.append(((fx) wc0Var.c.b).f());
        return sb.toString();
    }

    public final void b(yc0 yc0Var) {
        byte[] bArr = kq0.a;
        if (this.i != null) {
            throw new IllegalStateException("Check failed.");
        }
        this.i = yc0Var;
        yc0Var.p.add(new uc0(this, this.g));
    }

    public final IOException c(IOException iOException) {
        IOException interruptedIOException;
        Socket socketJ;
        byte[] bArr = kq0.a;
        yc0 yc0Var = this.i;
        if (yc0Var != null) {
            synchronized (yc0Var) {
                socketJ = j();
            }
            if (this.i == null) {
                if (socketJ != null) {
                    kq0.e(socketJ);
                }
            } else if (socketJ != null) {
                throw new IllegalStateException("Check failed.");
            }
        }
        if (this.e.i()) {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        } else {
            interruptedIOException = iOException;
        }
        if (iOException != null) {
            oy.g(interruptedIOException);
        }
        return interruptedIOException;
    }

    public final Object clone() {
        return new wc0(this.b, this.c);
    }

    public final void d() {
        Socket socket;
        if (this.n) {
            return;
        }
        this.n = true;
        so soVar = this.o;
        if (soVar != null) {
            soVar.c.cancel();
        }
        yc0 yc0Var = this.p;
        if (yc0Var == null || (socket = yc0Var.c) == null) {
            return;
        }
        kq0.e(socket);
    }

    public final void e(ga gaVar) {
        tc0 tc0Var;
        if (!this.f.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        rb0 rb0Var = rb0.a;
        this.g = rb0.a.g();
        ba baVar = this.b.b;
        tc0 tc0Var2 = new tc0(this, gaVar);
        baVar.getClass();
        synchronized (baVar) {
            ((ArrayDeque) baVar.b).add(tc0Var2);
            String str = ((fx) this.c.b).d;
            Iterator it = ((ArrayDeque) baVar.c).iterator();
            while (true) {
                if (!it.hasNext()) {
                    Iterator it2 = ((ArrayDeque) baVar.b).iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            tc0Var = null;
                            break;
                        } else {
                            tc0Var = (tc0) it2.next();
                            if (oy.c(((fx) tc0Var.d.c.b).d, str)) {
                                break;
                            }
                        }
                    }
                } else {
                    tc0Var = (tc0) it.next();
                    if (oy.c(((fx) tc0Var.d.c.b).d, str)) {
                        break;
                    }
                }
            }
            if (tc0Var != null) {
                tc0Var2.c = tc0Var.c;
            }
        }
        baVar.g();
    }

    public final void f(boolean z) {
        so soVar;
        synchronized (this) {
            if (!this.m) {
                throw new IllegalStateException("released");
            }
        }
        if (z && (soVar = this.o) != null) {
            soVar.c.cancel();
            soVar.a.h(soVar, true, true, null);
        }
        this.j = null;
    }

    public final gf0 g() {
        ArrayList arrayList = new ArrayList();
        ge.a0(arrayList, this.b.d);
        arrayList.add(new o9(this.b));
        arrayList.add(new o9(this.b.k));
        this.b.getClass();
        arrayList.add(new da(0));
        arrayList.add(da.b);
        ge.a0(arrayList, this.b.e);
        arrayList.add(new da(2));
        z4 z4Var = this.c;
        fa0 fa0Var = this.b;
        try {
            try {
                gf0 gf0VarB = new ad0(this, arrayList, 0, null, z4Var, fa0Var.w, fa0Var.x, fa0Var.y).b(this.c);
                if (this.n) {
                    kq0.d(gf0VarB);
                    throw new IOException("Canceled");
                }
                i(null);
                return gf0VarB;
            } catch (IOException e) {
                IOException iOExceptionI = i(e);
                oy.h(iOExceptionI, "null cannot be cast to non-null type kotlin.Throwable");
                throw iOExceptionI;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                i(null);
            }
            throw th;
        }
    }

    public final IOException h(so soVar, boolean z, boolean z2, IOException iOException) {
        boolean z3;
        boolean z4;
        oy.j(soVar, "exchange");
        if (soVar.equals(this.o)) {
            synchronized (this) {
                z3 = false;
                if (z) {
                    try {
                        if (!this.k) {
                            if (z2 || !this.l) {
                                z4 = false;
                            }
                        }
                        if (z) {
                            this.k = false;
                        }
                        if (z2) {
                            this.l = false;
                        }
                        boolean z5 = this.k;
                        boolean z6 = (z5 || this.l) ? false : true;
                        if (!z5 && !this.l) {
                            if (!this.m) {
                                z3 = true;
                            }
                        }
                        z4 = z3;
                        z3 = z6;
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    if (z2) {
                    }
                    z4 = false;
                }
            }
            if (z3) {
                this.o = null;
                yc0 yc0Var = this.i;
                if (yc0Var != null) {
                    synchronized (yc0Var) {
                        yc0Var.m++;
                    }
                }
            }
            if (z4) {
                return c(iOException);
            }
        }
        return iOException;
    }

    public final IOException i(IOException iOException) {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.m) {
                this.m = false;
                if (!this.k) {
                    if (!this.l) {
                        z = true;
                    }
                }
            }
        }
        return z ? c(iOException) : iOException;
    }

    public final Socket j() {
        yc0 yc0Var = this.i;
        oy.g(yc0Var);
        byte[] bArr = kq0.a;
        ArrayList arrayList = yc0Var.p;
        Iterator it = arrayList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (oy.c(((Reference) it.next()).get(), this)) {
                break;
            }
            i++;
        }
        if (i == -1) {
            throw new IllegalStateException("Check failed.");
        }
        arrayList.remove(i);
        this.i = null;
        if (!arrayList.isEmpty()) {
            return null;
        }
        yc0Var.q = System.nanoTime();
        zc0 zc0Var = this.d;
        zc0Var.getClass();
        byte[] bArr2 = kq0.a;
        boolean z = yc0Var.j;
        zm0 zm0Var = zc0Var.b;
        if (!z) {
            zm0Var.c(zc0Var.c, 0L);
            return null;
        }
        yc0Var.j = true;
        ConcurrentLinkedQueue concurrentLinkedQueue = zc0Var.d;
        concurrentLinkedQueue.remove(yc0Var);
        if (concurrentLinkedQueue.isEmpty()) {
            zm0Var.a();
        }
        Socket socket = yc0Var.d;
        oy.g(socket);
        return socket;
    }
}
