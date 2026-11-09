package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.R$attr;
import defpackage.f51;
import defpackage.hs;
import defpackage.kh0;
import defpackage.l21;
import defpackage.lh0;
import defpackage.mh0;
import defpackage.nh0;
import defpackage.oo;
import defpackage.p50;
import defpackage.ph0;
import defpackage.qh0;
import defpackage.t41;
import defpackage.up;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsObjectStoreUtil;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements ph0, mh0 {
    public static final float i0 = (float) (Math.log(0.78d) / Math.log(0.9d));
    public static final up j0 = new up(4);
    public static final int[] k0 = {R.attr.fillViewport};
    public final float E;
    public long F;
    public final Rect G;
    public final OverScroller H;
    public final EdgeEffect I;
    public final EdgeEffect J;
    public int K;
    public boolean L;
    public boolean M;
    public View N;
    public boolean O;
    public VelocityTracker P;
    public boolean Q;
    public boolean R;
    public final int S;
    public final int T;
    public final int U;
    public int V;
    public final int[] W;
    public final int[] a0;
    public int b0;
    public int c0;
    public SavedState d0;
    public final qh0 e0;
    public final nh0 f0;
    public float g0;
    public final oo h0;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int E;

        public final String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.E + JsonUtils.CLOSE;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.E);
        }
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    public static boolean l(View view, NestedScrollView nestedScrollView) {
        if (view == nestedScrollView) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && l((View) parent, nestedScrollView);
    }

    @Override // defpackage.oh0
    public final void a(View view, View view2, int i, int i2) {
        qh0 qh0Var = this.e0;
        if (i2 == 1) {
            qh0Var.b = i;
        } else {
            qh0Var.a = i;
        }
        this.f0.g(2, i2);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    @Override // defpackage.oh0
    public final void b(View view, int i) {
        qh0 qh0Var = this.e0;
        if (i == 1) {
            qh0Var.b = 0;
        } else {
            qh0Var.a = 0;
        }
        w(i);
    }

    @Override // defpackage.oh0
    public final void c(View view, int i, int i2, int[] iArr, int i3) {
        this.f0.c(i, i2, i3, iArr, null);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e9  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void computeScroll() {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.computeScroll():void");
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > iMax ? (scrollY - iMax) + bottom : bottom;
    }

    public final boolean d(int i) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !m(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            s(maxScrollAmount, 0, 1, true);
        } else {
            Rect rect = this.G;
            viewFindNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(viewFindNextFocus, rect);
            s(e(rect), 0, 1, true);
            viewFindNextFocus.requestFocus(i);
        }
        if (viewFindFocus != null && viewFindFocus.isFocused() && !m(viewFindFocus, 0, getHeight())) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || f(keyEvent);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f0.a(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return this.f0.b(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f0.c(i, i2, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f0.d(i, i2, i3, i4, iArr, 0, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        int scrollY = getScrollY();
        EdgeEffect edgeEffect = this.I;
        int paddingLeft2 = 0;
        if (!edgeEffect.isFinished()) {
            int iSave = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int iMin = Math.min(0, scrollY);
            if (kh0.a(this)) {
                width -= getPaddingRight() + getPaddingLeft();
                paddingLeft = getPaddingLeft();
            } else {
                paddingLeft = 0;
            }
            if (kh0.a(this)) {
                height -= getPaddingBottom() + getPaddingTop();
                iMin += getPaddingTop();
            }
            canvas.translate(paddingLeft, iMin);
            edgeEffect.setSize(width, height);
            if (edgeEffect.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect2 = this.J;
        if (edgeEffect2.isFinished()) {
            return;
        }
        int iSave2 = canvas.save();
        int width2 = getWidth();
        int height2 = getHeight();
        int iMax = Math.max(getScrollRange(), scrollY) + height2;
        if (kh0.a(this)) {
            width2 -= getPaddingRight() + getPaddingLeft();
            paddingLeft2 = getPaddingLeft();
        }
        if (kh0.a(this)) {
            height2 -= getPaddingBottom() + getPaddingTop();
            iMax -= getPaddingBottom();
        }
        canvas.translate(paddingLeft2 - width2, iMax);
        canvas.rotate(180.0f, width2, 0.0f);
        edgeEffect2.setSize(width2, height2);
        if (edgeEffect2.draw(canvas)) {
            postInvalidateOnAnimation();
        }
        canvas.restoreToCount(iSave2);
    }

    public final int e(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i2 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i - verticalFadingEdgeLength : i;
        int i3 = rect.bottom;
        if (i3 > i2 && rect.top > scrollY) {
            return Math.min(rect.height() > height ? rect.top - scrollY : rect.bottom - i2, (childAt.getBottom() + layoutParams.bottomMargin) - i);
        }
        if (rect.top >= scrollY || i3 >= i2) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i2 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean f(android.view.KeyEvent r6) {
        /*
            r5 = this;
            android.graphics.Rect r0 = r5.G
            r0.setEmpty()
            int r0 = r5.getChildCount()
            r1 = 0
            r2 = 130(0x82, float:1.82E-43)
            if (r0 <= 0) goto L98
            android.view.View r0 = r5.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r3 = r0.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r3 = (android.widget.FrameLayout.LayoutParams) r3
            int r0 = r0.getHeight()
            int r4 = r3.topMargin
            int r0 = r0 + r4
            int r3 = r3.bottomMargin
            int r0 = r0 + r3
            int r3 = r5.getHeight()
            int r4 = r5.getPaddingTop()
            int r3 = r3 - r4
            int r4 = r5.getPaddingBottom()
            int r3 = r3 - r4
            if (r0 <= r3) goto L98
            int r0 = r6.getAction()
            if (r0 != 0) goto Lc0
            int r0 = r6.getKeyCode()
            r3 = 19
            r4 = 33
            if (r0 == r3) goto L88
            r3 = 20
            if (r0 == r3) goto L78
            r3 = 62
            if (r0 == r3) goto L6d
            r6 = 92
            if (r0 == r6) goto L68
            r6 = 93
            if (r0 == r6) goto L63
            r6 = 122(0x7a, float:1.71E-43)
            if (r0 == r6) goto L5f
            r6 = 123(0x7b, float:1.72E-43)
            if (r0 == r6) goto L5b
            goto Lc0
        L5b:
            r5.q(r2)
            return r1
        L5f:
            r5.q(r4)
            return r1
        L63:
            boolean r6 = r5.k(r2)
            return r6
        L68:
            boolean r6 = r5.k(r4)
            return r6
        L6d:
            boolean r6 = r6.isShiftPressed()
            if (r6 == 0) goto L74
            r2 = r4
        L74:
            r5.q(r2)
            return r1
        L78:
            boolean r6 = r6.isAltPressed()
            if (r6 == 0) goto L83
            boolean r6 = r5.k(r2)
            return r6
        L83:
            boolean r6 = r5.d(r2)
            return r6
        L88:
            boolean r6 = r6.isAltPressed()
            if (r6 == 0) goto L93
            boolean r6 = r5.k(r4)
            return r6
        L93:
            boolean r6 = r5.d(r4)
            return r6
        L98:
            boolean r0 = r5.isFocused()
            if (r0 == 0) goto Lc0
            int r6 = r6.getKeyCode()
            r0 = 4
            if (r6 == r0) goto Lc0
            android.view.View r6 = r5.findFocus()
            if (r6 != r5) goto Lac
            r6 = 0
        Lac:
            android.view.FocusFinder r0 = android.view.FocusFinder.getInstance()
            android.view.View r6 = r0.findNextFocus(r5, r6, r2)
            if (r6 == 0) goto Lc0
            if (r6 == r5) goto Lc0
            boolean r6 = r6.requestFocus(r2)
            if (r6 == 0) goto Lc0
            r6 = 1
            return r6
        Lc0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.f(android.view.KeyEvent):boolean");
    }

    @Override // defpackage.ph0
    public final void g(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        n(i4, i5, iArr);
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        qh0 qh0Var = this.e0;
        return qh0Var.b | qh0Var.a;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public float getVerticalScrollFactorCompat() {
        if (this.g0 == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.g0 = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.g0;
    }

    public final void h(int i) {
        if (getChildCount() > 0) {
            this.H.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.f0.g(2, 1);
            this.c0 = getScrollY();
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f0.f(0);
    }

    @Override // defpackage.oh0
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
        n(i4, i5, null);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f0.d;
    }

    @Override // defpackage.oh0
    public final boolean j(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public final boolean k(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.G;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            rect.bottom = paddingBottom;
            rect.top = paddingBottom - height;
        }
        return r(i, rect.top, rect.bottom);
    }

    public final boolean m(View view, int i, int i2) {
        Rect rect = this.G;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        return rect.bottom + i >= getScrollY() && rect.top - i <= getScrollY() + i2;
    }

    @Override // android.view.ViewGroup
    public final void measureChild(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void n(int i, int i2, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f0.d(0, scrollY2, 0, i - scrollY2, null, i2, iArr);
    }

    public final void o(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.V) {
            int i = actionIndex == 0 ? 1 : 0;
            this.K = (int) motionEvent.getY(i);
            this.V = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.P;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.M = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r31) {
        /*
            Method dump skipped, instruction units count: 877
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0119  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredHeight;
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        this.L = false;
        View view = this.N;
        if (view != null && l(view, this)) {
            View view2 = this.N;
            Rect rect = this.G;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iE = e(rect);
            if (iE != 0) {
                scrollBy(0, iE);
            }
        }
        this.N = null;
        if (!this.M) {
            if (this.d0 != null) {
                scrollTo(getScrollX(), this.d0.E);
                this.d0 = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                measuredHeight = 0;
            }
            int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            if (paddingTop < measuredHeight && scrollY >= 0) {
                i5 = paddingTop + scrollY > measuredHeight ? measuredHeight - paddingTop : scrollY;
            }
            if (i5 != scrollY) {
                scrollTo(getScrollX(), i5);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.M = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.Q && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f2, true);
        h((int) f2);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return this.f0.b(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        this.f0.c(i, i2, 0, iArr, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        n(i4, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    @Override // android.view.View
    public final void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        if (viewFindNextFocus != null && m(viewFindNextFocus, 0, getHeight())) {
            return viewFindNextFocus.requestFocus(i, rect);
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
        super.onRestoreInstanceState(savedState.getSuperState());
        this.d0 = savedState;
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.E = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public final void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !m(viewFindFocus, 0, i4)) {
            return;
        }
        Rect rect = this.G;
        viewFindFocus.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(viewFindFocus, rect);
        int iE = e(rect);
        if (iE != 0) {
            if (this.R) {
                u(0, iE, false);
            } else {
                scrollBy(0, iE);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return j(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        b(view, 0);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.P == null) {
            this.P = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.b0 = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        float f = 0.0f;
        motionEventObtain.offsetLocation(0.0f, this.b0);
        nh0 nh0Var = this.f0;
        if (actionMasked != 0) {
            EdgeEffect edgeEffect = this.J;
            EdgeEffect edgeEffect2 = this.I;
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.P;
                velocityTracker.computeCurrentVelocity(1000, this.U);
                int yVelocity = (int) velocityTracker.getYVelocity(this.V);
                if (Math.abs(yVelocity) >= this.T) {
                    if (l21.I(edgeEffect2) != 0.0f) {
                        if (t(edgeEffect2, yVelocity)) {
                            edgeEffect2.onAbsorb(yVelocity);
                        } else {
                            h(-yVelocity);
                        }
                    } else if (l21.I(edgeEffect) != 0.0f) {
                        int i = -yVelocity;
                        if (t(edgeEffect, i)) {
                            edgeEffect.onAbsorb(i);
                        } else {
                            h(i);
                        }
                    } else {
                        int i2 = -yVelocity;
                        float f2 = i2;
                        if (!nh0Var.b(0.0f, f2)) {
                            dispatchNestedFling(0.0f, f2, true);
                            h(i2);
                        }
                    }
                } else if (this.H.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.V = -1;
                this.O = false;
                VelocityTracker velocityTracker2 = this.P;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.P = null;
                }
                w(0);
                this.I.onRelease();
                this.J.onRelease();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.V);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.V + " in onTouchEvent");
                } else {
                    int y = (int) motionEvent.getY(iFindPointerIndex);
                    int i3 = this.K - y;
                    float x = motionEvent.getX(iFindPointerIndex) / getWidth();
                    float height = i3 / getHeight();
                    if (l21.I(edgeEffect2) != 0.0f) {
                        float f3 = -l21.r0(edgeEffect2, -height, x);
                        if (l21.I(edgeEffect2) == 0.0f) {
                            edgeEffect2.onRelease();
                        }
                        f = f3;
                    } else if (l21.I(edgeEffect) != 0.0f) {
                        float fR0 = l21.r0(edgeEffect, height, 1.0f - x);
                        if (l21.I(edgeEffect) == 0.0f) {
                            edgeEffect.onRelease();
                        }
                        f = fR0;
                    }
                    int iRound = Math.round(f * getHeight());
                    if (iRound != 0) {
                        invalidate();
                    }
                    int i4 = i3 - iRound;
                    if (!this.O && Math.abs(i4) > this.S) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.O = true;
                        i4 = i4 > 0 ? i4 - this.S : i4 + this.S;
                    }
                    if (this.O) {
                        int iS = s(i4, (int) motionEvent.getX(iFindPointerIndex), 0, false);
                        this.K = y - iS;
                        this.b0 += iS;
                    }
                }
            } else if (actionMasked == 3) {
                if (this.O && getChildCount() > 0 && this.H.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.V = -1;
                this.O = false;
                VelocityTracker velocityTracker3 = this.P;
                if (velocityTracker3 != null) {
                    velocityTracker3.recycle();
                    this.P = null;
                }
                w(0);
                this.I.onRelease();
                this.J.onRelease();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.K = (int) motionEvent.getY(actionIndex);
                this.V = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                o(motionEvent);
                this.K = (int) motionEvent.getY(motionEvent.findPointerIndex(this.V));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            if (this.O && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.H.isFinished()) {
                this.H.abortAnimation();
                w(1);
            }
            int y2 = (int) motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            this.K = y2;
            this.V = pointerId;
            nh0Var.g(2, 0);
        }
        VelocityTracker velocityTracker4 = this.P;
        if (velocityTracker4 != null) {
            velocityTracker4.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public final boolean p(int i, int i2, int i3, int i4) {
        int i5;
        boolean z;
        int i6;
        boolean z2;
        getOverScrollMode();
        super.computeHorizontalScrollRange();
        super.computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        super.computeVerticalScrollExtent();
        int i7 = i3 + i;
        if (i2 <= 0 && i2 >= 0) {
            i5 = i2;
            z = false;
        } else {
            i5 = 0;
            z = true;
        }
        if (i7 > i4) {
            i6 = i4;
        } else {
            if (i7 >= 0) {
                i6 = i7;
                z2 = false;
                if (z2 && !this.f0.f(1)) {
                    this.H.springBack(i5, i6, 0, 0, 0, getScrollRange());
                }
                super.scrollTo(i5, i6);
                return !z || z2;
            }
            i6 = 0;
        }
        z2 = true;
        if (z2) {
            this.H.springBack(i5, i6, 0, 0, 0, getScrollRange());
        }
        super.scrollTo(i5, i6);
        if (z) {
        }
    }

    public final void q(int i) {
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.G;
        if (z) {
            rect.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
                if (rect.top + height > paddingBottom) {
                    rect.top = paddingBottom - height;
                }
            }
        } else {
            int scrollY = getScrollY() - height;
            rect.top = scrollY;
            if (scrollY < 0) {
                rect.top = 0;
            }
        }
        int i2 = rect.top;
        int i3 = height + i2;
        rect.bottom = i3;
        r(i, i2, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean r(int r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            int r4 = r0.getHeight()
            int r5 = r0.getScrollY()
            int r4 = r4 + r5
            r6 = 33
            if (r1 != r6) goto L17
            r6 = 1
            goto L18
        L17:
            r6 = 0
        L18:
            r9 = 2
            java.util.ArrayList r9 = r0.getFocusables(r9)
            int r10 = r9.size()
            r11 = 0
            r12 = 0
            r13 = 0
        L24:
            if (r12 >= r10) goto L6c
            java.lang.Object r14 = r9.get(r12)
            android.view.View r14 = (android.view.View) r14
            int r15 = r14.getTop()
            int r7 = r14.getBottom()
            if (r2 >= r7) goto L69
            if (r15 >= r3) goto L69
            if (r2 >= r15) goto L3f
            if (r7 >= r3) goto L3f
            r16 = 1
            goto L41
        L3f:
            r16 = 0
        L41:
            if (r11 != 0) goto L47
            r11 = r14
            r13 = r16
            goto L69
        L47:
            if (r6 == 0) goto L4f
            int r8 = r11.getTop()
            if (r15 < r8) goto L57
        L4f:
            if (r6 != 0) goto L59
            int r8 = r11.getBottom()
            if (r7 <= r8) goto L59
        L57:
            r7 = 1
            goto L5a
        L59:
            r7 = 0
        L5a:
            if (r13 == 0) goto L61
            if (r16 == 0) goto L69
            if (r7 == 0) goto L69
            goto L68
        L61:
            if (r16 == 0) goto L66
            r11 = r14
            r13 = 1
            goto L69
        L66:
            if (r7 == 0) goto L69
        L68:
            r11 = r14
        L69:
            int r12 = r12 + 1
            goto L24
        L6c:
            if (r11 != 0) goto L6f
            r11 = r0
        L6f:
            if (r2 < r5) goto L75
            if (r3 > r4) goto L75
            r7 = 0
            goto L82
        L75:
            if (r6 == 0) goto L7b
            int r2 = r2 - r5
        L78:
            r3 = 0
            r4 = 1
            goto L7e
        L7b:
            int r2 = r3 - r4
            goto L78
        L7e:
            r0.s(r2, r3, r4, r4)
            r7 = r4
        L82:
            android.view.View r2 = r0.findFocus()
            if (r11 == r2) goto L8b
            r11.requestFocus(r1)
        L8b:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.r(int, int, int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (this.L) {
            this.N = view2;
        } else {
            Rect rect = this.G;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iE = e(rect);
            if (iE != 0) {
                scrollBy(0, iE);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int iE = e(rect);
        boolean z2 = iE != 0;
        if (z2) {
            if (z) {
                scrollBy(0, iE);
                return z2;
            }
            u(0, iE, false);
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        VelocityTracker velocityTracker;
        if (z && (velocityTracker = this.P) != null) {
            velocityTracker.recycle();
            this.P = null;
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.L = true;
        super.requestLayout();
    }

    public final int s(int i, int i2, int i3, boolean z) {
        int i4;
        int i5;
        boolean z2;
        VelocityTracker velocityTracker;
        nh0 nh0Var = this.f0;
        if (i3 == 1) {
            nh0Var.g(2, i3);
        }
        boolean zC = this.f0.c(0, i, i3, this.a0, this.W);
        int[] iArr = this.a0;
        int[] iArr2 = this.W;
        if (zC) {
            i4 = i - iArr[1];
            i5 = iArr2[1];
        } else {
            i4 = i;
            i5 = 0;
        }
        int scrollY = getScrollY();
        int scrollRange = getScrollRange();
        int overScrollMode = getOverScrollMode();
        boolean z3 = (overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) && !z;
        boolean z4 = p(i4, 0, scrollY, scrollRange) && !nh0Var.f(i3);
        int scrollY2 = getScrollY() - scrollY;
        iArr[1] = 0;
        this.f0.d(0, scrollY2, 0, i4 - scrollY2, this.W, i3, iArr);
        int i6 = i5 + iArr2[1];
        int i7 = i4 - iArr[1];
        int i8 = scrollY + i7;
        EdgeEffect edgeEffect = this.J;
        EdgeEffect edgeEffect2 = this.I;
        if (i8 < 0) {
            if (z3) {
                l21.r0(edgeEffect2, (-i7) / getHeight(), i2 / getWidth());
                if (!edgeEffect.isFinished()) {
                    edgeEffect.onRelease();
                }
            }
        } else if (i8 > scrollRange && z3) {
            l21.r0(edgeEffect, i7 / getHeight(), 1.0f - (i2 / getWidth()));
            if (!edgeEffect2.isFinished()) {
                edgeEffect2.onRelease();
            }
        }
        if (edgeEffect2.isFinished() && edgeEffect.isFinished()) {
            z2 = z4;
        } else {
            postInvalidateOnAnimation();
            z2 = false;
        }
        if (z2 && i3 == 0 && (velocityTracker = this.P) != null) {
            velocityTracker.clear();
        }
        if (i3 == 1) {
            w(i3);
            edgeEffect2.onRelease();
            edgeEffect.onRelease();
        }
        return i6;
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width >= width2 || i < 0) {
                i = 0;
            } else if (width + i > width2) {
                i = width2 - width;
            }
            if (height >= height2 || i2 < 0) {
                i2 = 0;
            } else if (height + i2 > height2) {
                i2 = height2 - height;
            }
            if (i == getScrollX() && i2 == getScrollY()) {
                return;
            }
            super.scrollTo(i, i2);
        }
    }

    public void setFillViewport(boolean z) {
        if (z != this.Q) {
            this.Q = z;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        nh0 nh0Var = this.f0;
        if (nh0Var.d) {
            WeakHashMap weakHashMap = f51.a;
            t41.z(nh0Var.c);
        }
        nh0Var.d = z;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.R = z;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return this.f0.g(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        w(0);
    }

    public final boolean t(EdgeEffect edgeEffect, int i) {
        if (i > 0) {
            return true;
        }
        float fI = l21.I(edgeEffect) * getHeight();
        float fAbs = Math.abs(-i) * 0.35f;
        float f = this.E * 0.015f;
        double dLog = Math.log(fAbs / f);
        double d = i0;
        return ((float) (Math.exp((d / (d - 1.0d)) * dLog) * ((double) f))) < fI;
    }

    public final void u(int i, int i2, boolean z) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.F > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.H.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, height - height2))) - scrollY, 250);
            if (z) {
                this.f0.g(2, 1);
            } else {
                w(1);
            }
            this.c0 = getScrollY();
            postInvalidateOnAnimation();
        } else {
            if (!this.H.isFinished()) {
                this.H.abortAnimation();
                w(1);
            }
            scrollBy(i, i2);
        }
        this.F = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean v(MotionEvent motionEvent) {
        boolean z;
        EdgeEffect edgeEffect = this.I;
        if (l21.I(edgeEffect) != 0.0f) {
            l21.r0(edgeEffect, 0.0f, motionEvent.getX() / getWidth());
            z = true;
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.J;
        if (l21.I(edgeEffect2) == 0.0f) {
            return z;
        }
        l21.r0(edgeEffect2, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
        return true;
    }

    public final void w(int i) {
        this.f0.h(i);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.nestedScrollViewStyle);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        EdgeEffect edgeEffect;
        EdgeEffect edgeEffect2;
        super(context, attributeSet, i);
        this.G = new Rect();
        this.L = true;
        this.M = false;
        this.N = null;
        this.O = false;
        this.R = true;
        this.V = -1;
        this.W = new int[2];
        this.a0 = new int[2];
        this.h0 = new oo(getContext(), new p50(28, this));
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            edgeEffect = hs.a(context, attributeSet);
        } else {
            edgeEffect = new EdgeEffect(context);
        }
        this.I = edgeEffect;
        if (i2 >= 31) {
            edgeEffect2 = hs.a(context, attributeSet);
        } else {
            edgeEffect2 = new EdgeEffect(context);
        }
        this.J = edgeEffect2;
        this.E = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        this.H = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.S = viewConfiguration.getScaledTouchSlop();
        this.T = viewConfiguration.getScaledMinimumFlingVelocity();
        this.U = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k0, i, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.e0 = new qh0();
        this.f0 = new nh0(this);
        setNestedScrollingEnabled(true);
        f51.o(this, j0);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void setOnScrollChangeListener(lh0 lh0Var) {
    }
}
