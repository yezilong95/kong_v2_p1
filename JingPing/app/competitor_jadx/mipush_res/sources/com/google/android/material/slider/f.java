package com.google.android.material.slider;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.slider.BaseSlider;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        BaseSlider.SliderState sliderState = new BaseSlider.SliderState(parcel);
        sliderState.E = parcel.readFloat();
        sliderState.F = parcel.readFloat();
        ArrayList arrayList = new ArrayList();
        sliderState.G = arrayList;
        parcel.readList(arrayList, Float.class.getClassLoader());
        sliderState.H = parcel.readFloat();
        sliderState.I = parcel.createBooleanArray()[0];
        return sliderState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new BaseSlider.SliderState[i];
    }
}
