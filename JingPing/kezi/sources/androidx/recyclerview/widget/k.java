package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ae0;
import defpackage.e5;
import defpackage.fc;
import defpackage.gd0;
import defpackage.ld0;
import defpackage.md0;
import defpackage.n0;
import defpackage.o0;
import defpackage.o70;
import defpackage.po;
import defpackage.xr0;
import defpackage.yd0;
import defpackage.zu;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public po a;
    public RecyclerView b;
    public final e5 c;
    public final e5 d;
    public d e;
    public boolean f;
    public boolean g;
    public final boolean h;
    public final boolean i;
    public int j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;

    public k() {
        i iVar = new i(this);
        j jVar = new j(this);
        this.c = new e5(iVar);
        this.d = new e5(jVar);
        this.f = false;
        this.g = false;
        this.h = true;
        this.i = true;
    }

    public static int F(View view) {
        return ((RecyclerView.LayoutParams) view.getLayoutParams()).a.b();
    }

    public static md0 G(Context context, AttributeSet attributeSet, int i, int i2) {
        md0 md0Var = new md0();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
        md0Var.a = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
        md0Var.b = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_spanCount, 1);
        md0Var.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_reverseLayout, false);
        md0Var.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_stackFromEnd, false);
        typedArrayObtainStyledAttributes.recycle();
        return md0Var;
    }

    public static boolean K(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    public static void L(View view, int i, int i2, int i3, int i4) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.b;
        view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    public static int g(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int w(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            int r5 = r5 - r7
            r7 = 0
            int r5 = java.lang.Math.max(r7, r5)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r4 == 0) goto L1d
            if (r8 < 0) goto L12
        L10:
            r6 = r3
            goto L30
        L12:
            if (r8 != r1) goto L1a
            if (r6 == r2) goto L22
            if (r6 == 0) goto L1a
            if (r6 == r3) goto L22
        L1a:
            r6 = r7
            r8 = r6
            goto L30
        L1d:
            if (r8 < 0) goto L20
            goto L10
        L20:
            if (r8 != r1) goto L24
        L22:
            r8 = r5
            goto L30
        L24:
            if (r8 != r0) goto L1a
            if (r6 == r2) goto L2e
            if (r6 != r3) goto L2b
            goto L2e
        L2b:
            r8 = r5
            r6 = r7
            goto L30
        L2e:
            r8 = r5
            r6 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.k.w(boolean, int, int, int, int):int");
    }

    public final int A() {
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = xr0.a;
        return recyclerView.getLayoutDirection();
    }

    public final int B() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public final int C() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public final int D() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public final int E() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int H(l lVar, yd0 yd0Var) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || recyclerView.l == null || !e()) {
            return 1;
        }
        return this.b.l.a();
    }

    public final void I(Rect rect, View view) {
        Matrix matrix;
        Rect rect2 = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.b.k;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public boolean J() {
        return false;
    }

    public void M(int i) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int iV = recyclerView.f.v();
            for (int i2 = 0; i2 < iV; i2++) {
                recyclerView.f.u(i2).offsetLeftAndRight(i);
            }
        }
    }

    public void N(int i) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int iV = recyclerView.f.v();
            for (int i2 = 0; i2 < iV; i2++) {
                recyclerView.f.u(i2).offsetTopAndBottom(i);
            }
        }
    }

    public View P(View view, int i, l lVar, yd0 yd0Var) {
        return null;
    }

    public void Q(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.b;
        l lVar = recyclerView.c;
        yd0 yd0Var = recyclerView.e0;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z = true;
        if (!recyclerView.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        g gVar = this.b.l;
        if (gVar != null) {
            accessibilityEvent.setItemCount(gVar.a());
        }
    }

    public void R(l lVar, yd0 yd0Var, o0 o0Var) {
        if (this.b.canScrollVertically(-1) || this.b.canScrollHorizontally(-1)) {
            o0Var.a(8192);
            o0Var.l(true);
        }
        if (this.b.canScrollVertically(1) || this.b.canScrollHorizontally(1)) {
            o0Var.a(4096);
            o0Var.l(true);
        }
        o0Var.a.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(H(lVar, yd0Var), x(lVar, yd0Var), 0).c);
    }

    public final void S(View view, o0 o0Var) {
        n nVarI = RecyclerView.I(view);
        if (nVarI == null || nVarI.h() || ((ArrayList) this.a.e).contains(nVarI.a)) {
            return;
        }
        RecyclerView recyclerView = this.b;
        T(recyclerView.c, recyclerView.e0, view, o0Var);
    }

    public void T(l lVar, yd0 yd0Var, View view, o0 o0Var) {
        o0Var.j(n0.a(e() ? F(view) : 0, 1, d() ? F(view) : 0, 1, false, false));
    }

    public abstract void Z(l lVar, yd0 yd0Var);

    public abstract void a0(yd0 yd0Var);

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.view.View r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.k.b(android.view.View, int, boolean):void");
    }

    public void b0(Parcelable parcelable) {
    }

    public void c(String str) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.h(str);
        }
    }

    public Parcelable c0() {
        return null;
    }

    public abstract boolean d();

    public void d0(int i) {
    }

    public abstract boolean e();

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f A[PHI: r2
      0x003f: PHI (r2v8 int) = (r2v4 int), (r2v12 int) binds: [B:23:0x005b, B:15:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean e0(androidx.recyclerview.widget.l r2, defpackage.yd0 r3, int r4, android.os.Bundle r5) {
        /*
            r1 = this;
            androidx.recyclerview.widget.RecyclerView r2 = r1.b
            r3 = 0
            if (r2 != 0) goto L7
            goto L6d
        L7:
            r5 = 4096(0x1000, float:5.74E-42)
            r0 = 1
            if (r4 == r5) goto L41
            r5 = 8192(0x2000, float:1.148E-41)
            if (r4 == r5) goto L13
            r2 = r3
            r4 = r2
            goto L69
        L13:
            r4 = -1
            boolean r2 = r2.canScrollVertically(r4)
            if (r2 == 0) goto L28
            int r2 = r1.o
            int r5 = r1.E()
            int r2 = r2 - r5
            int r5 = r1.B()
            int r2 = r2 - r5
            int r2 = -r2
            goto L29
        L28:
            r2 = r3
        L29:
            androidx.recyclerview.widget.RecyclerView r5 = r1.b
            boolean r4 = r5.canScrollHorizontally(r4)
            if (r4 == 0) goto L3f
            int r4 = r1.n
            int r5 = r1.C()
            int r4 = r4 - r5
            int r5 = r1.D()
            int r4 = r4 - r5
            int r4 = -r4
            goto L69
        L3f:
            r4 = r3
            goto L69
        L41:
            boolean r2 = r2.canScrollVertically(r0)
            if (r2 == 0) goto L54
            int r2 = r1.o
            int r4 = r1.E()
            int r2 = r2 - r4
            int r4 = r1.B()
            int r2 = r2 - r4
            goto L55
        L54:
            r2 = r3
        L55:
            androidx.recyclerview.widget.RecyclerView r4 = r1.b
            boolean r4 = r4.canScrollHorizontally(r0)
            if (r4 == 0) goto L3f
            int r4 = r1.n
            int r5 = r1.C()
            int r4 = r4 - r5
            int r5 = r1.D()
            int r4 = r4 - r5
        L69:
            if (r2 != 0) goto L6e
            if (r4 != 0) goto L6e
        L6d:
            return r3
        L6e:
            androidx.recyclerview.widget.RecyclerView r3 = r1.b
            r3.a0(r4, r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.k.e0(androidx.recyclerview.widget.l, yd0, int, android.os.Bundle):boolean");
    }

    public boolean f(RecyclerView.LayoutParams layoutParams) {
        return layoutParams != null;
    }

    public final void f0(l lVar) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            if (!RecyclerView.I(u(iV)).o()) {
                View viewU = u(iV);
                i0(iV);
                lVar.f(viewU);
            }
        }
    }

    public final void g0(l lVar) {
        ArrayList arrayList;
        int size = lVar.a.size();
        int i = size - 1;
        while (true) {
            arrayList = lVar.a;
            if (i < 0) {
                break;
            }
            View view = ((n) arrayList.get(i)).a;
            n nVarI = RecyclerView.I(view);
            if (!nVarI.o()) {
                nVarI.n(false);
                if (nVarI.j()) {
                    this.b.removeDetachedView(view, false);
                }
                ld0 ld0Var = this.b.J;
                if (ld0Var != null) {
                    ld0Var.d(nVarI);
                }
                nVarI.n(true);
                n nVarI2 = RecyclerView.I(view);
                nVarI2.n = null;
                nVarI2.o = false;
                nVarI2.j &= -33;
                lVar.g(nVarI2);
            }
            i--;
        }
        arrayList.clear();
        ArrayList arrayList2 = lVar.b;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.b.invalidate();
        }
    }

    public final void h0(View view, l lVar) {
        po poVar = this.a;
        gd0 gd0Var = (gd0) poVar.c;
        int iIndexOfChild = gd0Var.a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            if (((fc) poVar.d).g(iIndexOfChild)) {
                poVar.R(view);
            }
            gd0Var.h(iIndexOfChild);
        }
        lVar.f(view);
    }

    public final void i0(int i) {
        if (u(i) != null) {
            po poVar = this.a;
            int iD = poVar.D(i);
            gd0 gd0Var = (gd0) poVar.c;
            View childAt = gd0Var.a.getChildAt(iD);
            if (childAt == null) {
                return;
            }
            if (((fc) poVar.d).g(iD)) {
                poVar.R(childAt);
            }
            gd0Var.h(iD);
        }
    }

    public abstract int j(yd0 yd0Var);

    /* JADX WARN: Removed duplicated region for block: B:28:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean j0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.C()
            int r1 = r8.E()
            int r2 = r8.n
            int r3 = r8.D()
            int r2 = r2 - r3
            int r3 = r8.o
            int r4 = r8.B()
            int r3 = r3 - r4
            int r4 = r10.getLeft()
            int r5 = r11.left
            int r4 = r4 + r5
            int r5 = r10.getScrollX()
            int r4 = r4 - r5
            int r5 = r10.getTop()
            int r6 = r11.top
            int r5 = r5 + r6
            int r10 = r10.getScrollY()
            int r5 = r5 - r10
            int r10 = r11.width()
            int r10 = r10 + r4
            int r11 = r11.height()
            int r11 = r11 + r5
            int r4 = r4 - r0
            r0 = 0
            int r6 = java.lang.Math.min(r0, r4)
            int r5 = r5 - r1
            int r1 = java.lang.Math.min(r0, r5)
            int r10 = r10 - r2
            int r2 = java.lang.Math.max(r0, r10)
            int r11 = r11 - r3
            int r11 = java.lang.Math.max(r0, r11)
            int r3 = r8.A()
            r7 = 1
            if (r3 != r7) goto L5c
            if (r2 == 0) goto L57
            goto L64
        L57:
            int r2 = java.lang.Math.max(r6, r10)
            goto L64
        L5c:
            if (r6 == 0) goto L5f
            goto L63
        L5f:
            int r6 = java.lang.Math.min(r4, r2)
        L63:
            r2 = r6
        L64:
            if (r1 == 0) goto L67
            goto L6b
        L67:
            int r1 = java.lang.Math.min(r5, r11)
        L6b:
            int[] r10 = new int[]{r2, r1}
            r11 = r10[r0]
            r10 = r10[r7]
            if (r13 == 0) goto Lae
            android.view.View r13 = r9.getFocusedChild()
            if (r13 != 0) goto L7c
            goto Lb3
        L7c:
            int r1 = r8.C()
            int r2 = r8.E()
            int r3 = r8.n
            int r4 = r8.D()
            int r3 = r3 - r4
            int r4 = r8.o
            int r5 = r8.B()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r5 = r8.b
            android.graphics.Rect r5 = r5.i
            r8.y(r5, r13)
            int r13 = r5.left
            int r13 = r13 - r11
            if (r13 >= r3) goto Lb3
            int r13 = r5.right
            int r13 = r13 - r11
            if (r13 <= r1) goto Lb3
            int r13 = r5.top
            int r13 = r13 - r10
            if (r13 >= r4) goto Lb3
            int r13 = r5.bottom
            int r13 = r13 - r10
            if (r13 > r2) goto Lae
            goto Lb3
        Lae:
            if (r11 != 0) goto Lb4
            if (r10 == 0) goto Lb3
            goto Lb4
        Lb3:
            return r0
        Lb4:
            if (r12 == 0) goto Lba
            r9.scrollBy(r11, r10)
            return r7
        Lba:
            r9.a0(r11, r10, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.k.j0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public abstract int k(yd0 yd0Var);

    public final void k0() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public abstract int l(yd0 yd0Var);

    public abstract int l0(int i, yd0 yd0Var, l lVar);

    public abstract int m(yd0 yd0Var);

    public abstract void m0(int i);

    public abstract int n(yd0 yd0Var);

    public abstract int n0(int i, yd0 yd0Var, l lVar);

    public abstract int o(yd0 yd0Var);

    public final void o0(RecyclerView recyclerView) {
        p0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public final void p(l lVar) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            n nVarI = RecyclerView.I(viewU);
            if (!nVarI.o()) {
                if (!nVarI.f() || nVarI.h() || this.b.l.b) {
                    u(iV);
                    this.a.o(iV);
                    lVar.h(viewU);
                    this.b.g.N(nVarI);
                } else {
                    i0(iV);
                    lVar.g(nVarI);
                }
            }
        }
    }

    public final void p0(int i, int i2) {
        this.n = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        this.l = mode;
        if (mode == 0) {
            int[] iArr = RecyclerView.u0;
        }
        this.o = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.m = mode2;
        if (mode2 == 0) {
            int[] iArr2 = RecyclerView.u0;
        }
    }

    public View q(int i) {
        int iV = v();
        for (int i2 = 0; i2 < iV; i2++) {
            View viewU = u(i2);
            n nVarI = RecyclerView.I(viewU);
            if (nVarI != null && nVarI.b() == i && !nVarI.o() && (this.b.e0.g || !nVarI.h())) {
                return viewU;
            }
        }
        return null;
    }

    public void q0(Rect rect, int i, int i2) {
        int iD = D() + C() + rect.width();
        int iB = B() + E() + rect.height();
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = xr0.a;
        this.b.setMeasuredDimension(g(i, iD, recyclerView.getMinimumWidth()), g(i2, iB, this.b.getMinimumHeight()));
    }

    public abstract RecyclerView.LayoutParams r();

    public final void r0(int i, int i2) {
        int iV = v();
        if (iV == 0) {
            this.b.m(i, i2);
            return;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < iV; i7++) {
            View viewU = u(i7);
            Rect rect = this.b.i;
            y(rect, viewU);
            int i8 = rect.left;
            if (i8 < i6) {
                i6 = i8;
            }
            int i9 = rect.right;
            if (i9 > i3) {
                i3 = i9;
            }
            int i10 = rect.top;
            if (i10 < i4) {
                i4 = i10;
            }
            int i11 = rect.bottom;
            if (i11 > i5) {
                i5 = i11;
            }
        }
        this.b.i.set(i6, i4, i3, i5);
        q0(this.b.i, i, i2);
    }

    public RecyclerView.LayoutParams s(Context context, AttributeSet attributeSet) {
        return new RecyclerView.LayoutParams(context, attributeSet);
    }

    public final void s0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.b = null;
            this.a = null;
            this.n = 0;
            this.o = 0;
        } else {
            this.b = recyclerView;
            this.a = recyclerView.f;
            this.n = recyclerView.getWidth();
            this.o = recyclerView.getHeight();
        }
        this.l = 1073741824;
        this.m = 1073741824;
    }

    public RecyclerView.LayoutParams t(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof RecyclerView.LayoutParams ? new RecyclerView.LayoutParams((RecyclerView.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new RecyclerView.LayoutParams(layoutParams);
    }

    public final boolean t0(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && this.h && K(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && K(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
    }

    public final View u(int i) {
        po poVar = this.a;
        if (poVar != null) {
            return poVar.u(i);
        }
        return null;
    }

    public boolean u0() {
        return false;
    }

    public final int v() {
        po poVar = this.a;
        if (poVar != null) {
            return poVar.v();
        }
        return 0;
    }

    public final boolean v0(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (this.h && K(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && K(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
    }

    public abstract void w0(RecyclerView recyclerView, int i);

    public int x(l lVar, yd0 yd0Var) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || recyclerView.l == null || !d()) {
            return 1;
        }
        return this.b.l.a();
    }

    public final void x0(d dVar) {
        d dVar2 = this.e;
        if (dVar2 != null && dVar != dVar2 && dVar2.e) {
            dVar2.d();
        }
        this.e = dVar;
        RecyclerView recyclerView = this.b;
        ae0 ae0Var = recyclerView.b0;
        ae0Var.h.removeCallbacks(ae0Var);
        ae0Var.d.abortAnimation();
        if (dVar.h) {
            Log.w("RecyclerView", "An instance of " + dVar.getClass().getSimpleName() + " was started more than once. Each instance of" + dVar.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
        }
        dVar.b = recyclerView;
        dVar.c = this;
        int i = dVar.a;
        if (i == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        recyclerView.e0.a = i;
        dVar.e = true;
        dVar.d = true;
        dVar.f = recyclerView.m.q(i);
        dVar.b.b0.a();
        dVar.h = true;
    }

    public void y(Rect rect, View view) {
        int[] iArr = RecyclerView.u0;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    public boolean y0() {
        return false;
    }

    public final int z() {
        RecyclerView recyclerView = this.b;
        g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (adapter != null) {
            return adapter.a();
        }
        return 0;
    }

    public void V() {
    }

    public void O(RecyclerView recyclerView) {
    }

    public void U(int i, int i2) {
    }

    public void W(int i, int i2) {
    }

    public void X(int i, int i2) {
    }

    public void Y(int i, int i2) {
    }

    public void i(int i, zu zuVar) {
    }

    public void h(int i, int i2, yd0 yd0Var, zu zuVar) {
    }
}
