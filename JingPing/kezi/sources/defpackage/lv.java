package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class lv extends ut0 {
    @Override // defpackage.fk
    public final void a(fk fkVar) {
        ik ikVar = this.h;
        if (ikVar.c && !ikVar.j) {
            ikVar.d((int) ((((ik) ikVar.l.get(0)).g * ((kv) this.b).g0) + 0.5f));
        }
    }

    @Override // defpackage.ut0
    public final void d() {
        mg mgVar = this.b;
        kv kvVar = (kv) mgVar;
        int i = kvVar.h0;
        int i2 = kvVar.i0;
        int i3 = kvVar.k0;
        ik ikVar = this.h;
        if (i3 == 1) {
            if (i != -1) {
                ikVar.l.add(mgVar.K.d.h);
                this.b.K.d.h.k.add(ikVar);
                ikVar.f = i;
            } else if (i2 != -1) {
                ikVar.l.add(mgVar.K.d.i);
                this.b.K.d.i.k.add(ikVar);
                ikVar.f = -i2;
            } else {
                ikVar.b = true;
                ikVar.l.add(mgVar.K.d.i);
                this.b.K.d.i.k.add(ikVar);
            }
            m(this.b.d.h);
            m(this.b.d.i);
            return;
        }
        if (i != -1) {
            ikVar.l.add(mgVar.K.e.h);
            this.b.K.e.h.k.add(ikVar);
            ikVar.f = i;
        } else if (i2 != -1) {
            ikVar.l.add(mgVar.K.e.i);
            this.b.K.e.i.k.add(ikVar);
            ikVar.f = -i2;
        } else {
            ikVar.b = true;
            ikVar.l.add(mgVar.K.e.i);
            this.b.K.e.i.k.add(ikVar);
        }
        m(this.b.e.h);
        m(this.b.e.i);
    }

    @Override // defpackage.ut0
    public final void e() {
        mg mgVar = this.b;
        int i = ((kv) mgVar).k0;
        ik ikVar = this.h;
        if (i == 1) {
            mgVar.P = ikVar.g;
        } else {
            mgVar.Q = ikVar.g;
        }
    }

    @Override // defpackage.ut0
    public final void f() {
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
