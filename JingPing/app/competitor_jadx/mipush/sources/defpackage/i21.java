package defpackage;

import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class i21 implements f21 {
    public final /* synthetic */ Class E;
    public final /* synthetic */ Class F;
    public final /* synthetic */ e21 G;

    public i21(Class cls, Class cls2, e21 e21Var) {
        this.E = cls;
        this.F = cls2;
        this.G = e21Var;
    }

    @Override // defpackage.f21
    public final e21 a(m20 m20Var, m21 m21Var) {
        Class cls = this.E;
        Class cls2 = m21Var.a;
        if (cls2 == cls || cls2 == this.F) {
            return this.G;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.F.getName() + Marker.ANY_NON_NULL_MARKER + this.E.getName() + ",adapter=" + this.G + "]";
    }
}
