package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class od implements View.OnFocusChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ od(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        switch (this.a) {
            case 0:
                rd rdVar = (rd) this.b;
                rdVar.t(rdVar.u());
                break;
            case 1:
                for (EditText editText : (EditText[]) this.b) {
                    if (editText.hasFocus()) {
                    }
                    break;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) bh.b(view.getContext(), InputMethodManager.class);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;
            default:
                gm gmVar = (gm) this.b;
                gmVar.l = z;
                gmVar.q();
                if (!z) {
                    gmVar.t(false);
                    gmVar.m = false;
                }
                break;
        }
    }
}
