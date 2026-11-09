package defpackage;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.j;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a11 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Toolbar F;

    public /* synthetic */ a11(Toolbar toolbar, int i) {
        this.E = i;
        this.F = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                j jVar = this.F.s0;
                de0 de0Var = jVar == null ? null : jVar.F;
                if (de0Var != null) {
                    de0Var.collapseActionView();
                }
                break;
            default:
                this.F.n();
                break;
        }
    }
}
