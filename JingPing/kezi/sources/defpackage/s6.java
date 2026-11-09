package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s6 implements Executor {
    public final /* synthetic */ int b;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.b) {
            case 0:
                t6.W().r.s.execute(runnable);
                break;
            default:
                runnable.run();
                break;
        }
    }
}
