package com.google.android.material.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.search.SearchBar;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SearchBar.SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new SearchBar.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new SearchBar.SavedState(parcel, null);
    }
}
