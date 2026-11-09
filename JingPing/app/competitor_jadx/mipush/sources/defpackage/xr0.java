package defpackage;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xr0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ SearchView F;

    public /* synthetic */ xr0(SearchView searchView, int i) {
        this.E = i;
        this.F = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        z81 z81VarH;
        z81 z81VarH2;
        switch (this.E) {
            case 0:
                SearchView searchView = this.F;
                EditText editText = searchView.N;
                if (editText.requestFocus()) {
                    editText.sendAccessibilityEvent(8);
                }
                if (searchView.g0 && (z81VarH = f51.h(editText)) != null) {
                    z81VarH.a.d();
                } else {
                    ((InputMethodManager) qi.b(editText.getContext(), InputMethodManager.class)).showSoftInput(editText, 1);
                }
                break;
            case 1:
                SearchView searchView2 = this.F;
                EditText editText2 = searchView2.N;
                editText2.clearFocus();
                SearchBar searchBar = searchView2.a0;
                if (searchBar != null) {
                    searchBar.requestFocus();
                }
                if (searchView2.g0 && (z81VarH2 = f51.h(editText2)) != null) {
                    z81VarH2.a.a();
                } else {
                    InputMethodManager inputMethodManager = (InputMethodManager) qi.b(editText2.getContext(), InputMethodManager.class);
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                    }
                }
                break;
            case 2:
                this.F.k();
                break;
            default:
                this.F.i();
                break;
        }
    }
}
