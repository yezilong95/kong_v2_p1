package defpackage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class m6 implements Executor {
    public final /* synthetic */ int b;
    public final ArrayDeque c;
    public Runnable d;
    public final Object e;
    public final Executor f;

    public m6(Executor executor) {
        this.b = 1;
        oy.j(executor, "executor");
        this.f = executor;
        this.c = new ArrayDeque();
        this.e = new Object();
    }

    public final void a() {
        switch (this.b) {
            case 0:
                synchronized (this.e) {
                    try {
                        Runnable runnable = (Runnable) this.c.poll();
                        this.d = runnable;
                        if (runnable != null) {
                            ((n6) this.f).execute(runnable);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            default:
                synchronized (this.e) {
                    Object objPoll = this.c.poll();
                    Runnable runnable2 = (Runnable) objPoll;
                    this.d = runnable2;
                    if (objPoll != null) {
                        this.f.execute(runnable2);
                    }
                    break;
                }
                return;
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.b) {
            case 0:
                synchronized (this.e) {
                    try {
                        this.c.add(new l6(this, 0, runnable));
                        if (this.d == null) {
                            a();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            default:
                oy.j(runnable, "command");
                synchronized (this.e) {
                    this.c.offer(new l6(runnable, this));
                    if (this.d == null) {
                        a();
                    }
                    break;
                }
                return;
        }
    }

    public m6(n6 n6Var) {
        this.b = 0;
        this.e = new Object();
        this.c = new ArrayDeque();
        this.f = n6Var;
    }
}
