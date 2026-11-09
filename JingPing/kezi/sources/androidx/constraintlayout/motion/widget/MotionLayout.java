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
import defpackage.ek0;
import defpackage.g8;
import defpackage.hg;
import defpackage.hk;
import defpackage.i70;
import defpackage.j70;
import defpackage.jg;
import defpackage.k70;
import defpackage.kg;
import defpackage.kk;
import defpackage.l70;
import defpackage.m70;
import defpackage.m90;
import defpackage.n70;
import defpackage.ng;
import defpackage.nm;
import defpackage.o70;
import defpackage.oy;
import defpackage.p70;
import defpackage.q70;
import defpackage.qk0;
import defpackage.r6;
import defpackage.r70;
import defpackage.rk0;
import defpackage.s70;
import defpackage.u70;
import defpackage.uk0;
import defpackage.v6;
import defpackage.v70;
import defpackage.w00;
import defpackage.w2;
import defpackage.w70;
import defpackage.yo;
import defpackage.zo0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements m90 {
    public static final /* synthetic */ int A0 = 0;
    public final HashMap A;
    public long B;
    public float C;
    public float D;
    public float E;
    public long F;
    public float G;
    public boolean H;
    public boolean I;
    public q70 J;
    public int K;
    public m70 L;
    public boolean M;
    public final uk0 N;
    public final l70 O;
    public kk P;
    public int Q;
    public int R;
    public boolean S;
    public float T;
    public float U;
    public long V;
    public float W;
    public boolean a0;
    public ArrayList b0;
    public ArrayList c0;
    public ArrayList d0;
    public int e0;
    public long f0;
    public float g0;
    public int h0;
    public float i0;
    public boolean j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public float q0;
    public w70 r;
    public final o70 r0;
    public Interpolator s;
    public boolean s0;
    public float t;
    public p70 t0;
    public int u;
    public r70 u0;
    public int v;
    public final n70 v0;
    public int w;
    public boolean w0;
    public int x;
    public final RectF x0;
    public int y;
    public View y0;
    public boolean z;
    public final ArrayList z0;

    public MotionLayout(Context context) {
        super(context);
        this.t = 0.0f;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = 0;
        this.y = 0;
        this.z = true;
        this.A = new HashMap();
        this.B = 0L;
        this.C = 1.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.G = 0.0f;
        this.I = false;
        this.K = 0;
        this.M = false;
        this.N = new uk0();
        this.O = new l70(this);
        this.S = false;
        this.a0 = false;
        this.b0 = null;
        this.c0 = null;
        this.d0 = null;
        this.e0 = 0;
        this.f0 = -1L;
        this.g0 = 0.0f;
        this.h0 = 0;
        this.i0 = 0.0f;
        this.j0 = false;
        this.r0 = new o70(25);
        this.s0 = false;
        this.u0 = r70.b;
        this.v0 = new n70(this);
        this.w0 = false;
        this.x0 = new RectF();
        this.y0 = null;
        this.z0 = new ArrayList();
        u(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void A(float r12, float r13, int r14) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.A(float, float, int):void");
    }

    public final void B(int i) {
        w2 w2Var;
        if (!super.isAttachedToWindow()) {
            if (this.t0 == null) {
                this.t0 = new p70(this);
            }
            this.t0.d = i;
            return;
        }
        w70 w70Var = this.r;
        if (w70Var != null && (w2Var = w70Var.b) != null) {
            int i2 = this.v;
            float f = -1;
            qk0 qk0Var = (qk0) ((SparseArray) w2Var.c).get(i);
            if (qk0Var == null) {
                i2 = i;
            } else {
                ArrayList arrayList = qk0Var.b;
                int i3 = qk0Var.c;
                if (f != -1.0f && f != -1.0f) {
                    Iterator it = arrayList.iterator();
                    rk0 rk0Var = null;
                    while (true) {
                        if (it.hasNext()) {
                            rk0 rk0Var2 = (rk0) it.next();
                            if (rk0Var2.a(f, f)) {
                                if (i2 == rk0Var2.e) {
                                    break;
                                } else {
                                    rk0Var = rk0Var2;
                                }
                            }
                        } else if (rk0Var != null) {
                            i2 = rk0Var.e;
                        }
                    }
                } else if (i3 != i2) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        if (i2 == ((rk0) it2.next()).e) {
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
        int i4 = this.v;
        if (i4 == i) {
            return;
        }
        if (this.u == i) {
            o(0.0f);
            return;
        }
        if (this.w == i) {
            o(1.0f);
            return;
        }
        this.w = i;
        if (i4 != -1) {
            z(i4, i);
            o(1.0f);
            this.E = 0.0f;
            o(1.0f);
            return;
        }
        this.M = false;
        this.G = 1.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = getNanoTime();
        this.B = getNanoTime();
        this.H = false;
        this.s = null;
        w70 w70Var2 = this.r;
        this.C = (w70Var2.c != null ? r6.h : w70Var2.j) / 1000.0f;
        this.u = -1;
        w70Var2.k(-1, this.w);
        this.r.g();
        int childCount = getChildCount();
        HashMap map = this.A;
        map.clear();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            map.put(childAt, new j70(childAt));
        }
        this.I = true;
        d dVarB = this.r.b(i);
        n70 n70Var = this.v0;
        n70Var.d(null, dVarB);
        x();
        n70Var.a();
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            View childAt2 = getChildAt(i6);
            j70 j70Var = (j70) map.get(childAt2);
            if (j70Var != null) {
                s70 s70Var = j70Var.d;
                s70Var.d = 0.0f;
                s70Var.e = 0.0f;
                float x = childAt2.getX();
                float y = childAt2.getY();
                float width = childAt2.getWidth();
                float height = childAt2.getHeight();
                s70Var.f = x;
                s70Var.g = y;
                s70Var.h = width;
                s70Var.i = height;
                i70 i70Var = j70Var.f;
                i70Var.getClass();
                childAt2.getX();
                childAt2.getY();
                childAt2.getWidth();
                childAt2.getHeight();
                i70Var.d = childAt2.getVisibility();
                i70Var.b = childAt2.getVisibility() != 0 ? 0.0f : childAt2.getAlpha();
                i70Var.e = childAt2.getElevation();
                i70Var.f = childAt2.getRotation();
                i70Var.g = childAt2.getRotationX();
                i70Var.h = childAt2.getRotationY();
                i70Var.i = childAt2.getScaleX();
                i70Var.j = childAt2.getScaleY();
                i70Var.k = childAt2.getPivotX();
                i70Var.l = childAt2.getPivotY();
                i70Var.m = childAt2.getTranslationX();
                i70Var.n = childAt2.getTranslationY();
                i70Var.o = childAt2.getTranslationZ();
            }
        }
        getWidth();
        getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            j70 j70Var2 = (j70) map.get(getChildAt(i7));
            this.r.e(j70Var2);
            j70Var2.e(getNanoTime());
        }
        v70 v70Var = this.r.c;
        float f2 = v70Var != null ? v70Var.i : 0.0f;
        if (f2 != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                s70 s70Var2 = ((j70) map.get(getChildAt(i8))).e;
                float f3 = s70Var2.g + s70Var2.f;
                fMin = Math.min(fMin, f3);
                fMax = Math.max(fMax, f3);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                j70 j70Var3 = (j70) map.get(getChildAt(i9));
                s70 s70Var3 = j70Var3.e;
                float f4 = s70Var3.f;
                float f5 = s70Var3.g;
                j70Var3.l = 1.0f / (1.0f - f2);
                j70Var3.k = f2 - ((((f4 + f5) - fMin) * f2) / (fMax - fMin));
            }
        }
        this.D = 0.0f;
        this.E = 0.0f;
        this.I = true;
        invalidate();
    }

    @Override // defpackage.l90
    public final void b(View view, View view2, int i, int i2) {
    }

    @Override // defpackage.l90
    public final void c(View view, int i) {
        zo0 zo0Var;
        w70 w70Var = this.r;
        if (w70Var == null) {
            return;
        }
        float f = this.T;
        float f2 = this.W;
        float f3 = f / f2;
        float f4 = this.U / f2;
        v70 v70Var = w70Var.c;
        if (v70Var == null || (zo0Var = v70Var.l) == null) {
            return;
        }
        zo0Var.k = false;
        MotionLayout motionLayout = zo0Var.o;
        float progress = motionLayout.getProgress();
        zo0Var.o.s(zo0Var.d, progress, zo0Var.h, zo0Var.g, zo0Var.l);
        float f5 = zo0Var.i;
        float[] fArr = zo0Var.l;
        float f6 = f5 != 0.0f ? (f3 * f5) / fArr[0] : (f4 * zo0Var.j) / fArr[1];
        if (!Float.isNaN(f6)) {
            progress += f6 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i2 = zo0Var.c;
            if ((i2 != 3) && z) {
                motionLayout.A(((double) progress) >= 0.5d ? 1.0f : 0.0f, f6, i2);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        m70 m70Var;
        Iterator it;
        char c;
        char c2;
        Paint paint;
        int i6;
        float f;
        m70 m70Var2;
        double dA;
        Paint paint2;
        float f2;
        String resourceEntryName;
        Canvas canvas2 = canvas;
        int i7 = 0;
        p(false);
        super.dispatchDraw(canvas);
        if (this.r == null) {
            return;
        }
        int i8 = 1;
        if ((this.K & 1) == 1 && !isInEditMode()) {
            this.e0++;
            long nanoTime = getNanoTime();
            long j = this.f0;
            if (j != -1) {
                if (nanoTime - j > 200000000) {
                    this.g0 = ((int) ((this.e0 / (r9 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.e0 = 0;
                    this.f0 = nanoTime;
                }
            } else {
                this.f0 = nanoTime;
            }
            Paint paint3 = new Paint();
            paint3.setTextSize(42.0f);
            float progress = ((int) (getProgress() * 1000.0f)) / 10.0f;
            StringBuilder sb = new StringBuilder();
            sb.append(this.g0);
            sb.append(" fps ");
            int i9 = this.u;
            StringBuilder sbL = hk.l(hk.k(sb, i9 == -1 ? "UNDEFINED" : getContext().getResources().getResourceEntryName(i9), " -> "));
            int i10 = this.w;
            sbL.append(i10 == -1 ? "UNDEFINED" : getContext().getResources().getResourceEntryName(i10));
            sbL.append(" (progress: ");
            sbL.append(progress);
            sbL.append(" ) state=");
            int i11 = this.v;
            if (i11 == -1) {
                resourceEntryName = "undefined";
            } else {
                resourceEntryName = i11 != -1 ? getContext().getResources().getResourceEntryName(i11) : "UNDEFINED";
            }
            sbL.append(resourceEntryName);
            String string = sbL.toString();
            paint3.setColor(-16777216);
            canvas2.drawText(string, 11.0f, getHeight() - 29, paint3);
            paint3.setColor(-7864184);
            canvas2.drawText(string, 10.0f, getHeight() - 30, paint3);
        }
        if (this.K > 1) {
            if (this.L == null) {
                this.L = new m70(this);
            }
            m70 m70Var3 = this.L;
            HashMap map = this.A;
            w70 w70Var = this.r;
            v70 v70Var = w70Var.c;
            int i12 = v70Var != null ? v70Var.h : w70Var.j;
            int i13 = this.K;
            m70Var3.getClass();
            if (map == null || map.size() == 0) {
                return;
            }
            canvas2.save();
            MotionLayout motionLayout = m70Var3.n;
            boolean zIsInEditMode = motionLayout.isInEditMode();
            Paint paint4 = m70Var3.e;
            if (!zIsInEditMode && (i13 & 1) == 2) {
                String str = motionLayout.getContext().getResources().getResourceName(motionLayout.w) + ":" + motionLayout.getProgress();
                canvas2.drawText(str, 10.0f, motionLayout.getHeight() - 30, m70Var3.h);
                canvas2.drawText(str, 11.0f, motionLayout.getHeight() - 29, paint4);
            }
            Iterator it2 = map.values().iterator();
            while (it2.hasNext()) {
                j70 j70Var = (j70) it2.next();
                int iMax = j70Var.d.c;
                ArrayList arrayList = j70Var.s;
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    iMax = Math.max(iMax, ((s70) it3.next()).c);
                }
                int iMax2 = Math.max(iMax, j70Var.e.c);
                if (i13 > 0 && iMax2 == 0) {
                    iMax2 = i8;
                }
                if (iMax2 != 0) {
                    s70 s70Var = j70Var.d;
                    float[] fArr = m70Var3.c;
                    if (fArr != null) {
                        double[] dArrZ = j70Var.h[i7].z();
                        i = i7;
                        int[] iArr = m70Var3.b;
                        if (iArr != null) {
                            Iterator it4 = arrayList.iterator();
                            int i14 = i;
                            while (it4.hasNext()) {
                                ((s70) it4.next()).getClass();
                                iArr[i14] = i;
                                i14++;
                            }
                        }
                        int i15 = i;
                        int i16 = i15;
                        while (i15 < dArrZ.length) {
                            j70Var.h[i].t(dArrZ[i15], j70Var.n);
                            s70Var.c(j70Var.m, j70Var.n, fArr, i16);
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
                    m70Var3.k = i4;
                    if (iMax2 >= 1) {
                        int i17 = i2 / 16;
                        float[] fArr2 = m70Var3.a;
                        if (fArr2 == null || fArr2.length != i17 * 2) {
                            m70Var3.a = new float[i17 * 2];
                            m70Var3.d = new Path();
                        }
                        int i18 = m70Var3.m;
                        float f3 = i18;
                        canvas2.translate(f3, f3);
                        paint4.setColor(1996488704);
                        Paint paint5 = m70Var3.i;
                        paint5.setColor(1996488704);
                        Paint paint6 = m70Var3.f;
                        paint6.setColor(1996488704);
                        Paint paint7 = m70Var3.g;
                        paint7.setColor(1996488704);
                        float[] fArr3 = m70Var3.a;
                        float f4 = 1.0f / (i17 - 1);
                        HashMap map2 = j70Var.w;
                        it = it2;
                        ek0 ek0Var = map2 == null ? null : (ek0) map2.get("translationX");
                        HashMap map3 = j70Var.w;
                        ek0 ek0Var2 = map3 == null ? null : (ek0) map3.get("translationY");
                        HashMap map4 = j70Var.x;
                        w00 w00Var = map4 == null ? null : (w00) map4.get("translationX");
                        HashMap map5 = j70Var.x;
                        w00 w00Var2 = map5 == null ? null : (w00) map5.get("translationY");
                        int i19 = i;
                        while (true) {
                            float f5 = Float.NaN;
                            float f6 = 0.0f;
                            if (i19 >= i17) {
                                break;
                            }
                            int i20 = i17;
                            float f7 = i19 * f4;
                            float f8 = j70Var.l;
                            if (f8 != 1.0f) {
                                float f9 = j70Var.k;
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
                            nm nmVar = s70Var.b;
                            Iterator it5 = arrayList.iterator();
                            while (it5.hasNext()) {
                                Iterator it6 = it5;
                                s70 s70Var2 = (s70) it5.next();
                                Paint paint8 = paint5;
                                nm nmVar2 = s70Var2.b;
                                if (nmVar2 != null) {
                                    float f10 = s70Var2.d;
                                    if (f10 < f) {
                                        f6 = f10;
                                        nmVar = nmVar2;
                                    } else if (Float.isNaN(f5)) {
                                        f5 = s70Var2.d;
                                    }
                                }
                                paint5 = paint8;
                                it5 = it6;
                            }
                            Paint paint9 = paint5;
                            if (nmVar != null) {
                                if (Float.isNaN(f5)) {
                                    f5 = 1.0f;
                                }
                                m70Var2 = m70Var3;
                                dA = (((float) nmVar.a((f - f6) / r26)) * (f5 - f6)) + f6;
                            } else {
                                m70Var2 = m70Var3;
                                dA = d;
                            }
                            m70 m70Var4 = m70Var2;
                            j70Var.h[i].t(dA, j70Var.n);
                            r6 r6Var = j70Var.i;
                            if (r6Var != null) {
                                double[] dArr = j70Var.n;
                                paint2 = paint6;
                                if (dArr.length > 0) {
                                    r6Var.t(dA, dArr);
                                }
                            } else {
                                paint2 = paint6;
                            }
                            int i21 = i6 * 2;
                            s70Var.c(j70Var.m, j70Var.n, fArr3, i21);
                            if (w00Var != null) {
                                fArr3[i21] = w00Var.a(f) + fArr3[i21];
                            } else if (ek0Var != null) {
                                fArr3[i21] = ek0Var.a(f) + fArr3[i21];
                            }
                            if (w00Var2 != null) {
                                int i22 = i21 + 1;
                                fArr3[i22] = w00Var2.a(f) + fArr3[i22];
                            } else if (ek0Var2 != null) {
                                int i23 = i21 + 1;
                                fArr3[i23] = ek0Var2.a(f) + fArr3[i23];
                            }
                            i19 = i6 + 1;
                            m70Var3 = m70Var4;
                            i17 = i20;
                            paint6 = paint2;
                            paint5 = paint9;
                            paint7 = paint;
                        }
                        m70Var = m70Var3;
                        m70Var.a(canvas2, iMax2, m70Var.k, j70Var);
                        paint4.setColor(-21965);
                        paint6.setColor(-2067046);
                        paint5.setColor(-2067046);
                        paint7.setColor(-13391360);
                        float f11 = -i18;
                        canvas2.translate(f11, f11);
                        m70Var.a(canvas2, iMax2, m70Var.k, j70Var);
                        char c3 = 5;
                        if (iMax2 == 5) {
                            m70Var.d.reset();
                            int i24 = i;
                            while (i24 <= 50) {
                                j70Var.h[i].t(j70Var.a(i24 / 50, null), j70Var.n);
                                int[] iArr2 = j70Var.m;
                                double[] dArr2 = j70Var.n;
                                float f12 = s70Var.f;
                                float f13 = s70Var.g;
                                float f14 = s70Var.h;
                                float f15 = s70Var.i;
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
                                float[] fArr4 = m70Var.j;
                                fArr4[i] = f19;
                                fArr4[1] = f20;
                                fArr4[2] = f21;
                                fArr4[3] = f20;
                                fArr4[4] = f21;
                                fArr4[c2] = f22;
                                fArr4[6] = f19;
                                fArr4[7] = f22;
                                m70Var.d.moveTo(f19, f20);
                                m70Var.d.lineTo(fArr4[2], fArr4[3]);
                                m70Var.d.lineTo(fArr4[4], fArr4[c2]);
                                m70Var.d.lineTo(fArr4[6], fArr4[7]);
                                m70Var.d.close();
                                i24++;
                                c3 = c2;
                            }
                            c = 2;
                            i5 = 1;
                            paint4.setColor(1140850688);
                            canvas2 = canvas;
                            canvas2.translate(2.0f, 2.0f);
                            canvas2.drawPath(m70Var.d, paint4);
                            canvas2.translate(-2.0f, -2.0f);
                            paint4.setColor(-65536);
                            canvas2.drawPath(m70Var.d, paint4);
                        } else {
                            c = 2;
                            i5 = 1;
                        }
                    } else {
                        i5 = 1;
                        m70Var = m70Var3;
                        it = it2;
                        c = 2;
                    }
                    m70Var3 = m70Var;
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    @Override // defpackage.l90
    public final void e(View view, int i, int i2, int[] iArr, int i3) {
        v70 v70Var;
        boolean z;
        char c;
        ?? r15;
        zo0 zo0Var;
        float f;
        zo0 zo0Var2;
        zo0 zo0Var3;
        int i4;
        w70 w70Var = this.r;
        if (w70Var == null || (v70Var = w70Var.c) == null || (z = v70Var.o)) {
            return;
        }
        if (z || (zo0Var3 = v70Var.l) == null || (i4 = zo0Var3.e) == -1 || view.getId() == i4) {
            w70 w70Var2 = this.r;
            if (w70Var2 != null) {
                v70 v70Var2 = w70Var2.c;
                if ((v70Var2 == null || (zo0Var2 = v70Var2.l) == null) ? false : zo0Var2.r) {
                    float f2 = this.D;
                    if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                        return;
                    }
                }
            }
            if (v70Var.l != null) {
                zo0 zo0Var4 = this.r.c.l;
                if ((zo0Var4.t & 1) != 0) {
                    float f3 = i;
                    float f4 = i2;
                    zo0Var4.o.s(zo0Var4.d, zo0Var4.o.getProgress(), zo0Var4.h, zo0Var4.g, zo0Var4.l);
                    float f5 = zo0Var4.i;
                    float[] fArr = zo0Var4.l;
                    if (f5 != 0.0f) {
                        if (fArr[0] == 0.0f) {
                            fArr[0] = 1.0E-7f;
                        }
                        f = (f3 * f5) / fArr[0];
                    } else {
                        if (fArr[1] == 0.0f) {
                            fArr[1] = 1.0E-7f;
                        }
                        f = (f4 * zo0Var4.j) / fArr[1];
                    }
                    float f6 = this.E;
                    if ((f6 <= 0.0f && f < 0.0f) || (f6 >= 1.0f && f > 0.0f)) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new v6(13, (ViewGroup) view));
                        return;
                    }
                }
            }
            float f7 = this.D;
            long nanoTime = getNanoTime();
            float f8 = i;
            this.T = f8;
            float f9 = i2;
            this.U = f9;
            this.W = (float) ((nanoTime - this.V) * 1.0E-9d);
            this.V = nanoTime;
            v70 v70Var3 = this.r.c;
            if (v70Var3 == null || (zo0Var = v70Var3.l) == null) {
                c = 1;
                r15 = 0;
            } else {
                MotionLayout motionLayout = zo0Var.o;
                float progress = motionLayout.getProgress();
                if (!zo0Var.k) {
                    zo0Var.k = true;
                    motionLayout.setProgress(progress);
                }
                c = 1;
                zo0Var.o.s(zo0Var.d, progress, zo0Var.h, zo0Var.g, zo0Var.l);
                float f10 = zo0Var.i;
                float[] fArr2 = zo0Var.l;
                boolean z2 = false;
                if (Math.abs((zo0Var.j * fArr2[1]) + (f10 * fArr2[0])) < 0.01d) {
                    fArr2[0] = 0.01f;
                    fArr2[1] = 0.01f;
                }
                float f11 = zo0Var.i;
                float fMax = Math.max(Math.min(progress + (f11 != 0.0f ? (f8 * f11) / fArr2[0] : (f9 * zo0Var.j) / fArr2[1]), 1.0f), 0.0f);
                r15 = z2;
                if (fMax != motionLayout.getProgress()) {
                    motionLayout.setProgress(fMax);
                    r15 = z2;
                }
            }
            if (f7 != this.D) {
                iArr[r15] = i;
                iArr[c] = i2;
            }
            p(r15);
            if (iArr[r15] == 0 && iArr[c] == 0) {
                return;
            }
            this.S = c;
        }
    }

    @Override // defpackage.m90
    public final void f(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (this.S || i != 0 || i2 != 0) {
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
        }
        this.S = false;
    }

    public int[] getConstraintSetIds() {
        w70 w70Var = this.r;
        if (w70Var == null) {
            return null;
        }
        SparseArray sparseArray = w70Var.g;
        int size = sparseArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = sparseArray.keyAt(i);
        }
        return iArr;
    }

    public int getCurrentState() {
        return this.v;
    }

    public ArrayList<v70> getDefinedTransitions() {
        w70 w70Var = this.r;
        if (w70Var == null) {
            return null;
        }
        return w70Var.d;
    }

    public kk getDesignTool() {
        if (this.P == null) {
            this.P = new kk();
        }
        return this.P;
    }

    public int getEndState() {
        return this.w;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.E;
    }

    public int getStartState() {
        return this.u;
    }

    public float getTargetPosition() {
        return this.G;
    }

    public Bundle getTransitionState() {
        if (this.t0 == null) {
            this.t0 = new p70(this);
        }
        p70 p70Var = this.t0;
        MotionLayout motionLayout = p70Var.e;
        p70Var.d = motionLayout.w;
        p70Var.c = motionLayout.u;
        p70Var.b = motionLayout.getVelocity();
        p70Var.a = motionLayout.getProgress();
        p70 p70Var2 = this.t0;
        p70Var2.getClass();
        Bundle bundle = new Bundle();
        bundle.putFloat("motion.progress", p70Var2.a);
        bundle.putFloat("motion.velocity", p70Var2.b);
        bundle.putInt("motion.StartState", p70Var2.c);
        bundle.putInt("motion.EndState", p70Var2.d);
        return bundle;
    }

    public long getTransitionTimeMs() {
        w70 w70Var = this.r;
        if (w70Var != null) {
            this.C = (w70Var.c != null ? r2.h : w70Var.j) / 1000.0f;
        }
        return (long) (this.C * 1000.0f);
    }

    public float getVelocity() {
        return this.t;
    }

    @Override // defpackage.l90
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
    }

    @Override // defpackage.l90
    public final boolean j(View view, View view2, int i, int i2) {
        v70 v70Var;
        zo0 zo0Var;
        w70 w70Var = this.r;
        return (w70Var == null || (v70Var = w70Var.c) == null || (zo0Var = v70Var.l) == null || (zo0Var.t & 2) != 0) ? false : true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public final void l(int i) {
        this.l = null;
    }

    public final void o(float f) {
        w70 w70Var = this.r;
        if (w70Var == null) {
            return;
        }
        float f2 = this.E;
        float f3 = this.D;
        if (f2 != f3 && this.H) {
            this.E = f3;
        }
        float f4 = this.E;
        if (f4 == f) {
            return;
        }
        this.M = false;
        this.G = f;
        this.C = (w70Var.c != null ? r3.h : w70Var.j) / 1000.0f;
        setProgress(f);
        this.s = this.r.d();
        this.H = false;
        this.B = getNanoTime();
        this.I = true;
        this.D = f4;
        this.E = f4;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        int i;
        super.onAttachedToWindow();
        w70 w70Var = this.r;
        if (w70Var != null && (i = this.v) != -1) {
            d dVarB = w70Var.b(i);
            w70 w70Var2 = this.r;
            int i2 = 0;
            loop0: while (true) {
                SparseArray sparseArray = w70Var2.g;
                if (i2 < sparseArray.size()) {
                    int iKeyAt = sparseArray.keyAt(i2);
                    SparseIntArray sparseIntArray = w70Var2.i;
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
                    w70Var2.j(iKeyAt);
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
                                hg hgVar = cVar.d;
                                if (z) {
                                    hgVar.e0 = ((ConstraintHelper) childAt).getReferencedIds();
                                    if (childAt instanceof Barrier) {
                                        Barrier barrier = (Barrier) childAt;
                                        hgVar.j0 = barrier.k.j0;
                                        hgVar.b0 = barrier.getType();
                                        hgVar.c0 = barrier.getMargin();
                                    }
                                }
                                hgVar.b = true;
                            }
                            jg jgVar = cVar.b;
                            if (!jgVar.a) {
                                jgVar.b = childAt.getVisibility();
                                jgVar.d = childAt.getAlpha();
                                jgVar.a = true;
                            }
                            kg kgVar = cVar.e;
                            if (!kgVar.a) {
                                kgVar.a = true;
                                kgVar.b = childAt.getRotation();
                                kgVar.c = childAt.getRotationX();
                                kgVar.d = childAt.getRotationY();
                                kgVar.e = childAt.getScaleX();
                                kgVar.f = childAt.getScaleY();
                                float pivotX = childAt.getPivotX();
                                float pivotY = childAt.getPivotY();
                                if (pivotX != 0.0d || pivotY != 0.0d) {
                                    kgVar.g = pivotX;
                                    kgVar.h = pivotY;
                                }
                                kgVar.i = childAt.getTranslationX();
                                kgVar.j = childAt.getTranslationY();
                                kgVar.k = childAt.getTranslationZ();
                                if (kgVar.l) {
                                    kgVar.m = childAt.getElevation();
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
            this.u = this.v;
        }
        v();
        p70 p70Var = this.t0;
        if (p70Var != null) {
            p70Var.a();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        v70 v70Var;
        zo0 zo0Var;
        int i;
        RectF rectFA;
        w70 w70Var = this.r;
        if (w70Var == null || !this.z || (v70Var = w70Var.c) == null || v70Var.o || (zo0Var = v70Var.l) == null) {
            return false;
        }
        if ((motionEvent.getAction() == 0 && (rectFA = zo0Var.a(this, new RectF())) != null && !rectFA.contains(motionEvent.getX(), motionEvent.getY())) || (i = zo0Var.e) == -1) {
            return false;
        }
        View view = this.y0;
        if (view == null || view.getId() != i) {
            this.y0 = findViewById(i);
        }
        if (this.y0 == null) {
            return false;
        }
        RectF rectF = this.x0;
        rectF.set(r0.getLeft(), this.y0.getTop(), this.y0.getRight(), this.y0.getBottom());
        if (!rectF.contains(motionEvent.getX(), motionEvent.getY()) || t(0.0f, 0.0f, this.y0, motionEvent)) {
            return false;
        }
        return onTouchEvent(motionEvent);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) throws Throwable {
        MotionLayout motionLayout;
        this.s0 = true;
        try {
            if (this.r == null) {
                super.onLayout(z, i, i2, i3, i4);
                this.s0 = false;
                return;
            }
            motionLayout = this;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            try {
                if (motionLayout.Q != i5 || motionLayout.R != i6) {
                    x();
                    p(true);
                }
                motionLayout.Q = i5;
                motionLayout.R = i6;
                motionLayout.s0 = false;
                return;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            motionLayout = this;
        }
        Throwable th3 = th;
        motionLayout.s0 = false;
        throw th3;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        boolean z;
        if (this.r == null) {
            super.onMeasure(i, i2);
            return;
        }
        boolean z2 = true;
        boolean z3 = (this.x == i && this.y == i2) ? false : true;
        if (this.w0) {
            this.w0 = false;
            v();
            w();
            z3 = true;
        }
        if (this.i) {
            z3 = true;
        }
        this.x = i;
        this.y = i2;
        int iG = this.r.g();
        v70 v70Var = this.r.c;
        int i3 = v70Var == null ? -1 : v70Var.c;
        ng ngVar = this.d;
        n70 n70Var = this.v0;
        if ((!z3 && iG == n70Var.e && i3 == n70Var.f) || this.u == -1) {
            z = true;
        } else {
            super.onMeasure(i, i2);
            n70Var.d(this.r.b(iG), this.r.b(i3));
            n70Var.e();
            n70Var.e = iG;
            n70Var.f = i3;
            z = false;
        }
        if (this.j0 || z) {
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int iM = ngVar.m() + getPaddingRight() + getPaddingLeft();
            int iJ = ngVar.j() + paddingBottom;
            int i4 = this.o0;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                iM = (int) ((this.q0 * (this.m0 - r1)) + this.k0);
                requestLayout();
            }
            int i5 = this.p0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iJ = (int) ((this.q0 * (this.n0 - r2)) + this.l0);
                requestLayout();
            }
            setMeasuredDimension(iM, iJ);
        }
        float fSignum = Math.signum(this.G - this.E);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.s;
        float interpolation = this.E + (!(interpolator instanceof uk0) ? (((nanoTime - this.F) * fSignum) * 1.0E-9f) / this.C : 0.0f);
        if (this.H) {
            interpolation = this.G;
        }
        if ((fSignum <= 0.0f || interpolation < this.G) && (fSignum > 0.0f || interpolation > this.G)) {
            z2 = false;
        } else {
            interpolation = this.G;
        }
        if (interpolator != null && !z2) {
            interpolation = this.M ? interpolator.getInterpolation((nanoTime - this.B) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.G) || (fSignum <= 0.0f && interpolation <= this.G)) {
            interpolation = this.G;
        }
        float f = interpolation;
        this.q0 = f;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            j70 j70Var = (j70) this.A.get(childAt);
            if (j70Var != null) {
                j70Var.c(f, nanoTime2, this.r0, childAt);
            }
        }
        if (this.j0) {
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
        zo0 zo0Var;
        w70 w70Var = this.r;
        if (w70Var != null) {
            boolean zK = k();
            w70Var.o = zK;
            v70 v70Var = w70Var.c;
            if (v70Var == null || (zo0Var = v70Var.l) == null) {
                return;
            }
            zo0Var.b(zK);
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r28) {
        /*
            Method dump skipped, instruction units count: 1036
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.d0 == null) {
                this.d0 = new ArrayList();
            }
            this.d0.add(motionHelper);
            if (motionHelper.i) {
                if (this.b0 == null) {
                    this.b0 = new ArrayList();
                }
                this.b0.add(motionHelper);
            }
            if (motionHelper.j) {
                if (this.c0 == null) {
                    this.c0 = new ArrayList();
                }
                this.c0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList arrayList = this.b0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList arrayList2 = this.c0;
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
        if (this.F == -1) {
            this.F = getNanoTime();
        }
        float f4 = this.E;
        float f5 = 0.0f;
        if (f4 > 0.0f && f4 < 1.0f) {
            this.v = -1;
        }
        boolean z4 = false;
        if (this.a0 || (this.I && (z || this.G != f4))) {
            float fSignum = Math.signum(this.G - f4);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.s;
            if (interpolator instanceof k70) {
                f = 0.0f;
            } else {
                f = (((nanoTime - this.F) * fSignum) * 1.0E-9f) / this.C;
                this.t = f;
            }
            float f6 = this.E + f;
            if (this.H) {
                f6 = this.G;
            }
            if ((fSignum <= 0.0f || f6 < this.G) && (fSignum > 0.0f || f6 > this.G)) {
                z2 = false;
            } else {
                f6 = this.G;
                this.I = false;
                z2 = true;
            }
            this.E = f6;
            this.D = f6;
            this.F = nanoTime;
            if (interpolator != null && !z2) {
                if (this.M) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.B) * 1.0E-9f);
                    this.E = interpolation;
                    this.F = nanoTime;
                    Interpolator interpolator2 = this.s;
                    if (interpolator2 instanceof k70) {
                        float fA = ((k70) interpolator2).a();
                        this.t = fA;
                        if (Math.abs(fA) * this.C <= 1.0E-5f) {
                            this.I = false;
                        }
                        if (fA > 0.0f && interpolation >= 1.0f) {
                            this.E = 1.0f;
                            this.I = false;
                            interpolation = 1.0f;
                        }
                        if (fA < 0.0f && interpolation <= 0.0f) {
                            this.E = 0.0f;
                            this.I = false;
                            f6 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f6);
                    Interpolator interpolator3 = this.s;
                    if (interpolator3 instanceof k70) {
                        this.t = ((k70) interpolator3).a();
                    } else {
                        this.t = ((interpolator3.getInterpolation(f6 + f) - interpolation) * fSignum) / f;
                    }
                }
                f6 = interpolation;
            }
            if (Math.abs(this.t) > 1.0E-5f) {
                setState(r70.d);
            }
            if ((fSignum > 0.0f && f6 >= this.G) || (fSignum <= 0.0f && f6 <= this.G)) {
                f6 = this.G;
                this.I = false;
            }
            float f7 = f6;
            r70 r70Var = r70.e;
            if (f7 >= 1.0f || f7 <= 0.0f) {
                this.I = false;
                setState(r70Var);
            }
            int childCount = getChildCount();
            this.a0 = false;
            long nanoTime2 = getNanoTime();
            this.q0 = f7;
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = getChildAt(i2);
                j70 j70Var = (j70) this.A.get(childAt);
                if (j70Var != null) {
                    f3 = f5;
                    this.a0 = j70Var.c(f7, nanoTime2, this.r0, childAt) | this.a0;
                } else {
                    f3 = f5;
                }
                i2++;
                f5 = f3;
            }
            f2 = f5;
            boolean z5 = (fSignum > 0.0f && f7 >= this.G) || (fSignum <= f2 && f7 <= this.G);
            if (!this.a0 && !this.I && z5) {
                setState(r70Var);
            }
            if (this.j0) {
                requestLayout();
            }
            this.a0 = (!z5) | this.a0;
            if (f7 <= f2 && (i = this.u) != -1 && this.v != i) {
                this.v = i;
                this.r.b(i).a(this);
                setState(r70Var);
                z4 = true;
            }
            if (f7 >= 1.0d) {
                int i3 = this.v;
                int i4 = this.w;
                if (i3 != i4) {
                    this.v = i4;
                    this.r.b(i4).a(this);
                    setState(r70Var);
                    z4 = true;
                }
            }
            if (this.a0 || this.I) {
                invalidate();
            } else if ((fSignum > 0.0f && f7 == 1.0f) || (fSignum < f2 && f7 == f2)) {
                setState(r70Var);
            }
            if ((!this.a0 && this.I && fSignum > 0.0f && f7 == 1.0f) || (fSignum < f2 && f7 == f2)) {
                v();
            }
        } else {
            f2 = 0.0f;
        }
        float f8 = this.E;
        if (f8 < 1.0f) {
            if (f8 <= f2) {
                int i5 = this.v;
                int i6 = this.u;
                z3 = i5 == i6 ? z4 : true;
                this.v = i6;
            }
            this.w0 |= z4;
            if (z4 && !this.s0) {
                requestLayout();
            }
            this.D = this.E;
        }
        int i7 = this.v;
        int i8 = this.w;
        z3 = i7 == i8 ? z4 : true;
        this.v = i8;
        z4 = z3;
        this.w0 |= z4;
        if (z4) {
            requestLayout();
        }
        this.D = this.E;
    }

    public final void q() {
        ArrayList arrayList;
        if ((this.J == null && ((arrayList = this.d0) == null || arrayList.isEmpty())) || this.i0 == this.D) {
            return;
        }
        if (this.h0 != -1) {
            q70 q70Var = this.J;
            if (q70Var != null) {
                q70Var.getClass();
            }
            ArrayList arrayList2 = this.d0;
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((q70) it.next()).getClass();
                }
            }
        }
        this.h0 = -1;
        this.i0 = this.D;
        q70 q70Var2 = this.J;
        if (q70Var2 != null) {
            q70Var2.getClass();
        }
        ArrayList arrayList3 = this.d0;
        if (arrayList3 != null) {
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                ((q70) it2.next()).getClass();
            }
        }
    }

    public final void r() {
        ArrayList arrayList;
        if ((this.J != null || ((arrayList = this.d0) != null && !arrayList.isEmpty())) && this.h0 == -1) {
            this.h0 = this.v;
            ArrayList arrayList2 = this.z0;
            int iIntValue = !arrayList2.isEmpty() ? ((Integer) arrayList2.get(arrayList2.size() - 1)).intValue() : -1;
            int i = this.v;
            if (iIntValue != i && i != -1) {
                arrayList2.add(Integer.valueOf(i));
            }
        }
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public final void requestLayout() {
        w70 w70Var;
        v70 v70Var;
        if (this.j0 || this.v != -1 || (w70Var = this.r) == null || (v70Var = w70Var.c) == null || v70Var.q != 0) {
            super.requestLayout();
        }
    }

    public final void s(int i, float f, float f2, float f3, float[] fArr) {
        View viewD = d(i);
        j70 j70Var = (j70) this.A.get(viewD);
        if (j70Var != null) {
            j70Var.b(f, f2, f3, fArr);
            viewD.getY();
        } else {
            Log.w("MotionLayout", "WARNING could not find view id " + (viewD == null ? hk.g(i, ExtensionRequestData.EMPTY_VALUE) : viewD.getContext().getResources().getResourceName(i)));
        }
    }

    public void setDebugMode(int i) {
        this.K = i;
        invalidate();
    }

    public void setInteractionEnabled(boolean z) {
        this.z = z;
    }

    public void setInterpolatedProgress(float f) {
        if (this.r != null) {
            setState(r70.d);
            Interpolator interpolatorD = this.r.d();
            if (interpolatorD != null) {
                setProgress(interpolatorD.getInterpolation(f));
                return;
            }
        }
        setProgress(f);
    }

    public void setOnHide(float f) {
        ArrayList arrayList = this.c0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((MotionHelper) this.c0.get(i)).setProgress(f);
            }
        }
    }

    public void setOnShow(float f) {
        ArrayList arrayList = this.b0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((MotionHelper) this.b0.get(i)).setProgress(f);
            }
        }
    }

    public void setProgress(float f) {
        if (!super.isAttachedToWindow()) {
            if (this.t0 == null) {
                this.t0 = new p70(this);
            }
            this.t0.a = f;
            return;
        }
        r70 r70Var = r70.e;
        if (f <= 0.0f) {
            this.v = this.u;
            if (this.E == 0.0f) {
                setState(r70Var);
            }
        } else if (f >= 1.0f) {
            this.v = this.w;
            if (this.E == 1.0f) {
                setState(r70Var);
            }
        } else {
            this.v = -1;
            setState(r70.d);
        }
        if (this.r == null) {
            return;
        }
        this.H = true;
        this.G = f;
        this.D = f;
        this.F = -1L;
        this.B = -1L;
        this.s = null;
        this.I = true;
        invalidate();
    }

    public void setScene(w70 w70Var) {
        zo0 zo0Var;
        this.r = w70Var;
        boolean zK = k();
        w70Var.o = zK;
        v70 v70Var = w70Var.c;
        if (v70Var != null && (zo0Var = v70Var.l) != null) {
            zo0Var.b(zK);
        }
        x();
    }

    public void setState(r70 r70Var) {
        r70 r70Var2 = r70.e;
        if (r70Var == r70Var2 && this.v == -1) {
            return;
        }
        r70 r70Var3 = this.u0;
        this.u0 = r70Var;
        r70 r70Var4 = r70.d;
        if (r70Var3 == r70Var4 && r70Var == r70Var4) {
            q();
        }
        int iOrdinal = r70Var3.ordinal();
        if (iOrdinal != 0 && iOrdinal != 1) {
            if (iOrdinal == 2 && r70Var == r70Var2) {
                r();
                return;
            }
            return;
        }
        if (r70Var == r70Var4) {
            q();
        }
        if (r70Var == r70Var2) {
            r();
        }
    }

    public void setTransition(int i) {
        v70 v70Var;
        w70 w70Var = this.r;
        if (w70Var != null) {
            Iterator it = w70Var.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    v70Var = null;
                    break;
                } else {
                    v70Var = (v70) it.next();
                    if (v70Var.a == i) {
                        break;
                    }
                }
            }
            this.u = v70Var.d;
            this.w = v70Var.c;
            if (!super.isAttachedToWindow()) {
                if (this.t0 == null) {
                    this.t0 = new p70(this);
                }
                p70 p70Var = this.t0;
                p70Var.c = this.u;
                p70Var.d = this.w;
                return;
            }
            int i2 = this.v;
            float f = i2 == this.u ? 0.0f : i2 == this.w ? 1.0f : Float.NaN;
            w70 w70Var2 = this.r;
            w70Var2.c = v70Var;
            zo0 zo0Var = v70Var.l;
            if (zo0Var != null) {
                zo0Var.b(w70Var2.o);
            }
            this.v0.d(this.r.b(this.u), this.r.b(this.w));
            x();
            this.E = Float.isNaN(f) ? 0.0f : f;
            if (!Float.isNaN(f)) {
                setProgress(f);
                return;
            }
            Log.v("MotionLayout", oy.v() + " transitionToStart ");
            o(0.0f);
        }
    }

    public void setTransitionDuration(int i) {
        w70 w70Var = this.r;
        if (w70Var == null) {
            Log.e("MotionLayout", "MotionScene not defined");
            return;
        }
        v70 v70Var = w70Var.c;
        if (v70Var != null) {
            v70Var.h = i;
        } else {
            w70Var.j = i;
        }
    }

    public void setTransitionListener(q70 q70Var) {
        this.J = q70Var;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.t0 == null) {
            this.t0 = new p70(this);
        }
        p70 p70Var = this.t0;
        p70Var.getClass();
        p70Var.a = bundle.getFloat("motion.progress");
        p70Var.b = bundle.getFloat("motion.velocity");
        p70Var.c = bundle.getInt("motion.StartState");
        p70Var.d = bundle.getInt("motion.EndState");
        if (super.isAttachedToWindow()) {
            this.t0.a();
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
        RectF rectF = this.x0;
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
        return oy.w(context, this.u) + "->" + oy.w(context, this.w) + " (pos:" + this.E + " Dpos/Dt:" + this.t;
    }

    public final void u(AttributeSet attributeSet) {
        w70 w70Var;
        isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionLayout_layoutDescription) {
                    this.r = new w70(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R$styleable.MotionLayout_currentState) {
                    this.v = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R$styleable.MotionLayout_motionProgress) {
                    this.G = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.I = true;
                } else if (index == R$styleable.MotionLayout_applyMotionScene) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == R$styleable.MotionLayout_showPaths) {
                    if (this.K == 0) {
                        this.K = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R$styleable.MotionLayout_motionDebug) {
                    this.K = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.r == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.r = null;
            }
        }
        if (this.K != 0) {
            w70 w70Var2 = this.r;
            if (w70Var2 == null) {
                Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            } else {
                int iG = w70Var2.g();
                w70 w70Var3 = this.r;
                d dVarB = w70Var3.b(w70Var3.g());
                String strW = oy.w(getContext(), iG);
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    int id = childAt.getId();
                    if (id == -1) {
                        Log.w("MotionLayout", "CHECK: " + strW + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
                    }
                    HashMap map = dVarB.c;
                    if ((map.containsKey(Integer.valueOf(id)) ? (c) map.get(Integer.valueOf(id)) : null) == null) {
                        Log.w("MotionLayout", "CHECK: " + strW + " NO CONSTRAINTS for " + oy.x(childAt));
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
                    String strW2 = oy.w(getContext(), i5);
                    if (findViewById(iArr[i4]) == null) {
                        Log.w("MotionLayout", "CHECK: " + strW + " NO View matches id " + strW2);
                    }
                    if (dVarB.g(i5).d.d == -1) {
                        Log.w("MotionLayout", "CHECK: " + strW + "(" + strW2 + ") no LAYOUT_HEIGHT");
                    }
                    if (dVarB.g(i5).d.c == -1) {
                        Log.w("MotionLayout", "CHECK: " + strW + "(" + strW2 + ") no LAYOUT_HEIGHT");
                    }
                }
                SparseIntArray sparseIntArray = new SparseIntArray();
                SparseIntArray sparseIntArray2 = new SparseIntArray();
                for (v70 v70Var : this.r.d) {
                    if (v70Var == this.r.c) {
                        Log.v("MotionLayout", "CHECK: CURRENT");
                    }
                    StringBuilder sb = new StringBuilder("CHECK: transition = ");
                    Context context = getContext();
                    String resourceEntryName = v70Var.d == -1 ? "null" : context.getResources().getResourceEntryName(v70Var.d);
                    sb.append(v70Var.c == -1 ? hk.i(resourceEntryName, " -> null") : resourceEntryName + " -> " + context.getResources().getResourceEntryName(v70Var.c));
                    Log.v("MotionLayout", sb.toString());
                    Log.v("MotionLayout", "CHECK: transition.setDuration = " + v70Var.h);
                    if (v70Var.d == v70Var.c) {
                        Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
                    }
                    int i6 = v70Var.d;
                    int i7 = v70Var.c;
                    String strW3 = oy.w(getContext(), i6);
                    String strW4 = oy.w(getContext(), i7);
                    if (sparseIntArray.get(i6) == i7) {
                        Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strW3 + "->" + strW4);
                    }
                    if (sparseIntArray2.get(i7) == i6) {
                        Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strW3 + "->" + strW4);
                    }
                    sparseIntArray.put(i6, i7);
                    sparseIntArray2.put(i7, i6);
                    if (this.r.b(i6) == null) {
                        Log.e("MotionLayout", " no such constraintSetStart " + strW3);
                    }
                    if (this.r.b(i7) == null) {
                        Log.e("MotionLayout", " no such constraintSetEnd " + strW3);
                    }
                }
            }
        }
        if (this.v != -1 || (w70Var = this.r) == null) {
            return;
        }
        this.v = w70Var.g();
        this.u = this.r.g();
        v70 v70Var2 = this.r.c;
        this.w = v70Var2 != null ? v70Var2.c : -1;
    }

    public final void v() {
        v70 v70Var;
        zo0 zo0Var;
        View viewFindViewById;
        w70 w70Var = this.r;
        if (w70Var == null) {
            return;
        }
        if (w70Var.a(this, this.v)) {
            requestLayout();
            return;
        }
        int i = this.v;
        if (i != -1) {
            w70 w70Var2 = this.r;
            ArrayList<v70> arrayList = w70Var2.d;
            for (v70 v70Var2 : arrayList) {
                if (v70Var2.m.size() > 0) {
                    Iterator it = v70Var2.m.iterator();
                    while (it.hasNext()) {
                        ((u70) it.next()).b(this);
                    }
                }
            }
            ArrayList<v70> arrayList2 = w70Var2.f;
            for (v70 v70Var3 : arrayList2) {
                if (v70Var3.m.size() > 0) {
                    Iterator it2 = v70Var3.m.iterator();
                    while (it2.hasNext()) {
                        ((u70) it2.next()).b(this);
                    }
                }
            }
            for (v70 v70Var4 : arrayList) {
                if (v70Var4.m.size() > 0) {
                    Iterator it3 = v70Var4.m.iterator();
                    while (it3.hasNext()) {
                        ((u70) it3.next()).a(this, i, v70Var4);
                    }
                }
            }
            for (v70 v70Var5 : arrayList2) {
                if (v70Var5.m.size() > 0) {
                    Iterator it4 = v70Var5.m.iterator();
                    while (it4.hasNext()) {
                        ((u70) it4.next()).a(this, i, v70Var5);
                    }
                }
            }
        }
        if (!this.r.l() || (v70Var = this.r.c) == null || (zo0Var = v70Var.l) == null) {
            return;
        }
        int i2 = zo0Var.d;
        if (i2 != -1) {
            MotionLayout motionLayout = zo0Var.o;
            viewFindViewById = motionLayout.findViewById(i2);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + oy.w(motionLayout.getContext(), zo0Var.d));
            }
        } else {
            viewFindViewById = null;
        }
        if (viewFindViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) viewFindViewById;
            nestedScrollView.setOnTouchListener(new g8(1));
            nestedScrollView.setOnScrollChangeListener(new yo(25));
        }
    }

    public final void w() {
        ArrayList arrayList;
        if (this.J == null && ((arrayList = this.d0) == null || arrayList.isEmpty())) {
            return;
        }
        ArrayList<Integer> arrayList2 = this.z0;
        for (Integer num : arrayList2) {
            q70 q70Var = this.J;
            if (q70Var != null) {
                num.intValue();
                q70Var.getClass();
            }
            ArrayList<q70> arrayList3 = this.d0;
            if (arrayList3 != null) {
                for (q70 q70Var2 : arrayList3) {
                    num.intValue();
                    q70Var2.getClass();
                }
            }
        }
        arrayList2.clear();
    }

    public final void x() {
        this.v0.e();
        invalidate();
    }

    public final void y(float f, float f2) {
        if (super.isAttachedToWindow()) {
            setProgress(f);
            setState(r70.d);
            this.t = f2;
            o(1.0f);
            return;
        }
        if (this.t0 == null) {
            this.t0 = new p70(this);
        }
        p70 p70Var = this.t0;
        p70Var.a = f;
        p70Var.b = f2;
    }

    public final void z(int i, int i2) {
        if (!super.isAttachedToWindow()) {
            if (this.t0 == null) {
                this.t0 = new p70(this);
            }
            p70 p70Var = this.t0;
            p70Var.c = i;
            p70Var.d = i2;
            return;
        }
        w70 w70Var = this.r;
        if (w70Var != null) {
            this.u = i;
            this.w = i2;
            w70Var.k(i, i2);
            this.v0.d(this.r.b(i), this.r.b(i2));
            x();
            this.E = 0.0f;
            o(0.0f);
        }
    }

    public void setTransition(v70 v70Var) {
        zo0 zo0Var;
        w70 w70Var = this.r;
        w70Var.c = v70Var;
        if (v70Var != null && (zo0Var = v70Var.l) != null) {
            zo0Var.b(w70Var.o);
        }
        setState(r70.c);
        int i = this.v;
        v70 v70Var2 = this.r.c;
        if (i == (v70Var2 == null ? -1 : v70Var2.c)) {
            this.E = 1.0f;
            this.D = 1.0f;
            this.G = 1.0f;
        } else {
            this.E = 0.0f;
            this.D = 0.0f;
            this.G = 0.0f;
        }
        this.F = (v70Var.r & 1) != 0 ? -1L : getNanoTime();
        int iG = this.r.g();
        w70 w70Var2 = this.r;
        v70 v70Var3 = w70Var2.c;
        int i2 = v70Var3 != null ? v70Var3.c : -1;
        if (iG == this.u && i2 == this.w) {
            return;
        }
        this.u = iG;
        this.w = i2;
        w70Var2.k(iG, i2);
        d dVarB = this.r.b(this.u);
        d dVarB2 = this.r.b(this.w);
        n70 n70Var = this.v0;
        n70Var.d(dVarB, dVarB2);
        int i3 = this.u;
        int i4 = this.w;
        n70Var.e = i3;
        n70Var.f = i4;
        n70Var.e();
        x();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = 0.0f;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = 0;
        this.y = 0;
        this.z = true;
        this.A = new HashMap();
        this.B = 0L;
        this.C = 1.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.G = 0.0f;
        this.I = false;
        this.K = 0;
        this.M = false;
        this.N = new uk0();
        this.O = new l70(this);
        this.S = false;
        this.a0 = false;
        this.b0 = null;
        this.c0 = null;
        this.d0 = null;
        this.e0 = 0;
        this.f0 = -1L;
        this.g0 = 0.0f;
        this.h0 = 0;
        this.i0 = 0.0f;
        this.j0 = false;
        this.r0 = new o70(25);
        this.s0 = false;
        this.u0 = r70.b;
        this.v0 = new n70(this);
        this.w0 = false;
        this.x0 = new RectF();
        this.y0 = null;
        this.z0 = new ArrayList();
        u(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.t = 0.0f;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = 0;
        this.y = 0;
        this.z = true;
        this.A = new HashMap();
        this.B = 0L;
        this.C = 1.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.G = 0.0f;
        this.I = false;
        this.K = 0;
        this.M = false;
        this.N = new uk0();
        this.O = new l70(this);
        this.S = false;
        this.a0 = false;
        this.b0 = null;
        this.c0 = null;
        this.d0 = null;
        this.e0 = 0;
        this.f0 = -1L;
        this.g0 = 0.0f;
        this.h0 = 0;
        this.i0 = 0.0f;
        this.j0 = false;
        this.r0 = new o70(25);
        this.s0 = false;
        this.u0 = r70.b;
        this.v0 = new n70(this);
        this.w0 = false;
        this.x0 = new RectF();
        this.y0 = null;
        this.z0 = new ArrayList();
        u(attributeSet);
    }
}
