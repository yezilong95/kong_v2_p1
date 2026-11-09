package defpackage;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e60 extends c80 {
    public final lg0 l = new lg0();

    @Override // defpackage.b30
    public final void e() {
        Iterator it = this.l.iterator();
        while (true) {
            hg0 hg0Var = (hg0) it;
            if (!hg0Var.hasNext()) {
                return;
            }
            d60 d60Var = (d60) ((Map.Entry) hg0Var.next()).getValue();
            d60Var.a.d(d60Var);
        }
    }

    @Override // defpackage.b30
    public final void f() {
        Iterator it = this.l.iterator();
        while (true) {
            hg0 hg0Var = (hg0) it;
            if (!hg0Var.hasNext()) {
                return;
            }
            d60 d60Var = (d60) ((Map.Entry) hg0Var.next()).getValue();
            d60Var.a.h(d60Var);
        }
    }

    public final void j(b30 b30Var, ba0 ba0Var) {
        Object obj;
        if (b30Var == null) {
            throw new NullPointerException("source cannot be null");
        }
        d60 d60Var = new d60(b30Var, ba0Var);
        lg0 lg0Var = this.l;
        ig0 ig0VarA = lg0Var.a(b30Var);
        if (ig0VarA != null) {
            obj = ig0VarA.c;
        } else {
            ig0 ig0Var = new ig0(b30Var, d60Var);
            lg0Var.e++;
            ig0 ig0Var2 = lg0Var.c;
            if (ig0Var2 == null) {
                lg0Var.b = ig0Var;
                lg0Var.c = ig0Var;
            } else {
                ig0Var2.d = ig0Var;
                ig0Var.e = ig0Var2;
                lg0Var.c = ig0Var;
            }
            obj = null;
        }
        d60 d60Var2 = (d60) obj;
        if (d60Var2 != null && d60Var2.b != ba0Var) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (d60Var2 == null && this.c > 0) {
            b30Var.d(d60Var);
        }
    }
}
