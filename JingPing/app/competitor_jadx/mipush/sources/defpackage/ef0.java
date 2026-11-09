package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ef0 {
    public final View a;
    public final int b;
    public t40[] h;
    public c8 i;
    public int[] m;
    public double[] n;
    public double[] o;
    public String[] p;
    public int[] q;
    public HashMap v;
    public HashMap w;
    public HashMap x;
    public int c = -1;
    public final mf0 d = new mf0();
    public final mf0 e = new mf0();
    public final df0 f = new df0();
    public final df0 g = new df0();
    public float j = Float.NaN;
    public float k = 0.0f;
    public float l = 1.0f;
    public final float[] r = new float[4];
    public final ArrayList s = new ArrayList();
    public final float[] t = new float[1];
    public final ArrayList u = new ArrayList();
    public int y = -1;

    public ef0(View view) {
        this.a = view;
        this.b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).getClass();
        }
    }

    public final float a(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.l;
            if (f3 != 1.0d) {
                float f4 = this.k;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = (f - f4) * f3;
                }
            }
        }
        vr vrVar = this.d.E;
        float f5 = Float.NaN;
        for (mf0 mf0Var : this.s) {
            vr vrVar2 = mf0Var.E;
            if (vrVar2 != null) {
                float f6 = mf0Var.G;
                if (f6 < f) {
                    vrVar = vrVar2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = mf0Var.G;
                }
            }
        }
        if (vrVar != null) {
            float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
            double d = (f - f2) / f7;
            f = (((float) vrVar.b(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) vrVar.c(d);
            }
        }
        return f;
    }

    public final void b(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float[] fArr2 = this.t;
        float fA = a(f, fArr2);
        t40[] t40VarArr = this.h;
        int i = 0;
        if (t40VarArr == null) {
            mf0 mf0Var = this.e;
            float f4 = mf0Var.I;
            mf0 mf0Var2 = this.d;
            float f5 = f4 - mf0Var2.I;
            float f6 = mf0Var.J - mf0Var2.J;
            float f7 = mf0Var.K - mf0Var2.K;
            float f8 = (mf0Var.L - mf0Var2.L) + f6;
            fArr[0] = ((f7 + f5) * f2) + ((1.0f - f2) * f5);
            fArr[1] = (f8 * f3) + ((1.0f - f3) * f6);
            return;
        }
        double d = fA;
        t40VarArr[0].o0(d, this.o);
        this.h[0].j0(d, this.n);
        float f9 = fArr2[0];
        while (true) {
            dArr = this.o;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f9);
            i++;
        }
        c8 c8Var = this.i;
        if (c8Var == null) {
            int[] iArr = this.m;
            double[] dArr2 = this.n;
            this.d.getClass();
            mf0.d(f2, f3, fArr, iArr, dArr, dArr2);
            return;
        }
        double[] dArr3 = this.n;
        if (dArr3.length > 0) {
            c8Var.j0(d, dArr3);
            this.i.o0(d, this.o);
            int[] iArr2 = this.m;
            double[] dArr4 = this.o;
            double[] dArr5 = this.n;
            this.d.getClass();
            mf0.d(f2, f3, fArr, iArr2, dArr4, dArr5);
        }
    }

    public final boolean c(float f, long j, z8 z8Var, View view) {
        boolean zB;
        boolean z;
        q01 q01Var;
        float f2;
        boolean z2;
        float f3;
        float f4;
        q01 q01Var2 = null;
        float fA = a(f, null);
        HashMap map = this.w;
        if (map != null) {
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                ((sw0) it.next()).c(view, fA);
            }
        }
        HashMap map2 = this.v;
        if (map2 != null) {
            q01 q01Var3 = null;
            zB = false;
            for (s01 s01Var : map2.values()) {
                if (s01Var instanceof q01) {
                    q01Var3 = (q01) s01Var;
                } else {
                    zB |= s01Var.b(fA, j, z8Var, view);
                }
            }
            q01Var2 = q01Var3;
        } else {
            zB = false;
        }
        t40[] t40VarArr = this.h;
        mf0 mf0Var = this.d;
        if (t40VarArr != null) {
            double d = fA;
            t40VarArr[0].j0(d, this.n);
            this.h[0].o0(d, this.o);
            c8 c8Var = this.i;
            if (c8Var != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    c8Var.j0(d, dArr);
                    this.i.o0(d, this.o);
                }
            }
            int[] iArr = this.m;
            double[] dArr2 = this.n;
            double[] dArr3 = this.o;
            float f5 = mf0Var.I;
            float f6 = mf0Var.J;
            float f7 = mf0Var.K;
            float f8 = mf0Var.L;
            int i = 1;
            if (iArr.length != 0) {
                q01Var = q01Var2;
                if (mf0Var.P.length <= iArr[iArr.length - 1]) {
                    int i2 = iArr[iArr.length - 1] + 1;
                    mf0Var.P = new double[i2];
                    mf0Var.Q = new double[i2];
                }
            } else {
                q01Var = q01Var2;
            }
            Arrays.fill(mf0Var.P, Double.NaN);
            for (int i3 = 0; i3 < iArr.length; i3++) {
                double[] dArr4 = mf0Var.P;
                int i4 = iArr[i3];
                dArr4[i4] = dArr2[i3];
                mf0Var.Q[i4] = dArr3[i3];
            }
            float f9 = f6;
            int i5 = 0;
            float f10 = f8;
            float f11 = Float.NaN;
            float f12 = 0.0f;
            float f13 = 0.0f;
            float f14 = 0.0f;
            float f15 = 0.0f;
            while (true) {
                double[] dArr5 = mf0Var.P;
                if (i5 >= dArr5.length) {
                    break;
                }
                if (Double.isNaN(dArr5[i5])) {
                    f3 = f10;
                    f4 = f9;
                } else {
                    f3 = f10;
                    f4 = f9;
                    float f16 = (float) (Double.isNaN(mf0Var.P[i5]) ? 0.0d : mf0Var.P[i5] + 0.0d);
                    float f17 = (float) mf0Var.Q[i5];
                    if (i5 == i) {
                        f12 = f17;
                        f9 = f4;
                        f5 = f16;
                    } else if (i5 == 2) {
                        f14 = f17;
                        f9 = f16;
                    } else if (i5 != 3) {
                        if (i5 == 4) {
                            f15 = f17;
                            f9 = f4;
                            f10 = f16;
                        } else if (i5 == 5) {
                            f10 = f3;
                            f9 = f4;
                            f11 = f16;
                        }
                        i5++;
                        i = 1;
                    } else {
                        f13 = f17;
                        f9 = f4;
                        f7 = f16;
                    }
                    f10 = f3;
                    i5++;
                    i = 1;
                }
                f10 = f3;
                f9 = f4;
                i5++;
                i = 1;
            }
            float f18 = f10;
            float f19 = f9;
            if (Float.isNaN(f11)) {
                if (!Float.isNaN(Float.NaN)) {
                    view.setRotation(Float.NaN);
                }
                z2 = zB;
                f2 = f19;
            } else {
                float f20 = (f13 / 2.0f) + f12;
                float f21 = (f15 / 2.0f) + f14;
                f2 = f19;
                z2 = zB;
                view.setRotation((float) (Math.toDegrees(Math.atan2(f21, f20)) + ((double) f11) + ((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN))));
            }
            float f22 = f5 + 0.5f;
            int i6 = (int) f22;
            float f23 = f2 + 0.5f;
            int i7 = (int) f23;
            int i8 = (int) (f22 + f7);
            int i9 = (int) (f23 + f18);
            int i10 = i8 - i6;
            int i11 = i9 - i7;
            if (i10 != view.getMeasuredWidth() || i11 != view.getMeasuredHeight()) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i10, 1073741824), View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
            }
            view.layout(i6, i7, i8, i9);
            HashMap map3 = this.w;
            if (map3 != null) {
                for (sw0 sw0Var : map3.values()) {
                    if (sw0Var instanceof qw0) {
                        double[] dArr6 = this.o;
                        view.setRotation(((qw0) sw0Var).a(fA) + ((float) Math.toDegrees(Math.atan2(dArr6[1], dArr6[0]))));
                    }
                }
            }
            if (q01Var != null) {
                double[] dArr7 = this.o;
                double d2 = dArr7[0];
                double d3 = dArr7[1];
                q01 q01Var4 = q01Var;
                view.setRotation(q01Var4.a(fA, j, z8Var, view) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
                z = z2 | q01Var4.d;
            } else {
                z = z2;
            }
            int i12 = 1;
            while (true) {
                t40[] t40VarArr2 = this.h;
                if (i12 >= t40VarArr2.length) {
                    break;
                }
                t40 t40Var = t40VarArr2[i12];
                float[] fArr = this.r;
                t40Var.k0(d, fArr);
                ((oh) mf0Var.O.get(this.p[i12 - 1])).g(view, fArr);
                i12++;
            }
            df0 df0Var = this.f;
            if (df0Var.F == 0) {
                if (fA <= 0.0f) {
                    view.setVisibility(df0Var.G);
                } else {
                    df0 df0Var2 = this.g;
                    if (fA >= 1.0f) {
                        view.setVisibility(df0Var2.G);
                    } else if (df0Var2.G != df0Var.G) {
                        view.setVisibility(0);
                    }
                }
            }
        } else {
            boolean z3 = zB;
            float f24 = mf0Var.I;
            mf0 mf0Var2 = this.e;
            float fD = zn.d(mf0Var2.I, f24, fA, f24);
            float f25 = mf0Var.J;
            float fD2 = zn.d(mf0Var2.J, f25, fA, f25);
            float f26 = mf0Var.K;
            float f27 = mf0Var2.K;
            float fD3 = zn.d(f27, f26, fA, f26);
            float f28 = mf0Var.L;
            float f29 = mf0Var2.L;
            float f30 = fD + 0.5f;
            int i13 = (int) f30;
            float f31 = fD2 + 0.5f;
            int i14 = (int) f31;
            int i15 = (int) (f30 + fD3);
            int iD = (int) (f31 + zn.d(f29, f28, fA, f28));
            int i16 = i15 - i13;
            int i17 = iD - i14;
            if (f27 != f26 || f29 != f28) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i16, 1073741824), View.MeasureSpec.makeMeasureSpec(i17, 1073741824));
            }
            view.layout(i13, i14, i15, iD);
            z = z3;
        }
        HashMap map4 = this.x;
        if (map4 != null) {
            for (l70 l70Var : map4.values()) {
                if (l70Var instanceof j70) {
                    double[] dArr8 = this.o;
                    view.setRotation(((j70) l70Var).a(fA) + ((float) Math.toDegrees(Math.atan2(dArr8[1], dArr8[0]))));
                } else {
                    l70Var.c(view, fA);
                }
            }
        }
        return z;
    }

    public final void d(mf0 mf0Var) {
        float x = (int) this.a.getX();
        float y = (int) this.a.getY();
        float width = this.a.getWidth();
        float height = this.a.getHeight();
        mf0Var.I = x;
        mf0Var.J = y;
        mf0Var.K = width;
        mf0Var.L = height;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:257:0x0448. Please report as an issue. */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x019c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(long r40) {
        /*
            Method dump skipped, instruction units count: 3436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ef0.e(long):void");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(" start: x: ");
        mf0 mf0Var = this.d;
        sb.append(mf0Var.I);
        sb.append(" y: ");
        sb.append(mf0Var.J);
        sb.append(" end: x: ");
        mf0 mf0Var2 = this.e;
        sb.append(mf0Var2.I);
        sb.append(" y: ");
        sb.append(mf0Var2.J);
        return sb.toString();
    }
}
