package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R$styleable;
import defpackage.f51;
import defpackage.ou;
import defpackage.q61;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public boolean E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public float K;
    public boolean L;
    public int[] M;
    public int[] N;
    public Drawable O;
    public int P;
    public int Q;
    public int R;
    public int S;

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void f(Canvas canvas, int i) {
        this.O.setBounds(getPaddingLeft() + this.S, i, (getWidth() - getPaddingRight()) - this.S, this.Q + i);
        this.O.draw(canvas);
    }

    public final void g(Canvas canvas, int i) {
        this.O.setBounds(i, getPaddingTop() + this.S, this.P + i, (getHeight() - getPaddingBottom()) - this.S);
        this.O.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.F < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.F;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.F == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.G;
        if (this.H == 1 && (i = this.I & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.J) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.J;
            }
        }
        return bottom + ((LinearLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.F;
    }

    public Drawable getDividerDrawable() {
        return this.O;
    }

    public int getDividerPadding() {
        return this.S;
    }

    public int getDividerWidth() {
        return this.P;
    }

    public int getGravity() {
        return this.I;
    }

    public int getOrientation() {
        return this.H;
    }

    public int getShowDividers() {
        return this.R;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.K;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.H;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public final boolean k(int i) {
        if (i == 0) {
            return (this.R & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.R & 4) != 0;
        }
        if ((this.R & 2) != 0) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i;
        if (this.O == null) {
            return;
        }
        int i2 = 0;
        if (this.H == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i2 < virtualChildCount) {
                View childAt = getChildAt(i2);
                if (childAt != null && childAt.getVisibility() != 8 && k(i2)) {
                    f(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin) - this.Q);
                }
                i2++;
            }
            if (k(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                f(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.Q : childAt2.getBottom() + ((LinearLayout.LayoutParams) ((LayoutParams) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean zA = q61.a(this);
        while (i2 < virtualChildCount2) {
            View childAt3 = getChildAt(i2);
            if (childAt3 != null && childAt3.getVisibility() != 8 && k(i2)) {
                LayoutParams layoutParams = (LayoutParams) childAt3.getLayoutParams();
                g(canvas, zA ? childAt3.getRight() + ((LinearLayout.LayoutParams) layoutParams).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) layoutParams).leftMargin) - this.P);
            }
            i2++;
        }
        if (k(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                LayoutParams layoutParams2 = (LayoutParams) childAt4.getLayoutParams();
                if (zA) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) layoutParams2).leftMargin;
                    i = this.P;
                    right = left - i;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.P;
                right = left - i;
            }
            g(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a5  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:228:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x079d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0148  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 2151
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z) {
        this.E = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.F = i;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.O) {
            return;
        }
        this.O = drawable;
        if (drawable != null) {
            this.P = drawable.getIntrinsicWidth();
            this.Q = drawable.getIntrinsicHeight();
        } else {
            this.P = 0;
            this.Q = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.S = i;
    }

    public void setGravity(int i) {
        if (this.I != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.I = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.I;
        if ((8388615 & i3) != i2) {
            this.I = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.L = z;
    }

    public void setOrientation(int i) {
        if (this.H != i) {
            this.H = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.R) {
            requestLayout();
        }
        this.R = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.I;
        if ((i3 & 112) != i2) {
            this.I = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.K = Math.max(0.0f, f);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = true;
        this.F = -1;
        this.G = 0;
        this.I = 8388659;
        ou ouVarP = ou.P(context, attributeSet, R$styleable.LinearLayoutCompat, i);
        f51.n(this, context, R$styleable.LinearLayoutCompat, attributeSet, (TypedArray) ouVarP.G, i);
        int i2 = R$styleable.LinearLayoutCompat_android_orientation;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        int i3 = typedArray.getInt(i2, -1);
        if (i3 >= 0) {
            setOrientation(i3);
        }
        int i4 = typedArray.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (i4 >= 0) {
            setGravity(i4);
        }
        boolean z = typedArray.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.K = typedArray.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.F = typedArray.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.L = typedArray.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(ouVarP.A(R$styleable.LinearLayoutCompat_divider));
        this.R = typedArray.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.S = typedArray.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        ouVarP.S();
    }
}
