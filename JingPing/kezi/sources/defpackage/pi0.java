package defpackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public final class pi0 extends ti0 {
    public final ri0 c;
    public final float d;
    public final float e;

    public pi0(ri0 ri0Var, float f, float f2) {
        this.c = ri0Var;
        this.d = f;
        this.e = f2;
    }

    @Override // defpackage.ti0
    public final void a(Matrix matrix, hi0 hi0Var, int i, Canvas canvas) {
        ri0 ri0Var = this.c;
        float f = ri0Var.c;
        float f2 = this.e;
        float f3 = ri0Var.b;
        float f4 = this.d;
        RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(f - f2, f3 - f4), 0.0f);
        Matrix matrix2 = this.a;
        matrix2.set(matrix);
        matrix2.preTranslate(f4, f2);
        matrix2.preRotate(b());
        hi0Var.getClass();
        rectF.bottom += i;
        rectF.offset(0.0f, -i);
        int[] iArr = hi0.i;
        iArr[0] = hi0Var.f;
        iArr[1] = hi0Var.e;
        iArr[2] = hi0Var.d;
        Paint paint = hi0Var.c;
        float f5 = rectF.left;
        paint.setShader(new LinearGradient(f5, rectF.top, f5, rectF.bottom, iArr, hi0.j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix2);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }

    public final float b() {
        ri0 ri0Var = this.c;
        return (float) Math.toDegrees(Math.atan((ri0Var.c - this.e) / (ri0Var.b - this.d)));
    }
}
