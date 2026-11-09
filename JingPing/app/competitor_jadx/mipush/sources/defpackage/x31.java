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
public final class x31 {
    public static final Matrix p = new Matrix();
    public final Path a;
    public final Path b;
    public final Matrix c;
    public Paint d;
    public Paint e;
    public PathMeasure f;
    public final u31 g;
    public float h;
    public float i;
    public float j;
    public float k;
    public int l;
    public String m;
    public Boolean n;
    public final k8 o;

    public x31() {
        this.c = new Matrix();
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 255;
        this.m = null;
        this.n = null;
        this.o = new k8();
        this.g = new u31();
        this.a = new Path();
        this.b = new Path();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(u31 u31Var, Matrix matrix, Canvas canvas, int i, int i2) {
        char c;
        float f;
        float f2;
        int i3;
        u31 u31Var2 = u31Var;
        char c2 = 1;
        u31Var2.a.set(matrix);
        Matrix matrix2 = u31Var2.a;
        matrix2.preConcat(u31Var2.j);
        canvas.save();
        char c3 = 0;
        int i4 = 0;
        while (true) {
            ArrayList arrayList = u31Var2.b;
            if (i4 >= arrayList.size()) {
                canvas.restore();
                return;
            }
            v31 v31Var = (v31) arrayList.get(i4);
            if (v31Var instanceof u31) {
                a((u31) v31Var, matrix2, canvas, i, i2);
            } else if (v31Var instanceof w31) {
                w31 w31Var = (w31) v31Var;
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
                    w31Var.getClass();
                    path.reset();
                    mk0[] mk0VarArr = w31Var.a;
                    if (mk0VarArr != null) {
                        mk0.b(mk0VarArr, path);
                    }
                    Path path2 = this.b;
                    path2.reset();
                    if (w31Var instanceof s31) {
                        path2.setFillType(w31Var.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                        path2.addPath(path, matrix3);
                        canvas.clipPath(path2);
                    } else {
                        t31 t31Var = (t31) w31Var;
                        float f6 = t31Var.i;
                        if (f6 != 0.0f || t31Var.j != 1.0f) {
                            float f7 = t31Var.k;
                            float f8 = (f6 + f7) % 1.0f;
                            float f9 = (t31Var.j + f7) % 1.0f;
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
                        l0 l0Var = t31Var.f;
                        if ((((Shader) l0Var.c) == null && l0Var.b == 0) ? false : true) {
                            if (this.e == null) {
                                i3 = 16777215;
                                Paint paint = new Paint(1);
                                this.e = paint;
                                paint.setStyle(Paint.Style.FILL);
                            } else {
                                i3 = 16777215;
                            }
                            Paint paint2 = this.e;
                            Shader shader = (Shader) l0Var.c;
                            if (shader != null) {
                                shader.setLocalMatrix(matrix3);
                                paint2.setShader(shader);
                                paint2.setAlpha(Math.round(t31Var.h * 255.0f));
                                f2 = 255.0f;
                            } else {
                                paint2.setShader(null);
                                paint2.setAlpha(255);
                                int i5 = l0Var.b;
                                float f12 = t31Var.h;
                                PorterDuff.Mode mode = a41.N;
                                f2 = 255.0f;
                                paint2.setColor((i5 & i3) | (((int) (Color.alpha(i5) * f12)) << 24));
                            }
                            paint2.setColorFilter(null);
                            path2.setFillType(t31Var.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            canvas.drawPath(path2, paint2);
                        } else {
                            f2 = 255.0f;
                            i3 = 16777215;
                        }
                        l0 l0Var2 = t31Var.d;
                        if (((Shader) l0Var2.c) != null || l0Var2.b != 0) {
                            if (this.d == null) {
                                Paint paint3 = new Paint(1);
                                this.d = paint3;
                                paint3.setStyle(Paint.Style.STROKE);
                            }
                            Paint paint4 = this.d;
                            Paint.Join join = t31Var.m;
                            if (join != null) {
                                paint4.setStrokeJoin(join);
                            }
                            Paint.Cap cap = t31Var.l;
                            if (cap != null) {
                                paint4.setStrokeCap(cap);
                            }
                            paint4.setStrokeMiter(t31Var.n);
                            Shader shader2 = (Shader) l0Var2.c;
                            if (shader2 != null) {
                                shader2.setLocalMatrix(matrix3);
                                paint4.setShader(shader2);
                                paint4.setAlpha(Math.round(t31Var.g * f2));
                            } else {
                                paint4.setShader(null);
                                paint4.setAlpha(255);
                                int i6 = l0Var2.b;
                                float f13 = t31Var.g;
                                PorterDuff.Mode mode2 = a41.N;
                                paint4.setColor((i6 & i3) | (((int) (Color.alpha(i6) * f13)) << 24));
                            }
                            paint4.setColorFilter(null);
                            paint4.setStrokeWidth(t31Var.e * fMin * fAbs);
                            canvas.drawPath(path2, paint4);
                        }
                    }
                }
                c = 1;
                i4++;
                u31Var2 = u31Var;
                c2 = c;
                c3 = 0;
            }
            c = c2;
            i4++;
            u31Var2 = u31Var;
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

    public x31(x31 x31Var) {
        this.c = new Matrix();
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 255;
        this.m = null;
        this.n = null;
        k8 k8Var = new k8();
        this.o = k8Var;
        this.g = new u31(x31Var.g, k8Var);
        this.a = new Path(x31Var.a);
        this.b = new Path(x31Var.b);
        this.h = x31Var.h;
        this.i = x31Var.i;
        this.j = x31Var.j;
        this.k = x31Var.k;
        this.l = x31Var.l;
        this.m = x31Var.m;
        String str = x31Var.m;
        if (str != null) {
            k8Var.put(str, this);
        }
        this.n = x31Var.n;
    }
}
