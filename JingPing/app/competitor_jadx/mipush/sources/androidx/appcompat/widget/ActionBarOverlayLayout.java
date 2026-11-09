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
import defpackage.a3;
import defpackage.b3;
import defpackage.f51;
import defpackage.g81;
import defpackage.h81;
import defpackage.i61;
import defpackage.i81;
import defpackage.j81;
import defpackage.m40;
import defpackage.oh0;
import defpackage.p81;
import defpackage.pe0;
import defpackage.ph0;
import defpackage.qh0;
import defpackage.qm;
import defpackage.r41;
import defpackage.rm;
import defpackage.s81;
import defpackage.sm;
import defpackage.t41;
import defpackage.x71;
import defpackage.z2;
import defpackage.zd0;
import io.nats.client.support.NatsJetStreamConstants;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements rm, oh0, ph0 {
    public static final int[] i0 = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    public int E;
    public int F;
    public ContentFrameLayout G;
    public ActionBarContainer H;
    public sm I;
    public Drawable J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public int P;
    public int Q;
    public final Rect R;
    public final Rect S;
    public final Rect T;
    public s81 U;
    public s81 V;
    public s81 W;
    public s81 a0;
    public b3 b0;
    public OverScroller c0;
    public ViewPropertyAnimator d0;
    public final z2 e0;
    public final a3 f0;
    public final a3 g0;
    public final qh0 h0;

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public static boolean d(View view, Rect rect, boolean z) {
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

    @Override // defpackage.oh0
    public final void a(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.oh0
    public final void b(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // defpackage.oh0
    public final void c(View view, int i, int i2, int[] iArr, int i3) {
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.J == null || this.K) {
            return;
        }
        if (this.H.getVisibility() == 0) {
            translationY = (int) (this.H.getTranslationY() + this.H.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.J.setBounds(0, translationY, getWidth(), this.J.getIntrinsicHeight() + translationY);
        this.J.draw(canvas);
    }

    public final void e() {
        removeCallbacks(this.f0);
        removeCallbacks(this.g0);
        ViewPropertyAnimator viewPropertyAnimator = this.d0;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void f(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(i0);
        this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.J = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.K = context.getApplicationInfo().targetSdkVersion < 19;
        this.c0 = new OverScroller(context);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // defpackage.ph0
    public final void g(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        i(view, i, i2, i3, i4, i5);
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
        ActionBarContainer actionBarContainer = this.H;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        qh0 qh0Var = this.h0;
        return qh0Var.b | qh0Var.a;
    }

    public CharSequence getTitle() {
        k();
        return ((l) this.I).a.getTitle();
    }

    public final void h(int i) {
        k();
        if (i == 2) {
            ((l) this.I).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i == 5) {
            ((l) this.I).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // defpackage.oh0
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // defpackage.oh0
    public final boolean j(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    public final void k() {
        sm wrapper;
        if (this.G == null) {
            this.G = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.H = (ActionBarContainer) findViewById(R$id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R$id.action_bar);
            if (callbackFindViewById instanceof sm) {
                wrapper = (sm) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.I = wrapper;
        }
    }

    public final void l(zd0 zd0Var, pe0 pe0Var) {
        k();
        l lVar = (l) this.I;
        b bVar = lVar.m;
        Toolbar toolbar = lVar.a;
        if (bVar == null) {
            b bVar2 = new b(toolbar.getContext());
            lVar.m = bVar2;
            bVar2.M = R$id.action_menu_presenter;
        }
        b bVar3 = lVar.m;
        bVar3.I = pe0Var;
        if (zd0Var == null && toolbar.E == null) {
            return;
        }
        toolbar.f();
        zd0 zd0Var2 = toolbar.E.T;
        if (zd0Var2 == zd0Var) {
            return;
        }
        if (zd0Var2 != null) {
            zd0Var2.r(toolbar.r0);
            zd0Var2.r(toolbar.s0);
        }
        if (toolbar.s0 == null) {
            toolbar.s0 = new j(toolbar);
        }
        bVar3.V = true;
        if (zd0Var != null) {
            zd0Var.b(bVar3, toolbar.N);
            zd0Var.b(toolbar.s0, toolbar.N);
        } else {
            bVar3.i(toolbar.N, null);
            toolbar.s0.i(toolbar.N, null);
            bVar3.j(true);
            toolbar.s0.j(true);
        }
        toolbar.E.setPopupTheme(toolbar.O);
        toolbar.E.setPresenter(bVar3);
        toolbar.r0 = bVar3;
        toolbar.u();
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        k();
        s81 s81VarH = s81.h(this, windowInsets);
        boolean zD = d(this.H, new Rect(s81VarH.b(), s81VarH.d(), s81VarH.c(), s81VarH.a()), false);
        WeakHashMap weakHashMap = f51.a;
        Rect rect = this.R;
        t41.b(this, s81VarH, rect);
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        p81 p81Var = s81VarH.a;
        s81 s81VarL = p81Var.l(i, i2, i3, i4);
        this.U = s81VarL;
        boolean z = true;
        if (!this.V.equals(s81VarL)) {
            this.V = this.U;
            zD = true;
        }
        Rect rect2 = this.S;
        if (rect2.equals(rect)) {
            z = zD;
        } else {
            rect2.set(rect);
        }
        if (z) {
            requestLayout();
        }
        return p81Var.a().a.c().a.b().g();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f(getContext());
        WeakHashMap weakHashMap = f51.a;
        r41.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
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
        measureChildWithMargins(this.H, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.H.getLayoutParams();
        int iMax = Math.max(0, this.H.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
        int iMax2 = Math.max(0, this.H.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.H.getMeasuredState());
        WeakHashMap weakHashMap = f51.a;
        boolean z = (getWindowSystemUiVisibility() & NatsJetStreamConstants.MAX_PULL_SIZE) != 0;
        if (z) {
            measuredHeight = this.E;
            if (this.M && this.H.getTabContainer() != null) {
                measuredHeight += this.E;
            }
        } else {
            measuredHeight = this.H.getVisibility() != 8 ? this.H.getMeasuredHeight() : 0;
        }
        Rect rect = this.R;
        Rect rect2 = this.T;
        rect2.set(rect);
        s81 s81Var = this.U;
        this.W = s81Var;
        if (this.L || z) {
            m40 m40VarB = m40.b(s81Var.b(), this.W.d() + measuredHeight, this.W.c(), this.W.a());
            s81 s81Var2 = this.W;
            int i3 = Build.VERSION.SDK_INT;
            j81 i81Var = i3 >= 30 ? new i81(s81Var2) : i3 >= 29 ? new h81(s81Var2) : new g81(s81Var2);
            i81Var.g(m40VarB);
            this.W = i81Var.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
            this.W = s81Var.a.l(0, measuredHeight, 0, 0);
        }
        d(this.G, rect2, true);
        if (!this.a0.equals(this.W)) {
            s81 s81Var3 = this.W;
            this.a0 = s81Var3;
            f51.b(this.G, s81Var3);
        }
        measureChildWithMargins(this.G, i, 0, i2, 0);
        LayoutParams layoutParams2 = (LayoutParams) this.G.getLayoutParams();
        int iMax3 = Math.max(iMax, this.G.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin);
        int iMax4 = Math.max(iMax2, this.G.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.G.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.N || !z) {
            return false;
        }
        this.c0.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.c0.getFinalY() > this.H.getHeight()) {
            e();
            this.g0.run();
        } else {
            e();
            this.f0.run();
        }
        this.O = true;
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
        int i5 = this.P + i2;
        this.P = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        x71 x71Var;
        i61 i61Var;
        this.h0.a = i;
        this.P = getActionBarHideOffset();
        e();
        b3 b3Var = this.b0;
        if (b3Var == null || (i61Var = (x71Var = (x71) b3Var).s) == null) {
            return;
        }
        i61Var.a();
        x71Var.s = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.H.getVisibility() != 0) {
            return false;
        }
        return this.N;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.N || this.O) {
            return;
        }
        if (this.P <= this.H.getHeight()) {
            e();
            postDelayed(this.f0, 600L);
        } else {
            e();
            postDelayed(this.g0, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        k();
        int i2 = this.Q ^ i;
        this.Q = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & NatsJetStreamConstants.MAX_PULL_SIZE) != 0;
        b3 b3Var = this.b0;
        if (b3Var != null) {
            x71 x71Var = (x71) b3Var;
            x71Var.o = !z2;
            if (z || !z2) {
                if (x71Var.p) {
                    x71Var.p = false;
                    x71Var.f(true);
                }
            } else if (!x71Var.p) {
                x71Var.p = true;
                x71Var.f(true);
            }
        }
        if ((i2 & NatsJetStreamConstants.MAX_PULL_SIZE) == 0 || this.b0 == null) {
            return;
        }
        WeakHashMap weakHashMap = f51.a;
        r41.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.F = i;
        b3 b3Var = this.b0;
        if (b3Var != null) {
            ((x71) b3Var).n = i;
        }
    }

    public void setActionBarHideOffset(int i) {
        e();
        this.H.setTranslationY(-Math.max(0, Math.min(i, this.H.getHeight())));
    }

    public void setActionBarVisibilityCallback(b3 b3Var) {
        this.b0 = b3Var;
        if (getWindowToken() != null) {
            ((x71) this.b0).n = this.F;
            int i = this.Q;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                WeakHashMap weakHashMap = f51.a;
                r41.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.M = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.N) {
            this.N = z;
            if (z) {
                return;
            }
            e();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        k();
        l lVar = (l) this.I;
        lVar.d = i != 0 ? qm.z(lVar.a.getContext(), i) : null;
        lVar.c();
    }

    public void setLogo(int i) {
        k();
        l lVar = (l) this.I;
        lVar.e = i != 0 ? qm.z(lVar.a.getContext(), i) : null;
        lVar.c();
    }

    public void setOverlayMode(boolean z) {
        this.L = z;
        this.K = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // defpackage.rm
    public void setWindowCallback(Window.Callback callback) {
        k();
        ((l) this.I).k = callback;
    }

    @Override // defpackage.rm
    public void setWindowTitle(CharSequence charSequence) {
        k();
        l lVar = (l) this.I;
        if (lVar.g) {
            return;
        }
        lVar.h = charSequence;
        if ((lVar.b & 8) != 0) {
            Toolbar toolbar = lVar.a;
            toolbar.setTitle(charSequence);
            if (lVar.g) {
                f51.p(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = 0;
        this.R = new Rect();
        this.S = new Rect();
        this.T = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        s81 s81Var = s81.b;
        this.U = s81Var;
        this.V = s81Var;
        this.W = s81Var;
        this.a0 = s81Var;
        this.e0 = new z2(0, this);
        this.f0 = new a3(this, 0);
        this.g0 = new a3(this, 1);
        f(context);
        this.h0 = new qh0();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        k();
        l lVar = (l) this.I;
        lVar.d = drawable;
        lVar.c();
    }
}
