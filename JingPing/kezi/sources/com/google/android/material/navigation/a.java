package com.google.android.material.navigation;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = new NavigationBarPresenter$SavedState();
        navigationBarPresenter$SavedState.b = parcel.readInt();
        navigationBarPresenter$SavedState.c = (ParcelableSparseArray) parcel.readParcelable(NavigationBarPresenter$SavedState.class.getClassLoader());
        return navigationBarPresenter$SavedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new NavigationBarPresenter$SavedState[i];
    }
}
