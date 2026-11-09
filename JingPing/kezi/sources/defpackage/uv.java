package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class uv extends ut0 {
    @Override // defpackage.fk
    public final void a(fk fkVar) {
        v7 v7Var = (v7) this.b;
        int i = v7Var.i0;
        ik ikVar = this.h;
        Iterator it = ikVar.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = ((ik) it.next()).g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i == 0 || i == 2) {
            ikVar.d(i3 + v7Var.k0);
        } else {
            ikVar.d(i2 + v7Var.k0);
        }
    }

    @Override // defpackage.ut0
    public final void d() {
        mg mgVar = this.b;
        if (mgVar instanceof v7) {
            ik ikVar = this.h;
            ikVar.b = true;
            v7 v7Var = (v7) mgVar;
            int i = v7Var.i0;
            boolean z = v7Var.j0;
            int i2 = 0;
            if (i == 0) {
                ikVar.e = 4;
                while (i2 < v7Var.h0) {
                    mg mgVar2 = v7Var.g0[i2];
                    if (z || mgVar2.X != 8) {
                        ik ikVar2 = mgVar2.d.h;
                        ikVar2.k.add(ikVar);
                        ikVar.l.add(ikVar2);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 1) {
                ikVar.e = 5;
                while (i2 < v7Var.h0) {
                    mg mgVar3 = v7Var.g0[i2];
                    if (z || mgVar3.X != 8) {
                        ik ikVar3 = mgVar3.d.i;
                        ikVar3.k.add(ikVar);
                        ikVar.l.add(ikVar3);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 2) {
                ikVar.e = 6;
                while (i2 < v7Var.h0) {
                    mg mgVar4 = v7Var.g0[i2];
                    if (z || mgVar4.X != 8) {
                        ik ikVar4 = mgVar4.e.h;
                        ikVar4.k.add(ikVar);
                        ikVar.l.add(ikVar4);
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
            ikVar.e = 7;
            while (i2 < v7Var.h0) {
                mg mgVar5 = v7Var.g0[i2];
                if (z || mgVar5.X != 8) {
                    ik ikVar5 = mgVar5.e.i;
                    ikVar5.k.add(ikVar);
                    ikVar.l.add(ikVar5);
                }
                i2++;
            }
            m(this.b.e.h);
            m(this.b.e.i);
        }
    }

    @Override // defpackage.ut0
    public final void e() {
        mg mgVar = this.b;
        if (mgVar instanceof v7) {
            int i = ((v7) mgVar).i0;
            ik ikVar = this.h;
            if (i == 0 || i == 1) {
                mgVar.P = ikVar.g;
            } else {
                mgVar.Q = ikVar.g;
            }
        }
    }

    @Override // defpackage.ut0
    public final void f() {
        this.c = null;
        this.h.c();
    }

    @Override // defpackage.ut0
    public final boolean k() {
        return false;
    }

    public final void m(ik ikVar) {
        ik ikVar2 = this.h;
        ikVar2.k.add(ikVar);
        ikVar.l.add(ikVar2);
    }
}
