package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class jh0 implements TextWatcher {
    public final /* synthetic */ int b;
    public final /* synthetic */ ViewGroup c;

    public /* synthetic */ jh0(ViewGroup viewGroup, int i) {
        this.b = i;
        this.c = viewGroup;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        switch (this.b) {
            case 0:
            case 1:
                break;
            default:
                TextInputLayout textInputLayout = (TextInputLayout) this.c;
                textInputLayout.u(!textInputLayout.A0, false);
                if (textInputLayout.l) {
                    textInputLayout.n(editable);
                }
                if (textInputLayout.t) {
                    textInputLayout.v(editable);
                }
                break;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = this.b;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        switch (this.b) {
            case 0:
                SearchView searchView = (SearchView) this.c;
                Editable text = searchView.q.getText();
                searchView.W = text;
                boolean zIsEmpty = TextUtils.isEmpty(text);
                searchView.x(!zIsEmpty);
                int i4 = 8;
                if (searchView.V && !searchView.O && zIsEmpty) {
                    searchView.v.setVisibility(8);
                    i4 = 0;
                }
                searchView.x.setVisibility(i4);
                searchView.t();
                searchView.w();
                charSequence.toString();
                break;
            case 1:
                ((com.google.android.material.search.SearchView) this.c).l.setVisibility(charSequence.length() > 0 ? 0 : 8);
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

    private final void e(int i, int i2, int i3, CharSequence charSequence) {
    }

    private final void f(int i, int i2, int i3, CharSequence charSequence) {
    }
}
