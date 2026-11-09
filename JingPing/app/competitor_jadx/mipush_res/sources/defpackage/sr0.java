package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class sr0 extends mn0 {
    public z51 a;
    public final ViewPager2 b;
    public final f61 c;
    public final LinearLayoutManager d;
    public int e;
    public int f;
    public final rr0 g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;

    public sr0(ViewPager2 viewPager2) {
        this.b = viewPager2;
        f61 f61Var = viewPager2.N;
        this.c = f61Var;
        this.d = (LinearLayoutManager) f61Var.getLayoutManager();
        this.g = new rr0();
        d();
    }

    @Override // defpackage.mn0
    public final void a(RecyclerView recyclerView, int i) {
        z51 z51Var;
        z51 z51Var2;
        int i2 = this.e;
        boolean z = true;
        if (!(i2 == 1 && this.f == 1) && i == 1) {
            this.e = 1;
            int i3 = this.i;
            if (i3 != -1) {
                this.h = i3;
                this.i = -1;
            } else if (this.h == -1) {
                this.h = this.d.M0();
            }
            c(1);
            return;
        }
        if ((i2 == 1 || i2 == 4) && i == 2) {
            if (this.k) {
                c(2);
                this.j = true;
                return;
            }
            return;
        }
        if (i2 != 1 && i2 != 4) {
            z = false;
        }
        rr0 rr0Var = this.g;
        if (z && i == 0) {
            e();
            if (!this.k) {
                int i4 = rr0Var.a;
                if (i4 != -1 && (z51Var2 = this.a) != null) {
                    z51Var2.b(i4, 0.0f, 0);
                }
            } else if (rr0Var.c == 0) {
                int i5 = this.h;
                int i6 = rr0Var.a;
                if (i5 != i6 && (z51Var = this.a) != null) {
                    z51Var.c(i6);
                }
            }
            c(0);
            d();
        }
        if (this.e == 2 && i == 0 && this.l) {
            e();
            if (rr0Var.c == 0) {
                int i7 = this.i;
                int i8 = rr0Var.a;
                if (i7 != i8) {
                    if (i8 == -1) {
                        i8 = 0;
                    }
                    z51 z51Var3 = this.a;
                    if (z51Var3 != null) {
                        z51Var3.c(i8);
                    }
                }
                c(0);
                d();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    @Override // defpackage.mn0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(androidx.recyclerview.widget.RecyclerView r6, int r7, int r8) {
        /*
            r5 = this;
            r6 = 1
            r5.k = r6
            r5.e()
            boolean r0 = r5.j
            rr0 r1 = r5.g
            r2 = -1
            r3 = 0
            if (r0 == 0) goto L40
            r5.j = r3
            if (r8 > 0) goto L28
            if (r8 != 0) goto L30
            if (r7 >= 0) goto L18
            r7 = r6
            goto L19
        L18:
            r7 = r3
        L19:
            androidx.viewpager2.widget.ViewPager2 r8 = r5.b
            b61 r8 = r8.K
            int r8 = r8.C()
            if (r8 != r6) goto L25
            r8 = r6
            goto L26
        L25:
            r8 = r3
        L26:
            if (r7 != r8) goto L30
        L28:
            int r7 = r1.c
            if (r7 == 0) goto L30
            int r7 = r1.a
            int r7 = r7 + r6
            goto L32
        L30:
            int r7 = r1.a
        L32:
            r5.i = r7
            int r8 = r5.h
            if (r8 == r7) goto L50
            z51 r8 = r5.a
            if (r8 == 0) goto L50
            r8.c(r7)
            goto L50
        L40:
            int r7 = r5.e
            if (r7 != 0) goto L50
            int r7 = r1.a
            if (r7 != r2) goto L49
            r7 = r3
        L49:
            z51 r8 = r5.a
            if (r8 == 0) goto L50
            r8.c(r7)
        L50:
            int r7 = r1.a
            if (r7 != r2) goto L55
            r7 = r3
        L55:
            float r8 = r1.b
            int r0 = r1.c
            z51 r4 = r5.a
            if (r4 == 0) goto L60
            r4.b(r7, r8, r0)
        L60:
            int r7 = r1.a
            int r8 = r5.i
            if (r7 == r8) goto L68
            if (r8 != r2) goto L76
        L68:
            int r7 = r1.c
            if (r7 != 0) goto L76
            int r7 = r5.f
            if (r7 == r6) goto L76
            r5.c(r3)
            r5.d()
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sr0.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i) {
        if ((this.e == 3 && this.f == 0) || this.f == i) {
            return;
        }
        this.f = i;
        z51 z51Var = this.a;
        if (z51Var != null) {
            z51Var.a(i);
        }
    }

    public final void d() {
        this.e = 0;
        this.f = 0;
        rr0 rr0Var = this.g;
        rr0Var.a = -1;
        rr0Var.b = 0.0f;
        rr0Var.c = 0;
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.l = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sr0.e():void");
    }
}
