package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ou0 implements lu0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ou0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.lu0
    public final Iterator iterator() {
        switch (this.a) {
            case 0:
                io0 io0Var = (io0) this.b;
                mu0 mu0Var = new mu0();
                mu0Var.G = t40.C(io0Var, mu0Var, mu0Var);
                return mu0Var;
            default:
                return (Iterator) this.b;
        }
    }
}
