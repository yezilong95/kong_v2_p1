package defpackage;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class vh0 {
    public static final th0 a = new th0(new byte[0], 0, 0, false);
    public static final int b;
    public static final AtomicReference[] c;

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        b = iHighestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i = 0; i < iHighestOneBit; i++) {
            atomicReferenceArr[i] = new AtomicReference();
        }
        c = atomicReferenceArr;
    }

    public static final void a(th0 th0Var) {
        oy.j(th0Var, "segment");
        if (th0Var.f != null || th0Var.g != null) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (th0Var.d) {
            return;
        }
        AtomicReference atomicReference = c[(int) (Thread.currentThread().getId() & (((long) b) - 1))];
        th0 th0Var2 = a;
        th0 th0Var3 = (th0) atomicReference.getAndSet(th0Var2);
        if (th0Var3 == th0Var2) {
            return;
        }
        int i = th0Var3 != null ? th0Var3.c : 0;
        if (i >= 65536) {
            atomicReference.set(th0Var3);
            return;
        }
        th0Var.f = th0Var3;
        th0Var.b = 0;
        th0Var.c = i + 8192;
        atomicReference.set(th0Var);
    }

    public static final th0 b() {
        AtomicReference atomicReference = c[(int) (Thread.currentThread().getId() & (((long) b) - 1))];
        th0 th0Var = a;
        th0 th0Var2 = (th0) atomicReference.getAndSet(th0Var);
        if (th0Var2 == th0Var) {
            return new th0();
        }
        if (th0Var2 == null) {
            atomicReference.set(null);
            return new th0();
        }
        atomicReference.set(th0Var2.f);
        th0Var2.f = null;
        th0Var2.c = 0;
        return th0Var2;
    }
}
