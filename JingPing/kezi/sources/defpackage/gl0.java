package defpackage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public final class gl0 implements jx {
    public volatile boolean b = false;
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final LinkedBlockingQueue d = new LinkedBlockingQueue();

    @Override // defpackage.jx
    public final synchronized n30 c(String str) {
        fl0 fl0Var;
        fl0Var = (fl0) this.c.get(str);
        if (fl0Var == null) {
            fl0Var = new fl0(str, this.d, this.b);
            this.c.put(str, fl0Var);
        }
        return fl0Var;
    }
}
