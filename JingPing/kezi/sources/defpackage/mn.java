package defpackage;

import android.os.Bundle;
import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class mn extends InputConnectionWrapper {
    public final EditText a;
    public final qm b;

    public mn(EditText editText, InputConnection inputConnection, EditorInfo editorInfo) {
        qm qmVar = new qm();
        super(inputConnection, false);
        this.a = editText;
        this.b = qmVar;
        if (hn.j != null) {
            hn hnVarA = hn.a();
            if (hnVarA.b() != 1 || editorInfo == null) {
                return;
            }
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            dn dnVar = hnVarA.e;
            dnVar.getClass();
            Bundle bundle = editorInfo.extras;
            e70 e70Var = (e70) dnVar.c.a;
            int iA = e70Var.a(4);
            bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iA != 0 ? ((ByteBuffer) e70Var.e).getInt(iA + e70Var.b) : 0);
            Bundle bundle2 = editorInfo.extras;
            dnVar.a.getClass();
            bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        Editable editableText = this.a.getEditableText();
        this.b.getClass();
        return qm.l(this, editableText, i, i2, false) || super.deleteSurroundingText(i, i2);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        Editable editableText = this.a.getEditableText();
        this.b.getClass();
        return qm.l(this, editableText, i, i2, true) || super.deleteSurroundingTextInCodePoints(i, i2);
    }
}
