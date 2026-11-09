package defpackage;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class co implements ln0 {
    public final /* synthetic */ fo a;

    public co(fo foVar) {
        this.a = foVar;
    }

    public final void a(TextInputLayout textInputLayout) {
        fo foVar = this.a;
        if (foVar.t == textInputLayout.getEditText()) {
            return;
        }
        EditText editText = foVar.t;
        bo boVar = foVar.w;
        if (editText != null) {
            editText.removeTextChangedListener(boVar);
            if (foVar.t.getOnFocusChangeListener() == foVar.b().e()) {
                foVar.t.setOnFocusChangeListener(null);
            }
        }
        EditText editText2 = textInputLayout.getEditText();
        foVar.t = editText2;
        if (editText2 != null) {
            editText2.addTextChangedListener(boVar);
        }
        foVar.b().m(foVar.t);
        foVar.j(foVar.b());
    }
}
