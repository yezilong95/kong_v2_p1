package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class x6 {
    public final int a;
    public final float b;
    public final int c;
    public final int d;
    public final float e;
    public final float f;
    public final int g;
    public final float h;

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public x6(int r6, float r7, float r8, float r9, int r10, float r11, int r12, float r13, int r14, float r15) {
        /*
            r5 = this;
            r5.<init>()
            r5.a = r6
            float r7 = defpackage.rp0.m(r7, r8, r9)
            r5.b = r7
            r5.c = r10
            r5.e = r11
            r5.d = r12
            r5.f = r13
            r5.g = r14
            float r0 = (float) r14
            float r1 = r13 * r0
            float r2 = (float) r12
            float r11 = r11 * r2
            float r11 = r11 + r1
            float r1 = (float) r10
            float r3 = r7 * r1
            float r3 = r3 + r11
            float r11 = r15 - r3
            r3 = 0
            if (r10 <= 0) goto L32
            int r4 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r4 <= 0) goto L32
            float r11 = r11 / r1
            float r9 = r9 - r7
            float r8 = java.lang.Math.min(r11, r9)
            float r8 = r8 + r7
            r5.b = r8
            goto L41
        L32:
            if (r10 <= 0) goto L41
            int r9 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r9 >= 0) goto L41
            float r11 = r11 / r1
            float r8 = r8 - r7
            float r8 = java.lang.Math.max(r11, r8)
            float r8 = r8 + r7
            r5.b = r8
        L41:
            float r7 = r5.b
            if (r10 <= 0) goto L47
            r8 = r7
            goto L48
        L47:
            r8 = r3
        L48:
            r9 = 1073741824(0x40000000, float:2.0)
            float r11 = r2 / r9
            float r1 = r1 + r11
            float r1 = r1 * r8
            float r15 = r15 - r1
            float r11 = r11 + r0
            float r15 = r15 / r11
            r5.f = r15
            float r7 = r7 + r15
            float r7 = r7 / r9
            r5.e = r7
            if (r12 <= 0) goto L8c
            int r8 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r8 == 0) goto L8c
            float r8 = r13 - r15
            float r8 = r8 * r0
            r9 = 1036831949(0x3dcccccd, float:0.1)
            float r7 = r7 * r9
            float r7 = r7 * r2
            float r9 = java.lang.Math.abs(r8)
            float r7 = java.lang.Math.min(r9, r7)
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 <= 0) goto L7f
            float r8 = r5.e
            float r9 = r7 / r2
            float r8 = r8 - r9
            r5.e = r8
            float r8 = r5.f
            float r7 = r7 / r0
            float r7 = r7 + r8
            r5.f = r7
            goto L8c
        L7f:
            float r8 = r5.e
            float r9 = r7 / r2
            float r9 = r9 + r8
            r5.e = r9
            float r8 = r5.f
            float r7 = r7 / r0
            float r8 = r8 - r7
            r5.f = r8
        L8c:
            if (r14 <= 0) goto La1
            if (r10 <= 0) goto La1
            if (r12 <= 0) goto La1
            float r7 = r5.f
            float r8 = r5.e
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 <= 0) goto Lae
            float r7 = r5.b
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 <= 0) goto Lae
            goto Lb2
        La1:
            if (r14 <= 0) goto Lb2
            if (r10 <= 0) goto Lb2
            float r7 = r5.f
            float r8 = r5.b
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 <= 0) goto Lae
            goto Lb2
        Lae:
            r6 = 2139095039(0x7f7fffff, float:3.4028235E38)
            goto Lbb
        Lb2:
            float r7 = r5.f
            float r13 = r13 - r7
            float r7 = java.lang.Math.abs(r13)
            float r6 = (float) r6
            float r6 = r6 * r7
        Lbb:
            r5.h = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x6.<init>(int, float, float, float, int, float, int, float, int, float):void");
    }

    public final String toString() {
        return "Arrangement [priority=" + this.a + ", smallCount=" + this.c + ", smallSize=" + this.b + ", mediumCount=" + this.d + ", mediumSize=" + this.e + ", largeCount=" + this.g + ", largeSize=" + this.f + ", cost=" + this.h + "]";
    }
}
