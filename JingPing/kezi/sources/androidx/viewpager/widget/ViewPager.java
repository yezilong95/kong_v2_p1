package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.customview.view.AbsSavedState;
import defpackage.ah;
import defpackage.e5;
import defpackage.eb0;
import defpackage.fd0;
import defpackage.j9;
import defpackage.lr0;
import defpackage.ns0;
import defpackage.os0;
import defpackage.ps0;
import defpackage.qs0;
import defpackage.v6;
import defpackage.xr0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    public static final int[] K = {R.attr.layout_gravity};
    public static final fd0 L = new fd0(2);
    public EdgeEffect A;
    public boolean B;
    public boolean C;
    public int D;
    public ArrayList E;
    public qs0 F;
    public qs0 G;
    public ArrayList H;
    public final v6 I;
    public int J;
    public final ArrayList b;
    public final os0 c;
    public final Rect d;
    public Parcelable e;
    public Scroller f;
    public int g;
    public Drawable h;
    public final float i;
    public final float j;
    public int k;
    public boolean l;
    public boolean m;
    public int n;
    public boolean o;
    public boolean p;
    public int q;
    public int r;
    public int s;
    public float t;
    public float u;
    public float v;
    public int w;
    public VelocityTracker x;
    public int y;
    public EdgeEffect z;

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public boolean a;
        public final int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.K);
            this.b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int d;
        public final Parcelable e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.d = parcel.readInt();
            this.e = parcel.readParcelable(classLoader);
        }

        public final String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.d + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
            parcel.writeParcelable(this.e, i);
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.b = new ArrayList();
        this.c = new os0();
        this.d = new Rect();
        this.e = null;
        this.i = -3.4028235E38f;
        this.j = Float.MAX_VALUE;
        this.n = 1;
        this.w = -1;
        this.B = true;
        this.I = new v6(19, this);
        this.J = 0;
        g();
    }

    public static boolean b(int i, int i2, int i3, View view, boolean z) {
        int i4;
        if (!(view instanceof ViewGroup)) {
            return z ? false : false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int scrollX = view.getScrollX();
        int scrollY = view.getScrollY();
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            int i5 = i2 + scrollX;
            if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom() && b(i, i5 - childAt.getLeft(), i4 - childAt.getTop(), childAt, true)) {
                break;
            }
        }
        if (z || !view.canScrollHorizontally(-i)) {
        }
        return true;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.m != z) {
            this.m = z;
        }
    }

    public final boolean a(int i) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            for (ViewParent parent = viewFindFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                if (parent == this) {
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(viewFindFocus.getClass().getSimpleName());
            for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                sb.append(" => ");
                sb.append(parent2.getClass().getSimpleName());
            }
            Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i);
        boolean zRequestFocus = false;
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            Rect rect = this.d;
            if (i == 17) {
                int i2 = d(rect, viewFindNextFocus).left;
                int i3 = d(rect, viewFindFocus).left;
                if (viewFindFocus == null || i2 < i3) {
                    zRequestFocus = viewFindNextFocus.requestFocus();
                }
            } else if (i == 66) {
                int i4 = d(rect, viewFindNextFocus).left;
                int i5 = d(rect, viewFindFocus).left;
                if (viewFindFocus == null || i4 > i5) {
                    zRequestFocus = viewFindNextFocus.requestFocus();
                }
            }
        } else if (i != 17) {
        }
        if (zRequestFocus) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return zRequestFocus;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3).getVisibility() == 0) {
                    e();
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i2 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    public void addOnAdapterChangeListener(ps0 ps0Var) {
        if (this.H == null) {
            this.H = new ArrayList();
        }
        this.H.add(ps0Var);
    }

    public void addOnPageChangeListener(qs0 qs0Var) {
        if (this.E == null) {
            this.E = new ArrayList();
        }
        this.E.add(qs0Var);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addTouchables(ArrayList arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getVisibility() == 0) {
                e();
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateDefaultLayoutParams();
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        boolean z = layoutParams2.a | (view.getClass().getAnnotation(ns0.class) != null);
        layoutParams2.a = z;
        if (!this.l) {
            super.addView(view, i, layoutParams);
        } else {
            if (z) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            addViewInLayout(view, i, layoutParams);
        }
    }

    public final void c(boolean z) {
        int i = 0;
        boolean z2 = this.J == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.f.isFinished()) {
                this.f.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f.getCurrX();
                int currY = this.f.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        i(currX);
                    }
                }
            }
        }
        while (true) {
            ArrayList arrayList = this.b;
            if (i >= arrayList.size()) {
                break;
            }
            ((os0) arrayList.get(i)).getClass();
            i++;
        }
        if (z2) {
            v6 v6Var = this.I;
            if (!z) {
                v6Var.run();
            } else {
                WeakHashMap weakHashMap = xr0.a;
                postOnAnimation(v6Var);
            }
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        if (this.f.isFinished() || !this.f.computeScrollOffset()) {
            c(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f.getCurrX();
        int currY = this.f.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!i(currX)) {
                this.f.abortAnimation();
                scrollTo(0, currY);
            }
        }
        WeakHashMap weakHashMap = xr0.a;
        postInvalidateOnAnimation();
    }

    public final Rect d(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0055 A[RETURN] */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            boolean r0 = super.dispatchKeyEvent(r6)
            r1 = 1
            if (r0 != 0) goto L56
            int r0 = r6.getAction()
            r2 = 0
            if (r0 != 0) goto L3c
            int r0 = r6.getKeyCode()
            r3 = 21
            r4 = 2
            if (r0 == r3) goto L45
            r3 = 22
            if (r0 == r3) goto L36
            r3 = 61
            if (r0 == r3) goto L20
            goto L3c
        L20:
            boolean r0 = r6.hasNoModifiers()
            if (r0 == 0) goto L2b
            boolean r6 = r5.a(r4)
            goto L52
        L2b:
            boolean r6 = r6.hasModifiers(r1)
            if (r6 == 0) goto L3c
            boolean r6 = r5.a(r1)
            goto L52
        L36:
            boolean r6 = r6.hasModifiers(r4)
            if (r6 == 0) goto L3e
        L3c:
            r6 = r2
            goto L52
        L3e:
            r6 = 66
            boolean r6 = r5.a(r6)
            goto L52
        L45:
            boolean r6 = r6.hasModifiers(r4)
            if (r6 == 0) goto L4c
            goto L3c
        L4c:
            r6 = 17
            boolean r6 = r5.a(r6)
        L52:
            if (r6 == 0) goto L55
            goto L56
        L55:
            return r2
        L56:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).getVisibility() == 0) {
                e();
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        boolean zDraw = false;
        if (getOverScrollMode() != 0) {
            this.z.finish();
            this.A.finish();
        } else {
            if (!this.z.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate(getPaddingTop() + (-height), this.i * width);
                this.z.setSize(height, width);
                zDraw = this.z.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.A.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.j + 1.0f)) * width2);
                this.A.setSize(height2, width2);
                zDraw |= this.A.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        }
        if (zDraw) {
            WeakHashMap weakHashMap = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.h;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e() {
        ArrayList arrayList = this.b;
        if (arrayList.size() <= 0) {
            return;
        }
        ((os0) arrayList.get(0)).getClass();
        throw null;
    }

    public final os0 f() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.b;
            if (i >= arrayList.size()) {
                return null;
            }
            os0 os0Var = (os0) arrayList.get(i);
            if (os0Var.a == 0) {
                return os0Var;
            }
            i++;
        }
    }

    public final void g() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f = new Scroller(context, L);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.s = viewConfiguration.getScaledPagingTouchSlop();
        viewConfiguration.getScaledMaximumFlingVelocity();
        this.z = new EdgeEffect(context);
        this.A = new EdgeEffect(context);
        this.y = (int) (2.0f * f);
        this.q = (int) (f * 16.0f);
        xr0.p(this, new j9(5, this));
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        lr0.u(this, new e5(this));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public eb0 getAdapter() {
        return null;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        throw null;
    }

    public int getCurrentItem() {
        return 0;
    }

    public int getOffscreenPageLimit() {
        return this.n;
    }

    public int getPageMargin() {
        return this.g;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r14 = r11.D
            r0 = 0
            r1 = 1
            if (r14 <= 0) goto L6c
            int r14 = r11.getScrollX()
            int r2 = r11.getPaddingLeft()
            int r3 = r11.getPaddingRight()
            int r4 = r11.getWidth()
            int r5 = r11.getChildCount()
            r6 = r0
        L1b:
            if (r6 >= r5) goto L6c
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r8 = (androidx.viewpager.widget.ViewPager.LayoutParams) r8
            boolean r9 = r8.a
            if (r9 != 0) goto L2c
            goto L69
        L2c:
            int r8 = r8.b
            r8 = r8 & 7
            if (r8 == r1) goto L50
            r9 = 3
            if (r8 == r9) goto L4a
            r9 = 5
            if (r8 == r9) goto L3a
            r8 = r2
            goto L5d
        L3a:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredWidth()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredWidth()
            int r3 = r3 + r9
        L46:
            r10 = r8
            r8 = r2
            r2 = r10
            goto L5d
        L4a:
            int r8 = r7.getWidth()
            int r8 = r8 + r2
            goto L5d
        L50:
            int r8 = r7.getMeasuredWidth()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
            goto L46
        L5d:
            int r2 = r2 + r14
            int r9 = r7.getLeft()
            int r2 = r2 - r9
            if (r2 == 0) goto L68
            r7.offsetLeftAndRight(r2)
        L68:
            r2 = r8
        L69:
            int r6 = r6 + 1
            goto L1b
        L6c:
            qs0 r14 = r11.F
            if (r14 == 0) goto L73
            r14.a(r12, r13)
        L73:
            java.util.ArrayList r14 = r11.E
            if (r14 == 0) goto L8d
            int r14 = r14.size()
        L7b:
            if (r0 >= r14) goto L8d
            java.util.ArrayList r2 = r11.E
            java.lang.Object r2 = r2.get(r0)
            qs0 r2 = (defpackage.qs0) r2
            if (r2 == 0) goto L8a
            r2.a(r12, r13)
        L8a:
            int r0 = r0 + 1
            goto L7b
        L8d:
            qs0 r14 = r11.G
            if (r14 == 0) goto L94
            r14.a(r12, r13)
        L94:
            r11.C = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.h(int, float, int):void");
    }

    public final boolean i(int i) {
        int i2;
        ArrayList arrayList = this.b;
        if (arrayList.size() == 0) {
            if (!this.B) {
                this.C = false;
                h(0, 0.0f, 0);
                if (!this.C) {
                    throw new IllegalStateException("onPageScrolled did not call superclass implementation");
                }
            }
            return false;
        }
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f = clientWidth > 0 ? this.g / clientWidth : 0.0f;
        int i3 = -1;
        boolean z = true;
        float f2 = 0.0f;
        int i4 = 0;
        os0 os0Var = null;
        while (i4 < arrayList.size()) {
            os0 os0Var2 = (os0) arrayList.get(i4);
            if (!z && os0Var2.a != (i2 = i3 + 1)) {
                os0 os0Var3 = this.c;
                os0Var3.b = f2 + 0.0f + f;
                os0Var3.a = i2;
                throw null;
            }
            f2 = os0Var2.b;
            float f3 = f2 + 0.0f + f;
            if (!z && scrollX < f2) {
                break;
            }
            if (scrollX < f3 || i4 == arrayList.size() - 1) {
                os0Var = os0Var2;
                break;
            }
            i3 = os0Var2.a;
            i4++;
            z = false;
            os0Var = os0Var2;
        }
        int clientWidth2 = getClientWidth();
        int i5 = this.g;
        int i6 = clientWidth2 + i5;
        float f4 = clientWidth2;
        int i7 = os0Var.a;
        float f5 = ((i / f4) - os0Var.b) / (0.0f + (i5 / f4));
        this.C = false;
        h(i7, f5, (int) (i6 * f5));
        if (this.C) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final void j(int i, int i2, int i3, int i4) {
        if (i2 > 0 && !this.b.isEmpty()) {
            if (!this.f.isFinished()) {
                this.f.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)) * (((i - getPaddingLeft()) - getPaddingRight()) + i3)), getScrollY());
                return;
            }
        }
        os0 os0VarF = f();
        int iMin = (int) ((os0VarF != null ? Math.min(os0VarF.b, this.j) : 0.0f) * ((i - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            c(false);
            scrollTo(iMin, getScrollY());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.B = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.I);
        Scroller scroller = this.f;
        if (scroller != null && !scroller.isFinished()) {
            this.f.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g <= 0 || this.h == null) {
            return;
        }
        this.b.size();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
    
        if (r13.p != false) goto L84;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        int i3;
        setMeasuredDimension(View.getDefaultSize(0, i), View.getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.r = Math.min(measuredWidth / 10, this.q);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i4 = 0;
        while (true) {
            int i5 = 1073741824;
            if (i4 >= childCount) {
                break;
            }
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8 && (layoutParams2 = (LayoutParams) childAt.getLayoutParams()) != null && layoutParams2.a) {
                int i6 = layoutParams2.b;
                int i7 = i6 & 7;
                int i8 = i6 & 112;
                boolean z = true;
                boolean z2 = i8 == 48 || i8 == 80;
                if (i7 != 3 && i7 != 5) {
                    z = false;
                }
                int i9 = Integer.MIN_VALUE;
                if (z2) {
                    i3 = Integer.MIN_VALUE;
                    i9 = 1073741824;
                } else {
                    i3 = z ? 1073741824 : Integer.MIN_VALUE;
                }
                int i10 = ((ViewGroup.LayoutParams) layoutParams2).width;
                if (i10 != -2) {
                    if (i10 == -1) {
                        i10 = paddingLeft;
                    }
                    i9 = 1073741824;
                } else {
                    i10 = paddingLeft;
                }
                int i11 = ((ViewGroup.LayoutParams) layoutParams2).height;
                if (i11 == -2) {
                    i11 = measuredHeight;
                    i5 = i3;
                } else if (i11 == -1) {
                    i11 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i10, i9), View.MeasureSpec.makeMeasureSpec(i11, i5));
                if (z2) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i4++;
        }
        View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.k = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.l = false;
        int childCount2 = getChildCount();
        for (int i12 = 0; i12 < childCount2; i12++) {
            View childAt2 = getChildAt(i12);
            if (childAt2.getVisibility() != 8 && ((layoutParams = (LayoutParams) childAt2.getLayoutParams()) == null || !layoutParams.a)) {
                layoutParams.getClass();
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * 0.0f), 1073741824), this.k);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3;
        int i4;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i4 = childCount;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            i3 = -1;
            i4 = -1;
        }
        while (i2 != i4) {
            if (getChildAt(i2).getVisibility() == 0) {
                e();
            }
            i2 += i3;
        }
        return false;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        this.e = savedState.e;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.d = 0;
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            int i5 = this.g;
            j(i, i3, i5, i5);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            motionEvent.getEdgeFlags();
        }
        return false;
    }

    public void removeOnAdapterChangeListener(ps0 ps0Var) {
        ArrayList arrayList = this.H;
        if (arrayList != null) {
            arrayList.remove(ps0Var);
        }
    }

    public void removeOnPageChangeListener(qs0 qs0Var) {
        ArrayList arrayList = this.E;
        if (arrayList != null) {
            arrayList.remove(qs0Var);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.l) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(eb0 eb0Var) {
        ArrayList arrayList = this.H;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = this.H.size();
        for (int i = 0; i < size; i++) {
            ((ps0) this.H.get(i)).c(this);
        }
    }

    public void setCurrentItem(int i) {
        setScrollingCacheEnabled(false);
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to 1");
            i = 1;
        }
        if (i != this.n) {
            this.n = i;
        }
    }

    @Deprecated
    public void setOnPageChangeListener(qs0 qs0Var) {
        this.F = qs0Var;
    }

    public void setPageMargin(int i) {
        int i2 = this.g;
        this.g = i;
        int width = getWidth();
        j(width, width, i, i2);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.h = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i) {
        if (this.J == i) {
            return;
        }
        this.J = i;
        qs0 qs0Var = this.F;
        if (qs0Var != null) {
            qs0Var.b(i);
        }
        ArrayList arrayList = this.E;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                qs0 qs0Var2 = (qs0) this.E.get(i2);
                if (qs0Var2 != null) {
                    qs0Var2.b(i);
                }
            }
        }
        qs0 qs0Var3 = this.G;
        if (qs0Var3 != null) {
            qs0Var3.b(i);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.h;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(ah.b(getContext(), i));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new ArrayList();
        this.c = new os0();
        this.d = new Rect();
        this.e = null;
        this.i = -3.4028235E38f;
        this.j = Float.MAX_VALUE;
        this.n = 1;
        this.w = -1;
        this.B = true;
        this.I = new v6(19, this);
        this.J = 0;
        g();
    }
}
