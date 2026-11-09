package defpackage;

import android.view.View;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wr0 implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ SearchView F;

    public /* synthetic */ wr0(SearchView searchView, int i) {
        this.E = i;
        this.F = searchView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SearchView searchView = this.F;
        switch (this.E) {
            case 0:
                searchView.N.setText("");
                searchView.i();
                break;
            case 1:
                int i = SearchView.k0;
                searchView.k();
                break;
            default:
                int i2 = SearchView.k0;
                if (!searchView.i0.equals(SearchView.TransitionState.HIDDEN) && !searchView.i0.equals(SearchView.TransitionState.HIDING)) {
                    searchView.S.j();
                    break;
                }
                break;
        }
    }
}
