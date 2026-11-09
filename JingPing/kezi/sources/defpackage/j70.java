package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class j70 {
    public final View a;
    public final int b;
    public nv0[] h;
    public r6 i;
    public int[] m;
    public double[] n;
    public double[] o;
    public String[] p;
    public int[] q;
    public HashMap v;
    public HashMap w;
    public HashMap x;
    public int c = -1;
    public final s70 d = new s70();
    public final s70 e = new s70();
    public final i70 f = new i70();
    public final i70 g = new i70();
    public float j = Float.NaN;
    public float k = 0.0f;
    public float l = 1.0f;
    public final float[] r = new float[4];
    public final ArrayList s = new ArrayList();
    public final float[] t = new float[1];
    public final ArrayList u = new ArrayList();
    public int y = -1;

    public j70(View view) {
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
        nm nmVar = this.d.b;
        float f5 = Float.NaN;
        for (s70 s70Var : this.s) {
            nm nmVar2 = s70Var.b;
            if (nmVar2 != null) {
                float f6 = s70Var.d;
                if (f6 < f) {
                    nmVar = nmVar2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = s70Var.d;
                }
            }
        }
        if (nmVar != null) {
            float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
            double d = (f - f2) / f7;
            f = (((float) nmVar.a(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) nmVar.b(d);
            }
        }
        return f;
    }

    public final void b(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float[] fArr2 = this.t;
        float fA = a(f, fArr2);
        nv0[] nv0VarArr = this.h;
        int i = 0;
        if (nv0VarArr == null) {
            s70 s70Var = this.e;
            float f4 = s70Var.f;
            s70 s70Var2 = this.d;
            float f5 = f4 - s70Var2.f;
            float f6 = s70Var.g - s70Var2.g;
            float f7 = s70Var.h - s70Var2.h;
            float f8 = (s70Var.i - s70Var2.i) + f6;
            fArr[0] = ((f7 + f5) * f2) + ((1.0f - f2) * f5);
            fArr[1] = (f8 * f3) + ((1.0f - f3) * f6);
            return;
        }
        double d = fA;
        nv0VarArr[0].w(d, this.o);
        this.h[0].t(d, this.n);
        float f9 = fArr2[0];
        while (true) {
            dArr = this.o;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f9);
            i++;
        }
        r6 r6Var = this.i;
        if (r6Var == null) {
            int[] iArr = this.m;
            double[] dArr2 = this.n;
            this.d.getClass();
            s70.d(f2, f3, fArr, iArr, dArr, dArr2);
            return;
        }
        double[] dArr3 = this.n;
        if (dArr3.length > 0) {
            r6Var.t(d, dArr3);
            this.i.w(d, this.o);
            int[] iArr2 = this.m;
            double[] dArr4 = this.o;
            double[] dArr5 = this.n;
            this.d.getClass();
            s70.d(f2, f3, fArr, iArr2, dArr4, dArr5);
        }
    }

    public final boolean c(float f, long j, o70 o70Var, View view) {
        boolean zB;
        boolean z;
        co0 co0Var;
        float f2;
        boolean z2;
        float f3;
        float f4;
        co0 co0Var2 = null;
        float fA = a(f, null);
        HashMap map = this.w;
        if (map != null) {
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                ((ek0) it.next()).c(view, fA);
            }
        }
        HashMap map2 = this.v;
        if (map2 != null) {
            co0 co0Var3 = null;
            zB = false;
            for (eo0 eo0Var : map2.values()) {
                if (eo0Var instanceof co0) {
                    co0Var3 = (co0) eo0Var;
                } else {
                    zB |= eo0Var.b(fA, j, o70Var, view);
                }
            }
            co0Var2 = co0Var3;
        } else {
            zB = false;
        }
        nv0[] nv0VarArr = this.h;
        s70 s70Var = this.d;
        if (nv0VarArr != null) {
            double d = fA;
            nv0VarArr[0].t(d, this.n);
            this.h[0].w(d, this.o);
            r6 r6Var = this.i;
            if (r6Var != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    r6Var.t(d, dArr);
                    this.i.w(d, this.o);
                }
            }
            int[] iArr = this.m;
            double[] dArr2 = this.n;
            double[] dArr3 = this.o;
            float f5 = s70Var.f;
            float f6 = s70Var.g;
            float f7 = s70Var.h;
            float f8 = s70Var.i;
            int i = 1;
            if (iArr.length != 0) {
                co0Var = co0Var2;
                if (s70Var.m.length <= iArr[iArr.length - 1]) {
                    int i2 = iArr[iArr.length - 1] + 1;
                    s70Var.m = new double[i2];
                    s70Var.n = new double[i2];
                }
            } else {
                co0Var = co0Var2;
            }
            Arrays.fill(s70Var.m, Double.NaN);
            for (int i3 = 0; i3 < iArr.length; i3++) {
                double[] dArr4 = s70Var.m;
                int i4 = iArr[i3];
                dArr4[i4] = dArr2[i3];
                s70Var.n[i4] = dArr3[i3];
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
                double[] dArr5 = s70Var.m;
                if (i5 >= dArr5.length) {
                    break;
                }
                if (Double.isNaN(dArr5[i5])) {
                    f3 = f10;
                    f4 = f9;
                } else {
                    f3 = f10;
                    f4 = f9;
                    float f16 = (float) (Double.isNaN(s70Var.m[i5]) ? 0.0d : s70Var.m[i5] + 0.0d);
                    float f17 = (float) s70Var.n[i5];
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
                for (ek0 ek0Var : map3.values()) {
                    if (ek0Var instanceof ck0) {
                        double[] dArr6 = this.o;
                        view.setRotation(((ck0) ek0Var).a(fA) + ((float) Math.toDegrees(Math.atan2(dArr6[1], dArr6[0]))));
                    }
                }
            }
            if (co0Var != null) {
                double[] dArr7 = this.o;
                double d2 = dArr7[0];
                double d3 = dArr7[1];
                co0 co0Var4 = co0Var;
                view.setRotation(co0Var4.a(fA, j, o70Var, view) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
                z = z2 | co0Var4.d;
            } else {
                z = z2;
            }
            int i12 = 1;
            while (true) {
                nv0[] nv0VarArr2 = this.h;
                if (i12 >= nv0VarArr2.length) {
                    break;
                }
                nv0 nv0Var = nv0VarArr2[i12];
                float[] fArr = this.r;
                nv0Var.u(d, fArr);
                ((cg) s70Var.l.get(this.p[i12 - 1])).g(view, fArr);
                i12++;
            }
            i70 i70Var = this.f;
            if (i70Var.c == 0) {
                if (fA <= 0.0f) {
                    view.setVisibility(i70Var.d);
                } else {
                    i70 i70Var2 = this.g;
                    if (fA >= 1.0f) {
                        view.setVisibility(i70Var2.d);
                    } else if (i70Var2.d != i70Var.d) {
                        view.setVisibility(0);
                    }
                }
            }
        } else {
            boolean z3 = zB;
            float f24 = s70Var.f;
            s70 s70Var2 = this.e;
            float fD = hk.d(s70Var2.f, f24, fA, f24);
            float f25 = s70Var.g;
            float fD2 = hk.d(s70Var2.g, f25, fA, f25);
            float f26 = s70Var.h;
            float f27 = s70Var2.h;
            float fD3 = hk.d(f27, f26, fA, f26);
            float f28 = s70Var.i;
            float f29 = s70Var2.i;
            float f30 = fD + 0.5f;
            int i13 = (int) f30;
            float f31 = fD2 + 0.5f;
            int i14 = (int) f31;
            int i15 = (int) (f30 + fD3);
            int iD = (int) (f31 + hk.d(f29, f28, fA, f28));
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
            for (w00 w00Var : map4.values()) {
                if (w00Var instanceof u00) {
                    double[] dArr8 = this.o;
                    view.setRotation(((u00) w00Var).a(fA) + ((float) Math.toDegrees(Math.atan2(dArr8[1], dArr8[0]))));
                } else {
                    w00Var.c(view, fA);
                }
            }
        }
        return z;
    }

    public final void d(s70 s70Var) {
        float x = (int) this.a.getX();
        float y = (int) this.a.getY();
        float width = this.a.getWidth();
        float height = this.a.getHeight();
        s70Var.f = x;
        s70Var.g = y;
        s70Var.h = width;
        s70Var.i = height;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:206:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x0a99  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x0a8d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x019c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(long r41) {
        /*
            Method dump skipped, instruction units count: 3432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j70.e(long):void");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(" start: x: ");
        s70 s70Var = this.d;
        sb.append(s70Var.f);
        sb.append(" y: ");
        sb.append(s70Var.g);
        sb.append(" end: x: ");
        s70 s70Var2 = this.e;
        sb.append(s70Var2.f);
        sb.append(" y: ");
        sb.append(s70Var2.g);
        return sb.toString();
    }
}
