package com.google.android.material.navigation;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import defpackage.de0;
import defpackage.di;
import defpackage.e2;
import defpackage.f51;
import defpackage.gp0;
import defpackage.i2;
import defpackage.ip;
import defpackage.la0;
import defpackage.n9;
import defpackage.ng0;
import defpackage.pg0;
import defpackage.pi;
import defpackage.re0;
import defpackage.v41;
import defpackage.wb;
import defpackage.y4;
import defpackage.yk0;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarItemView extends FrameLayout implements re0 {
    public static final int[] n0 = {R.attr.state_checked};
    public static final di o0 = new di(22);
    public static final pg0 p0 = new pg0(22);
    public boolean E;
    public ColorStateList F;
    public Drawable G;
    public int H;
    public int I;
    public int J;
    public float K;
    public float L;
    public float M;
    public int N;
    public boolean O;
    public final FrameLayout P;
    public final View Q;
    public final ImageView R;
    public final ViewGroup S;
    public final TextView T;
    public final TextView U;
    public int V;
    public int W;
    public de0 a0;
    public ColorStateList b0;
    public Drawable c0;
    public Drawable d0;
    public ValueAnimator e0;
    public di f0;
    public float g0;
    public boolean h0;
    public int i0;
    public int j0;
    public boolean k0;
    public int l0;
    public n9 m0;

    public NavigationBarItemView(Context context) {
        super(context);
        this.E = false;
        this.V = -1;
        this.W = 0;
        this.f0 = o0;
        this.g0 = 0.0f;
        this.h0 = false;
        this.i0 = 0;
        this.j0 = 0;
        this.k0 = false;
        this.l0 = 0;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.P = (FrameLayout) findViewById(R$id.navigation_bar_item_icon_container);
        this.Q = findViewById(R$id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(R$id.navigation_bar_item_icon_view);
        this.R = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.navigation_bar_item_labels_group);
        this.S = viewGroup;
        TextView textView = (TextView) findViewById(R$id.navigation_bar_item_small_label_view);
        this.T = textView;
        TextView textView2 = (TextView) findViewById(R$id.navigation_bar_item_large_label_view);
        this.U = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.H = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.I = viewGroup.getPaddingBottom();
        this.J = getResources().getDimensionPixelSize(R$dimen.m3_navigation_item_active_indicator_label_padding);
        WeakHashMap weakHashMap = f51.a;
        textView.setImportantForAccessibility(2);
        textView2.setImportantForAccessibility(2);
        setFocusable(true);
        a(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new ng0(0, this));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(android.widget.TextView r4, int r5) {
        /*
            r4.setTextAppearance(r5)
            android.content.Context r0 = r4.getContext()
            r1 = 0
            if (r5 != 0) goto Lb
            goto L21
        Lb:
            int[] r2 = com.google.android.material.R$styleable.TextAppearance
            android.content.res.TypedArray r5 = r0.obtainStyledAttributes(r5, r2)
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            int r3 = com.google.android.material.R$styleable.TextAppearance_android_textSize
            boolean r3 = r5.getValue(r3, r2)
            r5.recycle()
            if (r3 != 0) goto L23
        L21:
            r5 = r1
            goto L4e
        L23:
            int r5 = r2.getComplexUnit()
            r3 = 2
            if (r5 != r3) goto L40
            int r5 = r2.data
            float r5 = android.util.TypedValue.complexToFloat(r5)
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            float r5 = r5 * r0
            int r5 = java.lang.Math.round(r5)
            goto L4e
        L40:
            int r5 = r2.data
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r5 = android.util.TypedValue.complexToDimensionPixelSize(r5, r0)
        L4e:
            if (r5 == 0) goto L54
            float r5 = (float) r5
            r4.setTextSize(r1, r5)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.f(android.widget.TextView, int):void");
    }

    public static void g(View view, float f, float f2, int i) {
        view.setScaleX(f);
        view.setScaleY(f2);
        view.setVisibility(i);
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.P;
        return frameLayout != null ? frameLayout : this.R;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int iIndexOfChild = viewGroup.indexOfChild(this);
        int i = 0;
        for (int i2 = 0; i2 < iIndexOfChild; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i++;
            }
        }
        return i;
    }

    private int getSuggestedIconHeight() {
        return getIconOrContainer().getMeasuredHeight() + ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin;
    }

    private int getSuggestedIconWidth() {
        n9 n9Var = this.m0;
        int minimumWidth = n9Var == null ? 0 : n9Var.getMinimumWidth() - this.m0.I.b.a0.intValue();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.rightMargin) + this.R.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.leftMargin);
    }

    public static void h(View view, int i, int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i;
        layoutParams.gravity = i2;
        view.setLayoutParams(layoutParams);
    }

    public static void j(View view, int i) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i);
    }

    public final void a(float f, float f2) {
        this.K = f - f2;
        this.L = (f2 * 1.0f) / f;
        this.M = (f * 1.0f) / f2;
    }

    public final void b() {
        de0 de0Var = this.a0;
        if (de0Var != null) {
            setChecked(de0Var.isChecked());
        }
    }

    public final void c() {
        Drawable rippleDrawable = this.G;
        ColorStateList colorStateList = this.F;
        FrameLayout frameLayout = this.P;
        RippleDrawable rippleDrawable2 = null;
        boolean z = true;
        if (colorStateList != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.h0 && getActiveIndicatorDrawable() != null && frameLayout != null && activeIndicatorDrawable != null) {
                rippleDrawable2 = new RippleDrawable(gp0.c(this.F), null, activeIndicatorDrawable);
                z = false;
            } else if (rippleDrawable == null) {
                rippleDrawable = new RippleDrawable(gp0.a(this.F), null, null);
            }
        }
        if (frameLayout != null) {
            frameLayout.setPadding(0, 0, 0, 0);
            frameLayout.setForeground(rippleDrawable2);
        }
        WeakHashMap weakHashMap = f51.a;
        setBackground(rippleDrawable);
        if (Build.VERSION.SDK_INT >= 26) {
            setDefaultFocusHighlightEnabled(z);
        }
    }

    @Override // defpackage.re0
    public final void d(de0 de0Var) {
        this.a0 = de0Var;
        setCheckable(de0Var.isCheckable());
        setChecked(de0Var.isChecked());
        setEnabled(de0Var.isEnabled());
        setIcon(de0Var.getIcon());
        setTitle(de0Var.e);
        setId(de0Var.a);
        if (!TextUtils.isEmpty(de0Var.q)) {
            setContentDescription(de0Var.q);
        }
        la0.e0(this, !TextUtils.isEmpty(de0Var.r) ? de0Var.r : de0Var.e);
        setVisibility(de0Var.isVisible() ? 0 : 8);
        this.E = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.P;
        if (frameLayout != null && this.h0) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(float f, float f2) {
        View view = this.Q;
        if (view != null) {
            di diVar = this.f0;
            diVar.getClass();
            view.setScaleX(y4.a(0.4f, 1.0f, f));
            view.setScaleY(diVar.d(f, f2));
            view.setAlpha(y4.b(0.0f, 1.0f, f2 == 0.0f ? 0.8f : 0.0f, f2 == 0.0f ? 1.0f : 0.2f, f));
        }
        this.g0 = f;
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.Q;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public n9 getBadge() {
        return this.m0;
    }

    public int getItemBackgroundResId() {
        return R$drawable.mtrl_navigation_bar_item_background;
    }

    @Override // defpackage.re0
    public de0 getItemData() {
        return this.a0;
    }

    public int getItemDefaultMarginResId() {
        return R$dimen.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.V;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        ViewGroup viewGroup = this.S;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return viewGroup.getMeasuredHeight() + getSuggestedIconHeight() + (viewGroup.getVisibility() == 0 ? this.J : 0) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        ViewGroup viewGroup = this.S;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), viewGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
    }

    public final void i(int i) {
        View view = this.Q;
        if (view == null || i <= 0) {
            return;
        }
        int iMin = Math.min(this.i0, i - (this.l0 * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = (this.k0 && this.N == 2) ? iMin : this.j0;
        layoutParams.width = iMin;
        view.setLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        de0 de0Var = this.a0;
        if (de0Var != null && de0Var.isCheckable() && this.a0.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, n0);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        n9 n9Var = this.m0;
        if (n9Var != null && n9Var.isVisible()) {
            de0 de0Var = this.a0;
            CharSequence charSequence = de0Var.e;
            if (!TextUtils.isEmpty(de0Var.q)) {
                charSequence = this.a0.q;
            }
            accessibilityNodeInfo.setContentDescription(((Object) charSequence) + ", " + ((Object) this.m0.c()));
        }
        accessibilityNodeInfo.setCollectionItemInfo(i2.a(0, 1, getItemVisiblePosition(), 1, false, isSelected()).a);
        if (isSelected()) {
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) e2.g.a);
        }
        accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(R$string.item_view_role_description));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new wb(this, i, 3));
    }

    public void setActiveIndicatorDrawable(Drawable drawable) {
        View view = this.Q;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
        c();
    }

    public void setActiveIndicatorEnabled(boolean z) {
        this.h0 = z;
        c();
        View view = this.Q;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i) {
        this.j0 = i;
        i(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int i) {
        if (this.J != i) {
            this.J = i;
            b();
        }
    }

    public void setActiveIndicatorMarginHorizontal(int i) {
        this.l0 = i;
        i(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z) {
        this.k0 = z;
    }

    public void setActiveIndicatorWidth(int i) {
        this.i0 = i;
        i(getWidth());
    }

    public void setBadge(n9 n9Var) {
        n9 n9Var2 = this.m0;
        if (n9Var2 == n9Var) {
            return;
        }
        boolean z = n9Var2 != null;
        ImageView imageView = this.R;
        if (z && imageView != null) {
            Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
            if (this.m0 != null) {
                setClipChildren(true);
                setClipToPadding(true);
                n9 n9Var3 = this.m0;
                if (n9Var3 != null) {
                    if (n9Var3.d() != null) {
                        n9Var3.d().setForeground(null);
                    } else {
                        imageView.getOverlay().remove(n9Var3);
                    }
                }
                this.m0 = null;
            }
        }
        this.m0 = n9Var;
        if (imageView == null || n9Var == null) {
            return;
        }
        setClipChildren(false);
        setClipToPadding(false);
        n9 n9Var4 = this.m0;
        Rect rect = new Rect();
        imageView.getDrawingRect(rect);
        n9Var4.setBounds(rect);
        n9Var4.i(imageView, null);
        if (n9Var4.d() != null) {
            n9Var4.d().setForeground(n9Var4);
        } else {
            imageView.getOverlay().add(n9Var4);
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setChecked(boolean r13) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setChecked(boolean):void");
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.T.setEnabled(z);
        this.U.setEnabled(z);
        this.R.setEnabled(z);
        if (!z) {
            WeakHashMap weakHashMap = f51.a;
            v41.d(this, null);
        } else {
            PointerIcon pointerIconB = yk0.b(getContext(), CloseFrame.PROTOCOL_ERROR);
            WeakHashMap weakHashMap2 = f51.a;
            v41.d(this, pointerIconB);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.c0) {
            return;
        }
        this.c0 = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = drawable.mutate();
            this.d0 = drawable;
            ColorStateList colorStateList = this.b0;
            if (colorStateList != null) {
                ip.h(drawable, colorStateList);
            }
        }
        this.R.setImageDrawable(drawable);
    }

    public void setIconSize(int i) {
        ImageView imageView = this.R;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        imageView.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.b0 = colorStateList;
        if (this.a0 == null || (drawable = this.d0) == null) {
            return;
        }
        ip.h(drawable, colorStateList);
        this.d0.invalidateSelf();
    }

    public void setItemBackground(int i) {
        setItemBackground(i == 0 ? null : pi.b(getContext(), i));
    }

    public void setItemPaddingBottom(int i) {
        if (this.I != i) {
            this.I = i;
            b();
        }
    }

    public void setItemPaddingTop(int i) {
        if (this.H != i) {
            this.H = i;
            b();
        }
    }

    public void setItemPosition(int i) {
        this.V = i;
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.F = colorStateList;
        c();
    }

    public void setLabelVisibilityMode(int i) {
        if (this.N != i) {
            this.N = i;
            if (this.k0 && i == 2) {
                this.f0 = p0;
            } else {
                this.f0 = o0;
            }
            i(getWidth());
            b();
        }
    }

    public void setShifting(boolean z) {
        if (this.O != z) {
            this.O = z;
            b();
        }
    }

    public void setTextAppearanceActive(int i) {
        this.W = i;
        TextView textView = this.U;
        f(textView, i);
        a(this.T.getTextSize(), textView.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z) {
        setTextAppearanceActive(this.W);
        TextView textView = this.U;
        textView.setTypeface(textView.getTypeface(), z ? 1 : 0);
    }

    public void setTextAppearanceInactive(int i) {
        TextView textView = this.T;
        f(textView, i);
        a(textView.getTextSize(), this.U.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.T.setTextColor(colorStateList);
            this.U.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.T.setText(charSequence);
        this.U.setText(charSequence);
        de0 de0Var = this.a0;
        if (de0Var == null || TextUtils.isEmpty(de0Var.q)) {
            setContentDescription(charSequence);
        }
        de0 de0Var2 = this.a0;
        if (de0Var2 != null && !TextUtils.isEmpty(de0Var2.r)) {
            charSequence = this.a0.r;
        }
        la0.e0(this, charSequence);
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.G = drawable;
        c();
    }
}
