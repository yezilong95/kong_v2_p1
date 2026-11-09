package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

/* JADX INFO: loaded from: classes.dex */
public final class z10 extends wl {
    public float c;
    public float d;
    public float e;
    public Path f;

    public z10(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
        this.c = 300.0f;
    }

    @Override // defpackage.wl
    public final void a(Canvas canvas, Rect rect, float f) {
        this.c = rect.width();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.a;
        float f2 = linearProgressIndicatorSpec.a;
        canvas.translate((rect.width() / 2.0f) + rect.left, Math.max(0.0f, (rect.height() - linearProgressIndicatorSpec.a) / 2.0f) + (rect.height() / 2.0f) + rect.top);
        if (linearProgressIndicatorSpec.i) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.b.d() && linearProgressIndicatorSpec.e == 1) || (this.b.c() && linearProgressIndicatorSpec.f == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.b.d() || this.b.c()) {
            canvas.translate(0.0f, ((f - 1.0f) * linearProgressIndicatorSpec.a) / 2.0f);
        }
        float f3 = this.c;
        canvas.clipRect((-f3) / 2.0f, (-f2) / 2.0f, f3 / 2.0f, f2 / 2.0f);
        this.d = linearProgressIndicatorSpec.a * f;
        this.e = linearProgressIndicatorSpec.b * f;
    }

    @Override // defpackage.wl
    public final void b(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f == f2) {
            return;
        }
        float f3 = this.c;
        float f4 = (-f3) / 2.0f;
        float f5 = ((f * f3) + f4) - (this.e * 2.0f);
        float f6 = (f2 * f3) + f4;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(i);
        canvas.save();
        canvas.clipPath(this.f);
        float f7 = this.d;
        RectF rectF = new RectF(f5, (-f7) / 2.0f, f6, f7 / 2.0f);
        float f8 = this.e;
        canvas.drawRoundRect(rectF, f8, f8, paint);
        canvas.restore();
    }

    @Override // defpackage.wl
    public final void c(Canvas canvas, Paint paint) {
        int iJ = k9.j(((LinearProgressIndicatorSpec) this.a).d, this.b.k);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(iJ);
        Path path = new Path();
        this.f = path;
        float f = this.c;
        float f2 = this.d;
        RectF rectF = new RectF((-f) / 2.0f, (-f2) / 2.0f, f / 2.0f, f2 / 2.0f);
        float f3 = this.e;
        path.addRoundRect(rectF, f3, f3, Path.Direction.CCW);
        canvas.drawPath(this.f, paint);
    }

    @Override // defpackage.wl
    public final int d() {
        return ((LinearProgressIndicatorSpec) this.a).a;
    }

    @Override // defpackage.wl
    public final int e() {
        return -1;
    }
}
