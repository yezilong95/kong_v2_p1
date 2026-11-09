package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class yp0 extends ReplacementSpan {
    public final qn b;
    public final Paint.FontMetricsInt a = new Paint.FontMetricsInt();
    public float c = 1.0f;

    public yp0(qn qnVar) {
        k9.h(qnVar, "metadata cannot be null");
        this.b = qnVar;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        hn.a().getClass();
        qn qnVar = this.b;
        ba baVar = qnVar.b;
        Typeface typeface = (Typeface) baVar.d;
        Typeface typeface2 = paint.getTypeface();
        paint.setTypeface(typeface);
        canvas.drawText((char[]) baVar.b, qnVar.a * 2, 2, f, i4, paint);
        paint.setTypeface(typeface2);
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.a;
        paint.getFontMetricsInt(fontMetricsInt2);
        float fAbs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        qn qnVar = this.b;
        this.c = fAbs / (qnVar.b().a(14) != 0 ? ((ByteBuffer) r8.e).getShort(r1 + r8.b) : (short) 0);
        d70 d70VarB = qnVar.b();
        int iA = d70VarB.a(14);
        if (iA != 0) {
            ((ByteBuffer) d70VarB.e).getShort(iA + d70VarB.b);
        }
        short s = (short) ((qnVar.b().a(12) != 0 ? ((ByteBuffer) r5.e).getShort(r7 + r5.b) : (short) 0) * this.c);
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s;
    }
}
