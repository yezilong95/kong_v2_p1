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
import defpackage.al0;
import defpackage.az0;
import defpackage.bb;
import defpackage.bl0;
import defpackage.bz0;
import defpackage.cz0;
import defpackage.dz0;
import defpackage.e2;
import defpackage.ez0;
import defpackage.f51;
import defpackage.fa0;
import defpackage.gp0;
import defpackage.i01;
import defpackage.i2;
import defpackage.ip;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.n9;
import defpackage.p50;
import defpackage.pd0;
import defpackage.qm;
import defpackage.s90;
import defpackage.ss;
import defpackage.t40;
import defpackage.t41;
import defpackage.u51;
import defpackage.v41;
import defpackage.w51;
import defpackage.y4;
import defpackage.yj0;
import defpackage.yk0;
import defpackage.zy0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
@u51
public class TabLayout extends HorizontalScrollView {
    public static final int B0 = R$style.Widget_Design_TabLayout;
    public static final bl0 C0 = new bl0(16);
    public final al0 A0;
    public int E;
    public final ArrayList F;
    public b G;
    public final cz0 H;
    public final int I;
    public final int J;
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public ColorStateList P;
    public ColorStateList Q;
    public ColorStateList R;
    public Drawable S;
    public int T;
    public final PorterDuff.Mode U;
    public final float V;
    public final float W;
    public final int a0;
    public int b0;
    public final int c0;
    public final int d0;
    public final int e0;
    public final int f0;
    public int g0;
    public final int h0;
    public int i0;
    public int j0;
    public boolean k0;
    public boolean l0;
    public int m0;
    public int n0;
    public boolean o0;
    public a p0;
    public final TimeInterpolator q0;
    public az0 r0;
    public final ArrayList s0;
    public ez0 t0;
    public ValueAnimator u0;
    public ViewPager v0;
    public dz0 w0;
    public zy0 x0;
    public boolean y0;
    public int z0;

    public final class TabView extends LinearLayout {
        public static final /* synthetic */ int P = 0;
        public b E;
        public TextView F;
        public ImageView G;
        public View H;
        public n9 I;
        public View J;
        public TextView K;
        public ImageView L;
        public Drawable M;
        public int N;

        public TabView(Context context) {
            super(context);
            this.N = 2;
            e(context);
            int i = TabLayout.this.I;
            WeakHashMap weakHashMap = f51.a;
            setPaddingRelative(i, TabLayout.this.J, TabLayout.this.K, TabLayout.this.L);
            setGravity(17);
            setOrientation(!TabLayout.this.k0 ? 1 : 0);
            setClickable(true);
            v41.d(this, yk0.b(getContext(), CloseFrame.PROTOCOL_ERROR));
        }

        private n9 getBadge() {
            return this.I;
        }

