package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class mi0 {
    public final ui0[] a = new ui0[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final ui0 g = new ui0();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public final Path j = new Path();
    public final Path k = new Path();
    public final boolean l = true;

    public mi0() {
        for (int i = 0; i < 4; i++) {
            this.a[i] = new ui0();
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(ki0 ki0Var, float f, RectF rectF, o70 o70Var, Path path) {
        Matrix[] matrixArr;
        Matrix[] matrixArr2;
        ui0[] ui0VarArr;
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
            ui0VarArr = this.a;
            c = 0;
            fArr = this.h;
            if (i3 >= 4) {
                break;
            }
            kh khVar = i3 != 1 ? i3 != 2 ? i3 != 3 ? ki0Var.f : ki0Var.e : ki0Var.h : ki0Var.g;
            nv0 nv0Var = i3 != 1 ? i3 != 2 ? i3 != 3 ? ki0Var.b : ki0Var.a : ki0Var.d : ki0Var.c;
            ui0 ui0Var = ui0VarArr[i3];
            nv0Var.getClass();
            nv0Var.p(ui0Var, f, khVar.a(rectF));
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
            ui0 ui0Var2 = ui0VarArr[i2];
            fArr[0] = ui0Var2.c;
            fArr[1] = ui0Var2.d;
            matrixArr2[i2].mapPoints(fArr);
            matrixArr[i2].reset();
            matrixArr[i2].setTranslate(fArr[0], fArr[1]);
            matrixArr[i2].preRotate(f2);
            i3 = i4;
        }
        int i5 = 0;
        for (i = 4; i5 < i; i = 4) {
            ui0 ui0Var3 = ui0VarArr[i5];
            fArr[c] = ui0Var3.a;
            fArr[1] = ui0Var3.b;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 0) {
                path.moveTo(fArr[c], fArr[1]);
            } else {
                path.lineTo(fArr[c], fArr[1]);
            }
            ui0VarArr[i5].c(matrixArr2[i5], path);
            if (o70Var != null) {
                ui0 ui0Var4 = ui0VarArr[i5];
                Matrix matrix = matrixArr2[i5];
                u50 u50Var = (u50) o70Var.c;
                BitSet bitSet = u50Var.e;
                ui0Var4.getClass();
                bitSet.set(i5, (boolean) c);
                ui0Var4.b(ui0Var4.f);
                u50Var.c[i5] = new ni0(new ArrayList(ui0Var4.h), new Matrix(matrix));
            }
            int i6 = i5 + 1;
            int i7 = i6 % 4;
            ui0 ui0Var5 = ui0VarArr[i5];
            fArr[0] = ui0Var5.c;
            fArr[1] = ui0Var5.d;
            matrixArr2[i5].mapPoints(fArr);
            ui0 ui0Var6 = ui0VarArr[i7];
            float f3 = ui0Var6.a;
            float[] fArr2 = this.i;
            fArr2[0] = f3;
            fArr2[1] = ui0Var6.b;
            matrixArr2[i7].mapPoints(fArr2);
            Matrix[] matrixArr3 = matrixArr2;
            ui0[] ui0VarArr2 = ui0VarArr;
            float fMax = Math.max(((float) Math.hypot(fArr[0] - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
            ui0 ui0Var7 = ui0VarArr2[i5];
            fArr[0] = ui0Var7.c;
            fArr[1] = ui0Var7.d;
            matrixArr3[i5].mapPoints(fArr);
            float fAbs = (i5 == 1 || i5 == 3) ? Math.abs(rectF.centerX() - fArr[0]) : Math.abs(rectF.centerY() - fArr[1]);
            ui0 ui0Var8 = this.g;
            ui0Var8.e(0.0f, 0.0f, 270.0f, 0.0f);
            qm qmVar = i5 != 1 ? i5 != 2 ? i5 != 3 ? ki0Var.j : ki0Var.i : ki0Var.l : ki0Var.k;
            qmVar.j(fMax, fAbs, f, ui0Var8);
            Path path4 = this.j;
            path4.reset();
            ui0Var8.c(matrixArr[i5], path4);
            if (this.l && (qmVar.i() || b(path4, i5) || b(path4, i7))) {
                path4.op(path4, path3, Path.Op.DIFFERENCE);
                fArr[0] = ui0Var8.a;
                fArr[1] = ui0Var8.b;
                matrixArr[i5].mapPoints(fArr);
                path2.moveTo(fArr[0], fArr[1]);
                ui0Var8.c(matrixArr[i5], path2);
            } else {
                ui0Var8.c(matrixArr[i5], path);
            }
            if (o70Var != null) {
                Matrix matrix2 = matrixArr[i5];
                u50 u50Var2 = (u50) o70Var.c;
                c2 = 0;
                u50Var2.e.set(i5 + 4, false);
                ui0Var8.b(ui0Var8.f);
                u50Var2.d[i5] = new ni0(new ArrayList(ui0Var8.h), new Matrix(matrix2));
            } else {
                c2 = 0;
            }
            i5 = i6;
            c = c2;
            ui0VarArr = ui0VarArr2;
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
