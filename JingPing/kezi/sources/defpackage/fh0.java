package defpackage;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fh0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ SearchView c;

    public /* synthetic */ fh0(SearchView searchView, int i) {
        this.b = i;
        this.c = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ev0 ev0VarH;
        ev0 ev0VarH2;
        switch (this.b) {
            case 0:
                SearchView searchView = this.c;
                EditText editText = searchView.k;
                if (editText.requestFocus()) {
                    editText.sendAccessibilityEvent(8);
                }
                if (searchView.A && (ev0VarH = xr0.h(editText)) != null) {
                    ev0VarH.a.d();
                } else {
                    ((InputMethodManager) bh.b(editText.getContext(), InputMethodManager.class)).showSoftInput(editText, 1);
                }
                break;
            case 1:
                SearchView searchView2 = this.c;
                EditText editText2 = searchView2.k;
                editText2.clearFocus();
                SearchBar searchBar = searchView2.u;
                if (searchBar != null) {
                    searchBar.requestFocus();
                }
                if (searchView2.A && (ev0VarH2 = xr0.h(editText2)) != null) {
                    ev0VarH2.a.a();
                } else {
                    InputMethodManager inputMethodManager = (InputMethodManager) bh.b(editText2.getContext(), InputMethodManager.class);
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                    }
                }
                break;
            case 2:
                this.c.k();
                break;
            default:
                this.c.i();
                break;
        }
    }
}
