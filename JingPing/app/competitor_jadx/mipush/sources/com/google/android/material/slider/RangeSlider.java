package com.google.android.material.slider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.widget.SeekBar;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import defpackage.g80;
import defpackage.i01;
import defpackage.ld0;
import defpackage.t40;
import defpackage.w9;
import defpackage.x9;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RangeSlider extends BaseSlider<RangeSlider, Object, Object> {
    public float d1;
    public int e1;

    public static class RangeSliderState extends AbsSavedState {
        public static final Parcelable.Creator<RangeSliderState> CREATOR = new g();
        public float E;
        public int F;

        public RangeSliderState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.E);
            parcel.writeInt(this.F);
        }

        public RangeSliderState(Parcel parcel) {
            super(parcel.readParcelable(RangeSliderState.class.getClassLoader()));
            this.E = parcel.readFloat();
            this.F = parcel.readInt();
        }
    }

    public RangeSlider(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void addOnChangeListener(w9 w9Var) {
        super.addOnChangeListener(w9Var);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void addOnSliderTouchListener(x9 x9Var) {
        super.addOnSliderTouchListener(x9Var);
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public int getActiveThumbIndex() {
        return this.A0;
    }

    public int getFocusedThumbIndex() {
        return this.B0;
    }

    public int getHaloRadius() {
        return this.n0;
    }

    public ColorStateList getHaloTintList() {
        return this.K0;
    }

    public int getLabelBehavior() {
        return this.i0;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public float getMinSeparation() {
        return this.d1;
    }

    public float getStepSize() {
        return this.C0;
    }

    public float getThumbElevation() {
        return this.S0.E.m;
    }

    public int getThumbHeight() {
        return this.m0;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public int getThumbRadius() {
        return this.l0 / 2;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.S0.E.d;
    }

    public float getThumbStrokeWidth() {
        return this.S0.E.j;
    }

    public ColorStateList getThumbTintList() {
        return this.S0.E.c;
    }

    public int getThumbTrackGapSize() {
        return this.o0;
    }

    public int getThumbWidth() {
        return this.l0;
    }

    public int getTickActiveRadius() {
        return this.F0;
    }

    public ColorStateList getTickActiveTintList() {
        return this.L0;
    }

    public int getTickInactiveRadius() {
        return this.G0;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.M0;
    }

    public ColorStateList getTickTintList() {
        if (this.M0.equals(this.L0)) {
            return this.L0;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.N0;
    }

    public int getTrackHeight() {
        return this.j0;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.O0;
    }

    public int getTrackInsideCornerSize() {
        return this.s0;
    }

    public int getTrackSidePadding() {
        return this.k0;
    }

    public int getTrackStopIndicatorSize() {
        return this.r0;
    }

    public ColorStateList getTrackTintList() {
        if (this.O0.equals(this.N0)) {
            return this.N0;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.H0;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public float getValueFrom() {
        return this.x0;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public float getValueTo() {
        return this.y0;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public List<Float> getValues() {
        return super.getValues();
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        RangeSliderState rangeSliderState = (RangeSliderState) parcelable;
        super.onRestoreInstanceState(rangeSliderState.getSuperState());
        this.d1 = rangeSliderState.E;
        int i = rangeSliderState.F;
        this.e1 = i;
        setSeparationUnit(i);
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public final Parcelable onSaveInstanceState() {
        RangeSliderState rangeSliderState = new RangeSliderState(super.onSaveInstanceState());
        rangeSliderState.E = this.d1;
        rangeSliderState.F = this.e1;
        return rangeSliderState;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void removeOnChangeListener(w9 w9Var) {
        super.removeOnChangeListener(w9Var);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void removeOnSliderTouchListener(x9 x9Var) {
        super.removeOnSliderTouchListener(x9Var);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawable(Drawable drawable) {
        super.setCustomThumbDrawable(drawable);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawablesForValues(int... iArr) {
        super.setCustomThumbDrawablesForValues(iArr);
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

    public void setMinSeparation(float f) {
        this.d1 = f;
        this.e1 = 0;
        setSeparationUnit(0);
    }

    public void setMinSeparationValue(float f) {
        this.d1 = f;
        this.e1 = 1;
        setSeparationUnit(1);
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
    public /* bridge */ /* synthetic */ void setThumbHeight(int i) {
        super.setThumbHeight(i);
    }

    public void setThumbHeightResource(int i) {
        setThumbHeight(getResources().getDimensionPixelSize(i));
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
            setThumbStrokeColor(t40.Y(getContext(), i));
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
        ld0 ld0Var = this.S0;
        if (colorStateList.equals(ld0Var.E.c)) {
            return;
        }
        ld0Var.m(colorStateList);
        invalidate();
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setThumbTrackGapSize(int i) {
        super.setThumbTrackGapSize(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setThumbWidth(int i) {
        super.setThumbWidth(i);
    }

    public void setThumbWidthResource(int i) {
        setThumbWidth(getResources().getDimensionPixelSize(i));
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
        if (this.E0 != z) {
            this.E0 = z;
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

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTrackInsideCornerSize(int i) {
        super.setTrackInsideCornerSize(i);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public /* bridge */ /* synthetic */ void setTrackStopIndicatorSize(int i) {
        super.setTrackStopIndicatorSize(i);
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f) {
        this.x0 = f;
        this.J0 = true;
        postInvalidate();
    }

    public void setValueTo(float f) {
        this.y0 = f;
        this.J0 = true;
        postInvalidate();
    }

    @Override // com.google.android.material.slider.BaseSlider
    public void setValues(Float... fArr) {
        super.setValues(fArr);
    }

    public RangeSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public void setCustomThumbDrawable(int i) {
        setCustomThumbDrawable(getResources().getDrawable(i));
    }

    @Override // com.google.android.material.slider.BaseSlider
    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        super.setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // com.google.android.material.slider.BaseSlider
    public void setValues(List<Float> list) {
        super.setValues(list);
    }

    public RangeSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayD = i01.d(context, attributeSet, R$styleable.RangeSlider, i, BaseSlider.Y0, new int[0]);
        if (typedArrayD.hasValue(R$styleable.RangeSlider_values)) {
            TypedArray typedArrayObtainTypedArray = typedArrayD.getResources().obtainTypedArray(typedArrayD.getResourceId(R$styleable.RangeSlider_values, 0));
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                arrayList.add(Float.valueOf(typedArrayObtainTypedArray.getFloat(i2, -1.0f)));
            }
            setValues(arrayList);
        }
        this.d1 = typedArrayD.getDimension(R$styleable.RangeSlider_minSeparation, 0.0f);
        typedArrayD.recycle();
    }

    public /* bridge */ /* synthetic */ void setLabelFormatter(g80 g80Var) {
    }
}
