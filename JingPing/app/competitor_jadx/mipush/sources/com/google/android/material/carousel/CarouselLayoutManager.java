package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import androidx.recyclerview.widget.l;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import com.google.android.material.carousel.CarouselLayoutManager;
import defpackage.hc0;
import defpackage.i6;
import defpackage.kc;
import defpackage.lc;
import defpackage.mc;
import defpackage.nc;
import defpackage.oc;
import defpackage.pc;
import defpackage.s90;
import defpackage.sn0;
import defpackage.tf0;
import defpackage.un0;
import defpackage.v70;
import defpackage.w70;
import defpackage.x70;
import defpackage.y4;
import defpackage.zn;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CarouselLayoutManager extends k implements sn0 {
    public int A;
    public int B;
    public final int C;
    public int p;
    public int q;
    public int r;
    public final mc s;
    public final tf0 t;
    public x70 u;
    public w70 v;
    public int w;
    public HashMap x;
    public pc y;
    public final View.OnLayoutChangeListener z;

    public CarouselLayoutManager() {
        tf0 tf0Var = new tf0();
        this.s = new mc();
        this.w = 0;
        this.z = new View.OnLayoutChangeListener() { // from class: jc
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                CarouselLayoutManager carouselLayoutManager = this.a;
                if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
                    return;
                }
                view.post(new e(6, carouselLayoutManager));
            }
        };
        this.B = -1;
        this.C = 0;
        this.t = tf0Var;
        V0();
        X0(0);
    }

    public static i6 N0(List list, float f, boolean z) {
        float f2 = Float.MAX_VALUE;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        float f3 = -3.4028235E38f;
        float f4 = Float.MAX_VALUE;
        float f5 = Float.MAX_VALUE;
        for (int i5 = 0; i5 < list.size(); i5++) {
            v70 v70Var = (v70) list.get(i5);
            float f6 = z ? v70Var.b : v70Var.a;
            float fAbs = Math.abs(f6 - f);
            if (f6 <= f && fAbs <= f2) {
                i = i5;
                f2 = fAbs;
            }
            if (f6 > f && fAbs <= f4) {
                i3 = i5;
                f4 = fAbs;
            }
            if (f6 <= f5) {
                i2 = i5;
                f5 = f6;
            }
            if (f6 > f3) {
                i4 = i5;
                f3 = f6;
            }
        }
        if (i == -1) {
            i = i2;
        }
        if (i3 == -1) {
            i3 = i4;
        }
        return new i6((v70) list.get(i), (v70) list.get(i3));
    }

    public final void C0(View view, int i, lc lcVar) {
        float f = this.v.a / 2.0f;
        b(view, i, false);
        float f2 = lcVar.c;
        this.y.j(view, (int) (f2 - f), (int) (f2 + f));
        Y0(view, lcVar.b, lcVar.d);
    }

    public final float D0(float f, float f2) {
        return P0() ? f - f2 : f + f2;
    }

    public final void E0(int i, un0 un0Var, l lVar) {
        float fH0 = H0(i);
        while (i < un0Var.b()) {
            lc lcVarS0 = S0(lVar, fH0, i);
            float f = lcVarS0.c;
            i6 i6Var = lcVarS0.d;
            if (Q0(f, i6Var)) {
                return;
            }
            fH0 = D0(fH0, this.v.a);
            if (!R0(f, i6Var)) {
                C0(lcVarS0.a, -1, lcVarS0);
            }
            i++;
        }
    }

    public final void F0(l lVar, int i) {
        float fH0 = H0(i);
        while (i >= 0) {
            lc lcVarS0 = S0(lVar, fH0, i);
            i6 i6Var = lcVarS0.d;
            float f = lcVarS0.c;
            if (R0(f, i6Var)) {
                return;
            }
            float f2 = this.v.a;
            fH0 = P0() ? fH0 + f2 : fH0 - f2;
            if (!Q0(f, i6Var)) {
                C0(lcVarS0.a, 0, lcVarS0);
            }
            i--;
        }
    }

    public final float G0(View view, float f, i6 i6Var) {
        v70 v70Var = (v70) i6Var.F;
        float f2 = v70Var.b;
        v70 v70Var2 = (v70) i6Var.G;
        float f3 = v70Var2.b;
        float f4 = v70Var.a;
        float f5 = v70Var2.a;
        float fB = y4.b(f2, f3, f4, f5, f);
        if (v70Var2 != this.v.b() && v70Var != this.v.d()) {
            return fB;
        }
        return (((1.0f - v70Var2.c) + (this.y.b((RecyclerView.LayoutParams) view.getLayoutParams()) / this.v.a)) * (f - f5)) + fB;
    }

    public final float H0(int i) {
        return D0(this.y.h() - this.p, this.v.a * i);
    }

    public final void I0(l lVar, un0 un0Var) {
        while (v() > 0) {
            View viewU = u(0);
            Rect rect = new Rect();
            super.y(rect, viewU);
            float fCenterX = O0() ? rect.centerX() : rect.centerY();
            if (!R0(fCenterX, N0(this.v.b, fCenterX, true))) {
                break;
            } else {
                k0(viewU, lVar);
            }
        }
        while (v() - 1 >= 0) {
            View viewU2 = u(v() - 1);
            Rect rect2 = new Rect();
            super.y(rect2, viewU2);
            float fCenterX2 = O0() ? rect2.centerX() : rect2.centerY();
            if (!Q0(fCenterX2, N0(this.v.b, fCenterX2, true))) {
                break;
            } else {
                k0(viewU2, lVar);
            }
        }
        if (v() == 0) {
            F0(lVar, this.w - 1);
            E0(this.w, un0Var, lVar);
        } else {
            int iH = k.H(u(0));
            int iH2 = k.H(u(v() - 1));
            F0(lVar, iH - 1);
            E0(iH2 + 1, un0Var, lVar);
        }
    }

    public final int J0() {
        return O0() ? this.n : this.o;
    }

    public final w70 K0(int i) {
        w70 w70Var;
        HashMap map = this.x;
        return (map == null || (w70Var = (w70) map.get(Integer.valueOf(s90.m(i, 0, Math.max(0, B() + (-1)))))) == null) ? this.u.a : w70Var;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean L() {
        return true;
    }

    public final int L0(int i, w70 w70Var) {
        if (!P0()) {
            return (int) ((w70Var.a / 2.0f) + ((i * w70Var.a) - w70Var.a().a));
        }
        float fJ0 = J0() - w70Var.c().a;
        float f = w70Var.a;
        return (int) ((fJ0 - (i * f)) - (f / 2.0f));
    }

    public final int M0(int i, w70 w70Var) {
        int i2 = Integer.MAX_VALUE;
        for (v70 v70Var : w70Var.b.subList(w70Var.c, w70Var.d + 1)) {
            float f = w70Var.a;
            float f2 = (f / 2.0f) + (i * f);
            int iJ0 = (P0() ? (int) ((J0() - v70Var.a) - f2) : (int) (f2 - v70Var.a)) - this.p;
            if (Math.abs(i2) > Math.abs(iJ0)) {
                i2 = iJ0;
            }
        }
        return i2;
    }

    public final boolean O0() {
        return this.y.a == 0;
    }

    public final boolean P0() {
        return O0() && C() == 1;
    }

    @Override // androidx.recyclerview.widget.k
    public final void Q(RecyclerView recyclerView) {
        tf0 tf0Var = this.t;
        Context context = recyclerView.getContext();
        float dimension = tf0Var.a;
        if (dimension <= 0.0f) {
            dimension = context.getResources().getDimension(R$dimen.m3_carousel_small_item_size_min);
        }
        tf0Var.a = dimension;
        float dimension2 = tf0Var.b;
        if (dimension2 <= 0.0f) {
            dimension2 = context.getResources().getDimension(R$dimen.m3_carousel_small_item_size_max);
        }
        tf0Var.b = dimension2;
        V0();
        recyclerView.addOnLayoutChangeListener(this.z);
    }

    public final boolean Q0(float f, i6 i6Var) {
        v70 v70Var = (v70) i6Var.F;
        float f2 = v70Var.d;
        v70 v70Var2 = (v70) i6Var.G;
        float fB = y4.b(f2, v70Var2.d, v70Var.b, v70Var2.b, f) / 2.0f;
        float f3 = P0() ? f + fB : f - fB;
        return P0() ? f3 < 0.0f : f3 > ((float) J0());
    }

    @Override // androidx.recyclerview.widget.k
    public final void R(RecyclerView recyclerView) {
        recyclerView.removeOnLayoutChangeListener(this.z);
    }

    public final boolean R0(float f, i6 i6Var) {
        v70 v70Var = (v70) i6Var.F;
        float f2 = v70Var.d;
        v70 v70Var2 = (v70) i6Var.G;
        float fD0 = D0(f, y4.b(f2, v70Var2.d, v70Var.b, v70Var2.b, f) / 2.0f);
        return P0() ? fD0 > ((float) J0()) : fD0 < 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    @Override // androidx.recyclerview.widget.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View S(android.view.View r5, int r6, androidx.recyclerview.widget.l r7, defpackage.un0 r8) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.S(android.view.View, int, androidx.recyclerview.widget.l, un0):android.view.View");
    }

    public final lc S0(l lVar, float f, int i) {
        View view = lVar.i(i, Long.MAX_VALUE).a;
        T0(view);
        float fD0 = D0(f, this.v.a / 2.0f);
        i6 i6VarN0 = N0(this.v.b, fD0, false);
        return new lc(view, fD0, G0(view, fD0, i6VarN0), i6VarN0);
    }

    @Override // androidx.recyclerview.widget.k
    public final void T(AccessibilityEvent accessibilityEvent) {
        super.T(accessibilityEvent);
        if (v() > 0) {
            accessibilityEvent.setFromIndex(k.H(u(0)));
            accessibilityEvent.setToIndex(k.H(u(v() - 1)));
        }
    }

    public final void T0(View view) {
        if (!(view instanceof hc0)) {
            throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        Rect rect = new Rect();
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.J(view));
        }
        int i = rect.left + rect.right;
        int i2 = rect.top + rect.bottom;
        x70 x70Var = this.u;
        view.measure(k.w(O0(), this.n, this.l, F() + E() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i, (int) ((x70Var == null || this.y.a != 0) ? ((ViewGroup.MarginLayoutParams) layoutParams).width : x70Var.a.a)), k.w(e(), this.o, this.m, D() + G() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i2, (int) ((x70Var == null || this.y.a != 1) ? ((ViewGroup.MarginLayoutParams) layoutParams).height : x70Var.a.a)));
    }

    /* JADX WARN: Removed duplicated region for block: B:142:0x046f A[PHI: r25
      0x046f: PHI (r25v1 float) = (r25v3 float), (r25v4 float) binds: [B:141:0x046d, B:137:0x0465] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0585  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x059b  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x056e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void U0(androidx.recyclerview.widget.l r29) {
        /*
            Method dump skipped, instruction units count: 1678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.U0(androidx.recyclerview.widget.l):void");
    }

    public final void V0() {
        this.u = null;
        n0();
    }

    public final int W0(int i, un0 un0Var, l lVar) {
        if (v() == 0 || i == 0) {
            return 0;
        }
        if (this.u == null) {
            U0(lVar);
        }
        int i2 = this.p;
        int i3 = this.q;
        int i4 = this.r;
        int i5 = i2 + i;
        if (i5 < i3) {
            i = i3 - i2;
        } else if (i5 > i4) {
            i = i4 - i2;
        }
        this.p = i2 + i;
        Z0(this.u);
        float f = this.v.a / 2.0f;
        float fH0 = H0(k.H(u(0)));
        Rect rect = new Rect();
        float f2 = P0() ? this.v.c().b : this.v.a().b;
        float f3 = Float.MAX_VALUE;
        for (int i6 = 0; i6 < v(); i6++) {
            View viewU = u(i6);
            float fD0 = D0(fH0, f);
            i6 i6VarN0 = N0(this.v.b, fD0, false);
            float fG0 = G0(viewU, fD0, i6VarN0);
            super.y(rect, viewU);
            Y0(viewU, fD0, i6VarN0);
            this.y.l(viewU, rect, f, fG0);
            float fAbs = Math.abs(f2 - fG0);
            if (fAbs < f3) {
                this.B = k.H(viewU);
                f3 = fAbs;
            }
            fH0 = D0(fH0, this.v.a);
        }
        I0(lVar, un0Var);
        return i;
    }

    @Override // androidx.recyclerview.widget.k
    public final void X(int i, int i2) {
        a1();
    }

    public final void X0(int i) {
        pc ocVar;
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(zn.j(i, "invalid orientation:"));
        }
        c(null);
        pc pcVar = this.y;
        if (pcVar == null || i != pcVar.a) {
            if (i == 0) {
                ocVar = new oc(this);
            } else {
                if (i != 1) {
                    throw new IllegalArgumentException("invalid orientation");
                }
                ocVar = new nc(this);
            }
            this.y = ocVar;
            V0();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y0(View view, float f, i6 i6Var) {
        if (view instanceof hc0) {
            v70 v70Var = (v70) i6Var.F;
            float f2 = v70Var.c;
            v70 v70Var2 = (v70) i6Var.G;
            float fB = y4.b(f2, v70Var2.c, v70Var.a, v70Var2.a, f);
            float height = view.getHeight();
            float width = view.getWidth();
            RectF rectFC = this.y.c(height, width, y4.b(0.0f, height / 2.0f, 0.0f, 1.0f, fB), y4.b(0.0f, width / 2.0f, 0.0f, 1.0f, fB));
            float fG0 = G0(view, f, i6Var);
            RectF rectF = new RectF(fG0 - (rectFC.width() / 2.0f), fG0 - (rectFC.height() / 2.0f), (rectFC.width() / 2.0f) + fG0, (rectFC.height() / 2.0f) + fG0);
            RectF rectF2 = new RectF(this.y.f(), this.y.i(), this.y.g(), this.y.d());
            this.t.getClass();
            this.y.a(rectFC, rectF, rectF2);
            this.y.k(rectFC, rectF, rectF2);
            ((hc0) view).setMaskRectF(rectFC);
        }
    }

    public final void Z0(x70 x70Var) {
        int i = this.r;
        int i2 = this.q;
        if (i <= i2) {
            this.v = P0() ? x70Var.a() : x70Var.c();
        } else {
            this.v = x70Var.b(this.p, i2, i);
        }
        List list = this.v.b;
        mc mcVar = this.s;
        mcVar.getClass();
        mcVar.b = Collections.unmodifiableList(list);
    }

    @Override // defpackage.sn0
    public final PointF a(int i) {
        if (this.u == null) {
            return null;
        }
        int iL0 = L0(i, K0(i)) - this.p;
        return O0() ? new PointF(iL0, 0.0f) : new PointF(0.0f, iL0);
    }

    @Override // androidx.recyclerview.widget.k
    public final void a0(int i, int i2) {
        a1();
    }

    public final void a1() {
        int iB = B();
        int i = this.A;
        if (iB == i || this.u == null) {
            return;
        }
        tf0 tf0Var = this.t;
        if ((i < tf0Var.c && B() >= tf0Var.c) || (i >= tf0Var.c && B() < tf0Var.c)) {
            V0();
        }
        this.A = iB;
    }

    @Override // androidx.recyclerview.widget.k
    public final void c0(l lVar, un0 un0Var) {
        float f;
        if (un0Var.b() <= 0 || J0() <= 0.0f) {
            i0(lVar);
            this.w = 0;
            return;
        }
        boolean zP0 = P0();
        boolean z = this.u == null;
        if (z) {
            U0(lVar);
        }
        x70 x70Var = this.u;
        boolean zP02 = P0();
        w70 w70VarA = zP02 ? x70Var.a() : x70Var.c();
        float f2 = (zP02 ? w70VarA.c() : w70VarA.a()).a;
        float f3 = w70VarA.a / 2.0f;
        int iH = (int) (this.y.h() - (P0() ? f2 + f3 : f2 - f3));
        x70 x70Var2 = this.u;
        boolean zP03 = P0();
        w70 w70VarC = zP03 ? x70Var2.c() : x70Var2.a();
        v70 v70VarA = zP03 ? w70VarC.a() : w70VarC.c();
        int iB = (int) (((((un0Var.b() - 1) * w70VarC.a) * (zP03 ? -1.0f : 1.0f)) - (v70VarA.a - this.y.h())) + (this.y.e() - v70VarA.a) + (zP03 ? -v70VarA.g : v70VarA.h));
        int iMin = zP03 ? Math.min(0, iB) : Math.max(0, iB);
        this.q = zP0 ? iMin : iH;
        if (zP0) {
            iMin = iH;
        }
        this.r = iMin;
        if (z) {
            this.p = iH;
            x70 x70Var3 = this.u;
            int iB2 = B();
            int i = this.q;
            int i2 = this.r;
            boolean zP04 = P0();
            w70 w70Var = x70Var3.a;
            HashMap map = new HashMap();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                f = w70Var.a;
                if (i3 >= iB2) {
                    break;
                }
                int i5 = zP04 ? (iB2 - i3) - 1 : i3;
                float f4 = i5 * f * (zP04 ? -1 : 1);
                float f5 = i2 - x70Var3.g;
                List list = x70Var3.c;
                if (f4 > f5 || i3 >= iB2 - list.size()) {
                    map.put(Integer.valueOf(i5), (w70) list.get(s90.m(i4, 0, list.size() - 1)));
                    i4++;
                }
                i3++;
            }
            int i6 = 0;
            for (int i7 = iB2 - 1; i7 >= 0; i7--) {
                int i8 = zP04 ? (iB2 - i7) - 1 : i7;
                float f6 = i8 * f * (zP04 ? -1 : 1);
                float f7 = i + x70Var3.f;
                List list2 = x70Var3.b;
                if (f6 < f7 || i7 < list2.size()) {
                    map.put(Integer.valueOf(i8), (w70) list2.get(s90.m(i6, 0, list2.size() - 1)));
                    i6++;
                }
            }
            this.x = map;
            int i9 = this.B;
            if (i9 != -1) {
                this.p = L0(i9, K0(i9));
            }
        }
        int i10 = this.p;
        int i11 = this.q;
        int i12 = this.r;
        this.p = (i10 < i11 ? i11 - i10 : i10 > i12 ? i12 - i10 : 0) + i10;
        this.w = s90.m(this.w, 0, un0Var.b());
        Z0(this.u);
        p(lVar);
        I0(lVar, un0Var);
        this.A = B();
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean d() {
        return O0();
    }

    @Override // androidx.recyclerview.widget.k
    public final void d0(un0 un0Var) {
        if (v() == 0) {
            this.w = 0;
        } else {
            this.w = k.H(u(0));
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean e() {
        return !O0();
    }

    @Override // androidx.recyclerview.widget.k
    public final int j(un0 un0Var) {
        if (v() == 0 || this.u == null || B() <= 1) {
            return 0;
        }
        return (int) (this.n * (this.u.a.a / l(un0Var)));
    }

    @Override // androidx.recyclerview.widget.k
    public final int k(un0 un0Var) {
        return this.p;
    }

    @Override // androidx.recyclerview.widget.k
    public final int l(un0 un0Var) {
        return this.r - this.q;
    }

    @Override // androidx.recyclerview.widget.k
    public final int m(un0 un0Var) {
        if (v() == 0 || this.u == null || B() <= 1) {
            return 0;
        }
        return (int) (this.o * (this.u.a.a / o(un0Var)));
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean m0(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        int iM0;
        if (this.u == null || (iM0 = M0(k.H(view), K0(k.H(view)))) == 0) {
            return false;
        }
        int i = this.p;
        int i2 = this.q;
        int i3 = this.r;
        int i4 = i + iM0;
        if (i4 < i2) {
            iM0 = i2 - i;
        } else if (i4 > i3) {
            iM0 = i3 - i;
        }
        int iM02 = M0(k.H(view), this.u.b(i + iM0, i2, i3));
        if (O0()) {
            recyclerView.scrollBy(iM02, 0);
            return true;
        }
        recyclerView.scrollBy(0, iM02);
        return true;
    }

    @Override // androidx.recyclerview.widget.k
    public final int n(un0 un0Var) {
        return this.p;
    }

    @Override // androidx.recyclerview.widget.k
    public final int o(un0 un0Var) {
        return this.r - this.q;
    }

    @Override // androidx.recyclerview.widget.k
    public final int o0(int i, un0 un0Var, l lVar) {
        if (O0()) {
            return W0(i, un0Var, lVar);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.k
    public final void p0(int i) {
        this.B = i;
        if (this.u == null) {
            return;
        }
        this.p = L0(i, K0(i));
        this.w = s90.m(i, 0, Math.max(0, B() - 1));
        Z0(this.u);
        n0();
    }

    @Override // androidx.recyclerview.widget.k
    public final int q0(int i, un0 un0Var, l lVar) {
        if (e()) {
            return W0(i, un0Var, lVar);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams r() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.k
    public final void y(Rect rect, View view) {
        super.y(rect, view);
        float fCenterY = rect.centerY();
        if (O0()) {
            fCenterY = rect.centerX();
        }
        i6 i6VarN0 = N0(this.v.b, fCenterY, true);
        v70 v70Var = (v70) i6VarN0.F;
        float f = v70Var.d;
        v70 v70Var2 = (v70) i6VarN0.G;
        float fB = y4.b(f, v70Var2.d, v70Var.b, v70Var2.b, fCenterY);
        float fWidth = O0() ? (rect.width() - fB) / 2.0f : 0.0f;
        float fHeight = O0() ? 0.0f : (rect.height() - fB) / 2.0f;
        rect.set((int) (rect.left + fWidth), (int) (rect.top + fHeight), (int) (rect.right - fWidth), (int) (rect.bottom - fHeight));
    }

    @Override // androidx.recyclerview.widget.k
    public final void z0(RecyclerView recyclerView, int i) {
        kc kcVar = new kc(this, recyclerView.getContext(), 0);
        kcVar.a = i;
        A0(kcVar);
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.s = new mc();
        this.w = 0;
        this.z = new View.OnLayoutChangeListener() { // from class: jc
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i22, int i32, int i4, int i5, int i6, int i7, int i8) {
                CarouselLayoutManager carouselLayoutManager = this.a;
                if (i3 == i5 && i22 == i6 && i32 == i7 && i4 == i8) {
                    return;
                }
                view.post(new e(6, carouselLayoutManager));
            }
        };
        this.B = -1;
        this.C = 0;
        this.t = new tf0();
        V0();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Carousel);
            this.C = typedArrayObtainStyledAttributes.getInt(R$styleable.Carousel_carousel_alignment, 0);
            V0();
            X0(typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
