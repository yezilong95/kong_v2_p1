package defpackage;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class qu implements f21, Cloneable {
    public static final qu G;
    public List E;
    public List F;

    static {
        qu quVar = new qu();
        List list = Collections.EMPTY_LIST;
        quVar.E = list;
        quVar.F = list;
        G = quVar;
    }

    @Override // defpackage.f21
    public final e21 a(m20 m20Var, m21 m21Var) {
        boolean z;
        boolean z2;
        Class cls = m21Var.a;
        boolean z3 = !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
        if (z3) {
            z = true;
        } else {
            b(true);
            z = false;
        }
        if (z3) {
            z2 = true;
        } else {
            b(false);
            z2 = false;
        }
        if (z || z2) {
            return new pu(this, z2, z, m20Var, m21Var);
        }
        return null;
    }

    public final void b(boolean z) {
        Iterator it = (z ? this.E : this.F).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
    }

    public final Object clone() {
        try {
            return (qu) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
