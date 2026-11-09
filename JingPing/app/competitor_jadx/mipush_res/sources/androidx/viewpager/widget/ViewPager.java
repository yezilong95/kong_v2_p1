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
import defpackage.an0;
import defpackage.f;
import defpackage.f51;
import defpackage.hb;
import defpackage.n41;
import defpackage.pi;
import defpackage.t41;
import defpackage.u51;
import defpackage.v51;
import defpackage.w51;
import defpackage.x51;
import defpackage.yj0;
import io.nats.client.support.JsonUtils;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    public static final int[] q0 = {R.attr.layout_gravity};
    public static final an0 r0 = new an0(2);
    public final ArrayList E;
    public final v51 F;
    public final Rect G;
    public Parcelable H;
    public Scroller I;
    public int J;
    public Drawable K;
    public final float L;
    public final float M;
    public int N;
    public boolean O;
    public boolean P;
    public int Q;
    public boolean R;
    public boolean S;
    public int T;
    public int U;
    public int V;
    public float W;
    public float a0;
    public float b0;
    public int c0;
    public VelocityTracker d0;
    public int e0;
    public EdgeEffect f0;
    public EdgeEffect g0;
    public boolean h0;
    public boolean i0;
    public int j0;
    public ArrayList k0;
    public x51 l0;
    public x51 m0;
    public ArrayList n0;
    public final f o0;
    public int p0;

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public boolean a;
        public final int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.q0);
            this.b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int G;
        public final Parcelable H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.G = parcel.readInt();
            this.H = parcel.readParcelable(classLoader);
        }

        public final String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.G + JsonUtils.CLOSE;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.G);
            parcel.writeParcelable(this.H, i);
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.E = new ArrayList();
        this.F = new v51();
        this.G = new Rect();
        this.H = null;
        this.L = -3.4028235E38f;
        this.M = Float.MAX_VALUE;
        this.Q = 1;
        this.c0 = -1;
        this.h0 = true;
        this.o0 = new f(27, this);
        this.p0 = 0;
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
        if (this.P != z) {
            this.P = z;
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
            Rect rect = this.G;
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

    public void addOnAdapterChangeListener(w51 w51Var) {
        if (this.n0 == null) {
            this.n0 = new ArrayList();
        }
        this.n0.add(w51Var);
    }

    public void addOnPageChangeListener(x51 x51Var) {
        if (this.k0 == null) {
            this.k0 = new ArrayList();
        }
        this.k0.add(x51Var);
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
        boolean z = layoutParams2.a | (view.getClass().getAnnotation(u51.class) != null);
        layoutParams2.a = z;
        if (!this.O) {
            super.addView(view, i, layoutParams);
        } else {
            if (z) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            addViewInLayout(view, i, layoutParams);
        }
    }

    public final void c(boolean z) throws Throwable {
        int i = 0;
        boolean z2 = this.p0 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.I.isFinished()) {
                this.I.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.I.getCurrX();
                int currY = this.I.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        i(currX);
                    }
                }
            }
        }
        while (true) {
            ArrayList arrayList = this.E;
            if (i >= arrayList.size()) {
                break;
            }
            ((v51) arrayList.get(i)).getClass();
            i++;
        }
        if (z2) {
            f fVar = this.o0;
            if (!z) {
                fVar.run();
            } else {
                WeakHashMap weakHashMap = f51.a;
                postOnAnimation(fVar);
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
    public final void computeScroll() throws Throwable {
        if (this.I.isFinished() || !this.I.computeScrollOffset()) {
            c(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.I.getCurrX();
        int currY = this.I.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!i(currX)) {
                this.I.abortAnimation();
                scrollTo(0, currY);
            }
        }
        WeakHashMap weakHashMap = f51.a;
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
        To view partially-correct add '--show-bad-code' argument
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
            this.f0.finish();
            this.g0.finish();
        } else {
            if (!this.f0.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate(getPaddingTop() + (-height), this.L * width);
                this.f0.setSize(height, width);
                zDraw = this.f0.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.g0.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.M + 1.0f)) * width2);
                this.g0.setSize(height2, width2);
                zDraw |= this.g0.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        }
        if (zDraw) {
            WeakHashMap weakHashMap = f51.a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.K;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e() {
        ArrayList arrayList = this.E;
        if (arrayList.size() <= 0) {
            return;
        }
        ((v51) arrayList.get(0)).getClass();
        throw null;
    }

    public final v51 f() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.E;
            if (i >= arrayList.size()) {
                return null;
            }
            v51 v51Var = (v51) arrayList.get(i);
            if (v51Var.a == 0) {
                return v51Var;
            }
            i++;
        }
    }

    public final void g() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.I = new Scroller(context, r0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.V = viewConfiguration.getScaledPagingTouchSlop();
        viewConfiguration.getScaledMaximumFlingVelocity();
        this.f0 = new EdgeEffect(context);
        this.g0 = new EdgeEffect(context);
        this.e0 = (int) (2.0f * f);
        this.T = (int) (f * 16.0f);
        f51.o(this, new hb(5, this));
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        t41.u(this, new n41(this));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public yj0 getAdapter() {
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
        return this.Q;
    }

    public int getPageMargin() {
        return this.J;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r14 = r11.j0
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
            x51 r14 = r11.l0
            if (r14 == 0) goto L73
            r14.b(r12, r13)
        L73:
            java.util.ArrayList r14 = r11.k0
            if (r14 == 0) goto L8d
            int r14 = r14.size()
        L7b:
            if (r0 >= r14) goto L8d
            java.util.ArrayList r2 = r11.k0
            java.lang.Object r2 = r2.get(r0)
            x51 r2 = (defpackage.x51) r2
            if (r2 == 0) goto L8a
            r2.b(r12, r13)
        L8a:
            int r0 = r0 + 1
            goto L7b
        L8d:
            x51 r14 = r11.m0
            if (r14 == 0) goto L94
            r14.b(r12, r13)
        L94:
            r11.i0 = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.h(int, float, int):void");
    }

    public final boolean i(int i) {
        int i2;
        ArrayList arrayList = this.E;
        if (arrayList.size() == 0) {
            if (!this.h0) {
                this.i0 = false;
                h(0, 0.0f, 0);
                if (!this.i0) {
                    throw new IllegalStateException("onPageScrolled did not call superclass implementation");
                }
            }
            return false;
        }
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f = clientWidth > 0 ? this.J / clientWidth : 0.0f;
        int i3 = -1;
        boolean z = true;
        float f2 = 0.0f;
        int i4 = 0;
        v51 v51Var = null;
        while (i4 < arrayList.size()) {
            v51 v51Var2 = (v51) arrayList.get(i4);
            if (!z && v51Var2.a != (i2 = i3 + 1)) {
                v51 v51Var3 = this.F;
                v51Var3.b = f2 + 0.0f + f;
                v51Var3.a = i2;
                throw null;
            }
            f2 = v51Var2.b;
            float f3 = f2 + 0.0f + f;
            if (!z && scrollX < f2) {
                break;
            }
            if (scrollX < f3 || i4 == arrayList.size() - 1) {
                v51Var = v51Var2;
                break;
            }
            i3 = v51Var2.a;
            i4++;
            z = false;
            v51Var = v51Var2;
        }
        int clientWidth2 = getClientWidth();
        int i5 = this.J;
        int i6 = clientWidth2 + i5;
        float f4 = clientWidth2;
        int i7 = v51Var.a;
        float f5 = ((i / f4) - v51Var.b) / (0.0f + (i5 / f4));
        this.i0 = false;
        h(i7, f5, (int) (i6 * f5));
        if (this.i0) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final void j(int i, int i2, int i3, int i4) throws Throwable {
        if (i2 > 0 && !this.E.isEmpty()) {
            if (!this.I.isFinished()) {
                this.I.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)) * (((i - getPaddingLeft()) - getPaddingRight()) + i3)), getScrollY());
                return;
            }
        }
        v51 v51VarF = f();
        int iMin = (int) ((v51VarF != null ? Math.min(v51VarF.b, this.M) : 0.0f) * ((i - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            c(false);
            scrollTo(iMin, getScrollY());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h0 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.o0);
        Scroller scroller = this.I;
        if (scroller != null && !scroller.isFinished()) {
            this.I.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.J <= 0 || this.K == null) {
            return;
        }
        this.E.size();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
    
        if (r13.S != false) goto L84;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r19, int r20, int r21, int r22, int r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
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
        this.U = Math.min(measuredWidth / 10, this.T);
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
        this.N = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.O = false;
        int childCount2 = getChildCount();
        for (int i12 = 0; i12 < childCount2; i12++) {
            View childAt2 = getChildAt(i12);
            if (childAt2.getVisibility() != 8 && ((layoutParams = (LayoutParams) childAt2.getLayoutParams()) == null || !layoutParams.a)) {
                layoutParams.getClass();
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * 0.0f), 1073741824), this.N);
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
        super.onRestoreInstanceState(savedState.E);
        this.H = savedState.H;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.G = 0;
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) throws Throwable {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            int i5 = this.J;
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

    public void removeOnAdapterChangeListener(w51 w51Var) {
        ArrayList arrayList = this.n0;
        if (arrayList != null) {
            arrayList.remove(w51Var);
        }
    }

    public void removeOnPageChangeListener(x51 x51Var) {
        ArrayList arrayList = this.k0;
        if (arrayList != null) {
            arrayList.remove(x51Var);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.O) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(yj0 yj0Var) {
        ArrayList arrayList = this.n0;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = this.n0.size();
        for (int i = 0; i < size; i++) {
            ((w51) this.n0.get(i)).c(this);
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
        if (i != this.Q) {
            this.Q = i;
        }
    }

    @Deprecated
    public void setOnPageChangeListener(x51 x51Var) {
        this.l0 = x51Var;
    }

    public void setPageMargin(int i) throws Throwable {
        int i2 = this.J;
        this.J = i;
        int width = getWidth();
        j(width, width, i, i2);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.K = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i) {
        if (this.p0 == i) {
            return;
        }
        this.p0 = i;
        x51 x51Var = this.l0;
        if (x51Var != null) {
            x51Var.a(i);
        }
        ArrayList arrayList = this.k0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                x51 x51Var2 = (x51) this.k0.get(i2);
                if (x51Var2 != null) {
                    x51Var2.a(i);
                }
            }
        }
        x51 x51Var3 = this.m0;
        if (x51Var3 != null) {
            x51Var3.a(i);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.K;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(pi.b(getContext(), i));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new ArrayList();
        this.F = new v51();
        this.G = new Rect();
        this.H = null;
        this.L = -3.4028235E38f;
        this.M = Float.MAX_VALUE;
        this.Q = 1;
        this.c0 = -1;
        this.h0 = true;
        this.o0 = new f(27, this);
        this.p0 = 0;
        g();
    }
}
