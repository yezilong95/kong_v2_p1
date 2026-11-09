package androidx.viewpager2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.g;
import androidx.recyclerview.widget.k;
import androidx.viewpager2.R$styleable;
import defpackage.ah0;
import defpackage.ba;
import defpackage.db0;
import defpackage.ha;
import defpackage.ld0;
import defpackage.o70;
import defpackage.rs0;
import defpackage.ss0;
import defpackage.ts0;
import defpackage.us0;
import defpackage.ws0;
import defpackage.xr0;
import defpackage.xs0;
import defpackage.ys0;
import defpackage.zg0;
import defpackage.zs0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public final Rect b;
    public final Rect c;
    public final ss0 d;
    public int e;
    public boolean f;
    public final rs0 g;
    public us0 h;
    public int i;
    public Parcelable j;
    public zs0 k;
    public ys0 l;
    public ah0 m;
    public ss0 n;
    public o70 o;
    public db0 p;
    public ld0 q;
    public boolean r;
    public boolean s;
    public int t;
    public ba u;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int b;
        public int c;
        public Parcelable d;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeParcelable(this.d, i);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.b = new Rect();
        this.c = new Rect();
        this.d = new ss0();
        this.f = false;
        this.g = new rs0(0, this);
        this.i = -1;
        this.q = null;
        this.r = false;
        this.s = true;
        this.t = -1;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        int i = 0;
        int i2 = 1;
        ba baVar = new ba();
        baVar.d = this;
        baVar.a = new ws0(baVar, i);
        baVar.b = new ws0(baVar, i2);
        this.u = baVar;
        zs0 zs0Var = new zs0(this, context);
        this.k = zs0Var;
        WeakHashMap weakHashMap = xr0.a;
        zs0Var.setId(View.generateViewId());
        this.k.setDescendantFocusability(131072);
        us0 us0Var = new us0(this);
        this.h = us0Var;
        this.k.setLayoutManager(us0Var);
        this.k.setScrollingTouchSlop(1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ViewPager2);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, R$styleable.ViewPager2, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(R$styleable.ViewPager2_android_orientation, 0));
            typedArrayObtainStyledAttributes.recycle();
            this.k.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.k.addOnChildAttachStateChangeListener(new ts0());
            ah0 ah0Var = new ah0(this);
            this.m = ah0Var;
            this.o = new o70(19, ah0Var);
            ys0 ys0Var = new ys0(this);
            this.l = ys0Var;
            ys0Var.a(this.k);
            this.k.addOnScrollListener(this.m);
            ss0 ss0Var = new ss0();
            this.n = ss0Var;
            this.m.a = ss0Var;
            ss0 ss0Var2 = new ss0(this, i);
            ss0 ss0Var3 = new ss0(this, i2);
            ((ArrayList) ss0Var.b).add(ss0Var2);
            ((ArrayList) this.n.b).add(ss0Var3);
            ba baVar2 = this.u;
            zs0 zs0Var2 = this.k;
            baVar2.getClass();
            zs0Var2.setImportantForAccessibility(2);
            baVar2.c = new rs0(i2, baVar2);
            ViewPager2 viewPager2 = (ViewPager2) baVar2.d;
            if (viewPager2.getImportantForAccessibility() == 0) {
                viewPager2.setImportantForAccessibility(1);
            }
            ss0 ss0Var4 = this.n;
            ((ArrayList) ss0Var4.b).add(this.d);
            db0 db0Var = new db0();
            this.p = db0Var;
            ((ArrayList) this.n.b).add(db0Var);
            zs0 zs0Var3 = this.k;
            attachViewToParent(zs0Var3, 0, zs0Var3.getLayoutParams());
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void b() {
        g adapter;
        if (this.i == -1 || (adapter = getAdapter()) == null) {
            return;
        }
        if (this.j != null) {
            this.j = null;
        }
        int iMax = Math.max(0, Math.min(this.i, adapter.a() - 1));
        this.e = iMax;
        this.i = -1;
        this.k.Z(iMax);
        this.u.h();
    }

    public final void c(int i) {
        ss0 ss0Var;
        g adapter = getAdapter();
        if (adapter == null) {
            if (this.i != -1) {
                this.i = Math.max(i, 0);
                return;
            }
            return;
        }
        if (adapter.a() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i, 0), adapter.a() - 1);
        int i2 = this.e;
        if ((iMin == i2 && this.m.f == 0) || iMin == i2) {
            return;
        }
        double d = i2;
        this.e = iMin;
        this.u.h();
        ah0 ah0Var = this.m;
        if (ah0Var.f != 0) {
            ah0Var.e();
            zg0 zg0Var = ah0Var.g;
            d = ((double) zg0Var.a) + ((double) zg0Var.b);
        }
        ah0 ah0Var2 = this.m;
        ah0Var2.getClass();
        ah0Var2.e = 2;
        boolean z = ah0Var2.i != iMin;
        ah0Var2.i = iMin;
        ah0Var2.c(2);
        if (z && (ss0Var = ah0Var2.a) != null) {
            ss0Var.c(iMin);
        }
        double d2 = iMin;
        if (Math.abs(d2 - d) <= 3.0d) {
            this.k.b0(iMin);
            return;
        }
        this.k.Z(d2 > d ? iMin - 3 : iMin + 3);
        zs0 zs0Var = this.k;
        zs0Var.post(new ha(iMin, zs0Var));
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        return this.k.canScrollHorizontally(i);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        return this.k.canScrollVertically(i);
    }

    public final void d() {
        ys0 ys0Var = this.l;
        if (ys0Var == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewE = ys0Var.e(this.h);
        if (viewE == null) {
            return;
        }
        this.h.getClass();
        int iF = k.F(viewE);
        if (iF != this.e && getScrollState() == 0) {
            this.n.c(iF);
        }
        this.f = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i = ((SavedState) parcelable).b;
            sparseArray.put(this.k.getId(), sparseArray.get(i));
            sparseArray.remove(i);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        this.u.getClass();
        this.u.getClass();
        return "androidx.viewpager.widget.ViewPager";
    }

    public g getAdapter() {
        return this.k.getAdapter();
    }

    public int getCurrentItem() {
        return this.e;
    }

    public int getItemDecorationCount() {
        return this.k.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.t;
    }

    public int getOrientation() {
        return this.h.p;
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        zs0 zs0Var = this.k;
        if (getOrientation() == 0) {
            height = zs0Var.getWidth() - zs0Var.getPaddingLeft();
            paddingBottom = zs0Var.getPaddingRight();
        } else {
            height = zs0Var.getHeight() - zs0Var.getPaddingTop();
            paddingBottom = zs0Var.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.m.f;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int iA;
        int iA2;
        int iA3;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        ViewPager2 viewPager2 = (ViewPager2) this.u.d;
        if (viewPager2.getAdapter() == null) {
            iA = 0;
            iA2 = 0;
        } else if (viewPager2.getOrientation() == 1) {
            iA = viewPager2.getAdapter().a();
            iA2 = 0;
        } else {
            iA2 = viewPager2.getAdapter().a();
            iA = 0;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(iA, iA2, 0).c);
        g adapter = viewPager2.getAdapter();
        if (adapter == null || (iA3 = adapter.a()) == 0 || !viewPager2.s) {
            return;
        }
        if (viewPager2.e > 0) {
            accessibilityNodeInfo.addAction(8192);
        }
        if (viewPager2.e < iA3 - 1) {
            accessibilityNodeInfo.addAction(4096);
        }
        accessibilityNodeInfo.setScrollable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.k.getMeasuredWidth();
        int measuredHeight = this.k.getMeasuredHeight();
        int paddingLeft = getPaddingLeft();
        Rect rect = this.b;
        rect.left = paddingLeft;
        rect.right = (i3 - i) - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = (i4 - i2) - getPaddingBottom();
        Rect rect2 = this.c;
        Gravity.apply(8388659, measuredWidth, measuredHeight, rect, rect2);
        this.k.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        if (this.f) {
            d();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        measureChild(this.k, i, i2);
        int measuredWidth = this.k.getMeasuredWidth();
        int measuredHeight = this.k.getMeasuredHeight();
        int measuredState = this.k.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + measuredHeight;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i, measuredState), View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, measuredState << 16));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.i = savedState.c;
        this.j = savedState.d;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = this.k.getId();
        int i = this.i;
        if (i == -1) {
            i = this.e;
        }
        savedState.c = i;
        Parcelable parcelable = this.j;
        if (parcelable != null) {
            savedState.d = parcelable;
            return savedState;
        }
        this.k.getAdapter();
        return savedState;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        this.u.getClass();
        if (i != 8192 && i != 4096) {
            return super.performAccessibilityAction(i, bundle);
        }
        ba baVar = this.u;
        baVar.getClass();
        if (i != 8192 && i != 4096) {
            throw new IllegalStateException();
        }
        ViewPager2 viewPager2 = (ViewPager2) baVar.d;
        int currentItem = i == 8192 ? viewPager2.getCurrentItem() - 1 : viewPager2.getCurrentItem() + 1;
        if (viewPager2.s) {
            viewPager2.c(currentItem);
        }
        return true;
    }

    public void setAdapter(g gVar) {
        g adapter = this.k.getAdapter();
        ba baVar = this.u;
        if (adapter != null) {
            adapter.a.unregisterObserver((rs0) baVar.c);
        } else {
            baVar.getClass();
        }
        rs0 rs0Var = this.g;
        if (adapter != null) {
            adapter.a.unregisterObserver(rs0Var);
        }
        this.k.setAdapter(gVar);
        this.e = 0;
        b();
        ba baVar2 = this.u;
        baVar2.h();
        if (gVar != null) {
            gVar.a.registerObserver((rs0) baVar2.c);
        }
        if (gVar != null) {
            gVar.a.registerObserver(rs0Var);
        }
    }

    public void setCurrentItem(int i) {
        Object obj = this.o.c;
        c(i);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        this.u.h();
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1 && i != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.t = i;
        this.k.requestLayout();
    }

    public void setOrientation(int i) {
        this.h.Z0(i);
        this.u.h();
    }

    public void setPageTransformer(xs0 xs0Var) {
        if (xs0Var != null) {
            if (!this.r) {
                this.q = this.k.getItemAnimator();
                this.r = true;
            }
            this.k.setItemAnimator(null);
        } else if (this.r) {
            this.k.setItemAnimator(this.q);
            this.q = null;
            this.r = false;
        }
        this.p.getClass();
        if (xs0Var == null) {
            return;
        }
        this.p.getClass();
        this.p.getClass();
    }

    public void setUserInputEnabled(boolean z) {
        this.s = z;
        this.u.h();
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Rect();
        this.c = new Rect();
        this.d = new ss0();
        this.f = false;
        this.g = new rs0(0, this);
        this.i = -1;
        this.q = null;
        this.r = false;
        this.s = true;
        this.t = -1;
        a(context, attributeSet);
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Rect();
        this.c = new Rect();
        this.d = new ss0();
        this.f = false;
        this.g = new rs0(0, this);
        this.i = -1;
        this.q = null;
        this.r = false;
        this.s = true;
        this.t = -1;
        a(context, attributeSet);
    }
}
