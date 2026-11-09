package defpackage;

import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class jn extends za0 {
    public final /* synthetic */ za0 p;
    public final /* synthetic */ ThreadPoolExecutor q;

    public jn(za0 za0Var, ThreadPoolExecutor threadPoolExecutor) {
        this.p = za0Var;
        this.q = threadPoolExecutor;
    }

    @Override // defpackage.za0
    public final void F(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.q;
        try {
            this.p.F(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // defpackage.za0
    public final void I(ba baVar) {
        ThreadPoolExecutor threadPoolExecutor = this.q;
        try {
            this.p.I(baVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
