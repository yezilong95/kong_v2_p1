package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.R$attr;
import androidx.coordinatorlayout.R$style;
import androidx.coordinatorlayout.R$styleable;
import androidx.customview.view.AbsSavedState;
import defpackage.ah;
import defpackage.ba;
import defpackage.es0;
import defpackage.fj0;
import defpackage.ih;
import defpackage.il;
import defpackage.jh;
import defpackage.jr0;
import defpackage.l3;
import defpackage.l90;
import defpackage.lr0;
import defpackage.m90;
import defpackage.n90;
import defpackage.o70;
import defpackage.wb0;
import defpackage.xr0;
import defpackage.xu0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements l90, m90 {
    public static final String u;
    public static final Class[] v;
    public static final ThreadLocal w;
    public static final l3 x;
    public static final wb0 y;
    public final ArrayList b;
    public final ba c;
    public final ArrayList d;
    public final ArrayList e;
    public final int[] f;
    public final int[] g;
    public boolean h;
    public boolean i;
    public final int[] j;
    public View k;
    public View l;
    public jh m;
    public boolean n;
    public xu0 o;
    public boolean p;
    public Drawable q;
    public ViewGroup.OnHierarchyChangeListener r;
    public o70 s;
    public final n90 t;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new d();
        public SparseArray d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i = parcel.readInt();
            int[] iArr = new int[i];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.d = new SparseArray(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.d.append(iArr[i2], parcelableArray[i2]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            SparseArray sparseArray = this.d;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.d.keyAt(i2);
                parcelableArr[i2] = (Parcelable) this.d.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        u = r0 != null ? r0.getName() : null;
        x = new l3(2);
        v = new Class[]{Context.class, AttributeSet.class};
        w = new ThreadLocal();
        y = new wb0(12);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    public static void A(View view, int i) {
        c cVar = (c) view.getLayoutParams();
        int i2 = cVar.i;
        if (i2 != i) {
            WeakHashMap weakHashMap = xr0.a;
            view.offsetLeftAndRight(i - i2);
            cVar.i = i;
        }
    }

    public static void B(View view, int i) {
        c cVar = (c) view.getLayoutParams();
        int i2 = cVar.j;
        if (i2 != i) {
            WeakHashMap weakHashMap = xr0.a;
            view.offsetTopAndBottom(i - i2);
            cVar.j = i;
        }
    }

    public static Rect k() {
        Rect rect = (Rect) y.a();
        return rect == null ? new Rect() : rect;
    }

    public static void q(int i, Rect rect, Rect rect2, c cVar, int i2, int i3) {
        int i4 = cVar.c;
        if (i4 == 0) {
            i4 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
        int i5 = cVar.d;
        if ((i5 & 7) == 0) {
            i5 |= 8388611;
        }
        if ((i5 & 112) == 0) {
            i5 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i5, i);
        int i6 = absoluteGravity & 7;
        int i7 = absoluteGravity & 112;
        int i8 = absoluteGravity2 & 7;
        int i9 = absoluteGravity2 & 112;
        int iWidth = i8 != 1 ? i8 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i9 != 16 ? i9 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i6 == 1) {
            iWidth -= i2 / 2;
        } else if (i6 != 5) {
            iWidth -= i2;
        }
        if (i7 == 16) {
            iHeight -= i3 / 2;
        } else if (i7 != 80) {
            iHeight -= i3;
        }
        rect2.set(iWidth, iHeight, i2 + iWidth, i3 + iHeight);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static c s(View view) {
        c cVar = (c) view.getLayoutParams();
        if (!cVar.b) {
            if (view instanceof a) {
                Behavior behavior = ((a) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                Behavior behavior2 = cVar.a;
                if (behavior2 != behavior) {
                    if (behavior2 != null) {
                        behavior2.j();
                    }
                    cVar.a = behavior;
                    cVar.b = true;
                    if (behavior != null) {
                        behavior.g(cVar);
                    }
                }
                cVar.b = true;
                return cVar;
            }
            b bVar = null;
            for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                bVar = (b) superclass.getAnnotation(b.class);
                if (bVar != null) {
                    break;
                }
            }
            if (bVar != null) {
                try {
                    Behavior behavior3 = (Behavior) bVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    Behavior behavior4 = cVar.a;
                    if (behavior4 != behavior3) {
                        if (behavior4 != null) {
                            behavior4.j();
                        }
                        cVar.a = behavior3;
                        cVar.b = true;
                        if (behavior3 != null) {
                            behavior3.g(cVar);
                        }
                    }
                } catch (Exception e) {
                    Log.e("CoordinatorLayout", "Default behavior class " + bVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e);
                }
            }
            cVar.b = true;
        }
        return cVar;
    }

    public final void C() {
        WeakHashMap weakHashMap = xr0.a;
        if (!getFitsSystemWindows()) {
            lr0.u(this, null);
            return;
        }
        if (this.s == null) {
            this.s = new o70(15, this);
        }
        lr0.u(this, this.s);
        setSystemUiVisibility(1280);
    }

    @Override // defpackage.l90
    public final void b(View view, View view2, int i, int i2) {
        n90 n90Var = this.t;
        if (i2 == 1) {
            n90Var.b = i;
        } else {
            n90Var.a = i;
        }
        this.l = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ((c) getChildAt(i3).getLayoutParams()).getClass();
        }
    }

    @Override // defpackage.l90
    public final void c(View view, int i) {
        n90 n90Var = this.t;
        if (i == 1) {
            n90Var.b = 0;
        } else {
            n90Var.a = 0;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            c cVar = (c) childAt.getLayoutParams();
            if (cVar.a(i)) {
                Behavior behavior = cVar.a;
                if (behavior != null) {
                    behavior.u(this, childAt, view, i);
                }
                if (i == 0) {
                    cVar.m = false;
                } else if (i == 1) {
                    cVar.n = false;
                }
                cVar.o = false;
            }
        }
        this.l = null;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof c) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        Behavior behavior = ((c) view.getLayoutParams()).a;
        if (behavior != null) {
            behavior.getClass();
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.q;
        if ((drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState)) {
            invalidate();
        }
    }

    @Override // defpackage.l90
    public final void e(View view, int i, int i2, int[] iArr, int i3) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.a(i3) && (behavior = cVar.a) != null) {
                    int[] iArr2 = this.f;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.o(this, childAt, view, i, i2, iArr2, i3);
                    iMax = i > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i2 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z) {
            u(1);
        }
    }

    @Override // defpackage.m90
    public final void f(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        Behavior behavior;
        int childCount = getChildCount();
        int iMax = 0;
        int iMax2 = 0;
        boolean z = false;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.a(i5) && (behavior = cVar.a) != null) {
                    int[] iArr2 = this.f;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.p(this, childAt, i2, i3, i4, iArr2);
                    iMax = i3 > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i4 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z = true;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + iMax2;
        if (z) {
            u(1);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public final List<View> getDependencySortedChildren() {
        y();
        return Collections.unmodifiableList(this.b);
    }

    public final xu0 getLastWindowInsets() {
        return this.o;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        n90 n90Var = this.t;
        return n90Var.b | n90Var.a;
    }

    public Drawable getStatusBarBackground() {
        return this.q;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    @Override // defpackage.l90
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
        f(view, i, i2, i3, i4, 0, this.g);
    }

    @Override // defpackage.l90
    public final boolean j(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                Behavior behavior = cVar.a;
                if (behavior != null) {
                    boolean zT = behavior.t(this, childAt, view, view2, i, i2);
                    z |= zT;
                    if (i2 == 0) {
                        cVar.m = zT;
                    } else if (i2 == 1) {
                        cVar.n = zT;
                    }
                } else if (i2 == 0) {
                    cVar.m = false;
                } else if (i2 == 1) {
                    cVar.n = false;
                }
            }
        }
        return z;
    }

    public final void l(c cVar, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) cVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) cVar).bottomMargin));
        rect.set(iMax, iMax2, i + iMax, i2 + iMax2);
    }

    public final void m(View view) {
        List list = (List) ((fj0) this.c.b).getOrDefault(view, null);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            View view2 = (View) list.get(i);
            Behavior behavior = ((c) view2.getLayoutParams()).a;
            if (behavior != null) {
                behavior.h(this, view2, view);
            }
        }
    }

    public final void n(View view, Rect rect, boolean z) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            p(rect, view);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public final ArrayList o(View view) {
        fj0 fj0Var = (fj0) this.c.b;
        int i = fj0Var.d;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList2 = (ArrayList) fj0Var.j(i2);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fj0Var.h(i2));
            }
        }
        ArrayList arrayList3 = this.e;
        arrayList3.clear();
        if (arrayList != null) {
            arrayList3.addAll(arrayList);
        }
        return arrayList3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = 0;
        z(false);
        if (this.n) {
            if (this.m == null) {
                this.m = new jh(i, this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.m);
        }
        if (this.o == null) {
            WeakHashMap weakHashMap = xr0.a;
            if (getFitsSystemWindows()) {
                jr0.c(this);
            }
        }
        this.i = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        z(false);
        if (this.n && this.m != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.m);
        }
        View view = this.l;
        if (view != null) {
            c(view, 0);
        }
        this.i = false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.p || this.q == null) {
            return;
        }
        xu0 xu0Var = this.o;
        int iD = xu0Var != null ? xu0Var.d() : 0;
        if (iD > 0) {
            this.q.setBounds(0, 0, getWidth(), iD);
            this.q.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z(true);
        }
        boolean zX = x(motionEvent, 0);
        if (actionMasked != 1 && actionMasked != 3) {
            return zX;
        }
        z(true);
        return zX;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Behavior behavior;
        WeakHashMap weakHashMap = xr0.a;
        int layoutDirection = getLayoutDirection();
        ArrayList arrayList = this.b;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) arrayList.get(i5);
            if (view.getVisibility() != 8 && ((behavior = ((c) view.getLayoutParams()).a) == null || !behavior.l(this, view, layoutDirection))) {
                v(view, layoutDirection);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0190  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.a(0)) {
                    Behavior behavior = cVar.a;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean zN = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.a(0) && (behavior = cVar.a) != null) {
                    zN |= behavior.n(view);
                }
            }
        }
        return zN;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        e(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        i(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        b(view, view2, i, 0);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        SparseArray sparseArray = savedState.d;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = s(childAt).a;
            if (id != -1 && behavior != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                behavior.r(childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableS;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = ((c) childAt.getLayoutParams()).a;
            if (id != -1 && behavior != null && (parcelableS = behavior.s(childAt)) != null) {
                sparseArray.append(id, parcelableS);
            }
        }
        savedState.d = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return j(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        c(view, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015 A[PHI: r3
      0x0015: PHI (r3v4 boolean) = (r3v2 boolean), (r3v5 boolean) binds: [B:10:0x0022, B:5:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r1.getActionMasked()
            android.view.View r3 = r0.k
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L17
            boolean r3 = r0.x(r1, r4)
            if (r3 == 0) goto L15
            goto L18
        L15:
            r6 = r5
            goto L2a
        L17:
            r3 = r5
        L18:
            android.view.View r6 = r0.k
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.c r6 = (androidx.coordinatorlayout.widget.c) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r6 = r6.a
            if (r6 == 0) goto L15
            android.view.View r7 = r0.k
            boolean r6 = r6.v(r0, r7, r1)
        L2a:
            android.view.View r7 = r0.k
            r8 = 0
            if (r7 != 0) goto L35
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L48
        L35:
            if (r3 == 0) goto L48
            long r9 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r11 = r9
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L48:
            if (r8 == 0) goto L4d
            r8.recycle()
        L4d:
            if (r2 == r4) goto L54
            r1 = 3
            if (r2 != r1) goto L53
            goto L54
        L53:
            return r6
        L54:
            r0.z(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(Rect rect, View view) {
        ThreadLocal threadLocal = es0.a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal threadLocal2 = es0.a;
        Matrix matrix = (Matrix) threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        es0.a(this, view, matrix);
        ThreadLocal threadLocal3 = es0.b;
        RectF rectF = (RectF) threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public final int r(int i) {
        int[] iArr = this.j;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i);
            return 0;
        }
        if (i >= 0 && i < iArr.length) {
            return iArr[i];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i + " out of range for " + this);
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Behavior behavior = ((c) view.getLayoutParams()).a;
        if (behavior == null || !behavior.q(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (!z || this.h) {
            return;
        }
        z(false);
        this.h = true;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        C();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.r = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.q;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.q = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.q.setState(getDrawableState());
                }
                Drawable drawable3 = this.q;
                WeakHashMap weakHashMap = xr0.a;
                il.b(drawable3, getLayoutDirection());
                this.q.setVisible(getVisibility() == 0, false);
                this.q.setCallback(this);
            }
            WeakHashMap weakHashMap2 = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarBackgroundColor(int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i) {
        setStatusBarBackground(i != 0 ? ah.b(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.q;
        if (drawable == null || drawable.isVisible() == z) {
            return;
        }
        this.q.setVisible(z, false);
    }

    public final boolean t(View view, int i, int i2) {
        wb0 wb0Var = y;
        Rect rectK = k();
        p(rectK, view);
        try {
            return rectK.contains(i, i2);
        } finally {
            rectK.setEmpty();
            wb0Var.c(rectK);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0291 A[EDGE_INSN: B:147:0x0291->B:113:0x0291 BREAK  A[LOOP:2: B:118:0x02a9->B:136:0x02e5], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u(int r26) {
        /*
            Method dump skipped, instruction units count: 781
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.u(int):void");
    }

    public final void v(View view, int i) {
        Rect rectK;
        Rect rectK2;
        c cVar = (c) view.getLayoutParams();
        View view2 = cVar.k;
        if (view2 == null && cVar.f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        wb0 wb0Var = y;
        if (view2 != null) {
            rectK = k();
            rectK2 = k();
            try {
                p(rectK, view2);
                c cVar2 = (c) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                q(i, rectK, rectK2, cVar2, measuredWidth, measuredHeight);
                l(cVar2, rectK2, measuredWidth, measuredHeight);
                view.layout(rectK2.left, rectK2.top, rectK2.right, rectK2.bottom);
                return;
            } finally {
                rectK.setEmpty();
                wb0Var.c(rectK);
                rectK2.setEmpty();
                wb0Var.c(rectK2);
            }
        }
        int i2 = cVar.e;
        if (i2 < 0) {
            c cVar3 = (c) view.getLayoutParams();
            rectK = k();
            rectK.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) cVar3).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar3).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) cVar3).bottomMargin);
            if (this.o != null) {
                WeakHashMap weakHashMap = xr0.a;
                if (getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                    rectK.left = this.o.b() + rectK.left;
                    rectK.top = this.o.d() + rectK.top;
                    rectK.right -= this.o.c();
                    rectK.bottom -= this.o.a();
                }
            }
            rectK2 = k();
            int i3 = cVar3.c;
            if ((i3 & 7) == 0) {
                i3 |= 8388611;
            }
            if ((i3 & 112) == 0) {
                i3 |= 48;
            }
            Gravity.apply(i3, view.getMeasuredWidth(), view.getMeasuredHeight(), rectK, rectK2, i);
            view.layout(rectK2.left, rectK2.top, rectK2.right, rectK2.bottom);
            return;
        }
        c cVar4 = (c) view.getLayoutParams();
        int i4 = cVar4.c;
        if (i4 == 0) {
            i4 = 8388661;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
        int i5 = absoluteGravity & 7;
        int i6 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth2 = view.getMeasuredWidth();
        int measuredHeight2 = view.getMeasuredHeight();
        if (i == 1) {
            i2 = width - i2;
        }
        int iR = r(i2) - measuredWidth2;
        if (i5 == 1) {
            iR += measuredWidth2 / 2;
        } else if (i5 == 5) {
            iR += measuredWidth2;
        }
        int i7 = i6 != 16 ? i6 != 80 ? 0 : measuredHeight2 : measuredHeight2 / 2;
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar4).leftMargin, Math.min(iR, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) cVar4).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) cVar4).topMargin, Math.min(i7, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) cVar4).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth2 + iMax, measuredHeight2 + iMax2);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.q;
    }

    public final void w(int i, int i2, int i3, View view) {
        measureChildWithMargins(view, i, i2, i3, 0);
    }

    public final boolean x(MotionEvent motionEvent, int i) {
        int actionMasked = motionEvent.getActionMasked();
        ArrayList arrayList = this.d;
        arrayList.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            arrayList.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        l3 l3Var = x;
        if (l3Var != null) {
            Collections.sort(arrayList, l3Var);
        }
        int size = arrayList.size();
        MotionEvent motionEventObtain = null;
        boolean zK = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) arrayList.get(i3);
            Behavior behavior = ((c) view.getLayoutParams()).a;
            if (zK && actionMasked != 0) {
                if (behavior != null) {
                    if (motionEventObtain == null) {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    if (i == 0) {
                        behavior.k(this, view, motionEventObtain);
                    } else if (i == 1) {
                        behavior.v(this, view, motionEventObtain);
                    }
                }
            } else if (!zK && behavior != null) {
                if (i == 0) {
                    zK = behavior.k(this, view, motionEvent);
                } else if (i == 1) {
                    zK = behavior.v(this, view, motionEvent);
                }
                if (zK) {
                    this.k = view;
                }
            }
        }
        arrayList.clear();
        return zK;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y() {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.y():void");
    }

    public final void z(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Behavior behavior = ((c) childAt.getLayoutParams()).a;
            if (behavior != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    behavior.k(this, childAt, motionEventObtain);
                } else {
                    behavior.v(this, childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((c) getChildAt(i2).getLayoutParams()).getClass();
        }
        this.k = null;
        this.h = false;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.coordinatorLayoutStyle);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c ? new c((c) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes;
        CoordinatorLayout coordinatorLayout;
        Context context2;
        super(context, attributeSet, i);
        this.b = new ArrayList();
        this.c = new ba(1);
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new int[2];
        this.g = new int[2];
        this.t = new n90();
        if (i == 0) {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, i, 0);
        }
        TypedArray typedArray = typedArrayObtainStyledAttributes;
        if (Build.VERSION.SDK_INT < 29) {
            coordinatorLayout = this;
            context2 = context;
        } else if (i == 0) {
            coordinatorLayout = this;
            context2 = context;
            coordinatorLayout.saveAttributeDataForStyleable(context2, R$styleable.CoordinatorLayout, attributeSet, typedArray, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            context2 = context;
            coordinatorLayout = this;
            coordinatorLayout.saveAttributeDataForStyleable(context2, R$styleable.CoordinatorLayout, attributeSet, typedArray, i, 0);
        }
        int resourceId = typedArray.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context2.getResources();
            int[] intArray = resources.getIntArray(resourceId);
            coordinatorLayout.j = intArray;
            float f = resources.getDisplayMetrics().density;
            int length = intArray.length;
            for (int i2 = 0; i2 < length; i2++) {
                coordinatorLayout.j[i2] = (int) (r12[i2] * f);
            }
        }
        coordinatorLayout.q = typedArray.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        typedArray.recycle();
        C();
        super.setOnHierarchyChangeListener(new ih(this));
        WeakHashMap weakHashMap = xr0.a;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public static abstract class Behavior<V extends View> {
        public Behavior(Context context, AttributeSet attributeSet) {
        }

        public boolean e(Rect rect, View view) {
            return false;
        }

        public boolean f(View view, View view2) {
            return false;
        }

        public void g(c cVar) {
        }

        public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return false;
        }

        public void j() {
        }

        public boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return false;
        }

        public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            return false;
        }

        public boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            return false;
        }

        public boolean n(View view) {
            return false;
        }

        public void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
        }

        public void p(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
            iArr[0] = iArr[0] + i2;
            iArr[1] = iArr[1] + i3;
        }

        public boolean q(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            return false;
        }

        public Parcelable s(View view) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        public boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            return false;
        }

        public void u(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
        }

        public boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return false;
        }

        public void i(CoordinatorLayout coordinatorLayout, View view) {
        }

        public void r(View view, Parcelable parcelable) {
        }
    }
}
