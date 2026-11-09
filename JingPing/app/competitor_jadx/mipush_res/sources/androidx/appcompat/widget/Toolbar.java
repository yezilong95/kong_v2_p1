package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.ActionBar$LayoutParams;
import androidx.customview.view.AbsSavedState;
import defpackage.a11;
import defpackage.c11;
import defpackage.cw0;
import defpackage.cy0;
import defpackage.d11;
import defpackage.de0;
import defpackage.f51;
import defpackage.i6;
import defpackage.la0;
import defpackage.ou;
import defpackage.q61;
import defpackage.qm;
import defpackage.sm;
import defpackage.vp0;
import defpackage.wl0;
import defpackage.y2;
import defpackage.zd0;
import defpackage.zn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public ActionMenuView E;
    public AppCompatTextView F;
    public AppCompatTextView G;
    public AppCompatImageButton H;
    public AppCompatImageView I;
    public final Drawable J;
    public final CharSequence K;
    public AppCompatImageButton L;
    public View M;
    public Context N;
    public int O;
    public int P;
    public int Q;
    public final int R;
    public final int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public vp0 a0;
    public int b0;
    public int c0;
    public final int d0;
    public CharSequence e0;
    public CharSequence f0;
    public ColorStateList g0;
    public ColorStateList h0;
    public boolean i0;
    public boolean j0;
    public final ArrayList k0;
    public final ArrayList l0;
    public final int[] m0;
    public final i6 n0;
    public ArrayList o0;
    public final cw0 p0;
    public l q0;
    public b r0;
    public j s0;
    public boolean t0;
    public OnBackInvokedCallback u0;
    public OnBackInvokedDispatcher v0;
    public boolean w0;
    public final defpackage.f x0;

    public static class LayoutParams extends ActionBar$LayoutParams {
        public int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new k();
        public int G;
        public boolean H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readInt();
            this.H = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.G);
            parcel.writeInt(this.H ? 1 : 0);
        }
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i = 0; i < menu.size(); i++) {
            arrayList.add(menu.getItem(i));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new cy0(getContext());
    }

    public static LayoutParams h() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.b = 0;
        layoutParams.a = 8388627;
        return layoutParams;
    }

    public static LayoutParams i(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            LayoutParams layoutParams3 = new LayoutParams(layoutParams2);
            layoutParams3.b = 0;
            layoutParams3.b = layoutParams2.b;
            return layoutParams3;
        }
        if (layoutParams instanceof ActionBar$LayoutParams) {
            LayoutParams layoutParams4 = new LayoutParams((ActionBar$LayoutParams) layoutParams);
            layoutParams4.b = 0;
            return layoutParams4;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            LayoutParams layoutParams5 = new LayoutParams(layoutParams);
            layoutParams5.b = 0;
            return layoutParams5;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        LayoutParams layoutParams6 = new LayoutParams(marginLayoutParams);
        layoutParams6.b = 0;
        ((ViewGroup.MarginLayoutParams) layoutParams6).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) layoutParams6).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) layoutParams6).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) layoutParams6).bottomMargin = marginLayoutParams.bottomMargin;
        return layoutParams6;
    }

    public static int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(ArrayList arrayList, int i) {
        WeakHashMap weakHashMap = f51.a;
        boolean z = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        arrayList.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b == 0 && t(childAt)) {
                    int i3 = layoutParams.a;
                    WeakHashMap weakHashMap2 = f51.a;
                    int layoutDirection = getLayoutDirection();
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i3, layoutDirection) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = layoutDirection == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.b == 0 && t(childAt2)) {
                int i5 = layoutParams2.a;
                WeakHashMap weakHashMap3 = f51.a;
                int layoutDirection2 = getLayoutDirection();
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i5, layoutDirection2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = layoutDirection2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    public final void b(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        LayoutParams layoutParamsH = layoutParams == null ? h() : !checkLayoutParams(layoutParams) ? i(layoutParams) : (LayoutParams) layoutParams;
        layoutParamsH.b = 1;
        if (!z || this.M == null) {
            addView(view, layoutParamsH);
        } else {
            view.setLayoutParams(layoutParamsH);
            this.l0.add(view);
        }
    }

    public final void c() {
        if (this.L == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.L = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.J);
            this.L.setContentDescription(this.K);
            LayoutParams layoutParamsH = h();
            layoutParamsH.a = (this.R & 112) | 8388611;
            layoutParamsH.b = 2;
            this.L.setLayoutParams(layoutParamsH);
            this.L.setOnClickListener(new y2(5, this));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public final void d() {
        if (this.a0 == null) {
            vp0 vp0Var = new vp0();
            vp0Var.a = 0;
            vp0Var.b = 0;
            vp0Var.c = Integer.MIN_VALUE;
            vp0Var.d = Integer.MIN_VALUE;
            vp0Var.e = 0;
            vp0Var.f = 0;
            vp0Var.g = false;
            vp0Var.h = false;
            this.a0 = vp0Var;
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.E;
        if (actionMenuView.T == null) {
            zd0 zd0Var = (zd0) actionMenuView.getMenu();
            if (this.s0 == null) {
                this.s0 = new j(this);
            }
            this.E.setExpandedActionViewsExclusive(true);
            zd0Var.b(this.s0, this.N);
            u();
        }
    }

    public final void f() {
        if (this.E == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.E = actionMenuView;
            actionMenuView.setPopupTheme(this.O);
            this.E.setOnMenuItemClickListener(this.p0);
            ActionMenuView actionMenuView2 = this.E;
            wl0 wl0Var = new wl0((Object) this);
            actionMenuView2.getClass();
            actionMenuView2.b0 = wl0Var;
            LayoutParams layoutParamsH = h();
            layoutParamsH.a = (this.R & 112) | 8388613;
            this.E.setLayoutParams(layoutParamsH);
            b(this.E, false);
        }
    }

    public final void g() {
        if (this.H == null) {
            this.H = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            LayoutParams layoutParamsH = h();
            layoutParamsH.a = (this.R & 112) | 8388611;
            this.H.setLayoutParams(layoutParamsH);
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return h();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return i(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        AppCompatImageButton appCompatImageButton = this.L;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        AppCompatImageButton appCompatImageButton = this.L;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        vp0 vp0Var = this.a0;
        if (vp0Var != null) {
            return vp0Var.g ? vp0Var.a : vp0Var.b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.c0;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        vp0 vp0Var = this.a0;
        if (vp0Var != null) {
            return vp0Var.a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        vp0 vp0Var = this.a0;
        if (vp0Var != null) {
            return vp0Var.b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        vp0 vp0Var = this.a0;
        if (vp0Var != null) {
            return vp0Var.g ? vp0Var.b : vp0Var.a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.b0;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        zd0 zd0Var;
        ActionMenuView actionMenuView = this.E;
        return (actionMenuView == null || (zd0Var = actionMenuView.T) == null || !zd0Var.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.c0, 0));
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap weakHashMap = f51.a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap weakHashMap = f51.a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.b0, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        AppCompatImageView appCompatImageView = this.I;
        if (appCompatImageView != null) {
            return appCompatImageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        AppCompatImageView appCompatImageView = this.I;
        if (appCompatImageView != null) {
            return appCompatImageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.E.getMenu();
    }

    public View getNavButtonView() {
        return this.H;
    }

    public CharSequence getNavigationContentDescription() {
        AppCompatImageButton appCompatImageButton = this.H;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        AppCompatImageButton appCompatImageButton = this.H;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public b getOuterActionMenuPresenter() {
        return this.r0;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.E.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.N;
    }

    public int getPopupTheme() {
        return this.O;
    }

    public CharSequence getSubtitle() {
        return this.f0;
    }

    public final TextView getSubtitleTextView() {
        return this.G;
    }

    public CharSequence getTitle() {
        return this.e0;
    }

    public int getTitleMarginBottom() {
        return this.W;
    }

    public int getTitleMarginEnd() {
        return this.U;
    }

    public int getTitleMarginStart() {
        return this.T;
    }

    public int getTitleMarginTop() {
        return this.V;
    }

    public final TextView getTitleTextView() {
        return this.F;
    }

    public sm getWrapper() {
        Drawable drawable;
        if (this.q0 == null) {
            int i = R$string.abc_action_bar_up_description;
            l lVar = new l();
            lVar.n = 0;
            lVar.a = this;
            lVar.h = getTitle();
            lVar.i = getSubtitle();
            lVar.g = lVar.h != null;
            lVar.f = getNavigationIcon();
            ou ouVarP = ou.P(getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle);
            lVar.o = ouVarP.A(R$styleable.ActionBar_homeAsUpIndicator);
            int i2 = R$styleable.ActionBar_title;
            TypedArray typedArray = (TypedArray) ouVarP.G;
            CharSequence text = typedArray.getText(i2);
            if (!TextUtils.isEmpty(text)) {
                lVar.g = true;
                lVar.h = text;
                if ((lVar.b & 8) != 0) {
                    Toolbar toolbar = lVar.a;
                    toolbar.setTitle(text);
                    if (lVar.g) {
                        f51.p(toolbar.getRootView(), text);
                    }
                }
            }
            CharSequence text2 = typedArray.getText(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(text2)) {
                lVar.i = text2;
                if ((lVar.b & 8) != 0) {
                    setSubtitle(text2);
                }
            }
            Drawable drawableA = ouVarP.A(R$styleable.ActionBar_logo);
            if (drawableA != null) {
                lVar.e = drawableA;
                lVar.c();
            }
            Drawable drawableA2 = ouVarP.A(R$styleable.ActionBar_icon);
            if (drawableA2 != null) {
                lVar.d = drawableA2;
                lVar.c();
            }
            if (lVar.f == null && (drawable = lVar.o) != null) {
                lVar.f = drawable;
                int i3 = lVar.b & 4;
                Toolbar toolbar2 = lVar.a;
                if (i3 != 0) {
                    toolbar2.setNavigationIcon(drawable);
                } else {
                    toolbar2.setNavigationIcon((Drawable) null);
                }
            }
            lVar.a(typedArray.getInt(R$styleable.ActionBar_displayOptions, 0));
            int resourceId = typedArray.getResourceId(R$styleable.ActionBar_customNavigationLayout, 0);
            if (resourceId != 0) {
                View viewInflate = LayoutInflater.from(getContext()).inflate(resourceId, (ViewGroup) this, false);
                View view = lVar.c;
                if (view != null && (lVar.b & 16) != 0) {
                    removeView(view);
                }
                lVar.c = viewInflate;
                if (viewInflate != null && (lVar.b & 16) != 0) {
                    addView(viewInflate);
                }
                lVar.a(lVar.b | 16);
            }
            int layoutDimension = typedArray.getLayoutDimension(R$styleable.ActionBar_height, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = layoutDimension;
                setLayoutParams(layoutParams);
            }
            int dimensionPixelOffset = typedArray.getDimensionPixelOffset(R$styleable.ActionBar_contentInsetStart, -1);
            int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(R$styleable.ActionBar_contentInsetEnd, -1);
            if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
                int iMax = Math.max(dimensionPixelOffset, 0);
                int iMax2 = Math.max(dimensionPixelOffset2, 0);
                d();
                this.a0.a(iMax, iMax2);
            }
            int resourceId2 = typedArray.getResourceId(R$styleable.ActionBar_titleTextStyle, 0);
            if (resourceId2 != 0) {
                Context context = getContext();
                this.P = resourceId2;
                AppCompatTextView appCompatTextView = this.F;
                if (appCompatTextView != null) {
                    appCompatTextView.setTextAppearance(context, resourceId2);
                }
            }
            int resourceId3 = typedArray.getResourceId(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (resourceId3 != 0) {
                Context context2 = getContext();
                this.Q = resourceId3;
                AppCompatTextView appCompatTextView2 = this.G;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTextAppearance(context2, resourceId3);
                }
            }
            int resourceId4 = typedArray.getResourceId(R$styleable.ActionBar_popupTheme, 0);
            if (resourceId4 != 0) {
                setPopupTheme(resourceId4);
            }
            ouVarP.S();
            if (i != lVar.n) {
                lVar.n = i;
                if (TextUtils.isEmpty(getNavigationContentDescription())) {
                    int i4 = lVar.n;
                    lVar.j = i4 != 0 ? getContext().getString(i4) : null;
                    lVar.b();
                }
            }
            lVar.j = getNavigationContentDescription();
            setNavigationOnClickListener(new d11(lVar));
            this.q0 = lVar;
        }
        return this.q0;
    }

    public final int j(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = layoutParams.a & 112;
        if (i3 != 16 && i3 != 48 && i3 != 80) {
            i3 = this.d0 & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        if (iMax < i4) {
            iMax = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            if (i5 < i6) {
                iMax = Math.max(0, iMax - (i6 - i5));
            }
        }
        return paddingTop + iMax;
    }

    public void m(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public final void n() {
        Iterator it = this.o0.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        getMenuInflater();
        Iterator it2 = ((CopyOnWriteArrayList) this.n0.G).iterator();
        if (it2.hasNext()) {
            throw zn.i(it2);
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.o0 = currentMenuItems2;
    }

    public final boolean o(View view) {
        return view.getParent() == this || this.l0.contains(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.x0);
        u();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.j0 = false;
        }
        if (!this.j0) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.j0 = true;
            }
        }
        if (actionMasked != 10 && actionMasked != 3) {
            return true;
        }
        this.j0 = false;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0295 A[LOOP:0: B:104:0x0293->B:105:0x0295, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02ad A[LOOP:1: B:107:0x02ab->B:108:0x02ad, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02cd A[LOOP:2: B:110:0x02cb->B:111:0x02cd, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0321 A[LOOP:3: B:119:0x031f->B:120:0x0321, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x021c  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 818
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int iK;
        int iMax;
        int iCombineMeasuredStates;
        int iK2;
        int iL;
        int iCombineMeasuredStates2;
        int iMax2;
        boolean zA = q61.a(this);
        int i3 = !zA ? 1 : 0;
        int i4 = 0;
        if (t(this.H)) {
            s(this.H, i, 0, i2, this.S);
            iK = k(this.H) + this.H.getMeasuredWidth();
            iMax = Math.max(0, l(this.H) + this.H.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.H.getMeasuredState());
        } else {
            iK = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (t(this.L)) {
            s(this.L, i, 0, i2, this.S);
            iK = k(this.L) + this.L.getMeasuredWidth();
            iMax = Math.max(iMax, l(this.L) + this.L.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.L.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iK);
        int iMax4 = Math.max(0, currentContentInsetStart - iK);
        int[] iArr = this.m0;
        iArr[zA ? 1 : 0] = iMax4;
        if (t(this.E)) {
            s(this.E, i, iMax3, i2, this.S);
            iK2 = k(this.E) + this.E.getMeasuredWidth();
            iMax = Math.max(iMax, l(this.E) + this.E.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.E.getMeasuredState());
        } else {
            iK2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, iK2);
        iArr[i3] = Math.max(0, currentContentInsetEnd - iK2);
        if (t(this.M)) {
            iMax5 += r(this.M, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, l(this.M) + this.M.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.M.getMeasuredState());
        }
        if (t(this.I)) {
            iMax5 += r(this.I, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, l(this.I) + this.I.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.I.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (((LayoutParams) childAt.getLayoutParams()).b == 0 && t(childAt)) {
                iMax5 += r(childAt, i, iMax5, i2, 0, iArr);
                int iMax6 = Math.max(iMax, l(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
                iMax = iMax6;
            } else {
                iMax5 = iMax5;
            }
        }
        int i6 = iMax5;
        int i7 = this.V + this.W;
        int i8 = this.T + this.U;
        if (t(this.F)) {
            r(this.F, i, i6 + i8, i2, i7, iArr);
            int iK3 = k(this.F) + this.F.getMeasuredWidth();
            iL = l(this.F) + this.F.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.F.getMeasuredState());
            iMax2 = iK3;
        } else {
            iL = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (t(this.G)) {
            iMax2 = Math.max(iMax2, r(this.G, i, i6 + i8, i2, i7 + iL, iArr));
            iL += l(this.G) + this.G.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.G.getMeasuredState());
        }
        int iMax7 = Math.max(iMax, iL);
        int paddingRight = getPaddingRight() + getPaddingLeft() + i6 + iMax2;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax7;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16);
        if (!this.t0) {
            i4 = iResolveSizeAndState2;
            break;
        }
        int childCount2 = getChildCount();
        for (int i9 = 0; i9 < childCount2; i9++) {
            View childAt2 = getChildAt(i9);
            if (t(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                i4 = iResolveSizeAndState2;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, i4);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.E);
        ActionMenuView actionMenuView = this.E;
        zd0 zd0Var = actionMenuView != null ? actionMenuView.T : null;
        int i = savedState.G;
        if (i != 0 && this.s0 != null && zd0Var != null && (menuItemFindItem = zd0Var.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.H) {
            defpackage.f fVar = this.x0;
            removeCallbacks(fVar);
            post(fVar);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        d();
        vp0 vp0Var = this.a0;
        boolean z = i == 1;
        if (z == vp0Var.g) {
            return;
        }
        vp0Var.g = z;
        if (!vp0Var.h) {
            vp0Var.a = vp0Var.e;
            vp0Var.b = vp0Var.f;
            return;
        }
        if (z) {
            int i2 = vp0Var.d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = vp0Var.e;
            }
            vp0Var.a = i2;
            int i3 = vp0Var.c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = vp0Var.f;
            }
            vp0Var.b = i3;
            return;
        }
        int i4 = vp0Var.c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = vp0Var.e;
        }
        vp0Var.a = i4;
        int i5 = vp0Var.d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = vp0Var.f;
        }
        vp0Var.b = i5;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        b bVar;
        de0 de0Var;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        j jVar = this.s0;
        if (jVar != null && (de0Var = jVar.F) != null) {
            savedState.G = de0Var.a;
        }
        ActionMenuView actionMenuView = this.E;
        savedState.H = (actionMenuView == null || (bVar = actionMenuView.a0) == null || !bVar.h()) ? false : true;
        return savedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.i0 = false;
        }
        if (!this.i0) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.i0 = true;
            }
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        this.i0 = false;
        return true;
    }

    public final int p(View view, int i, int i2, int[] iArr) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin - iArr[0];
        int iMax = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int iJ = j(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iJ, iMax + measuredWidth, view.getMeasuredHeight() + iJ);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + iMax;
    }

    public final int q(View view, int i, int i2, int[] iArr) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iJ = j(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iJ, iMax, view.getMeasuredHeight() + iJ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
    }

    public final int r(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void s(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i4 >= 0) {
            if (mode != 0) {
                i4 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i4);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if (this.w0 != z) {
            this.w0 = z;
            u();
        }
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(qm.z(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.t0 = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.c0) {
            this.c0 = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.b0) {
            this.b0 = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(qm.z(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(qm.z(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.H.setOnClickListener(onClickListener);
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.E.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.O != i) {
            this.O = i;
            if (i == 0) {
                this.N = getContext();
            } else {
                this.N = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.W = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.U = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.T = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.V = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public final boolean t(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u() {
        /*
            r4 = this;
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            if (r1 < r2) goto L4f
            android.window.OnBackInvokedDispatcher r1 = defpackage.b11.a(r4)
            androidx.appcompat.widget.j r2 = r4.s0
            if (r2 == 0) goto L23
            de0 r2 = r2.F
            if (r2 == 0) goto L23
            if (r1 == 0) goto L23
            java.util.WeakHashMap r2 = defpackage.f51.a
            boolean r2 = r4.isAttachedToWindow()
            if (r2 == 0) goto L23
            boolean r2 = r4.w0
            if (r2 == 0) goto L23
            r2 = 1
            goto L24
        L23:
            r2 = r0
        L24:
            if (r2 == 0) goto L41
            android.window.OnBackInvokedDispatcher r3 = r4.v0
            if (r3 != 0) goto L41
            android.window.OnBackInvokedCallback r2 = r4.u0
            if (r2 != 0) goto L39
            a11 r2 = new a11
            r2.<init>(r4, r0)
            android.window.OnBackInvokedCallback r0 = defpackage.b11.b(r2)
            r4.u0 = r0
        L39:
            android.window.OnBackInvokedCallback r0 = r4.u0
            defpackage.b11.c(r1, r0)
            r4.v0 = r1
            return
        L41:
            if (r2 != 0) goto L4f
            android.window.OnBackInvokedDispatcher r0 = r4.v0
            if (r0 == 0) goto L4f
            android.window.OnBackInvokedCallback r1 = r4.u0
            defpackage.b11.d(r0, r1)
            r0 = 0
            r4.v0 = r0
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.u():void");
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        AppCompatImageButton appCompatImageButton = this.L;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.L.setImageDrawable(drawable);
        } else {
            AppCompatImageButton appCompatImageButton = this.L;
            if (appCompatImageButton != null) {
                appCompatImageButton.setImageDrawable(this.J);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.I == null) {
                this.I = new AppCompatImageView(getContext());
            }
            if (!o(this.I)) {
                b(this.I, true);
            }
        } else {
            AppCompatImageView appCompatImageView = this.I;
            if (appCompatImageView != null && o(appCompatImageView)) {
                removeView(this.I);
                this.l0.remove(this.I);
            }
        }
        AppCompatImageView appCompatImageView2 = this.I;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.I == null) {
            this.I = new AppCompatImageView(getContext());
        }
        AppCompatImageView appCompatImageView = this.I;
        if (appCompatImageView != null) {
            appCompatImageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        AppCompatImageButton appCompatImageButton = this.H;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
            la0.e0(this.H, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!o(this.H)) {
                b(this.H, true);
            }
        } else {
            AppCompatImageButton appCompatImageButton = this.H;
            if (appCompatImageButton != null && o(appCompatImageButton)) {
                removeView(this.H);
                this.l0.remove(this.H);
            }
        }
        AppCompatImageButton appCompatImageButton2 = this.H;
        if (appCompatImageButton2 != null) {
            appCompatImageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.G;
            if (appCompatTextView != null && o(appCompatTextView)) {
                removeView(this.G);
                this.l0.remove(this.G);
            }
        } else {
            if (this.G == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.G = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.G.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.Q;
                if (i != 0) {
                    this.G.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.h0;
                if (colorStateList != null) {
                    this.G.setTextColor(colorStateList);
                }
            }
            if (!o(this.G)) {
                b(this.G, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.G;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.f0 = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.h0 = colorStateList;
        AppCompatTextView appCompatTextView = this.G;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.F;
            if (appCompatTextView != null && o(appCompatTextView)) {
                removeView(this.F);
                this.l0.remove(this.F);
            }
        } else {
            if (this.F == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.F = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.F.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.P;
                if (i != 0) {
                    this.F.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.g0;
                if (colorStateList != null) {
                    this.F.setTextColor(colorStateList);
                }
            }
            if (!o(this.F)) {
                b(this.F, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.F;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.e0 = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.g0 = colorStateList;
        AppCompatTextView appCompatTextView = this.F;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d0 = 8388627;
        this.k0 = new ArrayList();
        this.l0 = new ArrayList();
        this.m0 = new int[2];
        this.n0 = new i6(new a11(this, 1));
        this.o0 = new ArrayList();
        this.p0 = new cw0(3, this);
        this.x0 = new defpackage.f(25, this);
        ou ouVarP = ou.P(getContext(), attributeSet, R$styleable.Toolbar, i);
        f51.n(this, context, R$styleable.Toolbar, attributeSet, (TypedArray) ouVarP.G, i);
        int i2 = R$styleable.Toolbar_titleTextAppearance;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        this.P = typedArray.getResourceId(i2, 0);
        this.Q = typedArray.getResourceId(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.d0 = typedArray.getInteger(R$styleable.Toolbar_android_gravity, 8388627);
        this.R = typedArray.getInteger(R$styleable.Toolbar_buttonGravity, 48);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMargin, 0);
        dimensionPixelOffset = typedArray.hasValue(R$styleable.Toolbar_titleMargins) ? typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMargins, dimensionPixelOffset) : dimensionPixelOffset;
        this.W = dimensionPixelOffset;
        this.V = dimensionPixelOffset;
        this.U = dimensionPixelOffset;
        this.T = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginStart, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.T = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginEnd, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.U = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginTop, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.V = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginBottom, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.W = dimensionPixelOffset5;
        }
        this.S = typedArray.getDimensionPixelSize(R$styleable.Toolbar_maxButtonHeight, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.Toolbar_contentInsetLeft, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(R$styleable.Toolbar_contentInsetRight, 0);
        d();
        vp0 vp0Var = this.a0;
        vp0Var.h = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            vp0Var.e = dimensionPixelSize;
            vp0Var.a = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            vp0Var.f = dimensionPixelSize2;
            vp0Var.b = dimensionPixelSize2;
        }
        if (dimensionPixelOffset6 != Integer.MIN_VALUE || dimensionPixelOffset7 != Integer.MIN_VALUE) {
            vp0Var.a(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.b0 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.c0 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.J = ouVarP.A(R$styleable.Toolbar_collapseIcon);
        this.K = typedArray.getText(R$styleable.Toolbar_collapseContentDescription);
        CharSequence text = typedArray.getText(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.N = getContext();
        setPopupTheme(typedArray.getResourceId(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawableA = ouVarP.A(R$styleable.Toolbar_navigationIcon);
        if (drawableA != null) {
            setNavigationIcon(drawableA);
        }
        CharSequence text3 = typedArray.getText(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawableA2 = ouVarP.A(R$styleable.Toolbar_logo);
        if (drawableA2 != null) {
            setLogo(drawableA2);
        }
        CharSequence text4 = typedArray.getText(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(R$styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(ouVarP.y(R$styleable.Toolbar_titleTextColor));
        }
        if (typedArray.hasValue(R$styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(ouVarP.y(R$styleable.Toolbar_subtitleTextColor));
        }
        if (typedArray.hasValue(R$styleable.Toolbar_menu)) {
            m(typedArray.getResourceId(R$styleable.Toolbar_menu, 0));
        }
        ouVarP.S();
    }

    public void setOnMenuItemClickListener(c11 c11Var) {
    }
}
