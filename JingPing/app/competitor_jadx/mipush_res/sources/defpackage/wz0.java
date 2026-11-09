package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class wz0 implements TextWatcher {
    public int E;
    public final /* synthetic */ EditText F;
    public final /* synthetic */ TextInputLayout G;

    public wz0(TextInputLayout textInputLayout, EditText editText) {
        this.G = textInputLayout;
        this.F = editText;
        this.E = editText.getLineCount();
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        TextInputLayout textInputLayout = this.G;
        textInputLayout.u(!textInputLayout.e1, false);
        if (textInputLayout.O) {
            textInputLayout.n(editable);
        }
        if (textInputLayout.W) {
            textInputLayout.v(editable);
        }
        EditText editText = this.F;
        int lineCount = editText.getLineCount();
        int i = this.E;
        if (lineCount != i) {
            if (lineCount < i) {
                WeakHashMap weakHashMap = f51.a;
                int minimumHeight = editText.getMinimumHeight();
                int i2 = textInputLayout.X0;
                if (minimumHeight != i2) {
                    editText.setMinimumHeight(i2);
                }
            }
            this.E = lineCount;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
