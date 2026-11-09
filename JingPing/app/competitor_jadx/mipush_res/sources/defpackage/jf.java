package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jf implements View.OnFocusChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ jf(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        switch (this.a) {
            case 0:
                mf mfVar = (mf) this.b;
                mfVar.t(mfVar.u());
                break;
            case 1:
                for (EditText editText : (EditText[]) this.b) {
                    if (editText.hasFocus()) {
                    }
                    break;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) qi.b(view.getContext(), InputMethodManager.class);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;
            default:
                jq jqVar = (jq) this.b;
                jqVar.l = z;
                jqVar.q();
                if (!z) {
                    jqVar.t(false);
                    jqVar.m = false;
                }
                break;
        }
    }
}
