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
import defpackage.a61;
import defpackage.b61;
import defpackage.cw0;
import defpackage.d61;
import defpackage.e61;
import defpackage.f51;
import defpackage.f61;
import defpackage.hn0;
import defpackage.p50;
import defpackage.rr0;
import defpackage.sr0;
import defpackage.tb;
import defpackage.wb;
import defpackage.wl0;
import defpackage.xj0;
import defpackage.y51;
import defpackage.z51;
import io.nats.client.Options;
import io.nats.client.support.NatsObjectStoreUtil;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public final Rect E;
    public final Rect F;
    public final z51 G;
    public int H;
    public boolean I;
    public final y51 J;
    public b61 K;
    public int L;
    public Parcelable M;
    public f61 N;
    public e61 O;
    public sr0 P;
    public z51 Q;
    public p50 R;
    public xj0 S;
    public hn0 T;
    public boolean U;
    public boolean V;
    public int W;
    public tb a0;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int E;
        public int F;
        public Parcelable G;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.E);
            parcel.writeInt(this.F);
            parcel.writeParcelable(this.G, i);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.E = new Rect();
        this.F = new Rect();
        this.G = new z51();
        this.I = false;
        this.J = new y51(0, this);
        this.L = -1;
        this.T = null;
        this.U = false;
        this.V = true;
        this.W = -1;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        int i = 0;
        int i2 = 1;
        tb tbVar = new tb();
        tbVar.d = this;
        tbVar.a = new cw0(5, tbVar);
        tbVar.b = new wl0(tbVar);
        this.a0 = tbVar;
        f61 f61Var = new f61(this, context);
        this.N = f61Var;
        WeakHashMap weakHashMap = f51.a;
        f61Var.setId(View.generateViewId());
        this.N.setDescendantFocusability(NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE);
        b61 b61Var = new b61(this);
        this.K = b61Var;
        this.N.setLayoutManager(b61Var);
        this.N.setScrollingTouchSlop(1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ViewPager2);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, R$styleable.ViewPager2, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(R$styleable.ViewPager2_android_orientation, 0));
            typedArrayObtainStyledAttributes.recycle();
            this.N.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.N.addOnChildAttachStateChangeListener(new a61());
            sr0 sr0Var = new sr0(this);
            this.P = sr0Var;
            this.R = new p50(21, sr0Var);
            e61 e61Var = new e61(this);
            this.O = e61Var;
            e61Var.a(this.N);
            this.N.addOnScrollListener(this.P);
            z51 z51Var = new z51();
            this.Q = z51Var;
            this.P.a = z51Var;
            z51 z51Var2 = new z51(this, i);
            z51 z51Var3 = new z51(this, i2);
            ((ArrayList) z51Var.b).add(z51Var2);
            ((ArrayList) this.Q.b).add(z51Var3);
            tb tbVar2 = this.a0;
            f61 f61Var2 = this.N;
            tbVar2.getClass();
            f61Var2.setImportantForAccessibility(2);
            tbVar2.c = new y51(i2, tbVar2);
            ViewPager2 viewPager2 = (ViewPager2) tbVar2.d;
            if (viewPager2.getImportantForAccessibility() == 0) {
                viewPager2.setImportantForAccessibility(1);
            }
            z51 z51Var4 = this.Q;
            ((ArrayList) z51Var4.b).add(this.G);
            xj0 xj0Var = new xj0();
            this.S = xj0Var;
            ((ArrayList) this.Q.b).add(xj0Var);
            f61 f61Var3 = this.N;
            attachViewToParent(f61Var3, 0, f61Var3.getLayoutParams());
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void b() {
        g adapter;
        if (this.L == -1 || (adapter = getAdapter()) == null) {
            return;
        }
        if (this.M != null) {
            this.M = null;
        }
        int iMax = Math.max(0, Math.min(this.L, adapter.a() - 1));
        this.H = iMax;
        this.L = -1;
        this.N.Z(iMax);
        this.a0.f();
    }

    public final void c(int i) {
        z51 z51Var;
        g adapter = getAdapter();
        if (adapter == null) {
            if (this.L != -1) {
                this.L = Math.max(i, 0);
                return;
            }
            return;
        }
        if (adapter.a() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i, 0), adapter.a() - 1);
        int i2 = this.H;
        if ((iMin == i2 && this.P.f == 0) || iMin == i2) {
            return;
        }
        double d = i2;
        this.H = iMin;
        this.a0.f();
        sr0 sr0Var = this.P;
        if (sr0Var.f != 0) {
            sr0Var.e();
            rr0 rr0Var = sr0Var.g;
            d = ((double) rr0Var.a) + ((double) rr0Var.b);
        }
        sr0 sr0Var2 = this.P;
        sr0Var2.getClass();
        sr0Var2.e = 2;
        boolean z = sr0Var2.i != iMin;
        sr0Var2.i = iMin;
        sr0Var2.c(2);
        if (z && (z51Var = sr0Var2.a) != null) {
            z51Var.c(iMin);
        }
        double d2 = iMin;
        if (Math.abs(d2 - d) <= 3.0d) {
            this.N.b0(iMin);
            return;
        }
        this.N.Z(d2 > d ? iMin - 3 : iMin + 3);
        f61 f61Var = this.N;
        f61Var.post(new wb(iMin, f61Var));
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        return this.N.canScrollHorizontally(i);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        return this.N.canScrollVertically(i);
    }

    public final void d() {
        e61 e61Var = this.O;
        if (e61Var == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewE = e61Var.e(this.K);
        if (viewE == null) {
            return;
        }
        this.K.getClass();
        int iH = k.H(viewE);
        if (iH != this.H && getScrollState() == 0) {
            this.Q.c(iH);
        }
        this.I = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i = ((SavedState) parcelable).E;
            sparseArray.put(this.N.getId(), sparseArray.get(i));
            sparseArray.remove(i);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        this.a0.getClass();
        this.a0.getClass();
        return "androidx.viewpager.widget.ViewPager";
    }

    public g getAdapter() {
        return this.N.getAdapter();
    }

    public int getCurrentItem() {
        return this.H;
    }

    public int getItemDecorationCount() {
        return this.N.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.W;
    }

    public int getOrientation() {
        return this.K.p;
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        f61 f61Var = this.N;
        if (getOrientation() == 0) {
            height = f61Var.getWidth() - f61Var.getPaddingLeft();
            paddingBottom = f61Var.getPaddingRight();
        } else {
            height = f61Var.getHeight() - f61Var.getPaddingTop();
            paddingBottom = f61Var.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.P.f;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int iA;
        int iA2;
        int iA3;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        ViewPager2 viewPager2 = (ViewPager2) this.a0.d;
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
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) p50.B(iA, iA2, 0).F);
        g adapter = viewPager2.getAdapter();
        if (adapter == null || (iA3 = adapter.a()) == 0 || !viewPager2.V) {
            return;
        }
        if (viewPager2.H > 0) {
            accessibilityNodeInfo.addAction(8192);
        }
        if (viewPager2.H < iA3 - 1) {
            accessibilityNodeInfo.addAction(Options.DEFAULT_MAX_CONTROL_LINE);
        }
        accessibilityNodeInfo.setScrollable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.N.getMeasuredWidth();
        int measuredHeight = this.N.getMeasuredHeight();
        int paddingLeft = getPaddingLeft();
        Rect rect = this.E;
        rect.left = paddingLeft;
        rect.right = (i3 - i) - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = (i4 - i2) - getPaddingBottom();
        Rect rect2 = this.F;
        Gravity.apply(8388659, measuredWidth, measuredHeight, rect, rect2);
        this.N.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        if (this.I) {
            d();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        measureChild(this.N, i, i2);
        int measuredWidth = this.N.getMeasuredWidth();
        int measuredHeight = this.N.getMeasuredHeight();
        int measuredState = this.N.getMeasuredState();
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
        this.L = savedState.F;
        this.M = savedState.G;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.E = this.N.getId();
        int i = this.L;
        if (i == -1) {
            i = this.H;
        }
        savedState.F = i;
        Parcelable parcelable = this.M;
        if (parcelable != null) {
            savedState.G = parcelable;
            return savedState;
        }
        this.N.getAdapter();
        return savedState;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        this.a0.getClass();
        if (i != 8192 && i != 4096) {
            return super.performAccessibilityAction(i, bundle);
        }
        tb tbVar = this.a0;
        tbVar.getClass();
        if (i != 8192 && i != 4096) {
            throw new IllegalStateException();
        }
        ViewPager2 viewPager2 = (ViewPager2) tbVar.d;
        int currentItem = i == 8192 ? viewPager2.getCurrentItem() - 1 : viewPager2.getCurrentItem() + 1;
        if (viewPager2.V) {
            viewPager2.c(currentItem);
        }
        return true;
    }

    public void setAdapter(g gVar) {
        g adapter = this.N.getAdapter();
        tb tbVar = this.a0;
        if (adapter != null) {
            adapter.a.unregisterObserver((y51) tbVar.c);
        } else {
            tbVar.getClass();
        }
        y51 y51Var = this.J;
        if (adapter != null) {
            adapter.a.unregisterObserver(y51Var);
        }
        this.N.setAdapter(gVar);
        this.H = 0;
        b();
        tb tbVar2 = this.a0;
        tbVar2.f();
        if (gVar != null) {
            gVar.a.registerObserver((y51) tbVar2.c);
        }
        if (gVar != null) {
            gVar.a.registerObserver(y51Var);
        }
    }

    public void setCurrentItem(int i) {
        Object obj = this.R.F;
        c(i);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        this.a0.f();
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1 && i != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.W = i;
        this.N.requestLayout();
    }

    public void setOrientation(int i) {
        this.K.c1(i);
        this.a0.f();
    }

    public void setPageTransformer(d61 d61Var) {
        if (d61Var != null) {
            if (!this.U) {
                this.T = this.N.getItemAnimator();
                this.U = true;
            }
            this.N.setItemAnimator(null);
        } else if (this.U) {
            this.N.setItemAnimator(this.T);
            this.T = null;
            this.U = false;
        }
        this.S.getClass();
        if (d61Var == null) {
            return;
        }
        this.S.getClass();
        this.S.getClass();
    }

    public void setUserInputEnabled(boolean z) {
        this.V = z;
        this.a0.f();
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new Rect();
        this.F = new Rect();
        this.G = new z51();
        this.I = false;
        this.J = new y51(0, this);
        this.L = -1;
        this.T = null;
        this.U = false;
        this.V = true;
        this.W = -1;
        a(context, attributeSet);
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = new Rect();
        this.F = new Rect();
        this.G = new z51();
        this.I = false;
        this.J = new y51(0, this);
        this.L = -1;
        this.T = null;
        this.U = false;
        this.V = true;
        this.W = -1;
        a(context, attributeSet);
    }
}
