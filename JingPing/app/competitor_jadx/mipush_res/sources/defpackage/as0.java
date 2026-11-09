package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class as0 implements TextWatcher {
    public final /* synthetic */ int E;
    public final /* synthetic */ ViewGroup F;

    public /* synthetic */ as0(ViewGroup viewGroup, int i) {
        this.E = i;
        this.F = viewGroup;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        int i = this.E;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = this.E;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        switch (this.E) {
            case 0:
                SearchView searchView = (SearchView) this.F;
                Editable text = searchView.T.getText();
                searchView.C0 = text;
                boolean zIsEmpty = TextUtils.isEmpty(text);
                searchView.x(!zIsEmpty);
                int i4 = 8;
                if (searchView.B0 && !searchView.u0 && zIsEmpty) {
                    searchView.b0.setVisibility(8);
                    i4 = 0;
                }
                searchView.d0.setVisibility(i4);
                searchView.t();
                searchView.w();
                charSequence.toString();
                break;
            default:
                ((com.google.android.material.search.SearchView) this.F).O.setVisibility(charSequence.length() > 0 ? 0 : 8);
                break;
        }
    }

    private final void a(Editable editable) {
    }

    private final void b(Editable editable) {
    }

    private final void c(int i, int i2, int i3, CharSequence charSequence) {
    }

    private final void d(int i, int i2, int i3, CharSequence charSequence) {
    }
}
