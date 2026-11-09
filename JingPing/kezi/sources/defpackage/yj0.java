package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class yj0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ zj0 c;
    public final /* synthetic */ vj d;

    public /* synthetic */ yj0(vj vjVar, zj0 zj0Var, int i) {
        this.b = i;
        this.d = vjVar;
        this.c = zj0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ArrayList arrayList = this.d.b;
                zj0 zj0Var = this.c;
                if (arrayList.contains(zj0Var)) {
                    hk.a(zj0Var.c.F, zj0Var.a);
                }
                break;
            default:
                vj vjVar = this.d;
                ArrayList arrayList2 = vjVar.b;
                zj0 zj0Var2 = this.c;
                arrayList2.remove(zj0Var2);
                vjVar.c.remove(zj0Var2);
                break;
        }
    }
}
