package defpackage;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class vt implements zz0 {
    public final /* synthetic */ xt a;

    public vt(xt xtVar) {
        this.a = xtVar;
    }

    public final void a(TextInputLayout textInputLayout) {
        xt xtVar = this.a;
        if (xtVar.W == textInputLayout.getEditText()) {
            return;
        }
        EditText editText = xtVar.W;
        ut utVar = xtVar.c0;
        if (editText != null) {
            editText.removeTextChangedListener(utVar);
            if (xtVar.W.getOnFocusChangeListener() == xtVar.b().e()) {
                xtVar.W.setOnFocusChangeListener(null);
            }
        }
        EditText editText2 = textInputLayout.getEditText();
        xtVar.W = editText2;
        if (editText2 != null) {
            editText2.addTextChangedListener(utVar);
        }
        xtVar.b().m(xtVar.W);
        xtVar.j(xtVar.b());
    }
}
