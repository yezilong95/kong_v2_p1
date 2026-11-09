package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d8 implements Executor {
    public final /* synthetic */ int E;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.E) {
            case 0:
                e8.k0().j.k.execute(runnable);
                break;
            default:
                runnable.run();
                break;
        }
    }
}
