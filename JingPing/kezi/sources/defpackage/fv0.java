package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class fv0 {
    public static final AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(fv0.class, Object.class, "lastScheduledTask");
    public static final AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(fv0.class, "producerIndex");
    public static final AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(fv0.class, "consumerIndex");
    public static final AtomicIntegerFieldUpdater e = AtomicIntegerFieldUpdater.newUpdater(fv0.class, "blockingTasksInBuffer");
    public final AtomicReferenceArray a = new AtomicReferenceArray(Uuid.SIZE_BITS);
    private volatile int blockingTasksInBuffer;
    private volatile int consumerIndex;
    private volatile Object lastScheduledTask;
    private volatile int producerIndex;

    public final wm0 a(wm0 wm0Var) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = c;
        if (atomicIntegerFieldUpdater.get(this) - d.get(this) == 127) {
            return wm0Var;
        }
        if (wm0Var.c.a == 1) {
            e.incrementAndGet(this);
        }
        int i = atomicIntegerFieldUpdater.get(this) & 127;
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.a;
            if (atomicReferenceArray.get(i) == null) {
                atomicReferenceArray.lazySet(i, wm0Var);
                atomicIntegerFieldUpdater.incrementAndGet(this);
                return null;
            }
            Thread.yield();
        }
    }

    public final wm0 b() {
        wm0 wm0Var;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = d;
            int i = atomicIntegerFieldUpdater.get(this);
            if (i - c.get(this) == 0) {
                return null;
            }
            int i2 = i & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i, i + 1) && (wm0Var = (wm0) this.a.getAndSet(i2, null)) != null) {
                if (wm0Var.c.a == 1) {
                    e.decrementAndGet(this);
                }
                return wm0Var;
            }
        }
    }

    public final wm0 c(int i, boolean z) {
        int i2 = i & 127;
        AtomicReferenceArray atomicReferenceArray = this.a;
        wm0 wm0Var = (wm0) atomicReferenceArray.get(i2);
        if (wm0Var != null) {
            if ((wm0Var.c.a == 1) == z) {
                while (!atomicReferenceArray.compareAndSet(i2, wm0Var, null)) {
                    if (atomicReferenceArray.get(i2) != wm0Var) {
                    }
                }
                if (z) {
                    e.decrementAndGet(this);
                }
                return wm0Var;
            }
        }
        return null;
    }
}
