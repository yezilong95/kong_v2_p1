package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class uy extends cz {
    public static final AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(uy.class, "_invoked");
    private volatile int _invoked;
    public final jz f;

    public uy(jz jzVar) {
        this.f = jzVar;
    }

    @Override // defpackage.pu
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        j((Throwable) obj);
        return bq0.a;
    }

    @Override // defpackage.jz
    public final void j(Throwable th) {
        if (g.compareAndSet(this, 0, 1)) {
            this.f.invoke(th);
        }
    }
}
