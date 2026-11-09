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
import defpackage.ae0;
import defpackage.ap0;
import defpackage.be0;
import defpackage.bs0;
import defpackage.bv;
import defpackage.e5;
import defpackage.fc;
import defpackage.fd0;
import defpackage.fj0;
import defpackage.fs0;
import defpackage.gd0;
import defpackage.j90;
import defpackage.jd0;
import defpackage.k90;
import defpackage.kd0;
import defpackage.ld0;
import defpackage.lr0;
import defpackage.mj;
import defpackage.n2;
import defpackage.n90;
import defpackage.nd0;
import defpackage.o2;
import defpackage.od0;
import defpackage.or0;
import defpackage.pd0;
import defpackage.po;
import defpackage.qd0;
import defpackage.qp;
import defpackage.rd0;
import defpackage.sd0;
import defpackage.t30;
import defpackage.td0;
import defpackage.ud0;
import defpackage.v6;
import defpackage.xr0;
import defpackage.yd0;
import defpackage.yr0;
import defpackage.zd0;
import defpackage.zu;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements j90 {
    public static final int[] u0 = {R.attr.nestedScrollingEnabled};
    public static final Class[] v0;
    public static final fd0 w0;
    public boolean A;
    public boolean B;
    public int C;
    public int D;
    public kd0 E;
    public EdgeEffect F;
    public EdgeEffect G;
    public EdgeEffect H;
    public EdgeEffect I;
    public ld0 J;
    public int K;
    public int L;
    public VelocityTracker M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public od0 S;
    public final int T;
    public final int U;
    public final float V;
    public final float W;
    public boolean a0;
    public final ud0 b;
    public final ae0 b0;
    public final l c;
    public bv c0;
    public SavedState d;
    public final zu d0;
    public final o2 e;
    public final yd0 e0;
    public final po f;
    public qd0 f0;
    public final e5 g;
    public ArrayList g0;
    public boolean h;
    public boolean h0;
    public final Rect i;
    public boolean i0;
    public final Rect j;
    public final gd0 j0;
    public final RectF k;
    public boolean k0;
    public g l;
    public be0 l0;
    public k m;
    public final int[] m0;
    public final ArrayList n;
    public k90 n0;
    public final ArrayList o;
    public final int[] o0;
    public pd0 p;
    public final int[] p0;
    public boolean q;
    public final int[] q0;
    public boolean r;
    public final ArrayList r0;
    public boolean s;
    public final v6 s0;
    public int t;
    public final gd0 t0;
    public boolean u;
    public boolean v;
    public boolean w;
    public int x;
    public final AccessibilityManager y;
    public ArrayList z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new m();
        public Parcelable d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readParcelable(classLoader == null ? k.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.d, 0);
        }
    }

    static {
        Class cls = Integer.TYPE;
        v0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        w0 = new fd0(0);
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

    private k90 getScrollingChildHelper() {
        if (this.n0 == null) {
            this.n0 = new k90(this);
        }
        return this.n0;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean B(android.view.MotionEvent r13) {
        /*
            r12 = this;
            int r0 = r13.getAction()
            java.util.ArrayList r1 = r12.o
            int r2 = r1.size()
            r3 = 0
            r4 = r3
        Lc:
            if (r4 >= r2) goto L67
            java.lang.Object r5 = r1.get(r4)
            pd0 r5 = (defpackage.pd0) r5
            r6 = r5
            qp r6 = (defpackage.qp) r6
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
            r12.p = r5
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
        int iV = this.f.v();
        if (iV == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < iV; i3++) {
            n nVarI = I(this.f.u(i3));
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
        if (this.A) {
            return null;
        }
        int iG = this.f.G();
        for (int i2 = 0; i2 < iG; i2++) {
            n nVarI = I(this.f.F(i2));
            if (nVarI != null && !nVarI.h() && F(nVarI) == i) {
                if (!((ArrayList) this.f.e).contains(nVarI.a)) {
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
        o2 o2Var = this.e;
        int i = nVar.c;
        ArrayList arrayList = (ArrayList) o2Var.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n2 n2Var = (n2) arrayList.get(i2);
            int i3 = n2Var.a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = n2Var.b;
                    if (i4 <= i) {
                        int i5 = n2Var.c;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = n2Var.b;
                    if (i6 == i) {
                        i = n2Var.c;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (n2Var.c <= i) {
                            i++;
                        }
                    }
                }
            } else if (n2Var.b <= i) {
                i += n2Var.c;
            }
        }
        return i;
    }

    public final long G(n nVar) {
        return this.l.b ? nVar.e : nVar.c;
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
        if (!z || (this.e0.g && (layoutParams.a.k() || layoutParams.a.f()))) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.n;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Rect rect2 = this.i;
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
        return !this.s || this.A || this.e.i();
    }

    public final boolean L() {
        return this.C > 0;
    }

    public final void M(int i) {
        if (this.m == null) {
            return;
        }
        setScrollState(2);
        this.m.m0(i);
        awakenScrollBars();
    }

    public final void N() {
        int iG = this.f.G();
        for (int i = 0; i < iG; i++) {
            ((LayoutParams) this.f.F(i).getLayoutParams()).c = true;
        }
        ArrayList arrayList = this.c.c;
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
        int iG = this.f.G();
        for (int i4 = 0; i4 < iG; i4++) {
            n nVarI = I(this.f.F(i4));
            if (nVarI != null && !nVarI.o()) {
                int i5 = nVarI.c;
                yd0 yd0Var = this.e0;
                if (i5 >= i3) {
                    nVarI.l(-i2, z);
                    yd0Var.f = true;
                } else if (i5 >= i) {
                    nVarI.a(8);
                    nVarI.l(-i2, z);
                    nVarI.c = i - 1;
                    yd0Var.f = true;
                }
            }
        }
        l lVar = this.c;
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
        this.C++;
    }

    public final void Q(boolean z) {
        int i;
        AccessibilityManager accessibilityManager;
        int i2 = this.C - 1;
        this.C = i2;
        if (i2 < 1) {
            this.C = 0;
            if (z) {
                int i3 = this.x;
                this.x = 0;
                if (i3 != 0 && (accessibilityManager = this.y) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    accessibilityEventObtain.setContentChangeTypes(i3);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                ArrayList arrayList = this.r0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    n nVar = (n) arrayList.get(size);
                    if (nVar.a.getParent() == this && !nVar.o() && (i = nVar.q) != -1) {
                        WeakHashMap weakHashMap = xr0.a;
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
        if (motionEvent.getPointerId(actionIndex) == this.L) {
            int i = actionIndex == 0 ? 1 : 0;
            this.L = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.P = x;
            this.N = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.Q = y;
            this.O = y;
        }
    }

    public final void S() {
        if (this.k0 || !this.q) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        postOnAnimation(this.s0);
        this.k0 = true;
    }

    public final void T(boolean z) {
        this.B = z | this.B;
        this.A = true;
        int iG = this.f.G();
        for (int i = 0; i < iG; i++) {
            n nVarI = I(this.f.F(i));
            if (nVarI != null && !nVarI.o()) {
                nVarI.a(6);
            }
        }
        N();
        l lVar = this.c;
        ArrayList arrayList = lVar.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n nVar = (n) arrayList.get(i2);
            if (nVar != null) {
                nVar.a(6);
                nVar.a(1024);
            }
        }
        g gVar = lVar.h.l;
        if (gVar == null || !gVar.b) {
            lVar.d();
        }
    }

    public final void U(n nVar, n90 n90Var) {
        nVar.j &= -8193;
        boolean z = this.e0.h;
        e5 e5Var = this.g;
        if (z && nVar.k() && !nVar.h() && !nVar.o()) {
            ((t30) e5Var.d).d(G(nVar), nVar);
        }
        fj0 fj0Var = (fj0) e5Var.c;
        fs0 fs0VarA = (fs0) fj0Var.getOrDefault(nVar, null);
        if (fs0VarA == null) {
            fs0VarA = fs0.a();
            fj0Var.put(nVar, fs0VarA);
        }
        fs0VarA.b = n90Var;
        fs0VarA.a |= 4;
    }

    public final void V(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.i;
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
        this.m.j0(this, view, this.i, !this.s, view2 == null);
    }

    public final void W() {
        VelocityTracker velocityTracker = this.M;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        e0(0);
        EdgeEffect edgeEffect = this.F;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.F.isFinished();
        }
        EdgeEffect edgeEffect2 = this.G;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.G.isFinished();
        }
        EdgeEffect edgeEffect3 = this.H;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.H.isFinished();
        }
        EdgeEffect edgeEffect4 = this.I;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.I.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean X(int r18, int r19, android.view.MotionEvent r20) {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.X(int, int, android.view.MotionEvent):boolean");
    }

    public final void Y(int i, int i2, int[] iArr) {
        n nVar;
        po poVar = this.f;
        c0();
        P();
        int i3 = ap0.a;
        Trace.beginSection("RV Scroll");
        yd0 yd0Var = this.e0;
        z(yd0Var);
        l lVar = this.c;
        int iL0 = i != 0 ? this.m.l0(i, yd0Var, lVar) : 0;
        int iN0 = i2 != 0 ? this.m.n0(i2, yd0Var, lVar) : 0;
        Trace.endSection();
        int iV = poVar.v();
        for (int i4 = 0; i4 < iV; i4++) {
            View viewU = poVar.u(i4);
            n nVarH = H(viewU);
            if (nVarH != null && (nVar = nVarH.i) != null) {
                int left = viewU.getLeft();
                int top = viewU.getTop();
                View view = nVar.a;
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        Q(true);
        d0(false);
        if (iArr != null) {
            iArr[0] = iL0;
            iArr[1] = iN0;
        }
    }

    public final void Z(int i) {
        d dVar;
        if (this.v) {
            return;
        }
        setScrollState(0);
        ae0 ae0Var = this.b0;
        ae0Var.h.removeCallbacks(ae0Var);
        ae0Var.d.abortAnimation();
        k kVar = this.m;
        if (kVar != null && (dVar = kVar.e) != null) {
            dVar.d();
        }
        k kVar2 = this.m;
        if (kVar2 == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            kVar2.m0(i);
            awakenScrollBars();
        }
    }

    public final void a0(int i, int i2, boolean z) {
        k kVar = this.m;
        if (kVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.v) {
            return;
        }
        if (!kVar.d()) {
            i = 0;
        }
        if (!this.m.e()) {
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
        this.b0.b(i, i2, Integer.MIN_VALUE, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        k kVar = this.m;
        if (kVar != null) {
            kVar.getClass();
        }
        super.addFocusables(arrayList, i, i2);
    }

    public void addOnChildAttachStateChangeListener(nd0 nd0Var) {
        if (this.z == null) {
            this.z = new ArrayList();
        }
        this.z.add(nd0Var);
    }

    public void addOnItemTouchListener(pd0 pd0Var) {
        this.o.add(pd0Var);
    }

    public void addOnScrollListener(qd0 qd0Var) {
        if (this.g0 == null) {
            this.g0 = new ArrayList();
        }
        this.g0.add(qd0Var);
    }

    public final void b0(int i) {
        if (this.v) {
            return;
        }
        k kVar = this.m;
        if (kVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            kVar.w0(this, i);
        }
    }

    public final void c0() {
        int i = this.t + 1;
        this.t = i;
        if (i != 1 || this.v) {
            return;
        }
        this.u = false;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.m.f((LayoutParams) layoutParams);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        k kVar = this.m;
        if (kVar != null && kVar.d()) {
            return this.m.j(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        k kVar = this.m;
        if (kVar != null && kVar.d()) {
            return this.m.k(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        k kVar = this.m;
        if (kVar != null && kVar.d()) {
            return this.m.l(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        k kVar = this.m;
        if (kVar != null && kVar.e()) {
            return this.m.m(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        k kVar = this.m;
        if (kVar != null && kVar.e()) {
            return this.m.n(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        k kVar = this.m;
        if (kVar != null && kVar.e()) {
            return this.m.o(this.e0);
        }
        return 0;
    }

    public final void d0(boolean z) {
        if (this.t < 1) {
            this.t = 1;
        }
        if (!z && !this.v) {
            this.u = false;
        }
        if (this.t == 1) {
            if (z && this.u && !this.v && this.m != null && this.l != null) {
                o();
            }
            if (!this.v) {
                this.u = false;
            }
        }
        this.t--;
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
        return getScrollingChildHelper().c(i, i2, iArr, iArr2, 0);
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
        ArrayList arrayList = this.n;
        int size = arrayList.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ((h) arrayList.get(i)).c(canvas, this);
        }
        EdgeEffect edgeEffect = this.F;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.h ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.F;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.G;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.h) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.G;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.H;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.h ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.H;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.I;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.h) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.I;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(iSave4);
        }
        if ((z || this.J == null || arrayList.size() <= 0 || !this.J.f()) ? z : true) {
            WeakHashMap weakHashMap = xr0.a;
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
        this.c.j(H(view));
        if (nVar.j()) {
            this.f.n(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z) {
            this.f.m(view, -1, true);
            return;
        }
        po poVar = this.f;
        int iIndexOfChild = ((gd0) poVar.c).a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            ((fc) poVar.d).i(iIndexOfChild);
            poVar.I(view);
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(h hVar) {
        k kVar = this.m;
        if (kVar != null) {
            kVar.c("Cannot add item decoration during a scroll  or layout");
        }
        ArrayList arrayList = this.n;
        if (arrayList.isEmpty()) {
            setWillNotDraw(false);
        }
        arrayList.add(hVar);
        N();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        k kVar = this.m;
        if (kVar != null) {
            return kVar.r();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + y());
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        k kVar = this.m;
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
        return this.l;
    }

    @Override // android.view.View
    public int getBaseline() {
        k kVar = this.m;
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
        return this.h;
    }

    public be0 getCompatAccessibilityDelegate() {
        return this.l0;
    }

    public kd0 getEdgeEffectFactory() {
        return this.E;
    }

    public ld0 getItemAnimator() {
        return this.J;
    }

    public int getItemDecorationCount() {
        return this.n.size();
    }

    public k getLayoutManager() {
        return this.m;
    }

    public int getMaxFlingVelocity() {
        return this.U;
    }

    public int getMinFlingVelocity() {
        return this.T;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public od0 getOnFlingListener() {
        return this.S;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.a0;
    }

    public sd0 getRecycledViewPool() {
        return this.c.c();
    }

    public int getScrollState() {
        return this.K;
    }

    public final void h(String str) {
        if (L()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + y());
        }
        if (this.D > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(ExtensionRequestData.EMPTY_VALUE + y()));
        }
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().f(0);
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.q;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.v;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().d;
    }

    public final void j() {
        int iG = this.f.G();
        for (int i = 0; i < iG; i++) {
            n nVarI = I(this.f.F(i));
            if (!nVarI.o()) {
                nVarI.d = -1;
                nVarI.g = -1;
            }
        }
        l lVar = this.c;
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
        EdgeEffect edgeEffect = this.F;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            zIsFinished = false;
        } else {
            this.F.onRelease();
            zIsFinished = this.F.isFinished();
        }
        EdgeEffect edgeEffect2 = this.H;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.H.onRelease();
            zIsFinished |= this.H.isFinished();
        }
        EdgeEffect edgeEffect3 = this.G;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.G.onRelease();
            zIsFinished |= this.G.isFinished();
        }
        EdgeEffect edgeEffect4 = this.I;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.I.onRelease();
            zIsFinished |= this.I.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public final void l() {
        o2 o2Var = this.e;
        if (!this.s || this.A) {
            int i = ap0.a;
            Trace.beginSection("RV FullInvalidate");
            o();
            Trace.endSection();
            return;
        }
        if (o2Var.i()) {
            o2Var.getClass();
            if (o2Var.i()) {
                int i2 = ap0.a;
                Trace.beginSection("RV FullInvalidate");
                o();
                Trace.endSection();
            }
        }
    }

    public final void m(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = xr0.a;
        setMeasuredDimension(k.g(i, paddingRight, getMinimumWidth()), k.g(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public final void n(View view) {
        I(view);
        ArrayList arrayList = this.z;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((nd0) this.z.get(size)).getClass();
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o() {
        /*
            Method dump skipped, instruction units count: 1026
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005a  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.C = r0
            r1 = 1
            r5.q = r1
            boolean r2 = r5.s
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.s = r2
            androidx.recyclerview.widget.k r2 = r5.m
            if (r2 == 0) goto L1e
            r2.g = r1
        L1e:
            r5.k0 = r0
            java.lang.ThreadLocal r0 = defpackage.bv.f
            java.lang.Object r1 = r0.get()
            bv r1 = (defpackage.bv) r1
            r5.c0 = r1
            if (r1 != 0) goto L68
            bv r1 = new bv
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.b = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.e = r2
            r5.c0 = r1
            java.util.WeakHashMap r1 = defpackage.xr0.a
            android.view.Display r1 = r5.getDisplay()
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L5a
            if (r1 == 0) goto L5a
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L5a
            goto L5c
        L5a:
            r1 = 1114636288(0x42700000, float:60.0)
        L5c:
            bv r2 = r5.c0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.d = r3
            r0.set(r2)
        L68:
            bv r0 = r5.c0
            java.util.ArrayList r0 = r0.b
            r0.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        d dVar;
        super.onDetachedFromWindow();
        ld0 ld0Var = this.J;
        if (ld0Var != null) {
            ld0Var.e();
        }
        setScrollState(0);
        ae0 ae0Var = this.b0;
        ae0Var.h.removeCallbacks(ae0Var);
        ae0Var.d.abortAnimation();
        k kVar = this.m;
        if (kVar != null && (dVar = kVar.e) != null) {
            dVar.d();
        }
        this.q = false;
        k kVar2 = this.m;
        if (kVar2 != null) {
            kVar2.g = false;
            kVar2.O(this);
        }
        this.r0.clear();
        removeCallbacks(this.s0);
        this.g.getClass();
        while (fs0.d.a() != null) {
        }
        bv bvVar = this.c0;
        if (bvVar != null) {
            bvVar.b.remove(this);
            this.c0 = null;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.n;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.k r0 = r5.m
            r1 = 0
            if (r0 != 0) goto L7
            goto L79
        L7:
            boolean r0 = r5.v
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
            androidx.recyclerview.widget.k r0 = r5.m
            boolean r0 = r0.e()
            if (r0 == 0) goto L2e
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2f
        L2e:
            r0 = r2
        L2f:
            androidx.recyclerview.widget.k r3 = r5.m
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
            androidx.recyclerview.widget.k r3 = r5.m
            boolean r3 = r3.e()
            if (r3 == 0) goto L59
            float r0 = -r0
            goto L3e
        L59:
            androidx.recyclerview.widget.k r3 = r5.m
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
            float r2 = r5.V
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.W
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
        if (!this.v) {
            this.p = null;
            if (B(motionEvent)) {
                W();
                setScrollState(0);
                return true;
            }
            k kVar = this.m;
            if (kVar != null) {
                boolean zD = kVar.d();
                boolean zE = this.m.e();
                if (this.M == null) {
                    this.M = VelocityTracker.obtain();
                }
                this.M.addMovement(motionEvent);
                int actionMasked = motionEvent.getActionMasked();
                int actionIndex = motionEvent.getActionIndex();
                if (actionMasked == 0) {
                    if (this.w) {
                        this.w = false;
                    }
                    this.L = motionEvent.getPointerId(0);
                    int x = (int) (motionEvent.getX() + 0.5f);
                    this.P = x;
                    this.N = x;
                    int y = (int) (motionEvent.getY() + 0.5f);
                    this.Q = y;
                    this.O = y;
                    if (this.K == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        e0(1);
                    }
                    int[] iArr = this.p0;
                    iArr[1] = 0;
                    iArr[0] = 0;
                    int i = zD;
                    if (zE) {
                        i = (zD ? 1 : 0) | 2;
                    }
                    getScrollingChildHelper().g(i, 0);
                } else if (actionMasked == 1) {
                    this.M.clear();
                    e0(0);
                } else if (actionMasked == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.L);
                    if (iFindPointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.L + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    int x2 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
                    int y2 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
                    if (this.K != 1) {
                        int i2 = x2 - this.N;
                        int i3 = y2 - this.O;
                        if (!zD || Math.abs(i2) <= this.R) {
                            z = false;
                        } else {
                            this.P = x2;
                            z = true;
                        }
                        if (zE && Math.abs(i3) > this.R) {
                            this.Q = y2;
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
                    this.L = motionEvent.getPointerId(actionIndex);
                    int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.P = x3;
                    this.N = x3;
                    int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.Q = y3;
                    this.O = y3;
                } else if (actionMasked == 6) {
                    R(motionEvent);
                }
                if (this.K == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = ap0.a;
        Trace.beginSection("RV OnLayout");
        o();
        Trace.endSection();
        this.s = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        k kVar = this.m;
        if (kVar == null) {
            m(i, i2);
            return;
        }
        boolean zJ = kVar.J();
        yd0 yd0Var = this.e0;
        if (!zJ) {
            if (this.r) {
                this.m.b.m(i, i2);
                return;
            }
            if (yd0Var.k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            g gVar = this.l;
            if (gVar != null) {
                yd0Var.e = gVar.a();
            } else {
                yd0Var.e = 0;
            }
            c0();
            this.m.b.m(i, i2);
            d0(false);
            yd0Var.g = false;
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.m.b.m(i, i2);
        if ((mode == 1073741824 && mode2 == 1073741824) || this.l == null) {
            return;
        }
        if (yd0Var.d == 1) {
            p();
        }
        this.m.p0(i, i2);
        yd0Var.i = true;
        q();
        this.m.r0(i, i2);
        if (this.m.u0()) {
            this.m.p0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            yd0Var.i = true;
            q();
            this.m.r0(i, i2);
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
        this.d = savedState;
        super.onRestoreInstanceState(savedState.b);
        k kVar = this.m;
        if (kVar == null || (parcelable2 = this.d.d) == null) {
            return;
        }
        kVar.b0(parcelable2);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.d;
        if (savedState2 != null) {
            savedState.d = savedState2.d;
            return savedState;
        }
        k kVar = this.m;
        if (kVar != null) {
            savedState.d = kVar.c0();
            return savedState;
        }
        savedState.d = null;
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.I = null;
        this.G = null;
        this.H = null;
        this.F = null;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r31) {
        /*
            Method dump skipped, instruction units count: 1194
            To view this dump change 'Code comments level' option to 'DEBUG'
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p() {
        /*
            Method dump skipped, instruction units count: 1350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.p():void");
    }

    public final void q() {
        c0();
        P();
        yd0 yd0Var = this.e0;
        yd0Var.a(6);
        this.e.c();
        yd0Var.e = this.l.a();
        yd0Var.c = 0;
        yd0Var.g = false;
        this.m.Z(this.c, yd0Var);
        yd0Var.f = false;
        this.d = null;
        yd0Var.j = yd0Var.j && this.J != null;
        yd0Var.d = 4;
        Q(true);
        d0(false);
    }

    public final boolean r(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().c(i, i2, iArr, iArr2, i3);
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

    public void removeOnChildAttachStateChangeListener(nd0 nd0Var) {
        ArrayList arrayList = this.z;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(nd0Var);
    }

    public void removeOnItemTouchListener(pd0 pd0Var) {
        this.o.remove(pd0Var);
        if (this.p == pd0Var) {
            this.p = null;
        }
    }

    public void removeOnScrollListener(qd0 qd0Var) {
        ArrayList arrayList = this.g0;
        if (arrayList != null) {
            arrayList.remove(qd0Var);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        d dVar = this.m.e;
        if ((dVar == null || !dVar.e) && !L() && view2 != null) {
            V(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.m.j0(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ArrayList arrayList = this.o;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((pd0) arrayList.get(i)).getClass();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.t != 0 || this.v) {
            this.u = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().d(i, i2, i3, i4, iArr, i5, iArr2);
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        k kVar = this.m;
        if (kVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.v) {
            return;
        }
        boolean zD = kVar.d();
        boolean zE = this.m.e();
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
            this.x |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        }
    }

    public void setAccessibilityDelegateCompat(be0 be0Var) {
        this.l0 = be0Var;
        xr0.p(this, be0Var);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        g gVar2 = this.l;
        ud0 ud0Var = this.b;
        if (gVar2 != null) {
            gVar2.a.unregisterObserver(ud0Var);
            this.l.getClass();
        }
        ld0 ld0Var = this.J;
        if (ld0Var != null) {
            ld0Var.e();
        }
        k kVar = this.m;
        l lVar = this.c;
        if (kVar != null) {
            kVar.f0(lVar);
            this.m.g0(lVar);
        }
        lVar.a.clear();
        lVar.d();
        o2 o2Var = this.e;
        o2Var.o((ArrayList) o2Var.c);
        o2Var.o((ArrayList) o2Var.d);
        g gVar3 = this.l;
        this.l = gVar;
        if (gVar != null) {
            gVar.a.registerObserver(ud0Var);
        }
        g gVar4 = this.l;
        lVar.a.clear();
        lVar.d();
        sd0 sd0VarC = lVar.c();
        if (gVar3 != null) {
            sd0VarC.b--;
        }
        if (sd0VarC.b == 0) {
            int i = 0;
            while (true) {
                SparseArray sparseArray = sd0VarC.a;
                if (i >= sparseArray.size()) {
                    break;
                }
                ((rd0) sparseArray.valueAt(i)).a.clear();
                i++;
            }
        }
        if (gVar4 != null) {
            sd0VarC.b++;
        }
        this.e0.f = true;
        T(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(jd0 jd0Var) {
        if (jd0Var == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(false);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.h) {
            this.I = null;
            this.G = null;
            this.H = null;
            this.F = null;
        }
        this.h = z;
        super.setClipToPadding(z);
        if (this.s) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(kd0 kd0Var) {
        kd0Var.getClass();
        this.E = kd0Var;
        this.I = null;
        this.G = null;
        this.H = null;
        this.F = null;
    }

    public void setHasFixedSize(boolean z) {
        this.r = z;
    }

    public void setItemAnimator(ld0 ld0Var) {
        ld0 ld0Var2 = this.J;
        if (ld0Var2 != null) {
            ld0Var2.e();
            this.J.a = null;
        }
        this.J = ld0Var;
        if (ld0Var != null) {
            ld0Var.a = this.j0;
        }
    }

    public void setItemViewCacheSize(int i) {
        l lVar = this.c;
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
        if (kVar == this.m) {
            return;
        }
        setScrollState(0);
        ae0 ae0Var = this.b0;
        ae0Var.h.removeCallbacks(ae0Var);
        ae0Var.d.abortAnimation();
        k kVar2 = this.m;
        if (kVar2 != null && (dVar = kVar2.e) != null) {
            dVar.d();
        }
        k kVar3 = this.m;
        l lVar = this.c;
        if (kVar3 != null) {
            ld0 ld0Var = this.J;
            if (ld0Var != null) {
                ld0Var.e();
            }
            this.m.f0(lVar);
            this.m.g0(lVar);
            lVar.a.clear();
            lVar.d();
            if (this.q) {
                k kVar4 = this.m;
                kVar4.g = false;
                kVar4.O(this);
            }
            this.m.s0(null);
            this.m = null;
        } else {
            lVar.a.clear();
            lVar.d();
        }
        po poVar = this.f;
        ((fc) poVar.d).h();
        ArrayList arrayList = (ArrayList) poVar.e;
        int size = arrayList.size() - 1;
        while (true) {
            recyclerView = ((gd0) poVar.c).a;
            if (size < 0) {
                break;
            }
            n nVarI = I((View) arrayList.get(size));
            if (nVarI != null) {
                int i = nVarI.p;
                if (recyclerView.L()) {
                    nVarI.q = i;
                    recyclerView.r0.add(nVarI);
                } else {
                    WeakHashMap weakHashMap = xr0.a;
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
        this.m = kVar;
        if (kVar != null) {
            if (kVar.b != null) {
                throw new IllegalArgumentException("LayoutManager " + kVar + " is already attached to a RecyclerView:" + kVar.b.y());
            }
            kVar.s0(this);
            if (this.q) {
                this.m.g = true;
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
        k90 scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.d) {
            WeakHashMap weakHashMap = xr0.a;
            lr0.z(scrollingChildHelper.c);
        }
        scrollingChildHelper.d = z;
    }

    public void setOnFlingListener(od0 od0Var) {
        this.S = od0Var;
    }

    @Deprecated
    public void setOnScrollListener(qd0 qd0Var) {
        this.f0 = qd0Var;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.a0 = z;
    }

    public void setRecycledViewPool(sd0 sd0Var) {
        l lVar = this.c;
        if (lVar.g != null) {
            r1.b--;
        }
        lVar.g = sd0Var;
        if (sd0Var == null || lVar.h.getAdapter() == null) {
            return;
        }
        lVar.g.b++;
    }

    public void setScrollState(int i) {
        d dVar;
        if (i == this.K) {
            return;
        }
        this.K = i;
        if (i != 2) {
            ae0 ae0Var = this.b0;
            ae0Var.h.removeCallbacks(ae0Var);
            ae0Var.d.abortAnimation();
            k kVar = this.m;
            if (kVar != null && (dVar = kVar.e) != null) {
                dVar.d();
            }
        }
        k kVar2 = this.m;
        if (kVar2 != null) {
            kVar2.d0(i);
        }
        qd0 qd0Var = this.f0;
        if (qd0Var != null) {
            qd0Var.a(this, i);
        }
        ArrayList arrayList = this.g0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((qd0) this.g0.get(size)).a(this, i);
            }
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 0) {
            if (i == 1) {
                this.R = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
        }
        this.R = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(zd0 zd0Var) {
        this.c.getClass();
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
        if (z != this.v) {
            h("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.v = false;
                if (this.u && this.m != null && this.l != null) {
                    requestLayout();
                }
                this.u = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.v = true;
            this.w = true;
            setScrollState(0);
            ae0 ae0Var = this.b0;
            ae0Var.h.removeCallbacks(ae0Var);
            ae0Var.d.abortAnimation();
            k kVar = this.m;
            if (kVar == null || (dVar = kVar.e) == null) {
                return;
            }
            dVar.d();
        }
    }

    public final void t(int i, int i2) {
        this.D++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        qd0 qd0Var = this.f0;
        if (qd0Var != null) {
            qd0Var.b(this, i, i2);
        }
        ArrayList arrayList = this.g0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((qd0) this.g0.get(size)).b(this, i, i2);
            }
        }
        this.D--;
    }

    public final void u() {
        if (this.I != null) {
            return;
        }
        this.E.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.I = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void v() {
        if (this.F != null) {
            return;
        }
        this.E.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.F = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void w() {
        if (this.H != null) {
            return;
        }
        this.E.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.H = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void x() {
        if (this.G != null) {
            return;
        }
        this.E.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.G = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String y() {
        return " " + super.toString() + ", adapter:" + this.l + ", layout:" + this.m + ", context:" + getContext();
    }

    public final void z(yd0 yd0Var) {
        if (getScrollState() != 2) {
            yd0Var.getClass();
            return;
        }
        OverScroller overScroller = this.b0.d;
        overScroller.getFinalX();
        overScroller.getCurrX();
        yd0Var.getClass();
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
        this.b = new ud0(this);
        this.c = new l(this);
        this.g = new e5(24);
        this.i = new Rect();
        this.j = new Rect();
        this.k = new RectF();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.t = 0;
        this.A = false;
        this.B = false;
        this.C = 0;
        this.D = 0;
        this.E = new kd0();
        mj mjVar = new mj();
        mjVar.a = null;
        mjVar.b = new ArrayList();
        mjVar.c = 120L;
        mjVar.d = 120L;
        mjVar.e = 250L;
        mjVar.f = 250L;
        mjVar.g = true;
        mjVar.h = new ArrayList();
        mjVar.i = new ArrayList();
        mjVar.j = new ArrayList();
        mjVar.k = new ArrayList();
        mjVar.l = new ArrayList();
        mjVar.m = new ArrayList();
        mjVar.n = new ArrayList();
        mjVar.o = new ArrayList();
        mjVar.p = new ArrayList();
        mjVar.q = new ArrayList();
        mjVar.r = new ArrayList();
        this.J = mjVar;
        this.K = 0;
        this.L = -1;
        this.V = Float.MIN_VALUE;
        this.W = Float.MIN_VALUE;
        this.a0 = true;
        this.b0 = new ae0(this);
        this.d0 = new zu();
        yd0 yd0Var = new yd0();
        yd0Var.a = -1;
        yd0Var.b = 0;
        yd0Var.c = 0;
        yd0Var.d = 1;
        yd0Var.e = 0;
        yd0Var.f = false;
        yd0Var.g = false;
        yd0Var.h = false;
        yd0Var.i = false;
        yd0Var.j = false;
        yd0Var.k = false;
        this.e0 = yd0Var;
        this.h0 = false;
        this.i0 = false;
        gd0 gd0Var = new gd0(this);
        this.j0 = gd0Var;
        this.k0 = false;
        this.m0 = new int[2];
        this.o0 = new int[2];
        this.p0 = new int[2];
        this.q0 = new int[2];
        this.r0 = new ArrayList();
        this.s0 = new v6(14, this);
        this.t0 = new gd0(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.R = viewConfiguration.getScaledTouchSlop();
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            Method method = bs0.a;
            fA = yr0.a(viewConfiguration);
        } else {
            fA = bs0.a(viewConfiguration, context);
        }
        this.V = fA;
        if (i3 >= 26) {
            fA2 = yr0.b(viewConfiguration);
        } else {
            fA2 = bs0.a(viewConfiguration, context);
        }
        this.W = fA2;
        this.T = viewConfiguration.getScaledMinimumFlingVelocity();
        this.U = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.J.a = gd0Var;
        this.e = new o2(new gd0(this));
        this.f = new po(new gd0(this));
        WeakHashMap weakHashMap = xr0.a;
        if ((i3 >= 26 ? or0.c(this) : 0) == 0 && i3 >= 26) {
            or0.m(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.y = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new be0(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, 0);
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, R$styleable.RecyclerView, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_android_clipToPadding, true);
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
                new qp(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R$dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R$dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R$dimen.fastscroll_margin));
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
                } else if (!strTrim.contains(".")) {
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
                        constructor = clsAsSubclass.getConstructor(v0);
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
        int[] iArr = u0;
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
        k kVar = this.m;
        if (kVar != null) {
            return kVar.t(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + y());
    }

    public void setRecyclerListener(td0 td0Var) {
    }
}
