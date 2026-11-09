package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class sq0 {
    public static final Matrix p = new Matrix();
    public final Path a;
    public final Path b;
    public final Matrix c;
    public Paint d;
    public Paint e;
    public PathMeasure f;
    public final pq0 g;
    public float h;
    public float i;
    public float j;
    public float k;
    public int l;
    public String m;
    public Boolean n;
    public final c7 o;

    public sq0() {
        this.c = new Matrix();
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 255;
        this.m = null;
        this.n = null;
        this.o = new c7();
        this.g = new pq0();
        this.a = new Path();
        this.b = new Path();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(pq0 pq0Var, Matrix matrix, Canvas canvas, int i, int i2) {
        char c;
        float f;
        float f2;
        int i3;
        pq0 pq0Var2 = pq0Var;
        char c2 = 1;
        pq0Var2.a.set(matrix);
        Matrix matrix2 = pq0Var2.a;
        matrix2.preConcat(pq0Var2.j);
        canvas.save();
        char c3 = 0;
        int i4 = 0;
        while (true) {
            ArrayList arrayList = pq0Var2.b;
            if (i4 >= arrayList.size()) {
                canvas.restore();
                return;
            }
            qq0 qq0Var = (qq0) arrayList.get(i4);
            if (qq0Var instanceof pq0) {
                a((pq0) qq0Var, matrix2, canvas, i, i2);
            } else if (qq0Var instanceof rq0) {
                rq0 rq0Var = (rq0) qq0Var;
                float f3 = i / this.j;
                float f4 = i2 / this.k;
                float fMin = Math.min(f3, f4);
                Matrix matrix3 = this.c;
                matrix3.set(matrix2);
                matrix3.postScale(f3, f4);
                float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                matrix2.mapVectors(fArr);
                float fHypot = (float) Math.hypot(fArr[c3], fArr[c2]);
                boolean z = c2;
                boolean z2 = c3;
                float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                float f5 = (fArr[z2 ? 1 : 0] * fArr[3]) - (fArr[z ? 1 : 0] * fArr[2]);
                float fMax = Math.max(fHypot, fHypot2);
                float fAbs = fMax > 0.0f ? Math.abs(f5) / fMax : 0.0f;
                if (fAbs != 0.0f) {
                    Path path = this.a;
                    rq0Var.getClass();
                    path.reset();
                    pb0[] pb0VarArr = rq0Var.a;
                    if (pb0VarArr != null) {
                        pb0.b(pb0VarArr, path);
                    }
                    Path path2 = this.b;
                    path2.reset();
                    if (rq0Var instanceof nq0) {
                        path2.setFillType(rq0Var.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                        path2.addPath(path, matrix3);
                        canvas.clipPath(path2);
                    } else {
                        oq0 oq0Var = (oq0) rq0Var;
                        float f6 = oq0Var.i;
                        if (f6 != 0.0f || oq0Var.j != 1.0f) {
                            float f7 = oq0Var.k;
                            float f8 = (f6 + f7) % 1.0f;
                            float f9 = (oq0Var.j + f7) % 1.0f;
                            if (this.f == null) {
                                this.f = new PathMeasure();
                            }
                            this.f.setPath(path, z2);
                            float length = this.f.getLength();
                            float f10 = f8 * length;
                            float f11 = f9 * length;
                            path.reset();
                            if (f10 > f11) {
                                this.f.getSegment(f10, length, path, z);
                                f = 0.0f;
                                this.f.getSegment(0.0f, f11, path, z);
                            } else {
                                f = 0.0f;
                                this.f.getSegment(f10, f11, path, z);
                            }
                            path.rLineTo(f, f);
                        }
                        path2.addPath(path, matrix3);
                        d5 d5Var = oq0Var.f;
                        if ((((Shader) d5Var.c) == null && d5Var.b == 0) ? false : true) {
                            if (this.e == null) {
                                i3 = 16777215;
                                Paint paint = new Paint(1);
                                this.e = paint;
                                paint.setStyle(Paint.Style.FILL);
                            } else {
                                i3 = 16777215;
                            }
                            Paint paint2 = this.e;
                            Shader shader = (Shader) d5Var.c;
                            if (shader != null) {
                                shader.setLocalMatrix(matrix3);
                                paint2.setShader(shader);
                                paint2.setAlpha(Math.round(oq0Var.h * 255.0f));
                                f2 = 255.0f;
                            } else {
                                paint2.setShader(null);
                                paint2.setAlpha(255);
                                int i5 = d5Var.b;
                                float f12 = oq0Var.h;
                                PorterDuff.Mode mode = vq0.k;
                                f2 = 255.0f;
                                paint2.setColor((i5 & i3) | (((int) (Color.alpha(i5) * f12)) << 24));
                            }
                            paint2.setColorFilter(null);
                            path2.setFillType(oq0Var.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            canvas.drawPath(path2, paint2);
                        } else {
                            f2 = 255.0f;
                            i3 = 16777215;
                        }
                        d5 d5Var2 = oq0Var.d;
                        if (((Shader) d5Var2.c) != null || d5Var2.b != 0) {
                            if (this.d == null) {
                                Paint paint3 = new Paint(1);
                                this.d = paint3;
                                paint3.setStyle(Paint.Style.STROKE);
                            }
                            Paint paint4 = this.d;
                            Paint.Join join = oq0Var.m;
                            if (join != null) {
                                paint4.setStrokeJoin(join);
                            }
                            Paint.Cap cap = oq0Var.l;
                            if (cap != null) {
                                paint4.setStrokeCap(cap);
                            }
                            paint4.setStrokeMiter(oq0Var.n);
                            Shader shader2 = (Shader) d5Var2.c;
                            if (shader2 != null) {
                                shader2.setLocalMatrix(matrix3);
                                paint4.setShader(shader2);
                                paint4.setAlpha(Math.round(oq0Var.g * f2));
                            } else {
                                paint4.setShader(null);
                                paint4.setAlpha(255);
                                int i6 = d5Var2.b;
                                float f13 = oq0Var.g;
                                PorterDuff.Mode mode2 = vq0.k;
                                paint4.setColor((i6 & i3) | (((int) (Color.alpha(i6) * f13)) << 24));
                            }
                            paint4.setColorFilter(null);
                            paint4.setStrokeWidth(oq0Var.e * fMin * fAbs);
                            canvas.drawPath(path2, paint4);
                        }
                    }
                }
                c = 1;
                i4++;
                pq0Var2 = pq0Var;
                c2 = c;
                c3 = 0;
            }
            c = c2;
            i4++;
            pq0Var2 = pq0Var;
            c2 = c;
            c3 = 0;
        }
    }

    public float getAlpha() {
        return getRootAlpha() / 255.0f;
    }

    public int getRootAlpha() {
        return this.l;
    }

    public void setAlpha(float f) {
        setRootAlpha((int) (f * 255.0f));
    }

    public void setRootAlpha(int i) {
        this.l = i;
    }

    public sq0(sq0 sq0Var) {
        this.c = new Matrix();
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 255;
        this.m = null;
        this.n = null;
        c7 c7Var = new c7();
        this.o = c7Var;
        this.g = new pq0(sq0Var.g, c7Var);
        this.a = new Path(sq0Var.a);
        this.b = new Path(sq0Var.b);
        this.h = sq0Var.h;
        this.i = sq0Var.i;
        this.j = sq0Var.j;
        this.k = sq0Var.k;
        this.l = sq0Var.l;
        this.m = sq0Var.m;
        String str = sq0Var.m;
        if (str != null) {
            c7Var.put(str, this);
        }
        this.n = sq0Var.n;
    }
}
