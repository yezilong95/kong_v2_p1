package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class h21 implements f21 {
    public final /* synthetic */ int E;
    public final /* synthetic */ Class F;
    public final /* synthetic */ e21 G;

    public /* synthetic */ h21(Class cls, e21 e21Var, int i) {
        this.E = i;
        this.F = cls;
        this.G = e21Var;
    }

    @Override // defpackage.f21
    public final e21 a(m20 m20Var, m21 m21Var) {
        switch (this.E) {
            case 0:
                if (m21Var.a == this.F) {
                    return this.G;
                }
                return null;
            default:
                Class cls = this.F;
                Class<?> cls2 = m21Var.a;
                if (cls.isAssignableFrom(cls2)) {
                    return new p8(this, cls2);
                }
                return null;
        }
    }

    public final String toString() {
        switch (this.E) {
            case 0:
                return "Factory[type=" + this.F.getName() + ",adapter=" + this.G + "]";
            default:
                return "Factory[typeHierarchy=" + this.F.getName() + ",adapter=" + this.G + "]";
        }
    }
}
