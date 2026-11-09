package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;

/* JADX INFO: loaded from: classes.dex */
public final class ne extends aq {
    public float b;
    public float c;
    public float d;
    public boolean e;
    public float f;

    @Override // defpackage.aq
    public final void a(Canvas canvas, Rect rect, float f, boolean z, boolean z2) {
        float fWidth = rect.width() / i();
        float fHeight = rect.height() / i();
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.a;
        float f2 = (circularProgressIndicatorSpec.h / 2.0f) + circularProgressIndicatorSpec.i;
        canvas.translate((f2 * fWidth) + rect.left, (f2 * fHeight) + rect.top);
        canvas.rotate(-90.0f);
        canvas.scale(fWidth, fHeight);
        if (circularProgressIndicatorSpec.j != 0) {
            canvas.scale(1.0f, -1.0f);
        }
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        int i = circularProgressIndicatorSpec.a;
        this.e = i / 2 <= circularProgressIndicatorSpec.b;
        this.b = i * f;
        this.c = Math.min(i / 2, r1) * f;
        int i2 = circularProgressIndicatorSpec.h;
        int i3 = circularProgressIndicatorSpec.a;
        float f4 = (i2 - i3) / 2.0f;
        this.d = f4;
        if (z || z2) {
            if ((z && circularProgressIndicatorSpec.e == 2) || (z2 && circularProgressIndicatorSpec.f == 1)) {
                this.d = (((1.0f - f) * i3) / 2.0f) + f4;
            } else if ((z && circularProgressIndicatorSpec.e == 1) || (z2 && circularProgressIndicatorSpec.f == 2)) {
                this.d = f4 - (((1.0f - f) * i3) / 2.0f);
            }
        }
        if (z2 && circularProgressIndicatorSpec.f == 3) {
            this.f = f;
        } else {
            this.f = 1.0f;
        }
    }

    @Override // defpackage.aq
    public final void c(Canvas canvas, Paint paint, zp zpVar, int i) {
        int iQ = fa0.q(zpVar.c, i);
        float f = zpVar.a;
        float f2 = zpVar.b;
        int i2 = zpVar.d;
        g(canvas, paint, f, f2, iQ, i2, i2);
    }

    @Override // defpackage.aq
    public final void d(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3) {
        g(canvas, paint, f, f2, fa0.q(i, i2), i3, i3);
    }

    @Override // defpackage.aq
    public final int e() {
        return i();
    }

    @Override // defpackage.aq
    public final int f() {
        return i();
    }

    public final void g(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3) {
        float f3 = f2 >= f ? f2 - f : (f2 + 1.0f) - f;
        float f4 = f % 1.0f;
        if (this.f < 1.0f) {
            float f5 = f4 + f3;
            if (f5 > 1.0f) {
                g(canvas, paint, f4, 1.0f, i, i2, 0);
                g(canvas, paint, 1.0f, f5, i, 0, i3);
                return;
            }
        }
        float degrees = (float) Math.toDegrees(this.c / this.d);
        if (f4 == 0.0f && f3 >= 0.99f) {
            f3 += (((degrees * 2.0f) / 360.0f) * (f3 - 0.99f)) / 0.01f;
        }
        float fR = o80.R(1.0f - this.f, 1.0f, f4);
        float fR2 = o80.R(0.0f, this.f, f3);
        float degrees2 = (float) Math.toDegrees(i2 / this.d);
        float degrees3 = ((fR2 * 360.0f) - degrees2) - ((float) Math.toDegrees(i3 / this.d));
        float f6 = (fR * 360.0f) + degrees2;
        if (degrees3 <= 0.0f) {
            return;
        }
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStrokeWidth(this.b);
        float f7 = degrees * 2.0f;
        if (degrees3 < f7) {
            float f8 = degrees3 / f7;
            paint.setStyle(Paint.Style.FILL);
            h(canvas, paint, (degrees * f8) + f6, this.c * 2.0f, this.b, f8);
            return;
        }
        float f9 = this.d;
        float f10 = -f9;
        RectF rectF = new RectF(f10, f10, f9, f9);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(this.e ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        float f11 = f6 + degrees;
        canvas.drawArc(rectF, f11, degrees3 - f7, false, paint);
        if (this.e || this.c <= 0.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        h(canvas, paint, f11, this.c * 2.0f, this.b, 1.0f);
        h(canvas, paint, (f6 + degrees3) - degrees, this.c * 2.0f, this.b, 1.0f);
    }

    public final void h(Canvas canvas, Paint paint, float f, float f2, float f3, float f4) {
        float fMin = (int) Math.min(f3, this.b);
        float f5 = f2 / 2.0f;
        float fMin2 = Math.min(f5, (this.c * fMin) / this.b);
        RectF rectF = new RectF((-fMin) / 2.0f, (-f2) / 2.0f, fMin / 2.0f, f5);
        canvas.save();
        double d = f;
        canvas.translate((float) (Math.cos(Math.toRadians(d)) * ((double) this.d)), (float) (Math.sin(Math.toRadians(d)) * ((double) this.d)));
        canvas.rotate(f);
        canvas.scale(f4, f4);
        canvas.drawRoundRect(rectF, fMin2, fMin2, paint);
        canvas.restore();
    }

    public final int i() {
        aa aaVar = this.a;
        return (((CircularProgressIndicatorSpec) aaVar).i * 2) + ((CircularProgressIndicatorSpec) aaVar).h;
    }

    @Override // defpackage.aq
    public final void b(Canvas canvas, Paint paint, int i, int i2) {
    }
}
