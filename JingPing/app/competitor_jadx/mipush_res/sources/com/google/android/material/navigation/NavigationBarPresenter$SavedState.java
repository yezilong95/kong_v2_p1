package com.google.android.material.navigation;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
class NavigationBarPresenter$SavedState implements Parcelable {
    public static final Parcelable.Creator<NavigationBarPresenter$SavedState> CREATOR = new a();
    public int E;
    public ParcelableSparseArray F;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.E);
        parcel.writeParcelable(this.F, 0);
    }
}
