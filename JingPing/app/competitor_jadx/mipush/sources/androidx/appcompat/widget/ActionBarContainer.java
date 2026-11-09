package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import defpackage.f51;
import defpackage.x2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {
    public boolean E;
    public ScrollingTabContainerView F;
    public View G;
    public View H;
    public Drawable I;
    public Drawable J;
    public Drawable K;
    public final boolean L;
    public boolean M;
    public final int N;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public static int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.I;
        if (drawable != null && drawable.isStateful()) {
            this.I.setState(getDrawableState());
        }
        Drawable drawable2 = this.J;
        if (drawable2 != null && drawable2.isStateful()) {
            this.J.setState(getDrawableState());
        }
        Drawable drawable3 = this.K;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.K.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.I;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.J;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.K;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.G = findViewById(R$id.action_bar);
        this.H = findViewById(R$id.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.E || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049 A[PHI: r1
      0x0049: PHI (r1v8 boolean) = (r1v1 boolean), (r1v1 boolean), (r1v0 boolean) binds: [B:31:0x00a6, B:33:0x00aa, B:15:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        /*
            r5 = this;
            super.onLayout(r6, r7, r8, r9, r10)
            r6 = r5
            androidx.appcompat.widget.ScrollingTabContainerView r8 = r6.F
            r10 = 8
            r0 = 1
            r1 = 0
            if (r8 == 0) goto L14
            int r2 = r8.getVisibility()
            if (r2 == r10) goto L14
            r2 = r0
            goto L15
        L14:
            r2 = r1
        L15:
            if (r8 == 0) goto L34
            int r3 = r8.getVisibility()
            if (r3 == r10) goto L34
            int r10 = r5.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r3 = r8.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r3 = (android.widget.FrameLayout.LayoutParams) r3
            int r4 = r8.getMeasuredHeight()
            int r4 = r10 - r4
            int r3 = r3.bottomMargin
            int r4 = r4 - r3
            int r10 = r10 - r3
            r8.layout(r7, r4, r9, r10)
        L34:
            boolean r7 = r6.L
            if (r7 == 0) goto L4c
            android.graphics.drawable.Drawable r7 = r6.K
            if (r7 == 0) goto L49
            int r8 = r5.getMeasuredWidth()
            int r9 = r5.getMeasuredHeight()
            r7.setBounds(r1, r1, r8, r9)
            goto Lbf
        L49:
            r0 = r1
            goto Lbf
        L4c:
            android.graphics.drawable.Drawable r7 = r6.I
            if (r7 == 0) goto La4
            android.view.View r7 = r6.G
            int r7 = r7.getVisibility()
            if (r7 != 0) goto L76
            android.graphics.drawable.Drawable r7 = r6.I
            android.view.View r9 = r6.G
            int r9 = r9.getLeft()
            android.view.View r10 = r6.G
            int r10 = r10.getTop()
            android.view.View r1 = r6.G
            int r1 = r1.getRight()
            android.view.View r3 = r6.G
            int r3 = r3.getBottom()
            r7.setBounds(r9, r10, r1, r3)
            goto La3
        L76:
            android.view.View r7 = r6.H
            if (r7 == 0) goto L9e
            int r7 = r7.getVisibility()
            if (r7 != 0) goto L9e
            android.graphics.drawable.Drawable r7 = r6.I
            android.view.View r9 = r6.H
            int r9 = r9.getLeft()
            android.view.View r10 = r6.H
            int r10 = r10.getTop()
            android.view.View r1 = r6.H
            int r1 = r1.getRight()
            android.view.View r3 = r6.H
            int r3 = r3.getBottom()
            r7.setBounds(r9, r10, r1, r3)
            goto La3
        L9e:
            android.graphics.drawable.Drawable r7 = r6.I
            r7.setBounds(r1, r1, r1, r1)
        La3:
            r1 = r0
        La4:
            r6.M = r2
            if (r2 == 0) goto L49
            android.graphics.drawable.Drawable r7 = r6.J
            if (r7 == 0) goto L49
            int r9 = r8.getLeft()
            int r10 = r8.getTop()
            int r1 = r8.getRight()
            int r8 = r8.getBottom()
            r7.setBounds(r9, r10, r1, r8)
        Lbf:
            if (r0 == 0) goto Lc4
            r5.invalidate()
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int iA;
        int i3;
        if (this.G == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.N) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.G == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        ScrollingTabContainerView scrollingTabContainerView = this.F;
        if (scrollingTabContainerView == null || scrollingTabContainerView.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        View view = this.G;
        if (view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0) {
            View view2 = this.H;
            iA = (view2 == null || view2.getVisibility() == 8 || view2.getMeasuredHeight() == 0) ? 0 : a(this.H);
        } else {
            iA = a(this.G);
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min(a(this.F) + iA, mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.I;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.I);
        }
        this.I = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.G;
            if (view != null) {
                this.I.setBounds(view.getLeft(), this.G.getTop(), this.G.getRight(), this.G.getBottom());
            }
        }
        boolean z = false;
        if (!this.L ? !(this.I != null || this.J != null) : this.K == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.K;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.K);
        }
        this.K = drawable;
        boolean z = this.L;
        boolean z2 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z && (drawable2 = this.K) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z ? !(this.I != null || this.J != null) : this.K == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.J;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.J);
        }
        this.J = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.M && (drawable2 = this.J) != null) {
                drawable2.setBounds(this.F.getLeft(), this.F.getTop(), this.F.getRight(), this.F.getBottom());
            }
        }
        boolean z = false;
        if (!this.L ? !(this.I != null || this.J != null) : this.K == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        ScrollingTabContainerView scrollingTabContainerView2 = this.F;
        if (scrollingTabContainerView2 != null) {
            removeView(scrollingTabContainerView2);
        }
        this.F = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.E = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.I;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.J;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.K;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.I;
        boolean z = this.L;
        if (drawable == drawable2 && !z) {
            return true;
        }
        if (drawable == this.J && this.M) {
            return true;
        }
        return (drawable == this.K && z) || super.verifyDrawable(drawable);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        x2 x2Var = new x2(this);
        WeakHashMap weakHashMap = f51.a;
        setBackground(x2Var);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBar);
        this.I = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_background);
        this.J = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_backgroundStacked);
        this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_height, -1);
        boolean z = true;
        if (getId() == R$id.split_action_bar) {
            this.L = true;
            this.K = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.L ? this.I != null || this.J != null : this.K != null) {
            z = false;
        }
        setWillNotDraw(z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }
}
