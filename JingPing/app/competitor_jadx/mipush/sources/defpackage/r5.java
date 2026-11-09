package defpackage;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class r5 implements gi, pe0 {
    public final /* synthetic */ c6 E;

    public /* synthetic */ r5(c6 c6Var) {
        this.E = c6Var;
    }

    @Override // defpackage.pe0
    public void a(zd0 zd0Var, boolean z) {
        b6 b6Var;
        zd0 zd0VarK = zd0Var.k();
        int i = 0;
        boolean z2 = zd0VarK != zd0Var;
        if (z2) {
            zd0Var = zd0VarK;
        }
        c6 c6Var = this.E;
        b6[] b6VarArr = c6Var.p0;
        int length = b6VarArr != null ? b6VarArr.length : 0;
        while (true) {
            if (i < length) {
                b6Var = b6VarArr[i];
                if (b6Var != null && b6Var.h == zd0Var) {
                    break;
                } else {
                    i++;
                }
            } else {
                b6Var = null;
                break;
            }
        }
        if (b6Var != null) {
            if (!z2) {
                c6Var.s(b6Var, z);
            } else {
                c6Var.q(b6Var.a, b6Var, zd0VarK);
                c6Var.s(b6Var, true);
            }
        }
    }

    @Override // defpackage.pe0
    public boolean n(zd0 zd0Var) {
        Window.Callback callback;
        if (zd0Var != zd0Var.k()) {
            return true;
        }
        c6 c6Var = this.E;
        if (!c6Var.j0 || (callback = c6Var.P.getCallback()) == null || c6Var.u0) {
            return true;
        }
        callback.onMenuOpened(108, zd0Var);
        return true;
    }
}
