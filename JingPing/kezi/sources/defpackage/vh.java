package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState;

/* JADX INFO: loaded from: classes.dex */
public final class vh extends Thread {
    public static final AtomicIntegerFieldUpdater j = AtomicIntegerFieldUpdater.newUpdater(vh.class, "workerCtl");
    public final fv0 b;
    public final Ref$ObjectRef c;
    public CoroutineScheduler$WorkerState d;
    public long e;
    public long f;
    public int g;
    public boolean h;
    public final /* synthetic */ wh i;
    private volatile int indexInArray;
    private volatile Object nextParkedWorker;
    private volatile int workerCtl;

    public vh(wh whVar, int i) {
        this.i = whVar;
        setDaemon(true);
        this.b = new fv0();
        this.c = new Ref$ObjectRef();
        this.d = CoroutineScheduler$WorkerState.DORMANT;
        this.nextParkedWorker = wh.l;
        this.g = pc0.Default.nextInt();
        f(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        r13 = defpackage.fv0.d.get(r3);
        r0 = defpackage.fv0.c.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0051, code lost:
    
        if (r13 == r0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
    
        if (defpackage.fv0.e.get(r3) != 0) goto L25;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.wm0 a(boolean r13) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vh.a(boolean):wm0");
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i) {
        int i2 = this.g;
        int i3 = i2 ^ (i2 << 13);
        int i4 = i3 ^ (i3 >> 17);
        int i5 = i4 ^ (i4 << 5);
        this.g = i5;
        int i6 = i - 1;
        return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
    }

    public final wm0 e() {
        int iD = d(2);
        wh whVar = this.i;
        if (iD == 0) {
            wm0 wm0Var = (wm0) whVar.f.d();
            return wm0Var != null ? wm0Var : (wm0) whVar.g.d();
        }
        wm0 wm0Var2 = (wm0) whVar.g.d();
        return wm0Var2 != null ? wm0Var2 : (wm0) whVar.f.d();
    }

    public final void f(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.i.e);
        sb.append("-worker-");
        sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
        setName(sb.toString());
        this.indexInArray = i;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(CoroutineScheduler$WorkerState coroutineScheduler$WorkerState) {
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState2 = this.d;
        boolean z = coroutineScheduler$WorkerState2 == CoroutineScheduler$WorkerState.CPU_ACQUIRED;
        if (z) {
            wh.j.addAndGet(this.i, 4398046511104L);
        }
        if (coroutineScheduler$WorkerState2 != coroutineScheduler$WorkerState) {
            this.d = coroutineScheduler$WorkerState;
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
    /* JADX WARN: Type inference failed for: r14v1, types: [T, java.lang.Object, wm0] */
    /* JADX WARN: Type inference failed for: r7v14, types: [wm0] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [wm0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.wm0 i(int r26) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vh.i(int):wm0");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vh.run():void");
    }
}
