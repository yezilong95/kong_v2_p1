package defpackage;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class hx0 {
    public CharSequence a;
    public final TextPaint b;
    public final int c;
    public int d;
    public boolean k;
    public Layout.Alignment e = Layout.Alignment.ALIGN_NORMAL;
    public int f = Integer.MAX_VALUE;
    public float g = 0.0f;
    public float h = 1.0f;
    public int i = 1;
    public boolean j = true;
    public TextUtils.TruncateAt l = null;

    public hx0(CharSequence charSequence, TextPaint textPaint, int i) {
        this.a = charSequence;
        this.b = textPaint;
        this.c = i;
        this.d = charSequence.length();
    }

    public final StaticLayout a() {
        if (this.a == null) {
            this.a = "";
        }
        int iMax = Math.max(0, this.c);
        CharSequence charSequenceEllipsize = this.a;
        int i = this.f;
        TextPaint textPaint = this.b;
        if (i == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint, iMax, this.l);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.d);
        this.d = iMin;
        if (this.k && this.f == 1) {
            this.e = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, 0, iMin, textPaint, iMax);
        builderObtain.setAlignment(this.e);
        builderObtain.setIncludePad(this.j);
        builderObtain.setTextDirection(this.k ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.l;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.f);
        float f = this.g;
        if (f != 0.0f || this.h != 1.0f) {
            builderObtain.setLineSpacing(f, this.h);
        }
        if (this.f > 1) {
            builderObtain.setHyphenationFrequency(this.i);
        }
        return builderObtain.build();
    }
}
