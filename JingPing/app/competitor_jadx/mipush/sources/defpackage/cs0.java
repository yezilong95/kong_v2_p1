package defpackage;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class cs0 implements View.OnFocusChangeListener {
    public final /* synthetic */ SearchView a;

    public cs0(SearchView searchView) {
        this.a = searchView;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        SearchView searchView = this.a;
        View.OnFocusChangeListener onFocusChangeListener = searchView.r0;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(searchView, z);
        }
    }
}
