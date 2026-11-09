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
import defpackage.a8;
import defpackage.b8;
import defpackage.g10;
import defpackage.rp0;
import defpackage.u50;
import defpackage.un0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RangeSlider extends BaseSlider<RangeSlider, Object, Object> {
    public float o0;
    public int p0;

    public static class RangeSliderState extends AbsSavedState {
        public static final Parcelable.Creator<RangeSliderState> CREATOR = new f();
        public float b;
        public int c;

        public RangeSliderState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.b);
            parcel.writeInt(this.c);
        }

        public RangeSliderState(Parcel parcel) {
            super(parcel.readParcelable(RangeSliderState.class.getClassLoader()));
            this.b = parcel.readFloat();
            this.c = parcel.readInt();
        }
    }

    public RangeSlider(Context context) {
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

    @Override // com.google.android.material.slider.BaseSlider
    public float getMinSeparation() {
        return this.o0;
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

    @Override // com.google.android.material.slider.BaseSlider
    public float getValueFrom() {
        return this.J;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public float getValueTo() {
        return this.K;
    }

    @Override // com.google.android.material.slider.BaseSlider
    public List<Float> getValues() {
        return super.getValues();
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        RangeSliderState rangeSliderState = (RangeSliderState) parcelable;
        super.onRestoreInstanceState(rangeSliderState.getSuperState());
        this.o0 = rangeSliderState.b;
        int i = rangeSliderState.c;
        this.p0 = i;
        setSeparationUnit(i);
    }

    @Override // com.google.android.material.slider.BaseSlider, android.view.View
    public final Parcelable onSaveInstanceState() {
        RangeSliderState rangeSliderState = new RangeSliderState(super.onSaveInstanceState());
        rangeSliderState.b = this.o0;
        rangeSliderState.c = this.p0;
        return rangeSliderState;
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
        this.o0 = f;
        this.p0 = 0;
        setSeparationUnit(0);
    }

    public void setMinSeparationValue(float f) {
        this.o0 = f;
        this.p0 = 1;
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
        TypedArray typedArrayD = un0.d(context, attributeSet, R$styleable.RangeSlider, i, BaseSlider.j0, new int[0]);
        if (typedArrayD.hasValue(R$styleable.RangeSlider_values)) {
            TypedArray typedArrayObtainTypedArray = typedArrayD.getResources().obtainTypedArray(typedArrayD.getResourceId(R$styleable.RangeSlider_values, 0));
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                arrayList.add(Float.valueOf(typedArrayObtainTypedArray.getFloat(i2, -1.0f)));
            }
            setValues(arrayList);
        }
        this.o0 = typedArrayD.getDimension(R$styleable.RangeSlider_minSeparation, 0.0f);
        typedArrayD.recycle();
    }

    public /* bridge */ /* synthetic */ void setLabelFormatter(g10 g10Var) {
    }
}
