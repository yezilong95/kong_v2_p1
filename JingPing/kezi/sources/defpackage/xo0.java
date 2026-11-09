package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class xo0 extends u50 implements gn0 {
    public final Paint.FontMetrics A;
    public final hn0 B;
    public final k80 C;
    public final Rect D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public float K;
    public float L;
    public float M;
    public float N;
    public CharSequence y;
    public final Context z;

    public xo0(Context context, int i) {
        super(context, null, 0, i);
        this.A = new Paint.FontMetrics();
        hn0 hn0Var = new hn0(this);
        this.B = hn0Var;
        this.C = new k80(2, this);
        this.D = new Rect();
        this.K = 1.0f;
        this.L = 1.0f;
        this.M = 0.5f;
        this.N = 1.0f;
        this.z = context;
        TextPaint textPaint = hn0Var.a;
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Canvas canvas2;
        canvas.save();
        float fW = w();
        float f = (float) (-((Math.sqrt(2.0d) * ((double) this.I)) - ((double) this.I)));
        canvas.scale(this.K, this.L, (getBounds().width() * 0.5f) + getBounds().left, (getBounds().height() * this.M) + getBounds().top);
        canvas.translate(fW, f);
        super.draw(canvas);
        if (this.y == null) {
            canvas2 = canvas;
        } else {
            float fCenterY = getBounds().centerY();
            hn0 hn0Var = this.B;
            TextPaint textPaint = hn0Var.a;
            Paint.FontMetrics fontMetrics = this.A;
            textPaint.getFontMetrics(fontMetrics);
            int i = (int) (fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f));
            en0 en0Var = hn0Var.g;
            TextPaint textPaint2 = hn0Var.a;
            if (en0Var != null) {
                textPaint2.drawableState = getState();
                hn0Var.g.e(this.z, textPaint2, hn0Var.b);
                textPaint2.setAlpha((int) (this.N * 255.0f));
            }
            CharSequence charSequence = this.y;
            canvas2 = canvas;
            canvas2.drawText(charSequence, 0, charSequence.length(), r0.centerX(), i, textPaint2);
        }
        canvas2.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) Math.max(this.B.a.getTextSize(), this.G);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float f = this.E * 2;
        CharSequence charSequence = this.y;
        return (int) Math.max(f + (charSequence == null ? 0.0f : this.B.a(charSequence.toString())), this.F);
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        ii0 ii0VarG = this.b.a.g();
        ii0VarG.k = x();
        setShapeAppearanceModel(ii0VarG.a());
    }

    public final float w() {
        int i;
        Rect rect = this.D;
        if (((rect.right - getBounds().right) - this.J) - this.H < 0) {
            i = ((rect.right - getBounds().right) - this.J) - this.H;
        } else {
            if (((rect.left - getBounds().left) - this.J) + this.H <= 0) {
                return 0.0f;
            }
            i = ((rect.left - getBounds().left) - this.J) + this.H;
        }
        return i;
    }

    public final ca0 x() {
        float f = -w();
        float fWidth = ((float) (((double) getBounds().width()) - (Math.sqrt(2.0d) * ((double) this.I)))) / 2.0f;
        return new ca0(new q40(this.I), Math.min(Math.max(f, -fWidth), fWidth));
    }
}
