package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes.dex */
public final class te extends Drawable implements Animatable {
    public static final LinearInterpolator K = new LinearInterpolator();
    public static final sv L = new sv(1);
    public static final int[] M = {-16777216};
    public final se E;
    public float F;
    public final Resources G;
    public final ValueAnimator H;
    public float I;
    public boolean J;

    public te(Context context) {
        context.getClass();
        this.G = context.getResources();
        se seVar = new se();
        this.E = seVar;
        seVar.i = M;
        seVar.a(0);
        seVar.h = 2.5f;
        seVar.b.setStrokeWidth(2.5f);
        invalidateSelf();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new qe(this, seVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(K);
        valueAnimatorOfFloat.addListener(new re(this, seVar));
        this.H = valueAnimatorOfFloat;
    }

    public static void d(float f, se seVar) {
        if (f <= 0.75f) {
            seVar.u = seVar.i[seVar.j];
            return;
        }
        float f2 = (f - 0.75f) / 0.25f;
        int[] iArr = seVar.i;
        int i = seVar.j;
        int i2 = iArr[i];
        int i3 = iArr[(i + 1) % iArr.length];
        seVar.u = ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r1) * f2))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r3) * f2))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r4) * f2))) << 8) | ((i2 & 255) + ((int) (f2 * ((i3 & 255) - r2))));
    }

    public final void a(float f, se seVar, boolean z) {
        float interpolation;
        float interpolation2;
        if (this.J) {
            d(f, seVar);
            float fFloor = (float) (Math.floor(seVar.m / 0.8f) + 1.0d);
            float f2 = seVar.k;
            float f3 = seVar.l;
            seVar.e = (((f3 - 0.01f) - f2) * f) + f2;
            seVar.f = f3;
            float f4 = seVar.m;
            seVar.g = zn.d(fFloor, f4, f, f4);
            return;
        }
        if (f != 1.0f || z) {
            float f5 = seVar.m;
            sv svVar = L;
            if (f < 0.5f) {
                interpolation = seVar.k;
                interpolation2 = (svVar.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float f6 = seVar.k + 0.79f;
                interpolation = f6 - (((1.0f - svVar.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = f6;
            }
            float f7 = (0.20999998f * f) + f5;
            float f8 = (f + this.I) * 216.0f;
            seVar.e = interpolation;
            seVar.f = interpolation2;
            seVar.g = f7;
            this.F = f8;
        }
    }

    public final void b(float f, float f2, float f3, float f4) {
        float f5 = this.G.getDisplayMetrics().density;
        float f6 = f2 * f5;
        se seVar = this.E;
        seVar.h = f6;
        seVar.b.setStrokeWidth(f6);
        seVar.q = f * f5;
        seVar.a(0);
        seVar.r = (int) (f3 * f5);
        seVar.s = (int) (f4 * f5);
    }

    public final void c(int i) {
        if (i == 0) {
            b(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            b(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.F, bounds.exactCenterX(), bounds.exactCenterY());
        se seVar = this.E;
        RectF rectF = seVar.a;
        float f = seVar.q;
        float fMin = (seVar.h / 2.0f) + f;
        if (f <= 0.0f) {
            fMin = (Math.min(bounds.width(), bounds.height()) / 2.0f) - Math.max((seVar.r * seVar.p) / 2.0f, seVar.h / 2.0f);
        }
        rectF.set(bounds.centerX() - fMin, bounds.centerY() - fMin, bounds.centerX() + fMin, bounds.centerY() + fMin);
        float f2 = seVar.e;
        float f3 = seVar.g;
        float f4 = (f2 + f3) * 360.0f;
        float f5 = ((seVar.f + f3) * 360.0f) - f4;
        Paint paint = seVar.b;
        paint.setColor(seVar.u);
        paint.setAlpha(seVar.t);
        float f6 = seVar.h / 2.0f;
        rectF.inset(f6, f6);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, seVar.d);
        float f7 = -f6;
        rectF.inset(f7, f7);
        canvas.drawArc(rectF, f4, f5, false, paint);
        if (seVar.n) {
            Path path = seVar.o;
            if (path == null) {
                Path path2 = new Path();
                seVar.o = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            float fMin2 = Math.min(rectF.width(), rectF.height()) / 2.0f;
            float f8 = (seVar.r * seVar.p) / 2.0f;
            seVar.o.moveTo(0.0f, 0.0f);
            seVar.o.lineTo(seVar.r * seVar.p, 0.0f);
            Path path3 = seVar.o;
            float f9 = seVar.r;
            float f10 = seVar.p;
            path3.lineTo((f9 * f10) / 2.0f, seVar.s * f10);
            seVar.o.offset((rectF.centerX() + fMin2) - f8, (seVar.h / 2.0f) + rectF.centerY());
            seVar.o.close();
            Paint paint2 = seVar.c;
            paint2.setColor(seVar.u);
            paint2.setAlpha(seVar.t);
            canvas.save();
            canvas.rotate(f4 + f5, rectF.centerX(), rectF.centerY());
            canvas.drawPath(seVar.o, paint2);
            canvas.restore();
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.E.t;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.H.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.E.t = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.E.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.H.cancel();
        se seVar = this.E;
        float f = seVar.e;
        seVar.k = f;
        float f2 = seVar.f;
        seVar.l = f2;
        seVar.m = seVar.g;
        if (f2 != f) {
            this.J = true;
            this.H.setDuration(666L);
            this.H.start();
            return;
        }
        seVar.a(0);
        seVar.k = 0.0f;
        seVar.l = 0.0f;
        seVar.m = 0.0f;
        seVar.e = 0.0f;
        seVar.f = 0.0f;
        seVar.g = 0.0f;
        this.H.setDuration(1332L);
        this.H.start();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.H.cancel();
        this.F = 0.0f;
        se seVar = this.E;
        if (seVar.n) {
            seVar.n = false;
        }
        seVar.a(0);
        seVar.k = 0.0f;
        seVar.l = 0.0f;
        seVar.m = 0.0f;
        seVar.e = 0.0f;
        seVar.f = 0.0f;
        seVar.g = 0.0f;
        invalidateSelf();
    }
}
