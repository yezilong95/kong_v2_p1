package defpackage;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class pu extends e21 {
    public e21 a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ m20 d;
    public final /* synthetic */ m21 e;
    public final /* synthetic */ qu f;

    public pu(qu quVar, boolean z, boolean z2, m20 m20Var, m21 m21Var) {
        this.f = quVar;
        this.b = z;
        this.c = z2;
        this.d = m20Var;
        this.e = m21Var;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) throws IOException {
        if (this.b) {
            a60Var.T();
            return null;
        }
        e21 e21Var = this.a;
        if (e21Var == null) {
            m21 m21Var = this.e;
            m20 m20Var = this.d;
            List<f21> list = m20Var.e;
            f21 f21Var = this.f;
            if (!list.contains(f21Var)) {
                f21Var = m20Var.d;
            }
            boolean z = false;
            for (f21 f21Var2 : list) {
                if (z) {
                    e21 e21VarA = f21Var2.a(m20Var, m21Var);
                    if (e21VarA != null) {
                        this.a = e21VarA;
                        e21Var = e21VarA;
                    }
                } else if (f21Var2 == f21Var) {
                    z = true;
                }
            }
            throw new IllegalArgumentException("GSON cannot serialize " + m21Var);
        }
        return e21Var.a(a60Var);
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        if (this.c) {
            f60Var.C();
            return;
        }
        e21 e21Var = this.a;
        if (e21Var == null) {
            m21 m21Var = this.e;
            m20 m20Var = this.d;
            List<f21> list = m20Var.e;
            f21 f21Var = this.f;
            if (!list.contains(f21Var)) {
                f21Var = m20Var.d;
            }
            boolean z = false;
            for (f21 f21Var2 : list) {
                if (z) {
                    e21 e21VarA = f21Var2.a(m20Var, m21Var);
                    if (e21VarA != null) {
                        this.a = e21VarA;
                        e21Var = e21VarA;
                    }
                } else if (f21Var2 == f21Var) {
                    z = true;
                }
            }
            throw new IllegalArgumentException("GSON cannot serialize " + m21Var);
        }
        e21Var.b(f60Var, obj);
    }
}
