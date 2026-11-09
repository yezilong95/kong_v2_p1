package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class pb {
    public static final pd a = new pd(-1, null, null, 0);
    public static final int b = fa0.J0(32, 12, "kotlinx.coroutines.bufferedChannel.segmentSize");
    public static final int c = fa0.J0(10000, 12, "kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations");
    public static final wy0 d = new wy0("BUFFERED", 0);
    public static final wy0 e = new wy0("SHOULD_BUFFER", 0);
    public static final wy0 f = new wy0("S_RESUMING_BY_RCV", 0);
    public static final wy0 g = new wy0("RESUMING_BY_EB", 0);
    public static final wy0 h = new wy0("POISONED", 0);
    public static final wy0 i = new wy0("DONE_RCV", 0);
    public static final wy0 j = new wy0("INTERRUPTED_SEND", 0);
    public static final wy0 k = new wy0("INTERRUPTED_RCV", 0);
    public static final wy0 l = new wy0("CHANNEL_CLOSED", 0);
    public static final wy0 m = new wy0("SUSPEND", 0);
    public static final wy0 n = new wy0("SUSPEND_NO_WAITER", 0);
    public static final wy0 o = new wy0("FAILED", 0);
    public static final wy0 p = new wy0("CLOSE_HANDLER_CLOSED", 0);
    public static final wy0 q = new wy0("CLOSE_HANDLER_INVOKED", 0);
    public static final wy0 r = new wy0("NO_CLOSE_CAUSE", 0);

    public static final boolean a(dc dcVar, Object obj, lj0 lj0Var) {
        wy0 wy0Var;
        ec ecVar = (ec) dcVar;
        ecVar.getClass();
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ec.K;
            Object obj2 = atomicReferenceFieldUpdater.get(ecVar);
            boolean z = obj2 instanceof xh0;
            wy0Var = qm.b;
            if (!z) {
                boolean z2 = obj2 instanceof mg;
                wy0Var = null;
                break;
            }
            int i2 = ecVar.G;
            if (!(obj instanceof og) && i2 != 1) {
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(ecVar, obj2, obj)) {
                if (atomicReferenceFieldUpdater.get(ecVar) != obj2) {
                    break;
                }
            }
            if (!ecVar.o()) {
                ecVar.k();
            }
        }
        if (wy0Var == null) {
            return false;
        }
        ecVar.l(ecVar.G);
        return true;
    }
}
