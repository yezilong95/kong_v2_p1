package defpackage;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class ft implements InputFilter {
    public final TextView a;
    public et b;

    public ft(TextView textView) {
        this.a = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        TextView textView = this.a;
        if (textView.isInEditMode()) {
            return charSequence;
        }
        int iB = zs.a().b();
        if (iB != 0) {
            if (iB == 1) {
                if ((i4 == 0 && i3 == 0 && spanned.length() == 0 && charSequence == textView.getText()) || charSequence == null) {
                    return charSequence;
                }
                if (i != 0 || i2 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i, i2);
                }
                return zs.a().e(charSequence, 0, charSequence.length());
            }
            if (iB != 3) {
                return charSequence;
            }
        }
        zs zsVarA = zs.a();
        if (this.b == null) {
            this.b = new et(textView, this);
        }
        zsVarA.f(this.b);
        return charSequence;
    }
}
