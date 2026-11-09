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
import defpackage.d20;
import defpackage.en;
import defpackage.hk;
import defpackage.md0;
import defpackage.wd0;
import defpackage.xi;
import defpackage.yd0;
import defpackage.zu;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends k implements wd0 {
    public final a A;
    public final d20 B;
    public final int C;
    public final int[] D;
    public int p;
    public b q;
    public en r;
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
        public int b;
        public int c;
        public boolean d;

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d ? 1 : 0);
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
        this.B = new d20();
        this.C = 2;
        this.D = new int[2];
        Z0(i);
        c(null);
        if (this.t) {
            this.t = false;
            k0();
        }
    }

    public void A0(yd0 yd0Var, b bVar, zu zuVar) {
        int i = bVar.d;
        if (i < 0 || i >= yd0Var.b()) {
            return;
        }
        zuVar.b(i, Math.max(0, bVar.g));
    }

    public final int B0(yd0 yd0Var) {
        if (v() == 0) {
            return 0;
        }
        F0();
        en enVar = this.r;
        boolean z = !this.w;
        return xi.i(yd0Var, enVar, I0(z), H0(z), this, this.w);
    }

    public final int C0(yd0 yd0Var) {
        if (v() == 0) {
            return 0;
        }
        F0();
        en enVar = this.r;
        boolean z = !this.w;
        return xi.j(yd0Var, enVar, I0(z), H0(z), this, this.w, this.u);
    }

    public final int D0(yd0 yd0Var) {
        if (v() == 0) {
            return 0;
        }
        F0();
        en enVar = this.r;
        boolean z = !this.w;
        return xi.k(yd0Var, enVar, I0(z), H0(z), this, this.w);
    }

    public final int E0(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.p == 1) ? 1 : Integer.MIN_VALUE : this.p == 0 ? 1 : Integer.MIN_VALUE : this.p == 1 ? -1 : Integer.MIN_VALUE : this.p == 0 ? -1 : Integer.MIN_VALUE : (this.p != 1 && S0()) ? -1 : 1 : (this.p != 1 && S0()) ? 1 : -1;
    }

    public final void F0() {
        if (this.q == null) {
            b bVar = new b();
            bVar.a = true;
            bVar.h = 0;
            bVar.i = 0;
            bVar.k = null;
            this.q = bVar;
        }
    }

    public final int G0(l lVar, b bVar, yd0 yd0Var, boolean z) {
        int i;
        int i2 = bVar.c;
        int i3 = bVar.g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                bVar.g = i3 + i2;
            }
            V0(lVar, bVar);
        }
        int i4 = bVar.c + bVar.h;
        while (true) {
            if ((!bVar.l && i4 <= 0) || (i = bVar.d) < 0 || i >= yd0Var.b()) {
                break;
            }
            d20 d20Var = this.B;
            d20Var.a = 0;
            d20Var.b = false;
            d20Var.c = false;
            d20Var.d = false;
            T0(lVar, yd0Var, bVar, d20Var);
            if (!d20Var.b) {
                int i5 = bVar.b;
                int i6 = d20Var.a;
                bVar.b = (bVar.f * i6) + i5;
                if (!d20Var.c || bVar.k != null || !yd0Var.g) {
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
                    V0(lVar, bVar);
                }
                if (z && d20Var.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - bVar.c;
    }

    public final View H0(boolean z) {
        return this.u ? M0(0, v(), z) : M0(v() - 1, -1, z);
    }

    public final View I0(boolean z) {
        return this.u ? M0(v() - 1, -1, z) : M0(0, v(), z);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean J() {
        return true;
    }

    public final int J0() {
        View viewM0 = M0(0, v(), false);
        if (viewM0 == null) {
            return -1;
        }
        return k.F(viewM0);
    }

    public final int K0() {
        View viewM0 = M0(v() - 1, -1, false);
        if (viewM0 == null) {
            return -1;
        }
        return k.F(viewM0);
    }

    public final View L0(int i, int i2) {
        int i3;
        int i4;
        F0();
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
        return this.p == 0 ? this.c.x(i, i2, i3, i4) : this.d.x(i, i2, i3, i4);
    }

    public final View M0(int i, int i2, boolean z) {
        F0();
        int i3 = z ? 24579 : 320;
        return this.p == 0 ? this.c.x(i, i2, i3, 320) : this.d.x(i, i2, i3, 320);
    }

    public View N0(l lVar, yd0 yd0Var, int i, int i2, int i3) {
        F0();
        int iK = this.r.k();
        int iG = this.r.g();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewU = u(i);
            int iF = k.F(viewU);
            if (iF >= 0 && iF < i3) {
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

    public final int O0(int i, l lVar, yd0 yd0Var, boolean z) {
        int iG;
        int iG2 = this.r.g() - i;
        if (iG2 <= 0) {
            return 0;
        }
        int i2 = -Y0(-iG2, yd0Var, lVar);
        int i3 = i + i2;
        if (!z || (iG = this.r.g() - i3) <= 0) {
            return i2;
        }
        this.r.o(iG);
        return iG + i2;
    }

    @Override // androidx.recyclerview.widget.k
    public View P(View view, int i, l lVar, yd0 yd0Var) {
        int iE0;
        X0();
        if (v() != 0 && (iE0 = E0(i)) != Integer.MIN_VALUE) {
            F0();
            b1(iE0, (int) (this.r.l() * 0.33333334f), false, yd0Var);
            b bVar = this.q;
            bVar.g = Integer.MIN_VALUE;
            bVar.a = false;
            G0(lVar, bVar, yd0Var, true);
            View viewL0 = iE0 == -1 ? this.u ? L0(v() - 1, -1) : L0(0, v()) : this.u ? L0(0, v()) : L0(v() - 1, -1);
            View viewR0 = iE0 == -1 ? R0() : Q0();
            if (!viewR0.hasFocusable()) {
                return viewL0;
            }
            if (viewL0 != null) {
                return viewR0;
            }
        }
        return null;
    }

    public final int P0(int i, l lVar, yd0 yd0Var, boolean z) {
        int iK;
        int iK2 = i - this.r.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i2 = -Y0(iK2, yd0Var, lVar);
        int i3 = i + i2;
        if (!z || (iK = i3 - this.r.k()) <= 0) {
            return i2;
        }
        this.r.o(-iK);
        return i2 - iK;
    }

    @Override // androidx.recyclerview.widget.k
    public final void Q(AccessibilityEvent accessibilityEvent) {
        super.Q(accessibilityEvent);
        if (v() > 0) {
            accessibilityEvent.setFromIndex(J0());
            accessibilityEvent.setToIndex(K0());
        }
    }

    public final View Q0() {
        return u(this.u ? 0 : v() - 1);
    }

    public final View R0() {
        return u(this.u ? v() - 1 : 0);
    }

    public final boolean S0() {
        return A() == 1;
    }

    public void T0(l lVar, yd0 yd0Var, b bVar, d20 d20Var) {
        int iC;
        int i;
        int i2;
        int iD;
        View viewB = bVar.b(lVar);
        if (viewB == null) {
            d20Var.b = true;
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
        int iW = k.w(d(), this.n, this.l, D() + C() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin + i3, ((ViewGroup.MarginLayoutParams) layoutParams2).width);
        int iW2 = k.w(e(), this.o, this.m, B() + E() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) layoutParams2).height);
        if (t0(viewB, iW, iW2, layoutParams2)) {
            viewB.measure(iW, iW2);
        }
        d20Var.a = this.r.c(viewB);
        if (this.p == 1) {
            if (S0()) {
                iD = this.n - D();
                iC = iD - this.r.d(viewB);
            } else {
                iC = C();
                iD = this.r.d(viewB) + iC;
            }
            if (bVar.f == -1) {
                i = bVar.b;
                i2 = i - d20Var.a;
            } else {
                i2 = bVar.b;
                i = d20Var.a + i2;
            }
        } else {
            int iE = E();
            int iD2 = this.r.d(viewB) + iE;
            if (bVar.f == -1) {
                int i5 = bVar.b;
                int i6 = i5 - d20Var.a;
                iD = i5;
                i = iD2;
                iC = i6;
                i2 = iE;
            } else {
                int i7 = bVar.b;
                int i8 = d20Var.a + i7;
                iC = i7;
                i = iD2;
                i2 = iE;
                iD = i8;
            }
        }
        k.L(viewB, iC, i2, iD, i);
        if (layoutParams.a.h() || layoutParams.a.k()) {
            d20Var.c = true;
        }
        d20Var.d = viewB.hasFocusable();
    }

    public final void V0(l lVar, b bVar) {
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
                        W0(lVar, 0, i3);
                        return;
                    }
                }
                return;
            }
            int i4 = iV - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View viewU2 = u(i5);
                if (this.r.e(viewU2) < iF || this.r.n(viewU2) < iF) {
                    W0(lVar, i4, i5);
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
                    W0(lVar, 0, i7);
                    return;
                }
            }
            return;
        }
        int i8 = iV2 - 1;
        for (int i9 = i8; i9 >= 0; i9--) {
            View viewU4 = u(i9);
            if (this.r.b(viewU4) > i6 || this.r.m(viewU4) > i6) {
                W0(lVar, i8, i9);
                return;
            }
        }
    }

    public final void W0(l lVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                View viewU = u(i);
                i0(i);
                lVar.f(viewU);
                i--;
            }
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            View viewU2 = u(i3);
            i0(i3);
            lVar.f(viewU2);
        }
    }

    public final void X0() {
        if (this.p == 1 || !S0()) {
            this.u = this.t;
        } else {
            this.u = !this.t;
        }
    }

    public final int Y0(int i, yd0 yd0Var, l lVar) {
        if (v() != 0 && i != 0) {
            F0();
            this.q.a = true;
            int i2 = i > 0 ? 1 : -1;
            int iAbs = Math.abs(i);
            b1(i2, iAbs, true, yd0Var);
            b bVar = this.q;
            int iG0 = G0(lVar, bVar, yd0Var, false) + bVar.g;
            if (iG0 >= 0) {
                if (iAbs > iG0) {
                    i = i2 * iG0;
                }
                this.r.o(-i);
                this.q.j = i;
                return i;
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0196  */
    @Override // androidx.recyclerview.widget.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Z(androidx.recyclerview.widget.l r18, defpackage.yd0 r19) {
        /*
            Method dump skipped, instruction units count: 1173
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.Z(androidx.recyclerview.widget.l, yd0):void");
    }

    public final void Z0(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(hk.g(i, "invalid orientation:"));
        }
        c(null);
        if (i != this.p || this.r == null) {
            en enVarA = en.a(this, i);
            this.r = enVarA;
            this.A.a = enVarA;
            this.p = i;
            k0();
        }
    }

    @Override // defpackage.wd0
    public final PointF a(int i) {
        if (v() == 0) {
            return null;
        }
        int i2 = (i < k.F(u(0))) != this.u ? -1 : 1;
        return this.p == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.k
    public void a0(yd0 yd0Var) {
        this.z = null;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.A.d();
    }

    public void a1(boolean z) {
        c(null);
        if (this.v == z) {
            return;
        }
        this.v = z;
        k0();
    }

    @Override // androidx.recyclerview.widget.k
    public final void b0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.z = (SavedState) parcelable;
            k0();
        }
    }

    public final void b1(int i, int i2, boolean z, yd0 yd0Var) {
        int iK;
        this.q.l = this.r.i() == 0 && this.r.f() == 0;
        this.q.f = i;
        int[] iArr = this.D;
        iArr[0] = 0;
        iArr[1] = 0;
        z0(yd0Var, iArr);
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
            View viewQ0 = Q0();
            b bVar2 = this.q;
            bVar2.e = this.u ? -1 : 1;
            int iF = k.F(viewQ0);
            b bVar3 = this.q;
            bVar2.d = iF + bVar3.e;
            bVar3.b = this.r.b(viewQ0);
            iK = this.r.b(viewQ0) - this.r.g();
        } else {
            View viewR0 = R0();
            b bVar4 = this.q;
            bVar4.h = this.r.k() + bVar4.h;
            b bVar5 = this.q;
            bVar5.e = this.u ? 1 : -1;
            int iF2 = k.F(viewR0);
            b bVar6 = this.q;
            bVar5.d = iF2 + bVar6.e;
            bVar6.b = this.r.e(viewR0);
            iK = (-this.r.e(viewR0)) + this.r.k();
        }
        b bVar7 = this.q;
        bVar7.c = i2;
        if (z) {
            bVar7.c = i2 - iK;
        }
        bVar7.g = iK;
    }

    @Override // androidx.recyclerview.widget.k
    public final void c(String str) {
        if (this.z == null) {
            super.c(str);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final Parcelable c0() {
        SavedState savedState = this.z;
        if (savedState != null) {
            SavedState savedState2 = new SavedState();
            savedState2.b = savedState.b;
            savedState2.c = savedState.c;
            savedState2.d = savedState.d;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        if (v() <= 0) {
            savedState3.b = -1;
            return savedState3;
        }
        F0();
        boolean z = this.s ^ this.u;
        savedState3.d = z;
        if (z) {
            View viewQ0 = Q0();
            savedState3.c = this.r.g() - this.r.b(viewQ0);
            savedState3.b = k.F(viewQ0);
            return savedState3;
        }
        View viewR0 = R0();
        savedState3.b = k.F(viewR0);
        savedState3.c = this.r.e(viewR0) - this.r.k();
        return savedState3;
    }

    public final void c1(int i, int i2) {
        this.q.c = this.r.g() - i2;
        b bVar = this.q;
        bVar.e = this.u ? -1 : 1;
        bVar.d = i;
        bVar.f = 1;
        bVar.b = i2;
        bVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean d() {
        return this.p == 0;
    }

    public final void d1(int i, int i2) {
        this.q.c = i2 - this.r.k();
        b bVar = this.q;
        bVar.d = i;
        bVar.e = this.u ? 1 : -1;
        bVar.f = -1;
        bVar.b = i2;
        bVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean e() {
        return this.p == 1;
    }

    @Override // androidx.recyclerview.widget.k
    public final void h(int i, int i2, yd0 yd0Var, zu zuVar) {
        if (this.p != 0) {
            i = i2;
        }
        if (v() == 0 || i == 0) {
            return;
        }
        F0();
        b1(i > 0 ? 1 : -1, Math.abs(i), true, yd0Var);
        A0(yd0Var, this.q, zuVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final void i(int i, zu zuVar) {
        boolean z;
        int i2;
        SavedState savedState = this.z;
        if (savedState == null || (i2 = savedState.b) < 0) {
            X0();
            z = this.u;
            i2 = this.x;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = savedState.d;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.C && i2 >= 0 && i2 < i; i4++) {
            zuVar.b(i2, 0);
            i2 += i3;
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final int j(yd0 yd0Var) {
        return B0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int k(yd0 yd0Var) {
        return C0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int l(yd0 yd0Var) {
        return D0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int l0(int i, yd0 yd0Var, l lVar) {
        if (this.p == 1) {
            return 0;
        }
        return Y0(i, yd0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final int m(yd0 yd0Var) {
        return B0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final void m0(int i) {
        this.x = i;
        this.y = Integer.MIN_VALUE;
        SavedState savedState = this.z;
        if (savedState != null) {
            savedState.b = -1;
        }
        k0();
    }

    @Override // androidx.recyclerview.widget.k
    public int n(yd0 yd0Var) {
        return C0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public int n0(int i, yd0 yd0Var, l lVar) {
        if (this.p == 0) {
            return 0;
        }
        return Y0(i, yd0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.k
    public int o(yd0 yd0Var) {
        return D0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final View q(int i) {
        int iV = v();
        if (iV == 0) {
            return null;
        }
        int iF = i - k.F(u(0));
        if (iF >= 0 && iF < iV) {
            View viewU = u(iF);
            if (k.F(viewU) == i) {
                return viewU;
            }
        }
        return super.q(i);
    }

    @Override // androidx.recyclerview.widget.k
    public RecyclerView.LayoutParams r() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean u0() {
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
    public void w0(RecyclerView recyclerView, int i) {
        d dVar = new d(recyclerView.getContext());
        dVar.a = i;
        x0(dVar);
    }

    @Override // androidx.recyclerview.widget.k
    public boolean y0() {
        return this.z == null && this.s == this.v;
    }

    public void z0(yd0 yd0Var, int[] iArr) {
        int i;
        int iL = yd0Var.a != -1 ? this.r.l() : 0;
        if (this.q.f == -1) {
            i = 0;
        } else {
            i = iL;
            iL = 0;
        }
        iArr[0] = iL;
        iArr[1] = i;
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
        this.B = new d20();
        this.C = 2;
        this.D = new int[2];
        md0 md0VarG = k.G(context, attributeSet, i, i2);
        Z0(md0VarG.a);
        boolean z = md0VarG.c;
        c(null);
        if (z != this.t) {
            this.t = z;
            k0();
        }
        a1(md0VarG.d);
    }

    @Override // androidx.recyclerview.widget.k
    public final void O(RecyclerView recyclerView) {
    }

    public void U0(l lVar, yd0 yd0Var, a aVar, int i) {
    }
}
