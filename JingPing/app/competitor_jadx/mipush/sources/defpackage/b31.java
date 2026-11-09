package defpackage;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes.dex */
public final class b31 implements Spannable {
    public boolean E = false;
    public Spannable F;

    public b31(Spannable spannable) {
        this.F = spannable;
    }

    public final void a() {
        Spannable spannable = this.F;
        if (!this.E) {
            if ((Build.VERSION.SDK_INT < 28 ? new er0() : new a31()).a(spannable)) {
                this.F = new SpannableString(spannable);
            }
        }
        this.E = true;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.F.charAt(i);
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.F.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.F.codePoints();
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.F.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.F.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.F.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final Object[] getSpans(int i, int i2, Class cls) {
        return this.F.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.F.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i, int i2, Class cls) {
        return this.F.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        a();
        this.F.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i, int i2, int i3) {
        a();
        this.F.setSpan(obj, i, i2, i3);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i, int i2) {
        return this.F.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.F.toString();
    }

    public b31(CharSequence charSequence) {
        this.F = new SpannableString(charSequence);
    }
}
