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
import defpackage.ah;
import defpackage.b4;
import defpackage.bn;
import defpackage.d9;
import defpackage.il;
import defpackage.jk;
import defpackage.jr0;
import defpackage.k9;
import defpackage.lr0;
import defpackage.ms0;
import defpackage.o70;
import defpackage.t3;
import defpackage.un0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.xu0;
import defpackage.y50;
import defpackage.yd;
import defpackage.zd;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    public static final int G = R$style.Widget_Design_CollapsingToolbar;
    public int A;
    public xu0 B;
    public int C;
    public boolean D;
    public int E;
    public boolean F;
    public boolean b;
    public final int c;
    public ViewGroup d;
    public View e;
    public View f;
    public int g;
    public int h;
    public int i;
    public int j;
    public final Rect k;
    public final yd l;
    public final bn m;
    public boolean n;
    public boolean o;
    public Drawable p;
    public Drawable q;
    public int r;
    public boolean s;
    public ValueAnimator t;
    public long u;
    public final TimeInterpolator v;
    public final TimeInterpolator w;
    public int x;
    public f y;
    public int z;

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

    public static ms0 b(View view) {
        ms0 ms0Var = (ms0) view.getTag(R$id.view_offset_helper);
        if (ms0Var != null) {
            return ms0Var;
        }
        ms0 ms0Var2 = new ms0(view);
        view.setTag(R$id.view_offset_helper, ms0Var2);
        return ms0Var2;
    }

    public final void a() {
        if (this.b) {
            ViewGroup viewGroup = null;
            this.d = null;
            this.e = null;
            int i = this.c;
            if (i != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i);
                this.d = viewGroup2;
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
                    this.e = view;
                }
            }
            if (this.d == null) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    if ((childAt instanceof Toolbar) || (childAt instanceof android.widget.Toolbar)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                }
                this.d = viewGroup;
            }
            c();
            this.b = false;
        }
    }

    public final void c() {
        View view;
        if (!this.n && (view = this.f) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f);
            }
        }
        if (!this.n || this.d == null) {
            return;
        }
        if (this.f == null) {
            this.f = new View(getContext());
        }
        if (this.f.getParent() == null) {
            this.d.addView(this.f, -1, -1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d() {
        if (this.p == null && this.q == null) {
            return;
        }
        setScrimsShown(getHeight() + this.z < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        a();
        if (this.d == null && (drawable = this.p) != null && this.r > 0) {
            drawable.mutate().setAlpha(this.r);
            this.p.draw(canvas);
        }
        if (this.n && this.o) {
            ViewGroup viewGroup = this.d;
            yd ydVar = this.l;
            if (viewGroup == null || this.p == null || this.r <= 0 || this.A != 1 || ydVar.b >= ydVar.e) {
                ydVar.d(canvas);
            } else {
                int iSave = canvas.save();
                canvas.clipRect(this.p.getBounds(), Region.Op.DIFFERENCE);
                ydVar.d(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        if (this.q == null || this.r <= 0) {
            return;
        }
        xu0 xu0Var = this.B;
        int iD = xu0Var != null ? xu0Var.d() : 0;
        if (iD > 0) {
            this.q.setBounds(0, -this.z, getWidth(), iD - this.z);
            this.q.mutate().setAlpha(this.r);
            this.q.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        View view2;
        Drawable drawable = this.p;
        if (drawable == null || this.r <= 0 || ((view2 = this.e) == null || view2 == this ? view != this.d : view != view2)) {
            z = false;
        } else {
            int width = getWidth();
            int height = getHeight();
            if (this.A == 1 && view != null && this.n) {
                height = view.getBottom();
            }
            drawable.setBounds(0, 0, width, height);
            this.p.mutate().setAlpha(this.r);
            this.p.draw(canvas);
            z = true;
        }
        return super.drawChild(canvas, view, j) || z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        ColorStateList colorStateList;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.q;
        boolean z = false;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.p;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        yd ydVar = this.l;
        if (ydVar != null) {
            ydVar.R = drawableState;
            ColorStateList colorStateList2 = ydVar.o;
            if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = ydVar.n) != null && colorStateList.isStateful())) {
                ydVar.i(false);
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
        if (!this.n || (view = this.f) == null) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        int titleMarginStart = 0;
        boolean z2 = view.isAttachedToWindow() && this.f.getVisibility() == 0;
        this.o = z2;
        if (z2 || z) {
            boolean z3 = getLayoutDirection() == 1;
            View view2 = this.e;
            if (view2 == null) {
                view2 = this.d;
            }
            int height = ((getHeight() - b(view2).b) - view2.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view2.getLayoutParams())).bottomMargin;
            View view3 = this.f;
            Rect rect = this.k;
            jk.a(this, view3, rect);
            ViewGroup viewGroup = this.d;
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
            yd ydVar = this.l;
            Rect rect2 = ydVar.h;
            if (rect2.left != i5 || rect2.top != i6 || rect2.right != i8 || rect2.bottom != i9) {
                rect2.set(i5, i6, i8, i9);
                ydVar.S = true;
            }
            int i10 = z3 ? this.i : this.g;
            int i11 = rect.top + this.h;
            int i12 = (i3 - i) - (z3 ? this.g : this.i);
            int i13 = (i4 - i2) - this.j;
            Rect rect3 = ydVar.g;
            if (rect3.left != i10 || rect3.top != i11 || rect3.right != i12 || rect3.bottom != i13) {
                rect3.set(i10, i11, i12, i13);
                ydVar.S = true;
            }
            ydVar.i(z);
        }
    }

    public final void f() {
        if (this.d != null && this.n && TextUtils.isEmpty(this.l.G)) {
            ViewGroup viewGroup = this.d;
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
        return this.l.k;
    }

    public float getCollapsedTitleTextSize() {
        return this.l.m;
    }

    public Typeface getCollapsedTitleTypeface() {
        Typeface typeface = this.l.w;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public Drawable getContentScrim() {
        return this.p;
    }

    public int getExpandedTitleGravity() {
        return this.l.j;
    }

    public int getExpandedTitleMarginBottom() {
        return this.j;
    }

    public int getExpandedTitleMarginEnd() {
        return this.i;
    }

    public int getExpandedTitleMarginStart() {
        return this.g;
    }

    public int getExpandedTitleMarginTop() {
        return this.h;
    }

    public float getExpandedTitleTextSize() {
        return this.l.l;
    }

    public Typeface getExpandedTitleTypeface() {
        Typeface typeface = this.l.z;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int getHyphenationFrequency() {
        return this.l.q0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.l.i0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    public float getLineSpacingAdd() {
        return this.l.i0.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.l.i0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.l.n0;
    }

    public int getScrimAlpha() {
        return this.r;
    }

    public long getScrimAnimationDuration() {
        return this.u;
    }

    public int getScrimVisibleHeightTrigger() {
        int i = this.x;
        if (i >= 0) {
            return i + this.C + this.E;
        }
        xu0 xu0Var = this.B;
        int iD = xu0Var != null ? xu0Var.d() : 0;
        WeakHashMap weakHashMap = xr0.a;
        int minimumHeight = getMinimumHeight();
        return minimumHeight > 0 ? Math.min((minimumHeight * 2) + iD, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.q;
    }

    public CharSequence getTitle() {
        if (this.n) {
            return this.l.G;
        }
        return null;
    }

    public int getTitleCollapseMode() {
        return this.A;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.l.V;
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.l.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.A == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
            WeakHashMap weakHashMap = xr0.a;
            setFitsSystemWindows(appBarLayout.getFitsSystemWindows());
            if (this.y == null) {
                this.y = new f(this);
            }
            appBarLayout.addOnOffsetChangedListener((b4) this.y);
            jr0.c(this);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.l.h(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ViewParent parent = getParent();
        f fVar = this.y;
        if (fVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener((b4) fVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        xu0 xu0Var = this.B;
        if (xu0Var != null) {
            int iD = xu0Var.d();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                WeakHashMap weakHashMap = xr0.a;
                if (!childAt.getFitsSystemWindows() && childAt.getTop() < iD) {
                    childAt.offsetTopAndBottom(iD);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            ms0 ms0VarB = b(getChildAt(i6));
            View view = ms0VarB.a;
            ms0VarB.b = view.getTop();
            ms0VarB.c = view.getLeft();
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            r9.a()
            super.onMeasure(r10, r11)
            int r11 = android.view.View.MeasureSpec.getMode(r11)
            xu0 r0 = r9.B
            if (r0 == 0) goto L13
            int r0 = r0.d()
            goto L14
        L13:
            r0 = 0
        L14:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r11 == 0) goto L1c
            boolean r11 = r9.D
            if (r11 == 0) goto L2c
        L1c:
            if (r0 <= 0) goto L2c
            r9.C = r0
            int r11 = r9.getMeasuredHeight()
            int r11 = r11 + r0
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r1)
            super.onMeasure(r10, r11)
        L2c:
            boolean r11 = r9.F
            if (r11 == 0) goto L7f
            yd r11 = r9.l
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
            r3.E = r0
            int r11 = r9.getMeasuredHeight()
            int r0 = r3.E
            int r11 = r11 + r0
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r1)
            super.onMeasure(r10, r11)
            goto L80
        L7f:
            r3 = r9
        L80:
            android.view.ViewGroup r10 = r3.d
            if (r10 == 0) goto Lc4
            android.view.View r11 = r3.e
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
        Drawable drawable = this.p;
        if (drawable != null) {
            ViewGroup viewGroup = this.d;
            if (this.A == 1 && viewGroup != null && this.n) {
                i2 = viewGroup.getBottom();
            }
            drawable.setBounds(0, 0, i, i2);
        }
    }

    public void setCollapsedTitleGravity(int i) {
        this.l.l(i);
    }

    public void setCollapsedTitleTextAppearance(int i) {
        this.l.k(i);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        yd ydVar = this.l;
        if (ydVar.o != colorStateList) {
            ydVar.o = colorStateList;
            ydVar.i(false);
        }
    }

    public void setCollapsedTitleTextSize(float f) {
        yd ydVar = this.l;
        if (ydVar.m != f) {
            ydVar.m = f;
            ydVar.i(false);
        }
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        yd ydVar = this.l;
        if (ydVar.m(typeface)) {
            ydVar.i(false);
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.p;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.p = drawableMutate;
            if (drawableMutate != null) {
                int width = getWidth();
                int height = getHeight();
                ViewGroup viewGroup = this.d;
                if (this.A == 1 && viewGroup != null && this.n) {
                    height = viewGroup.getBottom();
                }
                drawableMutate.setBounds(0, 0, width, height);
                this.p.setCallback(this);
                this.p.setAlpha(this.r);
            }
            WeakHashMap weakHashMap = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public void setContentScrimColor(int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(int i) {
        setContentScrim(ah.b(getContext(), i));
    }

    public void setExpandedTitleColor(int i) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setExpandedTitleGravity(int i) {
        yd ydVar = this.l;
        if (ydVar.j != i) {
            ydVar.j = i;
            ydVar.i(false);
        }
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.j = i;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.i = i;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i) {
        this.g = i;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i) {
        this.h = i;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i) {
        this.l.n(i);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        yd ydVar = this.l;
        if (ydVar.n != colorStateList) {
            ydVar.n = colorStateList;
            ydVar.i(false);
        }
    }

    public void setExpandedTitleTextSize(float f) {
        yd ydVar = this.l;
        if (ydVar.l != f) {
            ydVar.l = f;
            ydVar.i(false);
        }
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        yd ydVar = this.l;
        if (ydVar.o(typeface)) {
            ydVar.i(false);
        }
    }

    public void setExtraMultilineHeightEnabled(boolean z) {
        this.F = z;
    }

    public void setForceApplySystemWindowInsetTop(boolean z) {
        this.D = z;
    }

    public void setHyphenationFrequency(int i) {
        this.l.q0 = i;
    }

    public void setLineSpacingAdd(float f) {
        this.l.o0 = f;
    }

    public void setLineSpacingMultiplier(float f) {
        this.l.p0 = f;
    }

    public void setMaxLines(int i) {
        yd ydVar = this.l;
        if (i != ydVar.n0) {
            ydVar.n0 = i;
            Bitmap bitmap = ydVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ydVar.K = null;
            }
            ydVar.i(false);
        }
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.l.J = z;
    }

    public void setScrimAlpha(int i) {
        ViewGroup viewGroup;
        if (i != this.r) {
            if (this.p != null && (viewGroup = this.d) != null) {
                WeakHashMap weakHashMap = xr0.a;
                viewGroup.postInvalidateOnAnimation();
            }
            this.r = i;
            WeakHashMap weakHashMap2 = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public void setScrimAnimationDuration(long j) {
        this.u = j;
    }

    public void setScrimVisibleHeightTrigger(int i) {
        if (this.x != i) {
            this.x = i;
            d();
        }
    }

    public void setScrimsShown(boolean z) {
        WeakHashMap weakHashMap = xr0.a;
        boolean z2 = isLaidOut() && !isInEditMode();
        if (this.s != z) {
            if (z2) {
                int i = z ? 255 : 0;
                a();
                ValueAnimator valueAnimator = this.t;
                if (valueAnimator == null) {
                    ValueAnimator valueAnimator2 = new ValueAnimator();
                    this.t = valueAnimator2;
                    valueAnimator2.setInterpolator(i > this.r ? this.v : this.w);
                    this.t.addUpdateListener(new d9(1, this));
                } else if (valueAnimator.isRunning()) {
                    this.t.cancel();
                }
                this.t.setDuration(this.u);
                this.t.setIntValues(this.r, i);
                this.t.start();
            } else {
                setScrimAlpha(z ? 255 : 0);
            }
            this.s = z;
        }
    }

    public void setStaticLayoutBuilderConfigurer(zd zdVar) {
        yd ydVar = this.l;
        if (zdVar != null) {
            ydVar.i(true);
        } else {
            ydVar.getClass();
        }
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.q;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.q = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.q.setState(getDrawableState());
                }
                Drawable drawable3 = this.q;
                WeakHashMap weakHashMap = xr0.a;
                il.b(drawable3, getLayoutDirection());
                this.q.setVisible(getVisibility() == 0, false);
                this.q.setCallback(this);
                this.q.setAlpha(this.r);
            }
            WeakHashMap weakHashMap2 = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarScrimColor(int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(int i) {
        setStatusBarScrim(ah.b(getContext(), i));
    }

    public void setTitle(CharSequence charSequence) {
        yd ydVar = this.l;
        if (charSequence == null || !TextUtils.equals(ydVar.G, charSequence)) {
            ydVar.G = charSequence;
            ydVar.H = null;
            Bitmap bitmap = ydVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ydVar.K = null;
            }
            ydVar.i(false);
        }
        setContentDescription(getTitle());
    }

    public void setTitleCollapseMode(int i) {
        this.A = i;
        boolean z = i == 1;
        this.l.c = z;
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.A == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
        }
        if (z && this.p == null) {
            float dimension = getResources().getDimension(R$dimen.design_appbar_elevation);
            bn bnVar = this.m;
            setContentScrimColor(bnVar.b(bnVar.d, dimension));
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt truncateAt) {
        yd ydVar = this.l;
        ydVar.F = truncateAt;
        ydVar.i(false);
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.n) {
            this.n = z;
            setContentDescription(getTitle());
            c();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator) {
        yd ydVar = this.l;
        ydVar.V = timeInterpolator;
        ydVar.i(false);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.q;
        if (drawable != null && drawable.isVisible() != z) {
            this.q.setVisible(z, false);
        }
        Drawable drawable2 = this.p;
        if (drawable2 == null || drawable2.isVisible() == z) {
            return;
        }
        this.p.setVisible(z, false);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.p || drawable == this.q;
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
        ColorStateList colorStateListS;
        ColorStateList colorStateListS2;
        TextUtils.TruncateAt truncateAt;
        int i3 = G;
        super(y50.a(context, attributeSet, i, i3), attributeSet, i);
        this.b = true;
        this.k = new Rect();
        this.x = -1;
        this.C = 0;
        this.E = 0;
        Context context2 = getContext();
        yd ydVar = new yd(this);
        this.l = ydVar;
        ydVar.W = t3.e;
        ydVar.i(false);
        ydVar.J = false;
        this.m = new bn(context2);
        int[] iArr = R$styleable.CollapsingToolbarLayout;
        un0.a(context2, attributeSet, i, i3);
        un0.b(context2, attributeSet, iArr, i, i3, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i3);
        int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691);
        if (ydVar.j != i4) {
            ydVar.j = i4;
            ydVar.i(false);
        }
        ydVar.l(typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.j = dimensionPixelSize;
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.g = dimensionPixelSize;
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.n = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayObtainStyledAttributes.getText(R$styleable.CollapsingToolbarLayout_title));
        ydVar.n(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        ydVar.k(androidx.appcompat.R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            ydVar.n(typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            ydVar.k(typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
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
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleTextColor) && ydVar.n != (colorStateListS2 = xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.CollapsingToolbarLayout_expandedTitleTextColor))) {
            ydVar.n = colorStateListS2;
            ydVar.i(false);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_collapsedTitleTextColor) && ydVar.o != (colorStateListS = xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.CollapsingToolbarLayout_collapsedTitleTextColor))) {
            ydVar.o = colorStateListS;
            ydVar.i(false);
        }
        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_maxLines) && (i2 = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_maxLines, 1)) != ydVar.n0) {
            ydVar.n0 = i2;
            Bitmap bitmap = ydVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ydVar.K = null;
            }
            ydVar.i(false);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_titlePositionInterpolator)) {
            ydVar.V = AnimationUtils.loadInterpolator(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_titlePositionInterpolator, 0));
            ydVar.i(false);
        }
        this.u = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        this.v = k9.F(context2, R$attr.motionEasingStandardInterpolator, t3.c);
        this.w = k9.F(context2, R$attr.motionEasingStandardInterpolator, t3.d);
        setContentScrim(typedArrayObtainStyledAttributes.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayObtainStyledAttributes.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        setTitleCollapseMode(typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_titleCollapseMode, 0));
        this.c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
        this.D = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_forceApplySystemWindowInsetTop, false);
        this.F = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_extraMultilineHeightEnabled, false);
        typedArrayObtainStyledAttributes.recycle();
        setWillNotDraw(false);
        o70 o70Var = new o70(11, this);
        WeakHashMap weakHashMap = xr0.a;
        lr0.u(this, o70Var);
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
