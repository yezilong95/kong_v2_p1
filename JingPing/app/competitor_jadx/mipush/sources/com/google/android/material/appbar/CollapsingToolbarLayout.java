package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bb;
import defpackage.bo;
import defpackage.f5;
import defpackage.f51;
import defpackage.i01;
import defpackage.jp;
import defpackage.la0;
import defpackage.p50;
import defpackage.pd0;
import defpackage.pi;
import defpackage.r41;
import defpackage.s81;
import defpackage.s90;
import defpackage.sf;
import defpackage.t40;
import defpackage.t41;
import defpackage.t51;
import defpackage.tf;
import defpackage.ts;
import defpackage.y4;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    public static final int m0 = R$style.Widget_Design_CollapsingToolbar;
    public boolean E;
    public final int F;
    public ViewGroup G;
    public View H;
    public View I;
    public int J;
    public int K;
    public int L;
    public int M;
    public final Rect N;
    public final sf O;
    public final ts P;
    public boolean Q;
    public boolean R;
    public Drawable S;
    public Drawable T;
    public int U;
    public boolean V;
    public ValueAnimator W;
    public long a0;
    public final TimeInterpolator b0;
    public final TimeInterpolator c0;
    public int d0;
    public d e0;
    public int f0;
    public int g0;
    public s81 h0;
    public int i0;
    public boolean j0;
    public int k0;
    public boolean l0;

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public int a;
        public float b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            this.b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
            this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            this.b = typedArrayObtainStyledAttributes.getFloat(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public static t51 b(View view) {
        t51 t51Var = (t51) view.getTag(R$id.view_offset_helper);
        if (t51Var != null) {
            return t51Var;
        }
        t51 t51Var2 = new t51(view);
        view.setTag(R$id.view_offset_helper, t51Var2);
        return t51Var2;
    }

    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        Context context = getContext();
        TypedValue typedValueS = s90.S(context, R$attr.colorSurfaceContainer);
        ColorStateList colorStateListValueOf = null;
        if (typedValueS != null) {
            int i = typedValueS.resourceId;
            if (i != 0) {
                colorStateListValueOf = t40.Y(context, i);
            } else {
                int i2 = typedValueS.data;
                if (i2 != 0) {
                    colorStateListValueOf = ColorStateList.valueOf(i2);
                }
            }
        }
        if (colorStateListValueOf != null) {
            return colorStateListValueOf.getDefaultColor();
        }
        float dimension = getResources().getDimension(R$dimen.design_appbar_elevation);
        ts tsVar = this.P;
        return tsVar.b(tsVar.d, dimension);
    }

    public final void a() {
        if (this.E) {
            ViewGroup viewGroup = null;
            this.G = null;
            this.H = null;
            int i = this.F;
            if (i != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i);
                this.G = viewGroup2;
                if (viewGroup2 != null) {
                    ViewParent parent = viewGroup2.getParent();
                    View view = viewGroup2;
                    while (parent != this && parent != null) {
                        if (parent instanceof View) {
                            view = (View) parent;
                        }
                        parent = parent.getParent();
                        view = view;
                    }
                    this.H = view;
                }
            }
            if (this.G == null) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    if ((childAt instanceof Toolbar) || (childAt instanceof android.widget.Toolbar)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                }
                this.G = viewGroup;
            }
            c();
            this.E = false;
        }
    }

    public final void c() {
        View view;
        if (!this.Q && (view = this.I) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.I);
            }
        }
        if (!this.Q || this.G == null) {
            return;
        }
        if (this.I == null) {
            this.I = new View(getContext());
        }
        if (this.I.getParent() == null) {
            this.G.addView(this.I, -1, -1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d() {
        if (this.S == null && this.T == null) {
            return;
        }
        setScrimsShown(getHeight() + this.f0 < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        a();
        if (this.G == null && (drawable = this.S) != null && this.U > 0) {
            drawable.mutate().setAlpha(this.U);
            this.S.draw(canvas);
        }
        if (this.Q && this.R) {
            ViewGroup viewGroup = this.G;
            sf sfVar = this.O;
            if (viewGroup == null || this.S == null || this.U <= 0 || this.g0 != 1 || sfVar.b >= sfVar.e) {
                sfVar.d(canvas);
            } else {
                int iSave = canvas.save();
                canvas.clipRect(this.S.getBounds(), Region.Op.DIFFERENCE);
                sfVar.d(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        if (this.T == null || this.U <= 0) {
            return;
        }
        s81 s81Var = this.h0;
        int iD = s81Var != null ? s81Var.d() : 0;
        if (iD > 0) {
            this.T.setBounds(0, -this.f0, getWidth(), iD - this.f0);
            this.T.mutate().setAlpha(this.U);
            this.T.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        View view2;
        Drawable drawable = this.S;
        if (drawable == null || this.U <= 0 || ((view2 = this.H) == null || view2 == this ? view != this.G : view != view2)) {
            z = false;
        } else {
            int width = getWidth();
            int height = getHeight();
            if (this.g0 == 1 && view != null && this.Q) {
                height = view.getBottom();
            }
            drawable.setBounds(0, 0, width, height);
            this.S.mutate().setAlpha(this.U);
            this.S.draw(canvas);
            z = true;
        }
        return super.drawChild(canvas, view, j) || z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        ColorStateList colorStateList;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.T;
        boolean z = false;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.S;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        sf sfVar = this.O;
        if (sfVar != null) {
            sfVar.R = drawableState;
            ColorStateList colorStateList2 = sfVar.o;
            if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = sfVar.n) != null && colorStateList.isStateful())) {
                sfVar.i(false);
                z = true;
            }
            state |= z;
        }
        if (state) {
            invalidate();
        }
    }

    public final void e(boolean z, int i, int i2, int i3, int i4) {
        View view;
        int titleMarginBottom;
        int titleMarginEnd;
        int titleMarginTop;
        if (!this.Q || (view = this.I) == null) {
            return;
        }
        WeakHashMap weakHashMap = f51.a;
        int titleMarginStart = 0;
        boolean z2 = view.isAttachedToWindow() && this.I.getVisibility() == 0;
        this.R = z2;
        if (z2 || z) {
            boolean z3 = getLayoutDirection() == 1;
            View view2 = this.H;
            if (view2 == null) {
                view2 = this.G;
            }
            int height = ((getHeight() - b(view2).b) - view2.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view2.getLayoutParams())).bottomMargin;
            View view3 = this.I;
            Rect rect = this.N;
            bo.a(this, view3, rect);
            ViewGroup viewGroup = this.G;
            if (viewGroup instanceof Toolbar) {
                Toolbar toolbar = (Toolbar) viewGroup;
                titleMarginStart = toolbar.getTitleMarginStart();
                titleMarginEnd = toolbar.getTitleMarginEnd();
                titleMarginTop = toolbar.getTitleMarginTop();
                titleMarginBottom = toolbar.getTitleMarginBottom();
            } else if (viewGroup instanceof android.widget.Toolbar) {
                android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
                titleMarginStart = toolbar2.getTitleMarginStart();
                titleMarginEnd = toolbar2.getTitleMarginEnd();
                titleMarginTop = toolbar2.getTitleMarginTop();
                titleMarginBottom = toolbar2.getTitleMarginBottom();
            } else {
                titleMarginBottom = 0;
                titleMarginEnd = 0;
                titleMarginTop = 0;
            }
            int i5 = rect.left + (z3 ? titleMarginEnd : titleMarginStart);
            int i6 = rect.top + height + titleMarginTop;
            int i7 = rect.right;
            if (!z3) {
                titleMarginStart = titleMarginEnd;
            }
            int i8 = i7 - titleMarginStart;
            int i9 = (rect.bottom + height) - titleMarginBottom;
            sf sfVar = this.O;
            Rect rect2 = sfVar.h;
            if (rect2.left != i5 || rect2.top != i6 || rect2.right != i8 || rect2.bottom != i9) {
                rect2.set(i5, i6, i8, i9);
                sfVar.S = true;
            }
            int i10 = z3 ? this.L : this.J;
            int i11 = rect.top + this.K;
            int i12 = (i3 - i) - (z3 ? this.J : this.L);
            int i13 = (i4 - i2) - this.M;
            Rect rect3 = sfVar.g;
            if (rect3.left != i10 || rect3.top != i11 || rect3.right != i12 || rect3.bottom != i13) {
                rect3.set(i10, i11, i12, i13);
                sfVar.S = true;
            }
            sfVar.i(z);
        }
    }

    public final void f() {
        if (this.G != null && this.Q && TextUtils.isEmpty(this.O.G)) {
            ViewGroup viewGroup = this.G;
            setTitle(viewGroup instanceof Toolbar ? ((Toolbar) viewGroup).getTitle() : viewGroup instanceof android.widget.Toolbar ? ((android.widget.Toolbar) viewGroup).getTitle() : null);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.a = 0;
        layoutParams.b = 0.5f;
        return layoutParams;
    }

    public int getCollapsedTitleGravity() {
        return this.O.k;
    }

    public float getCollapsedTitleTextSize() {
        return this.O.m;
    }

    public Typeface getCollapsedTitleTypeface() {
        Typeface typeface = this.O.w;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public Drawable getContentScrim() {
        return this.S;
    }

    public int getExpandedTitleGravity() {
        return this.O.j;
    }

    public int getExpandedTitleMarginBottom() {
        return this.M;
    }

    public int getExpandedTitleMarginEnd() {
        return this.L;
    }

    public int getExpandedTitleMarginStart() {
        return this.J;
    }

    public int getExpandedTitleMarginTop() {
        return this.K;
    }

    public float getExpandedTitleTextSize() {
        return this.O.l;
    }

    public Typeface getExpandedTitleTypeface() {
        Typeface typeface = this.O.z;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int getHyphenationFrequency() {
        return this.O.q0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.O.i0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    public float getLineSpacingAdd() {
        return this.O.i0.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.O.i0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.O.n0;
    }

    public int getScrimAlpha() {
        return this.U;
    }

    public long getScrimAnimationDuration() {
        return this.a0;
    }

    public int getScrimVisibleHeightTrigger() {
        int i = this.d0;
        if (i >= 0) {
            return i + this.i0 + this.k0;
        }
        s81 s81Var = this.h0;
        int iD = s81Var != null ? s81Var.d() : 0;
        WeakHashMap weakHashMap = f51.a;
        int minimumHeight = getMinimumHeight();
        return minimumHeight > 0 ? Math.min((minimumHeight * 2) + iD, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.T;
    }

    public CharSequence getTitle() {
        if (this.Q) {
            return this.O.G;
        }
        return null;
    }

    public int getTitleCollapseMode() {
        return this.g0;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.O.V;
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.O.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.g0 == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
            WeakHashMap weakHashMap = f51.a;
            setFitsSystemWindows(appBarLayout.getFitsSystemWindows());
            if (this.e0 == null) {
                this.e0 = new d(this);
            }
            appBarLayout.addOnOffsetChangedListener((f5) this.e0);
            r41.c(this);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.O.h(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ViewParent parent = getParent();
        d dVar = this.e0;
        if (dVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener((f5) dVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        s81 s81Var = this.h0;
        if (s81Var != null) {
            int iD = s81Var.d();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                WeakHashMap weakHashMap = f51.a;
                if (!childAt.getFitsSystemWindows() && childAt.getTop() < iD) {
                    childAt.offsetTopAndBottom(iD);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            t51 t51VarB = b(getChildAt(i6));
            View view = t51VarB.a;
            t51VarB.b = view.getTop();
            t51VarB.c = view.getLeft();
        }
        e(false, i, i2, i3, i4);
        f();
        d();
        int childCount3 = getChildCount();
        for (int i7 = 0; i7 < childCount3; i7++) {
            b(getChildAt(i7)).a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            r9.a()
            super.onMeasure(r10, r11)
            int r11 = android.view.View.MeasureSpec.getMode(r11)
            s81 r0 = r9.h0
            if (r0 == 0) goto L13
            int r0 = r0.d()
            goto L14
        L13:
            r0 = 0
        L14:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r11 == 0) goto L1c
            boolean r11 = r9.j0
            if (r11 == 0) goto L2c
        L1c:
            if (r0 <= 0) goto L2c
            r9.i0 = r0
            int r11 = r9.getMeasuredHeight()
            int r11 = r11 + r0
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r1)
            super.onMeasure(r10, r11)
        L2c:
            boolean r11 = r9.l0
            if (r11 == 0) goto L7f
            sf r11 = r9.O
            int r0 = r11.n0
            r2 = 1
            if (r0 <= r2) goto L7f
            r9.f()
            int r7 = r9.getMeasuredWidth()
            int r8 = r9.getMeasuredHeight()
            r5 = 0
            r6 = 0
            r4 = 1
            r3 = r9
            r3.e(r4, r5, r6, r7, r8)
            int r0 = r11.p
            if (r0 <= r2) goto L80
            android.text.TextPaint r4 = r11.U
            float r5 = r11.l
            r4.setTextSize(r5)
            android.graphics.Typeface r5 = r11.z
            r4.setTypeface(r5)
            float r11 = r11.g0
            r4.setLetterSpacing(r11)
            float r11 = r4.ascent()
            float r11 = -r11
            float r4 = r4.descent()
            float r4 = r4 + r11
            int r11 = java.lang.Math.round(r4)
            int r0 = r0 - r2
            int r0 = r0 * r11
            r3.k0 = r0
            int r11 = r9.getMeasuredHeight()
            int r0 = r3.k0
            int r11 = r11 + r0
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r1)
            super.onMeasure(r10, r11)
            goto L80
        L7f:
            r3 = r9
        L80:
            android.view.ViewGroup r10 = r3.G
            if (r10 == 0) goto Lc4
            android.view.View r11 = r3.H
            if (r11 == 0) goto La8
            if (r11 != r3) goto L8b
            goto La8
        L8b:
            android.view.ViewGroup$LayoutParams r10 = r11.getLayoutParams()
            boolean r0 = r10 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r0 == 0) goto La0
            android.view.ViewGroup$MarginLayoutParams r10 = (android.view.ViewGroup.MarginLayoutParams) r10
            int r11 = r11.getMeasuredHeight()
            int r0 = r10.topMargin
            int r11 = r11 + r0
            int r10 = r10.bottomMargin
            int r11 = r11 + r10
            goto La4
        La0:
            int r11 = r11.getMeasuredHeight()
        La4:
            r9.setMinimumHeight(r11)
            return
        La8:
            android.view.ViewGroup$LayoutParams r11 = r10.getLayoutParams()
            boolean r0 = r11 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r0 == 0) goto Lbd
            android.view.ViewGroup$MarginLayoutParams r11 = (android.view.ViewGroup.MarginLayoutParams) r11
            int r10 = r10.getMeasuredHeight()
            int r0 = r11.topMargin
            int r10 = r10 + r0
            int r11 = r11.bottomMargin
            int r10 = r10 + r11
            goto Lc1
        Lbd:
            int r10 = r10.getMeasuredHeight()
        Lc1:
            r9.setMinimumHeight(r10)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.S;
        if (drawable != null) {
            ViewGroup viewGroup = this.G;
            if (this.g0 == 1 && viewGroup != null && this.Q) {
                i2 = viewGroup.getBottom();
            }
            drawable.setBounds(0, 0, i, i2);
        }
    }

    public void setCollapsedTitleGravity(int i) {
        this.O.l(i);
    }

    public void setCollapsedTitleTextAppearance(int i) {
        this.O.k(i);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        sf sfVar = this.O;
        if (sfVar.o != colorStateList) {
            sfVar.o = colorStateList;
            sfVar.i(false);
        }
    }

    public void setCollapsedTitleTextSize(float f) {
        sf sfVar = this.O;
        if (sfVar.m != f) {
            sfVar.m = f;
            sfVar.i(false);
        }
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        sf sfVar = this.O;
        if (sfVar.m(typeface)) {
            sfVar.i(false);
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.S;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.S = drawableMutate;
            if (drawableMutate != null) {
                int width = getWidth();
                int height = getHeight();
                ViewGroup viewGroup = this.G;
                if (this.g0 == 1 && viewGroup != null && this.Q) {
                    height = viewGroup.getBottom();
                }
                drawableMutate.setBounds(0, 0, width, height);
                this.S.setCallback(this);
                this.S.setAlpha(this.U);
            }
            WeakHashMap weakHashMap = f51.a;
            postInvalidateOnAnimation();
        }
    }

    public void setContentScrimColor(int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(int i) {
        setContentScrim(pi.b(getContext(), i));
    }

    public void setExpandedTitleColor(int i) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setExpandedTitleGravity(int i) {
        sf sfVar = this.O;
        if (sfVar.j != i) {
            sfVar.j = i;
            sfVar.i(false);
        }
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.M = i;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.L = i;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i) {
        this.J = i;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i) {
        this.K = i;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i) {
        this.O.n(i);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        sf sfVar = this.O;
        if (sfVar.n != colorStateList) {
            sfVar.n = colorStateList;
            sfVar.i(false);
        }
    }

    public void setExpandedTitleTextSize(float f) {
        sf sfVar = this.O;
        if (sfVar.l != f) {
            sfVar.l = f;
            sfVar.i(false);
        }
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        sf sfVar = this.O;
        if (sfVar.o(typeface)) {
            sfVar.i(false);
        }
    }

    public void setExtraMultilineHeightEnabled(boolean z) {
        this.l0 = z;
    }

    public void setForceApplySystemWindowInsetTop(boolean z) {
        this.j0 = z;
    }

    public void setHyphenationFrequency(int i) {
        this.O.q0 = i;
    }

    public void setLineSpacingAdd(float f) {
        this.O.o0 = f;
    }

    public void setLineSpacingMultiplier(float f) {
        this.O.p0 = f;
    }

    public void setMaxLines(int i) {
        sf sfVar = this.O;
        if (i != sfVar.n0) {
            sfVar.n0 = i;
            Bitmap bitmap = sfVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                sfVar.K = null;
            }
            sfVar.i(false);
        }
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.O.J = z;
    }

    public void setScrimAlpha(int i) {
        ViewGroup viewGroup;
        if (i != this.U) {
            if (this.S != null && (viewGroup = this.G) != null) {
                WeakHashMap weakHashMap = f51.a;
                viewGroup.postInvalidateOnAnimation();
            }
            this.U = i;
            WeakHashMap weakHashMap2 = f51.a;
            postInvalidateOnAnimation();
        }
    }

    public void setScrimAnimationDuration(long j) {
        this.a0 = j;
    }

    public void setScrimVisibleHeightTrigger(int i) {
        if (this.d0 != i) {
            this.d0 = i;
            d();
        }
    }

    public void setScrimsShown(boolean z) {
        WeakHashMap weakHashMap = f51.a;
        boolean z2 = isLaidOut() && !isInEditMode();
        if (this.V != z) {
            if (z2) {
                int i = z ? 255 : 0;
                a();
                ValueAnimator valueAnimator = this.W;
                if (valueAnimator == null) {
                    ValueAnimator valueAnimator2 = new ValueAnimator();
                    this.W = valueAnimator2;
                    valueAnimator2.setInterpolator(i > this.U ? this.b0 : this.c0);
                    this.W.addUpdateListener(new bb(1, this));
                } else if (valueAnimator.isRunning()) {
                    this.W.cancel();
                }
                this.W.setDuration(this.a0);
                this.W.setIntValues(this.U, i);
                this.W.start();
            } else {
                setScrimAlpha(z ? 255 : 0);
            }
            this.V = z;
        }
    }

    public void setStaticLayoutBuilderConfigurer(tf tfVar) {
        sf sfVar = this.O;
        if (tfVar != null) {
            sfVar.i(true);
        } else {
            sfVar.getClass();
        }
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.T;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.T = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.T.setState(getDrawableState());
                }
                Drawable drawable3 = this.T;
                WeakHashMap weakHashMap = f51.a;
                jp.b(drawable3, getLayoutDirection());
                this.T.setVisible(getVisibility() == 0, false);
                this.T.setCallback(this);
                this.T.setAlpha(this.U);
            }
            WeakHashMap weakHashMap2 = f51.a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarScrimColor(int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(int i) {
        setStatusBarScrim(pi.b(getContext(), i));
    }

    public void setTitle(CharSequence charSequence) {
        sf sfVar = this.O;
        if (charSequence == null || !TextUtils.equals(sfVar.G, charSequence)) {
            sfVar.G = charSequence;
            sfVar.H = null;
            Bitmap bitmap = sfVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                sfVar.K = null;
            }
            sfVar.i(false);
        }
        setContentDescription(getTitle());
    }

    public void setTitleCollapseMode(int i) {
        this.g0 = i;
        boolean z = i == 1;
        this.O.c = z;
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.g0 == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
        }
        if (z && this.S == null) {
            setContentScrimColor(getDefaultContentScrimColorForTitleCollapseFadeMode());
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt truncateAt) {
        sf sfVar = this.O;
        sfVar.F = truncateAt;
        sfVar.i(false);
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.Q) {
            this.Q = z;
            setContentDescription(getTitle());
            c();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator) {
        sf sfVar = this.O;
        sfVar.V = timeInterpolator;
        sfVar.i(false);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.T;
        if (drawable != null && drawable.isVisible() != z) {
            this.T.setVisible(z, false);
        }
        Drawable drawable2 = this.S;
        if (drawable2 == null || drawable2.isVisible() == z) {
            return;
        }
        this.S.setVisible(z, false);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.S || drawable == this.T;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.collapsingToolbarLayoutStyle);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        int i2;
        ColorStateList colorStateListP;
        ColorStateList colorStateListP2;
        TextUtils.TruncateAt truncateAt;
        int i3 = m0;
        super(pd0.a(context, attributeSet, i, i3), attributeSet, i);
        this.E = true;
        this.N = new Rect();
        this.d0 = -1;
        this.i0 = 0;
        this.k0 = 0;
        Context context2 = getContext();
        sf sfVar = new sf(this);
        this.O = sfVar;
        sfVar.W = y4.e;
        sfVar.i(false);
        sfVar.J = false;
        this.P = new ts(context2);
        int[] iArr = R$styleable.CollapsingToolbarLayout;
        i01.a(context2, attributeSet, i, i3);
        i01.b(context2, attributeSet, iArr, i, i3, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i3);
        int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691);
        if (sfVar.j != i4) {
            sfVar.j = i4;
            sfVar.i(false);
        }
        sfVar.l(typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.M = dimensionPixelSize;
        this.L = dimensionPixelSize;
        this.K = dimensionPixelSize;
        this.J = dimensionPixelSize;
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.Q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayObtainStyledAttributes.getText(R$styleable.CollapsingToolbarLayout_title));
        sfVar.n(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        sfVar.k(androidx.appcompat.R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            sfVar.n(typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            sfVar.k(typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_titleTextEllipsize)) {
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_titleTextEllipsize, -1);
            if (i5 == 0) {
                truncateAt = TextUtils.TruncateAt.START;
            } else if (i5 == 1) {
                truncateAt = TextUtils.TruncateAt.MIDDLE;
            } else if (i5 != 3) {
                truncateAt = TextUtils.TruncateAt.END;
            } else {
                truncateAt = TextUtils.TruncateAt.MARQUEE;
            }
            setTitleEllipsize(truncateAt);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleTextColor) && sfVar.n != (colorStateListP2 = la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.CollapsingToolbarLayout_expandedTitleTextColor))) {
            sfVar.n = colorStateListP2;
            sfVar.i(false);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_collapsedTitleTextColor) && sfVar.o != (colorStateListP = la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.CollapsingToolbarLayout_collapsedTitleTextColor))) {
            sfVar.o = colorStateListP;
            sfVar.i(false);
        }
        this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_maxLines) && (i2 = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_maxLines, 1)) != sfVar.n0) {
            sfVar.n0 = i2;
            Bitmap bitmap = sfVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                sfVar.K = null;
            }
            sfVar.i(false);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_titlePositionInterpolator)) {
            sfVar.V = AnimationUtils.loadInterpolator(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_titlePositionInterpolator, 0));
            sfVar.i(false);
        }
        this.a0 = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        this.b0 = s90.V(context2, R$attr.motionEasingStandardInterpolator, y4.c);
        this.c0 = s90.V(context2, R$attr.motionEasingStandardInterpolator, y4.d);
        setContentScrim(typedArrayObtainStyledAttributes.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayObtainStyledAttributes.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        setTitleCollapseMode(typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_titleCollapseMode, 0));
        this.F = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
        this.j0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_forceApplySystemWindowInsetTop, false);
        this.l0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_extraMultilineHeightEnabled, false);
        typedArrayObtainStyledAttributes.recycle();
        setWillNotDraw(false);
        p50 p50Var = new p50(13, this);
        WeakHashMap weakHashMap = f51.a;
        t41.u(this, p50Var);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2 = new LayoutParams(layoutParams);
        layoutParams2.a = 0;
        layoutParams2.b = 0.5f;
        return layoutParams2;
    }

    public void setCollapsedTitleTextColor(int i) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i));
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.a = 0;
        layoutParams.b = 0.5f;
        return layoutParams;
    }
}
