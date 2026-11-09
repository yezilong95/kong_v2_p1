package defpackage;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class vn implements TextWatcher {
    public final EditText b;
    public un c;
    public boolean d = true;

    public vn(EditText editText) {
        this.b = editText;
    }

    public static void a(EditText editText, int i) {
        int length;
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            hn hnVarA = hn.a();
            if (editableText == null) {
                length = 0;
            } else {
                hnVarA.getClass();
                length = editableText.length();
            }
            hnVarA.e(editableText, 0, length);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        EditText editText = this.b;
        if (editText.isInEditMode() || !this.d || hn.j == null || i2 > i3 || !(charSequence instanceof Spannable)) {
            return;
        }
        int iB = hn.a().b();
        if (iB != 0) {
            if (iB == 1) {
                hn.a().e((Spannable) charSequence, i, i3 + i);
                return;
            } else if (iB != 3) {
                return;
            }
        }
        hn hnVarA = hn.a();
        if (this.c == null) {
            this.c = new un(editText);
        }
        hnVarA.f(this.c);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
