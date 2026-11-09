package defpackage;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ud0 extends wf0 {
    public final yq0 l = new yq0();

    @Override // defpackage.ea0
    public final void d() {
        Iterator it = this.l.iterator();
        while (true) {
            uq0 uq0Var = (uq0) it;
            if (!uq0Var.hasNext()) {
                return;
            }
            td0 td0Var = (td0) ((Map.Entry) uq0Var.next()).getValue();
            td0Var.a.c(td0Var);
        }
    }

    @Override // defpackage.ea0
    public final void e() {
        Iterator it = this.l.iterator();
        while (true) {
            uq0 uq0Var = (uq0) it;
            if (!uq0Var.hasNext()) {
                return;
            }
            td0 td0Var = (td0) ((Map.Entry) uq0Var.next()).getValue();
            ea0 ea0Var = td0Var.a;
            ea0Var.getClass();
            ea0.a("removeObserver");
            da0 da0Var = (da0) ea0Var.b.b(td0Var);
            if (da0Var != null) {
                da0Var.a(false);
            }
        }
    }

    public final void h(ea0 ea0Var, ti0 ti0Var) {
        Object obj;
        if (ea0Var == null) {
            throw new NullPointerException("source cannot be null");
        }
        td0 td0Var = new td0(ea0Var, ti0Var);
        yq0 yq0Var = this.l;
        vq0 vq0VarA = yq0Var.a(ea0Var);
        if (vq0VarA != null) {
            obj = vq0VarA.F;
        } else {
            vq0 vq0Var = new vq0(ea0Var, td0Var);
            yq0Var.H++;
            vq0 vq0Var2 = yq0Var.F;
            if (vq0Var2 == null) {
                yq0Var.E = vq0Var;
                yq0Var.F = vq0Var;
            } else {
                vq0Var2.G = vq0Var;
                vq0Var.H = vq0Var2;
                yq0Var.F = vq0Var;
            }
            obj = null;
        }
        td0 td0Var2 = (td0) obj;
        if (td0Var2 != null && td0Var2.b != ti0Var) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (td0Var2 == null && this.c > 0) {
            ea0Var.c(td0Var);
        }
    }
}
