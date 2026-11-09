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
public final class zc extends Drawable implements Animatable {
    public static final LinearInterpolator h = new LinearInterpolator();
    public static final mp i = new mp(1);
    public static final int[] j = {-16777216};
    public final yc b;
    public float c;
    public final Resources d;
    public final ValueAnimator e;
    public float f;
    public boolean g;

    public zc(Context context) {
        context.getClass();
        this.d = context.getResources();
        yc ycVar = new yc();
        this.b = ycVar;
        ycVar.i = j;
        ycVar.a(0);
        ycVar.h = 2.5f;
        ycVar.b.setStrokeWidth(2.5f);
        invalidateSelf();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new wc(this, ycVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(h);
        valueAnimatorOfFloat.addListener(new xc(this, ycVar));
        this.e = valueAnimatorOfFloat;
    }

    public static void d(float f, yc ycVar) {
        if (f <= 0.75f) {
            ycVar.u = ycVar.i[ycVar.j];
            return;
        }
        float f2 = (f - 0.75f) / 0.25f;
        int[] iArr = ycVar.i;
        int i2 = ycVar.j;
        int i3 = iArr[i2];
        int i4 = iArr[(i2 + 1) % iArr.length];
        ycVar.u = ((((i3 >> 24) & 255) + ((int) ((((i4 >> 24) & 255) - r1) * f2))) << 24) | ((((i3 >> 16) & 255) + ((int) ((((i4 >> 16) & 255) - r3) * f2))) << 16) | ((((i3 >> 8) & 255) + ((int) ((((i4 >> 8) & 255) - r4) * f2))) << 8) | ((i3 & 255) + ((int) (f2 * ((i4 & 255) - r2))));
    }

    public final void a(float f, yc ycVar, boolean z) {
        float interpolation;
        float interpolation2;
        if (this.g) {
            d(f, ycVar);
            float fFloor = (float) (Math.floor(ycVar.m / 0.8f) + 1.0d);
            float f2 = ycVar.k;
            float f3 = ycVar.l;
            ycVar.e = (((f3 - 0.01f) - f2) * f) + f2;
            ycVar.f = f3;
            float f4 = ycVar.m;
            ycVar.g = hk.d(fFloor, f4, f, f4);
            return;
        }
        if (f != 1.0f || z) {
            float f5 = ycVar.m;
            mp mpVar = i;
            if (f < 0.5f) {
                interpolation = ycVar.k;
                interpolation2 = (mpVar.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float f6 = ycVar.k + 0.79f;
                interpolation = f6 - (((1.0f - mpVar.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = f6;
            }
            float f7 = (0.20999998f * f) + f5;
            float f8 = (f + this.f) * 216.0f;
            ycVar.e = interpolation;
            ycVar.f = interpolation2;
            ycVar.g = f7;
            this.c = f8;
        }
    }

    public final void b(float f, float f2, float f3, float f4) {
        float f5 = this.d.getDisplayMetrics().density;
        float f6 = f2 * f5;
        yc ycVar = this.b;
        ycVar.h = f6;
        ycVar.b.setStrokeWidth(f6);
        ycVar.q = f * f5;
        ycVar.a(0);
        ycVar.r = (int) (f3 * f5);
        ycVar.s = (int) (f4 * f5);
    }

    public final void c(int i2) {
        if (i2 == 0) {
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
        canvas.rotate(this.c, bounds.exactCenterX(), bounds.exactCenterY());
        yc ycVar = this.b;
        RectF rectF = ycVar.a;
        float f = ycVar.q;
        float fMin = (ycVar.h / 2.0f) + f;
        if (f <= 0.0f) {
            fMin = (Math.min(bounds.width(), bounds.height()) / 2.0f) - Math.max((ycVar.r * ycVar.p) / 2.0f, ycVar.h / 2.0f);
        }
        rectF.set(bounds.centerX() - fMin, bounds.centerY() - fMin, bounds.centerX() + fMin, bounds.centerY() + fMin);
        float f2 = ycVar.e;
        float f3 = ycVar.g;
        float f4 = (f2 + f3) * 360.0f;
        float f5 = ((ycVar.f + f3) * 360.0f) - f4;
        Paint paint = ycVar.b;
        paint.setColor(ycVar.u);
        paint.setAlpha(ycVar.t);
        float f6 = ycVar.h / 2.0f;
        rectF.inset(f6, f6);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, ycVar.d);
        float f7 = -f6;
        rectF.inset(f7, f7);
        canvas.drawArc(rectF, f4, f5, false, paint);
        if (ycVar.n) {
            Path path = ycVar.o;
            if (path == null) {
                Path path2 = new Path();
                ycVar.o = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            float fMin2 = Math.min(rectF.width(), rectF.height()) / 2.0f;
            float f8 = (ycVar.r * ycVar.p) / 2.0f;
            ycVar.o.moveTo(0.0f, 0.0f);
            ycVar.o.lineTo(ycVar.r * ycVar.p, 0.0f);
            Path path3 = ycVar.o;
            float f9 = ycVar.r;
            float f10 = ycVar.p;
            path3.lineTo((f9 * f10) / 2.0f, ycVar.s * f10);
            ycVar.o.offset((rectF.centerX() + fMin2) - f8, (ycVar.h / 2.0f) + rectF.centerY());
            ycVar.o.close();
            Paint paint2 = ycVar.c;
            paint2.setColor(ycVar.u);
            paint2.setAlpha(ycVar.t);
            canvas.save();
            canvas.rotate(f4 + f5, rectF.centerX(), rectF.centerY());
            canvas.drawPath(ycVar.o, paint2);
            canvas.restore();
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.b.t;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.e.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        this.b.t = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.b.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.e.cancel();
        yc ycVar = this.b;
        float f = ycVar.e;
        ycVar.k = f;
        float f2 = ycVar.f;
        ycVar.l = f2;
        ycVar.m = ycVar.g;
        if (f2 != f) {
            this.g = true;
            this.e.setDuration(666L);
            this.e.start();
            return;
        }
        ycVar.a(0);
        ycVar.k = 0.0f;
        ycVar.l = 0.0f;
        ycVar.m = 0.0f;
        ycVar.e = 0.0f;
        ycVar.f = 0.0f;
        ycVar.g = 0.0f;
        this.e.setDuration(1332L);
        this.e.start();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.e.cancel();
        this.c = 0.0f;
        yc ycVar = this.b;
        if (ycVar.n) {
            ycVar.n = false;
        }
        ycVar.a(0);
        ycVar.k = 0.0f;
        ycVar.l = 0.0f;
        ycVar.m = 0.0f;
        ycVar.e = 0.0f;
        ycVar.f = 0.0f;
        ycVar.g = 0.0f;
        invalidateSelf();
    }
}
