package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class ah0 extends qd0 {
    public ss0 a;
    public final ViewPager2 b;
    public final zs0 c;
    public final LinearLayoutManager d;
    public int e;
    public int f;
    public final zg0 g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;

    public ah0(ViewPager2 viewPager2) {
        this.b = viewPager2;
        zs0 zs0Var = viewPager2.k;
        this.c = zs0Var;
        this.d = (LinearLayoutManager) zs0Var.getLayoutManager();
        this.g = new zg0();
        d();
    }

    @Override // defpackage.qd0
    public final void a(RecyclerView recyclerView, int i) {
        ss0 ss0Var;
        ss0 ss0Var2;
        int i2 = this.e;
        boolean z = true;
        if (!(i2 == 1 && this.f == 1) && i == 1) {
            this.e = 1;
            int i3 = this.i;
            if (i3 != -1) {
                this.h = i3;
                this.i = -1;
            } else if (this.h == -1) {
                this.h = this.d.J0();
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
        zg0 zg0Var = this.g;
        if (z && i == 0) {
            e();
            if (!this.k) {
                int i4 = zg0Var.a;
                if (i4 != -1 && (ss0Var2 = this.a) != null) {
                    ss0Var2.b(i4, 0.0f, 0);
                }
            } else if (zg0Var.c == 0) {
                int i5 = this.h;
                int i6 = zg0Var.a;
                if (i5 != i6 && (ss0Var = this.a) != null) {
                    ss0Var.c(i6);
                }
            }
            c(0);
            d();
        }
        if (this.e == 2 && i == 0 && this.l) {
            e();
            if (zg0Var.c == 0) {
                int i7 = this.i;
                int i8 = zg0Var.a;
                if (i7 != i8) {
                    if (i8 == -1) {
                        i8 = 0;
                    }
                    ss0 ss0Var3 = this.a;
                    if (ss0Var3 != null) {
                        ss0Var3.c(i8);
                    }
                }
                c(0);
                d();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    @Override // defpackage.qd0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(androidx.recyclerview.widget.RecyclerView r6, int r7, int r8) {
        /*
            r5 = this;
            r6 = 1
            r5.k = r6
            r5.e()
            boolean r0 = r5.j
            zg0 r1 = r5.g
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
            us0 r8 = r8.h
            int r8 = r8.A()
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
            ss0 r8 = r5.a
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
            ss0 r8 = r5.a
            if (r8 == 0) goto L50
            r8.c(r7)
        L50:
            int r7 = r1.a
            if (r7 != r2) goto L55
            r7 = r3
        L55:
            float r8 = r1.b
            int r0 = r1.c
            ss0 r4 = r5.a
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ah0.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i) {
        if ((this.e == 3 && this.f == 0) || this.f == i) {
            return;
        }
        this.f = i;
        ss0 ss0Var = this.a;
        if (ss0Var != null) {
            ss0Var.a(i);
        }
    }

    public final void d() {
        this.e = 0;
        this.f = 0;
        zg0 zg0Var = this.g;
        zg0Var.a = -1;
        zg0Var.b = 0.0f;
        zg0Var.c = 0;
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.l = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e() {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ah0.e():void");
    }
}