        private n9 getOrCreateBadge() {
            if (this.I == null) {
                this.I = new n9(getContext(), null);
            }
            b();
            n9 n9Var = this.I;
            if (n9Var != null) {
                return n9Var;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void a() {
            if (this.I != null) {
                setClipChildren(true);
                setClipToPadding(true);
                ViewGroup viewGroup = (ViewGroup) getParent();
                if (viewGroup != null) {
                    viewGroup.setClipChildren(true);
                    viewGroup.setClipToPadding(true);
                }
                View view = this.H;
                if (view != null) {
                    n9 n9Var = this.I;
                    if (n9Var != null) {
                        if (n9Var.d() != null) {
                            n9Var.d().setForeground(null);
                        } else {
                            view.getOverlay().remove(n9Var);
                        }
                    }
                    this.H = null;
                }
            }
        }

        public final void b() {
            b bVar;
            if (this.I != null) {
                if (this.J != null) {
                    a();
                    return;
                }
                ImageView imageView = this.G;
                if (imageView != null && (bVar = this.E) != null && bVar.a != null) {
                    if (this.H == imageView) {
                        c(imageView);
                        return;
                    }
                    a();
                    ImageView imageView2 = this.G;
                    if (this.I == null || imageView2 == null) {
                        return;
                    }
                    setClipChildren(false);
                    setClipToPadding(false);
                    ViewGroup viewGroup = (ViewGroup) getParent();
                    if (viewGroup != null) {
                        viewGroup.setClipChildren(false);
                        viewGroup.setClipToPadding(false);
                    }
                    n9 n9Var = this.I;
                    Rect rect = new Rect();
                    imageView2.getDrawingRect(rect);
                    n9Var.setBounds(rect);
                    n9Var.i(imageView2, null);
                    if (n9Var.d() != null) {
                        n9Var.d().setForeground(n9Var);
                    } else {
                        imageView2.getOverlay().add(n9Var);
                    }
                    this.H = imageView2;
                    return;
                }
                TextView textView = this.F;
                if (textView == null || this.E == null) {
                    a();
                    return;
                }
                if (this.H == textView) {
                    c(textView);
                    return;
                }
                a();
                TextView textView2 = this.F;
                if (this.I == null || textView2 == null) {
                    return;
                }
                setClipChildren(false);
                setClipToPadding(false);
                ViewGroup viewGroup2 = (ViewGroup) getParent();
                if (viewGroup2 != null) {
                    viewGroup2.setClipChildren(false);
                    viewGroup2.setClipToPadding(false);
                }
                n9 n9Var2 = this.I;
                Rect rect2 = new Rect();
                textView2.getDrawingRect(rect2);
                n9Var2.setBounds(rect2);
                n9Var2.i(textView2, null);
                if (n9Var2.d() != null) {
                    n9Var2.d().setForeground(n9Var2);
                } else {
                    textView2.getOverlay().add(n9Var2);
                }
                this.H = textView2;
            }
        }

        public final void c(View view) {
            n9 n9Var = this.I;
            if (n9Var == null || view != this.H) {
                return;
            }
            Rect rect = new Rect();
            view.getDrawingRect(rect);
            n9Var.setBounds(rect);
            n9Var.i(view, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void d() {
            /*
                r3 = this;
                r3.f()
                com.google.android.material.tabs.b r0 = r3.E
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
            Drawable drawable = this.M;
            if ((drawable == null || !drawable.isStateful()) ? false : this.M.setState(drawableState)) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public final void e(Context context) {
            TabLayout tabLayout = TabLayout.this;
            int i = tabLayout.a0;
            if (i != 0) {
                Drawable drawableZ = qm.z(context, i);
                this.M = drawableZ;
                if (drawableZ != null && drawableZ.isStateful()) {
                    this.M.setState(getDrawableState());
                }
            } else {
                this.M = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable rippleDrawable = gradientDrawable;
            if (tabLayout.R != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = gp0.a(tabLayout.R);
                boolean z = tabLayout.o0;
                GradientDrawable gradientDrawable3 = gradientDrawable;
                if (z) {
                    gradientDrawable3 = null;
                }
                rippleDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z ? null : gradientDrawable2);
            }
            WeakHashMap weakHashMap = f51.a;
            setBackground(rippleDrawable);
            tabLayout.invalidate();
        }

        public final void f() {
            int i;
            ViewParent parent;
            b bVar = this.E;
            View view = bVar != null ? bVar.e : null;
            if (view != null) {
                ViewParent parent2 = view.getParent();
                if (parent2 != this) {
                    if (parent2 != null) {
                        ((ViewGroup) parent2).removeView(view);
                    }
                    View view2 = this.J;
                    if (view2 != null && (parent = view2.getParent()) != null) {
                        ((ViewGroup) parent).removeView(this.J);
                    }
                    addView(view);
                }
                this.J = view;
                TextView textView = this.F;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.G;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.G.setImageDrawable(null);
                }
                TextView textView2 = (TextView) view.findViewById(R.id.text1);
                this.K = textView2;
                if (textView2 != null) {
                    this.N = textView2.getMaxLines();
                }
                this.L = (ImageView) view.findViewById(R.id.icon);
            } else {
                View view3 = this.J;
                if (view3 != null) {
                    removeView(view3);
                    this.J = null;
                }
                this.K = null;
                this.L = null;
            }
            if (this.J == null) {
                if (this.G == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_icon, (ViewGroup) this, false);
                    this.G = imageView2;
                    addView(imageView2, 0);
                }
                if (this.F == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_text, (ViewGroup) this, false);
                    this.F = textView3;
                    addView(textView3);
                    this.N = this.F.getMaxLines();
                }
                TextView textView4 = this.F;
                TabLayout tabLayout = TabLayout.this;
                textView4.setTextAppearance(tabLayout.M);
                if (!isSelected() || (i = tabLayout.O) == -1) {
                    this.F.setTextAppearance(tabLayout.N);
                } else {
                    this.F.setTextAppearance(i);
                }
                ColorStateList colorStateList = tabLayout.P;
                if (colorStateList != null) {
                    this.F.setTextColor(colorStateList);
                }
                g(this.F, this.G, true);
                b();
                ImageView imageView3 = this.G;
                if (imageView3 != null) {
                    imageView3.addOnLayoutChangeListener(new c(this, imageView3));
                }
                TextView textView5 = this.F;
                if (textView5 != null) {
                    textView5.addOnLayoutChangeListener(new c(this, textView5));
                }
            } else {
                TextView textView6 = this.K;
                if (textView6 != null || this.L != null) {
                    g(textView6, this.L, false);
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
            b bVar = this.E;
            Drawable drawableMutate = (bVar == null || (drawable = bVar.a) == null) ? null : drawable.mutate();
            TabLayout tabLayout = TabLayout.this;
            if (drawableMutate != null) {
                ip.h(drawableMutate, tabLayout.Q);
                PorterDuff.Mode mode = tabLayout.U;
                if (mode != null) {
                    ip.i(drawableMutate, mode);
                }
            }
            b bVar2 = this.E;
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
                    this.E.getClass();
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
                int iC = (z2 && imageView.getVisibility() == 0) ? (int) fa0.C(getContext(), 8) : 0;
                if (tabLayout.k0) {
                    if (iC != marginLayoutParams.getMarginEnd()) {
                        marginLayoutParams.setMarginEnd(iC);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iC != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iC;
                    marginLayoutParams.setMarginEnd(0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            b bVar3 = this.E;
            CharSequence charSequence2 = bVar3 != null ? bVar3.c : null;
            if (zIsEmpty) {
                charSequence = charSequence2;
            }
            la0.e0(this, charSequence);
        }

        public int getContentHeight() {
            View[] viewArr = {this.F, this.G, this.J};
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
            View[] viewArr = {this.F, this.G, this.J};
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
            return this.E;
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            n9 n9Var = this.I;
            if (n9Var != null && n9Var.isVisible()) {
                accessibilityNodeInfo.setContentDescription(this.I.c());
            }
            accessibilityNodeInfo.setCollectionItemInfo(i2.a(0, 1, this.E.d, 1, false, isSelected()).a);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) e2.g.a);
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
                i = View.MeasureSpec.makeMeasureSpec(tabLayout.b0, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.F != null) {
                float f = tabLayout.V;
                int i3 = this.N;
                ImageView imageView = this.G;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.F;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = tabLayout.W;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.F.getTextSize();
                int lineCount = this.F.getLineCount();
                int maxLines = this.F.getMaxLines();
                if (f != textSize || (maxLines >= 0 && i3 != maxLines)) {
                    if (tabLayout.j0 == 1 && f > textSize && lineCount == 1) {
                        Layout layout = this.F.getLayout();
                        if (layout == null) {
                            return;
                        }
                        if ((f / layout.getPaint().getTextSize()) * layout.getLineWidth(0) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) {
                            return;
                        }
                    }
                    this.F.setTextSize(0, f);
                    this.F.setMaxLines(i3);
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.view.View
        public final boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.E == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            b bVar = this.E;
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
            TextView textView = this.F;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.G;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.J;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public void setTab(b bVar) {
            if (bVar != this.E) {
                this.E = bVar;
                d();
            }
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private int getDefaultHeight() {
        ArrayList arrayList = this.F;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) arrayList.get(i);
            if (bVar != null && bVar.a != null && !TextUtils.isEmpty(bVar.b)) {
                return !this.k0 ? 72 : 48;
            }
        }
        return 48;
    }

    private int getTabMinWidth() {
        int i = this.c0;
        if (i != -1) {
            return i;
        }
        int i2 = this.j0;
        if (i2 == 0 || i2 == 2) {
            return this.e0;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.H.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setSelectedTabView(int i) {
        cz0 cz0Var = this.H;
        int childCount = cz0Var.getChildCount();
        if (i < childCount) {
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = cz0Var.getChildAt(i2);
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
        b bVar = (b) C0.a();
        if (bVar == null) {
            bVar = new b();
            bVar.d = -1;
        }
        bVar.f = this;
        al0 al0Var = this.A0;
        TabView tabView = al0Var != null ? (TabView) al0Var.a() : null;
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
        CharSequence charSequence = tabItem.E;
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
        Drawable drawable = tabItem.F;
        if (drawable != null) {
            bVar.a = drawable;
            TabLayout tabLayout = bVar.f;
            if (tabLayout.g0 == 1 || tabLayout.j0 == 2) {
                tabLayout.j(true);
            }
            TabView tabView3 = bVar.g;
            if (tabView3 != null) {
                tabView3.d();
            }
        }
        int i = tabItem.G;
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
        ArrayList arrayList = this.F;
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
            if (((b) arrayList.get(i3)).d == this.E) {
                i2 = i3;
            }
            ((b) arrayList.get(i3)).d = i3;
        }
        this.E = i2;
        TabView tabView6 = bVar.g;
        tabView6.setSelected(false);
        tabView6.setActivated(false);
        int i4 = bVar.d;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        if (this.j0 == 1 && this.g0 == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
        this.H.addView(tabView6, i4, layoutParams);
        if (zIsEmpty) {
            TabLayout tabLayout2 = bVar.f;
            if (tabLayout2 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout2.g(bVar, true);
        }
    }

    public void addOnTabSelectedListener(bz0 bz0Var) {
        addOnTabSelectedListener((az0) bz0Var);
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
            WeakHashMap weakHashMap = f51.a;
            if (isLaidOut()) {
                cz0 cz0Var = this.H;
                int childCount = cz0Var.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    if (cz0Var.getChildAt(i2).getWidth() > 0) {
                    }
                }
                int scrollX = getScrollX();
                int iD = d(i, 0.0f);
                if (scrollX != iD) {
                    e();
                    this.u0.setIntValues(scrollX, iD);
                    this.u0.start();
                }
                ValueAnimator valueAnimator = cz0Var.E;
                if (valueAnimator != null && valueAnimator.isRunning() && cz0Var.F.E != i) {
                    cz0Var.E.cancel();
                }
                cz0Var.d(i, this.h0, true);
                return;
            }
        }
        h(i, 0.0f, true, true, true);
    }

    public final void c() {
        int i = this.j0;
        int iMax = (i == 0 || i == 2) ? Math.max(0, this.f0 - this.I) : 0;
        WeakHashMap weakHashMap = f51.a;
        cz0 cz0Var = this.H;
        cz0Var.setPaddingRelative(iMax, 0, 0, 0);
        int i2 = this.j0;
        if (i2 == 0) {
            int i3 = this.g0;
            if (i3 == 0) {
                Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
            } else if (i3 == 1) {
                cz0Var.setGravity(1);
            } else if (i3 == 2) {
            }
            cz0Var.setGravity(8388611);
        } else if (i2 == 1 || i2 == 2) {
            if (this.g0 == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            cz0Var.setGravity(1);
        }
        j(true);
    }

    public final int d(int i, float f) {
        cz0 cz0Var;
        View childAt;
        int i2 = this.j0;
        if ((i2 != 0 && i2 != 2) || (childAt = (cz0Var = this.H).getChildAt(i)) == null) {
            return 0;
        }
        int i3 = i + 1;
        View childAt2 = i3 < cz0Var.getChildCount() ? cz0Var.getChildAt(i3) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i4 = (int) ((width + width2) * 0.5f * f);
        WeakHashMap weakHashMap = f51.a;
        return getLayoutDirection() == 0 ? left + i4 : left - i4;
    }

    public final void e() {
        if (this.u0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.u0 = valueAnimator;
            valueAnimator.setInterpolator(this.q0);
            this.u0.setDuration(this.h0);
            this.u0.addUpdateListener(new bb(4, this));
        }
    }

    public final void f() {
        cz0 cz0Var = this.H;
        int childCount = cz0Var.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            TabView tabView = (TabView) cz0Var.getChildAt(childCount);
            cz0Var.removeViewAt(childCount);
            if (tabView != null) {
                tabView.setTab(null);
                tabView.setSelected(false);
                this.A0.c(tabView);
            }
            requestLayout();
        }
        Iterator it = this.F.iterator();
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
            C0.c(bVar);
        }
        this.G = null;
    }

    public final void g(b bVar, boolean z) {
        TabLayout tabLayout;
        b bVar2 = this.G;
        ArrayList arrayList = this.s0;
        if (bVar2 == bVar) {
            if (bVar2 != null) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((az0) arrayList.get(size)).getClass();
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
        tabLayout.G = bVar;
        if (bVar2 != null && bVar2.f != null) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                ((az0) arrayList.get(size2)).getClass();
            }
        }
        if (bVar != null) {
            for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                ez0 ez0Var = (ez0) ((az0) arrayList.get(size3));
                ez0Var.getClass();
                ez0Var.a.setCurrentItem(bVar.d);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition() {
        b bVar = this.G;
        if (bVar != null) {
            return bVar.d;
        }
        return -1;
    }

    public int getTabCount() {
        return this.F.size();
    }

    public int getTabGravity() {
        return this.g0;
    }

    public ColorStateList getTabIconTint() {
        return this.Q;
    }

    public int getTabIndicatorAnimationMode() {
        return this.n0;
    }

    public int getTabIndicatorGravity() {
        return this.i0;
    }

    public int getTabMaxWidth() {
        return this.b0;
    }

    public int getTabMode() {
        return this.j0;
    }

    public ColorStateList getTabRippleColor() {
        return this.R;
    }

    public Drawable getTabSelectedIndicator() {
        return this.S;
    }

    public ColorStateList getTabTextColors() {
        return this.P;
    }

    public final void h(int i, float f, boolean z, boolean z2, boolean z3) {
        float f2 = i + f;
        int iRound = Math.round(f2);
        if (iRound >= 0) {
            cz0 cz0Var = this.H;
            if (iRound >= cz0Var.getChildCount()) {
                return;
            }
            if (z2) {
                cz0Var.F.E = Math.round(f2);
                ValueAnimator valueAnimator = cz0Var.E;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    cz0Var.E.cancel();
                }
                cz0Var.c(cz0Var.getChildAt(i), cz0Var.getChildAt(i + 1), f);
            }
            ValueAnimator valueAnimator2 = this.u0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.u0.cancel();
            }
            int iD = d(i, f);
            int scrollX = getScrollX();
            boolean z4 = (i < getSelectedTabPosition() && iD >= scrollX) || (i > getSelectedTabPosition() && iD <= scrollX) || i == getSelectedTabPosition();
            WeakHashMap weakHashMap = f51.a;
            if (getLayoutDirection() == 1) {
                z4 = (i < getSelectedTabPosition() && iD <= scrollX) || (i > getSelectedTabPosition() && iD >= scrollX) || i == getSelectedTabPosition();
            }
            if (z4 || this.z0 == 1 || z3) {
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
        ViewPager viewPager2 = this.v0;
        if (viewPager2 != null) {
            dz0 dz0Var = this.w0;
            if (dz0Var != null) {
                viewPager2.removeOnPageChangeListener(dz0Var);
            }
            zy0 zy0Var = this.x0;
            if (zy0Var != null) {
                this.v0.removeOnAdapterChangeListener(zy0Var);
            }
        }
        ez0 ez0Var = this.t0;
        if (ez0Var != null) {
            removeOnTabSelectedListener((az0) ez0Var);
            this.t0 = null;
        }
        if (viewPager != null) {
            this.v0 = viewPager;
            if (this.w0 == null) {
                this.w0 = new dz0(this);
            }
            dz0 dz0Var2 = this.w0;
            dz0Var2.c = 0;
            dz0Var2.b = 0;
            viewPager.addOnPageChangeListener(dz0Var2);
            ez0 ez0Var2 = new ez0(viewPager);
            this.t0 = ez0Var2;
            addOnTabSelectedListener((az0) ez0Var2);
            viewPager.getAdapter();
            if (this.x0 == null) {
                this.x0 = new zy0(this);
            }
            w51 w51Var = this.x0;
            w51Var.getClass();
            viewPager.addOnAdapterChangeListener(w51Var);
            h(viewPager.getCurrentItem(), 0.0f, true, true, true);
            tabLayout = this;
        } else {
            tabLayout = this;
            tabLayout.v0 = null;
            f();
        }
        tabLayout.y0 = z;
    }

    public final void j(boolean z) {
        int i = 0;
        while (true) {
            cz0 cz0Var = this.H;
            if (i >= cz0Var.getChildCount()) {
                return;
            }
            View childAt = cz0Var.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (this.j0 == 1 && this.g0 == 0) {
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
        ma0.p0(this);
        if (this.v0 == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                i((ViewPager) parent, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.y0) {
            setupWithViewPager(null);
            this.y0 = false;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        TabView tabView;
        Drawable drawable;
        int i = 0;
        while (true) {
            cz0 cz0Var = this.H;
            if (i >= cz0Var.getChildCount()) {
                super.onDraw(canvas);
                return;
            }
            View childAt = cz0Var.getChildAt(i);
            if ((childAt instanceof TabView) && (drawable = (tabView = (TabView) childAt).M) != null) {
                drawable.setBounds(tabView.getLeft(), tabView.getTop(), tabView.getRight(), tabView.getBottom());
                tabView.M.draw(canvas);
            }
            i++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) p50.B(1, getTabCount(), 1).F);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (getTabMode() == 0 || getTabMode() == 2) && super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = defpackage.fa0.C(r0, r1)
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
            int r1 = r6.d0
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = defpackage.fa0.C(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.b0 = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Laa
            android.view.View r7 = r6.getChildAt(r3)
            int r0 = r6.j0
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

    public void removeOnTabSelectedListener(bz0 bz0Var) {
        removeOnTabSelectedListener((az0) bz0Var);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ma0.n0(this, f);
    }

    public void setInlineLabel(boolean z) {
        if (this.k0 == z) {
            return;
        }
        this.k0 = z;
        int i = 0;
        while (true) {
            cz0 cz0Var = this.H;
            if (i >= cz0Var.getChildCount()) {
                c();
                return;
            }
            View childAt = cz0Var.getChildAt(i);
            if (childAt instanceof TabView) {
                TabView tabView = (TabView) childAt;
                tabView.setOrientation(!TabLayout.this.k0 ? 1 : 0);
                TextView textView = tabView.K;
                if (textView == null && tabView.L == null) {
                    tabView.g(tabView.F, tabView.G, true);
                } else {
                    tabView.g(textView, tabView.L, false);
                }
            }
            i++;
        }
    }

    public void setInlineLabelResource(int i) {
        setInlineLabel(getResources().getBoolean(i));
    }

    @Deprecated
    public void setOnTabSelectedListener(bz0 bz0Var) {
        setOnTabSelectedListener((az0) bz0Var);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        e();
        this.u0.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable drawableMutate = drawable.mutate();
        this.S = drawableMutate;
        int i = this.T;
        if (i != 0) {
            ip.g(drawableMutate, i);
        } else {
            ip.h(drawableMutate, null);
        }
        int intrinsicHeight = this.m0;
        if (intrinsicHeight == -1) {
            intrinsicHeight = this.S.getIntrinsicHeight();
        }
        this.H.b(intrinsicHeight);
    }

    public void setSelectedTabIndicatorColor(int i) {
        this.T = i;
        Drawable drawable = this.S;
        if (i != 0) {
            ip.g(drawable, i);
        } else {
            ip.h(drawable, null);
        }
        j(false);
    }

    public void setSelectedTabIndicatorGravity(int i) {
        if (this.i0 != i) {
            this.i0 = i;
            WeakHashMap weakHashMap = f51.a;
            this.H.postInvalidateOnAnimation();
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i) {
        this.m0 = i;
        this.H.b(i);
    }

    public void setTabGravity(int i) {
        if (this.g0 != i) {
            this.g0 = i;
            c();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.Q != colorStateList) {
            this.Q = colorStateList;
            ArrayList arrayList = this.F;
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
        setTabIconTint(t40.Y(getContext(), i));
    }

    public void setTabIndicatorAnimationMode(int i) {
        this.n0 = i;
        if (i == 0) {
            this.p0 = new a();
            return;
        }
        if (i == 1) {
            this.p0 = new ss(0);
        } else {
            if (i == 2) {
                this.p0 = new ss(1);
                return;
            }
            throw new IllegalArgumentException(i + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.l0 = z;
        int i = cz0.G;
        cz0 cz0Var = this.H;
        cz0Var.a(cz0Var.F.getSelectedTabPosition());
        WeakHashMap weakHashMap = f51.a;
        cz0Var.postInvalidateOnAnimation();
    }

    public void setTabMode(int i) {
        if (i != this.j0) {
            this.j0 = i;
            c();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.R == colorStateList) {
            return;
        }
        this.R = colorStateList;
        int i = 0;
        while (true) {
            cz0 cz0Var = this.H;
            if (i >= cz0Var.getChildCount()) {
                return;
            }
            View childAt = cz0Var.getChildAt(i);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i2 = TabView.P;
                ((TabView) childAt).e(context);
            }
            i++;
        }
    }

    public void setTabRippleColorResource(int i) {
        setTabRippleColor(t40.Y(getContext(), i));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.P != colorStateList) {
            this.P = colorStateList;
            ArrayList arrayList = this.F;
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
    public void setTabsFromPagerAdapter(yj0 yj0Var) {
        f();
    }

    public void setUnboundedRipple(boolean z) {
        if (this.o0 == z) {
            return;
        }
        this.o0 = z;
        int i = 0;
        while (true) {
            cz0 cz0Var = this.H;
            if (i >= cz0Var.getChildCount()) {
                return;
            }
            View childAt = cz0Var.getChildAt(i);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i2 = TabView.P;
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
    public void addOnTabSelectedListener(az0 az0Var) {
        ArrayList arrayList = this.s0;
        if (arrayList.contains(az0Var)) {
            return;
        }
        arrayList.add(az0Var);
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
    public void removeOnTabSelectedListener(az0 az0Var) {
        this.s0.remove(az0Var);
    }

    @Deprecated
    public void setOnTabSelectedListener(az0 az0Var) {
        az0 az0Var2 = this.r0;
        if (az0Var2 != null) {
            removeOnTabSelectedListener(az0Var2);
        }
        this.r0 = az0Var;
        if (az0Var != null) {
            addOnTabSelectedListener(az0Var);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = B0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.E = -1;
        this.F = new ArrayList();
        this.O = -1;
        this.T = 0;
        this.b0 = Integer.MAX_VALUE;
        this.m0 = -1;
        this.s0 = new ArrayList();
        this.A0 = new al0(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        cz0 cz0Var = new cz0(this, context2);
        this.H = cz0Var;
        super.addView(cz0Var, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.TabLayout, i, i2, R$styleable.TabLayout_tabTextAppearance);
        ColorStateList colorStateListS = qm.s(getBackground());
        if (colorStateListS != null) {
            ld0 ld0Var = new ld0();
            ld0Var.m(colorStateListS);
            ld0Var.k(context2);
            WeakHashMap weakHashMap = f51.a;
            ld0Var.l(t41.i(this));
            setBackground(ld0Var);
        }
        setSelectedTabIndicator(la0.s(context2, typedArrayD, R$styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorColor(typedArrayD.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        cz0Var.b(typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, -1));
        setSelectedTabIndicatorGravity(typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorAnimationMode(typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorAnimationMode, 0));
        setTabIndicatorFullWidth(typedArrayD.getBoolean(R$styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.L = dimensionPixelSize;
        this.K = dimensionPixelSize;
        this.J = dimensionPixelSize;
        this.I = dimensionPixelSize;
        this.I = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.J = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, dimensionPixelSize);
        this.K = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, dimensionPixelSize);
        this.L = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, dimensionPixelSize);
        if (s90.T(context2, R$attr.isMaterial3Theme, false)) {
            this.M = R$attr.textAppearanceTitleSmall;
        } else {
            this.M = R$attr.textAppearanceButton;
        }
        int resourceId = typedArrayD.getResourceId(R$styleable.TabLayout_tabTextAppearance, R$style.TextAppearance_Design_Tab);
        this.N = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, androidx.appcompat.R$styleable.TextAppearance);
        try {
            float dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R$styleable.TextAppearance_android_textSize, 0);
            this.V = dimensionPixelSize2;
            this.P = la0.p(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R$styleable.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            if (typedArrayD.hasValue(R$styleable.TabLayout_tabSelectedTextAppearance)) {
                this.O = typedArrayD.getResourceId(R$styleable.TabLayout_tabSelectedTextAppearance, resourceId);
            }
            int i3 = this.O;
            int[] iArr = HorizontalScrollView.EMPTY_STATE_SET;
            int[] iArr2 = HorizontalScrollView.SELECTED_STATE_SET;
            if (i3 != -1) {
                typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(i3, androidx.appcompat.R$styleable.TextAppearance);
                try {
                    typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R$styleable.TextAppearance_android_textSize, (int) dimensionPixelSize2);
                    ColorStateList colorStateListP = la0.p(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R$styleable.TextAppearance_android_textColor);
                    if (colorStateListP != null) {
                        this.P = new ColorStateList(new int[][]{iArr2, iArr}, new int[]{colorStateListP.getColorForState(new int[]{R.attr.state_selected}, colorStateListP.getDefaultColor()), this.P.getDefaultColor()});
                    }
                } finally {
                }
            }
            if (typedArrayD.hasValue(R$styleable.TabLayout_tabTextColor)) {
                this.P = la0.p(context2, typedArrayD, R$styleable.TabLayout_tabTextColor);
            }
            if (typedArrayD.hasValue(R$styleable.TabLayout_tabSelectedTextColor)) {
                this.P = new ColorStateList(new int[][]{iArr2, iArr}, new int[]{typedArrayD.getColor(R$styleable.TabLayout_tabSelectedTextColor, 0), this.P.getDefaultColor()});
            }
            this.Q = la0.p(context2, typedArrayD, R$styleable.TabLayout_tabIconTint);
            this.U = fa0.p0(typedArrayD.getInt(R$styleable.TabLayout_tabIconTintMode, -1), null);
            this.R = la0.p(context2, typedArrayD, R$styleable.TabLayout_tabRippleColor);
            this.h0 = typedArrayD.getInt(R$styleable.TabLayout_tabIndicatorAnimationDuration, 300);
            this.q0 = s90.V(context2, R$attr.motionEasingEmphasizedInterpolator, y4.b);
            this.c0 = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, -1);
            this.d0 = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, -1);
            this.a0 = typedArrayD.getResourceId(R$styleable.TabLayout_tabBackground, 0);
            this.f0 = typedArrayD.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
            this.j0 = typedArrayD.getInt(R$styleable.TabLayout_tabMode, 1);
            this.g0 = typedArrayD.getInt(R$styleable.TabLayout_tabGravity, 0);
            this.k0 = typedArrayD.getBoolean(R$styleable.TabLayout_tabInlineLabel, false);
            this.o0 = typedArrayD.getBoolean(R$styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayD.recycle();
            Resources resources = getResources();
            this.W = resources.getDimensionPixelSize(R$dimen.design_tab_text_size_2line);
            this.e0 = resources.getDimensionPixelSize(R$dimen.design_tab_scrollable_min_width);
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
            setSelectedTabIndicator(qm.z(getContext(), i));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
