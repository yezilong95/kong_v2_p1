package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class mw0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ nw0 F;
    public final /* synthetic */ on G;

    public /* synthetic */ mw0(on onVar, nw0 nw0Var, int i) {
        this.E = i;
        this.G = onVar;
        this.F = nw0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ArrayList arrayList = this.G.b;
                nw0 nw0Var = this.F;
                if (arrayList.contains(nw0Var)) {
                    zn.a(nw0Var.c.i0, nw0Var.a);
                }
                break;
            default:
                on onVar = this.G;
                ArrayList arrayList2 = onVar.b;
                nw0 nw0Var2 = this.F;
                arrayList2.remove(nw0Var2);
                onVar.c.remove(nw0Var2);
                break;
        }
    }
}
