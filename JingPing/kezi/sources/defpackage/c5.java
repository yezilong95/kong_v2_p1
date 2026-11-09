package defpackage;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class c5 {
    public final TextView a;
    public final o70 b;

    public c5(TextView textView) {
        this.a = textView;
        this.b = new o70(textView);
    }

    public final InputFilter[] a(InputFilter[] inputFilterArr) {
        return ((rp0) this.b.c).z(inputFilterArr);
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
        ((rp0) this.b.c).T(z);
    }

    public final void d(boolean z) {
        ((rp0) this.b.c).U(z);
    }
}
