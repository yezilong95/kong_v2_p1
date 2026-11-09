package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import defpackage.bu0;
import defpackage.ct0;
import defpackage.d1;
import defpackage.e1;
import defpackage.f1;
import defpackage.i60;
import defpackage.jr0;
import defpackage.jy;
import defpackage.l90;
import defpackage.lr0;
import defpackage.lu0;
import defpackage.m90;
import defpackage.mu0;
import defpackage.n90;
import defpackage.nu0;
import defpackage.ou0;
import defpackage.rp0;
import defpackage.uu0;
import defpackage.xr0;
import defpackage.xu0;
import defpackage.y60;
import defpackage.yi;
import defpackage.zi;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements yi, l90, m90 {
    public static final int[] C = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    public final e1 A;
    public final n90 B;
    public int b;
    public int c;
    public ContentFrameLayout d;
    public ActionBarContainer e;
    public zi f;
    public Drawable g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public int n;
    public final Rect o;
    public final Rect p;
    public final Rect q;
    public xu0 r;
    public xu0 s;
    public xu0 t;
    public xu0 u;
    public f1 v;
    public OverScroller w;
    public ViewPropertyAnimator x;
    public final d1 y;
    public final e1 z;

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public static boolean a(View view, Rect rect, boolean z) {
        boolean z2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        int i2 = rect.left;
        if (i != i2) {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = i2;
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        int i4 = rect.top;
        if (i3 != i4) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i4;
            z2 = true;
        }
        int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int i6 = rect.right;
        if (i5 != i6) {
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = i6;
            z2 = true;
        }
        if (z) {
            int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            int i8 = rect.bottom;
            if (i7 != i8) {
                ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = i8;
                return true;
            }
        }
        return z2;
    }

    @Override // defpackage.l90
    public final void b(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.l90
    public final void c(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d() {
        removeCallbacks(this.z);
        removeCallbacks(this.A);
        ViewPropertyAnimator viewPropertyAnimator = this.x;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.g == null || this.h) {
            return;
        }
        if (this.e.getVisibility() == 0) {
            translationY = (int) (this.e.getTranslationY() + this.e.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.g.setBounds(0, translationY, getWidth(), this.g.getIntrinsicHeight() + translationY);
        this.g.draw(canvas);
    }

    @Override // defpackage.l90
    public final void e(View view, int i, int i2, int[] iArr, int i3) {
    }

    @Override // defpackage.m90
    public final void f(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        i(view, i, i2, i3, i4, i5);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public final void g(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(C);
        this.b = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.g = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.h = context.getApplicationInfo().targetSdkVersion < 19;
        this.w = new OverScroller(context);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.e;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        n90 n90Var = this.B;
        return n90Var.b | n90Var.a;
    }

    public CharSequence getTitle() {
        k();
        return ((l) this.f).a.getTitle();
    }

    public final void h(int i) {
        k();
        if (i == 2) {
            ((l) this.f).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i == 5) {
            ((l) this.f).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // defpackage.l90
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // defpackage.l90
    public final boolean j(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    public final void k() {
        zi wrapper;
        if (this.d == null) {
            this.d = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.e = (ActionBarContainer) findViewById(R$id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R$id.action_bar);
            if (callbackFindViewById instanceof zi) {
                wrapper = (zi) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.f = wrapper;
        }
    }

    public final void l(i60 i60Var, y60 y60Var) {
        k();
        l lVar = (l) this.f;
        b bVar = lVar.m;
        Toolbar toolbar = lVar.a;
        if (bVar == null) {
            b bVar2 = new b(toolbar.getContext());
            lVar.m = bVar2;
            bVar2.j = R$id.action_menu_presenter;
        }
        b bVar3 = lVar.m;
        bVar3.f = y60Var;
        if (i60Var == null && toolbar.b == null) {
            return;
        }
        toolbar.f();
        i60 i60Var2 = toolbar.b.q;
        if (i60Var2 == i60Var) {
            return;
        }
        if (i60Var2 != null) {
            i60Var2.r(toolbar.L);
            i60Var2.r(toolbar.M);
        }
        if (toolbar.M == null) {
            toolbar.M = new j(toolbar);
        }
        bVar3.s = true;
        if (i60Var != null) {
            i60Var.b(bVar3, toolbar.k);
            i60Var.b(toolbar.M, toolbar.k);
        } else {
            bVar3.k(toolbar.k, null);
            toolbar.M.k(toolbar.k, null);
            bVar3.l(true);
            toolbar.M.l(true);
        }
        toolbar.b.setPopupTheme(toolbar.l);
        toolbar.b.setPresenter(bVar3);
        toolbar.L = bVar3;
        toolbar.u();
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        k();
        xu0 xu0VarH = xu0.h(this, windowInsets);
        boolean zA = a(this.e, new Rect(xu0VarH.b(), xu0VarH.d(), xu0VarH.c(), xu0VarH.a()), false);
        WeakHashMap weakHashMap = xr0.a;
        Rect rect = this.o;
        lr0.b(this, xu0VarH, rect);
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        uu0 uu0Var = xu0VarH.a;
        xu0 xu0VarL = uu0Var.l(i, i2, i3, i4);
        this.r = xu0VarL;
        boolean z = true;
        if (!this.s.equals(xu0VarL)) {
            this.s = this.r;
            zA = true;
        }
        Rect rect2 = this.p;
        if (rect2.equals(rect)) {
            z = zA;
        } else {
            rect2.set(rect);
        }
        if (z) {
            requestLayout();
        }
        return uu0Var.a().a.c().a.b().g();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        g(getContext());
        WeakHashMap weakHashMap = xr0.a;
        jr0.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int measuredHeight;
        k();
        measureChildWithMargins(this.e, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.e.getLayoutParams();
        int iMax = Math.max(0, this.e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
        int iMax2 = Math.max(0, this.e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.e.getMeasuredState());
        WeakHashMap weakHashMap = xr0.a;
        boolean z = (getWindowSystemUiVisibility() & 256) != 0;
        if (z) {
            measuredHeight = this.b;
            if (this.j && this.e.getTabContainer() != null) {
                measuredHeight += this.b;
            }
        } else {
            measuredHeight = this.e.getVisibility() != 8 ? this.e.getMeasuredHeight() : 0;
        }
        Rect rect = this.o;
        Rect rect2 = this.q;
        rect2.set(rect);
        xu0 xu0Var = this.r;
        this.t = xu0Var;
        if (this.i || z) {
            jy jyVarB = jy.b(xu0Var.b(), this.t.d() + measuredHeight, this.t.c(), this.t.a());
            xu0 xu0Var2 = this.t;
            int i3 = Build.VERSION.SDK_INT;
            ou0 nu0Var = i3 >= 30 ? new nu0(xu0Var2) : i3 >= 29 ? new mu0(xu0Var2) : new lu0(xu0Var2);
            nu0Var.g(jyVarB);
            this.t = nu0Var.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
            this.t = xu0Var.a.l(0, measuredHeight, 0, 0);
        }
        a(this.d, rect2, true);
        if (!this.u.equals(this.t)) {
            xu0 xu0Var3 = this.t;
            this.u = xu0Var3;
            xr0.b(this.d, xu0Var3);
        }
        measureChildWithMargins(this.d, i, 0, i2, 0);
        LayoutParams layoutParams2 = (LayoutParams) this.d.getLayoutParams();
        int iMax3 = Math.max(iMax, this.d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin);
        int iMax4 = Math.max(iMax2, this.d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.d.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.k || !z) {
            return false;
        }
        this.w.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.w.getFinalY() > this.e.getHeight()) {
            d();
            this.A.run();
        } else {
            d();
            this.z.run();
        }
        this.l = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.m + i2;
        this.m = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        bu0 bu0Var;
        ct0 ct0Var;
        this.B.a = i;
        this.m = getActionBarHideOffset();
        d();
        f1 f1Var = this.v;
        if (f1Var == null || (ct0Var = (bu0Var = (bu0) f1Var).t) == null) {
            return;
        }
        ct0Var.a();
        bu0Var.t = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.e.getVisibility() != 0) {
            return false;
        }
        return this.k;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.k || this.l) {
            return;
        }
        if (this.m <= this.e.getHeight()) {
            d();
            postDelayed(this.z, 600L);
        } else {
            d();
            postDelayed(this.A, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        k();
        int i2 = this.n ^ i;
        this.n = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        f1 f1Var = this.v;
        if (f1Var != null) {
            bu0 bu0Var = (bu0) f1Var;
            bu0Var.o = !z2;
            if (z || !z2) {
                if (bu0Var.q) {
                    bu0Var.q = false;
                    bu0Var.f(true);
                }
            } else if (!bu0Var.q) {
                bu0Var.q = true;
                bu0Var.f(true);
            }
        }
        if ((i2 & 256) == 0 || this.v == null) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        jr0.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.c = i;
        f1 f1Var = this.v;
        if (f1Var != null) {
            ((bu0) f1Var).n = i;
        }
    }

    public void setActionBarHideOffset(int i) {
        d();
        this.e.setTranslationY(-Math.max(0, Math.min(i, this.e.getHeight())));
    }

    public void setActionBarVisibilityCallback(f1 f1Var) {
        this.v = f1Var;
        if (getWindowToken() != null) {
            ((bu0) this.v).n = this.c;
            int i = this.n;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                WeakHashMap weakHashMap = xr0.a;
                jr0.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.j = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.k) {
            this.k = z;
            if (z) {
                return;
            }
            d();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        k();
        l lVar = (l) this.f;
        lVar.d = i != 0 ? rp0.y(lVar.a.getContext(), i) : null;
        lVar.c();
    }

    public void setLogo(int i) {
        k();
        l lVar = (l) this.f;
        lVar.e = i != 0 ? rp0.y(lVar.a.getContext(), i) : null;
        lVar.c();
    }

    public void setOverlayMode(boolean z) {
        this.i = z;
        this.h = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // defpackage.yi
    public void setWindowCallback(Window.Callback callback) {
        k();
        ((l) this.f).k = callback;
    }

    @Override // defpackage.yi
    public void setWindowTitle(CharSequence charSequence) {
        k();
        l lVar = (l) this.f;
        if (lVar.g) {
            return;
        }
        lVar.h = charSequence;
        if ((lVar.b & 8) != 0) {
            Toolbar toolbar = lVar.a;
            toolbar.setTitle(charSequence);
            if (lVar.g) {
                xr0.q(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        xu0 xu0Var = xu0.b;
        this.r = xu0Var;
        this.s = xu0Var;
        this.t = xu0Var;
        this.u = xu0Var;
        this.y = new d1(0, this);
        this.z = new e1(this, 0);
        this.A = new e1(this, 1);
        g(context);
        this.B = new n90();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        k();
        l lVar = (l) this.f;
        lVar.d = drawable;
        lVar.c();
    }
}
