package defpackage;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class e9 implements ht0 {
    public final boolean b;
    public final Object c;

    public e9(qm qmVar, boolean z) {
        this.c = qmVar;
        this.b = z;
    }

    public boolean a() {
        return this.b;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b(java.lang.CharSequence r7, int r8) {
        /*
            r6 = this;
            r0 = 1
            if (r7 == 0) goto L44
            if (r8 < 0) goto L44
            int r1 = r7.length()
            int r1 = r1 - r8
            if (r1 < 0) goto L44
            java.lang.Object r1 = r6.c
            qm r1 = (defpackage.qm) r1
            if (r1 != 0) goto L17
            boolean r7 = r6.a()
            return r7
        L17:
            r1 = 0
            r2 = 2
            r3 = r1
            r4 = r2
        L1b:
            if (r3 >= r8) goto L39
            if (r4 != r2) goto L39
            char r4 = r7.charAt(r3)
            byte r4 = java.lang.Character.getDirectionality(r4)
            e9 r5 = defpackage.fn0.a
            if (r4 == 0) goto L36
            if (r4 == r0) goto L34
            if (r4 == r2) goto L34
            switch(r4) {
                case 14: goto L36;
                case 15: goto L36;
                case 16: goto L34;
                case 17: goto L34;
                default: goto L32;
            }
        L32:
            r4 = r2
            goto L37
        L34:
            r4 = r1
            goto L37
        L36:
            r4 = r0
        L37:
            int r3 = r3 + r0
            goto L1b
        L39:
            if (r4 == 0) goto L43
            if (r4 == r0) goto L42
            boolean r7 = r6.a()
            return r7
        L42:
            return r1
        L43:
            return r0
        L44:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e9.b(java.lang.CharSequence, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007b  */
    @Override // defpackage.ht0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public defpackage.xu0 f(android.view.View r11, defpackage.xu0 r12, defpackage.it0 r13) {
        /*
            r10 = this;
            uu0 r0 = r12.a
            r1 = 7
            jy r1 = r0.f(r1)
            r2 = 32
            jy r0 = r0.f(r2)
            int r2 = r1.b
            java.lang.Object r3 = r10.c
            com.google.android.material.bottomsheet.BottomSheetBehavior r3 = (com.google.android.material.bottomsheet.BottomSheetBehavior) r3
            r3.x = r2
            boolean r2 = defpackage.rp0.I(r11)
            int r4 = r11.getPaddingBottom()
            int r5 = r11.getPaddingLeft()
            int r6 = r11.getPaddingRight()
            boolean r7 = r3.p
            if (r7 == 0) goto L32
            int r4 = r12.a()
            r3.w = r4
            int r7 = r13.d
            int r4 = r4 + r7
        L32:
            boolean r7 = r3.q
            int r8 = r1.a
            if (r7 == 0) goto L40
            if (r2 == 0) goto L3d
            int r5 = r13.c
            goto L3f
        L3d:
            int r5 = r13.a
        L3f:
            int r5 = r5 + r8
        L40:
            boolean r7 = r3.r
            int r9 = r1.c
            if (r7 == 0) goto L4f
            if (r2 == 0) goto L4b
            int r13 = r13.a
            goto L4d
        L4b:
            int r13 = r13.c
        L4d:
            int r6 = r13 + r9
        L4f:
            android.view.ViewGroup$LayoutParams r13 = r11.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r13 = (android.view.ViewGroup.MarginLayoutParams) r13
            boolean r2 = r3.t
            r7 = 1
            if (r2 == 0) goto L62
            int r2 = r13.leftMargin
            if (r2 == r8) goto L62
            r13.leftMargin = r8
            r2 = r7
            goto L63
        L62:
            r2 = 0
        L63:
            boolean r8 = r3.u
            if (r8 == 0) goto L6e
            int r8 = r13.rightMargin
            if (r8 == r9) goto L6e
            r13.rightMargin = r9
            r2 = r7
        L6e:
            boolean r8 = r3.v
            if (r8 == 0) goto L7b
            int r8 = r13.topMargin
            int r1 = r1.b
            if (r8 == r1) goto L7b
            r13.topMargin = r1
            goto L7c
        L7b:
            r7 = r2
        L7c:
            if (r7 == 0) goto L81
            r11.setLayoutParams(r13)
        L81:
            int r13 = r11.getPaddingTop()
            r11.setPadding(r5, r13, r6, r4)
            boolean r11 = r10.b
            if (r11 == 0) goto L90
            int r13 = r0.d
            r3.n = r13
        L90:
            boolean r13 = r3.p
            if (r13 != 0) goto L98
            if (r11 == 0) goto L97
            goto L98
        L97:
            return r12
        L98:
            r3.Q()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e9.f(android.view.View, xu0, it0):xu0");
    }

    public e9(BottomSheetBehavior bottomSheetBehavior, boolean z) {
        this.c = bottomSheetBehavior;
        this.b = z;
    }
}
