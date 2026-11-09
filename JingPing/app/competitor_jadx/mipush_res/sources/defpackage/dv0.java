package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public final class dv0 extends iv0 {
    public final fv0 c;

    public dv0(fv0 fv0Var) {
        this.c = fv0Var;
    }

    @Override // defpackage.iv0
    public final void a(Matrix matrix, wu0 wu0Var, int i, Canvas canvas) {
        fv0 fv0Var = this.c;
        float f = fv0Var.f;
        float f2 = fv0Var.g;
        RectF rectF = new RectF(fv0Var.b, fv0Var.c, fv0Var.d, fv0Var.e);
        wu0Var.getClass();
        boolean z = f2 < 0.0f;
        Path path = wu0Var.g;
        int[] iArr = wu0.k;
        if (z) {
            iArr[0] = 0;
            iArr[1] = wu0Var.f;
            iArr[2] = wu0Var.e;
            iArr[3] = wu0Var.d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f, f2);
            path.close();
            float f3 = -i;
            rectF.inset(f3, f3);
            iArr[0] = 0;
            iArr[1] = wu0Var.d;
            iArr[2] = wu0Var.e;
            iArr[3] = wu0Var.f;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f4 = 1.0f - (i / fWidth);
        float[] fArr = wu0.l;
        fArr[1] = f4;
        fArr[2] = ((1.0f - f4) / 2.0f) + f4;
        RadialGradient radialGradient = new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, iArr, fArr, Shader.TileMode.CLAMP);
        Paint paint = wu0Var.b;
        paint.setShader(radialGradient);
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, wu0Var.h);
        }
        canvas.drawArc(rectF, f, f2, true, paint);
        canvas.restore();
    }
}
