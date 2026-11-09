package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import defpackage.bk0;
import defpackage.ck0;
import defpackage.u51;
import defpackage.x51;

/* JADX INFO: loaded from: classes.dex */
@u51
public class PagerTitleStrip extends ViewGroup {
    public static final int[] R = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};
    public static final int[] S = {R.attr.textAllCaps};
    public ViewPager E;
    public final TextView F;
    public final TextView G;
    public final TextView H;
    public int I;
    public float J;
    public int K;
    public int L;
    public boolean M;
    public boolean N;
    public final bk0 O;
    public int P;
    public int Q;

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        Context context = textView.getContext();
        ck0 ck0Var = new ck0();
        ck0Var.a = context.getResources().getConfiguration().locale;
        textView.setTransformationMethod(ck0Var);
    }

    public final void a() {
        ViewPager viewPager = this.E;
        if (viewPager != null) {
            this.I = -1;
            this.J = -1.0f;
            b(viewPager.getCurrentItem());
            requestLayout();
        }
    }

    public final void b(int i) {
        this.M = true;
        TextView textView = this.F;
        textView.setText((CharSequence) null);
        TextView textView2 = this.G;
        textView2.setText((CharSequence) null);
        TextView textView3 = this.H;
        textView3.setText((CharSequence) null);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        textView.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        textView2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        textView3.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.I = i;
        if (!this.N) {
            c(this.J, i, false);
        }
        this.M = false;
    }

    public void c(float f, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.I) {
            this.E.getAdapter();
            b(i);
        } else if (!z && f == this.J) {
            return;
        }
        this.N = true;
        TextView textView = this.F;
        int measuredWidth = textView.getMeasuredWidth();
        TextView textView2 = this.G;
        int measuredWidth2 = textView2.getMeasuredWidth();
        TextView textView3 = this.H;
        int measuredWidth3 = textView3.getMeasuredWidth();
        int i6 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f2 = f + 0.5f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (i8 * f2))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = textView.getBaseline();
        int baseline2 = textView2.getBaseline();
        int baseline3 = textView3.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = iMax - baseline;
        int i12 = iMax - baseline2;
        int i13 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(textView.getMeasuredHeight() + i11, textView2.getMeasuredHeight() + i12), textView3.getMeasuredHeight() + i13);
        int i14 = this.L & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i14 != 80) {
                i3 = i11 + paddingTop;
                i4 = paddingTop + i12;
                i5 = paddingTop + i13;
                textView2.layout(i9, i4, i10, textView2.getMeasuredHeight() + i4);
                int iMin = Math.min(paddingLeft, (i9 - this.K) - measuredWidth);
                textView.layout(iMin, i3, iMin + measuredWidth, textView.getMeasuredHeight() + i3);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.K);
                textView3.layout(iMax3, i5, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
                this.J = f;
                this.N = false;
            }
            i2 = (height - paddingBottom) - iMax2;
        }
        i3 = i11 + i2;
        i4 = i2 + i12;
        i5 = i2 + i13;
        textView2.layout(i9, i4, i10, textView2.getMeasuredHeight() + i4);
        int iMin2 = Math.min(paddingLeft, (i9 - this.K) - measuredWidth);
        textView.layout(iMin2, i3, iMin2 + measuredWidth, textView.getMeasuredHeight() + i3);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.K);
        textView3.layout(iMax32, i5, iMax32 + measuredWidth3, textView3.getMeasuredHeight() + i5);
        this.J = f;
        this.N = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.K;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        viewPager.getAdapter();
        bk0 bk0Var = this.O;
        viewPager.m0 = bk0Var;
        viewPager.addOnAdapterChangeListener(bk0Var);
        this.E = viewPager;
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.E;
        if (viewPager != null) {
            viewPager.getAdapter();
            a();
            ViewPager viewPager2 = this.E;
            x51 x51Var = viewPager2.m0;
            viewPager2.m0 = null;
            viewPager2.removeOnAdapterChangeListener(this.O);
            this.E = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.E != null) {
            float f = this.J;
            if (f < 0.0f) {
                f = 0.0f;
            }
            c(f, this.I, true);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iMax;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int size = View.MeasureSpec.getSize(i);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, (int) (size * 0.2f), -2);
        this.F.measure(childMeasureSpec2, childMeasureSpec);
        TextView textView = this.G;
        textView.measure(childMeasureSpec2, childMeasureSpec);
        this.H.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i2);
        } else {
            iMax = Math.max(getMinHeight(), textView.getMeasuredHeight() + paddingBottom);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i2, textView.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.M) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.L = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f) {
        int i = ((int) (f * 255.0f)) & 255;
        this.P = i;
        int i2 = (i << 24) | (this.Q & 16777215);
        this.F.setTextColor(i2);
        this.H.setTextColor(i2);
    }

    public void setTextColor(int i) {
        this.Q = i;
        this.G.setTextColor(i);
        int i2 = (this.P << 24) | (this.Q & 16777215);
        this.F.setTextColor(i2);
        this.H.setTextColor(i2);
    }

    public void setTextSpacing(int i) {
        this.K = i;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.I = -1;
        this.J = -1.0f;
        this.O = new bk0(this);
        TextView textView = new TextView(context);
        this.F = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.G = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.H = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            textView.setTextAppearance(resourceId);
            textView2.setTextAppearance(resourceId);
            textView3.setTextAppearance(resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            float f = dimensionPixelSize;
            textView.setTextSize(0, f);
            textView2.setTextSize(0, f);
            textView3.setTextSize(0, f);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            textView.setTextColor(color);
            textView2.setTextColor(color);
            textView3.setTextColor(color);
        }
        this.L = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.Q = textView2.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView.setEllipsize(truncateAt);
        textView2.setEllipsize(truncateAt);
        textView3.setEllipsize(truncateAt);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, S);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(textView);
            setSingleLineAllCaps(textView2);
            setSingleLineAllCaps(textView3);
        } else {
            textView.setSingleLine();
            textView2.setSingleLine();
            textView3.setSingleLine();
        }
        this.K = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
