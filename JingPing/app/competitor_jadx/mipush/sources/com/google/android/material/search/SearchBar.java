package com.google.android.material.search;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.er0;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fb;
import defpackage.i01;
import defpackage.ip;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.pd0;
import defpackage.qm;
import defpackage.rc;
import defpackage.t40;
import defpackage.t41;
import defpackage.zd0;
import defpackage.zu0;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchBar extends Toolbar {
    public static final int N0 = R$style.Widget_Material3_SearchBar;
    public final boolean A0;
    public final er0 B0;
    public final Drawable C0;
    public final boolean D0;
    public final boolean E0;
    public View F0;
    public final Integer G0;
    public Drawable H0;
    public int I0;
    public boolean J0;
    public final ld0 K0;
    public final AccessibilityManager L0;
    public final fb M0;
    public final TextView y0;
    public final boolean z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public String G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readString();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.G);
        }
    }

    public SearchBar(Context context) {
        this(context, null);
    }

    private void setNavigationIconDecorative(boolean z) {
        ImageButton imageButtonH0 = t40.h0(this);
        if (imageButtonH0 == null) {
            return;
        }
        imageButtonH0.setClickable(!z);
        imageButtonH0.setFocusable(!z);
        Drawable background = imageButtonH0.getBackground();
        if (background != null) {
            this.H0 = background;
        }
        imageButtonH0.setBackgroundDrawable(z ? null : this.H0);
        v();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.z0 && this.F0 == null && !(view instanceof ActionMenuView)) {
            this.F0 = view;
            view.setAlpha(0.0f);
        }
        super.addView(view, i, layoutParams);
    }

    public View getCenterView() {
        return this.F0;
    }

    public float getCompatElevation() {
        ld0 ld0Var = this.K0;
        if (ld0Var != null) {
            return ld0Var.E.m;
        }
        WeakHashMap weakHashMap = f51.a;
        return t41.i(this);
    }

    public float getCornerSize() {
        return this.K0.i();
    }

    public int getDefaultMarginVerticalResource() {
        return R$dimen.m3_searchbar_margin_vertical;
    }

    public int getDefaultNavigationIconResource() {
        return R$drawable.ic_search_black_24;
    }

    public CharSequence getHint() {
        return this.y0.getHint();
    }

    public int getMenuResId() {
        return this.I0;
    }

    public int getStrokeColor() {
        return this.K0.E.d.getDefaultColor();
    }

    public float getStrokeWidth() {
        return this.K0.E.j;
    }

    public CharSequence getText() {
        return this.y0.getText();
    }

    public TextView getTextView() {
        return this.y0;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public final void m(int i) {
        Menu menu = getMenu();
        boolean z = menu instanceof zd0;
        if (z) {
            ((zd0) menu).y();
        }
        super.m(i);
        this.I0 = i;
        if (z) {
            ((zd0) menu).x();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.o0(this, this.K0);
        if (this.A0 && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.m3_searchbar_margin_horizontal);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(getDefaultMarginVerticalResource());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            int i = marginLayoutParams.leftMargin;
            if (i == 0) {
                i = dimensionPixelSize;
            }
            marginLayoutParams.leftMargin = i;
            int i2 = marginLayoutParams.topMargin;
            if (i2 == 0) {
                i2 = dimensionPixelSize2;
            }
            marginLayoutParams.topMargin = i2;
            int i3 = marginLayoutParams.rightMargin;
            if (i3 != 0) {
                dimensionPixelSize = i3;
            }
            marginLayoutParams.rightMargin = dimensionPixelSize;
            int i4 = marginLayoutParams.bottomMargin;
            if (i4 != 0) {
                dimensionPixelSize2 = i4;
            }
            marginLayoutParams.bottomMargin = dimensionPixelSize2;
        }
        w();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getCanonicalName());
        int i = Build.VERSION.SDK_INT;
        accessibilityNodeInfo.setEditable(isEnabled());
        CharSequence text = getText();
        boolean zIsEmpty = TextUtils.isEmpty(text);
        if (i >= 26) {
            accessibilityNodeInfo.setHintText(getHint());
            accessibilityNodeInfo.setShowingHintText(zIsEmpty);
        }
        if (zIsEmpty) {
            text = getHint();
        }
        accessibilityNodeInfo.setText(text);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View view = this.F0;
        if (view != null) {
            int measuredWidth = view.getMeasuredWidth();
            int measuredWidth2 = (getMeasuredWidth() / 2) - (measuredWidth / 2);
            int i5 = measuredWidth + measuredWidth2;
            int measuredHeight = this.F0.getMeasuredHeight();
            int measuredHeight2 = (getMeasuredHeight() / 2) - (measuredHeight / 2);
            int i6 = measuredHeight + measuredHeight2;
            View view2 = this.F0;
            WeakHashMap weakHashMap = f51.a;
            if (getLayoutDirection() == 1) {
                view2.layout(getMeasuredWidth() - i5, measuredHeight2, getMeasuredWidth() - measuredWidth2, i6);
            } else {
                view2.layout(measuredWidth2, measuredHeight2, i5, i6);
            }
        }
        v();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.F0;
        if (view != null) {
            view.measure(i, i2);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.E);
        setText(savedState.G);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        CharSequence text = getText();
        savedState.G = text == null ? null : text.toString();
        return savedState;
    }

    public void setCenterView(View view) {
        View view2 = this.F0;
        if (view2 != null) {
            removeView(view2);
            this.F0 = null;
        }
        if (view != null) {
            addView(view);
        }
    }

    public void setDefaultScrollFlagsEnabled(boolean z) {
        this.J0 = z;
        w();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ld0 ld0Var = this.K0;
        if (ld0Var != null) {
            ld0Var.l(f);
        }
    }

    public void setHint(CharSequence charSequence) {
        this.y0.setHint(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        int iP;
        if (this.D0 && drawable != null) {
            Integer num = this.G0;
            if (num != null) {
                iP = num.intValue();
            } else {
                iP = fa0.P(this, drawable == this.C0 ? R$attr.colorOnSurfaceVariant : R$attr.colorOnSurface);
            }
            drawable = drawable.mutate();
            ip.g(drawable, iP);
        }
        super.setNavigationIcon(drawable);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        if (this.E0) {
            return;
        }
        super.setNavigationOnClickListener(onClickListener);
        setNavigationIconDecorative(onClickListener == null);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.B0.getClass();
    }

    public void setStrokeColor(int i) {
        if (getStrokeColor() != i) {
            this.K0.r(ColorStateList.valueOf(i));
        }
    }

    public void setStrokeWidth(float f) {
        if (getStrokeWidth() != f) {
            this.K0.s(f);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    public void setText(CharSequence charSequence) {
        this.y0.setText(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public final void v() {
        if (Build.VERSION.SDK_INT < 34) {
            return;
        }
        boolean z = getLayoutDirection() == 1;
        ImageButton imageButtonH0 = t40.h0(this);
        int width = (imageButtonH0 == null || !imageButtonH0.isClickable()) ? 0 : z ? getWidth() - imageButtonH0.getLeft() : imageButtonH0.getRight();
        ActionMenuView actionMenuViewU = t40.U(this);
        int right = actionMenuViewU != null ? z ? actionMenuViewU.getRight() : getWidth() - actionMenuViewU.getLeft() : 0;
        float f = -(z ? right : width);
        if (!z) {
            width = right;
        }
        setHandwritingBoundsOffsets(f, 0.0f, -width, 0.0f);
    }

    public final void w() {
        if (getLayoutParams() instanceof AppBarLayout.LayoutParams) {
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) getLayoutParams();
            if (this.J0) {
                if (layoutParams.a == 0) {
                    layoutParams.a = 53;
                }
            } else if (layoutParams.a == 53) {
                layoutParams.a = 0;
            }
        }
    }

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
        public boolean K;

        public ScrollingViewBehavior() {
            this.K = false;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            super.h(coordinatorLayout, view, view2);
            if (!this.K && (view2 instanceof AppBarLayout)) {
                this.K = true;
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                appBarLayout.setBackgroundColor(0);
                appBarLayout.setTargetElevation(0.0f);
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.K = false;
        }
    }

    public SearchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialSearchBarStyle);
    }

    public void setHint(int i) {
        this.y0.setHint(i);
    }

    public void setText(int i) {
        this.y0.setText(i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SearchBar(Context context, AttributeSet attributeSet, int i) {
        int i2 = N0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.I0 = -1;
        this.M0 = new fb(6, this);
        Context context2 = getContext();
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "title") == null) {
                if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "subtitle") != null) {
                    throw new UnsupportedOperationException("SearchBar does not support subtitle. Use hint or text instead.");
                }
            } else {
                throw new UnsupportedOperationException("SearchBar does not support title. Use hint or text instead.");
            }
        }
        Drawable drawableZ = qm.z(context2, getDefaultNavigationIconResource());
        this.C0 = drawableZ;
        er0 er0Var = new er0();
        new LinkedHashSet();
        new LinkedHashSet();
        new LinkedHashSet();
        this.B0 = er0Var;
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.SearchBar, i, i2, new int[0]);
        zu0 zu0VarA = zu0.c(context2, attributeSet, i, i2).a();
        int color = typedArrayD.getColor(R$styleable.SearchBar_backgroundTint, 0);
        float dimension = typedArrayD.getDimension(R$styleable.SearchBar_elevation, 0.0f);
        this.A0 = typedArrayD.getBoolean(R$styleable.SearchBar_defaultMarginsEnabled, true);
        this.J0 = typedArrayD.getBoolean(R$styleable.SearchBar_defaultScrollFlagsEnabled, true);
        boolean z = typedArrayD.getBoolean(R$styleable.SearchBar_hideNavigationIcon, false);
        this.E0 = typedArrayD.getBoolean(R$styleable.SearchBar_forceDefaultNavigationOnClickListener, false);
        this.D0 = typedArrayD.getBoolean(R$styleable.SearchBar_tintNavigationIcon, true);
        if (typedArrayD.hasValue(R$styleable.SearchBar_navigationIconTint)) {
            this.G0 = Integer.valueOf(typedArrayD.getColor(R$styleable.SearchBar_navigationIconTint, -1));
        }
        int resourceId = typedArrayD.getResourceId(R$styleable.SearchBar_android_textAppearance, -1);
        String string = typedArrayD.getString(R$styleable.SearchBar_android_text);
        String string2 = typedArrayD.getString(R$styleable.SearchBar_android_hint);
        float dimension2 = typedArrayD.getDimension(R$styleable.SearchBar_strokeWidth, -1.0f);
        int color2 = typedArrayD.getColor(R$styleable.SearchBar_strokeColor, 0);
        typedArrayD.recycle();
        if (!z) {
            setNavigationIcon(getNavigationIcon() != null ? getNavigationIcon() : drawableZ);
            setNavigationIconDecorative(true);
        }
        setClickable(true);
        setFocusable(true);
        LayoutInflater.from(context2).inflate(R$layout.mtrl_search_bar, this);
        this.z0 = true;
        TextView textView = (TextView) findViewById(R$id.open_search_bar_text_view);
        this.y0 = textView;
        WeakHashMap weakHashMap = f51.a;
        t41.s(this, dimension);
        if (resourceId != -1) {
            textView.setTextAppearance(resourceId);
        }
        setText(string);
        setHint(string2);
        if (getNavigationIcon() == null) {
            ((ViewGroup.MarginLayoutParams) textView.getLayoutParams()).setMarginStart(getResources().getDimensionPixelSize(R$dimen.m3_searchbar_text_margin_start_no_navigation_icon));
        }
        ld0 ld0Var = new ld0(zu0VarA);
        this.K0 = ld0Var;
        ld0Var.k(getContext());
        this.K0.l(dimension);
        if (dimension2 >= 0.0f) {
            ld0 ld0Var2 = this.K0;
            ld0Var2.s(dimension2);
            ld0Var2.r(ColorStateList.valueOf(color2));
        }
        int iP = fa0.P(this, R$attr.colorControlHighlight);
        this.K0.m(ColorStateList.valueOf(color));
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(iP);
        ld0 ld0Var3 = this.K0;
        setBackground(new RippleDrawable(colorStateListValueOf, ld0Var3, ld0Var3));
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.L0 = accessibilityManager;
        if (accessibilityManager != null) {
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                setFocusableInTouchMode(true);
            }
            addOnAttachStateChangeListener(new rc(3, this));
        }
    }
}
