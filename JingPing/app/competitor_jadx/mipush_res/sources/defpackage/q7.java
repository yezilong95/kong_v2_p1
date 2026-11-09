package defpackage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class q7 implements Executor {
    public final /* synthetic */ int E;
    public final ArrayDeque F;
    public Runnable G;
    public final Object H;
    public final Executor I;

    public q7(Executor executor) {
        this.E = 1;
        t40.n(executor, "executor");
        this.I = executor;
        this.F = new ArrayDeque();
        this.H = new Object();
    }

    public final void a() {
        switch (this.E) {
            case 0:
                synchronized (this.H) {
                    try {
                        Runnable runnable = (Runnable) this.F.poll();
                        this.G = runnable;
                        if (runnable != null) {
                            ((r7) this.I).execute(runnable);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            default:
                synchronized (this.H) {
                    Object objPoll = this.F.poll();
                    Runnable runnable2 = (Runnable) objPoll;
                    this.G = runnable2;
                    if (objPoll != null) {
                        this.I.execute(runnable2);
                    }
                    break;
                }
                return;
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.E) {
            case 0:
                synchronized (this.H) {
                    try {
                        this.F.add(new p7(this, 0, runnable));
                        if (this.G == null) {
                            a();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            default:
                t40.n(runnable, "command");
                synchronized (this.H) {
                    this.F.offer(new p7(runnable, this));
                    if (this.G == null) {
                        a();
                    }
                    break;
                }
                return;
        }
    }

    public q7(r7 r7Var) {
        this.E = 0;
        this.H = new Object();
        this.F = new ArrayDeque();
        this.I = r7Var;
    }
}
