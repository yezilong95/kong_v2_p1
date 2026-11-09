package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class p20 extends q71 {
    @Override // defpackage.xn
    public final void a(xn xnVar) {
        ao aoVar = this.h;
        if (aoVar.c && !aoVar.j) {
            aoVar.d((int) ((((ao) aoVar.l.get(0)).g * ((o20) this.b).g0) + 0.5f));
        }
    }

    @Override // defpackage.q71
    public final void d() {
        yh yhVar = this.b;
        o20 o20Var = (o20) yhVar;
        int i = o20Var.h0;
        int i2 = o20Var.i0;
        int i3 = o20Var.k0;
        ao aoVar = this.h;
        if (i3 == 1) {
            if (i != -1) {
                aoVar.l.add(yhVar.K.d.h);
                this.b.K.d.h.k.add(aoVar);
                aoVar.f = i;
            } else if (i2 != -1) {
                aoVar.l.add(yhVar.K.d.i);
                this.b.K.d.i.k.add(aoVar);
                aoVar.f = -i2;
            } else {
                aoVar.b = true;
                aoVar.l.add(yhVar.K.d.i);
                this.b.K.d.i.k.add(aoVar);
            }
            m(this.b.d.h);
            m(this.b.d.i);
            return;
        }
        if (i != -1) {
            aoVar.l.add(yhVar.K.e.h);
            this.b.K.e.h.k.add(aoVar);
            aoVar.f = i;
        } else if (i2 != -1) {
            aoVar.l.add(yhVar.K.e.i);
            this.b.K.e.i.k.add(aoVar);
            aoVar.f = -i2;
        } else {
            aoVar.b = true;
            aoVar.l.add(yhVar.K.e.i);
            this.b.K.e.i.k.add(aoVar);
        }
        m(this.b.e.h);
        m(this.b.e.i);
    }

    @Override // defpackage.q71
    public final void e() {
        yh yhVar = this.b;
        int i = ((o20) yhVar).k0;
        ao aoVar = this.h;
        if (i == 1) {
            yhVar.P = aoVar.g;
        } else {
            yhVar.Q = aoVar.g;
        }
    }

    @Override // defpackage.q71
    public final void f() {
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
