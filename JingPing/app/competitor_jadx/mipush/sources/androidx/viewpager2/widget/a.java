package androidx.viewpager2.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        ViewPager2.SavedState savedState = new ViewPager2.SavedState(parcel, null);
        savedState.E = parcel.readInt();
        savedState.F = parcel.readInt();
        savedState.G = parcel.readParcelable(null);
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new ViewPager2.SavedState[i];
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        ViewPager2.SavedState savedState = new ViewPager2.SavedState(parcel, classLoader);
        savedState.E = parcel.readInt();
        savedState.F = parcel.readInt();
        savedState.G = parcel.readParcelable(classLoader);
        return savedState;
    }
}
