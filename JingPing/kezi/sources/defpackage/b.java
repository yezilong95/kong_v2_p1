package defpackage;

import java.io.EOFException;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final byte[] a;

    static {
        byte[] bytes = "0123456789abcdef".getBytes(zb.a);
        oy.i(bytes, "this as java.lang.String).getBytes(charset)");
        a = bytes;
    }

    public static final String a(long j, p9 p9Var) throws EOFException {
        oy.j(p9Var, "<this>");
        if (j > 0) {
            long j2 = j - 1;
            if (p9Var.t(j2) == 13) {
                String strH = p9Var.H(j2, zb.a);
                p9Var.I(2L);
                return strH;
            }
        }
        String strH2 = p9Var.H(j, zb.a);
        p9Var.I(1L);
        return strH2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
    
        if (r19 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0063, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008c, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int b(defpackage.p9 r17, defpackage.bb0 r18, boolean r19) {
        /*
            r0 = r17
            r1 = r18
            java.lang.String r2 = "<this>"
            defpackage.oy.j(r0, r2)
            java.lang.String r2 = "options"
            defpackage.oy.j(r1, r2)
            th0 r0 = r0.b
            r2 = -1
            if (r0 != 0) goto L17
            if (r19 == 0) goto L16
            goto L63
        L16:
            return r2
        L17:
            int r3 = r0.b
            int r4 = r0.c
            r5 = 0
            byte[] r6 = r0.a
            r8 = r0
            r9 = r2
            r7 = r5
        L21:
            int r10 = r7 + 1
            int[] r11 = r1.c
            r12 = r11[r7]
            int r7 = r7 + 2
            r10 = r11[r10]
            if (r10 == r2) goto L2e
            r9 = r10
        L2e:
            if (r8 != 0) goto L31
            goto L61
        L31:
            r10 = 0
            if (r12 >= 0) goto L82
            int r12 = r12 * (-1)
            int r13 = r12 + r7
        L38:
            int r12 = r3 + 1
            r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r14 = r7 + 1
            r7 = r11[r7]
            if (r3 == r7) goto L45
            goto L8c
        L45:
            if (r14 != r13) goto L49
            r3 = 1
            goto L4a
        L49:
            r3 = r5
        L4a:
            if (r12 != r4) goto L6b
            defpackage.oy.g(r8)
            th0 r4 = r8.f
            defpackage.oy.g(r4)
            int r6 = r4.b
            int r7 = r4.c
            byte[] r8 = r4.a
            if (r4 != r0) goto L65
            if (r3 == 0) goto L61
            r4 = r7
            r7 = r10
            goto L6e
        L61:
            if (r19 == 0) goto L8c
        L63:
            r0 = -2
            return r0
        L65:
            r16 = r7
            r7 = r4
            r4 = r16
            goto L6e
        L6b:
            r7 = r8
            r8 = r6
            r6 = r12
        L6e:
            if (r3 == 0) goto L7d
            r3 = r11[r14]
            r16 = r6
            r6 = r4
            r4 = r16
            r16 = r8
            r8 = r7
            r7 = r16
            goto La8
        L7d:
            r3 = r6
            r6 = r8
            r8 = r7
            r7 = r14
            goto L38
        L82:
            int r13 = r3 + 1
            r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r14 = r7 + r12
        L8a:
            if (r7 != r14) goto L8d
        L8c:
            return r9
        L8d:
            r15 = r11[r7]
            if (r3 != r15) goto Lb5
            int r7 = r7 + r12
            r3 = r11[r7]
            if (r13 != r4) goto La5
            th0 r8 = r8.f
            defpackage.oy.g(r8)
            int r4 = r8.b
            int r6 = r8.c
            byte[] r7 = r8.a
            if (r8 != r0) goto La8
            r8 = r10
            goto La8
        La5:
            r7 = r6
            r6 = r4
            r4 = r13
        La8:
            if (r3 < 0) goto Lab
            return r3
        Lab:
            int r3 = -r3
            r16 = r7
            r7 = r3
            r3 = r4
            r4 = r6
            r6 = r16
            goto L21
        Lb5:
            int r7 = r7 + 1
            goto L8a
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.b.b(p9, bb0, boolean):int");
    }
}
