package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
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
import defpackage.d10;
import defpackage.e10;
import defpackage.e5;
import defpackage.f10;
import defpackage.hk;
import defpackage.qa;
import defpackage.r40;
import defpackage.ra;
import defpackage.rp0;
import defpackage.sa;
import defpackage.t3;
import defpackage.ta;
import defpackage.ua;
import defpackage.va;
import defpackage.wd0;
import defpackage.yd0;
import defpackage.z70;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CarouselLayoutManager extends k implements wd0 {
    public int p;
    public int q;
    public int r;
    public final sa s;
    public final z70 t;
    public f10 u;
    public e10 v;
    public int w;
    public HashMap x;
    public va y;

    public CarouselLayoutManager() {
        z70 z70Var = new z70();
        this.s = new sa();
        this.w = 0;
        this.t = z70Var;
        this.u = null;
        k0();
        Q0(0);
    }

    public static e5 I0(List list, float f, boolean z) {
        float f2 = Float.MAX_VALUE;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        float f3 = -3.4028235E38f;
        float f4 = Float.MAX_VALUE;
        float f5 = Float.MAX_VALUE;
        for (int i5 = 0; i5 < list.size(); i5++) {
            d10 d10Var = (d10) list.get(i5);
            float f6 = z ? d10Var.b : d10Var.a;
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
        return new e5((d10) list.get(i), (d10) list.get(i3));
    }

    public final int A0(int i, int i2) {
        return K0() ? i - i2 : i + i2;
    }

    public final void B0(int i, yd0 yd0Var, l lVar) {
        int iE0 = E0(i);
        while (i < yd0Var.b()) {
            ra raVarN0 = N0(lVar, iE0, i);
            float f = raVarN0.c;
            e5 e5Var = raVarN0.d;
            if (L0(f, e5Var)) {
                return;
            }
            iE0 = A0(iE0, (int) this.v.a);
            if (!M0(f, e5Var)) {
                z0(raVarN0.a, -1, raVarN0);
            }
            i++;
        }
    }

    public final void C0(l lVar, int i) {
        int iE0 = E0(i);
        while (i >= 0) {
            ra raVarN0 = N0(lVar, iE0, i);
            e5 e5Var = raVarN0.d;
            float f = raVarN0.c;
            if (M0(f, e5Var)) {
                return;
            }
            int i2 = (int) this.v.a;
            iE0 = K0() ? iE0 + i2 : iE0 - i2;
            if (!L0(f, e5Var)) {
                z0(raVarN0.a, 0, raVarN0);
            }
            i--;
        }
    }

    public final float D0(View view, float f, e5 e5Var) {
        d10 d10Var = (d10) e5Var.c;
        float f2 = d10Var.b;
        d10 d10Var2 = (d10) e5Var.d;
        float f3 = d10Var2.b;
        float f4 = d10Var.a;
        float f5 = d10Var2.a;
        float fB = t3.b(f2, f3, f4, f5, f);
        if (d10Var2 != this.v.b() && d10Var != this.v.d()) {
            return fB;
        }
        return (((1.0f - d10Var2.c) + (this.y.b((RecyclerView.LayoutParams) view.getLayoutParams()) / this.v.a)) * (f - f5)) + fB;
    }

    public final int E0(int i) {
        return A0(this.y.h() - this.p, (int) (this.v.a * i));
    }

    public final void F0(l lVar, yd0 yd0Var) {
        while (v() > 0) {
            View viewU = u(0);
            Rect rect = new Rect();
            super.y(rect, viewU);
            float fCenterX = rect.centerX();
            if (!M0(fCenterX, I0(this.v.b, fCenterX, true))) {
                break;
            } else {
                h0(viewU, lVar);
            }
        }
        while (v() - 1 >= 0) {
            View viewU2 = u(v() - 1);
            Rect rect2 = new Rect();
            super.y(rect2, viewU2);
            float fCenterX2 = rect2.centerX();
            if (!L0(fCenterX2, I0(this.v.b, fCenterX2, true))) {
                break;
            } else {
                h0(viewU2, lVar);
            }
        }
        if (v() == 0) {
            C0(lVar, this.w - 1);
            B0(this.w, yd0Var, lVar);
        } else {
            int iF = k.F(u(0));
            int iF2 = k.F(u(v() - 1));
            C0(lVar, iF - 1);
            B0(iF2 + 1, yd0Var, lVar);
        }
    }

    public final e10 G0(int i) {
        e10 e10Var;
        HashMap map = this.x;
        return (map == null || (e10Var = (e10) map.get(Integer.valueOf(rp0.n(i, 0, Math.max(0, z() + (-1)))))) == null) ? this.u.a : e10Var;
    }

    public final int H0(int i, e10 e10Var) {
        if (!K0()) {
            return (int) ((e10Var.a / 2.0f) + ((i * e10Var.a) - e10Var.a().a));
        }
        float f = (J0() ? this.n : this.o) - e10Var.c().a;
        float f2 = e10Var.a;
        return (int) ((f - (i * f2)) - (f2 / 2.0f));
    }

    public final boolean J0() {
        return this.y.a == 0;
    }

    public final boolean K0() {
        return J0() && A() == 1;
    }

    public final boolean L0(float f, e5 e5Var) {
        d10 d10Var = (d10) e5Var.c;
        float f2 = d10Var.d;
        d10 d10Var2 = (d10) e5Var.d;
        float fB = t3.b(f2, d10Var2.d, d10Var.b, d10Var2.b, f);
        int i = (int) f;
        int i2 = (int) (fB / 2.0f);
        int i3 = K0() ? i + i2 : i - i2;
        if (K0()) {
            return i3 < 0;
        }
        return i3 > (J0() ? this.n : this.o);
    }

    public final boolean M0(float f, e5 e5Var) {
        d10 d10Var = (d10) e5Var.c;
        float f2 = d10Var.d;
        d10 d10Var2 = (d10) e5Var.d;
        int iA0 = A0((int) f, (int) (t3.b(f2, d10Var2.d, d10Var.b, d10Var2.b, f) / 2.0f));
        if (K0()) {
            return iA0 > (J0() ? this.n : this.o);
        }
        return iA0 < 0;
    }

    public final ra N0(l lVar, float f, int i) {
        float f2 = this.v.a / 2.0f;
        View view = lVar.i(Long.MAX_VALUE, i).a;
        O0(view);
        float fA0 = A0((int) f, (int) f2);
        e5 e5VarI0 = I0(this.v.b, fA0, false);
        return new ra(view, fA0, D0(view, fA0, e5VarI0), e5VarI0);
    }

    public final void O0(View view) {
        if (!(view instanceof r40)) {
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
        f10 f10Var = this.u;
        view.measure(k.w(J0(), this.n, this.l, D() + C() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i, (int) ((f10Var == null || this.y.a != 0) ? ((ViewGroup.MarginLayoutParams) layoutParams).width : f10Var.a.a)), k.w(e(), this.o, this.m, B() + E() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i2, (int) ((f10Var == null || this.y.a != 1) ? ((ViewGroup.MarginLayoutParams) layoutParams).height : f10Var.a.a)));
    }

    public final int P0(int i, yd0 yd0Var, l lVar) {
        if (v() == 0 || i == 0) {
            return 0;
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
        S0();
        float f = this.v.a / 2.0f;
        int iE0 = E0(k.F(u(0)));
        Rect rect = new Rect();
        for (int i6 = 0; i6 < v(); i6++) {
            View viewU = u(i6);
            float fA0 = A0(iE0, (int) f);
            e5 e5VarI0 = I0(this.v.b, fA0, false);
            float fD0 = D0(viewU, fA0, e5VarI0);
            super.y(rect, viewU);
            R0(viewU, fA0, e5VarI0);
            this.y.l(viewU, rect, f, fD0);
            iE0 = A0(iE0, (int) this.v.a);
        }
        F0(lVar, yd0Var);
        return i;
    }

    @Override // androidx.recyclerview.widget.k
    public final void Q(AccessibilityEvent accessibilityEvent) {
        super.Q(accessibilityEvent);
        if (v() > 0) {
            accessibilityEvent.setFromIndex(k.F(u(0)));
            accessibilityEvent.setToIndex(k.F(u(v() - 1)));
        }
    }

    public final void Q0(int i) {
        va uaVar;
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(hk.g(i, "invalid orientation:"));
        }
        c(null);
        va vaVar = this.y;
        if (vaVar == null || i != vaVar.a) {
            if (i == 0) {
                uaVar = new ua(this);
            } else {
                if (i != 1) {
                    throw new IllegalArgumentException("invalid orientation");
                }
                uaVar = new ta(this);
            }
            this.y = uaVar;
            this.u = null;
            k0();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void R0(View view, float f, e5 e5Var) {
        if (view instanceof r40) {
            d10 d10Var = (d10) e5Var.c;
            float f2 = d10Var.c;
            d10 d10Var2 = (d10) e5Var.d;
            float fB = t3.b(f2, d10Var2.c, d10Var.a, d10Var2.a, f);
            float height = view.getHeight();
            float width = view.getWidth();
            RectF rectFC = this.y.c(height, width, t3.b(0.0f, height / 2.0f, 0.0f, 1.0f, fB), t3.b(0.0f, width / 2.0f, 0.0f, 1.0f, fB));
            float fD0 = D0(view, f, e5Var);
            RectF rectF = new RectF(fD0 - (rectFC.width() / 2.0f), fD0 - (rectFC.height() / 2.0f), (rectFC.width() / 2.0f) + fD0, (rectFC.height() / 2.0f) + fD0);
            RectF rectF2 = new RectF(this.y.f(), this.y.i(), this.y.g(), this.y.d());
            this.t.getClass();
            this.y.a(rectFC, rectF, rectF2);
            this.y.k(rectFC, rectF, rectF2);
            ((r40) view).setMaskRectF(rectFC);
        }
    }

    public final void S0() {
        e10 e10Var;
        float fB;
        List list;
        float[] fArr;
        float[] fArr2;
        e10 e10Var2;
        int i = this.r;
        int i2 = this.q;
        if (i <= i2) {
            if (K0()) {
                List list2 = this.u.c;
                e10Var2 = (e10) list2.get(list2.size() - 1);
            } else {
                List list3 = this.u.b;
                e10Var2 = (e10) list3.get(list3.size() - 1);
            }
            this.v = e10Var2;
        } else {
            f10 f10Var = this.u;
            float f = this.p;
            float f2 = i2;
            float f3 = i;
            float f4 = f10Var.f + f2;
            float f5 = f3 - f10Var.g;
            if (f < f4) {
                fB = t3.b(1.0f, 0.0f, f2, f4, f);
                list = f10Var.b;
                fArr = f10Var.d;
            } else if (f > f5) {
                fB = t3.b(0.0f, 1.0f, f5, f3, f);
                list = f10Var.c;
                fArr = f10Var.e;
            } else {
                e10Var = f10Var.a;
                this.v = e10Var;
            }
            int size = list.size();
            float f6 = fArr[0];
            int i3 = 1;
            while (true) {
                if (i3 >= size) {
                    fArr2 = new float[]{0.0f, 0.0f, 0.0f};
                    break;
                }
                float f7 = fArr[i3];
                if (fB <= f7) {
                    fArr2 = new float[]{t3.b(0.0f, 1.0f, f6, f7, fB), i3 - 1, i3};
                    break;
                } else {
                    i3++;
                    f6 = f7;
                }
            }
            e10 e10Var3 = (e10) list.get((int) fArr2[1]);
            e10 e10Var4 = (e10) list.get((int) fArr2[2]);
            float f8 = fArr2[0];
            if (e10Var3.a != e10Var4.a) {
                throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
            }
            List list4 = e10Var3.b;
            int size2 = list4.size();
            List list5 = e10Var4.b;
            if (size2 != list5.size()) {
                throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
            }
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < list4.size(); i4++) {
                d10 d10Var = (d10) list4.get(i4);
                d10 d10Var2 = (d10) list5.get(i4);
                arrayList.add(new d10(t3.a(d10Var.a, d10Var2.a, f8), t3.a(d10Var.b, d10Var2.b, f8), t3.a(d10Var.c, d10Var2.c, f8), t3.a(d10Var.d, d10Var2.d, f8)));
            }
            e10Var = new e10(e10Var3.a, arrayList, t3.c(e10Var3.c, f8, e10Var4.c), t3.c(e10Var3.d, f8, e10Var4.d));
            this.v = e10Var;
        }
        List list6 = this.v.b;
        sa saVar = this.s;
        saVar.getClass();
        saVar.b = Collections.unmodifiableList(list6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:506)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:509)
        */
    @Override // androidx.recyclerview.widget.k
    public final void Z(androidx.recyclerview.widget.l r34, defpackage.yd0 r35) {
        /*
            Method dump skipped, instruction units count: 1519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.Z(androidx.recyclerview.widget.l, yd0):void");
    }

    @Override // defpackage.wd0
    public final PointF a(int i) {
        if (this.u == null) {
            return null;
        }
        int iH0 = H0(i, G0(i)) - this.p;
        return J0() ? new PointF(iH0, 0.0f) : new PointF(0.0f, iH0);
    }

    @Override // androidx.recyclerview.widget.k
    public final void a0(yd0 yd0Var) {
        if (v() == 0) {
            this.w = 0;
        } else {
            this.w = k.F(u(0));
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean d() {
        return J0();
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean e() {
        return !J0();
    }

    @Override // androidx.recyclerview.widget.k
    public final int j(yd0 yd0Var) {
        return (int) this.u.a.a;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean j0(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        if (this.u != null) {
            int iH0 = H0(k.F(view), G0(k.F(view))) - this.p;
            if (!z2 && iH0 != 0) {
                recyclerView.scrollBy(iH0, 0);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.k
    public final int k(yd0 yd0Var) {
        return this.p;
    }

    @Override // androidx.recyclerview.widget.k
    public final int l(yd0 yd0Var) {
        return this.r - this.q;
    }

    @Override // androidx.recyclerview.widget.k
    public final int l0(int i, yd0 yd0Var, l lVar) {
        if (J0()) {
            return P0(i, yd0Var, lVar);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.k
    public final int m(yd0 yd0Var) {
        return (int) this.u.a.a;
    }

    @Override // androidx.recyclerview.widget.k
    public final void m0(int i) {
        if (this.u == null) {
            return;
        }
        this.p = H0(i, G0(i));
        this.w = rp0.n(i, 0, Math.max(0, z() - 1));
        S0();
        k0();
    }

    @Override // androidx.recyclerview.widget.k
    public final int n(yd0 yd0Var) {
        return this.p;
    }

    @Override // androidx.recyclerview.widget.k
    public final int n0(int i, yd0 yd0Var, l lVar) {
        if (e()) {
            return P0(i, yd0Var, lVar);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.k
    public final int o(yd0 yd0Var) {
        return this.r - this.q;
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams r() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.k
    public final void w0(RecyclerView recyclerView, int i) {
        qa qaVar = new qa(this, recyclerView.getContext(), 0);
        qaVar.a = i;
        x0(qaVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final void y(Rect rect, View view) {
        super.y(rect, view);
        float fCenterX = rect.centerX();
        e5 e5VarI0 = I0(this.v.b, fCenterX, true);
        d10 d10Var = (d10) e5VarI0.c;
        float f = d10Var.d;
        d10 d10Var2 = (d10) e5VarI0.d;
        float fWidth = (rect.width() - t3.b(f, d10Var2.d, d10Var.b, d10Var2.b, fCenterX)) / 2.0f;
        rect.set((int) (rect.left + fWidth), rect.top, (int) (rect.right - fWidth), rect.bottom);
    }

    public final void z0(View view, int i, ra raVar) {
        float f = this.v.a / 2.0f;
        b(view, i, false);
        float f2 = raVar.c;
        this.y.j(view, (int) (f2 - f), (int) (f2 + f));
        R0(view, raVar.b, raVar.d);
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.s = new sa();
        this.w = 0;
        Q0(k.G(context, attributeSet, i, i2).a);
        this.t = new z70();
        this.u = null;
        k0();
    }
}
