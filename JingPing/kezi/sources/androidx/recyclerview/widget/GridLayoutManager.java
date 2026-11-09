package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.d20;
import defpackage.e5;
import defpackage.hk;
import defpackage.n0;
import defpackage.o0;
import defpackage.xr0;
import defpackage.yd0;
import defpackage.zu;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean E;
    public int F;
    public int[] G;
    public View[] H;
    public final SparseIntArray I;
    public final SparseIntArray J;
    public final e5 K;
    public final Rect L;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new e5(17);
        this.L = new Rect();
        l1(k.G(context, attributeSet, i, i2).b);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void A0(yd0 yd0Var, b bVar, zu zuVar) {
        int i;
        int i2 = this.F;
        for (int i3 = 0; i3 < this.F && (i = bVar.d) >= 0 && i < yd0Var.b() && i2 > 0; i3++) {
            zuVar.b(bVar.d, Math.max(0, bVar.g));
            this.K.getClass();
            i2--;
            bVar.d += bVar.e;
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final int H(l lVar, yd0 yd0Var) {
        if (this.p == 0) {
            return this.F;
        }
        if (yd0Var.b() < 1) {
            return 0;
        }
        return h1(yd0Var.b() - 1, yd0Var, lVar) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View N0(l lVar, yd0 yd0Var, int i, int i2, int i3) {
        F0();
        int iK = this.r.k();
        int iG = this.r.g();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewU = u(i);
            int iF = k.F(viewU);
            if (iF >= 0 && iF < i3 && i1(iF, yd0Var, lVar) == 0) {
                if (((RecyclerView.LayoutParams) viewU.getLayoutParams()).a.h()) {
                    if (view2 == null) {
                        view2 = viewU;
                    }
                } else {
                    if (this.r.e(viewU) < iG && this.r.b(viewU) >= iK) {
                        return viewU;
                    }
                    if (view == null) {
                        view = viewU;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e2, code lost:
    
        if (r13 == (r2 > r15)) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0142, code lost:
    
        if (r16 == null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0144, code lost:
    
        return r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0145, code lost:
    
        return r17;
     */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View P(android.view.View r23, int r24, androidx.recyclerview.widget.l r25, defpackage.yd0 r26) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.P(android.view.View, int, androidx.recyclerview.widget.l, yd0):android.view.View");
    }

    @Override // androidx.recyclerview.widget.k
    public final void T(l lVar, yd0 yd0Var, View view, o0 o0Var) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            S(view, o0Var);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int iH1 = h1(layoutParams2.a.b(), yd0Var, lVar);
        if (this.p == 0) {
            o0Var.j(n0.a(layoutParams2.e, layoutParams2.f, iH1, 1, false, false));
        } else {
            o0Var.j(n0.a(iH1, 1, layoutParams2.e, layoutParams2.f, false, false));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v39 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void T0(l lVar, yd0 yd0Var, b bVar, d20 d20Var) {
        int i;
        int i2;
        int i3;
        int iD;
        int iC;
        int iD2;
        int i4;
        int iW;
        int iW2;
        ?? r8;
        int i5;
        View viewB;
        int iJ = this.r.j();
        boolean z = iJ != 1073741824;
        int i6 = v() > 0 ? this.G[this.F] : 0;
        if (z) {
            m1();
        }
        boolean z2 = bVar.e == 1;
        int iI1 = this.F;
        if (!z2) {
            iI1 = i1(bVar.d, yd0Var, lVar) + j1(bVar.d, yd0Var, lVar);
        }
        int i7 = 0;
        while (i7 < this.F && (i5 = bVar.d) >= 0 && i5 < yd0Var.b() && iI1 > 0) {
            int i8 = bVar.d;
            int iJ1 = j1(i8, yd0Var, lVar);
            if (iJ1 > this.F) {
                throw new IllegalArgumentException("Item at position " + i8 + " requires " + iJ1 + " spans but GridLayoutManager has only " + this.F + " spans.");
            }
            iI1 -= iJ1;
            if (iI1 < 0 || (viewB = bVar.b(lVar)) == null) {
                break;
            }
            this.H[i7] = viewB;
            i7++;
        }
        if (i7 == 0) {
            d20Var.b = true;
            return;
        }
        if (z2) {
            i3 = 1;
            i2 = i7;
            i = 0;
        } else {
            i = i7 - 1;
            i2 = -1;
            i3 = -1;
        }
        int i9 = 0;
        while (i != i2) {
            View view = this.H[i];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int iJ12 = j1(k.F(view), yd0Var, lVar);
            layoutParams.f = iJ12;
            layoutParams.e = i9;
            i9 += iJ12;
            i += i3;
        }
        float f = 0.0f;
        int i10 = 0;
        for (int i11 = 0; i11 < i7; i11++) {
            View view2 = this.H[i11];
            if (bVar.k != null) {
                r8 = 0;
                r8 = 0;
                if (z2) {
                    b(view2, -1, true);
                } else {
                    b(view2, 0, true);
                }
            } else if (z2) {
                r8 = 0;
                b(view2, -1, false);
            } else {
                r8 = 0;
                b(view2, 0, false);
            }
            RecyclerView recyclerView = this.b;
            Rect rect = this.L;
            if (recyclerView == null) {
                rect.set(r8, r8, r8, r8);
            } else {
                rect.set(recyclerView.J(view2));
            }
            k1(view2, iJ, r8);
            int iC2 = this.r.c(view2);
            if (iC2 > i10) {
                i10 = iC2;
            }
            float fD = (this.r.d(view2) * 1.0f) / ((LayoutParams) view2.getLayoutParams()).f;
            if (fD > f) {
                f = fD;
            }
        }
        if (z) {
            e1(Math.max(Math.round(f * this.F), i6));
            i10 = 0;
            for (int i12 = 0; i12 < i7; i12++) {
                View view3 = this.H[i12];
                k1(view3, 1073741824, true);
                int iC3 = this.r.c(view3);
                if (iC3 > i10) {
                    i10 = iC3;
                }
            }
        }
        for (int i13 = 0; i13 < i7; i13++) {
            View view4 = this.H[i13];
            if (this.r.c(view4) != i10) {
                LayoutParams layoutParams2 = (LayoutParams) view4.getLayoutParams();
                Rect rect2 = layoutParams2.b;
                int i14 = rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
                int i15 = rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
                int iG1 = g1(layoutParams2.e, layoutParams2.f);
                if (this.p == 1) {
                    iW2 = k.w(false, iG1, 1073741824, i15, ((ViewGroup.MarginLayoutParams) layoutParams2).width);
                    iW = View.MeasureSpec.makeMeasureSpec(i10 - i14, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10 - i15, 1073741824);
                    iW = k.w(false, iG1, 1073741824, i14, ((ViewGroup.MarginLayoutParams) layoutParams2).height);
                    iW2 = iMakeMeasureSpec;
                }
                if (v0(view4, iW2, iW, (RecyclerView.LayoutParams) view4.getLayoutParams())) {
                    view4.measure(iW2, iW);
                }
            }
        }
        d20Var.a = i10;
        if (this.p == 1) {
            if (bVar.f == -1) {
                int i16 = bVar.b;
                i4 = i16 - i10;
                iD2 = i16;
            } else {
                i4 = bVar.b;
                iD2 = i4 + i10;
            }
            iC = 0;
            iD = 0;
        } else {
            if (bVar.f == -1) {
                int i17 = bVar.b;
                iC = i17 - i10;
                iD = i17;
            } else {
                int i18 = bVar.b;
                iD = i18 + i10;
                iC = i18;
            }
            iD2 = 0;
            i4 = 0;
        }
        for (int i19 = 0; i19 < i7; i19++) {
            View view5 = this.H[i19];
            LayoutParams layoutParams3 = (LayoutParams) view5.getLayoutParams();
            if (this.p != 1) {
                int iE = E() + this.G[layoutParams3.e];
                i4 = iE;
                iD2 = this.r.d(view5) + iE;
            } else if (S0()) {
                int iC4 = C() + this.G[this.F - layoutParams3.e];
                iD = iC4;
                iC = iC4 - this.r.d(view5);
            } else {
                iC = C() + this.G[layoutParams3.e];
                iD = this.r.d(view5) + iC;
            }
            k.L(view5, iC, i4, iD, iD2);
            if (layoutParams3.a.h() || layoutParams3.a.k()) {
                d20Var.c = true;
            }
            d20Var.d = view5.hasFocusable() | d20Var.d;
        }
        Arrays.fill(this.H, (Object) null);
    }

    @Override // androidx.recyclerview.widget.k
    public final void U(int i, int i2) {
        e5 e5Var = this.K;
        e5Var.C();
        ((SparseIntArray) e5Var.d).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void U0(l lVar, yd0 yd0Var, a aVar, int i) {
        m1();
        if (yd0Var.b() > 0 && !yd0Var.g) {
            boolean z = i == 1;
            int iI1 = i1(aVar.b, yd0Var, lVar);
            if (z) {
                while (iI1 > 0) {
                    int i2 = aVar.b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    aVar.b = i3;
                    iI1 = i1(i3, yd0Var, lVar);
                }
            } else {
                int iB = yd0Var.b() - 1;
                int i4 = aVar.b;
                while (i4 < iB) {
                    int i5 = i4 + 1;
                    int iI12 = i1(i5, yd0Var, lVar);
                    if (iI12 <= iI1) {
                        break;
                    }
                    i4 = i5;
                    iI1 = iI12;
                }
                aVar.b = i4;
            }
        }
        f1();
    }

    @Override // androidx.recyclerview.widget.k
    public final void V() {
        e5 e5Var = this.K;
        e5Var.C();
        ((SparseIntArray) e5Var.d).clear();
    }

    @Override // androidx.recyclerview.widget.k
    public final void W(int i, int i2) {
        e5 e5Var = this.K;
        e5Var.C();
        ((SparseIntArray) e5Var.d).clear();
    }

    @Override // androidx.recyclerview.widget.k
    public final void X(int i, int i2) {
        e5 e5Var = this.K;
        e5Var.C();
        ((SparseIntArray) e5Var.d).clear();
    }

    @Override // androidx.recyclerview.widget.k
    public final void Y(int i, int i2) {
        e5 e5Var = this.K;
        e5Var.C();
        ((SparseIntArray) e5Var.d).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final void Z(l lVar, yd0 yd0Var) {
        boolean z = yd0Var.g;
        SparseIntArray sparseIntArray = this.J;
        SparseIntArray sparseIntArray2 = this.I;
        if (z) {
            int iV = v();
            for (int i = 0; i < iV; i++) {
                LayoutParams layoutParams = (LayoutParams) u(i).getLayoutParams();
                int iB = layoutParams.a.b();
                sparseIntArray2.put(iB, layoutParams.f);
                sparseIntArray.put(iB, layoutParams.e);
            }
        }
        super.Z(lVar, yd0Var);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final void a0(yd0 yd0Var) {
        super.a0(yd0Var);
        this.E = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void a1(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.a1(false);
    }

    public final void e1(int i) {
        int i2;
        int[] iArr = this.G;
        int i3 = this.F;
        if (iArr == null || iArr.length != i3 + 1 || iArr[iArr.length - 1] != i) {
            iArr = new int[i3 + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.G = iArr;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean f(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void f1() {
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
    }

    public final int g1(int i, int i2) {
        if (this.p != 1 || !S0()) {
            int[] iArr = this.G;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.G;
        int i3 = this.F;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public final int h1(int i, yd0 yd0Var, l lVar) {
        boolean z = yd0Var.g;
        e5 e5Var = this.K;
        if (!z) {
            int i2 = this.F;
            e5Var.getClass();
            return e5.A(i, i2);
        }
        int iB = lVar.b(i);
        if (iB != -1) {
            int i3 = this.F;
            e5Var.getClass();
            return e5.A(iB, i3);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    public final int i1(int i, yd0 yd0Var, l lVar) {
        boolean z = yd0Var.g;
        e5 e5Var = this.K;
        if (!z) {
            int i2 = this.F;
            e5Var.getClass();
            return i % i2;
        }
        int i3 = this.J.get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int iB = lVar.b(i);
        if (iB != -1) {
            int i4 = this.F;
            e5Var.getClass();
            return iB % i4;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    public final int j1(int i, yd0 yd0Var, l lVar) {
        boolean z = yd0Var.g;
        e5 e5Var = this.K;
        if (!z) {
            e5Var.getClass();
            return 1;
        }
        int i2 = this.I.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        if (lVar.b(i) != -1) {
            e5Var.getClass();
            return 1;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final int k(yd0 yd0Var) {
        return C0(yd0Var);
    }

    public final void k1(View view, int i, boolean z) {
        int iW;
        int iW2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.b;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int iG1 = g1(layoutParams.e, layoutParams.f);
        if (this.p == 1) {
            iW2 = k.w(false, iG1, i, i3, ((ViewGroup.MarginLayoutParams) layoutParams).width);
            iW = k.w(true, this.r.l(), this.m, i2, ((ViewGroup.MarginLayoutParams) layoutParams).height);
        } else {
            int iW3 = k.w(false, iG1, i, i2, ((ViewGroup.MarginLayoutParams) layoutParams).height);
            int iW4 = k.w(true, this.r.l(), this.l, i3, ((ViewGroup.MarginLayoutParams) layoutParams).width);
            iW = iW3;
            iW2 = iW4;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z ? v0(view, iW2, iW, layoutParams2) : t0(view, iW2, iW, layoutParams2)) {
            view.measure(iW2, iW);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final int l(yd0 yd0Var) {
        return D0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final int l0(int i, yd0 yd0Var, l lVar) {
        m1();
        f1();
        return super.l0(i, yd0Var, lVar);
    }

    public final void l1(int i) {
        if (i == this.F) {
            return;
        }
        this.E = true;
        if (i < 1) {
            throw new IllegalArgumentException(hk.g(i, "Span count should be at least 1. Provided "));
        }
        this.F = i;
        this.K.C();
        k0();
    }

    public final void m1() {
        int iB;
        int iE;
        if (this.p == 1) {
            iB = this.n - D();
            iE = C();
        } else {
            iB = this.o - B();
            iE = E();
        }
        e1(iB - iE);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final int n(yd0 yd0Var) {
        return C0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final int n0(int i, yd0 yd0Var, l lVar) {
        m1();
        f1();
        return super.n0(i, yd0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final int o(yd0 yd0Var) {
        return D0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final void q0(Rect rect, int i, int i2) {
        int iG;
        int iG2;
        if (this.G == null) {
            super.q0(rect, i, i2);
        }
        int iD = D() + C();
        int iB = B() + E();
        if (this.p == 1) {
            int iHeight = rect.height() + iB;
            RecyclerView recyclerView = this.b;
            WeakHashMap weakHashMap = xr0.a;
            iG2 = k.g(i2, iHeight, recyclerView.getMinimumHeight());
            int[] iArr = this.G;
            iG = k.g(i, iArr[iArr.length - 1] + iD, this.b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iD;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap weakHashMap2 = xr0.a;
            iG = k.g(i, iWidth, recyclerView2.getMinimumWidth());
            int[] iArr2 = this.G;
            iG2 = k.g(i2, iArr2[iArr2.length - 1] + iB, this.b.getMinimumHeight());
        }
        this.b.setMeasuredDimension(iG, iG2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams r() {
        return this.p == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams s(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams t(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            LayoutParams layoutParams2 = new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            layoutParams2.e = -1;
            layoutParams2.f = 0;
            return layoutParams2;
        }
        LayoutParams layoutParams3 = new LayoutParams(layoutParams);
        layoutParams3.e = -1;
        layoutParams3.f = 0;
        return layoutParams3;
    }

    @Override // androidx.recyclerview.widget.k
    public final int x(l lVar, yd0 yd0Var) {
        if (this.p == 1) {
            return this.F;
        }
        if (yd0Var.b() < 1) {
            return 0;
        }
        return h1(yd0Var.b() - 1, yd0Var, lVar) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.k
    public final boolean y0() {
        return this.z == null && !this.E;
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public int e;
        public int f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = -1;
            this.f = 0;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.e = -1;
            this.f = 0;
        }
    }

    public GridLayoutManager(int i) {
        super(1);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new e5(17);
        this.L = new Rect();
        l1(i);
    }
}
