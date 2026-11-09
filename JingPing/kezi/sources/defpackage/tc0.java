package defpackage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class tc0 implements Runnable {
    public final ga b;
    public volatile AtomicInteger c = new AtomicInteger(0);
    public final /* synthetic */ wc0 d;

    public tc0(wc0 wc0Var, ga gaVar) {
        this.d = wc0Var;
        this.b = gaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ba baVar;
        String strConcat = "OkHttp ".concat(((fx) this.d.c.b).f());
        wc0 wc0Var = this.d;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(strConcat);
        try {
            wc0Var.e.h();
            boolean z = false;
            try {
                try {
                    try {
                        this.b.f(wc0Var.g());
                        baVar = wc0Var.b.b;
                    } catch (IOException e) {
                        e = e;
                        z = true;
                        if (z) {
                            rb0 rb0Var = rb0.a;
                            rb0 rb0Var2 = rb0.a;
                            String str = "Callback failure for " + wc0.a(wc0Var);
                            rb0Var2.getClass();
                            rb0.i(4, str, e);
                        } else {
                            this.b.e(e);
                        }
                        baVar = wc0Var.b.b;
                    } catch (Throwable th) {
                        th = th;
                        z = true;
                        wc0Var.d();
                        if (!z) {
                            IOException iOException = new IOException("canceled due to " + th);
                            nv0.c(iOException, th);
                            this.b.e(iOException);
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    th = th2;
                }
                baVar.b(this);
            } catch (Throwable th3) {
                wc0Var.b.b.b(this);
                throw th3;
            }
        } finally {
            threadCurrentThread.setName(name);
        }
    }
}
