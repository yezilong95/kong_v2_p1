package defpackage;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class h6 {
    public final TextView a;
    public final z8 b;

    public h6(TextView textView) {
        this.a = textView;
        this.b = new z8(textView);
    }

    public final InputFilter[] a(InputFilter[] inputFilterArr) {
        return ((qm) this.b.F).A(inputFilterArr);
    }

    public final void b(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        try {
            boolean z = typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_emojiCompatEnabled) ? typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTextView_emojiCompatEnabled, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            d(z);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void c(boolean z) {
        ((qm) this.b.F).q0(z);
    }

    public final void d(boolean z) {
        ((qm) this.b.F).r0(z);
    }
}
