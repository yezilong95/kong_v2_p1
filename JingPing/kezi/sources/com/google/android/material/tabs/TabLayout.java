package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.an;
import defpackage.d9;
import defpackage.eb0;
import defpackage.hl;
import defpackage.j0;
import defpackage.jq0;
import defpackage.k9;
import defpackage.km0;
import defpackage.lf0;
import defpackage.lm0;
import defpackage.lr0;
import defpackage.mm0;
import defpackage.n0;
import defpackage.nm0;
import defpackage.nr0;
import defpackage.ns0;
import defpackage.nv0;
import defpackage.o70;
import defpackage.om0;
import defpackage.oy;
import defpackage.pm0;
import defpackage.ps0;
import defpackage.rp0;
import defpackage.t3;
import defpackage.t7;
import defpackage.u50;
import defpackage.ub0;
import defpackage.un0;
import defpackage.vb0;
import defpackage.wb0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
@ns0
public class TabLayout extends HorizontalScrollView {
    public static final int V = R$style.Widget_Design_TabLayout;
    public static final wb0 W = new wb0(16);
    public int A;
    public final int B;
    public int C;
    public int D;
    public boolean E;
    public boolean F;
    public int G;
    public int H;
    public boolean I;
    public a J;
    public final TimeInterpolator K;
    public lm0 L;
    public final ArrayList M;
    public pm0 N;
    public ValueAnimator O;
    public ViewPager P;
    public om0 Q;
    public km0 R;
    public boolean S;
    public int T;
    public final vb0 U;
    public int b;
    public final ArrayList c;
    public b d;
    public final nm0 e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public ColorStateList m;
    public ColorStateList n;
    public ColorStateList o;
    public Drawable p;
    public int q;
    public final PorterDuff.Mode r;
    public final float s;
    public final float t;
    public final int u;
    public int v;
    public final int w;
    public final int x;
    public final int y;
    public final int z;

    public final class TabView extends LinearLayout {
        public static final /* synthetic */ int m = 0;
        public b b;
        public TextView c;
        public ImageView d;
        public View e;
        public t7 f;
        public View g;
        public TextView h;
        public ImageView i;
        public Drawable j;
        public int k;

        public TabView(Context context) {
            super(context);
            this.k = 2;
            e(context);
            int i = TabLayout.this.f;
            WeakHashMap weakHashMap = xr0.a;
            setPaddingRelative(i, TabLayout.this.g, TabLayout.this.h, TabLayout.this.i);
            setGravity(17);
            setOrientation(!TabLayout.this.E ? 1 : 0);
            setClickable(true);
            nr0.d(this, ub0.b(getContext(), CloseFrame.PROTOCOL_ERROR));
        }

        private t7 getBadge() {
            return this.f;
        }

