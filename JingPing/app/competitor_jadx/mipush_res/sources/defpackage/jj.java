package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState;

/* JADX INFO: loaded from: classes.dex */
public final class jj extends Thread {
    public static final /* synthetic */ AtomicIntegerFieldUpdater M = AtomicIntegerFieldUpdater.newUpdater(jj.class, "workerCtl$volatile");
    public final e91 E;
    public final Ref$ObjectRef F;
    public CoroutineScheduler$WorkerState G;
    public long H;
    public long I;
    public int J;
    public boolean K;
    public final /* synthetic */ kj L;
    private volatile int indexInArray;
    private volatile Object nextParkedWorker;
    private volatile /* synthetic */ int workerCtl$volatile;

    public jj(kj kjVar, int i) {
        this.L = kjVar;
        setDaemon(true);
        setContextClassLoader(kjVar.getClass().getClassLoader());
        this.E = new e91();
        this.F = new Ref$ObjectRef();
        this.G = CoroutineScheduler$WorkerState.DORMANT;
        this.nextParkedWorker = kj.O;
        int iNanoTime = (int) System.nanoTime();
        this.J = iNanoTime == 0 ? 42 : iNanoTime;
        f(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        r13 = defpackage.e91.d.get(r3);
        r0 = defpackage.e91.c.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0051, code lost:
    
        if (r13 == r0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
    
        if (defpackage.e91.e.get(r3) != 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        r0 = r0 - 1;
        r1 = r3.c(r0, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        if (r1 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0064, code lost:
    
        r2 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.kz0 a(boolean r13) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jj.a(boolean):kz0");
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i) {
        int i2 = this.J;
        int i3 = i2 ^ (i2 << 13);
        int i4 = i3 ^ (i3 >> 17);
        int i5 = i4 ^ (i4 << 5);
        this.J = i5;
        int i6 = i - 1;
        return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
    }

    public final kz0 e() {
        int iD = d(2);
        kj kjVar = this.L;
        if (iD == 0) {
            kz0 kz0Var = (kz0) kjVar.I.d();
            return kz0Var != null ? kz0Var : (kz0) kjVar.J.d();
        }
        kz0 kz0Var2 = (kz0) kjVar.J.d();
        return kz0Var2 != null ? kz0Var2 : (kz0) kjVar.I.d();
    }

    public final void f(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.L.H);
        sb.append("-worker-");
        sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
        setName(sb.toString());
        this.indexInArray = i;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(CoroutineScheduler$WorkerState coroutineScheduler$WorkerState) {
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState2 = this.G;
        boolean z = coroutineScheduler$WorkerState2 == CoroutineScheduler$WorkerState.CPU_ACQUIRED;
        if (z) {
            kj.M.addAndGet(this.L, 4398046511104L);
        }
        if (coroutineScheduler$WorkerState2 != coroutineScheduler$WorkerState) {
            this.G = coroutineScheduler$WorkerState;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006b, code lost:
    
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a2, code lost:
    
        r7 = -2;
        r23 = r6;
        r5 = r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1, types: [T, java.lang.Object, kz0] */
    /* JADX WARN: Type inference failed for: r7v14, types: [kz0] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [kz0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.kz0 i(int r26) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jj.i(int):kz0");
    }

    /* JADX WARN: Code restructure failed: missing block: B:122:0x0004, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0004, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0004, code lost:
    
        continue;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jj.run():void");
    }
}
