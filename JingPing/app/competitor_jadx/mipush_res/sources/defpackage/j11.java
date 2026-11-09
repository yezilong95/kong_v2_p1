package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class j11 extends ld0 implements uz0 {
    public CharSequence c0;
    public final Context d0;
    public final Paint.FontMetrics e0;
    public final vz0 f0;
    public final ng0 g0;
    public final Rect h0;
    public int i0;
    public int j0;
    public int k0;
    public int l0;
    public boolean m0;
    public int n0;
    public int o0;
    public float p0;
    public float q0;
    public float r0;
    public float s0;

    public j11(Context context, int i) {
        super(context, null, 0, i);
        this.e0 = new Paint.FontMetrics();
        vz0 vz0Var = new vz0(this);
        this.f0 = vz0Var;
        this.g0 = new ng0(2, this);
        this.h0 = new Rect();
        this.p0 = 1.0f;
        this.q0 = 1.0f;
        this.r0 = 0.5f;
        this.s0 = 1.0f;
        this.d0 = context;
        TextPaint textPaint = vz0Var.a;
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Canvas canvas2;
        canvas.save();
        float fW = w();
        float f = (float) (-((Math.sqrt(2.0d) * ((double) this.n0)) - ((double) this.n0)));
        canvas.scale(this.p0, this.q0, (getBounds().width() * 0.5f) + getBounds().left, (getBounds().height() * this.r0) + getBounds().top);
        canvas.translate(fW, f);
        super.draw(canvas);
        if (this.c0 == null) {
            canvas2 = canvas;
        } else {
            float fCenterY = getBounds().centerY();
            vz0 vz0Var = this.f0;
            TextPaint textPaint = vz0Var.a;
            Paint.FontMetrics fontMetrics = this.e0;
            textPaint.getFontMetrics(fontMetrics);
            int i = (int) (fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f));
            sz0 sz0Var = vz0Var.g;
            TextPaint textPaint2 = vz0Var.a;
            if (sz0Var != null) {
                textPaint2.drawableState = getState();
                vz0Var.g.e(this.d0, textPaint2, vz0Var.b);
                textPaint2.setAlpha((int) (this.s0 * 255.0f));
            }
            CharSequence charSequence = this.c0;
            canvas2 = canvas;
            canvas2.drawText(charSequence, 0, charSequence.length(), r0.centerX(), i, textPaint2);
        }
        canvas2.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) Math.max(this.f0.a.getTextSize(), this.k0);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float f = this.i0 * 2;
        CharSequence charSequence = this.c0;
        return (int) Math.max(f + (charSequence == null ? 0.0f : this.f0.a(charSequence.toString())), this.j0);
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.m0) {
            xu0 xu0VarG = this.E.a.g();
            xu0VarG.k = x();
            setShapeAppearanceModel(xu0VarG.a());
        }
    }

    public final float w() {
        int i;
        Rect rect = this.h0;
        if (((rect.right - getBounds().right) - this.o0) - this.l0 < 0) {
            i = ((rect.right - getBounds().right) - this.o0) - this.l0;
        } else {
            if (((rect.left - getBounds().left) - this.o0) + this.l0 <= 0) {
                return 0.0f;
            }
            i = ((rect.left - getBounds().left) - this.o0) + this.l0;
        }
        return i;
    }

    public final ui0 x() {
        float f = -w();
        float fWidth = ((float) (((double) getBounds().width()) - (Math.sqrt(2.0d) * ((double) this.n0)))) / 2.0f;
        return new ui0(new gc0(this.n0), Math.min(Math.max(f, -fWidth), fWidth));
    }
}
