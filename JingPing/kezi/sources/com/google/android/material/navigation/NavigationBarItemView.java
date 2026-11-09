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
import defpackage.a70;
import defpackage.ah;
import defpackage.ha;
import defpackage.hl;
import defpackage.j0;
import defpackage.jq0;
import defpackage.k80;
import defpackage.lf0;
import defpackage.m60;
import defpackage.m80;
import defpackage.n0;
import defpackage.nr0;
import defpackage.t3;
import defpackage.t7;
import defpackage.ub0;
import defpackage.xr0;
import defpackage.yo;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarItemView extends FrameLayout implements a70 {
    public static final int[] H = {R.attr.state_checked};
    public static final yo I = new yo(8);
    public static final m80 J = new m80(8);
    public float A;
    public boolean B;
    public int C;
    public int D;
    public boolean E;
    public int F;
    public t7 G;
    public boolean b;
    public ColorStateList c;
    public Drawable d;
    public int e;
    public int f;
    public int g;
    public float h;
    public float i;
    public float j;
    public int k;
    public boolean l;
    public final FrameLayout m;
    public final View n;
    public final ImageView o;
    public final ViewGroup p;
    public final TextView q;
    public final TextView r;
    public int s;
    public int t;
    public m60 u;
    public ColorStateList v;
    public Drawable w;
    public Drawable x;
    public ValueAnimator y;
    public yo z;

    public NavigationBarItemView(Context context) {
        super(context);
        this.b = false;
        this.s = -1;
        this.t = 0;
        this.z = I;
        this.A = 0.0f;
        this.B = false;
        this.C = 0;
        this.D = 0;
        this.E = false;
        this.F = 0;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.m = (FrameLayout) findViewById(R$id.navigation_bar_item_icon_container);
        this.n = findViewById(R$id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(R$id.navigation_bar_item_icon_view);
        this.o = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.navigation_bar_item_labels_group);
        this.p = viewGroup;
        TextView textView = (TextView) findViewById(R$id.navigation_bar_item_small_label_view);
        this.q = textView;
        TextView textView2 = (TextView) findViewById(R$id.navigation_bar_item_large_label_view);
        this.r = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.e = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.f = viewGroup.getPaddingBottom();
        this.g = getResources().getDimensionPixelSize(R$dimen.m3_navigation_item_active_indicator_label_padding);
        WeakHashMap weakHashMap = xr0.a;
        textView.setImportantForAccessibility(2);
        textView2.setImportantForAccessibility(2);
        setFocusable(true);
        a(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new k80(0, this));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
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
        FrameLayout frameLayout = this.m;
        return frameLayout != null ? frameLayout : this.o;
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
        t7 t7Var = this.G;
        int minimumWidth = t7Var == null ? 0 : t7Var.getMinimumWidth() - this.G.f.b.x.intValue();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.rightMargin) + this.o.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.leftMargin);
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
        this.h = f - f2;
        this.i = (f2 * 1.0f) / f;
        this.j = (f * 1.0f) / f2;
    }

    public final void b() {
        m60 m60Var = this.u;
        if (m60Var != null) {
            setChecked(m60Var.isChecked());
        }
    }

    public final void c() {
        Drawable rippleDrawable = this.d;
        ColorStateList colorStateList = this.c;
        FrameLayout frameLayout = this.m;
        RippleDrawable rippleDrawable2 = null;
        boolean z = true;
        if (colorStateList != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.B && getActiveIndicatorDrawable() != null && frameLayout != null && activeIndicatorDrawable != null) {
                rippleDrawable2 = new RippleDrawable(lf0.c(this.c), null, activeIndicatorDrawable);
                z = false;
            } else if (rippleDrawable == null) {
                rippleDrawable = new RippleDrawable(lf0.a(this.c), null, null);
            }
        }
        if (frameLayout != null) {
            frameLayout.setPadding(0, 0, 0, 0);
            frameLayout.setForeground(rippleDrawable2);
        }
        WeakHashMap weakHashMap = xr0.a;
        setBackground(rippleDrawable);
        if (Build.VERSION.SDK_INT >= 26) {
            setDefaultFocusHighlightEnabled(z);
        }
    }

    @Override // defpackage.a70
    public final void d(m60 m60Var) {
        this.u = m60Var;
        setCheckable(m60Var.isCheckable());
        setChecked(m60Var.isChecked());
        setEnabled(m60Var.isEnabled());
        setIcon(m60Var.getIcon());
        setTitle(m60Var.e);
        setId(m60Var.a);
        if (!TextUtils.isEmpty(m60Var.q)) {
            setContentDescription(m60Var.q);
        }
        jq0.d0(this, !TextUtils.isEmpty(m60Var.r) ? m60Var.r : m60Var.e);
        setVisibility(m60Var.isVisible() ? 0 : 8);
        this.b = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.m;
        if (frameLayout != null && this.B) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(float f, float f2) {
        View view = this.n;
        if (view != null) {
            yo yoVar = this.z;
            yoVar.getClass();
            view.setScaleX(t3.a(0.4f, 1.0f, f));
            view.setScaleY(yoVar.i(f, f2));
            view.setAlpha(t3.b(0.0f, 1.0f, f2 == 0.0f ? 0.8f : 0.0f, f2 == 0.0f ? 1.0f : 0.2f, f));
        }
        this.A = f;
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.n;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public t7 getBadge() {
        return this.G;
    }

    public int getItemBackgroundResId() {
        return R$drawable.mtrl_navigation_bar_item_background;
    }

    @Override // defpackage.a70
    public m60 getItemData() {
        return this.u;
    }

    public int getItemDefaultMarginResId() {
        return R$dimen.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.s;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        ViewGroup viewGroup = this.p;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return viewGroup.getMeasuredHeight() + getSuggestedIconHeight() + (viewGroup.getVisibility() == 0 ? this.g : 0) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        ViewGroup viewGroup = this.p;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), viewGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
    }

    public final void i(int i) {
        View view = this.n;
        if (view == null || i <= 0) {
            return;
        }
        int iMin = Math.min(this.C, i - (this.F * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = (this.E && this.k == 2) ? iMin : this.D;
        layoutParams.width = iMin;
        view.setLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        m60 m60Var = this.u;
        if (m60Var != null && m60Var.isCheckable() && this.u.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, H);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        t7 t7Var = this.G;
        if (t7Var != null && t7Var.isVisible()) {
            m60 m60Var = this.u;
            CharSequence charSequence = m60Var.e;
            if (!TextUtils.isEmpty(m60Var.q)) {
                charSequence = this.u.q;
            }
            accessibilityNodeInfo.setContentDescription(((Object) charSequence) + ", " + ((Object) this.G.c()));
        }
        accessibilityNodeInfo.setCollectionItemInfo(n0.a(0, 1, getItemVisiblePosition(), 1, false, isSelected()).a);
        if (isSelected()) {
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) j0.g.a);
        }
        accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(R$string.item_view_role_description));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new ha(this, i, 3));
    }

    public void setActiveIndicatorDrawable(Drawable drawable) {
        View view = this.n;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
        c();
    }

    public void setActiveIndicatorEnabled(boolean z) {
        this.B = z;
        c();
        View view = this.n;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i) {
        this.D = i;
        i(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int i) {
        if (this.g != i) {
            this.g = i;
            b();
        }
    }

    public void setActiveIndicatorMarginHorizontal(int i) {
        this.F = i;
        i(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z) {
        this.E = z;
    }

    public void setActiveIndicatorWidth(int i) {
        this.C = i;
        i(getWidth());
    }

    public void setBadge(t7 t7Var) {
        t7 t7Var2 = this.G;
        if (t7Var2 == t7Var) {
            return;
        }
        boolean z = t7Var2 != null;
        ImageView imageView = this.o;
        if (z && imageView != null) {
            Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
            if (this.G != null) {
                setClipChildren(true);
                setClipToPadding(true);
                t7 t7Var3 = this.G;
                if (t7Var3 != null) {
                    if (t7Var3.d() != null) {
                        t7Var3.d().setForeground(null);
                    } else {
                        imageView.getOverlay().remove(t7Var3);
                    }
                }
                this.G = null;
            }
        }
        this.G = t7Var;
        if (imageView == null || t7Var == null) {
            return;
        }
        setClipChildren(false);
        setClipToPadding(false);
        t7 t7Var4 = this.G;
        Rect rect = new Rect();
        imageView.getDrawingRect(rect);
        t7Var4.setBounds(rect);
        t7Var4.i(imageView, null);
        if (t7Var4.d() != null) {
            t7Var4.d().setForeground(t7Var4);
        } else {
            imageView.getOverlay().add(t7Var4);
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setChecked(boolean r13) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setChecked(boolean):void");
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.q.setEnabled(z);
        this.r.setEnabled(z);
        this.o.setEnabled(z);
        if (!z) {
            WeakHashMap weakHashMap = xr0.a;
            nr0.d(this, null);
        } else {
            PointerIcon pointerIconB = ub0.b(getContext(), CloseFrame.PROTOCOL_ERROR);
            WeakHashMap weakHashMap2 = xr0.a;
            nr0.d(this, pointerIconB);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.w) {
            return;
        }
        this.w = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = drawable.mutate();
            this.x = drawable;
            ColorStateList colorStateList = this.v;
            if (colorStateList != null) {
                hl.h(drawable, colorStateList);
            }
        }
        this.o.setImageDrawable(drawable);
    }

    public void setIconSize(int i) {
        ImageView imageView = this.o;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        imageView.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.v = colorStateList;
        if (this.u == null || (drawable = this.x) == null) {
            return;
        }
        hl.h(drawable, colorStateList);
        this.x.invalidateSelf();
    }

    public void setItemBackground(int i) {
        setItemBackground(i == 0 ? null : ah.b(getContext(), i));
    }

    public void setItemPaddingBottom(int i) {
        if (this.f != i) {
            this.f = i;
            b();
        }
    }

    public void setItemPaddingTop(int i) {
        if (this.e != i) {
            this.e = i;
            b();
        }
    }

    public void setItemPosition(int i) {
        this.s = i;
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.c = colorStateList;
        c();
    }

    public void setLabelVisibilityMode(int i) {
        if (this.k != i) {
            this.k = i;
            if (this.E && i == 2) {
                this.z = J;
            } else {
                this.z = I;
            }
            i(getWidth());
            b();
        }
    }

    public void setShifting(boolean z) {
        if (this.l != z) {
            this.l = z;
            b();
        }
    }

    public void setTextAppearanceActive(int i) {
        this.t = i;
        TextView textView = this.r;
        f(textView, i);
        a(this.q.getTextSize(), textView.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z) {
        setTextAppearanceActive(this.t);
        TextView textView = this.r;
        textView.setTypeface(textView.getTypeface(), z ? 1 : 0);
    }

    public void setTextAppearanceInactive(int i) {
        TextView textView = this.q;
        f(textView, i);
        a(textView.getTextSize(), this.r.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.q.setTextColor(colorStateList);
            this.r.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.q.setText(charSequence);
        this.r.setText(charSequence);
        m60 m60Var = this.u;
        if (m60Var == null || TextUtils.isEmpty(m60Var.q)) {
            setContentDescription(charSequence);
        }
        m60 m60Var2 = this.u;
        if (m60Var2 != null && !TextUtils.isEmpty(m60Var2.r)) {
            charSequence = this.u.r;
        }
        jq0.d0(this, charSequence);
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.d = drawable;
        c();
    }
}
