package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

/* JADX INFO: loaded from: classes.dex */
public final class d90 extends aq {
    public float b;
    public float c;
    public float d;
    public boolean e;
    public float f;

    @Override // defpackage.aq
    public final void a(Canvas canvas, Rect rect, float f, boolean z, boolean z2) {
        this.b = rect.width();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.a;
        float f2 = linearProgressIndicatorSpec.a;
        canvas.translate((rect.width() / 2.0f) + rect.left, Math.max(0.0f, (rect.height() - f2) / 2.0f) + (rect.height() / 2.0f) + rect.top);
        if (linearProgressIndicatorSpec.j) {
            canvas.scale(-1.0f, 1.0f);
        }
        float f3 = this.b / 2.0f;
        float f4 = f2 / 2.0f;
        canvas.clipRect(-f3, -f4, f3, f4);
        int i = linearProgressIndicatorSpec.a;
        this.e = i / 2 == linearProgressIndicatorSpec.b;
        this.c = i * f;
        this.d = Math.min(i / 2, r6) * f;
        if (z || z2) {
            if ((z && linearProgressIndicatorSpec.e == 2) || (z2 && linearProgressIndicatorSpec.f == 1)) {
                canvas.scale(1.0f, -1.0f);
            }
            if (z || (z2 && linearProgressIndicatorSpec.f != 3)) {
                canvas.translate(0.0f, ((1.0f - f) * linearProgressIndicatorSpec.a) / 2.0f);
            }
        }
        if (z2 && linearProgressIndicatorSpec.f == 3) {
            this.f = f;
        } else {
            this.f = 1.0f;
        }
    }

    @Override // defpackage.aq
    public final void b(Canvas canvas, Paint paint, int i, int i2) {
        int iQ = fa0.q(i, i2);
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.a;
        if (linearProgressIndicatorSpec.k <= 0 || iQ == 0) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(iQ);
        PointF pointF = new PointF((this.b / 2.0f) - (this.c / 2.0f), 0.0f);
        int i3 = linearProgressIndicatorSpec.k;
        h(canvas, paint, pointF, null, i3, i3);
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
        return ((LinearProgressIndicatorSpec) this.a).a;
    }

    @Override // defpackage.aq
    public final int f() {
        return -1;
    }

    public final void g(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3) {
        float f3;
        float fL = s90.l(f, 0.0f, 1.0f);
        float fL2 = s90.l(f2, 0.0f, 1.0f);
        float fR = o80.R(1.0f - this.f, 1.0f, fL);
        float fR2 = o80.R(1.0f - this.f, 1.0f, fL2);
        int iL = (int) ((s90.l(fR, 0.0f, 0.01f) * i2) / 0.01f);
        float fL3 = 1.0f - s90.l(fR2, 0.99f, 1.0f);
        float f4 = this.b;
        int i4 = (int) ((fR * f4) + iL);
        int i5 = (int) ((fR2 * f4) - ((int) ((fL3 * i3) / 0.01f)));
        float f5 = (-f4) / 2.0f;
        if (i4 <= i5) {
            float f6 = this.d;
            float f7 = i4 + f6;
            float f8 = i5 - f6;
            float f9 = f6 * 2.0f;
            paint.setColor(i);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(this.c);
            if (f7 >= f8) {
                h(canvas, paint, new PointF(f7 + f5, 0.0f), new PointF(f8 + f5, 0.0f), f9, this.c);
                return;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(this.e ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            float f10 = f7 + f5;
            float f11 = f8 + f5;
            canvas.drawLine(f10, 0.0f, f11, 0.0f, paint);
            if (this.e || this.d <= 0.0f) {
                return;
            }
            paint.setStyle(Paint.Style.FILL);
            if (f7 > 0.0f) {
                f3 = f9;
                h(canvas, paint, new PointF(f10, 0.0f), null, f3, this.c);
            } else {
                f3 = f9;
            }
            if (f8 < this.b) {
                h(canvas, paint, new PointF(f11, 0.0f), null, f3, this.c);
            }
        }
    }

    public final void h(Canvas canvas, Paint paint, PointF pointF, PointF pointF2, float f, float f2) {
        float fMin = Math.min(f2, this.c);
        float f3 = f / 2.0f;
        float fMin2 = Math.min(f3, (this.d * fMin) / this.c);
        RectF rectF = new RectF((-f) / 2.0f, (-fMin) / 2.0f, f3, fMin / 2.0f);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        if (pointF2 != null) {
            canvas.translate(pointF2.x, pointF2.y);
            Path path = new Path();
            path.addRoundRect(rectF, fMin2, fMin2, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.translate(-pointF2.x, -pointF2.y);
        }
        canvas.translate(pointF.x, pointF.y);
        canvas.drawRoundRect(rectF, fMin2, fMin2, paint);
        canvas.restore();
    }
}
