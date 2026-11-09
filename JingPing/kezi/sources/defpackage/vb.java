package defpackage;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class vb extends uh0 {
    public final s9 e;
    public final AtomicReferenceArray f;

    public vb(long j, vb vbVar, s9 s9Var, int i) {
        super(vbVar, i, j);
        this.e = s9Var;
        this.f = new AtomicReferenceArray(u9.b * 2);
    }

    @Override // defpackage.uh0
    public final int f() {
        return u9.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        m(r5, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005c, code lost:
    
        if (r0 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005e, code lost:
    
        defpackage.oy.g(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return;
     */
    @Override // defpackage.uh0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(int r5, defpackage.nh r6) {
        /*
            r4 = this;
            int r6 = defpackage.u9.b
            if (r5 < r6) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            if (r0 == 0) goto La
            int r5 = r5 - r6
        La:
            java.util.concurrent.atomic.AtomicReferenceArray r6 = r4.f
            int r1 = r5 * 2
            r6.get(r1)
        L11:
            java.lang.Object r6 = r4.k(r5)
            boolean r1 = r6 instanceof defpackage.rt0
            s9 r2 = r4.e
            r3 = 0
            if (r1 != 0) goto L62
            boolean r1 = r6 instanceof defpackage.st0
            if (r1 == 0) goto L21
            goto L62
        L21:
            nm r1 = defpackage.u9.j
            if (r6 == r1) goto L59
            nm r1 = defpackage.u9.k
            if (r6 != r1) goto L2a
            goto L59
        L2a:
            nm r1 = defpackage.u9.g
            if (r6 == r1) goto L11
            nm r1 = defpackage.u9.f
            if (r6 != r1) goto L33
            goto L11
        L33:
            nm r5 = defpackage.u9.i
            if (r6 == r5) goto L7c
            nm r5 = defpackage.u9.d
            if (r6 != r5) goto L3c
            goto L7c
        L3c:
            nm r5 = defpackage.u9.l
            if (r6 != r5) goto L41
            goto L7c
        L41:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "unexpected state: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L59:
            r4.m(r5, r3)
            if (r0 == 0) goto L7c
            defpackage.oy.g(r2)
            return
        L62:
            if (r0 == 0) goto L67
            nm r1 = defpackage.u9.j
            goto L69
        L67:
            nm r1 = defpackage.u9.k
        L69:
            boolean r6 = r4.j(r6, r5, r1)
            if (r6 == 0) goto L11
            r4.m(r5, r3)
            r6 = r0 ^ 1
            r4.l(r5, r6)
            if (r0 == 0) goto L7c
            defpackage.oy.g(r2)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vb.g(int, nh):void");
    }

    public final boolean j(Object obj, int i, Object obj2) {
        AtomicReferenceArray atomicReferenceArray = this.f;
        int i2 = (i * 2) + 1;
        while (!atomicReferenceArray.compareAndSet(i2, obj, obj2)) {
            if (atomicReferenceArray.get(i2) != obj) {
                return false;
            }
        }
        return true;
    }

    public final Object k(int i) {
        return this.f.get((i * 2) + 1);
    }

    public final void l(int i, boolean z) {
        if (z) {
            s9 s9Var = this.e;
            oy.g(s9Var);
            s9Var.A((this.c * ((long) u9.b)) + ((long) i));
        }
        h();
    }

    public final void m(int i, wx wxVar) {
        this.f.lazySet(i * 2, wxVar);
    }

    public final void n(int i, nm nmVar) {
        this.f.set((i * 2) + 1, nmVar);
    }
}
