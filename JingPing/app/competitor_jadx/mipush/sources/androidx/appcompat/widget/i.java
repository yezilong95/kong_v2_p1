package androidx.appcompat.widget;

import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {
    public final /* synthetic */ SearchView.SearchAutoComplete E;

    public i(SearchView.SearchAutoComplete searchAutoComplete) {
        this.E = searchAutoComplete;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SearchView.SearchAutoComplete searchAutoComplete = this.E;
        if (searchAutoComplete.K) {
            ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
            searchAutoComplete.K = false;
        }
    }
}
