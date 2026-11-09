package defpackage;

import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class kh0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ SearchView c;

    public /* synthetic */ kh0(SearchView searchView, int i) {
        this.b = i;
        this.c = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                this.c.u();
                break;
            default:
                ei eiVar = this.c.P;
                if (eiVar instanceof jl0) {
                    eiVar.b(null);
                }
                break;
        }
    }
}
