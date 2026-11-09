package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.fa0;
import defpackage.h90;
import defpackage.in0;
import defpackage.sn0;
import defpackage.un0;
import defpackage.w10;
import defpackage.ws;
import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends k implements sn0 {
    public final a A;
    public final h90 B;
    public final int C;
    public final int[] D;
    public int p;
    public b q;
    public ws r;
    public boolean s;
    public final boolean t;
    public boolean u;
    public boolean v;
    public final boolean w;
    public int x;
    public int y;
    public SavedState z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new c();
        public int E;
        public int F;
        public boolean G;

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.E);
            parcel.writeInt(this.F);
            parcel.writeInt(this.G ? 1 : 0);
        }
    }

    public LinearLayoutManager(int i) {
        this.p = 1;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new a();
        this.B = new h90();
        this.C = 2;
        this.D = new int[2];
        c1(i);
        c(null);
        if (this.t) {
            this.t = false;
            n0();
        }
    }

    @Override // androidx.recyclerview.widget.k
    public boolean B0() {
        return this.z == null && this.s == this.v;
    }

    public void C0(un0 un0Var, int[] iArr) {
        int i;
        int iL = un0Var.a != -1 ? this.r.l() : 0;
        if (this.q.f == -1) {
            i = 0;
        } else {
            i = iL;
            iL = 0;
        }
        iArr[0] = iL;
        iArr[1] = i;
    }

    public void D0(un0 un0Var, b bVar, w10 w10Var) {
        int i = bVar.d;
        if (i < 0 || i >= un0Var.b()) {
            return;
        }
        w10Var.b(i, Math.max(0, bVar.g));
    }

    public final int E0(un0 un0Var) {
        if (v() == 0) {
            return 0;
        }
        I0();
        ws wsVar = this.r;
        boolean z = !this.w;
        return fa0.r(un0Var, wsVar, L0(z), K0(z), this, this.w);
    }

    public final int F0(un0 un0Var) {
        if (v() == 0) {
            return 0;
        }
        I0();
        ws wsVar = this.r;
        boolean z = !this.w;
        return fa0.s(un0Var, wsVar, L0(z), K0(z), this, this.w, this.u);
    }

    public final int G0(un0 un0Var) {
        if (v() == 0) {
            return 0;
        }
        I0();
        ws wsVar = this.r;
        boolean z = !this.w;
        return fa0.t(un0Var, wsVar, L0(z), K0(z), this, this.w);
    }

    public final int H0(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.p == 1) ? 1 : Integer.MIN_VALUE : this.p == 0 ? 1 : Integer.MIN_VALUE : this.p == 1 ? -1 : Integer.MIN_VALUE : this.p == 0 ? -1 : Integer.MIN_VALUE : (this.p != 1 && V0()) ? -1 : 1 : (this.p != 1 && V0()) ? 1 : -1;
    }

    public final void I0() {
        if (this.q == null) {
            b bVar = new b();
            bVar.a = true;
            bVar.h = 0;
            bVar.i = 0;
            bVar.k = null;
            this.q = bVar;
        }
    }

    public final int J0(l lVar, b bVar, un0 un0Var, boolean z) {
        int i;
        int i2 = bVar.c;
        int i3 = bVar.g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                bVar.g = i3 + i2;
            }
            Y0(lVar, bVar);
        }
        int i4 = bVar.c + bVar.h;
        while (true) {
            if ((!bVar.l && i4 <= 0) || (i = bVar.d) < 0 || i >= un0Var.b()) {
                break;
            }
            h90 h90Var = this.B;
            h90Var.a = 0;
            h90Var.b = false;
            h90Var.c = false;
            h90Var.d = false;
            W0(lVar, un0Var, bVar, h90Var);
            if (!h90Var.b) {
                int i5 = bVar.b;
                int i6 = h90Var.a;
                bVar.b = (bVar.f * i6) + i5;
                if (!h90Var.c || bVar.k != null || !un0Var.g) {
                    bVar.c -= i6;
                    i4 -= i6;
                }
                int i7 = bVar.g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + i6;
                    bVar.g = i8;
                    int i9 = bVar.c;
                    if (i9 < 0) {
                        bVar.g = i8 + i9;
                    }
                    Y0(lVar, bVar);
                }
                if (z && h90Var.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - bVar.c;
    }

    public final View K0(boolean z) {
        return this.u ? P0(0, v(), z) : P0(v() - 1, -1, z);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean L() {
        return true;
    }

    public final View L0(boolean z) {
        return this.u ? P0(v() - 1, -1, z) : P0(0, v(), z);
    }

    public final int M0() {
        View viewP0 = P0(0, v(), false);
        if (viewP0 == null) {
            return -1;
        }
        return k.H(viewP0);
    }

    public final int N0() {
        View viewP0 = P0(v() - 1, -1, false);
        if (viewP0 == null) {
            return -1;
        }
        return k.H(viewP0);
    }

    public final View O0(int i, int i2) {
        int i3;
        int i4;
        I0();
        if (i2 <= i && i2 >= i) {
            return u(i);
        }
        if (this.r.e(u(i)) < this.r.k()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.p == 0 ? this.c.b(i, i2, i3, i4) : this.d.b(i, i2, i3, i4);
    }

    public final View P0(int i, int i2, boolean z) {
        I0();
        int i3 = z ? 24579 : 320;
        return this.p == 0 ? this.c.b(i, i2, i3, 320) : this.d.b(i, i2, i3, 320);
    }

    public View Q0(l lVar, un0 un0Var, int i, int i2, int i3) {
        I0();
        int iK = this.r.k();
        int iG = this.r.g();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewU = u(i);
            int iH = k.H(viewU);
            if (iH >= 0 && iH < i3) {
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

    public final int R0(int i, l lVar, un0 un0Var, boolean z) {
        int iG;
        int iG2 = this.r.g() - i;
        if (iG2 <= 0) {
            return 0;
        }
        int i2 = -b1(-iG2, un0Var, lVar);
        int i3 = i + i2;
        if (!z || (iG = this.r.g() - i3) <= 0) {
            return i2;
        }
        this.r.o(iG);
        return iG + i2;
    }

    @Override // androidx.recyclerview.widget.k
    public View S(View view, int i, l lVar, un0 un0Var) {
        int iH0;
        a1();
        if (v() != 0 && (iH0 = H0(i)) != Integer.MIN_VALUE) {
            I0();
            e1(iH0, (int) (this.r.l() * 0.33333334f), false, un0Var);
            b bVar = this.q;
            bVar.g = Integer.MIN_VALUE;
            bVar.a = false;
            J0(lVar, bVar, un0Var, true);
            View viewO0 = iH0 == -1 ? this.u ? O0(v() - 1, -1) : O0(0, v()) : this.u ? O0(0, v()) : O0(v() - 1, -1);
            View viewU0 = iH0 == -1 ? U0() : T0();
            if (!viewU0.hasFocusable()) {
                return viewO0;
            }
            if (viewO0 != null) {
                return viewU0;
            }
        }
        return null;
    }

    public final int S0(int i, l lVar, un0 un0Var, boolean z) {
        int iK;
        int iK2 = i - this.r.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i2 = -b1(iK2, un0Var, lVar);
        int i3 = i + i2;
        if (!z || (iK = i3 - this.r.k()) <= 0) {
            return i2;
        }
        this.r.o(-iK);
        return i2 - iK;
    }

    @Override // androidx.recyclerview.widget.k
    public final void T(AccessibilityEvent accessibilityEvent) {
        super.T(accessibilityEvent);
        if (v() > 0) {
            accessibilityEvent.setFromIndex(M0());
            accessibilityEvent.setToIndex(N0());
        }
    }

    public final View T0() {
        return u(this.u ? 0 : v() - 1);
    }

    public final View U0() {
        return u(this.u ? v() - 1 : 0);
    }

    public final boolean V0() {
        return C() == 1;
    }

    public void W0(l lVar, un0 un0Var, b bVar, h90 h90Var) {
        int iE;
        int i;
        int i2;
        int iD;
        View viewB = bVar.b(lVar);
        if (viewB == null) {
            h90Var.b = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewB.getLayoutParams();
        if (bVar.k == null) {
            if (this.u == (bVar.f == -1)) {
                b(viewB, -1, false);
            } else {
                b(viewB, 0, false);
            }
        } else {
            if (this.u == (bVar.f == -1)) {
                b(viewB, -1, true);
            } else {
                b(viewB, 0, true);
            }
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) viewB.getLayoutParams();
        Rect rectJ = this.b.J(viewB);
        int i3 = rectJ.left + rectJ.right;
        int i4 = rectJ.top + rectJ.bottom;
        int iW = k.w(d(), this.n, this.l, F() + E() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin + i3, ((ViewGroup.MarginLayoutParams) layoutParams2).width);
        int iW2 = k.w(e(), this.o, this.m, D() + G() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) layoutParams2).height);
        if (w0(viewB, iW, iW2, layoutParams2)) {
            viewB.measure(iW, iW2);
        }
        h90Var.a = this.r.c(viewB);
        if (this.p == 1) {
            if (V0()) {
                iD = this.n - F();
                iE = iD - this.r.d(viewB);
            } else {
                iE = E();
                iD = this.r.d(viewB) + iE;
            }
            if (bVar.f == -1) {
                i = bVar.b;
                i2 = i - h90Var.a;
            } else {
                i2 = bVar.b;
                i = h90Var.a + i2;
            }
        } else {
            int iG = G();
            int iD2 = this.r.d(viewB) + iG;
            if (bVar.f == -1) {
                int i5 = bVar.b;
                int i6 = i5 - h90Var.a;
                iD = i5;
                i = iD2;
                iE = i6;
                i2 = iG;
            } else {
                int i7 = bVar.b;
                int i8 = h90Var.a + i7;
                iE = i7;
                i = iD2;
                i2 = iG;
                iD = i8;
            }
        }
        k.N(viewB, iE, i2, iD, i);
        if (layoutParams.a.h() || layoutParams.a.k()) {
            h90Var.c = true;
        }
        h90Var.d = viewB.hasFocusable();
    }

    public final void Y0(l lVar, b bVar) {
        if (!bVar.a || bVar.l) {
            return;
        }
        int i = bVar.g;
        int i2 = bVar.i;
        if (bVar.f == -1) {
            int iV = v();
            if (i < 0) {
                return;
            }
            int iF = (this.r.f() - i) + i2;
            if (this.u) {
                for (int i3 = 0; i3 < iV; i3++) {
                    View viewU = u(i3);
                    if (this.r.e(viewU) < iF || this.r.n(viewU) < iF) {
                        Z0(lVar, 0, i3);
                        return;
                    }
                }
                return;
            }
            int i4 = iV - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View viewU2 = u(i5);
                if (this.r.e(viewU2) < iF || this.r.n(viewU2) < iF) {
                    Z0(lVar, i4, i5);
                    return;
                }
            }
            return;
        }
        if (i < 0) {
            return;
        }
        int i6 = i - i2;
        int iV2 = v();
        if (!this.u) {
            for (int i7 = 0; i7 < iV2; i7++) {
                View viewU3 = u(i7);
                if (this.r.b(viewU3) > i6 || this.r.m(viewU3) > i6) {
                    Z0(lVar, 0, i7);
                    return;
                }
            }
            return;
        }
        int i8 = iV2 - 1;
        for (int i9 = i8; i9 >= 0; i9--) {
            View viewU4 = u(i9);
            if (this.r.b(viewU4) > i6 || this.r.m(viewU4) > i6) {
                Z0(lVar, i8, i9);
                return;
            }
        }
    }

    public final void Z0(l lVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                View viewU = u(i);
                l0(i);
                lVar.f(viewU);
                i--;
            }
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            View viewU2 = u(i3);
            l0(i3);
            lVar.f(viewU2);
        }
    }

    @Override // defpackage.sn0
    public final PointF a(int i) {
        if (v() == 0) {
            return null;
        }
        int i2 = (i < k.H(u(0))) != this.u ? -1 : 1;
        return this.p == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    public final void a1() {
        if (this.p == 1 || !V0()) {
            this.u = this.t;
        } else {
            this.u = !this.t;
        }
    }

    public final int b1(int i, un0 un0Var, l lVar) {
        if (v() != 0 && i != 0) {
            I0();
            this.q.a = true;
            int i2 = i > 0 ? 1 : -1;
            int iAbs = Math.abs(i);
            e1(i2, iAbs, true, un0Var);
            b bVar = this.q;
            int iJ0 = J0(lVar, bVar, un0Var, false) + bVar.g;
            if (iJ0 >= 0) {
                if (iAbs > iJ0) {
                    i = i2 * iJ0;
                }
                this.r.o(-i);
                this.q.j = i;
                return i;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.k
    public final void c(String str) {
        if (this.z == null) {
            super.c(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0196  */
    @Override // androidx.recyclerview.widget.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c0(androidx.recyclerview.widget.l r18, defpackage.un0 r19) {
        /*
            Method dump skipped, instruction units count: 1173
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.c0(androidx.recyclerview.widget.l, un0):void");
    }

    public final void c1(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(zn.j(i, "invalid orientation:"));
        }
        c(null);
        if (i != this.p || this.r == null) {
            ws wsVarA = ws.a(this, i);
            this.r = wsVarA;
            this.A.a = wsVarA;
            this.p = i;
            n0();
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean d() {
        return this.p == 0;
    }

    @Override // androidx.recyclerview.widget.k
    public void d0(un0 un0Var) {
        this.z = null;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.A.d();
    }

    public void d1(boolean z) {
        c(null);
        if (this.v == z) {
            return;
        }
        this.v = z;
        n0();
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean e() {
        return this.p == 1;
    }

    @Override // androidx.recyclerview.widget.k
    public final void e0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.z = (SavedState) parcelable;
            n0();
        }
    }

    public final void e1(int i, int i2, boolean z, un0 un0Var) {
        int iK;
        this.q.l = this.r.i() == 0 && this.r.f() == 0;
        this.q.f = i;
        int[] iArr = this.D;
        iArr[0] = 0;
        iArr[1] = 0;
        C0(un0Var, iArr);
        int iMax = Math.max(0, iArr[0]);
        int iMax2 = Math.max(0, iArr[1]);
        boolean z2 = i == 1;
        b bVar = this.q;
        int i3 = z2 ? iMax2 : iMax;
        bVar.h = i3;
        if (!z2) {
            iMax = iMax2;
        }
        bVar.i = iMax;
        if (z2) {
            bVar.h = this.r.h() + i3;
            View viewT0 = T0();
            b bVar2 = this.q;
            bVar2.e = this.u ? -1 : 1;
            int iH = k.H(viewT0);
            b bVar3 = this.q;
            bVar2.d = iH + bVar3.e;
            bVar3.b = this.r.b(viewT0);
            iK = this.r.b(viewT0) - this.r.g();
        } else {
            View viewU0 = U0();
            b bVar4 = this.q;
            bVar4.h = this.r.k() + bVar4.h;
            b bVar5 = this.q;
            bVar5.e = this.u ? 1 : -1;
            int iH2 = k.H(viewU0);
            b bVar6 = this.q;
            bVar5.d = iH2 + bVar6.e;
            bVar6.b = this.r.e(viewU0);
            iK = (-this.r.e(viewU0)) + this.r.k();
        }
        b bVar7 = this.q;
        bVar7.c = i2;
        if (z) {
            bVar7.c = i2 - iK;
        }
        bVar7.g = iK;
    }

    @Override // androidx.recyclerview.widget.k
    public final Parcelable f0() {
        SavedState savedState = this.z;
        if (savedState != null) {
            SavedState savedState2 = new SavedState();
            savedState2.E = savedState.E;
            savedState2.F = savedState.F;
            savedState2.G = savedState.G;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        if (v() <= 0) {
            savedState3.E = -1;
            return savedState3;
        }
        I0();
        boolean z = this.s ^ this.u;
        savedState3.G = z;
        if (z) {
            View viewT0 = T0();
            savedState3.F = this.r.g() - this.r.b(viewT0);
            savedState3.E = k.H(viewT0);
            return savedState3;
        }
        View viewU0 = U0();
        savedState3.E = k.H(viewU0);
        savedState3.F = this.r.e(viewU0) - this.r.k();
        return savedState3;
    }

    public final void f1(int i, int i2) {
        this.q.c = this.r.g() - i2;
        b bVar = this.q;
        bVar.e = this.u ? -1 : 1;
        bVar.d = i;
        bVar.f = 1;
        bVar.b = i2;
        bVar.g = Integer.MIN_VALUE;
    }

    public final void g1(int i, int i2) {
        this.q.c = i2 - this.r.k();
        b bVar = this.q;
        bVar.d = i;
        bVar.e = this.u ? 1 : -1;
        bVar.f = -1;
        bVar.b = i2;
        bVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.k
    public final void h(int i, int i2, un0 un0Var, w10 w10Var) {
        if (this.p != 0) {
            i = i2;
        }
        if (v() == 0 || i == 0) {
            return;
        }
        I0();
        e1(i > 0 ? 1 : -1, Math.abs(i), true, un0Var);
        D0(un0Var, this.q, w10Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final void i(int i, w10 w10Var) {
        boolean z;
        int i2;
        SavedState savedState = this.z;
        if (savedState == null || (i2 = savedState.E) < 0) {
            a1();
            z = this.u;
            i2 = this.x;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = savedState.G;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.C && i2 >= 0 && i2 < i; i4++) {
            w10Var.b(i2, 0);
            i2 += i3;
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final int j(un0 un0Var) {
        return E0(un0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int k(un0 un0Var) {
        return F0(un0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int l(un0 un0Var) {
        return G0(un0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final int m(un0 un0Var) {
        return E0(un0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int n(un0 un0Var) {
        return F0(un0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int o(un0 un0Var) {
        return G0(un0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int o0(int i, un0 un0Var, l lVar) {
        if (this.p == 1) {
            return 0;
        }
        return b1(i, un0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final void p0(int i) {
        this.x = i;
        this.y = Integer.MIN_VALUE;
        SavedState savedState = this.z;
        if (savedState != null) {
            savedState.E = -1;
        }
        n0();
    }

    @Override // androidx.recyclerview.widget.k
    public final View q(int i) {
        int iV = v();
        if (iV == 0) {
            return null;
        }
        int iH = i - k.H(u(0));
        if (iH >= 0 && iH < iV) {
            View viewU = u(iH);
            if (k.H(viewU) == i) {
                return viewU;
            }
        }
        return super.q(i);
    }

    @Override // androidx.recyclerview.widget.k
    public int q0(int i, un0 un0Var, l lVar) {
        if (this.p == 0) {
            return 0;
        }
        return b1(i, un0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.k
    public RecyclerView.LayoutParams r() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean x0() {
        if (this.m != 1073741824 && this.l != 1073741824) {
            int iV = v();
            for (int i = 0; i < iV; i++) {
                ViewGroup.LayoutParams layoutParams = u(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.k
    public void z0(RecyclerView recyclerView, int i) {
        d dVar = new d(recyclerView.getContext());
        dVar.a = i;
        A0(dVar);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.p = 1;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new a();
        this.B = new h90();
        this.C = 2;
        this.D = new int[2];
        in0 in0VarI = k.I(context, attributeSet, i, i2);
        c1(in0VarI.a);
        boolean z = in0VarI.c;
        c(null);
        if (z != this.t) {
            this.t = z;
            n0();
        }
        d1(in0VarI.d);
    }

    @Override // androidx.recyclerview.widget.k
    public final void R(RecyclerView recyclerView) {
    }

    public void X0(l lVar, un0 un0Var, a aVar, int i) {
    }
}
