package defpackage;

import android.text.PrecomputedText;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d0 {
    public static /* synthetic */ PrecomputedText.Params.Builder j(TextPaint textPaint) {
        return new PrecomputedText.Params.Builder(textPaint);
    }

    public static /* bridge */ /* synthetic */ boolean w(CharSequence charSequence) {
        return charSequence instanceof PrecomputedText;
    }
}
