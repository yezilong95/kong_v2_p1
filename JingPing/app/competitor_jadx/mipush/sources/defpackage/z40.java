package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class z40 extends g50 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater f = AtomicIntegerFieldUpdater.newUpdater(z40.class, "_invoked$volatile");
    private volatile /* synthetic */ int _invoked$volatile;
    public final s40 e;

    public z40(s40 s40Var) {
        this.e = s40Var;
    }

    @Override // defpackage.s40
    public final void b(Throwable th) {
        if (f.compareAndSet(this, 0, 1)) {
            this.e.b(th);
        }
    }
}
