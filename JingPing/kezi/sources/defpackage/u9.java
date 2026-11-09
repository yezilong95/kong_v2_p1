package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class u9 {
    public static final vb a = new vb(-1, null, null, 0);
    public static final int b = jq0.i0("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 12);
    public static final int c = jq0.i0("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 12);
    public static final nm d = new nm("BUFFERED");
    public static final nm e = new nm("SHOULD_BUFFER");
    public static final nm f = new nm("S_RESUMING_BY_RCV");
    public static final nm g = new nm("RESUMING_BY_EB");
    public static final nm h = new nm("POISONED");
    public static final nm i = new nm("DONE_RCV");
    public static final nm j = new nm("INTERRUPTED_SEND");
    public static final nm k = new nm("INTERRUPTED_RCV");
    public static final nm l = new nm("CHANNEL_CLOSED");
    public static final nm m = new nm("SUSPEND");
    public static final nm n = new nm("SUSPEND_NO_WAITER");
    public static final nm o = new nm("FAILED");
    public static final nm p = new nm("CLOSE_HANDLER_CLOSED");
    public static final nm q = new nm("CLOSE_HANDLER_INVOKED");
    public static final nm r = new nm("NO_CLOSE_CAUSE");

    public static final boolean a(la laVar, Object obj, wa0 wa0Var) {
        nm nmVar;
        ma maVar = (ma) laVar;
        maVar.getClass();
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ma.h;
            Object obj2 = atomicReferenceFieldUpdater.get(maVar);
            boolean z = obj2 instanceof r90;
            nmVar = rp0.a;
            if (!z) {
                boolean z2 = obj2 instanceof se;
                nmVar = null;
                break;
            }
            int i2 = maVar.d;
            if (!(obj instanceof ue) && i2 != 1) {
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(maVar, obj2, obj)) {
                if (atomicReferenceFieldUpdater.get(maVar) != obj2) {
                    break;
                }
            }
            if (!maVar.o()) {
                maVar.k();
            }
        }
        if (nmVar == null) {
            return false;
        }
        maVar.l(maVar.d);
        return true;
    }
}
