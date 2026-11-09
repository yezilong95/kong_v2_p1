package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;

/* JADX INFO: loaded from: classes.dex */
public final class tc extends wl {
    public int c;
    public float d;
    public float e;
    public float f;

    public tc(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
        this.c = 1;
    }

    @Override // defpackage.wl
    public final void a(Canvas canvas, Rect rect, float f) {
        float fWidth = rect.width() / g();
        float fHeight = rect.height() / g();
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.a;
        float f2 = (circularProgressIndicatorSpec.g / 2.0f) + circularProgressIndicatorSpec.h;
        canvas.translate((f2 * fWidth) + rect.left, (f2 * fHeight) + rect.top);
        canvas.scale(fWidth, fHeight);
        canvas.rotate(-90.0f);
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        this.c = circularProgressIndicatorSpec.i == 0 ? 1 : -1;
        this.d = circularProgressIndicatorSpec.a * f;
        this.e = circularProgressIndicatorSpec.b * f;
        this.f = (circularProgressIndicatorSpec.g - r9) / 2.0f;
        if ((this.b.d() && circularProgressIndicatorSpec.e == 2) || (this.b.c() && circularProgressIndicatorSpec.f == 1)) {
            this.f = (((1.0f - f) * circularProgressIndicatorSpec.a) / 2.0f) + this.f;
        } else if ((this.b.d() && circularProgressIndicatorSpec.e == 1) || (this.b.c() && circularProgressIndicatorSpec.f == 2)) {
            this.f -= ((1.0f - f) * circularProgressIndicatorSpec.a) / 2.0f;
        }
    }

    @Override // defpackage.wl
    public final void b(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f == f2) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStrokeWidth(this.d);
        float f3 = this.c;
        float f4 = f * 360.0f * f3;
        float f5 = (f2 >= f ? f2 - f : (1.0f + f2) - f) * 360.0f * f3;
        float f6 = this.f;
        float f7 = -f6;
        canvas.drawArc(new RectF(f7, f7, f6, f6), f4, f5, false, paint);
        if (this.e <= 0.0f || Math.abs(f5) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        f(canvas, paint, this.d, this.e, f4);
        f(canvas, paint, this.d, this.e, f4 + f5);
    }

    @Override // defpackage.wl
    public final void c(Canvas canvas, Paint paint) {
        int iJ = k9.j(((CircularProgressIndicatorSpec) this.a).d, this.b.k);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(iJ);
        paint.setStrokeWidth(this.d);
        float f = this.f;
        float f2 = -f;
        canvas.drawArc(new RectF(f2, f2, f, f), 0.0f, 360.0f, false, paint);
    }

    @Override // defpackage.wl
    public final int d() {
        return g();
    }

    @Override // defpackage.wl
    public final int e() {
        return g();
    }

    public final void f(Canvas canvas, Paint paint, float f, float f2, float f3) {
        canvas.save();
        canvas.rotate(f3);
        float f4 = this.f;
        float f5 = f / 2.0f;
        canvas.drawRoundRect(new RectF(f4 - f5, f2, f4 + f5, -f2), f2, f2, paint);
        canvas.restore();
    }

    public final int g() {
        e8 e8Var = this.a;
        return (((CircularProgressIndicatorSpec) e8Var).h * 2) + ((CircularProgressIndicatorSpec) e8Var).g;
    }
}
