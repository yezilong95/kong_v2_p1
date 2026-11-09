package defpackage;

import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class bt extends z91 {
    public final /* synthetic */ z91 b0;
    public final /* synthetic */ ThreadPoolExecutor c0;

    public bt(z91 z91Var, ThreadPoolExecutor threadPoolExecutor) {
        this.b0 = z91Var;
        this.c0 = threadPoolExecutor;
    }

    @Override // defpackage.z91
    public final void V(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.c0;
        try {
            this.b0.V(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // defpackage.z91
    public final void Y(tb tbVar) {
        ThreadPoolExecutor threadPoolExecutor = this.c0;
        try {
            this.b0.Y(tbVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
