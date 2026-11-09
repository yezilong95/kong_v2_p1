package defpackage;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class pd extends ju0 {
    public final nb e;
    public final /* synthetic */ AtomicReferenceArray f;

    public pd(long j, pd pdVar, nb nbVar, int i) {
        super(pdVar, i, j);
        this.e = nbVar;
        this.f = new AtomicReferenceArray(pb.b * 2);
    }

    @Override // defpackage.ju0
    public final int f() {
        return pb.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        m(r5, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005c, code lost:
    
        if (r0 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005e, code lost:
    
        defpackage.t40.k(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return;
     */
    @Override // defpackage.ju0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(int r5, defpackage.bj r6) {
        /*
            r4 = this;
            int r6 = defpackage.pb.b
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
            boolean r1 = r6 instanceof defpackage.d71
            nb r2 = r4.e
            r3 = 0
            if (r1 != 0) goto L62
            boolean r1 = r6 instanceof defpackage.e71
            if (r1 == 0) goto L21
            goto L62
        L21:
            wy0 r1 = defpackage.pb.j
            if (r6 == r1) goto L59
            wy0 r1 = defpackage.pb.k
            if (r6 != r1) goto L2a
            goto L59
        L2a:
            wy0 r1 = defpackage.pb.g
            if (r6 == r1) goto L11
            wy0 r1 = defpackage.pb.f
            if (r6 != r1) goto L33
            goto L11
        L33:
            wy0 r5 = defpackage.pb.i
            if (r6 == r5) goto L7c
            wy0 r5 = defpackage.pb.d
            if (r6 != r5) goto L3c
            goto L7c
        L3c:
            wy0 r5 = defpackage.pb.l
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
            defpackage.t40.k(r2)
            return
        L62:
            if (r0 == 0) goto L67
            wy0 r1 = defpackage.pb.j
            goto L69
        L67:
            wy0 r1 = defpackage.pb.k
        L69:
            boolean r6 = r4.j(r6, r5, r1)
            if (r6 == 0) goto L11
            r4.m(r5, r3)
            r6 = r0 ^ 1
            r4.l(r5, r6)
            if (r0 == 0) goto L7c
            defpackage.t40.k(r2)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pd.g(int, bj):void");
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
            nb nbVar = this.e;
            t40.k(nbVar);
            nbVar.A((this.c * ((long) pb.b)) + ((long) i));
        }
        h();
    }

    public final void m(int i, b40 b40Var) {
        this.f.set(i * 2, b40Var);
    }

    public final void n(int i, wy0 wy0Var) {
        this.f.set((i * 2) + 1, wy0Var);
    }
}
