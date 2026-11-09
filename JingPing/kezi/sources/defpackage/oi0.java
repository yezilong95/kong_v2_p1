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
public final class oi0 extends ti0 {
    public final qi0 c;

    public oi0(qi0 qi0Var) {
        this.c = qi0Var;
    }

    @Override // defpackage.ti0
    public final void a(Matrix matrix, hi0 hi0Var, int i, Canvas canvas) {
        qi0 qi0Var = this.c;
        float f = qi0Var.f;
        float f2 = qi0Var.g;
        RectF rectF = new RectF(qi0Var.b, qi0Var.c, qi0Var.d, qi0Var.e);
        hi0Var.getClass();
        boolean z = f2 < 0.0f;
        Path path = hi0Var.g;
        int[] iArr = hi0.k;
        if (z) {
            iArr[0] = 0;
            iArr[1] = hi0Var.f;
            iArr[2] = hi0Var.e;
            iArr[3] = hi0Var.d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f, f2);
            path.close();
            float f3 = -i;
            rectF.inset(f3, f3);
            iArr[0] = 0;
            iArr[1] = hi0Var.d;
            iArr[2] = hi0Var.e;
            iArr[3] = hi0Var.f;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f4 = 1.0f - (i / fWidth);
        float[] fArr = hi0.l;
        fArr[1] = f4;
        fArr[2] = ((1.0f - f4) / 2.0f) + f4;
        RadialGradient radialGradient = new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, iArr, fArr, Shader.TileMode.CLAMP);
        Paint paint = hi0Var.b;
        paint.setShader(radialGradient);
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, hi0Var.h);
        }
        canvas.drawArc(rectF, f, f2, true, paint);
        canvas.restore();
    }
}
