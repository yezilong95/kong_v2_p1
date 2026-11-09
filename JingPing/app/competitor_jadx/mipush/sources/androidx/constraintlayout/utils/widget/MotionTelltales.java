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
import defpackage.c8;
import defpackage.ef0;
import defpackage.ff0;
import defpackage.l70;
import defpackage.mf0;
import defpackage.sw0;
import defpackage.xb;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MotionTelltales extends MockView {
    public final Paint P;
    public MotionLayout Q;
    public final float[] R;
    public final Matrix S;
    public int T;
    public int U;
    public float V;

    public MotionTelltales(Context context) {
        super(context);
        this.P = new Paint();
        this.R = new float[2];
        this.S = new Matrix();
        this.T = 0;
        this.U = -65281;
        this.V = 0.25f;
        b(context, null);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionTelltales_telltales_tailColor) {
                    this.U = typedArrayObtainStyledAttributes.getColor(index, this.U);
                } else if (index == R$styleable.MotionTelltales_telltales_velocityMode) {
                    this.T = typedArrayObtainStyledAttributes.getInt(index, this.T);
                } else if (index == R$styleable.MotionTelltales_telltales_tailScale) {
                    this.V = typedArrayObtainStyledAttributes.getFloat(index, this.V);
                }
            }
        }
        int i2 = this.U;
        Paint paint = this.P;
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
        ef0 ef0Var;
        l70 l70Var;
        l70 l70Var2;
        int i6;
        l70 l70Var3;
        float f2;
        float f3;
        float[] fArr2;
        double[] dArr;
        xb xbVar;
        MotionTelltales motionTelltales = this;
        int i7 = 5;
        super.onDraw(canvas);
        Matrix matrix2 = motionTelltales.getMatrix();
        Matrix matrix3 = motionTelltales.S;
        matrix2.invert(matrix3);
        if (motionTelltales.Q == null) {
            ViewParent parent = motionTelltales.getParent();
            if (parent instanceof MotionLayout) {
                motionTelltales.Q = (MotionLayout) parent;
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
                MotionLayout motionLayout = motionTelltales.Q;
                int i10 = motionTelltales.T;
                float fA = motionLayout.W;
                float interpolation = motionLayout.k0;
                if (motionLayout.V != null) {
                    float fSignum = Math.signum(motionLayout.m0 - interpolation);
                    float interpolation2 = motionLayout.V.getInterpolation(motionLayout.k0 + 1.0E-5f);
                    c = 1;
                    interpolation = motionLayout.V.getInterpolation(motionLayout.k0);
                    fA = (((interpolation2 - interpolation) / 1.0E-5f) * fSignum) / motionLayout.i0;
                } else {
                    c = 1;
                }
                Interpolator interpolator = motionLayout.V;
                if (interpolator instanceof ff0) {
                    fA = ((ff0) interpolator).a();
                }
                float f6 = fA;
                ef0 ef0Var2 = (ef0) motionLayout.g0.get(motionTelltales);
                int i11 = i10 & 1;
                float f7 = f5;
                float f8 = f4;
                float[] fArr4 = motionTelltales.R;
                if (i11 == 0) {
                    int width2 = motionTelltales.getWidth();
                    int height2 = motionTelltales.getHeight();
                    float[] fArr5 = ef0Var2.t;
                    float fA2 = ef0Var2.a(interpolation, fArr5);
                    c2 = 0;
                    HashMap map = ef0Var2.w;
                    f = f6;
                    sw0 sw0Var = map == null ? null : (sw0) map.get("translationX");
                    fArr = fArr3;
                    HashMap map2 = ef0Var2.w;
                    i3 = i8;
                    sw0 sw0Var2 = map2 == null ? null : (sw0) map2.get("translationY");
                    HashMap map3 = ef0Var2.w;
                    sw0 sw0Var3 = map3 == null ? null : (sw0) map3.get("rotation");
                    HashMap map4 = ef0Var2.w;
                    sw0 sw0Var4 = map4 == null ? null : (sw0) map4.get("scaleX");
                    i5 = i9;
                    HashMap map5 = ef0Var2.w;
                    i2 = height;
                    sw0 sw0Var5 = map5 == null ? null : (sw0) map5.get("scaleY");
                    i = width;
                    HashMap map6 = ef0Var2.x;
                    l70 l70Var4 = map6 == null ? null : (l70) map6.get("translationX");
                    HashMap map7 = ef0Var2.x;
                    l70 l70Var5 = map7 == null ? null : (l70) map7.get("translationY");
                    HashMap map8 = ef0Var2.x;
                    l70 l70Var6 = map8 == null ? null : (l70) map8.get("rotation");
                    HashMap map9 = ef0Var2.x;
                    l70 l70Var7 = map9 == null ? null : (l70) map9.get("scaleX");
                    HashMap map10 = ef0Var2.x;
                    l70 l70Var8 = map10 != null ? (l70) map10.get("scaleY") : null;
                    xb xbVar2 = new xb();
                    matrix = matrix3;
                    xbVar2.e = 0.0f;
                    xbVar2.d = 0.0f;
                    xbVar2.c = 0.0f;
                    xbVar2.b = 0.0f;
                    xbVar2.a = 0.0f;
                    if (sw0Var3 != null) {
                        ef0Var = ef0Var2;
                        l70Var = l70Var5;
                        xbVar2.e = (float) sw0Var3.a.n0(fA2);
                        xbVar2.f = sw0Var3.a(fA2);
                    } else {
                        ef0Var = ef0Var2;
                        l70Var = l70Var5;
                    }
                    if (sw0Var != null) {
                        xbVar2.c = (float) sw0Var.a.n0(fA2);
                    }
                    if (sw0Var2 != null) {
                        xbVar2.d = (float) sw0Var2.a.n0(fA2);
                    }
                    if (sw0Var4 != null) {
                        xbVar2.a = (float) sw0Var4.a.n0(fA2);
                    }
                    if (sw0Var5 != null) {
                        xbVar2.b = (float) sw0Var5.a.n0(fA2);
                    }
                    if (l70Var6 != null) {
                        xbVar2.e = l70Var6.b(fA2);
                    }
                    if (l70Var4 != null) {
                        xbVar2.c = l70Var4.b(fA2);
                    }
                    if (l70Var != null) {
                        l70Var2 = l70Var;
                        xbVar2.d = l70Var2.b(fA2);
                    } else {
                        l70Var2 = l70Var;
                    }
                    if (l70Var7 != null || l70Var8 != null) {
                        if (l70Var7 == null) {
                            xbVar2.a = l70Var7.b(fA2);
                        }
                        if (l70Var8 == null) {
                            xbVar2.b = l70Var8.b(fA2);
                        }
                    }
                    ef0 ef0Var3 = ef0Var;
                    c8 c8Var = ef0Var3.i;
                    if (c8Var != null) {
                        double[] dArr2 = ef0Var3.n;
                        if (dArr2.length > 0) {
                            double d = fA2;
                            c8Var.j0(d, dArr2);
                            ef0Var3.i.o0(d, ef0Var3.o);
                            int[] iArr = ef0Var3.m;
                            double[] dArr3 = ef0Var3.o;
                            double[] dArr4 = ef0Var3.n;
                            ef0Var3.d.getClass();
                            i6 = i10;
                            mf0.d(f7, f8, fArr4, iArr, dArr3, dArr4);
                            fArr2 = fArr4;
                            f3 = f8;
                            f2 = f7;
                            xbVar = xbVar2;
                        } else {
                            i6 = i10;
                            xbVar = xbVar2;
                            f2 = f7;
                            f3 = f8;
                            fArr2 = fArr4;
                        }
                        xbVar.a(f2, f3, width2, height2, fArr2);
                    } else {
                        i6 = i10;
                        if (ef0Var3.h != null) {
                            double dA = ef0Var3.a(fA2, fArr5);
                            ef0Var3.h[0].o0(dA, ef0Var3.o);
                            ef0Var3.h[0].j0(dA, ef0Var3.n);
                            float f9 = fArr5[0];
                            int i12 = 0;
                            while (true) {
                                dArr = ef0Var3.o;
                                if (i12 >= dArr.length) {
                                    break;
                                }
                                dArr[i12] = dArr[i12] * ((double) f9);
                                i12++;
                            }
                            int[] iArr2 = ef0Var3.m;
                            double[] dArr5 = ef0Var3.n;
                            ef0Var3.d.getClass();
                            mf0.d(f7, f8, fArr4, iArr2, dArr, dArr5);
                            fArr2 = fArr4;
                            f3 = f8;
                            f2 = f7;
                            xbVar2.a(f2, f3, width2, height2, fArr2);
                        } else {
                            mf0 mf0Var = ef0Var3.e;
                            float f10 = mf0Var.I;
                            mf0 mf0Var2 = ef0Var3.d;
                            float f11 = f10 - mf0Var2.I;
                            float f12 = mf0Var.J - mf0Var2.J;
                            float f13 = mf0Var.K - mf0Var2.K;
                            float f14 = f12 + (mf0Var.L - mf0Var2.L);
                            fArr4[0] = ((f11 + f13) * f7) + ((1.0f - f7) * f11);
                            fArr4[c] = (f14 * f8) + ((1.0f - f8) * f12);
                            xbVar2.e = 0.0f;
                            xbVar2.d = 0.0f;
                            xbVar2.c = 0.0f;
                            xbVar2.b = 0.0f;
                            xbVar2.a = 0.0f;
                            if (sw0Var3 != null) {
                                i4 = i6;
                                l70Var3 = l70Var8;
                                xbVar2.e = (float) sw0Var3.a.n0(fA2);
                                xbVar2.f = sw0Var3.a(fA2);
                            } else {
                                i4 = i6;
                                l70Var3 = l70Var8;
                            }
                            if (sw0Var != null) {
                                xbVar2.c = (float) sw0Var.a.n0(fA2);
                            }
                            if (sw0Var2 != null) {
                                xbVar2.d = (float) sw0Var2.a.n0(fA2);
                            }
                            if (sw0Var4 != null) {
                                xbVar2.a = (float) sw0Var4.a.n0(fA2);
                            }
                            if (sw0Var5 != null) {
                                xbVar2.b = (float) sw0Var5.a.n0(fA2);
                            }
                            if (l70Var6 != null) {
                                xbVar2.e = l70Var6.b(fA2);
                            }
                            if (l70Var4 != null) {
                                xbVar2.c = l70Var4.b(fA2);
                            }
                            if (l70Var2 != null) {
                                xbVar2.d = l70Var2.b(fA2);
                            }
                            if (l70Var7 != null || l70Var3 != null) {
                                if (l70Var7 == null) {
                                    xbVar2.a = l70Var7.b(fA2);
                                }
                                if (l70Var3 == null) {
                                    xbVar2.b = l70Var3.b(fA2);
                                }
                            }
                            f2 = f7;
                            f3 = f8;
                            fArr2 = fArr4;
                            xbVar2.a(f2, f3, width2, height2, fArr2);
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
                    ef0Var2.b(interpolation, f7, f8, fArr4);
                }
                if (i4 < 2) {
                    fArr4[c2] = fArr4[c2] * f;
                    fArr4[c] = fArr4[c] * f;
                }
                motionTelltales = this;
                float[] fArr6 = motionTelltales.R;
                Matrix matrix4 = matrix;
                matrix4.mapVectors(fArr6);
                width = i;
                float f15 = width * f7;
                int i13 = i2;
                float f16 = i13 * f8;
                float f17 = fArr6[c2];
                float f18 = motionTelltales.V;
                float f19 = f16 - (fArr6[c] * f18);
                matrix4.mapVectors(fArr6);
                canvas.drawLine(f15, f16, f15 - (f17 * f18), f19, motionTelltales.P);
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
        this.J = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.P = new Paint();
        this.R = new float[2];
        this.S = new Matrix();
        this.T = 0;
        this.U = -65281;
        this.V = 0.25f;
        b(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.P = new Paint();
        this.R = new float[2];
        this.S = new Matrix();
        this.T = 0;
        this.U = -65281;
        this.V = 0.25f;
        b(context, attributeSet);
    }
}
