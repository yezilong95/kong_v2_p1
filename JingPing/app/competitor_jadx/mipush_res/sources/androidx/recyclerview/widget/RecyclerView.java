package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R$attr;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$styleable;
import defpackage.ae;
import defpackage.an0;
import defpackage.b4;
import defpackage.bn0;
import defpackage.c4;
import defpackage.cb0;
import defpackage.cn0;
import defpackage.f51;
import defpackage.fn;
import defpackage.fn0;
import defpackage.g51;
import defpackage.gn0;
import defpackage.hn0;
import defpackage.j51;
import defpackage.jn0;
import defpackage.kn0;
import defpackage.ln0;
import defpackage.m11;
import defpackage.mh0;
import defpackage.mn0;
import defpackage.n41;
import defpackage.n51;
import defpackage.nh0;
import defpackage.nn0;
import defpackage.on0;
import defpackage.ou;
import defpackage.pn0;
import defpackage.qh0;
import defpackage.qn0;
import defpackage.t41;
import defpackage.un0;
import defpackage.vn0;
import defpackage.vv;
import defpackage.vv0;
import defpackage.w10;
import defpackage.w41;
import defpackage.wn0;
import defpackage.xn0;
import defpackage.y10;
import io.nats.client.support.NatsConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements mh0 {
    public static final int[] X0 = {R.attr.nestedScrollingEnabled};
    public static final Class[] Y0;
    public static final an0 Z0;
    public final int A0;
    public final float B0;
    public final float C0;
    public boolean D0;
    public final qn0 E;
    public final wn0 E0;
    public final l F;
    public y10 F0;
    public SavedState G;
    public final w10 G0;
    public final c4 H;
    public final un0 H0;
    public final ou I;
    public mn0 I0;
    public final n41 J;
    public ArrayList J0;
    public boolean K;
    public boolean K0;
    public final Rect L;
    public boolean L0;
    public final Rect M;
    public final cn0 M0;
    public final RectF N;
    public boolean N0;
    public g O;
    public xn0 O0;
    public k P;
    public final int[] P0;
    public final ArrayList Q;
    public nh0 Q0;
    public final ArrayList R;
    public final int[] R0;
    public ln0 S;
    public final int[] S0;
    public boolean T;
    public final int[] T0;
    public boolean U;
    public final ArrayList U0;
    public boolean V;
    public final defpackage.f V0;
    public int W;
    public final bn0 W0;
    public boolean a0;
    public boolean b0;
    public boolean c0;
    public int d0;
    public final AccessibilityManager e0;
    public ArrayList f0;
    public boolean g0;
    public boolean h0;
    public int i0;
    public int j0;
    public gn0 k0;
    public EdgeEffect l0;
    public EdgeEffect m0;
    public EdgeEffect n0;
    public EdgeEffect o0;
    public hn0 p0;
    public int q0;
    public int r0;
    public VelocityTracker s0;
    public int t0;
    public int u0;
    public int v0;
    public int w0;
    public int x0;
    public kn0 y0;
    public final int z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new m();
        public Parcelable G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readParcelable(classLoader == null ? k.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.G, 0);
        }
    }

    static {
        Class cls = Integer.TYPE;
        Y0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        Z0 = new an0(0);
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public static RecyclerView D(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView recyclerViewD = D(viewGroup.getChildAt(i));
            if (recyclerViewD != null) {
                return recyclerViewD;
            }
        }
        return null;
    }

    public static n I(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).a;
    }

    private nh0 getScrollingChildHelper() {
        if (this.Q0 == null) {
            this.Q0 = new nh0(this);
        }
        return this.Q0;
    }

    public static void i(n nVar) {
        WeakReference weakReference = nVar.b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view == nVar.a) {
                    return;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            nVar.b = null;
        }
    }

    public final View A(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0061 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0064 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean B(android.view.MotionEvent r13) {
        /*
            r12 = this;
            int r0 = r13.getAction()
            java.util.ArrayList r1 = r12.R
            int r2 = r1.size()
            r3 = 0
            r4 = r3
        Lc:
            if (r4 >= r2) goto L67
            java.lang.Object r5 = r1.get(r4)
            ln0 r5 = (defpackage.ln0) r5
            r6 = r5
            vv r6 = (defpackage.vv) r6
            int r7 = r6.v
            r8 = 1
            r9 = 2
            if (r7 != r8) goto L5c
            float r7 = r13.getX()
            float r10 = r13.getY()
            boolean r7 = r6.e(r7, r10)
            float r10 = r13.getX()
            float r11 = r13.getY()
            boolean r10 = r6.d(r10, r11)
            int r11 = r13.getAction()
            if (r11 != 0) goto L64
            if (r7 != 0) goto L3f
            if (r10 == 0) goto L64
        L3f:
            if (r10 == 0) goto L4c
            r6.w = r8
            float r7 = r13.getX()
            int r7 = (int) r7
            float r7 = (float) r7
            r6.p = r7
            goto L58
        L4c:
            if (r7 == 0) goto L58
            r6.w = r9
            float r7 = r13.getY()
            int r7 = (int) r7
            float r7 = (float) r7
            r6.m = r7
        L58:
            r6.g(r9)
            goto L5e
        L5c:
            if (r7 != r9) goto L64
        L5e:
            r6 = 3
            if (r0 == r6) goto L64
            r12.S = r5
            return r8
        L64:
            int r4 = r4 + 1
            goto Lc
        L67:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.B(android.view.MotionEvent):boolean");
    }

    public final void C(int[] iArr) {
        int iX = this.I.x();
        if (iX == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < iX; i3++) {
            n nVarI = I(this.I.w(i3));
            if (!nVarI.o()) {
                int iB = nVarI.b();
                if (iB < i) {
                    i = iB;
                }
                if (iB > i2) {
                    i2 = iB;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public final n E(int i) {
        n nVar = null;
        if (this.g0) {
            return null;
        }
        int I = this.I.I();
        for (int i2 = 0; i2 < I; i2++) {
            n nVarI = I(this.I.H(i2));
            if (nVarI != null && !nVarI.h() && F(nVarI) == i) {
                if (!((ArrayList) this.I.H).contains(nVarI.a)) {
                    return nVarI;
                }
                nVar = nVarI;
            }
        }
        return nVar;
    }

    public final int F(n nVar) {
        if (((nVar.j & 524) != 0) || !nVar.e()) {
            return -1;
        }
        c4 c4Var = this.H;
        int i = nVar.c;
        ArrayList arrayList = (ArrayList) c4Var.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            b4 b4Var = (b4) arrayList.get(i2);
            int i3 = b4Var.a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = b4Var.b;
                    if (i4 <= i) {
                        int i5 = b4Var.c;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = b4Var.b;
                    if (i6 == i) {
                        i = b4Var.c;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (b4Var.c <= i) {
                            i++;
                        }
                    }
                }
            } else if (b4Var.b <= i) {
                i += b4Var.c;
            }
        }
        return i;
    }

    public final long G(n nVar) {
        return this.O.b ? nVar.e : nVar.c;
    }

    public final n H(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return I(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public final Rect J(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        boolean z = layoutParams.c;
        Rect rect = layoutParams.b;
        if (!z || (this.H0.g && (layoutParams.a.k() || layoutParams.a.f()))) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.Q;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Rect rect2 = this.L;
            rect2.set(0, 0, 0, 0);
            ((h) arrayList.get(i)).a(rect2, view, this);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.c = false;
        return rect;
    }

    public final boolean K() {
        return !this.V || this.g0 || this.H.h();
    }

    public final boolean L() {
        return this.i0 > 0;
    }

    public final void M(int i) {
        if (this.P == null) {
            return;
        }
        setScrollState(2);
        this.P.p0(i);
        awakenScrollBars();
    }

    public final void N() {
        int I = this.I.I();
        for (int i = 0; i < I; i++) {
            ((LayoutParams) this.I.H(i).getLayoutParams()).c = true;
        }
        ArrayList arrayList = this.F.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            LayoutParams layoutParams = (LayoutParams) ((n) arrayList.get(i2)).a.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.c = true;
            }
        }
    }

    public final void O(int i, int i2, boolean z) {
        int i3 = i + i2;
        int I = this.I.I();
        for (int i4 = 0; i4 < I; i4++) {
            n nVarI = I(this.I.H(i4));
            if (nVarI != null && !nVarI.o()) {
                int i5 = nVarI.c;
                un0 un0Var = this.H0;
                if (i5 >= i3) {
                    nVarI.l(-i2, z);
                    un0Var.f = true;
                } else if (i5 >= i) {
                    nVarI.a(8);
                    nVarI.l(-i2, z);
                    nVarI.c = i - 1;
                    un0Var.f = true;
                }
            }
        }
        l lVar = this.F;
        ArrayList arrayList = lVar.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            n nVar = (n) arrayList.get(size);
            if (nVar != null) {
                int i6 = nVar.c;
                if (i6 >= i3) {
                    nVar.l(-i2, z);
                } else if (i6 >= i) {
                    nVar.a(8);
                    lVar.e(size);
                }
            }
        }
        requestLayout();
    }

    public final void P() {
        this.i0++;
    }

    public final void Q(boolean z) {
        int i;
        AccessibilityManager accessibilityManager;
        int i2 = this.i0 - 1;
        this.i0 = i2;
        if (i2 < 1) {
            this.i0 = 0;
            if (z) {
                int i3 = this.d0;
                this.d0 = 0;
                if (i3 != 0 && (accessibilityManager = this.e0) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    accessibilityEventObtain.setContentChangeTypes(i3);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                ArrayList arrayList = this.U0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    n nVar = (n) arrayList.get(size);
                    if (nVar.a.getParent() == this && !nVar.o() && (i = nVar.q) != -1) {
                        WeakHashMap weakHashMap = f51.a;
                        nVar.a.setImportantForAccessibility(i);
                        nVar.q = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void R(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.r0) {
            int i = actionIndex == 0 ? 1 : 0;
            this.r0 = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.v0 = x;
            this.t0 = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.w0 = y;
            this.u0 = y;
        }
    }

    public final void S() {
        if (this.N0 || !this.T) {
            return;
        }
        WeakHashMap weakHashMap = f51.a;
        postOnAnimation(this.V0);
        this.N0 = true;
    }

    public final void T(boolean z) {
        this.h0 = z | this.h0;
        this.g0 = true;
        int I = this.I.I();
        for (int i = 0; i < I; i++) {
            n nVarI = I(this.I.H(i));
            if (nVarI != null && !nVarI.o()) {
                nVarI.a(6);
            }
        }
        N();
        l lVar = this.F;
        ArrayList arrayList = lVar.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n nVar = (n) arrayList.get(i2);
            if (nVar != null) {
                nVar.a(6);
                nVar.a(1024);
            }
        }
        g gVar = lVar.h.O;
        if (gVar == null || !gVar.b) {
            lVar.d();
        }
    }

    public final void U(n nVar, qh0 qh0Var) {
        nVar.j &= -8193;
        boolean z = this.H0.h;
        n41 n41Var = this.J;
        if (z && nVar.k() && !nVar.h() && !nVar.o()) {
            ((cb0) n41Var.G).d(G(nVar), nVar);
        }
        vv0 vv0Var = (vv0) n41Var.F;
        n51 n51VarA = (n51) vv0Var.getOrDefault(nVar, null);
        if (n51VarA == null) {
            n51VarA = n51.a();
            vv0Var.put(nVar, n51VarA);
        }
        n51VarA.b = qh0Var;
        n51VarA.a |= 4;
    }

    public final void V(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.L;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.c) {
                int i = rect.left;
                Rect rect2 = layoutParams2.b;
                rect.left = i - rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.P.m0(this, view, this.L, !this.V, view2 == null);
    }

    public final void W() {
        VelocityTracker velocityTracker = this.s0;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        e0(0);
        EdgeEffect edgeEffect = this.l0;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.l0.isFinished();
        }
        EdgeEffect edgeEffect2 = this.m0;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.m0.isFinished();
        }
        EdgeEffect edgeEffect3 = this.n0;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.n0.isFinished();
        }
        EdgeEffect edgeEffect4 = this.o0;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.o0.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = f51.a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean X(int r18, int r19, android.view.MotionEvent r20) {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.X(int, int, android.view.MotionEvent):boolean");
    }

    public final void Y(int i, int i2, int[] iArr) {
        n nVar;
        ou ouVar = this.I;
        c0();
        P();
        int i3 = m11.a;
        Trace.beginSection("RV Scroll");
        un0 un0Var = this.H0;
        z(un0Var);
        l lVar = this.F;
        int iO0 = i != 0 ? this.P.o0(i, un0Var, lVar) : 0;
        int iQ0 = i2 != 0 ? this.P.q0(i2, un0Var, lVar) : 0;
        Trace.endSection();
        int iX = ouVar.x();
        for (int i4 = 0; i4 < iX; i4++) {
            View viewW = ouVar.w(i4);
            n nVarH = H(viewW);
            if (nVarH != null && (nVar = nVarH.i) != null) {
                int left = viewW.getLeft();
                int top = viewW.getTop();
                View view = nVar.a;
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        Q(true);
        d0(false);
        if (iArr != null) {
            iArr[0] = iO0;
            iArr[1] = iQ0;
        }
    }

    public final void Z(int i) {
        d dVar;
        if (this.b0) {
            return;
        }
        setScrollState(0);
        wn0 wn0Var = this.E0;
        wn0Var.K.removeCallbacks(wn0Var);
        wn0Var.G.abortAnimation();
        k kVar = this.P;
        if (kVar != null && (dVar = kVar.e) != null) {
            dVar.d();
        }
        k kVar2 = this.P;
        if (kVar2 == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            kVar2.p0(i);
            awakenScrollBars();
        }
    }

    public final void a0(int i, int i2, boolean z) {
        k kVar = this.P;
        if (kVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.b0) {
            return;
        }
        if (!kVar.d()) {
            i = 0;
        }
        if (!this.P.e()) {
            i2 = 0;
        }
        if (i == 0 && i2 == 0) {
            return;
        }
        if (z) {
            int i3 = i != 0 ? 1 : 0;
            if (i2 != 0) {
                i3 |= 2;
            }
            getScrollingChildHelper().g(i3, 1);
        }
        this.E0.b(i, i2, Integer.MIN_VALUE, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        k kVar = this.P;
        if (kVar != null) {
            kVar.getClass();
        }
        super.addFocusables(arrayList, i, i2);
    }

    public void addOnChildAttachStateChangeListener(jn0 jn0Var) {
        if (this.f0 == null) {
            this.f0 = new ArrayList();
        }
        this.f0.add(jn0Var);
    }

    public void addOnItemTouchListener(ln0 ln0Var) {
        this.R.add(ln0Var);
    }

    public void addOnScrollListener(mn0 mn0Var) {
        if (this.J0 == null) {
            this.J0 = new ArrayList();
        }
        this.J0.add(mn0Var);
    }

    public final void b0(int i) {
        if (this.b0) {
            return;
        }
        k kVar = this.P;
        if (kVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            kVar.z0(this, i);
        }
    }

    public final void c0() {
        int i = this.W + 1;
        this.W = i;
        if (i != 1 || this.b0) {
            return;
        }
        this.a0 = false;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.P.f((LayoutParams) layoutParams);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        k kVar = this.P;
        if (kVar != null && kVar.d()) {
            return this.P.j(this.H0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        k kVar = this.P;
        if (kVar != null && kVar.d()) {
            return this.P.k(this.H0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        k kVar = this.P;
        if (kVar != null && kVar.d()) {
            return this.P.l(this.H0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        k kVar = this.P;
        if (kVar != null && kVar.e()) {
            return this.P.m(this.H0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        k kVar = this.P;
        if (kVar != null && kVar.e()) {
            return this.P.n(this.H0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        k kVar = this.P;
        if (kVar != null && kVar.e()) {
            return this.P.o(this.H0);
        }
        return 0;
    }

    public final void d0(boolean z) {
        if (this.W < 1) {
            this.W = 1;
        }
        if (!z && !this.b0) {
            this.a0 = false;
        }
        if (this.W == 1) {
            if (z && this.a0 && !this.b0 && this.P != null && this.O != null) {
                o();
            }
            if (!this.b0) {
                this.a0 = false;
            }
        }
        this.W--;
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().a(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().b(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i, i2, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().d(i, i2, i3, i4, iArr, 0, null);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        ArrayList arrayList = this.Q;
        int size = arrayList.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ((h) arrayList.get(i)).c(canvas, this);
        }
        EdgeEffect edgeEffect = this.l0;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.K ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.l0;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.m0;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.K) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.m0;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.n0;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.K ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.n0;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.o0;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.K) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.o0;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(iSave4);
        }
        if ((z || this.p0 == null || arrayList.size() <= 0 || !this.p0.f()) ? z : true) {
            WeakHashMap weakHashMap = f51.a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public final void e0(int i) {
        getScrollingChildHelper().h(i);
    }

    public final void f(n nVar) {
        View view = nVar.a;
        boolean z = view.getParent() == this;
        this.F.j(H(view));
        if (nVar.j()) {
            this.I.m(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z) {
            this.I.l(view, -1, true);
            return;
        }
        ou ouVar = this.I;
        int iIndexOfChild = ((cn0) ouVar.F).a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            ((ae) ouVar.G).h(iIndexOfChild);
            ouVar.K(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0198 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cc A[ADDED_TO_REGION] */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(h hVar) {
        k kVar = this.P;
        if (kVar != null) {
            kVar.c("Cannot add item decoration during a scroll  or layout");
        }
        ArrayList arrayList = this.Q;
        if (arrayList.isEmpty()) {
            setWillNotDraw(false);
        }
        arrayList.add(hVar);
        N();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        k kVar = this.P;
        if (kVar != null) {
            return kVar.r();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + y());
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        k kVar = this.P;
        if (kVar != null) {
            return kVar.s(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + y());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public g getAdapter() {
        return this.O;
    }

    @Override // android.view.View
    public int getBaseline() {
        k kVar = this.P;
        if (kVar == null) {
            return super.getBaseline();
        }
        kVar.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.K;
    }

    public xn0 getCompatAccessibilityDelegate() {
        return this.O0;
    }

    public gn0 getEdgeEffectFactory() {
        return this.k0;
    }

    public hn0 getItemAnimator() {
        return this.p0;
    }

    public int getItemDecorationCount() {
        return this.Q.size();
    }

    public k getLayoutManager() {
        return this.P;
    }

    public int getMaxFlingVelocity() {
        return this.A0;
    }

    public int getMinFlingVelocity() {
        return this.z0;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public kn0 getOnFlingListener() {
        return this.y0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.D0;
    }

    public on0 getRecycledViewPool() {
        return this.F.c();
    }

    public int getScrollState() {
        return this.q0;
    }

    public final void h(String str) {
        if (L()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + y());
        }
        if (this.j0 > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + y()));
        }
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().f(0);
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.T;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.b0;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().d;
    }

    public final void j() {
        int I = this.I.I();
        for (int i = 0; i < I; i++) {
            n nVarI = I(this.I.H(i));
            if (!nVarI.o()) {
                nVarI.d = -1;
                nVarI.g = -1;
            }
        }
        l lVar = this.F;
        ArrayList arrayList = lVar.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n nVar = (n) arrayList.get(i2);
            nVar.d = -1;
            nVar.g = -1;
        }
        ArrayList arrayList2 = lVar.a;
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            n nVar2 = (n) arrayList2.get(i3);
            nVar2.d = -1;
            nVar2.g = -1;
        }
        ArrayList arrayList3 = lVar.b;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i4 = 0; i4 < size3; i4++) {
                n nVar3 = (n) lVar.b.get(i4);
                nVar3.d = -1;
                nVar3.g = -1;
            }
        }
    }

    public final void k(int i, int i2) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.l0;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            zIsFinished = false;
        } else {
            this.l0.onRelease();
            zIsFinished = this.l0.isFinished();
        }
        EdgeEffect edgeEffect2 = this.n0;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.n0.onRelease();
            zIsFinished |= this.n0.isFinished();
        }
        EdgeEffect edgeEffect3 = this.m0;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.m0.onRelease();
            zIsFinished |= this.m0.isFinished();
        }
        EdgeEffect edgeEffect4 = this.o0;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.o0.onRelease();
            zIsFinished |= this.o0.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = f51.a;
            postInvalidateOnAnimation();
        }
    }

    public final void l() {
        c4 c4Var = this.H;
        if (!this.V || this.g0) {
            int i = m11.a;
            Trace.beginSection("RV FullInvalidate");
            o();
            Trace.endSection();
            return;
        }
        if (c4Var.h()) {
            c4Var.getClass();
            if (c4Var.h()) {
                int i2 = m11.a;
                Trace.beginSection("RV FullInvalidate");
                o();
                Trace.endSection();
            }
        }
    }

    public final void m(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = f51.a;
        setMeasuredDimension(k.g(i, paddingRight, getMinimumWidth()), k.g(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public final void n(View view) {
        I(view);
        ArrayList arrayList = this.f0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((jn0) this.f0.get(size)).getClass();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:157:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x039c  */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [androidx.recyclerview.widget.n] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20, types: [int] */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void o() {
        /*
            Method dump skipped, instruction units count: 1026
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.i0 = r0
            r1 = 1
            r5.T = r1
            boolean r2 = r5.V
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.V = r2
            androidx.recyclerview.widget.k r2 = r5.P
            if (r2 == 0) goto L21
            r2.g = r1
            r2.Q(r5)
        L21:
            r5.N0 = r0
            java.lang.ThreadLocal r0 = defpackage.y10.I
            java.lang.Object r1 = r0.get()
            y10 r1 = (defpackage.y10) r1
            r5.F0 = r1
            if (r1 != 0) goto L6b
            y10 r1 = new y10
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.E = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.H = r2
            r5.F0 = r1
            java.util.WeakHashMap r1 = defpackage.f51.a
            android.view.Display r1 = r5.getDisplay()
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L5d
            if (r1 == 0) goto L5d
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L5d
            goto L5f
        L5d:
            r1 = 1114636288(0x42700000, float:60.0)
        L5f:
            y10 r2 = r5.F0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.G = r3
            r0.set(r2)
        L6b:
            y10 r0 = r5.F0
            java.util.ArrayList r0 = r0.E
            r0.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        d dVar;
        super.onDetachedFromWindow();
        hn0 hn0Var = this.p0;
        if (hn0Var != null) {
            hn0Var.e();
        }
        setScrollState(0);
        wn0 wn0Var = this.E0;
        wn0Var.K.removeCallbacks(wn0Var);
        wn0Var.G.abortAnimation();
        k kVar = this.P;
        if (kVar != null && (dVar = kVar.e) != null) {
            dVar.d();
        }
        this.T = false;
        k kVar2 = this.P;
        if (kVar2 != null) {
            kVar2.g = false;
            kVar2.R(this);
        }
        this.U0.clear();
        removeCallbacks(this.V0);
        this.J.getClass();
        while (n51.d.a() != null) {
        }
        y10 y10Var = this.F0;
        if (y10Var != null) {
            y10Var.E.remove(this);
            this.F0 = null;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.Q;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((h) arrayList.get(i)).b(canvas, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.k r0 = r5.P
            r1 = 0
            if (r0 != 0) goto L7
            goto L79
        L7:
            boolean r0 = r5.b0
            if (r0 == 0) goto Ld
            goto L79
        Ld:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L79
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L40
            androidx.recyclerview.widget.k r0 = r5.P
            boolean r0 = r0.e()
            if (r0 == 0) goto L2e
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2f
        L2e:
            r0 = r2
        L2f:
            androidx.recyclerview.widget.k r3 = r5.P
            boolean r3 = r3.d()
            if (r3 == 0) goto L3e
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L66
        L3e:
            r3 = r2
            goto L66
        L40:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L64
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            androidx.recyclerview.widget.k r3 = r5.P
            boolean r3 = r3.e()
            if (r3 == 0) goto L59
            float r0 = -r0
            goto L3e
        L59:
            androidx.recyclerview.widget.k r3 = r5.P
            boolean r3 = r3.d()
            if (r3 == 0) goto L64
            r3 = r0
            r0 = r2
            goto L66
        L64:
            r0 = r2
            r3 = r0
        L66:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6e
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L79
        L6e:
            float r2 = r5.B0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.C0
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.X(r2, r0, r6)
        L79:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (!this.b0) {
            this.S = null;
            if (B(motionEvent)) {
                W();
                setScrollState(0);
                return true;
            }
            k kVar = this.P;
            if (kVar != null) {
                boolean zD = kVar.d();
                boolean zE = this.P.e();
                if (this.s0 == null) {
                    this.s0 = VelocityTracker.obtain();
                }
                this.s0.addMovement(motionEvent);
                int actionMasked = motionEvent.getActionMasked();
                int actionIndex = motionEvent.getActionIndex();
                if (actionMasked == 0) {
                    if (this.c0) {
                        this.c0 = false;
                    }
                    this.r0 = motionEvent.getPointerId(0);
                    int x = (int) (motionEvent.getX() + 0.5f);
                    this.v0 = x;
                    this.t0 = x;
                    int y = (int) (motionEvent.getY() + 0.5f);
                    this.w0 = y;
                    this.u0 = y;
                    if (this.q0 == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        e0(1);
                    }
                    int[] iArr = this.S0;
                    iArr[1] = 0;
                    iArr[0] = 0;
                    int i = zD;
                    if (zE) {
                        i = (zD ? 1 : 0) | 2;
                    }
                    getScrollingChildHelper().g(i, 0);
                } else if (actionMasked == 1) {
                    this.s0.clear();
                    e0(0);
                } else if (actionMasked == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.r0);
                    if (iFindPointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.r0 + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    int x2 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
                    int y2 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
                    if (this.q0 != 1) {
                        int i2 = x2 - this.t0;
                        int i3 = y2 - this.u0;
                        if (!zD || Math.abs(i2) <= this.x0) {
                            z = false;
                        } else {
                            this.v0 = x2;
                            z = true;
                        }
                        if (zE && Math.abs(i3) > this.x0) {
                            this.w0 = y2;
                            z = true;
                        }
                        if (z) {
                            setScrollState(1);
                        }
                    }
                } else if (actionMasked == 3) {
                    W();
                    setScrollState(0);
                } else if (actionMasked == 5) {
                    this.r0 = motionEvent.getPointerId(actionIndex);
                    int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.v0 = x3;
                    this.t0 = x3;
                    int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.w0 = y3;
                    this.u0 = y3;
                } else if (actionMasked == 6) {
                    R(motionEvent);
                }
                if (this.q0 == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = m11.a;
        Trace.beginSection("RV OnLayout");
        o();
        Trace.endSection();
        this.V = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        k kVar = this.P;
        if (kVar == null) {
            m(i, i2);
            return;
        }
        boolean zL = kVar.L();
        un0 un0Var = this.H0;
        if (!zL) {
            if (this.U) {
                this.P.b.m(i, i2);
                return;
            }
            if (un0Var.k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            g gVar = this.O;
            if (gVar != null) {
                un0Var.e = gVar.a();
            } else {
                un0Var.e = 0;
            }
            c0();
            this.P.b.m(i, i2);
            d0(false);
            un0Var.g = false;
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.P.b.m(i, i2);
        if ((mode == 1073741824 && mode2 == 1073741824) || this.O == null) {
            return;
        }
        if (un0Var.d == 1) {
            p();
        }
        this.P.s0(i, i2);
        un0Var.i = true;
        q();
        this.P.u0(i, i2);
        if (this.P.x0()) {
            this.P.s0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            un0Var.i = true;
            q();
            this.P.u0(i, i2);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (L()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.G = savedState;
        super.onRestoreInstanceState(savedState.E);
        k kVar = this.P;
        if (kVar == null || (parcelable2 = this.G.G) == null) {
            return;
        }
        kVar.e0(parcelable2);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.G;
        if (savedState2 != null) {
            savedState.G = savedState2.G;
            return savedState;
        }
        k kVar = this.P;
        if (kVar != null) {
            savedState.G = kVar.f0();
            return savedState;
        }
        savedState.G = null;
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.o0 = null;
        this.m0 = null;
        this.n0 = null;
        this.l0 = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01fa A[PHI: r1
      0x01fa: PHI (r1v54 int) = (r1v39 int), (r1v58 int) binds: [B:90:0x01e3, B:94:0x01f6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01fd  */
    /* JADX WARN: Type inference failed for: r9v43, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v45 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r31) {
        /*
            Method dump skipped, instruction units count: 1194
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:253:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0226 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p() {
        /*
            Method dump skipped, instruction units count: 1350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.p():void");
    }

    public final void q() {
        c0();
        P();
        un0 un0Var = this.H0;
        un0Var.a(6);
        this.H.b();
        un0Var.e = this.O.a();
        un0Var.c = 0;
        un0Var.g = false;
        this.P.c0(this.F, un0Var);
        un0Var.f = false;
        this.G = null;
        un0Var.j = un0Var.j && this.p0 != null;
        un0Var.d = 4;
        Q(true);
        d0(false);
    }

    public final boolean r(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i, i2, i3, iArr, iArr2);
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z) {
        n nVarI = I(view);
        if (nVarI != null) {
            if (nVarI.j()) {
                nVarI.j &= -257;
            } else if (!nVarI.o()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + nVarI + y());
            }
        }
        view.clearAnimation();
        n(view);
        super.removeDetachedView(view, z);
    }

    public void removeOnChildAttachStateChangeListener(jn0 jn0Var) {
        ArrayList arrayList = this.f0;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(jn0Var);
    }

    public void removeOnItemTouchListener(ln0 ln0Var) {
        this.R.remove(ln0Var);
        if (this.S == ln0Var) {
            this.S = null;
        }
    }

    public void removeOnScrollListener(mn0 mn0Var) {
        ArrayList arrayList = this.J0;
        if (arrayList != null) {
            arrayList.remove(mn0Var);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        d dVar = this.P.e;
        if ((dVar == null || !dVar.e) && !L() && view2 != null) {
            V(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.P.m0(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ArrayList arrayList = this.R;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((ln0) arrayList.get(i)).getClass();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.W != 0 || this.b0) {
            this.a0 = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().d(i, i2, i3, i4, iArr, i5, iArr2);
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        k kVar = this.P;
        if (kVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.b0) {
            return;
        }
        boolean zD = kVar.d();
        boolean zE = this.P.e();
        if (zD || zE) {
            if (!zD) {
                i = 0;
            }
            if (!zE) {
                i2 = 0;
            }
            X(i, i2, null);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!L()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.d0 |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        }
    }

    public void setAccessibilityDelegateCompat(xn0 xn0Var) {
        this.O0 = xn0Var;
        f51.o(this, xn0Var);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        g gVar2 = this.O;
        qn0 qn0Var = this.E;
        if (gVar2 != null) {
            gVar2.a.unregisterObserver(qn0Var);
            this.O.getClass();
        }
        hn0 hn0Var = this.p0;
        if (hn0Var != null) {
            hn0Var.e();
        }
        k kVar = this.P;
        l lVar = this.F;
        if (kVar != null) {
            kVar.i0(lVar);
            this.P.j0(lVar);
        }
        lVar.a.clear();
        lVar.d();
        c4 c4Var = this.H;
        c4Var.l((ArrayList) c4Var.c);
        c4Var.l((ArrayList) c4Var.d);
        g gVar3 = this.O;
        this.O = gVar;
        if (gVar != null) {
            gVar.a.registerObserver(qn0Var);
        }
        g gVar4 = this.O;
        lVar.a.clear();
        lVar.d();
        on0 on0VarC = lVar.c();
        if (gVar3 != null) {
            on0VarC.b--;
        }
        if (on0VarC.b == 0) {
            int i = 0;
            while (true) {
                SparseArray sparseArray = on0VarC.a;
                if (i >= sparseArray.size()) {
                    break;
                }
                ((nn0) sparseArray.valueAt(i)).a.clear();
                i++;
            }
        }
        if (gVar4 != null) {
            on0VarC.b++;
        }
        this.H0.f = true;
        T(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(fn0 fn0Var) {
        if (fn0Var == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(false);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.K) {
            this.o0 = null;
            this.m0 = null;
            this.n0 = null;
            this.l0 = null;
        }
        this.K = z;
        super.setClipToPadding(z);
        if (this.V) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(gn0 gn0Var) {
        gn0Var.getClass();
        this.k0 = gn0Var;
        this.o0 = null;
        this.m0 = null;
        this.n0 = null;
        this.l0 = null;
    }

    public void setHasFixedSize(boolean z) {
        this.U = z;
    }

    public void setItemAnimator(hn0 hn0Var) {
        hn0 hn0Var2 = this.p0;
        if (hn0Var2 != null) {
            hn0Var2.e();
            this.p0.a = null;
        }
        this.p0 = hn0Var;
        if (hn0Var != null) {
            hn0Var.a = this.M0;
        }
    }

    public void setItemViewCacheSize(int i) {
        l lVar = this.F;
        lVar.e = i;
        lVar.k();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(k kVar) {
        RecyclerView recyclerView;
        d dVar;
        if (kVar == this.P) {
            return;
        }
        setScrollState(0);
        wn0 wn0Var = this.E0;
        wn0Var.K.removeCallbacks(wn0Var);
        wn0Var.G.abortAnimation();
        k kVar2 = this.P;
        if (kVar2 != null && (dVar = kVar2.e) != null) {
            dVar.d();
        }
        k kVar3 = this.P;
        l lVar = this.F;
        if (kVar3 != null) {
            hn0 hn0Var = this.p0;
            if (hn0Var != null) {
                hn0Var.e();
            }
            this.P.i0(lVar);
            this.P.j0(lVar);
            lVar.a.clear();
            lVar.d();
            if (this.T) {
                k kVar4 = this.P;
                kVar4.g = false;
                kVar4.R(this);
            }
            this.P.v0(null);
            this.P = null;
        } else {
            lVar.a.clear();
            lVar.d();
        }
        ou ouVar = this.I;
        ((ae) ouVar.G).g();
        ArrayList arrayList = (ArrayList) ouVar.H;
        int size = arrayList.size() - 1;
        while (true) {
            recyclerView = ((cn0) ouVar.F).a;
            if (size < 0) {
                break;
            }
            n nVarI = I((View) arrayList.get(size));
            if (nVarI != null) {
                int i = nVarI.p;
                if (recyclerView.L()) {
                    nVarI.q = i;
                    recyclerView.U0.add(nVarI);
                } else {
                    WeakHashMap weakHashMap = f51.a;
                    nVarI.a.setImportantForAccessibility(i);
                }
                nVarI.p = 0;
            }
            arrayList.remove(size);
            size--;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.n(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.P = kVar;
        if (kVar != null) {
            if (kVar.b != null) {
                throw new IllegalArgumentException("LayoutManager " + kVar + " is already attached to a RecyclerView:" + kVar.b.y());
            }
            kVar.v0(this);
            if (this.T) {
                k kVar5 = this.P;
                kVar5.g = true;
                kVar5.Q(this);
            }
        }
        lVar.k();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        nh0 scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.d) {
            WeakHashMap weakHashMap = f51.a;
            t41.z(scrollingChildHelper.c);
        }
        scrollingChildHelper.d = z;
    }

    public void setOnFlingListener(kn0 kn0Var) {
        this.y0 = kn0Var;
    }

    @Deprecated
    public void setOnScrollListener(mn0 mn0Var) {
        this.I0 = mn0Var;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.D0 = z;
    }

    public void setRecycledViewPool(on0 on0Var) {
        l lVar = this.F;
        if (lVar.g != null) {
            r1.b--;
        }
        lVar.g = on0Var;
        if (on0Var == null || lVar.h.getAdapter() == null) {
            return;
        }
        lVar.g.b++;
    }

    public void setScrollState(int i) {
        d dVar;
        if (i == this.q0) {
            return;
        }
        this.q0 = i;
        if (i != 2) {
            wn0 wn0Var = this.E0;
            wn0Var.K.removeCallbacks(wn0Var);
            wn0Var.G.abortAnimation();
            k kVar = this.P;
            if (kVar != null && (dVar = kVar.e) != null) {
                dVar.d();
            }
        }
        k kVar2 = this.P;
        if (kVar2 != null) {
            kVar2.g0(i);
        }
        mn0 mn0Var = this.I0;
        if (mn0Var != null) {
            mn0Var.a(this, i);
        }
        ArrayList arrayList = this.J0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((mn0) this.J0.get(size)).a(this, i);
            }
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 0) {
            if (i == 1) {
                this.x0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
        }
        this.x0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(vn0 vn0Var) {
        this.F.getClass();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return getScrollingChildHelper().g(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().h(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        d dVar;
        if (z != this.b0) {
            h("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.b0 = false;
                if (this.a0 && this.P != null && this.O != null) {
                    requestLayout();
                }
                this.a0 = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.b0 = true;
            this.c0 = true;
            setScrollState(0);
            wn0 wn0Var = this.E0;
            wn0Var.K.removeCallbacks(wn0Var);
            wn0Var.G.abortAnimation();
            k kVar = this.P;
            if (kVar == null || (dVar = kVar.e) == null) {
                return;
            }
            dVar.d();
        }
    }

    public final void t(int i, int i2) {
        this.j0++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        mn0 mn0Var = this.I0;
        if (mn0Var != null) {
            mn0Var.b(this, i, i2);
        }
        ArrayList arrayList = this.J0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((mn0) this.J0.get(size)).b(this, i, i2);
            }
        }
        this.j0--;
    }

    public final void u() {
        if (this.o0 != null) {
            return;
        }
        this.k0.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.o0 = edgeEffect;
        if (this.K) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void v() {
        if (this.l0 != null) {
            return;
        }
        this.k0.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.l0 = edgeEffect;
        if (this.K) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void w() {
        if (this.n0 != null) {
            return;
        }
        this.k0.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.n0 = edgeEffect;
        if (this.K) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void x() {
        if (this.m0 != null) {
            return;
        }
        this.k0.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.m0 = edgeEffect;
        if (this.K) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String y() {
        return NatsConstants.SPACE + super.toString() + ", adapter:" + this.O + ", layout:" + this.P + ", context:" + getContext();
    }

    public final void z(un0 un0Var) {
        if (getScrollState() != 2) {
            un0Var.getClass();
            return;
        }
        OverScroller overScroller = this.E0.G;
        overScroller.getFinalX();
        overScroller.getCurrX();
        un0Var.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.recyclerViewStyle);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        float fA;
        float fA2;
        int i2;
        char c;
        TypedArray typedArray;
        ClassLoader classLoader;
        Constructor constructor;
        Object[] objArr;
        super(context, attributeSet, i);
        this.E = new qn0(this);
        this.F = new l(this);
        this.J = new n41();
        this.L = new Rect();
        this.M = new Rect();
        this.N = new RectF();
        this.Q = new ArrayList();
        this.R = new ArrayList();
        this.W = 0;
        this.g0 = false;
        this.h0 = false;
        this.i0 = 0;
        this.j0 = 0;
        this.k0 = new gn0();
        fn fnVar = new fn();
        fnVar.a = null;
        fnVar.b = new ArrayList();
        fnVar.c = 120L;
        fnVar.d = 120L;
        fnVar.e = 250L;
        fnVar.f = 250L;
        fnVar.g = true;
        fnVar.h = new ArrayList();
        fnVar.i = new ArrayList();
        fnVar.j = new ArrayList();
        fnVar.k = new ArrayList();
        fnVar.l = new ArrayList();
        fnVar.m = new ArrayList();
        fnVar.n = new ArrayList();
        fnVar.o = new ArrayList();
        fnVar.p = new ArrayList();
        fnVar.q = new ArrayList();
        fnVar.r = new ArrayList();
        this.p0 = fnVar;
        this.q0 = 0;
        this.r0 = -1;
        this.B0 = Float.MIN_VALUE;
        this.C0 = Float.MIN_VALUE;
        this.D0 = true;
        this.E0 = new wn0(this);
        this.G0 = new w10();
        un0 un0Var = new un0();
        un0Var.a = -1;
        un0Var.b = 0;
        un0Var.c = 0;
        un0Var.d = 1;
        un0Var.e = 0;
        un0Var.f = false;
        un0Var.g = false;
        un0Var.h = false;
        un0Var.i = false;
        un0Var.j = false;
        un0Var.k = false;
        this.H0 = un0Var;
        this.K0 = false;
        this.L0 = false;
        cn0 cn0Var = new cn0(this);
        this.M0 = cn0Var;
        this.N0 = false;
        this.P0 = new int[2];
        this.R0 = new int[2];
        this.S0 = new int[2];
        this.T0 = new int[2];
        this.U0 = new ArrayList();
        this.V0 = new defpackage.f(21, this);
        this.W0 = new bn0(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.x0 = viewConfiguration.getScaledTouchSlop();
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            Method method = j51.a;
            fA = g51.a(viewConfiguration);
        } else {
            fA = j51.a(viewConfiguration, context);
        }
        this.B0 = fA;
        if (i3 >= 26) {
            fA2 = g51.b(viewConfiguration);
        } else {
            fA2 = j51.a(viewConfiguration, context);
        }
        this.C0 = fA2;
        this.z0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.A0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.p0.a = cn0Var;
        this.H = new c4(new bn0(this));
        this.I = new ou(new cn0(this));
        WeakHashMap weakHashMap = f51.a;
        if ((i3 >= 26 ? w41.c(this) : 0) == 0 && i3 >= 26) {
            w41.m(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.e0 = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new xn0(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, 0);
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, R$styleable.RecyclerView, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_android_clipToPadding, true);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_fastScrollEnabled, false)) {
            StateListDrawable stateListDrawable = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalThumbDrawable);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalTrackDrawable);
            StateListDrawable stateListDrawable2 = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalThumbDrawable);
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalTrackDrawable);
            if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
                Resources resources = getContext().getResources();
                c = 2;
                typedArray = typedArrayObtainStyledAttributes;
                i2 = i;
                new vv(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R$dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R$dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R$dimen.fastscroll_margin));
            } else {
                throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + y());
            }
        } else {
            i2 = i;
            c = 2;
            typedArray = typedArrayObtainStyledAttributes;
        }
        typedArray.recycle();
        if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(NatsConstants.DOT)) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                String str = strTrim;
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> clsAsSubclass = Class.forName(str, false, classLoader).asSubclass(k.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(Y0);
                        objArr = new Object[4];
                        objArr[0] = context;
                        objArr[1] = attributeSet;
                        objArr[c] = Integer.valueOf(i2);
                        objArr[3] = 0;
                    } catch (NoSuchMethodException e) {
                        try {
                            constructor = clsAsSubclass.getConstructor(new Class[0]);
                            objArr = null;
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((k) constructor.newInstance(objArr));
                } catch (ClassCastException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + str, e3);
                } catch (ClassNotFoundException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + str, e4);
                } catch (IllegalAccessException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e5);
                } catch (InstantiationException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e6);
                } catch (InvocationTargetException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e7);
                }
            }
        }
        int i4 = Build.VERSION.SDK_INT;
        int[] iArr = X0;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        if (i4 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes2, i2, 0);
        }
        boolean z = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public n a;
        public final Rect b;
        public boolean c;
        public boolean d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        k kVar = this.P;
        if (kVar != null) {
            return kVar.t(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + y());
    }

    public void setRecyclerListener(pn0 pn0Var) {
    }
}
