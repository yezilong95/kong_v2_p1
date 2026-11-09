package defpackage;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.j;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class oo0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ Toolbar c;

    public /* synthetic */ oo0(Toolbar toolbar, int i) {
        this.b = i;
        this.c = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                j jVar = this.c.M;
                m60 m60Var = jVar == null ? null : jVar.c;
                if (m60Var != null) {
                    m60Var.collapseActionView();
                }
                break;
            default:
                this.c.n();
                break;
        }
    }
}
