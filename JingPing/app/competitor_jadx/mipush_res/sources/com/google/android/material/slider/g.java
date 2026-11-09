package com.google.android.material.slider;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.slider.RangeSlider;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new RangeSlider.RangeSliderState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new RangeSlider.RangeSliderState[i];
    }
}
