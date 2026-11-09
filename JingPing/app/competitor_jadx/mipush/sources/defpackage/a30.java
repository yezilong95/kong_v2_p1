package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class a30 extends q71 {
    @Override // defpackage.xn
    public final void a(xn xnVar) {
        q9 q9Var = (q9) this.b;
        int i = q9Var.i0;
        ao aoVar = this.h;
        Iterator it = aoVar.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = ((ao) it.next()).g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i == 0 || i == 2) {
            aoVar.d(i3 + q9Var.k0);
        } else {
            aoVar.d(i2 + q9Var.k0);
        }
    }

    @Override // defpackage.q71
    public final void d() {
        yh yhVar = this.b;
        if (yhVar instanceof q9) {
            ao aoVar = this.h;
            aoVar.b = true;
            q9 q9Var = (q9) yhVar;
            int i = q9Var.i0;
            boolean z = q9Var.j0;
            int i2 = 0;
            if (i == 0) {
                aoVar.e = 4;
                while (i2 < q9Var.h0) {
                    yh yhVar2 = q9Var.g0[i2];
                    if (z || yhVar2.X != 8) {
                        ao aoVar2 = yhVar2.d.h;
                        aoVar2.k.add(aoVar);
                        aoVar.l.add(aoVar2);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 1) {
                aoVar.e = 5;
                while (i2 < q9Var.h0) {
                    yh yhVar3 = q9Var.g0[i2];
                    if (z || yhVar3.X != 8) {
                        ao aoVar3 = yhVar3.d.i;
                        aoVar3.k.add(aoVar);
                        aoVar.l.add(aoVar3);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 2) {
                aoVar.e = 6;
                while (i2 < q9Var.h0) {
                    yh yhVar4 = q9Var.g0[i2];
                    if (z || yhVar4.X != 8) {
                        ao aoVar4 = yhVar4.e.h;
                        aoVar4.k.add(aoVar);
                        aoVar.l.add(aoVar4);
                    }
                    i2++;
                }
                m(this.b.e.h);
                m(this.b.e.i);
                return;
            }
            if (i != 3) {
                return;
            }
            aoVar.e = 7;
            while (i2 < q9Var.h0) {
                yh yhVar5 = q9Var.g0[i2];
                if (z || yhVar5.X != 8) {
                    ao aoVar5 = yhVar5.e.i;
                    aoVar5.k.add(aoVar);
                    aoVar.l.add(aoVar5);
                }
                i2++;
            }
            m(this.b.e.h);
            m(this.b.e.i);
        }
    }

    @Override // defpackage.q71
    public final void e() {
        yh yhVar = this.b;
        if (yhVar instanceof q9) {
            int i = ((q9) yhVar).i0;
            ao aoVar = this.h;
            if (i == 0 || i == 1) {
                yhVar.P = aoVar.g;
            } else {
                yhVar.Q = aoVar.g;
            }
        }
    }

    @Override // defpackage.q71
    public final void f() {
        this.c = null;
        this.h.c();
    }

    @Override // defpackage.q71
    public final boolean k() {
        return false;
    }

    public final void m(ao aoVar) {
        ao aoVar2 = this.h;
        aoVar2.k.add(aoVar);
        aoVar.l.add(aoVar2);
    }
}
