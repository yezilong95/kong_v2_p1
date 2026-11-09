package defpackage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public final class ux0 implements i30 {
    public volatile boolean E = false;
    public final ConcurrentHashMap F = new ConcurrentHashMap();
    public final LinkedBlockingQueue G = new LinkedBlockingQueue();

    @Override // defpackage.i30
    public final synchronized ua0 b(String str) {
        tx0 tx0Var;
        tx0Var = (tx0) this.F.get(str);
        if (tx0Var == null) {
            tx0Var = new tx0(str, this.G, this.E);
            this.F.put(str, tx0Var);
        }
        return tx0Var;
    }
}
