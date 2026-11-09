package defpackage;

import android.view.View;
import com.google.android.material.search.SearchView;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class eh0 implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ SearchView c;

    public /* synthetic */ eh0(SearchView searchView, int i) {
        this.b = i;
        this.c = searchView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SearchView searchView = this.c;
        switch (this.b) {
            case 0:
                searchView.k.setText(ExtensionRequestData.EMPTY_VALUE);
                searchView.i();
                break;
            case 1:
                int i = SearchView.E;
                searchView.k();
                break;
            default:
                int i2 = SearchView.E;
                if (!searchView.C.equals(SearchView.TransitionState.HIDDEN) && !searchView.C.equals(SearchView.TransitionState.HIDING)) {
                    searchView.p.j();
                    break;
                }
                break;
        }
    }
}
