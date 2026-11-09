package defpackage;

import androidx.work.impl.WorkDatabaseVersions;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public final Class a;
    public final /* synthetic */ int b;

    public o(int i, Class cls) {
        this.b = i;
        this.a = cls;
    }

    public final void a(g0 g0Var) {
        if (!this.a.isInstance(g0Var)) {
            throw new IllegalStateException("unexpected object: ".concat(g0Var.getClass().getName()));
        }
    }

    public final g0 b(byte[] bArr) throws IOException {
        g0 g0VarL = g0.l(bArr);
        a(g0VarL);
        return g0VarL;
    }

    public g0 c(j0 j0Var) {
        switch (this.b) {
            case 1:
                return j0Var.t();
            case 4:
                return j0Var.u();
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                throw new IllegalStateException("unexpected implicit constructed encoding");
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return j0Var.v();
            case 17:
                return j0Var;
            case 18:
                return j0Var.w();
            default:
                throw new IllegalStateException("unexpected implicit constructed encoding");
        }
    }

    public final g0 d(j9 j9Var, boolean z) {
        t40.j(j9Var);
        g0 g0VarO = j9Var.o(z, this);
        a(g0VarO);
        return g0VarO;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }
}
