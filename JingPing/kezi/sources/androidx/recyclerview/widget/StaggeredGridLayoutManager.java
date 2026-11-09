package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.en;
import defpackage.h10;
import defpackage.hk0;
import defpackage.md0;
import defpackage.n0;
import defpackage.o0;
import defpackage.v6;
import defpackage.wd0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.yd0;
import defpackage.zu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends k implements wd0 {
    public final p B;
    public final int C;
    public boolean D;
    public boolean E;
    public SavedState F;
    public final Rect G;
    public final hk0 H;
    public final boolean I;
    public int[] J;
    public final v6 K;
    public final int p;
    public final r[] q;
    public final en r;
    public final en s;
    public final int t;
    public int u;
    public final h10 v;
    public boolean w;
    public final BitSet y;
    public boolean x = false;
    public int z = -1;
    public int A = Integer.MIN_VALUE;

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public r e;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new q();
        public int b;
        public int c;
        public int d;
        public int[] e;
        public int f;
        public int[] g;
        public ArrayList h;
        public boolean i;
        public boolean j;
        public boolean k;

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            if (this.d > 0) {
                parcel.writeIntArray(this.e);
            }
            parcel.writeInt(this.f);
            if (this.f > 0) {
                parcel.writeIntArray(this.g);
            }
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeList(this.h);
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.p = -1;
        this.w = false;
        p pVar = new p();
        this.B = pVar;
        this.C = 2;
        this.G = new Rect();
        this.H = new hk0(this);
        this.I = true;
        this.K = new v6(15, this);
        md0 md0VarG = k.G(context, attributeSet, i, i2);
        int i3 = md0VarG.a;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        c(null);
        if (i3 != this.t) {
            this.t = i3;
            en enVar = this.r;
            this.r = this.s;
            this.s = enVar;
            k0();
        }
        int i4 = md0VarG.b;
        c(null);
        if (i4 != this.p) {
            int[] iArr = pVar.a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            pVar.b = null;
            k0();
            this.p = i4;
            this.y = new BitSet(this.p);
            this.q = new r[this.p];
            for (int i5 = 0; i5 < this.p; i5++) {
                this.q[i5] = new r(this, i5);
            }
            k0();
        }
        boolean z = md0VarG.c;
        c(null);
        SavedState savedState = this.F;
        if (savedState != null && savedState.i != z) {
            savedState.i = z;
        }
        this.w = z;
        k0();
        h10 h10Var = new h10();
        h10Var.a = true;
        h10Var.f = 0;
        h10Var.g = 0;
        this.v = h10Var;
        this.r = en.a(this, this.t);
        this.s = en.a(this, 1 - this.t);
    }

    public static int b1(int i, int i2, int i3) {
        int mode;
        return (!(i2 == 0 && i3 == 0) && ((mode = View.MeasureSpec.getMode(i)) == Integer.MIN_VALUE || mode == 1073741824)) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    public final int A0(yd0 yd0Var) {
        if (v() == 0) {
            return 0;
        }
        en enVar = this.r;
        boolean z = !this.I;
        return xi.i(yd0Var, enVar, F0(z), E0(z), this, this.I);
    }

    public final int B0(yd0 yd0Var) {
        if (v() == 0) {
            return 0;
        }
        en enVar = this.r;
        boolean z = !this.I;
        return xi.j(yd0Var, enVar, F0(z), E0(z), this, this.I, this.x);
    }

    public final int C0(yd0 yd0Var) {
        if (v() == 0) {
            return 0;
        }
        en enVar = this.r;
        boolean z = !this.I;
        return xi.k(yd0Var, enVar, F0(z), E0(z), this, this.I);
    }

    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean, int] */
    public final int D0(l lVar, h10 h10Var, yd0 yd0Var) {
        r rVar;
        ?? r6;
        int i;
        int iH;
        int iC;
        int iK;
        int iC2;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 1;
        this.y.set(0, this.p, true);
        h10 h10Var2 = this.v;
        int i7 = h10Var2.i ? h10Var.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : h10Var.e == 1 ? h10Var.g + h10Var.b : h10Var.f - h10Var.b;
        int i8 = h10Var.e;
        for (int i9 = 0; i9 < this.p; i9++) {
            if (!this.q[i9].a.isEmpty()) {
                a1(this.q[i9], i8, i7);
            }
        }
        int iG = this.x ? this.r.g() : this.r.k();
        boolean z = false;
        while (true) {
            int i10 = h10Var.c;
            if (((i10 < 0 || i10 >= yd0Var.b()) ? i5 : i6) == 0 || (!h10Var2.i && this.y.isEmpty())) {
                break;
            }
            View view = lVar.i(Long.MAX_VALUE, h10Var.c).a;
            h10Var.c += h10Var.d;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int iB = layoutParams.a.b();
            p pVar = this.B;
            int[] iArr = pVar.a;
            int i11 = (iArr == null || iB >= iArr.length) ? -1 : iArr[iB];
            if (i11 == -1) {
                if (R0(h10Var.e)) {
                    i4 = this.p - i6;
                    i3 = -1;
                    i2 = -1;
                } else {
                    i2 = i6;
                    i3 = this.p;
                    i4 = i5;
                }
                r rVar2 = null;
                if (h10Var.e == i6) {
                    int iK2 = this.r.k();
                    int i12 = Integer.MAX_VALUE;
                    while (i4 != i3) {
                        r rVar3 = this.q[i4];
                        int iF = rVar3.f(iK2);
                        if (iF < i12) {
                            i12 = iF;
                            rVar2 = rVar3;
                        }
                        i4 += i2;
                    }
                } else {
                    int iG2 = this.r.g();
                    int i13 = Integer.MIN_VALUE;
                    while (i4 != i3) {
                        r rVar4 = this.q[i4];
                        int iH2 = rVar4.h(iG2);
                        if (iH2 > i13) {
                            rVar2 = rVar4;
                            i13 = iH2;
                        }
                        i4 += i2;
                    }
                }
                rVar = rVar2;
                pVar.a(iB);
                pVar.a[iB] = rVar.e;
            } else {
                rVar = this.q[i11];
            }
            layoutParams.e = rVar;
            if (h10Var.e == 1) {
                r6 = 0;
                b(view, -1, false);
            } else {
                r6 = 0;
                b(view, 0, false);
            }
            if (this.t == 1) {
                i = 1;
                P0(view, k.w(r6, this.u, this.l, r6, ((ViewGroup.MarginLayoutParams) layoutParams).width), k.w(true, this.o, this.m, B() + E(), ((ViewGroup.MarginLayoutParams) layoutParams).height));
            } else {
                i = 1;
                P0(view, k.w(true, this.n, this.l, D() + C(), ((ViewGroup.MarginLayoutParams) layoutParams).width), k.w(false, this.u, this.m, 0, ((ViewGroup.MarginLayoutParams) layoutParams).height));
            }
            if (h10Var.e == i) {
                iC = rVar.f(iG);
                iH = this.r.c(view) + iC;
            } else {
                iH = rVar.h(iG);
                iC = iH - this.r.c(view);
            }
            if (h10Var.e == 1) {
                r rVar5 = layoutParams.e;
                rVar5.getClass();
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                layoutParams2.e = rVar5;
                ArrayList arrayList = rVar5.a;
                arrayList.add(view);
                rVar5.c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    rVar5.b = Integer.MIN_VALUE;
                }
                if (layoutParams2.a.h() || layoutParams2.a.k()) {
                    rVar5.d = rVar5.f.r.c(view) + rVar5.d;
                }
            } else {
                r rVar6 = layoutParams.e;
                rVar6.getClass();
                LayoutParams layoutParams3 = (LayoutParams) view.getLayoutParams();
                layoutParams3.e = rVar6;
                ArrayList arrayList2 = rVar6.a;
                arrayList2.add(0, view);
                rVar6.b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    rVar6.c = Integer.MIN_VALUE;
                }
                if (layoutParams3.a.h() || layoutParams3.a.k()) {
                    rVar6.d = rVar6.f.r.c(view) + rVar6.d;
                }
            }
            if (O0() && this.t == 1) {
                iC2 = this.s.g() - (((this.p - 1) - rVar.e) * this.u);
                iK = iC2 - this.s.c(view);
            } else {
                iK = this.s.k() + (rVar.e * this.u);
                iC2 = this.s.c(view) + iK;
            }
            if (this.t == 1) {
                k.L(view, iK, iC, iC2, iH);
            } else {
                k.L(view, iC, iK, iH, iC2);
            }
            a1(rVar, h10Var2.e, i7);
            T0(lVar, h10Var2);
            if (h10Var2.h && view.hasFocusable()) {
                this.y.set(rVar.e, false);
            }
            i6 = 1;
            z = true;
            i5 = 0;
        }
        if (!z) {
            T0(lVar, h10Var2);
        }
        int iK3 = h10Var2.e == -1 ? this.r.k() - L0(this.r.k()) : K0(this.r.g()) - this.r.g();
        if (iK3 > 0) {
            return Math.min(h10Var.b, iK3);
        }
        return 0;
    }

    public final View E0(boolean z) {
        int iK = this.r.k();
        int iG = this.r.g();
        View view = null;
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            int iE = this.r.e(viewU);
            int iB = this.r.b(viewU);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z) {
                    return viewU;
                }
                if (view == null) {
                    view = viewU;
                }
            }
        }
        return view;
    }

    public final View F0(boolean z) {
        int iK = this.r.k();
        int iG = this.r.g();
        int iV = v();
        View view = null;
        for (int i = 0; i < iV; i++) {
            View viewU = u(i);
            int iE = this.r.e(viewU);
            if (this.r.b(viewU) > iK && iE < iG) {
                if (iE >= iK || !z) {
                    return viewU;
                }
                if (view == null) {
                    view = viewU;
                }
            }
        }
        return view;
    }

    public final void G0(l lVar, yd0 yd0Var, boolean z) {
        int iG;
        int iK0 = K0(Integer.MIN_VALUE);
        if (iK0 != Integer.MIN_VALUE && (iG = this.r.g() - iK0) > 0) {
            int i = iG - (-X0(-iG, yd0Var, lVar));
            if (!z || i <= 0) {
                return;
            }
            this.r.o(i);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final int H(l lVar, yd0 yd0Var) {
        return this.t == 0 ? this.p : super.H(lVar, yd0Var);
    }

    public final void H0(l lVar, yd0 yd0Var, boolean z) {
        int iK;
        int iL0 = L0(Integer.MAX_VALUE);
        if (iL0 != Integer.MAX_VALUE && (iK = iL0 - this.r.k()) > 0) {
            int iX0 = iK - X0(iK, yd0Var, lVar);
            if (!z || iX0 <= 0) {
                return;
            }
            this.r.o(-iX0);
        }
    }

    public final int I0() {
        if (v() == 0) {
            return 0;
        }
        return k.F(u(0));
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean J() {
        return this.C != 0;
    }

    public final int J0() {
        int iV = v();
        if (iV == 0) {
            return 0;
        }
        return k.F(u(iV - 1));
    }

    public final int K0(int i) {
        int iF = this.q[0].f(i);
        for (int i2 = 1; i2 < this.p; i2++) {
            int iF2 = this.q[i2].f(i);
            if (iF2 > iF) {
                iF = iF2;
            }
        }
        return iF;
    }

    public final int L0(int i) {
        int iH = this.q[0].h(i);
        for (int i2 = 1; i2 < this.p; i2++) {
            int iH2 = this.q[i2].h(i);
            if (iH2 < iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    @Override // androidx.recyclerview.widget.k
    public final void M(int i) {
        super.M(i);
        for (int i2 = 0; i2 < this.p; i2++) {
            r rVar = this.q[i2];
            int i3 = rVar.b;
            if (i3 != Integer.MIN_VALUE) {
                rVar.b = i3 + i;
            }
            int i4 = rVar.c;
            if (i4 != Integer.MIN_VALUE) {
                rVar.c = i4 + i;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x006e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void M0(int r10, int r11, int r12) {
        /*
            r9 = this;
            boolean r0 = r9.x
            if (r0 == 0) goto L9
            int r0 = r9.J0()
            goto Ld
        L9:
            int r0 = r9.I0()
        Ld:
            r1 = 8
            if (r12 != r1) goto L1b
            if (r10 >= r11) goto L17
            int r2 = r11 + 1
        L15:
            r3 = r10
            goto L1e
        L17:
            int r2 = r10 + 1
            r3 = r11
            goto L1e
        L1b:
            int r2 = r10 + r11
            goto L15
        L1e:
            androidx.recyclerview.widget.p r4 = r9.B
            int[] r5 = r4.a
            r6 = -1
            if (r5 != 0) goto L27
            goto L93
        L27:
            int r5 = r5.length
            if (r3 < r5) goto L2c
            goto L93
        L2c:
            java.util.ArrayList r5 = r4.b
            if (r5 != 0) goto L32
        L30:
            r5 = r6
            goto L80
        L32:
            if (r5 != 0) goto L35
            goto L4d
        L35:
            int r5 = r5.size()
            int r5 = r5 + (-1)
        L3b:
            if (r5 < 0) goto L4d
            java.util.ArrayList r7 = r4.b
            java.lang.Object r7 = r7.get(r5)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r7 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r7
            int r8 = r7.b
            if (r8 != r3) goto L4a
            goto L4e
        L4a:
            int r5 = r5 + (-1)
            goto L3b
        L4d:
            r7 = 0
        L4e:
            if (r7 == 0) goto L55
            java.util.ArrayList r5 = r4.b
            r5.remove(r7)
        L55:
            java.util.ArrayList r5 = r4.b
            int r5 = r5.size()
            r7 = 0
        L5c:
            if (r7 >= r5) goto L6e
            java.util.ArrayList r8 = r4.b
            java.lang.Object r8 = r8.get(r7)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r8
            int r8 = r8.b
            if (r8 < r3) goto L6b
            goto L6f
        L6b:
            int r7 = r7 + 1
            goto L5c
        L6e:
            r7 = r6
        L6f:
            if (r7 == r6) goto L30
            java.util.ArrayList r5 = r4.b
            java.lang.Object r5 = r5.get(r7)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r5 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r5
            java.util.ArrayList r8 = r4.b
            r8.remove(r7)
            int r5 = r5.b
        L80:
            if (r5 != r6) goto L8c
            int[] r5 = r4.a
            int r7 = r5.length
            java.util.Arrays.fill(r5, r3, r7, r6)
            int[] r5 = r4.a
            int r5 = r5.length
            goto L93
        L8c:
            int[] r7 = r4.a
            int r5 = r5 + 1
            java.util.Arrays.fill(r7, r3, r5, r6)
        L93:
            r5 = 1
            if (r12 == r5) goto La7
            r6 = 2
            if (r12 == r6) goto La3
            if (r12 == r1) goto L9c
            goto Laa
        L9c:
            r4.c(r10, r5)
            r4.b(r11, r5)
            goto Laa
        La3:
            r4.c(r10, r11)
            goto Laa
        La7:
            r4.b(r10, r11)
        Laa:
            if (r2 > r0) goto Lad
            goto Lbf
        Lad:
            boolean r10 = r9.x
            if (r10 == 0) goto Lb6
            int r10 = r9.I0()
            goto Lba
        Lb6:
            int r10 = r9.J0()
        Lba:
            if (r3 > r10) goto Lbf
            r9.k0()
        Lbf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.M0(int, int, int):void");
    }

    @Override // androidx.recyclerview.widget.k
    public final void N(int i) {
        super.N(i);
        for (int i2 = 0; i2 < this.p; i2++) {
            r rVar = this.q[i2];
            int i3 = rVar.b;
            if (i3 != Integer.MIN_VALUE) {
                rVar.b = i3 + i;
            }
            int i4 = rVar.c;
            if (i4 != Integer.MIN_VALUE) {
                rVar.c = i4 + i;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View N0() {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.N0():android.view.View");
    }

    @Override // androidx.recyclerview.widget.k
    public final void O(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.K);
        }
        for (int i = 0; i < this.p; i++) {
            this.q[i].b();
        }
        recyclerView.requestLayout();
    }

    public final boolean O0() {
        return A() == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0053  */
    @Override // androidx.recyclerview.widget.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View P(android.view.View r9, int r10, androidx.recyclerview.widget.l r11, defpackage.yd0 r12) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.P(android.view.View, int, androidx.recyclerview.widget.l, yd0):android.view.View");
    }

    public final void P0(View view, int i, int i2) {
        RecyclerView recyclerView = this.b;
        Rect rect = this.G;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.J(view));
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int iB1 = b1(i, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int iB12 = b1(i2, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect.bottom);
        if (t0(view, iB1, iB12, layoutParams)) {
            view.measure(iB1, iB12);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final void Q(AccessibilityEvent accessibilityEvent) {
        super.Q(accessibilityEvent);
        if (v() > 0) {
            View viewF0 = F0(false);
            View viewE0 = E0(false);
            if (viewF0 == null || viewE0 == null) {
                return;
            }
            int iF = k.F(viewF0);
            int iF2 = k.F(viewE0);
            if (iF < iF2) {
                accessibilityEvent.setFromIndex(iF);
                accessibilityEvent.setToIndex(iF2);
            } else {
                accessibilityEvent.setFromIndex(iF2);
                accessibilityEvent.setToIndex(iF);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x041d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Q0(androidx.recyclerview.widget.l r17, defpackage.yd0 r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 1080
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.Q0(androidx.recyclerview.widget.l, yd0, boolean):void");
    }

    public final boolean R0(int i) {
        if (this.t == 0) {
            return (i == -1) != this.x;
        }
        return ((i == -1) == this.x) == O0();
    }

    public final void S0(int i, yd0 yd0Var) {
        int iI0;
        int i2;
        if (i > 0) {
            iI0 = J0();
            i2 = 1;
        } else {
            iI0 = I0();
            i2 = -1;
        }
        h10 h10Var = this.v;
        h10Var.a = true;
        Z0(iI0, yd0Var);
        Y0(i2);
        h10Var.c = iI0 + h10Var.d;
        h10Var.b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.k
    public final void T(l lVar, yd0 yd0Var, View view, o0 o0Var) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            S(view, o0Var);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.t == 0) {
            r rVar = layoutParams2.e;
            o0Var.j(n0.a(rVar != null ? rVar.e : -1, 1, -1, -1, false, false));
        } else {
            r rVar2 = layoutParams2.e;
            o0Var.j(n0.a(-1, -1, rVar2 != null ? rVar2.e : -1, 1, false, false));
        }
    }

    public final void T0(l lVar, h10 h10Var) {
        if (!h10Var.a || h10Var.i) {
            return;
        }
        if (h10Var.b == 0) {
            if (h10Var.e == -1) {
                U0(lVar, h10Var.g);
                return;
            } else {
                V0(lVar, h10Var.f);
                return;
            }
        }
        int i = 1;
        if (h10Var.e == -1) {
            int i2 = h10Var.f;
            int iH = this.q[0].h(i2);
            while (i < this.p) {
                int iH2 = this.q[i].h(i2);
                if (iH2 > iH) {
                    iH = iH2;
                }
                i++;
            }
            int i3 = i2 - iH;
            U0(lVar, i3 < 0 ? h10Var.g : h10Var.g - Math.min(i3, h10Var.b));
            return;
        }
        int i4 = h10Var.g;
        int iF = this.q[0].f(i4);
        while (i < this.p) {
            int iF2 = this.q[i].f(i4);
            if (iF2 < iF) {
                iF = iF2;
            }
            i++;
        }
        int i5 = iF - h10Var.g;
        V0(lVar, i5 < 0 ? h10Var.f : Math.min(i5, h10Var.b) + h10Var.f);
    }

    @Override // androidx.recyclerview.widget.k
    public final void U(int i, int i2) {
        M0(i, i2, 1);
    }

    public final void U0(l lVar, int i) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            if (this.r.e(viewU) < i || this.r.n(viewU) < i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) viewU.getLayoutParams();
            layoutParams.getClass();
            if (layoutParams.e.a.size() == 1) {
                return;
            }
            r rVar = layoutParams.e;
            ArrayList arrayList = rVar.a;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.e = null;
            if (layoutParams2.a.h() || layoutParams2.a.k()) {
                rVar.d -= rVar.f.r.c(view);
            }
            if (size == 1) {
                rVar.b = Integer.MIN_VALUE;
            }
            rVar.c = Integer.MIN_VALUE;
            h0(viewU, lVar);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final void V() {
        p pVar = this.B;
        int[] iArr = pVar.a;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        pVar.b = null;
        k0();
    }

    public final void V0(l lVar, int i) {
        while (v() > 0) {
            View viewU = u(0);
            if (this.r.b(viewU) > i || this.r.m(viewU) > i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) viewU.getLayoutParams();
            layoutParams.getClass();
            if (layoutParams.e.a.size() == 1) {
                return;
            }
            r rVar = layoutParams.e;
            ArrayList arrayList = rVar.a;
            View view = (View) arrayList.remove(0);
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.e = null;
            if (arrayList.size() == 0) {
                rVar.c = Integer.MIN_VALUE;
            }
            if (layoutParams2.a.h() || layoutParams2.a.k()) {
                rVar.d -= rVar.f.r.c(view);
            }
            rVar.b = Integer.MIN_VALUE;
            h0(viewU, lVar);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final void W(int i, int i2) {
        M0(i, i2, 8);
    }

    public final void W0() {
        if (this.t == 1 || !O0()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final void X(int i, int i2) {
        M0(i, i2, 2);
    }

    public final int X0(int i, yd0 yd0Var, l lVar) {
        if (v() == 0 || i == 0) {
            return 0;
        }
        S0(i, yd0Var);
        h10 h10Var = this.v;
        int iD0 = D0(lVar, h10Var, yd0Var);
        if (h10Var.b >= iD0) {
            i = i < 0 ? -iD0 : iD0;
        }
        this.r.o(-i);
        this.D = this.x;
        h10Var.b = 0;
        T0(lVar, h10Var);
        return i;
    }

    @Override // androidx.recyclerview.widget.k
    public final void Y(int i, int i2) {
        M0(i, i2, 4);
    }

    public final void Y0(int i) {
        h10 h10Var = this.v;
        h10Var.e = i;
        h10Var.d = this.x != (i == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.k
    public final void Z(l lVar, yd0 yd0Var) {
        Q0(lVar, yd0Var, true);
    }

    public final void Z0(int i, yd0 yd0Var) {
        int iL;
        int iL2;
        int i2;
        h10 h10Var = this.v;
        boolean z = false;
        h10Var.b = 0;
        h10Var.c = i;
        d dVar = this.e;
        if (!(dVar != null && dVar.e) || (i2 = yd0Var.a) == -1) {
            iL = 0;
            iL2 = 0;
        } else {
            if (this.x == (i2 < i)) {
                iL = this.r.l();
                iL2 = 0;
            } else {
                iL2 = this.r.l();
                iL = 0;
            }
        }
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || !recyclerView.h) {
            h10Var.g = this.r.f() + iL;
            h10Var.f = -iL2;
        } else {
            h10Var.f = this.r.k() - iL2;
            h10Var.g = this.r.g() + iL;
        }
        h10Var.h = false;
        h10Var.a = true;
        if (this.r.i() == 0 && this.r.f() == 0) {
            z = true;
        }
        h10Var.i = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000c  */
    @Override // defpackage.wd0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.PointF a(int r4) {
        /*
            r3 = this;
            int r0 = r3.v()
            r1 = -1
            r2 = 1
            if (r0 != 0) goto Le
            boolean r4 = r3.x
            if (r4 == 0) goto L1b
        Lc:
            r1 = r2
            goto L1b
        Le:
            int r0 = r3.I0()
            if (r4 >= r0) goto L16
            r4 = r2
            goto L17
        L16:
            r4 = 0
        L17:
            boolean r0 = r3.x
            if (r4 == r0) goto Lc
        L1b:
            android.graphics.PointF r4 = new android.graphics.PointF
            r4.<init>()
            if (r1 != 0) goto L24
            r4 = 0
            return r4
        L24:
            int r0 = r3.t
            r2 = 0
            if (r0 != 0) goto L2f
            float r0 = (float) r1
            r4.x = r0
            r4.y = r2
            return r4
        L2f:
            r4.x = r2
            float r0 = (float) r1
            r4.y = r0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.a(int):android.graphics.PointF");
    }

    @Override // androidx.recyclerview.widget.k
    public final void a0(yd0 yd0Var) {
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.F = null;
        this.H.a();
    }

    public final void a1(r rVar, int i, int i2) {
        int i3 = rVar.d;
        int i4 = rVar.e;
        if (i != -1) {
            int i5 = rVar.c;
            if (i5 == Integer.MIN_VALUE) {
                rVar.a();
                i5 = rVar.c;
            }
            if (i5 - i3 >= i2) {
                this.y.set(i4, false);
                return;
            }
            return;
        }
        int i6 = rVar.b;
        if (i6 == Integer.MIN_VALUE) {
            View view = (View) rVar.a.get(0);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            rVar.b = rVar.f.r.e(view);
            layoutParams.getClass();
            i6 = rVar.b;
        }
        if (i6 + i3 <= i2) {
            this.y.set(i4, false);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final void b0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.F = (SavedState) parcelable;
            k0();
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final void c(String str) {
        if (this.F == null) {
            super.c(str);
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final Parcelable c0() {
        int iH;
        int iK;
        int[] iArr;
        SavedState savedState = this.F;
        if (savedState != null) {
            SavedState savedState2 = new SavedState();
            savedState2.d = savedState.d;
            savedState2.b = savedState.b;
            savedState2.c = savedState.c;
            savedState2.e = savedState.e;
            savedState2.f = savedState.f;
            savedState2.g = savedState.g;
            savedState2.i = savedState.i;
            savedState2.j = savedState.j;
            savedState2.k = savedState.k;
            savedState2.h = savedState.h;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        savedState3.i = this.w;
        savedState3.j = this.D;
        savedState3.k = this.E;
        p pVar = this.B;
        if (pVar == null || (iArr = pVar.a) == null) {
            savedState3.f = 0;
        } else {
            savedState3.g = iArr;
            savedState3.f = iArr.length;
            savedState3.h = pVar.b;
        }
        if (v() <= 0) {
            savedState3.b = -1;
            savedState3.c = -1;
            savedState3.d = 0;
            return savedState3;
        }
        savedState3.b = this.D ? J0() : I0();
        View viewE0 = this.x ? E0(true) : F0(true);
        savedState3.c = viewE0 != null ? k.F(viewE0) : -1;
        int i = this.p;
        savedState3.d = i;
        savedState3.e = new int[i];
        for (int i2 = 0; i2 < this.p; i2++) {
            if (this.D) {
                iH = this.q[i2].f(Integer.MIN_VALUE);
                if (iH != Integer.MIN_VALUE) {
                    iK = this.r.g();
                    iH -= iK;
                }
            } else {
                iH = this.q[i2].h(Integer.MIN_VALUE);
                if (iH != Integer.MIN_VALUE) {
                    iK = this.r.k();
                    iH -= iK;
                }
            }
            savedState3.e[i2] = iH;
        }
        return savedState3;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean d() {
        return this.t == 0;
    }

    @Override // androidx.recyclerview.widget.k
    public final void d0(int i) {
        if (i == 0) {
            z0();
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean e() {
        return this.t == 1;
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean f(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.k
    public final void h(int i, int i2, yd0 yd0Var, zu zuVar) {
        h10 h10Var;
        int iF;
        int iH;
        if (this.t != 0) {
            i = i2;
        }
        if (v() == 0 || i == 0) {
            return;
        }
        S0(i, yd0Var);
        int[] iArr = this.J;
        if (iArr == null || iArr.length < this.p) {
            this.J = new int[this.p];
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = this.p;
            h10Var = this.v;
            if (i3 >= i5) {
                break;
            }
            if (h10Var.d == -1) {
                iF = h10Var.f;
                iH = this.q[i3].h(iF);
            } else {
                iF = this.q[i3].f(h10Var.g);
                iH = h10Var.g;
            }
            int i6 = iF - iH;
            if (i6 >= 0) {
                this.J[i4] = i6;
                i4++;
            }
            i3++;
        }
        Arrays.sort(this.J, 0, i4);
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = h10Var.c;
            if (i8 < 0 || i8 >= yd0Var.b()) {
                return;
            }
            zuVar.b(h10Var.c, this.J[i7]);
            h10Var.c += h10Var.d;
        }
    }

    @Override // androidx.recyclerview.widget.k
    public final int j(yd0 yd0Var) {
        return A0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final int k(yd0 yd0Var) {
        return B0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final int l(yd0 yd0Var) {
        return C0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final int l0(int i, yd0 yd0Var, l lVar) {
        return X0(i, yd0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final int m(yd0 yd0Var) {
        return A0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final void m0(int i) {
        SavedState savedState = this.F;
        if (savedState != null && savedState.b != i) {
            savedState.e = null;
            savedState.d = 0;
            savedState.b = -1;
            savedState.c = -1;
        }
        this.z = i;
        this.A = Integer.MIN_VALUE;
        k0();
    }

    @Override // androidx.recyclerview.widget.k
    public final int n(yd0 yd0Var) {
        return B0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final int n0(int i, yd0 yd0Var, l lVar) {
        return X0(i, yd0Var, lVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final int o(yd0 yd0Var) {
        return C0(yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final void q0(Rect rect, int i, int i2) {
        int iG;
        int iG2;
        int i3 = this.p;
        int iD = D() + C();
        int iB = B() + E();
        if (this.t == 1) {
            int iHeight = rect.height() + iB;
            RecyclerView recyclerView = this.b;
            WeakHashMap weakHashMap = xr0.a;
            iG2 = k.g(i2, iHeight, recyclerView.getMinimumHeight());
            iG = k.g(i, (this.u * i3) + iD, this.b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iD;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap weakHashMap2 = xr0.a;
            iG = k.g(i, iWidth, recyclerView2.getMinimumWidth());
            iG2 = k.g(i2, (this.u * i3) + iB, this.b.getMinimumHeight());
        }
        this.b.setMeasuredDimension(iG, iG2);
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams r() {
        return this.t == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams s(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.k
    public final RecyclerView.LayoutParams t(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.k
    public final void w0(RecyclerView recyclerView, int i) {
        d dVar = new d(recyclerView.getContext());
        dVar.a = i;
        x0(dVar);
    }

    @Override // androidx.recyclerview.widget.k
    public final int x(l lVar, yd0 yd0Var) {
        return this.t == 1 ? this.p : super.x(lVar, yd0Var);
    }

    @Override // androidx.recyclerview.widget.k
    public final boolean y0() {
        return this.F == null;
    }

    public final boolean z0() {
        int iI0;
        if (v() != 0 && this.C != 0 && this.g) {
            if (this.x) {
                iI0 = J0();
                I0();
            } else {
                iI0 = I0();
                J0();
            }
            p pVar = this.B;
            if (iI0 == 0 && N0() != null) {
                int[] iArr = pVar.a;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                pVar.b = null;
                this.f = true;
                k0();
                return true;
            }
        }
        return false;
    }
}
