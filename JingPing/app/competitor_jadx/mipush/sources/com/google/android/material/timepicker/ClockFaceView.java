package com.google.android.material.timepicker;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.f51;
import defpackage.la0;
import defpackage.p50;
import defpackage.qf;
import defpackage.t40;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends RadialViewGroup implements qf {
    public final ClockHandView a0;
    public final Rect b0;
    public final RectF c0;
    public final Rect d0;
    public final SparseArray e0;
    public final c f0;
    public final int[] g0;
    public final float[] h0;
    public final int i0;
    public final int j0;
    public final int k0;
    public final int l0;
    public final String[] m0;
    public float n0;
    public final ColorStateList o0;

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public final void o() {
        super.o();
        int i = 0;
        while (true) {
            SparseArray sparseArray = this.e0;
            if (i >= sparseArray.size()) {
                return;
            }
            ((TextView) sparseArray.get(i)).setVisibility(0);
            i++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) p50.B(1, this.m0.length, 1).F);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        p();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (this.l0 / Math.max(Math.max(this.j0 / displayMetrics.heightPixels, this.k0 / displayMetrics.widthPixels), 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, 1073741824);
        setMeasuredDimension(iMax, iMax);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }

    public final void p() {
        SparseArray sparseArray;
        RectF rectF;
        Rect rect;
        RectF rectF2 = this.a0.K;
        float f = Float.MAX_VALUE;
        TextView textView = null;
        int i = 0;
        while (true) {
            sparseArray = this.e0;
            int size = sparseArray.size();
            rectF = this.c0;
            rect = this.b0;
            if (i >= size) {
                break;
            }
            TextView textView2 = (TextView) sparseArray.get(i);
            if (textView2 != null) {
                textView2.getHitRect(rect);
                rectF.set(rect);
                rectF.union(rectF2);
                float fHeight = rectF.height() * rectF.width();
                if (fHeight < f) {
                    textView = textView2;
                    f = fHeight;
                }
            }
            i++;
        }
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            TextView textView3 = (TextView) sparseArray.get(i2);
            if (textView3 != null) {
                textView3.setSelected(textView3 == textView);
                textView3.getHitRect(rect);
                rectF.set(rect);
                textView3.getLineBounds(0, this.d0);
                rectF.inset(r8.left, r8.top);
                textView3.getPaint().setShader(!RectF.intersects(rectF2, rectF) ? null : new RadialGradient(rectF2.centerX() - rectF.left, rectF2.centerY() - rectF.top, 0.5f * rectF2.width(), this.g0, this.h0, Shader.TileMode.CLAMP));
                textView3.invalidate();
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b0 = new Rect();
        this.c0 = new RectF();
        this.d0 = new Rect();
        SparseArray sparseArray = new SparseArray();
        this.e0 = sparseArray;
        this.h0 = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockFaceView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateListP = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.ClockFaceView_clockNumberTextColor);
        this.o0 = colorStateListP;
        LayoutInflater.from(context).inflate(R$layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R$id.material_clock_hand);
        this.a0 = clockHandView;
        this.i0 = resources.getDimensionPixelSize(R$dimen.material_clock_hand_padding);
        int colorForState = colorStateListP.getColorForState(new int[]{R.attr.state_selected}, colorStateListP.getDefaultColor());
        this.g0 = new int[]{colorForState, colorForState, colorStateListP.getDefaultColor()};
        clockHandView.addOnRotateListener(this);
        int defaultColor = t40.Y(context, R$color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListP2 = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateListP2 != null ? colorStateListP2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b(this));
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.f0 = new c(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.m0 = strArr;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        boolean z = false;
        for (int i2 = 0; i2 < Math.max(this.m0.length, size); i2++) {
            TextView textView = (TextView) sparseArray.get(i2);
            if (i2 >= this.m0.length) {
                removeView(textView);
                sparseArray.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(R$layout.material_clockface_textview, (ViewGroup) this, false);
                    sparseArray.put(i2, textView);
                    addView(textView);
                }
                textView.setText(this.m0[i2]);
                textView.setTag(R$id.material_value_index, Integer.valueOf(i2));
                int i3 = (i2 / 12) + 1;
                textView.setTag(R$id.material_clock_level, Integer.valueOf(i3));
                z = i3 > 1 ? true : z;
                f51.o(textView, this.f0);
                textView.setTextColor(this.o0);
            }
        }
        ClockHandView clockHandView2 = this.a0;
        if (clockHandView2.F && !z) {
            clockHandView2.Q = 1;
        }
        clockHandView2.F = z;
        clockHandView2.invalidate();
        this.j0 = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_height);
        this.k0 = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_width);
        this.l0 = resources.getDimensionPixelSize(R$dimen.material_clock_size);
    }
}
