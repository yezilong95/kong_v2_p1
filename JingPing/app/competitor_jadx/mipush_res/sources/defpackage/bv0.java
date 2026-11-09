package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class bv0 {
    public final jv0[] a = new jv0[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final jv0 g = new jv0();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public final Path j = new Path();
    public final Path k = new Path();
    public final boolean l = true;

    public bv0() {
        for (int i = 0; i < 4; i++) {
            this.a[i] = new jv0();
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(zu0 zu0Var, float f, RectF rectF, z8 z8Var, Path path) {
        Matrix[] matrixArr;
        Matrix[] matrixArr2;
        jv0[] jv0VarArr;
        int i;
        char c;
        float[] fArr;
        char c2;
        int i2;
        path.rewind();
        Path path2 = this.e;
        path2.rewind();
        Path path3 = this.f;
        path3.rewind();
        path3.addRect(rectF, Path.Direction.CW);
        int i3 = 0;
        while (true) {
            matrixArr = this.c;
            matrixArr2 = this.b;
            jv0VarArr = this.a;
            c = 0;
            fArr = this.h;
            if (i3 >= 4) {
                break;
            }
            yi yiVar = i3 != 1 ? i3 != 2 ? i3 != 3 ? zu0Var.f : zu0Var.e : zu0Var.h : zu0Var.g;
            z91 z91Var = i3 != 1 ? i3 != 2 ? i3 != 3 ? zu0Var.b : zu0Var.a : zu0Var.d : zu0Var.c;
            jv0 jv0Var = jv0VarArr[i3];
            z91Var.getClass();
            z91Var.u(jv0Var, f, yiVar.a(rectF));
            int i4 = i3 + 1;
            float f2 = (i4 % 4) * 90;
            matrixArr2[i3].reset();
            PointF pointF = this.d;
            if (i3 == 1) {
                i2 = i3;
                pointF.set(rectF.right, rectF.bottom);
            } else if (i3 == 2) {
                i2 = i3;
                pointF.set(rectF.left, rectF.bottom);
            } else if (i3 != 3) {
                i2 = i3;
                pointF.set(rectF.right, rectF.top);
            } else {
                i2 = i3;
                pointF.set(rectF.left, rectF.top);
            }
            matrixArr2[i2].setTranslate(pointF.x, pointF.y);
            matrixArr2[i2].preRotate(f2);
            jv0 jv0Var2 = jv0VarArr[i2];
            fArr[0] = jv0Var2.c;
            fArr[1] = jv0Var2.d;
            matrixArr2[i2].mapPoints(fArr);
            matrixArr[i2].reset();
            matrixArr[i2].setTranslate(fArr[0], fArr[1]);
            matrixArr[i2].preRotate(f2);
            i3 = i4;
        }
        int i5 = 0;
        for (i = 4; i5 < i; i = 4) {
            jv0 jv0Var3 = jv0VarArr[i5];
            fArr[c] = jv0Var3.a;
            fArr[1] = jv0Var3.b;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 0) {
                path.moveTo(fArr[c], fArr[1]);
            } else {
                path.lineTo(fArr[c], fArr[1]);
            }
            jv0VarArr[i5].c(matrixArr2[i5], path);
            if (z8Var != null) {
                jv0 jv0Var4 = jv0VarArr[i5];
                Matrix matrix = matrixArr2[i5];
                ld0 ld0Var = (ld0) z8Var.F;
                BitSet bitSet = ld0Var.H;
                jv0Var4.getClass();
                bitSet.set(i5, (boolean) c);
                jv0Var4.b(jv0Var4.f);
                ld0Var.F[i5] = new cv0(new ArrayList(jv0Var4.h), new Matrix(matrix));
            }
            int i6 = i5 + 1;
            int i7 = i6 % 4;
            jv0 jv0Var5 = jv0VarArr[i5];
            fArr[0] = jv0Var5.c;
            fArr[1] = jv0Var5.d;
            matrixArr2[i5].mapPoints(fArr);
            jv0 jv0Var6 = jv0VarArr[i7];
            float f3 = jv0Var6.a;
            float[] fArr2 = this.i;
            fArr2[0] = f3;
            fArr2[1] = jv0Var6.b;
            matrixArr2[i7].mapPoints(fArr2);
            Matrix[] matrixArr3 = matrixArr2;
            jv0[] jv0VarArr2 = jv0VarArr;
            float fMax = Math.max(((float) Math.hypot(fArr[0] - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
            jv0 jv0Var7 = jv0VarArr2[i5];
            fArr[0] = jv0Var7.c;
            fArr[1] = jv0Var7.d;
            matrixArr3[i5].mapPoints(fArr);
            float fAbs = (i5 == 1 || i5 == 3) ? Math.abs(rectF.centerX() - fArr[0]) : Math.abs(rectF.centerY() - fArr[1]);
            jv0 jv0Var8 = this.g;
            jv0Var8.e(0.0f, 0.0f, 270.0f, 0.0f);
            is isVar = i5 != 1 ? i5 != 2 ? i5 != 3 ? zu0Var.j : zu0Var.i : zu0Var.l : zu0Var.k;
            isVar.d(fMax, fAbs, f, jv0Var8);
            Path path4 = this.j;
            path4.reset();
            jv0Var8.c(matrixArr[i5], path4);
            if (this.l && (isVar.c() || b(path4, i5) || b(path4, i7))) {
                path4.op(path4, path3, Path.Op.DIFFERENCE);
                fArr[0] = jv0Var8.a;
                fArr[1] = jv0Var8.b;
                matrixArr[i5].mapPoints(fArr);
                path2.moveTo(fArr[0], fArr[1]);
                jv0Var8.c(matrixArr[i5], path2);
            } else {
                jv0Var8.c(matrixArr[i5], path);
            }
            if (z8Var != null) {
                Matrix matrix2 = matrixArr[i5];
                ld0 ld0Var2 = (ld0) z8Var.F;
                c2 = 0;
                ld0Var2.H.set(i5 + 4, false);
                jv0Var8.b(jv0Var8.f);
                ld0Var2.G[i5] = new cv0(new ArrayList(jv0Var8.h), new Matrix(matrix2));
            } else {
                c2 = 0;
            }
            i5 = i6;
            c = c2;
            jv0VarArr = jv0VarArr2;
            matrixArr2 = matrixArr3;
        }
        path.close();
        path2.close();
        if (path2.isEmpty()) {
            return;
        }
        path.op(path2, Path.Op.UNION);
    }

    public final boolean b(Path path, int i) {
        Path path2 = this.k;
        path2.reset();
        this.a[i].c(this.b[i], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        return !rectF.isEmpty() || (rectF.width() > 1.0f && rectF.height() > 1.0f);
    }
}
