package com.google.android.material.slider;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.google.android.material.R$attr;
import defpackage.a8;
import defpackage.b8;
import defpackage.g10;
import defpackage.rp0;
import defpackage.u50;

/* JADX INFO: loaded from: classes.dex */
public class Slider extends BaseSlider<Slider, Object, Object> {
    public Slider(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void addOnChangeListener(a8 a8Var) {
        super.addOnChangeListener(a8Var);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void addOnSliderTouchListener(b8 b8Var) {
        super.addOnSliderTouchListener(b8Var);
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public int getActiveThumbIndex() {
        return this.M;
    }

    public int getFocusedThumbIndex() {
        return this.N;
    }

    public int getHaloRadius() {
        return this.E;
    }

    public ColorStateList getHaloTintList() {
        return this.W;
    }

    public int getLabelBehavior() {
        return this.A;
    }

    public float getStepSize() {
        return this.O;
    }

    public float getThumbElevation() {
        return this.e0.b.m;
    }

    public int getThumbRadius() {
        return this.D;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.e0.b.d;
    }

    public float getThumbStrokeWidth() {
        return this.e0.b.j;
    }

    public ColorStateList getThumbTintList() {
        return this.e0.b.c;
    }

    public int getTickActiveRadius() {
        return this.R;
    }

    public ColorStateList getTickActiveTintList() {
        return this.a0;
    }

    public int getTickInactiveRadius() {
        return this.S;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.b0;
    }

    public ColorStateList getTickTintList() {
        if (this.b0.equals(this.a0)) {
            return this.a0;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.c0;
    }

    public int getTrackHeight() {
        return this.B;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.d0;
    }

    public int getTrackSidePadding() {
        return this.C;
    }

    public ColorStateList getTrackTintList() {
        if (this.d0.equals(this.c0)) {
            return this.c0;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.T;
    }

    public float getValue() {
        return getValues().get(0).floatValue();
    }

    @Override // com.google.android.material.slider.BaseSlider
    public float getValueFrom() {
        return this.J;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public float getValueTo() {
        return this.K;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public final boolean p() {
        if (getActiveThumbIndex() != -1) {
            return true;
        }
        setActiveThumbIndex(0);
        return true;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void removeOnChangeListener(a8 a8Var) {
        super.removeOnChangeListener(a8Var);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void removeOnSliderTouchListener(b8 b8Var) {
        super.removeOnSliderTouchListener(b8Var);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawable(Drawable drawable) {
        super.setCustomThumbDrawable(drawable);
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public /* bridge */ /* synthetic */ void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setFocusedThumbIndex(int i) {
        super.setFocusedThumbIndex(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setHaloRadius(int i) {
        super.setHaloRadius(i);
    }

    public void setHaloRadiusResource(int i) {
        setHaloRadius(getResources().getDimensionPixelSize(i));
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setHaloTintList(ColorStateList colorStateList) {
        super.setHaloTintList(colorStateList);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setLabelBehavior(int i) {
        super.setLabelBehavior(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setStepSize(float f) {
        super.setStepSize(f);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setThumbElevation(float f) {
        super.setThumbElevation(f);
    }

    public void setThumbElevationResource(int i) {
        setThumbElevation(getResources().getDimension(i));
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setThumbRadius(int i) {
        super.setThumbRadius(i);
    }

    public void setThumbRadiusResource(int i) {
        setThumbRadius(getResources().getDimensionPixelSize(i));
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setThumbStrokeColor(ColorStateList colorStateList) {
        super.setThumbStrokeColor(colorStateList);
    }

    public void setThumbStrokeColorResource(int i) {
        if (i != 0) {
            setThumbStrokeColor(rp0.w(getContext(), i));
        }
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setThumbStrokeWidth(float f) {
        super.setThumbStrokeWidth(f);
    }

    public void setThumbStrokeWidthResource(int i) {
        if (i != 0) {
            setThumbStrokeWidth(getResources().getDimension(i));
        }
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        u50 u50Var = this.e0;
        if (colorStateList.equals(u50Var.b.c)) {
            return;
        }
        u50Var.m(colorStateList);
        invalidate();
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTickActiveRadius(int i) {
        super.setTickActiveRadius(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTickActiveTintList(ColorStateList colorStateList) {
        super.setTickActiveTintList(colorStateList);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTickInactiveRadius(int i) {
        super.setTickInactiveRadius(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTickInactiveTintList(ColorStateList colorStateList) {
        super.setTickInactiveTintList(colorStateList);
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z) {
        if (this.Q != z) {
            this.Q = z;
            postInvalidate();
        }
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTrackActiveTintList(ColorStateList colorStateList) {
        super.setTrackActiveTintList(colorStateList);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTrackHeight(int i) {
        super.setTrackHeight(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTrackInactiveTintList(ColorStateList colorStateList) {
        super.setTrackInactiveTintList(colorStateList);
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValue(float f) {
        setValues(Float.valueOf(f));
    }

    public void setValueFrom(float f) {
        this.J = f;
        this.V = true;
        postInvalidate();
    }

    public void setValueTo(float f) {
        this.K = f;
        this.V = true;
        postInvalidate();
    }

    public Slider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public void setCustomThumbDrawable(int i) {
        setCustomThumbDrawable(getResources().getDrawable(i));
    }

    public Slider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.value});
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            setValue(typedArrayObtainStyledAttributes.getFloat(0, 0.0f));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public /* bridge */ /* synthetic */ void setLabelFormatter(g10 g10Var) {
    }
}
