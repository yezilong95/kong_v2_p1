package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class vl implements y30 {
    public final /* synthetic */ int a = 0;
    public final int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ sl d;

    public vl(sl slVar, int i) {
        this.d = slVar;
        this.c = i;
        this.b = (i + 7) / 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0071  */
    @Override // defpackage.y30
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] a(long r12) throws java.lang.InterruptedException {
        /*
            r11 = this;
            sl r0 = r11.d
            r1 = 0
            r3 = 0
            int r4 = r11.b
            r5 = 8
            int r6 = r11.a
            switch(r6) {
                case 0: goto L48;
                default: goto Le;
            }
        Le:
            byte[] r6 = new byte[r4]
            r7 = r3
        L11:
            int r8 = r4 / 8
            if (r7 >= r8) goto L2d
            int r8 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r8 == 0) goto L1c
            java.lang.Thread.sleep(r12)
        L1c:
            java.lang.Object r8 = r0.b
            java.security.SecureRandom r8 = (java.security.SecureRandom) r8
            byte[] r8 = r8.generateSeed(r5)
            int r9 = r7 * 8
            int r10 = r8.length
            java.lang.System.arraycopy(r8, r3, r6, r9, r10)
            int r7 = r7 + 1
            goto L11
        L2d:
            int r8 = r8 * r5
            int r5 = r4 - r8
            if (r5 == 0) goto L47
            int r1 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r1 == 0) goto L39
            java.lang.Thread.sleep(r12)
        L39:
            java.lang.Object r12 = r0.b
            java.security.SecureRandom r12 = (java.security.SecureRandom) r12
            byte[] r12 = r12.generateSeed(r5)
            int r13 = r12.length
            int r4 = r4 - r13
            int r13 = r12.length
            java.lang.System.arraycopy(r12, r3, r6, r4, r13)
        L47:
            return r6
        L48:
            byte[] r5 = new byte[r4]
        L4a:
            if (r3 == r4) goto L6e
            int r6 = r4 - r3
            r0.getClass()
            ul r7 = new ul
            r7.<init>(r0, r5, r3, r6)
            java.lang.Object r6 = java.security.AccessController.doPrivileged(r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r7 = -1
            if (r6 <= r7) goto L6e
            int r3 = r3 + r6
            java.lang.String[][] r6 = defpackage.wl.a
            int r6 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r6 == 0) goto L4a
            java.lang.Thread.sleep(r12)
            goto L4a
        L6e:
            if (r3 != r4) goto L71
            return r5
        L71:
            java.lang.InternalError r12 = new java.lang.InternalError
            java.lang.String r13 = "unable to fully read random source"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vl.a(long):byte[]");
    }

    @Override // defpackage.fu
    public final byte[] b() {
        switch (this.a) {
            case 0:
                try {
                    return a(0L);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("initial entropy fetch interrupted");
                }
            default:
                try {
                    return a(0L);
                } catch (InterruptedException unused2) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("initial entropy fetch interrupted");
                }
        }
    }

    @Override // defpackage.fu
    public final int c() {
        switch (this.a) {
        }
        return this.c;
    }

    public vl(sl slVar, int i, byte b) {
        this.d = slVar;
        this.c = i;
        this.b = (i + 7) / 8;
    }
}
