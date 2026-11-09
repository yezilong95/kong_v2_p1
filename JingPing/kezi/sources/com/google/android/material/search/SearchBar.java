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
import defpackage.h9;
import defpackage.hl;
import defpackage.i60;
import defpackage.k9;
import defpackage.ki0;
import defpackage.lr0;
import defpackage.oy;
import defpackage.rp0;
import defpackage.u50;
import defpackage.un0;
import defpackage.wa;
import defpackage.xr0;
import defpackage.y50;
import defpackage.yo;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchBar extends Toolbar {
    public static final int k0 = R$style.Widget_Material3_SearchBar;
    public final TextView S;
    public final boolean T;
    public final boolean U;
    public final yo V;
    public final Drawable W;
    public final boolean a0;
    public final boolean b0;
    public View c0;
    public final Integer d0;
    public Drawable e0;
    public int f0;
    public boolean g0;
    public final u50 h0;
    public final AccessibilityManager i0;
    public final h9 j0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public String d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readString();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.d);
        }
    }

    public SearchBar(Context context) {
        this(context, null);
    }

    private void setNavigationIconDecorative(boolean z) {
        ImageButton imageButtonB = rp0.B(this);
        if (imageButtonB == null) {
            return;
        }
        imageButtonB.setClickable(!z);
        imageButtonB.setFocusable(!z);
        Drawable background = imageButtonB.getBackground();
        if (background != null) {
            this.e0 = background;
        }
        imageButtonB.setBackgroundDrawable(z ? null : this.e0);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.T && this.c0 == null && !(view instanceof ActionMenuView)) {
            this.c0 = view;
            view.setAlpha(0.0f);
        }
        super.addView(view, i, layoutParams);
    }

    public View getCenterView() {
        return this.c0;
    }

    public float getCompatElevation() {
        u50 u50Var = this.h0;
        if (u50Var != null) {
            return u50Var.b.m;
        }
        WeakHashMap weakHashMap = xr0.a;
        return lr0.i(this);
    }

    public float getCornerSize() {
        return this.h0.i();
    }

    public int getDefaultMarginVerticalResource() {
        return R$dimen.m3_searchbar_margin_vertical;
    }

    public int getDefaultNavigationIconResource() {
        return R$drawable.ic_search_black_24;
    }

    public CharSequence getHint() {
        return this.S.getHint();
    }

    public int getMenuResId() {
        return this.f0;
    }

    public int getStrokeColor() {
        return this.h0.b.d.getDefaultColor();
    }

    public float getStrokeWidth() {
        return this.h0.b.j;
    }

    public CharSequence getText() {
        return this.S.getText();
    }

    public TextView getTextView() {
        return this.S;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public final void m(int i) {
        Menu menu = getMenu();
        boolean z = menu instanceof i60;
        if (z) {
            ((i60) menu).y();
        }
        super.m(i);
        this.f0 = i;
        if (z) {
            ((i60) menu).x();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.O(this, this.h0);
        if (this.U && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
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
        v();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getCanonicalName());
        CharSequence text = getText();
        boolean zIsEmpty = TextUtils.isEmpty(text);
        if (Build.VERSION.SDK_INT >= 26) {
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
        View view = this.c0;
        if (view == null) {
            return;
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredWidth2 = (getMeasuredWidth() / 2) - (measuredWidth / 2);
        int i5 = measuredWidth + measuredWidth2;
        int measuredHeight = this.c0.getMeasuredHeight();
        int measuredHeight2 = (getMeasuredHeight() / 2) - (measuredHeight / 2);
        int i6 = measuredHeight + measuredHeight2;
        View view2 = this.c0;
        WeakHashMap weakHashMap = xr0.a;
        if (getLayoutDirection() == 1) {
            view2.layout(getMeasuredWidth() - i5, measuredHeight2, getMeasuredWidth() - measuredWidth2, i6);
        } else {
            view2.layout(measuredWidth2, measuredHeight2, i5, i6);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.c0;
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
        super.onRestoreInstanceState(savedState.b);
        setText(savedState.d);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        CharSequence text = getText();
        savedState.d = text == null ? null : text.toString();
        return savedState;
    }

    public void setCenterView(View view) {
        View view2 = this.c0;
        if (view2 != null) {
            removeView(view2);
            this.c0 = null;
        }
        if (view != null) {
            addView(view);
        }
    }

    public void setDefaultScrollFlagsEnabled(boolean z) {
        this.g0 = z;
        v();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        u50 u50Var = this.h0;
        if (u50Var != null) {
            u50Var.l(f);
        }
    }

    public void setHint(CharSequence charSequence) {
        this.S.setHint(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        int iQ;
        if (this.a0 && drawable != null) {
            Integer num = this.d0;
            if (num != null) {
                iQ = num.intValue();
            } else {
                iQ = k9.q(this, drawable == this.W ? R$attr.colorOnSurfaceVariant : R$attr.colorOnSurface);
            }
            drawable = drawable.mutate();
            hl.g(drawable, iQ);
        }
        super.setNavigationIcon(drawable);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        if (this.b0) {
            return;
        }
        super.setNavigationOnClickListener(onClickListener);
        setNavigationIconDecorative(onClickListener == null);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.V.getClass();
    }

    public void setStrokeColor(int i) {
        if (getStrokeColor() != i) {
            this.h0.r(ColorStateList.valueOf(i));
        }
    }

    public void setStrokeWidth(float f) {
        if (getStrokeWidth() != f) {
            this.h0.s(f);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    public void setText(CharSequence charSequence) {
        this.S.setText(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public final void v() {
        if (getLayoutParams() instanceof AppBarLayout.LayoutParams) {
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) getLayoutParams();
            if (this.g0) {
                if (layoutParams.a == 0) {
                    layoutParams.a = 53;
                }
            } else if (layoutParams.a == 53) {
                layoutParams.a = 0;
            }
        }
    }

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
        public boolean h;

        public ScrollingViewBehavior() {
            this.h = false;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            super.h(coordinatorLayout, view, view2);
            if (!this.h && (view2 instanceof AppBarLayout)) {
                this.h = true;
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                appBarLayout.setBackgroundColor(0);
                appBarLayout.setTargetElevation(0.0f);
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = false;
        }
    }

    public SearchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialSearchBarStyle);
    }

    public void setHint(int i) {
        this.S.setHint(i);
    }

    public void setText(int i) {
        this.S.setText(i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SearchBar(Context context, AttributeSet attributeSet, int i) {
        int i2 = k0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.f0 = -1;
        this.j0 = new h9(4, this);
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
        Drawable drawableY = rp0.y(context2, getDefaultNavigationIconResource());
        this.W = drawableY;
        yo yoVar = new yo(21);
        new LinkedHashSet();
        new LinkedHashSet();
        new LinkedHashSet();
        this.V = yoVar;
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.SearchBar, i, i2, new int[0]);
        ki0 ki0VarA = ki0.c(context2, attributeSet, i, i2).a();
        int color = typedArrayD.getColor(R$styleable.SearchBar_backgroundTint, 0);
        float dimension = typedArrayD.getDimension(R$styleable.SearchBar_elevation, 0.0f);
        this.U = typedArrayD.getBoolean(R$styleable.SearchBar_defaultMarginsEnabled, true);
        this.g0 = typedArrayD.getBoolean(R$styleable.SearchBar_defaultScrollFlagsEnabled, true);
        boolean z = typedArrayD.getBoolean(R$styleable.SearchBar_hideNavigationIcon, false);
        this.b0 = typedArrayD.getBoolean(R$styleable.SearchBar_forceDefaultNavigationOnClickListener, false);
        this.a0 = typedArrayD.getBoolean(R$styleable.SearchBar_tintNavigationIcon, true);
        if (typedArrayD.hasValue(R$styleable.SearchBar_navigationIconTint)) {
            this.d0 = Integer.valueOf(typedArrayD.getColor(R$styleable.SearchBar_navigationIconTint, -1));
        }
        int resourceId = typedArrayD.getResourceId(R$styleable.SearchBar_android_textAppearance, -1);
        String string = typedArrayD.getString(R$styleable.SearchBar_android_text);
        String string2 = typedArrayD.getString(R$styleable.SearchBar_android_hint);
        float dimension2 = typedArrayD.getDimension(R$styleable.SearchBar_strokeWidth, -1.0f);
        int color2 = typedArrayD.getColor(R$styleable.SearchBar_strokeColor, 0);
        typedArrayD.recycle();
        if (!z) {
            setNavigationIcon(getNavigationIcon() != null ? getNavigationIcon() : drawableY);
            setNavigationIconDecorative(true);
        }
        setClickable(true);
        setFocusable(true);
        LayoutInflater.from(context2).inflate(R$layout.mtrl_search_bar, this);
        this.T = true;
        TextView textView = (TextView) findViewById(R$id.open_search_bar_text_view);
        this.S = textView;
        WeakHashMap weakHashMap = xr0.a;
        lr0.s(this, dimension);
        if (resourceId != -1) {
            textView.setTextAppearance(resourceId);
        }
        setText(string);
        setHint(string2);
        if (getNavigationIcon() == null) {
            ((ViewGroup.MarginLayoutParams) textView.getLayoutParams()).setMarginStart(getResources().getDimensionPixelSize(R$dimen.m3_searchbar_text_margin_start_no_navigation_icon));
        }
        u50 u50Var = new u50(ki0VarA);
        this.h0 = u50Var;
        u50Var.k(getContext());
        this.h0.l(dimension);
        if (dimension2 >= 0.0f) {
            u50 u50Var2 = this.h0;
            u50Var2.s(dimension2);
            u50Var2.r(ColorStateList.valueOf(color2));
        }
        int iQ = k9.q(this, R$attr.colorControlHighlight);
        this.h0.m(ColorStateList.valueOf(color));
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(iQ);
        u50 u50Var3 = this.h0;
        setBackground(new RippleDrawable(colorStateListValueOf, u50Var3, u50Var3));
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.i0 = accessibilityManager;
        if (accessibilityManager != null) {
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                setFocusableInTouchMode(true);
            }
            addOnAttachStateChangeListener(new wa(3, this));
        }
    }
}
