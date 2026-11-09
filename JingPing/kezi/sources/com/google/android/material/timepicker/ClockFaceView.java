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
import defpackage.o70;
import defpackage.rp0;
import defpackage.ud;
import defpackage.xi;
import defpackage.xr0;
import java.util.Arrays;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends RadialViewGroup implements ud {
    public final int[] A;
    public final float[] B;
    public final int C;
    public final int D;
    public final int E;
    public final int F;
    public final String[] G;
    public float H;
    public final ColorStateList I;
    public final ClockHandView u;
    public final Rect v;
    public final RectF w;
    public final Rect x;
    public final SparseArray y;
    public final c z;

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public final void o() {
        super.o();
        int i = 0;
        while (true) {
            SparseArray sparseArray = this.y;
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
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(1, this.G.length, 1).c);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        p();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (this.F / Math.max(Math.max(this.D / displayMetrics.heightPixels, this.E / displayMetrics.widthPixels), 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, 1073741824);
        setMeasuredDimension(iMax, iMax);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }

    public final void p() {
        SparseArray sparseArray;
        RectF rectF;
        Rect rect;
        RectF rectF2 = this.u.h;
        float f = Float.MAX_VALUE;
        TextView textView = null;
        int i = 0;
        while (true) {
            sparseArray = this.y;
            int size = sparseArray.size();
            rectF = this.w;
            rect = this.v;
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
                textView3.getLineBounds(0, this.x);
                rectF.inset(r8.left, r8.top);
                textView3.getPaint().setShader(!RectF.intersects(rectF2, rectF) ? null : new RadialGradient(rectF2.centerX() - rectF.left, rectF2.centerY() - rectF.top, 0.5f * rectF2.width(), this.A, this.B, Shader.TileMode.CLAMP));
                textView3.invalidate();
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = new Rect();
        this.w = new RectF();
        this.x = new Rect();
        SparseArray sparseArray = new SparseArray();
        this.y = sparseArray;
        this.B = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockFaceView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateListS = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.ClockFaceView_clockNumberTextColor);
        this.I = colorStateListS;
        LayoutInflater.from(context).inflate(R$layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R$id.material_clock_hand);
        this.u = clockHandView;
        this.C = resources.getDimensionPixelSize(R$dimen.material_clock_hand_padding);
        int colorForState = colorStateListS.getColorForState(new int[]{R.attr.state_selected}, colorStateListS.getDefaultColor());
        this.A = new int[]{colorForState, colorForState, colorStateListS.getDefaultColor()};
        clockHandView.addOnRotateListener(this);
        int defaultColor = rp0.w(context, R$color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListS2 = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateListS2 != null ? colorStateListS2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b(this));
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.z = new c(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, ExtensionRequestData.EMPTY_VALUE);
        this.G = strArr;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        boolean z = false;
        for (int i2 = 0; i2 < Math.max(this.G.length, size); i2++) {
            TextView textView = (TextView) sparseArray.get(i2);
            if (i2 >= this.G.length) {
                removeView(textView);
                sparseArray.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(R$layout.material_clockface_textview, (ViewGroup) this, false);
                    sparseArray.put(i2, textView);
                    addView(textView);
                }
                textView.setText(this.G[i2]);
                textView.setTag(R$id.material_value_index, Integer.valueOf(i2));
                int i3 = (i2 / 12) + 1;
                textView.setTag(R$id.material_clock_level, Integer.valueOf(i3));
                z = i3 > 1 ? true : z;
                xr0.p(textView, this.z);
                textView.setTextColor(this.I);
            }
        }
        ClockHandView clockHandView2 = this.u;
        if (clockHandView2.c && !z) {
            clockHandView2.n = 1;
        }
        clockHandView2.c = z;
        clockHandView2.invalidate();
        this.D = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_height);
        this.E = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_width);
        this.F = resources.getDimensionPixelSize(R$dimen.material_clock_size);
    }
}
