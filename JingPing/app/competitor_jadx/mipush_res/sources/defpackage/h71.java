package defpackage;

import com.mamimobi.AccessService;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h71 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ k71 F;

    public /* synthetic */ h71(k71 k71Var, int i) {
        this.E = i;
        this.F = k71Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ExecutorService executorService = this.F.c.q;
                AccessService accessService = AccessService.e0;
                Objects.requireNonNull(accessService);
                executorService.execute(new k1(accessService, 4));
                return;
            case 1:
                ExecutorService executorService2 = this.F.c.q;
                AccessService accessService2 = AccessService.e0;
                Objects.requireNonNull(accessService2);
                executorService2.execute(new k1(accessService2, 4));
                return;
            case 2:
                k71 k71Var = this.F;
                o71 o71Var = k71Var.c;
                try {
                    try {
                        o71Var.d();
                        o71Var.c();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    return;
                } finally {
                    k71Var.a = false;
                }
            case 3:
                ExecutorService executorService3 = this.F.c.q;
                AccessService accessService3 = AccessService.e0;
                Objects.requireNonNull(accessService3);
                executorService3.execute(new k1(accessService3, 4));
                return;
            default:
                ExecutorService executorService4 = this.F.c.q;
                AccessService accessService4 = AccessService.e0;
                Objects.requireNonNull(accessService4);
                executorService4.execute(new k1(accessService4, 4));
                return;
        }
    }
}
