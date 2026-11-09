package defpackage;

import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class bs0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ SearchView F;

    public /* synthetic */ bs0(SearchView searchView, int i) {
        this.E = i;
        this.F = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.u();
                break;
            default:
                xj xjVar = this.F.v0;
                if (xjVar instanceof xx0) {
                    xjVar.b(null);
                }
                break;
        }
    }
}
