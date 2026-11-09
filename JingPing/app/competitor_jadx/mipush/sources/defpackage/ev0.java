package defpackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public final class ev0 extends iv0 {
    public final gv0 c;
    public final float d;
    public final float e;

    public ev0(gv0 gv0Var, float f, float f2) {
        this.c = gv0Var;
        this.d = f;
        this.e = f2;
    }

    @Override // defpackage.iv0
    public final void a(Matrix matrix, wu0 wu0Var, int i, Canvas canvas) {
        gv0 gv0Var = this.c;
        float f = gv0Var.c;
        float f2 = this.e;
        float f3 = gv0Var.b;
        float f4 = this.d;
        RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(f - f2, f3 - f4), 0.0f);
        Matrix matrix2 = this.a;
        matrix2.set(matrix);
        matrix2.preTranslate(f4, f2);
        matrix2.preRotate(b());
        wu0Var.getClass();
        rectF.bottom += i;
        rectF.offset(0.0f, -i);
        int[] iArr = wu0.i;
        iArr[0] = wu0Var.f;
        iArr[1] = wu0Var.e;
        iArr[2] = wu0Var.d;
        Paint paint = wu0Var.c;
        float f5 = rectF.left;
        paint.setShader(new LinearGradient(f5, rectF.top, f5, rectF.bottom, iArr, wu0.j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix2);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }

    public final float b() {
        gv0 gv0Var = this.c;
        return (float) Math.toDegrees(Math.atan((gv0Var.c - this.e) / (gv0Var.b - this.d)));
    }
}
