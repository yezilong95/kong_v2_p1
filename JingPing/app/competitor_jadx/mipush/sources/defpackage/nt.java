package defpackage;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class nt implements TextWatcher {
    public final EditText E;
    public mt F;
    public boolean G = true;

    public nt(EditText editText) {
        this.E = editText;
    }

    public static void a(EditText editText, int i) {
        int length;
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            zs zsVarA = zs.a();
            if (editableText == null) {
                length = 0;
            } else {
                zsVarA.getClass();
                length = editableText.length();
            }
            zsVarA.e(editableText, 0, length);
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
        EditText editText = this.E;
        if (editText.isInEditMode() || !this.G || zs.j == null || i2 > i3 || !(charSequence instanceof Spannable)) {
            return;
        }
        int iB = zs.a().b();
        if (iB != 0) {
            if (iB == 1) {
                zs.a().e((Spannable) charSequence, i, i3 + i);
                return;
            } else if (iB != 3) {
                return;
            }
        }
        zs zsVarA = zs.a();
        if (this.F == null) {
            this.F = new mt(editText);
        }
        zsVarA.f(this.F);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
