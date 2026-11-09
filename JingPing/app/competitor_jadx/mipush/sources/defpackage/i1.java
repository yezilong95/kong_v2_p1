package defpackage;

import android.text.PrecomputedText;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class i1 {
    public static /* synthetic */ PrecomputedText.Params.Builder h(TextPaint textPaint) {
        return new PrecomputedText.Params.Builder(textPaint);
    }

    public static /* bridge */ /* synthetic */ boolean v(CharSequence charSequence) {
        return charSequence instanceof PrecomputedText;
    }
}
