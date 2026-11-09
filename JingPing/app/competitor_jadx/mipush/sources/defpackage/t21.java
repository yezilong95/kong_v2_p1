package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class t21 extends ReplacementSpan {
    public final ht b;
    public final Paint.FontMetricsInt a = new Paint.FontMetricsInt();
    public float c = 1.0f;

    public t21(ht htVar) {
        la0.i(htVar, "metadata cannot be null");
        this.b = htVar;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        zs.a().getClass();
        ht htVar = this.b;
        tb tbVar = htVar.b;
        Typeface typeface = (Typeface) tbVar.d;
        Typeface typeface2 = paint.getTypeface();
        paint.setTypeface(typeface);
        canvas.drawText((char[]) tbVar.b, htVar.a * 2, 2, f, i4, paint);
        paint.setTypeface(typeface2);
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.a;
        paint.getFontMetricsInt(fontMetricsInt2);
        float fAbs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        ht htVar = this.b;
        this.c = fAbs / (htVar.b().a(14) != 0 ? ((ByteBuffer) r8.H).getShort(r1 + r8.E) : (short) 0);
        ve0 ve0VarB = htVar.b();
        int iA = ve0VarB.a(14);
        if (iA != 0) {
            ((ByteBuffer) ve0VarB.H).getShort(iA + ve0VarB.E);
        }
        short s = (short) ((htVar.b().a(12) != 0 ? ((ByteBuffer) r5.H).getShort(r7 + r5.E) : (short) 0) * this.c);
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s;
    }
}