        private t7 getOrCreateBadge() {
            if (this.f == null) {
                this.f = new t7(getContext(), null);
            }
            b();
            t7 t7Var = this.f;
            if (t7Var != null) {
                return t7Var;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void a() {
            if (this.f != null) {
                setClipChildren(true);
                setClipToPadding(true);
                ViewGroup viewGroup = (ViewGroup) getParent();
                if (viewGroup != null) {
                    viewGroup.setClipChildren(true);
                    viewGroup.setClipToPadding(true);
                }
                View view = this.e;
                if (view != null) {
                    t7 t7Var = this.f;
                    if (t7Var != null) {
                        if (t7Var.d() != null) {
                            t7Var.d().setForeground(null);
                        } else {
                            view.getOverlay().remove(t7Var);
                        }
                    }
                    this.e = null;
                }
            }
        }

        public final void b() {
            b bVar;
            if (this.f != null) {
                if (this.g != null) {
                    a();
                    return;
                }
                ImageView imageView = this.d;
                if (imageView != null && (bVar = this.b) != null && bVar.a != null) {
                    if (this.e == imageView) {
                        c(imageView);
                        return;
                    }
                    a();
                    ImageView imageView2 = this.d;
                    if (this.f == null || imageView2 == null) {
                        return;
                    }
                    setClipChildren(false);
                    setClipToPadding(false);
                    ViewGroup viewGroup = (ViewGroup) getParent();
                    if (viewGroup != null) {
                        viewGroup.setClipChildren(false);
                        viewGroup.setClipToPadding(false);
                    }
                    t7 t7Var = this.f;
                    Rect rect = new Rect();
                    imageView2.getDrawingRect(rect);
                    t7Var.setBounds(rect);
                    t7Var.i(imageView2, null);
                    if (t7Var.d() != null) {
                        t7Var.d().setForeground(t7Var);
                    } else {
                        imageView2.getOverlay().add(t7Var);
                    }
                    this.e = imageView2;
                    return;
                }
                TextView textView = this.c;
                if (textView == null || this.b == null) {
                    a();
                    return;
                }
                if (this.e == textView) {
                    c(textView);
                    return;
                }
                a();
                TextView textView2 = this.c;
                if (this.f == null || textView2 == null) {
                    return;
                }
                setClipChildren(false);
                setClipToPadding(false);
                ViewGroup viewGroup2 = (ViewGroup) getParent();
                if (viewGroup2 != null) {
                    viewGroup2.setClipChildren(false);
                    viewGroup2.setClipToPadding(false);
                }
                t7 t7Var2 = this.f;
                Rect rect2 = new Rect();
                textView2.getDrawingRect(rect2);
                t7Var2.setBounds(rect2);
                t7Var2.i(textView2, null);
                if (t7Var2.d() != null) {
                    t7Var2.d().setForeground(t7Var2);
                } else {
                    textView2.getOverlay().add(t7Var2);
                }
                this.e = textView2;
            }
        }

        public final void c(View view) {
            t7 t7Var = this.f;
            if (t7Var == null || view != this.e) {
                return;
            }
            Rect rect = new Rect();
            view.getDrawingRect(rect);
            t7Var.setBounds(rect);
            t7Var.i(view, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void d() {
            /*
                r3 = this;
                r3.f()
                com.google.android.material.tabs.b r0 = r3.b
                if (r0 == 0) goto L20
                com.google.android.material.tabs.TabLayout r1 = r0.f
                if (r1 == 0) goto L18
                int r1 = r1.getSelectedTabPosition()
                r2 = -1
                if (r1 == r2) goto L20
                int r0 = r0.d
                if (r1 != r0) goto L20
                r0 = 1
                goto L21
            L18:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "Tab not attached to a TabLayout"
                r0.<init>(r1)
                throw r0
            L20:
                r0 = 0
            L21:
                r3.setSelected(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.TabView.d():void");
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.j;
            if ((drawable == null || !drawable.isStateful()) ? false : this.j.setState(drawableState)) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public final void e(Context context) {
            TabLayout tabLayout = TabLayout.this;
            int i = tabLayout.u;
            if (i != 0) {
                Drawable drawableY = rp0.y(context, i);
                this.j = drawableY;
                if (drawableY != null && drawableY.isStateful()) {
                    this.j.setState(getDrawableState());
                }
            } else {
                this.j = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable rippleDrawable = gradientDrawable;
            if (tabLayout.o != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = lf0.a(tabLayout.o);
                boolean z = tabLayout.I;
                GradientDrawable gradientDrawable3 = gradientDrawable;
                if (z) {
                    gradientDrawable3 = null;
                }
                rippleDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z ? null : gradientDrawable2);
            }
            WeakHashMap weakHashMap = xr0.a;
            setBackground(rippleDrawable);
            tabLayout.invalidate();
        }

        public final void f() {
            int i;
            ViewParent parent;
            b bVar = this.b;
            View view = bVar != null ? bVar.e : null;
            if (view != null) {
                ViewParent parent2 = view.getParent();
                if (parent2 != this) {
                    if (parent2 != null) {
                        ((ViewGroup) parent2).removeView(view);
                    }
                    View view2 = this.g;
                    if (view2 != null && (parent = view2.getParent()) != null) {
                        ((ViewGroup) parent).removeView(this.g);
                    }
                    addView(view);
                }
                this.g = view;
                TextView textView = this.c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.d.setImageDrawable(null);
                }
                TextView textView2 = (TextView) view.findViewById(R.id.text1);
                this.h = textView2;
                if (textView2 != null) {
                    this.k = textView2.getMaxLines();
                }
                this.i = (ImageView) view.findViewById(R.id.icon);
            } else {
                View view3 = this.g;
                if (view3 != null) {
                    removeView(view3);
                    this.g = null;
                }
                this.h = null;
                this.i = null;
            }
            if (this.g == null) {
                if (this.d == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_icon, (ViewGroup) this, false);
                    this.d = imageView2;
                    addView(imageView2, 0);
                }
                if (this.c == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_text, (ViewGroup) this, false);
                    this.c = textView3;
                    addView(textView3);
                    this.k = this.c.getMaxLines();
                }
                TextView textView4 = this.c;
                TabLayout tabLayout = TabLayout.this;
                textView4.setTextAppearance(tabLayout.j);
                if (!isSelected() || (i = tabLayout.l) == -1) {
                    this.c.setTextAppearance(tabLayout.k);
                } else {
                    this.c.setTextAppearance(i);
                }
                ColorStateList colorStateList = tabLayout.m;
                if (colorStateList != null) {
                    this.c.setTextColor(colorStateList);
                }
                g(this.c, this.d, true);
                b();
                ImageView imageView3 = this.d;
                if (imageView3 != null) {
                    imageView3.addOnLayoutChangeListener(new c(this, imageView3));
                }
                TextView textView5 = this.c;
                if (textView5 != null) {
                    textView5.addOnLayoutChangeListener(new c(this, textView5));
                }
            } else {
                TextView textView6 = this.h;
                if (textView6 != null || this.i != null) {
                    g(textView6, this.i, false);
                }
            }
            if (bVar == null || TextUtils.isEmpty(bVar.c)) {
                return;
            }
            setContentDescription(bVar.c);
        }

        public final void g(TextView textView, ImageView imageView, boolean z) {
            boolean z2;
            Drawable drawable;
            b bVar = this.b;
            Drawable drawableMutate = (bVar == null || (drawable = bVar.a) == null) ? null : drawable.mutate();
            TabLayout tabLayout = TabLayout.this;
            if (drawableMutate != null) {
                hl.h(drawableMutate, tabLayout.n);
                PorterDuff.Mode mode = tabLayout.r;
                if (mode != null) {
                    hl.i(drawableMutate, mode);
                }
            }
            b bVar2 = this.b;
            CharSequence charSequence = bVar2 != null ? bVar2.b : null;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean zIsEmpty = TextUtils.isEmpty(charSequence);
            if (textView != null) {
                if (zIsEmpty) {
                    z2 = false;
                } else {
                    this.b.getClass();
                    z2 = true;
                }
                textView.setText(!zIsEmpty ? charSequence : null);
                textView.setVisibility(z2 ? 0 : 8);
                if (!zIsEmpty) {
                    setVisibility(0);
                }
            } else {
                z2 = false;
            }
            if (z && imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iT = (z2 && imageView.getVisibility() == 0) ? (int) rp0.t(getContext(), 8) : 0;
                if (tabLayout.E) {
                    if (iT != marginLayoutParams.getMarginEnd()) {
                        marginLayoutParams.setMarginEnd(iT);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iT != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iT;
                    marginLayoutParams.setMarginEnd(0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            b bVar3 = this.b;
            CharSequence charSequence2 = bVar3 != null ? bVar3.c : null;
            if (zIsEmpty) {
                charSequence = charSequence2;
            }
            jq0.d0(this, charSequence);
        }

        public int getContentHeight() {
            View[] viewArr = {this.c, this.d, this.g};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getTop()) : view.getTop();
                    iMax = z ? Math.max(iMax, view.getBottom()) : view.getBottom();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        public int getContentWidth() {
            View[] viewArr = {this.c, this.d, this.g};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z ? Math.max(iMax, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        public b getTab() {
            return this.b;
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            t7 t7Var = this.f;
            if (t7Var != null && t7Var.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f.c()));
            }
            accessibilityNodeInfo.setCollectionItemInfo(n0.a(0, 1, this.b.d, 1, false, isSelected()).a);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) j0.g.a);
            }
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(R$string.item_view_role_description));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public final void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            TabLayout tabLayout = TabLayout.this;
            int tabMaxWidth = tabLayout.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(tabLayout.v, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.c != null) {
                float f = tabLayout.s;
                int i3 = this.k;
                ImageView imageView = this.d;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = tabLayout.t;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.c.getTextSize();
                int lineCount = this.c.getLineCount();
                int maxLines = this.c.getMaxLines();
                if (f != textSize || (maxLines >= 0 && i3 != maxLines)) {
                    if (tabLayout.D == 1 && f > textSize && lineCount == 1) {
                        Layout layout = this.c.getLayout();
                        if (layout == null) {
                            return;
                        }
                        if ((f / layout.getPaint().getTextSize()) * layout.getLineWidth(0) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) {
                            return;
                        }
                    }
                    this.c.setTextSize(0, f);
                    this.c.setMaxLines(i3);
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.view.View
        public final boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.b == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            b bVar = this.b;
            TabLayout tabLayout = bVar.f;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.g(bVar, true);
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            isSelected();
            super.setSelected(z);
            TextView textView = this.c;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.d;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.g;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public void setTab(b bVar) {
            if (bVar != this.b) {
                this.b = bVar;
                d();
            }
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private int getDefaultHeight() {
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) arrayList.get(i);
            if (bVar != null && bVar.a != null && !TextUtils.isEmpty(bVar.b)) {
                return !this.E ? 72 : 48;
            }
        }
        return 48;
    }

    private int getTabMinWidth() {
        int i = this.w;
        if (i != -1) {
            return i;
        }
        int i2 = this.D;
        if (i2 == 0 || i2 == 2) {
            return this.y;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.e.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setSelectedTabView(int i) {
        nm0 nm0Var = this.e;
        int childCount = nm0Var.getChildCount();
        if (i < childCount) {
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = nm0Var.getChildAt(i2);
                if ((i2 != i || childAt.isSelected()) && (i2 == i || !childAt.isSelected())) {
                    childAt.setSelected(i2 == i);
                    childAt.setActivated(i2 == i);
                } else {
                    childAt.setSelected(i2 == i);
                    childAt.setActivated(i2 == i);
                    if (childAt instanceof TabView) {
                        ((TabView) childAt).f();
                    }
                }
                i2++;
            }
        }
    }

    public final void a(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        TabItem tabItem = (TabItem) view;
        b bVar = (b) W.a();
        if (bVar == null) {
            bVar = new b();
            bVar.d = -1;
        }
        bVar.f = this;
        vb0 vb0Var = this.U;
        TabView tabView = vb0Var != null ? (TabView) vb0Var.a() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        tabView.setTab(bVar);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(bVar.c)) {
            tabView.setContentDescription(bVar.b);
        } else {
            tabView.setContentDescription(bVar.c);
        }
        bVar.g = tabView;
        CharSequence charSequence = tabItem.b;
        if (charSequence != null) {
            if (TextUtils.isEmpty(bVar.c) && !TextUtils.isEmpty(charSequence)) {
                bVar.g.setContentDescription(charSequence);
            }
            bVar.b = charSequence;
            TabView tabView2 = bVar.g;
            if (tabView2 != null) {
                tabView2.d();
            }
        }
        Drawable drawable = tabItem.c;
        if (drawable != null) {
            bVar.a = drawable;
            TabLayout tabLayout = bVar.f;
            if (tabLayout.A == 1 || tabLayout.D == 2) {
                tabLayout.j(true);
            }
            TabView tabView3 = bVar.g;
            if (tabView3 != null) {
                tabView3.d();
            }
        }
        int i = tabItem.d;
        if (i != 0) {
            bVar.e = LayoutInflater.from(bVar.g.getContext()).inflate(i, (ViewGroup) bVar.g, false);
            TabView tabView4 = bVar.g;
            if (tabView4 != null) {
                tabView4.d();
            }
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            bVar.c = tabItem.getContentDescription();
            TabView tabView5 = bVar.g;
            if (tabView5 != null) {
                tabView5.d();
            }
        }
        ArrayList arrayList = this.c;
        boolean zIsEmpty = arrayList.isEmpty();
        int size = arrayList.size();
        if (bVar.f != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        bVar.d = size;
        arrayList.add(size, bVar);
        int size2 = arrayList.size();
        int i2 = -1;
        for (int i3 = size + 1; i3 < size2; i3++) {
            if (((b) arrayList.get(i3)).d == this.b) {
                i2 = i3;
            }
            ((b) arrayList.get(i3)).d = i3;
        }
        this.b = i2;
        TabView tabView6 = bVar.g;
        tabView6.setSelected(false);
        tabView6.setActivated(false);
        int i4 = bVar.d;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        if (this.D == 1 && this.A == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
        this.e.addView(tabView6, i4, layoutParams);
        if (zIsEmpty) {
            TabLayout tabLayout2 = bVar.f;
            if (tabLayout2 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout2.g(bVar, true);
        }
    }

    public void addOnTabSelectedListener(mm0 mm0Var) {
        addOnTabSelectedListener((lm0) mm0Var);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view) {
        a(view);
    }

    public final void b(int i) {
        if (i == -1) {
            return;
        }
        if (getWindowToken() != null) {
            WeakHashMap weakHashMap = xr0.a;
            if (isLaidOut()) {
                nm0 nm0Var = this.e;
                int childCount = nm0Var.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    if (nm0Var.getChildAt(i2).getWidth() > 0) {
                    }
                }
                int scrollX = getScrollX();
                int iD = d(i, 0.0f);
                if (scrollX != iD) {
                    e();
                    this.O.setIntValues(scrollX, iD);
                    this.O.start();
                }
                ValueAnimator valueAnimator = nm0Var.b;
                if (valueAnimator != null && valueAnimator.isRunning() && nm0Var.c.b != i) {
                    nm0Var.b.cancel();
                }
                nm0Var.d(i, this.B, true);
                return;
            }
        }
        h(i, 0.0f, true, true, true);
    }

    public final void c() {
        int i = this.D;
        int iMax = (i == 0 || i == 2) ? Math.max(0, this.z - this.f) : 0;
        WeakHashMap weakHashMap = xr0.a;
        nm0 nm0Var = this.e;
        nm0Var.setPaddingRelative(iMax, 0, 0, 0);
        int i2 = this.D;
        if (i2 == 0) {
            int i3 = this.A;
            if (i3 == 0) {
                Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
            } else if (i3 == 1) {
                nm0Var.setGravity(1);
            } else if (i3 == 2) {
            }
            nm0Var.setGravity(8388611);
        } else if (i2 == 1 || i2 == 2) {
            if (this.A == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            nm0Var.setGravity(1);
        }
        j(true);
    }

    public final int d(int i, float f) {
        nm0 nm0Var;
        View childAt;
        int i2 = this.D;
        if ((i2 != 0 && i2 != 2) || (childAt = (nm0Var = this.e).getChildAt(i)) == null) {
            return 0;
        }
        int i3 = i + 1;
        View childAt2 = i3 < nm0Var.getChildCount() ? nm0Var.getChildAt(i3) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i4 = (int) ((width + width2) * 0.5f * f);
        WeakHashMap weakHashMap = xr0.a;
        return getLayoutDirection() == 0 ? left + i4 : left - i4;
    }

    public final void e() {
        if (this.O == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.O = valueAnimator;
            valueAnimator.setInterpolator(this.K);
            this.O.setDuration(this.B);
            this.O.addUpdateListener(new d9(4, this));
        }
    }

    public final void f() {
        nm0 nm0Var = this.e;
        int childCount = nm0Var.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            TabView tabView = (TabView) nm0Var.getChildAt(childCount);
            nm0Var.removeViewAt(childCount);
            if (tabView != null) {
                tabView.setTab(null);
                tabView.setSelected(false);
                this.U.c(tabView);
            }
            requestLayout();
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            it.remove();
            bVar.f = null;
            bVar.g = null;
            bVar.a = null;
            bVar.b = null;
            bVar.c = null;
            bVar.d = -1;
            bVar.e = null;
            W.c(bVar);
        }
        this.d = null;
    }

    public final void g(b bVar, boolean z) {
        TabLayout tabLayout;
        b bVar2 = this.d;
        ArrayList arrayList = this.M;
        if (bVar2 == bVar) {
            if (bVar2 != null) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((lm0) arrayList.get(size)).getClass();
                }
                b(bVar.d);
                return;
            }
            return;
        }
        int i = bVar != null ? bVar.d : -1;
        if (z) {
            if ((bVar2 == null || bVar2.d == -1) && i != -1) {
                tabLayout = this;
                tabLayout.h(i, 0.0f, true, true, true);
            } else {
                tabLayout = this;
                b(i);
            }
            if (i != -1) {
                setSelectedTabView(i);
            }
        } else {
            tabLayout = this;
        }
        tabLayout.d = bVar;
        if (bVar2 != null && bVar2.f != null) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                ((lm0) arrayList.get(size2)).getClass();
            }
        }
        if (bVar != null) {
            for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                pm0 pm0Var = (pm0) ((lm0) arrayList.get(size3));
                pm0Var.getClass();
                pm0Var.a.setCurrentItem(bVar.d);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition() {
        b bVar = this.d;
        if (bVar != null) {
            return bVar.d;
        }
        return -1;
    }

    public int getTabCount() {
        return this.c.size();
    }

    public int getTabGravity() {
        return this.A;
    }

    public ColorStateList getTabIconTint() {
        return this.n;
    }

    public int getTabIndicatorAnimationMode() {
        return this.H;
    }

    public int getTabIndicatorGravity() {
        return this.C;
    }

    public int getTabMaxWidth() {
        return this.v;
    }

    public int getTabMode() {
        return this.D;
    }

    public ColorStateList getTabRippleColor() {
        return this.o;
    }

    public Drawable getTabSelectedIndicator() {
        return this.p;
    }

    public ColorStateList getTabTextColors() {
        return this.m;
    }

    public final void h(int i, float f, boolean z, boolean z2, boolean z3) {
        float f2 = i + f;
        int iRound = Math.round(f2);
        if (iRound >= 0) {
            nm0 nm0Var = this.e;
            if (iRound >= nm0Var.getChildCount()) {
                return;
            }
            if (z2) {
                nm0Var.c.b = Math.round(f2);
                ValueAnimator valueAnimator = nm0Var.b;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    nm0Var.b.cancel();
                }
                nm0Var.c(nm0Var.getChildAt(i), nm0Var.getChildAt(i + 1), f);
            }
            ValueAnimator valueAnimator2 = this.O;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.O.cancel();
            }
            int iD = d(i, f);
            int scrollX = getScrollX();
            boolean z4 = (i < getSelectedTabPosition() && iD >= scrollX) || (i > getSelectedTabPosition() && iD <= scrollX) || i == getSelectedTabPosition();
            WeakHashMap weakHashMap = xr0.a;
            if (getLayoutDirection() == 1) {
                z4 = (i < getSelectedTabPosition() && iD <= scrollX) || (i > getSelectedTabPosition() && iD >= scrollX) || i == getSelectedTabPosition();
            }
            if (z4 || this.T == 1 || z3) {
                if (i < 0) {
                    iD = 0;
                }
                scrollTo(iD, 0);
            }
            if (z) {
                setSelectedTabView(iRound);
            }
        }
    }

    public final void i(ViewPager viewPager, boolean z) {
        TabLayout tabLayout;
        ViewPager viewPager2 = this.P;
        if (viewPager2 != null) {
            om0 om0Var = this.Q;
            if (om0Var != null) {
                viewPager2.removeOnPageChangeListener(om0Var);
            }
            km0 km0Var = this.R;
            if (km0Var != null) {
                this.P.removeOnAdapterChangeListener(km0Var);
            }
        }
        pm0 pm0Var = this.N;
        if (pm0Var != null) {
            removeOnTabSelectedListener((lm0) pm0Var);
            this.N = null;
        }
        if (viewPager != null) {
            this.P = viewPager;
            if (this.Q == null) {
                this.Q = new om0(this);
            }
            om0 om0Var2 = this.Q;
            om0Var2.c = 0;
            om0Var2.b = 0;
            viewPager.addOnPageChangeListener(om0Var2);
            pm0 pm0Var2 = new pm0(viewPager);
            this.N = pm0Var2;
            addOnTabSelectedListener((lm0) pm0Var2);
            viewPager.getAdapter();
            if (this.R == null) {
                this.R = new km0(this);
            }
            ps0 ps0Var = this.R;
            ps0Var.getClass();
            viewPager.addOnAdapterChangeListener(ps0Var);
            h(viewPager.getCurrentItem(), 0.0f, true, true, true);
            tabLayout = this;
        } else {
            tabLayout = this;
            tabLayout.P = null;
            f();
        }
        tabLayout.S = z;
    }

    public final void j(boolean z) {
        int i = 0;
        while (true) {
            nm0 nm0Var = this.e;
            if (i >= nm0Var.getChildCount()) {
                return;
            }
            View childAt = nm0Var.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (this.D == 1 && this.A == 0) {
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            } else {
                layoutParams.width = -2;
                layoutParams.weight = 0.0f;
            }
            if (z) {
                childAt.requestLayout();
            }
            i++;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.P(this);
        if (this.P == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                i((ViewPager) parent, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.S) {
            setupWithViewPager(null);
            this.S = false;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        TabView tabView;
        Drawable drawable;
        int i = 0;
        while (true) {
            nm0 nm0Var = this.e;
            if (i >= nm0Var.getChildCount()) {
                super.onDraw(canvas);
                return;
            }
            View childAt = nm0Var.getChildAt(i);
            if ((childAt instanceof TabView) && (drawable = (tabView = (TabView) childAt).j) != null) {
                drawable.setBounds(tabView.getLeft(), tabView.getTop(), tabView.getRight(), tabView.getBottom());
                tabView.j.draw(canvas);
            }
            i++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(1, getTabCount(), 1).c);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (getTabMode() == 0 || getTabMode() == 2) && super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = defpackage.rp0.t(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 0
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = 1
            if (r1 == r2) goto L2e
            if (r1 == 0) goto L1f
            goto L41
        L1f:
            int r8 = r6.getPaddingTop()
            int r8 = r8 + r0
            int r0 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r4)
            goto L41
        L2e:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L41
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L41
            android.view.View r1 = r6.getChildAt(r3)
            r1.setMinimumHeight(r0)
        L41:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L5f
            int r1 = r6.x
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = defpackage.rp0.t(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.v = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Laa
            android.view.View r7 = r6.getChildAt(r3)
            int r0 = r6.D
            if (r0 == 0) goto L82
            if (r0 == r5) goto L76
            r1 = 2
            if (r0 == r1) goto L82
            goto Laa
        L76:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L81
            goto L8c
        L81:
            return
        L82:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto Laa
        L8c:
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r1 = r1 + r0
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            int r0 = r0.height
            int r8 = android.view.ViewGroup.getChildMeasureSpec(r8, r1, r0)
            int r0 = r6.getMeasuredWidth()
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r4)
            r7.measure(r0, r8)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || getTabMode() == 0 || getTabMode() == 2) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void removeOnTabSelectedListener(mm0 mm0Var) {
        removeOnTabSelectedListener((lm0) mm0Var);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oy.N(this, f);
    }

    public void setInlineLabel(boolean z) {
        if (this.E == z) {
            return;
        }
        this.E = z;
        int i = 0;
        while (true) {
            nm0 nm0Var = this.e;
            if (i >= nm0Var.getChildCount()) {
                c();
                return;
            }
            View childAt = nm0Var.getChildAt(i);
            if (childAt instanceof TabView) {
                TabView tabView = (TabView) childAt;
                tabView.setOrientation(!TabLayout.this.E ? 1 : 0);
                TextView textView = tabView.h;
                if (textView == null && tabView.i == null) {
                    tabView.g(tabView.c, tabView.d, true);
                } else {
                    tabView.g(textView, tabView.i, false);
                }
            }
            i++;
        }
    }

    public void setInlineLabelResource(int i) {
        setInlineLabel(getResources().getBoolean(i));
    }

    @Deprecated
    public void setOnTabSelectedListener(mm0 mm0Var) {
        setOnTabSelectedListener((lm0) mm0Var);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        e();
        this.O.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable drawableMutate = drawable.mutate();
        this.p = drawableMutate;
        int i = this.q;
        if (i != 0) {
            hl.g(drawableMutate, i);
        } else {
            hl.h(drawableMutate, null);
        }
        int intrinsicHeight = this.G;
        if (intrinsicHeight == -1) {
            intrinsicHeight = this.p.getIntrinsicHeight();
        }
        this.e.b(intrinsicHeight);
    }

    public void setSelectedTabIndicatorColor(int i) {
        this.q = i;
        Drawable drawable = this.p;
        if (i != 0) {
            hl.g(drawable, i);
        } else {
            hl.h(drawable, null);
        }
        j(false);
    }

    public void setSelectedTabIndicatorGravity(int i) {
        if (this.C != i) {
            this.C = i;
            WeakHashMap weakHashMap = xr0.a;
            this.e.postInvalidateOnAnimation();
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i) {
        this.G = i;
        this.e.b(i);
    }

    public void setTabGravity(int i) {
        if (this.A != i) {
            this.A = i;
            c();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            ArrayList arrayList = this.c;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                TabView tabView = ((b) arrayList.get(i)).g;
                if (tabView != null) {
                    tabView.d();
                }
            }
        }
    }

    public void setTabIconTintResource(int i) {
        setTabIconTint(rp0.w(getContext(), i));
    }

    public void setTabIndicatorAnimationMode(int i) {
        this.H = i;
        if (i == 0) {
            this.J = new a();
            return;
        }
        if (i == 1) {
            this.J = new an(0);
        } else {
            if (i == 2) {
                this.J = new an(1);
                return;
            }
            throw new IllegalArgumentException(i + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.F = z;
        int i = nm0.d;
        nm0 nm0Var = this.e;
        nm0Var.a(nm0Var.c.getSelectedTabPosition());
        WeakHashMap weakHashMap = xr0.a;
        nm0Var.postInvalidateOnAnimation();
    }

    public void setTabMode(int i) {
        if (i != this.D) {
            this.D = i;
            c();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.o == colorStateList) {
            return;
        }
        this.o = colorStateList;
        int i = 0;
        while (true) {
            nm0 nm0Var = this.e;
            if (i >= nm0Var.getChildCount()) {
                return;
            }
            View childAt = nm0Var.getChildAt(i);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i2 = TabView.m;
                ((TabView) childAt).e(context);
            }
            i++;
        }
    }

    public void setTabRippleColorResource(int i) {
        setTabRippleColor(rp0.w(getContext(), i));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            ArrayList arrayList = this.c;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                TabView tabView = ((b) arrayList.get(i)).g;
                if (tabView != null) {
                    tabView.d();
                }
            }
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(eb0 eb0Var) {
        f();
    }

    public void setUnboundedRipple(boolean z) {
        if (this.I == z) {
            return;
        }
        this.I = z;
        int i = 0;
        while (true) {
            nm0 nm0Var = this.e;
            if (i >= nm0Var.getChildCount()) {
                return;
            }
            View childAt = nm0Var.getChildAt(i);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i2 = TabView.m;
                ((TabView) childAt).e(context);
            }
            i++;
        }
    }

    public void setUnboundedRippleResource(int i) {
        setUnboundedRipple(getResources().getBoolean(i));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        i(viewPager, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.tabStyle);
    }

    @Deprecated
    public void addOnTabSelectedListener(lm0 lm0Var) {
        ArrayList arrayList = this.M;
        if (arrayList.contains(lm0Var)) {
            return;
        }
        arrayList.add(lm0Var);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view, int i) {
        a(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void removeOnTabSelectedListener(lm0 lm0Var) {
        this.M.remove(lm0Var);
    }

    @Deprecated
    public void setOnTabSelectedListener(lm0 lm0Var) {
        lm0 lm0Var2 = this.L;
        if (lm0Var2 != null) {
            removeOnTabSelectedListener(lm0Var2);
        }
        this.L = lm0Var;
        if (lm0Var != null) {
            addOnTabSelectedListener(lm0Var);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = V;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.b = -1;
        this.c = new ArrayList();
        this.l = -1;
        this.q = 0;
        this.v = Integer.MAX_VALUE;
        this.G = -1;
        this.M = new ArrayList();
        this.U = new vb0(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        nm0 nm0Var = new nm0(this, context2);
        this.e = nm0Var;
        super.addView(nm0Var, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.TabLayout, i, i2, R$styleable.TabLayout_tabTextAppearance);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            u50 u50Var = new u50();
            u50Var.m(ColorStateList.valueOf(colorDrawable.getColor()));
            u50Var.k(context2);
            WeakHashMap weakHashMap = xr0.a;
            u50Var.l(lr0.i(this));
            setBackground(u50Var);
        }
        setSelectedTabIndicator(xi.x(context2, typedArrayD, R$styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorColor(typedArrayD.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        nm0Var.b(typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, -1));
        setSelectedTabIndicatorGravity(typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorAnimationMode(typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorAnimationMode, 0));
        setTabIndicatorFullWidth(typedArrayD.getBoolean(R$styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.g = dimensionPixelSize;
        this.f = dimensionPixelSize;
        this.f = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.g = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, dimensionPixelSize);
        this.h = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, dimensionPixelSize);
        this.i = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, dimensionPixelSize);
        if (nv0.K(context2, R$attr.isMaterial3Theme, false)) {
            this.j = R$attr.textAppearanceTitleSmall;
        } else {
            this.j = R$attr.textAppearanceButton;
        }
        int resourceId = typedArrayD.getResourceId(R$styleable.TabLayout_tabTextAppearance, R$style.TextAppearance_Design_Tab);
        this.k = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, androidx.appcompat.R$styleable.TextAppearance);
        try {
            float dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R$styleable.TextAppearance_android_textSize, 0);
            this.s = dimensionPixelSize2;
            this.m = xi.s(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R$styleable.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            if (typedArrayD.hasValue(R$styleable.TabLayout_tabSelectedTextAppearance)) {
                this.l = typedArrayD.getResourceId(R$styleable.TabLayout_tabSelectedTextAppearance, resourceId);
            }
            int i3 = this.l;
            int[] iArr = HorizontalScrollView.EMPTY_STATE_SET;
            int[] iArr2 = HorizontalScrollView.SELECTED_STATE_SET;
            if (i3 != -1) {
                typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(i3, androidx.appcompat.R$styleable.TextAppearance);
                try {
                    typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R$styleable.TextAppearance_android_textSize, (int) dimensionPixelSize2);
                    ColorStateList colorStateListS = xi.s(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R$styleable.TextAppearance_android_textColor);
                    if (colorStateListS != null) {
                        this.m = new ColorStateList(new int[][]{iArr2, iArr}, new int[]{colorStateListS.getColorForState(new int[]{R.attr.state_selected}, colorStateListS.getDefaultColor()), this.m.getDefaultColor()});
                    }
                } finally {
                }
            }
            if (typedArrayD.hasValue(R$styleable.TabLayout_tabTextColor)) {
                this.m = xi.s(context2, typedArrayD, R$styleable.TabLayout_tabTextColor);
            }
            if (typedArrayD.hasValue(R$styleable.TabLayout_tabSelectedTextColor)) {
                this.m = new ColorStateList(new int[][]{iArr2, iArr}, new int[]{typedArrayD.getColor(R$styleable.TabLayout_tabSelectedTextColor, 0), this.m.getDefaultColor()});
            }
            this.n = xi.s(context2, typedArrayD, R$styleable.TabLayout_tabIconTint);
            this.r = rp0.O(typedArrayD.getInt(R$styleable.TabLayout_tabIconTintMode, -1), null);
            this.o = xi.s(context2, typedArrayD, R$styleable.TabLayout_tabRippleColor);
            this.B = typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorAnimationDuration, 300);
            this.K = k9.F(context2, R$attr.motionEasingEmphasizedInterpolator, t3.b);
            this.w = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, -1);
            this.x = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, -1);
            this.u = typedArrayD.getResourceId(R$styleable.TabLayout_tabBackground, 0);
            this.z = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
            this.D = typedArrayD.getInt(R$styleable.TabLayout_tabMode, 1);
            this.A = typedArrayD.getInt(R$styleable.TabLayout_tabGravity, 0);
            this.E = typedArrayD.getBoolean(R$styleable.TabLayout_tabInlineLabel, false);
            this.I = typedArrayD.getBoolean(R$styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayD.recycle();
            Resources resources = getResources();
            this.t = resources.getDimensionPixelSize(R$dimen.design_tab_text_size_2line);
            this.y = resources.getDimensionPixelSize(R$dimen.design_tab_scrollable_min_width);
            c();
        } finally {
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    public void setSelectedTabIndicator(int i) {
        if (i != 0) {
            setSelectedTabIndicator(rp0.y(getContext(), i));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
