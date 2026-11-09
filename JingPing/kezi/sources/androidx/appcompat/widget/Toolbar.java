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
import defpackage.ag0;
import defpackage.c1;
import defpackage.e5;
import defpackage.hk;
import defpackage.i60;
import defpackage.jq0;
import defpackage.kt0;
import defpackage.m60;
import defpackage.ol0;
import defpackage.oo0;
import defpackage.po;
import defpackage.po0;
import defpackage.ro0;
import defpackage.rp0;
import defpackage.so0;
import defpackage.v6;
import defpackage.xr0;
import defpackage.zi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public ColorStateList A;
    public ColorStateList B;
    public boolean C;
    public boolean D;
    public final ArrayList E;
    public final ArrayList F;
    public final int[] G;
    public final e5 H;
    public ArrayList I;
    public final po0 J;
    public l K;
    public b L;
    public j M;
    public boolean N;
    public OnBackInvokedCallback O;
    public OnBackInvokedDispatcher P;
    public boolean Q;
    public final v6 R;
    public ActionMenuView b;
    public AppCompatTextView c;
    public AppCompatTextView d;
    public AppCompatImageButton e;
    public AppCompatImageView f;
    public final Drawable g;
    public final CharSequence h;
    public AppCompatImageButton i;
    public View j;
    public Context k;
    public int l;
    public int m;
    public int n;
    public final int o;
    public final int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public ag0 u;
    public int v;
    public int w;
    public final int x;
    public CharSequence y;
    public CharSequence z;

    public static class LayoutParams extends ActionBar$LayoutParams {
        public int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new k();
        public int d;
        public boolean e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readInt();
            this.e = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e ? 1 : 0);
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
        return new ol0(getContext());
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
        WeakHashMap weakHashMap = xr0.a;
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
                    WeakHashMap weakHashMap2 = xr0.a;
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
                WeakHashMap weakHashMap3 = xr0.a;
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
        if (!z || this.j == null) {
            addView(view, layoutParamsH);
        } else {
            view.setLayoutParams(layoutParamsH);
            this.F.add(view);
        }
    }

    public final void c() {
        if (this.i == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.i = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.g);
            this.i.setContentDescription(this.h);
            LayoutParams layoutParamsH = h();
            layoutParamsH.a = (this.o & 112) | 8388611;
            layoutParamsH.b = 2;
            this.i.setLayoutParams(layoutParamsH);
            this.i.setOnClickListener(new c1(5, this));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public final void d() {
        if (this.u == null) {
            ag0 ag0Var = new ag0();
            ag0Var.a = 0;
            ag0Var.b = 0;
            ag0Var.c = Integer.MIN_VALUE;
            ag0Var.d = Integer.MIN_VALUE;
            ag0Var.e = 0;
            ag0Var.f = 0;
            ag0Var.g = false;
            ag0Var.h = false;
            this.u = ag0Var;
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.b;
        if (actionMenuView.q == null) {
            i60 i60Var = (i60) actionMenuView.getMenu();
            if (this.M == null) {
                this.M = new j(this);
            }
            this.b.setExpandedActionViewsExclusive(true);
            i60Var.b(this.M, this.k);
            u();
        }
    }

    public final void f() {
        if (this.b == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.b = actionMenuView;
            actionMenuView.setPopupTheme(this.l);
            this.b.setOnMenuItemClickListener(this.J);
            ActionMenuView actionMenuView2 = this.b;
            po0 po0Var = new po0(this);
            actionMenuView2.getClass();
            actionMenuView2.v = po0Var;
            LayoutParams layoutParamsH = h();
            layoutParamsH.a = (this.o & 112) | 8388613;
            this.b.setLayoutParams(layoutParamsH);
            b(this.b, false);
        }
    }

    public final void g() {
        if (this.e == null) {
            this.e = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            LayoutParams layoutParamsH = h();
            layoutParamsH.a = (this.o & 112) | 8388611;
            this.e.setLayoutParams(layoutParamsH);
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
        AppCompatImageButton appCompatImageButton = this.i;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        AppCompatImageButton appCompatImageButton = this.i;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        ag0 ag0Var = this.u;
        if (ag0Var != null) {
            return ag0Var.g ? ag0Var.a : ag0Var.b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.w;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        ag0 ag0Var = this.u;
        if (ag0Var != null) {
            return ag0Var.a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        ag0 ag0Var = this.u;
        if (ag0Var != null) {
            return ag0Var.b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        ag0 ag0Var = this.u;
        if (ag0Var != null) {
            return ag0Var.g ? ag0Var.b : ag0Var.a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.v;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        i60 i60Var;
        ActionMenuView actionMenuView = this.b;
        return (actionMenuView == null || (i60Var = actionMenuView.q) == null || !i60Var.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.w, 0));
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap weakHashMap = xr0.a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap weakHashMap = xr0.a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.v, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        AppCompatImageView appCompatImageView = this.f;
        if (appCompatImageView != null) {
            return appCompatImageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        AppCompatImageView appCompatImageView = this.f;
        if (appCompatImageView != null) {
            return appCompatImageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.b.getMenu();
    }

    public View getNavButtonView() {
        return this.e;
    }

    public CharSequence getNavigationContentDescription() {
        AppCompatImageButton appCompatImageButton = this.e;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        AppCompatImageButton appCompatImageButton = this.e;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public b getOuterActionMenuPresenter() {
        return this.L;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.b.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.k;
    }

    public int getPopupTheme() {
        return this.l;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    public final TextView getSubtitleTextView() {
        return this.d;
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public int getTitleMarginBottom() {
        return this.t;
    }

    public int getTitleMarginEnd() {
        return this.r;
    }

    public int getTitleMarginStart() {
        return this.q;
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    public final TextView getTitleTextView() {
        return this.c;
    }

    public zi getWrapper() {
        Drawable drawable;
        if (this.K == null) {
            int i = R$string.abc_action_bar_up_description;
            l lVar = new l();
            lVar.n = 0;
            lVar.a = this;
            lVar.h = getTitle();
            lVar.i = getSubtitle();
            lVar.g = lVar.h != null;
            lVar.f = getNavigationIcon();
            po poVarN = po.N(getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle);
            lVar.o = poVarN.y(R$styleable.ActionBar_homeAsUpIndicator);
            int i2 = R$styleable.ActionBar_title;
            TypedArray typedArray = (TypedArray) poVarN.d;
            CharSequence text = typedArray.getText(i2);
            if (!TextUtils.isEmpty(text)) {
                lVar.g = true;
                lVar.h = text;
                if ((lVar.b & 8) != 0) {
                    Toolbar toolbar = lVar.a;
                    toolbar.setTitle(text);
                    if (lVar.g) {
                        xr0.q(toolbar.getRootView(), text);
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
            Drawable drawableY = poVarN.y(R$styleable.ActionBar_logo);
            if (drawableY != null) {
                lVar.e = drawableY;
                lVar.c();
            }
            Drawable drawableY2 = poVarN.y(R$styleable.ActionBar_icon);
            if (drawableY2 != null) {
                lVar.d = drawableY2;
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
                this.u.a(iMax, iMax2);
            }
            int resourceId2 = typedArray.getResourceId(R$styleable.ActionBar_titleTextStyle, 0);
            if (resourceId2 != 0) {
                Context context = getContext();
                this.m = resourceId2;
                AppCompatTextView appCompatTextView = this.c;
                if (appCompatTextView != null) {
                    appCompatTextView.setTextAppearance(context, resourceId2);
                }
            }
            int resourceId3 = typedArray.getResourceId(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (resourceId3 != 0) {
                Context context2 = getContext();
                this.n = resourceId3;
                AppCompatTextView appCompatTextView2 = this.d;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTextAppearance(context2, resourceId3);
                }
            }
            int resourceId4 = typedArray.getResourceId(R$styleable.ActionBar_popupTheme, 0);
            if (resourceId4 != 0) {
                setPopupTheme(resourceId4);
            }
            poVarN.P();
            if (i != lVar.n) {
                lVar.n = i;
                if (TextUtils.isEmpty(getNavigationContentDescription())) {
                    int i4 = lVar.n;
                    lVar.j = i4 != 0 ? getContext().getString(i4) : null;
                    lVar.b();
                }
            }
            lVar.j = getNavigationContentDescription();
            setNavigationOnClickListener(new so0(lVar));
            this.K = lVar;
        }
        return this.K;
    }

    public final int j(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = layoutParams.a & 112;
        if (i3 != 16 && i3 != 48 && i3 != 80) {
            i3 = this.x & 112;
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
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        getMenuInflater();
        Iterator it2 = ((CopyOnWriteArrayList) this.H.d).iterator();
        if (it2.hasNext()) {
            throw hk.e(it2);
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.I = currentMenuItems2;
    }

    public final boolean o(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.R);
        u();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked != 10 && actionMasked != 3) {
            return true;
        }
        this.D = false;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 818
            To view this dump change 'Code comments level' option to 'DEBUG'
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
        boolean zA = kt0.a(this);
        int i3 = !zA ? 1 : 0;
        int i4 = 0;
        if (t(this.e)) {
            s(this.e, i, 0, i2, this.p);
            iK = k(this.e) + this.e.getMeasuredWidth();
            iMax = Math.max(0, l(this.e) + this.e.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.e.getMeasuredState());
        } else {
            iK = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (t(this.i)) {
            s(this.i, i, 0, i2, this.p);
            iK = k(this.i) + this.i.getMeasuredWidth();
            iMax = Math.max(iMax, l(this.i) + this.i.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.i.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iK);
        int iMax4 = Math.max(0, currentContentInsetStart - iK);
        int[] iArr = this.G;
        iArr[zA ? 1 : 0] = iMax4;
        if (t(this.b)) {
            s(this.b, i, iMax3, i2, this.p);
            iK2 = k(this.b) + this.b.getMeasuredWidth();
            iMax = Math.max(iMax, l(this.b) + this.b.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.b.getMeasuredState());
        } else {
            iK2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, iK2);
        iArr[i3] = Math.max(0, currentContentInsetEnd - iK2);
        if (t(this.j)) {
            iMax5 += r(this.j, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, l(this.j) + this.j.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.j.getMeasuredState());
        }
        if (t(this.f)) {
            iMax5 += r(this.f, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, l(this.f) + this.f.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f.getMeasuredState());
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
        int i7 = this.s + this.t;
        int i8 = this.q + this.r;
        if (t(this.c)) {
            r(this.c, i, i6 + i8, i2, i7, iArr);
            int iK3 = k(this.c) + this.c.getMeasuredWidth();
            iL = l(this.c) + this.c.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.c.getMeasuredState());
            iMax2 = iK3;
        } else {
            iL = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (t(this.d)) {
            iMax2 = Math.max(iMax2, r(this.d, i, i6 + i8, i2, i7 + iL, iArr));
            iL += l(this.d) + this.d.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.d.getMeasuredState());
        }
        int iMax7 = Math.max(iMax, iL);
        int paddingRight = getPaddingRight() + getPaddingLeft() + i6 + iMax2;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax7;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16);
        if (!this.N) {
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
        super.onRestoreInstanceState(savedState.b);
        ActionMenuView actionMenuView = this.b;
        i60 i60Var = actionMenuView != null ? actionMenuView.q : null;
        int i = savedState.d;
        if (i != 0 && this.M != null && i60Var != null && (menuItemFindItem = i60Var.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.e) {
            v6 v6Var = this.R;
            removeCallbacks(v6Var);
            post(v6Var);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        d();
        ag0 ag0Var = this.u;
        boolean z = i == 1;
        if (z == ag0Var.g) {
            return;
        }
        ag0Var.g = z;
        if (!ag0Var.h) {
            ag0Var.a = ag0Var.e;
            ag0Var.b = ag0Var.f;
            return;
        }
        if (z) {
            int i2 = ag0Var.d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = ag0Var.e;
            }
            ag0Var.a = i2;
            int i3 = ag0Var.c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = ag0Var.f;
            }
            ag0Var.b = i3;
            return;
        }
        int i4 = ag0Var.c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = ag0Var.e;
        }
        ag0Var.a = i4;
        int i5 = ag0Var.d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = ag0Var.f;
        }
        ag0Var.b = i5;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        b bVar;
        m60 m60Var;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        j jVar = this.M;
        if (jVar != null && (m60Var = jVar.c) != null) {
            savedState.d = m60Var.a;
        }
        ActionMenuView actionMenuView = this.b;
        savedState.e = (actionMenuView == null || (bVar = actionMenuView.u) == null || !bVar.j()) ? false : true;
        return savedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        this.C = false;
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
        if (this.Q != z) {
            this.Q = z;
            u();
        }
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(rp0.y(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.N = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.w) {
            this.w = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.v) {
            this.v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(rp0.y(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(rp0.y(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.e.setOnClickListener(onClickListener);
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.b.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.l != i) {
            this.l = i;
            if (i == 0) {
                this.k = getContext();
            } else {
                this.k = new ContextThemeWrapper(getContext(), i);
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
        this.t = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.q = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.s = i;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u() {
        /*
            r4 = this;
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            if (r1 < r2) goto L4f
            android.window.OnBackInvokedDispatcher r1 = defpackage.qo0.a(r4)
            androidx.appcompat.widget.j r2 = r4.M
            if (r2 == 0) goto L23
            m60 r2 = r2.c
            if (r2 == 0) goto L23
            if (r1 == 0) goto L23
            java.util.WeakHashMap r2 = defpackage.xr0.a
            boolean r2 = r4.isAttachedToWindow()
            if (r2 == 0) goto L23
            boolean r2 = r4.Q
            if (r2 == 0) goto L23
            r2 = 1
            goto L24
        L23:
            r2 = r0
        L24:
            if (r2 == 0) goto L41
            android.window.OnBackInvokedDispatcher r3 = r4.P
            if (r3 != 0) goto L41
            android.window.OnBackInvokedCallback r2 = r4.O
            if (r2 != 0) goto L39
            oo0 r2 = new oo0
            r2.<init>(r4, r0)
            android.window.OnBackInvokedCallback r0 = defpackage.qo0.b(r2)
            r4.O = r0
        L39:
            android.window.OnBackInvokedCallback r0 = r4.O
            defpackage.qo0.c(r1, r0)
            r4.P = r1
            return
        L41:
            if (r2 != 0) goto L4f
            android.window.OnBackInvokedDispatcher r0 = r4.P
            if (r0 == 0) goto L4f
            android.window.OnBackInvokedCallback r1 = r4.O
            defpackage.qo0.d(r0, r1)
            r0 = 0
            r4.P = r0
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
        AppCompatImageButton appCompatImageButton = this.i;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.i.setImageDrawable(drawable);
        } else {
            AppCompatImageButton appCompatImageButton = this.i;
            if (appCompatImageButton != null) {
                appCompatImageButton.setImageDrawable(this.g);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f == null) {
                this.f = new AppCompatImageView(getContext());
            }
            if (!o(this.f)) {
                b(this.f, true);
            }
        } else {
            AppCompatImageView appCompatImageView = this.f;
            if (appCompatImageView != null && o(appCompatImageView)) {
                removeView(this.f);
                this.F.remove(this.f);
            }
        }
        AppCompatImageView appCompatImageView2 = this.f;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f == null) {
            this.f = new AppCompatImageView(getContext());
        }
        AppCompatImageView appCompatImageView = this.f;
        if (appCompatImageView != null) {
            appCompatImageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        AppCompatImageButton appCompatImageButton = this.e;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
            jq0.d0(this.e, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!o(this.e)) {
                b(this.e, true);
            }
        } else {
            AppCompatImageButton appCompatImageButton = this.e;
            if (appCompatImageButton != null && o(appCompatImageButton)) {
                removeView(this.e);
                this.F.remove(this.e);
            }
        }
        AppCompatImageButton appCompatImageButton2 = this.e;
        if (appCompatImageButton2 != null) {
            appCompatImageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.d;
            if (appCompatTextView != null && o(appCompatTextView)) {
                removeView(this.d);
                this.F.remove(this.d);
            }
        } else {
            if (this.d == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.d = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.d.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.n;
                if (i != 0) {
                    this.d.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.B;
                if (colorStateList != null) {
                    this.d.setTextColor(colorStateList);
                }
            }
            if (!o(this.d)) {
                b(this.d, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.d;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.B = colorStateList;
        AppCompatTextView appCompatTextView = this.d;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.c;
            if (appCompatTextView != null && o(appCompatTextView)) {
                removeView(this.c);
                this.F.remove(this.c);
            }
        } else {
            if (this.c == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.c = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.m;
                if (i != 0) {
                    this.c.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.c.setTextColor(colorStateList);
                }
            }
            if (!o(this.c)) {
                b(this.c, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.c;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        AppCompatTextView appCompatTextView = this.c;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.x = 8388627;
        this.E = new ArrayList();
        this.F = new ArrayList();
        this.G = new int[2];
        this.H = new e5(new oo0(this, 1));
        this.I = new ArrayList();
        this.J = new po0(this);
        this.R = new v6(17, this);
        po poVarN = po.N(getContext(), attributeSet, R$styleable.Toolbar, i);
        xr0.o(this, context, R$styleable.Toolbar, attributeSet, (TypedArray) poVarN.d, i);
        int i2 = R$styleable.Toolbar_titleTextAppearance;
        TypedArray typedArray = (TypedArray) poVarN.d;
        this.m = typedArray.getResourceId(i2, 0);
        this.n = typedArray.getResourceId(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.x = typedArray.getInteger(R$styleable.Toolbar_android_gravity, 8388627);
        this.o = typedArray.getInteger(R$styleable.Toolbar_buttonGravity, 48);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMargin, 0);
        dimensionPixelOffset = typedArray.hasValue(R$styleable.Toolbar_titleMargins) ? typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMargins, dimensionPixelOffset) : dimensionPixelOffset;
        this.t = dimensionPixelOffset;
        this.s = dimensionPixelOffset;
        this.r = dimensionPixelOffset;
        this.q = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginStart, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.q = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginEnd, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.r = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginTop, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.s = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginBottom, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.t = dimensionPixelOffset5;
        }
        this.p = typedArray.getDimensionPixelSize(R$styleable.Toolbar_maxButtonHeight, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.Toolbar_contentInsetLeft, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(R$styleable.Toolbar_contentInsetRight, 0);
        d();
        ag0 ag0Var = this.u;
        ag0Var.h = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            ag0Var.e = dimensionPixelSize;
            ag0Var.a = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            ag0Var.f = dimensionPixelSize2;
            ag0Var.b = dimensionPixelSize2;
        }
        if (dimensionPixelOffset6 != Integer.MIN_VALUE || dimensionPixelOffset7 != Integer.MIN_VALUE) {
            ag0Var.a(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.v = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.w = typedArray.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.g = poVarN.y(R$styleable.Toolbar_collapseIcon);
        this.h = typedArray.getText(R$styleable.Toolbar_collapseContentDescription);
        CharSequence text = typedArray.getText(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.k = getContext();
        setPopupTheme(typedArray.getResourceId(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawableY = poVarN.y(R$styleable.Toolbar_navigationIcon);
        if (drawableY != null) {
            setNavigationIcon(drawableY);
        }
        CharSequence text3 = typedArray.getText(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawableY2 = poVarN.y(R$styleable.Toolbar_logo);
        if (drawableY2 != null) {
            setLogo(drawableY2);
        }
        CharSequence text4 = typedArray.getText(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(R$styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(poVarN.w(R$styleable.Toolbar_titleTextColor));
        }
        if (typedArray.hasValue(R$styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(poVarN.w(R$styleable.Toolbar_subtitleTextColor));
        }
        if (typedArray.hasValue(R$styleable.Toolbar_menu)) {
            m(typedArray.getResourceId(R$styleable.Toolbar_menu, 0));
        }
        poVarN.P();
    }

    public void setOnMenuItemClickListener(ro0 ro0Var) {
    }
}
