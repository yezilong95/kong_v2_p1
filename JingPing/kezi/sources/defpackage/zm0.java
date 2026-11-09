package defpackage;

import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public final class zm0 {
    public final an0 a;
    public final String b;
    public boolean c;
    public vm0 d;
    public final ArrayList e;
    public boolean f;

    public zm0(an0 an0Var, String str) {
        oy.j(an0Var, "taskRunner");
        oy.j(str, "name");
        this.a = an0Var;
        this.b = str;
        this.e = new ArrayList();
    }

    public final void a() {
        byte[] bArr = kq0.a;
        synchronized (this.a) {
            if (b()) {
                this.a.d(this);
            }
        }
    }

    public final boolean b() {
        vm0 vm0Var = this.d;
        if (vm0Var != null && vm0Var.b) {
            this.f = true;
        }
        ArrayList arrayList = this.e;
        boolean z = false;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            if (((vm0) arrayList.get(size)).b) {
                vm0 vm0Var2 = (vm0) arrayList.get(size);
                if (an0.i.isLoggable(Level.FINE)) {
                    oy.b(vm0Var2, this, "canceled");
                }
                arrayList.remove(size);
                z = true;
            }
        }
        return z;
    }

    public final void c(vm0 vm0Var, long j) {
        oy.j(vm0Var, "task");
        synchronized (this.a) {
            if (!this.c) {
                if (d(vm0Var, j, false)) {
                    this.a.d(this);
                }
            } else if (vm0Var.b) {
                if (an0.i.isLoggable(Level.FINE)) {
                    oy.b(vm0Var, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                if (an0.i.isLoggable(Level.FINE)) {
                    oy.b(vm0Var, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(defpackage.vm0 r11, long r12, boolean r14) {
        /*
            r10 = this;
            java.lang.String r0 = "task"
            defpackage.oy.j(r11, r0)
            zm0 r0 = r11.c
            if (r0 != r10) goto La
            goto Le
        La:
            if (r0 != 0) goto L8e
            r11.c = r10
        Le:
            an0 r0 = r10.a
            d90 r0 = r0.a
            long r0 = java.lang.System.nanoTime()
            long r2 = r0 + r12
            java.util.ArrayList r4 = r10.e
            int r5 = r4.indexOf(r11)
            r6 = 0
            r7 = -1
            if (r5 == r7) goto L3b
            long r8 = r11.d
            int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r8 > 0) goto L38
            java.util.logging.Logger r12 = defpackage.an0.i
            java.util.logging.Level r13 = java.util.logging.Level.FINE
            boolean r12 = r12.isLoggable(r13)
            if (r12 == 0) goto L8d
            java.lang.String r12 = "already scheduled"
            defpackage.oy.b(r11, r10, r12)
            return r6
        L38:
            r4.remove(r5)
        L3b:
            r11.d = r2
            java.util.logging.Logger r5 = defpackage.an0.i
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            boolean r5 = r5.isLoggable(r8)
            if (r5 == 0) goto L63
            if (r14 == 0) goto L55
            long r2 = r2 - r0
            java.lang.String r14 = defpackage.oy.t(r2)
            java.lang.String r2 = "run again after "
            java.lang.String r14 = r2.concat(r14)
            goto L60
        L55:
            long r2 = r2 - r0
            java.lang.String r14 = defpackage.oy.t(r2)
            java.lang.String r2 = "scheduled after "
            java.lang.String r14 = r2.concat(r14)
        L60:
            defpackage.oy.b(r11, r10, r14)
        L63:
            java.util.Iterator r14 = r4.iterator()
            r2 = r6
        L68:
            boolean r3 = r14.hasNext()
            if (r3 == 0) goto L7f
            java.lang.Object r3 = r14.next()
            vm0 r3 = (defpackage.vm0) r3
            long r8 = r3.d
            long r8 = r8 - r0
            int r3 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r3 <= 0) goto L7c
            goto L80
        L7c:
            int r2 = r2 + 1
            goto L68
        L7f:
            r2 = r7
        L80:
            if (r2 != r7) goto L86
            int r2 = r4.size()
        L86:
            r4.add(r2, r11)
            if (r2 != 0) goto L8d
            r11 = 1
            return r11
        L8d:
            return r6
        L8e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "task is in multiple queues"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zm0.d(vm0, long, boolean):boolean");
    }

    public final void e() {
        byte[] bArr = kq0.a;
        synchronized (this.a) {
            this.c = true;
            if (b()) {
                this.a.d(this);
            }
        }
    }

    public final String toString() {
        return this.b;
    }
}
