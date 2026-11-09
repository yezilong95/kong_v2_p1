package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class n00 implements w20, kr0, s51 {
    public final r51 E;
    public z80 F = null;
    public i6 G = null;

    public n00(vz vzVar, r51 r51Var) {
        this.E = r51Var;
    }

    @Override // defpackage.kr0
    public final i6 a() {
        e();
        return (i6) this.G.G;
    }

    public final void b(Lifecycle$Event lifecycle$Event) {
        this.F.e(lifecycle$Event);
    }

    @Override // defpackage.s51
    public final r51 d() {
        e();
        return this.E;
    }

    public final void e() {
        if (this.F == null) {
            this.F = new z80(this);
            this.G = new i6(new jr0(this, new qg(this, 2)));
        }
    }

    @Override // defpackage.x80
    public final s80 getLifecycle() {
        e();
        return this.F;
    }
}
