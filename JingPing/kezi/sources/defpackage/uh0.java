package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class uh0 extends Cif implements r90 {
    public static final AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(uh0.class, "cleanedAndPointers");
    public final long c;
    private volatile int cleanedAndPointers;

    public uh0(vb vbVar, int i, long j) {
        super(vbVar);
        this.c = j;
        this.cleanedAndPointers = i << 16;
    }

    @Override // defpackage.Cif
    public final boolean c() {
        return d.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return d.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i, nh nhVar);

    public final void h() {
        if (d.incrementAndGet(this) == u9.b) {
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
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, 65536 + i));
        return true;
    }
}
