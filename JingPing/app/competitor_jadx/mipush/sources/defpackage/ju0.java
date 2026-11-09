package defpackage;

import io.nats.client.Options;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class ju0 extends ch implements xh0 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(ju0.class, "cleanedAndPointers$volatile");
    public final long c;
    private volatile /* synthetic */ int cleanedAndPointers$volatile;

    public ju0(pd pdVar, int i, long j) {
        super(pdVar);
        this.c = j;
        this.cleanedAndPointers$volatile = i << 16;
    }

    @Override // defpackage.ch
    public final boolean c() {
        return d.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return d.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i, bj bjVar);

    public final void h() {
        if (d.incrementAndGet(this) == pb.b) {
            d();
        }
    }

    public final boolean i() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i;
        do {
            atomicIntegerFieldUpdater = d;
            i = atomicIntegerFieldUpdater.get(this);
            if (i == f() && b() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, Options.DEFAULT_BUFFER_SIZE + i));
        return true;
    }
}
