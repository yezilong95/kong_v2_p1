package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.c;
import androidx.constraintlayout.widget.d;
import androidx.core.widget.NestedScrollView;
import defpackage.c8;
import defpackage.ca;
import defpackage.co;
import defpackage.df0;
import defpackage.ef0;
import defpackage.er0;
import defpackage.ex0;
import defpackage.f;
import defpackage.ff0;
import defpackage.fx0;
import defpackage.gf0;
import defpackage.hf0;
import defpackage.if0;
import defpackage.ix0;
import defpackage.j4;
import defpackage.jf0;
import defpackage.kf0;
import defpackage.l11;
import defpackage.l70;
import defpackage.lf0;
import defpackage.mf0;
import defpackage.of0;
import defpackage.pf0;
import defpackage.ph0;
import defpackage.qf0;
import defpackage.sw0;
import defpackage.t40;
import defpackage.th;
import defpackage.vh;
import defpackage.vr;
import defpackage.wh;
import defpackage.z8;
import defpackage.zh;
import defpackage.zn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements ph0 {
    public static final /* synthetic */ int d1 = 0;
    public float A0;
    public long B0;
    public float C0;
    public boolean D0;
    public ArrayList E0;
    public ArrayList F0;
    public ArrayList G0;
    public int H0;
    public long I0;
    public float J0;
    public int K0;
    public float L0;
    public boolean M0;
    public int N0;
    public int O0;
    public int P0;
    public int Q0;
    public int R0;
    public int S0;
    public float T0;
    public qf0 U;
    public final z8 U0;
    public Interpolator V;
    public boolean V0;
    public float W;
    public jf0 W0;
    public lf0 X0;
    public final if0 Y0;
    public boolean Z0;
    public int a0;
    public final RectF a1;
    public int b0;
    public View b1;
    public int c0;
    public final ArrayList c1;
    public int d0;
    public int e0;
    public boolean f0;
    public final HashMap g0;
    public long h0;
    public float i0;
    public float j0;
    public float k0;
    public long l0;
    public float m0;
    public boolean n0;
    public boolean o0;
    public kf0 p0;
    public int q0;
    public hf0 r0;
    public boolean s0;
    public final ix0 t0;
    public final gf0 u0;
    public co v0;
    public int w0;
    public int x0;
    public boolean y0;
    public float z0;

    public MotionLayout(Context context) {
        super(context);
        this.W = 0.0f;
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = true;
        this.g0 = new HashMap();
        this.h0 = 0L;
        this.i0 = 1.0f;
        this.j0 = 0.0f;
        this.k0 = 0.0f;
        this.m0 = 0.0f;
        this.o0 = false;
        this.q0 = 0;
        this.s0 = false;
        this.t0 = new ix0();
        this.u0 = new gf0(this);
        this.y0 = false;
        this.D0 = false;
        this.E0 = null;
        this.F0 = null;
        this.G0 = null;
        this.H0 = 0;
        this.I0 = -1L;
        this.J0 = 0.0f;
        this.K0 = 0;
        this.L0 = 0.0f;
        this.M0 = false;
        this.U0 = new z8(23);
        this.V0 = false;
        this.X0 = lf0.E;
        this.Y0 = new if0(this);
        this.Z0 = false;
        this.a1 = new RectF();
        this.b1 = null;
        this.c1 = new ArrayList();
        u(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void A(float r12, float r13, int r14) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.A(float, float, int):void");
    }

    public final void B(int i) {
        j4 j4Var;
        if (!super.isAttachedToWindow()) {
            if (this.W0 == null) {
                this.W0 = new jf0(this);
            }
            this.W0.d = i;
            return;
        }
        qf0 qf0Var = this.U;
        if (qf0Var != null && (j4Var = qf0Var.b) != null) {
            int i2 = this.b0;
            float f = -1;
            ex0 ex0Var = (ex0) ((SparseArray) j4Var.F).get(i);
            if (ex0Var == null) {
                i2 = i;
            } else {
                ArrayList arrayList = ex0Var.b;
                int i3 = ex0Var.c;
                if (f != -1.0f && f != -1.0f) {
                    Iterator it = arrayList.iterator();
                    fx0 fx0Var = null;
                    while (true) {
                        if (it.hasNext()) {
                            fx0 fx0Var2 = (fx0) it.next();
                            if (fx0Var2.a(f, f)) {
                                if (i2 == fx0Var2.e) {
                                    break;
                                } else {
                                    fx0Var = fx0Var2;
                                }
                            }
                        } else if (fx0Var != null) {
                            i2 = fx0Var.e;
                        }
                    }
                } else if (i3 != i2) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        if (i2 == ((fx0) it2.next()).e) {
                            break;
                        }
                    }
                    i2 = i3;
                }
            }
            if (i2 != -1) {
                i = i2;
            }
        }
        int i4 = this.b0;
        if (i4 == i) {
            return;
        }
        if (this.a0 == i) {
            o(0.0f);
            return;
        }
        if (this.c0 == i) {
            o(1.0f);
            return;
        }
        this.c0 = i;
        if (i4 != -1) {
            z(i4, i);
            o(1.0f);
            this.k0 = 0.0f;
            o(1.0f);
            return;
        }
        this.s0 = false;
        this.m0 = 1.0f;
        this.j0 = 0.0f;
        this.k0 = 0.0f;
        this.l0 = getNanoTime();
        this.h0 = getNanoTime();
        this.n0 = false;
        this.V = null;
        qf0 qf0Var2 = this.U;
        this.i0 = (qf0Var2.c != null ? r6.h : qf0Var2.j) / 1000.0f;
        this.a0 = -1;
        qf0Var2.k(-1, this.c0);
        this.U.g();
        int childCount = getChildCount();
        HashMap map = this.g0;
        map.clear();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            map.put(childAt, new ef0(childAt));
        }
        this.o0 = true;
        d dVarB = this.U.b(i);
        if0 if0Var = this.Y0;
        if0Var.d(null, dVarB);
        x();
        if0Var.a();
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            View childAt2 = getChildAt(i6);
            ef0 ef0Var = (ef0) map.get(childAt2);
            if (ef0Var != null) {
                mf0 mf0Var = ef0Var.d;
                mf0Var.G = 0.0f;
                mf0Var.H = 0.0f;
                float x = childAt2.getX();
                float y = childAt2.getY();
                float width = childAt2.getWidth();
                float height = childAt2.getHeight();
                mf0Var.I = x;
                mf0Var.J = y;
                mf0Var.K = width;
                mf0Var.L = height;
                df0 df0Var = ef0Var.f;
                df0Var.getClass();
                childAt2.getX();
                childAt2.getY();
                childAt2.getWidth();
                childAt2.getHeight();
                df0Var.G = childAt2.getVisibility();
                df0Var.E = childAt2.getVisibility() != 0 ? 0.0f : childAt2.getAlpha();
                df0Var.H = childAt2.getElevation();
                df0Var.I = childAt2.getRotation();
                df0Var.J = childAt2.getRotationX();
                df0Var.K = childAt2.getRotationY();
                df0Var.L = childAt2.getScaleX();
                df0Var.M = childAt2.getScaleY();
                df0Var.N = childAt2.getPivotX();
                df0Var.O = childAt2.getPivotY();
                df0Var.P = childAt2.getTranslationX();
                df0Var.Q = childAt2.getTranslationY();
                df0Var.R = childAt2.getTranslationZ();
            }
        }
        getWidth();
        getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            ef0 ef0Var2 = (ef0) map.get(getChildAt(i7));
            this.U.e(ef0Var2);
            ef0Var2.e(getNanoTime());
        }
        pf0 pf0Var = this.U.c;
        float f2 = pf0Var != null ? pf0Var.i : 0.0f;
        if (f2 != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                mf0 mf0Var2 = ((ef0) map.get(getChildAt(i8))).e;
                float f3 = mf0Var2.J + mf0Var2.I;
                fMin = Math.min(fMin, f3);
                fMax = Math.max(fMax, f3);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                ef0 ef0Var3 = (ef0) map.get(getChildAt(i9));
                mf0 mf0Var3 = ef0Var3.e;
                float f4 = mf0Var3.I;
                float f5 = mf0Var3.J;
                ef0Var3.l = 1.0f / (1.0f - f2);
                ef0Var3.k = f2 - ((((f4 + f5) - fMin) * f2) / (fMax - fMin));
            }
        }
        this.j0 = 0.0f;
        this.k0 = 0.0f;
        this.o0 = true;
        invalidate();
    }

    @Override // defpackage.oh0
    public final void a(View view, View view2, int i, int i2) {
    }

    @Override // defpackage.oh0
    public final void b(View view, int i) {
        l11 l11Var;
        qf0 qf0Var = this.U;
        if (qf0Var == null) {
            return;
        }
        float f = this.z0;
        float f2 = this.C0;
        float f3 = f / f2;
        float f4 = this.A0 / f2;
        pf0 pf0Var = qf0Var.c;
        if (pf0Var == null || (l11Var = pf0Var.l) == null) {
            return;
        }
        l11Var.k = false;
        MotionLayout motionLayout = l11Var.o;
        float progress = motionLayout.getProgress();
        l11Var.o.s(l11Var.d, progress, l11Var.h, l11Var.g, l11Var.l);
        float f5 = l11Var.i;
        float[] fArr = l11Var.l;
        float f6 = f5 != 0.0f ? (f3 * f5) / fArr[0] : (f4 * l11Var.j) / fArr[1];
        if (!Float.isNaN(f6)) {
            progress += f6 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i2 = l11Var.c;
            if ((i2 != 3) && z) {
                motionLayout.A(((double) progress) >= 0.5d ? 1.0f : 0.0f, f6, i2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    @Override // defpackage.oh0
    public final void c(View view, int i, int i2, int[] iArr, int i3) {
        pf0 pf0Var;
        boolean z;
        char c;
        ?? r15;
        l11 l11Var;
        float f;
        l11 l11Var2;
        l11 l11Var3;
        int i4;
        qf0 qf0Var = this.U;
        if (qf0Var == null || (pf0Var = qf0Var.c) == null || (z = pf0Var.o)) {
            return;
        }
        if (z || (l11Var3 = pf0Var.l) == null || (i4 = l11Var3.e) == -1 || view.getId() == i4) {
            qf0 qf0Var2 = this.U;
            if (qf0Var2 != null) {
                pf0 pf0Var2 = qf0Var2.c;
                if ((pf0Var2 == null || (l11Var2 = pf0Var2.l) == null) ? false : l11Var2.r) {
                    float f2 = this.j0;
                    if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                        return;
                    }
                }
            }
            if (pf0Var.l != null) {
                l11 l11Var4 = this.U.c.l;
                if ((l11Var4.t & 1) != 0) {
                    float f3 = i;
                    float f4 = i2;
                    l11Var4.o.s(l11Var4.d, l11Var4.o.getProgress(), l11Var4.h, l11Var4.g, l11Var4.l);
                    float f5 = l11Var4.i;
                    float[] fArr = l11Var4.l;
                    if (f5 != 0.0f) {
                        if (fArr[0] == 0.0f) {
                            fArr[0] = 1.0E-7f;
                        }
                        f = (f3 * f5) / fArr[0];
                    } else {
                        if (fArr[1] == 0.0f) {
                            fArr[1] = 1.0E-7f;
                        }
                        f = (f4 * l11Var4.j) / fArr[1];
                    }
                    float f6 = this.k0;
                    if ((f6 <= 0.0f && f < 0.0f) || (f6 >= 1.0f && f > 0.0f)) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new f(17, (ViewGroup) view));
                        return;
                    }
                }
            }
            float f7 = this.j0;
            long nanoTime = getNanoTime();
            float f8 = i;
            this.z0 = f8;
            float f9 = i2;
            this.A0 = f9;
            this.C0 = (float) ((nanoTime - this.B0) * 1.0E-9d);
            this.B0 = nanoTime;
            pf0 pf0Var3 = this.U.c;
            if (pf0Var3 == null || (l11Var = pf0Var3.l) == null) {
                c = 1;
                r15 = 0;
            } else {
                MotionLayout motionLayout = l11Var.o;
                float progress = motionLayout.getProgress();
                if (!l11Var.k) {
                    l11Var.k = true;
                    motionLayout.setProgress(progress);
                }
                c = 1;
                l11Var.o.s(l11Var.d, progress, l11Var.h, l11Var.g, l11Var.l);
                float f10 = l11Var.i;
                float[] fArr2 = l11Var.l;
                boolean z2 = false;
                if (Math.abs((l11Var.j * fArr2[1]) + (f10 * fArr2[0])) < 0.01d) {
                    fArr2[0] = 0.01f;
                    fArr2[1] = 0.01f;
                }
                float f11 = l11Var.i;
                float fMax = Math.max(Math.min(progress + (f11 != 0.0f ? (f8 * f11) / fArr2[0] : (f9 * l11Var.j) / fArr2[1]), 1.0f), 0.0f);
                r15 = z2;
                if (fMax != motionLayout.getProgress()) {
                    motionLayout.setProgress(fMax);
                    r15 = z2;
                }
            }
            if (f7 != this.j0) {
                iArr[r15] = i;
                iArr[c] = i2;
            }
            p(r15);
            if (iArr[r15] == 0 && iArr[c] == 0) {
                return;
            }
            this.y0 = c;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        hf0 hf0Var;
        Iterator it;
        char c;
        char c2;
        Paint paint;
        int i6;
        float f;
        hf0 hf0Var2;
        double dB;
        Paint paint2;
        float f2;
        String resourceEntryName;
        Canvas canvas2 = canvas;
        int i7 = 0;
        p(false);
        super.dispatchDraw(canvas);
        if (this.U == null) {
            return;
        }
        int i8 = 1;
        if ((this.q0 & 1) == 1 && !isInEditMode()) {
            this.H0++;
            long nanoTime = getNanoTime();
            long j = this.I0;
            if (j != -1) {
                if (nanoTime - j > 200000000) {
                    this.J0 = ((int) ((this.H0 / (r9 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.H0 = 0;
                    this.I0 = nanoTime;
                }
            } else {
                this.I0 = nanoTime;
            }
            Paint paint3 = new Paint();
            paint3.setTextSize(42.0f);
            float progress = ((int) (getProgress() * 1000.0f)) / 10.0f;
            StringBuilder sb = new StringBuilder();
            sb.append(this.J0);
            sb.append(" fps ");
            int i9 = this.a0;
            StringBuilder sbP = zn.p(zn.o(sb, i9 == -1 ? "UNDEFINED" : getContext().getResources().getResourceEntryName(i9), " -> "));
            int i10 = this.c0;
            sbP.append(i10 == -1 ? "UNDEFINED" : getContext().getResources().getResourceEntryName(i10));
            sbP.append(" (progress: ");
            sbP.append(progress);
            sbP.append(" ) state=");
            int i11 = this.b0;
            if (i11 == -1) {
                resourceEntryName = "undefined";
            } else {
                resourceEntryName = i11 != -1 ? getContext().getResources().getResourceEntryName(i11) : "UNDEFINED";
            }
            sbP.append(resourceEntryName);
            String string = sbP.toString();
            paint3.setColor(-16777216);
            canvas2.drawText(string, 11.0f, getHeight() - 29, paint3);
            paint3.setColor(-7864184);
            canvas2.drawText(string, 10.0f, getHeight() - 30, paint3);
        }
        if (this.q0 > 1) {
            if (this.r0 == null) {
                this.r0 = new hf0(this);
            }
            hf0 hf0Var3 = this.r0;
            HashMap map = this.g0;
            qf0 qf0Var = this.U;
            pf0 pf0Var = qf0Var.c;
            int i12 = pf0Var != null ? pf0Var.h : qf0Var.j;
            int i13 = this.q0;
            hf0Var3.getClass();
            if (map == null || map.size() == 0) {
                return;
            }
            canvas2.save();
            MotionLayout motionLayout = hf0Var3.n;
            boolean zIsInEditMode = motionLayout.isInEditMode();
            Paint paint4 = hf0Var3.e;
            if (!zIsInEditMode && (i13 & 1) == 2) {
                String str = motionLayout.getContext().getResources().getResourceName(motionLayout.c0) + ":" + motionLayout.getProgress();
                canvas2.drawText(str, 10.0f, motionLayout.getHeight() - 30, hf0Var3.h);
                canvas2.drawText(str, 11.0f, motionLayout.getHeight() - 29, paint4);
            }
            Iterator it2 = map.values().iterator();
            while (it2.hasNext()) {
                ef0 ef0Var = (ef0) it2.next();
                int iMax = ef0Var.d.F;
                ArrayList arrayList = ef0Var.s;
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    iMax = Math.max(iMax, ((mf0) it3.next()).F);
                }
                int iMax2 = Math.max(iMax, ef0Var.e.F);
                if (i13 > 0 && iMax2 == 0) {
                    iMax2 = i8;
                }
                if (iMax2 != 0) {
                    mf0 mf0Var = ef0Var.d;
                    float[] fArr = hf0Var3.c;
                    if (fArr != null) {
                        double[] dArrR0 = ef0Var.h[i7].r0();
                        i = i7;
                        int[] iArr = hf0Var3.b;
                        if (iArr != null) {
                            Iterator it4 = arrayList.iterator();
                            int i14 = i;
                            while (it4.hasNext()) {
                                ((mf0) it4.next()).getClass();
                                iArr[i14] = i;
                                i14++;
                            }
                        }
                        int i15 = i;
                        int i16 = i15;
                        while (i15 < dArrR0.length) {
                            ef0Var.h[i].j0(dArrR0[i15], ef0Var.n);
                            mf0Var.c(ef0Var.m, ef0Var.n, fArr, i16);
                            i16 += 2;
                            i15++;
                            i12 = i12;
                            i13 = i13;
                        }
                        i2 = i12;
                        i3 = i13;
                        i4 = i16 / 2;
                    } else {
                        i = i7;
                        i2 = i12;
                        i3 = i13;
                        i4 = i;
                    }
                    hf0Var3.k = i4;
                    if (iMax2 >= 1) {
                        int i17 = i2 / 16;
                        float[] fArr2 = hf0Var3.a;
                        if (fArr2 == null || fArr2.length != i17 * 2) {
                            hf0Var3.a = new float[i17 * 2];
                            hf0Var3.d = new Path();
                        }
                        int i18 = hf0Var3.m;
                        float f3 = i18;
                        canvas2.translate(f3, f3);
                        paint4.setColor(1996488704);
                        Paint paint5 = hf0Var3.i;
                        paint5.setColor(1996488704);
                        Paint paint6 = hf0Var3.f;
                        paint6.setColor(1996488704);
                        Paint paint7 = hf0Var3.g;
                        paint7.setColor(1996488704);
                        float[] fArr3 = hf0Var3.a;
                        float f4 = 1.0f / (i17 - 1);
                        HashMap map2 = ef0Var.w;
                        it = it2;
                        sw0 sw0Var = map2 == null ? null : (sw0) map2.get("translationX");
                        HashMap map3 = ef0Var.w;
                        sw0 sw0Var2 = map3 == null ? null : (sw0) map3.get("translationY");
                        HashMap map4 = ef0Var.x;
                        l70 l70Var = map4 == null ? null : (l70) map4.get("translationX");
                        HashMap map5 = ef0Var.x;
                        l70 l70Var2 = map5 == null ? null : (l70) map5.get("translationY");
                        int i19 = i;
                        while (true) {
                            float f5 = Float.NaN;
                            float f6 = 0.0f;
                            if (i19 >= i17) {
                                break;
                            }
                            int i20 = i17;
                            float f7 = i19 * f4;
                            float f8 = ef0Var.l;
                            if (f8 != 1.0f) {
                                float f9 = ef0Var.k;
                                if (f7 < f9) {
                                    f2 = f9;
                                    f = 0.0f;
                                } else {
                                    f2 = f9;
                                    f = f7;
                                }
                                paint = paint7;
                                i6 = i19;
                                if (f > f2 && f < 1.0d) {
                                    f = (f - f2) * f8;
                                }
                            } else {
                                paint = paint7;
                                i6 = i19;
                                f = f7;
                            }
                            double d = f;
                            vr vrVar = mf0Var.E;
                            Iterator it5 = arrayList.iterator();
                            while (it5.hasNext()) {
                                Iterator it6 = it5;
                                mf0 mf0Var2 = (mf0) it5.next();
                                Paint paint8 = paint5;
                                vr vrVar2 = mf0Var2.E;
                                if (vrVar2 != null) {
                                    float f10 = mf0Var2.G;
                                    if (f10 < f) {
                                        f6 = f10;
                                        vrVar = vrVar2;
                                    } else if (Float.isNaN(f5)) {
                                        f5 = mf0Var2.G;
                                    }
                                }
                                paint5 = paint8;
                                it5 = it6;
                            }
                            Paint paint9 = paint5;
                            if (vrVar != null) {
                                if (Float.isNaN(f5)) {
                                    f5 = 1.0f;
                                }
                                hf0Var2 = hf0Var3;
                                dB = (((float) vrVar.b((f - f6) / r26)) * (f5 - f6)) + f6;
                            } else {
                                hf0Var2 = hf0Var3;
                                dB = d;
                            }
                            hf0 hf0Var4 = hf0Var2;
                            ef0Var.h[i].j0(dB, ef0Var.n);
                            c8 c8Var = ef0Var.i;
                            if (c8Var != null) {
                                double[] dArr = ef0Var.n;
                                paint2 = paint6;
                                if (dArr.length > 0) {
                                    c8Var.j0(dB, dArr);
                                }
                            } else {
                                paint2 = paint6;
                            }
                            int i21 = i6 * 2;
                            mf0Var.c(ef0Var.m, ef0Var.n, fArr3, i21);
                            if (l70Var != null) {
                                fArr3[i21] = l70Var.a(f) + fArr3[i21];
                            } else if (sw0Var != null) {
                                fArr3[i21] = sw0Var.a(f) + fArr3[i21];
                            }
                            if (l70Var2 != null) {
                                int i22 = i21 + 1;
                                fArr3[i22] = l70Var2.a(f) + fArr3[i22];
                            } else if (sw0Var2 != null) {
                                int i23 = i21 + 1;
                                fArr3[i23] = sw0Var2.a(f) + fArr3[i23];
                            }
                            i19 = i6 + 1;
                            hf0Var3 = hf0Var4;
                            i17 = i20;
                            paint6 = paint2;
                            paint5 = paint9;
                            paint7 = paint;
                        }
                        hf0Var = hf0Var3;
                        hf0Var.a(canvas2, iMax2, hf0Var.k, ef0Var);
                        paint4.setColor(-21965);
                        paint6.setColor(-2067046);
                        paint5.setColor(-2067046);
                        paint7.setColor(-13391360);
                        float f11 = -i18;
                        canvas2.translate(f11, f11);
                        hf0Var.a(canvas2, iMax2, hf0Var.k, ef0Var);
                        char c3 = 5;
                        if (iMax2 == 5) {
                            hf0Var.d.reset();
                            int i24 = i;
                            while (i24 <= 50) {
                                ef0Var.h[i].j0(ef0Var.a(i24 / 50, null), ef0Var.n);
                                int[] iArr2 = ef0Var.m;
                                double[] dArr2 = ef0Var.n;
                                float f12 = mf0Var.I;
                                float f13 = mf0Var.J;
                                float f14 = mf0Var.K;
                                float f15 = mf0Var.L;
                                int i25 = i;
                                while (true) {
                                    c2 = c3;
                                    if (i25 < iArr2.length) {
                                        float f16 = (float) dArr2[i25];
                                        int i26 = iArr2[i25];
                                        if (i26 == 1) {
                                            f12 = f16;
                                        } else if (i26 == 2) {
                                            f13 = f16;
                                        } else if (i26 == 3) {
                                            f14 = f16;
                                        } else if (i26 == 4) {
                                            f15 = f16;
                                        }
                                        i25++;
                                        c3 = c2;
                                    }
                                }
                                float f17 = f14 + f12;
                                float f18 = f15 + f13;
                                Float.isNaN(Float.NaN);
                                Float.isNaN(Float.NaN);
                                float f19 = f12 + 0.0f;
                                float f20 = f13 + 0.0f;
                                float f21 = f17 + 0.0f;
                                float f22 = f18 + 0.0f;
                                float[] fArr4 = hf0Var.j;
                                fArr4[i] = f19;
                                fArr4[1] = f20;
                                fArr4[2] = f21;
                                fArr4[3] = f20;
                                fArr4[4] = f21;
                                fArr4[c2] = f22;
                                fArr4[6] = f19;
                                fArr4[7] = f22;
                                hf0Var.d.moveTo(f19, f20);
                                hf0Var.d.lineTo(fArr4[2], fArr4[3]);
                                hf0Var.d.lineTo(fArr4[4], fArr4[c2]);
                                hf0Var.d.lineTo(fArr4[6], fArr4[7]);
                                hf0Var.d.close();
                                i24++;
                                c3 = c2;
                            }
                            c = 2;
                            i5 = 1;
                            paint4.setColor(1140850688);
                            canvas2 = canvas;
                            canvas2.translate(2.0f, 2.0f);
                            canvas2.drawPath(hf0Var.d, paint4);
                            canvas2.translate(-2.0f, -2.0f);
                            paint4.setColor(-65536);
                            canvas2.drawPath(hf0Var.d, paint4);
                        } else {
                            c = 2;
                            i5 = 1;
                        }
                    } else {
                        i5 = 1;
                        hf0Var = hf0Var3;
                        it = it2;
                        c = 2;
                    }
                    hf0Var3 = hf0Var;
                    i7 = i;
                    i8 = i5;
                    i12 = i2;
                    i13 = i3;
                    it2 = it;
                }
            }
            canvas2.restore();
        }
    }

    @Override // defpackage.ph0
    public final void g(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (this.y0 || i != 0 || i2 != 0) {
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
        }
        this.y0 = false;
    }

    public int[] getConstraintSetIds() {
        qf0 qf0Var = this.U;
        if (qf0Var == null) {
            return null;
        }
        SparseArray sparseArray = qf0Var.g;
        int size = sparseArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = sparseArray.keyAt(i);
        }
        return iArr;
    }

    public int getCurrentState() {
        return this.b0;
    }

    public ArrayList<pf0> getDefinedTransitions() {
        qf0 qf0Var = this.U;
        if (qf0Var == null) {
            return null;
        }
        return qf0Var.d;
    }

    public co getDesignTool() {
        if (this.v0 == null) {
            this.v0 = new co();
        }
        return this.v0;
    }

    public int getEndState() {
        return this.c0;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.k0;
    }

    public int getStartState() {
        return this.a0;
    }

    public float getTargetPosition() {
        return this.m0;
    }

    public Bundle getTransitionState() {
        if (this.W0 == null) {
            this.W0 = new jf0(this);
        }
        jf0 jf0Var = this.W0;
        MotionLayout motionLayout = jf0Var.e;
        jf0Var.d = motionLayout.c0;
        jf0Var.c = motionLayout.a0;
        jf0Var.b = motionLayout.getVelocity();
        jf0Var.a = motionLayout.getProgress();
        jf0 jf0Var2 = this.W0;
        jf0Var2.getClass();
        Bundle bundle = new Bundle();
        bundle.putFloat("motion.progress", jf0Var2.a);
        bundle.putFloat("motion.velocity", jf0Var2.b);
        bundle.putInt("motion.StartState", jf0Var2.c);
        bundle.putInt("motion.EndState", jf0Var2.d);
        return bundle;
    }

    public long getTransitionTimeMs() {
        qf0 qf0Var = this.U;
        if (qf0Var != null) {
            this.i0 = (qf0Var.c != null ? r2.h : qf0Var.j) / 1000.0f;
        }
        return (long) (this.i0 * 1000.0f);
    }

    public float getVelocity() {
        return this.W;
    }

    @Override // defpackage.oh0
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
    }

    @Override // defpackage.oh0
    public final boolean j(View view, View view2, int i, int i2) {
        pf0 pf0Var;
        l11 l11Var;
        qf0 qf0Var = this.U;
        return (qf0Var == null || (pf0Var = qf0Var.c) == null || (l11Var = pf0Var.l) == null || (l11Var.t & 2) != 0) ? false : true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public final void l(int i) {
        this.O = null;
    }

    public final void o(float f) {
        qf0 qf0Var = this.U;
        if (qf0Var == null) {
            return;
        }
        float f2 = this.k0;
        float f3 = this.j0;
        if (f2 != f3 && this.n0) {
            this.k0 = f3;
        }
        float f4 = this.k0;
        if (f4 == f) {
            return;
        }
        this.s0 = false;
        this.m0 = f;
        this.i0 = (qf0Var.c != null ? r3.h : qf0Var.j) / 1000.0f;
        setProgress(f);
        this.V = this.U.d();
        this.n0 = false;
        this.h0 = getNanoTime();
        this.o0 = true;
        this.j0 = f4;
        this.k0 = f4;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        int i;
        super.onAttachedToWindow();
        qf0 qf0Var = this.U;
        if (qf0Var != null && (i = this.b0) != -1) {
            d dVarB = qf0Var.b(i);
            qf0 qf0Var2 = this.U;
            int i2 = 0;
            loop0: while (true) {
                SparseArray sparseArray = qf0Var2.g;
                if (i2 < sparseArray.size()) {
                    int iKeyAt = sparseArray.keyAt(i2);
                    SparseIntArray sparseIntArray = qf0Var2.i;
                    int i3 = sparseIntArray.get(iKeyAt);
                    int size = sparseIntArray.size();
                    while (i3 > 0) {
                        if (i3 == iKeyAt) {
                            break loop0;
                        }
                        int i4 = size - 1;
                        if (size < 0) {
                            break loop0;
                        }
                        i3 = sparseIntArray.get(i3);
                        size = i4;
                    }
                    qf0Var2.j(iKeyAt);
                    i2++;
                } else {
                    for (int i5 = 0; i5 < sparseArray.size(); i5++) {
                        d dVar = (d) sparseArray.valueAt(i5);
                        dVar.getClass();
                        int childCount = getChildCount();
                        for (int i6 = 0; i6 < childCount; i6++) {
                            View childAt = getChildAt(i6);
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                            int id = childAt.getId();
                            if (dVar.b && id == -1) {
                                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                            }
                            HashMap map = dVar.c;
                            if (!map.containsKey(Integer.valueOf(id))) {
                                map.put(Integer.valueOf(id), new c());
                            }
                            c cVar = (c) map.get(Integer.valueOf(id));
                            if (!cVar.d.b) {
                                cVar.b(id, layoutParams);
                                boolean z = childAt instanceof ConstraintHelper;
                                th thVar = cVar.d;
                                if (z) {
                                    thVar.e0 = ((ConstraintHelper) childAt).getReferencedIds();
                                    if (childAt instanceof Barrier) {
                                        Barrier barrier = (Barrier) childAt;
                                        thVar.j0 = barrier.N.j0;
                                        thVar.b0 = barrier.getType();
                                        thVar.c0 = barrier.getMargin();
                                    }
                                }
                                thVar.b = true;
                            }
                            vh vhVar = cVar.b;
                            if (!vhVar.a) {
                                vhVar.b = childAt.getVisibility();
                                vhVar.d = childAt.getAlpha();
                                vhVar.a = true;
                            }
                            wh whVar = cVar.e;
                            if (!whVar.a) {
                                whVar.a = true;
                                whVar.b = childAt.getRotation();
                                whVar.c = childAt.getRotationX();
                                whVar.d = childAt.getRotationY();
                                whVar.e = childAt.getScaleX();
                                whVar.f = childAt.getScaleY();
                                float pivotX = childAt.getPivotX();
                                float pivotY = childAt.getPivotY();
                                if (pivotX != 0.0d || pivotY != 0.0d) {
                                    whVar.g = pivotX;
                                    whVar.h = pivotY;
                                }
                                whVar.i = childAt.getTranslationX();
                                whVar.j = childAt.getTranslationY();
                                whVar.k = childAt.getTranslationZ();
                                if (whVar.l) {
                                    whVar.m = childAt.getElevation();
                                }
                            }
                        }
                    }
                }
            }
            Log.e("MotionScene", "Cannot be derived from yourself");
            if (dVarB != null) {
                dVarB.b(this);
            }
            this.a0 = this.b0;
        }
        v();
        jf0 jf0Var = this.W0;
        if (jf0Var != null) {
            jf0Var.a();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        pf0 pf0Var;
        l11 l11Var;
        int i;
        RectF rectFA;
        qf0 qf0Var = this.U;
        if (qf0Var == null || !this.f0 || (pf0Var = qf0Var.c) == null || pf0Var.o || (l11Var = pf0Var.l) == null) {
            return false;
        }
        if ((motionEvent.getAction() == 0 && (rectFA = l11Var.a(this, new RectF())) != null && !rectFA.contains(motionEvent.getX(), motionEvent.getY())) || (i = l11Var.e) == -1) {
            return false;
        }
        View view = this.b1;
        if (view == null || view.getId() != i) {
            this.b1 = findViewById(i);
        }
        if (this.b1 == null) {
            return false;
        }
        RectF rectF = this.a1;
        rectF.set(r0.getLeft(), this.b1.getTop(), this.b1.getRight(), this.b1.getBottom());
        if (!rectF.contains(motionEvent.getX(), motionEvent.getY()) || t(0.0f, 0.0f, this.b1, motionEvent)) {
            return false;
        }
        return onTouchEvent(motionEvent);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) throws Throwable {
        MotionLayout motionLayout;
        this.V0 = true;
        try {
            if (this.U == null) {
                super.onLayout(z, i, i2, i3, i4);
                this.V0 = false;
                return;
            }
            motionLayout = this;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            try {
                if (motionLayout.w0 != i5 || motionLayout.x0 != i6) {
                    x();
                    p(true);
                }
                motionLayout.w0 = i5;
                motionLayout.x0 = i6;
                motionLayout.V0 = false;
                return;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            motionLayout = this;
        }
        Throwable th3 = th;
        motionLayout.V0 = false;
        throw th3;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        boolean z;
        if (this.U == null) {
            super.onMeasure(i, i2);
            return;
        }
        boolean z2 = true;
        boolean z3 = (this.d0 == i && this.e0 == i2) ? false : true;
        if (this.Z0) {
            this.Z0 = false;
            v();
            w();
            z3 = true;
        }
        if (this.L) {
            z3 = true;
        }
        this.d0 = i;
        this.e0 = i2;
        int iG = this.U.g();
        pf0 pf0Var = this.U.c;
        int i3 = pf0Var == null ? -1 : pf0Var.c;
        zh zhVar = this.G;
        if0 if0Var = this.Y0;
        if ((!z3 && iG == if0Var.e && i3 == if0Var.f) || this.a0 == -1) {
            z = true;
        } else {
            super.onMeasure(i, i2);
            if0Var.d(this.U.b(iG), this.U.b(i3));
            if0Var.e();
            if0Var.e = iG;
            if0Var.f = i3;
            z = false;
        }
        if (this.M0 || z) {
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int iM = zhVar.m() + getPaddingRight() + getPaddingLeft();
            int iJ = zhVar.j() + paddingBottom;
            int i4 = this.R0;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                iM = (int) ((this.T0 * (this.P0 - r1)) + this.N0);
                requestLayout();
            }
            int i5 = this.S0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iJ = (int) ((this.T0 * (this.Q0 - r2)) + this.O0);
                requestLayout();
            }
            setMeasuredDimension(iM, iJ);
        }
        float fSignum = Math.signum(this.m0 - this.k0);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.V;
        float interpolation = this.k0 + (!(interpolator instanceof ix0) ? (((nanoTime - this.l0) * fSignum) * 1.0E-9f) / this.i0 : 0.0f);
        if (this.n0) {
            interpolation = this.m0;
        }
        if ((fSignum <= 0.0f || interpolation < this.m0) && (fSignum > 0.0f || interpolation > this.m0)) {
            z2 = false;
        } else {
            interpolation = this.m0;
        }
        if (interpolator != null && !z2) {
            interpolation = this.s0 ? interpolator.getInterpolation((nanoTime - this.h0) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.m0) || (fSignum <= 0.0f && interpolation <= this.m0)) {
            interpolation = this.m0;
        }
        float f = interpolation;
        this.T0 = f;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            ef0 ef0Var = (ef0) this.g0.get(childAt);
            if (ef0Var != null) {
                ef0Var.c(f, nanoTime2, this.U0, childAt);
            }
        }
        if (this.M0) {
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        l11 l11Var;
        qf0 qf0Var = this.U;
        if (qf0Var != null) {
            boolean zK = k();
            qf0Var.o = zK;
            pf0 pf0Var = qf0Var.c;
            if (pf0Var == null || (l11Var = pf0Var.l) == null) {
                return;
            }
            l11Var.b(zK);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r28) {
        /*
            Method dump skipped, instruction units count: 1036
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.G0 == null) {
                this.G0 = new ArrayList();
            }
            this.G0.add(motionHelper);
            if (motionHelper.L) {
                if (this.E0 == null) {
                    this.E0 = new ArrayList();
                }
                this.E0.add(motionHelper);
            }
            if (motionHelper.M) {
                if (this.F0 == null) {
                    this.F0 = new ArrayList();
                }
                this.F0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList arrayList = this.E0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList arrayList2 = this.F0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public final void p(boolean z) {
        float f;
        boolean z2;
        float f2;
        int i;
        float f3;
        float interpolation;
        boolean z3;
        if (this.l0 == -1) {
            this.l0 = getNanoTime();
        }
        float f4 = this.k0;
        float f5 = 0.0f;
        if (f4 > 0.0f && f4 < 1.0f) {
            this.b0 = -1;
        }
        boolean z4 = false;
        if (this.D0 || (this.o0 && (z || this.m0 != f4))) {
            float fSignum = Math.signum(this.m0 - f4);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.V;
            if (interpolator instanceof ff0) {
                f = 0.0f;
            } else {
                f = (((nanoTime - this.l0) * fSignum) * 1.0E-9f) / this.i0;
                this.W = f;
            }
            float f6 = this.k0 + f;
            if (this.n0) {
                f6 = this.m0;
            }
            if ((fSignum <= 0.0f || f6 < this.m0) && (fSignum > 0.0f || f6 > this.m0)) {
                z2 = false;
            } else {
                f6 = this.m0;
                this.o0 = false;
                z2 = true;
            }
            this.k0 = f6;
            this.j0 = f6;
            this.l0 = nanoTime;
            if (interpolator != null && !z2) {
                if (this.s0) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.h0) * 1.0E-9f);
                    this.k0 = interpolation;
                    this.l0 = nanoTime;
                    Interpolator interpolator2 = this.V;
                    if (interpolator2 instanceof ff0) {
                        float fA = ((ff0) interpolator2).a();
                        this.W = fA;
                        if (Math.abs(fA) * this.i0 <= 1.0E-5f) {
                            this.o0 = false;
                        }
                        if (fA > 0.0f && interpolation >= 1.0f) {
                            this.k0 = 1.0f;
                            this.o0 = false;
                            interpolation = 1.0f;
                        }
                        if (fA < 0.0f && interpolation <= 0.0f) {
                            this.k0 = 0.0f;
                            this.o0 = false;
                            f6 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f6);
                    Interpolator interpolator3 = this.V;
                    if (interpolator3 instanceof ff0) {
                        this.W = ((ff0) interpolator3).a();
                    } else {
                        this.W = ((interpolator3.getInterpolation(f6 + f) - interpolation) * fSignum) / f;
                    }
                }
                f6 = interpolation;
            }
            if (Math.abs(this.W) > 1.0E-5f) {
                setState(lf0.G);
            }
            if ((fSignum > 0.0f && f6 >= this.m0) || (fSignum <= 0.0f && f6 <= this.m0)) {
                f6 = this.m0;
                this.o0 = false;
            }
            float f7 = f6;
            lf0 lf0Var = lf0.H;
            if (f7 >= 1.0f || f7 <= 0.0f) {
                this.o0 = false;
                setState(lf0Var);
            }
            int childCount = getChildCount();
            this.D0 = false;
            long nanoTime2 = getNanoTime();
            this.T0 = f7;
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = getChildAt(i2);
                ef0 ef0Var = (ef0) this.g0.get(childAt);
                if (ef0Var != null) {
                    f3 = f5;
                    this.D0 = ef0Var.c(f7, nanoTime2, this.U0, childAt) | this.D0;
                } else {
                    f3 = f5;
                }
                i2++;
                f5 = f3;
            }
            f2 = f5;
            boolean z5 = (fSignum > 0.0f && f7 >= this.m0) || (fSignum <= f2 && f7 <= this.m0);
            if (!this.D0 && !this.o0 && z5) {
                setState(lf0Var);
            }
            if (this.M0) {
                requestLayout();
            }
            this.D0 = (!z5) | this.D0;
            if (f7 <= f2 && (i = this.a0) != -1 && this.b0 != i) {
                this.b0 = i;
                this.U.b(i).a(this);
                setState(lf0Var);
                z4 = true;
            }
            if (f7 >= 1.0d) {
                int i3 = this.b0;
                int i4 = this.c0;
                if (i3 != i4) {
                    this.b0 = i4;
                    this.U.b(i4).a(this);
                    setState(lf0Var);
                    z4 = true;
                }
            }
            if (this.D0 || this.o0) {
                invalidate();
            } else if ((fSignum > 0.0f && f7 == 1.0f) || (fSignum < f2 && f7 == f2)) {
                setState(lf0Var);
            }
            if ((!this.D0 && this.o0 && fSignum > 0.0f && f7 == 1.0f) || (fSignum < f2 && f7 == f2)) {
                v();
            }
        } else {
            f2 = 0.0f;
        }
        float f8 = this.k0;
        if (f8 < 1.0f) {
            if (f8 <= f2) {
                int i5 = this.b0;
                int i6 = this.a0;
                z3 = i5 == i6 ? z4 : true;
                this.b0 = i6;
            }
            this.Z0 |= z4;
            if (z4 && !this.V0) {
                requestLayout();
            }
            this.j0 = this.k0;
        }
        int i7 = this.b0;
        int i8 = this.c0;
        z3 = i7 == i8 ? z4 : true;
        this.b0 = i8;
        z4 = z3;
        this.Z0 |= z4;
        if (z4) {
            requestLayout();
        }
        this.j0 = this.k0;
    }

    public final void q() {
        ArrayList arrayList;
        if ((this.p0 == null && ((arrayList = this.G0) == null || arrayList.isEmpty())) || this.L0 == this.j0) {
            return;
        }
        if (this.K0 != -1) {
            kf0 kf0Var = this.p0;
            if (kf0Var != null) {
                kf0Var.getClass();
            }
            ArrayList arrayList2 = this.G0;
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((kf0) it.next()).getClass();
                }
            }
        }
        this.K0 = -1;
        this.L0 = this.j0;
        kf0 kf0Var2 = this.p0;
        if (kf0Var2 != null) {
            kf0Var2.getClass();
        }
        ArrayList arrayList3 = this.G0;
        if (arrayList3 != null) {
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                ((kf0) it2.next()).getClass();
            }
        }
    }

    public final void r() {
        ArrayList arrayList;
        if ((this.p0 != null || ((arrayList = this.G0) != null && !arrayList.isEmpty())) && this.K0 == -1) {
            this.K0 = this.b0;
            ArrayList arrayList2 = this.c1;
            int iIntValue = !arrayList2.isEmpty() ? ((Integer) arrayList2.get(arrayList2.size() - 1)).intValue() : -1;
            int i = this.b0;
            if (iIntValue != i && i != -1) {
                arrayList2.add(Integer.valueOf(i));
            }
        }
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public final void requestLayout() {
        qf0 qf0Var;
        pf0 pf0Var;
        if (this.M0 || this.b0 != -1 || (qf0Var = this.U) == null || (pf0Var = qf0Var.c) == null || pf0Var.q != 0) {
            super.requestLayout();
        }
    }

    public final void s(int i, float f, float f2, float f3, float[] fArr) {
        View viewE = e(i);
        ef0 ef0Var = (ef0) this.g0.get(viewE);
        if (ef0Var != null) {
            ef0Var.b(f, f2, f3, fArr);
            viewE.getY();
        } else {
            Log.w("MotionLayout", "WARNING could not find view id " + (viewE == null ? zn.j(i, "") : viewE.getContext().getResources().getResourceName(i)));
        }
    }

    public void setDebugMode(int i) {
        this.q0 = i;
        invalidate();
    }

    public void setInteractionEnabled(boolean z) {
        this.f0 = z;
    }

    public void setInterpolatedProgress(float f) {
        if (this.U != null) {
            setState(lf0.G);
            Interpolator interpolatorD = this.U.d();
            if (interpolatorD != null) {
                setProgress(interpolatorD.getInterpolation(f));
                return;
            }
        }
        setProgress(f);
    }

    public void setOnHide(float f) {
        ArrayList arrayList = this.F0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((MotionHelper) this.F0.get(i)).setProgress(f);
            }
        }
    }

    public void setOnShow(float f) {
        ArrayList arrayList = this.E0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((MotionHelper) this.E0.get(i)).setProgress(f);
            }
        }
    }

    public void setProgress(float f) {
        if (!super.isAttachedToWindow()) {
            if (this.W0 == null) {
                this.W0 = new jf0(this);
            }
            this.W0.a = f;
            return;
        }
        lf0 lf0Var = lf0.H;
        if (f <= 0.0f) {
            this.b0 = this.a0;
            if (this.k0 == 0.0f) {
                setState(lf0Var);
            }
        } else if (f >= 1.0f) {
            this.b0 = this.c0;
            if (this.k0 == 1.0f) {
                setState(lf0Var);
            }
        } else {
            this.b0 = -1;
            setState(lf0.G);
        }
        if (this.U == null) {
            return;
        }
        this.n0 = true;
        this.m0 = f;
        this.j0 = f;
        this.l0 = -1L;
        this.h0 = -1L;
        this.V = null;
        this.o0 = true;
        invalidate();
    }

    public void setScene(qf0 qf0Var) {
        l11 l11Var;
        this.U = qf0Var;
        boolean zK = k();
        qf0Var.o = zK;
        pf0 pf0Var = qf0Var.c;
        if (pf0Var != null && (l11Var = pf0Var.l) != null) {
            l11Var.b(zK);
        }
        x();
    }

    public void setState(lf0 lf0Var) {
        lf0 lf0Var2 = lf0.H;
        if (lf0Var == lf0Var2 && this.b0 == -1) {
            return;
        }
        lf0 lf0Var3 = this.X0;
        this.X0 = lf0Var;
        lf0 lf0Var4 = lf0.G;
        if (lf0Var3 == lf0Var4 && lf0Var == lf0Var4) {
            q();
        }
        int iOrdinal = lf0Var3.ordinal();
        if (iOrdinal != 0 && iOrdinal != 1) {
            if (iOrdinal == 2 && lf0Var == lf0Var2) {
                r();
                return;
            }
            return;
        }
        if (lf0Var == lf0Var4) {
            q();
        }
        if (lf0Var == lf0Var2) {
            r();
        }
    }

    public void setTransition(int i) {
        pf0 pf0Var;
        qf0 qf0Var = this.U;
        if (qf0Var != null) {
            Iterator it = qf0Var.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    pf0Var = null;
                    break;
                } else {
                    pf0Var = (pf0) it.next();
                    if (pf0Var.a == i) {
                        break;
                    }
                }
            }
            this.a0 = pf0Var.d;
            this.c0 = pf0Var.c;
            if (!super.isAttachedToWindow()) {
                if (this.W0 == null) {
                    this.W0 = new jf0(this);
                }
                jf0 jf0Var = this.W0;
                jf0Var.c = this.a0;
                jf0Var.d = this.c0;
                return;
            }
            int i2 = this.b0;
            float f = i2 == this.a0 ? 0.0f : i2 == this.c0 ? 1.0f : Float.NaN;
            qf0 qf0Var2 = this.U;
            qf0Var2.c = pf0Var;
            l11 l11Var = pf0Var.l;
            if (l11Var != null) {
                l11Var.b(qf0Var2.o);
            }
            this.Y0.d(this.U.b(this.a0), this.U.b(this.c0));
            x();
            this.k0 = Float.isNaN(f) ? 0.0f : f;
            if (!Float.isNaN(f)) {
                setProgress(f);
                return;
            }
            Log.v("MotionLayout", t40.d0() + " transitionToStart ");
            o(0.0f);
        }
    }

    public void setTransitionDuration(int i) {
        qf0 qf0Var = this.U;
        if (qf0Var == null) {
            Log.e("MotionLayout", "MotionScene not defined");
            return;
        }
        pf0 pf0Var = qf0Var.c;
        if (pf0Var != null) {
            pf0Var.h = i;
        } else {
            qf0Var.j = i;
        }
    }

    public void setTransitionListener(kf0 kf0Var) {
        this.p0 = kf0Var;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.W0 == null) {
            this.W0 = new jf0(this);
        }
        jf0 jf0Var = this.W0;
        jf0Var.getClass();
        jf0Var.a = bundle.getFloat("motion.progress");
        jf0Var.b = bundle.getFloat("motion.velocity");
        jf0Var.c = bundle.getInt("motion.StartState");
        jf0Var.d = bundle.getInt("motion.EndState");
        if (super.isAttachedToWindow()) {
            this.W0.a();
        }
    }

    public final boolean t(float f, float f2, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (t(view.getLeft() + f, view.getTop() + f2, viewGroup.getChildAt(i), motionEvent)) {
                    return true;
                }
            }
        }
        RectF rectF = this.a1;
        rectF.set(view.getLeft() + f, view.getTop() + f2, f + view.getRight(), f2 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (rectF.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public final String toString() {
        Context context = getContext();
        return t40.f0(context, this.a0) + "->" + t40.f0(context, this.c0) + " (pos:" + this.k0 + " Dpos/Dt:" + this.W;
    }

    public final void u(AttributeSet attributeSet) {
        qf0 qf0Var;
        String string;
        isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionLayout_layoutDescription) {
                    this.U = new qf0(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R$styleable.MotionLayout_currentState) {
                    this.b0 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R$styleable.MotionLayout_motionProgress) {
                    this.m0 = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.o0 = true;
                } else if (index == R$styleable.MotionLayout_applyMotionScene) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == R$styleable.MotionLayout_showPaths) {
                    if (this.q0 == 0) {
                        this.q0 = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R$styleable.MotionLayout_motionDebug) {
                    this.q0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.U == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.U = null;
            }
        }
        if (this.q0 != 0) {
            qf0 qf0Var2 = this.U;
            if (qf0Var2 == null) {
                Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            } else {
                int iG = qf0Var2.g();
                qf0 qf0Var3 = this.U;
                d dVarB = qf0Var3.b(qf0Var3.g());
                String strF0 = t40.f0(getContext(), iG);
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    int id = childAt.getId();
                    if (id == -1) {
                        Log.w("MotionLayout", "CHECK: " + strF0 + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
                    }
                    HashMap map = dVarB.c;
                    if ((map.containsKey(Integer.valueOf(id)) ? (c) map.get(Integer.valueOf(id)) : null) == null) {
                        Log.w("MotionLayout", "CHECK: " + strF0 + " NO CONSTRAINTS for " + t40.g0(childAt));
                    }
                }
                Integer[] numArr = (Integer[]) dVarB.c.keySet().toArray(new Integer[0]);
                int length = numArr.length;
                int[] iArr = new int[length];
                for (int i3 = 0; i3 < length; i3++) {
                    iArr[i3] = numArr[i3].intValue();
                }
                for (int i4 = 0; i4 < length; i4++) {
                    int i5 = iArr[i4];
                    String strF02 = t40.f0(getContext(), i5);
                    if (findViewById(iArr[i4]) == null) {
                        Log.w("MotionLayout", "CHECK: " + strF0 + " NO View matches id " + strF02);
                    }
                    if (dVarB.g(i5).d.d == -1) {
                        Log.w("MotionLayout", "CHECK: " + strF0 + "(" + strF02 + ") no LAYOUT_HEIGHT");
                    }
                    if (dVarB.g(i5).d.c == -1) {
                        Log.w("MotionLayout", "CHECK: " + strF0 + "(" + strF02 + ") no LAYOUT_HEIGHT");
                    }
                }
                SparseIntArray sparseIntArray = new SparseIntArray();
                SparseIntArray sparseIntArray2 = new SparseIntArray();
                for (pf0 pf0Var : this.U.d) {
                    if (pf0Var == this.U.c) {
                        Log.v("MotionLayout", "CHECK: CURRENT");
                    }
                    StringBuilder sb = new StringBuilder("CHECK: transition = ");
                    Context context = getContext();
                    String resourceEntryName = pf0Var.d == -1 ? "null" : context.getResources().getResourceEntryName(pf0Var.d);
                    if (pf0Var.c == -1) {
                        string = zn.m(resourceEntryName, " -> null");
                    } else {
                        StringBuilder sbQ = zn.q(resourceEntryName, " -> ");
                        sbQ.append(context.getResources().getResourceEntryName(pf0Var.c));
                        string = sbQ.toString();
                    }
                    sb.append(string);
                    Log.v("MotionLayout", sb.toString());
                    Log.v("MotionLayout", "CHECK: transition.setDuration = " + pf0Var.h);
                    if (pf0Var.d == pf0Var.c) {
                        Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
                    }
                    int i6 = pf0Var.d;
                    int i7 = pf0Var.c;
                    String strF03 = t40.f0(getContext(), i6);
                    String strF04 = t40.f0(getContext(), i7);
                    if (sparseIntArray.get(i6) == i7) {
                        Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strF03 + "->" + strF04);
                    }
                    if (sparseIntArray2.get(i7) == i6) {
                        Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strF03 + "->" + strF04);
                    }
                    sparseIntArray.put(i6, i7);
                    sparseIntArray2.put(i7, i6);
                    if (this.U.b(i6) == null) {
                        Log.e("MotionLayout", " no such constraintSetStart " + strF03);
                    }
                    if (this.U.b(i7) == null) {
                        Log.e("MotionLayout", " no such constraintSetEnd " + strF03);
                    }
                }
            }
        }
        if (this.b0 != -1 || (qf0Var = this.U) == null) {
            return;
        }
        this.b0 = qf0Var.g();
        this.a0 = this.U.g();
        pf0 pf0Var2 = this.U.c;
        this.c0 = pf0Var2 != null ? pf0Var2.c : -1;
    }

    public final void v() {
        pf0 pf0Var;
        l11 l11Var;
        View viewFindViewById;
        qf0 qf0Var = this.U;
        if (qf0Var == null) {
            return;
        }
        if (qf0Var.a(this, this.b0)) {
            requestLayout();
            return;
        }
        int i = this.b0;
        if (i != -1) {
            qf0 qf0Var2 = this.U;
            ArrayList<pf0> arrayList = qf0Var2.d;
            for (pf0 pf0Var2 : arrayList) {
                if (pf0Var2.m.size() > 0) {
                    Iterator it = pf0Var2.m.iterator();
                    while (it.hasNext()) {
                        ((of0) it.next()).b(this);
                    }
                }
            }
            ArrayList<pf0> arrayList2 = qf0Var2.f;
            for (pf0 pf0Var3 : arrayList2) {
                if (pf0Var3.m.size() > 0) {
                    Iterator it2 = pf0Var3.m.iterator();
                    while (it2.hasNext()) {
                        ((of0) it2.next()).b(this);
                    }
                }
            }
            for (pf0 pf0Var4 : arrayList) {
                if (pf0Var4.m.size() > 0) {
                    Iterator it3 = pf0Var4.m.iterator();
                    while (it3.hasNext()) {
                        ((of0) it3.next()).a(this, i, pf0Var4);
                    }
                }
            }
            for (pf0 pf0Var5 : arrayList2) {
                if (pf0Var5.m.size() > 0) {
                    Iterator it4 = pf0Var5.m.iterator();
                    while (it4.hasNext()) {
                        ((of0) it4.next()).a(this, i, pf0Var5);
                    }
                }
            }
        }
        if (!this.U.l() || (pf0Var = this.U.c) == null || (l11Var = pf0Var.l) == null) {
            return;
        }
        int i2 = l11Var.d;
        if (i2 != -1) {
            MotionLayout motionLayout = l11Var.o;
            viewFindViewById = motionLayout.findViewById(i2);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + t40.f0(motionLayout.getContext(), l11Var.d));
            }
        } else {
            viewFindViewById = null;
        }
        if (viewFindViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) viewFindViewById;
            nestedScrollView.setOnTouchListener(new ca(1));
            nestedScrollView.setOnScrollChangeListener(new er0());
        }
    }

    public final void w() {
        ArrayList arrayList;
        if (this.p0 == null && ((arrayList = this.G0) == null || arrayList.isEmpty())) {
            return;
        }
        ArrayList<Integer> arrayList2 = this.c1;
        for (Integer num : arrayList2) {
            kf0 kf0Var = this.p0;
            if (kf0Var != null) {
                num.intValue();
                kf0Var.getClass();
            }
            ArrayList<kf0> arrayList3 = this.G0;
            if (arrayList3 != null) {
                for (kf0 kf0Var2 : arrayList3) {
                    num.intValue();
                    kf0Var2.getClass();
                }
            }
        }
        arrayList2.clear();
    }

    public final void x() {
        this.Y0.e();
        invalidate();
    }

    public final void y(float f, float f2) {
        if (super.isAttachedToWindow()) {
            setProgress(f);
            setState(lf0.G);
            this.W = f2;
            o(1.0f);
            return;
        }
        if (this.W0 == null) {
            this.W0 = new jf0(this);
        }
        jf0 jf0Var = this.W0;
        jf0Var.a = f;
        jf0Var.b = f2;
    }

    public final void z(int i, int i2) {
        if (!super.isAttachedToWindow()) {
            if (this.W0 == null) {
                this.W0 = new jf0(this);
            }
            jf0 jf0Var = this.W0;
            jf0Var.c = i;
            jf0Var.d = i2;
            return;
        }
        qf0 qf0Var = this.U;
        if (qf0Var != null) {
            this.a0 = i;
            this.c0 = i2;
            qf0Var.k(i, i2);
            this.Y0.d(this.U.b(i), this.U.b(i2));
            x();
            this.k0 = 0.0f;
            o(0.0f);
        }
    }

    public void setTransition(pf0 pf0Var) {
        l11 l11Var;
        qf0 qf0Var = this.U;
        qf0Var.c = pf0Var;
        if (pf0Var != null && (l11Var = pf0Var.l) != null) {
            l11Var.b(qf0Var.o);
        }
        setState(lf0.F);
        int i = this.b0;
        pf0 pf0Var2 = this.U.c;
        if (i == (pf0Var2 == null ? -1 : pf0Var2.c)) {
            this.k0 = 1.0f;
            this.j0 = 1.0f;
            this.m0 = 1.0f;
        } else {
            this.k0 = 0.0f;
            this.j0 = 0.0f;
            this.m0 = 0.0f;
        }
        this.l0 = (pf0Var.r & 1) != 0 ? -1L : getNanoTime();
        int iG = this.U.g();
        qf0 qf0Var2 = this.U;
        pf0 pf0Var3 = qf0Var2.c;
        int i2 = pf0Var3 != null ? pf0Var3.c : -1;
        if (iG == this.a0 && i2 == this.c0) {
            return;
        }
        this.a0 = iG;
        this.c0 = i2;
        qf0Var2.k(iG, i2);
        d dVarB = this.U.b(this.a0);
        d dVarB2 = this.U.b(this.c0);
        if0 if0Var = this.Y0;
        if0Var.d(dVarB, dVarB2);
        int i3 = this.a0;
        int i4 = this.c0;
        if0Var.e = i3;
        if0Var.f = i4;
        if0Var.e();
        x();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.W = 0.0f;
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = true;
        this.g0 = new HashMap();
        this.h0 = 0L;
        this.i0 = 1.0f;
        this.j0 = 0.0f;
        this.k0 = 0.0f;
        this.m0 = 0.0f;
        this.o0 = false;
        this.q0 = 0;
        this.s0 = false;
        this.t0 = new ix0();
        this.u0 = new gf0(this);
        this.y0 = false;
        this.D0 = false;
        this.E0 = null;
        this.F0 = null;
        this.G0 = null;
        this.H0 = 0;
        this.I0 = -1L;
        this.J0 = 0.0f;
        this.K0 = 0;
        this.L0 = 0.0f;
        this.M0 = false;
        this.U0 = new z8(23);
        this.V0 = false;
        this.X0 = lf0.E;
        this.Y0 = new if0(this);
        this.Z0 = false;
        this.a1 = new RectF();
        this.b1 = null;
        this.c1 = new ArrayList();
        u(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.W = 0.0f;
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = true;
        this.g0 = new HashMap();
        this.h0 = 0L;
        this.i0 = 1.0f;
        this.j0 = 0.0f;
        this.k0 = 0.0f;
        this.m0 = 0.0f;
        this.o0 = false;
        this.q0 = 0;
        this.s0 = false;
        this.t0 = new ix0();
        this.u0 = new gf0(this);
        this.y0 = false;
        this.D0 = false;
        this.E0 = null;
        this.F0 = null;
        this.G0 = null;
        this.H0 = 0;
        this.I0 = -1L;
        this.J0 = 0.0f;
        this.K0 = 0;
        this.L0 = 0.0f;
        this.M0 = false;
        this.U0 = new z8(23);
        this.V0 = false;
        this.X0 = lf0.E;
        this.Y0 = new if0(this);
        this.Z0 = false;
        this.a1 = new RectF();
        this.b1 = null;
        this.c1 = new ArrayList();
        u(attributeSet);
    }
}
