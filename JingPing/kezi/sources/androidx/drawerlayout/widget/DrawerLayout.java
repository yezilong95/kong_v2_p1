package androidx.drawerlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.R$attr;
import androidx.drawerlayout.R$dimen;
import androidx.drawerlayout.R$styleable;
import com.google.android.material.navigation.NavigationView;
import defpackage.ah;
import defpackage.cs0;
import defpackage.f90;
import defpackage.j0;
import defpackage.jy;
import defpackage.lr0;
import defpackage.o70;
import defpackage.pl;
import defpackage.po;
import defpackage.ql;
import defpackage.rl;
import defpackage.sl;
import defpackage.x40;
import defpackage.xr0;
import defpackage.xu0;
import defpackage.z1;
import java.util.ArrayList;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {
    public static final int[] E = {R.attr.colorPrimaryDark};
    public static final int[] F = {R.attr.layout_gravity};
    public static final boolean G;
    public static final boolean H;
    public static final boolean I;
    public final ArrayList A;
    public Rect B;
    public Matrix C;
    public final o70 D;
    public final rl b;
    public float c;
    public final int d;
    public int e;
    public float f;
    public final Paint g;
    public final cs0 h;
    public final cs0 i;
    public final b j;
    public final b k;
    public int l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;
    public int q;
    public int r;
    public boolean s;
    public sl t;
    public ArrayList u;
    public float v;
    public float w;
    public Drawable x;
    public WindowInsets y;
    public boolean z;

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int a;
        public float b;
        public boolean c;
        public int d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.F);
            this.a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = 0;
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        G = true;
        H = true;
        I = i >= 29;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public static boolean i(View view) {
        WeakHashMap weakHashMap = xr0.a;
        return (view.getImportantForAccessibility() == 4 || view.getImportantForAccessibility() == 2) ? false : true;
    }

    public static boolean j(View view) {
        return ((LayoutParams) view.getLayoutParams()).a == 0;
    }

    public static boolean k(View view) {
        if (l(view)) {
            return (((LayoutParams) view.getLayoutParams()).d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public static boolean l(View view) {
        int i = ((LayoutParams) view.getLayoutParams()).a;
        WeakHashMap weakHashMap = xr0.a;
        int absoluteGravity = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        return ((absoluteGravity & 3) == 0 && (absoluteGravity & 5) == 0) ? false : true;
    }

    public final boolean a(View view, int i) {
        return (h(view) & i) == i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        ArrayList arrayList2;
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        int i3 = 0;
        boolean z = false;
        while (true) {
            arrayList2 = this.A;
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (!l(childAt)) {
                arrayList2.add(childAt);
            } else if (k(childAt)) {
                childAt.addFocusables(arrayList, i, i2);
                z = true;
            }
            i3++;
        }
        if (!z) {
            int size = arrayList2.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = (View) arrayList2.get(i4);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i, i2);
                }
            }
        }
        arrayList2.clear();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (e() != null || l(view)) {
            WeakHashMap weakHashMap = xr0.a;
            view.setImportantForAccessibility(4);
        } else {
            WeakHashMap weakHashMap2 = xr0.a;
            view.setImportantForAccessibility(1);
        }
        if (G) {
            return;
        }
        xr0.p(view, this.b);
    }

    public final void b(View view, boolean z) {
        if (!l(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.n) {
            layoutParams.b = 0.0f;
            layoutParams.d = 0;
        } else if (z) {
            layoutParams.d |= 4;
            if (a(view, 3)) {
                this.h.s(view, -view.getWidth(), view.getTop());
            } else {
                this.i.s(view, getWidth(), view.getTop());
            }
        } else {
            float f = ((LayoutParams) view.getLayoutParams()).b;
            float width = view.getWidth();
            int i = ((int) (width * 0.0f)) - ((int) (f * width));
            if (!a(view, 3)) {
                i = -i;
            }
            view.offsetLeftAndRight(i);
            o(view, 0.0f);
            r(view, 0);
            view.setVisibility(4);
        }
        invalidate();
    }

    public final void c(boolean z) {
        int childCount = getChildCount();
        boolean zS = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (l(childAt) && (!z || layoutParams.c)) {
                zS |= a(childAt, 3) ? this.h.s(childAt, -childAt.getWidth(), childAt.getTop()) : this.i.s(childAt, getWidth(), childAt.getTop());
                layoutParams.c = false;
            }
        }
        b bVar = this.j;
        bVar.u.removeCallbacks(bVar.t);
        b bVar2 = this.k;
        bVar2.u.removeCallbacks(bVar2.t);
        if (zS) {
            invalidate();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i = 0; i < childCount; i++) {
            fMax = Math.max(fMax, ((LayoutParams) getChildAt(i).getLayoutParams()).b);
        }
        this.f = fMax;
        boolean zG = this.h.g();
        boolean zG2 = this.i.g();
        if (zG || zG2) {
            WeakHashMap weakHashMap = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public final View d(int i) {
        WeakHashMap weakHashMap = xr0.a;
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection()) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((h(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean zDispatchGenericMotionEvent;
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.f <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (this.B == null) {
                this.B = new Rect();
            }
            childAt.getHitRect(this.B);
            if (this.B.contains((int) x, (int) y) && !j(childAt)) {
                if (childAt.getMatrix().isIdentity()) {
                    float scrollX = getScrollX() - childAt.getLeft();
                    float scrollY = getScrollY() - childAt.getTop();
                    motionEvent.offsetLocation(scrollX, scrollY);
                    zDispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(motionEvent);
                    motionEvent.offsetLocation(-scrollX, -scrollY);
                } else {
                    float scrollX2 = getScrollX() - childAt.getLeft();
                    float scrollY2 = getScrollY() - childAt.getTop();
                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                    motionEventObtain.offsetLocation(scrollX2, scrollY2);
                    Matrix matrix = childAt.getMatrix();
                    if (!matrix.isIdentity()) {
                        if (this.C == null) {
                            this.C = new Matrix();
                        }
                        matrix.invert(this.C);
                        motionEventObtain.transform(this.C);
                    }
                    zDispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(motionEventObtain);
                    motionEventObtain.recycle();
                }
                if (zDispatchGenericMotionEvent) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        Drawable background;
        int height = getHeight();
        boolean zJ = j(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i = 0;
        if (zJ) {
            int childCount = getChildCount();
            int i2 = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0 && (background = childAt.getBackground()) != null && background.getOpacity() == -1 && l(childAt) && childAt.getHeight() >= height) {
                    if (a(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i2) {
                            i2 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i2, 0, width, getHeight());
            i = i2;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        float f = this.f;
        if (f > 0.0f && zJ) {
            int i4 = this.e;
            Paint paint = this.g;
            paint.setColor((((int) ((((-16777216) & i4) >>> 24) * f)) << 24) | (i4 & 16777215));
            canvas.drawRect(i, 0.0f, width, getHeight(), paint);
        }
        return zDrawChild;
    }

    public final View e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public final View f() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (l(childAt)) {
                if (!l(childAt)) {
                    throw new IllegalArgumentException("View " + childAt + " is not a drawer");
                }
                if (((LayoutParams) childAt.getLayoutParams()).b > 0.0f) {
                    return childAt;
                }
            }
        }
        return null;
    }

    public final int g(View view) {
        if (!l(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        int i = ((LayoutParams) view.getLayoutParams()).a;
        WeakHashMap weakHashMap = xr0.a;
        int layoutDirection = getLayoutDirection();
        if (i == 3) {
            int i2 = this.o;
            if (i2 != 3) {
                return i2;
            }
            int i3 = layoutDirection == 0 ? this.q : this.r;
            if (i3 != 3) {
                return i3;
            }
            return 0;
        }
        if (i == 5) {
            int i4 = this.p;
            if (i4 != 3) {
                return i4;
            }
            int i5 = layoutDirection == 0 ? this.r : this.q;
            if (i5 != 3) {
                return i5;
            }
            return 0;
        }
        if (i == 8388611) {
            int i6 = this.q;
            if (i6 != 3) {
                return i6;
            }
            int i7 = layoutDirection == 0 ? this.o : this.p;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        }
        if (i != 8388613) {
            return 0;
        }
        int i8 = this.r;
        if (i8 != 3) {
            return i8;
        }
        int i9 = layoutDirection == 0 ? this.p : this.o;
        if (i9 != 3) {
            return i9;
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.a = 0;
        return layoutParams;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            LayoutParams layoutParams3 = new LayoutParams(layoutParams2);
            layoutParams3.a = 0;
            layoutParams3.a = layoutParams2.a;
            return layoutParams3;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            LayoutParams layoutParams4 = new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            layoutParams4.a = 0;
            return layoutParams4;
        }
        LayoutParams layoutParams5 = new LayoutParams(layoutParams);
        layoutParams5.a = 0;
        return layoutParams5;
    }

    public float getDrawerElevation() {
        if (H) {
            return this.c;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.x;
    }

    public final int h(View view) {
        int i = ((LayoutParams) view.getLayoutParams()).a;
        WeakHashMap weakHashMap = xr0.a;
        return Gravity.getAbsoluteGravity(i, getLayoutDirection());
    }

    public final void m(View view) {
        if (!l(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.n) {
            layoutParams.b = 1.0f;
            layoutParams.d = 1;
            q(view, true);
            p(view);
        } else {
            layoutParams.d |= 2;
            if (a(view, 3)) {
                this.h.s(view, 0, view.getTop());
            } else {
                this.i.s(view, getWidth() - view.getWidth(), view.getTop());
            }
        }
        invalidate();
    }

    public final void n(int i, int i2) {
        View viewD;
        WeakHashMap weakHashMap = xr0.a;
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        if (i2 == 3) {
            this.o = i;
        } else if (i2 == 5) {
            this.p = i;
        } else if (i2 == 8388611) {
            this.q = i;
        } else if (i2 == 8388613) {
            this.r = i;
        }
        if (i != 0) {
            (absoluteGravity == 3 ? this.h : this.i).a();
        }
        if (i != 1) {
            if (i == 2 && (viewD = d(absoluteGravity)) != null) {
                m(viewD);
                return;
            }
            return;
        }
        View viewD2 = d(absoluteGravity);
        if (viewD2 != null) {
            b(viewD2, true);
        }
    }

    public final void o(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f == layoutParams.b) {
            return;
        }
        layoutParams.b = f;
        ArrayList arrayList = this.u;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((sl) this.u.get(size)).getClass();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.n = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.n = true;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.z || this.x == null) {
            return;
        }
        WindowInsets windowInsets = this.y;
        int systemWindowInsetTop = windowInsets != null ? windowInsets.getSystemWindowInsetTop() : 0;
        if (systemWindowInsetTop > 0) {
            this.x.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.x.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            cs0 r1 = r8.h
            boolean r2 = r1.r(r9)
            cs0 r3 = r8.i
            boolean r3 = r3.r(r9)
            r2 = r2 | r3
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L65
            if (r0 == r3) goto L5e
            r9 = 2
            if (r0 == r9) goto L1e
            r9 = 3
            if (r0 == r9) goto L5e
            goto L63
        L1e:
            float[] r9 = r1.d
            int r9 = r9.length
            r0 = r4
        L22:
            if (r0 >= r9) goto L63
            int r5 = r1.k
            int r6 = r3 << r0
            r5 = r5 & r6
            if (r5 == 0) goto L5b
            float[] r5 = r1.f
            r5 = r5[r0]
            float[] r6 = r1.d
            r6 = r6[r0]
            float r5 = r5 - r6
            float[] r6 = r1.g
            r6 = r6[r0]
            float[] r7 = r1.e
            r7 = r7[r0]
            float r6 = r6 - r7
            float r5 = r5 * r5
            float r6 = r6 * r6
            float r6 = r6 + r5
            int r5 = r1.b
            int r5 = r5 * r5
            float r5 = (float) r5
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 <= 0) goto L5b
            androidx.drawerlayout.widget.b r9 = r8.j
            v6 r0 = r9.t
            androidx.drawerlayout.widget.DrawerLayout r9 = r9.u
            r9.removeCallbacks(r0)
            androidx.drawerlayout.widget.b r9 = r8.k
            v6 r0 = r9.t
            androidx.drawerlayout.widget.DrawerLayout r9 = r9.u
            r9.removeCallbacks(r0)
            goto L63
        L5b:
            int r0 = r0 + 1
            goto L22
        L5e:
            r8.c(r3)
            r8.s = r4
        L63:
            r9 = r4
            goto L8b
        L65:
            float r0 = r9.getX()
            float r9 = r9.getY()
            r8.v = r0
            r8.w = r9
            float r5 = r8.f
            r6 = 0
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L88
            int r0 = (int) r0
            int r9 = (int) r9
            android.view.View r9 = r1.h(r0, r9)
            if (r9 == 0) goto L88
            boolean r9 = j(r9)
            if (r9 == 0) goto L88
            r9 = r3
            goto L89
        L88:
            r9 = r4
        L89:
            r8.s = r4
        L8b:
            if (r2 != 0) goto Lae
            if (r9 != 0) goto Lae
            int r9 = r8.getChildCount()
            r0 = r4
        L94:
            if (r0 >= r9) goto La8
            android.view.View r1 = r8.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.drawerlayout.widget.DrawerLayout$LayoutParams r1 = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams) r1
            boolean r1 = r1.c
            if (r1 == 0) goto La5
            goto Lae
        La5:
            int r0 = r0 + 1
            goto L94
        La8:
            boolean r9 = r8.s
            if (r9 == 0) goto Lad
            goto Lae
        Lad:
            return r4
        Lae:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || f() == null) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View viewF = f();
        if (viewF != null && g(viewF) == 0) {
            c(false);
        }
        return viewF != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        WindowInsets rootWindowInsets;
        float f;
        int i5;
        boolean z2 = true;
        this.m = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (j(childAt)) {
                    int i8 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    childAt.layout(i8, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, childAt.getMeasuredWidth() + i8, childAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f2 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (layoutParams.b * f2));
                        f = (measuredWidth + i5) / f2;
                    } else {
                        float f3 = measuredWidth;
                        f = (i6 - r11) / f3;
                        i5 = i6 - ((int) (layoutParams.b * f3));
                    }
                    boolean z3 = f != layoutParams.b ? z2 : false;
                    int i9 = layoutParams.a & 112;
                    if (i9 == 16) {
                        int i10 = i4 - i2;
                        int i11 = (i10 - measuredHeight) / 2;
                        int i12 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        if (i11 < i12) {
                            i11 = i12;
                        } else {
                            int i13 = i11 + measuredHeight;
                            int i14 = i10 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            if (i13 > i14) {
                                i11 = i14 - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i11, measuredWidth + i5, measuredHeight + i11);
                    } else if (i9 != 80) {
                        int i15 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        childAt.layout(i5, i15, measuredWidth + i5, measuredHeight + i15);
                    } else {
                        int i16 = i4 - i2;
                        childAt.layout(i5, (i16 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i16 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                    }
                    if (z3) {
                        o(childAt, f);
                    }
                    int i17 = layoutParams.b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i17) {
                        childAt.setVisibility(i17);
                    }
                }
            }
            i7++;
            z2 = true;
        }
        if (I && (rootWindowInsets = getRootWindowInsets()) != null) {
            jy jyVarI = xu0.h(null, rootWindowInsets).a.i();
            cs0 cs0Var = this.h;
            cs0Var.o = Math.max(cs0Var.p, jyVarI.a);
            cs0 cs0Var2 = this.i;
            cs0Var2.o = Math.max(cs0Var2.p, jyVarI.c);
        }
        this.m = false;
        this.n = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        View viewD;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        int i = savedState.d;
        if (i != 0 && (viewD = d(i)) != null) {
            m(viewD);
        }
        int i2 = savedState.e;
        if (i2 != 3) {
            n(i2, 3);
        }
        int i3 = savedState.f;
        if (i3 != 3) {
            n(i3, 5);
        }
        int i4 = savedState.g;
        if (i4 != 3) {
            n(i4, 8388611);
        }
        int i5 = savedState.h;
        if (i5 != 3) {
            n(i5, 8388613);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        if (H) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        getLayoutDirection();
        getLayoutDirection();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.d = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            int i2 = layoutParams.d;
            boolean z = i2 == 1;
            boolean z2 = i2 == 2;
            if (z || z2) {
                savedState.d = layoutParams.a;
                break;
            }
        }
        savedState.e = this.o;
        savedState.f = this.p;
        savedState.g = this.q;
        savedState.h = this.r;
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0054  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            cs0 r0 = r6.h
            r0.k(r7)
            cs0 r1 = r6.i
            r1.k(r7)
            int r1 = r7.getAction()
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L59
            if (r1 == r3) goto L20
            r7 = 3
            if (r1 == r7) goto L1a
            return r3
        L1a:
            r6.c(r3)
            r6.s = r2
            return r3
        L20:
            float r1 = r7.getX()
            float r7 = r7.getY()
            int r4 = (int) r1
            int r5 = (int) r7
            android.view.View r4 = r0.h(r4, r5)
            if (r4 == 0) goto L54
            boolean r4 = j(r4)
            if (r4 == 0) goto L54
            float r4 = r6.v
            float r1 = r1 - r4
            float r4 = r6.w
            float r7 = r7 - r4
            int r0 = r0.b
            float r1 = r1 * r1
            float r7 = r7 * r7
            float r7 = r7 + r1
            int r0 = r0 * r0
            float r0 = (float) r0
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L54
            android.view.View r7 = r6.e()
            if (r7 == 0) goto L54
            int r7 = r6.g(r7)
            r0 = 2
            if (r7 != r0) goto L55
        L54:
            r2 = r3
        L55:
            r6.c(r2)
            return r3
        L59:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.v = r0
            r6.w = r7
            r6.s = r2
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(View view) {
        j0 j0Var = j0.l;
        xr0.l(view, j0Var.a());
        if (!k(view) || g(view) == 2) {
            return;
        }
        xr0.n(view, j0Var, null, this.D);
    }

    public final void q(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || l(childAt)) && !(z && childAt == view)) {
                WeakHashMap weakHashMap = xr0.a;
                childAt.setImportantForAccessibility(4);
            } else {
                WeakHashMap weakHashMap2 = xr0.a;
                childAt.setImportantForAccessibility(1);
            }
        }
    }

    public final void r(View view, int i) {
        int i2;
        View rootView;
        int i3 = this.h.a;
        int i4 = this.i.a;
        if (i3 == 1 || i4 == 1) {
            i2 = 1;
        } else {
            i2 = 2;
            if (i3 != 2 && i4 != 2) {
                i2 = 0;
            }
        }
        if (view != null && i == 0) {
            float f = ((LayoutParams) view.getLayoutParams()).b;
            if (f == 0.0f) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if ((layoutParams.d & 1) == 1) {
                    layoutParams.d = 0;
                    ArrayList arrayList = this.u;
                    if (arrayList != null) {
                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                            NavigationView navigationView = ((f90) ((sl) this.u.get(size))).a;
                            if (view == navigationView) {
                                po poVar = navigationView.t;
                                x40 x40Var = (x40) poVar.c;
                                if (x40Var != null) {
                                    x40Var.c((FrameLayout) poVar.e);
                                }
                            }
                        }
                    }
                    q(view, false);
                    p(view);
                    if (hasWindowFocus() && (rootView = getRootView()) != null) {
                        rootView.sendAccessibilityEvent(32);
                    }
                }
            } else if (f == 1.0f) {
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                if ((layoutParams2.d & 1) == 0) {
                    layoutParams2.d = 1;
                    ArrayList arrayList2 = this.u;
                    if (arrayList2 != null) {
                        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                            NavigationView navigationView2 = ((f90) ((sl) this.u.get(size2))).a;
                            if (view == navigationView2) {
                                po poVar2 = navigationView2.t;
                                Objects.requireNonNull(poVar2);
                                view.post(new z1(16, poVar2));
                            }
                        }
                    }
                    q(view, true);
                    p(view);
                    if (hasWindowFocus()) {
                        sendAccessibilityEvent(32);
                    }
                }
            }
        }
        if (i2 != this.l) {
            this.l = i2;
            ArrayList arrayList3 = this.u;
            if (arrayList3 != null) {
                for (int size3 = arrayList3.size() - 1; size3 >= 0; size3--) {
                    ((sl) this.u.get(size3)).getClass();
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            c(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.m) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f) {
        this.c = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (l(childAt)) {
                float f2 = this.c;
                WeakHashMap weakHashMap = xr0.a;
                lr0.s(childAt, f2);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(sl slVar) {
        ArrayList arrayList;
        sl slVar2 = this.t;
        if (slVar2 != null && (arrayList = this.u) != null) {
            arrayList.remove(slVar2);
        }
        if (slVar != null) {
            if (this.u == null) {
                this.u = new ArrayList();
            }
            this.u.add(slVar);
        }
        this.t = slVar;
    }

    public void setDrawerLockMode(int i) {
        n(i, 3);
        n(i, 5);
    }

    public void setScrimColor(int i) {
        this.e = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.x = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.x = new ColorDrawable(i);
        invalidate();
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.drawerLayoutStyle);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new rl(0);
        this.e = -1728053248;
        this.g = new Paint();
        this.n = true;
        this.o = 3;
        this.p = 3;
        this.q = 3;
        this.r = 3;
        this.D = new o70(16, this);
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.d = (int) ((64.0f * f) + 0.5f);
        float f2 = f * 400.0f;
        b bVar = new b(this, 3);
        this.j = bVar;
        b bVar2 = new b(this, 5);
        this.k = bVar2;
        cs0 cs0Var = new cs0(getContext(), this, bVar);
        cs0Var.b = (int) (cs0Var.b * 1.0f);
        this.h = cs0Var;
        cs0Var.q = 1;
        cs0Var.n = f2;
        bVar.s = cs0Var;
        cs0 cs0Var2 = new cs0(getContext(), this, bVar2);
        cs0Var2.b = (int) (1.0f * cs0Var2.b);
        this.i = cs0Var2;
        cs0Var2.q = 2;
        cs0Var2.n = f2;
        bVar2.s = cs0Var2;
        setFocusableInTouchMode(true);
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(1);
        xr0.p(this, new ql(this));
        setMotionEventSplittingEnabled(false);
        if (getFitsSystemWindows()) {
            setOnApplyWindowInsetsListener(new pl());
            setSystemUiVisibility(1280);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(E);
            try {
                this.x = typedArrayObtainStyledAttributes.getDrawable(0);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.DrawerLayout, i, 0);
        try {
            if (typedArrayObtainStyledAttributes2.hasValue(R$styleable.DrawerLayout_elevation)) {
                this.c = typedArrayObtainStyledAttributes2.getDimension(R$styleable.DrawerLayout_elevation, 0.0f);
            } else {
                this.c = getResources().getDimension(R$dimen.def_drawer_elevation);
            }
            typedArrayObtainStyledAttributes2.recycle();
            this.A = new ArrayList();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    public void setStatusBarBackground(int i) {
        this.x = i != 0 ? ah.b(getContext(), i) : null;
        invalidate();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
