package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.ek0;
import defpackage.ia;
import defpackage.j70;
import defpackage.k70;
import defpackage.r6;
import defpackage.s70;
import defpackage.w00;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MotionTelltales extends MockView {
    public final Paint m;
    public MotionLayout n;
    public final float[] o;
    public final Matrix p;
    public int q;
    public int r;
    public float s;

    public MotionTelltales(Context context) {
        super(context);
        this.m = new Paint();
        this.o = new float[2];
        this.p = new Matrix();
        this.q = 0;
        this.r = -65281;
        this.s = 0.25f;
        b(context, null);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionTelltales_telltales_tailColor) {
                    this.r = typedArrayObtainStyledAttributes.getColor(index, this.r);
                } else if (index == R$styleable.MotionTelltales_telltales_velocityMode) {
                    this.q = typedArrayObtainStyledAttributes.getInt(index, this.q);
                } else if (index == R$styleable.MotionTelltales_telltales_tailScale) {
                    this.s = typedArrayObtainStyledAttributes.getFloat(index, this.s);
                }
            }
        }
        int i2 = this.r;
        Paint paint = this.m;
        paint.setColor(i2);
        paint.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public final void onDraw(Canvas canvas) {
        char c;
        float f;
        int i;
        Matrix matrix;
        int i2;
        float[] fArr;
        int i3;
        int i4;
        int i5;
        char c2;
        j70 j70Var;
        w00 w00Var;
        w00 w00Var2;
        int i6;
        w00 w00Var3;
        float f2;
        float f3;
        float[] fArr2;
        double[] dArr;
        ia iaVar;
        MotionTelltales motionTelltales = this;
        int i7 = 5;
        super.onDraw(canvas);
        Matrix matrix2 = motionTelltales.getMatrix();
        Matrix matrix3 = motionTelltales.p;
        matrix2.invert(matrix3);
        if (motionTelltales.n == null) {
            ViewParent parent = motionTelltales.getParent();
            if (parent instanceof MotionLayout) {
                motionTelltales.n = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = motionTelltales.getWidth();
        int height = motionTelltales.getHeight();
        float[] fArr3 = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        int i8 = 0;
        while (i8 < i7) {
            float f4 = fArr3[i8];
            int i9 = 0;
            while (i9 < i7) {
                float f5 = fArr3[i9];
                MotionLayout motionLayout = motionTelltales.n;
                int i10 = motionTelltales.q;
                float fA = motionLayout.t;
                float interpolation = motionLayout.E;
                if (motionLayout.s != null) {
                    float fSignum = Math.signum(motionLayout.G - interpolation);
                    float interpolation2 = motionLayout.s.getInterpolation(motionLayout.E + 1.0E-5f);
                    c = 1;
                    interpolation = motionLayout.s.getInterpolation(motionLayout.E);
                    fA = (((interpolation2 - interpolation) / 1.0E-5f) * fSignum) / motionLayout.C;
                } else {
                    c = 1;
                }
                Interpolator interpolator = motionLayout.s;
                if (interpolator instanceof k70) {
                    fA = ((k70) interpolator).a();
                }
                float f6 = fA;
                j70 j70Var2 = (j70) motionLayout.A.get(motionTelltales);
                int i11 = i10 & 1;
                float f7 = f5;
                float f8 = f4;
                float[] fArr4 = motionTelltales.o;
                if (i11 == 0) {
                    int width2 = motionTelltales.getWidth();
                    int height2 = motionTelltales.getHeight();
                    float[] fArr5 = j70Var2.t;
                    float fA2 = j70Var2.a(interpolation, fArr5);
                    c2 = 0;
                    HashMap map = j70Var2.w;
                    f = f6;
                    ek0 ek0Var = map == null ? null : (ek0) map.get("translationX");
                    fArr = fArr3;
                    HashMap map2 = j70Var2.w;
                    i3 = i8;
                    ek0 ek0Var2 = map2 == null ? null : (ek0) map2.get("translationY");
                    HashMap map3 = j70Var2.w;
                    ek0 ek0Var3 = map3 == null ? null : (ek0) map3.get("rotation");
                    HashMap map4 = j70Var2.w;
                    ek0 ek0Var4 = map4 == null ? null : (ek0) map4.get("scaleX");
                    i5 = i9;
                    HashMap map5 = j70Var2.w;
                    i2 = height;
                    ek0 ek0Var5 = map5 == null ? null : (ek0) map5.get("scaleY");
                    i = width;
                    HashMap map6 = j70Var2.x;
                    w00 w00Var4 = map6 == null ? null : (w00) map6.get("translationX");
                    HashMap map7 = j70Var2.x;
                    w00 w00Var5 = map7 == null ? null : (w00) map7.get("translationY");
                    HashMap map8 = j70Var2.x;
                    w00 w00Var6 = map8 == null ? null : (w00) map8.get("rotation");
                    HashMap map9 = j70Var2.x;
                    w00 w00Var7 = map9 == null ? null : (w00) map9.get("scaleX");
                    HashMap map10 = j70Var2.x;
                    w00 w00Var8 = map10 != null ? (w00) map10.get("scaleY") : null;
                    ia iaVar2 = new ia();
                    matrix = matrix3;
                    iaVar2.e = 0.0f;
                    iaVar2.d = 0.0f;
                    iaVar2.c = 0.0f;
                    iaVar2.b = 0.0f;
                    iaVar2.a = 0.0f;
                    if (ek0Var3 != null) {
                        j70Var = j70Var2;
                        w00Var = w00Var5;
                        iaVar2.e = (float) ek0Var3.a.v(fA2);
                        iaVar2.f = ek0Var3.a(fA2);
                    } else {
                        j70Var = j70Var2;
                        w00Var = w00Var5;
                    }
                    if (ek0Var != null) {
                        iaVar2.c = (float) ek0Var.a.v(fA2);
                    }
                    if (ek0Var2 != null) {
                        iaVar2.d = (float) ek0Var2.a.v(fA2);
                    }
                    if (ek0Var4 != null) {
                        iaVar2.a = (float) ek0Var4.a.v(fA2);
                    }
                    if (ek0Var5 != null) {
                        iaVar2.b = (float) ek0Var5.a.v(fA2);
                    }
                    if (w00Var6 != null) {
                        iaVar2.e = w00Var6.b(fA2);
                    }
                    if (w00Var4 != null) {
                        iaVar2.c = w00Var4.b(fA2);
                    }
                    if (w00Var != null) {
                        w00Var2 = w00Var;
                        iaVar2.d = w00Var2.b(fA2);
                    } else {
                        w00Var2 = w00Var;
                    }
                    if (w00Var7 != null || w00Var8 != null) {
                        if (w00Var7 == null) {
                            iaVar2.a = w00Var7.b(fA2);
                        }
                        if (w00Var8 == null) {
                            iaVar2.b = w00Var8.b(fA2);
                        }
                    }
                    j70 j70Var3 = j70Var;
                    r6 r6Var = j70Var3.i;
                    if (r6Var != null) {
                        double[] dArr2 = j70Var3.n;
                        if (dArr2.length > 0) {
                            double d = fA2;
                            r6Var.t(d, dArr2);
                            j70Var3.i.w(d, j70Var3.o);
                            int[] iArr = j70Var3.m;
                            double[] dArr3 = j70Var3.o;
                            double[] dArr4 = j70Var3.n;
                            j70Var3.d.getClass();
                            i6 = i10;
                            s70.d(f7, f8, fArr4, iArr, dArr3, dArr4);
                            fArr2 = fArr4;
                            f3 = f8;
                            f2 = f7;
                            iaVar = iaVar2;
                        } else {
                            i6 = i10;
                            iaVar = iaVar2;
                            f2 = f7;
                            f3 = f8;
                            fArr2 = fArr4;
                        }
                        iaVar.a(f2, f3, width2, height2, fArr2);
                    } else {
                        i6 = i10;
                        if (j70Var3.h != null) {
                            double dA = j70Var3.a(fA2, fArr5);
                            j70Var3.h[0].w(dA, j70Var3.o);
                            j70Var3.h[0].t(dA, j70Var3.n);
                            float f9 = fArr5[0];
                            int i12 = 0;
                            while (true) {
                                dArr = j70Var3.o;
                                if (i12 >= dArr.length) {
                                    break;
                                }
                                dArr[i12] = dArr[i12] * ((double) f9);
                                i12++;
                            }
                            int[] iArr2 = j70Var3.m;
                            double[] dArr5 = j70Var3.n;
                            j70Var3.d.getClass();
                            s70.d(f7, f8, fArr4, iArr2, dArr, dArr5);
                            fArr2 = fArr4;
                            f3 = f8;
                            f2 = f7;
                            iaVar2.a(f2, f3, width2, height2, fArr2);
                        } else {
                            s70 s70Var = j70Var3.e;
                            float f10 = s70Var.f;
                            s70 s70Var2 = j70Var3.d;
                            float f11 = f10 - s70Var2.f;
                            float f12 = s70Var.g - s70Var2.g;
                            float f13 = s70Var.h - s70Var2.h;
                            float f14 = f12 + (s70Var.i - s70Var2.i);
                            fArr4[0] = ((f11 + f13) * f7) + ((1.0f - f7) * f11);
                            fArr4[c] = (f14 * f8) + ((1.0f - f8) * f12);
                            iaVar2.e = 0.0f;
                            iaVar2.d = 0.0f;
                            iaVar2.c = 0.0f;
                            iaVar2.b = 0.0f;
                            iaVar2.a = 0.0f;
                            if (ek0Var3 != null) {
                                i4 = i6;
                                w00Var3 = w00Var8;
                                iaVar2.e = (float) ek0Var3.a.v(fA2);
                                iaVar2.f = ek0Var3.a(fA2);
                            } else {
                                i4 = i6;
                                w00Var3 = w00Var8;
                            }
                            if (ek0Var != null) {
                                iaVar2.c = (float) ek0Var.a.v(fA2);
                            }
                            if (ek0Var2 != null) {
                                iaVar2.d = (float) ek0Var2.a.v(fA2);
                            }
                            if (ek0Var4 != null) {
                                iaVar2.a = (float) ek0Var4.a.v(fA2);
                            }
                            if (ek0Var5 != null) {
                                iaVar2.b = (float) ek0Var5.a.v(fA2);
                            }
                            if (w00Var6 != null) {
                                iaVar2.e = w00Var6.b(fA2);
                            }
                            if (w00Var4 != null) {
                                iaVar2.c = w00Var4.b(fA2);
                            }
                            if (w00Var2 != null) {
                                iaVar2.d = w00Var2.b(fA2);
                            }
                            if (w00Var7 != null || w00Var3 != null) {
                                if (w00Var7 == null) {
                                    iaVar2.a = w00Var7.b(fA2);
                                }
                                if (w00Var3 == null) {
                                    iaVar2.b = w00Var3.b(fA2);
                                }
                            }
                            f2 = f7;
                            f3 = f8;
                            fArr2 = fArr4;
                            iaVar2.a(f2, f3, width2, height2, fArr2);
                            f7 = f2;
                            f8 = f3;
                            fArr4 = fArr2;
                        }
                    }
                    i4 = i6;
                    f7 = f2;
                    f8 = f3;
                    fArr4 = fArr2;
                } else {
                    f = f6;
                    i = width;
                    matrix = matrix3;
                    i2 = height;
                    fArr = fArr3;
                    i3 = i8;
                    i4 = i10;
                    i5 = i9;
                    c2 = 0;
                    j70Var2.b(interpolation, f7, f8, fArr4);
                }
                if (i4 < 2) {
                    fArr4[c2] = fArr4[c2] * f;
                    fArr4[c] = fArr4[c] * f;
                }
                motionTelltales = this;
                float[] fArr6 = motionTelltales.o;
                Matrix matrix4 = matrix;
                matrix4.mapVectors(fArr6);
                width = i;
                float f15 = width * f7;
                int i13 = i2;
                float f16 = i13 * f8;
                float f17 = fArr6[c2];
                float f18 = motionTelltales.s;
                float f19 = f16 - (fArr6[c] * f18);
                matrix4.mapVectors(fArr6);
                canvas.drawLine(f15, f16, f15 - (f17 * f18), f19, motionTelltales.m);
                i9 = i5 + 1;
                height = i13;
                f4 = f8;
                fArr3 = fArr;
                i8 = i3;
                i7 = 5;
                matrix3 = matrix4;
            }
            i8++;
            i7 = 5;
            matrix3 = matrix3;
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.g = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = new Paint();
        this.o = new float[2];
        this.p = new Matrix();
        this.q = 0;
        this.r = -65281;
        this.s = 0.25f;
        b(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = new Paint();
        this.o = new float[2];
        this.p = new Matrix();
        this.q = 0;
        this.r = -65281;
        this.s = 0.25f;
        b(context, attributeSet);
    }
}
