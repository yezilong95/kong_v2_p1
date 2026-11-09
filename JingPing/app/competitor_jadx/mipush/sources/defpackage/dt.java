package defpackage;

import android.os.Bundle;
import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class dt extends InputConnectionWrapper {
    public final EditText a;
    public final di b;

    public dt(EditText editText, InputConnection inputConnection, EditorInfo editorInfo) {
        di diVar = new di(10);
        super(inputConnection, false);
        this.a = editText;
        this.b = diVar;
        if (zs.j != null) {
            zs zsVarA = zs.a();
            if (zsVarA.b() != 1 || editorInfo == null) {
                return;
            }
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            vs vsVar = zsVarA.e;
            vsVar.getClass();
            Bundle bundle = editorInfo.extras;
            we0 we0Var = (we0) vsVar.c.a;
            int iA = we0Var.a(4);
            bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iA != 0 ? ((ByteBuffer) we0Var.H).getInt(iA + we0Var.E) : 0);
            Bundle bundle2 = editorInfo.extras;
            vsVar.a.getClass();
            bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        Editable editableText = this.a.getEditableText();
        this.b.getClass();
        return di.g(this, editableText, i, i2, false) || super.deleteSurroundingText(i, i2);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        Editable editableText = this.a.getEditableText();
        this.b.getClass();
        return di.g(this, editableText, i, i2, true) || super.deleteSurroundingTextInCodePoints(i, i2);
    }
}
